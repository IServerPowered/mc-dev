package net.minecraft.server;

public enum EnumDoorHalf implements INamable {

    UPPER, LOWER;

    public String toString() {
        return this.getName();
    }

    public String getName() {
        return this == EnumDoorHalf.UPPER ? "upper" : "lower";
    }
}
