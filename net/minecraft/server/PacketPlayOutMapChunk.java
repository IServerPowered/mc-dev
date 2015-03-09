package net.minecraft.server;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class PacketPlayOutMapChunk implements Packet<PacketListenerPlayOut> {

    private int a;
    private int b;
    private PacketPlayOutMapChunk.a c;
    private boolean d;

    public PacketPlayOutMapChunk() {}

    public PacketPlayOutMapChunk(Chunk chunk, boolean flag, int i) {
        this.a = chunk.locX;
        this.b = chunk.locZ;
        this.d = flag;
        this.c = a(chunk, flag, !chunk.getWorld().worldProvider.o(), i);
    }

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.readInt();
        this.b = packetdataserializer.readInt();
        this.d = packetdataserializer.readBoolean();
        this.c = new PacketPlayOutMapChunk.a();
        this.c.b = packetdataserializer.readShort();
        this.c.a = packetdataserializer.a();
    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.writeInt(this.a);
        packetdataserializer.writeInt(this.b);
        packetdataserializer.writeBoolean(this.d);
        packetdataserializer.writeShort((short) (this.c.b & '\uffff'));
        packetdataserializer.a(this.c.a);
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }

    protected static int a(int i, boolean flag, boolean flag1) {
        int j = i * 2 * 16 * 16 * 16;
        int k = i * 16 * 16 * 16 / 2;
        int l = flag ? i * 16 * 16 * 16 / 2 : 0;
        int i1 = flag1 ? 256 : 0;

        return j + k + l + i1;
    }

    public static PacketPlayOutMapChunk.a a(Chunk chunk, boolean flag, boolean flag1, int i) {
        ChunkSection[] achunksection = chunk.getSections();
        PacketPlayOutMapChunk.a packetplayoutmapchunk_a = new PacketPlayOutMapChunk.a();
        ArrayList arraylist = Lists.newArrayList();

        int j;

        for (j = 0; j < achunksection.length; ++j) {
            ChunkSection chunksection = achunksection[j];

            if (chunksection != null && (!flag || !chunksection.a()) && (i & 1 << j) != 0) {
                packetplayoutmapchunk_a.b |= 1 << j;
                arraylist.add(chunksection);
            }
        }

        packetplayoutmapchunk_a.a = new byte[a(Integer.bitCount(packetplayoutmapchunk_a.b), flag1, flag)];
        j = 0;
        Iterator iterator = arraylist.iterator();

        ChunkSection chunksection1;

        while (iterator.hasNext()) {
            chunksection1 = (ChunkSection) iterator.next();
            char[] achar = chunksection1.getIdArray();
            char[] achar1 = achar;
            int k = achar.length;

            for (int l = 0; l < k; ++l) {
                char c0 = achar1[l];

                packetplayoutmapchunk_a.a[j++] = (byte) (c0 & 255);
                packetplayoutmapchunk_a.a[j++] = (byte) (c0 >> 8 & 255);
            }
        }

        for (iterator = arraylist.iterator(); iterator.hasNext(); j = a(chunksection1.getEmittedLightArray().a(), packetplayoutmapchunk_a.a, j)) {
            chunksection1 = (ChunkSection) iterator.next();
        }

        if (flag1) {
            for (iterator = arraylist.iterator(); iterator.hasNext(); j = a(chunksection1.getSkyLightArray().a(), packetplayoutmapchunk_a.a, j)) {
                chunksection1 = (ChunkSection) iterator.next();
            }
        }

        if (flag) {
            a(chunk.getBiomeIndex(), packetplayoutmapchunk_a.a, j);
        }

        return packetplayoutmapchunk_a;
    }

    private static int a(byte[] abyte, byte[] abyte1, int i) {
        System.arraycopy(abyte, 0, abyte1, i, abyte.length);
        return i + abyte.length;
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayOut) packetlistener);
    }

    public static class a {

        public byte[] a;
        public int b;

        public a() {}
    }
}
