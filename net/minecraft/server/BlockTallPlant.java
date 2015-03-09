package net.minecraft.server;

import java.util.Random;

public class BlockTallPlant extends BlockPlant implements IBlockFragilePlantElement {

    public static final BlockStateEnum<BlockTallPlant.b> VARIANT = BlockStateEnum.of("variant", BlockTallPlant.b.class);
    public static final BlockStateEnum<BlockTallPlant.a> HALF = BlockStateEnum.of("half", BlockTallPlant.a.class);
    public static final BlockStateEnum<EnumDirection> N = BlockDirectional.FACING;

    public BlockTallPlant() {
        super(Material.REPLACEABLE_PLANT);
        this.j(this.blockStateList.getBlockData().set(BlockTallPlant.VARIANT, BlockTallPlant.b.SUNFLOWER).set(BlockTallPlant.HALF, BlockTallPlant.a.LOWER).set(BlockTallPlant.N, EnumDirection.NORTH));
        this.c(0.0F);
        this.a(BlockTallPlant.h);
        this.c("doublePlant");
    }

    public void updateShape(IBlockAccess iblockaccess, BlockPosition blockposition) {
        this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    public BlockTallPlant.b e(IBlockAccess iblockaccess, BlockPosition blockposition) {
        IBlockData iblockdata = iblockaccess.getType(blockposition);

        if (iblockdata.getBlock() == this) {
            iblockdata = this.updateState(iblockdata, iblockaccess, blockposition);
            return (BlockTallPlant.b) iblockdata.get(BlockTallPlant.VARIANT);
        } else {
            return BlockTallPlant.b.FERN;
        }
    }

    public boolean canPlace(World world, BlockPosition blockposition) {
        return super.canPlace(world, blockposition) && world.isEmpty(blockposition.up());
    }

    public boolean a(World world, BlockPosition blockposition) {
        IBlockData iblockdata = world.getType(blockposition);

        if (iblockdata.getBlock() != this) {
            return true;
        } else {
            BlockTallPlant.b blocktallplant_b = (BlockTallPlant.b) this.updateState(iblockdata, world, blockposition).get(BlockTallPlant.VARIANT);

            return blocktallplant_b == BlockTallPlant.b.FERN || blocktallplant_b == BlockTallPlant.b.GRASS;
        }
    }

    protected void e(World world, BlockPosition blockposition, IBlockData iblockdata) {
        if (!this.f(world, blockposition, iblockdata)) {
            boolean flag = iblockdata.get(BlockTallPlant.HALF) == BlockTallPlant.a.UPPER;
            BlockPosition blockposition1 = flag ? blockposition : blockposition.up();
            BlockPosition blockposition2 = flag ? blockposition.down() : blockposition;
            Object object = flag ? this : world.getType(blockposition1).getBlock();
            Object object1 = flag ? world.getType(blockposition2).getBlock() : this;

            if (object == this) {
                world.setTypeAndData(blockposition1, Blocks.AIR.getBlockData(), 2);
            }

            if (object1 == this) {
                world.setTypeAndData(blockposition2, Blocks.AIR.getBlockData(), 3);
                if (!flag) {
                    this.b(world, blockposition2, iblockdata, 0);
                }
            }

        }
    }

    public boolean f(World world, BlockPosition blockposition, IBlockData iblockdata) {
        if (iblockdata.get(BlockTallPlant.HALF) == BlockTallPlant.a.UPPER) {
            return world.getType(blockposition.down()).getBlock() == this;
        } else {
            IBlockData iblockdata1 = world.getType(blockposition.up());

            return iblockdata1.getBlock() == this && super.f(world, blockposition, iblockdata1);
        }
    }

    public Item getDropType(IBlockData iblockdata, Random random, int i) {
        if (iblockdata.get(BlockTallPlant.HALF) == BlockTallPlant.a.UPPER) {
            return null;
        } else {
            BlockTallPlant.b blocktallplant_b = (BlockTallPlant.b) iblockdata.get(BlockTallPlant.VARIANT);

            return blocktallplant_b == BlockTallPlant.b.FERN ? null : (blocktallplant_b == BlockTallPlant.b.GRASS ? (random.nextInt(8) == 0 ? Items.WHEAT_SEEDS : null) : Item.getItemOf(this));
        }
    }

    public int getDropData(IBlockData iblockdata) {
        return iblockdata.get(BlockTallPlant.HALF) != BlockTallPlant.a.UPPER && iblockdata.get(BlockTallPlant.VARIANT) != BlockTallPlant.b.GRASS ? ((BlockTallPlant.b) iblockdata.get(BlockTallPlant.VARIANT)).a() : 0;
    }

    public void a(World world, BlockPosition blockposition, BlockTallPlant.b blocktallplant_b, int i) {
        world.setTypeAndData(blockposition, this.getBlockData().set(BlockTallPlant.HALF, BlockTallPlant.a.LOWER).set(BlockTallPlant.VARIANT, blocktallplant_b), i);
        world.setTypeAndData(blockposition.up(), this.getBlockData().set(BlockTallPlant.HALF, BlockTallPlant.a.UPPER), i);
    }

    public void postPlace(World world, BlockPosition blockposition, IBlockData iblockdata, EntityLiving entityliving, ItemStack itemstack) {
        world.setTypeAndData(blockposition.up(), this.getBlockData().set(BlockTallPlant.HALF, BlockTallPlant.a.UPPER), 2);
    }

    public void a(World world, EntityHuman entityhuman, BlockPosition blockposition, IBlockData iblockdata, TileEntity tileentity) {
        if (world.isClientSide || entityhuman.bZ() == null || entityhuman.bZ().getItem() != Items.SHEARS || iblockdata.get(BlockTallPlant.HALF) != BlockTallPlant.a.LOWER || !this.b(world, blockposition, iblockdata, entityhuman)) {
            super.a(world, entityhuman, blockposition, iblockdata, tileentity);
        }
    }

    public void a(World world, BlockPosition blockposition, IBlockData iblockdata, EntityHuman entityhuman) {
        if (iblockdata.get(BlockTallPlant.HALF) == BlockTallPlant.a.UPPER) {
            if (world.getType(blockposition.down()).getBlock() == this) {
                if (!entityhuman.abilities.canInstantlyBuild) {
                    IBlockData iblockdata1 = world.getType(blockposition.down());
                    BlockTallPlant.b blocktallplant_b = (BlockTallPlant.b) iblockdata1.get(BlockTallPlant.VARIANT);

                    if (blocktallplant_b != BlockTallPlant.b.FERN && blocktallplant_b != BlockTallPlant.b.GRASS) {
                        world.setAir(blockposition.down(), true);
                    } else if (!world.isClientSide) {
                        if (entityhuman.bZ() != null && entityhuman.bZ().getItem() == Items.SHEARS) {
                            this.b(world, blockposition, iblockdata1, entityhuman);
                            world.setAir(blockposition.down());
                        } else {
                            world.setAir(blockposition.down(), true);
                        }
                    } else {
                        world.setAir(blockposition.down());
                    }
                } else {
                    world.setAir(blockposition.down());
                }
            }
        } else if (entityhuman.abilities.canInstantlyBuild && world.getType(blockposition.up()).getBlock() == this) {
            world.setTypeAndData(blockposition.up(), Blocks.AIR.getBlockData(), 2);
        }

        super.a(world, blockposition, iblockdata, entityhuman);
    }

    private boolean b(World world, BlockPosition blockposition, IBlockData iblockdata, EntityHuman entityhuman) {
        BlockTallPlant.b blocktallplant_b = (BlockTallPlant.b) iblockdata.get(BlockTallPlant.VARIANT);

        if (blocktallplant_b != BlockTallPlant.b.FERN && blocktallplant_b != BlockTallPlant.b.GRASS) {
            return false;
        } else {
            entityhuman.b(StatisticList.MINE_BLOCK_COUNT[Block.getId(this)]);
            int i = (blocktallplant_b == BlockTallPlant.b.GRASS ? BlockLongGrass.a.GRASS : BlockLongGrass.a.FERN).a();

            a(world, blockposition, new ItemStack(Blocks.TALLGRASS, 2, i));
            return true;
        }
    }

    public int getDropData(World world, BlockPosition blockposition) {
        return this.e(world, blockposition).a();
    }

    public boolean a(World world, BlockPosition blockposition, IBlockData iblockdata, boolean flag) {
        BlockTallPlant.b blocktallplant_b = this.e(world, blockposition);

        return blocktallplant_b != BlockTallPlant.b.GRASS && blocktallplant_b != BlockTallPlant.b.FERN;
    }

    public boolean a(World world, Random random, BlockPosition blockposition, IBlockData iblockdata) {
        return true;
    }

    public void b(World world, Random random, BlockPosition blockposition, IBlockData iblockdata) {
        a(world, blockposition, new ItemStack(this, 1, this.e(world, blockposition).a()));
    }

    public IBlockData fromLegacyData(int i) {
        return (i & 8) > 0 ? this.getBlockData().set(BlockTallPlant.HALF, BlockTallPlant.a.UPPER) : this.getBlockData().set(BlockTallPlant.HALF, BlockTallPlant.a.LOWER).set(BlockTallPlant.VARIANT, BlockTallPlant.b.a(i & 7));
    }

    public IBlockData updateState(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
        if (iblockdata.get(BlockTallPlant.HALF) == BlockTallPlant.a.UPPER) {
            IBlockData iblockdata1 = iblockaccess.getType(blockposition.down());

            if (iblockdata1.getBlock() == this) {
                iblockdata = iblockdata.set(BlockTallPlant.VARIANT, iblockdata1.get(BlockTallPlant.VARIANT));
            }
        }

        return iblockdata;
    }

    public int toLegacyData(IBlockData iblockdata) {
        return iblockdata.get(BlockTallPlant.HALF) == BlockTallPlant.a.UPPER ? 8 | ((EnumDirection) iblockdata.get(BlockTallPlant.N)).b() : ((BlockTallPlant.b) iblockdata.get(BlockTallPlant.VARIANT)).a();
    }

    protected BlockStateList getStateList() {
        return new BlockStateList(this, new IBlockState[] { BlockTallPlant.HALF, BlockTallPlant.VARIANT, BlockTallPlant.N});
    }

    public static enum a implements INamable {

        UPPER, LOWER;

        private a() {}

        public String toString() {
            return this.getName();
        }

        public String getName() {
            return this == BlockTallPlant.a.UPPER ? "upper" : "lower";
        }
    }

    public static enum b implements INamable {

        SUNFLOWER(0, "sunflower"), SYRINGA(1, "syringa"), GRASS(2, "double_grass", "grass"), FERN(3, "double_fern", "fern"), ROSE(4, "double_rose", "rose"), PAEONIA(5, "paeonia");

        private static final BlockTallPlant.b[] g = new BlockTallPlant.b[values().length];
        private final int h;
        private final String i;
        private final String j;

        private b(int i, String s) {
            this(i, s, s);
        }

        private b(int i, String s, String s1) {
            this.h = i;
            this.i = s;
            this.j = s1;
        }

        public int a() {
            return this.h;
        }

        public String toString() {
            return this.i;
        }

        public static BlockTallPlant.b a(int i) {
            if (i < 0 || i >= BlockTallPlant.b.g.length) {
                i = 0;
            }

            return BlockTallPlant.b.g[i];
        }

        public String getName() {
            return this.i;
        }

        public String c() {
            return this.j;
        }

        static {
            BlockTallPlant.b[] ablocktallplant_b = values();
            int i = ablocktallplant_b.length;

            for (int j = 0; j < i; ++j) {
                BlockTallPlant.b blocktallplant_b = ablocktallplant_b[j];

                BlockTallPlant.b.g[blocktallplant_b.a()] = blocktallplant_b;
            }

        }
    }
}
