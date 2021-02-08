package com.busvancar.cinema;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddsessionServlet
 */
public class AddsessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddsessionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	private void processData(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		MovieSession mSession = new MovieSession();
		MovieSessionDAO msDao = new MovieSessionDAO();
		mSession.setMovieId(Integer.parseInt(request.getParameter("movieId")));
		mSession.setPrice(Double.parseDouble(replaceCommas(request.getParameter("sessionPrice"))));
		
		String dateTime = request.getParameter("sessionDate")+" "+request.getParameter("sessionTime");
		
		
		Timestamp timestamp = null;

		try{
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		    Date parsedDate = sdf.parse(dateTime);
		    timestamp = new Timestamp(parsedDate.getTime());
		    mSession.setDateTime(timestamp);
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		try {
			if(msDao.insertMovieSession2db(mSession)) {
				out.print("<div style=\"color:green\">The movie session was successfully inserted</div>");
				out.print("<div>Time and date: "+String.format("%te %1$tB, %1$tY (%1$TH:%1$TM)", mSession.getDateTime().toLocalDateTime())+"</div>");
				out.print("<div>Price: "+mSession.getPrice()+"</div>");
				
			} else {
				out.print("<div style=\"color:red\">The movie session was not inserted</div>");
			}
			out.print("<div><a href=\"edit?m="+mSession.getMovieId()+"\">Continue...</div>");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processData(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private String replaceCommas(String number) {
		return number.replace(',','.');
	}

}