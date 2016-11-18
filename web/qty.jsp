
<html>
<head>
<title>Quantity</title>
</head>
<body>

<table>
<td ><img src="sup1.JPG" width=1000 height=135></td>
</table>
<table align="right">               
<td>
<a href="pro.html"><img src="back.gif" border=0></a>                                 
</table>                
<br>
<center><font size=5>Welcome &nbsp;&nbsp;&nbsp;<font color=red>
<%= session.getAttribute("cuser_id")%></font></font></center>
 <% String idee=request.getParameter("id");

int k=0;%>

 <%@ page language="java" import="java.sql.*" %>               
<%
   String driver = "sun.jdbc.odbc.JdbcOdbcDriver";
   Class.forName(driver).newInstance();
   Connection con=null;
   ResultSet rs,rs1;
   Statement  ps,ps1=null;
   con=DriverManager.getConnection("jdbc:odbc:project","scott","tiger");
   ps=con.createStatement();
   ps1=con.createStatement();
 %>

 <%  rs=ps.executeQuery("select * from products where pro_id='"+idee+"'");%>
 
   <%
 String name="",cate="",des="",path="";
 
 int price=0,minqty=0;
double price1=0;%>

	<% while(rs.next())
	{
       name=rs.getString(11);
    String pp=rs.getString(2);
    price1=Double.parseDouble(pp);
    cate=rs.getString(10);
    des=rs.getString(7) ;
    path=rs.getString(1);
	
	}

    %>


 <%   rs1 = ps1.executeQuery("select minqty from minqty where category='"+cate+"'");
	 while(rs1.next())
	{
		minqty=1;

         minqty=Integer.parseInt(rs1.getString(1));

	}
	
	%>

 
<table border=0 cellpadding=5 cellspacing=5 align=center >
<tr><td><img src="<%=path%>" width=100 height= 100></tr>
<tr><td><strong>Product ID:</strong>&nbsp;&nbsp;&nbsp;<%=idee%></tr>
<br>
<tr><td><strong> Name:</strong>&nbsp;&nbsp;&nbsp;<%=name%></tr>
<br>
<tr><td><strong>Unit Price:</strong>&nbsp;&nbsp;&nbsp;<%=price1%>
</tr>
<tr><td><strong>Description:</strong>&nbsp;&nbsp;&nbsp;<%=des%>
</tr>
<tr><td><strong>Minimum Quantity:</strong>&nbsp;&nbsp;&nbsp;<%=minqty%>
</tr>

<tr><td>
<form name='frm1' onSubmit="true" action="./Checkqty" method="POST">
<strong>Enter the quantity </strong><input type=text name='t1' size=5> </tr> 
<input type="hidden" name="cat" value="<%=cate%>">
<input type="hidden" name="id" value="<%=idee%>">
<tr><td><input type='submit' name='butt' value='submit'></tr>
</form> 
</table>
</body>
</html>