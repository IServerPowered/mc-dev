package net.minecraft.server;

import com.google.common.base.Charsets;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.exceptions.AuthenticationUnavailableException;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import java.math.BigInteger;
import java.security.PrivateKey;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import javax.crypto.SecretKey;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginListener implements PacketLoginInListener, IUpdatePlayerListBox {

    private static final AtomicInteger b = new AtomicInteger(0);
    private static final Logger c = LogManager.getLogger();
    private static final Random random = new Random();
    private final byte[] e = new byte[4];
    private final MinecraftServer server;
    public final NetworkManager networkManager;
    private LoginListener.a g;
    private int h;
    private GameProfile i;
    private String j;
    private SecretKey loginKey;
    private EntityPlayer l;

    public LoginListener(MinecraftServer minecraftserver, NetworkManager networkmanager) {
        this.g = LoginListener.a.HELLO;
        this.j = "";
        this.server = minecraftserver;
        this.networkManager = networkmanager;
        LoginListener.random.nextBytes(this.e);
    }

    public void c() {
        if (this.g == LoginListener.a.READY_TO_ACCEPT) {
            this.b();
        } else if (this.g == LoginListener.a.e) {
            EntityPlayer entityplayer = this.server.getPlayerList().a(this.i.getId());

            if (entityplayer == null) {
                this.g = LoginListener.a.READY_TO_ACCEPT;
                this.server.getPlayerList().a(this.networkManager, this.l);
                this.l = null;
            }
        }

        if (this.h++ == 600) {
            this.disconnect("Took too long to log in");
        }

    }

    public void disconnect(String s) {
        try {
            LoginListener.c.info("Disconnecting " + this.d() + ": " + s);
            ChatComponentText chatcomponenttext = new ChatComponentText(s);

            this.networkManager.handle(new PacketLoginOutDisconnect(chatcomponenttext));
            this.networkManager.close(chatcomponenttext);
        } catch (Exception exception) {
            LoginListener.c.error("Error whilst disconnecting player", (Throwable) exception);
        }

    }

    public void b() {
        if (!this.i.isComplete()) {
            this.i = this.a(this.i);
        }

        String s = this.server.getPlayerList().attemptLogin(this.networkManager.getSocketAddress(), this.i);

        if (s != null) {
            this.disconnect(s);
        } else {
            this.g = LoginListener.a.ACCEPTED;
            if (this.server.aJ() >= 0 && !this.networkManager.c()) {
                this.networkManager.a(new PacketLoginOutSetCompression(this.server.aJ()), new ChannelFutureListener() {
                    public void a(ChannelFuture channelfuture) throws Exception {
                        LoginListener.this.networkManager.a(LoginListener.this.server.aJ());
                    }

                    public void operationComplete(Future future) throws Exception {
                        this.a((ChannelFuture) future);
                    }
                }, new GenericFutureListener[0]);
            }

            this.networkManager.handle(new PacketLoginOutSuccess(this.i));
            EntityPlayer entityplayer = this.server.getPlayerList().a(this.i.getId());

            if (entityplayer != null) {
                this.g = LoginListener.a.e;
                this.l = this.server.getPlayerList().processLogin(this.i);
            } else {
                this.server.getPlayerList().a(this.networkManager, this.server.getPlayerList().processLogin(this.i));
            }
        }

    }

    public void a(IChatBaseComponent ichatbasecomponent) {
        LoginListener.c.info(this.d() + " lost connection: " + ichatbasecomponent.c());
    }

    public String d() {
        return this.i != null ? this.i.toString() + " (" + this.networkManager.getSocketAddress().toString() + ")" : String.valueOf(this.networkManager.getSocketAddress());
    }

    public void a(PacketLoginInStart packetlogininstart) {
        Validate.validState(this.g == LoginListener.a.HELLO, "Unexpected hello packet", new Object[0]);
        this.i = packetlogininstart.a();
        if (this.server.getOnlineMode() && !this.networkManager.c()) {
            this.g = LoginListener.a.KEY;
            this.networkManager.handle(new PacketLoginOutEncryptionBegin(this.j, this.server.P().getPublic(), this.e));
        } else {
            this.g = LoginListener.a.READY_TO_ACCEPT;
        }

    }

    public void a(PacketLoginInEncryptionBegin packetlogininencryptionbegin) {
        Validate.validState(this.g == LoginListener.a.KEY, "Unexpected key packet", new Object[0]);
        PrivateKey privatekey = this.server.P().getPrivate();

        if (!Arrays.equals(this.e, packetlogininencryptionbegin.b(privatekey))) {
            throw new IllegalStateException("Invalid nonce!");
        } else {
            this.loginKey = packetlogininencryptionbegin.a(privatekey);
            this.g = LoginListener.a.AUTHENTICATING;
            this.networkManager.a(this.loginKey);
            (new Thread("User Authenticator #" + LoginListener.b.incrementAndGet()) {
                public void run() {
                    GameProfile gameprofile = LoginListener.this.i;

                    try {
                        String s = (new BigInteger(MinecraftEncryption.a(LoginListener.this.j, LoginListener.this.server.P().getPublic(), LoginListener.this.loginKey))).toString(16);

                        LoginListener.this.i = LoginListener.this.server.aC().hasJoinedServer(new GameProfile((UUID) null, gameprofile.getName()), s);
                        if (LoginListener.this.i != null) {
                            LoginListener.c.info("UUID of player " + LoginListener.this.i.getName() + " is " + LoginListener.this.i.getId());
                            LoginListener.this.g = LoginListener.a.READY_TO_ACCEPT;
                        } else if (LoginListener.this.server.S()) {
                            LoginListener.c.warn("Failed to verify username but will let them in anyway!");
                            LoginListener.this.i = LoginListener.this.a(gameprofile);
                            LoginListener.this.g = LoginListener.a.READY_TO_ACCEPT;
                        } else {
                            LoginListener.this.disconnect("Failed to verify username!");
                            LoginListener.c.error("Username \'" + LoginListener.this.i.getName() + "\' tried to join with an invalid session");
                        }
                    } catch (AuthenticationUnavailableException authenticationunavailableexception) {
                        if (LoginListener.this.server.S()) {
                            LoginListener.c.warn("Authentication servers are down but will let them in anyway!");
                            LoginListener.this.i = LoginListener.this.a(gameprofile);
                            LoginListener.this.g = LoginListener.a.READY_TO_ACCEPT;
                        } else {
                            LoginListener.this.disconnect("Authentication servers are down. Please try again later, sorry!");
                            LoginListener.c.error("Couldn\'t verify username because servers are unavailable");
                        }
                    }

                }
            }).start();
        }
    }

    protected GameProfile a(GameProfile gameprofile) {
        UUID uuid = UUID.nameUUIDFromBytes(("OfflinePlayer:" + gameprofile.getName()).getBytes(Charsets.UTF_8));

        return new GameProfile(uuid, gameprofile.getName());
    }

    static enum a {

        HELLO, KEY, AUTHENTICATING, READY_TO_ACCEPT, e, ACCEPTED;

        private a() {}
    }
}
