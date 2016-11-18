

import java.lang.*;
import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;
import java.sql.*;
public class suporderveiw extends HttpServlet
{
        Connection con=null;
	Statement ps=null;	
	ResultSet rs=null;
	RequestDispatcher rd;
	
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
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
			pw.println("<td><img src='suporder1.JPG' width=970 height=135></td></tr>");
            	pw.println("<form  name=f2  action='./updstock' method=post>");
                
				 
           pw.println("<p><table cellpadding=1 cellspacing=1 border=1><th>select</th><th>product_name</th><th>product_id</th><th>branch_id</th><th>supplier_id</th><th>quantity</th><th>date</th></tr>");
		int i=1;
		 HttpSession ses=req.getSession(true);
                ps=con.createStatement();
		
//String ss=ses.getAttribute("supp_id");



            
	           int p=0;
              
rs=ps.executeQuery("select pro_name,pro_id,branch_id,supp_id,quantity ,dd  from sup_order ");

		while(rs.next())
				{
			
	        String product_name=rs.getString("pro_name");
           String product_id=rs.getString("pro_id");	
				String branch_id=rs.getString("branch_id"); 
				String supplier_id=rs.getString("supp_id");
				
				String quantity=rs.getString("quantity");
				String dd=rs.getString("dd");
				
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
	
          pw.println("<tr bgcolor=white><td><input type='checkbox' name='n' value='"+supplier_id+"'></td><td>"+product_name+"</td><td>"+product_id+"</td><td>"+branch_id+"</td><td>"+supplier_id+"</td><td>"+quantity+"</td><td>"+dd+"</td></tr>");
                       
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