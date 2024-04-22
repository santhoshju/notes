import java.util.*;
public class Main
{
   static Scanner sc = new Scanner(System.in);
 
public static void book(Flight currentflight, int t, int passengerId){
   if(t> currentflight.tickets){
       System.out.println("Ticket is not available");
       return;
   }
   
   int price = currentflight.price;
   price = t * price;
   String pasenger ="Passenger_id: "+ passengerId+ "---"+" no of booked tickets: "+ t + "---" +" price: "+ price;
   System.out.println(pasenger);
   currentflight.addPassenger(pasenger, t, passengerId);
   currentflight.summary();
   currentflight.print();

}

public static void cancel(Flight currentflight, int id){
   
  currentflight.cancelTicket(id);
   
}

public static void main(String[] args) {
   
   ArrayList<Flight> flight  = new ArrayList<>();
   
   for(int i=0;i<2;i++){
       flight.add(new Flight());
   }
  int passengerId =1;
 
while(true){
   System.out.println("1. Book 2. Cancel 3. Summary 4. Exit");
   
   int choice = sc.nextInt();
   
   switch(choice){
     case 1 :
       {
           System.out.println("Enter the flight id");
        int fid = sc.nextInt();
        if(fid > flight.size()){
            System.out.println("please enter the valid flight id");
            break;
        }
       
        Flight currentflight = null;
       
        for(Flight f : flight){
            if(f.flightId==fid){
                currentflight = f;
                break;
            }
        }
        System.out.println("Enter the no of tickets: ");
        int t = sc.nextInt();
        book(currentflight, t, passengerId);
       
        passengerId++;
           break;
       }    
           
     case 2 :{
       System.out.println("Enter the flight id and passenger id to cancel the ticket");
       int fid = sc.nextInt();
       
       if(fid> flight.size()){
           System.out.println("Enter the valid flight id");
           break;
       }
       
       Flight currentflight = null;
       for(Flight f : flight){
           if(f.flightId == fid){
               currentflight = f;
           }
       }
       
       int Id = sc.nextInt();
       cancel(currentflight, Id);
         break;
         
     }
         
     case 3 :
   //   print();
         break;
     case 4 :
         return ;
     default :
     System.out.println("Please Enter The valid choice");
     break;
   }
}

}



}


import java.util.*;
public class Flight{
   
    static  int id=1;
    int tickets;
     int price;
     int flightId;
    ArrayList <String> passengerDetails;
    ArrayList<Integer> pass_Id;
    ArrayList<Integer> cost;
    ArrayList<Integer> tic;
   
    public Flight(){
        tickets =5;
        price = 5000;
        flightId = id++;
        passengerDetails = new ArrayList<String>();
        pass_Id = new ArrayList<Integer>();
        cost= new ArrayList<Integer>();
        tic = new ArrayList<Integer>();
    }
   
    public void addPassenger(String passenger, int t, int passid){
       
        passengerDetails.add(passenger); //booked passenger details
       
        pass_Id.add(passid);
       
        tic.add(t);
       
        cost.add(price*t);
        tickets = tickets-t;
        price = price+(200*t);
        System.out.println("booked successfully");
       
    }
   
    public void cancelTicket(int passengerId){
       
        int indexof_remove = pass_Id.indexOf(passengerId);
        int cancel_tic = tic.get(indexof_remove);
        tickets = tickets + cancel_tic;
        price = price - 200 * cancel_tic;
       
        passengerDetails.remove(indexof_remove);
        pass_Id.remove(indexof_remove);
        tic.remove(indexof_remove);
        cost.remove(indexof_remove);
       
        System.out.println("Cancel ticket successfully");
    }
   
    public void summary(){
       
        System.out.println("Flight id: "+ flightId +" Remaing tickets: "+ tickets+ " current price: "+ price);
       
    }
   
    public void print(){
       
        for(String details : passengerDetails){
            System.out.println(details);
        }
    }
   
}
