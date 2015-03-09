package net.minecraft.server;

import com.google.common.collect.Lists;
import java.util.ArrayList;

public class EntityPainting extends EntityHanging {

    public EntityPainting.a art;

    public EntityPainting(World world) {
        super(world);
    }

    public EntityPainting(World world, BlockPosition blockposition, EnumDirection enumdirection) {
        super(world, blockposition);
        ArrayList arraylist = Lists.newArrayList();
        EntityPainting.a[] aentitypainting_a = EntityPainting.a.values();
        int i = aentitypainting_a.length;

        for (int j = 0; j < i; ++j) {
            EntityPainting.a entitypainting_a = aentitypainting_a[j];

            this.art = entitypainting_a;
            this.setDirection(enumdirection);
            if (this.survives()) {
                arraylist.add(entitypainting_a);
            }
        }

        if (!arraylist.isEmpty()) {
            this.art = (EntityPainting.a) arraylist.get(this.random.nextInt(arraylist.size()));
        }

        this.setDirection(enumdirection);
    }

    public void b(NBTTagCompound nbttagcompound) {
        nbttagcompound.setString("Motive", this.art.B);
        super.b(nbttagcompound);
    }

    public void a(NBTTagCompound nbttagcompound) {
        String s = nbttagcompound.getString("Motive");
        EntityPainting.a[] aentitypainting_a = EntityPainting.a.values();
        int i = aentitypainting_a.length;

        for (int j = 0; j < i; ++j) {
            EntityPainting.a entitypainting_a = aentitypainting_a[j];

            if (entitypainting_a.B.equals(s)) {
                this.art = entitypainting_a;
            }
        }

        if (this.art == null) {
            this.art = EntityPainting.a.KEBAB;
        }

        super.a(nbttagcompound);
    }

    public int l() {
        return this.art.C;
    }

    public int m() {
        return this.art.D;
    }

    public void b(Entity entity) {
        if (this.world.getGameRules().getBoolean("doEntityDrops")) {
            if (entity instanceof EntityHuman) {
                EntityHuman entityhuman = (EntityHuman) entity;

                if (entityhuman.abilities.canInstantlyBuild) {
                    return;
                }
            }

            this.a(new ItemStack(Items.PAINTING), 0.0F);
        }
    }

    public void setPositionRotation(double d0, double d1, double d2, float f, float f1) {
        BlockPosition blockposition = this.blockPosition.a(d0 - this.locX, d1 - this.locY, d2 - this.locZ);

        this.setPosition((double) blockposition.getX(), (double) blockposition.getY(), (double) blockposition.getZ());
    }

    public static enum a {

        KEBAB("Kebab", 16, 16, 0, 0), AZTEC("Aztec", 16, 16, 16, 0), ALBAN("Alban", 16, 16, 32, 0), AZTEC_2("Aztec2", 16, 16, 48, 0), BOMB("Bomb", 16, 16, 64, 0), PLANT("Plant", 16, 16, 80, 0), WASTELAND("Wasteland", 16, 16, 96, 0), POOL("Pool", 32, 16, 0, 32), COURBET("Courbet", 32, 16, 32, 32), SEA("Sea", 32, 16, 64, 32), SUNSET("Sunset", 32, 16, 96, 32), CREEBET("Creebet", 32, 16, 128, 32), WANDERER("Wanderer", 16, 32, 0, 64), GRAHAM("Graham", 16, 32, 16, 64), MATCH("Match", 32, 32, 0, 128), BUST("Bust", 32, 32, 32, 128), STAGE("Stage", 32, 32, 64, 128), VOID("Void", 32, 32, 96, 128), SKULL_AND_ROSES("SkullAndRoses", 32, 32, 128, 128), WITHER("Wither", 32, 32, 160, 128), FIGHTERS("Fighters", 64, 32, 0, 96), POINTER("Pointer", 64, 64, 0, 192), PIGSCENE("Pigscene", 64, 64, 64, 192), BURNING_SKULL("BurningSkull", 64, 64, 128, 192), SKELETON("Skeleton", 64, 48, 192, 64), DONKEY_KONG("DonkeyKong", 64, 48, 192, 112);

        public static final int A = "SkullAndRoses".length();
        public final String B;
        public final int C;
        public final int D;
        public final int E;
        public final int F;

        private a(String s, int i, int j, int k, int l) {
            this.B = s;
            this.C = i;
            this.D = j;
            this.E = k;
            this.F = l;
        }
    }
}
