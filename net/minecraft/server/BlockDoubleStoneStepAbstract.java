package net.minecraft.server;

import java.util.Random;

public abstract class BlockDoubleStoneStepAbstract extends BlockStepAbstract {

    public static final BlockStateBoolean SEAMLESS = BlockStateBoolean.of("seamless");
    public static final BlockStateEnum<BlockDoubleStoneStepAbstract.a> VARIANT = BlockStateEnum.of("variant", BlockDoubleStoneStepAbstract.a.class);

    public BlockDoubleStoneStepAbstract() {
        super(Material.STONE);
        IBlockData iblockdata = this.blockStateList.getBlockData();

        if (this.l()) {
            iblockdata = iblockdata.set(BlockDoubleStoneStepAbstract.SEAMLESS, Boolean.valueOf(false));
        } else {
            iblockdata = iblockdata.set(BlockDoubleStoneStepAbstract.HALF, BlockStepAbstract.a.BOTTOM);
        }

        this.j(iblockdata.set(BlockDoubleStoneStepAbstract.VARIANT, BlockDoubleStoneStepAbstract.a.RED_SANDSTONE));
        this.a(CreativeModeTab.b);
    }

    public String getName() {
        return LocaleI18n.get(this.a() + ".red_sandstone.name");
    }

    public Item getDropType(IBlockData iblockdata, Random random, int i) {
        return Item.getItemOf(Blocks.STONE_SLAB2);
    }

    public String b(int i) {
        return super.a() + "." + BlockDoubleStoneStepAbstract.a.a(i).d();
    }

    public IBlockState<?> n() {
        return BlockDoubleStoneStepAbstract.VARIANT;
    }

    public Object a(ItemStack itemstack) {
        return BlockDoubleStoneStepAbstract.a.a(itemstack.getData() & 7);
    }

    public IBlockData fromLegacyData(int i) {
        IBlockData iblockdata = this.getBlockData().set(BlockDoubleStoneStepAbstract.VARIANT, BlockDoubleStoneStepAbstract.a.a(i & 7));

        if (this.l()) {
            iblockdata = iblockdata.set(BlockDoubleStoneStepAbstract.SEAMLESS, Boolean.valueOf((i & 8) != 0));
        } else {
            iblockdata = iblockdata.set(BlockDoubleStoneStepAbstract.HALF, (i & 8) == 0 ? BlockStepAbstract.a.BOTTOM : BlockStepAbstract.a.TOP);
        }

        return iblockdata;
    }

    public int toLegacyData(IBlockData iblockdata) {
        byte b0 = 0;
        int i = b0 | ((BlockDoubleStoneStepAbstract.a) iblockdata.get(BlockDoubleStoneStepAbstract.VARIANT)).a();

        if (this.l()) {
            if (((Boolean) iblockdata.get(BlockDoubleStoneStepAbstract.SEAMLESS)).booleanValue()) {
                i |= 8;
            }
        } else if (iblockdata.get(BlockDoubleStoneStepAbstract.HALF) == BlockStepAbstract.a.TOP) {
            i |= 8;
        }

        return i;
    }

    protected BlockStateList getStateList() {
        return this.l() ? new BlockStateList(this, new IBlockState[] { BlockDoubleStoneStepAbstract.SEAMLESS, BlockDoubleStoneStepAbstract.VARIANT}) : new BlockStateList(this, new IBlockState[] { BlockDoubleStoneStepAbstract.HALF, BlockDoubleStoneStepAbstract.VARIANT});
    }

    public MaterialMapColor g(IBlockData iblockdata) {
        return ((BlockDoubleStoneStepAbstract.a) iblockdata.get(BlockDoubleStoneStepAbstract.VARIANT)).c();
    }

    public int getDropData(IBlockData iblockdata) {
        return ((BlockDoubleStoneStepAbstract.a) iblockdata.get(BlockDoubleStoneStepAbstract.VARIANT)).a();
    }

    public static enum a implements INamable {

        RED_SANDSTONE(0, "red_sandstone", BlockSand.a.RED_SAND.c());

        private static final BlockDoubleStoneStepAbstract.a[] b = new BlockDoubleStoneStepAbstract.a[values().length];
        private final int c;
        private final String d;
        private final MaterialMapColor e;

        private a(int i, String s, MaterialMapColor materialmapcolor) {
            this.c = i;
            this.d = s;
            this.e = materialmapcolor;
        }

        public int a() {
            return this.c;
        }

        public MaterialMapColor c() {
            return this.e;
        }

        public String toString() {
            return this.d;
        }

        public static BlockDoubleStoneStepAbstract.a a(int i) {
            if (i < 0 || i >= BlockDoubleStoneStepAbstract.a.b.length) {
                i = 0;
            }

            return BlockDoubleStoneStepAbstract.a.b[i];
        }

        public String getName() {
            return this.d;
        }

        public String d() {
            return this.d;
        }

        static {
            BlockDoubleStoneStepAbstract.a[] ablockdoublestonestepabstract_a = values();
            int i = ablockdoublestonestepabstract_a.length;

            for (int j = 0; j < i; ++j) {
                BlockDoubleStoneStepAbstract.a blockdoublestonestepabstract_a = ablockdoublestonestepabstract_a[j];

                BlockDoubleStoneStepAbstract.a.b[blockdoublestonestepabstract_a.a()] = blockdoublestonestepabstract_a;
            }

        }
    }
}
