package net.minecraft.server;

public class PacketPlayInUpdateSign implements Packet {

    private BlockPosition a;
    private IChatBaseComponent[] b;

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.c();
        this.b = new IChatBaseComponent[4];

        for (int i = 0; i < 4; ++i) {
            this.b[i] = packetdataserializer.d();
        }

    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.a(this.a);

        for (int i = 0; i < 4; ++i) {
            packetdataserializer.a(this.b[i]);
        }

    }

    public void a(PacketListenerPlayIn packetlistenerplayin) {
        packetlistenerplayin.a(this);
    }

    public BlockPosition a() {
        return this.a;
    }

    public IChatBaseComponent[] b() {
        return this.b;
    }
}
