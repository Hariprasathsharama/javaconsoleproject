
import java.sql.*;
import java.util.*;

public class ManageUsers {
    Scanner input = new Scanner(System.in);
    InputValidation validation = new InputValidation();
    private final ArrayList<CandidateRegisteration> candidateList = new ArrayList<>();
    public void User() throws SQLException {
        userPage();
        byte option = 0;
        System.out.println("Enter the option");
        option = input.nextByte();
        switch (option) {
            case 1:
                login();
                break;
            case 2:
                createUser();
                break;
            case 3:
                break;
            default:
                System.out.println("print valid number");
        }
    }

    public void createUser() throws SQLException {
        System.out.println("Enter your name");
        String name = validation.validName();
        System.out.println("Enter your age");
        byte age = validation.validAge();
        System.out.println("Enter the gender");
        String gender = validation.validGender();
        System.out.println("Enter the year of Gradution");
        int graduatedYear = validation.validYear();
        System.out.println("Choose the degree");
        String degreeGraduate = validation.degrees();
        System.out.println("Choose the roles");
        String roles = validation.roles();
        System.out.println("Enter the Email");
        String email = validation.isvalidMail();
        System.out.println("Enter the password");
        String passwords = input.next();
        candidateList.add(
                new CandidateRegisteration(name, age, gender, graduatedYear, degreeGraduate, roles, email, passwords, null));
        updateDatabase();
        System.out.println(" you are successfully registered ");
        System.out.println("Return to login page");
        new ManageUsers().login();
    }

    public static void userPage() {
        System.out.println();
        System.out.println("Enter the Login/Sign up");
        System.out.println("1) Login");
        System.out.println("2) Sign up");
        System.out.println("3) EXIT");
    }

    public void login() throws SQLException {

        System.out.println("Enter the email");
        String email = input.next();
        System.out.println("Enter the password");
        String password = input.next();

        if (isVerify(email, password)) {
            System.out.println("You successfully logged in");
            showStatus();
        } else {
            System.out.println("your account not detected");
            loginFailure();
        }
    }

    public boolean isVerify(String mail, String pass) throws SQLException {
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/candidatetable", "root", "Chrisevans@2309");
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery("select email,passwords from candidateslist");
        while (resultSet.next()) {
            if (resultSet.getString(1).equals(mail) && resultSet.getString(2).equals(pass)) {
                return true;
            }
        }
        return false;
    }

    public void showStatus() {
        try (Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/candidatetable", "root", "Chrisevans@2309")) {
            java.sql.Statement statement = con.createStatement();
            ResultSet resultset = statement.executeQuery("select email,passwords,status from candidateslist");
            System.out.println("Check the status");
            System.out.println("Enter the mail");
            String mail = input.next();
            System.out.println("Enter the password");
            String password = input.next();
            while (resultset.next()) {
                try {
                    if (resultset.getString(1).equals(mail) && resultset.getString(2).equals(password)) {

                        if (resultset.getString(3).equals("Selected")) {
                            System.out.println("congratulations! your application is shortlisted");
                            System.out.println("we will contact you later");
                            break;
                        } else if (resultset.getString(3).equals("Rejected")) {
                            System.out.println("Sorry! your profile is not eligible for this role");
                            System.out.println("Hope you get a good carrer");
                        } else {
                            System.out.println("you application is still pending");
                            new RecruitManagementApplication().choice();
                            break;
                        }
                    }
                } catch (NullPointerException e) {
                    System.out.println("application is still pending");
                    break;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void loginFailure() throws SQLException {
        byte option = 0;
        System.out.println("1) Register new user");
        System.out.println("2) Relogin");
        System.out.println("Enter the option");
        while (true) {
            try {
                option = input.nextByte();
            } catch (InputMismatchException e) {
                System.out.println("please enter valid option");
            }
            switch (option) {
                case 1:
                    createUser();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    break;
            }
        }
    }

    public void updateDatabase() throws SQLException {
        String sql = "INSERT INTO candidateslist"
                + "(name,age,gender,graduated_year,degreegraudate,roles,email,passwords,status) values "
                + "(?,?,?,?,?,?,?,?,?);";
        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/candidatetable", "root",
                        "Chrisevans@2309");
                PreparedStatement preparedstatement = connection.prepareStatement(sql)) {

            for (Iterator<CandidateRegisteration> iterator = candidateList.iterator(); iterator.hasNext(); ) {
                CandidateRegisteration candidate = (CandidateRegisteration) iterator.next();
                preparedstatement.setString(1, candidate.getName());
                preparedstatement.setByte(2, candidate.getAge());
                preparedstatement.setString(3, candidate.getGender());
                preparedstatement.setInt(4, candidate.getGraduatedYear());
                preparedstatement.setString(5, candidate.getDegree());
                preparedstatement.setString(6, candidate.getRole());
                preparedstatement.setString(7, candidate.getEmail());
                preparedstatement.setString(8, candidate.getPassword());
                preparedstatement.setString(9, candidate.getStatus());
                preparedstatement.addBatch();
            }
            int[] count = preparedstatement.executeBatch();
            System.out.println(Arrays.toString(count));
        }
    }

}