package net.minecraft.server;

public enum EnumFlowerType {

    YELLOW, RED;

    public BlockFlowers a() {
        return this == EnumFlowerType.YELLOW ? Blocks.YELLOW_FLOWER : Blocks.RED_FLOWER;
    }
}
