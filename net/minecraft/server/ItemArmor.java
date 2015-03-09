package net.minecraft.server;

import com.google.common.base.Predicates;
import java.util.List;

public class ItemArmor extends Item {

    private static final int[] k = new int[] { 11, 16, 15, 13};
    public static final String[] a = new String[] { "minecraft:items/empty_armor_slot_helmet", "minecraft:items/empty_armor_slot_chestplate", "minecraft:items/empty_armor_slot_leggings", "minecraft:items/empty_armor_slot_boots"};
    private static final IDispenseBehavior l = new DispenseBehaviorItem() {
        protected ItemStack b(ISourceBlock isourceblock, ItemStack itemstack) {
            BlockPosition blockposition = isourceblock.getBlockPosition().shift(BlockDispenser.b(isourceblock.f()));
            int i = blockposition.getX();
            int j = blockposition.getY();
            int k = blockposition.getZ();
            AxisAlignedBB axisalignedbb = new AxisAlignedBB((double) i, (double) j, (double) k, (double) (i + 1), (double) (j + 1), (double) (k + 1));
            List list = isourceblock.i().a(EntityLiving.class, axisalignedbb, Predicates.and(IEntitySelector.d, new IEntitySelector.a(itemstack)));

            if (list.size() > 0) {
                EntityLiving entityliving = (EntityLiving) list.get(0);
                int l = entityliving instanceof EntityHuman ? 1 : 0;
                int i1 = EntityInsentient.c(itemstack);
                ItemStack itemstack1 = itemstack.cloneItemStack();

                itemstack1.count = 1;
                entityliving.setEquipment(i1 - l, itemstack1);
                if (entityliving instanceof EntityInsentient) {
                    ((EntityInsentient) entityliving).a(i1, 2.0F);
                }

                --itemstack.count;
                return itemstack;
            } else {
                return super.b(isourceblock, itemstack);
            }
        }
    };
    public final int b;
    public final int durability;
    public final int craftingResult;
    private final ItemArmor.a m;

    public ItemArmor(ItemArmor.a itemarmor_a, int i, int j) {
        this.m = itemarmor_a;
        this.b = j;
        this.craftingResult = i;
        this.durability = itemarmor_a.b(j);
        this.setMaxDurability(itemarmor_a.a(j));
        this.maxStackSize = 1;
        this.a(CreativeModeTab.j);
        BlockDispenser.N.a(this, ItemArmor.l);
    }

    public int b() {
        return this.m.a();
    }

    public ItemArmor.a x_() {
        return this.m;
    }

    public boolean d_(ItemStack itemstack) {
        return this.m != ItemArmor.a.LEATHER ? false : (!itemstack.hasTag() ? false : (!itemstack.getTag().hasKeyOfType("display", 10) ? false : itemstack.getTag().getCompound("display").hasKeyOfType("color", 3)));
    }

    public int b(ItemStack itemstack) {
        if (this.m != ItemArmor.a.LEATHER) {
            return -1;
        } else {
            NBTTagCompound nbttagcompound = itemstack.getTag();

            if (nbttagcompound != null) {
                NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("display");

                if (nbttagcompound1 != null && nbttagcompound1.hasKeyOfType("color", 3)) {
                    return nbttagcompound1.getInt("color");
                }
            }

            return 10511680;
        }
    }

    public void c(ItemStack itemstack) {
        if (this.m == ItemArmor.a.LEATHER) {
            NBTTagCompound nbttagcompound = itemstack.getTag();

            if (nbttagcompound != null) {
                NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("display");

                if (nbttagcompound1.hasKey("color")) {
                    nbttagcompound1.remove("color");
                }

            }
        }
    }

    public void b(ItemStack itemstack, int i) {
        if (this.m != ItemArmor.a.LEATHER) {
            throw new UnsupportedOperationException("Can\'t dye non-leather!");
        } else {
            NBTTagCompound nbttagcompound = itemstack.getTag();

            if (nbttagcompound == null) {
                nbttagcompound = new NBTTagCompound();
                itemstack.setTag(nbttagcompound);
            }

            NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("display");

            if (!nbttagcompound.hasKeyOfType("display", 10)) {
                nbttagcompound.set("display", nbttagcompound1);
            }

            nbttagcompound1.setInt("color", i);
        }
    }

    public boolean a(ItemStack itemstack, ItemStack itemstack1) {
        return this.m.b() == itemstack1.getItem() ? true : super.a(itemstack, itemstack1);
    }

    public ItemStack a(ItemStack itemstack, World world, EntityHuman entityhuman) {
        int i = EntityInsentient.c(itemstack) - 1;
        ItemStack itemstack1 = entityhuman.q(i);

        if (itemstack1 == null) {
            entityhuman.setEquipment(i, itemstack.cloneItemStack());
            itemstack.count = 0;
        }

        return itemstack;
    }

    public static enum a {

        LEATHER("leather", 5, new int[] { 1, 3, 2, 1}, 15), CHAIN("chainmail", 15, new int[] { 2, 5, 4, 1}, 12), IRON("iron", 15, new int[] { 2, 6, 5, 2}, 9), GOLD("gold", 7, new int[] { 2, 5, 3, 1}, 25), DIAMOND("diamond", 33, new int[] { 3, 8, 6, 3}, 10);

        private final String f;
        private final int g;
        private final int[] h;
        private final int i;

        private a(String s, int i, int[] aint, int j) {
            this.f = s;
            this.g = i;
            this.h = aint;
            this.i = j;
        }

        public int a(int i) {
            return ItemArmor.k[i] * this.g;
        }

        public int b(int i) {
            return this.h[i];
        }

        public int a() {
            return this.i;
        }

        public Item b() {
            return this == ItemArmor.a.LEATHER ? Items.LEATHER : (this == ItemArmor.a.CHAIN ? Items.IRON_INGOT : (this == ItemArmor.a.GOLD ? Items.GOLD_INGOT : (this == ItemArmor.a.IRON ? Items.IRON_INGOT : (this == ItemArmor.a.DIAMOND ? Items.DIAMOND : null))));
        }
    }
}
