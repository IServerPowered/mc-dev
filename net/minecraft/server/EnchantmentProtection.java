package net.minecraft.server;

public class EnchantmentProtection extends Enchantment {

    private static final String[] E = new String[] { "all", "fire", "fall", "explosion", "projectile"};
    private static final int[] F = new int[] { 1, 10, 5, 5, 3};
    private static final int[] G = new int[] { 11, 8, 6, 8, 6};
    private static final int[] H = new int[] { 20, 12, 10, 12, 15};
    public final int byId;

    public EnchantmentProtection(int i, MinecraftKey minecraftkey, int j, int k) {
        super(i, minecraftkey, j, EnchantmentSlotType.ARMOR);
        this.byId = k;
        if (k == 2) {
            this.slot = EnchantmentSlotType.ARMOR_FEET;
        }

    }

    public int a(int i) {
        return EnchantmentProtection.F[this.byId] + (i - 1) * EnchantmentProtection.G[this.byId];
    }

    public int b(int i) {
        return this.a(i) + EnchantmentProtection.H[this.byId];
    }

    public int getMaxLevel() {
        return 4;
    }

    public int a(int i, DamageSource damagesource) {
        if (damagesource.ignoresInvulnerability()) {
            return 0;
        } else {
            float f = (float) (6 + i * i) / 3.0F;

            return this.byId == 0 ? MathHelper.d(f * 0.75F) : (this.byId == 1 && damagesource.o() ? MathHelper.d(f * 1.25F) : (this.byId == 2 && damagesource == DamageSource.FALL ? MathHelper.d(f * 2.5F) : (this.byId == 3 && damagesource.isExplosion() ? MathHelper.d(f * 1.5F) : (this.byId == 4 && damagesource.a() ? MathHelper.d(f * 1.5F) : 0))));
        }
    }

    public String a() {
        return "enchantment.protect." + EnchantmentProtection.E[this.byId];
    }

    public boolean a(Enchantment enchantment) {
        if (enchantment instanceof EnchantmentProtection) {
            EnchantmentProtection enchantmentprotection = (EnchantmentProtection) enchantment;

            return enchantmentprotection.byId == this.byId ? false : this.byId == 2 || enchantmentprotection.byId == 2;
        } else {
            return super.a(enchantment);
        }
    }

    public static int a(Entity entity, int i) {
        int j = EnchantmentManager.a(Enchantment.PROTECTION_FIRE.id, entity.getEquipment());

        if (j > 0) {
            i -= MathHelper.d((float) i * (float) j * 0.15F);
        }

        return i;
    }

    public static double a(Entity entity, double d0) {
        int i = EnchantmentManager.a(Enchantment.PROTECTION_EXPLOSIONS.id, entity.getEquipment());

        if (i > 0) {
            d0 -= (double) MathHelper.floor(d0 * (double) ((float) i * 0.15F));
        }

        return d0;
    }
}
