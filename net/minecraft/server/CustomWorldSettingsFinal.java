package net.minecraft.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

public class CustomWorldSettingsFinal {

    public final float a;
    public final float b;
    public final float c;
    public final float d;
    public final float e;
    public final float f;
    public final float g;
    public final float h;
    public final float i;
    public final float j;
    public final float k;
    public final float l;
    public final float m;
    public final float n;
    public final float o;
    public final float p;
    public final int q;
    public final boolean r;
    public final boolean s;
    public final int t;
    public final boolean u;
    public final boolean v;
    public final boolean w;
    public final boolean x;
    public final boolean y;
    public final boolean z;
    public final boolean A;
    public final int B;
    public final boolean C;
    public final int D;
    public final boolean E;
    public final int F;
    public final int G;
    public final int H;
    public final int I;
    public final int J;
    public final int K;
    public final int L;
    public final int M;
    public final int N;
    public final int O;
    public final int P;
    public final int Q;
    public final int R;
    public final int S;
    public final int T;
    public final int U;
    public final int V;
    public final int W;
    public final int X;
    public final int Y;
    public final int Z;
    public final int aa;
    public final int ab;
    public final int ac;
    public final int ad;
    public final int ae;
    public final int af;
    public final int ag;
    public final int ah;
    public final int ai;
    public final int aj;
    public final int ak;
    public final int al;
    public final int am;
    public final int an;
    public final int ao;
    public final int ap;
    public final int aq;
    public final int ar;
    public final int as;
    public final int at;
    public final int au;
    public final int av;
    public final int aw;
    public final int ax;
    public final int ay;
    public final int az;

    private CustomWorldSettingsFinal(CustomWorldSettingsFinal.a customworldsettingsfinal_a) {
        this.a = customworldsettingsfinal_a.b;
        this.b = customworldsettingsfinal_a.c;
        this.c = customworldsettingsfinal_a.d;
        this.d = customworldsettingsfinal_a.e;
        this.e = customworldsettingsfinal_a.f;
        this.f = customworldsettingsfinal_a.g;
        this.g = customworldsettingsfinal_a.h;
        this.h = customworldsettingsfinal_a.i;
        this.i = customworldsettingsfinal_a.j;
        this.j = customworldsettingsfinal_a.k;
        this.k = customworldsettingsfinal_a.l;
        this.l = customworldsettingsfinal_a.m;
        this.m = customworldsettingsfinal_a.n;
        this.n = customworldsettingsfinal_a.o;
        this.o = customworldsettingsfinal_a.p;
        this.p = customworldsettingsfinal_a.q;
        this.q = customworldsettingsfinal_a.r;
        this.r = customworldsettingsfinal_a.s;
        this.s = customworldsettingsfinal_a.t;
        this.t = customworldsettingsfinal_a.u;
        this.u = customworldsettingsfinal_a.v;
        this.v = customworldsettingsfinal_a.w;
        this.w = customworldsettingsfinal_a.x;
        this.x = customworldsettingsfinal_a.y;
        this.y = customworldsettingsfinal_a.z;
        this.z = customworldsettingsfinal_a.A;
        this.A = customworldsettingsfinal_a.B;
        this.B = customworldsettingsfinal_a.C;
        this.C = customworldsettingsfinal_a.D;
        this.D = customworldsettingsfinal_a.E;
        this.E = customworldsettingsfinal_a.F;
        this.F = customworldsettingsfinal_a.G;
        this.G = customworldsettingsfinal_a.H;
        this.H = customworldsettingsfinal_a.I;
        this.I = customworldsettingsfinal_a.J;
        this.J = customworldsettingsfinal_a.K;
        this.K = customworldsettingsfinal_a.L;
        this.L = customworldsettingsfinal_a.M;
        this.M = customworldsettingsfinal_a.N;
        this.N = customworldsettingsfinal_a.O;
        this.O = customworldsettingsfinal_a.P;
        this.P = customworldsettingsfinal_a.Q;
        this.Q = customworldsettingsfinal_a.R;
        this.R = customworldsettingsfinal_a.S;
        this.S = customworldsettingsfinal_a.T;
        this.T = customworldsettingsfinal_a.U;
        this.U = customworldsettingsfinal_a.V;
        this.V = customworldsettingsfinal_a.W;
        this.W = customworldsettingsfinal_a.X;
        this.X = customworldsettingsfinal_a.Y;
        this.Y = customworldsettingsfinal_a.Z;
        this.Z = customworldsettingsfinal_a.aa;
        this.aa = customworldsettingsfinal_a.ab;
        this.ab = customworldsettingsfinal_a.ac;
        this.ac = customworldsettingsfinal_a.ad;
        this.ad = customworldsettingsfinal_a.ae;
        this.ae = customworldsettingsfinal_a.af;
        this.af = customworldsettingsfinal_a.ag;
        this.ag = customworldsettingsfinal_a.ah;
        this.ah = customworldsettingsfinal_a.ai;
        this.ai = customworldsettingsfinal_a.aj;
        this.aj = customworldsettingsfinal_a.ak;
        this.ak = customworldsettingsfinal_a.al;
        this.al = customworldsettingsfinal_a.am;
        this.am = customworldsettingsfinal_a.an;
        this.an = customworldsettingsfinal_a.ao;
        this.ao = customworldsettingsfinal_a.ap;
        this.ap = customworldsettingsfinal_a.aq;
        this.aq = customworldsettingsfinal_a.ar;
        this.ar = customworldsettingsfinal_a.as;
        this.as = customworldsettingsfinal_a.at;
        this.at = customworldsettingsfinal_a.au;
        this.au = customworldsettingsfinal_a.av;
        this.av = customworldsettingsfinal_a.aw;
        this.aw = customworldsettingsfinal_a.ax;
        this.ax = customworldsettingsfinal_a.ay;
        this.ay = customworldsettingsfinal_a.az;
        this.az = customworldsettingsfinal_a.aA;
    }

    CustomWorldSettingsFinal(CustomWorldSettingsFinal.a customworldsettingsfinal_a, CustomWorldSettingsFinal.SyntheticClass_1 customworldsettingsfinal_syntheticclass_1) {
        this(customworldsettingsfinal_a);
    }

    static class SyntheticClass_1 {    }

    public static class b implements JsonDeserializer<CustomWorldSettingsFinal.a>, JsonSerializer<CustomWorldSettingsFinal.a> {

        public b() {}

        public CustomWorldSettingsFinal.a a(JsonElement jsonelement, Type type, JsonDeserializationContext jsondeserializationcontext) throws JsonParseException {
            JsonObject jsonobject = jsonelement.getAsJsonObject();
            CustomWorldSettingsFinal.a customworldsettingsfinal_a = new CustomWorldSettingsFinal.a();

            try {
                customworldsettingsfinal_a.b = ChatDeserializer.a(jsonobject, "coordinateScale", customworldsettingsfinal_a.b);
                customworldsettingsfinal_a.c = ChatDeserializer.a(jsonobject, "heightScale", customworldsettingsfinal_a.c);
                customworldsettingsfinal_a.e = ChatDeserializer.a(jsonobject, "lowerLimitScale", customworldsettingsfinal_a.e);
                customworldsettingsfinal_a.d = ChatDeserializer.a(jsonobject, "upperLimitScale", customworldsettingsfinal_a.d);
                customworldsettingsfinal_a.f = ChatDeserializer.a(jsonobject, "depthNoiseScaleX", customworldsettingsfinal_a.f);
                customworldsettingsfinal_a.g = ChatDeserializer.a(jsonobject, "depthNoiseScaleZ", customworldsettingsfinal_a.g);
                customworldsettingsfinal_a.h = ChatDeserializer.a(jsonobject, "depthNoiseScaleExponent", customworldsettingsfinal_a.h);
                customworldsettingsfinal_a.i = ChatDeserializer.a(jsonobject, "mainNoiseScaleX", customworldsettingsfinal_a.i);
                customworldsettingsfinal_a.j = ChatDeserializer.a(jsonobject, "mainNoiseScaleY", customworldsettingsfinal_a.j);
                customworldsettingsfinal_a.k = ChatDeserializer.a(jsonobject, "mainNoiseScaleZ", customworldsettingsfinal_a.k);
                customworldsettingsfinal_a.l = ChatDeserializer.a(jsonobject, "baseSize", customworldsettingsfinal_a.l);
                customworldsettingsfinal_a.m = ChatDeserializer.a(jsonobject, "stretchY", customworldsettingsfinal_a.m);
                customworldsettingsfinal_a.n = ChatDeserializer.a(jsonobject, "biomeDepthWeight", customworldsettingsfinal_a.n);
                customworldsettingsfinal_a.o = ChatDeserializer.a(jsonobject, "biomeDepthOffset", customworldsettingsfinal_a.o);
                customworldsettingsfinal_a.p = ChatDeserializer.a(jsonobject, "biomeScaleWeight", customworldsettingsfinal_a.p);
                customworldsettingsfinal_a.q = ChatDeserializer.a(jsonobject, "biomeScaleOffset", customworldsettingsfinal_a.q);
                customworldsettingsfinal_a.r = ChatDeserializer.a(jsonobject, "seaLevel", customworldsettingsfinal_a.r);
                customworldsettingsfinal_a.s = ChatDeserializer.a(jsonobject, "useCaves", customworldsettingsfinal_a.s);
                customworldsettingsfinal_a.t = ChatDeserializer.a(jsonobject, "useDungeons", customworldsettingsfinal_a.t);
                customworldsettingsfinal_a.u = ChatDeserializer.a(jsonobject, "dungeonChance", customworldsettingsfinal_a.u);
                customworldsettingsfinal_a.v = ChatDeserializer.a(jsonobject, "useStrongholds", customworldsettingsfinal_a.v);
                customworldsettingsfinal_a.w = ChatDeserializer.a(jsonobject, "useVillages", customworldsettingsfinal_a.w);
                customworldsettingsfinal_a.x = ChatDeserializer.a(jsonobject, "useMineShafts", customworldsettingsfinal_a.x);
                customworldsettingsfinal_a.y = ChatDeserializer.a(jsonobject, "useTemples", customworldsettingsfinal_a.y);
                customworldsettingsfinal_a.z = ChatDeserializer.a(jsonobject, "useMonuments", customworldsettingsfinal_a.z);
                customworldsettingsfinal_a.A = ChatDeserializer.a(jsonobject, "useRavines", customworldsettingsfinal_a.A);
                customworldsettingsfinal_a.B = ChatDeserializer.a(jsonobject, "useWaterLakes", customworldsettingsfinal_a.B);
                customworldsettingsfinal_a.C = ChatDeserializer.a(jsonobject, "waterLakeChance", customworldsettingsfinal_a.C);
                customworldsettingsfinal_a.D = ChatDeserializer.a(jsonobject, "useLavaLakes", customworldsettingsfinal_a.D);
                customworldsettingsfinal_a.E = ChatDeserializer.a(jsonobject, "lavaLakeChance", customworldsettingsfinal_a.E);
                customworldsettingsfinal_a.F = ChatDeserializer.a(jsonobject, "useLavaOceans", customworldsettingsfinal_a.F);
                customworldsettingsfinal_a.G = ChatDeserializer.a(jsonobject, "fixedBiome", customworldsettingsfinal_a.G);
                if (customworldsettingsfinal_a.G < 38 && customworldsettingsfinal_a.G >= -1) {
                    if (customworldsettingsfinal_a.G >= BiomeBase.HELL.id) {
                        customworldsettingsfinal_a.G += 2;
                    }
                } else {
                    customworldsettingsfinal_a.G = -1;
                }

                customworldsettingsfinal_a.H = ChatDeserializer.a(jsonobject, "biomeSize", customworldsettingsfinal_a.H);
                customworldsettingsfinal_a.I = ChatDeserializer.a(jsonobject, "riverSize", customworldsettingsfinal_a.I);
                customworldsettingsfinal_a.J = ChatDeserializer.a(jsonobject, "dirtSize", customworldsettingsfinal_a.J);
                customworldsettingsfinal_a.K = ChatDeserializer.a(jsonobject, "dirtCount", customworldsettingsfinal_a.K);
                customworldsettingsfinal_a.L = ChatDeserializer.a(jsonobject, "dirtMinHeight", customworldsettingsfinal_a.L);
                customworldsettingsfinal_a.M = ChatDeserializer.a(jsonobject, "dirtMaxHeight", customworldsettingsfinal_a.M);
                customworldsettingsfinal_a.N = ChatDeserializer.a(jsonobject, "gravelSize", customworldsettingsfinal_a.N);
                customworldsettingsfinal_a.O = ChatDeserializer.a(jsonobject, "gravelCount", customworldsettingsfinal_a.O);
                customworldsettingsfinal_a.P = ChatDeserializer.a(jsonobject, "gravelMinHeight", customworldsettingsfinal_a.P);
                customworldsettingsfinal_a.Q = ChatDeserializer.a(jsonobject, "gravelMaxHeight", customworldsettingsfinal_a.Q);
                customworldsettingsfinal_a.R = ChatDeserializer.a(jsonobject, "graniteSize", customworldsettingsfinal_a.R);
                customworldsettingsfinal_a.S = ChatDeserializer.a(jsonobject, "graniteCount", customworldsettingsfinal_a.S);
                customworldsettingsfinal_a.T = ChatDeserializer.a(jsonobject, "graniteMinHeight", customworldsettingsfinal_a.T);
                customworldsettingsfinal_a.U = ChatDeserializer.a(jsonobject, "graniteMaxHeight", customworldsettingsfinal_a.U);
                customworldsettingsfinal_a.V = ChatDeserializer.a(jsonobject, "dioriteSize", customworldsettingsfinal_a.V);
                customworldsettingsfinal_a.W = ChatDeserializer.a(jsonobject, "dioriteCount", customworldsettingsfinal_a.W);
                customworldsettingsfinal_a.X = ChatDeserializer.a(jsonobject, "dioriteMinHeight", customworldsettingsfinal_a.X);
                customworldsettingsfinal_a.Y = ChatDeserializer.a(jsonobject, "dioriteMaxHeight", customworldsettingsfinal_a.Y);
                customworldsettingsfinal_a.Z = ChatDeserializer.a(jsonobject, "andesiteSize", customworldsettingsfinal_a.Z);
                customworldsettingsfinal_a.aa = ChatDeserializer.a(jsonobject, "andesiteCount", customworldsettingsfinal_a.aa);
                customworldsettingsfinal_a.ab = ChatDeserializer.a(jsonobject, "andesiteMinHeight", customworldsettingsfinal_a.ab);
                customworldsettingsfinal_a.ac = ChatDeserializer.a(jsonobject, "andesiteMaxHeight", customworldsettingsfinal_a.ac);
                customworldsettingsfinal_a.ad = ChatDeserializer.a(jsonobject, "coalSize", customworldsettingsfinal_a.ad);
                customworldsettingsfinal_a.ae = ChatDeserializer.a(jsonobject, "coalCount", customworldsettingsfinal_a.ae);
                customworldsettingsfinal_a.af = ChatDeserializer.a(jsonobject, "coalMinHeight", customworldsettingsfinal_a.af);
                customworldsettingsfinal_a.ag = ChatDeserializer.a(jsonobject, "coalMaxHeight", customworldsettingsfinal_a.ag);
                customworldsettingsfinal_a.ah = ChatDeserializer.a(jsonobject, "ironSize", customworldsettingsfinal_a.ah);
                customworldsettingsfinal_a.ai = ChatDeserializer.a(jsonobject, "ironCount", customworldsettingsfinal_a.ai);
                customworldsettingsfinal_a.aj = ChatDeserializer.a(jsonobject, "ironMinHeight", customworldsettingsfinal_a.aj);
                customworldsettingsfinal_a.ak = ChatDeserializer.a(jsonobject, "ironMaxHeight", customworldsettingsfinal_a.ak);
                customworldsettingsfinal_a.al = ChatDeserializer.a(jsonobject, "goldSize", customworldsettingsfinal_a.al);
                customworldsettingsfinal_a.am = ChatDeserializer.a(jsonobject, "goldCount", customworldsettingsfinal_a.am);
                customworldsettingsfinal_a.an = ChatDeserializer.a(jsonobject, "goldMinHeight", customworldsettingsfinal_a.an);
                customworldsettingsfinal_a.ao = ChatDeserializer.a(jsonobject, "goldMaxHeight", customworldsettingsfinal_a.ao);
                customworldsettingsfinal_a.ap = ChatDeserializer.a(jsonobject, "redstoneSize", customworldsettingsfinal_a.ap);
                customworldsettingsfinal_a.aq = ChatDeserializer.a(jsonobject, "redstoneCount", customworldsettingsfinal_a.aq);
                customworldsettingsfinal_a.ar = ChatDeserializer.a(jsonobject, "redstoneMinHeight", customworldsettingsfinal_a.ar);
                customworldsettingsfinal_a.as = ChatDeserializer.a(jsonobject, "redstoneMaxHeight", customworldsettingsfinal_a.as);
                customworldsettingsfinal_a.at = ChatDeserializer.a(jsonobject, "diamondSize", customworldsettingsfinal_a.at);
                customworldsettingsfinal_a.au = ChatDeserializer.a(jsonobject, "diamondCount", customworldsettingsfinal_a.au);
                customworldsettingsfinal_a.av = ChatDeserializer.a(jsonobject, "diamondMinHeight", customworldsettingsfinal_a.av);
                customworldsettingsfinal_a.aw = ChatDeserializer.a(jsonobject, "diamondMaxHeight", customworldsettingsfinal_a.aw);
                customworldsettingsfinal_a.ax = ChatDeserializer.a(jsonobject, "lapisSize", customworldsettingsfinal_a.ax);
                customworldsettingsfinal_a.ay = ChatDeserializer.a(jsonobject, "lapisCount", customworldsettingsfinal_a.ay);
                customworldsettingsfinal_a.az = ChatDeserializer.a(jsonobject, "lapisCenterHeight", customworldsettingsfinal_a.az);
                customworldsettingsfinal_a.aA = ChatDeserializer.a(jsonobject, "lapisSpread", customworldsettingsfinal_a.aA);
            } catch (Exception exception) {
                ;
            }

            return customworldsettingsfinal_a;
        }

        public JsonElement a(CustomWorldSettingsFinal.a customworldsettingsfinal_a, Type type, JsonSerializationContext jsonserializationcontext) {
            JsonObject jsonobject = new JsonObject();

            jsonobject.addProperty("coordinateScale", (Number) Float.valueOf(customworldsettingsfinal_a.b));
            jsonobject.addProperty("heightScale", (Number) Float.valueOf(customworldsettingsfinal_a.c));
            jsonobject.addProperty("lowerLimitScale", (Number) Float.valueOf(customworldsettingsfinal_a.e));
            jsonobject.addProperty("upperLimitScale", (Number) Float.valueOf(customworldsettingsfinal_a.d));
            jsonobject.addProperty("depthNoiseScaleX", (Number) Float.valueOf(customworldsettingsfinal_a.f));
            jsonobject.addProperty("depthNoiseScaleZ", (Number) Float.valueOf(customworldsettingsfinal_a.g));
            jsonobject.addProperty("depthNoiseScaleExponent", (Number) Float.valueOf(customworldsettingsfinal_a.h));
            jsonobject.addProperty("mainNoiseScaleX", (Number) Float.valueOf(customworldsettingsfinal_a.i));
            jsonobject.addProperty("mainNoiseScaleY", (Number) Float.valueOf(customworldsettingsfinal_a.j));
            jsonobject.addProperty("mainNoiseScaleZ", (Number) Float.valueOf(customworldsettingsfinal_a.k));
            jsonobject.addProperty("baseSize", (Number) Float.valueOf(customworldsettingsfinal_a.l));
            jsonobject.addProperty("stretchY", (Number) Float.valueOf(customworldsettingsfinal_a.m));
            jsonobject.addProperty("biomeDepthWeight", (Number) Float.valueOf(customworldsettingsfinal_a.n));
            jsonobject.addProperty("biomeDepthOffset", (Number) Float.valueOf(customworldsettingsfinal_a.o));
            jsonobject.addProperty("biomeScaleWeight", (Number) Float.valueOf(customworldsettingsfinal_a.p));
            jsonobject.addProperty("biomeScaleOffset", (Number) Float.valueOf(customworldsettingsfinal_a.q));
            jsonobject.addProperty("seaLevel", (Number) Integer.valueOf(customworldsettingsfinal_a.r));
            jsonobject.addProperty("useCaves", Boolean.valueOf(customworldsettingsfinal_a.s));
            jsonobject.addProperty("useDungeons", Boolean.valueOf(customworldsettingsfinal_a.t));
            jsonobject.addProperty("dungeonChance", (Number) Integer.valueOf(customworldsettingsfinal_a.u));
            jsonobject.addProperty("useStrongholds", Boolean.valueOf(customworldsettingsfinal_a.v));
            jsonobject.addProperty("useVillages", Boolean.valueOf(customworldsettingsfinal_a.w));
            jsonobject.addProperty("useMineShafts", Boolean.valueOf(customworldsettingsfinal_a.x));
            jsonobject.addProperty("useTemples", Boolean.valueOf(customworldsettingsfinal_a.y));
            jsonobject.addProperty("useMonuments", Boolean.valueOf(customworldsettingsfinal_a.z));
            jsonobject.addProperty("useRavines", Boolean.valueOf(customworldsettingsfinal_a.A));
            jsonobject.addProperty("useWaterLakes", Boolean.valueOf(customworldsettingsfinal_a.B));
            jsonobject.addProperty("waterLakeChance", (Number) Integer.valueOf(customworldsettingsfinal_a.C));
            jsonobject.addProperty("useLavaLakes", Boolean.valueOf(customworldsettingsfinal_a.D));
            jsonobject.addProperty("lavaLakeChance", (Number) Integer.valueOf(customworldsettingsfinal_a.E));
            jsonobject.addProperty("useLavaOceans", Boolean.valueOf(customworldsettingsfinal_a.F));
            jsonobject.addProperty("fixedBiome", (Number) Integer.valueOf(customworldsettingsfinal_a.G));
            jsonobject.addProperty("biomeSize", (Number) Integer.valueOf(customworldsettingsfinal_a.H));
            jsonobject.addProperty("riverSize", (Number) Integer.valueOf(customworldsettingsfinal_a.I));
            jsonobject.addProperty("dirtSize", (Number) Integer.valueOf(customworldsettingsfinal_a.J));
            jsonobject.addProperty("dirtCount", (Number) Integer.valueOf(customworldsettingsfinal_a.K));
            jsonobject.addProperty("dirtMinHeight", (Number) Integer.valueOf(customworldsettingsfinal_a.L));
            jsonobject.addProperty("dirtMaxHeight", (Number) Integer.valueOf(customworldsettingsfinal_a.M));
            jsonobject.addProperty("gravelSize", (Number) Integer.valueOf(customworldsettingsfinal_a.N));
            jsonobject.addProperty("gravelCount", (Number) Integer.valueOf(customworldsettingsfinal_a.O));
            jsonobject.addProperty("gravelMinHeight", (Number) Integer.valueOf(customworldsettingsfinal_a.P));
            jsonobject.addProperty("gravelMaxHeight", (Number) Integer.valueOf(customworldsettingsfinal_a.Q));
            jsonobject.addProperty("graniteSize", (Number) Integer.valueOf(customworldsettingsfinal_a.R));
            jsonobject.addProperty("graniteCount", (Number) Integer.valueOf(customworldsettingsfinal_a.S));
            jsonobject.addProperty("graniteMinHeight", (Number) Integer.valueOf(customworldsettingsfinal_a.T));
            jsonobject.addProperty("graniteMaxHeight", (Number) Integer.valueOf(customworldsettingsfinal_a.U));
            jsonobject.addProperty("dioriteSize", (Number) Integer.valueOf(customworldsettingsfinal_a.V));
            jsonobject.addProperty("dioriteCount", (Number) Integer.valueOf(customworldsettingsfinal_a.W));
            jsonobject.addProperty("dioriteMinHeight", (Number) Integer.valueOf(customworldsettingsfinal_a.X));
            jsonobject.addProperty("dioriteMaxHeight", (Number) Integer.valueOf(customworldsettingsfinal_a.Y));
            jsonobject.addProperty("andesiteSize", (Number) Integer.valueOf(customworldsettingsfinal_a.Z));
            jsonobject.addProperty("andesiteCount", (Number) Integer.valueOf(customworldsettingsfinal_a.aa));
            jsonobject.addProperty("andesiteMinHeight", (Number) Integer.valueOf(customworldsettingsfinal_a.ab));
            jsonobject.addProperty("andesiteMaxHeight", (Number) Integer.valueOf(customworldsettingsfinal_a.ac));
            jsonobject.addProperty("coalSize", (Number) Integer.valueOf(customworldsettingsfinal_a.ad));
            jsonobject.addProperty("coalCount", (Number) Integer.valueOf(customworldsettingsfinal_a.ae));
            jsonobject.addProperty("coalMinHeight", (Number) Integer.valueOf(customworldsettingsfinal_a.af));
            jsonobject.addProperty("coalMaxHeight", (Number) Integer.valueOf(customworldsettingsfinal_a.ag));
            jsonobject.addProperty("ironSize", (Number) Integer.valueOf(customworldsettingsfinal_a.ah));
            jsonobject.addProperty("ironCount", (Number) Integer.valueOf(customworldsettingsfinal_a.ai));
            jsonobject.addProperty("ironMinHeight", (Number) Integer.valueOf(customworldsettingsfinal_a.aj));
            jsonobject.addProperty("ironMaxHeight", (Number) Integer.valueOf(customworldsettingsfinal_a.ak));
            jsonobject.addProperty("goldSize", (Number) Integer.valueOf(customworldsettingsfinal_a.al));
            jsonobject.addProperty("goldCount", (Number) Integer.valueOf(customworldsettingsfinal_a.am));
            jsonobject.addProperty("goldMinHeight", (Number) Integer.valueOf(customworldsettingsfinal_a.an));
            jsonobject.addProperty("goldMaxHeight", (Number) Integer.valueOf(customworldsettingsfinal_a.ao));
            jsonobject.addProperty("redstoneSize", (Number) Integer.valueOf(customworldsettingsfinal_a.ap));
            jsonobject.addProperty("redstoneCount", (Number) Integer.valueOf(customworldsettingsfinal_a.aq));
            jsonobject.addProperty("redstoneMinHeight", (Number) Integer.valueOf(customworldsettingsfinal_a.ar));
            jsonobject.addProperty("redstoneMaxHeight", (Number) Integer.valueOf(customworldsettingsfinal_a.as));
            jsonobject.addProperty("diamondSize", (Number) Integer.valueOf(customworldsettingsfinal_a.at));
            jsonobject.addProperty("diamondCount", (Number) Integer.valueOf(customworldsettingsfinal_a.au));
            jsonobject.addProperty("diamondMinHeight", (Number) Integer.valueOf(customworldsettingsfinal_a.av));
            jsonobject.addProperty("diamondMaxHeight", (Number) Integer.valueOf(customworldsettingsfinal_a.aw));
            jsonobject.addProperty("lapisSize", (Number) Integer.valueOf(customworldsettingsfinal_a.ax));
            jsonobject.addProperty("lapisCount", (Number) Integer.valueOf(customworldsettingsfinal_a.ay));
            jsonobject.addProperty("lapisCenterHeight", (Number) Integer.valueOf(customworldsettingsfinal_a.az));
            jsonobject.addProperty("lapisSpread", (Number) Integer.valueOf(customworldsettingsfinal_a.aA));
            return jsonobject;
        }

        public Object deserialize(JsonElement jsonelement, Type type, JsonDeserializationContext jsondeserializationcontext) throws JsonParseException {
            return this.a(jsonelement, type, jsondeserializationcontext);
        }

        public JsonElement serialize(Object object, Type type, JsonSerializationContext jsonserializationcontext) {
            return this.a((CustomWorldSettingsFinal.a) object, type, jsonserializationcontext);
        }
    }

    public static class a {

        static final Gson a = (new GsonBuilder()).registerTypeAdapter(CustomWorldSettingsFinal.a.class, new CustomWorldSettingsFinal.b()).create();
        public float b = 684.412F;
        public float c = 684.412F;
        public float d = 512.0F;
        public float e = 512.0F;
        public float f = 200.0F;
        public float g = 200.0F;
        public float h = 0.5F;
        public float i = 80.0F;
        public float j = 160.0F;
        public float k = 80.0F;
        public float l = 8.5F;
        public float m = 12.0F;
        public float n = 1.0F;
        public float o = 0.0F;
        public float p = 1.0F;
        public float q = 0.0F;
        public int r = 63;
        public boolean s = true;
        public boolean t = true;
        public int u = 8;
        public boolean v = true;
        public boolean w = true;
        public boolean x = true;
        public boolean y = true;
        public boolean z = true;
        public boolean A = true;
        public boolean B = true;
        public int C = 4;
        public boolean D = true;
        public int E = 80;
        public boolean F = false;
        public int G = -1;
        public int H = 4;
        public int I = 4;
        public int J = 33;
        public int K = 10;
        public int L = 0;
        public int M = 256;
        public int N = 33;
        public int O = 8;
        public int P = 0;
        public int Q = 256;
        public int R = 33;
        public int S = 10;
        public int T = 0;
        public int U = 80;
        public int V = 33;
        public int W = 10;
        public int X = 0;
        public int Y = 80;
        public int Z = 33;
        public int aa = 10;
        public int ab = 0;
        public int ac = 80;
        public int ad = 17;
        public int ae = 20;
        public int af = 0;
        public int ag = 128;
        public int ah = 9;
        public int ai = 20;
        public int aj = 0;
        public int ak = 64;
        public int al = 9;
        public int am = 2;
        public int an = 0;
        public int ao = 32;
        public int ap = 8;
        public int aq = 8;
        public int ar = 0;
        public int as = 16;
        public int at = 8;
        public int au = 1;
        public int av = 0;
        public int aw = 16;
        public int ax = 7;
        public int ay = 1;
        public int az = 16;
        public int aA = 16;

        public static CustomWorldSettingsFinal.a a(String s) {
            if (s.length() == 0) {
                return new CustomWorldSettingsFinal.a();
            } else {
                try {
                    return (CustomWorldSettingsFinal.a) CustomWorldSettingsFinal.a.a.fromJson(s, CustomWorldSettingsFinal.a.class);
                } catch (Exception exception) {
                    return new CustomWorldSettingsFinal.a();
                }
            }
        }

        public String toString() {
            return CustomWorldSettingsFinal.a.a.toJson((Object) this);
        }

        public a() {
            this.a();
        }

        public void a() {
            this.b = 684.412F;
            this.c = 684.412F;
            this.d = 512.0F;
            this.e = 512.0F;
            this.f = 200.0F;
            this.g = 200.0F;
            this.h = 0.5F;
            this.i = 80.0F;
            this.j = 160.0F;
            this.k = 80.0F;
            this.l = 8.5F;
            this.m = 12.0F;
            this.n = 1.0F;
            this.o = 0.0F;
            this.p = 1.0F;
            this.q = 0.0F;
            this.r = 63;
            this.s = true;
            this.t = true;
            this.u = 8;
            this.v = true;
            this.w = true;
            this.x = true;
            this.y = true;
            this.z = true;
            this.A = true;
            this.B = true;
            this.C = 4;
            this.D = true;
            this.E = 80;
            this.F = false;
            this.G = -1;
            this.H = 4;
            this.I = 4;
            this.J = 33;
            this.K = 10;
            this.L = 0;
            this.M = 256;
            this.N = 33;
            this.O = 8;
            this.P = 0;
            this.Q = 256;
            this.R = 33;
            this.S = 10;
            this.T = 0;
            this.U = 80;
            this.V = 33;
            this.W = 10;
            this.X = 0;
            this.Y = 80;
            this.Z = 33;
            this.aa = 10;
            this.ab = 0;
            this.ac = 80;
            this.ad = 17;
            this.ae = 20;
            this.af = 0;
            this.ag = 128;
            this.ah = 9;
            this.ai = 20;
            this.aj = 0;
            this.ak = 64;
            this.al = 9;
            this.am = 2;
            this.an = 0;
            this.ao = 32;
            this.ap = 8;
            this.aq = 8;
            this.ar = 0;
            this.as = 16;
            this.at = 8;
            this.au = 1;
            this.av = 0;
            this.aw = 16;
            this.ax = 7;
            this.ay = 1;
            this.az = 16;
            this.aA = 16;
        }

        public boolean equals(Object object) {
            if (this == object) {
                return true;
            } else if (object != null && this.getClass() == object.getClass()) {
                CustomWorldSettingsFinal.a customworldsettingsfinal_a = (CustomWorldSettingsFinal.a) object;

                return this.aa != customworldsettingsfinal_a.aa ? false : (this.ac != customworldsettingsfinal_a.ac ? false : (this.ab != customworldsettingsfinal_a.ab ? false : (this.Z != customworldsettingsfinal_a.Z ? false : (Float.compare(customworldsettingsfinal_a.l, this.l) != 0 ? false : (Float.compare(customworldsettingsfinal_a.o, this.o) != 0 ? false : (Float.compare(customworldsettingsfinal_a.n, this.n) != 0 ? false : (Float.compare(customworldsettingsfinal_a.q, this.q) != 0 ? false : (Float.compare(customworldsettingsfinal_a.p, this.p) != 0 ? false : (this.H != customworldsettingsfinal_a.H ? false : (this.ae != customworldsettingsfinal_a.ae ? false : (this.ag != customworldsettingsfinal_a.ag ? false : (this.af != customworldsettingsfinal_a.af ? false : (this.ad != customworldsettingsfinal_a.ad ? false : (Float.compare(customworldsettingsfinal_a.b, this.b) != 0 ? false : (Float.compare(customworldsettingsfinal_a.h, this.h) != 0 ? false : (Float.compare(customworldsettingsfinal_a.f, this.f) != 0 ? false : (Float.compare(customworldsettingsfinal_a.g, this.g) != 0 ? false : (this.au != customworldsettingsfinal_a.au ? false : (this.aw != customworldsettingsfinal_a.aw ? false : (this.av != customworldsettingsfinal_a.av ? false : (this.at != customworldsettingsfinal_a.at ? false : (this.W != customworldsettingsfinal_a.W ? false : (this.Y != customworldsettingsfinal_a.Y ? false : (this.X != customworldsettingsfinal_a.X ? false : (this.V != customworldsettingsfinal_a.V ? false : (this.K != customworldsettingsfinal_a.K ? false : (this.M != customworldsettingsfinal_a.M ? false : (this.L != customworldsettingsfinal_a.L ? false : (this.J != customworldsettingsfinal_a.J ? false : (this.u != customworldsettingsfinal_a.u ? false : (this.G != customworldsettingsfinal_a.G ? false : (this.am != customworldsettingsfinal_a.am ? false : (this.ao != customworldsettingsfinal_a.ao ? false : (this.an != customworldsettingsfinal_a.an ? false : (this.al != customworldsettingsfinal_a.al ? false : (this.S != customworldsettingsfinal_a.S ? false : (this.U != customworldsettingsfinal_a.U ? false : (this.T != customworldsettingsfinal_a.T ? false : (this.R != customworldsettingsfinal_a.R ? false : (this.O != customworldsettingsfinal_a.O ? false : (this.Q != customworldsettingsfinal_a.Q ? false : (this.P != customworldsettingsfinal_a.P ? false : (this.N != customworldsettingsfinal_a.N ? false : (Float.compare(customworldsettingsfinal_a.c, this.c) != 0 ? false : (this.ai != customworldsettingsfinal_a.ai ? false : (this.ak != customworldsettingsfinal_a.ak ? false : (this.aj != customworldsettingsfinal_a.aj ? false : (this.ah != customworldsettingsfinal_a.ah ? false : (this.az != customworldsettingsfinal_a.az ? false : (this.ay != customworldsettingsfinal_a.ay ? false : (this.ax != customworldsettingsfinal_a.ax ? false : (this.aA != customworldsettingsfinal_a.aA ? false : (this.E != customworldsettingsfinal_a.E ? false : (Float.compare(customworldsettingsfinal_a.e, this.e) != 0 ? false : (Float.compare(customworldsettingsfinal_a.i, this.i) != 0 ? false : (Float.compare(customworldsettingsfinal_a.j, this.j) != 0 ? false : (Float.compare(customworldsettingsfinal_a.k, this.k) != 0 ? false : (this.aq != customworldsettingsfinal_a.aq ? false : (this.as != customworldsettingsfinal_a.as ? false : (this.ar != customworldsettingsfinal_a.ar ? false : (this.ap != customworldsettingsfinal_a.ap ? false : (this.I != customworldsettingsfinal_a.I ? false : (this.r != customworldsettingsfinal_a.r ? false : (Float.compare(customworldsettingsfinal_a.m, this.m) != 0 ? false : (Float.compare(customworldsettingsfinal_a.d, this.d) != 0 ? false : (this.s != customworldsettingsfinal_a.s ? false : (this.t != customworldsettingsfinal_a.t ? false : (this.D != customworldsettingsfinal_a.D ? false : (this.F != customworldsettingsfinal_a.F ? false : (this.x != customworldsettingsfinal_a.x ? false : (this.A != customworldsettingsfinal_a.A ? false : (this.v != customworldsettingsfinal_a.v ? false : (this.y != customworldsettingsfinal_a.y ? false : (this.z != customworldsettingsfinal_a.z ? false : (this.w != customworldsettingsfinal_a.w ? false : (this.B != customworldsettingsfinal_a.B ? false : this.C == customworldsettingsfinal_a.C))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))));
            } else {
                return false;
            }
        }

        public int hashCode() {
            int i = this.b != 0.0F ? Float.floatToIntBits(this.b) : 0;

            i = 31 * i + (this.c != 0.0F ? Float.floatToIntBits(this.c) : 0);
            i = 31 * i + (this.d != 0.0F ? Float.floatToIntBits(this.d) : 0);
            i = 31 * i + (this.e != 0.0F ? Float.floatToIntBits(this.e) : 0);
            i = 31 * i + (this.f != 0.0F ? Float.floatToIntBits(this.f) : 0);
            i = 31 * i + (this.g != 0.0F ? Float.floatToIntBits(this.g) : 0);
            i = 31 * i + (this.h != 0.0F ? Float.floatToIntBits(this.h) : 0);
            i = 31 * i + (this.i != 0.0F ? Float.floatToIntBits(this.i) : 0);
            i = 31 * i + (this.j != 0.0F ? Float.floatToIntBits(this.j) : 0);
            i = 31 * i + (this.k != 0.0F ? Float.floatToIntBits(this.k) : 0);
            i = 31 * i + (this.l != 0.0F ? Float.floatToIntBits(this.l) : 0);
            i = 31 * i + (this.m != 0.0F ? Float.floatToIntBits(this.m) : 0);
            i = 31 * i + (this.n != 0.0F ? Float.floatToIntBits(this.n) : 0);
            i = 31 * i + (this.o != 0.0F ? Float.floatToIntBits(this.o) : 0);
            i = 31 * i + (this.p != 0.0F ? Float.floatToIntBits(this.p) : 0);
            i = 31 * i + (this.q != 0.0F ? Float.floatToIntBits(this.q) : 0);
            i = 31 * i + this.r;
            i = 31 * i + (this.s ? 1 : 0);
            i = 31 * i + (this.t ? 1 : 0);
            i = 31 * i + this.u;
            i = 31 * i + (this.v ? 1 : 0);
            i = 31 * i + (this.w ? 1 : 0);
            i = 31 * i + (this.x ? 1 : 0);
            i = 31 * i + (this.y ? 1 : 0);
            i = 31 * i + (this.z ? 1 : 0);
            i = 31 * i + (this.A ? 1 : 0);
            i = 31 * i + (this.B ? 1 : 0);
            i = 31 * i + this.C;
            i = 31 * i + (this.D ? 1 : 0);
            i = 31 * i + this.E;
            i = 31 * i + (this.F ? 1 : 0);
            i = 31 * i + this.G;
            i = 31 * i + this.H;
            i = 31 * i + this.I;
            i = 31 * i + this.J;
            i = 31 * i + this.K;
            i = 31 * i + this.L;
            i = 31 * i + this.M;
            i = 31 * i + this.N;
            i = 31 * i + this.O;
            i = 31 * i + this.P;
            i = 31 * i + this.Q;
            i = 31 * i + this.R;
            i = 31 * i + this.S;
            i = 31 * i + this.T;
            i = 31 * i + this.U;
            i = 31 * i + this.V;
            i = 31 * i + this.W;
            i = 31 * i + this.X;
            i = 31 * i + this.Y;
            i = 31 * i + this.Z;
            i = 31 * i + this.aa;
            i = 31 * i + this.ab;
            i = 31 * i + this.ac;
            i = 31 * i + this.ad;
            i = 31 * i + this.ae;
            i = 31 * i + this.af;
            i = 31 * i + this.ag;
            i = 31 * i + this.ah;
            i = 31 * i + this.ai;
            i = 31 * i + this.aj;
            i = 31 * i + this.ak;
            i = 31 * i + this.al;
            i = 31 * i + this.am;
            i = 31 * i + this.an;
            i = 31 * i + this.ao;
            i = 31 * i + this.ap;
            i = 31 * i + this.aq;
            i = 31 * i + this.ar;
            i = 31 * i + this.as;
            i = 31 * i + this.at;
            i = 31 * i + this.au;
            i = 31 * i + this.av;
            i = 31 * i + this.aw;
            i = 31 * i + this.ax;
            i = 31 * i + this.ay;
            i = 31 * i + this.az;
            i = 31 * i + this.aA;
            return i;
        }

        public CustomWorldSettingsFinal b() {
            return new CustomWorldSettingsFinal(this, (CustomWorldSettingsFinal.SyntheticClass_1) null);
        }
    }
}
