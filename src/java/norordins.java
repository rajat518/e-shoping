

import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;
import java.sql.*;
import java.lang.*;
public class norordins extends HttpServlet
{
 Connection con=null;
 Statement ps=null;
 ResultSet rs=null;
 public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
 {
  res.setContentType("text/html");
  PrintWriter pw=res.getWriter();
// pw.println("1");
  try
  {
   Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
   con=DriverManager.getConnection("jdbc:odbc:project","scott","tiger");		
   ps=con.createStatement();
   RequestDispatcher rd;
  // pw.println("2");
    String productname=req.getParameter("t1"); 
	pw.print(productname);
   String productid=req.getParameter("t2");
   String supplierid=req.getParameter("t3");
    String branchid=req.getParameter("t4");
   String quantity=req.getParameter("t5");
  
 // pw.println("3");
   String qry ="insert into sup_order(pro_name,pro_id,supp_id,branch_id,quantity) values('"+productname+"','"+productid+"','"+supplierid+"','"+branchid+"','"+quantity+"')";
  //pw.print(qry);
ps.executeUpdate(qry);
  
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