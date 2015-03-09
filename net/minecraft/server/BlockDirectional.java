package net.minecraft.server;

import com.google.common.base.Predicate;

public abstract class BlockDirectional extends Block {

    public static final BlockStateDirection FACING = BlockStateDirection.of("facing", (Predicate) EnumDirection.c.HORIZONTAL);

    protected BlockDirectional(Material material) {
        super(material);
    }

    protected BlockDirectional(Material material, MaterialMapColor materialmapcolor) {
        super(material, materialmapcolor);
    }
}
