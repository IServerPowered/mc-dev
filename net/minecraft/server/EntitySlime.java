package net.minecraft.server;

public class EntitySlime extends EntityInsentient implements IMonster {

    public float lookController;
    public float entityCount;
    public float goalTarget;
    private boolean bk;

    public EntitySlime(World world) {
        super(world);
        this.moveController = new EntitySlime.d(this);
        this.goalSelector.a(1, new EntitySlime.b(this));
        this.goalSelector.a(2, new EntitySlime.a(this));
        this.goalSelector.a(3, new EntitySlime.e(this));
        this.goalSelector.a(5, new EntitySlime.c(this));
        this.targetSelector.a(1, new PathfinderGoalTargetNearestPlayer(this));
        this.targetSelector.a(3, new PathfinderGoalNearestAttackableTargetInsentient(this, EntityIronGolem.class));
    }

    protected void h() {
        super.h();
        this.datawatcher.a(16, Byte.valueOf((byte) 1));
    }

    protected void setSize(int i) {
        this.datawatcher.watch(16, Byte.valueOf((byte) i));
        this.setSize(0.51000005F * (float) i, 0.51000005F * (float) i);
        this.setPosition(this.locX, this.locY, this.locZ);
        this.getAttributeInstance(GenericAttributes.maxHealth).setValue((double) (i * i));
        this.getAttributeInstance(GenericAttributes.d).setValue((double) (0.2F + 0.1F * (float) i));
        this.setHealth(this.getMaxHealth());
        this.b_ = i;
    }

    public int getSize() {
        return this.datawatcher.getByte(16);
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.setInt("Size", this.getSize() - 1);
        nbttagcompound.setBoolean("wasOnGround", this.bk);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        int i = nbttagcompound.getInt("Size");

        if (i < 0) {
            i = 0;
        }

        this.setSize(i + 1);
        this.bk = nbttagcompound.getBoolean("wasOnGround");
    }

    protected EnumParticle n() {
        return EnumParticle.SLIME;
    }

    protected String ck() {
        return "mob.slime." + (this.getSize() > 1 ? "big" : "small");
    }

    public void t_() {
        if (!this.world.isClientSide && this.world.getDifficulty() == EnumDifficulty.PEACEFUL && this.getSize() > 0) {
            this.dead = true;
        }

        this.entityCount += (this.lookController - this.entityCount) * 0.5F;
        this.goalTarget = this.entityCount;
        super.t_();
        if (this.onGround && !this.bk) {
            int i = this.getSize();

            for (int j = 0; j < i * 8; ++j) {
                float f = this.random.nextFloat() * 3.1415927F * 2.0F;
                float f1 = this.random.nextFloat() * 0.5F + 0.5F;
                float f2 = MathHelper.sin(f) * (float) i * 0.5F * f1;
                float f3 = MathHelper.cos(f) * (float) i * 0.5F * f1;
                World world = this.world;
                EnumParticle enumparticle = this.n();
                double d0 = this.locX + (double) f2;
                double d1 = this.locZ + (double) f3;

                world.addParticle(enumparticle, d0, this.getBoundingBox().b, d1, 0.0D, 0.0D, 0.0D, new int[0]);
            }

            if (this.cl()) {
                this.makeSound(this.ck(), this.bB(), ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) / 0.8F);
            }

            this.lookController = -0.5F;
        } else if (!this.onGround && this.bk) {
            this.lookController = 1.0F;
        }

        this.bk = this.onGround;
        this.ch();
    }

    protected void ch() {
        this.lookController *= 0.6F;
    }

    protected int cg() {
        return this.random.nextInt(20) + 10;
    }

    protected EntitySlime cf() {
        return new EntitySlime(this.world);
    }

    public void i(int i) {
        if (i == 16) {
            int j = this.getSize();

            this.setSize(0.51000005F * (float) j, 0.51000005F * (float) j);
            this.yaw = this.aK;
            this.aI = this.aK;
            if (this.V() && this.random.nextInt(20) == 0) {
                this.X();
            }
        }

        super.i(i);
    }

    public void die() {
        int i = this.getSize();

        if (!this.world.isClientSide && i > 1 && this.getHealth() <= 0.0F) {
            int j = 2 + this.random.nextInt(3);

            for (int k = 0; k < j; ++k) {
                float f = ((float) (k % 2) - 0.5F) * (float) i / 4.0F;
                float f1 = ((float) (k / 2) - 0.5F) * (float) i / 4.0F;
                EntitySlime entityslime = this.cf();

                if (this.hasCustomName()) {
                    entityslime.setCustomName(this.getCustomName());
                }

                if (this.isPersistent()) {
                    entityslime.bX();
                }

                entityslime.setSize(i / 2);
                entityslime.setPositionRotation(this.locX + (double) f, this.locY + 0.5D, this.locZ + (double) f1, this.random.nextFloat() * 360.0F, 0.0F);
                this.world.addEntity(entityslime);
            }
        }

        super.die();
    }

    public void collide(Entity entity) {
        super.collide(entity);
        if (entity instanceof EntityIronGolem && this.ci()) {
            this.e((EntityLiving) entity);
        }

    }

    public void d(EntityHuman entityhuman) {
        if (this.ci()) {
            this.e((EntityLiving) entityhuman);
        }

    }

    protected void e(EntityLiving entityliving) {
        int i = this.getSize();

        if (this.hasLineOfSight(entityliving) && this.h(entityliving) < 0.6D * (double) i * 0.6D * (double) i && entityliving.damageEntity(DamageSource.mobAttack(this), (float) this.cj())) {
            this.makeSound("mob.attack", 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
            this.a((EntityLiving) this, (Entity) entityliving);
        }

    }

    public float getHeadHeight() {
        return 0.625F * this.length;
    }

    protected boolean ci() {
        return this.getSize() > 1;
    }

    protected int cj() {
        return this.getSize();
    }

    protected String bo() {
        return "mob.slime." + (this.getSize() > 1 ? "big" : "small");
    }

    protected String bp() {
        return "mob.slime." + (this.getSize() > 1 ? "big" : "small");
    }

    protected Item getLoot() {
        return this.getSize() == 1 ? Items.SLIME : null;
    }

    public boolean bR() {
        BlockPosition blockposition = new BlockPosition(MathHelper.floor(this.locX), 0, MathHelper.floor(this.locZ));
        Chunk chunk = this.world.getChunkAtWorldCoords(blockposition);

        if (this.world.getWorldData().getType() == WorldType.FLAT && this.random.nextInt(4) != 1) {
            return false;
        } else {
            if (this.world.getDifficulty() != EnumDifficulty.PEACEFUL) {
                BiomeBase biomebase = this.world.getBiome(blockposition);

                if (biomebase == BiomeBase.SWAMPLAND && this.locY > 50.0D && this.locY < 70.0D && this.random.nextFloat() < 0.5F && this.random.nextFloat() < this.world.y() && this.world.getLightLevel(new BlockPosition(this)) <= this.random.nextInt(8)) {
                    return super.bR();
                }

                if (this.random.nextInt(10) == 0 && chunk.a(987234911L).nextInt(10) == 0 && this.locY < 40.0D) {
                    return super.bR();
                }
            }

            return false;
        }
    }

    protected float bB() {
        return 0.4F * (float) this.getSize();
    }

    public int bQ() {
        return 0;
    }

    protected boolean cn() {
        return this.getSize() > 0;
    }

    protected boolean cl() {
        return this.getSize() > 2;
    }

    protected void bF() {
        this.motY = 0.41999998688697815D;
        this.ai = true;
    }

    public GroupDataEntity prepare(DifficultyDamageScaler difficultydamagescaler, GroupDataEntity groupdataentity) {
        int i = this.random.nextInt(3);

        if (i < 2 && this.random.nextFloat() < 0.5F * difficultydamagescaler.c()) {
            ++i;
        }

        int j = 1 << i;

        this.setSize(j);
        return super.prepare(difficultydamagescaler, groupdataentity);
    }

    static class c extends PathfinderGoal {

        private EntitySlime a;

        public c(EntitySlime entityslime) {
            this.a = entityslime;
            this.a(5);
        }

        public boolean a() {
            return true;
        }

        public void e() {
            ((EntitySlime.d) this.a.getControllerMove()).a(1.0D);
        }
    }

    static class b extends PathfinderGoal {

        private EntitySlime a;

        public b(EntitySlime entityslime) {
            this.a = entityslime;
            this.a(5);
            ((Navigation) entityslime.getNavigation()).d(true);
        }

        public boolean a() {
            return this.a.V() || this.a.ab();
        }

        public void e() {
            if (this.a.bc().nextFloat() < 0.8F) {
                this.a.getControllerJump().a();
            }

            ((EntitySlime.d) this.a.getControllerMove()).a(1.2D);
        }
    }

    static class e extends PathfinderGoal {

        private EntitySlime a;
        private float b;
        private int c;

        public e(EntitySlime entityslime) {
            this.a = entityslime;
            this.a(2);
        }

        public boolean a() {
            return this.a.getGoalTarget() == null && (this.a.onGround || this.a.V() || this.a.ab());
        }

        public void e() {
            if (--this.c <= 0) {
                this.c = 40 + this.a.bc().nextInt(60);
                this.b = (float) this.a.bc().nextInt(360);
            }

            ((EntitySlime.d) this.a.getControllerMove()).a(this.b, false);
        }
    }

    static class a extends PathfinderGoal {

        private EntitySlime a;
        private int b;

        public a(EntitySlime entityslime) {
            this.a = entityslime;
            this.a(2);
        }

        public boolean a() {
            EntityLiving entityliving = this.a.getGoalTarget();

            return entityliving == null ? false : (!entityliving.isAlive() ? false : !(entityliving instanceof EntityHuman) || !((EntityHuman) entityliving).abilities.isInvulnerable);
        }

        public void c() {
            this.b = 300;
            super.c();
        }

        public boolean b() {
            EntityLiving entityliving = this.a.getGoalTarget();

            return entityliving == null ? false : (!entityliving.isAlive() ? false : (entityliving instanceof EntityHuman && ((EntityHuman) entityliving).abilities.isInvulnerable ? false : --this.b > 0));
        }

        public void e() {
            this.a.a(this.a.getGoalTarget(), 10.0F, 10.0F);
            ((EntitySlime.d) this.a.getControllerMove()).a(this.a.yaw, this.a.ci());
        }
    }

    static class d extends ControllerMove {

        private float g;
        private int h;
        private EntitySlime i;
        private boolean j;

        public d(EntitySlime entityslime) {
            super(entityslime);
            this.i = entityslime;
        }

        public void a(float f, boolean flag) {
            this.g = f;
            this.j = flag;
        }

        public void a(double d0) {
            this.e = d0;
            this.f = true;
        }

        public void c() {
            this.a.yaw = this.a(this.a.yaw, this.g, 30.0F);
            this.a.aK = this.a.yaw;
            this.a.aI = this.a.yaw;
            if (!this.f) {
                this.a.n(0.0F);
            } else {
                this.f = false;
                if (this.a.onGround) {
                    this.a.k((float) (this.e * this.a.getAttributeInstance(GenericAttributes.d).getValue()));
                    if (this.h-- <= 0) {
                        this.h = this.i.cg();
                        if (this.j) {
                            this.h /= 3;
                        }

                        this.i.getControllerJump().a();
                        if (this.i.cn()) {
                            this.i.makeSound(this.i.ck(), this.i.bB(), ((this.i.bc().nextFloat() - this.i.bc().nextFloat()) * 0.2F + 1.0F) * 0.8F);
                        }
                    } else {
                        this.i.aZ = this.i.ba = 0.0F;
                        this.a.k(0.0F);
                    }
                } else {
                    this.a.k((float) (this.e * this.a.getAttributeInstance(GenericAttributes.d).getValue()));
                }

            }
        }
    }
}
