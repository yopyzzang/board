package com.company.itbank;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class InitParam extends HttpServlet {
    private String company;
    private String manager;
    private String tel;
    private String email;

    @Override
    public void init() throws ServletException {
        System.out.println("init() ...");
        //Get ServletContext param
        company = getServletContext().getInitParameter("company");
        manager = getServletContext().getInitParameter("manager");

        //Get ServletConfig param
        tel = getServletConfig().getInitParameter("tel");
        email = getServletConfig().getInitParameter("email");
    }

    protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<html>");
            out.println("<body>");
            out.println("<li>회사명 : " + company + "</li>");
            out.println("<li>담당자 : " + manager + "</li>");
            out.println("<li>전화번호 : " + tel + "</li>");
            out.println("<li>이메일 : " + email + "</li>");
            out.println("</body>");
            out.println("</html>");
        }finally {
            out.close();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    processRequest(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
processRequest(request,response);
    }
}
