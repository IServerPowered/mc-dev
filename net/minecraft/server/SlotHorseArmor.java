package net.minecraft.server;

class SlotHorseArmor extends Slot {

    final EntityHorse index;
    final ContainerHorse b;

    SlotHorseArmor(ContainerHorse containerhorse, IInventory iinventory, int i, int j, int k, EntityHorse entityhorse) {
        super(iinventory, i, j, k);
        this.b = containerhorse;
        this.index = entityhorse;
    }

    public boolean isAllowed(ItemStack itemstack) {
        return super.isAllowed(itemstack) && this.index.cM() && EntityHorse.a(itemstack.getItem());
    }
}
