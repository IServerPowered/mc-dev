package net.minecraft.server;

public class EntityRabbit extends EntityAnimal {

    private EntityRabbit.c<EntityWolf> bm;
    private int bo = 0;
    private int bp = 0;
    private boolean bq = false;
    private boolean br = false;
    private int bs = 0;
    private EntityRabbit.b bt;
    private int bu;
    private EntityHuman bv;

    public EntityRabbit(World world) {
        super(world);
        this.bt = EntityRabbit.b.HOP;
        this.bu = 0;
        this.bv = null;
        this.setSize(0.6F, 0.7F);
        this.effects = new EntityRabbit.e(this);
        this.moveController = new EntityRabbit.f(this);
        ((Navigation) this.getNavigation()).a(true);
        this.navigation.a(2.5F);
        this.goalSelector.a(1, new PathfinderGoalFloat(this));
        this.goalSelector.a(1, new EntityRabbit.g(this, 1.33D));
        this.goalSelector.a(2, new PathfinderGoalTempt(this, 1.0D, Items.CARROT, false));
        this.goalSelector.a(2, new PathfinderGoalTempt(this, 1.0D, Items.GOLDEN_CARROT, false));
        this.goalSelector.a(2, new PathfinderGoalTempt(this, 1.0D, Item.getItemOf(Blocks.YELLOW_FLOWER), false));
        this.goalSelector.a(3, new PathfinderGoalBreed(this, 0.8D));
        this.goalSelector.a(5, new EntityRabbit.h(this));
        this.goalSelector.a(5, new PathfinderGoalRandomStroll(this, 0.6D));
        this.goalSelector.a(11, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 10.0F));
        this.bm = new EntityRabbit.c(this, EntityWolf.class, 16.0F, 1.33D, 1.33D);
        this.goalSelector.a(4, this.bm);
        this.b(0.0D);
    }

    protected float bE() {
        return this.moveController.a() && this.moveController.e() > this.locY + 0.5D ? 0.5F : this.bt.b();
    }

    public void a(EntityRabbit.b entityrabbit_b) {
        this.bt = entityrabbit_b;
    }

    public void b(double d0) {
        this.getNavigation().a(d0);
        this.moveController.a(this.moveController.d(), this.moveController.e(), this.moveController.f(), d0);
    }

    public void a(boolean flag, EntityRabbit.b entityrabbit_b) {
        super.i(flag);
        if (!flag) {
            if (this.bt == EntityRabbit.b.ATTACK) {
                this.bt = EntityRabbit.b.HOP;
            }
        } else {
            this.b(1.5D * (double) entityrabbit_b.a());
            this.makeSound(this.cm(), this.bB(), ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) * 0.8F);
        }

        this.bq = flag;
    }

    public void b(EntityRabbit.b entityrabbit_b) {
        this.a(true, entityrabbit_b);
        this.bp = entityrabbit_b.d();
        this.bo = 0;
    }

    public boolean cl() {
        return this.bq;
    }

    protected void h() {
        super.h();
        this.datawatcher.a(18, Byte.valueOf((byte) 0));
    }

    public void E() {
        if (this.moveController.b() > 0.8D) {
            this.a(EntityRabbit.b.SPRINT);
        } else if (this.bt != EntityRabbit.b.ATTACK) {
            this.a(EntityRabbit.b.HOP);
        }

        if (this.bs > 0) {
            --this.bs;
        }

        if (this.bu > 0) {
            this.bu -= this.random.nextInt(3);
            if (this.bu < 0) {
                this.bu = 0;
            }
        }

        if (this.onGround) {
            if (!this.br) {
                this.a(false, EntityRabbit.b.NONE);
                this.cw();
            }

            if (this.getRabbitType() == 99 && this.bs == 0) {
                EntityLiving entityliving = this.getGoalTarget();

                if (entityliving != null && this.h(entityliving) < 16.0D) {
                    this.a(entityliving.locX, entityliving.locZ);
                    this.moveController.a(entityliving.locX, entityliving.locY, entityliving.locZ, this.moveController.b());
                    this.b(EntityRabbit.b.ATTACK);
                    this.br = true;
                }
            }

            EntityRabbit.e entityrabbit_e = (EntityRabbit.e) this.effects;

            if (!entityrabbit_e.c()) {
                if (this.moveController.a() && this.bs == 0) {
                    PathEntity pathentity = this.navigation.j();
                    Vec3D vec3d = new Vec3D(this.moveController.d(), this.moveController.e(), this.moveController.f());

                    if (pathentity != null && pathentity.e() < pathentity.d()) {
                        vec3d = pathentity.a((Entity) this);
                    }

                    this.a(vec3d.a, vec3d.c);
                    this.b(this.bt);
                }
            } else if (!entityrabbit_e.d()) {
                this.ct();
            }
        }

        this.br = this.onGround;
    }

    public void Y() {}

    private void a(double d0, double d1) {
        this.yaw = (float) (MathHelper.b(d1 - this.locZ, d0 - this.locX) * 180.0D / 3.1415927410125732D) - 90.0F;
    }

    private void ct() {
        ((EntityRabbit.e) this.effects).a(true);
    }

    private void cu() {
        ((EntityRabbit.e) this.effects).a(false);
    }

    private void cv() {
        this.bs = this.co();
    }

    private void cw() {
        this.cv();
        this.cu();
    }

    public void recalcPosition() {
        super.recalcPosition();
        if (this.bo != this.bp) {
            if (this.bo == 0 && !this.world.isClientSide) {
                this.world.broadcastEntityEffect(this, (byte) 1);
            }

            ++this.bo;
        } else if (this.bp != 0) {
            this.bo = 0;
            this.bp = 0;
        }

    }

    protected void initAttributes() {
        super.initAttributes();
        this.getAttributeInstance(GenericAttributes.maxHealth).setValue(10.0D);
        this.getAttributeInstance(GenericAttributes.d).setValue(0.30000001192092896D);
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.setInt("RabbitType", this.getRabbitType());
        nbttagcompound.setInt("MoreCarrotTicks", this.bu);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.setRabbitType(nbttagcompound.getInt("RabbitType"));
        this.bu = nbttagcompound.getInt("MoreCarrotTicks");
    }

    protected String cm() {
        return "mob.rabbit.hop";
    }

    protected String z() {
        return "mob.rabbit.idle";
    }

    protected String bo() {
        return "mob.rabbit.hurt";
    }

    protected String bp() {
        return "mob.rabbit.death";
    }

    public boolean r(Entity entity) {
        if (this.getRabbitType() == 99) {
            this.makeSound("mob.attack", 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
            return entity.damageEntity(DamageSource.mobAttack(this), 8.0F);
        } else {
            return entity.damageEntity(DamageSource.mobAttack(this), 3.0F);
        }
    }

    public int br() {
        return this.getRabbitType() == 99 ? 8 : super.br();
    }

    public boolean damageEntity(DamageSource damagesource, float f) {
        return this.isInvulnerable(damagesource) ? false : super.damageEntity(damagesource, f);
    }

    protected void getRareDrop() {
        this.a(new ItemStack(Items.RABBIT_FOOT, 1), 0.0F);
    }

    protected void dropDeathLoot(boolean flag, int i) {
        int j = this.random.nextInt(2) + this.random.nextInt(1 + i);

        int k;

        for (k = 0; k < j; ++k) {
            this.a(Items.RABBIT_HIDE, 1);
        }

        j = this.random.nextInt(2);

        for (k = 0; k < j; ++k) {
            if (this.isBurning()) {
                this.a(Items.COOKED_RABBIT, 1);
            } else {
                this.a(Items.RABBIT, 1);
            }
        }

    }

    private boolean a(Item item) {
        return item == Items.CARROT || item == Items.GOLDEN_CARROT || item == Item.getItemOf(Blocks.YELLOW_FLOWER);
    }

    public EntityRabbit b(EntityAgeable entityageable) {
        EntityRabbit entityrabbit = new EntityRabbit(this.world);

        if (entityageable instanceof EntityRabbit) {
            entityrabbit.setRabbitType(this.random.nextBoolean() ? this.getRabbitType() : ((EntityRabbit) entityageable).getRabbitType());
        }

        return entityrabbit;
    }

    public boolean d(ItemStack itemstack) {
        return itemstack != null && this.a(itemstack.getItem());
    }

    public int getRabbitType() {
        return this.datawatcher.getByte(18);
    }

    public void setRabbitType(int i) {
        if (i == 99) {
            this.goalSelector.a((PathfinderGoal) this.bm);
            this.goalSelector.a(4, new EntityRabbit.a(this));
            this.targetSelector.a(1, new PathfinderGoalHurtByTarget(this, false, new Class[0]));
            this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityHuman.class, true));
            this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityWolf.class, true));
            if (!this.hasCustomName()) {
                this.setCustomName(LocaleI18n.get("entity.KillerBunny.name"));
            }
        }

        this.datawatcher.watch(18, Byte.valueOf((byte) i));
    }

    public GroupDataEntity prepare(DifficultyDamageScaler difficultydamagescaler, GroupDataEntity groupdataentity) {
        Object object = super.prepare(difficultydamagescaler, groupdataentity);
        int i = this.random.nextInt(6);
        boolean flag = false;

        if (object instanceof EntityRabbit.d) {
            i = ((EntityRabbit.d) object).a;
            flag = true;
        } else {
            object = new EntityRabbit.d(i);
        }

        this.setRabbitType(i);
        if (flag) {
            this.setAgeRaw(-24000);
        }

        return (GroupDataEntity) object;
    }

    private boolean cx() {
        return this.bu == 0;
    }

    protected int co() {
        return this.bt.c();
    }

    protected void cp() {
        this.world.addParticle(EnumParticle.BLOCK_DUST, this.locX + (double) (this.random.nextFloat() * this.width * 2.0F) - (double) this.width, this.locY + 0.5D + (double) (this.random.nextFloat() * this.length), this.locZ + (double) (this.random.nextFloat() * this.width * 2.0F) - (double) this.width, 0.0D, 0.0D, 0.0D, new int[] { Block.getCombinedId(Blocks.CARROTS.fromLegacyData(7))});
        this.bu = 100;
    }

    public EntityAgeable createChild(EntityAgeable entityageable) {
        return this.b(entityageable);
    }

    static enum b {

        NONE(0.0F, 0.0F, 30, 1), HOP(0.8F, 0.2F, 20, 10), STEP(1.0F, 0.45F, 14, 14), SPRINT(1.75F, 0.4F, 1, 8), ATTACK(2.0F, 0.7F, 7, 8);

        private final float f;
        private final float g;
        private final int h;
        private final int i;

        private b(float f, float f1, int i, int j) {
            this.f = f;
            this.g = f1;
            this.h = i;
            this.i = j;
        }

        public float a() {
            return this.f;
        }

        public float b() {
            return this.g;
        }

        public int c() {
            return this.h;
        }

        public int d() {
            return this.i;
        }
    }

    static class a extends PathfinderGoalMeleeAttack {

        public a(EntityRabbit entityrabbit) {
            super(entityrabbit, EntityLiving.class, 1.4D, true);
        }

        protected double a(EntityLiving entityliving) {
            return (double) (4.0F + entityliving.width);
        }
    }

    static class g extends PathfinderGoalPanic {

        private EntityRabbit b;

        public g(EntityRabbit entityrabbit, double d0) {
            super(entityrabbit, d0);
            this.b = entityrabbit;
        }

        public void e() {
            super.e();
            this.b.b(this.a);
        }
    }

    static class h extends PathfinderGoalGotoTarget {

        private final EntityRabbit c;
        private boolean d;
        private boolean e = false;

        public h(EntityRabbit entityrabbit) {
            super(entityrabbit, 0.699999988079071D, 16);
            this.c = entityrabbit;
        }

        public boolean a() {
            if (this.a <= 0) {
                if (!this.c.world.getGameRules().getBoolean("mobGriefing")) {
                    return false;
                }

                this.e = false;
                this.d = this.c.cx();
            }

            return super.a();
        }

        public boolean b() {
            return this.e && super.b();
        }

        public void c() {
            super.c();
        }

        public void d() {
            super.d();
        }

        public void e() {
            super.e();
            this.c.getControllerLook().a((double) this.b.getX() + 0.5D, (double) (this.b.getY() + 1), (double) this.b.getZ() + 0.5D, 10.0F, (float) this.c.bQ());
            if (this.f()) {
                World world = this.c.world;
                BlockPosition blockposition = this.b.up();
                IBlockData iblockdata = world.getType(blockposition);
                Block block = iblockdata.getBlock();

                if (this.e && block instanceof BlockCarrots && ((Integer) iblockdata.get(BlockCarrots.AGE)).intValue() == 7) {
                    world.setTypeAndData(blockposition, Blocks.AIR.getBlockData(), 2);
                    world.setAir(blockposition, true);
                    this.c.cp();
                }

                this.e = false;
                this.a = 10;
            }

        }

        protected boolean a(World world, BlockPosition blockposition) {
            Block block = world.getType(blockposition).getBlock();

            if (block == Blocks.FARMLAND) {
                blockposition = blockposition.up();
                IBlockData iblockdata = world.getType(blockposition);

                block = iblockdata.getBlock();
                if (block instanceof BlockCarrots && ((Integer) iblockdata.get(BlockCarrots.AGE)).intValue() == 7 && this.d && !this.e) {
                    this.e = true;
                    return true;
                }
            }

            return false;
        }
    }

    static class c<T extends Entity> extends PathfinderGoalAvoidTarget<T> {

        private EntityRabbit c;

        public c(EntityRabbit entityrabbit, Class<T> oclass, float f, double d0, double d1) {
            super(entityrabbit, oclass, f, d0, d1);
            this.c = entityrabbit;
        }

        public void e() {
            super.e();
        }
    }

    static class f extends ControllerMove {

        private EntityRabbit g;

        public f(EntityRabbit entityrabbit) {
            super(entityrabbit);
            this.g = entityrabbit;
        }

        public void c() {
            if (this.g.onGround && !this.g.cl()) {
                this.g.b(0.0D);
            }

            super.c();
        }
    }

    public class e extends ControllerJump {

        private EntityRabbit c;
        private boolean d = false;

        public e(EntityRabbit entityrabbit) {
            super(entityrabbit);
            this.c = entityrabbit;
        }

        public boolean c() {
            return this.a;
        }

        public boolean d() {
            return this.d;
        }

        public void a(boolean flag) {
            this.d = flag;
        }

        public void b() {
            if (this.a) {
                this.c.b(EntityRabbit.b.STEP);
                this.a = false;
            }

        }
    }

    public static class d implements GroupDataEntity {

        public int a;

        public d(int i) {
            this.a = i;
        }
    }
}
