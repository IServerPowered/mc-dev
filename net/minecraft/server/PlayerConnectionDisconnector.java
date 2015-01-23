package net.minecraft.server;

class PlayerConnectionDisconnector implements Runnable {

    final PlayerConnection a;

    PlayerConnectionDisconnector(PlayerConnection playerconnection) {
        this.a = playerconnection;
    }

    public void run() {
        this.a.networkManager.l();
    }
}
