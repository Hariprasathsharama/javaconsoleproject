import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

class BankApplication {

    Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {

        BankApplication bankingApplication = new BankApplication();
        bankingApplication.welcomePage();
        bankingApplication.userChoice();
    }

    public void welcomePage() {
        System.out.println("-------WELCOME TO INDIAN BANK--------");
        System.out.println("Choose one of  the option");
        System.out.println("1) SIGN UP");
        System.out.println("2) LOGIN");
        System.out.println("3) EXIT");
    }

    public void userChoice() throws SQLException {

        System.out.println("Enter one of the option");
        Byte option = 0;

        WHILE_LABLE: while (true) {

            try {
                option = input.nextByte();

                if (option < 1 || option > 3)
                    System.out.println("ENTER VALID NUMBER : ");
                else
                    break WHILE_LABLE;
            } catch (InputMismatchException e) {
                input.next();
                System.out.println("Enter valid entry");

            }
        }
        switch (option) {
            case 1:
                new ManageUser().addCustomer();

                break;
            case 2:
                new ManageUser().login();
                break;
            default:
                break;
        }

    }
}