

import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;
import java.sql.*;
public class login extends HttpServlet
{
        Connection con=null;
	Statement ps=null;	
	ResultSet rs=null;
	HttpSession ses=null;
    private String uid;
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException ,ServletException
	{
			res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
				
 		try
		{
		
     		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");    	 
	pw.println("connection");

con=DriverManager.getConnection("jdbc:odbc:project","scott","tiger");
		uid=req.getParameter("uname");
		pw.println("before prep");
                ps=con.createStatement();
		pw.println("after prep");
		ses=req.getSession(true);
			}
		catch(ClassNotFoundException e)
		{
		pw.println("exception"+e);
		}
		catch(SQLException e)
		{
			pw.println("exception"+e);	
		}
	    	try
	      	{
				
	
//				pw.println("hello"+ses.getAttribute("cname"));

					int p=0;
                rs=ps.executeQuery("select name,user_id,company,mob_no,address,city,state,country,birthday from user_details where user_id='"+uid+"'");
					
		while(rs.next())
			{
			pw.println("while in");
				String cname=rs.getString(1);
				pw.println("cname is ok");
				String cuser_id=rs.getString(2);	
				String comp=rs.getString(4);
			   //	String cph_no=rs.getString("ph_no");
                       	        String cmob_no=rs.getString(6);
				String addr=rs.getString(7);
				String city=rs.getString(8);
				String state=rs.getString(9);
						
				String country=rs.getString(10);
				
				
			
			
			
				req.setAttribute("cname",cname);
				req.setAttribute("cuser_id",cuser_id);
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
		pw.println("can't load driver"+e.getMessage());
		}

}
        public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException ,ServletException
	{
            doGet(req,res);
        }
}