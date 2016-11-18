

import java.lang.*;
import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;
import java.sql.*;
public class branchorder extends HttpServlet
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
			pw.println("<table border='0' cellspacing='0' cellpadding='0'><tr><td><img src='s.gif' width=300 height=135></td><td background='a.GIF' width=700></td></tr></table>");
			
            	//pw.println("<form  name=f1  action=./prodel method=get>");
                
				 
           pw.println("<p><table cellpadding=1 cellspacing=1 border=1><th>Select</th><th >orderid</th><th>ord_add</th><th>ord_city</th><th>ord_state</th><th>ord_pincode</th><th>ord_date</th><th>ord_time</th><th>ord_status</th><th>ord_price</th></tr>");
		int i=1;
		 HttpSession ses=req.getSession(true);
                ps=con.createStatement();
		

       
	           int p=0;
              
rs=ps.executeQuery("select * from orders where ord_status='processsed'");

		while(rs.next())
				{
			
	        String orderid=rs.getString("orderid");
			String ord_add=rs.getString("ord_add");
           String ord_city=rs.getString("ord_city");
           String ord_state=rs.getString("ord_state");
			String ord_pincode=rs.getString("ord_pincode");
			String ord_date=rs.getString("ord_date");
			String ord_time=rs.getString("ord_time");
             String ord_status=rs.getString("ord_status");
		     int ord_price=Integer.parseInt(rs.getString("ord_price"));	

			
		

           	
				
	
          pw.println("<tr bgcolor=white><td><center><input type=checkbox name=n value="+orderid+"></td><td><td>'"+orderid+"'</td><td>"+ord_add+"</td><td>"+ord_city+"</td><td>"+ord_state+"</td><td>'"+ord_pincode+"'</td><td>"+ord_date+"</td><td>'"+ord_time+"'</td><td>'"+ord_status+"'</td><td>"+ord_price+"</td></tr>");
                       
					   i++;
					   
				 
				}
            
				
				pw.print("</table>");
				 pw.println("<br><br>");
				 pw.println("<table><tr>");
				
		
	  
	   pw.println("<form name='f2' action='' method='post'");
	   pw.println("<td><input type='submit' name='submit' value='send'>");
	   pw.println("</form>");
	   pw.print("<td><td><td>");
     pw.println("<form name='f3' action='prosrc.html' method='post'");
	   pw.println("<td><input type='submit' name='submit' value='update'>");
	   pw.println("</form>");
	   pw.print("<td><td><td>");
	     pw.println("<form name='f4' action='./stockles ' method='post'");
	   pw.println("<td><input type='submit' name='submit' value='stock'>");
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