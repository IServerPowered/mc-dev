package net.minecraft.server;

public class ItemHoe extends Item {

    protected Item.a a;

    public ItemHoe(Item.a item_a) {
        this.a = item_a;
        this.maxStackSize = 1;
        this.setMaxDurability(item_a.a());
        this.a(CreativeModeTab.i);
    }

    public boolean interactWith(ItemStack itemstack, EntityHuman entityhuman, World world, BlockPosition blockposition, EnumDirection enumdirection, float f, float f1, float f2) {
        if (!entityhuman.a(blockposition.shift(enumdirection), enumdirection, itemstack)) {
            return false;
        } else {
            IBlockData iblockdata = world.getType(blockposition);
            Block block = iblockdata.getBlock();

            if (enumdirection != EnumDirection.DOWN && world.getType(blockposition.up()).getBlock().getMaterial() == Material.AIR) {
                if (block == Blocks.GRASS) {
                    return this.a(itemstack, entityhuman, world, blockposition, Blocks.FARMLAND.getBlockData());
                }

                if (block == Blocks.DIRT) {
                    switch (ItemHoe.SyntheticClass_1.a[((BlockDirt.a) iblockdata.get(BlockDirt.VARIANT)).ordinal()]) {
                    case 1:
                        return this.a(itemstack, entityhuman, world, blockposition, Blocks.FARMLAND.getBlockData());

                    case 2:
                        return this.a(itemstack, entityhuman, world, blockposition, Blocks.DIRT.getBlockData().set(BlockDirt.VARIANT, BlockDirt.a.DIRT));
                    }
                }
            }

            return false;
        }
    }

    protected boolean a(ItemStack itemstack, EntityHuman entityhuman, World world, BlockPosition blockposition, IBlockData iblockdata) {
        world.makeSound((double) ((float) blockposition.getX() + 0.5F), (double) ((float) blockposition.getY() + 0.5F), (double) ((float) blockposition.getZ() + 0.5F), iblockdata.getBlock().stepSound.getStepSound(), (iblockdata.getBlock().stepSound.getVolume1() + 1.0F) / 2.0F, iblockdata.getBlock().stepSound.getVolume2() * 0.8F);
        if (world.isClientSide) {
            return true;
        } else {
            world.setTypeUpdate(blockposition, iblockdata);
            itemstack.damage(1, entityhuman);
            return true;
        }
    }

    public String g() {
        return this.a.toString();
    }

    static class SyntheticClass_1 {

        static final int[] a = new int[BlockDirt.a.values().length];

        static {
            try {
                ItemHoe.SyntheticClass_1.a[BlockDirt.a.DIRT.ordinal()] = 1;
            } catch (NoSuchFieldError nosuchfielderror) {
                ;
            }

            try {
                ItemHoe.SyntheticClass_1.a[BlockDirt.a.COARSE_DIRT.ordinal()] = 2;
            } catch (NoSuchFieldError nosuchfielderror1) {
                ;
            }

        }
    }
}
