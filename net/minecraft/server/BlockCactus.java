package net.minecraft.server;

import java.util.Iterator;
import java.util.Random;

public class BlockCactus extends Block {

    public static final BlockStateInteger AGE = BlockStateInteger.of("age", 0, 15);

    protected BlockCactus() {
        super(Material.CACTUS);
        this.j(this.blockStateList.getBlockData().set(BlockCactus.AGE, Integer.valueOf(0)));
        this.a(true);
        this.a(CreativeModeTab.c);
    }

    public void b(World world, BlockPosition blockposition, IBlockData iblockdata, Random random) {
        BlockPosition blockposition1 = blockposition.up();

        if (world.isEmpty(blockposition1)) {
            int i;

            for (i = 1; world.getType(blockposition.down(i)).getBlock() == this; ++i) {
                ;
            }

            if (i < 3) {
                int j = ((Integer) iblockdata.get(BlockCactus.AGE)).intValue();

                if (j == 15) {
                    world.setTypeUpdate(blockposition1, this.getBlockData());
                    IBlockData iblockdata1 = iblockdata.set(BlockCactus.AGE, Integer.valueOf(0));

                    world.setTypeAndData(blockposition, iblockdata1, 4);
                    this.doPhysics(world, blockposition1, iblockdata1, this);
                } else {
                    world.setTypeAndData(blockposition, iblockdata.set(BlockCactus.AGE, Integer.valueOf(j + 1)), 4);
                }

            }
        }
    }

    public AxisAlignedBB a(World world, BlockPosition blockposition, IBlockData iblockdata) {
        float f = 0.0625F;

        return new AxisAlignedBB((double) ((float) blockposition.getX() + f), (double) blockposition.getY(), (double) ((float) blockposition.getZ() + f), (double) ((float) (blockposition.getX() + 1) - f), (double) ((float) (blockposition.getY() + 1) - f), (double) ((float) (blockposition.getZ() + 1) - f));
    }

    public boolean d() {
        return false;
    }

    public boolean c() {
        return false;
    }

    public boolean canPlace(World world, BlockPosition blockposition) {
        return super.canPlace(world, blockposition) ? this.d(world, blockposition) : false;
    }

    public void doPhysics(World world, BlockPosition blockposition, IBlockData iblockdata, Block block) {
        if (!this.d(world, blockposition)) {
            world.setAir(blockposition, true);
        }

    }

    public boolean d(World world, BlockPosition blockposition) {
        Iterator iterator = EnumDirectionLimit.HORIZONTAL.iterator();

        while (iterator.hasNext()) {
            EnumDirection enumdirection = (EnumDirection) iterator.next();

            if (world.getType(blockposition.shift(enumdirection)).getBlock().getMaterial().isBuildable()) {
                return false;
            }
        }

        Block block = world.getType(blockposition.down()).getBlock();

        return block == Blocks.CACTUS || block == Blocks.SAND;
    }

    public void a(World world, BlockPosition blockposition, IBlockData iblockdata, Entity entity) {
        entity.damageEntity(DamageSource.CACTUS, 1.0F);
    }

    public IBlockData fromLegacyData(int i) {
        return this.getBlockData().set(BlockCactus.AGE, Integer.valueOf(i));
    }

    public int toLegacyData(IBlockData iblockdata) {
        return ((Integer) iblockdata.get(BlockCactus.AGE)).intValue();
    }

    protected BlockStateList getStateList() {
        return new BlockStateList(this, new IBlockState[] { BlockCactus.AGE});
    }
}
