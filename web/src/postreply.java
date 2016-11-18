package src;

import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;
import java.sql.*;
import java.lang.*;
public class postreply extends HttpServlet
{
 Connection con=null;
 Statement ps=null;
 ResultSet rs=null;
 public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
 {
  res.setContentType("text/html");
  PrintWriter pw=res.getWriter();
 pw.println("1");
  try
  {
   Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
   con=DriverManager.getConnection("jdbc:odbc:project","scott","tiger");		
   ps=con.createStatement();
   RequestDispatcher rd;
   pw.println("2");
   String uid=req.getParameter("uid");
   String mess=req.getParameter("response");
String compid=req.getParameter("compid");

   //String date=req.getParameter("dd");
       pw.println("3"+uid+"fasdf"+mess);
   String qry = " insert into replymsg(reply,user_id) values ('"+mess+"','"+uid+"')";
   pw.println(qry);
   int i=ps.executeUpdate(qry);
if(i!=0)
	ps.executeUpdate("update complaints set status=1 where user_id='"+uid+"' and comp_id='"+compid+"'");
   pw.println("4");
  con.commit();

pw.println("<html><body>");
pw.println("<center>");
pw.println("<h3>inserted</h3>");
pw.println("<h1><a href='edit11.html'><i>return...</i></a></h1>");
pw.println("</center>");
pw.println("</body></html>");
 
   }
   catch(ClassNotFoundException e)
    {
     pw.println("exception"+e);
    }
   catch(SQLException e)
    {
     pw.println("SQL EXCEPTION");	
     pw.println("TRY AGAIN");
    }
   catch(Exception e)
    {
     pw.println("can't load driver"+e.getMessage());
    }
}
}