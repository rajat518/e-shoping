

import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;
import java.sql.*;
import java.lang.*;
public class suporder extends HttpServlet
{
 Connection con=null;
 Statement ps=null;
 Statement ps1=null;	

 public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
 {
  res.setContentType("text/html");
  PrintWriter pw=res.getWriter();
 //pw.println("1");
  try
  {
   Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
   con=DriverManager.getConnection("jdbc:odbc:project","scott","tiger");		
   ps=con.createStatement();
   ps1=con.createStatement();
   RequestDispatcher rd;
  // pw.println("2");
  String id[]=req.getParameterValues("n");
   int len=id.length;
   //pw.print(len);

   
   for(int i=0;i<len;i++)
   {
	   pw.print("entered for"+i);
	  // pw.println(id[i]);
String img=req.getParameter("img["+id[i]+"]");
   String pid=req.getParameter("pid["+id[i]+"]");
   String bid=req.getParameter("bid["+id[i]+"]");
    String sid=req.getParameter("sid["+id[i]+"]");
   String qty=req.getParameter("t["+id[i]+"]");
  // String pri=req.getParameter("s_pri["+id[i]+"]");
  String qry ="select * from  sup_order";
   ps.executeUpdate(qry);
   }

  pw.println("<html><center>");
  pw.println("<i><h3>ur order has been sent</h3></i>");
  pw.println("</centter></html>");
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
	}}