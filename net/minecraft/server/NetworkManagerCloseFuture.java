package net.minecraft.server;

import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

class NetworkManagerCloseFuture implements GenericFutureListener {

    final NetworkManager a;
    final ChatComponentText b;
    final ServerConnection c;

    NetworkManagerCloseFuture(ServerConnection serverconnection, NetworkManager networkmanager, ChatComponentText chatcomponenttext) {
        this.c = serverconnection;
        this.a = networkmanager;
        this.b = chatcomponenttext;
    }

    public void operationComplete(Future future) {
        this.a.close(this.b);
    }
}
