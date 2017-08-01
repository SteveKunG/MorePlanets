package stevekung.mods.moreplanets.init;

import net.minecraft.block.SoundType;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundEvent;
import stevekung.mods.moreplanets.util.helper.CommonRegisterHelper;

public class MPSounds
{
    public static SoundEvent BLACK_HOLE_CREATED = CommonRegisterHelper.registerSound("black_hole_created");
    public static SoundEvent BLACK_HOLE_DESTROYED = CommonRegisterHelper.registerSound("black_hole_destroyed");
    public static SoundEvent BLACK_HOLE_AMBIENT = CommonRegisterHelper.registerSound("black_hole_ambient");
    public static SoundEvent MACHINE_ACTIVATE_AMBIENT = CommonRegisterHelper.registerSound("machine_activate_ambient");
    public static SoundEvent MACHINE_GENERATOR_AMBIENT = CommonRegisterHelper.registerSound("machine_generator_ambient");
    public static SoundEvent MACHINE_ALERT = CommonRegisterHelper.registerSound("machine_alert");
    public static SoundEvent MACHINE_DANGER = CommonRegisterHelper.registerSound("machine_danger");
    public static SoundEvent MACHINE_START = CommonRegisterHelper.registerSound("machine_start");
    public static SoundEvent MACHINE_STOP = CommonRegisterHelper.registerSound("machine_stop");
    public static SoundEvent ALIEN_BEAM = CommonRegisterHelper.registerSound("alien_beam");
    public static SoundEvent LASER_SHOOTED = CommonRegisterHelper.registerSound("laser_shooted");
    public static SoundEvent ALIEN_EGG_DESTROYED = CommonRegisterHelper.registerSound("alien_egg_destroyed");
    public static SoundEvent ALIEN_MINER_AMBIENT = CommonRegisterHelper.registerSound("alien_miner_ambient");
    public static SoundEvent ALIEN_MINER_CHARGED = CommonRegisterHelper.registerSound("alien_miner_charged");
    public static SoundEvent ALIEN_MINER_ATTACK = CommonRegisterHelper.registerSound("alien_miner_attack");
    public static SoundEvent ALIEN_MINER_SHOCK = CommonRegisterHelper.registerSound("alien_miner_shock");
    public static SoundEvent INFECTED_MOB_ATTACK = CommonRegisterHelper.registerSound("infected_mob_attack");
    public static SoundEvent INFECTED_MOB_HURT = CommonRegisterHelper.registerSound("infected_mob_hurt");
    public static SoundEvent INFECTED_MOB_EXPLODE = CommonRegisterHelper.registerSound("infected_mob_explode");
    public static SoundEvent FRONOS_MOB_STEP = CommonRegisterHelper.registerSound("fronos_mob_step");
    public static SoundEvent FRONOS_MOB_AMBIENT = CommonRegisterHelper.registerSound("fronos_mob_ambient");
    public static SoundEvent FRONOS_MOB_HURT = CommonRegisterHelper.registerSound("fronos_mob_hurt");
    public static SoundEvent FRONOS_MOB_DEATH = CommonRegisterHelper.registerSound("fronos_mob_death");
    public static SoundEvent LOUD_THUNDER = CommonRegisterHelper.registerSound("loud_thunder");
    public static SoundEvent LILYPAD_PLACE = CommonRegisterHelper.registerSound("lilypad_place");
    public static SoundEvent UNLOCK_TREASURE_CHEST = CommonRegisterHelper.registerSound("unlock_treasure_chest");
    public static SoundEvent SHLIME_HURT = CommonRegisterHelper.registerSound("shlime_hurt");
    public static SoundEvent SHLIME_DEATH = CommonRegisterHelper.registerSound("shlime_death");
    public static SoundEvent SLIME_SMALL = CommonRegisterHelper.registerSound("slime_small");
    public static SoundEvent SMALL_SLIME_JUMP = CommonRegisterHelper.registerSound("slime_small_jump");

    public static SoundEvent A_PLANET_TO_CONQUER = CommonRegisterHelper.registerRecord("a_planet_to_conquer");

    public static SoundType ALIEN_EGG = new SoundType(1.0F, 1.0F, MPSounds.ALIEN_EGG_DESTROYED, SoundEvents.BLOCK_SLIME_STEP, SoundEvents.BLOCK_SLIME_PLACE, SoundEvents.BLOCK_SLIME_HIT, SoundEvents.BLOCK_SLIME_FALL);
    public static SoundType SMALL_SLIME = new SoundType(1.0F, 1.0F, MPSounds.SLIME_SMALL, SoundEvents.BLOCK_SLIME_STEP, MPSounds.SLIME_SMALL, SoundEvents.BLOCK_SLIME_HIT, SoundEvents.BLOCK_SLIME_FALL);
    public static SoundType LILYPAD = new SoundType(1.0F, 1.0F, SoundEvents.BLOCK_GRASS_BREAK, SoundEvents.BLOCK_GRASS_STEP, MPSounds.LILYPAD_PLACE, SoundEvents.BLOCK_GRASS_HIT, SoundEvents.BLOCK_GRASS_FALL);
}