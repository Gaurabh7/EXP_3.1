package com.example.servlet;

import com.example.dao.EmployeeDao;
import com.example.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/employee")
public class EmployeeServlet extends HttpServlet {
    private EmployeeDao employeeDao;

    @Override
    public void init() throws ServletException {
        employeeDao = new EmployeeDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String employeeId = request.getParameter("id");
        List<Employee> employees;

        if (employeeId != null && !employeeId.isEmpty()) {
            Employee employee = employeeDao.getEmployeeById(Integer.parseInt(employeeId));
            request.setAttribute("employee", employee);
        } else {
            employees = employeeDao.getAllEmployees();
            request.setAttribute("employees", employees);
        }

        request.getRequestDispatcher("/employee.jsp").forward(request, response);
    }
}