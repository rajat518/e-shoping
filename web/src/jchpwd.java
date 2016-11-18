package src;

import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;
import java.sql.*;
public class jchpwd extends HttpServlet
{
        Connection con=null;
	Statement stmt=null;	
	ResultSet rs=null;
	
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{
			res.setContentType("css/html");
		PrintWriter pw=res.getWriter();
	
 		try
		{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
        pw.println("connection");
	    con=DriverManager.getConnection("jdbc:odbc:project","scott","tiger");		
		pw.println("before prep");
                stmt=con.createStatement();
		pw.println("after prep");
	RequestDispatcher rd;	
		String s1=req.getParameter("t1");
                String s2=req.getParameter("t2");
                String s3=req.getParameter("t3");
						int p=0;
                ResultSet rs=stmt.executeQuery("select * from user_details");
		while(rs.next())
			{
			
			String s4=rs.getString("user_id");
			pw.println("while in");
				
				 if((s1.equals(rs.getString("password")))) 
				
				{

				   PreparedStatement ps=con.prepareStatement("update user_details set password=? where user_id=?");

                   if(s2.equals(s3))
					{
					   ps.setString(1,s2);
					   ps.setString(2,s4);

					   ps.executeUpdate();
            					   
                    rd= req.getRequestDispatcher("login.html");
				rd.forward(req,res);

                                         }

				}
		
   	

                         }
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
		pw.println("can't load driver"+e.getMessage());
		}
  }
}