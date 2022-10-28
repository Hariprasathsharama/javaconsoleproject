package RailwayReservation;

import java.util.ArrayList;

public class PassengerBooking {

    public void booking(){
        int total = 0;
        System.out.println("How Many ticket you want ?");
        total = Singleton.getScannerInstance().nextInt();

        for(int i = 0; i < total; i++){
            Singleton.getScannerInstance().nextLine();
            System.out.println("Enter the - "+(i+1)+" person name");
            String name = Singleton.getScannerInstance().nextLine();
            System.out.println("Enter your age");
            int age = Singleton.getScannerInstance().nextInt();
            System.out.println("Select your Gender");
            System.out.println("1) Male");
            System.out.println("2) Female");
            System.out.println("Enter your option");
            int option = Singleton.getScannerInstance().nextInt();
            switch (option) {
                case 1 -> {
                    String gender = "Male";
                    RailwayApplication.passengerArrayList.add(new Passenger(name, age, gender));
                    break;
                }
                case 2 -> {
                    String gender = "Female";
                    RailwayApplication.passengerArrayList.add(new Passenger(name, age, gender));
                    break;
                }
            }
        }
        allotBerth(RailwayApplication.passengerArrayList);
    }

    static int ticket = 1;
    static int count = 0;
    public void allotBerth(ArrayList<Passenger> passengerArrayList) {
        boolean result = false;
        int ch = 0;
         for(int i = count; i < passengerArrayList.size(); i++){
             if(!result){
                 ch = count;
                 result = true;
             }
             count++;
             if(ticket >= 6 && ticket <= 8 ){
                 passengerArrayList.get(i).berth = "Side_Lower";
                 passengerArrayList.get(i).ticket = ticket++;
             }
             else if(ticket >=9 && ticket <= 10 ){
                 passengerArrayList.get(i).berth = "Waiting_List";
                 passengerArrayList.get(i).ticket = ticket++;
             }
             else if (ticket >= 11){
                 System.out.println("Not available Ticket");
             }
            else if(passengerArrayList.get(i).age >= 60){
                 passengerArrayList.get(i).berth = "Lower_Berth";
                 passengerArrayList.get(i).ticket = ticket++;
             }
             else if(passengerArrayList.get(i).age > 3 && passengerArrayList.get(i).age < 60){
                 passengerArrayList.get(i).berth = "Upper_Berth";
                 passengerArrayList.get(i).ticket = ticket++;
             }
             else if(passengerArrayList.get(i).age <= 3){
                  for(int j = ch; j < passengerArrayList.size(); j++){
                      if(passengerArrayList.get(j).gender.equals("Female")){
                            passengerArrayList.get(j).berth = "Lower_Berth";
                            passengerArrayList.get(i).berth = "Join_Berth";
                      }
                  }
             }
         }
         RailwayApplication.homePage();
    }

    public void printTickets(ArrayList<Passenger> passengerArrayList){
        System.out.println("Ticket Number \t\t Name \t\t Age");
        for(int i = 0; i < passengerArrayList.size(); i++){
            System.out.println(passengerArrayList.get(i).ticket + " "+passengerArrayList.get(i).name+" "+passengerArrayList.get(i).berth);
        }
        System.out.println();
        System.out.println();
        RailwayApplication.homePage();
    }

    public void cancelTicket(ArrayList<Passenger> passengerArrayList){
        String conformBerth = "";
        //String racBerth = "";
        System.out.println("Enter your Ticket Number");
        int ticketNumber = Singleton.getScannerInstance().nextInt();
        for(int i = 0; i < passengerArrayList.size(); i++){
            if(ticketNumber == passengerArrayList.get(i).ticket){
                conformBerth = passengerArrayList.get(i).berth;
                passengerArrayList.remove(i);
                System.out.println("Ticket is Successfully Canceled");
                break;
            }
        }
        for(int i = 0; i < passengerArrayList.size(); i++){
            if(passengerArrayList.get(i).berth.equals("Side_Lower")){
                passengerArrayList.get(i).berth = conformBerth;
                break;
            }
        }
        for(int i = 0; i < passengerArrayList.size(); i++){
            if(passengerArrayList.get(i).berth.equals("Waiting_List")){
                passengerArrayList.get(i).berth = "Side_Lower";
                break;
            }
        }
        RailwayApplication.homePage();
    }
}
