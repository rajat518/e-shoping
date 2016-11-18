<html>
<head>
<table border="0" cellspacing="0" cellpadding="0">
<tr>
<td><img src="C:\Program Files\Apache Software Foundation\Tomcat 5.5\webapps\swapna\s.gif" width=300 height=135></td>
<td background="C:\Program Files\Apache Software Foundation\Tomcat 5.5\webapps\swapna\cus.gif" width=700></td>
</tr>
</table>
</head>
<body text="black" >
<form name="pp" action="delete.html" method="get">
<br><br><br><br><br>

<%
Integer rows=(Integer)request.getAttribute("rows");
Integer cols=(Integer)request.getAttribute("cols");
%>
<table cellspacing="1" cellpadding="1" border="1">
<th><b><h4>name </h4></th>
<th><b><h4>user_id </h4></th>
<th><b><h4>password </h4></th>
<th><b><h4>company</h4></th>
<th><b><h4>phone_no </h4></th>
<th><b><h4>mobile_no</h4></th>
<th><b><h4>address</h4></th>
<th><b><h4>city </h4></th>
<th><b><h4>state </h4></th>
<th><b><h4>country </h4></th>
<th><b><h4>question </h4></th>
<th><b><h4>answer </h4></th>
<th><b><h4>birthday </h4></th>
<th><b><h4>pincode</h4></th>
<%

for(int i=0;i<rows;i++)
{
%>
<tr>
<%
for(int j=1;j<=cols;j++)
{%>
<td>&nbsp&nbsp
<%=request.getAttribute("arr["+i+"]["+j+"]")%>
</td>
<%}%>
</tr>
 <% } %>

</table>

</p>
<input type="submit" name="submit" value="delete">




</form>
</body>
</html>


