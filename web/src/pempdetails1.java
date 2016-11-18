package src;

import javax.servlet.*;
import java.io.*;
import java.sql.*;
import java.text.*;
import javax.servlet.http.*;
public class pempdetails1 extends HttpServlet
{
            Connection con=null; 
              RequestDispatcher rd;
              Statement s=null;
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
    {
    PrintWriter pw = res.getWriter();    
    res.setContentType("text/html");
     HttpSession hs=req.getSession(true);
	int j=0;
    try
    {
        String id[]=req.getParameterValues("n");
  
       
               Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
             
               con=DriverManager.getConnection("jdbc:odbc:project","scott","tiger");
                s=con.createStatement();
				 pw.println("<html>");
          
            pw.println("<body><center>");
			pw.println("<form name=del method=get action=./empdel>");

           
          
		   for(int i=0;i<id.length;i++)
          { 
			     String sql="delete from emp_details where emp_no='"+id[i]+"'";     

                       s.executeUpdate(sql);  
       
               pw.println("<br>");
             
      
pw.print("<h2><i>Database updated<i></h2>");
pw.print("<a href='admin.html'><h3><B>BACK</B></h3></a>");
			
			pw.println("</form></center></body></html>");		   
				
				
        pw.close();
    }}
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
