package net.minecraft.server;

import java.util.Iterator;

public class BlockLever extends Block {

    public static final BlockStateEnum<BlockLever.a> FACING = BlockStateEnum.of("facing", BlockLever.a.class);
    public static final BlockStateBoolean POWERED = BlockStateBoolean.of("powered");

    protected BlockLever() {
        super(Material.ORIENTABLE);
        this.j(this.blockStateList.getBlockData().set(BlockLever.FACING, BlockLever.a.NORTH).set(BlockLever.POWERED, Boolean.valueOf(false)));
        this.a(CreativeModeTab.d);
    }

    public AxisAlignedBB a(World world, BlockPosition blockposition, IBlockData iblockdata) {
        return null;
    }

    public boolean c() {
        return false;
    }

    public boolean d() {
        return false;
    }

    public boolean canPlace(World world, BlockPosition blockposition, EnumDirection enumdirection) {
        return a(world, blockposition, enumdirection.opposite());
    }

    public boolean canPlace(World world, BlockPosition blockposition) {
        EnumDirection[] aenumdirection = EnumDirection.values();
        int i = aenumdirection.length;

        for (int j = 0; j < i; ++j) {
            EnumDirection enumdirection = aenumdirection[j];

            if (a(world, blockposition, enumdirection)) {
                return true;
            }
        }

        return false;
    }

    protected static boolean a(World world, BlockPosition blockposition, EnumDirection enumdirection) {
        return BlockButtonAbstract.a(world, blockposition, enumdirection);
    }

    public IBlockData getPlacedState(World world, BlockPosition blockposition, EnumDirection enumdirection, float f, float f1, float f2, int i, EntityLiving entityliving) {
        IBlockData iblockdata = this.getBlockData().set(BlockLever.POWERED, Boolean.valueOf(false));

        if (a(world, blockposition, enumdirection.opposite())) {
            return iblockdata.set(BlockLever.FACING, BlockLever.a.a(enumdirection, entityliving.getDirection()));
        } else {
            Iterator iterator = EnumDirection.c.HORIZONTAL.iterator();

            EnumDirection enumdirection1;

            do {
                if (!iterator.hasNext()) {
                    if (World.a((IBlockAccess) world, blockposition.down())) {
                        return iblockdata.set(BlockLever.FACING, BlockLever.a.a(EnumDirection.UP, entityliving.getDirection()));
                    }

                    return iblockdata;
                }

                enumdirection1 = (EnumDirection) iterator.next();
            } while (enumdirection1 == enumdirection || !a(world, blockposition, enumdirection1.opposite()));

            return iblockdata.set(BlockLever.FACING, BlockLever.a.a(enumdirection1, entityliving.getDirection()));
        }
    }

    public static int a(EnumDirection enumdirection) {
        switch (BlockLever.SyntheticClass_1.a[enumdirection.ordinal()]) {
        case 1:
            return 0;

        case 2:
            return 5;

        case 3:
            return 4;

        case 4:
            return 3;

        case 5:
            return 2;

        case 6:
            return 1;

        default:
            return -1;
        }
    }

    public void doPhysics(World world, BlockPosition blockposition, IBlockData iblockdata, Block block) {
        if (this.e(world, blockposition, iblockdata) && !a(world, blockposition, ((BlockLever.a) iblockdata.get(BlockLever.FACING)).c().opposite())) {
            this.b(world, blockposition, iblockdata, 0);
            world.setAir(blockposition);
        }

    }

    private boolean e(World world, BlockPosition blockposition, IBlockData iblockdata) {
        if (this.canPlace(world, blockposition)) {
            return true;
        } else {
            this.b(world, blockposition, iblockdata, 0);
            world.setAir(blockposition);
            return false;
        }
    }

    public void updateShape(IBlockAccess iblockaccess, BlockPosition blockposition) {
        float f = 0.1875F;

        switch (BlockLever.SyntheticClass_1.b[((BlockLever.a) iblockaccess.getType(blockposition).get(BlockLever.FACING)).ordinal()]) {
        case 1:
            this.a(0.0F, 0.2F, 0.5F - f, f * 2.0F, 0.8F, 0.5F + f);
            break;

        case 2:
            this.a(1.0F - f * 2.0F, 0.2F, 0.5F - f, 1.0F, 0.8F, 0.5F + f);
            break;

        case 3:
            this.a(0.5F - f, 0.2F, 0.0F, 0.5F + f, 0.8F, f * 2.0F);
            break;

        case 4:
            this.a(0.5F - f, 0.2F, 1.0F - f * 2.0F, 0.5F + f, 0.8F, 1.0F);
            break;

        case 5:
        case 6:
            f = 0.25F;
            this.a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.6F, 0.5F + f);
            break;

        case 7:
        case 8:
            f = 0.25F;
            this.a(0.5F - f, 0.4F, 0.5F - f, 0.5F + f, 1.0F, 0.5F + f);
        }

    }

    public boolean interact(World world, BlockPosition blockposition, IBlockData iblockdata, EntityHuman entityhuman, EnumDirection enumdirection, float f, float f1, float f2) {
        if (world.isClientSide) {
            return true;
        } else {
            iblockdata = iblockdata.a(BlockLever.POWERED);
            world.setTypeAndData(blockposition, iblockdata, 3);
            world.makeSound((double) blockposition.getX() + 0.5D, (double) blockposition.getY() + 0.5D, (double) blockposition.getZ() + 0.5D, "random.click", 0.3F, ((Boolean) iblockdata.get(BlockLever.POWERED)).booleanValue() ? 0.6F : 0.5F);
            world.applyPhysics(blockposition, this);
            EnumDirection enumdirection1 = ((BlockLever.a) iblockdata.get(BlockLever.FACING)).c();

            world.applyPhysics(blockposition.shift(enumdirection1.opposite()), this);
            return true;
        }
    }

    public void remove(World world, BlockPosition blockposition, IBlockData iblockdata) {
        if (((Boolean) iblockdata.get(BlockLever.POWERED)).booleanValue()) {
            world.applyPhysics(blockposition, this);
            EnumDirection enumdirection = ((BlockLever.a) iblockdata.get(BlockLever.FACING)).c();

            world.applyPhysics(blockposition.shift(enumdirection.opposite()), this);
        }

        super.remove(world, blockposition, iblockdata);
    }

    public int a(IBlockAccess iblockaccess, BlockPosition blockposition, IBlockData iblockdata, EnumDirection enumdirection) {
        return ((Boolean) iblockdata.get(BlockLever.POWERED)).booleanValue() ? 15 : 0;
    }

    public int b(IBlockAccess iblockaccess, BlockPosition blockposition, IBlockData iblockdata, EnumDirection enumdirection) {
        return !((Boolean) iblockdata.get(BlockLever.POWERED)).booleanValue() ? 0 : (((BlockLever.a) iblockdata.get(BlockLever.FACING)).c() == enumdirection ? 15 : 0);
    }

    public boolean isPowerSource() {
        return true;
    }

    public IBlockData fromLegacyData(int i) {
        return this.getBlockData().set(BlockLever.FACING, BlockLever.a.a(i & 7)).set(BlockLever.POWERED, Boolean.valueOf((i & 8) > 0));
    }

    public int toLegacyData(IBlockData iblockdata) {
        byte b0 = 0;
        int i = b0 | ((BlockLever.a) iblockdata.get(BlockLever.FACING)).a();

        if (((Boolean) iblockdata.get(BlockLever.POWERED)).booleanValue()) {
            i |= 8;
        }

        return i;
    }

    protected BlockStateList getStateList() {
        return new BlockStateList(this, new IBlockState[] { BlockLever.FACING, BlockLever.POWERED});
    }

    static class SyntheticClass_1 {

        static final int[] a;
        static final int[] b;
        static final int[] c = new int[EnumDirection.a.values().length];

        static {
            try {
                BlockLever.SyntheticClass_1.c[EnumDirection.a.X.ordinal()] = 1;
            } catch (NoSuchFieldError nosuchfielderror) {
                ;
            }

            try {
                BlockLever.SyntheticClass_1.c[EnumDirection.a.Z.ordinal()] = 2;
            } catch (NoSuchFieldError nosuchfielderror1) {
                ;
            }

            b = new int[BlockLever.a.values().length];

            try {
                BlockLever.SyntheticClass_1.b[BlockLever.a.EAST.ordinal()] = 1;
            } catch (NoSuchFieldError nosuchfielderror2) {
                ;
            }

            try {
                BlockLever.SyntheticClass_1.b[BlockLever.a.WEST.ordinal()] = 2;
            } catch (NoSuchFieldError nosuchfielderror3) {
                ;
            }

            try {
                BlockLever.SyntheticClass_1.b[BlockLever.a.SOUTH.ordinal()] = 3;
            } catch (NoSuchFieldError nosuchfielderror4) {
                ;
            }

            try {
                BlockLever.SyntheticClass_1.b[BlockLever.a.NORTH.ordinal()] = 4;
            } catch (NoSuchFieldError nosuchfielderror5) {
                ;
            }

            try {
                BlockLever.SyntheticClass_1.b[BlockLever.a.UP_Z.ordinal()] = 5;
            } catch (NoSuchFieldError nosuchfielderror6) {
                ;
            }

            try {
                BlockLever.SyntheticClass_1.b[BlockLever.a.UP_X.ordinal()] = 6;
            } catch (NoSuchFieldError nosuchfielderror7) {
                ;
            }

            try {
                BlockLever.SyntheticClass_1.b[BlockLever.a.DOWN_X.ordinal()] = 7;
            } catch (NoSuchFieldError nosuchfielderror8) {
                ;
            }

            try {
                BlockLever.SyntheticClass_1.b[BlockLever.a.DOWN_Z.ordinal()] = 8;
            } catch (NoSuchFieldError nosuchfielderror9) {
                ;
            }

            a = new int[EnumDirection.values().length];

            try {
                BlockLever.SyntheticClass_1.a[EnumDirection.DOWN.ordinal()] = 1;
            } catch (NoSuchFieldError nosuchfielderror10) {
                ;
            }

            try {
                BlockLever.SyntheticClass_1.a[EnumDirection.UP.ordinal()] = 2;
            } catch (NoSuchFieldError nosuchfielderror11) {
                ;
            }

            try {
                BlockLever.SyntheticClass_1.a[EnumDirection.NORTH.ordinal()] = 3;
            } catch (NoSuchFieldError nosuchfielderror12) {
                ;
            }

            try {
                BlockLever.SyntheticClass_1.a[EnumDirection.SOUTH.ordinal()] = 4;
            } catch (NoSuchFieldError nosuchfielderror13) {
                ;
            }

            try {
                BlockLever.SyntheticClass_1.a[EnumDirection.WEST.ordinal()] = 5;
            } catch (NoSuchFieldError nosuchfielderror14) {
                ;
            }

            try {
                BlockLever.SyntheticClass_1.a[EnumDirection.EAST.ordinal()] = 6;
            } catch (NoSuchFieldError nosuchfielderror15) {
                ;
            }

        }
    }

    public static enum a implements INamable {

        DOWN_X(0, "down_x", EnumDirection.DOWN), EAST(1, "east", EnumDirection.EAST), WEST(2, "west", EnumDirection.WEST), SOUTH(3, "south", EnumDirection.SOUTH), NORTH(4, "north", EnumDirection.NORTH), UP_Z(5, "up_z", EnumDirection.UP), UP_X(6, "up_x", EnumDirection.UP), DOWN_Z(7, "down_z", EnumDirection.DOWN);

        private static final BlockLever.a[] i = new BlockLever.a[values().length];
        private final int j;
        private final String k;
        private final EnumDirection l;

        private a(int i, String s, EnumDirection enumdirection) {
            this.j = i;
            this.k = s;
            this.l = enumdirection;
        }

        public int a() {
            return this.j;
        }

        public EnumDirection c() {
            return this.l;
        }

        public String toString() {
            return this.k;
        }

        public static BlockLever.a a(int i) {
            if (i < 0 || i >= BlockLever.a.i.length) {
                i = 0;
            }

            return BlockLever.a.i[i];
        }

        public static BlockLever.a a(EnumDirection enumdirection, EnumDirection enumdirection1) {
            switch (BlockLever.SyntheticClass_1.a[enumdirection.ordinal()]) {
            case 1:
                switch (BlockLever.SyntheticClass_1.c[enumdirection1.k().ordinal()]) {
                case 1:
                    return BlockLever.a.DOWN_X;

                case 2:
                    return BlockLever.a.DOWN_Z;

                default:
                    throw new IllegalArgumentException("Invalid entityFacing " + enumdirection1 + " for facing " + enumdirection);
                }

            case 2:
                switch (BlockLever.SyntheticClass_1.c[enumdirection1.k().ordinal()]) {
                case 1:
                    return BlockLever.a.UP_X;

                case 2:
                    return BlockLever.a.UP_Z;

                default:
                    throw new IllegalArgumentException("Invalid entityFacing " + enumdirection1 + " for facing " + enumdirection);
                }

            case 3:
                return BlockLever.a.NORTH;

            case 4:
                return BlockLever.a.SOUTH;

            case 5:
                return BlockLever.a.WEST;

            case 6:
                return BlockLever.a.EAST;

            default:
                throw new IllegalArgumentException("Invalid facing: " + enumdirection);
            }
        }

        public String getName() {
            return this.k;
        }

        static {
            BlockLever.a[] ablocklever_a = values();
            int i = ablocklever_a.length;

            for (int j = 0; j < i; ++j) {
                BlockLever.a blocklever_a = ablocklever_a[j];

                BlockLever.a.i[blocklever_a.a()] = blocklever_a;
            }

        }
    }
}
