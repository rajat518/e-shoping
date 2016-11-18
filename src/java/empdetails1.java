

import java.lang.*;
import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;
import java.sql.*;
public class empdetails1 extends HttpServlet
{
       Connection con=null;
       Statement ps=null;
       ResultSet rs=null;
       ResultSetMetaData rsmd=null;
       public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
       {
                       res.setContentType("text/html");
               PrintWriter pw=res.getWriter();

               try
               {
               Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
           con=DriverManager.getConnection("jdbc:odbc:project","scott","tiger");
               ps=con.createStatement();
       RequestDispatcher rd;

               int p=0;

 		 rs=ps.executeQuery("select * from emp_details");
               rsmd=rs.getMetaData();
			   int cols=rsmd.getColumnCount();
			      int rows=0;
               while(rs.next())
                       {
                               rows++;
							     for(int j=1;j<=cols;j++)
                               {
			                            req.setAttribute("arr["+rows+"]["+cols+"]",rs.getString(j)); 
								}
                       }

                      rs.close();

                        req.setAttribute("rows",rows);

                        req.setAttribute("cols",cols);

                     
rd=req.getRequestDispatcher("empmulti.jsp");
                             rd.include(req,res);



       ps.close();
	          con.commit();
               con.close();
			   

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
