<html>
 <body>
<form name="f1" action="./deletstf"  method="post"> 
<table>
                     <tr>
                     <td >
<h4>Login Name    </h4>
			</td>
			<td >
                                                       <input type="text" name="x1" value=<%=request.getAttribute("eno")%>>
			</td>
		</tr>
		<tr>
			<td >
			
<h4>Password  </h4>

			</td>
			<td >
			 <input type="text" name="x2" value=<%=request.getAttribute("ename")%>>
			</td>
		</tr>
		<tr>
			

<td>
<h4>First Name </h4>
			</td>
			<td >
			 <input type="text" name="x3" value=<%=request.getAttribute("fn")%>>
			</td>
		</tr>
                  	<tr>
			<td>
                      
		
<h4>Middle Name </h4>

		</td><td>
			 <input type="text" name="x4" value=<%=request.getAttribute("mn")%>>
			</td>
		</tr>

	
		<tr>
			<td>
			
<h4>Last Name</h4>

		</td>
<td>			 <input type="text" name="x5" value=<%=request.getAttribute("ln")%>>
			</td>
		</tr>

	
		<tr>
			<td>
			
<h4>Date Of Birth</h4>

		</td>
			<td>		
                         <input type="text" name="x6" value=<%=request.getAttribute("dt")%>>
			</td>
		</tr>
		<tr>
			<td>

<h4>Age    </h4>
</td>
			<td> <input type="text" name="x7" value=<%=request.getAttribute("age1")%>>
			</td>
		</tr>
<tr>
<td width="40%">	
<h4>Sex</h4>
</td>
		
			<td> <input type="text" name="x8"  value=<%=request.getAttribute("s")%>>
			</td>
		</tr>
<tr>
			<td>	
<h4>Marital Status </h4>

	
	</td>
			

	
			<td> <input type="text" name="x9" value=<%=request.getAttribute("m")%>>
			</td>
		</tr>
<tr>
<td>	
<h4> Residential Address   </h4>

	</td>
		
			<td> <input type="text" name="x10" value=<%=request.getAttribute("ad")%>> 
			</td>
		</tr>


		<tr>
			<td>	
<h4>Phone No</h4>
		</td>
			<td> <input type="text" name="x11" value=<%=request.getAttribute("pn")%>>
			</td>
		</tr>


		<tr>
			<td>	
	
<h4>Mobile Number</h4>

			</td>
			<td >
			 <input type="text" name="x12" value=<%=request.getAttribute("mb")%>>
			</td>
		</tr>
<tr>
<td>
<h4>Fax</h4>

			</td>
			<td >
			 <input type="text" name="x13" value=<%=request.getAttribute("fx")%>>
			</td>
		</tr>	
<tr>	
<td>
<h4>Email</h4>

			</td>
			<td >
			 <input type="text" name="x14" value=<%=request.getAttribute("em")%>>
			</td>
		</tr>		

</table>




       
<center>
<input type="submit"  value=Delete>
</center>

</form>

</body>
</html>