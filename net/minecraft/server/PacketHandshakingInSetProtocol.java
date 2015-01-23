package net.minecraft.server;

public class PacketHandshakingInSetProtocol implements Packet {

    private int a;
    private String b;
    private int c;
    private EnumProtocol d;

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.e();
        this.b = packetdataserializer.c(255);
        this.c = packetdataserializer.readUnsignedShort();
        this.d = EnumProtocol.a(packetdataserializer.e());
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.b(this.a);
        packetdataserializer.a(this.b);
        packetdataserializer.writeShort(this.c);
        packetdataserializer.b(this.d.a());
    }

    public void a(PacketHandshakingInListener packethandshakinginlistener) {
        packethandshakinginlistener.a(this);
    }

    public EnumProtocol a() {
        return this.d;
    }

    public int b() {
        return this.a;
    }
}
