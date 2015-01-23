package net.minecraft.server;

public class PacketPlayOutSpawnEntityPainting implements Packet {

    private int a;
    private BlockPosition b;
    private EnumDirection c;
    private String d;

    public PacketPlayOutSpawnEntityPainting() {}

    public PacketPlayOutSpawnEntityPainting(EntityPainting entitypainting) {
        this.a = entitypainting.getId();
        this.b = entitypainting.getBlockPosition();
        this.c = entitypainting.direction;
        this.d = entitypainting.art.B;
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.e();
        this.d = packetdataserializer.c(EnumArt.A);
        this.b = packetdataserializer.c();
        this.c = EnumDirection.fromType2(packetdataserializer.readUnsignedByte());
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.b(this.a);
        packetdataserializer.a(this.d);
        packetdataserializer.a(this.b);
        packetdataserializer.writeByte(this.c.b());
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }
}
