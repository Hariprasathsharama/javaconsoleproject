package RailwayReservation;

import java.util.Scanner;

public class Singleton {
         static Scanner scan = new Scanner(System.in);
         static PassengerBooking passengerBooking = new PassengerBooking();

        public static Scanner getScannerInstance(){
            return scan;
        }
        public static PassengerBooking getPassengerUtilInstance(){
            return passengerBooking;
        }
}
