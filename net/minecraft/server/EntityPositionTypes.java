package net.minecraft.server;

import com.google.common.collect.Maps;
import java.util.HashMap;

public class EntityPositionTypes {

    private static final HashMap<Class, EntityInsentient.a> a = Maps.newHashMap();

    public static EntityInsentient.a a(Class oclass) {
        return (EntityInsentient.a) EntityPositionTypes.a.get(oclass);
    }

    static {
        EntityPositionTypes.a.put(EntityBat.class, EntityInsentient.a.ON_GROUND);
        EntityPositionTypes.a.put(EntityChicken.class, EntityInsentient.a.ON_GROUND);
        EntityPositionTypes.a.put(EntityCow.class, EntityInsentient.a.ON_GROUND);
        EntityPositionTypes.a.put(EntityHorse.class, EntityInsentient.a.ON_GROUND);
        EntityPositionTypes.a.put(EntityMushroomCow.class, EntityInsentient.a.ON_GROUND);
        EntityPositionTypes.a.put(EntityOcelot.class, EntityInsentient.a.ON_GROUND);
        EntityPositionTypes.a.put(EntityPig.class, EntityInsentient.a.ON_GROUND);
        EntityPositionTypes.a.put(EntityRabbit.class, EntityInsentient.a.ON_GROUND);
        EntityPositionTypes.a.put(EntitySheep.class, EntityInsentient.a.ON_GROUND);
        EntityPositionTypes.a.put(EntitySnowman.class, EntityInsentient.a.ON_GROUND);
        EntityPositionTypes.a.put(EntitySquid.class, EntityInsentient.a.IN_WATER);
        EntityPositionTypes.a.put(EntityIronGolem.class, EntityInsentient.a.ON_GROUND);
        EntityPositionTypes.a.put(EntityWolf.class, EntityInsentient.a.ON_GROUND);
        EntityPositionTypes.a.put(EntityVillager.class, EntityInsentient.a.ON_GROUND);
        EntityPositionTypes.a.put(EntityEnderDragon.class, EntityInsentient.a.ON_GROUND);
        EntityPositionTypes.a.put(EntityWither.class, EntityInsentient.a.ON_GROUND);
        EntityPositionTypes.a.put(EntityBlaze.class, EntityInsentient.a.ON_GROUND);
        EntityPositionTypes.a.put(EntityCaveSpider.class, EntityInsentient.a.ON_GROUND);
        EntityPositionTypes.a.put(EntityCreeper.class, EntityInsentient.a.ON_GROUND);
        EntityPositionTypes.a.put(EntityEnderman.class, EntityInsentient.a.ON_GROUND);
        EntityPositionTypes.a.put(EntityEndermite.class, EntityInsentient.a.ON_GROUND);
        EntityPositionTypes.a.put(EntityGhast.class, EntityInsentient.a.ON_GROUND);
        EntityPositionTypes.a.put(EntityGiantZombie.class, EntityInsentient.a.ON_GROUND);
        EntityPositionTypes.a.put(EntityGuardian.class, EntityInsentient.a.IN_WATER);
        EntityPositionTypes.a.put(EntityMagmaCube.class, EntityInsentient.a.ON_GROUND);
        EntityPositionTypes.a.put(EntityPigZombie.class, EntityInsentient.a.ON_GROUND);
        EntityPositionTypes.a.put(EntitySilverfish.class, EntityInsentient.a.ON_GROUND);
        EntityPositionTypes.a.put(EntitySkeleton.class, EntityInsentient.a.ON_GROUND);
        EntityPositionTypes.a.put(EntitySlime.class, EntityInsentient.a.ON_GROUND);
        EntityPositionTypes.a.put(EntitySpider.class, EntityInsentient.a.ON_GROUND);
        EntityPositionTypes.a.put(EntityWitch.class, EntityInsentient.a.ON_GROUND);
        EntityPositionTypes.a.put(EntityZombie.class, EntityInsentient.a.ON_GROUND);
    }
}
