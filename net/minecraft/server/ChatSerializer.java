package net.minecraft.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Map.Entry;

public class ChatSerializer implements JsonDeserializer, JsonSerializer {

    private static final Gson a;

    public IChatBaseComponent a(JsonElement jsonelement, Type type, JsonDeserializationContext jsondeserializationcontext) {
        if (jsonelement.isJsonPrimitive()) {
            return new ChatComponentText(jsonelement.getAsString());
        } else if (!jsonelement.isJsonObject()) {
            if (jsonelement.isJsonArray()) {
                JsonArray jsonarray = jsonelement.getAsJsonArray();
                IChatBaseComponent ichatbasecomponent = null;
                Iterator iterator = jsonarray.iterator();

                while (iterator.hasNext()) {
                    JsonElement jsonelement1 = (JsonElement) iterator.next();
                    IChatBaseComponent ichatbasecomponent1 = this.a(jsonelement1, (Type) jsonelement1.getClass(), jsondeserializationcontext);

                    if (ichatbasecomponent == null) {
                        ichatbasecomponent = ichatbasecomponent1;
                    } else {
                        ichatbasecomponent.addSibling(ichatbasecomponent1);
                    }
                }

                return ichatbasecomponent;
            } else {
                throw new JsonParseException("Don\'t know how to turn " + jsonelement.toString() + " into a Component");
            }
        } else {
            JsonObject jsonobject = jsonelement.getAsJsonObject();
            Object object;

            if (jsonobject.has("text")) {
                object = new ChatComponentText(jsonobject.get("text").getAsString());
            } else if (jsonobject.has("translate")) {
                String s = jsonobject.get("translate").getAsString();

                if (jsonobject.has("with")) {
                    JsonArray jsonarray1 = jsonobject.getAsJsonArray("with");
                    Object[] aobject = new Object[jsonarray1.size()];

                    for (int i = 0; i < aobject.length; ++i) {
                        aobject[i] = this.a(jsonarray1.get(i), type, jsondeserializationcontext);
                        if (aobject[i] instanceof ChatComponentText) {
                            ChatComponentText chatcomponenttext = (ChatComponentText) aobject[i];

                            if (chatcomponenttext.getChatModifier().g() && chatcomponenttext.a().isEmpty()) {
                                aobject[i] = chatcomponenttext.g();
                            }
                        }
                    }

                    object = new ChatMessage(s, aobject);
                } else {
                    object = new ChatMessage(s, new Object[0]);
                }
            } else if (jsonobject.has("score")) {
                JsonObject jsonobject1 = jsonobject.getAsJsonObject("score");

                if (!jsonobject1.has("name") || !jsonobject1.has("objective")) {
                    throw new JsonParseException("A score component needs a least a name and an objective");
                }

                object = new ChatComponentScore(ChatDeserializer.h(jsonobject1, "name"), ChatDeserializer.h(jsonobject1, "objective"));
                if (jsonobject1.has("value")) {
                    ((ChatComponentScore) object).b(ChatDeserializer.h(jsonobject1, "value"));
                }
            } else {
                if (!jsonobject.has("selector")) {
                    throw new JsonParseException("Don\'t know how to turn " + jsonelement.toString() + " into a Component");
                }

                object = new ChatComponentSelector(ChatDeserializer.h(jsonobject, "selector"));
            }

            if (jsonobject.has("extra")) {
                JsonArray jsonarray2 = jsonobject.getAsJsonArray("extra");

                if (jsonarray2.size() <= 0) {
                    throw new JsonParseException("Unexpected empty array of components");
                }

                for (int j = 0; j < jsonarray2.size(); ++j) {
                    ((IChatBaseComponent) object).addSibling(this.a(jsonarray2.get(j), type, jsondeserializationcontext));
                }
            }

            ((IChatBaseComponent) object).setChatModifier((ChatModifier) jsondeserializationcontext.deserialize(jsonelement, ChatModifier.class));
            return (IChatBaseComponent) object;
        }
    }

    private void a(ChatModifier chatmodifier, JsonObject jsonobject, JsonSerializationContext jsonserializationcontext) {
        JsonElement jsonelement = jsonserializationcontext.serialize(chatmodifier);

        if (jsonelement.isJsonObject()) {
            JsonObject jsonobject1 = (JsonObject) jsonelement;
            Iterator iterator = jsonobject1.entrySet().iterator();

            while (iterator.hasNext()) {
                Entry entry = (Entry) iterator.next();

                jsonobject.add((String) entry.getKey(), (JsonElement) entry.getValue());
            }
        }

    }

    public JsonElement a(IChatBaseComponent ichatbasecomponent, Type type, JsonSerializationContext jsonserializationcontext) {
        if (ichatbasecomponent instanceof ChatComponentText && ichatbasecomponent.getChatModifier().g() && ichatbasecomponent.a().isEmpty()) {
            return new JsonPrimitive(((ChatComponentText) ichatbasecomponent).g());
        } else {
            JsonObject jsonobject = new JsonObject();

            if (!ichatbasecomponent.getChatModifier().g()) {
                this.a(ichatbasecomponent.getChatModifier(), jsonobject, jsonserializationcontext);
            }

            if (!ichatbasecomponent.a().isEmpty()) {
                JsonArray jsonarray = new JsonArray();
                Iterator iterator = ichatbasecomponent.a().iterator();

                while (iterator.hasNext()) {
                    IChatBaseComponent ichatbasecomponent1 = (IChatBaseComponent) iterator.next();

                    jsonarray.add(this.a(ichatbasecomponent1, (Type) ichatbasecomponent1.getClass(), jsonserializationcontext));
                }

                jsonobject.add("extra", jsonarray);
            }

            if (ichatbasecomponent instanceof ChatComponentText) {
                jsonobject.addProperty("text", ((ChatComponentText) ichatbasecomponent).g());
            } else if (ichatbasecomponent instanceof ChatMessage) {
                ChatMessage chatmessage = (ChatMessage) ichatbasecomponent;

                jsonobject.addProperty("translate", chatmessage.i());
                if (chatmessage.j() != null && chatmessage.j().length > 0) {
                    JsonArray jsonarray1 = new JsonArray();
                    Object[] aobject = chatmessage.j();
                    int i = aobject.length;

                    for (int j = 0; j < i; ++j) {
                        Object object = aobject[j];

                        if (object instanceof IChatBaseComponent) {
                            jsonarray1.add(this.a((IChatBaseComponent) object, (Type) object.getClass(), jsonserializationcontext));
                        } else {
                            jsonarray1.add(new JsonPrimitive(String.valueOf(object)));
                        }
                    }

                    jsonobject.add("with", jsonarray1);
                }
            } else if (ichatbasecomponent instanceof ChatComponentScore) {
                ChatComponentScore chatcomponentscore = (ChatComponentScore) ichatbasecomponent;
                JsonObject jsonobject1 = new JsonObject();

                jsonobject1.addProperty("name", chatcomponentscore.g());
                jsonobject1.addProperty("objective", chatcomponentscore.h());
                jsonobject1.addProperty("value", chatcomponentscore.getText());
                jsonobject.add("score", jsonobject1);
            } else {
                if (!(ichatbasecomponent instanceof ChatComponentSelector)) {
                    throw new IllegalArgumentException("Don\'t know how to serialize " + ichatbasecomponent + " as a Component");
                }

                ChatComponentSelector chatcomponentselector = (ChatComponentSelector) ichatbasecomponent;

                jsonobject.addProperty("selector", chatcomponentselector.g());
            }

            return jsonobject;
        }
    }

    public static String a(IChatBaseComponent ichatbasecomponent) {
        return ChatSerializer.a.toJson((Object) ichatbasecomponent);
    }

    public static IChatBaseComponent a(String s) {
        return (IChatBaseComponent) ChatSerializer.a.fromJson(s, IChatBaseComponent.class);
    }

    public JsonElement serialize(Object object, Type type, JsonSerializationContext jsonserializationcontext) {
        return this.a((IChatBaseComponent) object, type, jsonserializationcontext);
    }

    public Object deserialize(JsonElement jsonelement, Type type, JsonDeserializationContext jsondeserializationcontext) {
        return this.a(jsonelement, type, jsondeserializationcontext);
    }

    static {
        GsonBuilder gsonbuilder = new GsonBuilder();

        gsonbuilder.registerTypeHierarchyAdapter(IChatBaseComponent.class, new ChatSerializer());
        gsonbuilder.registerTypeHierarchyAdapter(ChatModifier.class, new ChatModifierSerializer());
        gsonbuilder.registerTypeAdapterFactory(new ChatTypeAdapterFactory());
        a = gsonbuilder.create();
    }
}
