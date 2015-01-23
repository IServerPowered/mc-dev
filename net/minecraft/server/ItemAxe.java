package net.minecraft.server;

import com.google.common.collect.Sets;
import java.util.Set;

public class ItemAxe extends ItemTool {

    private static final Set c = Sets.newHashSet((Object[]) (new Block[] { Blocks.PLANKS, Blocks.BOOKSHELF, Blocks.LOG, Blocks.LOG2, Blocks.CHEST, Blocks.PUMPKIN, Blocks.LIT_PUMPKIN, Blocks.MELON_BLOCK, Blocks.LADDER}));

    protected ItemAxe(EnumToolMaterial enumtoolmaterial) {
        super(3.0F, enumtoolmaterial, ItemAxe.c);
    }

    public float getDestroySpeed(ItemStack itemstack, Block block) {
        return block.getMaterial() != Material.WOOD && block.getMaterial() != Material.PLANT && block.getMaterial() != Material.REPLACEABLE_PLANT ? super.getDestroySpeed(itemstack, block) : this.a;
    }
}
