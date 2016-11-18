package src;

import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;
import java.sql.*;
public class updateprod1 extends HttpServlet
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

               String s1=req.getParameter("pro_id");
				pw.println(s1+"hee");
            	String s2=req.getParameter("pro_name");
				pw.println(s2);
                String s3=req.getParameter("pro_qty");
				pw.println(s3);
		        String s4=req.getParameter("pro_price");
				pw.println(s4);
                String s5=req.getParameter("num_of_items");
				pw.println(s5);
                String s6=req.getParameter("branch_id");
				pw.println(s6);
		        String s7=req.getParameter("supp_id");
				pw.println(s7);
                String s8=req.getParameter("description");
				pw.println(s8);
//String s9=req.getParameter("country");

						int p=0;
 PreparedStatement ps=con.prepareStatement("update products set pro_name=?,pro_qty=?,pro_price=?, num_of_items=?,supp_id=?,description=? where pro_id=? and branch_id=?"); 
			

                       
					   ps.setString(1,s2);
					   ps.setString(2,s3);
					   ps.setString(3,s4);
					   ps.setString(4,s5);
					    ps.setString(5,s7);
					    ps.setString(6,s8);					 
                       ps.setString(7,s1);
					    ps.setString(8,s6);
					   ps.executeUpdate();
                rd= req.getRequestDispatcher("./updpro");
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