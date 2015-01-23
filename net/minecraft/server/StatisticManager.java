package net.minecraft.server;

import com.google.common.collect.Maps;
import java.util.Map;

public class StatisticManager {

    protected final Map a = Maps.newConcurrentMap();

    public boolean hasAchievement(Achievement achievement) {
        return this.getStatisticValue(achievement) > 0;
    }

    public boolean b(Achievement achievement) {
        return achievement.c == null || this.hasAchievement(achievement.c);
    }

    public void b(EntityHuman entityhuman, Statistic statistic, int i) {
        if (!statistic.d() || this.b((Achievement) statistic)) {
            this.setStatistic(entityhuman, statistic, this.getStatisticValue(statistic) + i);
        }
    }

    public void setStatistic(EntityHuman entityhuman, Statistic statistic, int i) {
        StatisticWrapper statisticwrapper = (StatisticWrapper) this.a.get(statistic);

        if (statisticwrapper == null) {
            statisticwrapper = new StatisticWrapper();
            this.a.put(statistic, statisticwrapper);
        }

        statisticwrapper.a(i);
    }

    public int getStatisticValue(Statistic statistic) {
        StatisticWrapper statisticwrapper = (StatisticWrapper) this.a.get(statistic);

        return statisticwrapper == null ? 0 : statisticwrapper.a();
    }

    public IJsonStatistic b(Statistic statistic) {
        StatisticWrapper statisticwrapper = (StatisticWrapper) this.a.get(statistic);

        return statisticwrapper != null ? statisticwrapper.b() : null;
    }

    public IJsonStatistic a(Statistic statistic, IJsonStatistic ijsonstatistic) {
        StatisticWrapper statisticwrapper = (StatisticWrapper) this.a.get(statistic);

        if (statisticwrapper == null) {
            statisticwrapper = new StatisticWrapper();
            this.a.put(statistic, statisticwrapper);
        }

        statisticwrapper.a(ijsonstatistic);
        return ijsonstatistic;
    }
}
