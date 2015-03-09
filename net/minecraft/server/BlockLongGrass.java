package net.minecraft.server;

import java.util.Random;

public class BlockLongGrass extends BlockPlant implements IBlockFragilePlantElement {

    public static final BlockStateEnum<BlockLongGrass.a> TYPE = BlockStateEnum.of("type", BlockLongGrass.a.class);

    protected BlockLongGrass() {
        super(Material.REPLACEABLE_PLANT);
        this.j(this.blockStateList.getBlockData().set(BlockLongGrass.TYPE, BlockLongGrass.a.DEAD_BUSH));
        float f = 0.4F;

        this.a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.8F, 0.5F + f);
    }

    public boolean f(World world, BlockPosition blockposition, IBlockData iblockdata) {
        return this.c(world.getType(blockposition.down()).getBlock());
    }

    public boolean a(World world, BlockPosition blockposition) {
        return true;
    }

    public Item getDropType(IBlockData iblockdata, Random random, int i) {
        return random.nextInt(8) == 0 ? Items.WHEAT_SEEDS : null;
    }

    public int getDropCount(int i, Random random) {
        return 1 + random.nextInt(i * 2 + 1);
    }

    public void a(World world, EntityHuman entityhuman, BlockPosition blockposition, IBlockData iblockdata, TileEntity tileentity) {
        if (!world.isClientSide && entityhuman.bZ() != null && entityhuman.bZ().getItem() == Items.SHEARS) {
            entityhuman.b(StatisticList.MINE_BLOCK_COUNT[Block.getId(this)]);
            a(world, blockposition, new ItemStack(Blocks.TALLGRASS, 1, ((BlockLongGrass.a) iblockdata.get(BlockLongGrass.TYPE)).a()));
        } else {
            super.a(world, entityhuman, blockposition, iblockdata, tileentity);
        }

    }

    public int getDropData(World world, BlockPosition blockposition) {
        IBlockData iblockdata = world.getType(blockposition);

        return iblockdata.getBlock().toLegacyData(iblockdata);
    }

    public boolean a(World world, BlockPosition blockposition, IBlockData iblockdata, boolean flag) {
        return iblockdata.get(BlockLongGrass.TYPE) != BlockLongGrass.a.DEAD_BUSH;
    }

    public boolean a(World world, Random random, BlockPosition blockposition, IBlockData iblockdata) {
        return true;
    }

    public void b(World world, Random random, BlockPosition blockposition, IBlockData iblockdata) {
        BlockTallPlant.b blocktallplant_b = BlockTallPlant.b.GRASS;

        if (iblockdata.get(BlockLongGrass.TYPE) == BlockLongGrass.a.FERN) {
            blocktallplant_b = BlockTallPlant.b.FERN;
        }

        if (Blocks.DOUBLE_PLANT.canPlace(world, blockposition)) {
            Blocks.DOUBLE_PLANT.a(world, blockposition, blocktallplant_b, 2);
        }

    }

    public IBlockData fromLegacyData(int i) {
        return this.getBlockData().set(BlockLongGrass.TYPE, BlockLongGrass.a.a(i));
    }

    public int toLegacyData(IBlockData iblockdata) {
        return ((BlockLongGrass.a) iblockdata.get(BlockLongGrass.TYPE)).a();
    }

    protected BlockStateList getStateList() {
        return new BlockStateList(this, new IBlockState[] { BlockLongGrass.TYPE});
    }

    public static enum a implements INamable {

        DEAD_BUSH(0, "dead_bush"), GRASS(1, "tall_grass"), FERN(2, "fern");

        private static final BlockLongGrass.a[] d = new BlockLongGrass.a[values().length];
        private final int e;
        private final String f;

        private a(int i, String s) {
            this.e = i;
            this.f = s;
        }

        public int a() {
            return this.e;
        }

        public String toString() {
            return this.f;
        }

        public static BlockLongGrass.a a(int i) {
            if (i < 0 || i >= BlockLongGrass.a.d.length) {
                i = 0;
            }

            return BlockLongGrass.a.d[i];
        }

        public String getName() {
            return this.f;
        }

        static {
            BlockLongGrass.a[] ablocklonggrass_a = values();
            int i = ablocklonggrass_a.length;

            for (int j = 0; j < i; ++j) {
                BlockLongGrass.a blocklonggrass_a = ablocklonggrass_a[j];

                BlockLongGrass.a.d[blocklonggrass_a.a()] = blocklonggrass_a;
            }

        }
    }
}
