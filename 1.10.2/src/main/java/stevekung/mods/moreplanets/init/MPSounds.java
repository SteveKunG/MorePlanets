package stevekung.mods.moreplanets.init;

import net.minecraft.block.SoundType;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundEvent;
import stevekung.mods.moreplanets.util.helper.CommonRegisterHelper;

public class MPSounds
{
    public static SoundEvent BLACK_HOLE_CREATED;
    public static SoundEvent BLACK_HOLE_DESTROYED;
    public static SoundEvent BLACK_HOLE_AMBIENT;
    public static SoundEvent MACHINE_ACTIVATE_AMBIENT;
    public static SoundEvent MACHINE_GENERATOR_AMBIENT;
    public static SoundEvent MACHINE_ALERT;
    public static SoundEvent MACHINE_DANGER;
    public static SoundEvent MACHINE_STOP;
    public static SoundEvent ALIEN_BEAM;
    public static SoundEvent LASER_SHOOTED;
    public static SoundEvent ALIEN_EGG_DESTROYED;
    public static SoundEvent ALIEN_MINER_AMBIENT;
    public static SoundEvent ALIEN_MINER_CHARGED;
    public static SoundEvent ALIEN_MINER_ATTACK;
    public static SoundEvent ALIEN_MINER_SHOCK;
    public static SoundEvent INFECTED_MOB_ATTACK;
    public static SoundEvent INFECTED_MOB_HURT;
    public static SoundEvent INFECTED_MOB_EXPLODE;
    public static SoundEvent FRONOS_MOB_STEP;
    public static SoundEvent FRONOS_MOB_AMBIENT;
    public static SoundEvent FRONOS_MOB_HURT;
    public static SoundEvent FRONOS_MOB_DEATH;
    public static SoundEvent LOUD_THUNDER;
    public static SoundEvent LILYPAD_PLACE;
    public static SoundEvent UNLOCK_TREASURE_CHEST;
    public static SoundEvent SHLIME_HURT;
    public static SoundEvent SHLIME_DEATH;
    public static SoundType ALIEN_EGG;
    public static SoundType SMALL_SLIME;
    public static SoundType LILYPAD;

    public static void init()
    {
        MPSounds.BLACK_HOLE_CREATED = CommonRegisterHelper.registerSound("black_hole_created");
        MPSounds.BLACK_HOLE_DESTROYED = CommonRegisterHelper.registerSound("black_hole_destroyed");
        MPSounds.BLACK_HOLE_AMBIENT = CommonRegisterHelper.registerSound("black_hole_ambient");
        MPSounds.MACHINE_ACTIVATE_AMBIENT = CommonRegisterHelper.registerSound("machine_activate_ambient");
        MPSounds.MACHINE_GENERATOR_AMBIENT = CommonRegisterHelper.registerSound("machine_generator_ambient");
        MPSounds.MACHINE_ALERT = CommonRegisterHelper.registerSound("machine_alert");
        MPSounds.MACHINE_DANGER = CommonRegisterHelper.registerSound("machine_danger");
        MPSounds.MACHINE_STOP = CommonRegisterHelper.registerSound("machine_stop");
        MPSounds.ALIEN_BEAM = CommonRegisterHelper.registerSound("alien_beam");
        MPSounds.LASER_SHOOTED = CommonRegisterHelper.registerSound("laser_shooted");
        MPSounds.ALIEN_EGG_DESTROYED = CommonRegisterHelper.registerSound("alien_egg_destroyed");
        MPSounds.ALIEN_MINER_AMBIENT = CommonRegisterHelper.registerSound("alien_miner_ambient");
        MPSounds.ALIEN_MINER_CHARGED = CommonRegisterHelper.registerSound("alien_miner_charged");
        MPSounds.ALIEN_MINER_ATTACK = CommonRegisterHelper.registerSound("alien_miner_attack");
        MPSounds.ALIEN_MINER_SHOCK = CommonRegisterHelper.registerSound("alien_miner_shock");
        MPSounds.INFECTED_MOB_ATTACK = CommonRegisterHelper.registerSound("infected_mob_attack");
        MPSounds.INFECTED_MOB_HURT = CommonRegisterHelper.registerSound("infected_mob_hurt");
        MPSounds.INFECTED_MOB_EXPLODE = CommonRegisterHelper.registerSound("infected_mob_explode");
        MPSounds.FRONOS_MOB_STEP = CommonRegisterHelper.registerSound("fronos_mob_step");
        MPSounds.FRONOS_MOB_AMBIENT = CommonRegisterHelper.registerSound("fronos_mob_ambient");
        MPSounds.FRONOS_MOB_HURT = CommonRegisterHelper.registerSound("fronos_mob_hurt");
        MPSounds.FRONOS_MOB_DEATH = CommonRegisterHelper.registerSound("fronos_mob_death");
        MPSounds.LOUD_THUNDER = CommonRegisterHelper.registerSound("loud_thunder");
        MPSounds.LILYPAD_PLACE = CommonRegisterHelper.registerSound("lilypad_place");
        MPSounds.UNLOCK_TREASURE_CHEST = CommonRegisterHelper.registerSound("unlock_treasure_chest");
        MPSounds.SHLIME_HURT = CommonRegisterHelper.registerSound("shlime_hurt");
        MPSounds.SHLIME_DEATH = CommonRegisterHelper.registerSound("shlime_death");

        MPSounds.ALIEN_EGG = new SoundType(1.0F, 1.0F, MPSounds.ALIEN_EGG_DESTROYED, SoundEvents.BLOCK_SLIME_STEP, SoundEvents.BLOCK_SLIME_PLACE, SoundEvents.BLOCK_SLIME_HIT, SoundEvents.BLOCK_SLIME_FALL);
        MPSounds.SMALL_SLIME = new SoundType(1.0F, 1.0F, SoundEvents.ENTITY_SMALL_SLIME_JUMP, SoundEvents.ENTITY_SMALL_SLIME_JUMP, SoundEvents.ENTITY_SMALL_SLIME_JUMP, SoundEvents.ENTITY_SMALL_SLIME_JUMP, SoundEvents.ENTITY_SMALL_SLIME_JUMP);
        MPSounds.LILYPAD = new SoundType(1.0F, 1.0F, MPSounds.LILYPAD_PLACE, SoundEvents.BLOCK_GRASS_STEP, SoundEvents.BLOCK_GRASS_PLACE, SoundEvents.BLOCK_GRASS_HIT, SoundEvents.BLOCK_GRASS_FALL);
    }
}