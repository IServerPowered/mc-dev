package net.minecraft.server;

public class BlockWood extends Block {

    public static final BlockStateEnum<BlockWood.a> VARIANT = BlockStateEnum.of("variant", BlockWood.a.class);

    public BlockWood() {
        super(Material.WOOD);
        this.j(this.blockStateList.getBlockData().set(BlockWood.VARIANT, BlockWood.a.OAK));
        this.a(CreativeModeTab.b);
    }

    public int getDropData(IBlockData iblockdata) {
        return ((BlockWood.a) iblockdata.get(BlockWood.VARIANT)).a();
    }

    public IBlockData fromLegacyData(int i) {
        return this.getBlockData().set(BlockWood.VARIANT, BlockWood.a.a(i));
    }

    public MaterialMapColor g(IBlockData iblockdata) {
        return ((BlockWood.a) iblockdata.get(BlockWood.VARIANT)).c();
    }

    public int toLegacyData(IBlockData iblockdata) {
        return ((BlockWood.a) iblockdata.get(BlockWood.VARIANT)).a();
    }

    protected BlockStateList getStateList() {
        return new BlockStateList(this, new IBlockState[] { BlockWood.VARIANT});
    }

    public static enum a implements INamable {

        OAK(0, "oak", MaterialMapColor.o), SPRUCE(1, "spruce", MaterialMapColor.J), BIRCH(2, "birch", MaterialMapColor.d), JUNGLE(3, "jungle", MaterialMapColor.l), ACACIA(4, "acacia", MaterialMapColor.q), DARK_OAK(5, "dark_oak", "big_oak", MaterialMapColor.B);

        private static final BlockWood.a[] g = new BlockWood.a[values().length];
        private final int h;
        private final String i;
        private final String j;
        private final MaterialMapColor k;

        private a(int i, String s, MaterialMapColor materialmapcolor) {
            this(i, s, s, materialmapcolor);
        }

        private a(int i, String s, String s1, MaterialMapColor materialmapcolor) {
            this.h = i;
            this.i = s;
            this.j = s1;
            this.k = materialmapcolor;
        }

        public int a() {
            return this.h;
        }

        public MaterialMapColor c() {
            return this.k;
        }

        public String toString() {
            return this.i;
        }

        public static BlockWood.a a(int i) {
            if (i < 0 || i >= BlockWood.a.g.length) {
                i = 0;
            }

            return BlockWood.a.g[i];
        }

        public String getName() {
            return this.i;
        }

        public String d() {
            return this.j;
        }

        static {
            BlockWood.a[] ablockwood_a = values();
            int i = ablockwood_a.length;

            for (int j = 0; j < i; ++j) {
                BlockWood.a blockwood_a = ablockwood_a[j];

                BlockWood.a.g[blockwood_a.a()] = blockwood_a;
            }

        }
    }
}
