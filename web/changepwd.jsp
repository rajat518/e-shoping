<%-- 
    Document   : changepwd
    Created on : Nov 5, 2012, 12:49:52 PM
    Author     : G5
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       <%
String s1=null,s2=null,s3=null;
s1=request.getParameter("name");
s2=request.getParameter("password");
s3=request.getParameter("t2");
Statement ps=null;
Statement ps1=null;
Statement ps2=null;
Connection con=null;
ResultSet rs=null;
ResultSet rs1=null;
int i=0;
String sql1="update user_details set password='"+s2+"'";
String sql2="select password from user_details where password='"+s2+"'";

 try
		{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
        	
		con=DriverManager.getConnection("jdbc:odbc:project","scott","tiger");		
		
		ps=con.createStatement();
                ps1=con.createStatement();
                ps2=con.createStatement();
                String sql="select user_id from user_details where password='"+s1+"'";
                rs=ps.executeQuery(sql);
                if(rs.next())
                                       {
                    rs1=ps1.executeQuery(sql2);
                   
                    if(rs1.next())
                                               {
                        
                    }
                    else
                                               {
                                               
                         out.print("hello");
                        i=ps2.executeUpdate(sql1) ;
                         RequestDispatcher rd=request.getRequestDispatcher("eprofile.html");
                    rd.forward(request, response);
                    }
                   
                
              
               
                   
               
                        }
                               }
 catch(Exception e)
                 {
     out.print(e);
 }
 
%>
    </body>
</html>
