package net.minecraft.server;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JsonList<K, V extends JsonListEntry<K>> {

    protected static final Logger a = LogManager.getLogger();
    protected final Gson b;
    private final File c;
    private final Map<String, V> d = Maps.newHashMap();
    private boolean e = true;
    private static final ParameterizedType f = new ParameterizedType() {
        public Type[] getActualTypeArguments() {
            return new Type[] { JsonListEntry.class};
        }

        public Type getRawType() {
            return List.class;
        }

        public Type getOwnerType() {
            return null;
        }
    };

    public JsonList(File file) {
        this.c = file;
        GsonBuilder gsonbuilder = (new GsonBuilder()).setPrettyPrinting();

        gsonbuilder.registerTypeHierarchyAdapter(JsonListEntry.class, new JsonList.a(null));
        this.b = gsonbuilder.create();
    }

    public boolean isEnabled() {
        return this.e;
    }

    public void a(boolean flag) {
        this.e = flag;
    }

    public File c() {
        return this.c;
    }

    public void add(V v0) {
        this.d.put(this.a(v0.getKey()), v0);

        try {
            this.save();
        } catch (IOException ioexception) {
            JsonList.a.warn("Could not save the list after adding a user.", (Throwable) ioexception);
        }

    }

    public V get(K k0) {
        this.h();
        return (JsonListEntry) this.d.get(this.a(k0));
    }

    public void remove(K k0) {
        this.d.remove(this.a(k0));

        try {
            this.save();
        } catch (IOException ioexception) {
            JsonList.a.warn("Could not save the list after removing a user.", (Throwable) ioexception);
        }

    }

    public String[] getEntries() {
        return (String[]) this.d.keySet().toArray(new String[this.d.size()]);
    }

    public boolean isEmpty() {
        return this.d.size() < 1;
    }

    protected String a(K k0) {
        return k0.toString();
    }

    protected boolean d(K k0) {
        return this.d.containsKey(this.a(k0));
    }

    private void h() {
        ArrayList arraylist = Lists.newArrayList();
        Iterator iterator = this.d.values().iterator();

        while (iterator.hasNext()) {
            JsonListEntry jsonlistentry = (JsonListEntry) iterator.next();

            if (jsonlistentry.hasExpired()) {
                arraylist.add(jsonlistentry.getKey());
            }
        }

        iterator = arraylist.iterator();

        while (iterator.hasNext()) {
            Object object = iterator.next();

            this.d.remove(object);
        }

    }

    protected JsonListEntry<K> a(JsonObject jsonobject) {
        return new JsonListEntry((Object) null, jsonobject);
    }

    protected Map<String, V> e() {
        return this.d;
    }

    public void save() throws IOException {
        Collection collection = this.d.values();
        String s = this.b.toJson((Object) collection);
        BufferedWriter bufferedwriter = null;

        try {
            bufferedwriter = Files.newWriter(this.c, Charsets.UTF_8);
            bufferedwriter.write(s);
        } finally {
            IOUtils.closeQuietly((Writer) bufferedwriter);
        }

    }

    public void load() throws FileNotFoundException {
        Collection collection = null;
        BufferedReader bufferedreader = null;

        try {
            bufferedreader = Files.newReader(this.c, Charsets.UTF_8);
            collection = (Collection) this.b.fromJson((Reader) bufferedreader, (Type) JsonList.f);
        } finally {
            IOUtils.closeQuietly((Reader) bufferedreader);
        }

        if (collection != null) {
            this.d.clear();
            Iterator iterator = collection.iterator();

            while (iterator.hasNext()) {
                JsonListEntry jsonlistentry = (JsonListEntry) iterator.next();

                if (jsonlistentry.getKey() != null) {
                    this.d.put(this.a(jsonlistentry.getKey()), jsonlistentry);
                }
            }
        }

    }

    class a implements JsonDeserializer<JsonListEntry<K>>, JsonSerializer<JsonListEntry<K>> {

        private a() {}

        public JsonElement a(JsonListEntry<K> jsonlistentry, Type type, JsonSerializationContext jsonserializationcontext) {
            JsonObject jsonobject = new JsonObject();

            jsonlistentry.a(jsonobject);
            return jsonobject;
        }

        public JsonListEntry<K> a(JsonElement jsonelement, Type type, JsonDeserializationContext jsondeserializationcontext) throws JsonParseException {
            if (jsonelement.isJsonObject()) {
                JsonObject jsonobject = jsonelement.getAsJsonObject();
                JsonListEntry jsonlistentry = JsonList.this.a(jsonobject);

                return jsonlistentry;
            } else {
                return null;
            }
        }

        public JsonElement serialize(Object object, Type type, JsonSerializationContext jsonserializationcontext) {
            return this.a((JsonListEntry) object, type, jsonserializationcontext);
        }

        public Object deserialize(JsonElement jsonelement, Type type, JsonDeserializationContext jsondeserializationcontext) throws JsonParseException {
            return this.a(jsonelement, type, jsondeserializationcontext);
        }

        a(Object object) {
            this();
        }
    }
}
