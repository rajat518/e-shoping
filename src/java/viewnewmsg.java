

import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;
import java.sql.*;
import java.lang.*;
public class viewnewmsg extends HttpServlet
{
 Connection con=null;
 Statement ps=null;
 ResultSet rs=null;
 public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
 {
  res.setContentType("text/html");
  PrintWriter pw=res.getWriter();
 
  try
  {
   Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
   con=DriverManager.getConnection("jdbc:odbc:project","scott","tiger");		
   ps=con.createStatement();
   RequestDispatcher rd;
   int i=0;
  //int j=0; 
  pw.println("<html><body>");
  pw.println("<table border=0 cellspacing=0 cellpadding=0>");
pw.println("<tr>");
pw.println("<td><img src='s.gif' width=300 height=135></td>");
pw.println("<td background='com.gif' width=700></td>");
pw.println("</tr>");
pw.println("</table>");
pw.println("</body></html>");
 
   String qry="select comp_id,pro_name,user_id,mess,status,d from complaints";
    // pw.println(qry);
 rs= ps.executeQuery(qry);
 while(rs.next())
	  {
	
   req.setAttribute("comp_id["+i+"]",rs.getString(1));
   //pw.println(rs.getString(1));
   req.setAttribute("pro_name["+i+"]",rs.getString(2));
   req.setAttribute("user_id["+i+"]",rs.getString(3));
   //req.setAttribute("subject["+i+"]",rs.getString(4));
   req.setAttribute("mess["+i+"]",rs.getString(4));
   	   req.setAttribute("status["+i+"]",rs.getInt(5));
		req.setAttribute("date["+i+"]",rs.getString(6));
//pw.println(rs.getString(1)+"&nbsp;"+rs.getString(2)+"&nbsp;"+rs.getString(3)+"&nbsp;"+rs.getString(4)+"&nbsp;"+rs.getString(5)+"&nbsp;"+i+"<br><br><br>");
	   i++;
	  }
	 
req.setAttribute("cnt",i);
con.commit();
/*     pw.println(rs.getString(1)+"&nbsp;"+rs.getString(2)+"&nbsp;"+rs.getString(3)+"&nbsp;"+rs.getString(4)+"&nbsp;"+rs.getString(5)+"&nbsp;"+rs.getString(6)+"&nbsp;"+rs.getString(7)+"<br>");
    j=0;
	rs=ps.executeQuery("select * from responsemsg");
  while(rs.next())
	 {
req.setAttribute("aut1["+j+"]",rs.getString(1));
   req.setAttribute("ml1["+j+"]",rs.getString(2));
   req.setAttribute("sub1["+j+"]",rs.getString(3));
   req.setAttribute("cid1["+j+"]",rs.getString(4));
   req.setAttribute("msg1["+j+"]",rs.getString(5));
   req.setAttribute("day["+j+"]",rs.getString(6));
   req.setAttribute("ansid["+j+"]",rs.getString(7));
   req.setAttribute("qid1["+j+"]",rs.getString(8));
 //   pw.println(rs.getString(1)+"&nbsp;"+rs.getString(2)+"&nbsp;"+rs.getString(3)+"&nbsp;"+rs.getString(4)+"&nbsp;"+rs.getString(5)+"&nbsp;"+rs.getString(6)+"&nbsp;"+rs.getString(7)+"&nbsp;"+rs.getString(8)+"<br>");
   j++;
	  }
	 req.setAttribute("cnt1",j);*/
	  rd=req.getRequestDispatcher("postnew.jsp");
	  rd.forward(req,res);
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