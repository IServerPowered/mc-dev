package net.minecraft.server;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.mojang.authlib.GameProfile;
import java.lang.reflect.Type;
import java.util.UUID;

public class ServerPing {

    private IChatBaseComponent a;
    private ServerPing.a b;
    private ServerPing.c c;
    private String d;

    public ServerPing() {}

    public IChatBaseComponent a() {
        return this.a;
    }

    public void setMOTD(IChatBaseComponent ichatbasecomponent) {
        this.a = ichatbasecomponent;
    }

    public ServerPing.a b() {
        return this.b;
    }

    public void setPlayerSample(ServerPing.a serverping_a) {
        this.b = serverping_a;
    }

    public ServerPing.c c() {
        return this.c;
    }

    public void setServerInfo(ServerPing.c serverping_c) {
        this.c = serverping_c;
    }

    public void setFavicon(String s) {
        this.d = s;
    }

    public String d() {
        return this.d;
    }

    public static class b implements JsonDeserializer<ServerPing>, JsonSerializer<ServerPing> {

        public b() {}

        public ServerPing a(JsonElement jsonelement, Type type, JsonDeserializationContext jsondeserializationcontext) throws JsonParseException {
            JsonObject jsonobject = ChatDeserializer.l(jsonelement, "status");
            ServerPing serverping = new ServerPing();

            if (jsonobject.has("description")) {
                serverping.setMOTD((IChatBaseComponent) jsondeserializationcontext.deserialize(jsonobject.get("description"), IChatBaseComponent.class));
            }

            if (jsonobject.has("players")) {
                serverping.setPlayerSample((ServerPing.a) jsondeserializationcontext.deserialize(jsonobject.get("players"), ServerPing.a.class));
            }

            if (jsonobject.has("version")) {
                serverping.setServerInfo((ServerPing.c) jsondeserializationcontext.deserialize(jsonobject.get("version"), ServerPing.c.class));
            }

            if (jsonobject.has("favicon")) {
                serverping.setFavicon(ChatDeserializer.h(jsonobject, "favicon"));
            }

            return serverping;
        }

        public JsonElement a(ServerPing serverping, Type type, JsonSerializationContext jsonserializationcontext) {
            JsonObject jsonobject = new JsonObject();

            if (serverping.a() != null) {
                jsonobject.add("description", jsonserializationcontext.serialize(serverping.a()));
            }

            if (serverping.b() != null) {
                jsonobject.add("players", jsonserializationcontext.serialize(serverping.b()));
            }

            if (serverping.c() != null) {
                jsonobject.add("version", jsonserializationcontext.serialize(serverping.c()));
            }

            if (serverping.d() != null) {
                jsonobject.addProperty("favicon", serverping.d());
            }

            return jsonobject;
        }

        public JsonElement serialize(Object object, Type type, JsonSerializationContext jsonserializationcontext) {
            return this.a((ServerPing) object, type, jsonserializationcontext);
        }

        public Object deserialize(JsonElement jsonelement, Type type, JsonDeserializationContext jsondeserializationcontext) throws JsonParseException {
            return this.a(jsonelement, type, jsondeserializationcontext);
        }
    }

    public static class c {

        private final String a;
        private final int b;

        public c(String s, int i) {
            this.a = s;
            this.b = i;
        }

        public String a() {
            return this.a;
        }

        public int b() {
            return this.b;
        }

        public static class a implements JsonDeserializer<ServerPing.c>, JsonSerializer<ServerPing.c> {

            public a() {}

            public ServerPing.c a(JsonElement jsonelement, Type type, JsonDeserializationContext jsondeserializationcontext) throws JsonParseException {
                JsonObject jsonobject = ChatDeserializer.l(jsonelement, "version");

                return new ServerPing.c(ChatDeserializer.h(jsonobject, "name"), ChatDeserializer.m(jsonobject, "protocol"));
            }

            public JsonElement a(ServerPing.c serverping_c, Type type, JsonSerializationContext jsonserializationcontext) {
                JsonObject jsonobject = new JsonObject();

                jsonobject.addProperty("name", serverping_c.a());
                jsonobject.addProperty("protocol", (Number) Integer.valueOf(serverping_c.b()));
                return jsonobject;
            }

            public JsonElement serialize(Object object, Type type, JsonSerializationContext jsonserializationcontext) {
                return this.a((ServerPing.c) object, type, jsonserializationcontext);
            }

            public Object deserialize(JsonElement jsonelement, Type type, JsonDeserializationContext jsondeserializationcontext) throws JsonParseException {
                return this.a(jsonelement, type, jsondeserializationcontext);
            }
        }
    }

    public static class a {

        private final int a;
        private final int b;
        private GameProfile[] c;

        public a(int i, int j) {
            this.a = i;
            this.b = j;
        }

        public int a() {
            return this.a;
        }

        public int b() {
            return this.b;
        }

        public GameProfile[] c() {
            return this.c;
        }

        public void a(GameProfile[] agameprofile) {
            this.c = agameprofile;
        }

        public static class a implements JsonDeserializer<ServerPing.a>, JsonSerializer<ServerPing.a> {

            public a() {}

            public ServerPing.a a(JsonElement jsonelement, Type type, JsonDeserializationContext jsondeserializationcontext) throws JsonParseException {
                JsonObject jsonobject = ChatDeserializer.l(jsonelement, "players");
                ServerPing.a serverping_a = new ServerPing.a(ChatDeserializer.m(jsonobject, "max"), ChatDeserializer.m(jsonobject, "online"));

                if (ChatDeserializer.d(jsonobject, "sample")) {
                    JsonArray jsonarray = ChatDeserializer.t(jsonobject, "sample");

                    if (jsonarray.size() > 0) {
                        GameProfile[] agameprofile = new GameProfile[jsonarray.size()];

                        for (int i = 0; i < agameprofile.length; ++i) {
                            JsonObject jsonobject1 = ChatDeserializer.l(jsonarray.get(i), "player[" + i + "]");
                            String s = ChatDeserializer.h(jsonobject1, "id");

                            agameprofile[i] = new GameProfile(UUID.fromString(s), ChatDeserializer.h(jsonobject1, "name"));
                        }

                        serverping_a.a(agameprofile);
                    }
                }

                return serverping_a;
            }

            public JsonElement a(ServerPing.a serverping_a, Type type, JsonSerializationContext jsonserializationcontext) {
                JsonObject jsonobject = new JsonObject();

                jsonobject.addProperty("max", (Number) Integer.valueOf(serverping_a.a()));
                jsonobject.addProperty("online", (Number) Integer.valueOf(serverping_a.b()));
                if (serverping_a.c() != null && serverping_a.c().length > 0) {
                    JsonArray jsonarray = new JsonArray();

                    for (int i = 0; i < serverping_a.c().length; ++i) {
                        JsonObject jsonobject1 = new JsonObject();
                        UUID uuid = serverping_a.c()[i].getId();

                        jsonobject1.addProperty("id", uuid == null ? "" : uuid.toString());
                        jsonobject1.addProperty("name", serverping_a.c()[i].getName());
                        jsonarray.add(jsonobject1);
                    }

                    jsonobject.add("sample", jsonarray);
                }

                return jsonobject;
            }

            public JsonElement serialize(Object object, Type type, JsonSerializationContext jsonserializationcontext) {
                return this.a((ServerPing.a) object, type, jsonserializationcontext);
            }

            public Object deserialize(JsonElement jsonelement, Type type, JsonDeserializationContext jsondeserializationcontext) throws JsonParseException {
                return this.a(jsonelement, type, jsondeserializationcontext);
            }
        }
    }
}
