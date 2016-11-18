package src;

import java.lang.*;
import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;
import java.sql.*;
public class complaintmulti extends HttpServlet
{
       Connection con=null;
       Statement ps=null;
       ResultSet rs=null;
       ResultSet rs1=null;
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
//tring s=req.getParameter("custid");
               int p=0;

                         rs1=ps.executeQuery("select * from complaints ");
               rsmd=rs1.getMetaData();
               int cols=rsmd.getColumnCount();
           int rows=0;

               while(rs1.next())
                       {
                               rows++;
                       }

                       rs1.close();

                      
                       String arr[][]=new String[rows+1][cols+1];
                      int i=-1;

                               rs=ps.executeQuery("select * from complaints");

                                 while(rs.next())
                       {

                       i++;
                            for(int j=1;j<=cols;j++)

                          {
                           arr[i][j]=rs.getString(j);

                                                   }
               }


           for(int k=0;k<rows;k++)
                       {
                               for(int l=1;l<=cols;l++)
                               {
                                        req.setAttribute("arr["+k+"]["+l+"]",arr[k][l]);
               }


                       }

                        req.setAttribute("rows",rows);

                        req.setAttribute("cols",cols);

                       rd=req.getRequestDispatcher("commulti.jsp");
                               rd.include(req,res);

               rs.close();

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
