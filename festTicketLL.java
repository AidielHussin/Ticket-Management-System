/***=============================================
Main Application of Ticket Management System 
=============================================***/

/**
List of processing:
    1. Insert the data from input file.
    2. copy the details of tickets that picked to watch Mitski into concertMitski, those that watch Taylor
       Swift into concertSwift and those that watch BlackPink into concertBP.
    3. Display the details of tickets done by the customers that choose VVIP.
    4. Update the ticketDate into 03042024 for customer with phone number 0173669682.
    5. Display the customer's details in concertMitski.
    6. Calculate and display the total price for each ticket. 
    7. Remove ticket from Aidiel Adha
*/
// Import necessary Java packages for file I/O and data structures
import java.io.*;
import java.util.*;

public class festTicketLL       // Define the class named "festTicketLL"
{
    public static void main (String args[]) throws Exception         // Declare the main method, which may throw an Exception
    {
     try{           // Try block to handle potential exceptions during program execution
        LinkedList ticketList = new LinkedList(); //declare new LinkedList named ticketList
        Ticket t;                                 //declare new Ticket object called t
        
        // Process 1
        // Prompt display
        System.out.print("Welcome to Ticket Management System by Dato's!");
        System.out.println("\n\nList of Processes: ");
        System.out.println("\t=================================================================================");
        System.out.println("\t                          1. Insert data from input file.");
        System.out.println("\t=================================================================================");
                            
        readFile(ticketList);                    //Use function to get input from input file
         
        //Display all the current data from the input
        displayTicketDetails(ticketList,"all");
        
        //Process 2
        //Display message for process 2
        System.out.println("\n\t=================================================================================");
        System.out.println("\t          2. Copy the details of tickets that picked to watch Mitski into ");
        System.out.println("\t          concertMitski, those that  watch Taylor Swift into concertSwift");
        System.out.println("\t                   and those that watch BlackPink into concertBP.");
        System.out.println("\t=================================================================================");

        
        //Declaration of new lists according to performers
        LinkedList concertMitski = new LinkedList();            //LinkedList for people who come when Mitski perform
        LinkedList concertSwift = new LinkedList();             //LinkedList for people who come when Taylor Swift perform  
        LinkedList concertBP = new LinkedList();                //LinkedList for people who come when BlackPink perform
        
        Ticket performer = (Ticket) ticketList.getHead();       //Call for the first ticket in ticketList
        while( performer != null)                               //Loop when performer is not null
        {
            if(performer.performance().equals("Mitski"))                        //If performer is Mitski
                concertMitski.addFirst(performer);                              //then ticket will be added to concertMitski list
            else if (performer.performance().equals("Taylor Swift"))            //If performer is Taylor Swift
                concertSwift.addFirst(performer);                               //then ticket will be added to concertSwift list
            else if (performer.performance().equals("BlackPink"))               //If performer is BlackPink
                concertBP.addFirst(performer);                                  //the ticket will added to concertBP list

            performer = (Ticket) ticketList.getNext();         //Call for the next ticket in ticketList
        }
        
        //Display the tickets that want to watch Mitski by passing the linkedlist and subject as argument
        displayTicketDetails(concertMitski, "Mitski");
        
        //Display the tickets that want to watch Taylor Swift  by passing the linkedlist and subject as argument
        displayTicketDetails(concertSwift, "Taylor Swift");
        
        //Display the tickets that want to watch Blackpink  by passing the linkedlist and subject as argument
        displayTicketDetails(concertBP, "BlackPink");
        
        //Process 3
        //Display message to show the start of third process
        System.out.println("\n\t=================================================================================");
        System.out.println("\t    3. Display the details of tickets done by the customers that choose VVIP.");
        System.out.println("\t=================================================================================");
        
        Ticket vvip = (Ticket) ticketList.getHead();                                //Call for the first ticket in ticketList with vvip as variable
        System.out.println("\nMummy Fest Ticket Details: [VVIP] ");                 //Display message to indicate the purpose of the output
        int p =0;    //initialize counting number
        
        //to find tickets that have the category as 1
        while (vvip != null)             //Loop when vvip is not null
        {
            p++;                         //increment p 
            if (vvip.getCategory()=='1') // if the category in ticket is '1'
                System.out.println("\nCustomer " +p+ ": " + vvip.toString()); //then it will displayed
            vvip = (Ticket) ticketList.getNext(); //Call for the next ticket in ticketList
        }
        
        //Process 4
        //Display message for process 4
        System.out.println("\n\t=================================================================================");
        System.out.println("\t 4. Update the ticketDate into 03042024 for customer with phone number 0173669682.");
        System.out.println("\t=================================================================================");
        
        Ticket update = (Ticket) ticketList.getHead();          // Get the first ticket in ticketList
        System.out.println("\nFinding 0173669682.... ");        //Display message about finding the phone number
        LinkedList temp = new LinkedList();
        
        while (update != null)              //Loop when update is not null
        {   // Check if the current ticket's phone number matches the target phone number
            if (update.getCust().getPhoneNum().equals("0173669682")) {      
                // Display the details of the ticket before the update
                System.out.println("Before update: " + "\n" + update.toString());       
                String performanceBefore = update.performance(); //  // Store the performance information before the update
            
                // Transfer all elements except the one to be removed to the temporary list
                while (concertMitski.getHead() != null) {       //Loop when concertMitski is not null
                   Ticket current = (Ticket) concertMitski.removeFirst(); 
                   if (!current.equals(update)) {
                      temp.addLast(current);
                   }
                }
            
                // Update the original list with the temporary one
                while (temp.getHead() != null) {                   // Iterate through the temporary list
                    concertMitski.addLast(temp.removeFirst());      //add each ticket to the original performance list (concertMitski)
                }
            
                // Set the new festival date for the updated ticket
                update.setFestDate("03042024");     
                // Display the details of the ticket after the update
                System.out.println("\nAfter update: " + "\n" + update.toString());
            
                // Reevaluate the performance category based on the updated date
                if (update.performance().equals("Mitski")) {            // If the updated ticket's performance is Mitski,
                    concertMitski.addLast(update);                      // then add it to the concertMitski list
                } else if (update.performance().equals("Taylor Swift")) {     // If the updated ticket's performance is Taylor Swift,
                    concertSwift.addLast(update);                             // then add it to the concertSwift list     
                } else if (update.performance().equals("BlackPink")) {          // If the updated ticket's performance is BlackPink,
                    concertBP.addLast(update);                                  // then add it to the concertBP list
                }
                break;                                                            // and the loop will break immediately after this block is executed
                }
            update = (Ticket) ticketList.getNext();                                 //Call for the next ticket in ticketList
        }
        
        //Process 5
        //Display message to show the start of fifth process
        System.out.println("\n\t=================================================================================");
        System.out.println("\t\t         5. Display the customer's details in concertMitski.");
        System.out.println("\t=================================================================================");
        
        Ticket mitskiStan = (Ticket) concertMitski.getHead();               //Call for the first ticket in ticketList
        System.out.println("\nMummy Fest [MITSKI] Customer Details: ");     //Display message to indicate the purpose of this output
        int r=0;    //initializing r to count
        while (mitskiStan != null)              //Loop when mitskiStan is not null  
        {
            r++;         //increment r
            System.out.println("\nCustomer " +r+ ": " + mitskiStan.getCust().toString());       //{rint the customer's details of each ticket in concertMitski list
            mitskiStan = (Ticket) concertMitski.getNext();               //Call for the next ticket in ticketList           
        }
        
        //Process 6
        //Display message to show the start of sixth process
        System.out.println("\n\t=================================================================================");
        System.out.println("\t\t     6. Calculate and display the total price for each ticket.");
        System.out.println("\t=================================================================================");
        
        Ticket price = (Ticket) ticketList.getHead();           //Call for the first ticket in ticketList
        System.out.println("\nMummy Fest Price: ");             //Display message to indicate the purpose of this output
        int s=0;            //Initializing s to count
        while (price != null)           //Loop when price is not null  
        {
            s++;            //Increment s
            System.out.println("\nCustomer " +s+ ": " + price.getCust().toString()); // print the customer's details of each ticket 
            System.out.printf("\nTotal Price: %.2f\n", price.calcPrice());           // print customer's total price for the ticket
            price = (Ticket) ticketList.getNext();                                   //Call for the next ticket in ticketList
        }
    
        //Process 7
        //Display message to show the start of sixth process
        System.out.println("\n\t=================================================================================");
        System.out.println("\t\t                 7. Remove ticket named Aidiel Adha.");
        System.out.println("\t=================================================================================");
        
        // Create an array of LinkedLists containing all the linked lists created
        LinkedList[] lists = {ticketList, concertMitski, concertSwift, concertBP};
        boolean foundDiel = false;
        for (int i = 0; i < lists.length; i++) {    // Iterate through the array of linked lists
            LinkedList currentList = lists[i];      //Get the current linked list
            
            Ticket currentTicket = (Ticket) currentList.getHead(); // Get the first ticket in the current linked list
            LinkedList temp2 = new LinkedList(); // Create a temporary list to store tickets
                
            while (currentTicket != null) {      // Iterate through the tickets in the current linked list
                if (currentTicket.getCust().getCustName().equalsIgnoreCase("Aidiel Adha")) { // // Check if the current ticket's customer name is 'Aidiel Adha'
                    // Print or process the removed ticket
                    foundDiel = true;
                } else {
                    // tickets that are not by Aidiel Adha are added to the temporary list
                    temp2.addLast(currentTicket);
                }
                    currentTicket = (Ticket) currentList.getNext();  // Move to the next ticket in the current linked list
            }
            // Clear the original list
            currentList.clear();
            
            // Add all tickets back to the original list except those named 'Aidiel Adha'
            Ticket tempTicket = (Ticket) temp2.getHead();       // Get the first ticket in tempTicket
            while (tempTicket != null) {        //Loop while the list is not null
                currentList.addLast(tempTicket);                //add the tickets back to its original
                tempTicket = (Ticket) temp2.getNext();          //Move to the next ticket in tempTicket
                }
            }
        if(foundDiel)
                System.out.println("\nTicket named Aidiel Adha successfully removed.");
        else{
                System.out.println("\nAidiel Adha is not found");}
            
        //Message is displayed to traverse the list for final version
        System.out.println("\n\t=================================================================================");
        System.out.println("\t\t                         TRAVERSING LIST");
        System.out.println("\t=================================================================================");
        
        //Display all the current data from ticketList
        displayTicketDetails(ticketList,"all");
        
        //Display the tickets that want to watch Mitski
        displayTicketDetails(concertMitski, "Mitski");
        
        //Display the tickets that want to watch tay swift
        displayTicketDetails(concertSwift, "Taylor Swift");
        
        //Display the tickets that want to watch blackpink
        displayTicketDetails(concertBP, "BlackPink");
        }catch (Exception e){               //error handling for input file
            e.printStackTrace();            // Print any exceptions that occur during file reading and processing
     }
    }

    public static void displayTicketDetails(LinkedList sourceList, String festivalName) { //a function to traverse lists by taking the list and the subject as parameters
        int customerNumber = 0;  //initialize customerNumber for counting purposes
        Ticket ticket = (Ticket) sourceList.getHead();      //Call for the first ticket in the list
        System.out.println("\nMummy Fest (" + festivalName + ") Ticket Details: ");    // print the subject of the traversal

        while (ticket != null) {                //Loop while ticket is not null
            customerNumber++;                   //increment customerNumber
            System.out.println("\nCustomer " + customerNumber + ": " + ticket.toString());      //display the contents of each ticket
            ticket = (Ticket) sourceList.getNext();                                             //Call for the next ticket in list 
        }
    }

    //Method to read the input file
    public static void readFile(LinkedList ticketList) throws Exception  
    {
        try // Try block to handle potential exceptions during file reading and processing
        {
            FileReader fr = new FileReader("TicketInput.txt");   // Open a FileReader to read from "TicketInput.txt"
            BufferedReader br = new BufferedReader(fr);          // Create a BufferedReader to efficiently read characters from FileReader
                
            StringTokenizer st= null; // Initialize StringTokenizer to null
            String dataRow = br.readLine(); //Read the first line from the file
                
            while (dataRow != null) // Loop through each line in the file
            {
                st = new StringTokenizer(dataRow, "*"); // Tokenize the current line using '*' as a delimiter
                    
                String cName = st.nextToken();    //Extract customer's name from first token
                String cIC = st.nextToken();      // Extract customer's IC from the next token
                String cPhone = st.nextToken();   //Extract customer phone number from the next token
                Customer cust= new Customer (cName, cIC, cPhone);  // Create a Customer object with the extracted information
                    
                //input ticketNum,festDate, stagezone, [discount], category - (vvip/vip/basic/no concert)
                String ticketNum = st.nextToken();  //Extract ticketNum form the next token
                String date = st.nextToken();       //Extract date from the next token
                char zone = st.nextToken().charAt(0); // Extract zone from the next token
                char category = st.nextToken().charAt(0); //Extract category from the next token
                // Create a Ticket object with ticketNum, date, zone, category, and cust (composite with Customer))
                Ticket t = new Ticket (ticketNum, date,zone,category, cust); 
                    
                ticketList.addLast(t);  // Add Ticket object to ticketList
                dataRow = br.readLine();// Read the next line from the file
            }
            br.close();     // Close the BufferedReader after processing the file
        }catch (Exception e) { //error handling for input file
            e.printStackTrace(); // Print any exceptions that occur during file reading and processing
        }
    }
}