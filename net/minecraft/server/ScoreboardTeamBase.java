package net.minecraft.server;

import com.google.common.collect.Maps;
import java.util.Collection;
import java.util.Map;

public abstract class ScoreboardTeamBase {

    public ScoreboardTeamBase() {}

    public boolean isAlly(ScoreboardTeamBase scoreboardteambase) {
        return scoreboardteambase == null ? false : this == scoreboardteambase;
    }

    public abstract String getName();

    public abstract String getFormattedName(String s);

    public abstract boolean allowFriendlyFire();

    public abstract Collection<String> getPlayerNameSet();

    public abstract ScoreboardTeamBase.a j();

    public static enum a {

        ALWAYS("always", 0), NEVER("never", 1), HIDE_FOR_OTHER_TEAMS("hideForOtherTeams", 2), HIDE_FOR_OWN_TEAM("hideForOwnTeam", 3);

        private static Map<String, ScoreboardTeamBase.a> g = Maps.newHashMap();
        public final String e;
        public final int f;

        public static String[] a() {
            return (String[]) ScoreboardTeamBase.a.g.keySet().toArray(new String[ScoreboardTeamBase.a.g.size()]);
        }

        public static ScoreboardTeamBase.a a(String s) {
            return (ScoreboardTeamBase.a) ScoreboardTeamBase.a.g.get(s);
        }

        private a(String s, int i) {
            this.e = s;
            this.f = i;
        }

        static {
            ScoreboardTeamBase.a[] ascoreboardteambase_a = values();
            int i = ascoreboardteambase_a.length;

            for (int j = 0; j < i; ++j) {
                ScoreboardTeamBase.a scoreboardteambase_a = ascoreboardteambase_a[j];

                ScoreboardTeamBase.a.g.put(scoreboardteambase_a.e, scoreboardteambase_a);
            }

        }
    }
}
