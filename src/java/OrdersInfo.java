/*
 * @author  Sujatha
 * @version 1.0
 *
 *  Development Environment        :  Oracle9i JDeveloper
 *  Name of the Application        :  OrdersInfo.java
 *  Creation/Modification History  :
 *
 *     Sujatha      17-Oct-2002      Created
 */




/**
 * This class maintains information about an order.  This is used to share order
 * information between the JavaBean and the Java Server Pages.
 */
public class OrdersInfo {

  private int id;
  private String userID;  
  private String status;
  private String   orderDate;
  private Integer[]  productID;
  private String[] productName;
  private Integer[] quantity;
  private Float[] listPrice;
  private Integer[] quantityOnHand;
  
  /**
   * Empty Constructor
   */
  public OrdersInfo()  {
    
  }

  /**
   * Constructor. Stores the values passed as parameters into member variables
   */
  public OrdersInfo( int orderID, String userID, String orderStatus, String orderDate,
                     Integer[] productID, String[] productName, Integer[] quantity,
                     Float[] productPrice, Integer[] quantityOnHand ) {
    this.id  = orderID;
    this.userID = userID;
    this.status = orderStatus;
    this.orderDate = orderDate;
    this.productID = productID;
    this.productName = productName;
    this.quantity = quantity;
    this.listPrice = productPrice;
    this.quantityOnHand = quantityOnHand;
  }

  /**
   * Returns the Order ID
   */
  public int getID() {
    return this.id;
  }

  /**
   * Returns the value of User ID who placed the order
   */
  public String getUserID() {
    return this.userID;
  }

  /**
   * Returns the Order status
   */
  public String getStatus() {
    return this.status;
  }

  /**
   * Returns the date on which the order was placed
   */  
  public String getOrderDate() {
    return this.orderDate;
  }

  /**
   * Returns a list of Product IDs corresponding to a particular order
   */
  public Integer[] getProductID() {
    return this.productID;
  }

  /**
   * Returns a list of Product Names corresponding to a particular order
   */
  public String[] getProductName() {
    return this.productName;
  }

  /**
   * Returns a list of quantities of each of the products corresponding to a 
   * particular order
   */
  public Integer[] getQuantity() {
    return this.quantity;
  }

  /**
   * Returns a list of prices of each of the product corresponding to a 
   * particular order
   */
  public Float[] getPrice() {
    return this.listPrice;
  }

  /**
   * Returns a list of quantities of each of the products available in the inventory
   * corresponding to a particular order
   */
  public Integer[] getQuantityOnHand() {
    return this.quantityOnHand;
  }
}