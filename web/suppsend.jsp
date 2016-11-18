
<html>
<head>
<title>Supplier</title>

<body>
<table>
<td background="order.JPG" width=1000 height=135></td>
</table>
<table align="right">               
<td>
<a href="pro.html"><img src="back.gif" border=0></a>                                 
</table>

<center><font size =6 color=blue><strong>Supplier Order</strong></font></center>
<br><br>
<center><font size=5>WELCOME</font><font size=5 color=red> &nbsp;<%=session.getAttribute("supp_id")%></font></center>
<%@ page language="java" import="java.sql.*" %>
<%
String driver = "sun.jdbc.odbc.JdbcOdbcDriver";
Class.forName(driver).newInstance();
Connection con=null;
Statement ps=null;
ResultSet rs=null;

con=DriverManager.getConnection("jdbc:odbc:project","scott","tiger");
ps=con.createStatement();
int p=0,quantity=0;



String product_name="",product_id="",branch_id="",supplier_id="",dd="",s3="";
%>
<%

	s3="select pro_name,pro_id,branch_id,quantity,dd from sup_order where supp_id='"+session.getAttribute("supp_id")+"'";
	rs=ps.executeQuery(s3);
%>



<br>
<table width="770" border="1">

<tr>
<td>S.no  </td><td>Product Id</td>
<td>Product Name</td>
<td>Branch Id</td>
<td>Quantity</td>
<td>Date</td>
</tr>

<%
while(rs.next())
{
	          p++;
	         product_name=rs.getString("pro_name");
             product_id=rs.getString("pro_id");	
			 branch_id=rs.getString("branch_id"); 
			 quantity=Integer.parseInt(rs.getString("quantity"));
			 dd=rs.getString("dd");
				
				
%>

<form action="./Suppsend" method="post">
<input type= hidden name = pid value=<%=product_id%>></input>
<input type= hidden name = bid value=<%=branch_id%>></input>
<input type= hidden name = qty value=<%=quantity%>></input>
<tr>

<td><%=p%></td>
<td><%=product_id%></td>
<td><%=product_name%></td>
<td><%=branch_id%></td>
<td><%=quantity%></td>
<td><%=dd%></td>
<td><input type="submit" name="send" value="SEND"></input></td>
</tr>
</form>
<%}
%>
</table>
</table>
</body>
</html>
