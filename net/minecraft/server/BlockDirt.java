package net.minecraft.server;

public class BlockDirt extends Block {

    public static final BlockStateEnum<BlockDirt.a> VARIANT = BlockStateEnum.of("variant", BlockDirt.a.class);
    public static final BlockStateBoolean SNOWY = BlockStateBoolean.of("snowy");

    protected BlockDirt() {
        super(Material.EARTH);
        this.j(this.blockStateList.getBlockData().set(BlockDirt.VARIANT, BlockDirt.a.DIRT).set(BlockDirt.SNOWY, Boolean.valueOf(false)));
        this.a(CreativeModeTab.b);
    }

    public MaterialMapColor g(IBlockData iblockdata) {
        return ((BlockDirt.a) iblockdata.get(BlockDirt.VARIANT)).d();
    }

    public IBlockData updateState(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
        if (iblockdata.get(BlockDirt.VARIANT) == BlockDirt.a.PODZOL) {
            Block block = iblockaccess.getType(blockposition.up()).getBlock();

            iblockdata = iblockdata.set(BlockDirt.SNOWY, Boolean.valueOf(block == Blocks.SNOW || block == Blocks.SNOW_LAYER));
        }

        return iblockdata;
    }

    public int getDropData(World world, BlockPosition blockposition) {
        IBlockData iblockdata = world.getType(blockposition);

        return iblockdata.getBlock() != this ? 0 : ((BlockDirt.a) iblockdata.get(BlockDirt.VARIANT)).a();
    }

    public IBlockData fromLegacyData(int i) {
        return this.getBlockData().set(BlockDirt.VARIANT, BlockDirt.a.a(i));
    }

    public int toLegacyData(IBlockData iblockdata) {
        return ((BlockDirt.a) iblockdata.get(BlockDirt.VARIANT)).a();
    }

    protected BlockStateList getStateList() {
        return new BlockStateList(this, new IBlockState[] { BlockDirt.VARIANT, BlockDirt.SNOWY});
    }

    public int getDropData(IBlockData iblockdata) {
        BlockDirt.a blockdirt_a = (BlockDirt.a) iblockdata.get(BlockDirt.VARIANT);

        if (blockdirt_a == BlockDirt.a.PODZOL) {
            blockdirt_a = BlockDirt.a.DIRT;
        }

        return blockdirt_a.a();
    }

    public static enum a implements INamable {

        DIRT(0, "dirt", "default", MaterialMapColor.l), COARSE_DIRT(1, "coarse_dirt", "coarse", MaterialMapColor.l), PODZOL(2, "podzol", MaterialMapColor.J);

        private static final BlockDirt.a[] d = new BlockDirt.a[values().length];
        private final int e;
        private final String f;
        private final String g;
        private final MaterialMapColor h;

        private a(int i, String s, MaterialMapColor materialmapcolor) {
            this(i, s, s, materialmapcolor);
        }

        private a(int i, String s, String s1, MaterialMapColor materialmapcolor) {
            this.e = i;
            this.f = s;
            this.g = s1;
            this.h = materialmapcolor;
        }

        public int a() {
            return this.e;
        }

        public String c() {
            return this.g;
        }

        public MaterialMapColor d() {
            return this.h;
        }

        public String toString() {
            return this.f;
        }

        public static BlockDirt.a a(int i) {
            if (i < 0 || i >= BlockDirt.a.d.length) {
                i = 0;
            }

            return BlockDirt.a.d[i];
        }

        public String getName() {
            return this.f;
        }

        static {
            BlockDirt.a[] ablockdirt_a = values();
            int i = ablockdirt_a.length;

            for (int j = 0; j < i; ++j) {
                BlockDirt.a blockdirt_a = ablockdirt_a[j];

                BlockDirt.a.d[blockdirt_a.a()] = blockdirt_a;
            }

        }
    }
}
