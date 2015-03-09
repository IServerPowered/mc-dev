package net.minecraft.server;

import com.google.common.base.Predicate;

public class BlockLog1 extends BlockLogAbstract {

    public static final BlockStateEnum<BlockWood.a> VARIANT = BlockStateEnum.a("variant", BlockWood.a.class, new Predicate() {
        public boolean a(BlockWood.a blockwood_a) {
            return blockwood_a.a() < 4;
        }

        public boolean apply(Object object) {
            return this.a((BlockWood.a) object);
        }
    });

    public BlockLog1() {
        this.j(this.blockStateList.getBlockData().set(BlockLog1.VARIANT, BlockWood.a.OAK).set(BlockLog1.AXIS, BlockLogAbstract.a.Y));
    }

    public MaterialMapColor g(IBlockData iblockdata) {
        BlockWood.a blockwood_a = (BlockWood.a) iblockdata.get(BlockLog1.VARIANT);

        switch (BlockLog1.SyntheticClass_1.b[((BlockLogAbstract.a) iblockdata.get(BlockLog1.AXIS)).ordinal()]) {
        case 1:
        case 2:
        case 3:
        default:
            switch (BlockLog1.SyntheticClass_1.a[blockwood_a.ordinal()]) {
            case 1:
            default:
                return BlockWood.a.SPRUCE.c();

            case 2:
                return BlockWood.a.DARK_OAK.c();

            case 3:
                return MaterialMapColor.p;

            case 4:
                return BlockWood.a.SPRUCE.c();
            }

        case 4:
            return blockwood_a.c();
        }
    }

    public IBlockData fromLegacyData(int i) {
        IBlockData iblockdata = this.getBlockData().set(BlockLog1.VARIANT, BlockWood.a.a((i & 3) % 4));

        switch (i & 12) {
        case 0:
            iblockdata = iblockdata.set(BlockLog1.AXIS, BlockLogAbstract.a.Y);
            break;

        case 4:
            iblockdata = iblockdata.set(BlockLog1.AXIS, BlockLogAbstract.a.X);
            break;

        case 8:
            iblockdata = iblockdata.set(BlockLog1.AXIS, BlockLogAbstract.a.Z);
            break;

        default:
            iblockdata = iblockdata.set(BlockLog1.AXIS, BlockLogAbstract.a.NONE);
        }

        return iblockdata;
    }

    public int toLegacyData(IBlockData iblockdata) {
        byte b0 = 0;
        int i = b0 | ((BlockWood.a) iblockdata.get(BlockLog1.VARIANT)).a();

        switch (BlockLog1.SyntheticClass_1.b[((BlockLogAbstract.a) iblockdata.get(BlockLog1.AXIS)).ordinal()]) {
        case 1:
            i |= 4;
            break;

        case 2:
            i |= 8;
            break;

        case 3:
            i |= 12;
        }

        return i;
    }

    protected BlockStateList getStateList() {
        return new BlockStateList(this, new IBlockState[] { BlockLog1.VARIANT, BlockLog1.AXIS});
    }

    protected ItemStack i(IBlockData iblockdata) {
        return new ItemStack(Item.getItemOf(this), 1, ((BlockWood.a) iblockdata.get(BlockLog1.VARIANT)).a());
    }

    public int getDropData(IBlockData iblockdata) {
        return ((BlockWood.a) iblockdata.get(BlockLog1.VARIANT)).a();
    }

    static class SyntheticClass_1 {

        static final int[] a;
        static final int[] b = new int[BlockLogAbstract.a.values().length];

        static {
            try {
                BlockLog1.SyntheticClass_1.b[BlockLogAbstract.a.X.ordinal()] = 1;
            } catch (NoSuchFieldError nosuchfielderror) {
                ;
            }

            try {
                BlockLog1.SyntheticClass_1.b[BlockLogAbstract.a.Z.ordinal()] = 2;
            } catch (NoSuchFieldError nosuchfielderror1) {
                ;
            }

            try {
                BlockLog1.SyntheticClass_1.b[BlockLogAbstract.a.NONE.ordinal()] = 3;
            } catch (NoSuchFieldError nosuchfielderror2) {
                ;
            }

            try {
                BlockLog1.SyntheticClass_1.b[BlockLogAbstract.a.Y.ordinal()] = 4;
            } catch (NoSuchFieldError nosuchfielderror3) {
                ;
            }

            a = new int[BlockWood.a.values().length];

            try {
                BlockLog1.SyntheticClass_1.a[BlockWood.a.OAK.ordinal()] = 1;
            } catch (NoSuchFieldError nosuchfielderror4) {
                ;
            }

            try {
                BlockLog1.SyntheticClass_1.a[BlockWood.a.SPRUCE.ordinal()] = 2;
            } catch (NoSuchFieldError nosuchfielderror5) {
                ;
            }

            try {
                BlockLog1.SyntheticClass_1.a[BlockWood.a.BIRCH.ordinal()] = 3;
            } catch (NoSuchFieldError nosuchfielderror6) {
                ;
            }

            try {
                BlockLog1.SyntheticClass_1.a[BlockWood.a.JUNGLE.ordinal()] = 4;
            } catch (NoSuchFieldError nosuchfielderror7) {
                ;
            }

        }
    }
}
