package net.minecraft.server;

import com.google.common.collect.Lists;
import java.util.Random;

public class BiomeBaseSub extends BiomeBase {

    protected BiomeBase biomes;

    public BiomeBaseSub(int i, BiomeBase biomebase) {
        super(i);
        this.biomes = biomebase;
        this.a(biomebase.ai, true);
        this.ah = biomebase.ah + " M";
        this.ak = biomebase.ak;
        this.al = biomebase.al;
        this.am = biomebase.am;
        this.an = biomebase.an;
        this.ao = biomebase.ao;
        this.temperature = biomebase.temperature;
        this.humidity = biomebase.humidity;
        this.ar = biomebase.ar;
        this.ax = biomebase.ax;
        this.ay = biomebase.ay;
        this.au = Lists.newArrayList((Iterable) biomebase.au);
        this.at = Lists.newArrayList((Iterable) biomebase.at);
        this.aw = Lists.newArrayList((Iterable) biomebase.aw);
        this.av = Lists.newArrayList((Iterable) biomebase.av);
        this.temperature = biomebase.temperature;
        this.humidity = biomebase.humidity;
        this.an = biomebase.an + 0.1F;
        this.ao = biomebase.ao + 0.2F;
    }

    public void a(World world, Random random, BlockPosition blockposition) {
        this.biomes.as.a(world, random, this, blockposition);
    }

    public void a(World world, Random random, ChunkSnapshot chunksnapshot, int i, int j, double d0) {
        this.biomes.a(world, random, chunksnapshot, i, j, d0);
    }

    public float g() {
        return this.biomes.g();
    }

    public WorldGenTreeAbstract a(Random random) {
        return this.biomes.a(random);
    }

    public Class<? extends BiomeBase> l() {
        return this.biomes.l();
    }

    public boolean a(BiomeBase biomebase) {
        return this.biomes.a(biomebase);
    }

    public BiomeBase.b m() {
        return this.biomes.m();
    }
}
