package net.minecraft.server;

public class LongHashMap<V> {

    private transient LongHashMap.a<V>[] entries = new LongHashMap.a[4096];
    private transient int count;
    private int c;
    private int d = 3072;
    private final float e = 0.75F;
    private transient volatile int f;

    public LongHashMap() {
        this.c = this.entries.length - 1;
    }

    private static int g(long i) {
        return a((int) (i ^ i >>> 32));
    }

    private static int a(int i) {
        i ^= i >>> 20 ^ i >>> 12;
        return i ^ i >>> 7 ^ i >>> 4;
    }

    private static int a(int i, int j) {
        return i & j;
    }

    public int count() {
        return this.count;
    }

    public V getEntry(long i) {
        int j = g(i);

        for (LongHashMap.a longhashmap_a = this.entries[a(j, this.c)]; longhashmap_a != null; longhashmap_a = longhashmap_a.c) {
            if (longhashmap_a.a == i) {
                return longhashmap_a.b;
            }
        }

        return null;
    }

    public boolean contains(long i) {
        return this.c(i) != null;
    }

    final LongHashMap.a<V> c(long i) {
        int j = g(i);

        for (LongHashMap.a longhashmap_a = this.entries[a(j, this.c)]; longhashmap_a != null; longhashmap_a = longhashmap_a.c) {
            if (longhashmap_a.a == i) {
                return longhashmap_a;
            }
        }

        return null;
    }

    public void put(long i, V v0) {
        int j = g(i);
        int k = a(j, this.c);

        for (LongHashMap.a longhashmap_a = this.entries[k]; longhashmap_a != null; longhashmap_a = longhashmap_a.c) {
            if (longhashmap_a.a == i) {
                longhashmap_a.b = v0;
                return;
            }
        }

        ++this.f;
        this.a(j, i, v0, k);
    }

    private void b(int i) {
        LongHashMap.a[] alonghashmap_a = this.entries;
        int j = alonghashmap_a.length;

        if (j == 1073741824) {
            this.d = Integer.MAX_VALUE;
        } else {
            LongHashMap.a[] alonghashmap_a1 = new LongHashMap.a[i];

            this.a(alonghashmap_a1);
            this.entries = alonghashmap_a1;
            this.c = this.entries.length - 1;
            this.d = (int) ((float) i * this.e);
        }
    }

    private void a(LongHashMap.a<V>[] alonghashmap_a) {
        LongHashMap.a[] alonghashmap_a1 = this.entries;
        int i = alonghashmap_a.length;

        for (int j = 0; j < alonghashmap_a1.length; ++j) {
            LongHashMap.a longhashmap_a = alonghashmap_a1[j];

            if (longhashmap_a != null) {
                alonghashmap_a1[j] = null;

                LongHashMap.a longhashmap_a1;

                do {
                    longhashmap_a1 = longhashmap_a.c;
                    int k = a(longhashmap_a.d, i - 1);

                    longhashmap_a.c = alonghashmap_a[k];
                    alonghashmap_a[k] = longhashmap_a;
                    longhashmap_a = longhashmap_a1;
                } while (longhashmap_a1 != null);
            }
        }

    }

    public V remove(long i) {
        LongHashMap.a longhashmap_a = this.e(i);

        return longhashmap_a == null ? null : longhashmap_a.b;
    }

    final LongHashMap.a<V> e(long i) {
        int j = g(i);
        int k = a(j, this.c);
        LongHashMap.a longhashmap_a = this.entries[k];

        LongHashMap.a longhashmap_a1;
        LongHashMap.a longhashmap_a2;

        for (longhashmap_a1 = longhashmap_a; longhashmap_a1 != null; longhashmap_a1 = longhashmap_a2) {
            longhashmap_a2 = longhashmap_a1.c;
            if (longhashmap_a1.a == i) {
                ++this.f;
                --this.count;
                if (longhashmap_a == longhashmap_a1) {
                    this.entries[k] = longhashmap_a2;
                } else {
                    longhashmap_a.c = longhashmap_a2;
                }

                return longhashmap_a1;
            }

            longhashmap_a = longhashmap_a1;
        }

        return longhashmap_a1;
    }

    private void a(int i, long j, V v0, int k) {
        LongHashMap.a longhashmap_a = this.entries[k];

        this.entries[k] = new LongHashMap.a(i, j, v0, longhashmap_a);
        if (this.count++ >= this.d) {
            this.b(2 * this.entries.length);
        }

    }

    static class a<V> {

        final long a;
        V b;
        LongHashMap.a<V> c;
        final int d;

        a(int i, long j, V v0, LongHashMap.a<V> longhashmap_a) {
            this.b = v0;
            this.c = longhashmap_a;
            this.a = j;
            this.d = i;
        }

        public final long a() {
            return this.a;
        }

        public final V b() {
            return this.b;
        }

        public final boolean equals(Object object) {
            if (!(object instanceof LongHashMap.a)) {
                return false;
            } else {
                LongHashMap.a longhashmap_a = (LongHashMap.a) object;
                Long olong = Long.valueOf(this.a());
                Long olong1 = Long.valueOf(longhashmap_a.a());

                if (olong == olong1 || olong != null && olong.equals(olong1)) {
                    Object object1 = this.b();
                    Object object2 = longhashmap_a.b();

                    if (object1 == object2 || object1 != null && object1.equals(object2)) {
                        return true;
                    }
                }

                return false;
            }
        }

        public final int hashCode() {
            return LongHashMap.g(this.a);
        }

        public final String toString() {
            return this.a() + "=" + this.b();
        }
    }
}
