package net.minecraft.server;

import com.google.common.base.Charsets;
import com.google.common.collect.Iterators;
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
import com.mojang.authlib.Agent;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.ProfileLookupCallback;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import org.apache.commons.io.IOUtils;

public class UserCache {

    public static final SimpleDateFormat a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
    private final Map<String, UserCache.a> c = Maps.newHashMap();
    private final Map<UUID, UserCache.a> d = Maps.newHashMap();
    private final LinkedList<GameProfile> e = Lists.newLinkedList();
    private final MinecraftServer f;
    protected final Gson b;
    private final File g;
    private static final ParameterizedType h = new ParameterizedType() {
        public Type[] getActualTypeArguments() {
            return new Type[] { UserCache.a.class};
        }

        public Type getRawType() {
            return List.class;
        }

        public Type getOwnerType() {
            return null;
        }
    };

    public UserCache(MinecraftServer minecraftserver, File file) {
        this.f = minecraftserver;
        this.g = file;
        GsonBuilder gsonbuilder = new GsonBuilder();

        gsonbuilder.registerTypeHierarchyAdapter(UserCache.a.class, new UserCache.b(null));
        this.b = gsonbuilder.create();
        this.b();
    }

    private static GameProfile a(MinecraftServer minecraftserver, String s) {
        final GameProfile[] agameprofile = new GameProfile[1];
        ProfileLookupCallback profilelookupcallback = new ProfileLookupCallback() {
            public void onProfileLookupSucceeded(GameProfile gameprofile) {
                agameprofile[0] = gameprofile;
            }

            public void onProfileLookupFailed(GameProfile gameprofile, Exception exception) {
                agameprofile[0] = null;
            }
        };

        minecraftserver.getGameProfileRepository().findProfilesByNames(new String[] { s}, Agent.MINECRAFT, profilelookupcallback);
        if (!minecraftserver.getOnlineMode() && agameprofile[0] == null) {
            UUID uuid = EntityHuman.a(new GameProfile((UUID) null, s));
            GameProfile gameprofile = new GameProfile(uuid, s);

            profilelookupcallback.onProfileLookupSucceeded(gameprofile);
        }

        return agameprofile[0];
    }

    public void a(GameProfile gameprofile) {
        this.a(gameprofile, (Date) null);
    }

    private void a(GameProfile gameprofile, Date date) {
        UUID uuid = gameprofile.getId();

        if (date == null) {
            Calendar calendar = Calendar.getInstance();

            calendar.setTime(new Date());
            calendar.add(2, 1);
            date = calendar.getTime();
        }

        String s = gameprofile.getName().toLowerCase(Locale.ROOT);
        UserCache.a usercache_a = new UserCache.a(gameprofile, date, null);

        if (this.d.containsKey(uuid)) {
            UserCache.a usercache_a1 = (UserCache.a) this.d.get(uuid);

            this.c.remove(usercache_a1.a().getName().toLowerCase(Locale.ROOT));
            this.c.put(gameprofile.getName().toLowerCase(Locale.ROOT), usercache_a);
            this.e.remove(gameprofile);
        } else {
            this.d.put(uuid, usercache_a);
            this.c.put(s, usercache_a);
        }

        this.e.addFirst(gameprofile);
    }

    public GameProfile getProfile(String s) {
        String s1 = s.toLowerCase(Locale.ROOT);
        UserCache.a usercache_a = (UserCache.a) this.c.get(s1);

        if (usercache_a != null && (new Date()).getTime() >= usercache_a.c.getTime()) {
            this.d.remove(usercache_a.a().getId());
            this.c.remove(usercache_a.a().getName().toLowerCase(Locale.ROOT));
            this.e.remove(usercache_a.a());
            usercache_a = null;
        }

        GameProfile gameprofile;

        if (usercache_a != null) {
            gameprofile = usercache_a.a();
            this.e.remove(gameprofile);
            this.e.addFirst(gameprofile);
        } else {
            gameprofile = a(this.f, s1);
            if (gameprofile != null) {
                this.a(gameprofile);
                usercache_a = (UserCache.a) this.c.get(s1);
            }
        }

        this.c();
        return usercache_a == null ? null : usercache_a.a();
    }

    public String[] a() {
        ArrayList arraylist = Lists.newArrayList((Iterable) this.c.keySet());

        return (String[]) arraylist.toArray(new String[arraylist.size()]);
    }

    public GameProfile a(UUID uuid) {
        UserCache.a usercache_a = (UserCache.a) this.d.get(uuid);

        return usercache_a == null ? null : usercache_a.a();
    }

    private UserCache.a b(UUID uuid) {
        UserCache.a usercache_a = (UserCache.a) this.d.get(uuid);

        if (usercache_a != null) {
            GameProfile gameprofile = usercache_a.a();

            this.e.remove(gameprofile);
            this.e.addFirst(gameprofile);
        }

        return usercache_a;
    }

    public void b() {
        List list = null;
        BufferedReader bufferedreader = null;

        label64: {
            try {
                bufferedreader = Files.newReader(this.g, Charsets.UTF_8);
                list = (List) this.b.fromJson((Reader) bufferedreader, (Type) UserCache.h);
                break label64;
            } catch (FileNotFoundException filenotfoundexception) {
                ;
            } finally {
                IOUtils.closeQuietly((Reader) bufferedreader);
            }

            return;
        }

        if (list != null) {
            this.c.clear();
            this.d.clear();
            this.e.clear();
            list = Lists.reverse(list);
            Iterator iterator = list.iterator();

            while (iterator.hasNext()) {
                UserCache.a usercache_a = (UserCache.a) iterator.next();

                if (usercache_a != null) {
                    this.a(usercache_a.a(), usercache_a.b());
                }
            }
        }

    }

    public void c() {
        String s = this.b.toJson((Object) this.a(1000));
        BufferedWriter bufferedwriter = null;

        try {
            bufferedwriter = Files.newWriter(this.g, Charsets.UTF_8);
            bufferedwriter.write(s);
            return;
        } catch (FileNotFoundException filenotfoundexception) {
            ;
        } catch (IOException ioexception) {
            return;
        } finally {
            IOUtils.closeQuietly((Writer) bufferedwriter);
        }

    }

    private List<UserCache.a> a(int i) {
        ArrayList arraylist = Lists.newArrayList();
        ArrayList arraylist1 = Lists.newArrayList(Iterators.limit(this.e.iterator(), i));
        Iterator iterator = arraylist1.iterator();

        while (iterator.hasNext()) {
            GameProfile gameprofile = (GameProfile) iterator.next();
            UserCache.a usercache_a = this.b(gameprofile.getId());

            if (usercache_a != null) {
                arraylist.add(usercache_a);
            }
        }

        return arraylist;
    }

    class a {

        private final GameProfile b;
        private final Date c;

        private a(GameProfile gameprofile, Date date) {
            this.b = gameprofile;
            this.c = date;
        }

        public GameProfile a() {
            return this.b;
        }

        public Date b() {
            return this.c;
        }

        a(GameProfile gameprofile, Date date, Object object) {
            this(gameprofile, date);
        }
    }

    class b implements JsonDeserializer<UserCache.a>, JsonSerializer<UserCache.a> {

        private b() {}

        public JsonElement a(UserCache.a usercache_a, Type type, JsonSerializationContext jsonserializationcontext) {
            JsonObject jsonobject = new JsonObject();

            jsonobject.addProperty("name", usercache_a.a().getName());
            UUID uuid = usercache_a.a().getId();

            jsonobject.addProperty("uuid", uuid == null ? "" : uuid.toString());
            jsonobject.addProperty("expiresOn", UserCache.a.format(usercache_a.b()));
            return jsonobject;
        }

        public UserCache.a a(JsonElement jsonelement, Type type, JsonDeserializationContext jsondeserializationcontext) throws JsonParseException {
            if (jsonelement.isJsonObject()) {
                JsonObject jsonobject = jsonelement.getAsJsonObject();
                JsonElement jsonelement1 = jsonobject.get("name");
                JsonElement jsonelement2 = jsonobject.get("uuid");
                JsonElement jsonelement3 = jsonobject.get("expiresOn");

                if (jsonelement1 != null && jsonelement2 != null) {
                    String s = jsonelement2.getAsString();
                    String s1 = jsonelement1.getAsString();
                    Date date = null;

                    if (jsonelement3 != null) {
                        try {
                            date = UserCache.a.parse(jsonelement3.getAsString());
                        } catch (ParseException parseexception) {
                            date = null;
                        }
                    }

                    if (s1 != null && s != null) {
                        UUID uuid;

                        try {
                            uuid = UUID.fromString(s);
                        } catch (Throwable throwable) {
                            return null;
                        }

                        UserCache.a usercache_a = UserCache.this.new a(new GameProfile(uuid, s1), date, null);

                        return usercache_a;
                    } else {
                        return null;
                    }
                } else {
                    return null;
                }
            } else {
                return null;
            }
        }

        public JsonElement serialize(Object object, Type type, JsonSerializationContext jsonserializationcontext) {
            return this.a((UserCache.a) object, type, jsonserializationcontext);
        }

        public Object deserialize(JsonElement jsonelement, Type type, JsonDeserializationContext jsondeserializationcontext) throws JsonParseException {
            return this.a(jsonelement, type, jsondeserializationcontext);
        }

        b(Object object) {
            this();
        }
    }
}
