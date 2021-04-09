package json;

import com.google.gson.*;
import domain.Amount;
import java.lang.reflect.Type;

public class AmountTypeAdapter implements JsonSerializer<Amount>, JsonDeserializer<Amount> {

    @Override
    public JsonElement serialize(Amount src, Type typeOfSrc, JsonSerializationContext context) {
        double amountAsDouble = src.getValue();
        return new JsonPrimitive(amountAsDouble);
    }

    @Override
    public Amount deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        double asDouble = json.getAsDouble();
        return new Amount(asDouble);
    }
}
