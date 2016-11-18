

<html>
<body bgcolor=#f7f6f9 text=657693>
<form name="f" action="./update" method="get">

<table border="0" class="box" cellpadding="1" cellspacing="0" width="90%"  align=center>
						   <tr>
                           <td>
	        <table border="0"  width="100%" cellpadding="3" cellspacing="0" align="center">
		<tr>
			<th colspan=2><h1>Customer Details form</h1></td>
		</tr>
		<tr>
			<td >
			
<h4>customer id</h4>

			</td>
			<td >
			<input type="text" name="cuser_id" style="TEXT-TRANSFORM: capitalize" maxlength="25"  size="40" value="<%=session.getAttribute("cuser_id")%>" readonly >
			</td>
		</tr>
		<tr>
                                    <td >
<h4>customer name</h4>
			</td>
			<td >
			<input type="text"  name="cname"   style="TEXT-TRANSFORM: capitalize"  maxlength="25"                                                          size="40" value=<%=request.getAttribute("cname")%>>			</td>
		</tr>
		                  
		<tr>
			<td width="40%">

<h4>company</h4>
			</td>
			<td >
			<input type="text"  name="comp"   style="TEXT-TRANSFORM: capitalize"  maxlength="25" size="40" value="<%=request.getAttribute("company")%>">
			</td>
		</tr>
                 
				  
				  <td width="40%"> 
                  
		
		
<tr>
			<td width="40%">

<h4>MOBILE NUMBER</h4>
			</td>
			<td >
			<input type="text"  name="mob_no"   style="TEXT-TRANSFORM: capitalize"  maxlength="25"                                                          size="40" value="<%=request.getAttribute("mobile number")%>">
			</td>
		</tr>
<tr>
<td width="40%">
<h4>Address</h4>

			</td>
			<td>
			<textarea name="addr" rows=4 style="width:230;"><%=request.getAttribute("addr")%></textarea>
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
	</table>

<input type="submit" name="submit" value="UPDATE">

</form>


</table>

<!-- End of regpatient FORM -->
</body>
</html>
