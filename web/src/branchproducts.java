package src;

import java.lang.*;
import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;
import java.sql.*;
public class branchproducts extends HttpServlet
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
			pw.println("<td><img src='s0.GIF' width=1000 height=135></td></tr>");
            	//pw.println("<form  name=f2  action=./empdel method=get>");
                
				 pw.println("ok");
           pw.println("<p><table cellpadding=1 cellspacing=1 border=1><th>product name</th><th>pro_id</th><th>pro_price</th><th>num_of_items</th><th>branch_id</th><th>supp_id</th><th>description</th></tr>");
		int i=1;
		 HttpSession ses=req.getSession(true);
                ps=con.createStatement();
		




            
	           int p=0;
              
rs=ps.executeQuery("select path,pro_name,pro_id,pro_price,num_of_items,branch_id,supp_id,description from products where branch_id='br01'");

		while(rs.next())
				{
			
	            String path=rs.getString("path");
				 String pro_name=rs.getString("pro_name");
			  //  pw.print(pro_name);
                String pro_id=rs.getString("pro_id");	
		        // pw.print(pro_id);
				//int pro_qty=Integer.parseInt(rs.getString("pro_qty")); 							
			//	pw.print(pro_qty);
				int pro_price=Integer.parseInt(rs.getString("pro_price")); 		
			//	pw.print(pro_price);
				int num_of_items=Integer.parseInt(rs.getString("num_of_items")); 		
			//	pw.print(num_of_items);
				String branch_id=rs.getString("branch_id");		
			//	pw.print(branch_id);
				String supp_id=rs.getString("supp_id");
			//	pw.print(supp_id);
				String description=rs.getString("description");
			//	pw.print(description);
					 
			/*String  butt=req.getParameter("buttton");
			if(butt.equals("delete"))
					{
					RequestDispatcher rd=req.getRequestDispatcher("./empdel");
				    rd.forward(req,res); 

					}*/
	
          pw.println("<tr bgcolor=white><td><img src="+path+" width=150 height=125></td><td>"+pro_name+"</td><td>"+pro_id+"</td><td>"+pro_price+"</td><td>"+num_of_items+"</td><td>"+branch_id+"</td><td>"+supp_id+"</td><td>"+description+"</td></tr>");
                       
					   i++;
					   
				 
				}
            
				con.commit();
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