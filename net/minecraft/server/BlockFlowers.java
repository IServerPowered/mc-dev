package net.minecraft.server;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import java.util.Collection;

public abstract class BlockFlowers extends BlockPlant {

    protected BlockStateEnum<BlockFlowers.a> TYPE;

    protected BlockFlowers() {
        this.j(this.blockStateList.getBlockData().set(this.n(), this.l() == BlockFlowers.b.RED ? BlockFlowers.a.POPPY : BlockFlowers.a.DANDELION));
    }

    public int getDropData(IBlockData iblockdata) {
        return ((BlockFlowers.a) iblockdata.get(this.n())).b();
    }

    public IBlockData fromLegacyData(int i) {
        return this.getBlockData().set(this.n(), BlockFlowers.a.a(this.l(), i));
    }

    public abstract BlockFlowers.b l();

    public IBlockState<BlockFlowers.a> n() {
        if (this.TYPE == null) {
            this.TYPE = BlockStateEnum.a("type", BlockFlowers.a.class, new Predicate() {
                public boolean a(BlockFlowers.a blockflowers_a) {
                    return blockflowers_a.a() == BlockFlowers.this.l();
                }

                public boolean apply(Object object) {
                    return this.a((BlockFlowers.a) object);
                }
            });
        }

        return this.TYPE;
    }

    public int toLegacyData(IBlockData iblockdata) {
        return ((BlockFlowers.a) iblockdata.get(this.n())).b();
    }

    protected BlockStateList getStateList() {
        return new BlockStateList(this, new IBlockState[] { this.n()});
    }

    public static enum a implements INamable {

        DANDELION(BlockFlowers.b.YELLOW, 0, "dandelion"), POPPY(BlockFlowers.b.RED, 0, "poppy"), BLUE_ORCHID(BlockFlowers.b.RED, 1, "blue_orchid", "blueOrchid"), ALLIUM(BlockFlowers.b.RED, 2, "allium"), HOUSTONIA(BlockFlowers.b.RED, 3, "houstonia"), RED_TULIP(BlockFlowers.b.RED, 4, "red_tulip", "tulipRed"), ORANGE_TULIP(BlockFlowers.b.RED, 5, "orange_tulip", "tulipOrange"), WHITE_TULIP(BlockFlowers.b.RED, 6, "white_tulip", "tulipWhite"), PINK_TULIP(BlockFlowers.b.RED, 7, "pink_tulip", "tulipPink"), OXEYE_DAISY(BlockFlowers.b.RED, 8, "oxeye_daisy", "oxeyeDaisy");

        private static final BlockFlowers.a[][] k = new BlockFlowers.a[BlockFlowers.b.values().length][];
        private final BlockFlowers.b l;
        private final int m;
        private final String n;
        private final String o;

        private a(BlockFlowers.b blockflowers_b, int i, String s) {
            this(blockflowers_b, i, s, s);
        }

        private a(BlockFlowers.b blockflowers_b, int i, String s, String s1) {
            this.l = blockflowers_b;
            this.m = i;
            this.n = s;
            this.o = s1;
        }

        public BlockFlowers.b a() {
            return this.l;
        }

        public int b() {
            return this.m;
        }

        public static BlockFlowers.a a(BlockFlowers.b blockflowers_b, int i) {
            BlockFlowers.a[] ablockflowers_a = BlockFlowers.a.k[blockflowers_b.ordinal()];

            if (i < 0 || i >= ablockflowers_a.length) {
                i = 0;
            }

            return ablockflowers_a[i];
        }

        public String toString() {
            return this.n;
        }

        public String getName() {
            return this.n;
        }

        public String d() {
            return this.o;
        }

        static {
            BlockFlowers.b[] ablockflowers_b = BlockFlowers.b.values();
            int i = ablockflowers_b.length;

            for (int j = 0; j < i; ++j) {
                final BlockFlowers.b blockflowers_b = ablockflowers_b[j];
                Collection collection = Collections2.filter(Lists.newArrayList((Object[]) values()), new Predicate() {
                    public boolean a(BlockFlowers.a blockflowers_a) {
                        return blockflowers_a.a() == blockflowers_b;
                    }

                    public boolean apply(Object object) {
                        return this.a((BlockFlowers.a) object);
                    }
                });

                BlockFlowers.a.k[blockflowers_b.ordinal()] = (BlockFlowers.a[]) collection.toArray(new BlockFlowers.a[collection.size()]);
            }

        }
    }

    public static enum b {

        YELLOW, RED;

        private b() {}

        public BlockFlowers a() {
            return this == BlockFlowers.b.YELLOW ? Blocks.YELLOW_FLOWER : Blocks.RED_FLOWER;
        }
    }
}
