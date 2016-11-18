

import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;
import java.sql.*;
import java.lang.String.*;
public class forgot1 extends HttpServlet
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
	
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        
	    con=DriverManager.getConnection("jdbc:odbc:project","scott","tiger");		
		
                ps=con.createStatement();
		
		
		
		 String emailide=req.getParameter("email");
		
		String sques=req.getParameter("secque");
		
		String sans=req.getParameter("secans");
		
		String s16=req.getParameter("mon");

                String s17=req.getParameter("dd");
				
		String s18=req.getParameter("year");
		
String p=s16.concat("-");

String q=p.concat(s17);

p=q.concat("-");

String dob1=p.concat(s18);


/*
String p=s16.concat(s17);
String dob=p.concat(s18);*/


	int p1=0;
	ResultSet rs=ps.executeQuery("select user_id,password ,ques,ans,birthday from user_details where user_id='"+emailide+"'");
	
while(rs.next())
	{		
			custid3=rs.getString(1);
			pass3=rs.getString(2);
			String sq=rs.getString(3);
			String sa=rs.getString(4);
			String db=rs.getString(5);
			
			if((sques.equals(sq)) && (sans.equals(sa)) && (dob1.equals(db)))
				{
				
				p1=1;
					 
                                     	pw.println("user id: "+"  "+custid3);
                                        
					pw.println("password:"+ "  "+pass3);
                                                      
  			         }
                                        pw.println("<html><body>");
					pw.println("<table align='right'><tr><td>");
					pw.println("<a href='login.html'><b>BACK</b></a>");
                                        pw.println("</td></tr></table>");
					pw.println("</body></html>");	
 			 
}


if(p1==0)
				{
				pw.println("<html><body>");
pw.println("<center><br><br><br><br><br><br><h1><b>Invalid details entered</b></h1><br>");
	pw.println("<a href='ff.html'><b>TRY AGAIN</b></a>");
			pw.println("</center></body></html>");	
			pw.close();
		
				}
		}	
   
		catch(ClassNotFoundException e)
		{
		pw.println("exception"+e);
		}
		
		     	catch(Exception e)
		{
		pw.println("can't load driver"+e.getMessage());
		}
  }
}