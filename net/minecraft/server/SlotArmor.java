package net.minecraft.server;

class SlotArmor extends Slot {

    final int index;
    final ContainerPlayer b;

    SlotArmor(ContainerPlayer containerplayer, IInventory iinventory, int i, int j, int k, int l) {
        super(iinventory, i, j, k);
        this.b = containerplayer;
        this.index = l;
    }

    public int getMaxStackSize() {
        return 1;
    }

    public boolean isAllowed(ItemStack itemstack) {
        return itemstack == null ? false : (itemstack.getItem() instanceof ItemArmor ? ((ItemArmor) itemstack.getItem()).b == this.index : (itemstack.getItem() != Item.getItemOf(Blocks.PUMPKIN) && itemstack.getItem() != Items.SKULL ? false : this.index == 0));
    }
}
