package Project.SRC;

import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;
import java.sql.*;
import java.lang.String.*;
public class Forgot extends HttpServlet
{	String emailidee=null;
        String custid3=null;
        String pass3=null;
	String x=null;
        Connection con=null;
	Statement ps=null;	
	ResultSet rs=null;
	int flag,row;
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{
			res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
	     
 		try
		{
pw.println("hello");		
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        pw.println("connection");
	    con=DriverManager.getConnection("jdbc:odbc:ibm2","scott","tiger");		
		pw.println("before prep");
                ps=con.createStatement();
		pw.println("after prep");
	RequestDispatcher rr;	
		
		
		emailidee=req.getParameter("email");
		
		String sques=req.getParameter("secque");
		String sans=req.getParameter("secans");
		String s16=req.getParameter("month");
		String s17=req.getParameter("day");
		String s18=req.getParameter("year");
String p=s17.concat("-");
String q=p.concat(s16);
p=q.concat("-");
String dob1=p.concat(s18);
/*
String p=s16.concat(s17);
String dob=p.concat(s18);*/


	int p1=0;
                ResultSet rs=ps.executeQuery("select cust_id,password,s_ques,s_ans,dob from customer where emailid='"+emailidee+"'");
pw.println("test0");	
while(rs.next())
	{		
			
			custid3=rs.getString(1);
			pass3=rs.getString(2);
			
			pw.println("testsd");
			if((sques.equals(rs.getString(3))) && (sans.equals(rs.getString(4)))&&(dob1.equals(rs.getString(5))))
				{
				p1=1; pw.println("test2");
                                     	pw.println("  "+custid3);
					pw.println("  "+pass3);
					/* custid3=rs.getString(3); pw.println("test7");
					 pass3=rs.getString(4);*/
/*			                 rd= req.getRequestDispatcher("sign.html");
				           rd.forward(req,res);*/

  			         }
			 }


/*if(p1==1)
{       pw.println("test3");
	pw.println("<html><body><table><tr><td>'+custid+','+pass+'</td></tr></table>");
	pw.println("</body></html>");
	pw.close();
}*/
if(p1==0)
				{
				pw.println("<html><body>");
pw.println("<center><br><br><br><br><br><br><h1><b>Invalid details entered</b></h1><br>");
	pw.println("<a href='forgotpwd.htm'><b>TRY AGAIN</b></a>");
			pw.println("</center></body></html>");	
			pw.close();
		
				}
		}	
   
		catch(ClassNotFoundException e)
		{
		pw.println("exception"+e);
		}
		/*catch(SQLException e)
		{
			pw.println("USER ID ALREADY EXISTS");	
			pw.println("TRY WITH DIFFERENT USER ID");
		}*/
		     	catch(Exception e)
		{
		pw.println("can't load driver"+e.getMessage());
		}
  }
}