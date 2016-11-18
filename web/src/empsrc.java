package src;

import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;
import java.sql.*;
public class empsrc extends HttpServlet
{
        Connection con=null;
	Statement ps=null;	
	ResultSet rs=null;
	
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException ,ServletException
	{
			res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
				
 		try
		{
		
     		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");    	 
	pw.println("connection");

con=DriverManager.getConnection("jdbc:odbc:project","scott","tiger");
		 pw.println("<html>");
          
            pw.println("<body>");
			pw.println("<table border=0 cellspacing=0 cellpadding=0><tr>");
			pw.println("<td><img src='emp1.gif' width=970 height=135></td></tr>");
			pw.println("</body></html>");
		pw.println("before prep");
                ps=con.createStatement();
		pw.println("after prep");
		
		HttpSession ses=req.getSession(true);	
		String s=req.getParameter("t1");
		pw.println(s);
	    	
				
	rs=ps.executeQuery(" select * from emp_details where emp_no='"+s+"'");
					pw.println("2");
		while(rs.next())
			{
			pw.println("0");
			String empid=rs.getString("emp_no");
			pw.println(empid);
			if(s.equals(empid))
				{
				pw.println("1");
				 pw.println(empid);
				pw.println("asdfasdf");
			   ses.setAttribute("empid",s);
			   pw.println(empid);
				req.setAttribute("ename",rs.getString("ename"));
				req.setAttribute("desg",rs.getString("desg"));
				req.setAttribute("branchid",rs.getString("branchid"));
				
				req.setAttribute("city",rs.getString("city"));
				req.setAttribute("state",rs.getString("state"));
				req.setAttribute("country",rs.getString("country"));
				req.setAttribute("mobno",rs.getString("mob_no"));
				req.setAttribute("addr",rs.getString("address"));
				
				req.setAttribute("emailid",rs.getString("emailid"));
				req.setAttribute("password",rs.getString("password"));
				req.setAttribute("hire_date",rs.getString("hire_date"));
					int salary=Integer.parseInt(rs.getString("salary"));
				req.setAttribute("salary",salary);
				
				
		RequestDispatcher rd=req.getRequestDispatcher("empstatic.jsp");
			    
				rd.forward(req,res);
				}
				pw.close();
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