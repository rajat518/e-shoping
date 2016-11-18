package src;

import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;
import java.sql.*;
import java.lang.*;
public class jcomplaint extends HttpServlet
{
 Connection con=null;
 Statement ps=null;
 ResultSet rs=null;
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
   RequestDispatcher rd;
   pw.println("2");
   //String comp_id=req.getParameter("comp_id");
   String pro_name=req.getParameter("pro_name");
   String user_id=req.getParameter("user_id");
   //String subject=req.getParameter("sub");
   String mess=req.getParameter("mess");
    
     

  pw.println("3");
   String qry = "insert into complaints(pro_name,mess,user_id) values('"+pro_name+"','"+mess+"','"+user_id+"')";
   ps.executeUpdate(qry);
   pw.println("4");
  con.commit();

/*int i=0;
  rs=ps.executeQuery("select * from complaints");
  while(rs.next())
	  {
req.setAttribute("aut["+i+"]",rs.getString(1));
req.setAttribute("ml["+i+"]",rs.getString(2));
req.setAttribute("sub["+i+"]",rs.getString(3));
req.setAttribute("cid["+i+"]",rs.getString(4));
req.setAttribute("msg["+i+"]",rs.getString(5));
req.setAttribute("dt["+i+"]",rs.getString(6));
i++;
	  }
	  req.setAttribute("cnt",i);
	  rd=req.getRequestDispatcher("post.jsp");
	 rd.forward(req,res);*/
pw.println("<html><body>");
pw.println("<center>");
pw.println("<h3>inserted</h3>");
pw.println("<h1><a href='edit11.html'><i>return...</i></a></h1>");
pw.println("</center>");
pw.println("</body></html>");
 
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
}
}