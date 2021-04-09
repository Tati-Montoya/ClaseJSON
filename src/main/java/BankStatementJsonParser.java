import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import domain.Amount;
import json.AmountTypeAdapter;
import json.LocalDateTypeAdapter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BankStatementJsonParser implements BankStatementParser {
    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
            .registerTypeAdapter(Amount.class, new AmountTypeAdapter())
            .create();

    @Override
    public BankTransaction parseFromString(String line) {
        return gson.fromJson(line, BankTransaction.class);
    }

    @Override
    public List<BankTransaction> parseLines(List<String> lines) {
        List<BankTransaction> bankTransactions = new ArrayList<>();
        for (String line : lines) {
            BankTransaction bankTransaction = parseFromString(line);
            bankTransactions.add(bankTransaction);
        }
        return bankTransactions;
    }
}
