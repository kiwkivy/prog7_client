package serializers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import data.DragonCave;

import java.lang.reflect.Type;

public class CaveSerializer implements JsonSerializer<DragonCave> {

    @Override
    public JsonElement serialize(DragonCave dragonCave, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject serCave = new JsonObject();
        serCave.addProperty("depth", dragonCave.getDepth());
        serCave.addProperty("numberOfTreasures", dragonCave.getNumberOfTreasures());
        return serCave;
    }
}
