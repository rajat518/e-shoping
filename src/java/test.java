

import java.sql.*;
 import javax.servlet.*;
import javax.servlet.http.*;
 import java.io.*;
 import java.util.*;

 public class test extends HttpServlet
  {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException,ServletException
     {
	try
	{ 

      res.setContentType("text/html");
       PrintWriter out=res.getWriter();

 Connection dbconn;
 Statement stmt=null;
 Statement stmt1=null;
Statement stmt2=null;
 ResultSet rs;
 ResultSet rs1;
 ResultSet rs2;
HttpSession ses=null;


Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
out.println("class");


 dbconn=DriverManager.getConnection("jdbc:odbc:project","scott","tiger");

out.println("connect");
stmt=dbconn.createStatement();
stmt1=dbconn.createStatement();
stmt2=dbconn.createStatement();
out.println("statement");
ses=req.getSession(true);
String name=req.getParameter("uname");
String pwd=req.getParameter("pwd");
if(name.equals("admin")&& pwd.equals("admin"))
{
    RequestDispatcher rd=req.getRequestDispatcher("admin1.html");
    rd.forward(req, res);
}
out.println("hello");

rs=stmt.executeQuery("select * from user_details where user_id='"+name+"'");
out.println("hello");

while(rs.next())
		{

            if((rs.getString(3)).equals(pwd))
           {
              out.println("welcome");
              ses.setAttribute("cuser_id",name);
			  
  RequestDispatcher rd =req.getRequestDispatcher("./cusdetails.html");
	     rd.forward(req,res);

           } 
		   else
			{
						  
  RequestDispatcher rd =req.getRequestDispatcher("login.html");
	     rd.forward(req,res);
			}
		}
rs.close();
rs1=stmt1.executeQuery("select * from branch_Details where branch_id='"+name+"'");
while(rs1.next())
	{
if((rs1.getString("password")).equals(pwd))
           {
	 ses.setAttribute("branch_id",name);
             out.println("welcome");
             
			  
  RequestDispatcher rd =req.getRequestDispatcher("edit2.html");
	     rd.forward(req,res);

           }

        		
		
		   else
		{
					  
  RequestDispatcher rd =req.getRequestDispatcher("login.html");
	     rd.forward(req,res);
		}
	      }
		   rs1.close();
rs2=stmt2.executeQuery("select * from supplier_Details where supp_id='"+name+"'");
while(rs2.next())
	{
if((rs2.getString("password")).equals(pwd))
           {
    	ses.setAttribute("supp_id",name);
             out.println("welcome");
             
			  
  RequestDispatcher rd =req.getRequestDispatcher("edit3.html");
	     rd.forward(req,res);

           }
    		
		
		   else
		{
					  
  RequestDispatcher rd =req.getRequestDispatcher("login.html");
	     rd.forward(req,res);
		}
		
		   
	} 
	rs2.close();}
	catch(Exception e)
	{
    // out.println("exeception" );
	}
}
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException,ServletException
     {
         doGet(req,res);
     }
    
}
