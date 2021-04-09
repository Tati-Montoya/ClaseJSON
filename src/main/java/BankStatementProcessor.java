import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
   // Metodo que filtra en rango de fechas
    public List<BankTransaction> filterByDate (String fechaInicial, String fechaFinal){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        LocalDate fechaIni = LocalDate.parse(fechaInicial, formatter);
        LocalDate fechaFin = LocalDate.parse(fechaFinal, formatter);

        long numOfDaysBetween = ChronoUnit.DAYS.between(fechaIni, fechaFin.plusDays(1));
        List<BankTransaction> transactionFiltradas = new ArrayList<BankTransaction>();
        List rangeDates = IntStream.iterate(0, i -> i + 1)
                .limit(numOfDaysBetween)
                .mapToObj(i -> fechaIni.plusDays(i))
                .collect(Collectors.toList());
        for(int i = 0; i < this.bankTransactions.size(); i++) {
            boolean found = rangeDates.contains(this.bankTransactions.get(i).getDate());
            if(found) {
                transactionFiltradas.add(this.bankTransactions.get(i));
            }
        }
        return transactionFiltradas;
    }

    public double calculateMaxForDate() {
        List<BankTransaction> filterAmount = filterByDate( "2017-02-02", "2017-10-02");
        double max = 0;
        for (BankTransaction transaction: filterAmount) {
            if (transaction.getAmount().getValue() > max){
                max = transaction.getAmount().getValue();
            }
        }
        System.out.println(max);
        return max;
    }

    public double calculateMinForDate() {
        List<BankTransaction> filterAmount = filterByDate( "2017-02-02", "2017-10-02");
        double min = 0;
        for (BankTransaction transaction: filterAmount) {
            if (transaction.getAmount().getValue() < min){
                min = transaction.getAmount().getValue();
            }
        }
        System.out.println(min);
        return min;
    }

}
