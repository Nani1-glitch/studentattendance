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
 * Servlet implementation class StudentLeave
 */
@WebServlet("/StudentLeave")
public class StudentLeave extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = 0;
		HttpSession session=request.getSession();
		String name=(String)session.getAttribute("StudentName");
		String message = request.getParameter("message");
		int days = Integer.parseInt(request.getParameter("days"));
		String leave_status = "Pending";
		try {
			Connection connection = DatabaseConnection.getConnection();
			Statement statement = connection.createStatement();
			int applyLeave = statement.executeUpdate("insert into student_leave(id,name,leave_reasone,no_of_days,leave_status,leave_apply_date) values('" + id+ "','" + name + "','" + message + "','" + days + "','" + leave_status + "',CURDATE())");
			if (applyLeave > 0) {
				String leave="Leave apply submitted.";
				session.setAttribute("leave-apply",leave);
				response.sendRedirect("apply_for_leave.jsp");
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
