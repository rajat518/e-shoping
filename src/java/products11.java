

import java.lang.*;
import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;
import java.sql.*;
public class products11 extends HttpServlet
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
           pw.println("<form action=updateproduct_price>");
			
			pw.println("<table border='0' cellspacing='0' cellpadding='0'><tr><td><img src='s.gif' width=300 height=135></td><td background='a.GIF' width=700></td></tr></table>");
			pw.println("<table align='right'><tr>");
			pw.println("<td><a href='./products'><img src='back.gif' border=0></a></td>");
			pw.println("</tr></table>");
            	pw.println("<form  name=f1  action=./prodel method=get>");
                
				 
           pw.println("<p><table cellpadding=1 cellspacing=1 border=1><th>Select</th><th >products</th><th>pro_price</th><th>num_of_items</th><th>category</th><th>pro_id</th><th>product name</th></tr>");
		int i=1;
		 HttpSession ses=req.getSession(true);
                ps=con.createStatement();
		

       
	           int p=0;
             // pw.print("helloooo");
rs=ps.executeQuery("select path,pro_name,pro_price,category,pro_id,sum(num_of_items) from products group by(category,path,pro_name,pro_id,pro_price) order by(pro_id)");
//pw.print("while");
 
		while(rs.next())
				{
                                 
			//pw.print("entered while");
	        String path=rs.getString("path");
			//pw.println(path);
				String pro_name=rs.getString("pro_name");
				//pw.print(pro_name);
			 int pro_price=rs.getInt("pro_price");	
			//pw.print(pro_price);
			String category=rs.getString("category");
			//pw.print(category);
				String pro_id=rs.getString("pro_id"); 
			//	pw.print(pro_id);
			int num_of_items=Integer.parseInt(rs.getString("sum(num_of_items)"));
			//pw.print(num_of_items);
           	
			String ss1=pro_id+"t";
	String ss2=pro_id+"s";
          pw.println("<tr bgcolor=white><td><center><input type=checkbox name=n value="+pro_id+"></td><td><img src="+path+" width=120 height=120></td><td><input type=text value='"+pro_price+"' name='"+ss1+"'/></td><td><input type=text  value='"+num_of_items+"'  name='"+ss2+"'/></td><td>"+category+"</td><td>'"+pro_id+"'</td><td>"+pro_name+"</td></tr>");
                       pw.println("<input type=hidden name='"+pro_id+"'s'>");
					   i++;
					   
				 
				}
            
				
				pw.print("</table>");
				 pw.println("<br><br>");
				 pw.println("<table><tr>");
				
		pw.println("<td><input type='submit' name='submit' value='delete'>");	
       pw.print("</form>");
	   pw.print("<td><td><td>");
	   pw.println("<form name='f2' action='proinsert.jsp' method='post'");
	   pw.println("<td><input type='submit' name='submit' value='add'>");
	   pw.println("</form>");
	   pw.print("<td><td><td>");
    // pw.println("<form   name='f3' method='get'");
	   pw.println("<td><input type='submit' name='submit' value='update'>");
	//pw.println("</form>");
	   pw.print("<td><td><td>");
	    
				 
	   			pw.println("</tr></table>");
                               pw.println("</form>");
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
