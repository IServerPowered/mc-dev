package net.minecraft.server;

class SlotHorseSaddle extends Slot {

    final ContainerHorse index;

    SlotHorseSaddle(ContainerHorse containerhorse, IInventory iinventory, int i, int j, int k) {
        super(iinventory, i, j, k);
        this.index = containerhorse;
    }

    public boolean isAllowed(ItemStack itemstack) {
        return super.isAllowed(itemstack) && itemstack.getItem() == Items.SADDLE && !this.hasItem();
    }
}
