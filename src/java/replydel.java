

import java.sql.*;
import java.io.*;
 import javax.servlet.*;
 import javax.servlet.http.*;
 import java.util.*;

public class replydel extends HttpServlet
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

   String s1=rq.getParameter("msgid");  
   int vals=Integer.parseInt(s1);
   
  String sql="delete from replymsg where sno="+vals;     
  st.executeUpdate(sql);  
pw.println("2");
//RequestDispatcher rd=rq.getRequestDispatcher("html");
//			rd.forward(rq,rs);
pw.print("<html><body>");
			
pw.print("<h3><i>Database updated</i></h3>");
pw.println("</body></html>");
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