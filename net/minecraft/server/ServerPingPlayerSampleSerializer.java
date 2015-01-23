package net.minecraft.server;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.mojang.authlib.GameProfile;
import java.lang.reflect.Type;
import java.util.UUID;

public class ServerPingPlayerSampleSerializer implements JsonDeserializer, JsonSerializer {

    public ServerPingPlayerSample a(JsonElement jsonelement, Type type, JsonDeserializationContext jsondeserializationcontext) {
        JsonObject jsonobject = ChatDeserializer.l(jsonelement, "players");
        ServerPingPlayerSample serverpingplayersample = new ServerPingPlayerSample(ChatDeserializer.m(jsonobject, "max"), ChatDeserializer.m(jsonobject, "online"));

        if (ChatDeserializer.d(jsonobject, "sample")) {
            JsonArray jsonarray = ChatDeserializer.t(jsonobject, "sample");

            if (jsonarray.size() > 0) {
                GameProfile[] agameprofile = new GameProfile[jsonarray.size()];

                for (int i = 0; i < agameprofile.length; ++i) {
                    JsonObject jsonobject1 = ChatDeserializer.l(jsonarray.get(i), "player[" + i + "]");
                    String s = ChatDeserializer.h(jsonobject1, "id");

                    agameprofile[i] = new GameProfile(UUID.fromString(s), ChatDeserializer.h(jsonobject1, "name"));
                }

                serverpingplayersample.a(agameprofile);
            }
        }

        return serverpingplayersample;
    }

    public JsonElement a(ServerPingPlayerSample serverpingplayersample, Type type, JsonSerializationContext jsonserializationcontext) {
        JsonObject jsonobject = new JsonObject();

        jsonobject.addProperty("max", (Number) Integer.valueOf(serverpingplayersample.a()));
        jsonobject.addProperty("online", (Number) Integer.valueOf(serverpingplayersample.b()));
        if (serverpingplayersample.c() != null && serverpingplayersample.c().length > 0) {
            JsonArray jsonarray = new JsonArray();

            for (int i = 0; i < serverpingplayersample.c().length; ++i) {
                JsonObject jsonobject1 = new JsonObject();
                UUID uuid = serverpingplayersample.c()[i].getId();

                jsonobject1.addProperty("id", uuid == null ? "" : uuid.toString());
                jsonobject1.addProperty("name", serverpingplayersample.c()[i].getName());
                jsonarray.add(jsonobject1);
            }

            jsonobject.add("sample", jsonarray);
        }

        return jsonobject;
    }

    public JsonElement serialize(Object object, Type type, JsonSerializationContext jsonserializationcontext) {
        return this.a((ServerPingPlayerSample) object, type, jsonserializationcontext);
    }

    public Object deserialize(JsonElement jsonelement, Type type, JsonDeserializationContext jsondeserializationcontext) {
        return this.a(jsonelement, type, jsondeserializationcontext);
    }
}
