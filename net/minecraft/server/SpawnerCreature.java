package net.minecraft.server;

import com.google.common.collect.Sets;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

public final class SpawnerCreature {

    private static final int a = (int) Math.pow(17.0D, 2.0D);
    private final Set<ChunkCoordIntPair> b = Sets.newHashSet();

    public SpawnerCreature() {}

    public int a(WorldServer worldserver, boolean flag, boolean flag1, boolean flag2) {
        if (!flag && !flag1) {
            return 0;
        } else {
            this.b.clear();
            int i = 0;
            Iterator iterator = worldserver.players.iterator();

            int j;
            int k;

            while (iterator.hasNext()) {
                EntityHuman entityhuman = (EntityHuman) iterator.next();

                if (!entityhuman.v()) {
                    int l = MathHelper.floor(entityhuman.locX / 16.0D);

                    j = MathHelper.floor(entityhuman.locZ / 16.0D);
                    byte b0 = 8;

                    for (int i1 = -b0; i1 <= b0; ++i1) {
                        for (k = -b0; k <= b0; ++k) {
                            boolean flag3 = i1 == -b0 || i1 == b0 || k == -b0 || k == b0;
                            ChunkCoordIntPair chunkcoordintpair = new ChunkCoordIntPair(i1 + l, k + j);

                            if (!this.b.contains(chunkcoordintpair)) {
                                ++i;
                                if (!flag3 && worldserver.getWorldBorder().isInBounds(chunkcoordintpair)) {
                                    this.b.add(chunkcoordintpair);
                                }
                            }
                        }
                    }
                }
            }

            int j1 = 0;
            BlockPosition blockposition = worldserver.getSpawn();
            EnumCreatureType[] aenumcreaturetype = EnumCreatureType.values();

            j = aenumcreaturetype.length;

            for (int k1 = 0; k1 < j; ++k1) {
                EnumCreatureType enumcreaturetype = aenumcreaturetype[k1];

                if ((!enumcreaturetype.d() || flag1) && (enumcreaturetype.d() || flag) && (!enumcreaturetype.e() || flag2)) {
                    k = worldserver.a(enumcreaturetype.a());
                    int l1 = enumcreaturetype.b() * i / SpawnerCreature.a;

                    if (k <= l1) {
                        Iterator iterator1 = this.b.iterator();

                        label115:
                        while (iterator1.hasNext()) {
                            ChunkCoordIntPair chunkcoordintpair1 = (ChunkCoordIntPair) iterator1.next();
                            BlockPosition blockposition1 = getRandomPosition(worldserver, chunkcoordintpair1.x, chunkcoordintpair1.z);
                            int i2 = blockposition1.getX();
                            int j2 = blockposition1.getY();
                            int k2 = blockposition1.getZ();
                            Block block = worldserver.getType(blockposition1).getBlock();

                            if (!block.isOccluding()) {
                                int l2 = 0;
                                int i3 = 0;

                                while (i3 < 3) {
                                    int j3 = i2;
                                    int k3 = j2;
                                    int l3 = k2;
                                    byte b1 = 6;
                                    BiomeBase.c biomebase_c = null;
                                    GroupDataEntity groupdataentity = null;
                                    int i4 = 0;

                                    while (true) {
                                        if (i4 < 4) {
                                            label108: {
                                                j3 += worldserver.random.nextInt(b1) - worldserver.random.nextInt(b1);
                                                k3 += worldserver.random.nextInt(1) - worldserver.random.nextInt(1);
                                                l3 += worldserver.random.nextInt(b1) - worldserver.random.nextInt(b1);
                                                BlockPosition blockposition2 = new BlockPosition(j3, k3, l3);
                                                float f = (float) j3 + 0.5F;
                                                float f1 = (float) l3 + 0.5F;

                                                if (!worldserver.isPlayerNearby((double) f, (double) k3, (double) f1, 24.0D) && blockposition.c((double) f, (double) k3, (double) f1) >= 576.0D) {
                                                    if (biomebase_c == null) {
                                                        biomebase_c = worldserver.a(enumcreaturetype, blockposition2);
                                                        if (biomebase_c == null) {
                                                            break label108;
                                                        }
                                                    }

                                                    if (worldserver.a(enumcreaturetype, biomebase_c, blockposition2) && a(EntityPositionTypes.a(biomebase_c.b), worldserver, blockposition2)) {
                                                        EntityInsentient entityinsentient;

                                                        try {
                                                            entityinsentient = (EntityInsentient) biomebase_c.b.getConstructor(new Class[] { World.class}).newInstance(new Object[] { worldserver});
                                                        } catch (Exception exception) {
                                                            exception.printStackTrace();
                                                            return j1;
                                                        }

                                                        entityinsentient.setPositionRotation((double) f, (double) k3, (double) f1, worldserver.random.nextFloat() * 360.0F, 0.0F);
                                                        if (entityinsentient.bR() && entityinsentient.canSpawn()) {
                                                            groupdataentity = entityinsentient.prepare(worldserver.E(new BlockPosition(entityinsentient)), groupdataentity);
                                                            if (entityinsentient.canSpawn()) {
                                                                ++l2;
                                                                worldserver.addEntity(entityinsentient);
                                                            }

                                                            if (l2 >= entityinsentient.bV()) {
                                                                continue label115;
                                                            }
                                                        }

                                                        j1 += l2;
                                                    }
                                                }

                                                ++i4;
                                                continue;
                                            }
                                        }

                                        ++i3;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }

            return j1;
        }
    }

    protected static BlockPosition getRandomPosition(World world, int i, int j) {
        Chunk chunk = world.getChunkAt(i, j);
        int k = i * 16 + world.random.nextInt(16);
        int l = j * 16 + world.random.nextInt(16);
        int i1 = MathHelper.c(chunk.f(new BlockPosition(k, 0, l)) + 1, 16);
        int j1 = world.random.nextInt(i1 > 0 ? i1 : chunk.g() + 16 - 1);

        return new BlockPosition(k, j1, l);
    }

    public static boolean a(EntityInsentient.a entityinsentient_a, World world, BlockPosition blockposition) {
        if (!world.getWorldBorder().a(blockposition)) {
            return false;
        } else {
            Block block = world.getType(blockposition).getBlock();

            if (entityinsentient_a == EntityInsentient.a.IN_WATER) {
                return block.getMaterial().isLiquid() && world.getType(blockposition.down()).getBlock().getMaterial().isLiquid() && !world.getType(blockposition.up()).getBlock().isOccluding();
            } else {
                BlockPosition blockposition1 = blockposition.down();

                if (!World.a((IBlockAccess) world, blockposition1)) {
                    return false;
                } else {
                    Block block1 = world.getType(blockposition1).getBlock();
                    boolean flag = block1 != Blocks.BEDROCK && block1 != Blocks.BARRIER;

                    return flag && !block.isOccluding() && !block.getMaterial().isLiquid() && !world.getType(blockposition.up()).getBlock().isOccluding();
                }
            }
        }
    }

    public static void a(World world, BiomeBase biomebase, int i, int j, int k, int l, Random random) {
        List list = biomebase.getMobs(EnumCreatureType.CREATURE);

        if (!list.isEmpty()) {
            while (random.nextFloat() < biomebase.g()) {
                BiomeBase.c biomebase_c = (BiomeBase.c) WeightedRandom.a(world.random, list);
                int i1 = biomebase_c.c + random.nextInt(1 + biomebase_c.d - biomebase_c.c);
                GroupDataEntity groupdataentity = null;
                int j1 = i + random.nextInt(k);
                int k1 = j + random.nextInt(l);
                int l1 = j1;
                int i2 = k1;

                for (int j2 = 0; j2 < i1; ++j2) {
                    boolean flag = false;

                    for (int k2 = 0; !flag && k2 < 4; ++k2) {
                        BlockPosition blockposition = world.r(new BlockPosition(j1, 0, k1));

                        if (a(EntityInsentient.a.ON_GROUND, world, blockposition)) {
                            EntityInsentient entityinsentient;

                            try {
                                entityinsentient = (EntityInsentient) biomebase_c.b.getConstructor(new Class[] { World.class}).newInstance(new Object[] { world});
                            } catch (Exception exception) {
                                exception.printStackTrace();
                                continue;
                            }

                            entityinsentient.setPositionRotation((double) ((float) j1 + 0.5F), (double) blockposition.getY(), (double) ((float) k1 + 0.5F), random.nextFloat() * 360.0F, 0.0F);
                            world.addEntity(entityinsentient);
                            groupdataentity = entityinsentient.prepare(world.E(new BlockPosition(entityinsentient)), groupdataentity);
                            flag = true;
                        }

                        j1 += random.nextInt(5) - random.nextInt(5);

                        for (k1 += random.nextInt(5) - random.nextInt(5); j1 < i || j1 >= i + k || k1 < j || k1 >= j + k; k1 = i2 + random.nextInt(5) - random.nextInt(5)) {
                            j1 = l1 + random.nextInt(5) - random.nextInt(5);
                        }
                    }
                }
            }

        }
    }
}
