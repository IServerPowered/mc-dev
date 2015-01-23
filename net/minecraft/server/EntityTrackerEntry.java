package net.minecraft.server;

import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EntityTrackerEntry {

    private static final Logger p = LogManager.getLogger();
    public Entity tracker;
    public int b;
    public int c;
    public int xLoc;
    public int yLoc;
    public int zLoc;
    public int yRot;
    public int xRot;
    public int i;
    public double j;
    public double k;
    public double l;
    public int m;
    private double q;
    private double r;
    private double s;
    private boolean isMoving;
    private boolean u;
    private int v;
    private Entity w;
    private boolean x;
    private boolean y;
    public boolean n;
    public Set trackedPlayers = Sets.newHashSet();

    public EntityTrackerEntry(Entity entity, int i, int j, boolean flag) {
        this.tracker = entity;
        this.b = i;
        this.c = j;
        this.u = flag;
        this.xLoc = MathHelper.floor(entity.locX * 32.0D);
        this.yLoc = MathHelper.floor(entity.locY * 32.0D);
        this.zLoc = MathHelper.floor(entity.locZ * 32.0D);
        this.yRot = MathHelper.d(entity.yaw * 256.0F / 360.0F);
        this.xRot = MathHelper.d(entity.pitch * 256.0F / 360.0F);
        this.i = MathHelper.d(entity.getHeadRotation() * 256.0F / 360.0F);
        this.y = entity.onGround;
    }

    public boolean equals(Object object) {
        return object instanceof EntityTrackerEntry ? ((EntityTrackerEntry) object).tracker.getId() == this.tracker.getId() : false;
    }

    public int hashCode() {
        return this.tracker.getId();
    }

    public void track(List list) {
        this.n = false;
        if (!this.isMoving || this.tracker.e(this.q, this.r, this.s) > 16.0D) {
            this.q = this.tracker.locX;
            this.r = this.tracker.locY;
            this.s = this.tracker.locZ;
            this.isMoving = true;
            this.n = true;
            this.scanPlayers(list);
        }

        if (this.w != this.tracker.vehicle || this.tracker.vehicle != null && this.m % 60 == 0) {
            this.w = this.tracker.vehicle;
            this.broadcast(new PacketPlayOutAttachEntity(0, this.tracker, this.tracker.vehicle));
        }

        if (this.tracker instanceof EntityItemFrame && this.m % 10 == 0) {
            EntityItemFrame entityitemframe = (EntityItemFrame) this.tracker;
            ItemStack itemstack = entityitemframe.getItem();

            if (itemstack != null && itemstack.getItem() instanceof ItemWorldMap) {
                WorldMap worldmap = Items.FILLED_MAP.getSavedMap(itemstack, this.tracker.world);
                Iterator iterator = list.iterator();

                while (iterator.hasNext()) {
                    EntityHuman entityhuman = (EntityHuman) iterator.next();
                    EntityPlayer entityplayer = (EntityPlayer) entityhuman;

                    worldmap.a(entityplayer, itemstack);
                    Packet packet = Items.FILLED_MAP.c(itemstack, this.tracker.world, entityplayer);

                    if (packet != null) {
                        entityplayer.playerConnection.sendPacket(packet);
                    }
                }
            }

            this.b();
        }

        if (this.m % this.c == 0 || this.tracker.ai || this.tracker.getDataWatcher().a()) {
            int i;
            int j;

            if (this.tracker.vehicle == null) {
                ++this.v;
                i = MathHelper.floor(this.tracker.locX * 32.0D);
                j = MathHelper.floor(this.tracker.locY * 32.0D);
                int k = MathHelper.floor(this.tracker.locZ * 32.0D);
                int l = MathHelper.d(this.tracker.yaw * 256.0F / 360.0F);
                int i1 = MathHelper.d(this.tracker.pitch * 256.0F / 360.0F);
                int j1 = i - this.xLoc;
                int k1 = j - this.yLoc;
                int l1 = k - this.zLoc;
                Object object = null;
                boolean flag = Math.abs(j1) >= 4 || Math.abs(k1) >= 4 || Math.abs(l1) >= 4 || this.m % 60 == 0;
                boolean flag1 = Math.abs(l - this.yRot) >= 4 || Math.abs(i1 - this.xRot) >= 4;

                if (this.m > 0 || this.tracker instanceof EntityArrow) {
                    if (j1 >= -128 && j1 < 128 && k1 >= -128 && k1 < 128 && l1 >= -128 && l1 < 128 && this.v <= 400 && !this.x && this.y == this.tracker.onGround) {
                        if (flag && flag1) {
                            object = new PacketPlayOutRelEntityMoveLook(this.tracker.getId(), (byte) j1, (byte) k1, (byte) l1, (byte) l, (byte) i1, this.tracker.onGround);
                        } else if (flag) {
                            object = new PacketPlayOutRelEntityMove(this.tracker.getId(), (byte) j1, (byte) k1, (byte) l1, this.tracker.onGround);
                        } else if (flag1) {
                            object = new PacketPlayOutEntityLook(this.tracker.getId(), (byte) l, (byte) i1, this.tracker.onGround);
                        }
                    } else {
                        this.y = this.tracker.onGround;
                        this.v = 0;
                        object = new PacketPlayOutEntityTeleport(this.tracker.getId(), i, j, k, (byte) l, (byte) i1, this.tracker.onGround);
                    }
                }

                if (this.u) {
                    double d0 = this.tracker.motX - this.j;
                    double d1 = this.tracker.motY - this.k;
                    double d2 = this.tracker.motZ - this.l;
                    double d3 = 0.02D;
                    double d4 = d0 * d0 + d1 * d1 + d2 * d2;

                    if (d4 > d3 * d3 || d4 > 0.0D && this.tracker.motX == 0.0D && this.tracker.motY == 0.0D && this.tracker.motZ == 0.0D) {
                        this.j = this.tracker.motX;
                        this.k = this.tracker.motY;
                        this.l = this.tracker.motZ;
                        this.broadcast(new PacketPlayOutEntityVelocity(this.tracker.getId(), this.j, this.k, this.l));
                    }
                }

                if (object != null) {
                    this.broadcast((Packet) object);
                }

                this.b();
                if (flag) {
                    this.xLoc = i;
                    this.yLoc = j;
                    this.zLoc = k;
                }

                if (flag1) {
                    this.yRot = l;
                    this.xRot = i1;
                }

                this.x = false;
            } else {
                i = MathHelper.d(this.tracker.yaw * 256.0F / 360.0F);
                j = MathHelper.d(this.tracker.pitch * 256.0F / 360.0F);
                boolean flag2 = Math.abs(i - this.yRot) >= 4 || Math.abs(j - this.xRot) >= 4;

                if (flag2) {
                    this.broadcast(new PacketPlayOutEntityLook(this.tracker.getId(), (byte) i, (byte) j, this.tracker.onGround));
                    this.yRot = i;
                    this.xRot = j;
                }

                this.xLoc = MathHelper.floor(this.tracker.locX * 32.0D);
                this.yLoc = MathHelper.floor(this.tracker.locY * 32.0D);
                this.zLoc = MathHelper.floor(this.tracker.locZ * 32.0D);
                this.b();
                this.x = true;
            }

            i = MathHelper.d(this.tracker.getHeadRotation() * 256.0F / 360.0F);
            if (Math.abs(i - this.i) >= 4) {
                this.broadcast(new PacketPlayOutEntityHeadRotation(this.tracker, (byte) i));
                this.i = i;
            }

            this.tracker.ai = false;
        }

        ++this.m;
        if (this.tracker.velocityChanged) {
            this.broadcastIncludingSelf(new PacketPlayOutEntityVelocity(this.tracker));
            this.tracker.velocityChanged = false;
        }

    }

    private void b() {
        DataWatcher datawatcher = this.tracker.getDataWatcher();

        if (datawatcher.a()) {
            this.broadcastIncludingSelf(new PacketPlayOutEntityMetadata(this.tracker.getId(), datawatcher, false));
        }

        if (this.tracker instanceof EntityLiving) {
            AttributeMapServer attributemapserver = (AttributeMapServer) ((EntityLiving) this.tracker).getAttributeMap();
            Set set = attributemapserver.getAttributes();

            if (!set.isEmpty()) {
                this.broadcastIncludingSelf(new PacketPlayOutUpdateAttributes(this.tracker.getId(), set));
            }

            set.clear();
        }

    }

    public void broadcast(Packet packet) {
        Iterator iterator = this.trackedPlayers.iterator();

        while (iterator.hasNext()) {
            EntityPlayer entityplayer = (EntityPlayer) iterator.next();

            entityplayer.playerConnection.sendPacket(packet);
        }

    }

    public void broadcastIncludingSelf(Packet packet) {
        this.broadcast(packet);
        if (this.tracker instanceof EntityPlayer) {
            ((EntityPlayer) this.tracker).playerConnection.sendPacket(packet);
        }

    }

    public void a() {
        Iterator iterator = this.trackedPlayers.iterator();

        while (iterator.hasNext()) {
            EntityPlayer entityplayer = (EntityPlayer) iterator.next();

            entityplayer.d(this.tracker);
        }

    }

    public void a(EntityPlayer entityplayer) {
        if (this.trackedPlayers.contains(entityplayer)) {
            entityplayer.d(this.tracker);
            this.trackedPlayers.remove(entityplayer);
        }

    }

    public void updatePlayer(EntityPlayer entityplayer) {
        if (entityplayer != this.tracker) {
            if (this.c(entityplayer)) {
                if (!this.trackedPlayers.contains(entityplayer) && (this.e(entityplayer) || this.tracker.attachedToPlayer)) {
                    this.trackedPlayers.add(entityplayer);
                    Packet packet = this.c();

                    entityplayer.playerConnection.sendPacket(packet);
                    if (!this.tracker.getDataWatcher().d()) {
                        entityplayer.playerConnection.sendPacket(new PacketPlayOutEntityMetadata(this.tracker.getId(), this.tracker.getDataWatcher(), true));
                    }

                    NBTTagCompound nbttagcompound = this.tracker.getNBTTag();

                    if (nbttagcompound != null) {
                        entityplayer.playerConnection.sendPacket(new PacketPlayOutUpdateEntityNBT(this.tracker.getId(), nbttagcompound));
                    }

                    if (this.tracker instanceof EntityLiving) {
                        AttributeMapServer attributemapserver = (AttributeMapServer) ((EntityLiving) this.tracker).getAttributeMap();
                        Collection collection = attributemapserver.c();

                        if (!collection.isEmpty()) {
                            entityplayer.playerConnection.sendPacket(new PacketPlayOutUpdateAttributes(this.tracker.getId(), collection));
                        }
                    }

                    this.j = this.tracker.motX;
                    this.k = this.tracker.motY;
                    this.l = this.tracker.motZ;
                    if (this.u && !(packet instanceof PacketPlayOutSpawnEntityLiving)) {
                        entityplayer.playerConnection.sendPacket(new PacketPlayOutEntityVelocity(this.tracker.getId(), this.tracker.motX, this.tracker.motY, this.tracker.motZ));
                    }

                    if (this.tracker.vehicle != null) {
                        entityplayer.playerConnection.sendPacket(new PacketPlayOutAttachEntity(0, this.tracker, this.tracker.vehicle));
                    }

                    if (this.tracker instanceof EntityInsentient && ((EntityInsentient) this.tracker).getLeashHolder() != null) {
                        entityplayer.playerConnection.sendPacket(new PacketPlayOutAttachEntity(1, this.tracker, ((EntityInsentient) this.tracker).getLeashHolder()));
                    }

                    if (this.tracker instanceof EntityLiving) {
                        for (int i = 0; i < 5; ++i) {
                            ItemStack itemstack = ((EntityLiving) this.tracker).getEquipment(i);

                            if (itemstack != null) {
                                entityplayer.playerConnection.sendPacket(new PacketPlayOutEntityEquipment(this.tracker.getId(), i, itemstack));
                            }
                        }
                    }

                    if (this.tracker instanceof EntityHuman) {
                        EntityHuman entityhuman = (EntityHuman) this.tracker;

                        if (entityhuman.isSleeping()) {
                            entityplayer.playerConnection.sendPacket(new PacketPlayOutBed(entityhuman, new BlockPosition(this.tracker)));
                        }
                    }

                    if (this.tracker instanceof EntityLiving) {
                        EntityLiving entityliving = (EntityLiving) this.tracker;
                        Iterator iterator = entityliving.getEffects().iterator();

                        while (iterator.hasNext()) {
                            MobEffect mobeffect = (MobEffect) iterator.next();

                            entityplayer.playerConnection.sendPacket(new PacketPlayOutEntityEffect(this.tracker.getId(), mobeffect));
                        }
                    }
                }
            } else if (this.trackedPlayers.contains(entityplayer)) {
                this.trackedPlayers.remove(entityplayer);
                entityplayer.d(this.tracker);
            }

        }
    }

    public boolean c(EntityPlayer entityplayer) {
        double d0 = entityplayer.locX - (double) (this.xLoc / 32);
        double d1 = entityplayer.locZ - (double) (this.zLoc / 32);

        return d0 >= (double) (-this.b) && d0 <= (double) this.b && d1 >= (double) (-this.b) && d1 <= (double) this.b && this.tracker.a(entityplayer);
    }

    private boolean e(EntityPlayer entityplayer) {
        return entityplayer.u().getPlayerChunkMap().a(entityplayer, this.tracker.ae, this.tracker.ag);
    }

    public void scanPlayers(List list) {
        for (int i = 0; i < list.size(); ++i) {
            this.updatePlayer((EntityPlayer) list.get(i));
        }

    }

    private Packet c() {
        if (this.tracker.dead) {
            EntityTrackerEntry.p.warn("Fetching addPacket for removed entity");
        }

        if (this.tracker instanceof EntityItem) {
            return new PacketPlayOutSpawnEntity(this.tracker, 2, 1);
        } else if (this.tracker instanceof EntityPlayer) {
            return new PacketPlayOutNamedEntitySpawn((EntityHuman) this.tracker);
        } else if (this.tracker instanceof EntityMinecartAbstract) {
            EntityMinecartAbstract entityminecartabstract = (EntityMinecartAbstract) this.tracker;

            return new PacketPlayOutSpawnEntity(this.tracker, 10, entityminecartabstract.s().a());
        } else if (this.tracker instanceof EntityBoat) {
            return new PacketPlayOutSpawnEntity(this.tracker, 1);
        } else if (this.tracker instanceof IAnimal) {
            this.i = MathHelper.d(this.tracker.getHeadRotation() * 256.0F / 360.0F);
            return new PacketPlayOutSpawnEntityLiving((EntityLiving) this.tracker);
        } else if (this.tracker instanceof EntityFishingHook) {
            EntityHuman entityhuman = ((EntityFishingHook) this.tracker).owner;

            return new PacketPlayOutSpawnEntity(this.tracker, 90, entityhuman != null ? entityhuman.getId() : this.tracker.getId());
        } else if (this.tracker instanceof EntityArrow) {
            Entity entity = ((EntityArrow) this.tracker).shooter;

            return new PacketPlayOutSpawnEntity(this.tracker, 60, entity != null ? entity.getId() : this.tracker.getId());
        } else if (this.tracker instanceof EntitySnowball) {
            return new PacketPlayOutSpawnEntity(this.tracker, 61);
        } else if (this.tracker instanceof EntityPotion) {
            return new PacketPlayOutSpawnEntity(this.tracker, 73, ((EntityPotion) this.tracker).getPotionValue());
        } else if (this.tracker instanceof EntityThrownExpBottle) {
            return new PacketPlayOutSpawnEntity(this.tracker, 75);
        } else if (this.tracker instanceof EntityEnderPearl) {
            return new PacketPlayOutSpawnEntity(this.tracker, 65);
        } else if (this.tracker instanceof EntityEnderSignal) {
            return new PacketPlayOutSpawnEntity(this.tracker, 72);
        } else if (this.tracker instanceof EntityFireworks) {
            return new PacketPlayOutSpawnEntity(this.tracker, 76);
        } else {
            PacketPlayOutSpawnEntity packetplayoutspawnentity;

            if (this.tracker instanceof EntityFireball) {
                EntityFireball entityfireball = (EntityFireball) this.tracker;

                packetplayoutspawnentity = null;
                byte b0 = 63;

                if (this.tracker instanceof EntitySmallFireball) {
                    b0 = 64;
                } else if (this.tracker instanceof EntityWitherSkull) {
                    b0 = 66;
                }

                if (entityfireball.shooter != null) {
                    packetplayoutspawnentity = new PacketPlayOutSpawnEntity(this.tracker, b0, ((EntityFireball) this.tracker).shooter.getId());
                } else {
                    packetplayoutspawnentity = new PacketPlayOutSpawnEntity(this.tracker, b0, 0);
                }

                packetplayoutspawnentity.d((int) (entityfireball.dirX * 8000.0D));
                packetplayoutspawnentity.e((int) (entityfireball.dirY * 8000.0D));
                packetplayoutspawnentity.f((int) (entityfireball.dirZ * 8000.0D));
                return packetplayoutspawnentity;
            } else if (this.tracker instanceof EntityEgg) {
                return new PacketPlayOutSpawnEntity(this.tracker, 62);
            } else if (this.tracker instanceof EntityTNTPrimed) {
                return new PacketPlayOutSpawnEntity(this.tracker, 50);
            } else if (this.tracker instanceof EntityEnderCrystal) {
                return new PacketPlayOutSpawnEntity(this.tracker, 51);
            } else if (this.tracker instanceof EntityFallingBlock) {
                EntityFallingBlock entityfallingblock = (EntityFallingBlock) this.tracker;

                return new PacketPlayOutSpawnEntity(this.tracker, 70, Block.getCombinedId(entityfallingblock.getBlock()));
            } else if (this.tracker instanceof EntityArmorStand) {
                return new PacketPlayOutSpawnEntity(this.tracker, 78);
            } else if (this.tracker instanceof EntityPainting) {
                return new PacketPlayOutSpawnEntityPainting((EntityPainting) this.tracker);
            } else {
                BlockPosition blockposition;

                if (this.tracker instanceof EntityItemFrame) {
                    EntityItemFrame entityitemframe = (EntityItemFrame) this.tracker;

                    packetplayoutspawnentity = new PacketPlayOutSpawnEntity(this.tracker, 71, entityitemframe.direction.b());
                    blockposition = entityitemframe.getBlockPosition();
                    packetplayoutspawnentity.a(MathHelper.d((float) (blockposition.getX() * 32)));
                    packetplayoutspawnentity.b(MathHelper.d((float) (blockposition.getY() * 32)));
                    packetplayoutspawnentity.c(MathHelper.d((float) (blockposition.getZ() * 32)));
                    return packetplayoutspawnentity;
                } else if (this.tracker instanceof EntityLeash) {
                    EntityLeash entityleash = (EntityLeash) this.tracker;

                    packetplayoutspawnentity = new PacketPlayOutSpawnEntity(this.tracker, 77);
                    blockposition = entityleash.getBlockPosition();
                    packetplayoutspawnentity.a(MathHelper.d((float) (blockposition.getX() * 32)));
                    packetplayoutspawnentity.b(MathHelper.d((float) (blockposition.getY() * 32)));
                    packetplayoutspawnentity.c(MathHelper.d((float) (blockposition.getZ() * 32)));
                    return packetplayoutspawnentity;
                } else if (this.tracker instanceof EntityExperienceOrb) {
                    return new PacketPlayOutSpawnEntityExperienceOrb((EntityExperienceOrb) this.tracker);
                } else {
                    throw new IllegalArgumentException("Don\'t know how to add " + this.tracker.getClass() + "!");
                }
            }
        }
    }

    public void clear(EntityPlayer entityplayer) {
        if (this.trackedPlayers.contains(entityplayer)) {
            this.trackedPlayers.remove(entityplayer);
            entityplayer.d(this.tracker);
        }

    }
}
