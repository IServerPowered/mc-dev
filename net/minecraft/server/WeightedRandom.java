package net.minecraft.server;

import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

public class WeightedRandom {

    public static int a(Collection<? extends WeightedRandom.a> collection) {
        int i = 0;

        WeightedRandom.a weightedrandom_a;

        for (Iterator iterator = collection.iterator(); iterator.hasNext(); i += weightedrandom_a.a) {
            weightedrandom_a = (WeightedRandom.a) iterator.next();
        }

        return i;
    }

    public static <T extends WeightedRandom.a> T a(Random random, Collection<T> collection, int i) {
        if (i <= 0) {
            throw new IllegalArgumentException();
        } else {
            int j = random.nextInt(i);

            return a(collection, j);
        }
    }

    public static <T extends WeightedRandom.a> T a(Collection<T> collection, int i) {
        Iterator iterator = collection.iterator();

        WeightedRandom.a weightedrandom_a;

        do {
            if (!iterator.hasNext()) {
                return null;
            }

            weightedrandom_a = (WeightedRandom.a) iterator.next();
            i -= weightedrandom_a.a;
        } while (i >= 0);

        return weightedrandom_a;
    }

    public static <T extends WeightedRandom.a> T a(Random random, Collection<T> collection) {
        return a(random, collection, a(collection));
    }

    public static class a {

        protected int a;

        public a(int i) {
            this.a = i;
        }
    }
}
