package net.minecraft.server;

public abstract class BlockRotatable extends Block {

    public static final BlockStateEnum<EnumDirection.a> AXIS = BlockStateEnum.of("axis", EnumDirection.a.class);

    protected BlockRotatable(Material material) {
        super(material, material.r());
    }

    protected BlockRotatable(Material material, MaterialMapColor materialmapcolor) {
        super(material, materialmapcolor);
    }
}
