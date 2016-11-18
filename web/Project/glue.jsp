<html>
<head>
<title>Glue</title>
</head>
<body>

<table>
<td background="x.jpg" width=1000 height=135></td>
</table>
<table align="right">               
<td>
<a href="pro.html"><img src="back.gif" border=0></a>  
                               
</table>                
<br>
<h3>Welcome</h3>
<%= session.getAttribute("cust_id")%>

<%@ page language="java" import="java.sql.*" %>               
<%
   String driver = "sun.jdbc.odbc.JdbcOdbcDriver";
   Class.forName(driver).newInstance();
   Connection con=null;
   Statement  ps=null;
   con=DriverManager.getConnection("jdbc:odbc:project","scott","tiger");
   ps=con.createStatement();
 %>
 <%  int i=0; ResultSet rs=ps.executeQuery("select * from product where category='GLUE'");
 String id="",name="",cate="",des="",path="";
    int price=0,qty=0;%>

<table border=0>
<tr>
 <%while(rs.next())
 {  
   i++;       
  id=rs.getString(1);
  name=rs.getString(2);
  qty=Integer.parseInt(rs.getString(3));
  price=Integer.parseInt(rs.getString(4));
  cate=rs.getString(5);
  des=rs.getString(6) ;
  path=rs.getString(7);

%>    	 

<td>
<table border=0 cellpadding=5 cellspacing=5 >
<tr><td><img src="<%=path%>" width=100 height= 100 border=0></tr>
<tr><td><strong>Product ID:</strong>&nbsp;&nbsp;&nbsp;<%=id%></tr>
<br>
<tr><td><strong> Name:</strong>&nbsp;&nbsp;&nbsp;<%=name%></tr>
<br>
<tr><td><strong>Unit Price:</strong>&nbsp;&nbsp;&nbsp;<%=price%>
</tr>
<tr><td>
<form name='frm1' onSubmit="true" action="qty.jsp" >
<input type="hidden" name="id" value="<%=id%>">
<input type='image' src="smallbutton.jpg">
</form>
</tr>
</table>
</td>
<%}%>
</tr>
</table>
<a href="./Viewcart">Click this to view...</a>

</body>
</html>