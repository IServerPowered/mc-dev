package net.minecraft.server;

import java.util.Random;

class BiomeBaseSubForest2 extends BiomeBaseSub {

    final BiomeForest aD;

    BiomeBaseSubForest2(BiomeForest biomeforest, int i, BiomeBase biomebase) {
        super(i, biomebase);
        this.aD = biomeforest;
    }

    public void a(World world, Random random, BlockPosition blockposition) {
        this.biomes.a(world, random, blockposition);
    }
}
