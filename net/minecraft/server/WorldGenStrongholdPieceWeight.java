package net.minecraft.server;

class WorldGenStrongholdPieceWeight {

    public Class a;
    public final int b;
    public int c;
    public int d;

    public WorldGenStrongholdPieceWeight(Class oclass, int i, int j) {
        this.a = oclass;
        this.b = i;
        this.d = j;
    }

    public boolean a(int i) {
        return this.d == 0 || this.c < this.d;
    }

    public boolean a() {
        return this.d == 0 || this.c < this.d;
    }
}
