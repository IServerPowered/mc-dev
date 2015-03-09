package net.minecraft.server;

import java.io.IOException;

public class PacketPlayOutMultiBlockChange implements Packet<PacketListenerPlayOut> {

    private ChunkCoordIntPair a;
    private PacketPlayOutMultiBlockChange.a[] b;

    public PacketPlayOutMultiBlockChange() {}

    public PacketPlayOutMultiBlockChange(int i, short[] ashort, Chunk chunk) {
        this.a = new ChunkCoordIntPair(chunk.locX, chunk.locZ);
        this.b = new PacketPlayOutMultiBlockChange.a[i];

        for (int j = 0; j < this.b.length; ++j) {
            this.b[j] = new PacketPlayOutMultiBlockChange.a(ashort[j], chunk);
        }

    }

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = new ChunkCoordIntPair(packetdataserializer.readInt(), packetdataserializer.readInt());
        this.b = new PacketPlayOutMultiBlockChange.a[packetdataserializer.e()];

        for (int i = 0; i < this.b.length; ++i) {
            this.b[i] = new PacketPlayOutMultiBlockChange.a(packetdataserializer.readShort(), (IBlockData) Block.d.a(packetdataserializer.e()));
        }

    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.writeInt(this.a.x);
        packetdataserializer.writeInt(this.a.z);
        packetdataserializer.b(this.b.length);
        PacketPlayOutMultiBlockChange.a[] apacketplayoutmultiblockchange_a = this.b;
        int i = apacketplayoutmultiblockchange_a.length;

        for (int j = 0; j < i; ++j) {
            PacketPlayOutMultiBlockChange.a packetplayoutmultiblockchange_a = apacketplayoutmultiblockchange_a[j];

            packetdataserializer.writeShort(packetplayoutmultiblockchange_a.b());
            packetdataserializer.b(Block.d.b(packetplayoutmultiblockchange_a.c()));
        }

    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayOut) packetlistener);
    }

    public class a {

        private final short b;
        private final IBlockData c;

        public a(short short0, IBlockData iblockdata) {
            this.b = short0;
            this.c = iblockdata;
        }

        public a(short short0, Chunk chunk) {
            this.b = short0;
            this.c = chunk.getBlockData(this.a());
        }

        public BlockPosition a() {
            return new BlockPosition(PacketPlayOutMultiBlockChange.this.a.a(this.b >> 12 & 15, this.b & 255, this.b >> 8 & 15));
        }

        public short b() {
            return this.b;
        }

        public IBlockData c() {
            return this.c;
        }
    }
}
