package com.attendance;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.attendance.DatabaseConnection;
import java.sql.*;
import java.security.SecureRandom;
import java.util.Random;

/**
 * Servlet implementation class PasswordGeneration
 */
@WebServlet("/PasswordGeneration")
public class PasswordGeneration extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ResultSet resultset = DatabaseConnection.getResultFromSqlQuery("select * from students");
		try {
			HttpSession hs=request.getSession();
			while (resultset.next()) {
				String password = DatabaseConnection.randompasswordgeneration();
				DatabaseConnection.insertUpdateFromSqlQuery("update students set password='" + password+ "' where student_id='" + resultset.getInt(1) + "' ");
				String message = "System generated all students password successfully.";
				hs.setAttribute("message-success", message);
			}
			response.sendRedirect("students_password_change_dynamically.jsp");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
