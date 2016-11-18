/*
 * @author  Anupama Majety
 * @version 1.0
 *
 * Development Environment        :  Oracle9i JDeveloper
 * Name of the Application        :  Product.java
 * Creation/Modification History  :
 *
 *    Anupama Majety      01-Jun-2002      Created
 *    Srikanth Mohan      24-Feb-2003      Certified on Linux
 *
 */

// Package name


/**
 * This is a Java Bean with public fields and constructor to assign values to.
 * This class maps directly to a product sold by the manufacturer.
 */
public class Product {

  /**
   * ID of the product
   */
  public String productID;

  /**
   * Name of the Product
   */
  public String productName;

  /**
   * Description about the Product
   */
  public String productDesc;

  /**
   * Price of the Product
   */
  public float productPrice;

  /**
   * Category to which this product belongs
   */
  public String categoryDesc;

  /**
   * Quantity of the Product that the manufacturer has
   */
  public int quantityOnHand;

  /**
   * Default Constructor
   */
  public Product(){
    this.productID      = "";
    this.productName    = "";
    this.productDesc    = "";
    this.productPrice   = 0.0f;
    this.categoryDesc   = "";
    this.quantityOnHand = 0;
  }

  /**
   * This Constructor can be used if we know the attributes of the product
   * beforehand.
   *
   * @param productID ID of the Product
   * @param productName Name of the Product
   * @param productDesc Description of the Product
   * @param productPrice Price of the Product
   * @param categoryDesc Category to which this product belongs
   * @param quantityOnHand Quantity available
   * @exception Exception In case of some unreported Exception
   */
  public Product(String productID, String productName, String productDesc,
                     float productPrice, String categoryDesc,
                     int quantityOnHand ) throws Exception{
    this.productID      = productID;
    this.productName    = productName;
    this.productDesc    = productDesc;
    this.productPrice   = productPrice;
    this.categoryDesc   = categoryDesc;
    this.quantityOnHand = quantityOnHand;
  }
}