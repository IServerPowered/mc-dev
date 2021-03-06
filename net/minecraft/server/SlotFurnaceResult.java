package net.minecraft.server;

public class SlotFurnaceResult extends Slot {

    private EntityHuman a;
    private int b;

    public SlotFurnaceResult(EntityHuman entityhuman, IInventory iinventory, int i, int j, int k) {
        super(iinventory, i, j, k);
        this.a = entityhuman;
    }

    public boolean isAllowed(ItemStack itemstack) {
        return false;
    }

    public ItemStack a(int i) {
        if (this.hasItem()) {
            this.b += Math.min(i, this.getItem().count);
        }

        return super.a(i);
    }

    public void a(EntityHuman entityhuman, ItemStack itemstack) {
        this.c(itemstack);
        super.a(entityhuman, itemstack);
    }

    protected void a(ItemStack itemstack, int i) {
        this.b += i;
        this.c(itemstack);
    }

    protected void c(ItemStack itemstack) {
        itemstack.a(this.a.world, this.a, this.b);
        if (!this.a.world.isClientSide) {
            int i = this.b;
            float f = RecipesFurnace.getInstance().b(itemstack);
            int j;

            if (f == 0.0F) {
                i = 0;
            } else if (f < 1.0F) {
                j = MathHelper.d((float) i * f);
                if (j < MathHelper.f((float) i * f) && Math.random() < (double) ((float) i * f - (float) j)) {
                    ++j;
                }

                i = j;
            }

            while (i > 0) {
                j = EntityExperienceOrb.getOrbValue(i);
                i -= j;
                this.a.world.addEntity(new EntityExperienceOrb(this.a.world, this.a.locX, this.a.locY + 0.5D, this.a.locZ + 0.5D, j));
            }
        }

        this.b = 0;
        if (itemstack.getItem() == Items.IRON_INGOT) {
            this.a.b((Statistic) AchievementList.k);
        }

        if (itemstack.getItem() == Items.COOKED_FISH) {
            this.a.b((Statistic) AchievementList.p);
        }

    }
}
