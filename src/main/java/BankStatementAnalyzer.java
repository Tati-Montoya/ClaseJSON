import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

public class BankStatementAnalyzer {
    private static final String RESOURCES = "src/main/resources/";

    public SummaryStatistics analyze(String filename, BankStatementParser parser) throws IOException {
        Path path = Paths.get(RESOURCES + filename);
        List<String> lines = Files.readAllLines(path);

        List<BankTransaction> transactions = parser.parseLines(lines);
        BankStatementProcessor processor = new BankStatementProcessor(transactions);

        /**
         * new BankTransactionFilter() {
         *                     @Override
         *                     public boolean test (BankTransaction transaction) {
         *          *          *                         boolean isFeb = transaction.getDate().getMonth().equals(Month.FEBRUARY);
         *          *          *                         boolean isExpensive = transaction.getAmount() >= 1000;
         *          *          *                         return isFeb && isExpensive;
         *          *          *                     }
         *                 }
         *
         *
         *
         */

        List<BankTransaction> expensiveTransactions = processor
                .findTransactions(bankTransaction -> {
                    boolean isFeb = bankTransaction.getDate().getMonth().equals(Month.FEBRUARY);
                    boolean isExpensive = bankTransaction.getAmount().getValue() >= 1000;
                    return isFeb && isExpensive;
                });



        // Stream API
        List<BankTransaction> expensiveTransactionsStream = transactions
                .stream()
                .filter(bankTransaction -> {
                    boolean isFeb = bankTransaction.getDate().getMonth().equals(Month.FEBRUARY);
                    boolean isExpensive = bankTransaction.getAmount().getValue() >= 1000;
                    return isFeb && isExpensive;
                })
                .collect(Collectors.toList());

        List<BankTransaction> cheapTransactions = processor.findTransactions(transaction -> {
            return transaction.getAmount().getValue() < 500;
        });

        return collectSummary(processor);
    }

    public static SummaryStatistics collectSummary(BankStatementProcessor bankStatementProcessor) {
        return new SummaryStatistics(
                bankStatementProcessor.calculateTotal(),
                0,
                0,
                0
        );
    }
}
