package net.minecraft.server;

public class TileEntityCommand extends TileEntity {

    private final CommandBlockListenerAbstract a = new TileEntityCommandListener(this);

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        this.a.a(nbttagcompound);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.a.b(nbttagcompound);
    }

    public Packet getUpdatePacket() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();

        this.b(nbttagcompound);
        return new PacketPlayOutTileEntityData(this.position, 2, nbttagcompound);
    }

    public CommandBlockListenerAbstract getCommandBlock() {
        return this.a;
    }

    public CommandObjectiveExecutor c() {
        return this.a.n();
    }
}
