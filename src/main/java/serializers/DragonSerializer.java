package serializers;

import com.google.gson.*;
import data.Color;
import data.Dragon;

import java.lang.reflect.Type;

public class DragonSerializer implements JsonSerializer<Dragon> {

    @Override
    public JsonElement serialize(Dragon dragon, Type type, JsonSerializationContext jsonSerializationContext) {
        if (dragon!= null) {
            JsonObject serDragon = new JsonObject();
            serDragon.addProperty("name", dragon.getName());
            serDragon.addProperty("age", dragon.getAge());
            serDragon.addProperty("color", dragon.getColor().getName());
            serDragon.addProperty("type", dragon.getType().getName());
            serDragon.addProperty("character", dragon.getCharacter().getName());
            serDragon.add("coordinates", jsonSerializationContext.serialize(dragon.getCoordinates()));
            serDragon.add("cave", jsonSerializationContext.serialize(dragon.getCave()));
            return serDragon;
        }
        return new JsonNull();
    }
}
