

import java.sql.*;
 import javax.servlet.*;
import javax.servlet.http.*;
 import java.io.*;
 import java.util.*;

 public class adtest extends HttpServlet
  {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException,ServletException
     {
	try
	{ 

      res.setContentType("text/html");
       PrintWriter out=res.getWriter();

 Connection dbconn;
 Statement stmt;
 ResultSet rs;
// ResultSet rs1;


Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
out.println("class");


 dbconn=DriverManager.getConnection("jdbc:odbc:project","scott","tiger");

out.println("connect");
stmt=dbconn.createStatement();
out.println("statement");
String name=(String)req.getParameter("uname");
String pwd=(String)req.getParameter("pwd");
/*String name1=(String)req.getParameter("uname");
String pwd1=(String)req.getParameter("pwd");*/
out.println("hello");

rs=stmt.executeQuery("select * from login where uname='"+name+"'");
rs.next();
out.println(rs);
            if((rs.getString("pwd")).equals(pwd))
           {
             out.println("welcome");
	     RequestDispatcher rd = req.getRequestDispatcher("admin1.html");
	     rd.forward(req,res);

           }

     
		   }

        
	catch(Exception e)
	{
       
	}
}
    
}
