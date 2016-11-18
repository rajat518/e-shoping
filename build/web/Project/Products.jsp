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
 * This page initially displays a list of products available to the user. 
 * Users can purchase any products of their choice after providing their customer
 * id.  When the users submit their request, the page first authorizes the customer
 * using the Credit Agency Web Service, then makes an entry to the
 * orders table and displays a success message.  The page also displays a "View
 * Order Status" link, which leads to the ViewOrders page.
--%>

<%-- Set the page properties and the Error page --%>
<%@ page contentType="text/html;charset=WINDOWS-1252"
         language="java"
         errorPage="ErrorHandler.jsp" %>
         
<%@ page import="java.util.Enumeration"%>
<%@ page import="java.util.StringTokenizer"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="oracle.otnsamples.wsclient.CreditAgencyServiceStub"%>
<%@ page import="oracle.otnsamples.wsclient.ProductsInfo"%>

<jsp:usebean id="productsBean" class="oracle.otnsamples.wsclient.StoresBean" scope="page" />

<HTML>
  <HEAD>
    <TITLE>Database as Web Service Client</TITLE>
    <SCRIPT type="text/javascript" language="JavaScript1.1">

     function validateForm() {
       var frm = document.productslist;
       // Check if the user has enterd a customer id
       if ( document.productslist.custID.value == "" ) {
         alert("Please enter your Customer ID");
         document.productslist.custID.focus();
         return false;
       }

       // Check if at least one product has been selected by user before submitting
       // the form
       var index = 0;
       for( i = 0; i < frm.elements.length; i++ ) {
         if(frm.elements[i].name == "prodInfo" && frm.elements[i].checked == "1" )  {                   
           index++;
         }
       }
       if( index == 0 ) {
         alert("You must select atleast one product before proceeding");
         return false;
       }
       
      // Check if the quantity entered is a valid number
       var quantity;       
       for( i = 0; i < frm.length; i++ ) {
         if(frm.elements[i].name != "custID" && frm.elements[i].name != "prodInfo" &&
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

  <BODY bgcolor="white" text="black">
    <TABLE width="100%" border="0">
      <TR>
        <TD>
          <FONT face="Times New Roman, Times, serif" size="+3"
          color="990000"><I><B>OTN Online Computer
          Stores</B></I></FONT>
        </TD>
        <TD align="right">
          <TABLE width="100%" border="0">
            <TR>
              <TD align="center">
                <A href="ViewOrders.jsp"><IMG src=
                "images/shipment.gif" border="0"></A>
              </TD>
            </TR>
          </TABLE>
        </TD>
      </TR>
      <TR>
        <TD colspan="2" align="right">
          <A href="ViewOrders.jsp"><FONT face=
          "Verdana, Arial, Helvetica, sans-serif" size="1">View
          Order Status</FONT></A>
        </TD>
      </TR>
    </TABLE>
    <HR>
<%
   String customerID = request.getParameter("custID");      
   
   // Check if the customer id is valid using the Credit Agency Web Service
   if( customerID != null ) {
     CreditAgencyServiceStub stub = new CreditAgencyServiceStub();
     String authFlag = stub.authorizeCustomer(customerID);
%>
    <H3>
      <FONT face="Verdana, Arial, Helvetica, sans-serif" size=
      "2">Thank you for visiting Computer Hardware Stores.</FONT>
    </H3>

<%     if( authFlag.equals("VALID_CUSTOMER") ) {

         // The customer is validated successfully. Add order information to
         // the database
         int orderID = 0;  

         // Get the list of products chosen by the user
         // Each entry in the list is of the form <product_id#product_price>
         // So break the string and get the values of the product id and price
         String products[] = request.getParameterValues("prodInfo");
         if( products != null ) {
           StringTokenizer st    = null;
           ArrayList productInfo = new ArrayList();
           String id       = null;
           String price    = null;
           String quantity = null;
           
           /* For each product selected by the user, add its information to a 
              ProductsInfo value object.  An array of such value objects will be
              formed   */
            for( int i = 0; i < products.length; i++ ) {
              st = new StringTokenizer( products[i], "#" );
              id = st.nextToken();
              price = st.nextToken();
              quantity = request.getParameter((String)id);
              productInfo.add(new ProductsInfo(Integer.parseInt(id), null, null, Float.parseFloat(price),Integer.parseInt(quantity.equals("")?"1":quantity)));
            }       
     
            // Invoke the java bean to add the order information to the database
            orderID = productsBean.addOrder(productInfo, customerID);
         } %>
        <P>
         <FONT face="Verdana, Arial, Helvetica, sans-serif" size=
          "2">Your order has been placed successfully with id <B><%=orderID%></B>. 
          Please use this number to check your order status.</FONT>
       </P>
       
<%      } else if( authFlag.equals("INVALID_CUSTOMER") ) { // Invalid Customer %>

       <P>
         <FONT face="Verdana, Arial, Helvetica, sans-serif" size=
           "2">You have entered an invalid customer id. Please try again.
         </FONT>
      </P>
           
<%      } else {  // Unable to access web service %>

      <P>
        <FONT face="Verdana, Arial, Helvetica, sans-serif" size=
          "2">Sorry...We are unable to authorize the information given
            by you. Please try later.</FONT>
      </P>
           
<%      } %>

    <TABLE width="100%" align="center">
      <TR>
        <TD align="center" colspan="2">
          &nbsp;
        </TD>
      </TR>
      <TR>
        <TD align="center" width="53%">
          <A href="Products.jsp"><FONT face=
          "Verdana, Arial, Helvetica, sans-serif" size="2">Back to
          Stores</FONT></A>
        </TD>
      </TR>
      <TR>
        <TD align="center" colspan="2">
          &nbsp;
        </TD>
      </TR>
    </TABLE>
      
<%   }  else {  // The user is yet to submit the form so display the list of products
%>
    <FORM name="productslist" method="post" action="Products.jsp"
    onsubmit="javascript:return validateForm();">
      <BR>

      <TABLE width="80%" border="0" cellspacing="0" cellpadding=
      "1">
        <TR>
          <TD align="right" width="50%">
            <FONT face="Verdana, Arial, Helvetica, sans-serif"
            size="2">Customer ID&nbsp;&nbsp;</FONT>
          </TD>
          <TD align="left" width="50%">
            <INPUT type="text" name="custID" size="25" maxlength=
            "20">
          </TD>
        </TR>
      </TABLE>
      <BR>

      <H3>
        Select the products you wish to purchase
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
            size="2">Product Name</FONT>
          </TH>
          <TH>
            <FONT face="Verdana, Arial, Helvetica, sans-serif"
            size="2">Product Price($)</FONT>
          </TH>
          <TH>
            <FONT face="Verdana, Arial, Helvetica, sans-serif"
            size="2">Quantity</FONT>
          </TH>
        </TR>

<%
        /* Invoke the java bean to get the list of products from the database
           and display the same */
        ProductsInfo[] productsList = productsBean.listProducts();
        for( int i = 0; i < productsList.length - 1; i++ ) {
          /* Concatenate the product id and price and add this as value of the
             checkbox */
          String productInfo = productsList[i].getID() + "#" + productsList[i].getPrice() ;
%>
        <TR>
          <TD align="center" width="10%">
            <INPUT type="checkbox" name="prodInfo" value="<%= productInfo %>">
          </TD>
          <TD width="50%" align="center">
            <FONT face="Verdana, Arial, Helvetica, sans-serif"
              size="2"><%=productsList[i].getName()%>
            </FONT>
          </TD>
          <TD width="10%" align="right">
            <FONT face="Verdana, Arial, Helvetica, sans-serif"
              size="2"><%=productsList[i].getPrice() %>
            </FONT>
          </TD>
          <TD width="10%" align="center">
            <INPUT type="text" name="<%= productsList[i].getID() %>" size="3" 
               maxlength="3">
          </TD>
        </TR>
<%    }   // end of for loop %>        
      </TABLE>
      <TABLE width="100%" align="center">
        <TR>
          <TD align="center" colspan="2">
            &nbsp;
          </TD>
        </TR>
        <TR>
          <TD align="right" width="53%">
            <INPUT type="submit" value="Place Order">
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
<% }   // end of if-else %>
    </FORM>
  </BODY>
</HTML>