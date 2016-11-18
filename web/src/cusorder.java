package src;

import java.lang.*;
import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;
import java.sql.*;
public class cusorder extends HttpServlet
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
			pw.println("<td><img src=s.gif width=300 height=135></td>");
			pw.println("<td background =ord.GIF width=700></td></tr>");
            	pw.println("<form  name=f2  action= method=get>");
                
				pw.println("<table align='right'><tr><td> ");
				pw.println("<a href='admin.html'><img src='homepage.gif' border=0></a>");
				pw.println("</tr></td></table>");
				pw.println("<br>");
				pw.println("<br>");
           pw.println("<p><table cellpadding=1 cellspacing=1 border=1><th>Select</th><th >orderid</th><th>pro_id</th><th>user_id</th><th>pro_price</th><th>pro_qty</th><th>branchid</th><th>dpro_qty</th></tr>");
		int i=1;
		 HttpSession ses=req.getSession(true);
                ps=con.createStatement();
		




            
	           int p=0;
              
rs=ps.executeQuery("select * from orderdetails");

		while(rs.next())
				{
			
	        String orderid=rs.getString("orderid");
			 String pro_id=rs.getString("pro_id");
           String user_id=rs.getString("user_id");	
		   
				String pro_price=rs.getString("pro_price"); 
				
				String pro_qty=rs.getString("pro_qty");
				
				//tring ph_no=rs.getString("ph_no");
				
				String branchid=rs.getString("branch_id");
				String dpro_qty=rs.getString("dpro_qty");
			/*	String city=rs.getString("city");
				String state=rs.getString("state");
				String country=rs.getString("country");
				
				String ques=rs.getString("ques");		
				String ans=rs.getString("ans");
				String birthday=rs.getString("birthday");
				String pincode=rs.getString("pincode");
				
						 
			String  butt=req.getParameter("buttton");
			if(butt.equals("delete"))
					{
					RequestDispatcher rd=req.getRequestDispatcher("./empdel");
				    rd.forward(req,res); 

					}*/
	
          pw.println("<tr bgcolor=white><td><center><input type=checkbox name=n value="+orderid+"></td><td>"+orderid+"</td><td>"+pro_id+"</td><td>"+user_id+"</td><td>"+pro_price+"</td><td>"+pro_qty+"</td><td>"+branchid+"</td><td>"+dpro_qty+"</td></tr>");
                       
					   i++;
					   
				 
				}
            
				
				pw.print("</table>");
				 pw.println("<br><br>");
				 pw.println("<table>");
				 pw.println("<tr>");
		//	pw.print("<td><input type='submit' name='button' value='delete'>");
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