package src;

import java.lang.*;
import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;
import java.sql.*;
public class supporder extends HttpServlet
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
			pw.println("<td><img src='order.JPG' width=970 height=135></td></tr>");
            	pw.println("<form  name=f2  action=./empdel method=get>");
                
				 pw.println("<table align='right'><tr><td> ");
				pw.println("<a href='admin.html'><img src='homepage.gif' border=0></a>");
				pw.println("</tr></td></table>");
				pw.println("<br>");
				pw.println("<br>");
           pw.println("<p><table cellpadding=1 cellspacing=1 border=1><th>Select</th><th >pro_name</th><th>pro_id</th><th>branch_id</th><th>supp_id</th><th>quantity</th><th>dd</th></tr>");
		int i=1;
		 HttpSession ses=req.getSession(true);
                ps=con.createStatement();
		




            
	           int p=0;
              
rs=ps.executeQuery("select * from sup_order");

		while(rs.next())
				{
			
	        String pro_name=rs.getString("pro_name");
           String pro_id=rs.getString("pro_id");	
				
				String branchid=rs.getString("branch_id");
				
				String supp_id=rs.getString("supp_id");
				String quantity=rs.getString("quantity");
				String dd=rs.getString("dd");
						 
			/*String  butt=req.getParameter("buttton");
			if(butt.equals("delete"))
					{
					RequestDispatcher rd=req.getRequestDispatcher("./empdel");
				    rd.forward(req,res); 

					}*/
	
          pw.println("<tr bgcolor=white><td><center><input type=checkbox name=n value="+pro_id+"></td><td>"+pro_name+"</td><td>"+pro_id+"</td><td>"+branchid+"</td><td>"+supp_id+"</td><td>"+quantity+"</td><td>"+dd+"</td></tr>");
                       
					   i++;
					   
				 
				}
            
				
				pw.print("</table>");
				 pw.println("<br><br>");
				 pw.println("<table>");
				 pw.println("<tr>");
			pw.print("<td><input type='submit' name='button' value='delete'>");
       pw.print("</form>");
	   
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