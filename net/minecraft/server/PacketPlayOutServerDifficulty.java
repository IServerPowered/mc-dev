package net.minecraft.server;

public class PacketPlayOutServerDifficulty implements Packet {

    private EnumDifficulty a;
    private boolean b;

    public PacketPlayOutServerDifficulty() {}

    public PacketPlayOutServerDifficulty(EnumDifficulty enumdifficulty, boolean flag) {
        this.a = enumdifficulty;
        this.b = flag;
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = EnumDifficulty.getById(packetdataserializer.readUnsignedByte());
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.writeByte(this.a.a());
    }
}
