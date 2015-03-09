package net.minecraft.server;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

public class GameRules {

    private TreeMap<String, GameRules.a> a = new TreeMap();

    public GameRules() {
        this.a("doFireTick", "true", GameRules.b.BOOLEAN_VALUE);
        this.a("mobGriefing", "true", GameRules.b.BOOLEAN_VALUE);
        this.a("keepInventory", "false", GameRules.b.BOOLEAN_VALUE);
        this.a("doMobSpawning", "true", GameRules.b.BOOLEAN_VALUE);
        this.a("doMobLoot", "true", GameRules.b.BOOLEAN_VALUE);
        this.a("doTileDrops", "true", GameRules.b.BOOLEAN_VALUE);
        this.a("doEntityDrops", "true", GameRules.b.BOOLEAN_VALUE);
        this.a("commandBlockOutput", "true", GameRules.b.BOOLEAN_VALUE);
        this.a("naturalRegeneration", "true", GameRules.b.BOOLEAN_VALUE);
        this.a("doDaylightCycle", "true", GameRules.b.BOOLEAN_VALUE);
        this.a("logAdminCommands", "true", GameRules.b.BOOLEAN_VALUE);
        this.a("showDeathMessages", "true", GameRules.b.BOOLEAN_VALUE);
        this.a("randomTickSpeed", "3", GameRules.b.NUMERICAL_VALUE);
        this.a("sendCommandFeedback", "true", GameRules.b.BOOLEAN_VALUE);
        this.a("reducedDebugInfo", "false", GameRules.b.BOOLEAN_VALUE);
    }

    public void a(String s, String s1, GameRules.b gamerules_b) {
        this.a.put(s, new GameRules.a(s1, gamerules_b));
    }

    public void set(String s, String s1) {
        GameRules.a gamerules_a = (GameRules.a) this.a.get(s);

        if (gamerules_a != null) {
            gamerules_a.a(s1);
        } else {
            this.a(s, s1, GameRules.b.ANY_VALUE);
        }

    }

    public String get(String s) {
        GameRules.a gamerules_a = (GameRules.a) this.a.get(s);

        return gamerules_a != null ? gamerules_a.a() : "";
    }

    public boolean getBoolean(String s) {
        GameRules.a gamerules_a = (GameRules.a) this.a.get(s);

        return gamerules_a != null ? gamerules_a.b() : false;
    }

    public int c(String s) {
        GameRules.a gamerules_a = (GameRules.a) this.a.get(s);

        return gamerules_a != null ? gamerules_a.c() : 0;
    }

    public NBTTagCompound a() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        Iterator iterator = this.a.keySet().iterator();

        while (iterator.hasNext()) {
            String s = (String) iterator.next();
            GameRules.a gamerules_a = (GameRules.a) this.a.get(s);

            nbttagcompound.setString(s, gamerules_a.a());
        }

        return nbttagcompound;
    }

    public void a(NBTTagCompound nbttagcompound) {
        Set set = nbttagcompound.c();
        Iterator iterator = set.iterator();

        while (iterator.hasNext()) {
            String s = (String) iterator.next();
            String s1 = nbttagcompound.getString(s);

            this.set(s, s1);
        }

    }

    public String[] getGameRules() {
        Set set = this.a.keySet();

        return (String[]) set.toArray(new String[set.size()]);
    }

    public boolean contains(String s) {
        return this.a.containsKey(s);
    }

    public boolean a(String s, GameRules.b gamerules_b) {
        GameRules.a gamerules_a = (GameRules.a) this.a.get(s);

        return gamerules_a != null && (gamerules_a.e() == gamerules_b || gamerules_b == GameRules.b.ANY_VALUE);
    }

    public static enum b {

        ANY_VALUE, BOOLEAN_VALUE, NUMERICAL_VALUE;

        private b() {}
    }

    static class a {

        private String a;
        private boolean b;
        private int c;
        private double d;
        private final GameRules.b e;

        public a(String s, GameRules.b gamerules_b) {
            this.e = gamerules_b;
            this.a(s);
        }

        public void a(String s) {
            this.a = s;
            this.b = Boolean.parseBoolean(s);
            this.c = this.b ? 1 : 0;

            try {
                this.c = Integer.parseInt(s);
            } catch (NumberFormatException numberformatexception) {
                ;
            }

            try {
                this.d = Double.parseDouble(s);
            } catch (NumberFormatException numberformatexception1) {
                ;
            }

        }

        public String a() {
            return this.a;
        }

        public boolean b() {
            return this.b;
        }

        public int c() {
            return this.c;
        }

        public GameRules.b e() {
            return this.e;
        }
    }
}
