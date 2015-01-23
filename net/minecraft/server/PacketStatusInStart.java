package net.minecraft.server;

public class PacketStatusInStart implements Packet {

    public void a(PacketDataSerializer packetdataserializer) {}

    public void b(PacketDataSerializer packetdataserializer) {}

    public void a(PacketStatusInListener packetstatusinlistener) {
        packetstatusinlistener.a(this);
    }
}
