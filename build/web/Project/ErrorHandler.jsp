<%--
 * @author  Sujatha
 * @version 1.0
 *
 * Development Environment        :  Oracle9i JDeveloper
 * Name of the Application        :  ErrorHandler.jsp
 * Creation/Modification History  :
 *
 *     Sujatha        03-Jul-2002      Created
 *
 * Overview of Application        :
 * This page displays any error message encountered when running the sample application
 *
--%>
<%@ page language="java" isErrorPage="true"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="java.io.StringWriter"%>

<HTML>
  <HEAD>
    <TITLE>Database as Web Service Client - Error</TITLE>
  </HEAD>
<BODY>
<FONT face="Verdana, Arial, Helvetica, sans-serif" size="2">The demo has generated 
an error. Please check that you have followed the setup instructions in the Readme. 
If you have and are still encountering an error, please post a message on the 
OTN Discussion Forum currently located <A href="http://otn.oracle.com/forums/sample_code.html">here</A>. 
Thank you. <BR>
</FONT><BR>
<FONT face="Verdana, Arial, Helvetica, sans-serif" size="1" color="#FF0000">Error: <%= exception.getMessage() %> 
<%
  StringWriter sw = new StringWriter();
  PrintWriter pw = new PrintWriter(sw);
  exception.printStackTrace(pw);
  out.println(sw);
%>
</FONT>
</BODY>
</HTML>