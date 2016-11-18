/*
 * @author  Anupama Majety
 * @version 1.0
 *
 * Development Environment        :  Oracle9i JDeveloper
 * Name of the Application        :  OrderMasterInfo.java
 * Creation/Modification History  :
 *
 *    Anupama Majety      01-Jun-2002      Created
 *    Srikanth Mohan      24-Feb-2003      Certified on Linux
 *
 */

package src;

/**
 * This is a Java Bean with public fields and a constructor to assign values to.
 * This object contains the order details.
 */
public class OrderMasterInfo {

  /**
   * Id of the order
   */
  public int orderId;

  /**
   * Customer Id of the person who placed the order.
   */
  public String customerId;

  /**
   * Date on which the order is placed
   */
  public String orderDate;

  /**
   * Order Total
   */
  public float orderTotal;

  /**
   * Location from which the order must be shipped
   */
  public String branchLocation;

  /**
   * Status of the order
   */
  public String orderStatus;

  /**
  * Default constructor
  */
  public OrderMasterInfo(){
    this.orderId        = 0;
    this.customerId     = "";
    this.orderDate      = "";
    this.orderTotal     = 0.0f;
    this.branchLocation = "";
    this.orderStatus    = "";
  }

  /**
   * This constructor is used when the shipping details are known and has to be
   * transferred as an object from one page to another.
   *
   * @param     orderId         Holds the Unique Id of the order
   * @param     customerId      Customer Id of the person who placed the order
   * @param     orderDate       Date on which the order is placed
   * @param     orderTotal      order Total
   * @param     branchLocation  Location from which the order must be shipped
   * @param     orderStatus     Status of the order
   *
   * @exception Exception       Exception raised in case invalid inputs are sent.
   */
  public OrderMasterInfo(int orderId, String customerId, String orderDate,
                float orderTotal, String branchLocation,
                String orderStatus) throws Exception {
    this.orderId        = orderId;
    this.customerId     = customerId;
    this.orderDate      = orderDate;
    this.orderTotal     = orderTotal;
    this.branchLocation = branchLocation;
    this.orderStatus    = orderStatus;
  }
}