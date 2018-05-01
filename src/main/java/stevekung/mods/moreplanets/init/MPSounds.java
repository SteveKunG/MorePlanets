package stevekung.mods.moreplanets.init;

import net.minecraft.block.SoundType;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundEvent;
import stevekung.mods.moreplanets.core.MorePlanetsMod;

public class MPSounds
{
    public static final SoundEvent BLACK_HOLE_CREATED = MorePlanetsMod.COMMON_REGISTRY.registerSound("black_hole_created");
    public static final SoundEvent BLACK_HOLE_DESTROYED = MorePlanetsMod.COMMON_REGISTRY.registerSound("black_hole_destroyed");
    public static final SoundEvent BLACK_HOLE_AMBIENT = MorePlanetsMod.COMMON_REGISTRY.registerSound("black_hole_ambient");
    public static final SoundEvent MACHINE_ACTIVATE_AMBIENT = MorePlanetsMod.COMMON_REGISTRY.registerSound("machine_activate_ambient");
    public static final SoundEvent MACHINE_GENERATOR_AMBIENT = MorePlanetsMod.COMMON_REGISTRY.registerSound("machine_generator_ambient");
    public static final SoundEvent MACHINE_ALERT = MorePlanetsMod.COMMON_REGISTRY.registerSound("machine_alert");
    public static final SoundEvent MACHINE_DANGER = MorePlanetsMod.COMMON_REGISTRY.registerSound("machine_danger");
    public static final SoundEvent MACHINE_START = MorePlanetsMod.COMMON_REGISTRY.registerSound("machine_start");
    public static final SoundEvent MACHINE_STOP = MorePlanetsMod.COMMON_REGISTRY.registerSound("machine_stop");
    public static final SoundEvent ALIEN_BEAM = MorePlanetsMod.COMMON_REGISTRY.registerSound("alien_beam");
    public static final SoundEvent LASER_SHOOTED = MorePlanetsMod.COMMON_REGISTRY.registerSound("laser_shooted");
    public static final SoundEvent ALIEN_EGG_DESTROYED = MorePlanetsMod.COMMON_REGISTRY.registerSound("alien_egg_destroyed");
    public static final SoundEvent ALIEN_MINER_AMBIENT = MorePlanetsMod.COMMON_REGISTRY.registerSound("alien_miner_ambient");
    public static final SoundEvent ALIEN_MINER_HURT = MorePlanetsMod.COMMON_REGISTRY.registerSound("alien_miner_hurt");
    public static final SoundEvent ALIEN_MINER_DEATH = MorePlanetsMod.COMMON_REGISTRY.registerSound("alien_miner_death");
    public static final SoundEvent ALIEN_MINER_CHARGED = MorePlanetsMod.COMMON_REGISTRY.registerSound("alien_miner_charged");
    public static final SoundEvent ALIEN_MINER_ATTACK = MorePlanetsMod.COMMON_REGISTRY.registerSound("alien_miner_attack");
    public static final SoundEvent ALIEN_MINER_SHOCK = MorePlanetsMod.COMMON_REGISTRY.registerSound("alien_miner_shock");
    public static final SoundEvent INFECTED_MOB_ATTACK = MorePlanetsMod.COMMON_REGISTRY.registerSound("infected_mob_attack");
    public static final SoundEvent INFECTED_MOB_HURT = MorePlanetsMod.COMMON_REGISTRY.registerSound("infected_mob_hurt");
    public static final SoundEvent INFECTED_MOB_EXPLODE = MorePlanetsMod.COMMON_REGISTRY.registerSound("infected_mob_explode");
    public static final SoundEvent FRONOS_MOB_STEP = MorePlanetsMod.COMMON_REGISTRY.registerSound("fronos_mob_step");
    public static final SoundEvent FRONOS_MOB_AMBIENT = MorePlanetsMod.COMMON_REGISTRY.registerSound("fronos_mob_ambient");
    public static final SoundEvent FRONOS_MOB_HURT = MorePlanetsMod.COMMON_REGISTRY.registerSound("fronos_mob_hurt");
    public static final SoundEvent FRONOS_MOB_DEATH = MorePlanetsMod.COMMON_REGISTRY.registerSound("fronos_mob_death");
    public static final SoundEvent LOUD_THUNDER = MorePlanetsMod.COMMON_REGISTRY.registerSound("loud_thunder");
    public static final SoundEvent LILYPAD_PLACE = MorePlanetsMod.COMMON_REGISTRY.registerSound("lilypad_place");
    public static final SoundEvent UNLOCK_TREASURE_CHEST = MorePlanetsMod.COMMON_REGISTRY.registerSound("unlock_treasure_chest");
    public static final SoundEvent SHLIME_HURT = MorePlanetsMod.COMMON_REGISTRY.registerSound("shlime_hurt");
    public static final SoundEvent SHLIME_DEATH = MorePlanetsMod.COMMON_REGISTRY.registerSound("shlime_death");
    public static final SoundEvent SLIME_SMALL_PLACE = MorePlanetsMod.COMMON_REGISTRY.registerSound("slime_small_place");
    public static final SoundEvent SLIME_SMALL_DESTROYED = MorePlanetsMod.COMMON_REGISTRY.registerSound("slime_small_destroy");
    public static final SoundEvent SMALL_SLIME_JUMP = MorePlanetsMod.COMMON_REGISTRY.registerSound("slime_small_jump");

    public static final SoundEvent A_PLANET_TO_CONQUER = MorePlanetsMod.COMMON_REGISTRY.registerRecord("a_planet_to_conquer");

    public static final SoundType ALIEN_EGG = new SoundType(1.0F, 1.0F, MPSounds.ALIEN_EGG_DESTROYED, SoundEvents.BLOCK_SLIME_STEP, SoundEvents.BLOCK_SLIME_PLACE, SoundEvents.BLOCK_SLIME_HIT, SoundEvents.BLOCK_SLIME_FALL);
    public static final SoundType SMALL_SLIME = new SoundType(1.0F, 1.0F, MPSounds.SLIME_SMALL_DESTROYED, SoundEvents.BLOCK_SLIME_STEP, MPSounds.SLIME_SMALL_PLACE, SoundEvents.BLOCK_SLIME_HIT, SoundEvents.BLOCK_SLIME_FALL);
    public static final SoundType LILYPAD = new SoundType(1.0F, 1.0F, SoundEvents.BLOCK_GRASS_BREAK, SoundEvents.BLOCK_GRASS_STEP, MPSounds.LILYPAD_PLACE, SoundEvents.BLOCK_GRASS_HIT, SoundEvents.BLOCK_GRASS_FALL);
}