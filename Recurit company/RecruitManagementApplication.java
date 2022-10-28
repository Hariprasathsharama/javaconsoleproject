
import java.util.InputMismatchException;
import java.util.Scanner;

public class RecruitManagementApplication {
    public static void main(String[] args) throws Exception {
        RecruitManagementApplication recruitManagementApplication = new RecruitManagementApplication();
        recruitManagementApplication.choice();
    }

    public void WelcomePage() {
        System.out.println("-------RECRUIT MANAGEMENT--------");
        System.out.println("Enter the option");
        System.out.println("1) ADMIN");
        System.out.println("2) USER");
        System.out.println("3) EXIT");
    }

    public void choice() throws Exception {
        byte option = 0;
        WelcomePage();
        while (true) {
            try {
                option = new Scanner(System.in).nextByte();
            } catch (InputMismatchException e) {
                System.out.println("Non-Valid option");
                new Scanner(System.in).next();
            }
            switch (option) {
                case 1:
                    new CompanyAdmin().adminChoice();
                    break;
                case 2:
                    new ManageUsers().User();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Enter given choice");

            }
        }
    }
}
