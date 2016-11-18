

<html>
<body bgcolor=#f7f6f9 text=657693>
<form name="f" action=".\updatep" method="get">

<table border="0" class="box" cellpadding="1" cellspacing="0" width="90%"  align=center>
						   <tr>
                           <td>
	        <table border="0"  width="100%" cellpadding="3" cellspacing="0" align="center">
		<tr>
			<th colspan=2><h1>product details form</h1></td>
		</tr>
		<tr>
			<td >
			
<h4>product id</h4>

			</td>
			<td >
			<input type="text" name="pro_id" style="TEXT-TRANSFORM: capitalize" maxlength="25"  size="40" value="<%=session.getAttribute("pro_id")%>" readonly >
			</td>
		</tr>
		<tr>
			<td >
			
<h4>product name</h4>

			</td>
			<td >
			<input type="text" name="pro_name" style="TEXT-TRANSFORM: capitalize" maxlength="25"  size="40" value="<%=request.getAttribute("pro_name")%>"  >
			</td>
		</tr>
		<tr>
                                    <td >
<h4>pro_qty</h4>
			</td>
			<td >
			<input type="text"  name="pro_qty"   style="TEXT-TRANSFORM: capitalize"  maxlength="25"                                                          size="40" value=<%=request.getAttribute("pro_qty")%>>			</td>
		</tr>
		                  
		<tr>
			<td width="40%">

<h4>pro_price</h4>
			</td>
			<td >
			<input type="text"  name="pro_price"   style="TEXT-TRANSFORM: capitalize"  maxlength="25"                                                          size="40" value="<%=request.getAttribute("pro_price")%>">
			</td>
		</tr>
                 
				  
				  <td width="40%"> 
                  
		<tr>
			<td width="40%">

<h4>num_of_items</h4>
			</td>
			<td >
			<input type="text"  name="num_of_items"   style="TEXT-TRANSFORM: capitalize"  maxlength="25"                                                          size="40" value="<%=request.getAttribute("num_of_items")%>">
			</td>
		</tr>
		

<tr>
<td width="40%">
<h4>branch_id</h4>

			</td>
			<td>
			<input type="text"  name="branch_id"   style="TEXT-TRANSFORM: capitalize"  maxlength="25"                                                          size="40" value="<%=request.getAttribute("branch_id")%>">
			</td>
		</tr>
		<tr>
<td width="40%">
<h4>supp_id</h4>

			</td>
			<td>
			<input type="text"  name="supp_id"   style="TEXT-TRANSFORM: capitalize"  maxlength="25"                                                          size="40" value="<%=request.getAttribute("supp_id")%>"
			</td>
		</tr>

		<tr>
<td width="40%">
<h4>description</h4>

			</td>
			<td>
			<textarea name="description" rows=4 style="width:230;"><%=request.getAttribute("description")%></textarea>
			</td>
		</tr>

	</table>

<input type="submit" name="submit" value="UPDATED">

</form>


</table>

<!-- End of regpatient FORM -->
</body>
</html>
