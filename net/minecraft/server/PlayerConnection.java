package net.minecraft.server;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.Futures;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PlayerConnection implements PacketListenerPlayIn, IUpdatePlayerListBox {

    private static final Logger c = LogManager.getLogger();
    public final NetworkManager networkManager;
    private final MinecraftServer minecraftServer;
    public EntityPlayer player;
    private int e;
    private int f;
    private int g;
    private boolean h;
    private int i;
    private long j;
    private long k;
    private int chatThrottle;
    private int m;
    private IntHashMap<Short> n = new IntHashMap();
    private double o;
    private double p;
    private double q;
    private boolean checkMovement = true;

    public PlayerConnection(MinecraftServer minecraftserver, NetworkManager networkmanager, EntityPlayer entityplayer) {
        this.minecraftServer = minecraftserver;
        this.networkManager = networkmanager;
        networkmanager.a((PacketListener) this);
        this.player = entityplayer;
        entityplayer.playerConnection = this;
    }

    public void c() {
        this.h = false;
        ++this.e;
        this.minecraftServer.methodProfiler.a("keepAlive");
        if ((long) this.e - this.k > 40L) {
            this.k = (long) this.e;
            this.j = this.d();
            this.i = (int) this.j;
            this.sendPacket(new PacketPlayOutKeepAlive(this.i));
        }

        this.minecraftServer.methodProfiler.b();
        if (this.chatThrottle > 0) {
            --this.chatThrottle;
        }

        if (this.m > 0) {
            --this.m;
        }

        if (this.player.D() > 0L && this.minecraftServer.getIdleTimeout() > 0 && MinecraftServer.ay() - this.player.D() > (long) (this.minecraftServer.getIdleTimeout() * 1000 * 60)) {
            this.disconnect("You have been idle for too long!");
        }

    }

    public NetworkManager a() {
        return this.networkManager;
    }

    public void disconnect(String s) {
        final ChatComponentText chatcomponenttext = new ChatComponentText(s);

        this.networkManager.a(new PacketPlayOutKickDisconnect(chatcomponenttext), new GenericFutureListener() {
            public void operationComplete(Future<? super Void> future) throws Exception {
                PlayerConnection.this.networkManager.close(chatcomponenttext);
            }
        }, new GenericFutureListener[0]);
        this.networkManager.k();
        Futures.getUnchecked(this.minecraftServer.postToMainThread(new Runnable() {
            public void run() {
                PlayerConnection.this.networkManager.l();
            }
        }));
    }

    public void a(PacketPlayInSteerVehicle packetplayinsteervehicle) {
        PlayerConnectionUtils.ensureMainThread(packetplayinsteervehicle, this, this.player.u());
        this.player.a(packetplayinsteervehicle.a(), packetplayinsteervehicle.b(), packetplayinsteervehicle.c(), packetplayinsteervehicle.d());
    }

    public void a(PacketPlayInFlying packetplayinflying) {
        PlayerConnectionUtils.ensureMainThread(packetplayinflying, this, this.player.u());
        WorldServer worldserver = this.minecraftServer.getWorldServer(this.player.dimension);

        this.h = true;
        if (!this.player.viewingCredits) {
            double d0 = this.player.locX;
            double d1 = this.player.locY;
            double d2 = this.player.locZ;
            double d3 = 0.0D;
            double d4 = packetplayinflying.a() - this.o;
            double d5 = packetplayinflying.b() - this.p;
            double d6 = packetplayinflying.c() - this.q;

            if (packetplayinflying.g()) {
                d3 = d4 * d4 + d5 * d5 + d6 * d6;
                if (!this.checkMovement && d3 < 0.25D) {
                    this.checkMovement = true;
                }
            }

            if (this.checkMovement) {
                this.f = this.e;
                double d7;
                double d8;
                double d9;

                if (this.player.vehicle != null) {
                    float f = this.player.yaw;
                    float f1 = this.player.pitch;

                    this.player.vehicle.al();
                    d7 = this.player.locX;
                    d8 = this.player.locY;
                    d9 = this.player.locZ;
                    if (packetplayinflying.h()) {
                        f = packetplayinflying.d();
                        f1 = packetplayinflying.e();
                    }

                    this.player.onGround = packetplayinflying.f();
                    this.player.l();
                    this.player.setLocation(d7, d8, d9, f, f1);
                    if (this.player.vehicle != null) {
                        this.player.vehicle.al();
                    }

                    this.minecraftServer.getPlayerList().d(this.player);
                    if (this.player.vehicle != null) {
                        if (d3 > 4.0D) {
                            Entity entity = this.player.vehicle;

                            this.player.playerConnection.sendPacket(new PacketPlayOutEntityTeleport(entity));
                            this.a(this.player.locX, this.player.locY, this.player.locZ, this.player.yaw, this.player.pitch);
                        }

                        this.player.vehicle.ai = true;
                    }

                    if (this.checkMovement) {
                        this.o = this.player.locX;
                        this.p = this.player.locY;
                        this.q = this.player.locZ;
                    }

                    worldserver.g(this.player);
                    return;
                }

                if (this.player.isSleeping()) {
                    this.player.l();
                    this.player.setLocation(this.o, this.p, this.q, this.player.yaw, this.player.pitch);
                    worldserver.g(this.player);
                    return;
                }

                double d10 = this.player.locY;

                this.o = this.player.locX;
                this.p = this.player.locY;
                this.q = this.player.locZ;
                d7 = this.player.locX;
                d8 = this.player.locY;
                d9 = this.player.locZ;
                float f2 = this.player.yaw;
                float f3 = this.player.pitch;

                if (packetplayinflying.g() && packetplayinflying.b() == -999.0D) {
                    packetplayinflying.a(false);
                }

                if (packetplayinflying.g()) {
                    d7 = packetplayinflying.a();
                    d8 = packetplayinflying.b();
                    d9 = packetplayinflying.c();
                    if (Math.abs(packetplayinflying.a()) > 3.0E7D || Math.abs(packetplayinflying.c()) > 3.0E7D) {
                        this.disconnect("Illegal position");
                        return;
                    }
                }

                if (packetplayinflying.h()) {
                    f2 = packetplayinflying.d();
                    f3 = packetplayinflying.e();
                }

                this.player.l();
                this.player.setLocation(this.o, this.p, this.q, f2, f3);
                if (!this.checkMovement) {
                    return;
                }

                double d11 = d7 - this.player.locX;
                double d12 = d8 - this.player.locY;
                double d13 = d9 - this.player.locZ;
                double d14 = Math.min(Math.abs(d11), Math.abs(this.player.motX));
                double d15 = Math.min(Math.abs(d12), Math.abs(this.player.motY));
                double d16 = Math.min(Math.abs(d13), Math.abs(this.player.motZ));
                double d17 = d14 * d14 + d15 * d15 + d16 * d16;

                if (d17 > 100.0D && (!this.minecraftServer.S() || !this.minecraftServer.R().equals(this.player.getName()))) {
                    PlayerConnection.c.warn(this.player.getName() + " moved too quickly! " + d11 + "," + d12 + "," + d13 + " (" + d14 + ", " + d15 + ", " + d16 + ")");
                    this.a(this.o, this.p, this.q, this.player.yaw, this.player.pitch);
                    return;
                }

                float f4 = 0.0625F;
                boolean flag = worldserver.getCubes(this.player, this.player.getBoundingBox().shrink((double) f4, (double) f4, (double) f4)).isEmpty();

                if (this.player.onGround && !packetplayinflying.f() && d12 > 0.0D) {
                    this.player.bF();
                }

                this.player.move(d11, d12, d13);
                this.player.onGround = packetplayinflying.f();
                double d18 = d12;

                d11 = d7 - this.player.locX;
                d12 = d8 - this.player.locY;
                if (d12 > -0.5D || d12 < 0.5D) {
                    d12 = 0.0D;
                }

                d13 = d9 - this.player.locZ;
                d17 = d11 * d11 + d12 * d12 + d13 * d13;
                boolean flag1 = false;

                if (d17 > 0.0625D && !this.player.isSleeping() && !this.player.playerInteractManager.isCreative()) {
                    flag1 = true;
                    PlayerConnection.c.warn(this.player.getName() + " moved wrongly!");
                }

                this.player.setLocation(d7, d8, d9, f2, f3);
                this.player.checkMovement(this.player.locX - d0, this.player.locY - d1, this.player.locZ - d2);
                if (!this.player.noclip) {
                    boolean flag2 = worldserver.getCubes(this.player, this.player.getBoundingBox().shrink((double) f4, (double) f4, (double) f4)).isEmpty();

                    if (flag && (flag1 || !flag2) && !this.player.isSleeping()) {
                        this.a(this.o, this.p, this.q, f2, f3);
                        return;
                    }
                }

                AxisAlignedBB axisalignedbb = this.player.getBoundingBox().grow((double) f4, (double) f4, (double) f4).a(0.0D, -0.55D, 0.0D);

                if (!this.minecraftServer.getAllowFlight() && !this.player.abilities.canFly && !worldserver.c(axisalignedbb)) {
                    if (d18 >= -0.03125D) {
                        ++this.g;
                        if (this.g > 80) {
                            PlayerConnection.c.warn(this.player.getName() + " was kicked for floating too long!");
                            this.disconnect("Flying is not enabled on this server");
                            return;
                        }
                    }
                } else {
                    this.g = 0;
                }

                this.player.onGround = packetplayinflying.f();
                this.minecraftServer.getPlayerList().d(this.player);
                this.player.a(this.player.locY - d10, packetplayinflying.f());
            } else if (this.e - this.f > 20) {
                this.a(this.o, this.p, this.q, this.player.yaw, this.player.pitch);
            }

        }
    }

    public void a(double d0, double d1, double d2, float f, float f1) {
        this.a(d0, d1, d2, f, f1, Collections.emptySet());
    }

    public void a(double d0, double d1, double d2, float f, float f1, Set<PacketPlayOutPosition.a> set) {
        this.checkMovement = false;
        this.o = d0;
        this.p = d1;
        this.q = d2;
        if (set.contains(PacketPlayOutPosition.a.X)) {
            this.o += this.player.locX;
        }

        if (set.contains(PacketPlayOutPosition.a.Y)) {
            this.p += this.player.locY;
        }

        if (set.contains(PacketPlayOutPosition.a.Z)) {
            this.q += this.player.locZ;
        }

        float f2 = f;
        float f3 = f1;

        if (set.contains(PacketPlayOutPosition.a.Y_ROT)) {
            f2 = f + this.player.yaw;
        }

        if (set.contains(PacketPlayOutPosition.a.X_ROT)) {
            f3 = f1 + this.player.pitch;
        }

        this.player.setLocation(this.o, this.p, this.q, f2, f3);
        this.player.playerConnection.sendPacket(new PacketPlayOutPosition(d0, d1, d2, f, f1, set));
    }

    public void a(PacketPlayInBlockDig packetplayinblockdig) {
        PlayerConnectionUtils.ensureMainThread(packetplayinblockdig, this, this.player.u());
        WorldServer worldserver = this.minecraftServer.getWorldServer(this.player.dimension);
        BlockPosition blockposition = packetplayinblockdig.a();

        this.player.z();
        switch (PlayerConnection.SyntheticClass_1.a[packetplayinblockdig.c().ordinal()]) {
        case 1:
            if (!this.player.v()) {
                this.player.a(false);
            }

            return;

        case 2:
            if (!this.player.v()) {
                this.player.a(true);
            }

            return;

        case 3:
            this.player.bU();
            return;

        case 4:
        case 5:
        case 6:
            double d0 = this.player.locX - ((double) blockposition.getX() + 0.5D);
            double d1 = this.player.locY - ((double) blockposition.getY() + 0.5D) + 1.5D;
            double d2 = this.player.locZ - ((double) blockposition.getZ() + 0.5D);
            double d3 = d0 * d0 + d1 * d1 + d2 * d2;

            if (d3 > 36.0D) {
                return;
            } else if (blockposition.getY() >= this.minecraftServer.getMaxBuildHeight()) {
                return;
            } else {
                if (packetplayinblockdig.c() == PacketPlayInBlockDig.a.START_DESTROY_BLOCK) {
                    if (!this.minecraftServer.a(worldserver, blockposition, this.player) && worldserver.getWorldBorder().a(blockposition)) {
                        this.player.playerInteractManager.a(blockposition, packetplayinblockdig.b());
                    } else {
                        this.player.playerConnection.sendPacket(new PacketPlayOutBlockChange(worldserver, blockposition));
                    }
                } else {
                    if (packetplayinblockdig.c() == PacketPlayInBlockDig.a.STOP_DESTROY_BLOCK) {
                        this.player.playerInteractManager.a(blockposition);
                    } else if (packetplayinblockdig.c() == PacketPlayInBlockDig.a.ABORT_DESTROY_BLOCK) {
                        this.player.playerInteractManager.e();
                    }

                    if (worldserver.getType(blockposition).getBlock().getMaterial() != Material.AIR) {
                        this.player.playerConnection.sendPacket(new PacketPlayOutBlockChange(worldserver, blockposition));
                    }
                }

                return;
            }

        default:
            throw new IllegalArgumentException("Invalid player action");
        }
    }

    public void a(PacketPlayInBlockPlace packetplayinblockplace) {
        PlayerConnectionUtils.ensureMainThread(packetplayinblockplace, this, this.player.u());
        WorldServer worldserver = this.minecraftServer.getWorldServer(this.player.dimension);
        ItemStack itemstack = this.player.inventory.getItemInHand();
        boolean flag = false;
        BlockPosition blockposition = packetplayinblockplace.a();
        EnumDirection enumdirection = EnumDirection.fromType1(packetplayinblockplace.getFace());

        this.player.z();
        if (packetplayinblockplace.getFace() == 255) {
            if (itemstack == null) {
                return;
            }

            this.player.playerInteractManager.useItem(this.player, worldserver, itemstack);
        } else if (blockposition.getY() >= this.minecraftServer.getMaxBuildHeight() - 1 && (enumdirection == EnumDirection.UP || blockposition.getY() >= this.minecraftServer.getMaxBuildHeight())) {
            ChatMessage chatmessage = new ChatMessage("build.tooHigh", new Object[] { Integer.valueOf(this.minecraftServer.getMaxBuildHeight())});

            chatmessage.getChatModifier().setColor(EnumChatFormat.RED);
            this.player.playerConnection.sendPacket(new PacketPlayOutChat(chatmessage));
            flag = true;
        } else {
            if (this.checkMovement && this.player.e((double) blockposition.getX() + 0.5D, (double) blockposition.getY() + 0.5D, (double) blockposition.getZ() + 0.5D) < 64.0D && !this.minecraftServer.a(worldserver, blockposition, this.player) && worldserver.getWorldBorder().a(blockposition)) {
                this.player.playerInteractManager.interact(this.player, worldserver, itemstack, blockposition, enumdirection, packetplayinblockplace.d(), packetplayinblockplace.e(), packetplayinblockplace.f());
            }

            flag = true;
        }

        if (flag) {
            this.player.playerConnection.sendPacket(new PacketPlayOutBlockChange(worldserver, blockposition));
            this.player.playerConnection.sendPacket(new PacketPlayOutBlockChange(worldserver, blockposition.shift(enumdirection)));
        }

        itemstack = this.player.inventory.getItemInHand();
        if (itemstack != null && itemstack.count == 0) {
            this.player.inventory.items[this.player.inventory.itemInHandIndex] = null;
            itemstack = null;
        }

        if (itemstack == null || itemstack.l() == 0) {
            this.player.effects = true;
            this.player.inventory.items[this.player.inventory.itemInHandIndex] = ItemStack.b(this.player.inventory.items[this.player.inventory.itemInHandIndex]);
            Slot slot = this.player.activeContainer.getSlot(this.player.inventory, this.player.inventory.itemInHandIndex);

            this.player.activeContainer.b();
            this.player.effects = false;
            if (!ItemStack.matches(this.player.inventory.getItemInHand(), packetplayinblockplace.getItemStack())) {
                this.sendPacket(new PacketPlayOutSetSlot(this.player.activeContainer.windowId, slot.rawSlotIndex, this.player.inventory.getItemInHand()));
            }
        }

    }

    public void a(PacketPlayInSpectate packetplayinspectate) {
        PlayerConnectionUtils.ensureMainThread(packetplayinspectate, this, this.player.u());
        if (this.player.v()) {
            Entity entity = null;
            WorldServer[] aworldserver = this.minecraftServer.worldServer;
            int i = aworldserver.length;

            for (int j = 0; j < i; ++j) {
                WorldServer worldserver = aworldserver[j];

                if (worldserver != null) {
                    entity = packetplayinspectate.a(worldserver);
                    if (entity != null) {
                        break;
                    }
                }
            }

            if (entity != null) {
                this.player.e((Entity) this.player);
                this.player.mount((Entity) null);
                if (entity.world != this.player.world) {
                    WorldServer worldserver1 = this.player.u();
                    WorldServer worldserver2 = (WorldServer) entity.world;

                    this.player.dimension = entity.dimension;
                    this.sendPacket(new PacketPlayOutRespawn(this.player.dimension, worldserver1.getDifficulty(), worldserver1.getWorldData().getType(), this.player.playerInteractManager.getGameMode()));
                    worldserver1.removeEntity(this.player);
                    this.player.dead = false;
                    this.player.setPositionRotation(entity.locX, entity.locY, entity.locZ, entity.yaw, entity.pitch);
                    if (this.player.isAlive()) {
                        worldserver1.entityJoinedWorld(this.player, false);
                        worldserver2.addEntity(this.player);
                        worldserver2.entityJoinedWorld(this.player, false);
                    }

                    this.player.spawnIn(worldserver2);
                    this.minecraftServer.getPlayerList().a(this.player, worldserver1);
                    this.player.enderTeleportTo(entity.locX, entity.locY, entity.locZ);
                    this.player.playerInteractManager.a(worldserver2);
                    this.minecraftServer.getPlayerList().b(this.player, worldserver2);
                    this.minecraftServer.getPlayerList().updateClient(this.player);
                } else {
                    this.player.enderTeleportTo(entity.locX, entity.locY, entity.locZ);
                }
            }
        }

    }

    public void a(PacketPlayInResourcePackStatus packetplayinresourcepackstatus) {}

    public void a(IChatBaseComponent ichatbasecomponent) {
        PlayerConnection.c.info(this.player.getName() + " lost connection: " + ichatbasecomponent);
        this.minecraftServer.aG();
        ChatMessage chatmessage = new ChatMessage("multiplayer.player.left", new Object[] { this.player.getScoreboardDisplayName()});

        chatmessage.getChatModifier().setColor(EnumChatFormat.YELLOW);
        this.minecraftServer.getPlayerList().sendMessage(chatmessage);
        this.player.q();
        this.minecraftServer.getPlayerList().disconnect(this.player);
        if (this.minecraftServer.S() && this.player.getName().equals(this.minecraftServer.R())) {
            PlayerConnection.c.info("Stopping singleplayer server as player logged out");
            this.minecraftServer.safeShutdown();
        }

    }

    public void sendPacket(final Packet packet) {
        if (packet instanceof PacketPlayOutChat) {
            PacketPlayOutChat packetplayoutchat = (PacketPlayOutChat) packet;
            EntityHuman.b entityhuman_b = this.player.getChatFlags();

            if (entityhuman_b == EntityHuman.b.HIDDEN) {
                return;
            }

            if (entityhuman_b == EntityHuman.b.SYSTEM && !packetplayoutchat.b()) {
                return;
            }
        }

        try {
            this.networkManager.handle(packet);
        } catch (Throwable throwable) {
            CrashReport crashreport = CrashReport.a(throwable, "Sending packet");
            CrashReportSystemDetails crashreportsystemdetails = crashreport.a("Packet being sent");

            crashreportsystemdetails.a("Packet class", new Callable() {
                public String a() throws Exception {
                    return packet.getClass().getCanonicalName();
                }

                public Object call() throws Exception {
                    return this.a();
                }
            });
            throw new ReportedException(crashreport);
        }
    }

    public void a(PacketPlayInHeldItemSlot packetplayinhelditemslot) {
        PlayerConnectionUtils.ensureMainThread(packetplayinhelditemslot, this, this.player.u());
        if (packetplayinhelditemslot.a() >= 0 && packetplayinhelditemslot.a() < PlayerInventory.getHotbarSize()) {
            this.player.inventory.itemInHandIndex = packetplayinhelditemslot.a();
            this.player.z();
        } else {
            PlayerConnection.c.warn(this.player.getName() + " tried to set an invalid carried item");
        }
    }

    public void a(PacketPlayInChat packetplayinchat) {
        PlayerConnectionUtils.ensureMainThread(packetplayinchat, this, this.player.u());
        if (this.player.getChatFlags() == EntityHuman.b.HIDDEN) {
            ChatMessage chatmessage = new ChatMessage("chat.cannotSend", new Object[0]);

            chatmessage.getChatModifier().setColor(EnumChatFormat.RED);
            this.sendPacket(new PacketPlayOutChat(chatmessage));
        } else {
            this.player.z();
            String s = packetplayinchat.a();

            s = StringUtils.normalizeSpace(s);

            for (int i = 0; i < s.length(); ++i) {
                if (!SharedConstants.isAllowedChatCharacter(s.charAt(i))) {
                    this.disconnect("Illegal characters in chat");
                    return;
                }
            }

            if (s.startsWith("/")) {
                this.handleCommand(s);
            } else {
                ChatMessage chatmessage1 = new ChatMessage("chat.type.text", new Object[] { this.player.getScoreboardDisplayName(), s});

                this.minecraftServer.getPlayerList().sendMessage(chatmessage1, false);
            }

            this.chatThrottle += 20;
            if (this.chatThrottle > 200 && !this.minecraftServer.getPlayerList().isOp(this.player.getProfile())) {
                this.disconnect("disconnect.spam");
            }

        }
    }

    private void handleCommand(String s) {
        this.minecraftServer.getCommandHandler().a(this.player, s);
    }

    public void a(PacketPlayInArmAnimation packetplayinarmanimation) {
        PlayerConnectionUtils.ensureMainThread(packetplayinarmanimation, this, this.player.u());
        this.player.z();
        this.player.bw();
    }

    public void a(PacketPlayInEntityAction packetplayinentityaction) {
        PlayerConnectionUtils.ensureMainThread(packetplayinentityaction, this, this.player.u());
        this.player.z();
        switch (PlayerConnection.SyntheticClass_1.b[packetplayinentityaction.b().ordinal()]) {
        case 1:
            this.player.setSneaking(true);
            break;

        case 2:
            this.player.setSneaking(false);
            break;

        case 3:
            this.player.setSprinting(true);
            break;

        case 4:
            this.player.setSprinting(false);
            break;

        case 5:
            this.player.a(false, true, true);
            this.checkMovement = false;
            break;

        case 6:
            if (this.player.vehicle instanceof EntityHorse) {
                ((EntityHorse) this.player.vehicle).v(packetplayinentityaction.c());
            }
            break;

        case 7:
            if (this.player.vehicle instanceof EntityHorse) {
                ((EntityHorse) this.player.vehicle).g((EntityHuman) this.player);
            }
            break;

        default:
            throw new IllegalArgumentException("Invalid client command!");
        }

    }

    public void a(PacketPlayInUseEntity packetplayinuseentity) {
        PlayerConnectionUtils.ensureMainThread(packetplayinuseentity, this, this.player.u());
        WorldServer worldserver = this.minecraftServer.getWorldServer(this.player.dimension);
        Entity entity = packetplayinuseentity.a((World) worldserver);

        this.player.z();
        if (entity != null) {
            boolean flag = this.player.hasLineOfSight(entity);
            double d0 = 36.0D;

            if (!flag) {
                d0 = 9.0D;
            }

            if (this.player.h(entity) < d0) {
                if (packetplayinuseentity.a() == PacketPlayInUseEntity.a.INTERACT) {
                    this.player.u(entity);
                } else if (packetplayinuseentity.a() == PacketPlayInUseEntity.a.INTERACT_AT) {
                    entity.a((EntityHuman) this.player, packetplayinuseentity.b());
                } else if (packetplayinuseentity.a() == PacketPlayInUseEntity.a.ATTACK) {
                    if (entity instanceof EntityItem || entity instanceof EntityExperienceOrb || entity instanceof EntityArrow || entity == this.player) {
                        this.disconnect("Attempting to attack an invalid entity");
                        this.minecraftServer.warning("Player " + this.player.getName() + " tried to attack an invalid entity");
                        return;
                    }

                    this.player.attack(entity);
                }
            }
        }

    }

    public void a(PacketPlayInClientCommand packetplayinclientcommand) {
        PlayerConnectionUtils.ensureMainThread(packetplayinclientcommand, this, this.player.u());
        this.player.z();
        PacketPlayInClientCommand.a packetplayinclientcommand_a = packetplayinclientcommand.a();

        switch (PlayerConnection.SyntheticClass_1.c[packetplayinclientcommand_a.ordinal()]) {
        case 1:
            if (this.player.viewingCredits) {
                this.player = this.minecraftServer.getPlayerList().moveToWorld(this.player, 0, true);
            } else if (this.player.u().getWorldData().isHardcore()) {
                if (this.minecraftServer.S() && this.player.getName().equals(this.minecraftServer.R())) {
                    this.player.playerConnection.disconnect("You have died. Game over, man, it\'s game over!");
                    this.minecraftServer.Z();
                } else {
                    GameProfileBanEntry gameprofilebanentry = new GameProfileBanEntry(this.player.getProfile(), (Date) null, "(You just lost the game)", (Date) null, "Death in Hardcore");

                    this.minecraftServer.getPlayerList().getProfileBans().add(gameprofilebanentry);
                    this.player.playerConnection.disconnect("You have died. Game over, man, it\'s game over!");
                }
            } else {
                if (this.player.getHealth() > 0.0F) {
                    return;
                }

                this.player = this.minecraftServer.getPlayerList().moveToWorld(this.player, 0, false);
            }
            break;

        case 2:
            this.player.getStatisticManager().a(this.player);
            break;

        case 3:
            this.player.b((Statistic) AchievementList.f);
        }

    }

    public void a(PacketPlayInCloseWindow packetplayinclosewindow) {
        PlayerConnectionUtils.ensureMainThread(packetplayinclosewindow, this, this.player.u());
        this.player.p();
    }

    public void a(PacketPlayInWindowClick packetplayinwindowclick) {
        PlayerConnectionUtils.ensureMainThread(packetplayinwindowclick, this, this.player.u());
        this.player.z();
        if (this.player.activeContainer.windowId == packetplayinwindowclick.a() && this.player.activeContainer.c(this.player)) {
            if (this.player.v()) {
                ArrayList arraylist = Lists.newArrayList();

                for (int i = 0; i < this.player.activeContainer.c.size(); ++i) {
                    arraylist.add(((Slot) this.player.activeContainer.c.get(i)).getItem());
                }

                this.player.a(this.player.activeContainer, (List) arraylist);
            } else {
                ItemStack itemstack = this.player.activeContainer.clickItem(packetplayinwindowclick.b(), packetplayinwindowclick.c(), packetplayinwindowclick.f(), this.player);

                if (ItemStack.matches(packetplayinwindowclick.e(), itemstack)) {
                    this.player.playerConnection.sendPacket(new PacketPlayOutTransaction(packetplayinwindowclick.a(), packetplayinwindowclick.d(), true));
                    this.player.effects = true;
                    this.player.activeContainer.b();
                    this.player.broadcastCarriedItem();
                    this.player.effects = false;
                } else {
                    this.n.a(this.player.activeContainer.windowId, Short.valueOf(packetplayinwindowclick.d()));
                    this.player.playerConnection.sendPacket(new PacketPlayOutTransaction(packetplayinwindowclick.a(), packetplayinwindowclick.d(), false));
                    this.player.activeContainer.a(this.player, false);
                    ArrayList arraylist1 = Lists.newArrayList();

                    for (int j = 0; j < this.player.activeContainer.c.size(); ++j) {
                        arraylist1.add(((Slot) this.player.activeContainer.c.get(j)).getItem());
                    }

                    this.player.a(this.player.activeContainer, (List) arraylist1);
                }
            }
        }

    }

    public void a(PacketPlayInEnchantItem packetplayinenchantitem) {
        PlayerConnectionUtils.ensureMainThread(packetplayinenchantitem, this, this.player.u());
        this.player.z();
        if (this.player.activeContainer.windowId == packetplayinenchantitem.a() && this.player.activeContainer.c(this.player) && !this.player.v()) {
            this.player.activeContainer.a(this.player, packetplayinenchantitem.b());
            this.player.activeContainer.b();
        }

    }

    public void a(PacketPlayInSetCreativeSlot packetplayinsetcreativeslot) {
        PlayerConnectionUtils.ensureMainThread(packetplayinsetcreativeslot, this, this.player.u());
        if (this.player.playerInteractManager.isCreative()) {
            boolean flag = packetplayinsetcreativeslot.a() < 0;
            ItemStack itemstack = packetplayinsetcreativeslot.getItemStack();

            if (itemstack != null && itemstack.hasTag() && itemstack.getTag().hasKeyOfType("BlockEntityTag", 10)) {
                NBTTagCompound nbttagcompound = itemstack.getTag().getCompound("BlockEntityTag");

                if (nbttagcompound.hasKey("x") && nbttagcompound.hasKey("y") && nbttagcompound.hasKey("z")) {
                    BlockPosition blockposition = new BlockPosition(nbttagcompound.getInt("x"), nbttagcompound.getInt("y"), nbttagcompound.getInt("z"));
                    TileEntity tileentity = this.player.world.getTileEntity(blockposition);

                    if (tileentity != null) {
                        NBTTagCompound nbttagcompound1 = new NBTTagCompound();

                        tileentity.b(nbttagcompound1);
                        nbttagcompound1.remove("x");
                        nbttagcompound1.remove("y");
                        nbttagcompound1.remove("z");
                        itemstack.a("BlockEntityTag", (NBTBase) nbttagcompound1);
                    }
                }
            }

            boolean flag1 = packetplayinsetcreativeslot.a() >= 1 && packetplayinsetcreativeslot.a() < 36 + PlayerInventory.getHotbarSize();
            boolean flag2 = itemstack == null || itemstack.getItem() != null;
            boolean flag3 = itemstack == null || itemstack.getData() >= 0 && itemstack.count <= 64 && itemstack.count > 0;

            if (flag1 && flag2 && flag3) {
                if (itemstack == null) {
                    this.player.defaultContainer.setItem(packetplayinsetcreativeslot.a(), (ItemStack) null);
                } else {
                    this.player.defaultContainer.setItem(packetplayinsetcreativeslot.a(), itemstack);
                }

                this.player.defaultContainer.a(this.player, true);
            } else if (flag && flag2 && flag3 && this.m < 200) {
                this.m += 20;
                EntityItem entityitem = this.player.drop(itemstack, true);

                if (entityitem != null) {
                    entityitem.j();
                }
            }
        }

    }

    public void a(PacketPlayInTransaction packetplayintransaction) {
        PlayerConnectionUtils.ensureMainThread(packetplayintransaction, this, this.player.u());
        Short oshort = (Short) this.n.get(this.player.activeContainer.windowId);

        if (oshort != null && packetplayintransaction.b() == oshort.shortValue() && this.player.activeContainer.windowId == packetplayintransaction.a() && !this.player.activeContainer.c(this.player) && !this.player.v()) {
            this.player.activeContainer.a(this.player, true);
        }

    }

    public void a(PacketPlayInUpdateSign packetplayinupdatesign) {
        PlayerConnectionUtils.ensureMainThread(packetplayinupdatesign, this, this.player.u());
        this.player.z();
        WorldServer worldserver = this.minecraftServer.getWorldServer(this.player.dimension);
        BlockPosition blockposition = packetplayinupdatesign.a();

        if (worldserver.isLoaded(blockposition)) {
            TileEntity tileentity = worldserver.getTileEntity(blockposition);

            if (!(tileentity instanceof TileEntitySign)) {
                return;
            }

            TileEntitySign tileentitysign = (TileEntitySign) tileentity;

            if (!tileentitysign.b() || tileentitysign.c() != this.player) {
                this.minecraftServer.warning("Player " + this.player.getName() + " just tried to change non-editable sign");
                return;
            }

            IChatBaseComponent[] aichatbasecomponent = packetplayinupdatesign.b();

            for (int i = 0; i < aichatbasecomponent.length; ++i) {
                tileentitysign.lines[i] = new ChatComponentText(aichatbasecomponent[i].c());
            }

            tileentitysign.update();
            worldserver.notify(blockposition);
        }

    }

    public void a(PacketPlayInKeepAlive packetplayinkeepalive) {
        if (packetplayinkeepalive.a() == this.i) {
            int i = (int) (this.d() - this.j);

            this.player.ping = (this.player.ping * 3 + i) / 4;
        }

    }

    private long d() {
        return System.nanoTime() / 1000000L;
    }

    public void a(PacketPlayInAbilities packetplayinabilities) {
        PlayerConnectionUtils.ensureMainThread(packetplayinabilities, this, this.player.u());
        this.player.abilities.isFlying = packetplayinabilities.isFlying() && this.player.abilities.canFly;
    }

    public void a(PacketPlayInTabComplete packetplayintabcomplete) {
        PlayerConnectionUtils.ensureMainThread(packetplayintabcomplete, this, this.player.u());
        ArrayList arraylist = Lists.newArrayList();
        Iterator iterator = this.minecraftServer.tabCompleteCommand(this.player, packetplayintabcomplete.a(), packetplayintabcomplete.b()).iterator();

        while (iterator.hasNext()) {
            String s = (String) iterator.next();

            arraylist.add(s);
        }

        this.player.playerConnection.sendPacket(new PacketPlayOutTabComplete((String[]) arraylist.toArray(new String[arraylist.size()])));
    }

    public void a(PacketPlayInSettings packetplayinsettings) {
        PlayerConnectionUtils.ensureMainThread(packetplayinsettings, this, this.player.u());
        this.player.a(packetplayinsettings);
    }

    public void a(PacketPlayInCustomPayload packetplayincustompayload) {
        PlayerConnectionUtils.ensureMainThread(packetplayincustompayload, this, this.player.u());
        PacketDataSerializer packetdataserializer;
        ItemStack itemstack;
        ItemStack itemstack1;

        if ("MC|BEdit".equals(packetplayincustompayload.a())) {
            packetdataserializer = new PacketDataSerializer(Unpooled.wrappedBuffer((ByteBuf) packetplayincustompayload.b()));

            try {
                itemstack = packetdataserializer.i();
                if (itemstack == null) {
                    return;
                }

                if (!ItemBookAndQuill.b(itemstack.getTag())) {
                    throw new IOException("Invalid book tag!");
                }

                itemstack1 = this.player.inventory.getItemInHand();
                if (itemstack1 != null) {
                    if (itemstack.getItem() == Items.WRITABLE_BOOK && itemstack.getItem() == itemstack1.getItem()) {
                        itemstack1.a("pages", (NBTBase) itemstack.getTag().getList("pages", 8));
                    }

                    return;
                }
            } catch (Exception exception) {
                PlayerConnection.c.error("Couldn\'t handle book info", (Throwable) exception);
                return;
            } finally {
                packetdataserializer.release();
            }

            return;
        } else if ("MC|BSign".equals(packetplayincustompayload.a())) {
            packetdataserializer = new PacketDataSerializer(Unpooled.wrappedBuffer((ByteBuf) packetplayincustompayload.b()));

            try {
                itemstack = packetdataserializer.i();
                if (itemstack == null) {
                    return;
                }

                if (!ItemWrittenBook.b(itemstack.getTag())) {
                    throw new IOException("Invalid book tag!");
                }

                itemstack1 = this.player.inventory.getItemInHand();
                if (itemstack1 != null) {
                    if (itemstack.getItem() == Items.WRITTEN_BOOK && itemstack1.getItem() == Items.WRITABLE_BOOK) {
                        itemstack1.a("author", (NBTBase) (new NBTTagString(this.player.getName())));
                        itemstack1.a("title", (NBTBase) (new NBTTagString(itemstack.getTag().getString("title"))));
                        itemstack1.a("pages", (NBTBase) itemstack.getTag().getList("pages", 8));
                        itemstack1.setItem(Items.WRITTEN_BOOK);
                    }

                    return;
                }
            } catch (Exception exception1) {
                PlayerConnection.c.error("Couldn\'t sign book", (Throwable) exception1);
                return;
            } finally {
                packetdataserializer.release();
            }

            return;
        } else if ("MC|TrSel".equals(packetplayincustompayload.a())) {
            try {
                int i = packetplayincustompayload.b().readInt();
                Container container = this.player.activeContainer;

                if (container instanceof ContainerMerchant) {
                    ((ContainerMerchant) container).d(i);
                }
            } catch (Exception exception2) {
                PlayerConnection.c.error("Couldn\'t select trade", (Throwable) exception2);
            }
        } else if ("MC|AdvCdm".equals(packetplayincustompayload.a())) {
            if (!this.minecraftServer.getEnableCommandBlock()) {
                this.player.sendMessage(new ChatMessage("advMode.notEnabled", new Object[0]));
            } else if (this.player.a(2, "") && this.player.abilities.canInstantlyBuild) {
                packetdataserializer = packetplayincustompayload.b();

                try {
                    byte b0 = packetdataserializer.readByte();
                    CommandBlockListenerAbstract commandblocklistenerabstract = null;

                    if (b0 == 0) {
                        TileEntity tileentity = this.player.world.getTileEntity(new BlockPosition(packetdataserializer.readInt(), packetdataserializer.readInt(), packetdataserializer.readInt()));

                        if (tileentity instanceof TileEntityCommand) {
                            commandblocklistenerabstract = ((TileEntityCommand) tileentity).getCommandBlock();
                        }
                    } else if (b0 == 1) {
                        Entity entity = this.player.world.a(packetdataserializer.readInt());

                        if (entity instanceof EntityMinecartCommandBlock) {
                            commandblocklistenerabstract = ((EntityMinecartCommandBlock) entity).getCommandBlock();
                        }
                    }

                    String s = packetdataserializer.c(packetdataserializer.readableBytes());
                    boolean flag = packetdataserializer.readBoolean();

                    if (commandblocklistenerabstract != null) {
                        commandblocklistenerabstract.setCommand(s);
                        commandblocklistenerabstract.a(flag);
                        if (!flag) {
                            commandblocklistenerabstract.b((IChatBaseComponent) null);
                        }

                        commandblocklistenerabstract.h();
                        this.player.sendMessage(new ChatMessage("advMode.setCommand.success", new Object[] { s}));
                    }
                } catch (Exception exception3) {
                    PlayerConnection.c.error("Couldn\'t set command block", (Throwable) exception3);
                } finally {
                    packetdataserializer.release();
                }
            } else {
                this.player.sendMessage(new ChatMessage("advMode.notAllowed", new Object[0]));
            }
        } else if ("MC|Beacon".equals(packetplayincustompayload.a())) {
            if (this.player.activeContainer instanceof ContainerBeacon) {
                try {
                    packetdataserializer = packetplayincustompayload.b();
                    int j = packetdataserializer.readInt();
                    int k = packetdataserializer.readInt();
                    ContainerBeacon containerbeacon = (ContainerBeacon) this.player.activeContainer;
                    Slot slot = containerbeacon.getSlot(0);

                    if (slot.hasItem()) {
                        slot.a(1);
                        IInventory iinventory = containerbeacon.e();

                        iinventory.b(1, j);
                        iinventory.b(2, k);
                        iinventory.update();
                    }
                } catch (Exception exception4) {
                    PlayerConnection.c.error("Couldn\'t set beacon", (Throwable) exception4);
                }
            }
        } else if ("MC|ItemName".equals(packetplayincustompayload.a()) && this.player.activeContainer instanceof ContainerAnvil) {
            ContainerAnvil containeranvil = (ContainerAnvil) this.player.activeContainer;

            if (packetplayincustompayload.b() != null && packetplayincustompayload.b().readableBytes() >= 1) {
                String s1 = SharedConstants.a(packetplayincustompayload.b().c(32767));

                if (s1.length() <= 30) {
                    containeranvil.a(s1);
                }
            } else {
                containeranvil.a("");
            }
        }

    }

    static class SyntheticClass_1 {

        static final int[] a;
        static final int[] b;
        static final int[] c = new int[PacketPlayInClientCommand.a.values().length];

        static {
            try {
                PlayerConnection.SyntheticClass_1.c[PacketPlayInClientCommand.a.PERFORM_RESPAWN.ordinal()] = 1;
            } catch (NoSuchFieldError nosuchfielderror) {
                ;
            }

            try {
                PlayerConnection.SyntheticClass_1.c[PacketPlayInClientCommand.a.REQUEST_STATS.ordinal()] = 2;
            } catch (NoSuchFieldError nosuchfielderror1) {
                ;
            }

            try {
                PlayerConnection.SyntheticClass_1.c[PacketPlayInClientCommand.a.OPEN_INVENTORY_ACHIEVEMENT.ordinal()] = 3;
            } catch (NoSuchFieldError nosuchfielderror2) {
                ;
            }

            b = new int[PacketPlayInEntityAction.a.values().length];

            try {
                PlayerConnection.SyntheticClass_1.b[PacketPlayInEntityAction.a.START_SNEAKING.ordinal()] = 1;
            } catch (NoSuchFieldError nosuchfielderror3) {
                ;
            }

            try {
                PlayerConnection.SyntheticClass_1.b[PacketPlayInEntityAction.a.STOP_SNEAKING.ordinal()] = 2;
            } catch (NoSuchFieldError nosuchfielderror4) {
                ;
            }

            try {
                PlayerConnection.SyntheticClass_1.b[PacketPlayInEntityAction.a.START_SPRINTING.ordinal()] = 3;
            } catch (NoSuchFieldError nosuchfielderror5) {
                ;
            }

            try {
                PlayerConnection.SyntheticClass_1.b[PacketPlayInEntityAction.a.STOP_SPRINTING.ordinal()] = 4;
            } catch (NoSuchFieldError nosuchfielderror6) {
                ;
            }

            try {
                PlayerConnection.SyntheticClass_1.b[PacketPlayInEntityAction.a.STOP_SLEEPING.ordinal()] = 5;
            } catch (NoSuchFieldError nosuchfielderror7) {
                ;
            }

            try {
                PlayerConnection.SyntheticClass_1.b[PacketPlayInEntityAction.a.RIDING_JUMP.ordinal()] = 6;
            } catch (NoSuchFieldError nosuchfielderror8) {
                ;
            }

            try {
                PlayerConnection.SyntheticClass_1.b[PacketPlayInEntityAction.a.OPEN_INVENTORY.ordinal()] = 7;
            } catch (NoSuchFieldError nosuchfielderror9) {
                ;
            }

            a = new int[PacketPlayInBlockDig.a.values().length];

            try {
                PlayerConnection.SyntheticClass_1.a[PacketPlayInBlockDig.a.DROP_ITEM.ordinal()] = 1;
            } catch (NoSuchFieldError nosuchfielderror10) {
                ;
            }

            try {
                PlayerConnection.SyntheticClass_1.a[PacketPlayInBlockDig.a.DROP_ALL_ITEMS.ordinal()] = 2;
            } catch (NoSuchFieldError nosuchfielderror11) {
                ;
            }

            try {
                PlayerConnection.SyntheticClass_1.a[PacketPlayInBlockDig.a.RELEASE_USE_ITEM.ordinal()] = 3;
            } catch (NoSuchFieldError nosuchfielderror12) {
                ;
            }

            try {
                PlayerConnection.SyntheticClass_1.a[PacketPlayInBlockDig.a.START_DESTROY_BLOCK.ordinal()] = 4;
            } catch (NoSuchFieldError nosuchfielderror13) {
                ;
            }

            try {
                PlayerConnection.SyntheticClass_1.a[PacketPlayInBlockDig.a.ABORT_DESTROY_BLOCK.ordinal()] = 5;
            } catch (NoSuchFieldError nosuchfielderror14) {
                ;
            }

            try {
                PlayerConnection.SyntheticClass_1.a[PacketPlayInBlockDig.a.STOP_DESTROY_BLOCK.ordinal()] = 6;
            } catch (NoSuchFieldError nosuchfielderror15) {
                ;
            }

        }
    }
}
