package src;

import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;
import java.sql.*;
public class updateprod extends HttpServlet
{
        Connection con=null;
	Statement stmt=null;	
	ResultSet rs=null;
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{
			res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
	
 		try
		{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
        pw.println("connection");
	    con=DriverManager.getConnection("jdbc:odbc:project","scott","tiger");		
		pw.println("before prep");
                stmt=con.createStatement();
				
		pw.println("after prep");
		HttpSession ses=req.getSession();
	String t1=(String)ses.getAttribute("t1");
			String t2=(String)ses.getAttribute("t2");
			
	RequestDispatcher rd;	
       rs=stmt.executeQuery(" select pro_id,path,pro_name,pro_price,num_of_items,branch_id,supp_id,description from products where pro_id='"+t1+"' and branch_id='"+t2+"'");
					
		rs.next();

                String pro_id=rs.getString("pro_id");
				//pw.println(pro_id);
            	String path=rs.getString("path");
				String pro_name=rs.getString("pro_name");
                int pro_price=Integer.parseInt(rs.getString("pro_price"));
		        int num_of_items=Integer.parseInt(rs.getString("num_of_items"));
                String branch_id=rs.getString("branch_id");
		        String supp_id=rs.getString("supp_id");
                String description=rs.getString("description");
					
				req.setAttribute("pro_id",pro_id);
				req.setAttribute("path",path);
				req.setAttribute("pro_name",pro_name);
				req.setAttribute("pro_price",pro_price);
				req.setAttribute("num_of_items",num_of_items);
				//req.setAttribute("pro_qty",pro_qty);
				req.setAttribute("branch_id",branch_id);
				req.setAttribute("supp_id",supp_id);
				req.setAttribute("description",description);
	  rd= req.getRequestDispatcher("proupdate.jsp");
				rd.forward(req,res);           
			
//String s9=req.getParameter("country");

				/*		int p=0;
              
			PreparedStatement ps=con.prepareStatement("update products set pro_name=?,pro_price=?, num_of_items=?,pro_qty=?,branch_id=?,supp_id=?,description=? where pro_id=?");

                       
					   ps.setString(1,s2);
					   ps.setInt(2,s3);
					   ps.setInt(3,s4);
					   ps.setInt(4,s5);
					   ps.setString(5,s6);
					   ps.setString(6,s7);
					   ps.setString(7,s8);
					  
					   ps.setString(8,s1);					 
                       				
					   ps.executeUpdate();
            	*/				   
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