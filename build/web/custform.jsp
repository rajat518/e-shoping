

<html>
<body bgcolor=#f7f6f9 text=657693>
<form name="f" method="post" action="./view1">
<table align="right">
<tr>
<td align="right"><a href="home.html"><img src="C:\Program Files\Apache Software Foundation\Tomcat 5.5\webapps\swapna\homepage.gif"  border="0" ></a></td></tr>
</table>

<table border="1" class="box" cellpadding="1" cellspacing="1" width="90%"  align=center>
                           <tr>
                           <td>
	        <table border="0"  width="100%" cellpadding="3" cellspacing="0" align="center">
		<tr>
			<th colspan=2><h1>Customer Details </h1></td>
		</tr>
		<tr>
			<td >
			
<h4>USER ID</h4>

			</td>
			<td >
			<%=session.getAttribute("cuser_id")%>
			</td>
		</tr>
		<tr>
            <td >
<h4>CUSTOMER NAME</h4>
			</td>
			<td >
			<%=request.getAttribute("cname")%>
			</td>
		</tr>
		
		<tr>
			<td> 
<h4>COMPANY</h4>
			</td>
			<td >
			<%=request.getAttribute("company")%>
			</td><td width="40%"> 
		</tr>

	<tr>		<td>
<h4>MOBILE NUMBER</h4>
			</td>
			<td >
			<%=request.getAttribute("mobile number")%>
			</td>
		</tr>
             <tr>
			 
			  
			  <td>
<h4>ADDRESS</h4>
			</td>
			<td >
			<%=request.getAttribute("addr")%>
			</td>
		</tr>
		<tr><td>
<h4>CITY</h4>
			</td>
			<td >
			<%=request.getAttribute("city")%>
			</td>
			
		</tr>
		<tr><td>
<h4>STATE</h4>
			</td>
			<td >
			<%=request.getAttribute("state")%>
			</td>
			
		</tr>
<tr><td>
<h4>COUNTRY</h4>
			</td>
			<td >
			<%=request.getAttribute("country")%>
			</td>
			
		</tr>		
</table></table>
&nbsp;
&nbsp;
<table align="center">

<input type="submit" name="submit" value="UPDATE">
</form>
</table>


<!-- End of regpatient FORM -->
</body>
</html>
