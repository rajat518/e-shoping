package src;

import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;
import java.sql.*;
import java.lang.*;
public class jempinsert extends HttpServlet
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
   String empid=req.getParameter("EmployeeId");
   String ename=req.getParameter("ename");
    String jobid=req.getParameter("JobId");
   String branchid=req.getParameter("branchid");
   String address=req.getParameter("address");
   
String city=req.getParameter("city");
String state=req.getParameter("state");
   String country=req.getParameter("country");
  String mobno=req.getParameter("mobileno");

String emailid=req.getParameter("emailid"); 
String password=req.getParameter("password");     
String salary=req.getParameter("Salary");
String s16=req.getParameter("mon");

                String s17=req.getParameter("dd");
				
		String s18=req.getParameter("year");
		
String p=s16.concat("-");

String q=p.concat(s17);

p=q.concat("-");

String dob1=p.concat(s18);

  pw.println("3");
   String qry ="insert into emp_details values('"+empid+"','"+ename+"','"+jobid+"','"+branchid+"','"+address+"','"+city+"','"+state+"','"+country+"','"+mobno+"','"+dob1+"','"+emailid+"','"+password+"',"+salary+")";
  
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