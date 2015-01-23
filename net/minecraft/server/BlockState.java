package net.minecraft.server;

import com.google.common.base.Objects;

public abstract class BlockState implements IBlockState {

    private final Class a;
    private final String b;

    protected BlockState(String s, Class oclass) {
        this.a = oclass;
        this.b = s;
    }

    public String a() {
        return this.b;
    }

    public Class b() {
        return this.a;
    }

    public String toString() {
        return Objects.toStringHelper((Object) this).add("name", this.b).add("clazz", this.a).add("values", this.c()).toString();
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (object != null && this.getClass() == object.getClass()) {
            BlockState blockstate = (BlockState) object;

            return this.a.equals(blockstate.a) && this.b.equals(blockstate.b);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return 31 * this.a.hashCode() + this.b.hashCode();
    }
}
