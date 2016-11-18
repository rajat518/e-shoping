package src;

import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;
import java.sql.*;
import java.lang.String.*;
public class regis extends HttpServlet
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
        	
		con=DriverManager.getConnection("jdbc:odbc:project","scott","tiger");		
		
		ps=con.createStatement();
		RequestDispatcher rr;	
		String loginid=req.getParameter("user_id");
	                	String password=req.getParameter("password");
	                	String s13=req.getParameter("t2");
	                	String iname=req.getParameter("name");
     			    	String coname=req.getParameter("company");
	                	int  phno=Integer.parseInt(req.getParameter("ph_no")); 
		        		int  mobno=Integer.parseInt(req.getParameter("mob_no")); 
                        String address =  req.getParameter("Add1");
                        String city=req.getParameter("city");
		                String state=req.getParameter("state");		
		                int  pin=Integer.parseInt(req.getParameter("pin"));
    			    	String country=req.getParameter("country");
                        String s_ques=req.getParameter("secque");                                         
			     		String s_ans=req.getParameter("secans");
				String s16=req.getParameter("mon");
				String s17=req.getParameter("dd");
				String s18=req.getParameter("year");
		String p=s17.concat("-");
                                   String q=p.concat(s16);
 		p=q.concat("-");  
		String dob=p.concat(s18);
                pw.println("connection");
            
		String qry = "insert into user_details values('"+iname+"','"+loginid+"','"+password+"','"+coname+"',"+phno+","+mobno+",'"+address+"','"+city+"','"+state+"','"+country+"','"+s_ques+"','"+s_ans+"','"+dob+"',"+pin+")";
		row=ps.executeUpdate(qry);
                 pw.println("insert");                  
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
			pw.println("<a href='registration.html'>please register again</a>");
			pw.println("</body></html>");
			
		}
		     	catch(Exception e)
		{
		pw.println("can't load driver"+e.getMessage());
		}
  }
}