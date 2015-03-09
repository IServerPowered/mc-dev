package net.minecraft.server;

import java.io.IOException;

public class PacketPlayInEntityAction implements Packet<PacketListenerPlayIn> {

    private int a;
    private PacketPlayInEntityAction.a animation;
    private int c;

    public PacketPlayInEntityAction() {}

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.e();
        this.animation = (PacketPlayInEntityAction.a) packetdataserializer.a(PacketPlayInEntityAction.a.class);
        this.c = packetdataserializer.e();
    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.b(this.a);
        packetdataserializer.a((Enum) this.animation);
        packetdataserializer.b(this.c);
    }

    public void a(PacketListenerPlayIn packetlistenerplayin) {
        packetlistenerplayin.a(this);
    }

    public PacketPlayInEntityAction.a b() {
        return this.animation;
    }

    public int c() {
        return this.c;
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayIn) packetlistener);
    }

    public static enum a {

        START_SNEAKING, STOP_SNEAKING, STOP_SLEEPING, START_SPRINTING, STOP_SPRINTING, RIDING_JUMP, OPEN_INVENTORY;

        private a() {}
    }
}
