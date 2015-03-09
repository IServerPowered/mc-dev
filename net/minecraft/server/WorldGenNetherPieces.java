package net.minecraft.server;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class WorldGenNetherPieces {

    private static final WorldGenNetherPieces.n[] a = new WorldGenNetherPieces.n[] { new WorldGenNetherPieces.n(WorldGenNetherPieces.c.class, 30, 0, true), new WorldGenNetherPieces.n(WorldGenNetherPieces.a.class, 10, 4), new WorldGenNetherPieces.n(WorldGenNetherPieces.o.class, 10, 4), new WorldGenNetherPieces.n(WorldGenNetherPieces.p.class, 10, 3), new WorldGenNetherPieces.n(WorldGenNetherPieces.l.class, 5, 2), new WorldGenNetherPieces.n(WorldGenNetherPieces.f.class, 5, 1)};
    private static final WorldGenNetherPieces.n[] b = new WorldGenNetherPieces.n[] { new WorldGenNetherPieces.n(WorldGenNetherPieces.i.class, 25, 0, true), new WorldGenNetherPieces.n(WorldGenNetherPieces.g.class, 15, 5), new WorldGenNetherPieces.n(WorldGenNetherPieces.j.class, 5, 10), new WorldGenNetherPieces.n(WorldGenNetherPieces.h.class, 5, 10), new WorldGenNetherPieces.n(WorldGenNetherPieces.d.class, 10, 3, true), new WorldGenNetherPieces.n(WorldGenNetherPieces.e.class, 7, 2), new WorldGenNetherPieces.n(WorldGenNetherPieces.k.class, 5, 2)};

    public static void a() {
        WorldGenFactory.a(WorldGenNetherPieces.a.class, "NeBCr");
        WorldGenFactory.a(WorldGenNetherPieces.b.class, "NeBEF");
        WorldGenFactory.a(WorldGenNetherPieces.c.class, "NeBS");
        WorldGenFactory.a(WorldGenNetherPieces.d.class, "NeCCS");
        WorldGenFactory.a(WorldGenNetherPieces.e.class, "NeCTB");
        WorldGenFactory.a(WorldGenNetherPieces.f.class, "NeCE");
        WorldGenFactory.a(WorldGenNetherPieces.g.class, "NeSCSC");
        WorldGenFactory.a(WorldGenNetherPieces.h.class, "NeSCLT");
        WorldGenFactory.a(WorldGenNetherPieces.i.class, "NeSC");
        WorldGenFactory.a(WorldGenNetherPieces.j.class, "NeSCRT");
        WorldGenFactory.a(WorldGenNetherPieces.k.class, "NeCSR");
        WorldGenFactory.a(WorldGenNetherPieces.l.class, "NeMT");
        WorldGenFactory.a(WorldGenNetherPieces.o.class, "NeRC");
        WorldGenFactory.a(WorldGenNetherPieces.p.class, "NeSR");
        WorldGenFactory.a(WorldGenNetherPieces.q.class, "NeStart");
    }

    private static WorldGenNetherPieces.m b(WorldGenNetherPieces.n worldgennetherpieces_n, List<StructurePiece> list, Random random, int i, int j, int k, EnumDirection enumdirection, int l) {
        Class oclass = worldgennetherpieces_n.a;
        Object object = null;

        if (oclass == WorldGenNetherPieces.c.class) {
            object = WorldGenNetherPieces.c.a(list, random, i, j, k, enumdirection, l);
        } else if (oclass == WorldGenNetherPieces.a.class) {
            object = WorldGenNetherPieces.a.a(list, random, i, j, k, enumdirection, l);
        } else if (oclass == WorldGenNetherPieces.o.class) {
            object = WorldGenNetherPieces.o.a(list, random, i, j, k, enumdirection, l);
        } else if (oclass == WorldGenNetherPieces.p.class) {
            object = WorldGenNetherPieces.p.a(list, random, i, j, k, l, enumdirection);
        } else if (oclass == WorldGenNetherPieces.l.class) {
            object = WorldGenNetherPieces.l.a(list, random, i, j, k, l, enumdirection);
        } else if (oclass == WorldGenNetherPieces.f.class) {
            object = WorldGenNetherPieces.f.a(list, random, i, j, k, enumdirection, l);
        } else if (oclass == WorldGenNetherPieces.i.class) {
            object = WorldGenNetherPieces.i.a(list, random, i, j, k, enumdirection, l);
        } else if (oclass == WorldGenNetherPieces.j.class) {
            object = WorldGenNetherPieces.j.a(list, random, i, j, k, enumdirection, l);
        } else if (oclass == WorldGenNetherPieces.h.class) {
            object = WorldGenNetherPieces.h.a(list, random, i, j, k, enumdirection, l);
        } else if (oclass == WorldGenNetherPieces.d.class) {
            object = WorldGenNetherPieces.d.a(list, random, i, j, k, enumdirection, l);
        } else if (oclass == WorldGenNetherPieces.e.class) {
            object = WorldGenNetherPieces.e.a(list, random, i, j, k, enumdirection, l);
        } else if (oclass == WorldGenNetherPieces.g.class) {
            object = WorldGenNetherPieces.g.a(list, random, i, j, k, enumdirection, l);
        } else if (oclass == WorldGenNetherPieces.k.class) {
            object = WorldGenNetherPieces.k.a(list, random, i, j, k, enumdirection, l);
        }

        return (WorldGenNetherPieces.m) object;
    }

    static class SyntheticClass_1 {

        static final int[] a = new int[EnumDirection.values().length];

        static {
            try {
                WorldGenNetherPieces.SyntheticClass_1.a[EnumDirection.NORTH.ordinal()] = 1;
            } catch (NoSuchFieldError nosuchfielderror) {
                ;
            }

            try {
                WorldGenNetherPieces.SyntheticClass_1.a[EnumDirection.SOUTH.ordinal()] = 2;
            } catch (NoSuchFieldError nosuchfielderror1) {
                ;
            }

            try {
                WorldGenNetherPieces.SyntheticClass_1.a[EnumDirection.WEST.ordinal()] = 3;
            } catch (NoSuchFieldError nosuchfielderror2) {
                ;
            }

            try {
                WorldGenNetherPieces.SyntheticClass_1.a[EnumDirection.EAST.ordinal()] = 4;
            } catch (NoSuchFieldError nosuchfielderror3) {
                ;
            }

        }
    }

    public static class e extends WorldGenNetherPieces.m {

        public e() {}

        public e(int i, Random random, StructureBoundingBox structureboundingbox, EnumDirection enumdirection) {
            super(i);
            this.m = enumdirection;
            this.l = structureboundingbox;
        }

        public void a(StructurePiece structurepiece, List<StructurePiece> list, Random random) {
            byte b0 = 1;

            if (this.m == EnumDirection.WEST || this.m == EnumDirection.NORTH) {
                b0 = 5;
            }

            this.b((WorldGenNetherPieces.q) structurepiece, list, random, 0, b0, random.nextInt(8) > 0);
            this.c((WorldGenNetherPieces.q) structurepiece, list, random, 0, b0, random.nextInt(8) > 0);
        }

        public static WorldGenNetherPieces.e a(List<StructurePiece> list, Random random, int i, int j, int k, EnumDirection enumdirection, int l) {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -3, 0, 0, 9, 7, 9, enumdirection);

            return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenNetherPieces.e(l, random, structureboundingbox, enumdirection) : null;
        }

        public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
            this.a(world, structureboundingbox, 0, 0, 0, 8, 1, 8, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 0, 2, 0, 8, 5, 8, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
            this.a(world, structureboundingbox, 0, 6, 0, 8, 6, 5, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 0, 2, 0, 2, 5, 0, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 6, 2, 0, 8, 5, 0, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 1, 3, 0, 1, 4, 0, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK_FENCE.getBlockData(), false);
            this.a(world, structureboundingbox, 7, 3, 0, 7, 4, 0, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK_FENCE.getBlockData(), false);
            this.a(world, structureboundingbox, 0, 2, 4, 8, 2, 8, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 1, 1, 4, 2, 2, 4, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
            this.a(world, structureboundingbox, 6, 1, 4, 7, 2, 4, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
            this.a(world, structureboundingbox, 0, 3, 8, 8, 3, 8, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK_FENCE.getBlockData(), false);
            this.a(world, structureboundingbox, 0, 3, 6, 0, 3, 7, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK_FENCE.getBlockData(), false);
            this.a(world, structureboundingbox, 8, 3, 6, 8, 3, 7, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK_FENCE.getBlockData(), false);
            this.a(world, structureboundingbox, 0, 3, 4, 0, 5, 5, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 8, 3, 4, 8, 5, 5, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 1, 3, 5, 2, 5, 5, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 6, 3, 5, 7, 5, 5, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 1, 4, 5, 1, 5, 5, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK_FENCE.getBlockData(), false);
            this.a(world, structureboundingbox, 7, 4, 5, 7, 5, 5, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK_FENCE.getBlockData(), false);

            for (int i = 0; i <= 5; ++i) {
                for (int j = 0; j <= 8; ++j) {
                    this.b(world, Blocks.NETHER_BRICK.getBlockData(), j, -1, i, structureboundingbox);
                }
            }

            return true;
        }
    }

    public static class d extends WorldGenNetherPieces.m {

        public d() {}

        public d(int i, Random random, StructureBoundingBox structureboundingbox, EnumDirection enumdirection) {
            super(i);
            this.m = enumdirection;
            this.l = structureboundingbox;
        }

        public void a(StructurePiece structurepiece, List<StructurePiece> list, Random random) {
            this.a((WorldGenNetherPieces.q) structurepiece, list, random, 1, 0, true);
        }

        public static WorldGenNetherPieces.d a(List<StructurePiece> list, Random random, int i, int j, int k, EnumDirection enumdirection, int l) {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -1, -7, 0, 5, 14, 10, enumdirection);

            return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenNetherPieces.d(l, random, structureboundingbox, enumdirection) : null;
        }

        public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
            int i = this.a(Blocks.NETHER_BRICK_STAIRS, 2);

            for (int j = 0; j <= 9; ++j) {
                int k = Math.max(1, 7 - j);
                int l = Math.min(Math.max(k + 5, 14 - j), 13);
                int i1 = j;

                this.a(world, structureboundingbox, 0, 0, j, 4, k, j, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
                this.a(world, structureboundingbox, 1, k + 1, j, 3, l - 1, j, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
                if (j <= 6) {
                    this.a(world, Blocks.NETHER_BRICK_STAIRS.fromLegacyData(i), 1, k + 1, j, structureboundingbox);
                    this.a(world, Blocks.NETHER_BRICK_STAIRS.fromLegacyData(i), 2, k + 1, j, structureboundingbox);
                    this.a(world, Blocks.NETHER_BRICK_STAIRS.fromLegacyData(i), 3, k + 1, j, structureboundingbox);
                }

                this.a(world, structureboundingbox, 0, l, j, 4, l, j, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
                this.a(world, structureboundingbox, 0, k + 1, j, 0, l - 1, j, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
                this.a(world, structureboundingbox, 4, k + 1, j, 4, l - 1, j, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
                if ((j & 1) == 0) {
                    this.a(world, structureboundingbox, 0, k + 2, j, 0, k + 3, j, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK_FENCE.getBlockData(), false);
                    this.a(world, structureboundingbox, 4, k + 2, j, 4, k + 3, j, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK_FENCE.getBlockData(), false);
                }

                for (int j1 = 0; j1 <= 4; ++j1) {
                    this.b(world, Blocks.NETHER_BRICK.getBlockData(), j1, -1, i1, structureboundingbox);
                }
            }

            return true;
        }
    }

    public static class h extends WorldGenNetherPieces.m {

        private boolean b;

        public h() {}

        public h(int i, Random random, StructureBoundingBox structureboundingbox, EnumDirection enumdirection) {
            super(i);
            this.m = enumdirection;
            this.l = structureboundingbox;
            this.b = random.nextInt(3) == 0;
        }

        protected void b(NBTTagCompound nbttagcompound) {
            super.b(nbttagcompound);
            this.b = nbttagcompound.getBoolean("Chest");
        }

        protected void a(NBTTagCompound nbttagcompound) {
            super.a(nbttagcompound);
            nbttagcompound.setBoolean("Chest", this.b);
        }

        public void a(StructurePiece structurepiece, List<StructurePiece> list, Random random) {
            this.b((WorldGenNetherPieces.q) structurepiece, list, random, 0, 1, true);
        }

        public static WorldGenNetherPieces.h a(List<StructurePiece> list, Random random, int i, int j, int k, EnumDirection enumdirection, int l) {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -1, 0, 0, 5, 7, 5, enumdirection);

            return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenNetherPieces.h(l, random, structureboundingbox, enumdirection) : null;
        }

        public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
            this.a(world, structureboundingbox, 0, 0, 0, 4, 1, 4, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 0, 2, 0, 4, 5, 4, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
            this.a(world, structureboundingbox, 4, 2, 0, 4, 5, 4, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 4, 3, 1, 4, 4, 1, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK_FENCE.getBlockData(), false);
            this.a(world, structureboundingbox, 4, 3, 3, 4, 4, 3, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK_FENCE.getBlockData(), false);
            this.a(world, structureboundingbox, 0, 2, 0, 0, 5, 0, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 0, 2, 4, 3, 5, 4, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 1, 3, 4, 1, 4, 4, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 3, 3, 4, 3, 4, 4, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            if (this.b && structureboundingbox.b((BaseBlockPosition) (new BlockPosition(this.a(3, 3), this.d(2), this.b(3, 3))))) {
                this.b = false;
                this.a(world, structureboundingbox, random, 3, 2, 3, WorldGenNetherPieces.h.a, 2 + random.nextInt(4));
            }

            this.a(world, structureboundingbox, 0, 6, 0, 4, 6, 4, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);

            for (int i = 0; i <= 4; ++i) {
                for (int j = 0; j <= 4; ++j) {
                    this.b(world, Blocks.NETHER_BRICK.getBlockData(), i, -1, j, structureboundingbox);
                }
            }

            return true;
        }
    }

    public static class j extends WorldGenNetherPieces.m {

        private boolean b;

        public j() {}

        public j(int i, Random random, StructureBoundingBox structureboundingbox, EnumDirection enumdirection) {
            super(i);
            this.m = enumdirection;
            this.l = structureboundingbox;
            this.b = random.nextInt(3) == 0;
        }

        protected void b(NBTTagCompound nbttagcompound) {
            super.b(nbttagcompound);
            this.b = nbttagcompound.getBoolean("Chest");
        }

        protected void a(NBTTagCompound nbttagcompound) {
            super.a(nbttagcompound);
            nbttagcompound.setBoolean("Chest", this.b);
        }

        public void a(StructurePiece structurepiece, List<StructurePiece> list, Random random) {
            this.c((WorldGenNetherPieces.q) structurepiece, list, random, 0, 1, true);
        }

        public static WorldGenNetherPieces.j a(List<StructurePiece> list, Random random, int i, int j, int k, EnumDirection enumdirection, int l) {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -1, 0, 0, 5, 7, 5, enumdirection);

            return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenNetherPieces.j(l, random, structureboundingbox, enumdirection) : null;
        }

        public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
            this.a(world, structureboundingbox, 0, 0, 0, 4, 1, 4, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 0, 2, 0, 4, 5, 4, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
            this.a(world, structureboundingbox, 0, 2, 0, 0, 5, 4, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 0, 3, 1, 0, 4, 1, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK_FENCE.getBlockData(), false);
            this.a(world, structureboundingbox, 0, 3, 3, 0, 4, 3, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK_FENCE.getBlockData(), false);
            this.a(world, structureboundingbox, 4, 2, 0, 4, 5, 0, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 1, 2, 4, 4, 5, 4, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 1, 3, 4, 1, 4, 4, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 3, 3, 4, 3, 4, 4, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            if (this.b && structureboundingbox.b((BaseBlockPosition) (new BlockPosition(this.a(1, 3), this.d(2), this.b(1, 3))))) {
                this.b = false;
                this.a(world, structureboundingbox, random, 1, 2, 3, WorldGenNetherPieces.j.a, 2 + random.nextInt(4));
            }

            this.a(world, structureboundingbox, 0, 6, 0, 4, 6, 4, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);

            for (int i = 0; i <= 4; ++i) {
                for (int j = 0; j <= 4; ++j) {
                    this.b(world, Blocks.NETHER_BRICK.getBlockData(), i, -1, j, structureboundingbox);
                }
            }

            return true;
        }
    }

    public static class g extends WorldGenNetherPieces.m {

        public g() {}

        public g(int i, Random random, StructureBoundingBox structureboundingbox, EnumDirection enumdirection) {
            super(i);
            this.m = enumdirection;
            this.l = structureboundingbox;
        }

        public void a(StructurePiece structurepiece, List<StructurePiece> list, Random random) {
            this.a((WorldGenNetherPieces.q) structurepiece, list, random, 1, 0, true);
            this.b((WorldGenNetherPieces.q) structurepiece, list, random, 0, 1, true);
            this.c((WorldGenNetherPieces.q) structurepiece, list, random, 0, 1, true);
        }

        public static WorldGenNetherPieces.g a(List<StructurePiece> list, Random random, int i, int j, int k, EnumDirection enumdirection, int l) {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -1, 0, 0, 5, 7, 5, enumdirection);

            return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenNetherPieces.g(l, random, structureboundingbox, enumdirection) : null;
        }

        public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
            this.a(world, structureboundingbox, 0, 0, 0, 4, 1, 4, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 0, 2, 0, 4, 5, 4, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
            this.a(world, structureboundingbox, 0, 2, 0, 0, 5, 0, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 4, 2, 0, 4, 5, 0, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 0, 2, 4, 0, 5, 4, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 4, 2, 4, 4, 5, 4, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 0, 6, 0, 4, 6, 4, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);

            for (int i = 0; i <= 4; ++i) {
                for (int j = 0; j <= 4; ++j) {
                    this.b(world, Blocks.NETHER_BRICK.getBlockData(), i, -1, j, structureboundingbox);
                }
            }

            return true;
        }
    }

    public static class i extends WorldGenNetherPieces.m {

        public i() {}

        public i(int i, Random random, StructureBoundingBox structureboundingbox, EnumDirection enumdirection) {
            super(i);
            this.m = enumdirection;
            this.l = structureboundingbox;
        }

        public void a(StructurePiece structurepiece, List<StructurePiece> list, Random random) {
            this.a((WorldGenNetherPieces.q) structurepiece, list, random, 1, 0, true);
        }

        public static WorldGenNetherPieces.i a(List<StructurePiece> list, Random random, int i, int j, int k, EnumDirection enumdirection, int l) {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -1, 0, 0, 5, 7, 5, enumdirection);

            return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenNetherPieces.i(l, random, structureboundingbox, enumdirection) : null;
        }

        public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
            this.a(world, structureboundingbox, 0, 0, 0, 4, 1, 4, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 0, 2, 0, 4, 5, 4, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
            this.a(world, structureboundingbox, 0, 2, 0, 0, 5, 4, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 4, 2, 0, 4, 5, 4, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 0, 3, 1, 0, 4, 1, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK_FENCE.getBlockData(), false);
            this.a(world, structureboundingbox, 0, 3, 3, 0, 4, 3, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK_FENCE.getBlockData(), false);
            this.a(world, structureboundingbox, 4, 3, 1, 4, 4, 1, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK_FENCE.getBlockData(), false);
            this.a(world, structureboundingbox, 4, 3, 3, 4, 4, 3, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK_FENCE.getBlockData(), false);
            this.a(world, structureboundingbox, 0, 6, 0, 4, 6, 4, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);

            for (int i = 0; i <= 4; ++i) {
                for (int j = 0; j <= 4; ++j) {
                    this.b(world, Blocks.NETHER_BRICK.getBlockData(), i, -1, j, structureboundingbox);
                }
            }

            return true;
        }
    }

    public static class k extends WorldGenNetherPieces.m {

        public k() {}

        public k(int i, Random random, StructureBoundingBox structureboundingbox, EnumDirection enumdirection) {
            super(i);
            this.m = enumdirection;
            this.l = structureboundingbox;
        }

        public void a(StructurePiece structurepiece, List<StructurePiece> list, Random random) {
            this.a((WorldGenNetherPieces.q) structurepiece, list, random, 5, 3, true);
            this.a((WorldGenNetherPieces.q) structurepiece, list, random, 5, 11, true);
        }

        public static WorldGenNetherPieces.k a(List<StructurePiece> list, Random random, int i, int j, int k, EnumDirection enumdirection, int l) {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -5, -3, 0, 13, 14, 13, enumdirection);

            return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenNetherPieces.k(l, random, structureboundingbox, enumdirection) : null;
        }

        public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
            this.a(world, structureboundingbox, 0, 3, 0, 12, 4, 12, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 0, 5, 0, 12, 13, 12, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
            this.a(world, structureboundingbox, 0, 5, 0, 1, 12, 12, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 11, 5, 0, 12, 12, 12, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 2, 5, 11, 4, 12, 12, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 8, 5, 11, 10, 12, 12, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 5, 9, 11, 7, 12, 12, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 2, 5, 0, 4, 12, 1, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 8, 5, 0, 10, 12, 1, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 5, 9, 0, 7, 12, 1, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 2, 11, 2, 10, 12, 10, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);

            int i;

            for (i = 1; i <= 11; i += 2) {
                this.a(world, structureboundingbox, i, 10, 0, i, 11, 0, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK_FENCE.getBlockData(), false);
                this.a(world, structureboundingbox, i, 10, 12, i, 11, 12, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK_FENCE.getBlockData(), false);
                this.a(world, structureboundingbox, 0, 10, i, 0, 11, i, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK_FENCE.getBlockData(), false);
                this.a(world, structureboundingbox, 12, 10, i, 12, 11, i, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK_FENCE.getBlockData(), false);
                this.a(world, Blocks.NETHER_BRICK.getBlockData(), i, 13, 0, structureboundingbox);
                this.a(world, Blocks.NETHER_BRICK.getBlockData(), i, 13, 12, structureboundingbox);
                this.a(world, Blocks.NETHER_BRICK.getBlockData(), 0, 13, i, structureboundingbox);
                this.a(world, Blocks.NETHER_BRICK.getBlockData(), 12, 13, i, structureboundingbox);
                this.a(world, Blocks.NETHER_BRICK_FENCE.getBlockData(), i + 1, 13, 0, structureboundingbox);
                this.a(world, Blocks.NETHER_BRICK_FENCE.getBlockData(), i + 1, 13, 12, structureboundingbox);
                this.a(world, Blocks.NETHER_BRICK_FENCE.getBlockData(), 0, 13, i + 1, structureboundingbox);
                this.a(world, Blocks.NETHER_BRICK_FENCE.getBlockData(), 12, 13, i + 1, structureboundingbox);
            }

            this.a(world, Blocks.NETHER_BRICK_FENCE.getBlockData(), 0, 13, 0, structureboundingbox);
            this.a(world, Blocks.NETHER_BRICK_FENCE.getBlockData(), 0, 13, 12, structureboundingbox);
            this.a(world, Blocks.NETHER_BRICK_FENCE.getBlockData(), 0, 13, 0, structureboundingbox);
            this.a(world, Blocks.NETHER_BRICK_FENCE.getBlockData(), 12, 13, 0, structureboundingbox);

            for (i = 3; i <= 9; i += 2) {
                this.a(world, structureboundingbox, 1, 7, i, 1, 8, i, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK_FENCE.getBlockData(), false);
                this.a(world, structureboundingbox, 11, 7, i, 11, 8, i, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK_FENCE.getBlockData(), false);
            }

            i = this.a(Blocks.NETHER_BRICK_STAIRS, 3);

            int j;
            int k;
            int l;

            for (j = 0; j <= 6; ++j) {
                k = j + 4;

                for (l = 5; l <= 7; ++l) {
                    this.a(world, Blocks.NETHER_BRICK_STAIRS.fromLegacyData(i), l, 5 + j, k, structureboundingbox);
                }

                if (k >= 5 && k <= 8) {
                    this.a(world, structureboundingbox, 5, 5, k, 7, j + 4, k, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
                } else if (k >= 9 && k <= 10) {
                    this.a(world, structureboundingbox, 5, 8, k, 7, j + 4, k, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
                }

                if (j >= 1) {
                    this.a(world, structureboundingbox, 5, 6 + j, k, 7, 9 + j, k, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
                }
            }

            for (j = 5; j <= 7; ++j) {
                this.a(world, Blocks.NETHER_BRICK_STAIRS.fromLegacyData(i), j, 12, 11, structureboundingbox);
            }

            this.a(world, structureboundingbox, 5, 6, 7, 5, 7, 7, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK_FENCE.getBlockData(), false);
            this.a(world, structureboundingbox, 7, 6, 7, 7, 7, 7, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK_FENCE.getBlockData(), false);
            this.a(world, structureboundingbox, 5, 13, 12, 7, 13, 12, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
            this.a(world, structureboundingbox, 2, 5, 2, 3, 5, 3, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 2, 5, 9, 3, 5, 10, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 2, 5, 4, 2, 5, 8, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 9, 5, 2, 10, 5, 3, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 9, 5, 9, 10, 5, 10, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 10, 5, 4, 10, 5, 8, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            j = this.a(Blocks.NETHER_BRICK_STAIRS, 0);
            k = this.a(Blocks.NETHER_BRICK_STAIRS, 1);
            this.a(world, Blocks.NETHER_BRICK_STAIRS.fromLegacyData(k), 4, 5, 2, structureboundingbox);
            this.a(world, Blocks.NETHER_BRICK_STAIRS.fromLegacyData(k), 4, 5, 3, structureboundingbox);
            this.a(world, Blocks.NETHER_BRICK_STAIRS.fromLegacyData(k), 4, 5, 9, structureboundingbox);
            this.a(world, Blocks.NETHER_BRICK_STAIRS.fromLegacyData(k), 4, 5, 10, structureboundingbox);
            this.a(world, Blocks.NETHER_BRICK_STAIRS.fromLegacyData(j), 8, 5, 2, structureboundingbox);
            this.a(world, Blocks.NETHER_BRICK_STAIRS.fromLegacyData(j), 8, 5, 3, structureboundingbox);
            this.a(world, Blocks.NETHER_BRICK_STAIRS.fromLegacyData(j), 8, 5, 9, structureboundingbox);
            this.a(world, Blocks.NETHER_BRICK_STAIRS.fromLegacyData(j), 8, 5, 10, structureboundingbox);
            this.a(world, structureboundingbox, 3, 4, 4, 4, 4, 8, Blocks.SOUL_SAND.getBlockData(), Blocks.SOUL_SAND.getBlockData(), false);
            this.a(world, structureboundingbox, 8, 4, 4, 9, 4, 8, Blocks.SOUL_SAND.getBlockData(), Blocks.SOUL_SAND.getBlockData(), false);
            this.a(world, structureboundingbox, 3, 5, 4, 4, 5, 8, Blocks.NETHER_WART.getBlockData(), Blocks.NETHER_WART.getBlockData(), false);
            this.a(world, structureboundingbox, 8, 5, 4, 9, 5, 8, Blocks.NETHER_WART.getBlockData(), Blocks.NETHER_WART.getBlockData(), false);
            this.a(world, structureboundingbox, 4, 2, 0, 8, 2, 12, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 0, 2, 4, 12, 2, 8, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 4, 0, 0, 8, 1, 3, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 4, 0, 9, 8, 1, 12, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 0, 0, 4, 3, 1, 8, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 9, 0, 4, 12, 1, 8, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);

            int i1;

            for (l = 4; l <= 8; ++l) {
                for (i1 = 0; i1 <= 2; ++i1) {
                    this.b(world, Blocks.NETHER_BRICK.getBlockData(), l, -1, i1, structureboundingbox);
                    this.b(world, Blocks.NETHER_BRICK.getBlockData(), l, -1, 12 - i1, structureboundingbox);
                }
            }

            for (l = 0; l <= 2; ++l) {
                for (i1 = 4; i1 <= 8; ++i1) {
                    this.b(world, Blocks.NETHER_BRICK.getBlockData(), l, -1, i1, structureboundingbox);
                    this.b(world, Blocks.NETHER_BRICK.getBlockData(), 12 - l, -1, i1, structureboundingbox);
                }
            }

            return true;
        }
    }

    public static class f extends WorldGenNetherPieces.m {

        public f() {}

        public f(int i, Random random, StructureBoundingBox structureboundingbox, EnumDirection enumdirection) {
            super(i);
            this.m = enumdirection;
            this.l = structureboundingbox;
        }

        public void a(StructurePiece structurepiece, List<StructurePiece> list, Random random) {
            this.a((WorldGenNetherPieces.q) structurepiece, list, random, 5, 3, true);
        }

        public static WorldGenNetherPieces.f a(List<StructurePiece> list, Random random, int i, int j, int k, EnumDirection enumdirection, int l) {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -5, -3, 0, 13, 14, 13, enumdirection);

            return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenNetherPieces.f(l, random, structureboundingbox, enumdirection) : null;
        }

        public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
            this.a(world, structureboundingbox, 0, 3, 0, 12, 4, 12, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 0, 5, 0, 12, 13, 12, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
            this.a(world, structureboundingbox, 0, 5, 0, 1, 12, 12, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 11, 5, 0, 12, 12, 12, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 2, 5, 11, 4, 12, 12, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 8, 5, 11, 10, 12, 12, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 5, 9, 11, 7, 12, 12, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 2, 5, 0, 4, 12, 1, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 8, 5, 0, 10, 12, 1, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 5, 9, 0, 7, 12, 1, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 2, 11, 2, 10, 12, 10, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 5, 8, 0, 7, 8, 0, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK_FENCE.getBlockData(), false);

            int i;

            for (i = 1; i <= 11; i += 2) {
                this.a(world, structureboundingbox, i, 10, 0, i, 11, 0, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK_FENCE.getBlockData(), false);
                this.a(world, structureboundingbox, i, 10, 12, i, 11, 12, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK_FENCE.getBlockData(), false);
                this.a(world, structureboundingbox, 0, 10, i, 0, 11, i, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK_FENCE.getBlockData(), false);
                this.a(world, structureboundingbox, 12, 10, i, 12, 11, i, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK_FENCE.getBlockData(), false);
                this.a(world, Blocks.NETHER_BRICK.getBlockData(), i, 13, 0, structureboundingbox);
                this.a(world, Blocks.NETHER_BRICK.getBlockData(), i, 13, 12, structureboundingbox);
                this.a(world, Blocks.NETHER_BRICK.getBlockData(), 0, 13, i, structureboundingbox);
                this.a(world, Blocks.NETHER_BRICK.getBlockData(), 12, 13, i, structureboundingbox);
                this.a(world, Blocks.NETHER_BRICK_FENCE.getBlockData(), i + 1, 13, 0, structureboundingbox);
                this.a(world, Blocks.NETHER_BRICK_FENCE.getBlockData(), i + 1, 13, 12, structureboundingbox);
                this.a(world, Blocks.NETHER_BRICK_FENCE.getBlockData(), 0, 13, i + 1, structureboundingbox);
                this.a(world, Blocks.NETHER_BRICK_FENCE.getBlockData(), 12, 13, i + 1, structureboundingbox);
            }

            this.a(world, Blocks.NETHER_BRICK_FENCE.getBlockData(), 0, 13, 0, structureboundingbox);
            this.a(world, Blocks.NETHER_BRICK_FENCE.getBlockData(), 0, 13, 12, structureboundingbox);
            this.a(world, Blocks.NETHER_BRICK_FENCE.getBlockData(), 0, 13, 0, structureboundingbox);
            this.a(world, Blocks.NETHER_BRICK_FENCE.getBlockData(), 12, 13, 0, structureboundingbox);

            for (i = 3; i <= 9; i += 2) {
                this.a(world, structureboundingbox, 1, 7, i, 1, 8, i, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK_FENCE.getBlockData(), false);
                this.a(world, structureboundingbox, 11, 7, i, 11, 8, i, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK_FENCE.getBlockData(), false);
            }

            this.a(world, structureboundingbox, 4, 2, 0, 8, 2, 12, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 0, 2, 4, 12, 2, 8, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 4, 0, 0, 8, 1, 3, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 4, 0, 9, 8, 1, 12, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 0, 0, 4, 3, 1, 8, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 9, 0, 4, 12, 1, 8, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);

            int j;

            for (i = 4; i <= 8; ++i) {
                for (j = 0; j <= 2; ++j) {
                    this.b(world, Blocks.NETHER_BRICK.getBlockData(), i, -1, j, structureboundingbox);
                    this.b(world, Blocks.NETHER_BRICK.getBlockData(), i, -1, 12 - j, structureboundingbox);
                }
            }

            for (i = 0; i <= 2; ++i) {
                for (j = 4; j <= 8; ++j) {
                    this.b(world, Blocks.NETHER_BRICK.getBlockData(), i, -1, j, structureboundingbox);
                    this.b(world, Blocks.NETHER_BRICK.getBlockData(), 12 - i, -1, j, structureboundingbox);
                }
            }

            this.a(world, structureboundingbox, 5, 5, 5, 7, 5, 7, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 6, 1, 6, 6, 4, 6, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
            this.a(world, Blocks.NETHER_BRICK.getBlockData(), 6, 0, 6, structureboundingbox);
            this.a(world, Blocks.FLOWING_LAVA.getBlockData(), 6, 5, 6, structureboundingbox);
            BlockPosition blockposition = new BlockPosition(this.a(6, 6), this.d(5), this.b(6, 6));

            if (structureboundingbox.b((BaseBlockPosition) blockposition)) {
                world.a((Block) Blocks.FLOWING_LAVA, blockposition, random);
            }

            return true;
        }
    }

    public static class l extends WorldGenNetherPieces.m {

        private boolean b;

        public l() {}

        public l(int i, Random random, StructureBoundingBox structureboundingbox, EnumDirection enumdirection) {
            super(i);
            this.m = enumdirection;
            this.l = structureboundingbox;
        }

        protected void b(NBTTagCompound nbttagcompound) {
            super.b(nbttagcompound);
            this.b = nbttagcompound.getBoolean("Mob");
        }

        protected void a(NBTTagCompound nbttagcompound) {
            super.a(nbttagcompound);
            nbttagcompound.setBoolean("Mob", this.b);
        }

        public static WorldGenNetherPieces.l a(List<StructurePiece> list, Random random, int i, int j, int k, int l, EnumDirection enumdirection) {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -2, 0, 0, 7, 8, 9, enumdirection);

            return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenNetherPieces.l(l, random, structureboundingbox, enumdirection) : null;
        }

        public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
            this.a(world, structureboundingbox, 0, 2, 0, 6, 7, 7, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
            this.a(world, structureboundingbox, 1, 0, 0, 5, 1, 7, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 1, 2, 1, 5, 2, 7, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 1, 3, 2, 5, 3, 7, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 1, 4, 3, 5, 4, 7, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 1, 2, 0, 1, 4, 2, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 5, 2, 0, 5, 4, 2, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 1, 5, 2, 1, 5, 3, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 5, 5, 2, 5, 5, 3, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 0, 5, 3, 0, 5, 8, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 6, 5, 3, 6, 5, 8, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 1, 5, 8, 5, 5, 8, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, Blocks.NETHER_BRICK_FENCE.getBlockData(), 1, 6, 3, structureboundingbox);
            this.a(world, Blocks.NETHER_BRICK_FENCE.getBlockData(), 5, 6, 3, structureboundingbox);
            this.a(world, structureboundingbox, 0, 6, 3, 0, 6, 8, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK_FENCE.getBlockData(), false);
            this.a(world, structureboundingbox, 6, 6, 3, 6, 6, 8, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK_FENCE.getBlockData(), false);
            this.a(world, structureboundingbox, 1, 6, 8, 5, 7, 8, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK_FENCE.getBlockData(), false);
            this.a(world, structureboundingbox, 2, 8, 8, 4, 8, 8, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK_FENCE.getBlockData(), false);
            if (!this.b) {
                BlockPosition blockposition = new BlockPosition(this.a(3, 5), this.d(5), this.b(3, 5));

                if (structureboundingbox.b((BaseBlockPosition) blockposition)) {
                    this.b = true;
                    world.setTypeAndData(blockposition, Blocks.MOB_SPAWNER.getBlockData(), 2);
                    TileEntity tileentity = world.getTileEntity(blockposition);

                    if (tileentity instanceof TileEntityMobSpawner) {
                        ((TileEntityMobSpawner) tileentity).getSpawner().setMobName("Blaze");
                    }
                }
            }

            for (int i = 0; i <= 6; ++i) {
                for (int j = 0; j <= 6; ++j) {
                    this.b(world, Blocks.NETHER_BRICK.getBlockData(), i, -1, j, structureboundingbox);
                }
            }

            return true;
        }
    }

    public static class p extends WorldGenNetherPieces.m {

        public p() {}

        public p(int i, Random random, StructureBoundingBox structureboundingbox, EnumDirection enumdirection) {
            super(i);
            this.m = enumdirection;
            this.l = structureboundingbox;
        }

        public void a(StructurePiece structurepiece, List<StructurePiece> list, Random random) {
            this.c((WorldGenNetherPieces.q) structurepiece, list, random, 6, 2, false);
        }

        public static WorldGenNetherPieces.p a(List<StructurePiece> list, Random random, int i, int j, int k, int l, EnumDirection enumdirection) {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -2, 0, 0, 7, 11, 7, enumdirection);

            return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenNetherPieces.p(l, random, structureboundingbox, enumdirection) : null;
        }

        public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
            this.a(world, structureboundingbox, 0, 0, 0, 6, 1, 6, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 0, 2, 0, 6, 10, 6, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
            this.a(world, structureboundingbox, 0, 2, 0, 1, 8, 0, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 5, 2, 0, 6, 8, 0, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 0, 2, 1, 0, 8, 6, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 6, 2, 1, 6, 8, 6, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 1, 2, 6, 5, 8, 6, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 0, 3, 2, 0, 5, 4, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK_FENCE.getBlockData(), false);
            this.a(world, structureboundingbox, 6, 3, 2, 6, 5, 2, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK_FENCE.getBlockData(), false);
            this.a(world, structureboundingbox, 6, 3, 4, 6, 5, 4, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK_FENCE.getBlockData(), false);
            this.a(world, Blocks.NETHER_BRICK.getBlockData(), 5, 2, 5, structureboundingbox);
            this.a(world, structureboundingbox, 4, 2, 5, 4, 3, 5, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 3, 2, 5, 3, 4, 5, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 2, 2, 5, 2, 5, 5, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 1, 2, 5, 1, 6, 5, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 1, 7, 1, 5, 7, 4, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 6, 8, 2, 6, 8, 4, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
            this.a(world, structureboundingbox, 2, 6, 0, 4, 8, 0, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 2, 5, 0, 4, 5, 0, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK_FENCE.getBlockData(), false);

            for (int i = 0; i <= 6; ++i) {
                for (int j = 0; j <= 6; ++j) {
                    this.b(world, Blocks.NETHER_BRICK.getBlockData(), i, -1, j, structureboundingbox);
                }
            }

            return true;
        }
    }

    public static class o extends WorldGenNetherPieces.m {

        public o() {}

        public o(int i, Random random, StructureBoundingBox structureboundingbox, EnumDirection enumdirection) {
            super(i);
            this.m = enumdirection;
            this.l = structureboundingbox;
        }

        public void a(StructurePiece structurepiece, List<StructurePiece> list, Random random) {
            this.a((WorldGenNetherPieces.q) structurepiece, list, random, 2, 0, false);
            this.b((WorldGenNetherPieces.q) structurepiece, list, random, 0, 2, false);
            this.c((WorldGenNetherPieces.q) structurepiece, list, random, 0, 2, false);
        }

        public static WorldGenNetherPieces.o a(List<StructurePiece> list, Random random, int i, int j, int k, EnumDirection enumdirection, int l) {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -2, 0, 0, 7, 9, 7, enumdirection);

            return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenNetherPieces.o(l, random, structureboundingbox, enumdirection) : null;
        }

        public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
            this.a(world, structureboundingbox, 0, 0, 0, 6, 1, 6, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 0, 2, 0, 6, 7, 6, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
            this.a(world, structureboundingbox, 0, 2, 0, 1, 6, 0, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 0, 2, 6, 1, 6, 6, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 5, 2, 0, 6, 6, 0, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 5, 2, 6, 6, 6, 6, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 0, 2, 0, 0, 6, 1, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 0, 2, 5, 0, 6, 6, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 6, 2, 0, 6, 6, 1, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 6, 2, 5, 6, 6, 6, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 2, 6, 0, 4, 6, 0, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 2, 5, 0, 4, 5, 0, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK_FENCE.getBlockData(), false);
            this.a(world, structureboundingbox, 2, 6, 6, 4, 6, 6, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 2, 5, 6, 4, 5, 6, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK_FENCE.getBlockData(), false);
            this.a(world, structureboundingbox, 0, 6, 2, 0, 6, 4, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 0, 5, 2, 0, 5, 4, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK_FENCE.getBlockData(), false);
            this.a(world, structureboundingbox, 6, 6, 2, 6, 6, 4, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 6, 5, 2, 6, 5, 4, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK_FENCE.getBlockData(), false);

            for (int i = 0; i <= 6; ++i) {
                for (int j = 0; j <= 6; ++j) {
                    this.b(world, Blocks.NETHER_BRICK.getBlockData(), i, -1, j, structureboundingbox);
                }
            }

            return true;
        }
    }

    public static class a extends WorldGenNetherPieces.m {

        public a() {}

        public a(int i, Random random, StructureBoundingBox structureboundingbox, EnumDirection enumdirection) {
            super(i);
            this.m = enumdirection;
            this.l = structureboundingbox;
        }

        protected a(Random random, int i, int j) {
            super(0);
            this.m = EnumDirection.c.HORIZONTAL.a(random);
            switch (WorldGenNetherPieces.SyntheticClass_1.a[this.m.ordinal()]) {
            case 1:
            case 2:
                this.l = new StructureBoundingBox(i, 64, j, i + 19 - 1, 73, j + 19 - 1);
                break;

            default:
                this.l = new StructureBoundingBox(i, 64, j, i + 19 - 1, 73, j + 19 - 1);
            }

        }

        public void a(StructurePiece structurepiece, List<StructurePiece> list, Random random) {
            this.a((WorldGenNetherPieces.q) structurepiece, list, random, 8, 3, false);
            this.b((WorldGenNetherPieces.q) structurepiece, list, random, 3, 8, false);
            this.c((WorldGenNetherPieces.q) structurepiece, list, random, 3, 8, false);
        }

        public static WorldGenNetherPieces.a a(List<StructurePiece> list, Random random, int i, int j, int k, EnumDirection enumdirection, int l) {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -8, -3, 0, 19, 10, 19, enumdirection);

            return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenNetherPieces.a(l, random, structureboundingbox, enumdirection) : null;
        }

        public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
            this.a(world, structureboundingbox, 7, 3, 0, 11, 4, 18, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 0, 3, 7, 18, 4, 11, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 8, 5, 0, 10, 7, 18, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
            this.a(world, structureboundingbox, 0, 5, 8, 18, 7, 10, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
            this.a(world, structureboundingbox, 7, 5, 0, 7, 5, 7, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 7, 5, 11, 7, 5, 18, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 11, 5, 0, 11, 5, 7, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 11, 5, 11, 11, 5, 18, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 0, 5, 7, 7, 5, 7, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 11, 5, 7, 18, 5, 7, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 0, 5, 11, 7, 5, 11, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 11, 5, 11, 18, 5, 11, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 7, 2, 0, 11, 2, 5, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 7, 2, 13, 11, 2, 18, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 7, 0, 0, 11, 1, 3, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 7, 0, 15, 11, 1, 18, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);

            int i;
            int j;

            for (i = 7; i <= 11; ++i) {
                for (j = 0; j <= 2; ++j) {
                    this.b(world, Blocks.NETHER_BRICK.getBlockData(), i, -1, j, structureboundingbox);
                    this.b(world, Blocks.NETHER_BRICK.getBlockData(), i, -1, 18 - j, structureboundingbox);
                }
            }

            this.a(world, structureboundingbox, 0, 2, 7, 5, 2, 11, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 13, 2, 7, 18, 2, 11, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 0, 0, 7, 3, 1, 11, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 15, 0, 7, 18, 1, 11, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);

            for (i = 0; i <= 2; ++i) {
                for (j = 7; j <= 11; ++j) {
                    this.b(world, Blocks.NETHER_BRICK.getBlockData(), i, -1, j, structureboundingbox);
                    this.b(world, Blocks.NETHER_BRICK.getBlockData(), 18 - i, -1, j, structureboundingbox);
                }
            }

            return true;
        }
    }

    public static class b extends WorldGenNetherPieces.m {

        private int b;

        public b() {}

        public b(int i, Random random, StructureBoundingBox structureboundingbox, EnumDirection enumdirection) {
            super(i);
            this.m = enumdirection;
            this.l = structureboundingbox;
            this.b = random.nextInt();
        }

        public static WorldGenNetherPieces.b a(List<StructurePiece> list, Random random, int i, int j, int k, EnumDirection enumdirection, int l) {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -1, -3, 0, 5, 10, 8, enumdirection);

            return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenNetherPieces.b(l, random, structureboundingbox, enumdirection) : null;
        }

        protected void b(NBTTagCompound nbttagcompound) {
            super.b(nbttagcompound);
            this.b = nbttagcompound.getInt("Seed");
        }

        protected void a(NBTTagCompound nbttagcompound) {
            super.a(nbttagcompound);
            nbttagcompound.setInt("Seed", this.b);
        }

        public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
            Random random1 = new Random((long) this.b);

            int i;
            int j;
            int k;

            for (i = 0; i <= 4; ++i) {
                for (j = 3; j <= 4; ++j) {
                    k = random1.nextInt(8);
                    this.a(world, structureboundingbox, i, j, 0, i, j, k, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
                }
            }

            i = random1.nextInt(8);
            this.a(world, structureboundingbox, 0, 5, 0, 0, 5, i, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            i = random1.nextInt(8);
            this.a(world, structureboundingbox, 4, 5, 0, 4, 5, i, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);

            for (i = 0; i <= 4; ++i) {
                j = random1.nextInt(5);
                this.a(world, structureboundingbox, i, 2, 0, i, 2, j, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            }

            for (i = 0; i <= 4; ++i) {
                for (j = 0; j <= 1; ++j) {
                    k = random1.nextInt(3);
                    this.a(world, structureboundingbox, i, j, 0, i, j, k, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
                }
            }

            return true;
        }
    }

    public static class c extends WorldGenNetherPieces.m {

        public c() {}

        public c(int i, Random random, StructureBoundingBox structureboundingbox, EnumDirection enumdirection) {
            super(i);
            this.m = enumdirection;
            this.l = structureboundingbox;
        }

        public void a(StructurePiece structurepiece, List<StructurePiece> list, Random random) {
            this.a((WorldGenNetherPieces.q) structurepiece, list, random, 1, 3, false);
        }

        public static WorldGenNetherPieces.c a(List<StructurePiece> list, Random random, int i, int j, int k, EnumDirection enumdirection, int l) {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -1, -3, 0, 5, 10, 19, enumdirection);

            return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenNetherPieces.c(l, random, structureboundingbox, enumdirection) : null;
        }

        public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
            this.a(world, structureboundingbox, 0, 3, 0, 4, 4, 18, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 1, 5, 0, 3, 7, 18, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
            this.a(world, structureboundingbox, 0, 5, 0, 0, 5, 18, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 4, 5, 0, 4, 5, 18, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 0, 2, 0, 4, 2, 5, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 0, 2, 13, 4, 2, 18, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 0, 0, 0, 4, 1, 3, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
            this.a(world, structureboundingbox, 0, 0, 15, 4, 1, 18, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);

            for (int i = 0; i <= 4; ++i) {
                for (int j = 0; j <= 2; ++j) {
                    this.b(world, Blocks.NETHER_BRICK.getBlockData(), i, -1, j, structureboundingbox);
                    this.b(world, Blocks.NETHER_BRICK.getBlockData(), i, -1, 18 - j, structureboundingbox);
                }
            }

            this.a(world, structureboundingbox, 0, 1, 1, 0, 4, 1, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK_FENCE.getBlockData(), false);
            this.a(world, structureboundingbox, 0, 3, 4, 0, 4, 4, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK_FENCE.getBlockData(), false);
            this.a(world, structureboundingbox, 0, 3, 14, 0, 4, 14, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK_FENCE.getBlockData(), false);
            this.a(world, structureboundingbox, 0, 1, 17, 0, 4, 17, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK_FENCE.getBlockData(), false);
            this.a(world, structureboundingbox, 4, 1, 1, 4, 4, 1, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK_FENCE.getBlockData(), false);
            this.a(world, structureboundingbox, 4, 3, 4, 4, 4, 4, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK_FENCE.getBlockData(), false);
            this.a(world, structureboundingbox, 4, 3, 14, 4, 4, 14, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK_FENCE.getBlockData(), false);
            this.a(world, structureboundingbox, 4, 1, 17, 4, 4, 17, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK_FENCE.getBlockData(), false);
            return true;
        }
    }

    public static class q extends WorldGenNetherPieces.a {

        public WorldGenNetherPieces.n b;
        public List<WorldGenNetherPieces.n> c;
        public List<WorldGenNetherPieces.n> d;
        public List<StructurePiece> e = Lists.newArrayList();

        public q() {}

        public q(Random random, int i, int j) {
            super(random, i, j);
            this.c = Lists.newArrayList();
            WorldGenNetherPieces.n[] aworldgennetherpieces_n = WorldGenNetherPieces.a;
            int k = aworldgennetherpieces_n.length;

            int l;
            WorldGenNetherPieces.n worldgennetherpieces_n;

            for (l = 0; l < k; ++l) {
                worldgennetherpieces_n = aworldgennetherpieces_n[l];
                worldgennetherpieces_n.c = 0;
                this.c.add(worldgennetherpieces_n);
            }

            this.d = Lists.newArrayList();
            aworldgennetherpieces_n = WorldGenNetherPieces.b;
            k = aworldgennetherpieces_n.length;

            for (l = 0; l < k; ++l) {
                worldgennetherpieces_n = aworldgennetherpieces_n[l];
                worldgennetherpieces_n.c = 0;
                this.d.add(worldgennetherpieces_n);
            }

        }

        protected void b(NBTTagCompound nbttagcompound) {
            super.b(nbttagcompound);
        }

        protected void a(NBTTagCompound nbttagcompound) {
            super.a(nbttagcompound);
        }
    }

    abstract static class m extends StructurePiece {

        protected static final List<StructurePieceTreasure> a = Lists.newArrayList((Object[]) (new StructurePieceTreasure[] { new StructurePieceTreasure(Items.DIAMOND, 0, 1, 3, 5), new StructurePieceTreasure(Items.IRON_INGOT, 0, 1, 5, 5), new StructurePieceTreasure(Items.GOLD_INGOT, 0, 1, 3, 15), new StructurePieceTreasure(Items.GOLDEN_SWORD, 0, 1, 1, 5), new StructurePieceTreasure(Items.GOLDEN_CHESTPLATE, 0, 1, 1, 5), new StructurePieceTreasure(Items.FLINT_AND_STEEL, 0, 1, 1, 5), new StructurePieceTreasure(Items.NETHER_WART, 0, 3, 7, 5), new StructurePieceTreasure(Items.SADDLE, 0, 1, 1, 10), new StructurePieceTreasure(Items.GOLDEN_HORSE_ARMOR, 0, 1, 1, 8), new StructurePieceTreasure(Items.IRON_HORSE_ARMOR, 0, 1, 1, 5), new StructurePieceTreasure(Items.DIAMOND_HORSE_ARMOR, 0, 1, 1, 3), new StructurePieceTreasure(Item.getItemOf(Blocks.OBSIDIAN), 0, 2, 4, 2)}));

        public m() {}

        protected m(int i) {
            super(i);
        }

        protected void b(NBTTagCompound nbttagcompound) {}

        protected void a(NBTTagCompound nbttagcompound) {}

        private int a(List<WorldGenNetherPieces.n> list) {
            boolean flag = false;
            int i = 0;

            WorldGenNetherPieces.n worldgennetherpieces_n;

            for (Iterator iterator = list.iterator(); iterator.hasNext(); i += worldgennetherpieces_n.b) {
                worldgennetherpieces_n = (WorldGenNetherPieces.n) iterator.next();
                if (worldgennetherpieces_n.d > 0 && worldgennetherpieces_n.c < worldgennetherpieces_n.d) {
                    flag = true;
                }
            }

            return flag ? i : -1;
        }

        private WorldGenNetherPieces.m a(WorldGenNetherPieces.q worldgennetherpieces_q, List<WorldGenNetherPieces.n> list, List<StructurePiece> list1, Random random, int i, int j, int k, EnumDirection enumdirection, int l) {
            int i1 = this.a(list);
            boolean flag = i1 > 0 && l <= 30;
            int j1 = 0;

            while (j1 < 5 && flag) {
                ++j1;
                int k1 = random.nextInt(i1);
                Iterator iterator = list.iterator();

                while (iterator.hasNext()) {
                    WorldGenNetherPieces.n worldgennetherpieces_n = (WorldGenNetherPieces.n) iterator.next();

                    k1 -= worldgennetherpieces_n.b;
                    if (k1 < 0) {
                        if (!worldgennetherpieces_n.a(l) || worldgennetherpieces_n == worldgennetherpieces_q.b && !worldgennetherpieces_n.e) {
                            break;
                        }

                        WorldGenNetherPieces.m worldgennetherpieces_m = WorldGenNetherPieces.b(worldgennetherpieces_n, list1, random, i, j, k, enumdirection, l);

                        if (worldgennetherpieces_m != null) {
                            ++worldgennetherpieces_n.c;
                            worldgennetherpieces_q.b = worldgennetherpieces_n;
                            if (!worldgennetherpieces_n.a()) {
                                list.remove(worldgennetherpieces_n);
                            }

                            return worldgennetherpieces_m;
                        }
                    }
                }
            }

            return WorldGenNetherPieces.b.a(list1, random, i, j, k, enumdirection, l);
        }

        private StructurePiece a(WorldGenNetherPieces.q worldgennetherpieces_q, List<StructurePiece> list, Random random, int i, int j, int k, EnumDirection enumdirection, int l, boolean flag) {
            if (Math.abs(i - worldgennetherpieces_q.c().a) <= 112 && Math.abs(k - worldgennetherpieces_q.c().c) <= 112) {
                List list1 = worldgennetherpieces_q.c;

                if (flag) {
                    list1 = worldgennetherpieces_q.d;
                }

                WorldGenNetherPieces.m worldgennetherpieces_m = this.a(worldgennetherpieces_q, list1, list, random, i, j, k, enumdirection, l + 1);

                if (worldgennetherpieces_m != null) {
                    list.add(worldgennetherpieces_m);
                    worldgennetherpieces_q.e.add(worldgennetherpieces_m);
                }

                return worldgennetherpieces_m;
            } else {
                return WorldGenNetherPieces.b.a(list, random, i, j, k, enumdirection, l);
            }
        }

        protected StructurePiece a(WorldGenNetherPieces.q worldgennetherpieces_q, List<StructurePiece> list, Random random, int i, int j, boolean flag) {
            if (this.m != null) {
                switch (WorldGenNetherPieces.SyntheticClass_1.a[this.m.ordinal()]) {
                case 1:
                    return this.a(worldgennetherpieces_q, list, random, this.l.a + i, this.l.b + j, this.l.c - 1, this.m, this.d(), flag);

                case 2:
                    return this.a(worldgennetherpieces_q, list, random, this.l.a + i, this.l.b + j, this.l.f + 1, this.m, this.d(), flag);

                case 3:
                    return this.a(worldgennetherpieces_q, list, random, this.l.a - 1, this.l.b + j, this.l.c + i, this.m, this.d(), flag);

                case 4:
                    return this.a(worldgennetherpieces_q, list, random, this.l.d + 1, this.l.b + j, this.l.c + i, this.m, this.d(), flag);
                }
            }

            return null;
        }

        protected StructurePiece b(WorldGenNetherPieces.q worldgennetherpieces_q, List<StructurePiece> list, Random random, int i, int j, boolean flag) {
            if (this.m != null) {
                switch (WorldGenNetherPieces.SyntheticClass_1.a[this.m.ordinal()]) {
                case 1:
                    return this.a(worldgennetherpieces_q, list, random, this.l.a - 1, this.l.b + i, this.l.c + j, EnumDirection.WEST, this.d(), flag);

                case 2:
                    return this.a(worldgennetherpieces_q, list, random, this.l.a - 1, this.l.b + i, this.l.c + j, EnumDirection.WEST, this.d(), flag);

                case 3:
                    return this.a(worldgennetherpieces_q, list, random, this.l.a + j, this.l.b + i, this.l.c - 1, EnumDirection.NORTH, this.d(), flag);

                case 4:
                    return this.a(worldgennetherpieces_q, list, random, this.l.a + j, this.l.b + i, this.l.c - 1, EnumDirection.NORTH, this.d(), flag);
                }
            }

            return null;
        }

        protected StructurePiece c(WorldGenNetherPieces.q worldgennetherpieces_q, List<StructurePiece> list, Random random, int i, int j, boolean flag) {
            if (this.m != null) {
                switch (WorldGenNetherPieces.SyntheticClass_1.a[this.m.ordinal()]) {
                case 1:
                    return this.a(worldgennetherpieces_q, list, random, this.l.d + 1, this.l.b + i, this.l.c + j, EnumDirection.EAST, this.d(), flag);

                case 2:
                    return this.a(worldgennetherpieces_q, list, random, this.l.d + 1, this.l.b + i, this.l.c + j, EnumDirection.EAST, this.d(), flag);

                case 3:
                    return this.a(worldgennetherpieces_q, list, random, this.l.a + j, this.l.b + i, this.l.f + 1, EnumDirection.SOUTH, this.d(), flag);

                case 4:
                    return this.a(worldgennetherpieces_q, list, random, this.l.a + j, this.l.b + i, this.l.f + 1, EnumDirection.SOUTH, this.d(), flag);
                }
            }

            return null;
        }

        protected static boolean a(StructureBoundingBox structureboundingbox) {
            return structureboundingbox != null && structureboundingbox.b > 10;
        }
    }

    static class n {

        public Class<? extends WorldGenNetherPieces.m> a;
        public final int b;
        public int c;
        public int d;
        public boolean e;

        public n(Class<? extends WorldGenNetherPieces.m> oclass, int i, int j, boolean flag) {
            this.a = oclass;
            this.b = i;
            this.d = j;
            this.e = flag;
        }

        public n(Class<? extends WorldGenNetherPieces.m> oclass, int i, int j) {
            this(oclass, i, j, false);
        }

        public boolean a(int i) {
            return this.d == 0 || this.c < this.d;
        }

        public boolean a() {
            return this.d == 0 || this.c < this.d;
        }
    }
}
