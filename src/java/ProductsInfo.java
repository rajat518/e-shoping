/*
 * @author  Sujatha
 * @version 1.0
 *
 *  Development Environment        :  Oracle9i JDeveloper
 *  Name of the Application        :  ProductsInfo.java
 *  Creation/Modification History  :
 *
 *     Sujatha      17-Oct-2002      Created
 */



/**
 * This class maintains information about a product.  This is used to share 
 * product information between the JavaBean and the Java Server Pages.
 */
public class ProductsInfo {

  private int id;
  private String name;  
  private String desc;
  private float listPrice;
  private int quantity;

  /**
   * Empty Constructor
   */
  public ProductsInfo()  {
    
  }

  /**
   * Constructor. Stores the values passed as parameters into member variables
   */  
  public ProductsInfo( int productID, String productName, String productDesc,
                       float productPrice, int quantity ) {
    this.id  = productID;
    this.name = productName;
    this.desc = productDesc;
    this.listPrice = productPrice;
    this.quantity = quantity;
  }

  /**
   * Returns the Product ID
   */  
  public int getID() {
    return this.id;
  }

  /**
   * Returns the Product name
   */
  public String getName() {
    return this.name;
  }

  /**
   * Returns the Product Description
   */
  public String getDesc() {
    return this.desc;
  }

  /**
   * Returns the Product Price
   */
  public float getPrice() {
    return this.listPrice;
  }

  /**
   * Returns the quantity of product ordered by customer
   */
  public int getQuantity() {
    return this.quantity;
  }
}