package net.minecraft.server;

public class PacketPlayInHeldItemSlot implements Packet {

    private int itemInHandIndex;

    public void a(PacketDataSerializer packetdataserializer) {
        this.itemInHandIndex = packetdataserializer.readShort();
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.writeShort(this.itemInHandIndex);
    }

    public void a(PacketListenerPlayIn packetlistenerplayin) {
        packetlistenerplayin.a(this);
    }

    public int a() {
        return this.itemInHandIndex;
    }
}
