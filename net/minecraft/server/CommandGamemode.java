package net.minecraft.server;

import java.util.List;

public class CommandGamemode extends CommandAbstract {

    public CommandGamemode() {}

    public String getCommand() {
        return "gamemode";
    }

    public int a() {
        return 2;
    }

    public String getUsage(ICommandListener icommandlistener) {
        return "commands.gamemode.usage";
    }

    public void execute(ICommandListener icommandlistener, String[] astring) throws CommandException {
        if (astring.length <= 0) {
            throw new ExceptionUsage("commands.gamemode.usage", new Object[0]);
        } else {
            WorldSettings.a worldsettings_a = this.h(icommandlistener, astring[0]);
            EntityPlayer entityplayer = astring.length >= 2 ? a(icommandlistener, astring[1]) : b(icommandlistener);

            entityplayer.a(worldsettings_a);
            entityplayer.fallDistance = 0.0F;
            if (icommandlistener.getWorld().getGameRules().getBoolean("sendCommandFeedback")) {
                entityplayer.sendMessage(new ChatMessage("gameMode.changed", new Object[0]));
            }

            ChatMessage chatmessage = new ChatMessage("gameMode." + worldsettings_a.b(), new Object[0]);

            if (entityplayer != icommandlistener) {
                a(icommandlistener, this, 1, "commands.gamemode.success.other", new Object[] { entityplayer.getName(), chatmessage});
            } else {
                a(icommandlistener, this, 1, "commands.gamemode.success.self", new Object[] { chatmessage});
            }

        }
    }

    protected WorldSettings.a h(ICommandListener icommandlistener, String s) throws ExceptionInvalidNumber {
        return !s.equalsIgnoreCase(WorldSettings.a.SURVIVAL.b()) && !s.equalsIgnoreCase("s") ? (!s.equalsIgnoreCase(WorldSettings.a.CREATIVE.b()) && !s.equalsIgnoreCase("c") ? (!s.equalsIgnoreCase(WorldSettings.a.ADVENTURE.b()) && !s.equalsIgnoreCase("a") ? (!s.equalsIgnoreCase(WorldSettings.a.SPECTATOR.b()) && !s.equalsIgnoreCase("sp") ? WorldSettings.a(a(s, 0, WorldSettings.a.values().length - 2)) : WorldSettings.a.SPECTATOR) : WorldSettings.a.ADVENTURE) : WorldSettings.a.CREATIVE) : WorldSettings.a.SURVIVAL;
    }

    public List<String> tabComplete(ICommandListener icommandlistener, String[] astring, BlockPosition blockposition) {
        return astring.length == 1 ? a(astring, new String[] { "survival", "creative", "adventure", "spectator"}) : (astring.length == 2 ? a(astring, this.d()) : null);
    }

    protected String[] d() {
        return MinecraftServer.getServer().getPlayers();
    }

    public boolean isListStart(String[] astring, int i) {
        return i == 1;
    }
}
