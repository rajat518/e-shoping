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
 * This page initially displays a list of pending orders. 
 * The Shipping service staff can select any order to be shipped. They can also 
 * modify the quantity of any product to be shipped based on the product availability
 * in the inventory.
 *
 * When the request is submitted, the orders table is updated to modify the status,
 * and the order_items table is modified with the newly specified quantity.
--%>

<%-- Set the page properties and the Error page --%>
<%@ page contentType="text/html;charset=WINDOWS-1252"
         language="java"
         errorPage="ErrorHandler.jsp" %>
         
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Enumeration"%>
<%@ page import="java.util.StringTokenizer"%>
<%@ page import="oracle.otnsamples.wsclient.ProductsInfo"%>
<%@ page import="oracle.otnsamples.wsclient.OrdersInfo"%>

<jsp:usebean id="ordersBean" class="oracle.otnsamples.wsclient.StoresBean" scope="page" />

<HTML>
  <HEAD>
    <TITLE>Database as Web Service Client</TITLE>
    <SCRIPT type="text/javascript" language="JavaScript1.1">

     function validateForm() {
       var frm = document.orderslist;

       // Check if at least one order has been selected by user before submitting
       // the form
       var index = 0;
       for( i = 0; i < frm.elements.length; i++ ) {
         if(frm.elements[i].name == "orderInfo" && frm.elements[i].checked == "1" )  {                   
           index++;
         }
       }
       if( index == 0 ) {
         alert("You must select atleast one order before proceeding");
         return false;
       } 

      // Check if the quantity entered is a valid number
       var quantity;       
       for( i = 0; i < frm.length; i++ ) {
         if(frm.elements[i].name != "orderInfo" &&
            frm.elements[i].name != "" ) {            
            if( frm.elements[i].value != "" ) {
              quantity = frm.elements[i].value;
               var flag = isNaN(quantity);
               if (flag == true){
                 alert("Please enter a valid integer for quantity");
                 frm.elements[i].focus();
                 return false;

               } else if ( quantity <= 0 ) {
                 alert("Please enter a value greater than 0 for quantity");
                 frm.elements[i].focus();
                 return false;
               }               
            }
         }  
       }
       
       return true;
     }
    </SCRIPT>
  </HEAD>

  <BODY bgcolor="#FFFFFF" text="#000000">
    <FONT face="Times New Roman, Times, serif" size="+3" color=
    "990000"><I><B>OTN Online Computer Stores - Shipment
    Module</B></I></FONT>
    <HR>
    <FORM name="orderslist" method="post" action="ShipOrders.jsp"
    onsubmit="javascript:return validateForm();">

<%
     // Get the list of orders selected by the shipping service staff for shipping
     String ordersInfo[] = request.getParameterValues("orderInfo");

     // If the shipping service staff has submitted the form, get the list of orders to be shipped     
     if( ordersInfo != null ) {   %>
     
      <H4>
        <FONT face="Verdana, Arial, Helvetica, sans-serif" size=
        "1">Your request has been processed. Here is the current
        status of the orders</FONT>
      </H4>
      <FONT face="Verdana, Arial, Helvetica, sans-serif" size=
      "1"><BR>
      </FONT>
      <TABLE width="80%" border="1" cellspacing="0" cellpadding=
      "1">
        <TR>
          <TH>
            <FONT face="Verdana, Arial, Helvetica, sans-serif"
            size="2">Customer ID</FONT>
          </TH>
          <TH>
            <FONT face="Verdana, Arial, Helvetica, sans-serif"
            size="2">Order ID</FONT>
          </TH>
          <TH>
            <FONT face="Verdana, Arial, Helvetica, sans-serif"
            size="2">Order Status</FONT>
          </TH>
          <TH>
            <FONT face="Verdana, Arial, Helvetica, sans-serif"
            size="2">Product Name</FONT>
          </TH>
          <TH>
            <FONT face="Verdana, Arial, Helvetica, sans-serif"
            size="2">Quantity Shipped</FONT>
          </TH>
        </TR>
          
<%     ArrayList productIDList  = new ArrayList();
       ArrayList quantity       = new ArrayList();
       ArrayList orderInfoList  = new ArrayList();;
       StringTokenizer st;       
       String orderID = null;
       String productID = null;

       // Loop through each order selected by shipping service staff
       for( int i = 0; i < ordersInfo.length; i++ ) {

         // Each entry in the ordersInfo list is of the form - 
        // <order_id#prod_id1#prod_id2#prod_id3...> So break the string and get
        // the order_id and the list of all product ids in the order
         st = new StringTokenizer( ordersInfo[i], "#" );
         orderID = st.nextToken();
         while( st.hasMoreTokens() ) {
           productID = st.nextToken();
           productIDList.add( new Integer( productID) );
           quantity.add(new Integer(request.getParameter(productID)));
         }  
         orderInfoList.add(new OrdersInfo(Integer.parseInt(orderID),
                           null,
                           null,
                           null,
                           (Integer[])productIDList.toArray(new Integer[productIDList.size()]),
                           null,
                           (Integer[])quantity.toArray(new Integer[quantity.size()]) ,
                           null,
                           null));    
         
       }
       
       // Invoke the java bean to modify the order status  
       OrdersInfo[] ordersList = ordersBean.modifyOrders(orderInfoList);  

       // For each of the order chosen for shipping by the shipping service staff,
       // get the new status and product quantity
       for( int i = 0; i < ordersList.length ; i++ ) {
          int orderItemsCount = ordersList[i].getProductName().length;       
%>
        <TR>
          <TD rowspan="<%=orderItemsCount %>" width="25%" align=
          "center">
            <FONT face="Verdana, Arial, Helvetica, sans-serif"
            size="2"><%=ordersList[i].getUserID()%></FONT>
          </TD>
          <TD rowspan="<%=orderItemsCount %>" width="15%" align=
          "center">
            <FONT face="Verdana, Arial, Helvetica, sans-serif"
            size="2"><%=ordersList[i].getID()%></FONT>
          </TD>
          <TD rowspan="<%=orderItemsCount %>" width="20%" align=
          "center">
            <FONT face="Verdana, Arial, Helvetica, sans-serif"
            size="2"><%=ordersList[i].getStatus()%></FONT>
          </TD>
<%         
          
           for( int j = 0; j < orderItemsCount; j++ ) { 
%>
          <TD width="40%" align="center">
            <FONT face="Verdana, Arial, Helvetica, sans-serif"
            size="2"><%=
            ordersList[i].getProductName()[j]%></FONT>
          </TD>
          <TD width="10%" align="center">
            <FONT face="Verdana, Arial, Helvetica, sans-serif"
            size="2"><%= ordersList[i].getQuantity()[j]%></FONT> 
          </TD>
        </TR>
<%    if( (j+1) != orderItemsCount) {%>
        <TR>
<%    }  
    } %>
        </TR>            
<%     }       // end of for %>
      </TABLE>
      <TABLE width="100%" align="center">
        <TR>
          <TD align="center" colspan="2">
            &nbsp;
          </TD>
        </TR>
        <TR>
          <TD align="center" width="53%">
            <FONT face="Verdana, Arial, Helvetica, sans-serif"
            size="2"><A href="ShipOrders.jsp">Back to Orders
            Page</A></FONT>
          </TD>
        </TR>
        <TR>
          <TD align="center" colspan="2">
            &nbsp;
          </TD>
        </TR>
      </TABLE>
       
<%   } else {  // The form is not yet submitted. Display the list of all pending orders

        OrdersInfo[] ordersList = ordersBean.listOrders();
        if ( ordersList.length > 0 ) {
%>
      <H3>
        <FONT face="Verdana, Arial, Helvetica, sans-serif" size=
        "1">Select the orders you wish to ship</FONT>
      </H3>
      <TABLE width="80%" border="1" cellspacing="0" cellpadding=
      "1">
        <TR>
          <TH>
            <FONT face="Verdana, Arial, Helvetica, sans-serif"
            size="2">Select</FONT>
          </TH>
          <TH>
            <FONT face="Verdana, Arial, Helvetica, sans-serif"
            size="2">Order Id</FONT>
          </TH>
          <TH>
            <FONT face="Verdana, Arial, Helvetica, sans-serif"
            size="2">Customer Id</FONT>
          </TH>
          <TH>
            <FONT face="Verdana, Arial, Helvetica, sans-serif"
            size="2">Order Date</FONT>
          </TH>
          <TH>
            <FONT face="Verdana, Arial, Helvetica, sans-serif"
            size="2">Product</FONT>
          </TH>
          <TH>
            <FONT face="Verdana, Arial, Helvetica, sans-serif"
            size="2">Price($)</FONT>
          </TH>
          <TH>
            <FONT face="Verdana, Arial, Helvetica, sans-serif"
            size="2">Qty for Shipment</FONT>
          </TH>
          <TH>
            <FONT face="Verdana, Arial, Helvetica, sans-serif"
            size="2">Qty Available</FONT>
          </TH>
        </TR>
        
<%      for( int i = 0; i < ordersList.length; i++ ) { 

          // Concatenate the order_id with the list of products_id in the order
          // This will form the value of the checkbox
          int orderItemsCount = ordersList[i].getProductID().length;
          String idList = "" + ordersList[i].getID();
          for( int j = 0; j < orderItemsCount; j++ )
            idList += ( "#" + ordersList[i].getProductID()[j] );
%>
        <TR>
          <TD rowspan="<%=orderItemsCount %>" align="center" width=
          "10%">
            <INPUT type="checkbox" name="orderInfo" value=
            "<%= idList %>">
          </TD>
          <TD rowspan="<%=orderItemsCount %>" width="10%" align=
          "center">
            <FONT face="Verdana, Arial, Helvetica, sans-serif"
            size="2"><%= ordersList[i].getID() %></FONT>
          </TD>
          <TD rowspan="<%=orderItemsCount %>" width="25%" align=
          "center">
            <FONT face="Verdana, Arial, Helvetica, sans-serif"
            size="2"><%= ordersList[i].getUserID() %></FONT>
          </TD>
          <TD rowspan="<%=orderItemsCount %>" width="15%" align=
          "center">
            <FONT face="Verdana, Arial, Helvetica, sans-serif"
            size="2"><%= ordersList[i].getOrderDate() %></FONT>
          </TD>

<%        
       // For each order, display all the product information
       for( int j = 0; j < orderItemsCount; j++ ) { 
%>
          <TD width="40%" align="center">
            <FONT face="Verdana, Arial, Helvetica, sans-serif"
            size="2"><%=
            ordersList[i].getProductName()[j]%></FONT>
          </TD>
          <TD width="20%" align="center">
            <FONT face="Verdana, Arial, Helvetica, sans-serif"
            size="2"><%= ordersList[i].getPrice()[j]%></FONT>
          </TD>
          <TD width="20%" align="center">
            <INPUT type="text" name=
            "<%= ordersList[i].getProductID()[j] %>" value=
            "<%=ordersList[i].getQuantity()[j]%>" size="3"
            maxlength="3">
          </TD>
          <TD width="10%" align="center">
            <FONT face="Verdana, Arial, Helvetica, sans-serif"
            size="2"><%=
            ordersList[i].getQuantityOnHand()[j]%></FONT>
          </TD>
        </TR>
<%    if( (j+1) != orderItemsCount) {%>
        <TR>
<%    }  
    } %>
        </TR>
 <%} %>        
   
      </TABLE>

      <TABLE width="100%" align="center">
        <TR>
          <TD align="center" colspan="2">
            &nbsp;
          </TD>
        </TR>
        <TR>
          <TD align="right" width="53%">
            <INPUT type="submit" value="Ship Order"> 
          </TD>
          <TD width="47%">
            <INPUT type="reset" value="Reset"> 
          </TD>
        </TR>
        <TR>
          <TD align="center" colspan="2">
            &nbsp;
          </TD>
        </TR>
      </TABLE>
      
<% } else { // ordersList.length == 0, i.e there are no pending orders%>
     <P><B>
        <FONT face="Verdana, Arial, Helvetica, sans-serif" size="2">
          There are no pending orders.</FONT>
        </B>  
     </P>     
<% }
 }%>
    </FORM>
  </BODY>
</HTML>