package net.minecraft.server;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

public class CustomWorldSettingsSerializer implements JsonDeserializer, JsonSerializer {

    public CustomWorldSettings a(JsonElement jsonelement, Type type, JsonDeserializationContext jsondeserializationcontext) {
        JsonObject jsonobject = jsonelement.getAsJsonObject();
        CustomWorldSettings customworldsettings = new CustomWorldSettings();

        try {
            customworldsettings.b = ChatDeserializer.a(jsonobject, "coordinateScale", customworldsettings.b);
            customworldsettings.c = ChatDeserializer.a(jsonobject, "heightScale", customworldsettings.c);
            customworldsettings.e = ChatDeserializer.a(jsonobject, "lowerLimitScale", customworldsettings.e);
            customworldsettings.d = ChatDeserializer.a(jsonobject, "upperLimitScale", customworldsettings.d);
            customworldsettings.f = ChatDeserializer.a(jsonobject, "depthNoiseScaleX", customworldsettings.f);
            customworldsettings.g = ChatDeserializer.a(jsonobject, "depthNoiseScaleZ", customworldsettings.g);
            customworldsettings.h = ChatDeserializer.a(jsonobject, "depthNoiseScaleExponent", customworldsettings.h);
            customworldsettings.i = ChatDeserializer.a(jsonobject, "mainNoiseScaleX", customworldsettings.i);
            customworldsettings.j = ChatDeserializer.a(jsonobject, "mainNoiseScaleY", customworldsettings.j);
            customworldsettings.k = ChatDeserializer.a(jsonobject, "mainNoiseScaleZ", customworldsettings.k);
            customworldsettings.l = ChatDeserializer.a(jsonobject, "baseSize", customworldsettings.l);
            customworldsettings.m = ChatDeserializer.a(jsonobject, "stretchY", customworldsettings.m);
            customworldsettings.n = ChatDeserializer.a(jsonobject, "biomeDepthWeight", customworldsettings.n);
            customworldsettings.o = ChatDeserializer.a(jsonobject, "biomeDepthOffset", customworldsettings.o);
            customworldsettings.p = ChatDeserializer.a(jsonobject, "biomeScaleWeight", customworldsettings.p);
            customworldsettings.q = ChatDeserializer.a(jsonobject, "biomeScaleOffset", customworldsettings.q);
            customworldsettings.r = ChatDeserializer.a(jsonobject, "seaLevel", customworldsettings.r);
            customworldsettings.s = ChatDeserializer.a(jsonobject, "useCaves", customworldsettings.s);
            customworldsettings.t = ChatDeserializer.a(jsonobject, "useDungeons", customworldsettings.t);
            customworldsettings.u = ChatDeserializer.a(jsonobject, "dungeonChance", customworldsettings.u);
            customworldsettings.v = ChatDeserializer.a(jsonobject, "useStrongholds", customworldsettings.v);
            customworldsettings.w = ChatDeserializer.a(jsonobject, "useVillages", customworldsettings.w);
            customworldsettings.x = ChatDeserializer.a(jsonobject, "useMineShafts", customworldsettings.x);
            customworldsettings.y = ChatDeserializer.a(jsonobject, "useTemples", customworldsettings.y);
            customworldsettings.z = ChatDeserializer.a(jsonobject, "useMonuments", customworldsettings.z);
            customworldsettings.A = ChatDeserializer.a(jsonobject, "useRavines", customworldsettings.A);
            customworldsettings.B = ChatDeserializer.a(jsonobject, "useWaterLakes", customworldsettings.B);
            customworldsettings.C = ChatDeserializer.a(jsonobject, "waterLakeChance", customworldsettings.C);
            customworldsettings.D = ChatDeserializer.a(jsonobject, "useLavaLakes", customworldsettings.D);
            customworldsettings.E = ChatDeserializer.a(jsonobject, "lavaLakeChance", customworldsettings.E);
            customworldsettings.F = ChatDeserializer.a(jsonobject, "useLavaOceans", customworldsettings.F);
            customworldsettings.G = ChatDeserializer.a(jsonobject, "fixedBiome", customworldsettings.G);
            if (customworldsettings.G < 38 && customworldsettings.G >= -1) {
                if (customworldsettings.G >= BiomeBase.HELL.id) {
                    customworldsettings.G += 2;
                }
            } else {
                customworldsettings.G = -1;
            }

            customworldsettings.H = ChatDeserializer.a(jsonobject, "biomeSize", customworldsettings.H);
            customworldsettings.I = ChatDeserializer.a(jsonobject, "riverSize", customworldsettings.I);
            customworldsettings.J = ChatDeserializer.a(jsonobject, "dirtSize", customworldsettings.J);
            customworldsettings.K = ChatDeserializer.a(jsonobject, "dirtCount", customworldsettings.K);
            customworldsettings.L = ChatDeserializer.a(jsonobject, "dirtMinHeight", customworldsettings.L);
            customworldsettings.M = ChatDeserializer.a(jsonobject, "dirtMaxHeight", customworldsettings.M);
            customworldsettings.N = ChatDeserializer.a(jsonobject, "gravelSize", customworldsettings.N);
            customworldsettings.O = ChatDeserializer.a(jsonobject, "gravelCount", customworldsettings.O);
            customworldsettings.P = ChatDeserializer.a(jsonobject, "gravelMinHeight", customworldsettings.P);
            customworldsettings.Q = ChatDeserializer.a(jsonobject, "gravelMaxHeight", customworldsettings.Q);
            customworldsettings.R = ChatDeserializer.a(jsonobject, "graniteSize", customworldsettings.R);
            customworldsettings.S = ChatDeserializer.a(jsonobject, "graniteCount", customworldsettings.S);
            customworldsettings.T = ChatDeserializer.a(jsonobject, "graniteMinHeight", customworldsettings.T);
            customworldsettings.U = ChatDeserializer.a(jsonobject, "graniteMaxHeight", customworldsettings.U);
            customworldsettings.V = ChatDeserializer.a(jsonobject, "dioriteSize", customworldsettings.V);
            customworldsettings.W = ChatDeserializer.a(jsonobject, "dioriteCount", customworldsettings.W);
            customworldsettings.X = ChatDeserializer.a(jsonobject, "dioriteMinHeight", customworldsettings.X);
            customworldsettings.Y = ChatDeserializer.a(jsonobject, "dioriteMaxHeight", customworldsettings.Y);
            customworldsettings.Z = ChatDeserializer.a(jsonobject, "andesiteSize", customworldsettings.Z);
            customworldsettings.aa = ChatDeserializer.a(jsonobject, "andesiteCount", customworldsettings.aa);
            customworldsettings.ab = ChatDeserializer.a(jsonobject, "andesiteMinHeight", customworldsettings.ab);
            customworldsettings.ac = ChatDeserializer.a(jsonobject, "andesiteMaxHeight", customworldsettings.ac);
            customworldsettings.ad = ChatDeserializer.a(jsonobject, "coalSize", customworldsettings.ad);
            customworldsettings.ae = ChatDeserializer.a(jsonobject, "coalCount", customworldsettings.ae);
            customworldsettings.af = ChatDeserializer.a(jsonobject, "coalMinHeight", customworldsettings.af);
            customworldsettings.ag = ChatDeserializer.a(jsonobject, "coalMaxHeight", customworldsettings.ag);
            customworldsettings.ah = ChatDeserializer.a(jsonobject, "ironSize", customworldsettings.ah);
            customworldsettings.ai = ChatDeserializer.a(jsonobject, "ironCount", customworldsettings.ai);
            customworldsettings.aj = ChatDeserializer.a(jsonobject, "ironMinHeight", customworldsettings.aj);
            customworldsettings.ak = ChatDeserializer.a(jsonobject, "ironMaxHeight", customworldsettings.ak);
            customworldsettings.al = ChatDeserializer.a(jsonobject, "goldSize", customworldsettings.al);
            customworldsettings.am = ChatDeserializer.a(jsonobject, "goldCount", customworldsettings.am);
            customworldsettings.an = ChatDeserializer.a(jsonobject, "goldMinHeight", customworldsettings.an);
            customworldsettings.ao = ChatDeserializer.a(jsonobject, "goldMaxHeight", customworldsettings.ao);
            customworldsettings.ap = ChatDeserializer.a(jsonobject, "redstoneSize", customworldsettings.ap);
            customworldsettings.aq = ChatDeserializer.a(jsonobject, "redstoneCount", customworldsettings.aq);
            customworldsettings.ar = ChatDeserializer.a(jsonobject, "redstoneMinHeight", customworldsettings.ar);
            customworldsettings.as = ChatDeserializer.a(jsonobject, "redstoneMaxHeight", customworldsettings.as);
            customworldsettings.at = ChatDeserializer.a(jsonobject, "diamondSize", customworldsettings.at);
            customworldsettings.au = ChatDeserializer.a(jsonobject, "diamondCount", customworldsettings.au);
            customworldsettings.av = ChatDeserializer.a(jsonobject, "diamondMinHeight", customworldsettings.av);
            customworldsettings.aw = ChatDeserializer.a(jsonobject, "diamondMaxHeight", customworldsettings.aw);
            customworldsettings.ax = ChatDeserializer.a(jsonobject, "lapisSize", customworldsettings.ax);
            customworldsettings.ay = ChatDeserializer.a(jsonobject, "lapisCount", customworldsettings.ay);
            customworldsettings.az = ChatDeserializer.a(jsonobject, "lapisCenterHeight", customworldsettings.az);
            customworldsettings.aA = ChatDeserializer.a(jsonobject, "lapisSpread", customworldsettings.aA);
        } catch (Exception exception) {
            ;
        }

        return customworldsettings;
    }

    public JsonElement a(CustomWorldSettings customworldsettings, Type type, JsonSerializationContext jsonserializationcontext) {
        JsonObject jsonobject = new JsonObject();

        jsonobject.addProperty("coordinateScale", (Number) Float.valueOf(customworldsettings.b));
        jsonobject.addProperty("heightScale", (Number) Float.valueOf(customworldsettings.c));
        jsonobject.addProperty("lowerLimitScale", (Number) Float.valueOf(customworldsettings.e));
        jsonobject.addProperty("upperLimitScale", (Number) Float.valueOf(customworldsettings.d));
        jsonobject.addProperty("depthNoiseScaleX", (Number) Float.valueOf(customworldsettings.f));
        jsonobject.addProperty("depthNoiseScaleZ", (Number) Float.valueOf(customworldsettings.g));
        jsonobject.addProperty("depthNoiseScaleExponent", (Number) Float.valueOf(customworldsettings.h));
        jsonobject.addProperty("mainNoiseScaleX", (Number) Float.valueOf(customworldsettings.i));
        jsonobject.addProperty("mainNoiseScaleY", (Number) Float.valueOf(customworldsettings.j));
        jsonobject.addProperty("mainNoiseScaleZ", (Number) Float.valueOf(customworldsettings.k));
        jsonobject.addProperty("baseSize", (Number) Float.valueOf(customworldsettings.l));
        jsonobject.addProperty("stretchY", (Number) Float.valueOf(customworldsettings.m));
        jsonobject.addProperty("biomeDepthWeight", (Number) Float.valueOf(customworldsettings.n));
        jsonobject.addProperty("biomeDepthOffset", (Number) Float.valueOf(customworldsettings.o));
        jsonobject.addProperty("biomeScaleWeight", (Number) Float.valueOf(customworldsettings.p));
        jsonobject.addProperty("biomeScaleOffset", (Number) Float.valueOf(customworldsettings.q));
        jsonobject.addProperty("seaLevel", (Number) Integer.valueOf(customworldsettings.r));
        jsonobject.addProperty("useCaves", Boolean.valueOf(customworldsettings.s));
        jsonobject.addProperty("useDungeons", Boolean.valueOf(customworldsettings.t));
        jsonobject.addProperty("dungeonChance", (Number) Integer.valueOf(customworldsettings.u));
        jsonobject.addProperty("useStrongholds", Boolean.valueOf(customworldsettings.v));
        jsonobject.addProperty("useVillages", Boolean.valueOf(customworldsettings.w));
        jsonobject.addProperty("useMineShafts", Boolean.valueOf(customworldsettings.x));
        jsonobject.addProperty("useTemples", Boolean.valueOf(customworldsettings.y));
        jsonobject.addProperty("useMonuments", Boolean.valueOf(customworldsettings.z));
        jsonobject.addProperty("useRavines", Boolean.valueOf(customworldsettings.A));
        jsonobject.addProperty("useWaterLakes", Boolean.valueOf(customworldsettings.B));
        jsonobject.addProperty("waterLakeChance", (Number) Integer.valueOf(customworldsettings.C));
        jsonobject.addProperty("useLavaLakes", Boolean.valueOf(customworldsettings.D));
        jsonobject.addProperty("lavaLakeChance", (Number) Integer.valueOf(customworldsettings.E));
        jsonobject.addProperty("useLavaOceans", Boolean.valueOf(customworldsettings.F));
        jsonobject.addProperty("fixedBiome", (Number) Integer.valueOf(customworldsettings.G));
        jsonobject.addProperty("biomeSize", (Number) Integer.valueOf(customworldsettings.H));
        jsonobject.addProperty("riverSize", (Number) Integer.valueOf(customworldsettings.I));
        jsonobject.addProperty("dirtSize", (Number) Integer.valueOf(customworldsettings.J));
        jsonobject.addProperty("dirtCount", (Number) Integer.valueOf(customworldsettings.K));
        jsonobject.addProperty("dirtMinHeight", (Number) Integer.valueOf(customworldsettings.L));
        jsonobject.addProperty("dirtMaxHeight", (Number) Integer.valueOf(customworldsettings.M));
        jsonobject.addProperty("gravelSize", (Number) Integer.valueOf(customworldsettings.N));
        jsonobject.addProperty("gravelCount", (Number) Integer.valueOf(customworldsettings.O));
        jsonobject.addProperty("gravelMinHeight", (Number) Integer.valueOf(customworldsettings.P));
        jsonobject.addProperty("gravelMaxHeight", (Number) Integer.valueOf(customworldsettings.Q));
        jsonobject.addProperty("graniteSize", (Number) Integer.valueOf(customworldsettings.R));
        jsonobject.addProperty("graniteCount", (Number) Integer.valueOf(customworldsettings.S));
        jsonobject.addProperty("graniteMinHeight", (Number) Integer.valueOf(customworldsettings.T));
        jsonobject.addProperty("graniteMaxHeight", (Number) Integer.valueOf(customworldsettings.U));
        jsonobject.addProperty("dioriteSize", (Number) Integer.valueOf(customworldsettings.V));
        jsonobject.addProperty("dioriteCount", (Number) Integer.valueOf(customworldsettings.W));
        jsonobject.addProperty("dioriteMinHeight", (Number) Integer.valueOf(customworldsettings.X));
        jsonobject.addProperty("dioriteMaxHeight", (Number) Integer.valueOf(customworldsettings.Y));
        jsonobject.addProperty("andesiteSize", (Number) Integer.valueOf(customworldsettings.Z));
        jsonobject.addProperty("andesiteCount", (Number) Integer.valueOf(customworldsettings.aa));
        jsonobject.addProperty("andesiteMinHeight", (Number) Integer.valueOf(customworldsettings.ab));
        jsonobject.addProperty("andesiteMaxHeight", (Number) Integer.valueOf(customworldsettings.ac));
        jsonobject.addProperty("coalSize", (Number) Integer.valueOf(customworldsettings.ad));
        jsonobject.addProperty("coalCount", (Number) Integer.valueOf(customworldsettings.ae));
        jsonobject.addProperty("coalMinHeight", (Number) Integer.valueOf(customworldsettings.af));
        jsonobject.addProperty("coalMaxHeight", (Number) Integer.valueOf(customworldsettings.ag));
        jsonobject.addProperty("ironSize", (Number) Integer.valueOf(customworldsettings.ah));
        jsonobject.addProperty("ironCount", (Number) Integer.valueOf(customworldsettings.ai));
        jsonobject.addProperty("ironMinHeight", (Number) Integer.valueOf(customworldsettings.aj));
        jsonobject.addProperty("ironMaxHeight", (Number) Integer.valueOf(customworldsettings.ak));
        jsonobject.addProperty("goldSize", (Number) Integer.valueOf(customworldsettings.al));
        jsonobject.addProperty("goldCount", (Number) Integer.valueOf(customworldsettings.am));
        jsonobject.addProperty("goldMinHeight", (Number) Integer.valueOf(customworldsettings.an));
        jsonobject.addProperty("goldMaxHeight", (Number) Integer.valueOf(customworldsettings.ao));
        jsonobject.addProperty("redstoneSize", (Number) Integer.valueOf(customworldsettings.ap));
        jsonobject.addProperty("redstoneCount", (Number) Integer.valueOf(customworldsettings.aq));
        jsonobject.addProperty("redstoneMinHeight", (Number) Integer.valueOf(customworldsettings.ar));
        jsonobject.addProperty("redstoneMaxHeight", (Number) Integer.valueOf(customworldsettings.as));
        jsonobject.addProperty("diamondSize", (Number) Integer.valueOf(customworldsettings.at));
        jsonobject.addProperty("diamondCount", (Number) Integer.valueOf(customworldsettings.au));
        jsonobject.addProperty("diamondMinHeight", (Number) Integer.valueOf(customworldsettings.av));
        jsonobject.addProperty("diamondMaxHeight", (Number) Integer.valueOf(customworldsettings.aw));
        jsonobject.addProperty("lapisSize", (Number) Integer.valueOf(customworldsettings.ax));
        jsonobject.addProperty("lapisCount", (Number) Integer.valueOf(customworldsettings.ay));
        jsonobject.addProperty("lapisCenterHeight", (Number) Integer.valueOf(customworldsettings.az));
        jsonobject.addProperty("lapisSpread", (Number) Integer.valueOf(customworldsettings.aA));
        return jsonobject;
    }

    public Object deserialize(JsonElement jsonelement, Type type, JsonDeserializationContext jsondeserializationcontext) {
        return this.a(jsonelement, type, jsondeserializationcontext);
    }

    public JsonElement serialize(Object object, Type type, JsonSerializationContext jsonserializationcontext) {
        return this.a((CustomWorldSettings) object, type, jsonserializationcontext);
    }
}
