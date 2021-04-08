import java.time.Month;

public class BankTransactionIsFebruaryAndExpensive implements BankTransactionFilter {

    @Override
    public boolean test(BankTransaction transaction) {
        return transaction.getDate().getMonth().equals(Month.FEBRUARY) && transaction.getAmount().getValue() >= 1000;
    }
}
