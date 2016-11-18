<%--
  *  @author   Anupama Majety
  *  @version  1.0
  *
  *  Development Environment        : Oracle9i JDeveloper
  *  Name of the Application        : ShoppingCart.jsp
  *  Creation/Modification History  :
  *
  *     Anupama Majety     01-Jun-2002     Created
  *     Srikanth Mohan     24-Feb-2003     Certified on Linux
  *
  *  Overview of Application        :
  *  This JSP displays two things.
  *       1. Products sold by the Retail Shop
  *       2. Customers Shopping Cart
  *  The customer can see all the available products that he can purchase in
  *  this page. He can add a product to his shopping cart, update the quantity
  *  in the Shopping Cart or delete a product from the shopping cart.
--%>

<%@ page language="java" %>
<%@ page errorPage="Exception.jsp" %>
<%@ page import="oracle.otnsamples.oracle9ijdbc.transactiontoggling.Product"%>
<%@ page import="oracle.otnsamples.oracle9ijdbc.transactiontoggling.ShoppingCartProduct"%>
<%@ page import="java.util.Hashtable" %>
<%@ page import="java.util.Enumeration" %>

<HTML>
<HEAD>
<TITLE>Transaction Toggling Sample - Shopping Cart</TITLE>
<META content="text/html; charset=iso-8859-1" http-equiv=Content-Type>
<META content="MSHTML 5.00.2314.1000" name=GENERATOR>
<link rel="stylesheet" href="stylesheets/styles.css" type="text/css">
<SCRIPT language="JavaScript">

  /** 
   * This function is called when the customer wants to add a product to his
   * shopping cart. It submits the form appending the
   * appropriate event.
   */
  function addToSC(){
    if (checkboxesToSell()){
      document.ShoppingForm.action = "ShoppingCart.jsp?Event=AddPrds";
      document.ShoppingForm.submit();
    }else{
      alert("Select a Product To Add");
    }
  }

 /**
  * This function trims the input String. It removes the leading and trailing
  * spaces from the String.
  */
  function trim(strText) {
    // this will get rid of leading spaces
    while (strText.substring(0,1) == ' ')
      strText = strText.substring(1, strText.length);
    // this will get rid of trailing spaces
    while (strText.substring(strText.length-1,strText.length) == ' ')
      strText = strText.substring(0, strText.length-1);
    return strText;
  }

  /**
   *  This function validates the quantity ordered.
   */
  function validate(quantity){
    if (quantity == ""){
      alert("Quantity cannot be null. Please enter some valid value.");
      return false;
    }else if(quantity <= 0 ){
      alert("Quantity cannot be zero or a negative value. Please enter a valid quantity.");
      return false;
    }else if (isNaN(quantity)){
      alert("Quantity has to be an integer. Please enter a valid quantity.");
      return false;
    }
    var index = quantity.indexOf(".");
    if(index != -1){
      alert("Quantity cannot have a decimal");
      return false;
    }

    return true;
  }

  /**
   *  This function is called when the customer wants to update the quantity of
   * a product in his shopping cart. It submits the form to the ControllerServlet
   * appending the appropriate event. Before updation, it also checks if any product
   * has been added in the shopping cart for updation.
   */
  function updateSC(){
    var noErrorFlag = true;
    if (document.ShoppingForm.EmptyShoppingCart.value == 1){
      if (document.ShoppingForm.NoOfProducts.length >1){
        for (i=0; i<document.ShoppingForm.NoOfProducts.length; i++){
          noErrorFlag = validate(trim(document.ShoppingForm.NoOfProducts[i].value));         
		  if(!noErrorFlag)
			  break;
        } // end of for
      }else{
        noErrorFlag = validate(trim(document.ShoppingForm.NoOfProducts.value));       
      }

 	  if(noErrorFlag){
        document.ShoppingForm.action = "ShoppingCart.jsp?Event=UpdatePrds";
        document.ShoppingForm.submit();
	  }
    }else{
      alert("Add a Product to the Shopping Cart");
      return false;
    }
  }

  /**
   * This function is called before adding a product to the shopping cart.
   * It checks if a product has been checked for adding to the shopping cart.
   */
  function checkboxesToSell(){
    var flag = false;
    if (document.ShoppingForm.ProductsToSell.length > 1){
      for (i=0; i<document.ShoppingForm.ProductsToSell.length; i++){
        if(document.ShoppingForm.ProductsToSell[i].checked){ // Check for true
          flag = true;
        }
      }
    }else{
      if(document.ShoppingForm.ProductsToSell.checked){  // Check for true
        flag = true;
      }
    }
    return flag;
  }

  /**
   * This function is called before deleting a product from the shopping cart.
   * It checks if a product has been checked for deletion.
   */
  function checkboxesInCart(){
    var flag = false;
    if (document.ShoppingForm.ProductsInSC.length > 1){
      for (i=0; i<document.ShoppingForm.ProductsInSC.length; i++){
        if(document.ShoppingForm.ProductsInSC[i].checked){ // Check for true
          flag = true;
        }
      }
    }else{
      if(document.ShoppingForm.ProductsInSC.checked){ // Check for true
        flag = true;
      }
    }
    return flag;
  }

  /**
   *  This function is called when the customer wants to delete a product from his
   * shopping cart. It submits the form appending the
   * appropriate event. Before deletion, it also checks if any product has been
   * added in the shopping cart for deletion.
   */
  function deleteFromSC(){
    if (document.ShoppingForm.EmptyShoppingCart.value == 1){
      if (checkboxesInCart()){
        document.ShoppingForm.action = "ShoppingCart.jsp?Event=DeletePrds";
        document.ShoppingForm.submit();
      }else{
        alert("Select a Product To Delete");
      }
    }else{
      alert("Add a Product to the Shopping Cart");
    }
  }

  /**
   *  This function is called when the customer wants to order the products in his
   * shopping cart. It submits the form appending the
   * appropriate event. Before ordering, it also checks if any product has been
   * added in the shopping cart for ordering.
   */
  function orderSC(){
    if (document.ShoppingForm.EmptyShoppingCart.value == 1){
      document.ShoppingForm.action = "AddressDetails.jsp";
      document.ShoppingForm.submit();
    }else{
      alert("Add a Product to the Shopping Cart");
    }
  }

  /**
   *  This function is called when the customer wants to do some operation to all
   * the products displayed.
   */
  function checkAllProducts(){
    if (document.ShoppingForm.SelectAllProducts.checked){ // Check for true
      if (document.ShoppingForm.ProductsToSell.length > 1){
        for (i=0; i<document.ShoppingForm.ProductsToSell.length; i++){
          document.ShoppingForm.ProductsToSell[i].checked = true;
        }
      }else{
        document.ShoppingForm.ProductsToSell.checked = true;
      }
    }else{
      if (document.ShoppingForm.ProductsToSell.length > 1){
        for (i=0; i<document.ShoppingForm.ProductsToSell.length; i++){
          document.ShoppingForm.ProductsToSell[i].checked = false;
        }
      }else{
        document.ShoppingForm.ProductsToSell.checked = false;
      }
    }
  }

  /**
   * This function is called when the customer wants to do some operation to all
   * the products added to his shopping cart.
   */ 
  function checkAllCart(){
    if (document.ShoppingForm.SelectAllCart.checked){ // Check for true
      if (document.ShoppingForm.ProductsInSC.length >1){
        for (i=0; i<document.ShoppingForm.ProductsInSC.length; i++){
          document.ShoppingForm.ProductsInSC[i].checked = true;
        }
      }else{
        document.ShoppingForm.ProductsInSC.checked = true;
      }
    }else{
      if (document.ShoppingForm.ProductsInSC.length >1){
        for (i = 0; i< document.ShoppingForm.ProductsInSC.length; i++){
          document.ShoppingForm.ProductsInSC[i].checked = false;
        }
      }else{
        document.ShoppingForm.ProductsInSC.checked = false;
      }
    }
  }

</SCRIPT>
</HEAD>
<%-- Instantiate the bean on session scope with id as transTogglingBean. This id
        will be used to reference this bean --%>
<jsp:useBean id="transTogglingBean" 
               class="oracle.otnsamples.oracle9ijdbc.transactiontoggling.TransTogglingSampleBean" 
               scope="session" />
<%-- Instantiate the bean on session scope with id as shoppingCartBean. This id
        will be used to reference this bean --%>
<jsp:useBean id="shoppingCartBean" 
               class="oracle.otnsamples.oracle9ijdbc.transactiontoggling.ShoppingCartBean" 
               scope="session" />
<%
  Hashtable productsList = null;
  Hashtable shoppingCart = new Hashtable();

  // If no parameter is sent then display the products and
  // instantiate the shoppingcart data in session.
  if(request.getParameter("Event")==null){
	  // Get the available products list and store in session.
	  productsList = transTogglingBean.getProductDetails();
	  session.setAttribute("ProductsList", productsList);
	  
	  session.setAttribute("ShoppingCart", shoppingCart);
   } else {
        // Get the request type and product list
		String requestType = request.getParameter("Event");
		productsList = (Hashtable)session.getAttribute("ProductsList");
		
		// Depending on the action call the respective method in bean.
		if(requestType.equals("AddPrds")){
			shoppingCartBean.addToShoppingCart(request);
		}else if(requestType.equals("UpdatePrds")){
			shoppingCartBean.updateShoppingCart(request);					
		}else if (requestType.equals("DeletePrds")){
			shoppingCartBean.deleteFromCart(request);
		}
		
		// Retrieve the products in ShoppingCart from the session.
		shoppingCart = (Hashtable)session.getAttribute("ShoppingCart");

	}

%>
<BODY bgColor=#ffffff  text=#000000>
<form name="ShoppingForm" method="post">
  <!-- Main table -->
  <TABLE border=0 cellPadding=0 cellSpacing=0 height=58 width=970>
    <TR> 
      <TD class=mlink  height="58" valign="top"> 
        <!-- 3rd Table starts for top bar -->
        <table border=0 cellPadding=0 cellSpacing=0 height=89 width=100%>
          <tr> 
            <td valign="top" align="center" colspan=2> 
			<IMG name="transactiontoggling" src="images/trans2.gif" width="588" height="86" border="0"> 
            </td>
          </tr>
          <tr bgColor=#4f89a2 height=25> 
            <td vAlign=center noWrap>&nbsp;&nbsp;<SPAN class="greeting"> 
              Hi <%= session.getAttribute("UserName") %>! You are connected to Local database. </SPAN> 
            </td>
            <td align="right"> <B> <A href="Login.jsp?Event=SignOut"> <SPAN class="greeting">Sign 
              Out</SPAN></A></B> </td>
          </tr>
        </table>
        <!-- 3rd Table ends here for top bar -->
      </TD>
    </TR>
    <TR> 
      <TD> 
        <!--  7th table End of table which includes left bar, main working area -->
        <TABLE WIDTH ="100%">
          <TR>
            <TD>&nbsp;</TD>
          </TR>
          <!-- Heading of the page goes here -->
          <TR> 
            <TD class="mainHeading" align="center" height="22">Product List</TD>
          </TR>
          <TR> 
            <TD height="19">&nbsp;</TD>
          </TR>
          <TR> 
            <TD> 
              <!-- 5th table starts here working areaf5f1e9 -->
              <TABLE border=0 cellPadding=0 cellSpacing=0 width=100% >
                <TBODY> 
                <TR> 
                  <TD valign="top" width="8%" noWrap>&nbsp;</TD>
                  <TD vAlign=top width="84%" align="center"> 
                    <!-- 6th table starts here-->
                    <TABLE border=1 borderColor="#4f89a2" cellPadding=2 cellSpacing=0 width=100%>
                      <TBODY> 
                      <TR align="center"> 
                        <TD class="subHeading" width="4%"> 
                          <input type="checkbox" name="SelectAllProducts" onClick="checkAllProducts();">
                        </TD>
                        <TD class="subHeading" width="5%">S.No</TD>
                        <TD class="subHeading" width="8%">Product Id</TD>
                        <TD class="subHeading" width="16%">Product Name</TD>
                        <TD class="subHeading" width="35%">Product Description</TD>
                        <TD class="subHeading" width="21%">Category Desc</TD>
                        <TD class="subHeading" width="11%">Price (in Dollars)</TD>
                      </TR>
                      <%
                        // Get all the keys stored in the Hashtable in an Enumeration.
                        Enumeration enumProduct = productsList.keys();

                        // Initialize Serial Number
                        int serialNo = 0;

                        // Iterate through the Hashtable
                        while (enumProduct.hasMoreElements()){
                          Object key = enumProduct.nextElement();
                          serialNo++;

                          // Get the first Product from the Hashtable and display it
                          Product product = (Product)productsList.get(key);
                    %>
                      <TR align="center" height="25"> 
                        <TD class="data" width="4%"> 
                          <input type="checkbox" name="ProductsToSell" value="<%=key%>">
                        </TD>
                        <TD class="data" width="5%"><%=serialNo%></TD>
                        <TD class="data" width="8%"><%=product.productID%></TD>
                        <TD class="data" width="16%"><%=product.productName%></TD>
                        <TD class="data" width="35%"><%=product.productDesc%></TD>
                        <TD class="data" width="21%"><%=product.categoryDesc%></TD>
                        <TD class="data" width="11%"><%=product.productPrice%></TD>
                      </TR>
                      <%
                       }
                    %>
                      </TBODY> 
                    </TABLE>
                    <!-- 6th table ends here -->
                  </TD>
                  <TD width="8%">&nbsp;</TD>
                </TR>
                <TR>
                  <TD colspan=3>&nbsp;</TD>
                </TR>
                <TR> 
                  <TD colspan=3 align="center"> <a href="javascript:addToSC()">
				  <img src="images/Add.gif" border=0  width="67" height="33"></a> </TD>
                </TR>
                <TR height=10> 
                  <TD colspan=3>&nbsp;</TD>
                </TR>
                <TR> 
                  <TD colspan=3><img src=images/brownLine.gif width="100%" height="2"></TD>
                </TR>
                <TR height=10> 
                  <TD colspan=3>&nbsp;</TD>
                </TR>
                <TR>
                  <TD colspan=3 class="mainHeading" align="center">Shopping Cart</TD>
                </TR>
                <TR>
                  <TD colspan=3>&nbsp;</TD>
                </TR>
                <TR> 
                  <TD valign="top" width="8%" noWrap>&nbsp;</TD>
                  <TD vAlign=top width="84%"> 
                    <!-- 8th table starts here (shopping cart)-->
                    <TABLE border=1 bordercolor="#4f89a2" cellpadding=2 cellspacing=0 width="100%">
                      <TBODY> 
                      <%
                       // Checks if the Shopping Cart is empty.
                       if (!shoppingCart.isEmpty()){
                    %>
                      <!-- Sets the value if the shopping cart is not empty.
                         This value is used by JavaScript -->
                      <INPUT type="hidden" name="EmptyShoppingCart" value="1">
                      <TR align="center"> 
                        <TD class="subHeading" width="3%"> 
                          <INPUT type="checkbox" name="SelectAllCart" onClick="checkAllCart();">
                        </TD>
                        <TD class="subHeading" width="2%">S.No</TD>
                        <TD class="subHeading" width="8%">Product Id</TD>
                        <TD class="subHeading" width="25%">Product Name</TD>
                        <TD class="subHeading" width="17%">Category Desc</TD>
                        <TD class="subHeading" width="15%">Price</TD>
                        <TD class="subHeading" width="10%">Quantity</TD>
                        <TD class="subHeading" width="13%" align="center"> 
                          <P>Sub total (in Dollars)</P>
                        </TD>
                      </TR>
                      <%
                          // Get all the keys stored in the Hashtable in an Enumeration.
                          Enumeration enumCart = shoppingCart.keys();

                          // Initialize Serial Number
                          int serial = 0;

                          // Iterate through the Hashtable
                          while (enumCart.hasMoreElements()){
                            Object key = enumCart.nextElement();
                            serial++;

                            // Get the first Product from the Hashtable and display it
                            ShoppingCartProduct scProduct = (ShoppingCartProduct)shoppingCart.get(key);
                    %>
                      <TR align="center" height="25"> 
                        <TD class="data" width="3%"> 
                          <INPUT type="checkbox" name="ProductsInSC" value="<%=key%>">
                        </TD>
                        <TD class="data" width="2%"><%=serial%></TD>
                        <TD class="data" width="8%"><%=scProduct.productID%></TD>
                        <TD class="data" width="25%"><%=scProduct.productName%></TD>
                        <TD class="data" width="17%"><%=scProduct.categoryDesc%></TD>
                        <TD class="data" width="15%"><%=scProduct.productPrice%></TD>
                        <TD class="data" width="10%"> 
                         <INPUT type="text" name="NoOfProducts" maxlength="3" 
						 			size="3" value="<%=scProduct.getQuantityOrdered()%>">
                        </TD>
                        <TD class="data" align="center" width="13%"><%=scProduct.getTotalPrice()%></TD>
                      </TR>
                      <%
                          }
                       }else{
                    %>
                      <!-- Sets the value if the shopping cart is empty.
                         This value is used by JavaScript -->
                      <INPUT type="hidden" name="EmptyShoppingCart" value="0">
                      <TR align="center"> 
                        <TD class="subHeading" width="3%"> No Products are present 
                          in your Shopping Cart. </TD>
                      </TR>
                      <%
                       }
                    %>
                      </TBODY> 
                    </TABLE>
                    <!-- 8th table ends here (shopping cart)-->
                  </TD>
                  <TD width="8%">&nbsp;</TD>
                </TR>
                <TR>
                  <TD colspan=3>&nbsp;</TD>
                </TR>
                <TR> 
                  <TD colspan=3 align="center"><a href="javascript: updateSC()"><img src="images/Update.gif" border=0 ></a> 
                    &nbsp;&nbsp; <a href="javascript:deleteFromSC()"><img src="images/Delete1.gif" border=0 ></a> 
                    &nbsp;&nbsp; <a href="javascript:orderSC()">
					<img src="images/CheckOut.gif" border=0 width="67" height="33" ></a> 
                  </TD>
                </TR>
                <TR>
                  <TD colspan=3>&nbsp;</TD>
                </TR>
                </TBODY> 
              </TABLE>
              <!-- 5th table ends here working area-->
            </TD>
          </TR>
          <TR> 
            <TD vAlign=top align=left noWrap>&nbsp;</TD>
          </TR>
          <TR> 
            <TD vAlign=top align=left ><image src="images/Line.gif" height="1" width="100%"></TD>
          </TR>
        </TABLE>
        <!--  7th table End of table which includes left bar, main working area -->
      </TD>
    </TR>
    <TR> 
      <TD height=19 vAlign=top align="left"><font face="Arial, Helvetica" size=-2> 
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &copy; Copyright 2002, Oracle Corporation. 
        All rights reserved.</font> </TD>
    </TR>
  </TABLE>
  <!-- Main table ends here-->
</form>
</BODY>
</HTML>
