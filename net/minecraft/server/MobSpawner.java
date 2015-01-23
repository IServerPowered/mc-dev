package net.minecraft.server;

class MobSpawner extends MobSpawnerAbstract {

    final TileEntityMobSpawner spawnDelay;

    MobSpawner(TileEntityMobSpawner tileentitymobspawner) {
        this.spawnDelay = tileentitymobspawner;
    }

    public void a(int i) {
        this.spawnDelay.world.playBlockAction(this.spawnDelay.position, Blocks.MOB_SPAWNER, i, 0);
    }

    public World a() {
        return this.spawnDelay.world;
    }

    public BlockPosition b() {
        return this.spawnDelay.position;
    }

    public void a(TileEntityMobSpawnerData tileentitymobspawnerdata) {
        super.a(tileentitymobspawnerdata);
        if (this.a() != null) {
            this.a().notify(this.spawnDelay.position);
        }

    }
}
