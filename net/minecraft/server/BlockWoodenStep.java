package net.minecraft.server;

import java.util.Random;

public abstract class BlockWoodenStep extends BlockStepAbstract {

    public static final BlockStateEnum<BlockWood.a> VARIANT = BlockStateEnum.of("variant", BlockWood.a.class);

    public BlockWoodenStep() {
        super(Material.WOOD);
        IBlockData iblockdata = this.blockStateList.getBlockData();

        if (!this.l()) {
            iblockdata = iblockdata.set(BlockWoodenStep.HALF, BlockStepAbstract.a.BOTTOM);
        }

        this.j(iblockdata.set(BlockWoodenStep.VARIANT, BlockWood.a.OAK));
        this.a(CreativeModeTab.b);
    }

    public MaterialMapColor g(IBlockData iblockdata) {
        return ((BlockWood.a) iblockdata.get(BlockWoodenStep.VARIANT)).c();
    }

    public Item getDropType(IBlockData iblockdata, Random random, int i) {
        return Item.getItemOf(Blocks.WOODEN_SLAB);
    }

    public String b(int i) {
        return super.a() + "." + BlockWood.a.a(i).d();
    }

    public IBlockState<?> n() {
        return BlockWoodenStep.VARIANT;
    }

    public Object a(ItemStack itemstack) {
        return BlockWood.a.a(itemstack.getData() & 7);
    }

    public IBlockData fromLegacyData(int i) {
        IBlockData iblockdata = this.getBlockData().set(BlockWoodenStep.VARIANT, BlockWood.a.a(i & 7));

        if (!this.l()) {
            iblockdata = iblockdata.set(BlockWoodenStep.HALF, (i & 8) == 0 ? BlockStepAbstract.a.BOTTOM : BlockStepAbstract.a.TOP);
        }

        return iblockdata;
    }

    public int toLegacyData(IBlockData iblockdata) {
        byte b0 = 0;
        int i = b0 | ((BlockWood.a) iblockdata.get(BlockWoodenStep.VARIANT)).a();

        if (!this.l() && iblockdata.get(BlockWoodenStep.HALF) == BlockStepAbstract.a.TOP) {
            i |= 8;
        }

        return i;
    }

    protected BlockStateList getStateList() {
        return this.l() ? new BlockStateList(this, new IBlockState[] { BlockWoodenStep.VARIANT}) : new BlockStateList(this, new IBlockState[] { BlockWoodenStep.HALF, BlockWoodenStep.VARIANT});
    }

    public int getDropData(IBlockData iblockdata) {
        return ((BlockWood.a) iblockdata.get(BlockWoodenStep.VARIANT)).a();
    }
}
