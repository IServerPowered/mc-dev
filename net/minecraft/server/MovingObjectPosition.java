package net.minecraft.server;

public class MovingObjectPosition {

    private BlockPosition e;
    public MovingObjectPosition.a type;
    public EnumDirection direction;
    public Vec3D pos;
    public Entity entity;

    public MovingObjectPosition(Vec3D vec3d, EnumDirection enumdirection, BlockPosition blockposition) {
        this(MovingObjectPosition.a.BLOCK, vec3d, enumdirection, blockposition);
    }

    public MovingObjectPosition(Vec3D vec3d, EnumDirection enumdirection) {
        this(MovingObjectPosition.a.BLOCK, vec3d, enumdirection, BlockPosition.ZERO);
    }

    public MovingObjectPosition(Entity entity) {
        this(entity, new Vec3D(entity.locX, entity.locY, entity.locZ));
    }

    public MovingObjectPosition(MovingObjectPosition.a movingobjectposition_a, Vec3D vec3d, EnumDirection enumdirection, BlockPosition blockposition) {
        this.type = movingobjectposition_a;
        this.e = blockposition;
        this.direction = enumdirection;
        this.pos = new Vec3D(vec3d.a, vec3d.b, vec3d.c);
    }

    public MovingObjectPosition(Entity entity, Vec3D vec3d) {
        this.type = MovingObjectPosition.a.ENTITY;
        this.entity = entity;
        this.pos = vec3d;
    }

    public BlockPosition a() {
        return this.e;
    }

    public String toString() {
        return "HitResult{type=" + this.type + ", blockpos=" + this.e + ", f=" + this.direction + ", pos=" + this.pos + ", entity=" + this.entity + '}';
    }

    public static enum a {

        MISS, BLOCK, ENTITY;

        private a() {}
    }
}
