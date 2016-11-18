package src;

import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;
import java.sql.*;
import java.lang.*;
public class supinsert extends HttpServlet
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
   //pw.println("2");
   String supp_id=req.getParameter("supp_id");
   //pw.print(supp_id);
           String supp_name=req.getParameter("supp_name");	
	//	   pw.print(supp_name);
				String address=req.getParameter("address"); 
	//			pw.print(address);
				String city=req.getParameter("city");
	//			pw.print(city);
				String state=req.getParameter("state");
	//			pw.print(state);
				String country=req.getParameter("country");
	//			pw.print(country);
	            String mob_no=req.getParameter("mob_no");
	//			pw.print(mob_no);
				String mailid=req.getParameter("mailid");
	//			pw.print(mailid);
			
 //pw.println("3");
   String qry ="insert into supplier_details values('"+supp_id+"','"+supp_name+"','"+address+"','"+city+"','"+state+"','"+country+"','"+mob_no+"','"+mailid+"')";
  //pw.print(qry);
  ps.executeUpdate(qry);
  pw.println("4");
  con.commit();
pw.println("<html><body>");
pw.println("<center>");
pw.println("<h2>updated</h2>");
pw.println("<a href='admin.html'><b><h3>BACK</h3></b></a>");
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