package com.attendance;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class StudentFeedback
 */
@WebServlet("/StudentFeedback")
public class StudentFeedback extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int fid = 0;
		HttpSession session = request.getSession();
		String uname = (String) session.getAttribute("uname");
		String full_name = request.getParameter("full_name");
		String mobile = request.getParameter("mobile");
		String feedback = request.getParameter("message");
		try {
			Connection connection = DatabaseConnection.getConnection();
			Statement statement = connection.createStatement();
			int applyLeave = statement.executeUpdate("insert into student_feedback(fid,full_name,mobile,feedback,uname) values('" + fid+ "','" + full_name + "','" + mobile + "','" + feedback + "','" + uname + "')");
			if (applyLeave > 0) {
				String message = "Feedback submitted successfully.";
				session.setAttribute("student-feedback", message);
				response.sendRedirect("student_feedback.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
