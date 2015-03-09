package net.minecraft.server;

import java.io.IOException;

public class PacketPlayInResourcePackStatus implements Packet<PacketListenerPlayIn> {

    private String a;
    private PacketPlayInResourcePackStatus.a b;

    public PacketPlayInResourcePackStatus() {}

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.c(40);
        this.b = (PacketPlayInResourcePackStatus.a) packetdataserializer.a(PacketPlayInResourcePackStatus.a.class);
    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.a(this.a);
        packetdataserializer.a((Enum) this.b);
    }

    public void a(PacketListenerPlayIn packetlistenerplayin) {
        packetlistenerplayin.a(this);
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayIn) packetlistener);
    }

    public static enum a {

        SUCCESSFULLY_LOADED, DECLINED, FAILED_DOWNLOAD, ACCEPTED;

        private a() {}
    }
}
