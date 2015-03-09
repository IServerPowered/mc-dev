package net.minecraft.server;

import java.io.IOException;

public class PacketPlayInClientCommand implements Packet<PacketListenerPlayIn> {

    private PacketPlayInClientCommand.a a;

    public PacketPlayInClientCommand() {}

    public PacketPlayInClientCommand(PacketPlayInClientCommand.a packetplayinclientcommand_a) {
        this.a = packetplayinclientcommand_a;
    }

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = (PacketPlayInClientCommand.a) packetdataserializer.a(PacketPlayInClientCommand.a.class);
    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.a((Enum) this.a);
    }

    public void a(PacketListenerPlayIn packetlistenerplayin) {
        packetlistenerplayin.a(this);
    }

    public PacketPlayInClientCommand.a a() {
        return this.a;
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayIn) packetlistener);
    }

    public static enum a {

        PERFORM_RESPAWN, REQUEST_STATS, OPEN_INVENTORY_ACHIEVEMENT;

        private a() {}
    }
}
