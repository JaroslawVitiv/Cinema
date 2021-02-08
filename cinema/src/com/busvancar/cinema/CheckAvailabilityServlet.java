package com.busvancar.cinema;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CheckAvailabilityServlet
 */
public class CheckAvailabilityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final int ROWS = 12;
	private final int SEATS = 96;
	private double basicPrice;
	private double priceIncrementRate;
	
	private double getBasicPrice(){
		return basicPrice;
	}
	
	private void setbasicPrice( double basicPrice) {
		this.basicPrice =  basicPrice;
	}
     
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckAvailabilityServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void processData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		MovieSessionDAO movieDao = new MovieSessionDAO();
		MovieSession ms = null;
		
		HttpSession session = request.getSession();
		response.setContentType("text/html");  
		
		
		//List<Ticket> tickets;
		StringBuilder cinemaHall = new StringBuilder();
		String path;
		try {
				ms = movieDao.getMovieSession(Integer.parseInt(request.getParameter("movie_session")));
				path = "images" + File.separator +ms.getMoviePoster();
				
				cinemaHall.append("<div >");

				cinemaHall.append("<img src=\"");
				cinemaHall.append(path);
				cinemaHall.append("\" width=\"30%\" />");
				
				cinemaHall.append("<h1><u><b>"+ms.getMovieTitle()+"</b></u></h1>");
				cinemaHall.append("<h2>"+ms.getMovieDescriptionEn()+"</h2>");
				cinemaHall.append("<h2>"+ms.getMovieDescriptionUa()+"</h2>");
				
				cinemaHall.append("<h3>Duration: <b>"+ms.getMovieDuration()+"</b> minutes</h3>");
				cinemaHall.append("<h4>Genre: <b>"+ms.getMovieGenre()+"</b> </h4>");
				cinemaHall.append("</div>");


				setbasicPrice(ms.getPrice());

				cinemaHall.append("<hr/>");

				cinemaHall.append(getSeats(SEATS));
			
				basicPrice = ms.getPrice();
			request.setAttribute("cinemaHall", cinemaHall.toString());
			
			StringBuilder logingBoard = new StringBuilder();
			if(session.getAttribute("firstName")==null) {
				logingBoard.append(" <a class=\"btn btn-lg btn-outline-info\" href=\"signin.jsp\">Sign in</a> "
										+ " <a class=\"btn btn-lg btn-outline-info\" href=\"login.jsp\">Log in</a> ");
			}else {
				logingBoard.append("Hi, ");
				logingBoard.append(session.getAttribute("firstName"));
				logingBoard.append("! <a href=\"logout\">Log out</a>");
				logingBoard.append(" | <a href=\"addmovie.jsp\">Add a new Movie</a>");
			}
			
			request.setAttribute("logingBoard", logingBoard.toString());
			request.setAttribute("autos", getAutoGrid(ROWS));
			request.getRequestDispatcher("availability.jsp").forward(request,response);
			
		} catch (SQLException | ServletException | IOException e) {
				e.printStackTrace();
	    }
		
		
		
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/availability.jsp");
		rd.include(request, response);
	}
	

	private String getAutoGrid(int rows) {
		StringBuilder autoGrid = new  StringBuilder();
		int row=0;
		while(rows>row++) {
			autoGrid.append(" auto ");
		}
		return autoGrid.toString();
	}

	

	private String getSeats(int seats) {
		StringBuilder seatsLine = new StringBuilder();
		double priceIncrementRate = 1;
		DecimalFormat df = new DecimalFormat("#.##");
		df.setRoundingMode(RoundingMode.CEILING);
		
		seatsLine.append("<div id=\"screen-container\">"
							+ "<div id=\"screen\"> SCREEN  </div>"
							+ "</div>");
			
		
		seatsLine.append("<div id=\"cinemaHall\" class=\"grid-container\">");
		int seat = 0;
		while(seats > seat) {
			
				
			if(seat==55 || seat==77)
				seatsLine.append(getSeat(seat, Double.parseDouble(df.format(getBasicPrice() * priceIncrementRate)), false, true));
			else
				seatsLine.append(getSeat(seat, Double.parseDouble(df.format(getBasicPrice() * priceIncrementRate)), true, false));
			
			seat++;
			
			if(seat % ROWS == 0) {
				priceIncrementRate += 0.049;
			}
		}
		seatsLine.append("</div>");

		return seatsLine.toString();
	}

	private String getSeat(int seatNumber, double price,  boolean available, boolean isPaid) {
		String color = "danger";
		String vacant = "false";
		String paid = "false";
		String disabled = "";
		if(isPaid) {
			paid = " true ";
			disabled = " disabled ";
		}
		
		if(available) {
			color="success";
			vacant = "true";
		}
		return " <div><span id=\"seat"+(seatNumber+1)+"\" ><button onclick=\"add2cart("+(seatNumber+1)+", "+price+", "+vacant+", "+paid+")\" class=\"btn btn-sm btn-"+color+"\"  "+disabled+">"+(seatNumber+1)+" <hr/> Price:<br/>"+price+"</button></span></div> ";
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processData(request, response);
	}

}