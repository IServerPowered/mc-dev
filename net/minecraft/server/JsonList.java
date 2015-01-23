package net.minecraft.server;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JsonList {

    protected static final Logger a = LogManager.getLogger();
    protected final Gson b;
    private final File c;
    private final Map d = Maps.newHashMap();
    private boolean e = true;
    private static final ParameterizedType f = new JsonListType();

    public JsonList(File file) {
        this.c = file;
        GsonBuilder gsonbuilder = (new GsonBuilder()).setPrettyPrinting();

        gsonbuilder.registerTypeHierarchyAdapter(JsonListEntry.class, new JsonListEntrySerializer(this, (JsonListType) null));
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

    public void add(JsonListEntry jsonlistentry) {
        this.d.put(this.a(jsonlistentry.getKey()), jsonlistentry);

        try {
            this.save();
        } catch (IOException ioexception) {
            JsonList.a.warn("Could not save the list after adding a user.", (Throwable) ioexception);
        }

    }

    public JsonListEntry get(Object object) {
        this.h();
        return (JsonListEntry) this.d.get(this.a(object));
    }

    public void remove(Object object) {
        this.d.remove(this.a(object));

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

    protected String a(Object object) {
        return object.toString();
    }

    protected boolean d(Object object) {
        return this.d.containsKey(this.a(object));
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

    protected JsonListEntry a(JsonObject jsonobject) {
        return new JsonListEntry((Object) null, jsonobject);
    }

    protected Map e() {
        return this.d;
    }

    public void save() {
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

    public void load() {
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
}
