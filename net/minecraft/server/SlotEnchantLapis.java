package net.minecraft.server;

class SlotEnchantLapis extends Slot {

    final ContainerEnchantTable index;

    SlotEnchantLapis(ContainerEnchantTable containerenchanttable, IInventory iinventory, int i, int j, int k) {
        super(iinventory, i, j, k);
        this.index = containerenchanttable;
    }

    public boolean isAllowed(ItemStack itemstack) {
        return itemstack.getItem() == Items.DYE && EnumColor.fromInvColorIndex(itemstack.getData()) == EnumColor.BLUE;
    }
}
