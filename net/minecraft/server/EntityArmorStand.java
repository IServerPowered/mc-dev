package net.minecraft.server;

import java.util.List;

public class EntityArmorStand extends EntityLiving {

    private static final Vector3f a = new Vector3f(0.0F, 0.0F, 0.0F);
    private static final Vector3f b = new Vector3f(0.0F, 0.0F, 0.0F);
    private static final Vector3f c = new Vector3f(-10.0F, 0.0F, -10.0F);
    private static final Vector3f d = new Vector3f(-15.0F, 0.0F, 10.0F);
    private static final Vector3f e = new Vector3f(-1.0F, 0.0F, -1.0F);
    private static final Vector3f f = new Vector3f(1.0F, 0.0F, 1.0F);
    private final ItemStack[] items;
    private boolean h;
    private long i;
    private int bg;
    private Vector3f headPose;
    private Vector3f bodyPose;
    private Vector3f leftArmPose;
    private Vector3f rightArmPose;
    private Vector3f leftLegPose;
    private Vector3f rightLegPose;

    public EntityArmorStand(World world) {
        super(world);
        this.items = new ItemStack[5];
        this.headPose = EntityArmorStand.a;
        this.bodyPose = EntityArmorStand.b;
        this.leftArmPose = EntityArmorStand.c;
        this.rightArmPose = EntityArmorStand.d;
        this.leftLegPose = EntityArmorStand.e;
        this.rightLegPose = EntityArmorStand.f;
        this.b(true);
        this.T = this.hasGravity();
        this.a(0.5F, 1.975F);
    }

    public EntityArmorStand(World world, double d0, double d1, double d2) {
        this(world);
        this.setPosition(d0, d1, d2);
    }

    public boolean bL() {
        return super.bL() && !this.hasGravity();
    }

    protected void h() {
        super.h();
        this.datawatcher.a(10, Byte.valueOf((byte) 0));
        this.datawatcher.a(11, EntityArmorStand.a);
        this.datawatcher.a(12, EntityArmorStand.b);
        this.datawatcher.a(13, EntityArmorStand.c);
        this.datawatcher.a(14, EntityArmorStand.d);
        this.datawatcher.a(15, EntityArmorStand.e);
        this.datawatcher.a(16, EntityArmorStand.f);
    }

    public ItemStack bz() {
        return this.items[0];
    }

    public ItemStack getEquipment(int i) {
        return this.items[i];
    }

    public void setEquipment(int i, ItemStack itemstack) {
        this.items[i] = itemstack;
    }

    public ItemStack[] getEquipment() {
        return this.items;
    }

    public boolean d(int i, ItemStack itemstack) {
        int j;

        if (i == 99) {
            j = 0;
        } else {
            j = i - 100 + 1;
            if (j < 0 || j >= this.items.length) {
                return false;
            }
        }

        if (itemstack != null && EntityInsentient.c(itemstack) != j && (j != 4 || !(itemstack.getItem() instanceof ItemBlock))) {
            return false;
        } else {
            this.setEquipment(j, itemstack);
            return true;
        }
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.items.length; ++i) {
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();

            if (this.items[i] != null) {
                this.items[i].save(nbttagcompound1);
            }

            nbttaglist.add(nbttagcompound1);
        }

        nbttagcompound.set("Equipment", nbttaglist);
        if (this.getCustomNameVisible() && (this.getCustomName() == null || this.getCustomName().length() == 0)) {
            nbttagcompound.setBoolean("CustomNameVisible", this.getCustomNameVisible());
        }

        nbttagcompound.setBoolean("Invisible", this.isInvisible());
        nbttagcompound.setBoolean("Small", this.isSmall());
        nbttagcompound.setBoolean("ShowArms", this.hasArms());
        nbttagcompound.setInt("DisabledSlots", this.bg);
        nbttagcompound.setBoolean("NoGravity", this.hasGravity());
        nbttagcompound.setBoolean("NoBasePlate", this.hasBasePlate());
        nbttagcompound.set("Pose", this.y());
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        if (nbttagcompound.hasKeyOfType("Equipment", 9)) {
            NBTTagList nbttaglist = nbttagcompound.getList("Equipment", 10);

            for (int i = 0; i < this.items.length; ++i) {
                this.items[i] = ItemStack.createStack(nbttaglist.get(i));
            }
        }

        this.setInvisible(nbttagcompound.getBoolean("Invisible"));
        this.setSmall(nbttagcompound.getBoolean("Small"));
        this.setArms(nbttagcompound.getBoolean("ShowArms"));
        this.bg = nbttagcompound.getInt("DisabledSlots");
        this.setGravity(nbttagcompound.getBoolean("NoGravity"));
        this.setBasePlate(nbttagcompound.getBoolean("NoBasePlate"));
        this.T = this.hasGravity();
        NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("Pose");

        this.h(nbttagcompound1);
    }

    private void h(NBTTagCompound nbttagcompound) {
        NBTTagList nbttaglist = nbttagcompound.getList("Head", 5);

        if (nbttaglist.size() > 0) {
            this.setHeadPose(new Vector3f(nbttaglist));
        } else {
            this.setHeadPose(EntityArmorStand.a);
        }

        NBTTagList nbttaglist1 = nbttagcompound.getList("Body", 5);

        if (nbttaglist1.size() > 0) {
            this.setBodyPose(new Vector3f(nbttaglist1));
        } else {
            this.setBodyPose(EntityArmorStand.b);
        }

        NBTTagList nbttaglist2 = nbttagcompound.getList("LeftArm", 5);

        if (nbttaglist2.size() > 0) {
            this.setLeftArmPose(new Vector3f(nbttaglist2));
        } else {
            this.setLeftArmPose(EntityArmorStand.c);
        }

        NBTTagList nbttaglist3 = nbttagcompound.getList("RightArm", 5);

        if (nbttaglist3.size() > 0) {
            this.setRightArmPose(new Vector3f(nbttaglist3));
        } else {
            this.setRightArmPose(EntityArmorStand.d);
        }

        NBTTagList nbttaglist4 = nbttagcompound.getList("LeftLeg", 5);

        if (nbttaglist4.size() > 0) {
            this.setLeftLegPose(new Vector3f(nbttaglist4));
        } else {
            this.setLeftLegPose(EntityArmorStand.e);
        }

        NBTTagList nbttaglist5 = nbttagcompound.getList("RightLeg", 5);

        if (nbttaglist5.size() > 0) {
            this.setRightLegPose(new Vector3f(nbttaglist5));
        } else {
            this.setRightLegPose(EntityArmorStand.f);
        }

    }

    private NBTTagCompound y() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();

        if (!EntityArmorStand.a.equals(this.headPose)) {
            nbttagcompound.set("Head", this.headPose.a());
        }

        if (!EntityArmorStand.b.equals(this.bodyPose)) {
            nbttagcompound.set("Body", this.bodyPose.a());
        }

        if (!EntityArmorStand.c.equals(this.leftArmPose)) {
            nbttagcompound.set("LeftArm", this.leftArmPose.a());
        }

        if (!EntityArmorStand.d.equals(this.rightArmPose)) {
            nbttagcompound.set("RightArm", this.rightArmPose.a());
        }

        if (!EntityArmorStand.e.equals(this.leftLegPose)) {
            nbttagcompound.set("LeftLeg", this.leftLegPose.a());
        }

        if (!EntityArmorStand.f.equals(this.rightLegPose)) {
            nbttagcompound.set("RightLeg", this.rightLegPose.a());
        }

        return nbttagcompound;
    }

    public boolean ae() {
        return false;
    }

    protected void s(Entity entity) {}

    protected void bK() {
        List list = this.world.getEntities(this, this.getBoundingBox());

        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); ++i) {
                Entity entity = (Entity) list.get(i);

                if (entity instanceof EntityMinecartAbstract && ((EntityMinecartAbstract) entity).s() == EnumMinecartType.RIDEABLE && this.h(entity) <= 0.2D) {
                    entity.collide(this);
                }
            }
        }

    }

    public boolean a(EntityHuman entityhuman, Vec3D vec3d) {
        if (!this.world.isStatic && !entityhuman.v()) {
            byte b0 = 0;
            ItemStack itemstack = entityhuman.bY();
            boolean flag = itemstack != null;

            if (flag && itemstack.getItem() instanceof ItemArmor) {
                ItemArmor itemarmor = (ItemArmor) itemstack.getItem();

                if (itemarmor.b == 3) {
                    b0 = 1;
                } else if (itemarmor.b == 2) {
                    b0 = 2;
                } else if (itemarmor.b == 1) {
                    b0 = 3;
                } else if (itemarmor.b == 0) {
                    b0 = 4;
                }
            }

            if (flag && (itemstack.getItem() == Items.SKULL || itemstack.getItem() == Item.getItemOf(Blocks.PUMPKIN))) {
                b0 = 4;
            }

            double d0 = 0.1D;
            double d1 = 0.9D;
            double d2 = 0.4D;
            double d3 = 1.6D;
            byte b1 = 0;
            boolean flag1 = this.isSmall();
            double d4 = flag1 ? vec3d.b * 2.0D : vec3d.b;

            if (d4 >= 0.1D && d4 < 0.1D + (flag1 ? 0.8D : 0.45D) && this.items[1] != null) {
                b1 = 1;
            } else if (d4 >= 0.9D + (flag1 ? 0.3D : 0.0D) && d4 < 0.9D + (flag1 ? 1.0D : 0.7D) && this.items[3] != null) {
                b1 = 3;
            } else if (d4 >= 0.4D && d4 < 0.4D + (flag1 ? 1.0D : 0.8D) && this.items[2] != null) {
                b1 = 2;
            } else if (d4 >= 1.6D && this.items[4] != null) {
                b1 = 4;
            }

            boolean flag2 = this.items[b1] != null;

            if ((this.bg & 1 << b1) != 0 || (this.bg & 1 << b0) != 0) {
                b1 = b0;
                if ((this.bg & 1 << b0) != 0) {
                    if ((this.bg & 1) != 0) {
                        return true;
                    }

                    b1 = 0;
                }
            }

            if (flag && b0 == 0 && !this.hasArms()) {
                return true;
            } else {
                if (flag) {
                    this.a(entityhuman, b0);
                } else if (flag2) {
                    this.a(entityhuman, b1);
                }

                return true;
            }
        } else {
            return true;
        }
    }

    private void a(EntityHuman entityhuman, int i) {
        ItemStack itemstack = this.items[i];

        if (itemstack == null || (this.bg & 1 << i + 8) == 0) {
            if (itemstack != null || (this.bg & 1 << i + 16) == 0) {
                int j = entityhuman.inventory.itemInHandIndex;
                ItemStack itemstack1 = entityhuman.inventory.getItem(j);
                ItemStack itemstack2;

                if (entityhuman.abilities.canInstantlyBuild && (itemstack == null || itemstack.getItem() == Item.getItemOf(Blocks.AIR)) && itemstack1 != null) {
                    itemstack2 = itemstack1.cloneItemStack();
                    itemstack2.count = 1;
                    this.setEquipment(i, itemstack2);
                } else if (itemstack1 != null && itemstack1.count > 1) {
                    if (itemstack == null) {
                        itemstack2 = itemstack1.cloneItemStack();
                        itemstack2.count = 1;
                        this.setEquipment(i, itemstack2);
                        --itemstack1.count;
                    }
                } else {
                    this.setEquipment(i, itemstack1);
                    entityhuman.inventory.setItem(j, itemstack);
                }
            }
        }
    }

    public boolean damageEntity(DamageSource damagesource, float f) {
        if (!this.world.isStatic && !this.h) {
            if (DamageSource.OUT_OF_WORLD.equals(damagesource)) {
                this.die();
                return false;
            } else if (this.isInvulnerable(damagesource)) {
                return false;
            } else if (damagesource.isExplosion()) {
                this.C();
                this.die();
                return false;
            } else if (DamageSource.FIRE.equals(damagesource)) {
                if (!this.isBurning()) {
                    this.setOnFire(5);
                } else {
                    this.a(0.15F);
                }

                return false;
            } else if (DamageSource.BURN.equals(damagesource) && this.getHealth() > 0.5F) {
                this.a(4.0F);
                return false;
            } else {
                boolean flag = "arrow".equals(damagesource.p());
                boolean flag1 = "player".equals(damagesource.p());

                if (!flag1 && !flag) {
                    return false;
                } else {
                    if (damagesource.i() instanceof EntityArrow) {
                        damagesource.i().die();
                    }

                    if (damagesource.getEntity() instanceof EntityHuman && !((EntityHuman) damagesource.getEntity()).abilities.mayBuild) {
                        return false;
                    } else if (damagesource.u()) {
                        this.z();
                        this.die();
                        return false;
                    } else {
                        long i = this.world.getTime();

                        if (i - this.i > 5L && !flag) {
                            this.i = i;
                        } else {
                            this.A();
                            this.z();
                            this.die();
                        }

                        return false;
                    }
                }
            }
        } else {
            return false;
        }
    }

    private void z() {
        if (this.world instanceof WorldServer) {
            ((WorldServer) this.world).a(EnumParticle.BLOCK_DUST, this.locX, this.locY + (double) this.length / 1.5D, this.locZ, 10, (double) (this.width / 4.0F), (double) (this.length / 4.0F), (double) (this.width / 4.0F), 0.05D, new int[] { Block.getCombinedId(Blocks.PLANKS.getBlockData())});
        }

    }

    private void a(float f) {
        float f1 = this.getHealth();

        f1 -= f;
        if (f1 <= 0.5F) {
            this.C();
            this.die();
        } else {
            this.setHealth(f1);
        }

    }

    private void A() {
        Block.a(this.world, new BlockPosition(this), new ItemStack(Items.ARMOR_STAND));
        this.C();
    }

    private void C() {
        for (int i = 0; i < this.items.length; ++i) {
            if (this.items[i] != null && this.items[i].count > 0) {
                if (this.items[i] != null) {
                    Block.a(this.world, (new BlockPosition(this)).up(), this.items[i]);
                }

                this.items[i] = null;
            }
        }

    }

    protected float h(float f, float f1) {
        this.aH = this.lastYaw;
        this.aG = this.yaw;
        return 0.0F;
    }

    public float getHeadHeight() {
        return this.isBaby() ? this.length * 0.5F : this.length * 0.9F;
    }

    public void g(float f, float f1) {
        if (!this.hasGravity()) {
            super.g(f, f1);
        }
    }

    public void s_() {
        super.s_();
        Vector3f vector3f = this.datawatcher.h(11);

        if (!this.headPose.equals(vector3f)) {
            this.setHeadPose(vector3f);
        }

        Vector3f vector3f1 = this.datawatcher.h(12);

        if (!this.bodyPose.equals(vector3f1)) {
            this.setBodyPose(vector3f1);
        }

        Vector3f vector3f2 = this.datawatcher.h(13);

        if (!this.leftArmPose.equals(vector3f2)) {
            this.setLeftArmPose(vector3f2);
        }

        Vector3f vector3f3 = this.datawatcher.h(14);

        if (!this.rightArmPose.equals(vector3f3)) {
            this.setRightArmPose(vector3f3);
        }

        Vector3f vector3f4 = this.datawatcher.h(15);

        if (!this.leftLegPose.equals(vector3f4)) {
            this.setLeftLegPose(vector3f4);
        }

        Vector3f vector3f5 = this.datawatcher.h(16);

        if (!this.rightLegPose.equals(vector3f5)) {
            this.setRightLegPose(vector3f5);
        }

    }

    protected void B() {
        this.setInvisible(this.h);
    }

    public void setInvisible(boolean flag) {
        this.h = flag;
        super.setInvisible(flag);
    }

    public boolean isBaby() {
        return this.isSmall();
    }

    public void G() {
        this.die();
    }

    public boolean aV() {
        return this.isInvisible();
    }

    private void setSmall(boolean flag) {
        byte b0 = this.datawatcher.getByte(10);

        if (flag) {
            b0 = (byte) (b0 | 1);
        } else {
            b0 &= -2;
        }

        this.datawatcher.watch(10, Byte.valueOf(b0));
    }

    public boolean isSmall() {
        return (this.datawatcher.getByte(10) & 1) != 0;
    }

    private void setGravity(boolean flag) {
        byte b0 = this.datawatcher.getByte(10);

        if (flag) {
            b0 = (byte) (b0 | 2);
        } else {
            b0 &= -3;
        }

        this.datawatcher.watch(10, Byte.valueOf(b0));
    }

    public boolean hasGravity() {
        return (this.datawatcher.getByte(10) & 2) != 0;
    }

    private void setArms(boolean flag) {
        byte b0 = this.datawatcher.getByte(10);

        if (flag) {
            b0 = (byte) (b0 | 4);
        } else {
            b0 &= -5;
        }

        this.datawatcher.watch(10, Byte.valueOf(b0));
    }

    public boolean hasArms() {
        return (this.datawatcher.getByte(10) & 4) != 0;
    }

    private void setBasePlate(boolean flag) {
        byte b0 = this.datawatcher.getByte(10);

        if (flag) {
            b0 = (byte) (b0 | 8);
        } else {
            b0 &= -9;
        }

        this.datawatcher.watch(10, Byte.valueOf(b0));
    }

    public boolean hasBasePlate() {
        return (this.datawatcher.getByte(10) & 8) != 0;
    }

    public void setHeadPose(Vector3f vector3f) {
        this.headPose = vector3f;
        this.datawatcher.watch(11, vector3f);
    }

    public void setBodyPose(Vector3f vector3f) {
        this.bodyPose = vector3f;
        this.datawatcher.watch(12, vector3f);
    }

    public void setLeftArmPose(Vector3f vector3f) {
        this.leftArmPose = vector3f;
        this.datawatcher.watch(13, vector3f);
    }

    public void setRightArmPose(Vector3f vector3f) {
        this.rightArmPose = vector3f;
        this.datawatcher.watch(14, vector3f);
    }

    public void setLeftLegPose(Vector3f vector3f) {
        this.leftLegPose = vector3f;
        this.datawatcher.watch(15, vector3f);
    }

    public void setRightLegPose(Vector3f vector3f) {
        this.rightLegPose = vector3f;
        this.datawatcher.watch(16, vector3f);
    }

    public Vector3f s() {
        return this.headPose;
    }

    public Vector3f t() {
        return this.bodyPose;
    }
}
