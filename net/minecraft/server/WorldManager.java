package net.minecraft.server;

import java.util.Iterator;

public class WorldManager implements IWorldAccess {

    private MinecraftServer a;
    private WorldServer world;

    public WorldManager(MinecraftServer minecraftserver, WorldServer worldserver) {
        this.a = minecraftserver;
        this.world = worldserver;
    }

    public void a(int i, boolean flag, double d0, double d1, double d2, double d3, double d4, double d5, int... aint) {}

    public void a(Entity entity) {
        this.world.getTracker().track(entity);
    }

    public void b(Entity entity) {
        this.world.getTracker().untrackEntity(entity);
    }

    public void a(String s, double d0, double d1, double d2, float f, float f1) {
        this.a.getPlayerList().sendPacketNearby(d0, d1, d2, f > 1.0F ? (double) (16.0F * f) : 16.0D, this.world.worldProvider.getDimension(), new PacketPlayOutNamedSoundEffect(s, d0, d1, d2, f, f1));
    }

    public void a(EntityHuman entityhuman, String s, double d0, double d1, double d2, float f, float f1) {
        this.a.getPlayerList().sendPacketNearby(entityhuman, d0, d1, d2, f > 1.0F ? (double) (16.0F * f) : 16.0D, this.world.worldProvider.getDimension(), new PacketPlayOutNamedSoundEffect(s, d0, d1, d2, f, f1));
    }

    public void a(int i, int j, int k, int l, int i1, int j1) {}

    public void a(BlockPosition blockposition) {
        this.world.getPlayerChunkMap().flagDirty(blockposition);
    }

    public void b(BlockPosition blockposition) {}

    public void a(String s, BlockPosition blockposition) {}

    public void a(EntityHuman entityhuman, int i, BlockPosition blockposition, int j) {
        this.a.getPlayerList().sendPacketNearby(entityhuman, (double) blockposition.getX(), (double) blockposition.getY(), (double) blockposition.getZ(), 64.0D, this.world.worldProvider.getDimension(), new PacketPlayOutWorldEvent(i, blockposition, j, false));
    }

    public void a(int i, BlockPosition blockposition, int j) {
        this.a.getPlayerList().sendAll(new PacketPlayOutWorldEvent(i, blockposition, j, true));
    }

    public void b(int i, BlockPosition blockposition, int j) {
        Iterator iterator = this.a.getPlayerList().players.iterator();

        while (iterator.hasNext()) {
            EntityPlayer entityplayer = (EntityPlayer) iterator.next();

            if (entityplayer != null && entityplayer.world == this.world && entityplayer.getId() != i) {
                double d0 = (double) blockposition.getX() - entityplayer.locX;
                double d1 = (double) blockposition.getY() - entityplayer.locY;
                double d2 = (double) blockposition.getZ() - entityplayer.locZ;

                if (d0 * d0 + d1 * d1 + d2 * d2 < 1024.0D) {
                    entityplayer.playerConnection.sendPacket(new PacketPlayOutBlockBreakAnimation(i, blockposition, j));
                }
            }
        }

    }
}