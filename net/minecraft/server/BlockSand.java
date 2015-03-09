package net.minecraft.server;

public class BlockSand extends BlockFalling {

    public static final BlockStateEnum<BlockSand.a> VARIANT = BlockStateEnum.of("variant", BlockSand.a.class);

    public BlockSand() {
        this.j(this.blockStateList.getBlockData().set(BlockSand.VARIANT, BlockSand.a.SAND));
    }

    public int getDropData(IBlockData iblockdata) {
        return ((BlockSand.a) iblockdata.get(BlockSand.VARIANT)).a();
    }

    public MaterialMapColor g(IBlockData iblockdata) {
        return ((BlockSand.a) iblockdata.get(BlockSand.VARIANT)).c();
    }

    public IBlockData fromLegacyData(int i) {
        return this.getBlockData().set(BlockSand.VARIANT, BlockSand.a.a(i));
    }

    public int toLegacyData(IBlockData iblockdata) {
        return ((BlockSand.a) iblockdata.get(BlockSand.VARIANT)).a();
    }

    protected BlockStateList getStateList() {
        return new BlockStateList(this, new IBlockState[] { BlockSand.VARIANT});
    }

    public static enum a implements INamable {

        SAND(0, "sand", "default", MaterialMapColor.d), RED_SAND(1, "red_sand", "red", MaterialMapColor.q);

        private static final BlockSand.a[] c = new BlockSand.a[values().length];
        private final int d;
        private final String e;
        private final MaterialMapColor f;
        private final String g;

        private a(int i, String s, String s1, MaterialMapColor materialmapcolor) {
            this.d = i;
            this.e = s;
            this.f = materialmapcolor;
            this.g = s1;
        }

        public int a() {
            return this.d;
        }

        public String toString() {
            return this.e;
        }

        public MaterialMapColor c() {
            return this.f;
        }

        public static BlockSand.a a(int i) {
            if (i < 0 || i >= BlockSand.a.c.length) {
                i = 0;
            }

            return BlockSand.a.c[i];
        }

        public String getName() {
            return this.e;
        }

        public String d() {
            return this.g;
        }

        static {
            BlockSand.a[] ablocksand_a = values();
            int i = ablocksand_a.length;

            for (int j = 0; j < i; ++j) {
                BlockSand.a blocksand_a = ablocksand_a[j];

                BlockSand.a.c[blocksand_a.a()] = blocksand_a;
            }

        }
    }
}
