<html>
<body>

<blockquote>
  <div>
  </div>

 <form action="./replycmp" method=post>
  <%
  Integer x=(Integer)request.getAttribute("cnt");
    for(int i=0;i<x;i++)
  {
 //java.lang.String uid=(java.lang.String)request.getAttribute("user_id["+i+"]");
	 
	  %>
  <a name="<%=i%>"></a>
  <div class="thread">
    <div class="first_message_div">
      <div class="first_message_header">
        <span class="first_message_span"><a class="article_link" href="complaint.html">
		
		<span class="sub_cell"><%=request.getAttribute("reply["+i+"]")%></span>
		<span class="date_cell"><%=request.getAttribute("date["+i+"]")%></span>	
		

		
		</form>
		<%}%>
		</span>
      </div>
	  <hr>
    </div>
	
		
 
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
