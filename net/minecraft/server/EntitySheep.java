package net.minecraft.server;

import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Random;

public class EntitySheep extends EntityAnimal {

    private final InventoryCrafting bk = new InventoryCrafting(new ContainerSheepBreed(this), 2, 1);
    private static final Map bm = Maps.newEnumMap(EnumColor.class);
    private int bn;
    private PathfinderGoalEatTile bo = new PathfinderGoalEatTile(this);

    public static float[] a(EnumColor enumcolor) {
        return (float[]) EntitySheep.bm.get(enumcolor);
    }

    public EntitySheep(World world) {
        super(world);
        this.a(0.9F, 1.3F);
        ((Navigation) this.getNavigation()).a(true);
        this.goalSelector.a(0, new PathfinderGoalFloat(this));
        this.goalSelector.a(1, new PathfinderGoalPanic(this, 1.25D));
        this.goalSelector.a(2, new PathfinderGoalBreed(this, 1.0D));
        this.goalSelector.a(3, new PathfinderGoalTempt(this, 1.1D, Items.WHEAT, false));
        this.goalSelector.a(4, new PathfinderGoalFollowParent(this, 1.1D));
        this.goalSelector.a(5, this.bo);
        this.goalSelector.a(6, new PathfinderGoalRandomStroll(this, 1.0D));
        this.goalSelector.a(7, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 6.0F));
        this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
        this.bk.setItem(0, new ItemStack(Items.DYE, 1, 0));
        this.bk.setItem(1, new ItemStack(Items.DYE, 1, 0));
    }

    protected void E() {
        this.bn = this.bo.f();
        super.E();
    }

    public void recalcPosition() {
        if (this.world.isStatic) {
            this.bn = Math.max(0, this.bn - 1);
        }

        super.recalcPosition();
    }

    protected void aW() {
        super.aW();
        this.getAttributeInstance(GenericAttributes.maxHealth).setValue(8.0D);
        this.getAttributeInstance(GenericAttributes.d).setValue(0.23000000417232513D);
    }

    protected void h() {
        super.h();
        this.datawatcher.a(16, new Byte((byte) 0));
    }

    protected void dropDeathLoot(boolean flag, int i) {
        if (!this.isSheared()) {
            this.a(new ItemStack(Item.getItemOf(Blocks.WOOL), 1, this.getColor().getColorIndex()), 0.0F);
        }

        int j = this.random.nextInt(2) + 1 + this.random.nextInt(1 + i);

        for (int k = 0; k < j; ++k) {
            if (this.isBurning()) {
                this.a(Items.COOKED_MUTTON, 1);
            } else {
                this.a(Items.MUTTON, 1);
            }
        }

    }

    protected Item getLoot() {
        return Item.getItemOf(Blocks.WOOL);
    }

    public boolean a(EntityHuman entityhuman) {
        ItemStack itemstack = entityhuman.inventory.getItemInHand();

        if (itemstack != null && itemstack.getItem() == Items.SHEARS && !this.isSheared() && !this.isBaby()) {
            if (!this.world.isStatic) {
                this.setSheared(true);
                int i = 1 + this.random.nextInt(3);

                for (int j = 0; j < i; ++j) {
                    EntityItem entityitem = this.a(new ItemStack(Item.getItemOf(Blocks.WOOL), 1, this.getColor().getColorIndex()), 1.0F);

                    entityitem.motY += (double) (this.random.nextFloat() * 0.05F);
                    entityitem.motX += (double) ((this.random.nextFloat() - this.random.nextFloat()) * 0.1F);
                    entityitem.motZ += (double) ((this.random.nextFloat() - this.random.nextFloat()) * 0.1F);
                }
            }

            itemstack.damage(1, entityhuman);
            this.makeSound("mob.sheep.shear", 1.0F, 1.0F);
        }

        return super.a(entityhuman);
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.setBoolean("Sheared", this.isSheared());
        nbttagcompound.setByte("Color", (byte) this.getColor().getColorIndex());
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.setSheared(nbttagcompound.getBoolean("Sheared"));
        this.setColor(EnumColor.fromColorIndex(nbttagcompound.getByte("Color")));
    }

    protected String z() {
        return "mob.sheep.say";
    }

    protected String bn() {
        return "mob.sheep.say";
    }

    protected String bo() {
        return "mob.sheep.say";
    }

    protected void a(BlockPosition blockposition, Block block) {
        this.makeSound("mob.sheep.step", 0.15F, 1.0F);
    }

    public EnumColor getColor() {
        return EnumColor.fromColorIndex(this.datawatcher.getByte(16) & 15);
    }

    public void setColor(EnumColor enumcolor) {
        byte b0 = this.datawatcher.getByte(16);

        this.datawatcher.watch(16, Byte.valueOf((byte) (b0 & 240 | enumcolor.getColorIndex() & 15)));
    }

    public boolean isSheared() {
        return (this.datawatcher.getByte(16) & 16) != 0;
    }

    public void setSheared(boolean flag) {
        byte b0 = this.datawatcher.getByte(16);

        if (flag) {
            this.datawatcher.watch(16, Byte.valueOf((byte) (b0 | 16)));
        } else {
            this.datawatcher.watch(16, Byte.valueOf((byte) (b0 & -17)));
        }

    }

    public static EnumColor a(Random random) {
        int i = random.nextInt(100);

        return i < 5 ? EnumColor.BLACK : (i < 10 ? EnumColor.GRAY : (i < 15 ? EnumColor.SILVER : (i < 18 ? EnumColor.BROWN : (random.nextInt(500) == 0 ? EnumColor.PINK : EnumColor.WHITE))));
    }

    public EntitySheep b(EntityAgeable entityageable) {
        EntitySheep entitysheep = (EntitySheep) entityageable;
        EntitySheep entitysheep1 = new EntitySheep(this.world);

        entitysheep1.setColor(this.a((EntityAnimal) this, (EntityAnimal) entitysheep));
        return entitysheep1;
    }

    public void v() {
        this.setSheared(false);
        if (this.isBaby()) {
            this.setAge(60);
        }

    }

    public GroupDataEntity prepare(DifficultyDamageScaler difficultydamagescaler, GroupDataEntity groupdataentity) {
        groupdataentity = super.prepare(difficultydamagescaler, groupdataentity);
        this.setColor(a(this.world.random));
        return groupdataentity;
    }

    private EnumColor a(EntityAnimal entityanimal, EntityAnimal entityanimal1) {
        int i = ((EntitySheep) entityanimal).getColor().getInvColorIndex();
        int j = ((EntitySheep) entityanimal1).getColor().getInvColorIndex();

        this.bk.getItem(0).setData(i);
        this.bk.getItem(1).setData(j);
        ItemStack itemstack = CraftingManager.getInstance().craft(this.bk, ((EntitySheep) entityanimal).world);
        int k;

        if (itemstack != null && itemstack.getItem() == Items.DYE) {
            k = itemstack.getData();
        } else {
            k = this.world.random.nextBoolean() ? i : j;
        }

        return EnumColor.fromInvColorIndex(k);
    }

    public float getHeadHeight() {
        return 0.95F * this.length;
    }

    public EntityAgeable createChild(EntityAgeable entityageable) {
        return this.b(entityageable);
    }

    static {
        EntitySheep.bm.put(EnumColor.WHITE, new float[] { 1.0F, 1.0F, 1.0F});
        EntitySheep.bm.put(EnumColor.ORANGE, new float[] { 0.85F, 0.5F, 0.2F});
        EntitySheep.bm.put(EnumColor.MAGENTA, new float[] { 0.7F, 0.3F, 0.85F});
        EntitySheep.bm.put(EnumColor.LIGHT_BLUE, new float[] { 0.4F, 0.6F, 0.85F});
        EntitySheep.bm.put(EnumColor.YELLOW, new float[] { 0.9F, 0.9F, 0.2F});
        EntitySheep.bm.put(EnumColor.LIME, new float[] { 0.5F, 0.8F, 0.1F});
        EntitySheep.bm.put(EnumColor.PINK, new float[] { 0.95F, 0.5F, 0.65F});
        EntitySheep.bm.put(EnumColor.GRAY, new float[] { 0.3F, 0.3F, 0.3F});
        EntitySheep.bm.put(EnumColor.SILVER, new float[] { 0.6F, 0.6F, 0.6F});
        EntitySheep.bm.put(EnumColor.CYAN, new float[] { 0.3F, 0.5F, 0.6F});
        EntitySheep.bm.put(EnumColor.PURPLE, new float[] { 0.5F, 0.25F, 0.7F});
        EntitySheep.bm.put(EnumColor.BLUE, new float[] { 0.2F, 0.3F, 0.7F});
        EntitySheep.bm.put(EnumColor.BROWN, new float[] { 0.4F, 0.3F, 0.2F});
        EntitySheep.bm.put(EnumColor.GREEN, new float[] { 0.4F, 0.5F, 0.2F});
        EntitySheep.bm.put(EnumColor.RED, new float[] { 0.6F, 0.2F, 0.2F});
        EntitySheep.bm.put(EnumColor.BLACK, new float[] { 0.1F, 0.1F, 0.1F});
    }
}