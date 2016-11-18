

import java.lang.*;
import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;
import java.sql.*;
public class pempdetails extends HttpServlet
{
        Connection con=null;
	Statement ps=null;	
	ResultSet rs=null;
	RequestDispatcher rd;
	
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{
			res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
	
 		try
		{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
       
	    con=DriverManager.getConnection("jdbc:odbc:project","scott","tiger");		
		
		 pw.println("<html>");
          
            pw.println("<body>");
			pw.println("<table border=0 cellspacing=0 cellpadding=0><tr>");
			pw.println("<td><img src='emp1.gif' width=970 height=135></td></tr>");
            	pw.println("<form  name=f2  action=./empdel method=get>");
                
				 pw.println("<table align='right'><tr><td> ");
				pw.println("<a href='admin.html'><img src='homepage.gif' border=0></a>");
				pw.println("</tr></td></table>");
				pw.println("<br>");
				pw.println("<br>");
           pw.println("<p><table cellpadding=1 cellspacing=1 border=1><th>Select</th><th >EMPID</th><th>EMPNAME</th><th>DESIGNATION</th><th>BRANCH ID</th><th>CITY</th><th>STATE</th><th>COUNTRY</th><th>MOBILE NO</th><th>ADDRESS</th><th>EMAIL ID</th><th>PASSWORD</th><th>HIREDATE</th><th>SALARY</th></tr>");
		int i=1;
		 HttpSession ses=req.getSession(true);
                ps=con.createStatement();
		




            
	           int p=0;
              
rs=ps.executeQuery("select emp_no,ename,desg,branchid,address,city,state,country,mob_no,hire_date,emailid,password,salary from emp_details");

		while(rs.next())
				{
			
	        String empid=rs.getString("emp_no");
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
				
			String salary=rs.getString("salary");			 
			/*String  butt=req.getParameter("buttton");
			if(butt.equals("delete"))
					{
					RequestDispatcher rd=req.getRequestDispatcher("./empdel");
				    rd.forward(req,res); 

					}*/
	
          pw.println("<tr bgcolor=white><td><center><input type=checkbox name=n value="+empid+"></td><td>"+empid+"</td><td>"+ename+"</td><td>"+desg+"</td><td>"+branchid+"</td><td>"+city+"</td><td>"+state+"</td><td>"+country+"</td><td>"+mobno+"</td><td>"+addr+"</td><td>"+emailid+"</td><td>"+password+"</td><td>"+hire_date+"</td><td>"+salary+"</td></tr>");
                       
					   i++;
					   
				 
				}
            
				
				pw.print("</table>");
				 pw.println("<br><br>");
				 pw.println("<table>");
				 pw.println("<tr>");
			pw.print("<td><input type='submit' name='button' value='delete'>");
       pw.print("</form>");
	   pw.print("<td><td><td>");
		pw.print("<form name=del method=get action=empinsert.jsp>");
			pw.print("<td><input type='submit' value='add' name='button'>");
			
			pw.print("</form>");
		    pw.print("<td><td><td>");
			pw.print("<form action=empsrc.html method=get>");

			pw.print("<td><input type='submit' name='submit' value='search'>");
			pw.println("</form>");
			pw.println("</tr></table>");
			pw.print("</body></html>");		   
			
						
		
			
               
			rs.close();
		
        ps.close();
		con.close();
   
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