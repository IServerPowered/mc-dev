package net.minecraft.server;

public class CommandObjectiveExecutor {

    private static final int a = CommandObjectiveExecutor.a.values().length;
    private static final String[] b = new String[CommandObjectiveExecutor.a];
    private String[] c;
    private String[] d;

    public CommandObjectiveExecutor() {
        this.c = CommandObjectiveExecutor.b;
        this.d = CommandObjectiveExecutor.b;
    }

    public void a(final ICommandListener icommandlistener, CommandObjectiveExecutor.a commandobjectiveexecutor_a, int i) {
        String s = this.c[commandobjectiveexecutor_a.a()];

        if (s != null) {
            ICommandListener icommandlistener1 = new ICommandListener() {
                public String getName() {
                    return icommandlistener.getName();
                }

                public IChatBaseComponent getScoreboardDisplayName() {
                    return icommandlistener.getScoreboardDisplayName();
                }

                public void sendMessage(IChatBaseComponent ichatbasecomponent) {
                    icommandlistener.sendMessage(ichatbasecomponent);
                }

                public boolean a(int i, String s) {
                    return true;
                }

                public BlockPosition getChunkCoordinates() {
                    return icommandlistener.getChunkCoordinates();
                }

                public Vec3D d() {
                    return icommandlistener.d();
                }

                public World getWorld() {
                    return icommandlistener.getWorld();
                }

                public Entity f() {
                    return icommandlistener.f();
                }

                public boolean getSendCommandFeedback() {
                    return icommandlistener.getSendCommandFeedback();
                }

                public void a(CommandObjectiveExecutor.a commandobjectiveexecutor_a, int i) {
                    icommandlistener.a(commandobjectiveexecutor_a, i);
                }
            };

            String s1;

            try {
                s1 = CommandAbstract.e(icommandlistener1, s);
            } catch (ExceptionEntityNotFound exceptionentitynotfound) {
                return;
            }

            String s2 = this.d[commandobjectiveexecutor_a.a()];

            if (s2 != null) {
                Scoreboard scoreboard = icommandlistener.getWorld().getScoreboard();
                ScoreboardObjective scoreboardobjective = scoreboard.getObjective(s2);

                if (scoreboardobjective != null) {
                    if (scoreboard.b(s1, scoreboardobjective)) {
                        ScoreboardScore scoreboardscore = scoreboard.getPlayerScoreForObjective(s1, scoreboardobjective);

                        scoreboardscore.setScore(i);
                    }
                }
            }
        }
    }

    public void a(NBTTagCompound nbttagcompound) {
        if (nbttagcompound.hasKeyOfType("CommandStats", 10)) {
            NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("CommandStats");
            CommandObjectiveExecutor.a[] acommandobjectiveexecutor_a = CommandObjectiveExecutor.a.values();
            int i = acommandobjectiveexecutor_a.length;

            for (int j = 0; j < i; ++j) {
                CommandObjectiveExecutor.a commandobjectiveexecutor_a = acommandobjectiveexecutor_a[j];
                String s = commandobjectiveexecutor_a.b() + "Name";
                String s1 = commandobjectiveexecutor_a.b() + "Objective";

                if (nbttagcompound1.hasKeyOfType(s, 8) && nbttagcompound1.hasKeyOfType(s1, 8)) {
                    String s2 = nbttagcompound1.getString(s);
                    String s3 = nbttagcompound1.getString(s1);

                    a(this, commandobjectiveexecutor_a, s2, s3);
                }
            }

        }
    }

    public void b(NBTTagCompound nbttagcompound) {
        NBTTagCompound nbttagcompound1 = new NBTTagCompound();
        CommandObjectiveExecutor.a[] acommandobjectiveexecutor_a = CommandObjectiveExecutor.a.values();
        int i = acommandobjectiveexecutor_a.length;

        for (int j = 0; j < i; ++j) {
            CommandObjectiveExecutor.a commandobjectiveexecutor_a = acommandobjectiveexecutor_a[j];
            String s = this.c[commandobjectiveexecutor_a.a()];
            String s1 = this.d[commandobjectiveexecutor_a.a()];

            if (s != null && s1 != null) {
                nbttagcompound1.setString(commandobjectiveexecutor_a.b() + "Name", s);
                nbttagcompound1.setString(commandobjectiveexecutor_a.b() + "Objective", s1);
            }
        }

        if (!nbttagcompound1.isEmpty()) {
            nbttagcompound.set("CommandStats", nbttagcompound1);
        }

    }

    public static void a(CommandObjectiveExecutor commandobjectiveexecutor, CommandObjectiveExecutor.a commandobjectiveexecutor_a, String s, String s1) {
        if (s != null && s.length() != 0 && s1 != null && s1.length() != 0) {
            if (commandobjectiveexecutor.c == CommandObjectiveExecutor.b || commandobjectiveexecutor.d == CommandObjectiveExecutor.b) {
                commandobjectiveexecutor.c = new String[CommandObjectiveExecutor.a];
                commandobjectiveexecutor.d = new String[CommandObjectiveExecutor.a];
            }

            commandobjectiveexecutor.c[commandobjectiveexecutor_a.a()] = s;
            commandobjectiveexecutor.d[commandobjectiveexecutor_a.a()] = s1;
        } else {
            a(commandobjectiveexecutor, commandobjectiveexecutor_a);
        }
    }

    private static void a(CommandObjectiveExecutor commandobjectiveexecutor, CommandObjectiveExecutor.a commandobjectiveexecutor_a) {
        if (commandobjectiveexecutor.c != CommandObjectiveExecutor.b && commandobjectiveexecutor.d != CommandObjectiveExecutor.b) {
            commandobjectiveexecutor.c[commandobjectiveexecutor_a.a()] = null;
            commandobjectiveexecutor.d[commandobjectiveexecutor_a.a()] = null;
            boolean flag = true;
            CommandObjectiveExecutor.a[] acommandobjectiveexecutor_a = CommandObjectiveExecutor.a.values();
            int i = acommandobjectiveexecutor_a.length;

            for (int j = 0; j < i; ++j) {
                CommandObjectiveExecutor.a commandobjectiveexecutor_a1 = acommandobjectiveexecutor_a[j];

                if (commandobjectiveexecutor.c[commandobjectiveexecutor_a1.a()] != null && commandobjectiveexecutor.d[commandobjectiveexecutor_a1.a()] != null) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                commandobjectiveexecutor.c = CommandObjectiveExecutor.b;
                commandobjectiveexecutor.d = CommandObjectiveExecutor.b;
            }

        }
    }

    public void a(CommandObjectiveExecutor commandobjectiveexecutor) {
        CommandObjectiveExecutor.a[] acommandobjectiveexecutor_a = CommandObjectiveExecutor.a.values();
        int i = acommandobjectiveexecutor_a.length;

        for (int j = 0; j < i; ++j) {
            CommandObjectiveExecutor.a commandobjectiveexecutor_a = acommandobjectiveexecutor_a[j];

            a(this, commandobjectiveexecutor_a, commandobjectiveexecutor.c[commandobjectiveexecutor_a.a()], commandobjectiveexecutor.d[commandobjectiveexecutor_a.a()]);
        }

    }

    public static enum a {

        SUCCESS_COUNT(0, "SuccessCount"), AFFECTED_BLOCKS(1, "AffectedBlocks"), AFFECTED_ENTITIES(2, "AffectedEntities"), AFFECTED_ITEMS(3, "AffectedItems"), QUERY_RESULT(4, "QueryResult");

        final int f;
        final String g;

        private a(int i, String s) {
            this.f = i;
            this.g = s;
        }

        public int a() {
            return this.f;
        }

        public String b() {
            return this.g;
        }

        public static String[] c() {
            String[] astring = new String[values().length];
            int i = 0;
            CommandObjectiveExecutor.a[] acommandobjectiveexecutor_a = values();
            int j = acommandobjectiveexecutor_a.length;

            for (int k = 0; k < j; ++k) {
                CommandObjectiveExecutor.a commandobjectiveexecutor_a = acommandobjectiveexecutor_a[k];

                astring[i++] = commandobjectiveexecutor_a.b();
            }

            return astring;
        }

        public static CommandObjectiveExecutor.a a(String s) {
            CommandObjectiveExecutor.a[] acommandobjectiveexecutor_a = values();
            int i = acommandobjectiveexecutor_a.length;

            for (int j = 0; j < i; ++j) {
                CommandObjectiveExecutor.a commandobjectiveexecutor_a = acommandobjectiveexecutor_a[j];

                if (commandobjectiveexecutor_a.b().equals(s)) {
                    return commandobjectiveexecutor_a;
                }
            }

            return null;
        }
    }
}
