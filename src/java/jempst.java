

import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;
import java.sql.*;
public class jempst extends HttpServlet
{
        Connection con=null;
	Statement ps=null;	
	ResultSet rs=null;
	HttpSession ses=null;
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException ,ServletException
	{
			res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
				
 		try
		{
		
     		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");    	 
	pw.println("connection");

con=DriverManager.getConnection("jdbc:odbc:project","scott","tiger");
		
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
				
	
				pw.println("hello"+ses.getAttribute("emp_no"));

					int p=0;
                rs=ps.executeQuery(" select emp_no,ename,desg,branchid,address,city,state,country,mob_no,hire_date,emailid,password,salary from emp_details where emp_no='"+ses.getAttribute("empid")+"'");
					
		while(rs.next())
			{
			pw.println("while in");
				String empid=rs.getString("emp_no");
				pw.println("cname is ok");
				String ename=rs.getString("ename");	
				String desg=rs.getString("desg"); 
				String branchid=rs.getString("branchid"); 
				String addr=rs.getString("address");
				String city=rs.getString("city");
				String state=rs.getString("state");
				String country=rs.getString("country");
				String mobno=rs.getString("mob_no");		
				String hire_date=rs.getString("hire_date");
				String emailid=rs.getString("emailid");
				String password=rs.getString("password");
				int salary=Integer.parseInt(rs.getString("salary"));			 
			
			
				req.setAttribute("empid",empid);

				req.setAttribute("ename",ename);
				req.setAttribute("desg",desg);
				req.setAttribute("branchid",branchid);
				req.setAttribute("addr",addr);
				req.setAttribute("city",city);
				req.setAttribute("state",state);
				req.setAttribute("country",country);
				req.setAttribute("mobno",mobno);
				req.setAttribute("hire_date",hire_date);
				req.setAttribute("emailid",emailid);
				req.setAttribute("password",password);
				req.setAttribute("salary",salary);
				
				pw.println("SUCCESSFUL");
				RequestDispatcher rd=req.getRequestDispatcher("empdyn.jsp");
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
}