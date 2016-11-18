<html>
<head>
<title>Branch</title>

<body>
<table>
<td background="x.jpg" width=1000 height=135></td>
</table>
<table align="right">               
<td>
<a href="pro.html"><img src="back.gif" border=0></a>                                 
</table>

<center><font size =6 color=blue><strong>Supplier Order</strong></font></center>
<br><br>
<center><font size=5>WELCOME</font><font size=5 color=red> &nbsp; <%=session.getAttribute("branch_id")%></font></center>




<%@ page language="java" import="java.sql.*" %>
<%
String driver = "sun.jdbc.odbc.JdbcOdbcDriver";
Class.forName(driver).newInstance();
Connection con=null;
Statement ps=null;
ResultSet rs=null;

con=DriverManager.getConnection("jdbc:odbc:project","scott","tiger");
ps=con.createStatement();
int p=0,qty=0,price=0;
String pid="",uid="";


String orderid=(String)request.getParameter("orid");

%>
<%

	rs=ps.executeQuery("select pro_id,user_id,pro_price,pro_qty from orderdetails where orderid='"+orderid+"'");
%>



<br>
<table width="770" border="1">

<tr>
<td>S.no  </td><td>OrderId </td>
<td>Product Id</td>
<td>UserId</td>
<td>Price</td>
<td>Quantity</td>
</tr>



<%
while(rs.next())
{
	          p++;
	           pid=rs.getString("pro_id");
			   uid=rs.getString("user_id");
			   price=rs.getInt("pro_price");
			   qty=rs.getInt("pro_qty");
				
%>
<form action="./Set1" method="post">
<input type= hidden name = oid value=<%=orderid%>></input>

<tr>

<td><%=p%></td>
<td><%=orderid%></td>
<td><%=pid%></td>
<td><%=uid%></td>
<td><%=price%></td>
<td><%=qty%></td>
</tr>
</form>

<%}
%>
</table>




</body>
</html>




