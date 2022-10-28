import java.sql.SQLException;
import java.util.Random;

public class BankAdmin {
    public void createAccountnumber(String name,String mobileNumber,String password,int balance) throws SQLException {
        String accountNumber;
        Random random=new Random();
        int length=6;
            accountNumber="";
            for (int i = 0; i < length; i++) {
                accountNumber+=((Integer)random.nextInt(10)).toString();
            }

         new BankDatabase().updatetoDatabase(name, mobileNumber, accountNumber, password,balance);
        System.out.println("your account number is"+accountNumber);
    }

    
}

