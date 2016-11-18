<html>
 <body>
<form name="f1" action="./del"  method="post"> 
<table>
                     <tr>
                     <td >
<h4>name    </h4>
			</td>
			<td >
                                                       <input type="text" name="x1" value=<%=request.getAttribute("name")%>>
			</td>
		</tr>
		<tr>
			<td >

<h4>user_id</h4>
			</td>
			<td >
                                                       <input type="text" name="x1" value=<%=request.getAttribute("user_id")%>>
			</td>
		</tr>
		<tr>
			<td >			
<h4>password  </h4>

			</td>
			<td >
			 <input type="text" name="x2" value=<%=request.getAttribute("password")%>>
			</td>
		</tr>
		<tr>
			

<td>
<h4>company </h4>
			</td>
			<td >
			 <input type="text" name="x3" value=<%=request.getAttribute("company")%>>
			</td>
		</tr>
                  	<tr>
			<td>
                      
		
<h4>address </h4>

		</td><td>
			 <input type="text" name="x4" value=<%=request.getAttribute("address")%>>
			</td>
		</tr>

	
		<tr>
			<td>
			
<h4>phone number</h4>

		</td>
<td>			 <input type="text" name="x5" value=<%=request.getAttribute("pn")%>>
			</td>
		</tr>
<tr>
			<td>
			
<h4>mobile number</h4>

		</td>
<td>			 <input type="text" name="x5" value=<%=request.getAttribute("mb")%>>
			</td>
		</tr>
<tr>
			<td>
			
<h4>city</h4>

		</td>
<td>			 <input type="text" name="x5" value=<%=request.getAttribute("city")%>>
			</td>
		</tr>	
		<tr>
			<td>
			
<h4>state</h4>

		</td>
<td>			 <input type="text" name="x5" value=<%=request.getAttribute("state")%>>
			</td>
		</tr>
		<tr>
			<td>
			
<h4>country</h4>

		</td>
<td>			 <input type="text" name="x5" value=<%=request.getAttribute("country")%>>
			</td>
		</tr>
		<tr>
			<td>
			
<h4>pincode</h4>

		</td>
<td>			 <input type="text" name="x5" value=<%=request.getAttribute("pin")%>>
			</td>
		</tr>
		<tr>
			<td>
			
<h4>Date Of Birth</h4>

		</td>
			<td>		
                         <input type="text" name="x6" value=<%=request.getAttribute("dob1")%>>
			</td>
		</tr>
		
</table>
       
<center>
<input type="submit"  value=Delete>
</center>

</form>

</body>
</html>