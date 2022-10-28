import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ManageUser {
    Scanner input = new Scanner(System.in);
    BankDatabase database=new BankDatabase();
    public void addCustomer() throws SQLException {
        System.out.println("Enter your name");
        String name = new UserInputValidation().validName();
        System.out.println("Enter your Mobilenumber");
        String mobile = new UserInputValidation().validPhonenumber();
        System.out.println("Enter your password");
        String password = input.nextLine();
        int balance=1000;
        new BankAdmin().createAccountnumber(name, mobile, password,balance);
        login();

    }

    public void login() throws SQLException {
        System.out.println("```````````````````````````````");
        System.out.println("LOGIN PAGE");
        System.out.println("```````````````````````````````");
        System.out.println("Please enter your login details:");
        System.out.println("Enter your username: ");
        String accountnumber = input.next();
        System.out.println("Enter your password");
        String passwords = input.next();
        if (database.isaccountVerify(accountnumber, passwords)) {
            bankprocess();
        } else {
            System.out.println("------------------------------");
            System.out.println("USERNAME OR PASSWORD IS WRONG");
            System.out.println("------------------------------");
            System.out.println("Enter your Details Correctly");
            login();
        }

    }

    public void bankprocess() throws SQLException {
        new UserDetails().toString();//show account number
        byte selection = 0;
        System.out.println("Please select an option");
        System.out.println("1) Balance Enquiry");
        System.out.println("2) WITHDRAWAL");
        System.out.println("3) Deposit");
        System.out.println("4) Exit");
        System.out.print("Your selection: ");
        while (true) {

            try {
                 input = new Scanner(System.in);
                selection = input.nextByte();
            } catch (InputMismatchException e) {
                System.out.println("Enter valid option");

            }

            switch (selection) {
                case 1: {
                     database.checkBalance();
                    break;
                }
                case 2: {
                     database.withdraw();
                    break;
                }
                case 3:{
                     database.deposit();
                    break;
                }
                case 4:{
                    return;
                }
                default:
                    System.out.println("Enter the correct option");
            }
        }
    }
}
