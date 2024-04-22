import java.util.*;
public class Main
{
    public static Scanner sc = new Scanner(System.in);
    public static void book(int id, char pickup, char drop, int pickup_time, List<Taxi>freetaxi){
       
        Taxi booktaxi = null;
       
        int min =1000;
        int minDistance=0;
        int FindminDistance=0;
        int earning =0;
        char nextspot ='\0';
        int nextfreetime=0;
       
        for(Taxi t : freetaxi){
             FindminDistance = Math.abs((t.currentspot - '0') - (pickup-'0'))*15;
            if(FindminDistance<min){
                booktaxi=t;
            minDistance = Math.abs((pickup - '0') - (drop- '0'))*15;
            earning = (minDistance-5)*10 +100;
            nextfreetime =Math.abs((pickup - '0') - (drop- '0')) + pickup_time;
            nextspot = drop;
            min = FindminDistance;
           
            }
        }
       
       
        String details =minDistance+ "customerID: " + id + "   pickup point: " + pickup + "   drop point: "+drop + "  pickup time : "+
        pickup_time +"  drop time: "+ nextfreetime +"   total Earnings: "+ booktaxi.earnings+earning;
                         
                          System.out.println("taxi "+ id + " Booked");
       
        booktaxi.setdetails(nextspot, nextfreetime, booktaxi.earnings + earning, details);
       
       
    }
   
   
   
   
   
public static void main(String[] args) {
   
   ArrayList<Taxi> taxis = new ArrayList<>();
   
   for(int i=0;i<4;i++){
       taxis.add(new Taxi());
   }
   int id =1;
   
   while(true){
      // System.out.println("Enter the choice");
       System.out.println("1. Book 2. print");
       int choice = sc.nextInt();
       
       switch(choice){
           case 1:{
               System.out.println("Enter the pickup point");
               char pickup = sc.next().charAt(0);
               System.out.println("Enter the drop point");
               char drop = sc.next().charAt(0);
               System.out.println("Enter the pick up time");
               int pickup_time = sc.nextInt();
               
               if(pickup <'A' && pickup>'F' && drop<'A' && drop >'F'){
                   System.out.println("enter the valid pickup and drop point");
                   return ;
               }
               
               ArrayList<Taxi> freetaxi = new ArrayList<>();
               
               for(Taxi t : taxis){
                   if(t.freetime<=pickup_time && ((Math.abs(t.currentspot -'0') - (pickup-'0')) <= (pickup_time - t.freetime))){
                       freetaxi.add(t);
                   }
               }
               
               if(freetaxi.size()==0){
                   System.out.println("No taxi is allocated");
                   return ;
               }
               Collections.sort (freetaxi, (a,b) -> a.earnings - b.earnings);
               
               book(id, pickup,drop,pickup_time,freetaxi);
               id++;
               
               break;
           }
           case 2:{
               for(Taxi t : taxis){
                   t.printdetails();
               }
               break;
           }
       }
   }

}
}




import java.util.*;

public class Taxi{
    Scanner sc = new Scanner(System.in);
   
    static int id = 1;
    int taxiId;
    boolean booked;
    char currentspot;
    int freetime;
    int earnings;
    ArrayList<String> trips;
   
    public Taxi(){
            taxiId = id;
            booked = false;
            currentspot = 'A';
            freetime = 6;
            earnings = 0;
            trips = new ArrayList<>();
    }
   
    public void setdetails(char currentspot, int freetime, int earnings,String details){
        this.currentspot = currentspot;
        this.freetime = freetime;
        this. earnings = earnings;
        trips.add(details);
    }
   
    public void printdetails(){
        for(int i=0;i<trips.size();i++){
            System.out.println(trips.get(i));
        }
    }
   
}
