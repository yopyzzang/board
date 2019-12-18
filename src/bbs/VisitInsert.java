package bbs;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

public class VisitInsert extends HttpServlet {

    protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String writer = request.getParameter("writer");
        String memo = request.getParameter("memo");
        System.out.println("작성자 : " + writer);
        System.out.println("내용 : " + memo);
        String sql = "insert into visit(no,writer,memo,regdate) values(visit_seq.nextval,?,?,sysdate)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        connection = DriverManager.getConnection(
        "jdbc:oracle:thin:@localhost:1521/XEPDB1","yopyzzang","1234");
        preparedStatement = connection.prepareStatement(sql.toString());
        preparedStatement.setString(1,writer);
        preparedStatement.setString(2,memo);
        preparedStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try{
                if(preparedStatement!=null)
                    preparedStatement.close();
                if(connection!=null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        response.sendRedirect("VisitList");
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    processRequest(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    processRequest(request,response);
    }
}
