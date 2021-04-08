import domain.Amount;

import java.time.LocalDate;

public class Test {
    public static void main(String[] args) {
        LocalDate now = LocalDate.now();
        BankTransaction b1 = new BankTransaction(now, new Amount(100d), "Starbucks");
        BankTransaction b2 = new BankTransaction(now, new Amount(100d), "Starbucks");
        boolean isEqual = b1.equals(b2);

        System.out.println("isEqual = " + isEqual);
    }
}
