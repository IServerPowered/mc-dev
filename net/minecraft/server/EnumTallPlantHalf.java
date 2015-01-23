package net.minecraft.server;

enum EnumTallPlantHalf implements INamable {

    UPPER, LOWER;

    public String toString() {
        return this.getName();
    }

    public String getName() {
        return this == EnumTallPlantHalf.UPPER ? "upper" : "lower";
    }
}
