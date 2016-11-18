

import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;
import java.sql.*;
public class custformdata1 extends HttpServlet
{
        Connection con=null;
	Statement ps=null;	
	ResultSet rs=null;
	
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException ,ServletException
	{
			res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
			HttpSession ses=req.getSession();	
 		try
		{
		
     		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");    	 
	pw.println("connection");

con=DriverManager.getConnection("jdbc:odbc:project","scott","tiger");
		
		pw.println("before prep");
                ps=con.createStatement();
		pw.println("after prep");
		
			}
		catch(ClassNotFoundException e)
		{
		pw.println("exception"+e);
		}
		catch(SQLException e)
		{
			pw.println("exception"+e);	
		}
                catch(Exception e)
		{
			pw.println("exception"+e);	
		}
	    	try
	      	{
				
	
				pw.println("hello"+ses.getAttribute("cuser_id"));
                    
					int p=0;
                rs=ps.executeQuery(" select name,user_id,company,mob_no,address,city,state,country from user_details where user_id='"+ses.getAttribute("cuser_id")+"'");
					
		while(rs.next())
			{
			pw.println("while in");
				String cname=rs.getString("name");
			String cuser_id=rs.getString("user_id");
				pw.println("cname is ok");
					
				String comp=rs.getString("company");
			   	//String cph_no=rs.getString("ph_no");
                       	        String cmob_no=rs.getString("mob_no");
				String addr=rs.getString("address");
				String city=rs.getString("city");
				String state=rs.getString("state");
						
				String country=rs.getString("country");
				
				
			
			
			
				
				req.setAttribute("cuser_id",cuser_id);
				req.setAttribute("cname",cname);
				req.setAttribute("company",comp);
				//req.setAttribute("phone number",cph_no);
				req.setAttribute("mobile number",cmob_no);
				req.setAttribute("addr",addr);
				req.setAttribute("city",city);
				req.setAttribute("state",state);
				req.setAttribute("country",country);
				pw.println("SUCCESSFUL");
				RequestDispatcher rd=req.getRequestDispatcher("custform.jsp");
				rd.forward(req,res);
			
				  }

			if(p==0)
				{
				pw.println("<html><body>");
	pw.println("<center><br><br><br><br><br><br><h1><b>Login Failed</b></h1>");
			pw.println("</center></body></html>");
			pw.close();
		
				}
		
               
}	

        	catch(Exception e)
		{
		e.printStackTrace();
		}

}
}