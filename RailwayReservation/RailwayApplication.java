package RailwayReservation;

import java.util.ArrayList;

public class RailwayApplication {

    static ArrayList<Passenger> passengerArrayList = new ArrayList<>();
    public static void main(String[] args) {
        System.out.println("Welcome to Railway Application");
        homePage();
    }

    public static void homePage() {
        int option = 0;
        System.out.println("1) Booking");
        System.out.println("2) Print Book Tickets");
        System.out.println("3) Cancel Ticket");
        System.out.println("4) Exit");
        System.out.println("Enter your Option");

        try{
            option = Singleton.getScannerInstance().nextInt();
        }catch(Exception ex){
            System.out.println("Type of Exception - "+ex);
            System.out.println("Please, Enter the correct Option");
            homePage();
        }
        switch (option)
        {
            case 1:
            {
                Singleton.getPassengerUtilInstance().booking();
                break;
            }
            case 2:
            {
                Singleton.getPassengerUtilInstance().printTickets(passengerArrayList);
                break;
            }
            case 3:
            {
                Singleton.getPassengerUtilInstance().cancelTicket(passengerArrayList);
                break;
            }
            case 4:
            {
                break;
            }
        }
    }
}
