package src;

 import java.sql.*;
 import javax.servlet.*;
 import java.io.*;
 import java.util.*;

 public class home extends GenericServlet
  {
    public void service(ServletRequest rq, ServletResponse rs) throws IOException,ServletException
     {
       rs.setContentType("text/html");
       PrintWriter pw=rs.getWriter();
       BufferedReader br = new BufferedReader(new FileReader("C:/Program Files/Apache Software Foundation/Tomcat 5.5/webapps/project/home1.txt"));
       String data="";
       while((data=br.readLine()) != null)
       pw.println(data);
       pw.close();
     }
     
   
  }
