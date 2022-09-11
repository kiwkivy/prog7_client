package serializers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import data.Coordinates;

import java.lang.reflect.Type;

public class CoordinatesSerializer implements JsonSerializer<Coordinates> {

    @Override
    public JsonElement serialize(Coordinates coordinates, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject serCoordinates = new JsonObject();
        serCoordinates.addProperty("x", coordinates.getX());
        serCoordinates.addProperty("y", coordinates.getY());
        return serCoordinates;
    }
}
