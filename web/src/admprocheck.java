package src;

import java.lang.*;
import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;
import java.sql.*;
public class admprocheck extends HttpServlet
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
			pw.println("<td><img src='sup.GIF' width=970 height=135></td></tr>");
            	pw.println("<form  name=f2  action='./updstock' method=post>");
                
				 
           pw.println("<p><table cellpadding=1 cellspacing=1 border=1><th>select</th><th>product_name</th><th>product_id</th><th>branch_id</th><th>supplier_id</th><th>quantity</th></tr>");
		int i=1;
		 HttpSession ses=req.getSession(true);
                ps=con.createStatement();
		
//String ss=ses.getAttribute("supp_id");



            
	           int p=0;
              
rs=ps.executeQuery("select product_name,product_id,branch_id,supplier_id,quantity from sup_order where branch_id='"+ses.getAttribute("branch_id")+"'");

		while(rs.next())
				{
			
	        String product_name=rs.getString("product_name");
           String product_id=rs.getString("product_id");	
				String branch_id=rs.getString("branch_id"); 
				String supplier_id=rs.getString("supplier_id");
				
				String quantity=rs.getString("quantity");
				//String dd=rs.getString("dd");
				/*String state=rs.getString("state");
				String country=rs.getString("country");
				
				String mobno=rs.getString("mob_no");		
				String hire_date=rs.getString("hire_date");
				String emailid=rs.getString("emailid");
				String password=rs.getString("password");
				
			String salary=rs.getString("salary");			 
			String  butt=req.getParameter("buttton");
			if(butt.equals("delete"))
					{
					RequestDispatcher rd=req.getRequestDispatcher("./empdel");
				    rd.forward(req,res); 

					}*/
	
          pw.println("<tr bgcolor=white><td><input type='checkbox' name='n' value='"+i+"'></td><td>"+product_name+"</td><td><input type='hidden' name='product_id["+i+"]' value="+product_id+">"+product_id+"</td><td><input type='hidden' name='branch_id["+i+"]' value="+branch_id+">"+branch_id+"</td><td><input type='hidden' name='supplier_id["+i+"]' value="+supplier_id+">"+supplier_id+"</td><td><input type='hidden' name='quantity["+i+"]' value="+quantity+">"+quantity+"</td></tr>");
                       
					   i++;
					   
				 
				}
            
				
				pw.print("</table>");
				 pw.println("<br><br>");
				 pw.println("<table>");
				 pw.println("<tr>");
			pw.print("<td><input type='submit' name='button' value='checked'>");
       pw.print("</form>");
	   pw.print("<td><td><td>");
		pw.print("<form name=del method=get action=>");
			pw.print("<td><input type='submit' value='delete' name='button'>");
			
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