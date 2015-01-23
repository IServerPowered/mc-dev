package net.minecraft.server;

class SlotBeacon extends Slot {

    final ContainerBeacon index;

    public SlotBeacon(ContainerBeacon containerbeacon, IInventory iinventory, int i, int j, int k) {
        super(iinventory, i, j, k);
        this.index = containerbeacon;
    }

    public boolean isAllowed(ItemStack itemstack) {
        return itemstack == null ? false : itemstack.getItem() == Items.EMERALD || itemstack.getItem() == Items.DIAMOND || itemstack.getItem() == Items.GOLD_INGOT || itemstack.getItem() == Items.IRON_INGOT;
    }

    public int getMaxStackSize() {
        return 1;
    }
}
