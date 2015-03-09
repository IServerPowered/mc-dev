package net.minecraft.server;

import java.io.IOException;

public class PacketPlayInUseEntity implements Packet<PacketListenerPlayIn> {

    private int a;
    private PacketPlayInUseEntity.a action;
    private Vec3D c;

    public PacketPlayInUseEntity() {}

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.e();
        this.action = (PacketPlayInUseEntity.a) packetdataserializer.a(PacketPlayInUseEntity.a.class);
        if (this.action == PacketPlayInUseEntity.a.INTERACT_AT) {
            this.c = new Vec3D((double) packetdataserializer.readFloat(), (double) packetdataserializer.readFloat(), (double) packetdataserializer.readFloat());
        }

    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.b(this.a);
        packetdataserializer.a((Enum) this.action);
        if (this.action == PacketPlayInUseEntity.a.INTERACT_AT) {
            packetdataserializer.writeFloat((float) this.c.a);
            packetdataserializer.writeFloat((float) this.c.b);
            packetdataserializer.writeFloat((float) this.c.c);
        }

    }

    public void a(PacketListenerPlayIn packetlistenerplayin) {
        packetlistenerplayin.a(this);
    }

    public Entity a(World world) {
        return world.a(this.a);
    }

    public PacketPlayInUseEntity.a a() {
        return this.action;
    }

    public Vec3D b() {
        return this.c;
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayIn) packetlistener);
    }

    public static enum a {

        INTERACT, ATTACK, INTERACT_AT;

        private a() {}
    }
}
