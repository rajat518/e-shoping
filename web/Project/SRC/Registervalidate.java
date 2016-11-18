package Project.SRC;

import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;
import java.sql.*;
import java.lang.String.*;
public class Registervalidate extends HttpServlet
{
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
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
        	
		con=DriverManager.getConnection("jdbc:odbc:PROJECT","scott","tiger");		
		ps=con.createStatement();
		RequestDispatcher rr;	
		String loginid=req.getParameter("loginid");
	    String password=req.getParameter("pwd1");
	                	String s13=req.getParameter("pwd2");
	                	String iname=req.getParameter("iname1");
		String coname=req.getParameter("Cname");
	                	String phno =req.getParameter("phno"); 
		String email_id=req.getParameter("eid");
                                   String address =  req.getParameter("Add1");
               		String city=req.getParameter("city");
		String state=req.getParameter("state");		
		String Pin=req.getParameter("pin");
                                   String Phno=req.getParameter("phno");   		
		String s_ques=req.getParameter("secque");
		String s_ans=req.getParameter("secans");
		String s16=req.getParameter("month");
		String s17=req.getParameter("day");
		String s18=req.getParameter("year");
		String p=s17.concat("-");
                                   String q=p.concat(s16);
 		p=q.concat("-");  
		String doe=p.concat(s18);

                  
String qry = "insert into customer values('"+loginid+"','"+password+"','"+coname+"','"+address+"','"+city+"','"+state+"','"+Pin+"','"+Phno+"','"+email_id+"','"+s_ques+"','"+s_ans+"','"+doe+"');";
		row=ps.executeUpdate(qry);
                                   
		con.commit();
	                rr= req.getRequestDispatcher("login.html");
	                rr.forward(req,res);

                } 	
   
		catch(ClassNotFoundException e)
		{
		pw.println("exception"+e);
		}
		catch(SQLException e)
		{
			
			pw.println("<html><body>");
			pw.println("USER ID ALREADY EXISTS");	
			pw.println("TRY WITH DIFFERENT USER ID");
			pw.println("<a href=registration.html>please register again</a>");
			pw.println("</body></html>");
			
		}
		     	catch(Exception e)
		{
		pw.println("can't load driver"+e.getMessage());
		}
  }
}