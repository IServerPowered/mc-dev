package net.minecraft.server;

public class BlockRedSandstone extends Block {

    public static final BlockStateEnum<BlockRedSandstone.a> TYPE = BlockStateEnum.of("type", BlockRedSandstone.a.class);

    public BlockRedSandstone() {
        super(Material.STONE, BlockSand.a.RED_SAND.c());
        this.j(this.blockStateList.getBlockData().set(BlockRedSandstone.TYPE, BlockRedSandstone.a.DEFAULT));
        this.a(CreativeModeTab.b);
    }

    public int getDropData(IBlockData iblockdata) {
        return ((BlockRedSandstone.a) iblockdata.get(BlockRedSandstone.TYPE)).a();
    }

    public IBlockData fromLegacyData(int i) {
        return this.getBlockData().set(BlockRedSandstone.TYPE, BlockRedSandstone.a.a(i));
    }

    public int toLegacyData(IBlockData iblockdata) {
        return ((BlockRedSandstone.a) iblockdata.get(BlockRedSandstone.TYPE)).a();
    }

    protected BlockStateList getStateList() {
        return new BlockStateList(this, new IBlockState[] { BlockRedSandstone.TYPE});
    }

    public static enum a implements INamable {

        DEFAULT(0, "red_sandstone", "default"), CHISELED(1, "chiseled_red_sandstone", "chiseled"), SMOOTH(2, "smooth_red_sandstone", "smooth");

        private static final BlockRedSandstone.a[] d = new BlockRedSandstone.a[values().length];
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

        public static BlockRedSandstone.a a(int i) {
            if (i < 0 || i >= BlockRedSandstone.a.d.length) {
                i = 0;
            }

            return BlockRedSandstone.a.d[i];
        }

        public String getName() {
            return this.f;
        }

        public String c() {
            return this.g;
        }

        static {
            BlockRedSandstone.a[] ablockredsandstone_a = values();
            int i = ablockredsandstone_a.length;

            for (int j = 0; j < i; ++j) {
                BlockRedSandstone.a blockredsandstone_a = ablockredsandstone_a[j];

                BlockRedSandstone.a.d[blockredsandstone_a.a()] = blockredsandstone_a;
            }

        }
    }
}
