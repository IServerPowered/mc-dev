package net.minecraft.server;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PathfinderGoalSelector {

    private static final Logger a = LogManager.getLogger();
    private List<PathfinderGoalSelector.a> b = Lists.newArrayList();
    private List<PathfinderGoalSelector.a> c = Lists.newArrayList();
    private final MethodProfiler d;
    private int e;
    private int f = 3;

    public PathfinderGoalSelector(MethodProfiler methodprofiler) {
        this.d = methodprofiler;
    }

    public void a(int i, PathfinderGoal pathfindergoal) {
        this.b.add(new PathfinderGoalSelector.a(i, pathfindergoal));
    }

    public void a(PathfinderGoal pathfindergoal) {
        Iterator iterator = this.b.iterator();

        while (iterator.hasNext()) {
            PathfinderGoalSelector.a pathfindergoalselector_a = (PathfinderGoalSelector.a) iterator.next();
            PathfinderGoal pathfindergoal1 = pathfindergoalselector_a.a;

            if (pathfindergoal1 == pathfindergoal) {
                if (this.c.contains(pathfindergoalselector_a)) {
                    pathfindergoal1.d();
                    this.c.remove(pathfindergoalselector_a);
                }

                iterator.remove();
            }
        }

    }

    public void a() {
        this.d.a("goalSetup");
        Iterator iterator;
        PathfinderGoalSelector.a pathfindergoalselector_a;

        if (this.e++ % this.f == 0) {
            iterator = this.b.iterator();

            while (iterator.hasNext()) {
                pathfindergoalselector_a = (PathfinderGoalSelector.a) iterator.next();
                boolean flag = this.c.contains(pathfindergoalselector_a);

                if (flag) {
                    if (this.b(pathfindergoalselector_a) && this.a(pathfindergoalselector_a)) {
                        continue;
                    }

                    pathfindergoalselector_a.a.d();
                    this.c.remove(pathfindergoalselector_a);
                }

                if (this.b(pathfindergoalselector_a) && pathfindergoalselector_a.a.a()) {
                    pathfindergoalselector_a.a.c();
                    this.c.add(pathfindergoalselector_a);
                }
            }
        } else {
            iterator = this.c.iterator();

            while (iterator.hasNext()) {
                pathfindergoalselector_a = (PathfinderGoalSelector.a) iterator.next();
                if (!this.a(pathfindergoalselector_a)) {
                    pathfindergoalselector_a.a.d();
                    iterator.remove();
                }
            }
        }

        this.d.b();
        this.d.a("goalTick");
        iterator = this.c.iterator();

        while (iterator.hasNext()) {
            pathfindergoalselector_a = (PathfinderGoalSelector.a) iterator.next();
            pathfindergoalselector_a.a.e();
        }

        this.d.b();
    }

    private boolean a(PathfinderGoalSelector.a pathfindergoalselector_a) {
        boolean flag = pathfindergoalselector_a.a.b();

        return flag;
    }

    private boolean b(PathfinderGoalSelector.a pathfindergoalselector_a) {
        Iterator iterator = this.b.iterator();

        while (iterator.hasNext()) {
            PathfinderGoalSelector.a pathfindergoalselector_a1 = (PathfinderGoalSelector.a) iterator.next();

            if (pathfindergoalselector_a1 != pathfindergoalselector_a) {
                if (pathfindergoalselector_a.b >= pathfindergoalselector_a1.b) {
                    if (!this.a(pathfindergoalselector_a, pathfindergoalselector_a1) && this.c.contains(pathfindergoalselector_a1)) {
                        return false;
                    }
                } else if (!pathfindergoalselector_a1.a.i() && this.c.contains(pathfindergoalselector_a1)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean a(PathfinderGoalSelector.a pathfindergoalselector_a, PathfinderGoalSelector.a pathfindergoalselector_a1) {
        return (pathfindergoalselector_a.a.j() & pathfindergoalselector_a1.a.j()) == 0;
    }

    class a {

        public PathfinderGoal a;
        public int b;

        public a(int i, PathfinderGoal pathfindergoal) {
            this.b = i;
            this.a = pathfindergoal;
        }
    }
}
