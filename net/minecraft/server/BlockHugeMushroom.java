package net.minecraft.server;

import java.util.Random;

public class BlockHugeMushroom extends Block {

    public static final BlockStateEnum<BlockHugeMushroom.a> VARIANT = BlockStateEnum.of("variant", BlockHugeMushroom.a.class);
    private final Block b;

    public BlockHugeMushroom(Material material, MaterialMapColor materialmapcolor, Block block) {
        super(material, materialmapcolor);
        this.j(this.blockStateList.getBlockData().set(BlockHugeMushroom.VARIANT, BlockHugeMushroom.a.ALL_OUTSIDE));
        this.b = block;
    }

    public int a(Random random) {
        return Math.max(0, random.nextInt(10) - 7);
    }

    public MaterialMapColor g(IBlockData iblockdata) {
        switch (BlockHugeMushroom.SyntheticClass_1.a[((BlockHugeMushroom.a) iblockdata.get(BlockHugeMushroom.VARIANT)).ordinal()]) {
        case 1:
            return MaterialMapColor.e;

        case 2:
            return MaterialMapColor.d;

        case 3:
            return MaterialMapColor.d;

        default:
            return super.g(iblockdata);
        }
    }

    public Item getDropType(IBlockData iblockdata, Random random, int i) {
        return Item.getItemOf(this.b);
    }

    public IBlockData getPlacedState(World world, BlockPosition blockposition, EnumDirection enumdirection, float f, float f1, float f2, int i, EntityLiving entityliving) {
        return this.getBlockData();
    }

    public IBlockData fromLegacyData(int i) {
        return this.getBlockData().set(BlockHugeMushroom.VARIANT, BlockHugeMushroom.a.a(i));
    }

    public int toLegacyData(IBlockData iblockdata) {
        return ((BlockHugeMushroom.a) iblockdata.get(BlockHugeMushroom.VARIANT)).a();
    }

    protected BlockStateList getStateList() {
        return new BlockStateList(this, new IBlockState[] { BlockHugeMushroom.VARIANT});
    }

    static class SyntheticClass_1 {

        static final int[] a = new int[BlockHugeMushroom.a.values().length];

        static {
            try {
                BlockHugeMushroom.SyntheticClass_1.a[BlockHugeMushroom.a.ALL_STEM.ordinal()] = 1;
            } catch (NoSuchFieldError nosuchfielderror) {
                ;
            }

            try {
                BlockHugeMushroom.SyntheticClass_1.a[BlockHugeMushroom.a.ALL_INSIDE.ordinal()] = 2;
            } catch (NoSuchFieldError nosuchfielderror1) {
                ;
            }

            try {
                BlockHugeMushroom.SyntheticClass_1.a[BlockHugeMushroom.a.STEM.ordinal()] = 3;
            } catch (NoSuchFieldError nosuchfielderror2) {
                ;
            }

        }
    }

    public static enum a implements INamable {

        NORTH_WEST(1, "north_west"), NORTH(2, "north"), NORTH_EAST(3, "north_east"), WEST(4, "west"), CENTER(5, "center"), EAST(6, "east"), SOUTH_WEST(7, "south_west"), SOUTH(8, "south"), SOUTH_EAST(9, "south_east"), STEM(10, "stem"), ALL_INSIDE(0, "all_inside"), ALL_OUTSIDE(14, "all_outside"), ALL_STEM(15, "all_stem");

        private static final BlockHugeMushroom.a[] n = new BlockHugeMushroom.a[16];
        private final int o;
        private final String p;

        private a(int i, String s) {
            this.o = i;
            this.p = s;
        }

        public int a() {
            return this.o;
        }

        public String toString() {
            return this.p;
        }

        public static BlockHugeMushroom.a a(int i) {
            if (i < 0 || i >= BlockHugeMushroom.a.n.length) {
                i = 0;
            }

            BlockHugeMushroom.a blockhugemushroom_a = BlockHugeMushroom.a.n[i];

            return blockhugemushroom_a == null ? BlockHugeMushroom.a.n[0] : blockhugemushroom_a;
        }

        public String getName() {
            return this.p;
        }

        static {
            BlockHugeMushroom.a[] ablockhugemushroom_a = values();
            int i = ablockhugemushroom_a.length;

            for (int j = 0; j < i; ++j) {
                BlockHugeMushroom.a blockhugemushroom_a = ablockhugemushroom_a[j];

                BlockHugeMushroom.a.n[blockhugemushroom_a.a()] = blockhugemushroom_a;
            }

        }
    }
}
