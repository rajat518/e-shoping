<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>*** ADMIN SERVICES ***</title>

<body>
<table>
<td background="x.jpg" width=1000 height=135></td>
</table>
<table align="right">               
<td>
<a href="pro.html"><img src="back.gif" border=0></a>                                 
</table>

<center><font size =6 color=blue><strong>ORDER TRACKING</strong></font></center>
<br><br><br><br><br>


<table width="770" align="center" cellpadding="0" cellspacing="0"  border="0">
<tr>
<form action="./adminot.jsp" method="post"> 
<td align="left">Start Date:</td><td align="left"><input type="text" name="sdate" value="DD/MM/YYYY"></input></td>
<td align="left">End Date:</td><td align="left"><input type="text" name="edate" value="DD/MM/YYYY"></input></td> <td><input type="submit" name="submit" value="DateSearch"></input></td>
</form>
</tr>
<tr><td><br></td><td></td><td></td></tr>
</table>
<%@ page language="java" import="java.sql.*" %>
<%
String driver = "sun.jdbc.odbc.JdbcOdbcDriver";
Class.forName(driver).newInstance();
Connection con=null;
Statement ps=null;
ResultSet rs=null;
con=DriverManager.getConnection("jdbc:odbc:PROJECT","scott","tiger");
ps=con.createStatement();
int p=0,id=0;
String s1="",s2="",s3="";
s1=request.getParameter("sdate");
s2=request.getParameter("edate");
String sdate1="",custid="",ordadd="",ordcity="",state1="",ordstatus="",ordprice="",pincode="";
%>
<%

	s3="select orders1.orderid,orders1.ord_add,orders1.ord_city,orders1.ord_state,orders1.ord_pincode,to_char(orders1.ord_date,'dd/mm/yyyy') the_date,orders1.ord_status,orders1.ord_price,orderdetails.cust_id from orders1,orderdetails where orders1.orderid=orderdetails.orderid and to_char(orders1.ord_date,'dd/mm/yyyy')>='"+s1+"' and to_char(orders1.ord_date,'dd/mm/yyyy')<='"+s2+"' order by orders1.orderid";


	rs=ps.executeQuery(s3);
%>
<center>FROM DATE::<%=s1%>&nbsp;&nbsp;&nbsp;TO DATE::<%=s2%></center>
<table width="770" style="background-image:url(fullbgnd.gif)" border="1">
<tr>
<td>S.no  </td><td>Order No.</td>
<td>Date</td><td>Customer ID</td>
<td>Shipping Address</td>
<td>Total Price</td>
<td>Status</td>
<td>Set Status</td>
<td>Action</td>
</tr>



<%
while(rs.next())
{
				p++;
				id=Integer.parseInt(rs.getString(1));
				ordadd=rs.getString(2);
				ordcity=rs.getString(3);
				state1=rs.getString(4);
	
				pincode=rs.getString(5);
				sdate1=rs.getString(6);
				ordstatus=rs.getString(7);
               	ordprice=rs.getString(8);
				custid=rs.getString(9);
				
%>

<form action="./Set>" method="post">
<input type= hidden name = oid value=<%=id%></input>
<tr>

<td><%=p%></td>
<td><%=id%></td>
<td><%=sdate1%></td>
<td><%=custid%></td>
<td><%=ordadd%>,&nbsp <%=ordcity%>,&nbsp<%=state1%>,&nbsp<%=pincode%></td>
<td><%=ordprice%></td>
<td><%=ordstatus%></td>
<td><select name="status" ID="statusid">
<option value="select" selected="selected">order status</option> 
<option value="New">New</option>
<option value="OnHold">OnHold</option>
<option value="To Ship">To Ship</option> 
<option value="Partially Shipped">Partially Shipped</option>
<option value="Shipped">Shipped</option>
<option value="Returned">Returned</option> 
<option value="Cancelled">Cancelled</option></select></td>
<td><input type="submit" name="setstatus" value="go"></input></td>
</tr>
</form>
<%}
%>
</table>
</table>

</body>
</html>
