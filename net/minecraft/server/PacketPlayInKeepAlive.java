package net.minecraft.server;

public class PacketPlayInKeepAlive implements Packet {

    private int a;

    public void a(PacketListenerPlayIn packetlistenerplayin) {
        packetlistenerplayin.a(this);
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.e();
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.b(this.a);
    }

    public int a() {
        return this.a;
    }
}
