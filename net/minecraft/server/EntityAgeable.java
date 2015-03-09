package net.minecraft.server;

public abstract class EntityAgeable extends EntityCreature {

    protected int lookController;
    protected int entityCount;
    protected int goalTarget;
    private float bm = -1.0F;
    private float bn;

    public EntityAgeable(World world) {
        super(world);
    }

    public abstract EntityAgeable createChild(EntityAgeable entityageable);

    public boolean a(EntityHuman entityhuman) {
        ItemStack itemstack = entityhuman.inventory.getItemInHand();

        if (itemstack != null && itemstack.getItem() == Items.SPAWN_EGG) {
            if (!this.world.isClientSide) {
                Class oclass = EntityTypes.a(itemstack.getData());

                if (oclass != null && this.getClass() == oclass) {
                    EntityAgeable entityageable = this.createChild(this);

                    if (entityageable != null) {
                        entityageable.setAgeRaw(-24000);
                        entityageable.setPositionRotation(this.locX, this.locY, this.locZ, 0.0F, 0.0F);
                        this.world.addEntity(entityageable);
                        if (itemstack.hasName()) {
                            entityageable.setCustomName(itemstack.getName());
                        }

                        if (!entityhuman.abilities.canInstantlyBuild) {
                            --itemstack.count;
                            if (itemstack.count <= 0) {
                                entityhuman.inventory.setItem(entityhuman.inventory.itemInHandIndex, (ItemStack) null);
                            }
                        }
                    }
                }
            }

            return true;
        } else {
            return false;
        }
    }

    protected void h() {
        super.h();
        this.datawatcher.a(12, Byte.valueOf((byte) 0));
    }

    public int getAge() {
        return this.world.isClientSide ? this.datawatcher.getByte(12) : this.lookController;
    }

    public void setAge(int i, boolean flag) {
        int j = this.getAge();
        int k = j;

        j += i * 20;
        if (j > 0) {
            j = 0;
            if (k < 0) {
                this.n();
            }
        }

        int l = j - k;

        this.setAgeRaw(j);
        if (flag) {
            this.entityCount += l;
            if (this.goalTarget == 0) {
                this.goalTarget = 40;
            }
        }

        if (this.getAge() == 0) {
            this.setAgeRaw(this.entityCount);
        }

    }

    public void setAge(int i) {
        this.setAge(i, false);
    }

    public void setAgeRaw(int i) {
        this.datawatcher.watch(12, Byte.valueOf((byte) MathHelper.clamp(i, -1, 1)));
        this.lookController = i;
        this.a(this.isBaby());
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.setInt("Age", this.getAge());
        nbttagcompound.setInt("ForcedAge", this.entityCount);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.setAgeRaw(nbttagcompound.getInt("Age"));
        this.entityCount = nbttagcompound.getInt("ForcedAge");
    }

    public void recalcPosition() {
        super.recalcPosition();
        if (this.world.isClientSide) {
            if (this.goalTarget > 0) {
                if (this.goalTarget % 4 == 0) {
                    this.world.addParticle(EnumParticle.VILLAGER_HAPPY, this.locX + (double) (this.random.nextFloat() * this.width * 2.0F) - (double) this.width, this.locY + 0.5D + (double) (this.random.nextFloat() * this.length), this.locZ + (double) (this.random.nextFloat() * this.width * 2.0F) - (double) this.width, 0.0D, 0.0D, 0.0D, new int[0]);
                }

                --this.goalTarget;
            }

            this.a(this.isBaby());
        } else {
            int i = this.getAge();

            if (i < 0) {
                ++i;
                this.setAgeRaw(i);
                if (i == 0) {
                    this.n();
                }
            } else if (i > 0) {
                --i;
                this.setAgeRaw(i);
            }
        }

    }

    protected void n() {}

    public boolean isBaby() {
        return this.getAge() < 0;
    }

    public void a(boolean flag) {
        this.a(flag ? 0.5F : 1.0F);
    }

    protected final void setSize(float f, float f1) {
        boolean flag = this.bm > 0.0F;

        this.bm = f;
        this.bn = f1;
        if (!flag) {
            this.a(1.0F);
        }

    }

    protected final void a(float f) {
        super.setSize(this.bm * f, this.bn * f);
    }
}
