

import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;
import java.sql.*;

public class Loginvalidate extends HttpServlet
{
       Connection con=null; 
       Statement stmt=null;
       ResultSet rs=null;
	   HttpSession ses=null;

       public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
       {
                       res.setContentType ("test/html");
               PrintWriter out=res.getWriter();

               try
               {

		   ses=req.getSession(true);
	       Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
	       out.println("before connection");      	
		   con=DriverManager.getConnection("jdbc:odbc:PROJECT","scott","tiger");
           out.println("after connection");
              	stmt=con.createStatement();
				out.println("after statement");               	
		       	RequestDispatcher rd;
               	
				String s2=req.getParameter("loginid");
               	String s3=req.getParameter("pwd");
              		out.println("after getparameter");
               	int p=0; 
              	ResultSet rs=stmt.executeQuery("select * from login");
				out.println("after resultset");
               while(rs.next())
                       {
             		out.println("inside while");
                               if( s2.equals(rs.getString("CUST_ID")) && (s3.equals(rs.getString("PASSWORD"))))

                               {
                                 ses.setAttribute("cust_id",s2);                   
	                               p=1;
                                 }  

                         }
if(p==1)
{
	rd= req.getRequestDispatcher("glue.jsp");
                               rd.forward(req,res); 
}
else
{
	rd= req.getRequestDispatcher("check.html");
                               rd.forward(req,res); 
 }
                                

  }           
	  catch(ClassNotFoundException e) 
               {
               out.println("exception"+e);
               }
               catch(SQLException e)
               {
                       out.println("exception"+e);
               }
                catch(Exception e)
               {
              out.println("can't load driver"+e.getMessage());
               }
 }
}
