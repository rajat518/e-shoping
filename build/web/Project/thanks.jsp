<html><body>
<center><br><br><br>
YOUR ORDER ID IS:: <%=request.getAttribute("id")%>
<%HttpSession ses = request.getSession(false); 
ses.invalidate();%><br><br>
PLEASE RESERVE THIS ID FOR FUTURE PURPOSE<br><br><h1><b>THANK U FOR SHOPPING</b></h1><br>
<a href='home.jsp'><b>VISIT AGAIN</b></a>
</center></body></html>