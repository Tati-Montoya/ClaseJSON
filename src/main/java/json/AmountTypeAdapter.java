package json;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.util.Currency;
import java.util.Set;

public class AmountTypeAdapter implements JsonSerializer<Currency>, JsonDeserializer<Set<Currency>> {
    private final Set<Currency> AMOUNT = Currency.getAvailableCurrencies();

    @Override
    public JsonElement serialize(Currency src, Type typeOfSrc, JsonSerializationContext context) {
        String amountAsDouble = AMOUNT.toString();
        return new JsonPrimitive(amountAsDouble);
    }

    @Override
    public Set<Currency> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        double asDouble = json.getAsDouble();
        return Currency.getAvailableCurrencies();
    }
}
