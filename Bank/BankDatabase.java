import java.sql.*;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BankDatabase {

    Scanner input=new Scanner(System.in);
    private final String DB_URL = "jdbc:mysql://localhost/Railwayreservation";
    private final String USER = "root";
    private final String PASS = "Chrisevans@2309";

    private static BankDatabase database = null;
    public void updatetoDatabase(String name, String number, String account, String password,int balance) throws SQLException {
            Connection connection=DriverManager.getConnection(DB_URL,USER,PASS);
            String sql = "Insert into bank" + "(name,number,account,password,balance) values"
                    + "(?,?,?,?,?);";
            PreparedStatement preparedstatement = connection.prepareStatement(sql);
            preparedstatement.setString(1, name);
            preparedstatement.setString(2, number);
            preparedstatement.setString(3, account);
            preparedstatement.setString(4, password);
            preparedstatement.setInt(5,balance);
            preparedstatement.addBatch();
            int[] count = preparedstatement.executeBatch();
            System.out.println(Arrays.toString(count));
        }

    public boolean isaccountVerify(String accountnumber,String password) throws SQLException {
              Connection connection=DriverManager.getConnection(DB_URL,USER,PASS);
            Statement statement = connection.createStatement();
            ResultSet resultset = statement.executeQuery("select account,password from bank");
            int flag = 0;
            while (resultset.next()) {
                if (resultset.getString(1).equals(accountnumber) && resultset.getString(2).equals(password)) {
                    return true;

                }
            }
        return false;
        }
    public void checkBalance() throws SQLException  {
        Connection connection=DriverManager.getConnection(DB_URL,USER,PASS);
        Statement statement = connection.createStatement();
        System.out.println("Enter the account number");
        String accountNumber=input.next();
        ResultSet resultSet = statement.executeQuery("select account,balance from bank");
        while (resultSet.next()) {
            if (resultSet.getString(1).equals(accountNumber)) {
                System.out.println("The balance amount is"+resultSet.getString(2));
                new ManageUser().bankprocess();
            }

        }
        System.out.println("Your account number not exsits");
        new ManageUser().login();

    }
    public void deposit() throws SQLException {
        System.out.println("Enter your accountnumber");
        String accountnumber = input.next();
        System.out.println("Enter your password");
        String password=input.next();
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Bankapplication", "root", "Chrisevans@2309");
            java.sql.Statement stmt = con.createStatement();
            ResultSet resultset = stmt.executeQuery("select account,password,balance from bank");
            int exsistedamount = 0;
            int newamount = 0;
            while (resultset.next()) {
                if (accountnumber.equals(resultset.getString(1)) && password.equals(resultset.getString(2))) {
                    System.out.print("Enter the amount to deposit :");
                    int amount = input.nextInt();
                    exsistedamount = resultset.getInt(3);
                    newamount = exsistedamount + amount;
                    System.out.println(newamount + "  rupees " + "deposited successfully");
                    stmt.executeUpdate(
                            "update bank set balance=" + newamount + " where account = " + accountnumber + " ");
                    new ManageUser().bankprocess();
                }

            }
            System.out.println("No account found");
            deposit();
        }

    public void withdraw() throws SQLException {
        Connection connection=DriverManager.getConnection(DB_URL,USER,PASS);
        Statement statement = connection.createStatement();
            System.out.println("Enter the account number");
            String accountNumber=input.next();
            ResultSet resultSet = statement.executeQuery("select account,balance from bank");
            int withdrawamount = 0;
            while (resultSet.next()) {

                if (accountNumber.equals(resultSet.getString(1))) {
                    System.out.print("Enter the amount to withdraw :");
                    int amount = input.nextInt();

                    int currentamount = resultSet.getInt(2);

                    if (amount < currentamount) {
                        withdrawamount = currentamount - amount;
                        System.out.println("Your new balance is " + withdrawamount);
                        statement.executeUpdate(
                                "update bank set balance=" + " " + withdrawamount + " " + "where account="
                                        + accountNumber + " ");
                        new ManageUser().bankprocess();
                        
                    } else {
                        System.out.println("Insufficient balance");
                        new ManageUser().login();
                    }
                }
            }

            System.out.println("No account found");
            withdraw();
        }

    }

        

