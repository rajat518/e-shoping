package src;

import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;
import java.sql.*;
import java.lang.*;
public class updatestock extends HttpServlet
{
 Connection con=null;
 Statement ps=null;
 Statement ps1=null;	

 public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
 {
  res.setContentType("text/html");
  PrintWriter pw=res.getWriter();
 pw.println("1");
  try
  {
   Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
   con=DriverManager.getConnection("jdbc:odbc:project","scott","tiger");		
   ps=con.createStatement();
   ps1=con.createStatement();
   RequestDispatcher rd;
   pw.println("2");
  String id[]=req.getParameterValues("n");
   int len=id.length;
   pw.print(len);

   
   for(int i=0;i<len;i++)
   {
	   pw.print("entered for"+i);
	   pw.println(id[i]);
   String product_id=req.getParameter("product_id["+id[i]+"]");
   String branch_id=req.getParameter("branch_id["+id[i]+"]");
    String supplier_id=req.getParameter("supplier_id["+id[i]+"]");
   int quantity=Integer.parseInt(req.getParameter("quantity["+id[i]+"]"));
 
  String qry ="update products set num_of_items=num_of_items+"+quantity+",status=0 where pro_id='"+product_id+"' and branch_id='"+branch_id+"'";
  pw.print(qry);
ps.executeUpdate(qry);
qry="delete from sup_order where product_id='"+product_id+"' and branch_id='"+branch_id+"'";
pw.println(qry);
 ps.executeUpdate(qry);
 // qry ="update products set status=1 where pro_id='"+pid+"'";
  //  pw.print(qry);
//ps1.executeUpdate(qry);
   }
  con.commit();
  pw.print("updated");
   }
  catch(ClassNotFoundException e)
    {
     pw.println("exception"+e);
    }
   catch(SQLException e)
    {
     pw.println("SQL EXCEPTION");	
     pw.println("TRY AGAIN");
    }
   catch(Exception e)
    {
     pw.println("can't load driver"+e.getMessage());
    
	}
	}}