package net.minecraft.server;

import com.google.common.collect.Maps;
import java.util.Map;

public class ItemFish extends ItemFood {

    private final boolean b;

    public ItemFish(boolean flag) {
        super(0, 0.0F, false);
        this.b = flag;
    }

    public int getNutrition(ItemStack itemstack) {
        ItemFish.a itemfish_a = ItemFish.a.a(itemstack);

        return this.b && itemfish_a.g() ? itemfish_a.e() : itemfish_a.c();
    }

    public float getSaturationModifier(ItemStack itemstack) {
        ItemFish.a itemfish_a = ItemFish.a.a(itemstack);

        return this.b && itemfish_a.g() ? itemfish_a.f() : itemfish_a.d();
    }

    public String j(ItemStack itemstack) {
        return ItemFish.a.a(itemstack) == ItemFish.a.PUFFERFISH ? PotionBrewer.m : null;
    }

    protected void c(ItemStack itemstack, World world, EntityHuman entityhuman) {
        ItemFish.a itemfish_a = ItemFish.a.a(itemstack);

        if (itemfish_a == ItemFish.a.PUFFERFISH) {
            entityhuman.addEffect(new MobEffect(MobEffectList.POISON.id, 1200, 3));
            entityhuman.addEffect(new MobEffect(MobEffectList.HUNGER.id, 300, 2));
            entityhuman.addEffect(new MobEffect(MobEffectList.CONFUSION.id, 300, 1));
        }

        super.c(itemstack, world, entityhuman);
    }

    public String e_(ItemStack itemstack) {
        ItemFish.a itemfish_a = ItemFish.a.a(itemstack);

        return this.getName() + "." + itemfish_a.b() + "." + (this.b && itemfish_a.g() ? "cooked" : "raw");
    }

    public static enum a {

        COD(0, "cod", 2, 0.1F, 5, 0.6F), SALMON(1, "salmon", 2, 0.1F, 6, 0.8F), CLOWNFISH(2, "clownfish", 1, 0.1F), PUFFERFISH(3, "pufferfish", 1, 0.1F);

        private static final Map<Integer, ItemFish.a> e = Maps.newHashMap();
        private final int f;
        private final String g;
        private final int h;
        private final float i;
        private final int j;
        private final float k;
        private boolean l = false;

        private a(int i, String s, int j, float f, int k, float f1) {
            this.f = i;
            this.g = s;
            this.h = j;
            this.i = f;
            this.j = k;
            this.k = f1;
            this.l = true;
        }

        private a(int i, String s, int j, float f) {
            this.f = i;
            this.g = s;
            this.h = j;
            this.i = f;
            this.j = 0;
            this.k = 0.0F;
            this.l = false;
        }

        public int a() {
            return this.f;
        }

        public String b() {
            return this.g;
        }

        public int c() {
            return this.h;
        }

        public float d() {
            return this.i;
        }

        public int e() {
            return this.j;
        }

        public float f() {
            return this.k;
        }

        public boolean g() {
            return this.l;
        }

        public static ItemFish.a a(int i) {
            ItemFish.a itemfish_a = (ItemFish.a) ItemFish.a.e.get(Integer.valueOf(i));

            return itemfish_a == null ? ItemFish.a.COD : itemfish_a;
        }

        public static ItemFish.a a(ItemStack itemstack) {
            return itemstack.getItem() instanceof ItemFish ? a(itemstack.getData()) : ItemFish.a.COD;
        }

        static {
            ItemFish.a[] aitemfish_a = values();
            int i = aitemfish_a.length;

            for (int j = 0; j < i; ++j) {
                ItemFish.a itemfish_a = aitemfish_a[j];

                ItemFish.a.e.put(Integer.valueOf(itemfish_a.a()), itemfish_a);
            }

        }
    }
}
