package net.minecraft.server;

final class DispenseBehaviorNoop implements IDispenseBehavior {

    public ItemStack a(ISourceBlock isourceblock, ItemStack itemstack) {
        return itemstack;
    }
}
