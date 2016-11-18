<html>
<body>

<blockquote>
  <div>
<table border="0" cellspacing="0" cellpadding="0">
<tr>
<td><img src="s.gif" width=300 height=135></td>
<td background="com.GIF" width=700></td>
</tr>
<center>
<tr><td></td>
<td align="right"><a href="home.html"><img src="homepage.gif"  border="0" ></a></td>

</tr>
</table>
 <br>
 <br>
 <br>
  <%
  Integer x=(Integer)request.getAttribute("cnt");
  
   for(int i=0;i<x;i++)
  {
 java.lang.String uid=(java.lang.String)request.getAttribute("user_id["+i+"]");
  java.lang.String compid=(java.lang.String)request.getAttribute("comp_id["+i+"]");
	 
	  %>
  <a name="<%=i%>"></a>
  <div class="thread">
    <div class="first_message_div">
      <div class="first_message_header">
        <span class="first_message_span"><a class="article_link" href="complaint.html"><%=request.getAttribute("comp_id["+i+"]")%></a>
        <span class="proname_cell"><%=request.getAttribute("pro_name["+i+"]")%></span>
		<span class="cust_cell"><%=uid%></span>
		
		<span class="msg_cell"><%=request.getAttribute("mess["+i+"]")%></span>
		<%Integer status=(Integer)request.getAttribute("status["+i+"]");%>
		<span class="date_cell"><%=request.getAttribute("date["+i+"]")%></span>	
		<%	if(status==0){ %>
<form action="./postreply?uid=<%=uid%>&compid=<%=compid%>" method=post>
		<span class="reply_cell"><textarea name="response"></textarea></span>
		
		<span class="rep_cell"><input type="submit" name="submit" value="Reply"></span>
		</form>
		<%}%>
		</span>
      </div>
	  <hr>
    </div>
	
		
  <%
  }
	  %>
	    </div>

  	 <br>
  
  <br>
  
  <!--<form action="" method="post">
    <input type="hidden" value="238100" name="disc"><input type="hidden" value="1" name="pagemark">-->
   

  
  <!-- " -->
  </SCRIPT>
  </NOSCRIPT>
  
</blockquote>


</body>

</html>
