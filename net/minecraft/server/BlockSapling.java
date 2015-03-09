package net.minecraft.server;

import java.util.Random;

public class BlockSapling extends BlockPlant implements IBlockFragilePlantElement {

    public static final BlockStateEnum<BlockWood.a> TYPE = BlockStateEnum.of("type", BlockWood.a.class);
    public static final BlockStateInteger STAGE = BlockStateInteger.of("stage", 0, 1);

    protected BlockSapling() {
        this.j(this.blockStateList.getBlockData().set(BlockSapling.TYPE, BlockWood.a.OAK).set(BlockSapling.STAGE, Integer.valueOf(0)));
        float f = 0.4F;

        this.a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
        this.a(CreativeModeTab.c);
    }

    public String getName() {
        return LocaleI18n.get(this.a() + "." + BlockWood.a.OAK.d() + ".name");
    }

    public void b(World world, BlockPosition blockposition, IBlockData iblockdata, Random random) {
        if (!world.isClientSide) {
            super.b(world, blockposition, iblockdata, random);
            if (world.getLightLevel(blockposition.up()) >= 9 && random.nextInt(7) == 0) {
                this.grow(world, blockposition, iblockdata, random);
            }

        }
    }

    public void grow(World world, BlockPosition blockposition, IBlockData iblockdata, Random random) {
        if (((Integer) iblockdata.get(BlockSapling.STAGE)).intValue() == 0) {
            world.setTypeAndData(blockposition, iblockdata.a(BlockSapling.STAGE), 4);
        } else {
            this.e(world, blockposition, iblockdata, random);
        }

    }

    public void e(World world, BlockPosition blockposition, IBlockData iblockdata, Random random) {
        Object object = random.nextInt(10) == 0 ? new WorldGenBigTree(true) : new WorldGenTrees(true);
        int i = 0;
        int j = 0;
        boolean flag = false;
        IBlockData iblockdata1;

        switch (BlockSapling.SyntheticClass_1.a[((BlockWood.a) iblockdata.get(BlockSapling.TYPE)).ordinal()]) {
        case 1:
            label66:
            for (i = 0; i >= -1; --i) {
                for (j = 0; j >= -1; --j) {
                    if (this.a(world, blockposition, i, j, BlockWood.a.SPRUCE)) {
                        object = new WorldGenMegaTree(false, random.nextBoolean());
                        flag = true;
                        break label66;
                    }
                }
            }

            if (!flag) {
                j = 0;
                i = 0;
                object = new WorldGenTaiga2(true);
            }
            break;

        case 2:
            object = new WorldGenForest(true, false);
            break;

        case 3:
            iblockdata1 = Blocks.LOG.getBlockData().set(BlockLog1.VARIANT, BlockWood.a.JUNGLE);
            IBlockData iblockdata2 = Blocks.LEAVES.getBlockData().set(BlockLeaves1.VARIANT, BlockWood.a.JUNGLE).set(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false));

            label78:
            for (i = 0; i >= -1; --i) {
                for (j = 0; j >= -1; --j) {
                    if (this.a(world, blockposition, i, j, BlockWood.a.JUNGLE)) {
                        object = new WorldGenJungleTree(true, 10, 20, iblockdata1, iblockdata2);
                        flag = true;
                        break label78;
                    }
                }
            }

            if (!flag) {
                j = 0;
                i = 0;
                object = new WorldGenTrees(true, 4 + random.nextInt(7), iblockdata1, iblockdata2, false);
            }
            break;

        case 4:
            object = new WorldGenAcaciaTree(true);
            break;

        case 5:
            label90:
            for (i = 0; i >= -1; --i) {
                for (j = 0; j >= -1; --j) {
                    if (this.a(world, blockposition, i, j, BlockWood.a.DARK_OAK)) {
                        object = new WorldGenForestTree(true);
                        flag = true;
                        break label90;
                    }
                }
            }

            if (!flag) {
                return;
            }

        case 6:
        }

        iblockdata1 = Blocks.AIR.getBlockData();
        if (flag) {
            world.setTypeAndData(blockposition.a(i, 0, j), iblockdata1, 4);
            world.setTypeAndData(blockposition.a(i + 1, 0, j), iblockdata1, 4);
            world.setTypeAndData(blockposition.a(i, 0, j + 1), iblockdata1, 4);
            world.setTypeAndData(blockposition.a(i + 1, 0, j + 1), iblockdata1, 4);
        } else {
            world.setTypeAndData(blockposition, iblockdata1, 4);
        }

        if (!((WorldGenerator) object).generate(world, random, blockposition.a(i, 0, j))) {
            if (flag) {
                world.setTypeAndData(blockposition.a(i, 0, j), iblockdata, 4);
                world.setTypeAndData(blockposition.a(i + 1, 0, j), iblockdata, 4);
                world.setTypeAndData(blockposition.a(i, 0, j + 1), iblockdata, 4);
                world.setTypeAndData(blockposition.a(i + 1, 0, j + 1), iblockdata, 4);
            } else {
                world.setTypeAndData(blockposition, iblockdata, 4);
            }
        }

    }

    private boolean a(World world, BlockPosition blockposition, int i, int j, BlockWood.a blockwood_a) {
        return this.a(world, blockposition.a(i, 0, j), blockwood_a) && this.a(world, blockposition.a(i + 1, 0, j), blockwood_a) && this.a(world, blockposition.a(i, 0, j + 1), blockwood_a) && this.a(world, blockposition.a(i + 1, 0, j + 1), blockwood_a);
    }

    public boolean a(World world, BlockPosition blockposition, BlockWood.a blockwood_a) {
        IBlockData iblockdata = world.getType(blockposition);

        return iblockdata.getBlock() == this && iblockdata.get(BlockSapling.TYPE) == blockwood_a;
    }

    public int getDropData(IBlockData iblockdata) {
        return ((BlockWood.a) iblockdata.get(BlockSapling.TYPE)).a();
    }

    public boolean a(World world, BlockPosition blockposition, IBlockData iblockdata, boolean flag) {
        return true;
    }

    public boolean a(World world, Random random, BlockPosition blockposition, IBlockData iblockdata) {
        return (double) world.random.nextFloat() < 0.45D;
    }

    public void b(World world, Random random, BlockPosition blockposition, IBlockData iblockdata) {
        this.grow(world, blockposition, iblockdata, random);
    }

    public IBlockData fromLegacyData(int i) {
        return this.getBlockData().set(BlockSapling.TYPE, BlockWood.a.a(i & 7)).set(BlockSapling.STAGE, Integer.valueOf((i & 8) >> 3));
    }

    public int toLegacyData(IBlockData iblockdata) {
        byte b0 = 0;
        int i = b0 | ((BlockWood.a) iblockdata.get(BlockSapling.TYPE)).a();

        i |= ((Integer) iblockdata.get(BlockSapling.STAGE)).intValue() << 3;
        return i;
    }

    protected BlockStateList getStateList() {
        return new BlockStateList(this, new IBlockState[] { BlockSapling.TYPE, BlockSapling.STAGE});
    }

    static class SyntheticClass_1 {

        static final int[] a = new int[BlockWood.a.values().length];

        static {
            try {
                BlockSapling.SyntheticClass_1.a[BlockWood.a.SPRUCE.ordinal()] = 1;
            } catch (NoSuchFieldError nosuchfielderror) {
                ;
            }

            try {
                BlockSapling.SyntheticClass_1.a[BlockWood.a.BIRCH.ordinal()] = 2;
            } catch (NoSuchFieldError nosuchfielderror1) {
                ;
            }

            try {
                BlockSapling.SyntheticClass_1.a[BlockWood.a.JUNGLE.ordinal()] = 3;
            } catch (NoSuchFieldError nosuchfielderror2) {
                ;
            }

            try {
                BlockSapling.SyntheticClass_1.a[BlockWood.a.ACACIA.ordinal()] = 4;
            } catch (NoSuchFieldError nosuchfielderror3) {
                ;
            }

            try {
                BlockSapling.SyntheticClass_1.a[BlockWood.a.DARK_OAK.ordinal()] = 5;
            } catch (NoSuchFieldError nosuchfielderror4) {
                ;
            }

            try {
                BlockSapling.SyntheticClass_1.a[BlockWood.a.OAK.ordinal()] = 6;
            } catch (NoSuchFieldError nosuchfielderror5) {
                ;
            }

        }
    }
}
