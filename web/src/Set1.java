package src;

import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;
import java.sql.*;
public class Set1 extends HttpServlet
{
    Connection con=null;
	Statement ps,ps1=null;		
    int p=0;


	public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{
			res.setContentType("text/html");
            HttpSession ses=req.getSession();
			PrintWriter pw=res.getWriter();
	
 		try
		{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
       
	    con=DriverManager.getConnection("jdbc:odbc:project","scott","tiger");		
		
	               ps=con.createStatement();
				   ps1=con.createStatement();
				   pw.println("entered");
                   RequestDispatcher rd;	

      
     String s1=req.getParameter("status");
     String s2=req.getParameter("oid");
	 pw.println(""+s1);
	 pw.println(""+s2);
     ResultSet rs=ps.executeQuery("select ord_status from orders where orderid='"+s2+"'");
	 pw.println("test1");
while(rs.next())
{		pw.println("test23");
        int row=ps1.executeUpdate("update orders set ord_status='"+s1+"' where orderid='"+s2+"'");
		pw.println("test5");
         rd= req.getRequestDispatcher("branchorders.jsp");
          rd.forward(req,res);
	       pw.close();
		 }
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