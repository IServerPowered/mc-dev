package net.minecraft.server;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class CommandSpreadPlayers extends CommandAbstract {

    public CommandSpreadPlayers() {}

    public String getCommand() {
        return "spreadplayers";
    }

    public int a() {
        return 2;
    }

    public String getUsage(ICommandListener icommandlistener) {
        return "commands.spreadplayers.usage";
    }

    public void execute(ICommandListener icommandlistener, String[] astring) throws CommandException {
        if (astring.length < 6) {
            throw new ExceptionUsage("commands.spreadplayers.usage", new Object[0]);
        } else {
            byte b0 = 0;
            BlockPosition blockposition = icommandlistener.getChunkCoordinates();
            double d0 = (double) blockposition.getX();
            int i = b0 + 1;
            double d1 = b(d0, astring[b0], true);
            double d2 = b((double) blockposition.getZ(), astring[i++], true);
            double d3 = a(astring[i++], 0.0D);
            double d4 = a(astring[i++], d3 + 1.0D);
            boolean flag = d(astring[i++]);
            ArrayList arraylist = Lists.newArrayList();

            while (i < astring.length) {
                String s = astring[i++];

                if (PlayerSelector.isPattern(s)) {
                    List list = PlayerSelector.getPlayers(icommandlistener, s, Entity.class);

                    if (list.size() == 0) {
                        throw new ExceptionEntityNotFound();
                    }

                    arraylist.addAll(list);
                } else {
                    EntityPlayer entityplayer = MinecraftServer.getServer().getPlayerList().getPlayer(s);

                    if (entityplayer == null) {
                        throw new ExceptionPlayerNotFound();
                    }

                    arraylist.add(entityplayer);
                }
            }

            icommandlistener.a(CommandObjectiveExecutor.a.AFFECTED_ENTITIES, arraylist.size());
            if (arraylist.isEmpty()) {
                throw new ExceptionEntityNotFound();
            } else {
                icommandlistener.sendMessage(new ChatMessage("commands.spreadplayers.spreading." + (flag ? "teams" : "players"), new Object[] { Integer.valueOf(arraylist.size()), Double.valueOf(d4), Double.valueOf(d1), Double.valueOf(d2), Double.valueOf(d3)}));
                this.a(icommandlistener, arraylist, new CommandSpreadPlayers.a(d1, d2), d3, d4, ((Entity) arraylist.get(0)).world, flag);
            }
        }
    }

    private void a(ICommandListener icommandlistener, List<Entity> list, CommandSpreadPlayers.a commandspreadplayers_a, double d0, double d1, World world, boolean flag) throws CommandException {
        Random random = new Random();
        double d2 = commandspreadplayers_a.a - d1;
        double d3 = commandspreadplayers_a.b - d1;
        double d4 = commandspreadplayers_a.a + d1;
        double d5 = commandspreadplayers_a.b + d1;
        CommandSpreadPlayers.a[] acommandspreadplayers_a = this.a(random, flag ? this.b(list) : list.size(), d2, d3, d4, d5);
        int i = this.a(commandspreadplayers_a, d0, world, random, d2, d3, d4, d5, acommandspreadplayers_a, flag);
        double d6 = this.a(list, world, acommandspreadplayers_a, flag);

        a(icommandlistener, this, "commands.spreadplayers.success." + (flag ? "teams" : "players"), new Object[] { Integer.valueOf(acommandspreadplayers_a.length), Double.valueOf(commandspreadplayers_a.a), Double.valueOf(commandspreadplayers_a.b)});
        if (acommandspreadplayers_a.length > 1) {
            icommandlistener.sendMessage(new ChatMessage("commands.spreadplayers.info." + (flag ? "teams" : "players"), new Object[] { String.format("%.2f", new Object[] { Double.valueOf(d6)}), Integer.valueOf(i)}));
        }

    }

    private int b(List<Entity> list) {
        HashSet hashset = Sets.newHashSet();
        Iterator iterator = list.iterator();

        while (iterator.hasNext()) {
            Entity entity = (Entity) iterator.next();

            if (entity instanceof EntityHuman) {
                hashset.add(((EntityHuman) entity).getScoreboardTeam());
            } else {
                hashset.add((Object) null);
            }
        }

        return hashset.size();
    }

    private int a(CommandSpreadPlayers.a commandspreadplayers_a, double d0, World world, Random random, double d1, double d2, double d3, double d4, CommandSpreadPlayers.a[] acommandspreadplayers_a, boolean flag) throws CommandException {
        boolean flag1 = true;
        double d5 = 3.4028234663852886E38D;

        int i;

        for (i = 0; i < 10000 && flag1; ++i) {
            flag1 = false;
            d5 = 3.4028234663852886E38D;

            int j;
            CommandSpreadPlayers.a commandspreadplayers_a1;

            for (int k = 0; k < acommandspreadplayers_a.length; ++k) {
                CommandSpreadPlayers.a commandspreadplayers_a2 = acommandspreadplayers_a[k];

                j = 0;
                commandspreadplayers_a1 = new CommandSpreadPlayers.a();

                for (int l = 0; l < acommandspreadplayers_a.length; ++l) {
                    if (k != l) {
                        CommandSpreadPlayers.a commandspreadplayers_a3 = acommandspreadplayers_a[l];
                        double d6 = commandspreadplayers_a2.a(commandspreadplayers_a3);

                        d5 = Math.min(d6, d5);
                        if (d6 < d0) {
                            ++j;
                            commandspreadplayers_a1.a += commandspreadplayers_a3.a - commandspreadplayers_a2.a;
                            commandspreadplayers_a1.b += commandspreadplayers_a3.b - commandspreadplayers_a2.b;
                        }
                    }
                }

                if (j > 0) {
                    commandspreadplayers_a1.a /= (double) j;
                    commandspreadplayers_a1.b /= (double) j;
                    double d7 = (double) commandspreadplayers_a1.b();

                    if (d7 > 0.0D) {
                        commandspreadplayers_a1.a();
                        commandspreadplayers_a2.b(commandspreadplayers_a1);
                    } else {
                        commandspreadplayers_a2.a(random, d1, d2, d3, d4);
                    }

                    flag1 = true;
                }

                if (commandspreadplayers_a2.a(d1, d2, d3, d4)) {
                    flag1 = true;
                }
            }

            if (!flag1) {
                CommandSpreadPlayers.a[] acommandspreadplayers_a1 = acommandspreadplayers_a;
                int i1 = acommandspreadplayers_a.length;

                for (j = 0; j < i1; ++j) {
                    commandspreadplayers_a1 = acommandspreadplayers_a1[j];
                    if (!commandspreadplayers_a1.b(world)) {
                        commandspreadplayers_a1.a(random, d1, d2, d3, d4);
                        flag1 = true;
                    }
                }
            }
        }

        if (i >= 10000) {
            throw new CommandException("commands.spreadplayers.failure." + (flag ? "teams" : "players"), new Object[] { Integer.valueOf(acommandspreadplayers_a.length), Double.valueOf(commandspreadplayers_a.a), Double.valueOf(commandspreadplayers_a.b), String.format("%.2f", new Object[] { Double.valueOf(d5)})});
        } else {
            return i;
        }
    }

    private double a(List<Entity> list, World world, CommandSpreadPlayers.a[] acommandspreadplayers_a, boolean flag) {
        double d0 = 0.0D;
        int i = 0;
        HashMap hashmap = Maps.newHashMap();

        for (int j = 0; j < list.size(); ++j) {
            Entity entity = (Entity) list.get(j);
            CommandSpreadPlayers.a commandspreadplayers_a;

            if (flag) {
                ScoreboardTeamBase scoreboardteambase = entity instanceof EntityHuman ? ((EntityHuman) entity).getScoreboardTeam() : null;

                if (!hashmap.containsKey(scoreboardteambase)) {
                    hashmap.put(scoreboardteambase, acommandspreadplayers_a[i++]);
                }

                commandspreadplayers_a = (CommandSpreadPlayers.a) hashmap.get(scoreboardteambase);
            } else {
                commandspreadplayers_a = acommandspreadplayers_a[i++];
            }

            entity.enderTeleportTo((double) ((float) MathHelper.floor(commandspreadplayers_a.a) + 0.5F), (double) commandspreadplayers_a.a(world), (double) MathHelper.floor(commandspreadplayers_a.b) + 0.5D);
            double d1 = Double.MAX_VALUE;

            for (int k = 0; k < acommandspreadplayers_a.length; ++k) {
                if (commandspreadplayers_a != acommandspreadplayers_a[k]) {
                    double d2 = commandspreadplayers_a.a(acommandspreadplayers_a[k]);

                    d1 = Math.min(d2, d1);
                }
            }

            d0 += d1;
        }

        d0 /= (double) list.size();
        return d0;
    }

    private CommandSpreadPlayers.a[] a(Random random, int i, double d0, double d1, double d2, double d3) {
        CommandSpreadPlayers.a[] acommandspreadplayers_a = new CommandSpreadPlayers.a[i];

        for (int j = 0; j < acommandspreadplayers_a.length; ++j) {
            CommandSpreadPlayers.a commandspreadplayers_a = new CommandSpreadPlayers.a();

            commandspreadplayers_a.a(random, d0, d1, d2, d3);
            acommandspreadplayers_a[j] = commandspreadplayers_a;
        }

        return acommandspreadplayers_a;
    }

    public List<String> tabComplete(ICommandListener icommandlistener, String[] astring, BlockPosition blockposition) {
        return astring.length >= 1 && astring.length <= 2 ? b(astring, 0, blockposition) : null;
    }

    static class a {

        double a;
        double b;

        a() {}

        a(double d0, double d1) {
            this.a = d0;
            this.b = d1;
        }

        double a(CommandSpreadPlayers.a commandspreadplayers_a) {
            double d0 = this.a - commandspreadplayers_a.a;
            double d1 = this.b - commandspreadplayers_a.b;

            return Math.sqrt(d0 * d0 + d1 * d1);
        }

        void a() {
            double d0 = (double) this.b();

            this.a /= d0;
            this.b /= d0;
        }

        float b() {
            return MathHelper.sqrt(this.a * this.a + this.b * this.b);
        }

        public void b(CommandSpreadPlayers.a commandspreadplayers_a) {
            this.a -= commandspreadplayers_a.a;
            this.b -= commandspreadplayers_a.b;
        }

        public boolean a(double d0, double d1, double d2, double d3) {
            boolean flag = false;

            if (this.a < d0) {
                this.a = d0;
                flag = true;
            } else if (this.a > d2) {
                this.a = d2;
                flag = true;
            }

            if (this.b < d1) {
                this.b = d1;
                flag = true;
            } else if (this.b > d3) {
                this.b = d3;
                flag = true;
            }

            return flag;
        }

        public int a(World world) {
            BlockPosition blockposition = new BlockPosition(this.a, 256.0D, this.b);

            do {
                if (blockposition.getY() <= 0) {
                    return 257;
                }

                blockposition = blockposition.down();
            } while (world.getType(blockposition).getBlock().getMaterial() == Material.AIR);

            return blockposition.getY() + 1;
        }

        public boolean b(World world) {
            BlockPosition blockposition = new BlockPosition(this.a, 256.0D, this.b);

            Material material;

            do {
                if (blockposition.getY() <= 0) {
                    return false;
                }

                blockposition = blockposition.down();
                material = world.getType(blockposition).getBlock().getMaterial();
            } while (material == Material.AIR);

            return !material.isLiquid() && material != Material.FIRE;
        }

        public void a(Random random, double d0, double d1, double d2, double d3) {
            this.a = MathHelper.a(random, d0, d2);
            this.b = MathHelper.a(random, d1, d3);
        }
    }
}
