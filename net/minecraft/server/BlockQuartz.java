package net.minecraft.server;

public class BlockQuartz extends Block {

    public static final BlockStateEnum<BlockQuartz.a> VARIANT = BlockStateEnum.of("variant", BlockQuartz.a.class);

    public BlockQuartz() {
        super(Material.STONE);
        this.j(this.blockStateList.getBlockData().set(BlockQuartz.VARIANT, BlockQuartz.a.DEFAULT));
        this.a(CreativeModeTab.b);
    }

    public IBlockData getPlacedState(World world, BlockPosition blockposition, EnumDirection enumdirection, float f, float f1, float f2, int i, EntityLiving entityliving) {
        if (i == BlockQuartz.a.LINES_Y.a()) {
            switch (BlockQuartz.SyntheticClass_1.a[enumdirection.k().ordinal()]) {
            case 1:
                return this.getBlockData().set(BlockQuartz.VARIANT, BlockQuartz.a.LINES_Z);

            case 2:
                return this.getBlockData().set(BlockQuartz.VARIANT, BlockQuartz.a.LINES_X);

            case 3:
            default:
                return this.getBlockData().set(BlockQuartz.VARIANT, BlockQuartz.a.LINES_Y);
            }
        } else {
            return i == BlockQuartz.a.CHISELED.a() ? this.getBlockData().set(BlockQuartz.VARIANT, BlockQuartz.a.CHISELED) : this.getBlockData().set(BlockQuartz.VARIANT, BlockQuartz.a.DEFAULT);
        }
    }

    public int getDropData(IBlockData iblockdata) {
        BlockQuartz.a blockquartz_a = (BlockQuartz.a) iblockdata.get(BlockQuartz.VARIANT);

        return blockquartz_a != BlockQuartz.a.LINES_X && blockquartz_a != BlockQuartz.a.LINES_Z ? blockquartz_a.a() : BlockQuartz.a.LINES_Y.a();
    }

    protected ItemStack i(IBlockData iblockdata) {
        BlockQuartz.a blockquartz_a = (BlockQuartz.a) iblockdata.get(BlockQuartz.VARIANT);

        return blockquartz_a != BlockQuartz.a.LINES_X && blockquartz_a != BlockQuartz.a.LINES_Z ? super.i(iblockdata) : new ItemStack(Item.getItemOf(this), 1, BlockQuartz.a.LINES_Y.a());
    }

    public MaterialMapColor g(IBlockData iblockdata) {
        return MaterialMapColor.p;
    }

    public IBlockData fromLegacyData(int i) {
        return this.getBlockData().set(BlockQuartz.VARIANT, BlockQuartz.a.a(i));
    }

    public int toLegacyData(IBlockData iblockdata) {
        return ((BlockQuartz.a) iblockdata.get(BlockQuartz.VARIANT)).a();
    }

    protected BlockStateList getStateList() {
        return new BlockStateList(this, new IBlockState[] { BlockQuartz.VARIANT});
    }

    static class SyntheticClass_1 {

        static final int[] a = new int[EnumDirection.a.values().length];

        static {
            try {
                BlockQuartz.SyntheticClass_1.a[EnumDirection.a.Z.ordinal()] = 1;
            } catch (NoSuchFieldError nosuchfielderror) {
                ;
            }

            try {
                BlockQuartz.SyntheticClass_1.a[EnumDirection.a.X.ordinal()] = 2;
            } catch (NoSuchFieldError nosuchfielderror1) {
                ;
            }

            try {
                BlockQuartz.SyntheticClass_1.a[EnumDirection.a.Y.ordinal()] = 3;
            } catch (NoSuchFieldError nosuchfielderror2) {
                ;
            }

        }
    }

    public static enum a implements INamable {

        DEFAULT(0, "default", "default"), CHISELED(1, "chiseled", "chiseled"), LINES_Y(2, "lines_y", "lines"), LINES_X(3, "lines_x", "lines"), LINES_Z(4, "lines_z", "lines");

        private static final BlockQuartz.a[] f = new BlockQuartz.a[values().length];
        private final int g;
        private final String h;
        private final String i;

        private a(int i, String s, String s1) {
            this.g = i;
            this.h = s;
            this.i = s1;
        }

        public int a() {
            return this.g;
        }

        public String toString() {
            return this.i;
        }

        public static BlockQuartz.a a(int i) {
            if (i < 0 || i >= BlockQuartz.a.f.length) {
                i = 0;
            }

            return BlockQuartz.a.f[i];
        }

        public String getName() {
            return this.h;
        }

        static {
            BlockQuartz.a[] ablockquartz_a = values();
            int i = ablockquartz_a.length;

            for (int j = 0; j < i; ++j) {
                BlockQuartz.a blockquartz_a = ablockquartz_a[j];

                BlockQuartz.a.f[blockquartz_a.a()] = blockquartz_a;
            }

        }
    }
}
