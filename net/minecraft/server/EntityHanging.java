package net.minecraft.server;

import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.Validate;

public abstract class EntityHanging extends Entity {

    private int c;
    protected BlockPosition blockPosition;
    public EnumDirection direction;

    public EntityHanging(World world) {
        super(world);
        this.setSize(0.5F, 0.5F);
    }

    public EntityHanging(World world, BlockPosition blockposition) {
        this(world);
        this.blockPosition = blockposition;
    }

    protected void h() {}

    protected void setDirection(EnumDirection enumdirection) {
        Validate.notNull(enumdirection);
        Validate.isTrue(enumdirection.k().c());
        this.direction = enumdirection;
        this.lastYaw = this.yaw = (float) (this.direction.b() * 90);
        this.updateBoundingBox();
    }

    private void updateBoundingBox() {
        if (this.direction != null) {
            double d0 = (double) this.blockPosition.getX() + 0.5D;
            double d1 = (double) this.blockPosition.getY() + 0.5D;
            double d2 = (double) this.blockPosition.getZ() + 0.5D;
            double d3 = 0.46875D;
            double d4 = this.a(this.l());
            double d5 = this.a(this.m());

            d0 -= (double) this.direction.getAdjacentX() * 0.46875D;
            d2 -= (double) this.direction.getAdjacentZ() * 0.46875D;
            d1 += d5;
            EnumDirection enumdirection = this.direction.f();

            d0 += d4 * (double) enumdirection.getAdjacentX();
            d2 += d4 * (double) enumdirection.getAdjacentZ();
            this.locX = d0;
            this.locY = d1;
            this.locZ = d2;
            double d6 = (double) this.l();
            double d7 = (double) this.m();
            double d8 = (double) this.l();

            if (this.direction.k() == EnumDirection.a.Z) {
                d8 = 1.0D;
            } else {
                d6 = 1.0D;
            }

            d6 /= 32.0D;
            d7 /= 32.0D;
            d8 /= 32.0D;
            this.a(new AxisAlignedBB(d0 - d6, d1 - d7, d2 - d8, d0 + d6, d1 + d7, d2 + d8));
        }
    }

    private double a(int i) {
        return i % 32 == 0 ? 0.5D : 0.0D;
    }

    public void t_() {
        this.lastX = this.locX;
        this.lastY = this.locY;
        this.lastZ = this.locZ;
        if (this.c++ == 100 && !this.world.isClientSide) {
            this.c = 0;
            if (!this.dead && !this.survives()) {
                this.die();
                this.b((Entity) null);
            }
        }

    }

    public boolean survives() {
        if (!this.world.getCubes(this, this.getBoundingBox()).isEmpty()) {
            return false;
        } else {
            int i = Math.max(1, this.l() / 16);
            int j = Math.max(1, this.m() / 16);
            BlockPosition blockposition = this.blockPosition.shift(this.direction.opposite());
            EnumDirection enumdirection = this.direction.f();

            for (int k = 0; k < i; ++k) {
                for (int l = 0; l < j; ++l) {
                    BlockPosition blockposition1 = blockposition.shift(enumdirection, k).up(l);
                    Block block = this.world.getType(blockposition1).getBlock();

                    if (!block.getMaterial().isBuildable() && !BlockDiodeAbstract.d(block)) {
                        return false;
                    }
                }
            }

            List list = this.world.getEntities(this, this.getBoundingBox());
            Iterator iterator = list.iterator();

            Entity entity;

            do {
                if (!iterator.hasNext()) {
                    return true;
                }

                entity = (Entity) iterator.next();
            } while (!(entity instanceof EntityHanging));

            return false;
        }
    }

    public boolean ad() {
        return true;
    }

    public boolean l(Entity entity) {
        return entity instanceof EntityHuman ? this.damageEntity(DamageSource.playerAttack((EntityHuman) entity), 0.0F) : false;
    }

    public EnumDirection getDirection() {
        return this.direction;
    }

    public boolean damageEntity(DamageSource damagesource, float f) {
        if (this.isInvulnerable(damagesource)) {
            return false;
        } else {
            if (!this.dead && !this.world.isClientSide) {
                this.die();
                this.ac();
                this.b(damagesource.getEntity());
            }

            return true;
        }
    }

    public void move(double d0, double d1, double d2) {
        if (!this.world.isClientSide && !this.dead && d0 * d0 + d1 * d1 + d2 * d2 > 0.0D) {
            this.die();
            this.b((Entity) null);
        }

    }

    public void g(double d0, double d1, double d2) {
        if (!this.world.isClientSide && !this.dead && d0 * d0 + d1 * d1 + d2 * d2 > 0.0D) {
            this.die();
            this.b((Entity) null);
        }

    }

    public void b(NBTTagCompound nbttagcompound) {
        nbttagcompound.setByte("Facing", (byte) this.direction.b());
        nbttagcompound.setInt("TileX", this.getBlockPosition().getX());
        nbttagcompound.setInt("TileY", this.getBlockPosition().getY());
        nbttagcompound.setInt("TileZ", this.getBlockPosition().getZ());
    }

    public void a(NBTTagCompound nbttagcompound) {
        this.blockPosition = new BlockPosition(nbttagcompound.getInt("TileX"), nbttagcompound.getInt("TileY"), nbttagcompound.getInt("TileZ"));
        EnumDirection enumdirection;

        if (nbttagcompound.hasKeyOfType("Direction", 99)) {
            enumdirection = EnumDirection.fromType2(nbttagcompound.getByte("Direction"));
            this.blockPosition = this.blockPosition.shift(enumdirection);
        } else if (nbttagcompound.hasKeyOfType("Facing", 99)) {
            enumdirection = EnumDirection.fromType2(nbttagcompound.getByte("Facing"));
        } else {
            enumdirection = EnumDirection.fromType2(nbttagcompound.getByte("Dir"));
        }

        this.setDirection(enumdirection);
    }

    public abstract int l();

    public abstract int m();

    public abstract void b(Entity entity);

    protected boolean af() {
        return false;
    }

    public void setPosition(double d0, double d1, double d2) {
        this.locX = d0;
        this.locY = d1;
        this.locZ = d2;
        BlockPosition blockposition = this.blockPosition;

        this.blockPosition = new BlockPosition(d0, d1, d2);
        if (!this.blockPosition.equals(blockposition)) {
            this.updateBoundingBox();
            this.ai = true;
        }

    }

    public BlockPosition getBlockPosition() {
        return this.blockPosition;
    }
}
