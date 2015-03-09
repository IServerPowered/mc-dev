package net.minecraft.server;

import java.io.IOException;

public class PacketPlayOutTitle implements Packet<PacketListenerPlayOut> {

    private PacketPlayOutTitle.a a;
    private IChatBaseComponent b;
    private int c;
    private int d;
    private int e;

    public PacketPlayOutTitle() {}

    public PacketPlayOutTitle(PacketPlayOutTitle.a packetplayouttitle_a, IChatBaseComponent ichatbasecomponent) {
        this(packetplayouttitle_a, ichatbasecomponent, -1, -1, -1);
    }

    public PacketPlayOutTitle(int i, int j, int k) {
        this(PacketPlayOutTitle.a.TIMES, (IChatBaseComponent) null, i, j, k);
    }

    public PacketPlayOutTitle(PacketPlayOutTitle.a packetplayouttitle_a, IChatBaseComponent ichatbasecomponent, int i, int j, int k) {
        this.a = packetplayouttitle_a;
        this.b = ichatbasecomponent;
        this.c = i;
        this.d = j;
        this.e = k;
    }

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = (PacketPlayOutTitle.a) packetdataserializer.a(PacketPlayOutTitle.a.class);
        if (this.a == PacketPlayOutTitle.a.TITLE || this.a == PacketPlayOutTitle.a.SUBTITLE) {
            this.b = packetdataserializer.d();
        }

        if (this.a == PacketPlayOutTitle.a.TIMES) {
            this.c = packetdataserializer.readInt();
            this.d = packetdataserializer.readInt();
            this.e = packetdataserializer.readInt();
        }

    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.a((Enum) this.a);
        if (this.a == PacketPlayOutTitle.a.TITLE || this.a == PacketPlayOutTitle.a.SUBTITLE) {
            packetdataserializer.a(this.b);
        }

        if (this.a == PacketPlayOutTitle.a.TIMES) {
            packetdataserializer.writeInt(this.c);
            packetdataserializer.writeInt(this.d);
            packetdataserializer.writeInt(this.e);
        }

    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayOut) packetlistener);
    }

    public static enum a {

        TITLE, SUBTITLE, TIMES, CLEAR, RESET;

        private a() {}

        public static PacketPlayOutTitle.a a(String s) {
            PacketPlayOutTitle.a[] apacketplayouttitle_a = values();
            int i = apacketplayouttitle_a.length;

            for (int j = 0; j < i; ++j) {
                PacketPlayOutTitle.a packetplayouttitle_a = apacketplayouttitle_a[j];

                if (packetplayouttitle_a.name().equalsIgnoreCase(s)) {
                    return packetplayouttitle_a;
                }
            }

            return PacketPlayOutTitle.a.TITLE;
        }

        public static String[] a() {
            String[] astring = new String[values().length];
            int i = 0;
            PacketPlayOutTitle.a[] apacketplayouttitle_a = values();
            int j = apacketplayouttitle_a.length;

            for (int k = 0; k < j; ++k) {
                PacketPlayOutTitle.a packetplayouttitle_a = apacketplayouttitle_a[k];

                astring[i++] = packetplayouttitle_a.name().toLowerCase();
            }

            return astring;
        }
    }
}
