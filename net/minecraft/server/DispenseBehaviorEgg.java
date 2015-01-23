package net.minecraft.server;

final class DispenseBehaviorEgg extends DispenseBehaviorProjectile {

    protected IProjectile a(World world, IPosition iposition) {
        return new EntityEgg(world, iposition.getX(), iposition.getY(), iposition.getZ());
    }
}
