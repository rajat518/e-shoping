<html>
<body>

<blockquote>
  <div>
  </div>
  </FORM>
  <form action="complaint.html" method="post">
  
    <center><input class="button" type="submit" value="Post a Message" name="postmessage"></center>
    <div>
    </div>
  </form>
  <% Integer x=(Integer)request.getAttribute("cnt");
   for(int i=0;i<x;i++)
  {
	  %>
  <a name="<%=i%>"></a>
  <div class="thread">
    <div class="first_message_div">
      <div class="first_message_header">
        <span class="first_message_span"><a class="article_link" href="complaint.html"><%=request.getAttribute("compid["+i+"]")%></a>
        —<span class="author_cell"><%=request.getAttribute("userid["+i+"]")%></span></span>
      </div>
    </div>
	<br>
  <hr>
  <br>
  <%
  }
	  %>
 
    
  <!-- " -->
  </SCRIPT>
  </NOSCRIPT>
  
</blockquote>
</FORM>

</body>

</html>
