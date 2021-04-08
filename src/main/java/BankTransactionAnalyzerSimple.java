import java.io.IOException;

public class BankTransactionAnalyzerSimple {

    public static void main(String[] args) throws IOException {
        BankStatementAnalyzer analyzer = new BankStatementAnalyzer();

        BankStatementParser bankStatementCSVParser = new BankStatementCSVParser();
        BankStatementParser jsonParser = new BankStatementJsonParser();
        String fileName = "data.jsonl";

        SummaryStatistics summaryStatistics = analyzer.analyze(fileName, jsonParser);

        Exporter exporter = new JsonExporter();
        Exporter htmlExporter = new HtmlExporter();

        String json = exporter.export(summaryStatistics);
        String html = htmlExporter.export(summaryStatistics);

        System.out.println("json = " + json);
        System.out.println("html = " + html);
    }



}
