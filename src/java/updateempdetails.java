

import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;
import java.sql.*;
public class updateempdetails extends HttpServlet
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
		HttpSession ses=req.getSession();
	
              String s1=(String)ses.getAttribute("empid");
			
                String s2=req.getParameter("ename");
        
			String s3=req.getParameter("desg");
			
		        String s4=req.getParameter("branchid");
			
                String s5=req.getParameter("addr");
	
                String s6=req.getParameter("city");
				
                String s7=req.getParameter("state");
		
				String s8=req.getParameter("country");
		
				String s9=req.getParameter("mobno");
			
				String s10=req.getParameter("hire_date");
				
				String s11=req.getParameter("emailid");
			
				String s12=req.getParameter("password");
			
                
                 String s13=req.getParameter("salary");
		
						int p=0;
              
		PreparedStatement ps=con.prepareStatement("update emp_details set desg=?,branchid=?, address=?,city=?,state=?,country=?,mob_no=?,hire_Date=?,emailid=?,password=?,salary=? where emp_no=?");               
					   ps.setString(1,s3);
					   ps.setString(2,s4);
					   ps.setString(3,s5);
					   ps.setString(4,s6);
					   ps.setString(5,s7);
					   ps.setString(6,s8);
					   ps.setString(7,s9);
					   ps.setString(8,s10);					 
						 ps.setString(9,s11);	
						  ps.setString(10,s12);	
						   ps.setString(11,s13);	
						   ps.setString(12,s1);	 
					   ps.executeUpdate();
					 /*  ses.setAttribute("empid",s1);
				req.setAttribute("ename",s2);
				req.setAttribute("desg",s3);
				req.setAttribute("branchid",s4);
				req.setAttribute("addr",s5);
				req.setAttribute("city",s6);
				req.setAttribute("state",s7);
				req.setAttribute("country",s8);
				req.setAttribute("mobno",s9);
				req.setAttribute("hire_date",s10);
				req.setAttribute("emailid",s11);
				req.setAttribute("password",s12);
				req.setAttribute("salary",s13);*/
				
           RequestDispatcher rd= req.getRequestDispatcher("./jempst");
				rd.forward(req,res);      	
						   
    	con.commit();
	
    
  

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