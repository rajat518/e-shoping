package src;

import java.lang.*;
import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;
import java.sql.*;
public class suppdetails extends HttpServlet
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
			pw.println("<td><img src='sup.GIF' width=972 height=155></td></tr>");
			
            	pw.println("<form  name=f2  action=./supdel method=get>");
                
				 
           pw.println("<p><table cellpadding=1 cellspacing=1 border=1><th>Select</th><th >SUPPLIERID</th><th>SUPPLIERNAME</th><th>ADDRESS</th><th>CITY</th><th>STATE</th><th>COUNTRY</th><th>MOBILE NO</th><th>MAILID</th></tr>");
		int i=1;
		 HttpSession ses=req.getSession(true);
                ps=con.createStatement();
		




            
	           int p=0;
              
rs=ps.executeQuery("select * from supplier_details");

		while(rs.next())
				{
			String supp_id=rs.getString("supp_id");
           String supp_name=rs.getString("supp_name");	
				String address=rs.getString("address"); 
				String city=rs.getString("city");
				String state=rs.getString("state");
				String country=rs.getString("country");
	           	String mob_no=rs.getString("mob_no");
				String mailid=rs.getString("mailid");
			//String password=rs.getString("password");
	
  pw.println("<tr bgcolor=white><td><center><input type=checkbox name=n value="+supp_id+"></td><td>"+supp_id+"</td><td>"+supp_name+"</td><td>"+address+"</td><td>"+city+"</td><td>"+state+"</td><td>"+country+"</td><td>"+mob_no+"</td><td>"+mailid+"</td></tr>");
                       
		   i++;
					   
				 
				}
            
				
				pw.print("</table>");
				 pw.println("<br><br>");
				 pw.println("<table>");
				 pw.println("<tr>");
			pw.print("<td><input type='submit' name='button' value='delete'>");
       pw.print("</form>");
	   pw.print("<td><td><td>");
		pw.print("<form name=f1 method=get action=supinsert.jsp>");
			pw.print("<td><input type='submit' value='add' name='button'>");
			
			pw.print("</form>");
		    pw.print("<td><td><td>");
			pw.print("<form action='./supsearch1' method=get>");

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
			