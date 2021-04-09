import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class BankStatementProcessor {
    private final List<BankTransaction> bankTransactions;

    public BankStatementProcessor(List<BankTransaction> bankTransactions) {
        this.bankTransactions = bankTransactions;
    }

    public double calculateTotal() {
        return summarizeTransactions((accumulator, transaction) -> accumulator + transaction.getAmount().getValue());
    }

    public double calculateAverage() {
        return calculateTotal()/ bankTransactions.size();
    }

    public double calculateMin() {
        double min = 0;
        for (BankTransaction transaction: bankTransactions) {
            if (transaction.getAmount().getValue() < min){
                min = transaction.getAmount().getValue();
            }
        }
        return min;
    }

    public double calculateMax() {
        double max = 0;
        for (BankTransaction transaction: bankTransactions) {
            if (transaction.getAmount().getValue() > max){
                max = transaction.getAmount().getValue();
            }
        }
        return max;
    }

    public double calculateTotalForMonth(Month month) {
        return summarizeTransactions((accumulator, transaction) -> {
            if (transaction.getDate().getMonth() == month) {
                return accumulator + transaction.getAmount().getValue();
            } else {
                return accumulator;
            }
        });
    }

    public double calculateForCategory(String category) {
        return summarizeTransactions((accumulator, transaction) -> {
            boolean isSameDescription = transaction.getDescription().equals(category);
            double transactionAmount = transaction.getAmount().getValue();
            return isSameDescription ? transactionAmount + accumulator : accumulator;
        });
    }


    public double summarizeTransactions(BankTransactionSummarizer summarizer) {
        double total = 0d;
        for (BankTransaction transaction : bankTransactions) {
            total = summarizer.summarize(total, transaction);
        }
        return total;
    }

    public List<BankTransaction> findTransactions(BankTransactionFilter filter) {
        List<BankTransaction> result = new ArrayList<>();
        for (BankTransaction transaction : bankTransactions) {
            boolean passEvaluation = filter.test(transaction);
            if (passEvaluation) {
                result.add(transaction);
            }
        }
        return result;
    }
}
