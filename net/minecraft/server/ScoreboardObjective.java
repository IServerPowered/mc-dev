package net.minecraft.server;

public class ScoreboardObjective {

    private final Scoreboard a;
    private final String b;
    private final IScoreboardCriteria c;
    private IScoreboardCriteria.a d;
    private String e;

    public ScoreboardObjective(Scoreboard scoreboard, String s, IScoreboardCriteria iscoreboardcriteria) {
        this.a = scoreboard;
        this.b = s;
        this.c = iscoreboardcriteria;
        this.e = s;
        this.d = iscoreboardcriteria.c();
    }

    public String getName() {
        return this.b;
    }

    public IScoreboardCriteria getCriteria() {
        return this.c;
    }

    public String getDisplayName() {
        return this.e;
    }

    public void setDisplayName(String s) {
        this.e = s;
        this.a.handleObjectiveChanged(this);
    }

    public IScoreboardCriteria.a e() {
        return this.d;
    }

    public void a(IScoreboardCriteria.a iscoreboardcriteria_a) {
        this.d = iscoreboardcriteria_a;
        this.a.handleObjectiveChanged(this);
    }
}
