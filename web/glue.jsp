<html>
<head>
<title>Glue</title>
</head>
<body>

<table>
<td><img src='product1.JPG' width=970 height=145>
</tr>
</table>
<table align="right">               
<td>
<a href="custlogin.html"><img src="back.gif" border=0></a>  
                               
</table>                
<br>
<h3>Welcome</h3>
<%= session.getAttribute("cuser_id")%>

<%@ page language="java" import="java.sql.*" %>               
<%
   String driver = "sun.jdbc.odbc.JdbcOdbcDriver";
   Class.forName(driver).newInstance();
   Connection con=null;
   Statement  ps=null;
   con=DriverManager.getConnection("jdbc:odbc:project","scott","tiger");
   ps=con.createStatement();
 %>
 <%  int i=0; ResultSet rs=ps.executeQuery("select * from products where category='pen'");
 String id="",name="",cate="",des="",path="";
    int price=0,qty=0;%>

<table border=0>
<tr>
 <% while(rs.next())
 {  
   i++;     
    
	path=rs.getString(1);
   price=rs.getInt(2);
  qty=rs.getInt(3);
   id=rs.getString(4);
   des=rs.getString(5);
  cate=rs.getString(6);
   name=rs.getString(7);
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
<a href="Viewcart12">Click this to view...</a>

</body>
</html>