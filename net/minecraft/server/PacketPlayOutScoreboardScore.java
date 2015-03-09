package net.minecraft.server;

import java.io.IOException;

public class PacketPlayOutScoreboardScore implements Packet<PacketListenerPlayOut> {

    private String a = "";
    private String b = "";
    private int c;
    private PacketPlayOutScoreboardScore.a d;

    public PacketPlayOutScoreboardScore() {}

    public PacketPlayOutScoreboardScore(ScoreboardScore scoreboardscore) {
        this.a = scoreboardscore.getPlayerName();
        this.b = scoreboardscore.getObjective().getName();
        this.c = scoreboardscore.getScore();
        this.d = PacketPlayOutScoreboardScore.a.CHANGE;
    }

    public PacketPlayOutScoreboardScore(String s) {
        this.a = s;
        this.b = "";
        this.c = 0;
        this.d = PacketPlayOutScoreboardScore.a.REMOVE;
    }

    public PacketPlayOutScoreboardScore(String s, ScoreboardObjective scoreboardobjective) {
        this.a = s;
        this.b = scoreboardobjective.getName();
        this.c = 0;
        this.d = PacketPlayOutScoreboardScore.a.REMOVE;
    }

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.c(40);
        this.d = (PacketPlayOutScoreboardScore.a) packetdataserializer.a(PacketPlayOutScoreboardScore.a.class);
        this.b = packetdataserializer.c(16);
        if (this.d != PacketPlayOutScoreboardScore.a.REMOVE) {
            this.c = packetdataserializer.e();
        }

    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.a(this.a);
        packetdataserializer.a((Enum) this.d);
        packetdataserializer.a(this.b);
        if (this.d != PacketPlayOutScoreboardScore.a.REMOVE) {
            packetdataserializer.b(this.c);
        }

    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayOut) packetlistener);
    }

    public static enum a {

        CHANGE, REMOVE;

        private a() {}
    }
}
