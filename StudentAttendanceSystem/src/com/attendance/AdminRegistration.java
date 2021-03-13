package com.attendance;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;

/**
 * Servlet implementation class AdminRegistration
 */
@WebServlet("/AdminRegistration")
public class AdminRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = 0;
		HttpSession hs=request.getSession();
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String uname = request.getParameter("uname");
		String password = request.getParameter("passwd");
		try {
			Connection con = DatabaseConnection.getConnection();
			Statement st = con.createStatement();
			int i = st.executeUpdate("insert into admin(id,admin_name,email,uname,password)values('" + id + "','" + name+ "','" + email + "','" + uname + "','" + password + "')");
			if (i > 0) {
				String message="Account register successfully.";
				hs.setAttribute("message-success", message);
				response.sendRedirect("index.jsp");
			} else {
				response.sendRedirect("index.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
