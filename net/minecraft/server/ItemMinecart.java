package net.minecraft.server;

public class ItemMinecart extends Item {

    private static final IDispenseBehavior a = new DispenseBehaviorItem() {
        private final DispenseBehaviorItem b = new DispenseBehaviorItem();

        public ItemStack b(ISourceBlock isourceblock, ItemStack itemstack) {
            EnumDirection enumdirection = BlockDispenser.b(isourceblock.f());
            World world = isourceblock.i();
            double d0 = isourceblock.getX() + (double) enumdirection.getAdjacentX() * 1.125D;
            double d1 = Math.floor(isourceblock.getY()) + (double) enumdirection.getAdjacentY();
            double d2 = isourceblock.getZ() + (double) enumdirection.getAdjacentZ() * 1.125D;
            BlockPosition blockposition = isourceblock.getBlockPosition().shift(enumdirection);
            IBlockData iblockdata = world.getType(blockposition);
            BlockMinecartTrackAbstract.b blockminecarttrackabstract_b = iblockdata.getBlock() instanceof BlockMinecartTrackAbstract ? (BlockMinecartTrackAbstract.b) iblockdata.get(((BlockMinecartTrackAbstract) iblockdata.getBlock()).n()) : BlockMinecartTrackAbstract.b.NORTH_SOUTH;
            double d3;

            if (BlockMinecartTrackAbstract.d(iblockdata)) {
                if (blockminecarttrackabstract_b.c()) {
                    d3 = 0.6D;
                } else {
                    d3 = 0.1D;
                }
            } else {
                if (iblockdata.getBlock().getMaterial() != Material.AIR || !BlockMinecartTrackAbstract.d(world.getType(blockposition.down()))) {
                    return this.b.a(isourceblock, itemstack);
                }

                IBlockData iblockdata1 = world.getType(blockposition.down());
                BlockMinecartTrackAbstract.b blockminecarttrackabstract_b1 = iblockdata1.getBlock() instanceof BlockMinecartTrackAbstract ? (BlockMinecartTrackAbstract.b) iblockdata1.get(((BlockMinecartTrackAbstract) iblockdata1.getBlock()).n()) : BlockMinecartTrackAbstract.b.NORTH_SOUTH;

                if (enumdirection != EnumDirection.DOWN && blockminecarttrackabstract_b1.c()) {
                    d3 = -0.4D;
                } else {
                    d3 = -0.9D;
                }
            }

            EntityMinecartAbstract entityminecartabstract = EntityMinecartAbstract.a(world, d0, d1 + d3, d2, ((ItemMinecart) itemstack.getItem()).b);

            if (itemstack.hasName()) {
                entityminecartabstract.setCustomName(itemstack.getName());
            }

            world.addEntity(entityminecartabstract);
            itemstack.a(1);
            return itemstack;
        }

        protected void a(ISourceBlock isourceblock) {
            isourceblock.i().triggerEffect(1000, isourceblock.getBlockPosition(), 0);
        }
    };
    private final EntityMinecartAbstract.a b;

    public ItemMinecart(EntityMinecartAbstract.a entityminecartabstract_a) {
        this.maxStackSize = 1;
        this.b = entityminecartabstract_a;
        this.a(CreativeModeTab.e);
        BlockDispenser.N.a(this, ItemMinecart.a);
    }

    public boolean interactWith(ItemStack itemstack, EntityHuman entityhuman, World world, BlockPosition blockposition, EnumDirection enumdirection, float f, float f1, float f2) {
        IBlockData iblockdata = world.getType(blockposition);

        if (BlockMinecartTrackAbstract.d(iblockdata)) {
            if (!world.isClientSide) {
                BlockMinecartTrackAbstract.b blockminecarttrackabstract_b = iblockdata.getBlock() instanceof BlockMinecartTrackAbstract ? (BlockMinecartTrackAbstract.b) iblockdata.get(((BlockMinecartTrackAbstract) iblockdata.getBlock()).n()) : BlockMinecartTrackAbstract.b.NORTH_SOUTH;
                double d0 = 0.0D;

                if (blockminecarttrackabstract_b.c()) {
                    d0 = 0.5D;
                }

                EntityMinecartAbstract entityminecartabstract = EntityMinecartAbstract.a(world, (double) blockposition.getX() + 0.5D, (double) blockposition.getY() + 0.0625D + d0, (double) blockposition.getZ() + 0.5D, this.b);

                if (itemstack.hasName()) {
                    entityminecartabstract.setCustomName(itemstack.getName());
                }

                world.addEntity(entityminecartabstract);
            }

            --itemstack.count;
            return true;
        } else {
            return false;
        }
    }
}
