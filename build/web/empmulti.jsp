<html>
<head>
<table border="0" cellspacing="0" cellpadding="0">
<tr>
<td><img src="C:\Program Files\Apache Software Foundation\Tomcat 5.5\webapps\swapna\s.gif" width=300 height=135></td>
<td background="C:\Program Files\Apache Software Foundation\Tomcat 5.5\webapps\swapna\emp.gif" width=700></td>
</tr>
</table>
</head>
<body text="black" >
<form name="pp" action="empinsert.jsp" method="post">
<br><br><br><br><br>

<%
Integer rows=(Integer)request.getAttribute("rows");
Integer cols=(Integer)request.getAttribute("cols");
%>
<table cellspacing="1" cellpadding="1" border="1">
<th><input type="checkbox" name="c1">
<th><b><h4>empid </h4></th>
<th><b><h4>ename </h4></th>
<th><b><h4>desg </h4></th>
<th><b><h4>branch id </h4></th>
<th><b><h4>city </h4></th>
<th><b><h4>state </h4></th>
<th><b><h4>country </h4></th>
<th><b><h4>moblie no</h4></th>
<th><b><h4>address</h4></th>
<th><b><h4>eamil id </h4></th>
<th><b><h4>password </h4></th>
<th><b><h4>hire_Date </h4></th>
<th><b><h4>salary</h4></th>
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
<input type="submit" name="submit" value="add">


</form>


<form action="empdel.html" method="post">
<input type="submit" name="submit" value="delete">
</form>
<form action="empveiw.html" method="post">
<input type="submit" name="submit" value="search">
</form>
</body>
</html>
