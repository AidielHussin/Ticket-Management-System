/***==================================================
Definition of Ticket Class 
==================================================***/

public class Ticket
{
    private String ticketNo;    //declaration of  attribute ticket number 
    private String festDate;    //declaration of attribute festival date 
                                //date determining which performer will attend 
                                //Format : DDMMYYYY
    private char stageZone;     //declaration of attribute stage zone 
                                //Area of the concert that customer wants 
                                //zone 1 - nearest with stage
                                //zone 2 - Basic zone
                                //Zone 3 - furthest from the stage
    private char category;      //declaration of attribute category 
                                //Ticket category: VVIP-1, VVIP-2, Basic-3, Festival-4, No concert-5
    private Customer cust;      //declaration of attribute customer
                                //link with Customer class
    
    //default constructor
    public Ticket()
    {
        ticketNo="";            //Initialize ticketNo to an empty string 
        festDate="00/00/00";    //Initialize festDate to "00/00/00"
        stageZone='n';          //Initialize stage to "n"
        category='n';           //Initialize category to "n"
        cust = cust;            //Initialize cust to a new customer object 
    }
    
    //normal constructor
    public Ticket(String ticketNo, String festDate, char stageZone, char category, Customer cust)
    {
        this.ticketNo=ticketNo;    //name value accepted from parameter as ticketNo 
        this.festDate=festDate;    //name value accepted from parameter as festDate
        this.stageZone=stageZone;  //name value accepted from parameter as tstageZone
        this.category=category;    //name value accepted from parameter as category
        this.cust = cust;          //name value accepted from parameter as customer
    }
    
    //setter method
    public void setTicket(String ticketNo, String festDate, char stageZone, double discount, char category, Customer cust)
    {
        this.festDate=festDate;     //set the current festDate's value
        this.stageZone=stageZone;   //set the current stageZone's value 
        this.category=category;     //set the current category's value 
        this.cust= cust;            //set the current customer's value 
    }
    
    //setter
    public void setTicketNo(String ticketNo){this.ticketNo=ticketNo;}    //set for current ticketNo's value
    public void setFestDate(String festDate){this.festDate=festDate;}    //set for current festDate's value
    public void setStageZone(char stageZone){this.stageZone=stageZone;}  //set for current stageZone's value
    public void setCategory(char category) {this.category=category;}     //set for current category's value
    public void setCust(Customer cust){this.cust=cust;}                  //set for current customer's value
    
    //getter method
    public String getTicketNo() {return ticketNo;}    //retrieving the value of the attribute ticketNo
    public String getFestDate() {return festDate;}    //retrieving the value of the attribute festDate
    public char getStageZone() {return stageZone;}    //retrieving the value of the attribute stageZone
    public char getCategory() {return category;}      //retrieving the value of the attribute category
    public Customer getCust() {return cust;}          //retrieving the value of the attribute cust
   
    //calculatePrice()
    public double calcPrice() 
    {
        //default price (without concert) is RM 20.00;
        double discount = 0, price = 20.00;
        
        //below is pricing according to zone (price + default price) 
        switch (stageZone)              //Use a switch statement to determine pricing adjusment based on the selected stage zone        
        {
            case '1' :                  //If Zone 1 is selected
                price = price + 30.00;  //add RM30.00 to the default price
                break;                  //Break out of the switch statement to avoid falling through to the next case 
            case '2' :                  //If Zone 2 is selected 
                price = price + 15.00;  //add RM15.00 to the default price
                break;                  //Break out of the switch statement to avoid falling through to the next case
            case '3' :                  //If Zone 3 is selected 
                price = price + 7.00;   //add RM 7.00 to the default price 
                break;                  //Break out of the switch statement to indicate to end of the switch
        }
        
        //price based on the selected category and any additional features (VVIP,VIP,BASIC, NO CONCERT)
        switch (category)       //Use a switch statement to determine pricing adjustment based on the selected ticket category
        {
            case '1':
                //VVIP category : Add RM200.00 for meet and greet
                price += 200.00;
                break;           //Exit the switch statement after applying the VVIP pricing
            case '2' :
                //VIP category : Add RM100.00 for top-stage view (rooftop)
                price +=100.00;
                break;           //Exit the switch statement after applying the VIP pricing
            case '3' :
                //Basic category : Add RM 20.00 for standard for seating or normal hoes
                price += 20.00;
                break;           //Exit the switch statement after applying the Basic pricing
            case '4' :
                //No concert category: No additional charges
                price += 0.00;
                break; //Exit the switch statement after applying the No Concert pricing
            }
        

        //Check if the customer has birthday in February (month code '02' in IC) and calculate discount
        if(getCust().getCustIC().substring(2,4).equals("02")) //If it's the customer's birthday month (February), apply 20% discount
             discount = 20/100 ; 
             
        price = price - (price * discount);  //Apply the calculated discount to the final ticket price
        
        return price; //return the final calculated price after applying any discount
    }
    
    
    //The date to know who the performer is
    public String performance() 
    {
        String performer="";                            //Initialize the performer string
        if (festDate.equalsIgnoreCase("01042024"))      //Check if the festival date is 01-Apr-2024
            performer = "Mitski";                       //If true, set the performer to Mitski
        else if (festDate.equalsIgnoreCase("02042024")) //Check if the festival date is 02-Apr-2024
            performer = "Taylor Swift";                 //If true, set the performer to Taylor Swift
        else if (festDate.equalsIgnoreCase("03042024")) //Check if the festival date is 03-Apr-2024
            performer = "BlackPink";                    //If true, set the performer to BlackPink
        return performer;                               //Return the performer 
    }
    
    //ToString() 
    public String toString()
    {    
        String display = getCust().toString()+"\nTicket No: "+ticketNo+     //To display 
                  "\n============================" +
                  "\nDate : " + festDate +                                  //return to display festDate
                  "\nStage Zone: " + Character.toString(stageZone)+         //return to display stageZone
                  "\nCategory: " +Character.toString(category)+             //return to display category 
                  "\nPerformer: " +performance();                           //return to display perfomance
        return display;                                                     //return display 
    }
}