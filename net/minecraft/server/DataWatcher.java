package net.minecraft.server;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.apache.commons.lang3.ObjectUtils;

public class DataWatcher {

    private final Entity a;
    private boolean b = true;
    private static final Map<Class<?>, Integer> c = Maps.newHashMap();
    private final Map<Integer, DataWatcher.a> d = Maps.newHashMap();
    private boolean e;
    private ReadWriteLock f = new ReentrantReadWriteLock();

    public DataWatcher(Entity entity) {
        this.a = entity;
    }

    public <T> void a(int i, T t0) {
        Integer integer = (Integer) DataWatcher.c.get(t0.getClass());

        if (integer == null) {
            throw new IllegalArgumentException("Unknown data type: " + t0.getClass());
        } else if (i > 31) {
            throw new IllegalArgumentException("Data value id is too big with " + i + "! (Max is " + 31 + ")");
        } else if (this.d.containsKey(Integer.valueOf(i))) {
            throw new IllegalArgumentException("Duplicate id value for " + i + "!");
        } else {
            DataWatcher.a datawatcher_a = new DataWatcher.a(integer.intValue(), i, t0);

            this.f.writeLock().lock();
            this.d.put(Integer.valueOf(i), datawatcher_a);
            this.f.writeLock().unlock();
            this.b = false;
        }
    }

    public void add(int i, int j) {
        DataWatcher.a datawatcher_a = new DataWatcher.a(j, i, (Object) null);

        this.f.writeLock().lock();
        this.d.put(Integer.valueOf(i), datawatcher_a);
        this.f.writeLock().unlock();
        this.b = false;
    }

    public byte getByte(int i) {
        return ((Byte) this.j(i).b()).byteValue();
    }

    public short getShort(int i) {
        return ((Short) this.j(i).b()).shortValue();
    }

    public int getInt(int i) {
        return ((Integer) this.j(i).b()).intValue();
    }

    public float getFloat(int i) {
        return ((Float) this.j(i).b()).floatValue();
    }

    public String getString(int i) {
        return (String) this.j(i).b();
    }

    public ItemStack getItemStack(int i) {
        return (ItemStack) this.j(i).b();
    }

    private DataWatcher.a j(int i) {
        this.f.readLock().lock();

        DataWatcher.a datawatcher_a;

        try {
            datawatcher_a = (DataWatcher.a) this.d.get(Integer.valueOf(i));
        } catch (Throwable throwable) {
            CrashReport crashreport = CrashReport.a(throwable, "Getting synched entity data");
            CrashReportSystemDetails crashreportsystemdetails = crashreport.a("Synched entity data");

            crashreportsystemdetails.a("Data ID", (Object) Integer.valueOf(i));
            throw new ReportedException(crashreport);
        }

        this.f.readLock().unlock();
        return datawatcher_a;
    }

    public Vector3f h(int i) {
        return (Vector3f) this.j(i).b();
    }

    public <T> void watch(int i, T t0) {
        DataWatcher.a datawatcher_a = this.j(i);

        if (ObjectUtils.notEqual(t0, datawatcher_a.b())) {
            datawatcher_a.a(t0);
            this.a.i(i);
            datawatcher_a.a(true);
            this.e = true;
        }

    }

    public void update(int i) {
        this.j(i).d = true;
        this.e = true;
    }

    public boolean a() {
        return this.e;
    }

    public static void a(List<DataWatcher.a> list, PacketDataSerializer packetdataserializer) throws IOException {
        if (list != null) {
            Iterator iterator = list.iterator();

            while (iterator.hasNext()) {
                DataWatcher.a datawatcher_a = (DataWatcher.a) iterator.next();

                a(packetdataserializer, datawatcher_a);
            }
        }

        packetdataserializer.writeByte(127);
    }

    public List<DataWatcher.a> b() {
        ArrayList arraylist = null;

        if (this.e) {
            this.f.readLock().lock();
            Iterator iterator = this.d.values().iterator();

            while (iterator.hasNext()) {
                DataWatcher.a datawatcher_a = (DataWatcher.a) iterator.next();

                if (datawatcher_a.d()) {
                    datawatcher_a.a(false);
                    if (arraylist == null) {
                        arraylist = Lists.newArrayList();
                    }

                    arraylist.add(datawatcher_a);
                }
            }

            this.f.readLock().unlock();
        }

        this.e = false;
        return arraylist;
    }

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.f.readLock().lock();
        Iterator iterator = this.d.values().iterator();

        while (iterator.hasNext()) {
            DataWatcher.a datawatcher_a = (DataWatcher.a) iterator.next();

            a(packetdataserializer, datawatcher_a);
        }

        this.f.readLock().unlock();
        packetdataserializer.writeByte(127);
    }

    public List<DataWatcher.a> c() {
        ArrayList arraylist = null;

        this.f.readLock().lock();

        DataWatcher.a datawatcher_a;

        for (Iterator iterator = this.d.values().iterator(); iterator.hasNext(); arraylist.add(datawatcher_a)) {
            datawatcher_a = (DataWatcher.a) iterator.next();
            if (arraylist == null) {
                arraylist = Lists.newArrayList();
            }
        }

        this.f.readLock().unlock();
        return arraylist;
    }

    private static void a(PacketDataSerializer packetdataserializer, DataWatcher.a datawatcher_a) throws IOException {
        int i = (datawatcher_a.c() << 5 | datawatcher_a.a() & 31) & 255;

        packetdataserializer.writeByte(i);
        switch (datawatcher_a.c()) {
        case 0:
            packetdataserializer.writeByte(((Byte) datawatcher_a.b()).byteValue());
            break;

        case 1:
            packetdataserializer.writeShort(((Short) datawatcher_a.b()).shortValue());
            break;

        case 2:
            packetdataserializer.writeInt(((Integer) datawatcher_a.b()).intValue());
            break;

        case 3:
            packetdataserializer.writeFloat(((Float) datawatcher_a.b()).floatValue());
            break;

        case 4:
            packetdataserializer.a((String) datawatcher_a.b());
            break;

        case 5:
            ItemStack itemstack = (ItemStack) datawatcher_a.b();

            packetdataserializer.a(itemstack);
            break;

        case 6:
            BlockPosition blockposition = (BlockPosition) datawatcher_a.b();

            packetdataserializer.writeInt(blockposition.getX());
            packetdataserializer.writeInt(blockposition.getY());
            packetdataserializer.writeInt(blockposition.getZ());
            break;

        case 7:
            Vector3f vector3f = (Vector3f) datawatcher_a.b();

            packetdataserializer.writeFloat(vector3f.getX());
            packetdataserializer.writeFloat(vector3f.getY());
            packetdataserializer.writeFloat(vector3f.getZ());
        }

    }

    public static List<DataWatcher.a> b(PacketDataSerializer packetdataserializer) throws IOException {
        ArrayList arraylist = null;

        for (byte b0 = packetdataserializer.readByte(); b0 != 127; b0 = packetdataserializer.readByte()) {
            if (arraylist == null) {
                arraylist = Lists.newArrayList();
            }

            int i = (b0 & 224) >> 5;
            int j = b0 & 31;
            DataWatcher.a datawatcher_a = null;

            switch (i) {
            case 0:
                datawatcher_a = new DataWatcher.a(i, j, Byte.valueOf(packetdataserializer.readByte()));
                break;

            case 1:
                datawatcher_a = new DataWatcher.a(i, j, Short.valueOf(packetdataserializer.readShort()));
                break;

            case 2:
                datawatcher_a = new DataWatcher.a(i, j, Integer.valueOf(packetdataserializer.readInt()));
                break;

            case 3:
                datawatcher_a = new DataWatcher.a(i, j, Float.valueOf(packetdataserializer.readFloat()));
                break;

            case 4:
                datawatcher_a = new DataWatcher.a(i, j, packetdataserializer.c(32767));
                break;

            case 5:
                datawatcher_a = new DataWatcher.a(i, j, packetdataserializer.i());
                break;

            case 6:
                int k = packetdataserializer.readInt();
                int l = packetdataserializer.readInt();
                int i1 = packetdataserializer.readInt();

                datawatcher_a = new DataWatcher.a(i, j, new BlockPosition(k, l, i1));
                break;

            case 7:
                float f = packetdataserializer.readFloat();
                float f1 = packetdataserializer.readFloat();
                float f2 = packetdataserializer.readFloat();

                datawatcher_a = new DataWatcher.a(i, j, new Vector3f(f, f1, f2));
            }

            arraylist.add(datawatcher_a);
        }

        return arraylist;
    }

    public boolean d() {
        return this.b;
    }

    public void e() {
        this.e = false;
    }

    static {
        DataWatcher.c.put(Byte.class, Integer.valueOf(0));
        DataWatcher.c.put(Short.class, Integer.valueOf(1));
        DataWatcher.c.put(Integer.class, Integer.valueOf(2));
        DataWatcher.c.put(Float.class, Integer.valueOf(3));
        DataWatcher.c.put(String.class, Integer.valueOf(4));
        DataWatcher.c.put(ItemStack.class, Integer.valueOf(5));
        DataWatcher.c.put(BlockPosition.class, Integer.valueOf(6));
        DataWatcher.c.put(Vector3f.class, Integer.valueOf(7));
    }

    public static class a {

        private final int a;
        private final int b;
        private Object c;
        private boolean d;

        public a(int i, int j, Object object) {
            this.b = j;
            this.c = object;
            this.a = i;
            this.d = true;
        }

        public int a() {
            return this.b;
        }

        public void a(Object object) {
            this.c = object;
        }

        public Object b() {
            return this.c;
        }

        public int c() {
            return this.a;
        }

        public boolean d() {
            return this.d;
        }

        public void a(boolean flag) {
            this.d = flag;
        }
    }
}
