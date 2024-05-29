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

public class festTicketQ // Define the class named "festTicketQ"
{
    public static void main(String args[]) throws Exception //// Declare the main method, which may throw an Exception
    {
      try{          // Try block to handle potential exceptions during program execution
        //declaration 
        Queue ticketQ = new Queue(); //Create new Queue called ticketQ
        Queue tempTicketQ = new Queue(); //Create temporary Queue called tempTicketQ
        Ticket ticket; //Declare new Ticket object

        
        //Process 1
        //Display message to greet user
        System.out.print("Welcome to Ticket Management System by Dato's!");
        System.out.println("\n\nList of Processes: ");
        //Display the current process running
        System.out.println("\t=================================================================================");
        System.out.println("\t                          1. Insert data from input file.");
        System.out.println("\t=================================================================================");
                            
        readFile(ticketQ);          //to get data from input file using a function
        
        //Display all the current data from the input
        displayTicketDetails(ticketQ, tempTicketQ, "all");
          
        //Process 2
        //Display message for process 2
        System.out.println("\n\t=================================================================================");
        System.out.println("\t          2. Copy the details of tickets that picked to watch Mitski into ");
        System.out.println("\t          concertMitski, those that  watch Taylor Swift into concertSwift");
        System.out.println("\t                   and those that watch BlackPink into concertBP.");
        System.out.println("\t=================================================================================");

        //Declaration of new lists according to performers
        Queue tempQ2 = new Queue();             //Create new temporary queue called tempQ2
        Queue concertMitski = new Queue();      //Create new queue called concertMitski
        Queue concertSwift = new Queue();       //Create new queue called concertSwift
        Queue concertBP = new Queue();          //Create new queue called concertBP
    
        while(!ticketQ.isEmpty())               //Loop while ticketQ is not null
        {
            ticket = (Ticket)ticketQ.dequeue();     //Call the Ticket object to dequeue from ticketQ 
            if(ticket.performance().equalsIgnoreCase("Mitski"))             //if the main performance is Mitski
                concertMitski.enqueue(ticket);                              //then the ticket will be added into concertMitski queue
            else if (ticket.performance().equalsIgnoreCase("Taylor Swift")) //if the main performance is Taylor Swift
                concertSwift.enqueue(ticket);                               //then the ticket will be added into concertSwift queue
            else if (ticket.performance().equalsIgnoreCase("BlackPink"))    //if the main performance is BlackPink
                concertBP.enqueue(ticket);                                  //then the ticket will be added into concerBP queue
            tempQ2.enqueue(ticket);                 //ticket will be added into tempQ2 
        }
        
        //Display the tickets that want to watch Mitski 
        displayTicketDetails(concertMitski, tempTicketQ, "MITSKI");
        //Display the tickets that want to watch Taylor Swift 
        displayTicketDetails(concertSwift, tempTicketQ, "TAYLOR SWIFT");
        //Display the tickets that want to watch BlackPink
        displayTicketDetails(concertBP, tempTicketQ, "BLACKPINK");
        
        //Process 3
        //Display message for process 3
        System.out.println("\n\t=================================================================================");
        System.out.println("\t    3. Display the details of tickets done by the customers that choose VVIP.");
        System.out.println("\t=================================================================================");
        
        System.out.println("\nMummy Fest Ticket Details: [VVIP] ");     //Display message to indicate the purpose of the output
        int p =0;               //initialize p 
        //trying to find tickets that put category as 1
        while(!tempQ2.isEmpty())        //loop while tempQ2 is not empty
        {
            ticket =(Ticket)tempQ2.dequeue();           //Call to dequeue the tempQ2 queue          
            p++;                                        //increment p
            if (ticket.getCategory() == '1')            //if the ticket's category is 1 
                System.out.println("\nCustomer " +p+ ": " + ticket.toString());     //then the customer's details will be displayed
            ticketQ.enqueue(ticket);                    //ticket wil be added into ticketQ queue
        }
        
        //Process 4
        //Display the message for process 4
        System.out.println("\n\t=================================================================================");
        System.out.println("\t 4. Update the ticketDate into 03042024 for customer with phone number 0173669682.");
        System.out.println("\t=================================================================================");
        Queue tempQ3 = new Queue();
        System.out.println("\nFinding 0173669682.... "); //Display message about finding the phone number
        boolean foundNum = false;
        // Create an array of queues containing all the queues (excluding temporary queues) created
        while(!ticketQ.isEmpty()){
            ticket = (Ticket) ticketQ.dequeue();
            if (ticket.getCust().getPhoneNum().equals("0173669682")) {
                System.out.println("\nBefore Update: \n" + ticket.toString());

                String performanceBefore = ticket.performance();  // Store the performance before removal
                ticket.setFestDate("03042024");
                
                if (performanceBefore.equalsIgnoreCase("Mitski")) {
                    // Remove the ticket from concertMitski based on phone number
                    removeTicket(concertMitski, tempQ3 ,"phoneNum", "0173669682", foundNum);
                } else if (performanceBefore.equalsIgnoreCase("Taylor Swift")) {
                    // Remove the ticket from concertMitski based on phone number
                    removeTicket(concertSwift, tempQ3 ,"phoneNum", "0173669682", foundNum);
                } else if (performanceBefore.equalsIgnoreCase("BlackPink")) {
                    // Remove the ticket from concertMitski based on phone number
                    removeTicket(concertBP, tempQ3,"phoneNum", "0173669682", foundNum);
                }
        
                // Re-enqueue the ticket into concertMitski or other queues based on performance
                if (ticket.performance().equalsIgnoreCase("Mitski")) {
                    concertMitski.enqueue(ticket);
                } else if (ticket.performance().equalsIgnoreCase("Taylor Swift")) {
                    concertSwift.enqueue(ticket);
                } else if (ticket.performance().equalsIgnoreCase("BlackPink")) {
                    concertBP.enqueue(ticket);
                }
                System.out.println("\nAfter Update: \n" + ticket.toString());
            }
            tempQ2.enqueue(ticket);
        }
        
        // Re-enqueue the remaining tickets back to ticketQ
        while (!tempQ2.isEmpty()) {
            ticketQ.enqueue(tempQ2.dequeue());
        }                                                                   
                
        //Process 5
         //Display the message for process 5
        System.out.println("\n\t=================================================================================");
        System.out.println("\t\t         5. Display the customer's details in concertMitski.");
        System.out.println("\t=================================================================================");
        
        System.out.println("\nMummy Fest [MITSKI] Customer Details: ");     // Display message to indicate the purpose of this output
        int r=0;             //initializing r to count
        while (!concertMitski.isEmpty())        // loop while concertMitski is not empty    
        {
            ticket =(Ticket)concertMitski.dequeue();        //Call to dequeue the concertMitski queue 
            r++;                                            //increment r
            System.out.println("\nCustomer " +r+ ": " + ticket.getCust().toString());   // print the customer's details of each ticket in concertMitski list
            tempTicketQ.enqueue(ticket);             //ticket wil be added into tempTicketQ queue
        }
        
        //Put the original contents of concertMitski back
        while (!tempTicketQ.isEmpty())          // loop while tempTicketQ is not empty    
        {
            concertMitski.enqueue((Ticket)tempTicketQ.dequeue());       //Contents that are dequeued from tempTicketQ will be added into concertMitski queue
        }
        
        //Process 6
         //Display the message for process 6
        System.out.println("\n\t=================================================================================");
        System.out.println("\t\t     6. Calculate and display the total price for each ticket.");
        System.out.println("\t=================================================================================");
        
        System.out.println("\nMummy Fest Price: "); // Display message to indicate the purpose of this output
        int s=0; //initializing s to count
        while (!ticketQ.isEmpty()) //Loop when tempQ2 is not empty
        {
            ticket =(Ticket)ticketQ.dequeue();       //Call to dequeue tempQ2 
            s++;                                    //increment s
            System.out.println("\nCustomer " +s+ ": " + ticket.getCust().toString()); // print the customer's details of each ticket 
            System.out.printf("\nTotal Price: %.2f\n", ticket.calcPrice());           // print customer's total price for the ticket
            tempQ2.enqueue(ticket);            //ticket wil be added into ticketQ queue
        }
        
        while(!tempQ2.isEmpty())
        {  ticketQ.enqueue((Ticket) tempQ2.dequeue());}
        
        //Process 7
        //Display the message for process 7
        System.out.println("\n\t=================================================================================");
        System.out.println("\t\t                 7. Remove ticket named Aidiel Adha.");
        System.out.println("\t=================================================================================");
        
        Queue[] queue = {ticketQ, concertMitski, concertSwift, concertBP};
        
        boolean foundDiel = false;
        for (Queue currentQueue : queue) { // Iterating through each queue in the array
            removeTicket(currentQueue, tempQ2, "custName", "Aidiel Adha", foundDiel);
            foundDiel = true;
        }
        
        if (foundDiel) {
            System.out.println("\nTicket named Aidiel Adha successfully removed.");
        } else {
            System.out.println("\nTicket not found for the specified criteria.");
        }
        
        //Display message to traverse the final version of the queue
        System.out.println("\n\t=================================================================================");
        System.out.println("\t\t                         TRAVERSING QUEUE");
        System.out.println("\t=================================================================================");
        
        //Display all the current data from ticketList
        displayTicketDetails(ticketQ,tempTicketQ,"all");
        
        //Display the tickets that want to watch Mitski
        displayTicketDetails(concertMitski, tempTicketQ,"Mitski");
        
        //Display the tickets that want to watch tay swift
        displayTicketDetails(concertSwift, tempTicketQ,"Taylor Swift");
        
        //Display the tickets that want to watch blackpink
        displayTicketDetails(concertBP, tempTicketQ, "BlackPink");
      }catch (Exception e){         //error handling for input file
            e.printStackTrace();    // Print any exceptions that occur during file reading and processing
        }
    }
    
    public static void displayTicketDetails(Queue sourceQueue, Queue tempQueue, String festivalName) {//a function to traverse queues by taking the queue, temporary queue and the subject as parameters
        int customerNumber = 0;     //initialize customerNumber for counting purposes
        Ticket ticket;      //Declare Ticket object

        System.out.println("\nMummy Fest (" + festivalName + ") Ticket Details: ");  // print the subject of the traversal

        while (!sourceQueue.isEmpty()) {        //Loop while the queue is not empty
            ticket = (Ticket) sourceQueue.dequeue();    //Call to dequeue the queue
            customerNumber++;       //increment customerNumber
            System.out.println("\nCustomer " + customerNumber + ": " + ticket.toString());      //display the contents of each ticket
            tempQueue.enqueue(ticket);          //add the ticket into tempQueue
        }
        //put the contents of temporary queue back into its original
        while (!tempQueue.isEmpty()) {      //Loop when the tempQueue is not empty
            sourceQueue.enqueue((Ticket) tempQueue.dequeue());      //Contents that are dequeued from temporary Queue will be added into its original queue 
        }
    }
    
    // Helper method to remove a ticket from a specific queue based on a String condition
    public static void removeTicket(Queue queue, Queue temporary ,String conditionType, String conditionValue, boolean found) {
        while (!queue.isEmpty()) {
            Ticket currentTicket = (Ticket) queue.dequeue();
            if ((conditionType.equals("phoneNum") && currentTicket.getCust().getPhoneNum().equals(conditionValue))
                    || (conditionType.equals("custName") && currentTicket.getCust().getCustName().equalsIgnoreCase(conditionValue))) {
                found = true;
                // Do not enqueue, skip the ticket
            } else {
                temporary.enqueue(currentTicket);
            }
        }
        
        while(!temporary.isEmpty()){
            queue.enqueue((Ticket) temporary.dequeue());
        }
    }

    //Method to read the input file
    public static void readFile(Queue ticketQ) throws Exception 
    {
        try         // Try block to handle potential exceptions during file reading and processing
        {
            FileReader fr = new FileReader("TicketInput.txt");      // Open a FileReader to read from "TicketInput.txt"
            BufferedReader br = new BufferedReader(fr);             // Create a BufferedReader to efficiently read characters from FileReader
                
            StringTokenizer st= null;                   // Initialize StringTokenizer to null
            String dataRow = br.readLine();             //Read the first line from the file
                
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
                Ticket ticket = new Ticket (ticketNum, date,zone,category, cust);
                    
                ticketQ.enqueue(ticket);        //Add Ticket object into ticketQ 
                dataRow = br.readLine();        // Read the next line from the file
            }
            br.close();              // Close the BufferedReader after processing the file
        }catch (Exception e) {//error handling for input file
            e.printStackTrace(); // Print any exceptions that occur during file reading and processing
        }
    }   
}