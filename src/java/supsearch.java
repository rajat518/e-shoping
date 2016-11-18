

import java.lang.*;
import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;
import java.sql.*;
public class supsearch extends HttpServlet
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
			pw.println("<td><img src=sup.GIF width=970 height=135 ></td></tr>");
            	//pw.println("<form  name=f2  action=./supdel method=get>");
                
				 
           pw.println("<p><table cellpadding=1 cellspacing=1 border=1><th>supplier_id</th><th >product_id</th><th>branch_id</th></tr>");
		int i=1;
		 HttpSession ses=req.getSession(true);
                ps=con.createStatement();
		

//pw.println("1");

  //     pw.println("2");
     
	           int p=0;
              
rs=ps.executeQuery("select supp_id,pro_id,branch_id from products ");
		while(rs.next())
				{
	//		pw.println("entered while");

                String s1=rs.getString("supp_id");
			String pro_id=rs.getString("pro_id");
           String branch_id=rs.getString("branch_id");	
				/*/String address=rs.getString("address"); 
				String city=rs.getString("city");
				String state=rs.getString("state");
				String country=rs.getString("country");
	           	String mob_no=rs.getString("mob_no");
				String mailid=rs.getString("mailid");*/
			
	
  pw.println("<tr bgcolor=white><td>"+s1+"</td><td>"+pro_id+"</td><td>"+branch_id+"</td></tr>");
                       
		   i++;
					   
				 
				}
            
				
				pw.print("</table>");
				 pw.println("<br><br>");
		/*		 pw.println("<table>");
				 pw.println("<tr>");
		pw.print("<td><input type='submit' name='button' value='delete'>");
       pw.print("</form>");
	   pw.print("<td><td><td>");
		pw.print("<form name=f1 method=get action=supinsert.jsp>");
			pw.print("<td><input type='submit' value='add' name='button'>");
			
			pw.print("</form>");
		    pw.print("<td><td><td>");
			pw.print("<form action='./supsearch' method=get>");

			pw.print("<td><input type='submit' name='submit' value='search'>");
			pw.println("</form>");
			pw.println("</tr></table>");*/
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
			