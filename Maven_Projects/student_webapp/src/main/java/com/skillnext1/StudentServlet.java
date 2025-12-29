package com.skillnext1;

import com.skillnext1.StudentDAO;
import com.skillnext1.Student;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class StudentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html");
        resp.getWriter().println("<h2>Student Servlet is WORKING</h2>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        int sem = Integer.parseInt(req.getParameter("sem"));
        String dept = req.getParameter("dept");

        Student stu = new Student();
        stu.setId(id);
        stu.setName(name);
        stu.setSem(sem);
        stu.setDept(dept);

        StudentDAO.save(stu);

        res.sendRedirect("success.jsp");
    }
}