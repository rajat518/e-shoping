/*
 * @author  Anupama Majety
 * @version 1.0
 *
 * Development Environment        :  Oracle9i JDeveloper
 * Name of the Application        :  ShippingDetails.java
 * Creation/Modification History  :
 *
 *    Anupama Majety      01-Jun-2002      Created
 *    Srikanth Mohan      24-Feb-2003      Certified on Linux
 *
 */

// Package name
package Project.SRC;

/**
 * This is a Java Bean with public fields and a constructor to assign values to.
 * This object contains the details where the object must be shipped and the
 * products within the order.
 */
public class ShippingDetails {

  /**
   * Name of the person to whom the product is shipped.
   */
  public String name;

  /**
   * Email id of the person to whom the product is shipped.
   */
  public String emailId;

  /**
   * Phone Number
   */
  public String phoneNumber;

  /**
   * Address where the product is shipped.
   */
  public String address;

  /**
   * Name of the city
   */
  public String city;

  /**
   * Country to which the product must be shipped.
   */
  public String country;

  /**
   * Zip code of the place.
   */
  public int zipCode;

  /**
  * Default constructor
  */
  public ShippingDetails(){
    this.name         = "";
    this.emailId      = "";
    this.address      = "";
    this.phoneNumber  = "";
    this.city         = "";
    this.country      = "";
    this.zipCode      = 0;
  }

  /**
   * This constructor is used when the shipping details are known and has to be
   * transferred as an object from one page to another.
   *
   * @param     name        Name of the person to whom order is to be shipped
   * @param     emailId     Email Id of the person
   * @param     phoneNumber PhoneNumber of the person
   * @param     address     Address where the order must be shipped
   * @param     city        Name of the city
   * @param     country     Name of the country
   * @param     zipCode     Zip code of the country
   *
   * @exception Exception   Exception raised in case invalid inputs are sent.
   */
  public ShippingDetails(String name, String emailId, String phoneNumber,
                String address, String city, String country, int zipCode)
                throws Exception{

    this.name         = name;
    this.emailId      = emailId;
    this.phoneNumber  = phoneNumber;
    this.address      = address;
    this.city         = city;
    this.country      = country;
    this.zipCode      = zipCode;
  }

}