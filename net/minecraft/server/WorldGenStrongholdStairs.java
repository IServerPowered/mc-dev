package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenStrongholdStairs extends WorldGenStrongholdPiece {

    private boolean a;
    private boolean b;

    public WorldGenStrongholdStairs() {}

    public WorldGenStrongholdStairs(int i, Random random, StructureBoundingBox structureboundingbox, EnumDirection enumdirection) {
        super(i);
        this.m = enumdirection;
        this.d = this.a(random);
        this.l = structureboundingbox;
        this.a = random.nextInt(2) == 0;
        this.b = random.nextInt(2) == 0;
    }

    protected void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        nbttagcompound.setBoolean("Left", this.a);
        nbttagcompound.setBoolean("Right", this.b);
    }

    protected void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        this.a = nbttagcompound.getBoolean("Left");
        this.b = nbttagcompound.getBoolean("Right");
    }

    public void a(StructurePiece structurepiece, List list, Random random) {
        this.a((WorldGenStrongholdStart) structurepiece, list, random, 1, 1);
        if (this.a) {
            this.b((WorldGenStrongholdStart) structurepiece, list, random, 1, 2);
        }

        if (this.b) {
            this.c((WorldGenStrongholdStart) structurepiece, list, random, 1, 2);
        }

    }

    public static WorldGenStrongholdStairs a(List list, Random random, int i, int j, int k, EnumDirection enumdirection, int l) {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -1, -1, 0, 5, 5, 7, enumdirection);

        return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenStrongholdStairs(l, random, structureboundingbox, enumdirection) : null;
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        if (this.a(world, structureboundingbox)) {
            return false;
        } else {
            this.a(world, structureboundingbox, 0, 0, 0, 4, 4, 6, true, random, WorldGenStrongholdPieces.c());
            this.a(world, random, structureboundingbox, this.d, 1, 1, 0);
            this.a(world, random, structureboundingbox, WorldGenStrongholdDoorType.OPENING, 1, 1, 6);
            this.a(world, structureboundingbox, random, 0.1F, 1, 2, 1, Blocks.TORCH.getBlockData());
            this.a(world, structureboundingbox, random, 0.1F, 3, 2, 1, Blocks.TORCH.getBlockData());
            this.a(world, structureboundingbox, random, 0.1F, 1, 2, 5, Blocks.TORCH.getBlockData());
            this.a(world, structureboundingbox, random, 0.1F, 3, 2, 5, Blocks.TORCH.getBlockData());
            if (this.a) {
                this.a(world, structureboundingbox, 0, 1, 2, 0, 3, 4, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
            }

            if (this.b) {
                this.a(world, structureboundingbox, 4, 1, 2, 4, 3, 4, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
            }

            return true;
        }
    }
}
