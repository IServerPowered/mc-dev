package net.minecraft.server;

import java.util.Random;

public abstract class BlockDoubleStepAbstract extends BlockStepAbstract {

    public static final BlockStateBoolean SEAMLESS = BlockStateBoolean.of("seamless");
    public static final BlockStateEnum<BlockDoubleStepAbstract.a> VARIANT = BlockStateEnum.of("variant", BlockDoubleStepAbstract.a.class);

    public BlockDoubleStepAbstract() {
        super(Material.STONE);
        IBlockData iblockdata = this.blockStateList.getBlockData();

        if (this.l()) {
            iblockdata = iblockdata.set(BlockDoubleStepAbstract.SEAMLESS, Boolean.valueOf(false));
        } else {
            iblockdata = iblockdata.set(BlockDoubleStepAbstract.HALF, BlockStepAbstract.a.BOTTOM);
        }

        this.j(iblockdata.set(BlockDoubleStepAbstract.VARIANT, BlockDoubleStepAbstract.a.STONE));
        this.a(CreativeModeTab.b);
    }

    public Item getDropType(IBlockData iblockdata, Random random, int i) {
        return Item.getItemOf(Blocks.STONE_SLAB);
    }

    public String b(int i) {
        return super.a() + "." + BlockDoubleStepAbstract.a.a(i).d();
    }

    public IBlockState<?> n() {
        return BlockDoubleStepAbstract.VARIANT;
    }

    public Object a(ItemStack itemstack) {
        return BlockDoubleStepAbstract.a.a(itemstack.getData() & 7);
    }

    public IBlockData fromLegacyData(int i) {
        IBlockData iblockdata = this.getBlockData().set(BlockDoubleStepAbstract.VARIANT, BlockDoubleStepAbstract.a.a(i & 7));

        if (this.l()) {
            iblockdata = iblockdata.set(BlockDoubleStepAbstract.SEAMLESS, Boolean.valueOf((i & 8) != 0));
        } else {
            iblockdata = iblockdata.set(BlockDoubleStepAbstract.HALF, (i & 8) == 0 ? BlockStepAbstract.a.BOTTOM : BlockStepAbstract.a.TOP);
        }

        return iblockdata;
    }

    public int toLegacyData(IBlockData iblockdata) {
        byte b0 = 0;
        int i = b0 | ((BlockDoubleStepAbstract.a) iblockdata.get(BlockDoubleStepAbstract.VARIANT)).a();

        if (this.l()) {
            if (((Boolean) iblockdata.get(BlockDoubleStepAbstract.SEAMLESS)).booleanValue()) {
                i |= 8;
            }
        } else if (iblockdata.get(BlockDoubleStepAbstract.HALF) == BlockStepAbstract.a.TOP) {
            i |= 8;
        }

        return i;
    }

    protected BlockStateList getStateList() {
        return this.l() ? new BlockStateList(this, new IBlockState[] { BlockDoubleStepAbstract.SEAMLESS, BlockDoubleStepAbstract.VARIANT}) : new BlockStateList(this, new IBlockState[] { BlockDoubleStepAbstract.HALF, BlockDoubleStepAbstract.VARIANT});
    }

    public int getDropData(IBlockData iblockdata) {
        return ((BlockDoubleStepAbstract.a) iblockdata.get(BlockDoubleStepAbstract.VARIANT)).a();
    }

    public MaterialMapColor g(IBlockData iblockdata) {
        return ((BlockDoubleStepAbstract.a) iblockdata.get(BlockDoubleStepAbstract.VARIANT)).c();
    }

    public static enum a implements INamable {

        STONE(0, MaterialMapColor.m, "stone"), SAND(1, MaterialMapColor.d, "sandstone", "sand"), WOOD(2, MaterialMapColor.o, "wood_old", "wood"), COBBLESTONE(3, MaterialMapColor.m, "cobblestone", "cobble"), BRICK(4, MaterialMapColor.D, "brick"), SMOOTHBRICK(5, MaterialMapColor.m, "stone_brick", "smoothStoneBrick"), NETHERBRICK(6, MaterialMapColor.K, "nether_brick", "netherBrick"), QUARTZ(7, MaterialMapColor.p, "quartz");

        private static final BlockDoubleStepAbstract.a[] i = new BlockDoubleStepAbstract.a[values().length];
        private final int j;
        private final MaterialMapColor k;
        private final String l;
        private final String m;

        private a(int i, MaterialMapColor materialmapcolor, String s) {
            this(i, materialmapcolor, s, s);
        }

        private a(int i, MaterialMapColor materialmapcolor, String s, String s1) {
            this.j = i;
            this.k = materialmapcolor;
            this.l = s;
            this.m = s1;
        }

        public int a() {
            return this.j;
        }

        public MaterialMapColor c() {
            return this.k;
        }

        public String toString() {
            return this.l;
        }

        public static BlockDoubleStepAbstract.a a(int i) {
            if (i < 0 || i >= BlockDoubleStepAbstract.a.i.length) {
                i = 0;
            }

            return BlockDoubleStepAbstract.a.i[i];
        }

        public String getName() {
            return this.l;
        }

        public String d() {
            return this.m;
        }

        static {
            BlockDoubleStepAbstract.a[] ablockdoublestepabstract_a = values();
            int i = ablockdoublestepabstract_a.length;

            for (int j = 0; j < i; ++j) {
                BlockDoubleStepAbstract.a blockdoublestepabstract_a = ablockdoublestepabstract_a[j];

                BlockDoubleStepAbstract.a.i[blockdoublestepabstract_a.a()] = blockdoublestepabstract_a;
            }

        }
    }
}
