package net.minecraft.server;

public interface IRecipe {

    boolean a(InventoryCrafting inventorycrafting, World world);

    ItemStack a(InventoryCrafting inventorycrafting);

    int a();

    ItemStack b();

    ItemStack[] b(InventoryCrafting inventorycrafting);
}
