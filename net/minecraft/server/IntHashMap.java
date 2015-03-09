package net.minecraft.server;

public class IntHashMap<V> {

    private transient IntHashMap.a<V>[] a = new IntHashMap.a[16];
    private transient int b;
    private int c = 12;
    private final float d = 0.75F;

    public IntHashMap() {}

    private static int g(int i) {
        i ^= i >>> 20 ^ i >>> 12;
        return i ^ i >>> 7 ^ i >>> 4;
    }

    private static int a(int i, int j) {
        return i & j - 1;
    }

    public V get(int i) {
        int j = g(i);

        for (IntHashMap.a inthashmap_a = this.a[a(j, this.a.length)]; inthashmap_a != null; inthashmap_a = inthashmap_a.c) {
            if (inthashmap_a.a == i) {
                return inthashmap_a.b;
            }
        }

        return null;
    }

    public boolean b(int i) {
        return this.c(i) != null;
    }

    final IntHashMap.a<V> c(int i) {
        int j = g(i);

        for (IntHashMap.a inthashmap_a = this.a[a(j, this.a.length)]; inthashmap_a != null; inthashmap_a = inthashmap_a.c) {
            if (inthashmap_a.a == i) {
                return inthashmap_a;
            }
        }

        return null;
    }

    public void a(int i, V v0) {
        int j = g(i);
        int k = a(j, this.a.length);

        for (IntHashMap.a inthashmap_a = this.a[k]; inthashmap_a != null; inthashmap_a = inthashmap_a.c) {
            if (inthashmap_a.a == i) {
                inthashmap_a.b = v0;
                return;
            }
        }

        this.a(j, i, v0, k);
    }

    private void h(int i) {
        IntHashMap.a[] ainthashmap_a = this.a;
        int j = ainthashmap_a.length;

        if (j == 1073741824) {
            this.c = Integer.MAX_VALUE;
        } else {
            IntHashMap.a[] ainthashmap_a1 = new IntHashMap.a[i];

            this.a(ainthashmap_a1);
            this.a = ainthashmap_a1;
            this.c = (int) ((float) i * this.d);
        }
    }

    private void a(IntHashMap.a<V>[] ainthashmap_a) {
        IntHashMap.a[] ainthashmap_a1 = this.a;
        int i = ainthashmap_a.length;

        for (int j = 0; j < ainthashmap_a1.length; ++j) {
            IntHashMap.a inthashmap_a = ainthashmap_a1[j];

            if (inthashmap_a != null) {
                ainthashmap_a1[j] = null;

                IntHashMap.a inthashmap_a1;

                do {
                    inthashmap_a1 = inthashmap_a.c;
                    int k = a(inthashmap_a.d, i);

                    inthashmap_a.c = ainthashmap_a[k];
                    ainthashmap_a[k] = inthashmap_a;
                    inthashmap_a = inthashmap_a1;
                } while (inthashmap_a1 != null);
            }
        }

    }

    public V d(int i) {
        IntHashMap.a inthashmap_a = this.e(i);

        return inthashmap_a == null ? null : inthashmap_a.b;
    }

    final IntHashMap.a<V> e(int i) {
        int j = g(i);
        int k = a(j, this.a.length);
        IntHashMap.a inthashmap_a = this.a[k];

        IntHashMap.a inthashmap_a1;
        IntHashMap.a inthashmap_a2;

        for (inthashmap_a1 = inthashmap_a; inthashmap_a1 != null; inthashmap_a1 = inthashmap_a2) {
            inthashmap_a2 = inthashmap_a1.c;
            if (inthashmap_a1.a == i) {
                --this.b;
                if (inthashmap_a == inthashmap_a1) {
                    this.a[k] = inthashmap_a2;
                } else {
                    inthashmap_a.c = inthashmap_a2;
                }

                return inthashmap_a1;
            }

            inthashmap_a = inthashmap_a1;
        }

        return inthashmap_a1;
    }

    public void c() {
        IntHashMap.a[] ainthashmap_a = this.a;

        for (int i = 0; i < ainthashmap_a.length; ++i) {
            ainthashmap_a[i] = null;
        }

        this.b = 0;
    }

    private void a(int i, int j, V v0, int k) {
        IntHashMap.a inthashmap_a = this.a[k];

        this.a[k] = new IntHashMap.a(i, j, v0, inthashmap_a);
        if (this.b++ >= this.c) {
            this.h(2 * this.a.length);
        }

    }

    static class a<V> {

        final int a;
        V b;
        IntHashMap.a<V> c;
        final int d;

        a(int i, int j, V v0, IntHashMap.a<V> inthashmap_a) {
            this.b = v0;
            this.c = inthashmap_a;
            this.a = j;
            this.d = i;
        }

        public final int a() {
            return this.a;
        }

        public final V b() {
            return this.b;
        }

        public final boolean equals(Object object) {
            if (!(object instanceof IntHashMap.a)) {
                return false;
            } else {
                IntHashMap.a inthashmap_a = (IntHashMap.a) object;
                Integer integer = Integer.valueOf(this.a());
                Integer integer1 = Integer.valueOf(inthashmap_a.a());

                if (integer == integer1 || integer != null && integer.equals(integer1)) {
                    Object object1 = this.b();
                    Object object2 = inthashmap_a.b();

                    if (object1 == object2 || object1 != null && object1.equals(object2)) {
                        return true;
                    }
                }

                return false;
            }
        }

        public final int hashCode() {
            return IntHashMap.g(this.a);
        }

        public final String toString() {
            return this.a() + "=" + this.b();
        }
    }
}
