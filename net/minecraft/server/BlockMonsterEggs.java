package net.minecraft.server;

import java.util.Random;

public class BlockMonsterEggs extends Block {

    public static final BlockStateEnum<BlockMonsterEggs.a> VARIANT = BlockStateEnum.of("variant", BlockMonsterEggs.a.class);

    public BlockMonsterEggs() {
        super(Material.CLAY);
        this.j(this.blockStateList.getBlockData().set(BlockMonsterEggs.VARIANT, BlockMonsterEggs.a.STONE));
        this.c(0.0F);
        this.a(CreativeModeTab.c);
    }

    public int a(Random random) {
        return 0;
    }

    public static boolean d(IBlockData iblockdata) {
        Block block = iblockdata.getBlock();

        return iblockdata == Blocks.STONE.getBlockData().set(BlockStone.VARIANT, BlockStone.a.STONE) || block == Blocks.COBBLESTONE || block == Blocks.STONEBRICK;
    }

    protected ItemStack i(IBlockData iblockdata) {
        switch (BlockMonsterEggs.SyntheticClass_1.a[((BlockMonsterEggs.a) iblockdata.get(BlockMonsterEggs.VARIANT)).ordinal()]) {
        case 1:
            return new ItemStack(Blocks.COBBLESTONE);

        case 2:
            return new ItemStack(Blocks.STONEBRICK);

        case 3:
            return new ItemStack(Blocks.STONEBRICK, 1, BlockSmoothBrick.a.MOSSY.a());

        case 4:
            return new ItemStack(Blocks.STONEBRICK, 1, BlockSmoothBrick.a.CRACKED.a());

        case 5:
            return new ItemStack(Blocks.STONEBRICK, 1, BlockSmoothBrick.a.CHISELED.a());

        default:
            return new ItemStack(Blocks.STONE);
        }
    }

    public void dropNaturally(World world, BlockPosition blockposition, IBlockData iblockdata, float f, int i) {
        if (!world.isClientSide && world.getGameRules().getBoolean("doTileDrops")) {
            EntitySilverfish entitysilverfish = new EntitySilverfish(world);

            entitysilverfish.setPositionRotation((double) blockposition.getX() + 0.5D, (double) blockposition.getY(), (double) blockposition.getZ() + 0.5D, 0.0F, 0.0F);
            world.addEntity(entitysilverfish);
            entitysilverfish.y();
        }

    }

    public int getDropData(World world, BlockPosition blockposition) {
        IBlockData iblockdata = world.getType(blockposition);

        return iblockdata.getBlock().toLegacyData(iblockdata);
    }

    public IBlockData fromLegacyData(int i) {
        return this.getBlockData().set(BlockMonsterEggs.VARIANT, BlockMonsterEggs.a.a(i));
    }

    public int toLegacyData(IBlockData iblockdata) {
        return ((BlockMonsterEggs.a) iblockdata.get(BlockMonsterEggs.VARIANT)).a();
    }

    protected BlockStateList getStateList() {
        return new BlockStateList(this, new IBlockState[] { BlockMonsterEggs.VARIANT});
    }

    static class SyntheticClass_1 {

        static final int[] a = new int[BlockMonsterEggs.a.values().length];

        static {
            try {
                BlockMonsterEggs.SyntheticClass_1.a[BlockMonsterEggs.a.COBBLESTONE.ordinal()] = 1;
            } catch (NoSuchFieldError nosuchfielderror) {
                ;
            }

            try {
                BlockMonsterEggs.SyntheticClass_1.a[BlockMonsterEggs.a.STONEBRICK.ordinal()] = 2;
            } catch (NoSuchFieldError nosuchfielderror1) {
                ;
            }

            try {
                BlockMonsterEggs.SyntheticClass_1.a[BlockMonsterEggs.a.MOSSY_STONEBRICK.ordinal()] = 3;
            } catch (NoSuchFieldError nosuchfielderror2) {
                ;
            }

            try {
                BlockMonsterEggs.SyntheticClass_1.a[BlockMonsterEggs.a.CRACKED_STONEBRICK.ordinal()] = 4;
            } catch (NoSuchFieldError nosuchfielderror3) {
                ;
            }

            try {
                BlockMonsterEggs.SyntheticClass_1.a[BlockMonsterEggs.a.CHISELED_STONEBRICK.ordinal()] = 5;
            } catch (NoSuchFieldError nosuchfielderror4) {
                ;
            }

        }
    }

    public static enum a implements INamable {

        STONE(0, "stone") {;
            public IBlockData d() {
                return Blocks.STONE.getBlockData().set(BlockStone.VARIANT, BlockStone.a.STONE);
            }
        }, COBBLESTONE(1, "cobblestone", "cobble") {;
    public IBlockData d() {
        return Blocks.COBBLESTONE.getBlockData();
    }
}, STONEBRICK(2, "stone_brick", "brick") {;
    public IBlockData d() {
        return Blocks.STONEBRICK.getBlockData().set(BlockSmoothBrick.VARIANT, BlockSmoothBrick.a.DEFAULT);
    }
}, MOSSY_STONEBRICK(3, "mossy_brick", "mossybrick") {;
    public IBlockData d() {
        return Blocks.STONEBRICK.getBlockData().set(BlockSmoothBrick.VARIANT, BlockSmoothBrick.a.MOSSY);
    }
}, CRACKED_STONEBRICK(4, "cracked_brick", "crackedbrick") {;
    public IBlockData d() {
        return Blocks.STONEBRICK.getBlockData().set(BlockSmoothBrick.VARIANT, BlockSmoothBrick.a.CRACKED);
    }
}, CHISELED_STONEBRICK(5, "chiseled_brick", "chiseledbrick") {;
    public IBlockData d() {
        return Blocks.STONEBRICK.getBlockData().set(BlockSmoothBrick.VARIANT, BlockSmoothBrick.a.CHISELED);
    }
};

        private static final BlockMonsterEggs.a[] g = new BlockMonsterEggs.a[values().length];
        private final int h;
        private final String i;
        private final String j;

        private a(int i, String s) {
            this(i, s, s);
        }

        private a(int i, String s, String s1) {
            this.h = i;
            this.i = s;
            this.j = s1;
        }

        public int a() {
            return this.h;
        }

        public String toString() {
            return this.i;
        }

        public static BlockMonsterEggs.a a(int i) {
            if (i < 0 || i >= BlockMonsterEggs.a.g.length) {
                i = 0;
            }

            return BlockMonsterEggs.a.g[i];
        }

        public String getName() {
            return this.i;
        }

        public String c() {
            return this.j;
        }

        public abstract IBlockData d();

        public static BlockMonsterEggs.a a(IBlockData iblockdata) {
            BlockMonsterEggs.a[] ablockmonstereggs_a = values();
            int i = ablockmonstereggs_a.length;

            for (int j = 0; j < i; ++j) {
                BlockMonsterEggs.a blockmonstereggs_a = ablockmonstereggs_a[j];

                if (iblockdata == blockmonstereggs_a.d()) {
                    return blockmonstereggs_a;
                }
            }

            return BlockMonsterEggs.a.STONE;
        }

        a(int i, String s, BlockMonsterEggs.SyntheticClass_1 blockmonstereggs_syntheticclass_1) {
            this(i, s);
        }

        a(int i, String s, String s1, BlockMonsterEggs.SyntheticClass_1 blockmonstereggs_syntheticclass_1) {
            this(i, s, s1);
        }

        static {
            BlockMonsterEggs.a[] ablockmonstereggs_a = values();
            int i = ablockmonstereggs_a.length;

            for (int j = 0; j < i; ++j) {
                BlockMonsterEggs.a blockmonstereggs_a = ablockmonstereggs_a[j];

                BlockMonsterEggs.a.g[blockmonstereggs_a.a()] = blockmonstereggs_a;
            }

        }
    }
}
