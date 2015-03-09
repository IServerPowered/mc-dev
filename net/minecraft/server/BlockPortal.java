package net.minecraft.server;

import com.google.common.cache.LoadingCache;
import java.util.Random;

public class BlockPortal extends BlockHalfTransparent {

    public static final BlockStateEnum<EnumDirection.a> AXIS = BlockStateEnum.of("axis", EnumDirection.a.class, new EnumDirection.a[] { EnumDirection.a.X, EnumDirection.a.Z});

    public BlockPortal() {
        super(Material.PORTAL, false);
        this.j(this.blockStateList.getBlockData().set(BlockPortal.AXIS, EnumDirection.a.X));
        this.a(true);
    }

    public void b(World world, BlockPosition blockposition, IBlockData iblockdata, Random random) {
        super.b(world, blockposition, iblockdata, random);
        if (world.worldProvider.d() && world.getGameRules().getBoolean("doMobSpawning") && random.nextInt(2000) < world.getDifficulty().a()) {
            int i = blockposition.getY();

            BlockPosition blockposition1;

            for (blockposition1 = blockposition; !World.a((IBlockAccess) world, blockposition1) && blockposition1.getY() > 0; blockposition1 = blockposition1.down()) {
                ;
            }

            if (i > 0 && !world.getType(blockposition1.up()).getBlock().isOccluding()) {
                Entity entity = ItemMonsterEgg.a(world, 57, (double) blockposition1.getX() + 0.5D, (double) blockposition1.getY() + 1.1D, (double) blockposition1.getZ() + 0.5D);

                if (entity != null) {
                    entity.portalCooldown = entity.aq();
                }
            }
        }

    }

    public AxisAlignedBB a(World world, BlockPosition blockposition, IBlockData iblockdata) {
        return null;
    }

    public void updateShape(IBlockAccess iblockaccess, BlockPosition blockposition) {
        EnumDirection.a enumdirection_a = (EnumDirection.a) iblockaccess.getType(blockposition).get(BlockPortal.AXIS);
        float f = 0.125F;
        float f1 = 0.125F;

        if (enumdirection_a == EnumDirection.a.X) {
            f = 0.5F;
        }

        if (enumdirection_a == EnumDirection.a.Z) {
            f1 = 0.5F;
        }

        this.a(0.5F - f, 0.0F, 0.5F - f1, 0.5F + f, 1.0F, 0.5F + f1);
    }

    public static int a(EnumDirection.a enumdirection_a) {
        return enumdirection_a == EnumDirection.a.X ? 1 : (enumdirection_a == EnumDirection.a.Z ? 2 : 0);
    }

    public boolean d() {
        return false;
    }

    public boolean e(World world, BlockPosition blockposition) {
        BlockPortal.a blockportal_a = new BlockPortal.a(world, blockposition, EnumDirection.a.X);

        if (blockportal_a.d() && blockportal_a.e == 0) {
            blockportal_a.e();
            return true;
        } else {
            BlockPortal.a blockportal_a1 = new BlockPortal.a(world, blockposition, EnumDirection.a.Z);

            if (blockportal_a1.d() && blockportal_a1.e == 0) {
                blockportal_a1.e();
                return true;
            } else {
                return false;
            }
        }
    }

    public void doPhysics(World world, BlockPosition blockposition, IBlockData iblockdata, Block block) {
        EnumDirection.a enumdirection_a = (EnumDirection.a) iblockdata.get(BlockPortal.AXIS);
        BlockPortal.a blockportal_a;

        if (enumdirection_a == EnumDirection.a.X) {
            blockportal_a = new BlockPortal.a(world, blockposition, EnumDirection.a.X);
            if (!blockportal_a.d() || blockportal_a.e < blockportal_a.h * blockportal_a.g) {
                world.setTypeUpdate(blockposition, Blocks.AIR.getBlockData());
            }
        } else if (enumdirection_a == EnumDirection.a.Z) {
            blockportal_a = new BlockPortal.a(world, blockposition, EnumDirection.a.Z);
            if (!blockportal_a.d() || blockportal_a.e < blockportal_a.h * blockportal_a.g) {
                world.setTypeUpdate(blockposition, Blocks.AIR.getBlockData());
            }
        }

    }

    public int a(Random random) {
        return 0;
    }

    public void a(World world, BlockPosition blockposition, IBlockData iblockdata, Entity entity) {
        if (entity.vehicle == null && entity.passenger == null) {
            entity.d(blockposition);
        }

    }

    public IBlockData fromLegacyData(int i) {
        return this.getBlockData().set(BlockPortal.AXIS, (i & 3) == 2 ? EnumDirection.a.Z : EnumDirection.a.X);
    }

    public int toLegacyData(IBlockData iblockdata) {
        return a((EnumDirection.a) iblockdata.get(BlockPortal.AXIS));
    }

    protected BlockStateList getStateList() {
        return new BlockStateList(this, new IBlockState[] { BlockPortal.AXIS});
    }

    public ShapeDetector.b f(World world, BlockPosition blockposition) {
        EnumDirection.a enumdirection_a = EnumDirection.a.Z;
        BlockPortal.a blockportal_a = new BlockPortal.a(world, blockposition, EnumDirection.a.X);
        LoadingCache loadingcache = ShapeDetector.a(world, true);

        if (!blockportal_a.d()) {
            enumdirection_a = EnumDirection.a.X;
            blockportal_a = new BlockPortal.a(world, blockposition, EnumDirection.a.Z);
        }

        if (!blockportal_a.d()) {
            return new ShapeDetector.b(blockposition, EnumDirection.NORTH, EnumDirection.UP, loadingcache, 1, 1, 1);
        } else {
            int[] aint = new int[EnumDirection.b.values().length];
            EnumDirection enumdirection = blockportal_a.c.f();
            BlockPosition blockposition1 = blockportal_a.f.up(blockportal_a.a() - 1);
            EnumDirection.b[] aenumdirection_b = EnumDirection.b.values();
            int i = aenumdirection_b.length;

            int j;

            for (j = 0; j < i; ++j) {
                EnumDirection.b enumdirection_b = aenumdirection_b[j];
                ShapeDetector.b shapedetector_b = new ShapeDetector.b(enumdirection.c() == enumdirection_b ? blockposition1 : blockposition1.shift(blockportal_a.c, blockportal_a.b() - 1), EnumDirection.a(enumdirection_b, enumdirection_a), EnumDirection.UP, loadingcache, blockportal_a.b(), blockportal_a.a(), 1);

                for (int k = 0; k < blockportal_a.b(); ++k) {
                    for (int l = 0; l < blockportal_a.a(); ++l) {
                        ShapeDetectorBlock shapedetectorblock = shapedetector_b.a(k, l, 1);

                        if (shapedetectorblock.a() != null && shapedetectorblock.a().getBlock().getMaterial() != Material.AIR) {
                            ++aint[enumdirection_b.ordinal()];
                        }
                    }
                }
            }

            EnumDirection.b enumdirection_b1 = EnumDirection.b.POSITIVE;
            EnumDirection.b[] aenumdirection_b1 = EnumDirection.b.values();

            j = aenumdirection_b1.length;

            for (int i1 = 0; i1 < j; ++i1) {
                EnumDirection.b enumdirection_b2 = aenumdirection_b1[i1];

                if (aint[enumdirection_b2.ordinal()] < aint[enumdirection_b1.ordinal()]) {
                    enumdirection_b1 = enumdirection_b2;
                }
            }

            return new ShapeDetector.b(enumdirection.c() == enumdirection_b1 ? blockposition1 : blockposition1.shift(blockportal_a.c, blockportal_a.b() - 1), EnumDirection.a(enumdirection_b1, enumdirection_a), EnumDirection.UP, loadingcache, blockportal_a.b(), blockportal_a.a(), 1);
        }
    }

    public static class a {

        private final World a;
        private final EnumDirection.a b;
        private final EnumDirection c;
        private final EnumDirection d;
        private int e = 0;
        private BlockPosition f;
        private int g;
        private int h;

        public a(World world, BlockPosition blockposition, EnumDirection.a enumdirection_a) {
            this.a = world;
            this.b = enumdirection_a;
            if (enumdirection_a == EnumDirection.a.X) {
                this.d = EnumDirection.EAST;
                this.c = EnumDirection.WEST;
            } else {
                this.d = EnumDirection.NORTH;
                this.c = EnumDirection.SOUTH;
            }

            for (BlockPosition blockposition1 = blockposition; blockposition.getY() > blockposition1.getY() - 21 && blockposition.getY() > 0 && this.a(world.getType(blockposition.down()).getBlock()); blockposition = blockposition.down()) {
                ;
            }

            int i = this.a(blockposition, this.d) - 1;

            if (i >= 0) {
                this.f = blockposition.shift(this.d, i);
                this.h = this.a(this.f, this.c);
                if (this.h < 2 || this.h > 21) {
                    this.f = null;
                    this.h = 0;
                }
            }

            if (this.f != null) {
                this.g = this.c();
            }

        }

        protected int a(BlockPosition blockposition, EnumDirection enumdirection) {
            int i;

            for (i = 0; i < 22; ++i) {
                BlockPosition blockposition1 = blockposition.shift(enumdirection, i);

                if (!this.a(this.a.getType(blockposition1).getBlock()) || this.a.getType(blockposition1.down()).getBlock() != Blocks.OBSIDIAN) {
                    break;
                }
            }

            Block block = this.a.getType(blockposition.shift(enumdirection, i)).getBlock();

            return block == Blocks.OBSIDIAN ? i : 0;
        }

        public int a() {
            return this.g;
        }

        public int b() {
            return this.h;
        }

        protected int c() {
            int i;

            label56:
            for (this.g = 0; this.g < 21; ++this.g) {
                for (i = 0; i < this.h; ++i) {
                    BlockPosition blockposition = this.f.shift(this.c, i).up(this.g);
                    Block block = this.a.getType(blockposition).getBlock();

                    if (!this.a(block)) {
                        break label56;
                    }

                    if (block == Blocks.PORTAL) {
                        ++this.e;
                    }

                    if (i == 0) {
                        block = this.a.getType(blockposition.shift(this.d)).getBlock();
                        if (block != Blocks.OBSIDIAN) {
                            break label56;
                        }
                    } else if (i == this.h - 1) {
                        block = this.a.getType(blockposition.shift(this.c)).getBlock();
                        if (block != Blocks.OBSIDIAN) {
                            break label56;
                        }
                    }
                }
            }

            for (i = 0; i < this.h; ++i) {
                if (this.a.getType(this.f.shift(this.c, i).up(this.g)).getBlock() != Blocks.OBSIDIAN) {
                    this.g = 0;
                    break;
                }
            }

            if (this.g <= 21 && this.g >= 3) {
                return this.g;
            } else {
                this.f = null;
                this.h = 0;
                this.g = 0;
                return 0;
            }
        }

        protected boolean a(Block block) {
            return block.material == Material.AIR || block == Blocks.FIRE || block == Blocks.PORTAL;
        }

        public boolean d() {
            return this.f != null && this.h >= 2 && this.h <= 21 && this.g >= 3 && this.g <= 21;
        }

        public void e() {
            for (int i = 0; i < this.h; ++i) {
                BlockPosition blockposition = this.f.shift(this.c, i);

                for (int j = 0; j < this.g; ++j) {
                    this.a.setTypeAndData(blockposition.up(j), Blocks.PORTAL.getBlockData().set(BlockPortal.AXIS, this.b), 2);
                }
            }

        }
    }
}
