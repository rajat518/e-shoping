package src;

import java.lang.*;
import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;
import java.sql.*;
public class custdetails extends HttpServlet
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
			pw.println("<td background =cus.GIF width=700></td></tr>");
            	pw.println("<form  name=f2  action=./del method=get>");
                
				pw.println("<table align='right'><tr><td> ");
				pw.println("<a href='admin.html'><img src='homepage.gif' border=0></a>");
				pw.println("</tr></td></table>");
				pw.println("<br>");
				pw.println("<br>");
           pw.println("<p><table cellpadding=1 cellspacing=1 border=1><th>Select</th><th >NAME</th><th>USER_ID</th><th>PASSWORD</th><th>COMPANY</th><th>MOB_NO</th><th>ADDRESS</th><th>CITY</th><th>STATE</th><th>COUNTRY</th><th>QUESTION</th><th>ANSWER</th><th>BIRTHDAY</th><th>PINCODE</th></tr>");
		int i=1;
		 HttpSession ses=req.getSession(true);
                ps=con.createStatement();
		




            
	           int p=0;
              
rs=ps.executeQuery("select * from user_details");

		while(rs.next())
				{
			
	        String name=rs.getString("name");
			
           String user_id=rs.getString("user_id");	
		   
				String password=rs.getString("password"); 
				
				String company=rs.getString("company");
				
				//tring ph_no=rs.getString("ph_no");
				
				String mob_no=rs.getString("mob_no");
				String address=rs.getString("address");
				String city=rs.getString("city");
				String state=rs.getString("state");
				String country=rs.getString("country");
				
				String ques=rs.getString("ques");		
				String ans=rs.getString("ans");
				String birthday=rs.getString("birthday");
				String pincode=rs.getString("pincode");
				
						 
			/*String  butt=req.getParameter("buttton");
			if(butt.equals("delete"))
					{
					RequestDispatcher rd=req.getRequestDispatcher("./empdel");
				    rd.forward(req,res); 

					}*/
	
          pw.println("<tr bgcolor=white><td><center><input type=checkbox name=n value="+user_id+"></td><td>"+name+"h</td><td>"+user_id+"</td><td>"+password+"</td><td>"+company+"</td><td>"+mob_no+"</td><td>"+address+"</td><td>"+city+"</td><td>"+state+"</td><td>"+country+"</td><td>"+ques+"</td><td>"+ans+"</td><td>"+birthday+"</td><td>"+pincode+"</td></tr>");
                       
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