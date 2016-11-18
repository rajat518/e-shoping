package src;

import java.sql.*;
import java.io.*;
 import javax.servlet.*;
 import javax.servlet.http.*;
 import java.util.*;

public class empdel extends HttpServlet
{
public static Connection dbconn;
public static Statement stmt;
 public void doGet(HttpServletRequest rq,HttpServletResponse rs) throws IOException,ServletException
{
 try
 {
 Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
 
 dbconn=DriverManager.getConnection("jdbc:odbc:project","scott","tiger");
 stmt=dbconn.createStatement();
PrintWriter pw=rs.getWriter();
  Statement st=dbconn.createStatement();
//pw.print("Driver connected");
   String s1=rq.getParameter("t1");  
   pw.print("1");
  String sql="delete from emp_details where emp_no='"+s1+"'";     

st.executeUpdate(sql);  

//RequestDispatcher rd=rq.getRequestDispatcher("html");
//			rd.forward(rq,rs);

stmt.close();
dbconn.commit();
dbconn.close();
pw.print("<html><body>");	
pw.print("<center>");
pw.print("<h2><i>Database updated<i></h2>");
pw.print("<a href='admin.html'><h3><B>BACK</B></h3></a>");
pw.print("</center></body></html>");

}

catch(Exception e)
{
 System.out.println(e);

}
}
}