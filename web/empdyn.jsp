

<html>
<body bgcolor=#f7f6f9 text=657693>
<form name="f" action="./updateemp" method="get">
<table align="right">
<tr>
<td align="right"><a href="admin.html"><img src="C:\Program Files\Apache Software Foundation\Tomcat 5.5\webapps\swapna\homepage.gif"  border="0" ></a></td></tr>
</table>
<table border="0" class="box" cellpadding="1" cellspacing="0" width="90%"  align=center>
						   <tr>
                           <td>
	        <table border="0"  width="100%" cellpadding="3" cellspacing="0" align="center">
		<tr>
			<th colspan=2><h1>Employee Details form</h1></td>
		</tr>
		<tr>
                                    <td >
<h4>Employees id</h4>
			</td>
			<td >
			<input type="text"  name="empid"   style="TEXT-TRANSFORM: capitalize"  maxlength="25"                                                          size="40" value="<%=request.getAttribute("empid")%>"readonly >
			</td>
		</tr>
		<tr>
			<td >
			
<h4>Employee name</h4>

			</td>
			<td >
			<input type="text" name="ename" style="TEXT-TRANSFORM: capitalize" maxlength="25"  size="40" value=<%=request.getAttribute("ename")%> >
			</td>
		</tr>
		   <td width="40%">                  
		<tr>
			<td width="40%">

<h4>Designation</h4>
			</td>
			<td >
			<input type="text"  name="desg"   style="TEXT-TRANSFORM: capitalize"  maxlength="25"                                                          size="40" value="<%=request.getAttribute("desg")%>">
			</td>
		</tr>
                 
				  
				  <td width="40%"> 
                  
		<tr>
			<td width="40%">

<h4>Branch id</h4>
			</td>
			<td >
			<input type="text"  name="branchid"   style="TEXT-TRANSFORM: capitalize"  maxlength="25"                                                          size="40" value="<%=request.getAttribute("branchid")%>">
			</td>
		</tr>
		
<tr>
			<td width="40%">

<h4>ADDRESS</h4>
			</td>
			<td >
			<input type="text" name="addr" style="TEXT-TRANSFORM: capitalize"  maxlength="25"  size="40" value="<%=request.getAttribute("addr")%>" > </textarea>
			</td>
		</tr>

		<tr>
<td width="40%">
<h4>CITY</h4>

			</td>
			<td>
			<textarea name="city" rows=4 style="width:230;"><%=request.getAttribute("city")%></textarea>
			</td>
		</tr>

		<tr>
<td width="40%">
<h4>STATE</h4>

			</td>
			<td>
			<textarea name="state" rows=4 style="width:230;"><%=request.getAttribute("state")%></textarea>
			</td>
		</tr>
<tr>
<td width="40%">
<h4>COUNTRY</h4>

			</td>
			<td>
			<textarea name="country" rows=4 style="width:230;"><%=request.getAttribute("country")%></textarea>
			</td>
		</tr>
	<tr>
			<td width="40%">

</td>
</tr>

		<tr>
<td width="40%">
<h4>Mobile number</h4>

			</td>
			<td>
			<textarea name="mobno" rows=4 style="width:230;"><%=request.getAttribute("mobno")%></textarea>
			</td>
		</tr>

		<tr>
<td width="40%">
<h4>Hire date</h4>

			</td>
			<td>
			<textarea name="hire_date" rows=4 style="width:230;"><%=request.getAttribute("hire_date")%></textarea>
			</td>
		</tr>
		
		<tr>
<td width="40%">
<h4>Emailid</h4>

			</td>
			<td>
			<textarea name="emailid" rows=4 style="width:230;"><%=request.getAttribute("emailid")%></textarea>
			</td>
		</tr>

		<tr>
<td width="40%">
<h4>Password</h4>

			</td>
			<td>
			<textarea name="password" rows=4 style="width:230;"><%=request.getAttribute("password")%></textarea>
			</td>
		</tr>
		
		<tr>
<td width="40%">
<h4>salary</h4>

			</td>
			<td>
			<textarea name="salary" rows=4 style="width:230;"><%=request.getAttribute("salary")%></textarea>
			</td>
		</tr>
	</table>

<input type="submit" name="submit" value="UPDATED">

</form>


</table>

<!-- End of regpatient FORM -->
</body>
</html>
