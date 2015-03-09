package net.minecraft.server;

public class EntitySquid extends EntityWaterAnimal {

    public float lookController;
    public float entityCount;
    public float goalTarget;
    public float bk;
    public float equipment;
    public float canPickUpLoot;
    public float persistent;
    public float bo;
    private float bp;
    private float bq;
    private float br;
    private float bs;
    private float bt;
    private float bu;

    public EntitySquid(World world) {
        super(world);
        this.setSize(0.95F, 0.95F);
        this.random.setSeed((long) (1 + this.getId()));
        this.bq = 1.0F / (this.random.nextFloat() + 1.0F) * 0.2F;
        this.goalSelector.a(0, new EntitySquid.a(this));
    }

    protected void initAttributes() {
        super.initAttributes();
        this.getAttributeInstance(GenericAttributes.maxHealth).setValue(10.0D);
    }

    public float getHeadHeight() {
        return this.length * 0.5F;
    }

    protected String z() {
        return null;
    }

    protected String bo() {
        return null;
    }

    protected String bp() {
        return null;
    }

    protected float bB() {
        return 0.4F;
    }

    protected Item getLoot() {
        return null;
    }

    protected boolean s_() {
        return false;
    }

    protected void dropDeathLoot(boolean flag, int i) {
        int j = this.random.nextInt(3 + i) + 1;

        for (int k = 0; k < j; ++k) {
            this.a(new ItemStack(Items.DYE, 1, EnumColor.BLACK.getInvColorIndex()), 0.0F);
        }

    }

    public boolean V() {
        return this.world.a(this.getBoundingBox().grow(0.0D, -0.6000000238418579D, 0.0D), Material.WATER, (Entity) this);
    }

    public void recalcPosition() {
        super.recalcPosition();
        this.entityCount = this.lookController;
        this.bk = this.goalTarget;
        this.canPickUpLoot = this.equipment;
        this.bo = this.persistent;
        this.equipment += this.bq;
        if ((double) this.equipment > 6.283185307179586D) {
            if (this.world.isClientSide) {
                this.equipment = 6.2831855F;
            } else {
                this.equipment = (float) ((double) this.equipment - 6.283185307179586D);
                if (this.random.nextInt(10) == 0) {
                    this.bq = 1.0F / (this.random.nextFloat() + 1.0F) * 0.2F;
                }

                this.world.broadcastEntityEffect(this, (byte) 19);
            }
        }

        if (this.inWater) {
            float f;

            if (this.equipment < 3.1415927F) {
                f = this.equipment / 3.1415927F;
                this.persistent = MathHelper.sin(f * f * 3.1415927F) * 3.1415927F * 0.25F;
                if ((double) f > 0.75D) {
                    this.bp = 1.0F;
                    this.br = 1.0F;
                } else {
                    this.br *= 0.8F;
                }
            } else {
                this.persistent = 0.0F;
                this.bp *= 0.9F;
                this.br *= 0.99F;
            }

            if (!this.world.isClientSide) {
                this.motX = (double) (this.bs * this.bp);
                this.motY = (double) (this.bt * this.bp);
                this.motZ = (double) (this.bu * this.bp);
            }

            f = MathHelper.sqrt(this.motX * this.motX + this.motZ * this.motZ);
            this.aI += (-((float) MathHelper.b(this.motX, this.motZ)) * 180.0F / 3.1415927F - this.aI) * 0.1F;
            this.yaw = this.aI;
            this.goalTarget = (float) ((double) this.goalTarget + 3.141592653589793D * (double) this.br * 1.5D);
            this.lookController += (-((float) MathHelper.b((double) f, this.motY)) * 180.0F / 3.1415927F - this.lookController) * 0.1F;
        } else {
            this.persistent = MathHelper.e(MathHelper.sin(this.equipment)) * 3.1415927F * 0.25F;
            if (!this.world.isClientSide) {
                this.motX = 0.0D;
                this.motY -= 0.08D;
                this.motY *= 0.9800000190734863D;
                this.motZ = 0.0D;
            }

            this.lookController = (float) ((double) this.lookController + (double) (-90.0F - this.lookController) * 0.02D);
        }

    }

    public void g(float f, float f1) {
        this.move(this.motX, this.motY, this.motZ);
    }

    public boolean bR() {
        return this.locY > 45.0D && this.locY < (double) this.world.F() && super.bR();
    }

    public void b(float f, float f1, float f2) {
        this.bs = f;
        this.bt = f1;
        this.bu = f2;
    }

    public boolean n() {
        return this.bs != 0.0F || this.bt != 0.0F || this.bu != 0.0F;
    }

    static class a extends PathfinderGoal {

        private EntitySquid a;

        public a(EntitySquid entitysquid) {
            this.a = entitysquid;
        }

        public boolean a() {
            return true;
        }

        public void e() {
            int i = this.a.bh();

            if (i > 100) {
                this.a.b(0.0F, 0.0F, 0.0F);
            } else if (this.a.bc().nextInt(50) == 0 || !this.a.inWater || !this.a.n()) {
                float f = this.a.bc().nextFloat() * 3.1415927F * 2.0F;
                float f1 = MathHelper.cos(f) * 0.2F;
                float f2 = -0.1F + this.a.bc().nextFloat() * 0.2F;
                float f3 = MathHelper.sin(f) * 0.2F;

                this.a.b(f1, f2, f3);
            }

        }
    }
}
