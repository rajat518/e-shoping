


import java.lang.*;
import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;
import java.sql.*;
public class stockless extends HttpServlet
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
			pw.println("<tr><td><img src='s.gif' width=300 height=135></td><td background='a.GIF' width=700></td></tr>");
            	pw.println("<form  name=f2  action='./stockorder' method=post>");
                
				 
           pw.println("<p><table cellpadding=1 cellspacing=1 border=1><th>Select</th><th>PRODUCTNAME</th><th >PRODUCT ID</th><th>NUM_OF_ITEMS</th><th>BRANCH_ID</th><th>SUPPLIER_ID</th><th>QUANTITY</th></tr>");
		int i=1;
		 HttpSession ses=req.getSession(true);
          ps=con.createStatement();            
	           int p=0;
              
	rs=ps.executeQuery("select pro_id,path,num_of_items,branch_id,supp_id from products where num_of_items<buffer and status=0;");

		while(rs.next())
				{
			
	        String pro_id=rs.getString("pro_id");
             String path=rs.getString("path");
			  //String pro_name=rs.getString("pro_name");
           String num_of_items=rs.getString("num_of_items");	
				String branch_id=rs.getString("branch_id"); 
				String supp_id=rs.getString("supp_id");
				//String s_price=rs.getString("s_price");
				//int price=Integer.parseInt(rs.getString("s_price"));
			
			/*String  butt=req.getParameter("buttton");
			if(butt.equals("delete"))
					{
					RequestDispatcher rd=req.getRequestDispatcher("./empdel");
				    rd.forward(req,res); 

					}*/

          pw.println("<tr bgcolor=white><td><center><input type=checkbox name=n value='"+i+"'><td><input type=hidden name='img["+i+"]' value="+path+"><img src="+path+" width=150 height=125></td></td><td><input type=hidden name='pid["+i+"]' value="+pro_id+">"+pro_id+"</td><td>"+num_of_items+"</td><td><input type=hidden name='bid["+i+"]' value="+branch_id+">"+branch_id+"</td><td><input type=hidden name='sid["+i+"]' value="+supp_id+">"+supp_id+"</td><td><input type='text' name='t["+i+"]' value='0'></td></tr>");
                       
					   i++;
					   
				 
				}
            
				
				pw.print("</table>");
				 pw.println("<br><br>");
				 pw.println("<table>");
				 pw.println("<tr>");
			pw.print("<td><input type='submit' name='submit' value='send'>");
       pw.print("</form>");
	   pw.print("<td><td><td>");
		pw.print("<form name=del method=get action='order.html'>");
			pw.print("<td><input type='submit' value='add' name='submit'>");
			
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