
<html>
<head>
<title>CONFIRM ORDER</title>

<script>
function isValidDetails()
{
if(f.add.value.length==0)
{ alert("Please Enter Valid Address");f.add.value="";f.add.focus();return false;}
else if(f.add.value=="D.No./ Street / Lane")
{ alert("Please Enter Valid Address");f.add.value="";f.add.focus();return false;}
else if(f.city.value.length==0)
{ alert("Please Enter Valid Address");f.city.value="";f.city.focus();return false;}
else if(f.city.value=="city")
{ alert("Please Enter Valid Address");f.city.value="";f.city.focus();return false;}
else if(f.state.value.length==0)
{ alert("Please Enter Valid Address");f.state.value="";f.state.focus();return false;}
else if(f.state.value=="state")
{ alert("Please Enter Valid Address");f.state.value="";f.state.focus();return false;}
else if(f.pin.value.length<6)
{ alert("Please Enter Valid Address");f.pin.value="";f.pin.focus();return false;}
else if(f.pin.value=="pincode")
{ alert("Please Enter Valid Address");f.pin.value="";f.pin.focus();return false;}
else if(f.ccno.value.length==0)
{ alert("Please Enter Valid Credit Card Number");f.ccno.value="";f.ccno.focus();return false;}
else if(f.ccno2.value.length<3)
{ alert("Please Enter Valid PIN");f.ccno2.value="";f.ccno2.focus();return false;}
}
</script>
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

<font size=5 color=red> !!!Invalid Transaction by the card!!!!!!<font>

<%@ page language="java" import="java.sql.*" %>
<%
String driver = "sun.jdbc.odbc.JdbcOdbcDriver";
Class.forName(driver).newInstance();
Connection con=null;
Statement stmt,st=null;
ResultSet rs,rs2=null;
con=DriverManager.getConnection("jdbc:odbc:project","scott","tiger");
stmt=con.createStatement();
st=con.createStatement();
int p=0,q=0,tqty=0;
String name1="",address="",city="",state="",name2="";
int pincode=0;
double sum=0.00;
rs=stmt.executeQuery("select company,address,city,state,pincode from user_details where user_id='"+session.getAttribute("cuser_id")+"'");
while(rs.next())
{
name1=rs.getString("company");
address=rs.getString("address");
city=rs.getString("city");
state=rs.getString("state");
pincode=rs.getInt("pincode");
}

rs2=st.executeQuery("select distinct cart.pro_qty,products.pro_price from products,cart where cart.user_id='"+session.getAttribute("cuser_id")+"' and cart.pro_id=products.pro_id");

while(rs2.next())
{		
              p=rs2.getInt(1);
              q=rs2.getInt(2);
            
sum=sum+q*p; tqty=p;
}
%>
<%if(sum!=0)
{
	%>
<strong>
<form name="f" onsubmit="return isValidDetails(this.form)" action="./Makeorder?amt=<%=sum%>" method="post">
<table width="800" border="0" align="center" cellpadding="3" cellspacing="5">
<tr>
<td width="185">&nbsp;
</td>
<td width="31">&nbsp;</td>
<td width="36">&nbsp;</td>
<td width="513">&nbsp;</td>
</tr><tr>
<td width="185" height="36">&nbsp;
</td>
<td width="31">&nbsp;</td>
<td width="36">&nbsp;</td>
<td width="513">&nbsp;</td>
</tr>
<tr>
<td width="185"><strong>
Customer Name</strong></td>
<td width="31">::</td>
<td width="36"><%=name1%>&nbsp;</td>
<td width="513"></td>
</tr>
<tr>
<td><strong>
Customer ID</strong></td>
<td>::</td>
<td><%=session.getAttribute("cuser_id")%></td>
<td>&nbsp;</td>
</tr>
<tr>
<td>
<strong>Billing/Customer Address</strong></td>
<td>::</td>
<td><%=address%>,&nbsp;<%=city%>
<br><%=state%>,&nbsp;<%=pincode%></td>
</tr>
<tr>
<td>
</td>
<td></td>
<td>&nbsp;</td>
<td>&nbsp;</td>
</tr>
<tr>
<td>
<strong>Shipping Address</strong></td>
<td>::</td>
<td><input type="text" name="add" id="add" maxlength="15" size="30" value="D.No./ Street / Lane"></input>
<br><input type="text" name="city" id="city"  maxlength="15" size="30"  value="city"></input>
<br><input type="text" name="state" id="state" maxlength="15" size="30" value="state"></input>
<br><input type="text" name="pin" id="pin" maxlength="15" size="30" value="pincode"></input>
</td>
</tr>
<tr>
<td></td>
<td>&nbsp;</td>
<td>&nbsp;</td>
</tr>
<tr>
<td><strong>
Date and Time</strong></td>
<td>::</td>
<td><%=new java.util.Date()%></td>
<td></td>
</tr>
<tr>
<td><strong>
Total Amount</strong></td>
<td>::</td>
<td>Rs.<%=sum%></td>
<td>&nbsp;</td>
</tr>
<tr>
<td><strong>
Credit Card No</strong></td>
<td>::</td>

<td><input type="text" name="ccno" id="ccno" maxlength="15" size="30" ></input></td>
</tr>
<tr>
<td><strong>
Credit Card PIN</strong></td>
<td>::</td>

<td><input type="password" id="ccno2" name="ccno2" maxlength="3" size="10"></td>
</tr>
</table>
<table  width="800" border="0" align="center" cellpadding="3" cellspacing="5" >
<tr><td align="center"><input type="submit" value="MAKEORDER"></input></td>
</tr>
</table>
</form>

<form action="./Viewcart">
<table align="center"><tr><td align="center">
<input type="submit" value="EDITCART"></input>
</tr>
<table>
</form>

<%}else { %>
<br><br><center><b>INVALID CART SELECTED....<a href="glue.jsp">PLEASE ADD ITEMS TO CART</a></b></center><br><br>
<%}%>
</body>
</html>
