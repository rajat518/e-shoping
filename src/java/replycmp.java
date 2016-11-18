

import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;
import java.sql.*;
import java.lang.*;
public class replycmp extends HttpServlet
{
 Connection con=null;
 Statement ps=null;
 ResultSet rs=null;
 HttpSession ses=null;
 public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
 {
  res.setContentType("text/html");
  PrintWriter pw=res.getWriter();
  HttpSession ses=req.getSession();
  try
  {
   Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
   con=DriverManager.getConnection("jdbc:odbc:project","scott","tiger");		
   ps=con.createStatement();

   RequestDispatcher rd;
   int i=0;
 
String uid=(String)ses.getAttribute("cuser_id");
  
   String qry=("select * from replymsg where user_id='"+uid+"'");
   rs=ps.executeQuery(qry);
  
   pw.print("<html><body><center>");
 pw.print("<form><table cellpadding='1' cellspacing='1' border='1'>");
 pw.print("<tr><td>Select</td><td>Reply</td><td>Action</tr>");
// pw.println("<hr>");
 while(rs.next())
	  {
	 
	 String ry=rs.getString("reply");
	 String s5=rs.getString("sno");
	 int val=Integer.parseInt(s5);
	
	 pw.print("<tr><td>"+val+"<td>"+ry+"</td><td><a href=http://localhost:8082/swapna/replydel?msgid="+val+">Delete</a></tr>");
//pw.println("<td><hr></td>");

	  }
	  /*
	 pw.println("<br>length"+id.length);
for(int i=0;i<id.length;i++)
          { 
			   /  String sql="delete from emp_details where reply='"+ry+"'";     

                       ps.executeUpdate(sql);  
       
               pw.println("<br>a");
             
      	pw.println("<i><h3>deleted..</h3></i>");}
			pw.println("<input type='submit' name='submit' value='Delete'>");*/
			 pw.println("</center></table>");
 
			pw.println("</form></center></body></html>");		   
				
  }
    
    catch(ClassNotFoundException e)
    {
     pw.println("exception"+e);
    }
    catch(SQLException e)
    {
     pw.println("SQL EXCEPTION"+e);	
     pw.println("TRY AGAIN");
    }
    catch(Exception e)
    {
     pw.println("can't load driver"+e.getMessage());
    }
}
}