package net.minecraft.server;

public class BlockSandStone extends Block {

    public static final BlockStateEnum<BlockSandStone.a> TYPE = BlockStateEnum.of("type", BlockSandStone.a.class);

    public BlockSandStone() {
        super(Material.STONE);
        this.j(this.blockStateList.getBlockData().set(BlockSandStone.TYPE, BlockSandStone.a.DEFAULT));
        this.a(CreativeModeTab.b);
    }

    public int getDropData(IBlockData iblockdata) {
        return ((BlockSandStone.a) iblockdata.get(BlockSandStone.TYPE)).a();
    }

    public MaterialMapColor g(IBlockData iblockdata) {
        return MaterialMapColor.d;
    }

    public IBlockData fromLegacyData(int i) {
        return this.getBlockData().set(BlockSandStone.TYPE, BlockSandStone.a.a(i));
    }

    public int toLegacyData(IBlockData iblockdata) {
        return ((BlockSandStone.a) iblockdata.get(BlockSandStone.TYPE)).a();
    }

    protected BlockStateList getStateList() {
        return new BlockStateList(this, new IBlockState[] { BlockSandStone.TYPE});
    }

    public static enum a implements INamable {

        DEFAULT(0, "sandstone", "default"), CHISELED(1, "chiseled_sandstone", "chiseled"), SMOOTH(2, "smooth_sandstone", "smooth");

        private static final BlockSandStone.a[] d = new BlockSandStone.a[values().length];
        private final int e;
        private final String f;
        private final String g;

        private a(int i, String s, String s1) {
            this.e = i;
            this.f = s;
            this.g = s1;
        }

        public int a() {
            return this.e;
        }

        public String toString() {
            return this.f;
        }

        public static BlockSandStone.a a(int i) {
            if (i < 0 || i >= BlockSandStone.a.d.length) {
                i = 0;
            }

            return BlockSandStone.a.d[i];
        }

        public String getName() {
            return this.f;
        }

        public String c() {
            return this.g;
        }

        static {
            BlockSandStone.a[] ablocksandstone_a = values();
            int i = ablocksandstone_a.length;

            for (int j = 0; j < i; ++j) {
                BlockSandStone.a blocksandstone_a = ablocksandstone_a[j];

                BlockSandStone.a.d[blocksandstone_a.a()] = blocksandstone_a;
            }

        }
    }
}
