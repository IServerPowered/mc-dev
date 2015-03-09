package net.minecraft.server;

import com.google.common.collect.Lists;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

public class WorldGenLargeFeature extends StructureGenerator {

    private static final List<BiomeBase> d = Arrays.asList(new BiomeBase[] { BiomeBase.DESERT, BiomeBase.DESERT_HILLS, BiomeBase.JUNGLE, BiomeBase.JUNGLE_HILLS, BiomeBase.SWAMPLAND});
    private List<BiomeBase.c> f;
    private int g;
    private int h;

    public WorldGenLargeFeature() {
        this.f = Lists.newArrayList();
        this.g = 32;
        this.h = 8;
        this.f.add(new BiomeBase.c(EntityWitch.class, 1, 1, 1));
    }

    public WorldGenLargeFeature(Map<String, String> map) {
        this();
        Iterator iterator = map.entrySet().iterator();

        while (iterator.hasNext()) {
            Entry entry = (Entry) iterator.next();

            if (((String) entry.getKey()).equals("distance")) {
                this.g = MathHelper.a((String) entry.getValue(), this.g, this.h + 1);
            }
        }

    }

    public String a() {
        return "Temple";
    }

    protected boolean a(int i, int j) {
        int k = i;
        int l = j;

        if (i < 0) {
            i -= this.g - 1;
        }

        if (j < 0) {
            j -= this.g - 1;
        }

        int i1 = i / this.g;
        int j1 = j / this.g;
        Random random = this.c.a(i1, j1, 14357617);

        i1 *= this.g;
        j1 *= this.g;
        i1 += random.nextInt(this.g - this.h);
        j1 += random.nextInt(this.g - this.h);
        if (k == i1 && l == j1) {
            BiomeBase biomebase = this.c.getWorldChunkManager().getBiome(new BlockPosition(k * 16 + 8, 0, l * 16 + 8));

            if (biomebase == null) {
                return false;
            }

            Iterator iterator = WorldGenLargeFeature.d.iterator();

            while (iterator.hasNext()) {
                BiomeBase biomebase1 = (BiomeBase) iterator.next();

                if (biomebase == biomebase1) {
                    return true;
                }
            }
        }

        return false;
    }

    protected StructureStart b(int i, int j) {
        return new WorldGenLargeFeature.a(this.c, this.b, i, j);
    }

    public boolean a(BlockPosition blockposition) {
        StructureStart structurestart = this.c(blockposition);

        if (structurestart != null && structurestart instanceof WorldGenLargeFeature.a && !structurestart.a.isEmpty()) {
            StructurePiece structurepiece = (StructurePiece) structurestart.a.getFirst();

            return structurepiece instanceof WorldGenRegistration.d;
        } else {
            return false;
        }
    }

    public List<BiomeBase.c> b() {
        return this.f;
    }

    public static class a extends StructureStart {

        public a() {}

        public a(World world, Random random, int i, int j) {
            super(i, j);
            BiomeBase biomebase = world.getBiome(new BlockPosition(i * 16 + 8, 0, j * 16 + 8));

            if (biomebase != BiomeBase.JUNGLE && biomebase != BiomeBase.JUNGLE_HILLS) {
                if (biomebase == BiomeBase.SWAMPLAND) {
                    WorldGenRegistration.d worldgenregistration_d = new WorldGenRegistration.d(random, i * 16, j * 16);

                    this.a.add(worldgenregistration_d);
                } else if (biomebase == BiomeBase.DESERT || biomebase == BiomeBase.DESERT_HILLS) {
                    WorldGenRegistration.a worldgenregistration_a = new WorldGenRegistration.a(random, i * 16, j * 16);

                    this.a.add(worldgenregistration_a);
                }
            } else {
                WorldGenRegistration.b worldgenregistration_b = new WorldGenRegistration.b(random, i * 16, j * 16);

                this.a.add(worldgenregistration_b);
            }

            this.c();
        }
    }
}
