package net.minecraft.server;

class MobSpawnerMinecart extends MobSpawnerAbstract {

    final EntityMinecartMobSpawner spawnDelay;

    MobSpawnerMinecart(EntityMinecartMobSpawner entityminecartmobspawner) {
        this.spawnDelay = entityminecartmobspawner;
    }

    public void a(int i) {
        this.spawnDelay.world.broadcastEntityEffect(this.spawnDelay, (byte) i);
    }

    public World a() {
        return this.spawnDelay.world;
    }

    public BlockPosition b() {
        return new BlockPosition(this.spawnDelay);
    }
}
