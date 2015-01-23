package net.minecraft.server;

import java.util.List;

public class CommandEntityData extends CommandAbstract {

    public String getCommand() {
        return "entitydata";
    }

    public int a() {
        return 2;
    }

    public String getUsage(ICommandListener icommandlistener) {
        return "commands.entitydata.usage";
    }

    public void execute(ICommandListener icommandlistener, String[] astring) {
        if (astring.length < 2) {
            throw new ExceptionUsage("commands.entitydata.usage", new Object[0]);
        } else {
            Entity entity = b(icommandlistener, astring[0]);

            if (entity instanceof EntityHuman) {
                throw new CommandException("commands.entitydata.noPlayers", new Object[] { entity.getScoreboardDisplayName()});
            } else {
                NBTTagCompound nbttagcompound = new NBTTagCompound();

                entity.e(nbttagcompound);
                NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttagcompound.clone();

                NBTTagCompound nbttagcompound2;

                try {
                    nbttagcompound2 = MojangsonParser.parse(a(icommandlistener, astring, 1).c());
                } catch (MojangsonParseException mojangsonparseexception) {
                    throw new CommandException("commands.entitydata.tagError", new Object[] { mojangsonparseexception.getMessage()});
                }

                nbttagcompound2.remove("UUIDMost");
                nbttagcompound2.remove("UUIDLeast");
                nbttagcompound.a(nbttagcompound2);
                if (nbttagcompound.equals(nbttagcompound1)) {
                    throw new CommandException("commands.entitydata.failed", new Object[] { nbttagcompound.toString()});
                } else {
                    entity.f(nbttagcompound);
                    a(icommandlistener, this, "commands.entitydata.success", new Object[] { nbttagcompound.toString()});
                }
            }
        }
    }

    public List tabComplete(ICommandListener icommandlistener, String[] astring, BlockPosition blockposition) {
        return astring.length == 1 ? a(astring, MinecraftServer.getServer().getPlayers()) : null;
    }

    public boolean isListStart(String[] astring, int i) {
        return i == 0;
    }
}
