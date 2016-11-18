<%--
 * @author  Sujatha
 * @version 1.0
 *
 * Development Environment        :  Oracle9i JDeveloper
 * Name of the Application        :  Products.jsp
 * Creation/Modification History  :
 *
 *     Sujatha        26-Jul-2002      Created
 *
 * Overview of Application        :
 * This page is used by customers to view the status of their orders.
 * The customer needs to enter the order id and then the java bean is used to 
 * look up the status of the order.  The same is displayed. If the user enters
 * an invalid order id an appropriate message is displayed.
--%>

<%-- Set the page properties and the Error page --%>
<%@ page contentType="text/html;charset=WINDOWS-1252"
         language="java"
         errorPage="ErrorHandler.jsp" %>
         
<%@ page import="java.util.Hashtable"%>
<%@ page import="java.util.Enumeration"%>
<%@ page import="java.util.StringTokenizer"%>

<jsp:usebean id="ordersBean" class="oracle.otnsamples.wsclient.StoresBean" scope="page" />

<HTML>
  <HEAD>
    <TITLE>Database as Web Service Client</TITLE>
    <SCRIPT type="text/javascript" language="JavaScript1.1">

     function validateForm() {
       idValue = document.orderstatus.orderID.value;

       // Check if the user has entered a value for order id
       if(idValue == "" ) {       
         alert("You must enter the order ID before proceeding");
         document.orderstatus.orderID.focus();         
         return false;
       } 
       var flag = isNaN(idValue);
       
       // Check if the user has entered a valid number as order id
       if( flag == true || idValue <= 0 ) {
         alert("You must enter a valid order ID before proceeding");
         document.orderstatus.orderID.focus();         
         return false;       
       }
       return true;
     }
    </SCRIPT>
  </HEAD>

  <BODY bgcolor="#FFFFFF" text="#000000">
    <FONT face="Times New Roman, Times, serif" size="+3" color=
    "990000"><I><B>OTN Online Computer Store</B></I></FONT>
    <HR>
    <FORM name="orderstatus" method="post" action="ViewOrders.jsp"
    onsubmit="javascript:return validateForm();">
      <BR>
      
<%
   // Invoke the java bean with the order id provided by the user to get the
   // status of the order.
   String orderID  = request.getParameter("orderID");      
   if( orderID != null ) {
     Hashtable orderStatus = ordersBean.viewOrderStatus(Integer.parseInt(orderID));
%>
      <H2>
        Here is the status of your order
      </H2>
      <TABLE border="0" cellspacing="0" cellpadding="2">
        <TR>
          <TD align="right">
            <FONT face="Verdana, Arial, Helvetica, sans-serif"
            size="2"><B>Order ID</B></FONT>
          </TD>
          <TD align="center"> -
          </TD>
          <TD>
            <FONT face="Verdana, Arial, Helvetica, sans-serif"
            size="2"><%=orderID%></FONT>
          </TD>
        </TR>
        <TR>
          <TD align="right">
            <FONT face="Verdana, Arial, Helvetica, sans-serif"
            size="2"><B>Order Status</B></FONT>
          </TD>
          <TD align="center"> -
          </TD>
          <TD>
            <FONT face="Verdana, Arial, Helvetica, sans-serif"
            size="2"><%=orderStatus.get(new Integer(orderID))%>
            </FONT>
          </TD>
        </TR>
      </TABLE>
      <BR>

      <TABLE width="80%" border="0" cellspacing="0" cellpadding=
      "2">
        <TR align="center">
          <TD align="center" colspan="2">
            <FONT face="Verdana, Arial, Helvetica, sans-serif"
            size="2"><A href="ViewOrders.jsp">View Order
            Status</A></FONT>
          </TD>
        </TR>
      </TABLE>

<% } else { // User has not yet submitted the form %>    
      <TABLE width="80%" border="0" cellspacing="0" cellpadding=
      "2">
        <TR>
          <TD align="right" width="53%">
            Enter your Order ID
          </TD>
          <TD align="left" width="47%">
            <INPUT type="text" name="orderID" size="25" maxlength=
            "20">
          </TD>
        </TR>
      </TABLE>
      <TABLE width="80%" align="center" border="0">
        <TR>
          <TD align="center" colspan="2">
            &nbsp;
          </TD>
        </TR>
        <TR>
          <TD align="right" width="57%">
            <INPUT type="submit" value="Get Order Details">
          </TD>
          <TD width="43%">
            <INPUT type="reset" value="Reset">
          </TD>
        </TR>
        <TR>
          <TD align="center" colspan="2">
            &nbsp;
          </TD>
        </TR>
        <TR>
          <TD align="center" colspan="2">
            <FONT face="Verdana, Arial, Helvetica, sans-serif"
            size="2"><A href="Products.jsp">View Products
            Catalog</A></FONT>
          </TD>
        </TR>
      </TABLE>
<% } %>      
    </FORM>
  </BODY>
</HTML>