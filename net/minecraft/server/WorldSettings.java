package net.minecraft.server;

public final class WorldSettings {

    private final long a;
    private final WorldSettings.a b;
    private final boolean c;
    private final boolean d;
    private final WorldType e;
    private boolean f;
    private boolean g;
    private String h;

    public WorldSettings(long i, WorldSettings.a worldsettings_a, boolean flag, boolean flag1, WorldType worldtype) {
        this.h = "";
        this.a = i;
        this.b = worldsettings_a;
        this.c = flag;
        this.d = flag1;
        this.e = worldtype;
    }

    public WorldSettings(WorldData worlddata) {
        this(worlddata.getSeed(), worlddata.getGameType(), worlddata.shouldGenerateMapFeatures(), worlddata.isHardcore(), worlddata.getType());
    }

    public WorldSettings a() {
        this.g = true;
        return this;
    }

    public WorldSettings setGeneratorSettings(String s) {
        this.h = s;
        return this;
    }

    public boolean c() {
        return this.g;
    }

    public long d() {
        return this.a;
    }

    public WorldSettings.a e() {
        return this.b;
    }

    public boolean f() {
        return this.d;
    }

    public boolean g() {
        return this.c;
    }

    public WorldType h() {
        return this.e;
    }

    public boolean i() {
        return this.f;
    }

    public static WorldSettings.a a(int i) {
        return WorldSettings.a.getById(i);
    }

    public String j() {
        return this.h;
    }

    public static enum a {

        NOT_SET(-1, ""), SURVIVAL(0, "survival"), CREATIVE(1, "creative"), ADVENTURE(2, "adventure"), SPECTATOR(3, "spectator");

        int f;
        String g;

        private a(int i, String s) {
            this.f = i;
            this.g = s;
        }

        public int getId() {
            return this.f;
        }

        public String b() {
            return this.g;
        }

        public void a(PlayerAbilities playerabilities) {
            if (this == WorldSettings.a.CREATIVE) {
                playerabilities.canFly = true;
                playerabilities.canInstantlyBuild = true;
                playerabilities.isInvulnerable = true;
            } else if (this == WorldSettings.a.SPECTATOR) {
                playerabilities.canFly = true;
                playerabilities.canInstantlyBuild = false;
                playerabilities.isInvulnerable = true;
                playerabilities.isFlying = true;
            } else {
                playerabilities.canFly = false;
                playerabilities.canInstantlyBuild = false;
                playerabilities.isInvulnerable = false;
                playerabilities.isFlying = false;
            }

            playerabilities.mayBuild = !this.c();
        }

        public boolean c() {
            return this == WorldSettings.a.ADVENTURE || this == WorldSettings.a.SPECTATOR;
        }

        public boolean d() {
            return this == WorldSettings.a.CREATIVE;
        }

        public boolean e() {
            return this == WorldSettings.a.SURVIVAL || this == WorldSettings.a.ADVENTURE;
        }

        public static WorldSettings.a getById(int i) {
            WorldSettings.a[] aworldsettings_a = values();
            int j = aworldsettings_a.length;

            for (int k = 0; k < j; ++k) {
                WorldSettings.a worldsettings_a = aworldsettings_a[k];

                if (worldsettings_a.f == i) {
                    return worldsettings_a;
                }
            }

            return WorldSettings.a.SURVIVAL;
        }
    }
}
