package src;

import java.lang.*;
import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;
import java.sql.*;
public class ordstatus extends HttpServlet
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
			pw.println("<td><img src='status.JPG' width=970 height=135></td></tr>");
            	//pw.println("<form  name=f2  action=./empdel method=get>");
                
				 
           //pw.println("<p><table cellpadding=1 cellspacing=1 border=1><th >orderid</th><th>userid</th><th>status</th></tr>");
		
		 HttpSession ses=req.getSession(true);
                ps=con.createStatement();
		


int num=Integer.parseInt(req.getParameter("t1"));

     pw.println("num");       
	           int p=0;
              
rs=ps.executeQuery("select orders.orderid,orderdetails.user_id,orders.ord_status from orders,orderdetails where orders.orderid='"+num+"'");

		
		rs.next();
			
	           
				int orderid=Integer.parseInt(rs.getString("orderid")); 							
				String user_id=rs.getString("user_id");		
				String ord_status=rs.getString("ord_status");
				
					 
			/*String  butt=req.getParameter("buttton");
			if(butt.equals("delete"))
					{
					RequestDispatcher rd=req.getRequestDispatcher("./empdel");
				    rd.forward(req,res); 

					}*/pw.println("</table>");
					pw.println("<tr><center>");
	
          pw.println("<td><h3>orderid="+orderid+"</h3></td>");
           pw.println("<td><h3>userid="+user_id+"</h3></td>");
		    pw.println("<td><h3>status="+ord_status+"</h3></td>");
					  
					pw.println("</center></tr>");   
				 
				
            
				
				pw.print("</table>");
				 pw.println("<br><br>");
				 pw.println("<table>");
				 pw.println("<tr>");
			
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