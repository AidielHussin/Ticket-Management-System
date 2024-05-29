/***==================================================
Definition of Customer Class 
==================================================***/

public class Customer
{
    private String custName;                    //declaration of the attribute name (customer's name)
    private String custIC;                      //declaration of the attribute identification card (customer's IC)
    private String phoneNum;                    //declaration of the attribute phone number (customer's phone number)
    
    public Customer(String custName, String custIC, String phoneNum)    //normal constructor 
    {
        this.custName = custName;              // name value accepted from parameter as name attribute
        this.custIC = custIC;                  // phone value accepted from parameter as phone attribute
        this.phoneNum = phoneNum;              // address value accepted from parameter as address attribute
    }
    
    //setter method
    public void setCustomer(String name, String custIC, String phoneNum)      //setter method for customer's details
    {
        this.custName = custName;                                             // name value accepted from parameter as name attribute
        this.custIC = custIC;                                                 // phone value accepted from parameter as phone attribute
        this.phoneNum = phoneNum;                                             //set the current address' value
    }
    
    public void setCustName(String custName){this.custName = custName;}                //set current name's value 
    public void setCustIC(String custIC){this.custIC = custIC;  }                      //set current phone's value
    public void setPhoneNum(String phoneNum){this.phoneNum = phoneNum;  }              //set current address' value
    
    //getter method
    public String getCustName(){return custName; }          //return the value of the attribute name
    public String getCustIC(){return custIC; }              //return the value of the attribute phone
    public String getPhoneNum(){return phoneNum; }          //return the value of the attribute address
    
    //display customers' details
    public String toString()        
    {
        return("\nCustomer Name: " + custName +     //return to display customer's name
               "\nCustomer IC " + custIC +          //return to display customer's phone number
               "\nPhone Number:" + phoneNum);       //return to display customer's address 
    }
}