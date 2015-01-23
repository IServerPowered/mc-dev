package net.minecraft.server;

public class PacketPlayInSteerVehicle implements Packet {

    private float a;
    private float b;
    private boolean c;
    private boolean d;

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.readFloat();
        this.b = packetdataserializer.readFloat();
        byte b0 = packetdataserializer.readByte();

        this.c = (b0 & 1) > 0;
        this.d = (b0 & 2) > 0;
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.writeFloat(this.a);
        packetdataserializer.writeFloat(this.b);
        byte b0 = 0;

        if (this.c) {
            b0 = (byte) (b0 | 1);
        }

        if (this.d) {
            b0 = (byte) (b0 | 2);
        }

        packetdataserializer.writeByte(b0);
    }

    public void a(PacketListenerPlayIn packetlistenerplayin) {
        packetlistenerplayin.a(this);
    }

    public float a() {
        return this.a;
    }

    public float b() {
        return this.b;
    }

    public boolean c() {
        return this.c;
    }

    public boolean d() {
        return this.d;
    }
}
