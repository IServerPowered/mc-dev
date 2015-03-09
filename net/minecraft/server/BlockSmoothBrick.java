package net.minecraft.server;

public class BlockSmoothBrick extends Block {

    public static final BlockStateEnum<BlockSmoothBrick.a> VARIANT = BlockStateEnum.of("variant", BlockSmoothBrick.a.class);
    public static final int b = BlockSmoothBrick.a.DEFAULT.a();
    public static final int N = BlockSmoothBrick.a.MOSSY.a();
    public static final int O = BlockSmoothBrick.a.CRACKED.a();
    public static final int P = BlockSmoothBrick.a.CHISELED.a();

    public BlockSmoothBrick() {
        super(Material.STONE);
        this.j(this.blockStateList.getBlockData().set(BlockSmoothBrick.VARIANT, BlockSmoothBrick.a.DEFAULT));
        this.a(CreativeModeTab.b);
    }

    public int getDropData(IBlockData iblockdata) {
        return ((BlockSmoothBrick.a) iblockdata.get(BlockSmoothBrick.VARIANT)).a();
    }

    public IBlockData fromLegacyData(int i) {
        return this.getBlockData().set(BlockSmoothBrick.VARIANT, BlockSmoothBrick.a.a(i));
    }

    public int toLegacyData(IBlockData iblockdata) {
        return ((BlockSmoothBrick.a) iblockdata.get(BlockSmoothBrick.VARIANT)).a();
    }

    protected BlockStateList getStateList() {
        return new BlockStateList(this, new IBlockState[] { BlockSmoothBrick.VARIANT});
    }

    public static enum a implements INamable {

        DEFAULT(0, "stonebrick", "default"), MOSSY(1, "mossy_stonebrick", "mossy"), CRACKED(2, "cracked_stonebrick", "cracked"), CHISELED(3, "chiseled_stonebrick", "chiseled");

        private static final BlockSmoothBrick.a[] e = new BlockSmoothBrick.a[values().length];
        private final int f;
        private final String g;
        private final String h;

        private a(int i, String s, String s1) {
            this.f = i;
            this.g = s;
            this.h = s1;
        }

        public int a() {
            return this.f;
        }

        public String toString() {
            return this.g;
        }

        public static BlockSmoothBrick.a a(int i) {
            if (i < 0 || i >= BlockSmoothBrick.a.e.length) {
                i = 0;
            }

            return BlockSmoothBrick.a.e[i];
        }

        public String getName() {
            return this.g;
        }

        public String c() {
            return this.h;
        }

        static {
            BlockSmoothBrick.a[] ablocksmoothbrick_a = values();
            int i = ablocksmoothbrick_a.length;

            for (int j = 0; j < i; ++j) {
                BlockSmoothBrick.a blocksmoothbrick_a = ablocksmoothbrick_a[j];

                BlockSmoothBrick.a.e[blocksmoothbrick_a.a()] = blocksmoothbrick_a;
            }

        }
    }
}
