import java.util.List;

public interface BankStatementParser {
    BankTransaction parseFromString(String line);

    List<BankTransaction> parseLines(List<String> lines);
}
