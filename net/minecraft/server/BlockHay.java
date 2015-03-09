package net.minecraft.server;

public class BlockHay extends BlockRotatable {

    public BlockHay() {
        super(Material.GRASS, MaterialMapColor.t);
        this.j(this.blockStateList.getBlockData().set(BlockHay.AXIS, EnumDirection.a.Y));
        this.a(CreativeModeTab.b);
    }

    public IBlockData fromLegacyData(int i) {
        EnumDirection.a enumdirection_a = EnumDirection.a.Y;
        int j = i & 12;

        if (j == 4) {
            enumdirection_a = EnumDirection.a.X;
        } else if (j == 8) {
            enumdirection_a = EnumDirection.a.Z;
        }

        return this.getBlockData().set(BlockHay.AXIS, enumdirection_a);
    }

    public int toLegacyData(IBlockData iblockdata) {
        int i = 0;
        EnumDirection.a enumdirection_a = (EnumDirection.a) iblockdata.get(BlockHay.AXIS);

        if (enumdirection_a == EnumDirection.a.X) {
            i |= 4;
        } else if (enumdirection_a == EnumDirection.a.Z) {
            i |= 8;
        }

        return i;
    }

    protected BlockStateList getStateList() {
        return new BlockStateList(this, new IBlockState[] { BlockHay.AXIS});
    }

    protected ItemStack i(IBlockData iblockdata) {
        return new ItemStack(Item.getItemOf(this), 1, 0);
    }

    public IBlockData getPlacedState(World world, BlockPosition blockposition, EnumDirection enumdirection, float f, float f1, float f2, int i, EntityLiving entityliving) {
        return super.getPlacedState(world, blockposition, enumdirection, f, f1, f2, i, entityliving).set(BlockHay.AXIS, enumdirection.k());
    }
}
