import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InputValidation {
    private Scanner input = new Scanner(System.in);
    private ArrayList<String> degrees = new ArrayList<>(Arrays.asList("B.SC", "B.E", "M.SC", "M.E"));
    private ArrayList<String> jobroles = new ArrayList<>(
            Arrays.asList("PROGRAMMER", "WEB-DEVELOPER", "ANALYST", "DESIGNER"));

    public String validName() {
        String name = "";
        while (true) {
            name = input.next();
            String regex = "[a-zA-Z]+\\.?";
            if (name.matches(regex)) {
                return name;
            } else {
                System.out.println("Enter the valid username");
            }
        }

    }

    public String isvalidMail() {

        while (true) {
            String email = input.next();
            String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
            if (email.matches(regex)) {
                return email;
            } else {
                System.out.println("Enter the valid mail");
            }
        }
    }

    public String validGender() {
        System.out.println("Choose the given below");
        System.out.println("1) Male");
        System.out.println("2) Female");
        while (true) {
            byte option = 0;
            try {
                option = input.nextByte();
            } catch (InputMismatchException e) {

                System.out.println("Please enter numeric values");
                input.nextLine();
            }
            if (option == 1) {
                return "male";
            } else if (option == 2) {
                return "female";
            } else {
                System.out.println("----------------------");
                System.out.println("Enter the valid option");
                System.out.println("----------------------");

            }
        }
    }

    public Byte validAge() throws SQLException {
        byte age;
        WHILE_LABEL:

        while (true) {
            try {
                age = input.nextByte();
                if (age > 19 && age < 35) {
                    break WHILE_LABEL;
                } else {
                    System.out.println("You not eligible for this application");
                    System.out.println("Retrun to home page");
                    new ManageUsers().createUser();
                }
            } catch (InputMismatchException e) {
                System.out.println("Non-valid input");
                input.nextLine();
            }
        }
        return age;
    }

    public int validYear() throws SQLException {
        while (true) {
            int year = 0;
            try {
                year = input.nextInt();
            } catch (InputMismatchException e) {
                input.nextLine();
                System.out.println("please enter valid option");
            }
            if (year > 2017 && year <= 2022) {
                return year;
            } else {
                System.out.println("you are eligible for this application");
                System.out.println("Return to home page");
                new ManageUsers().createUser();
            }
        }
    }

    public String degrees() {
        int j = 1;
        byte option = 0;
        for (String i : degrees) {
            System.out.println(j + ")" + " " + i);
            j++;
        }
        while (true) {
            try {
                option = input.nextByte();
            } catch (InputMismatchException e) {
                System.out.println("please enter valid option");
                input.nextLine();
            }
            switch (option) {
                case 1:
                    return degrees.get(1);
                case 2:
                    return degrees.get(2);
                case 3:
                    return degrees.get(3);
                case 4:
                    return degrees.get(4);
                default:
                    System.out.println("Enter valid  option");
            }
        }
    }

    public String roles() {
        byte option = 0;
        int j = 1;
        for (String i : jobroles) {
            System.out.println(j + ")" + " " + i);
            j++;
        }
        while (true) {
            try {
                option = input.nextByte();
            } catch (InputMismatchException e) {
                System.out.println("please enter numeric values");
                input.nextLine();
            }
            switch (option) {
                case 1:
                    return jobroles.get(0);
                case 2:
                    return jobroles.get(1);
                case 3:
                    return jobroles.get(2);
                case 4:
                    return jobroles.get(3);
            }
            System.out.println("Enter valid option");
        }
    }
}
