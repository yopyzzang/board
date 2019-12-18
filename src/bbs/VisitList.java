package bbs;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class VisitList extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<html>");
            out.println("<head><title>방명록 리스트</title></head>");
            out.println("<body>");
            StringBuffer sql = new StringBuffer();
            sql.append("select no,writer,memo,regdate ");
            sql.append("from visit ");
            sql.append("order by no desc ");
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                connection = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521/XEPDB1","yopyzzang","1234");
                preparedStatement = connection.prepareStatement(sql.toString());
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    int no = resultSet.getInt("no");
                    String writer = resultSet.getString("writer");
                    String memo = resultSet.getString("memo");
                    java.sql.Date regdate = resultSet.getDate("regdate");
                    out.println("<table align=center width=500 border=1>");
                    out.println("<tr>");
                    out.println("<th width=50>번호</th>");
                    out.println("<td width=50 align=center>"+no+"</td>");
                    out.println("<th width=70>작성자</th>");
                    out.println("<td width=180 align=center>"+writer+"</td>");
                    out.println("<th width=50>날짜</th>");
                    out.println("<td width=100 align=center>"+regdate+"</td>");
                    out.println("</tr>");
                    out.println("<tr>");
                    out.println("<th width=50>내용</th>");
                    out.println("<td colspan=5>&nbsp;<textarea rows=3 cols=50>"
                    +memo+"</textarea></td>");
                    out.println("</tr>");
                    out.println("</table>");
                    out.println("<p>");
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                if(preparedStatement!=null)
                    preparedStatement.close();
                if(connection!=null)
                    connection.close();
                if(resultSet!=null)
                    resultSet.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    processRequest(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }
}
