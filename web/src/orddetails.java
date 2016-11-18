package src;

import java.lang.*;
import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;
import java.sql.*;
public class orddetails extends HttpServlet
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
			pw.println("<table border=0 cellspacing=0 cellpadding=0><tr><td><img src='order.JPG' width=1000 height=135></td></tr>");
			
            	pw.println("<form  name=f2  action= method=get>");
                
				 
           pw.println("<p><table cellpadding=1 cellspacing=1 border=1><th>Select</th><th >ORDERID</th><th>PRO_ID</th><th>USER_ID</th><th>PRO_QTY</th><th>BRANCH</th><th>STATUS</th></tr>");
		int i=1;
		 HttpSession ses=req.getSession(true);
                ps=con.createStatement();
		




            
	           int p=0;
              
rs=ps.executeQuery("select orderdetails.orderid,orderdetails.pro_id,orderdetails.user_id,orderdetails.pro_qty,orderdetails.branch_id,orders.ord_status from orderdetails,orders where orderdetails.orderid=orders.orderid");

		while(rs.next())
				{
			
	        String orderid=rs.getString(1);
			
           String pro_id=rs.getString(2);	
		   
				String user_id=rs.getString(3); 
				
				String pro_qty=rs.getString(4);
				
				String branch_id=rs.getString(5);
				
				String ord_Status=rs.getString(6);
						 
			/*String  butt=req.getParameter("buttton");
			if(butt.equals("delete"))
					{
					RequestDispatcher rd=req.getRequestDispatcher("./empdel");
				    rd.forward(req,res); 

					}*/
	
          pw.println("<tr bgcolor=white><td><center><input type=checkbox name=n value="+branch_id+"></td><td>"+orderid+"</td><td>"+pro_id+"</td><td>"+user_id+"</td><td>"+pro_qty+"</td><td>"+branch_id+"</td><td>"+ord_Status+"</td></tr>");
                       
					   i++;
					   
				 
				}
            
				
				pw.print("</table>");
				 pw.println("<br><br>");
				 pw.println("<table>");
				 pw.println("<tr>");
			pw.print("<td><input type='submit' name='button' value='send'>");
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