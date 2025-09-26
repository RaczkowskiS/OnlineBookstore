package json;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.time.Instant;

public final class InstantTypeAdapter implements JsonSerializer<Instant>, JsonDeserializer<Instant> {
	
	@Override
	public JsonElement serialize(Instant src, Type typeOfSrc, JsonSerializationContext ctx) {
		return new JsonPrimitive(src.toString());
	}
	
	@Override
	public Instant deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext ctx) throws JsonParseException {
		return Instant.parse(json.getAsString());
	}
}

