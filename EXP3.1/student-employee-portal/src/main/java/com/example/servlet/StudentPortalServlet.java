package com.example.servlet;

import com.example.dao.AttendanceDao;
import com.example.model.Attendance;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/student/portal")
public class StudentPortalServlet extends HttpServlet {
    private AttendanceDao attendanceDao;

    @Override
    public void init() throws ServletException {
        attendanceDao = new AttendanceDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/student/portal.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String studentId = request.getParameter("studentId");
        String date = request.getParameter("date");
        String status = request.getParameter("status");

        Attendance attendance = new Attendance();
        attendance.setStudentId(studentId);
        attendance.setDate(date);
        attendance.setStatus(status);

        attendanceDao.saveAttendance(attendance);
        response.sendRedirect(request.getContextPath() + "/student/attendance-list.jsp");
    }
}