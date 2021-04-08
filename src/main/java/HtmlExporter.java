public class HtmlExporter implements Exporter {
    @Override
    public String export(SummaryStatistics summaryStatistics) {
        String result = "<!doctype html>";
        result += "<html lang='es'>";
        result += "<head><title>Bank Transactions</title></head>";
        result += "<body>";
        result += "<ul>";
        result += "<li><strong>La suma es:</strong>" + summaryStatistics.getSum() + "</li>";
        result += "<li><strong>La minima es:</strong>" + summaryStatistics.getMin() + "</li>";
        result += "<li><strong>La máxima es:</strong>" + summaryStatistics.getMax() + "</li>";
        result += "<li><strong>El promedio es:</strong>" + summaryStatistics.getAverage() + "</li>";
        result += "</ul>";
        result += "</body>";
        result += "</html>";
        return result;
    }
}
