import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CompanyAdmin {
    Scanner input = new Scanner(System.in);

    public void adminChoice() throws Exception {
        byte option = 0;
        choiceList();
        WHILE_LABEL:
        while (true) {
            try {
                option = input.nextByte();
                if (option < 1 || option > 3) {
                    System.out.println("Enter valid number");
                } else {
                    break WHILE_LABEL;
                }
            } catch (InputMismatchException e) {
                System.out.println("Non-Valid option");
                input.nextLine();
            }
        }
        switch (option) {
            case 1:
                viewStatus();
                break;
            case 2:
                updateStatus();
                break;
            case 3:
                break;
        }
    }

    public void choiceList() {
        System.out.println("Enter the choice");
        System.out.println("1) View the status");
        System.out.println("2) Update the status");
    }

    public void viewStatus() throws Exception {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/candidatetable", "root", "Chrisevans@2309");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select name,age,degreegraudate,graduated_year,roles from candidateslist");
        System.out.println("The candidates List");
        while (resultSet.next()) {
            System.out.println("Name-->" + resultSet.getString(1) + "Age-->" + resultSet.getInt(2) + "Degree-->" + resultSet.getString(3) + "Year of graduation-->" + resultSet.getString(4) + "Role-->" + resultSet.getString(5));
        }
        adminChoice();
    }

    public void updateStatus() throws Exception {
        int id = 0, choice = 0;
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/candidatetable", "root", "Chrisevans@2309");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select id ,name from candidateslist");
        while (resultSet.next()) {
            System.out.println("ID-> " + resultSet.getInt(1) + " " + "NAME-> " + resultSet.getString(2));
        }
        System.out.println("1) Hire the candidate");
        System.out.println("2) Reject the candidate");
        while (true) {
            try {
                System.out.println("Enter the id ");
                id = input.nextInt();
                System.out.println("Enter the choice");
                choice = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("enter numeric value");
                input.nextLine();
                updateStatus();
            }

            switch (choice) {
                case 1:
                    statement.executeUpdate("update candidateslist set status='Selected' where id=" + id + "");
                    System.out.println("Candidate profile is updated");
                    new RecruitManagementApplication().choice();
                    return;
                case 2:
                    statement.executeUpdate("update candidateslist set status='Rejected' where id=" + id + "");
                    System.out.println("Candidate profile is updated");
                    new RecruitManagementApplication().choice();
                    return;
                case 3:
                    break;
            }
        }
    }
}