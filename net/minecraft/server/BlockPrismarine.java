package net.minecraft.server;

public class BlockPrismarine extends Block {

    public static final BlockStateEnum<BlockPrismarine.a> VARIANT = BlockStateEnum.of("variant", BlockPrismarine.a.class);
    public static final int b = BlockPrismarine.a.ROUGH.a();
    public static final int N = BlockPrismarine.a.BRICKS.a();
    public static final int O = BlockPrismarine.a.DARK.a();

    public BlockPrismarine() {
        super(Material.STONE);
        this.j(this.blockStateList.getBlockData().set(BlockPrismarine.VARIANT, BlockPrismarine.a.ROUGH));
        this.a(CreativeModeTab.b);
    }

    public String getName() {
        return LocaleI18n.get(this.a() + "." + BlockPrismarine.a.ROUGH.c() + ".name");
    }

    public MaterialMapColor g(IBlockData iblockdata) {
        return iblockdata.get(BlockPrismarine.VARIANT) == BlockPrismarine.a.ROUGH ? MaterialMapColor.y : MaterialMapColor.G;
    }

    public int getDropData(IBlockData iblockdata) {
        return ((BlockPrismarine.a) iblockdata.get(BlockPrismarine.VARIANT)).a();
    }

    public int toLegacyData(IBlockData iblockdata) {
        return ((BlockPrismarine.a) iblockdata.get(BlockPrismarine.VARIANT)).a();
    }

    protected BlockStateList getStateList() {
        return new BlockStateList(this, new IBlockState[] { BlockPrismarine.VARIANT});
    }

    public IBlockData fromLegacyData(int i) {
        return this.getBlockData().set(BlockPrismarine.VARIANT, BlockPrismarine.a.a(i));
    }

    public static enum a implements INamable {

        ROUGH(0, "prismarine", "rough"), BRICKS(1, "prismarine_bricks", "bricks"), DARK(2, "dark_prismarine", "dark");

        private static final BlockPrismarine.a[] d = new BlockPrismarine.a[values().length];
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

        public static BlockPrismarine.a a(int i) {
            if (i < 0 || i >= BlockPrismarine.a.d.length) {
                i = 0;
            }

            return BlockPrismarine.a.d[i];
        }

        public String getName() {
            return this.f;
        }

        public String c() {
            return this.g;
        }

        static {
            BlockPrismarine.a[] ablockprismarine_a = values();
            int i = ablockprismarine_a.length;

            for (int j = 0; j < i; ++j) {
                BlockPrismarine.a blockprismarine_a = ablockprismarine_a[j];

                BlockPrismarine.a.d[blockprismarine_a.a()] = blockprismarine_a;
            }

        }
    }
}
