package com.attendance;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class ManageStudents
 */
@WebServlet("/ManageStudents")
public class ManageStudents extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int statusMode = 0;
			Connection con = DatabaseConnection.getConnection();
			Statement st = con.createStatement();
			Statement statement = con.createStatement();
			ResultSet rs = st.executeQuery("select status from students where student_id='" + request.getParameter("id") + "'");
			while (rs.next()) {
				if (rs.getInt(1) == 1) {
					statusMode = statement.executeUpdate("update students set status=0 where student_id='" + request.getParameter("id") + "'");
				} else {
					statusMode = statement.executeUpdate("update students set status=1 where student_id='" + request.getParameter("id") + "'");
				}
			}
			if (statusMode > 0) {
				response.sendRedirect("manage_students.jsp");
			} else {
				response.sendRedirect("manage_students.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
