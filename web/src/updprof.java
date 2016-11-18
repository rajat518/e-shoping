package src;

import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;
import java.sql.*;
public class updprof extends HttpServlet
{
        Connection con=null;
	Statement stmt=null;	
	ResultSet rs=null;
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{
			res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
	
 		try
		{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
        pw.println("connection");
	    con=DriverManager.getConnection("jdbc:odbc:project","scott","tiger");		
		pw.println("before prep");
                stmt=con.createStatement();
				
		pw.println("after prep");
		//HttpSession ses=req.getSession();
	RequestDispatcher rd;	

                String s1=req.getParameter("cuser_id");
				pw.println(s1);
//				String s2=req.getParameter("cname");
				
                String s3=req.getParameter("comp");
		        //String s4=req.getParameter("phone_no");
                String s5=req.getParameter("mob_no");
                String s6=req.getParameter("addr");
		        String s7=req.getParameter("city");
                String s8=req.getParameter("state");
String s9=req.getParameter("country");

						int p=0;
              
			PreparedStatement ps=con.prepareStatement("update user_details set company=?, mob_no=?,address=?,city=?,state=?,country=? where user_id=?");

                       
					   ps.setString(1,s3);
					   //ps.setString(2,s4);
					   ps.setString(2,s5);
					   ps.setString(4,s6);
					   ps.setString(5,s7);
					   ps.setString(6,s8);
					   ps.setString(7,s9);
					   ps.setString(8,s1);					 
                       				
					   ps.executeUpdate();
            					   
        rd= req.getRequestDispatcher("./view");
				rd.forward(req,res);           
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