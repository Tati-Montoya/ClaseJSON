import com.google.gson.Gson;

public class JsonExporter implements Exporter {
    private final Gson gson = new Gson();

    @Override
    public String export(SummaryStatistics summaryStatistics) {
        return gson.toJson(summaryStatistics);
    }
}
