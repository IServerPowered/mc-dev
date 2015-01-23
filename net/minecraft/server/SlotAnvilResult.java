package net.minecraft.server;

class SlotAnvilResult extends Slot {

    final World index;
    final BlockPosition b;
    final ContainerAnvil c;

    SlotAnvilResult(ContainerAnvil containeranvil, IInventory iinventory, int i, int j, int k, World world, BlockPosition blockposition) {
        super(iinventory, i, j, k);
        this.c = containeranvil;
        this.index = world;
        this.b = blockposition;
    }

    public boolean isAllowed(ItemStack itemstack) {
        return false;
    }

    public boolean isAllowed(EntityHuman entityhuman) {
        return (entityhuman.abilities.canInstantlyBuild || entityhuman.expLevel >= this.c.a) && this.c.a > 0 && this.hasItem();
    }

    public void a(EntityHuman entityhuman, ItemStack itemstack) {
        if (!entityhuman.abilities.canInstantlyBuild) {
            entityhuman.levelDown(-this.c.a);
        }

        ContainerAnvil.a(this.c).setItem(0, (ItemStack) null);
        if (ContainerAnvil.b(this.c) > 0) {
            ItemStack itemstack1 = ContainerAnvil.a(this.c).getItem(1);

            if (itemstack1 != null && itemstack1.count > ContainerAnvil.b(this.c)) {
                itemstack1.count -= ContainerAnvil.b(this.c);
                ContainerAnvil.a(this.c).setItem(1, itemstack1);
            } else {
                ContainerAnvil.a(this.c).setItem(1, (ItemStack) null);
            }
        } else {
            ContainerAnvil.a(this.c).setItem(1, (ItemStack) null);
        }

        this.c.a = 0;
        IBlockData iblockdata = this.index.getType(this.b);

        if (!entityhuman.abilities.canInstantlyBuild && !this.index.isStatic && iblockdata.getBlock() == Blocks.ANVIL && entityhuman.bb().nextFloat() < 0.12F) {
            int i = ((Integer) iblockdata.get(BlockAnvil.DAMAGE)).intValue();

            ++i;
            if (i > 2) {
                this.index.setAir(this.b);
                this.index.triggerEffect(1020, this.b, 0);
            } else {
                this.index.setTypeAndData(this.b, iblockdata.set(BlockAnvil.DAMAGE, Integer.valueOf(i)), 2);
                this.index.triggerEffect(1021, this.b, 0);
            }
        } else if (!this.index.isStatic) {
            this.index.triggerEffect(1021, this.b, 0);
        }

    }
}
