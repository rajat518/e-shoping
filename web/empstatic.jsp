

<html>
<body bgcolor=#f7f6f9 text=657693>
<form name="f" method="get" action="./jempst">
<table align="right">
<tr>
<td align="right"><a href="home.html"><img src="C:\Program Files\Apache Software Foundation\Tomcat 5.5\webapps\swapna\homepage.gif"  border="0" ></a></td></tr>
</table>

<table border="1" class="box" cellpadding="1" cellspacing="1" width="90%"  align=center>
                           <tr>
                           <td>
	        <table border="0"  width="100%" cellpadding="3" cellspacing="0" align="center">
		<tr>
			<th colspan=2><h1>Employee Details </h1></td>
		</tr>
		<tr>
            <td >
<h4>EMPLOYEE ID</h4>
			</td>
			<td >
			<%=session.getAttribute("empid")%>
			</td>
		</tr>
		<tr>
            <td >
<h4>EMPLOYEE NAME</h4>
			</td>
			<td >
			<%=request.getAttribute("ename")%>
			</td>
		</tr>
		<tr>
			<td >
			
<h4>DESIGNATION</h4>

			</td>
			<td >
			<%=request.getAttribute("desg")%>
			</td>
		</tr>
		<tr>
			<td> 
<h4>BRANCH ID</h4> 
			</td>
			<td >
			<%=request.getAttribute("branchid")%>
			</td><td width="40%"> 
		</tr>
<tr><td>
<h4>ADDRESS</h4></td>
			<td >
			<%=request.getAttribute("addr")%>
             </td>         
		</tr>
	<tr>		<td>
<h4>CITY</h4>
			</td>
			<td >
			<%=request.getAttribute("city")%>
			</td>
		</tr>
             <tr>
			 
			  
			  <td>
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
		<tr><td>
<h4>MOBILE NUMBER</h4>
			</td>
			<td >
			<%=request.getAttribute("mobno")%>
			</td>
			
		</tr>
<tr><td>
<h4>HIRE DATE</h4>
			</td>	
			<td >
			<%=request.getAttribute("hire_date")%>
			</td></tr>
<tr><td>
<h4>EMAIL ID</h4>
			</td>
			<td >
			<%=request.getAttribute("emailid")%>
			</td>			
		</tr>		
<tr><td>
<h4>PASSWORD</h4>
			</td>
			<td >
			<%=request.getAttribute("password")%>
			</td>			
		</tr>
		<tr><td>
<h4>SALARY</h4>
			</td>
			<td >
			<%=request.getAttribute("salary")%>
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
