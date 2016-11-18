package src;

import java.sql.*;
import java.io.*;
 import javax.servlet.*;
 import javax.servlet.http.*;
 import java.util.*;

public class del extends HttpServlet
{
public static Connection dbconn;
public static Statement stmt;
 public void doPost(HttpServletRequest rq,HttpServletResponse rs) throws IOException,ServletException
{
 try
 {
 Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
 dbconn=DriverManager.getConnection("jdbc:odbc:project","scott","tiger");
 stmt=dbconn.createStatement();
PrintWriter pw=rs.getWriter();
  Statement st=dbconn.createStatement();
pw.print("Driver connected");
   String s1=rq.getParameter("t1");       
  String sql="delete  from user_details where user_id='"+s1+"'";     

st.executeUpdate(sql);  

RequestDispatcher rd=rq.getRequestDispatcher("customer.html");
			rd.forward(rq,rs);
			
pw.print("Database updated");
stmt.close();
dbconn.commit();
dbconn.close();


}

catch(Exception e)
{
 System.out.println(e);

}
}
}