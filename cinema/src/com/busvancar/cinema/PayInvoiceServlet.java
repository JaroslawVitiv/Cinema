package com.busvancar.cinema;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class PayInvoiceServlet
 */
public class PayInvoiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PayInvoiceServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void processData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
    	HttpSession session = request.getSession();
		if(session.getAttribute("session_token") == null) {
			session.setAttribute("session_token", UUID.randomUUID().toString());
		}
    	String sessionToken =   request.getParameter("session_token");
		User user = (User) session.getAttribute("user");
		
			PrintWriter out = response.getWriter();
		
		TicketDAO tDao = new TicketDAO();
		double total = 0;
		if(tDao.setUserId2pay(user, sessionToken)) {
			total = tDao.getTodaysSum(user, sessionToken);
			out.print("<h3 style=\"color:green\">Thank you for buying tickets of our VitivCinema</h3>");
			out.print("<h4 style=\"color:green\">You paid us "+total+" $$ </h4>");
		}else {
			out.print("<h3 style=\"color:red\">Sorry, the payment is not through. Your payment is declined...:(</h3>");
		}
    		
    }
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processData(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		processData(request, response);
//	}

}
