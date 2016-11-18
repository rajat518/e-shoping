

import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;
import java.sql.*;
import java.lang.*;
public class proinsert extends HttpServlet
{
 Connection con=null;
 Statement ps=null;
 ResultSet rs=null;
 public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
 {
  res.setContentType("text/html");
  PrintWriter pw=res.getWriter();
 
  try
  {
   Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
   con=DriverManager.getConnection("jdbc:odbc:project","scott","tiger");		
   ps=con.createStatement();
   RequestDispatcher rd;
  
   String path=req.getParameter("path");
   int pro_price=Integer.parseInt(req.getParameter("pro_price"));
	int num_of_items=Integer.parseInt(req.getParameter("num_of_items"));
    String pro_id=req.getParameter("pro_id");
    String branch_id=req.getParameter("branch_id");
    String supp_id=req.getParameter("supp_id");
    String description=req.getParameter("description");
    int buffer=Integer.parseInt(req.getParameter("buffer"));
    int status=Integer.parseInt(req.getParameter("status"));
	String category=req.getParameter("category");
	String pro_name=req.getParameter("pro_name");
	int max=Integer.parseInt(req.getParameter("max"));

   String qry ="insert into products values('"+path+"',"+pro_price+","+num_of_items+",'"+pro_id+"','"+branch_id+"','"+supp_id+"','"+description+"',"+buffer+","+status+",'"+category+"','"+pro_name+"',"+max+")";
ps.executeUpdate(qry);
   con.commit();
 
pw.println("<html><body>");
pw.println("<center>");
pw.println("<h2>updated</h2>");
pw.println("<a href='admin1.html'><b><h3>BACK</h3></b></a>");
pw.println("<a href='proinsert.jsp'><i><h3>what to insert another product?</h3></i></a>");
pw.println("</center></body></html>");
	 
   }
   catch(ClassNotFoundException e)
    {
     pw.println("exception"+e);
    }
   catch(SQLException e)
    {
     pw.println("e");	
     pw.println("TRY AGAIN");
    }
   catch(Exception e)
    {
     pw.println("can't load driver"+e.getMessage());
    }
}
}