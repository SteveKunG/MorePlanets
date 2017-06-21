package stevekung.mods.moreplanets.init;

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

    public static void init()
    {
        CommonRegisterHelper.registerSound(MPSounds.BLACK_HOLE_CREATED, "black_hole_created");
        CommonRegisterHelper.registerSound(MPSounds.BLACK_HOLE_DESTROYED, "black_hole_destroyed");
        CommonRegisterHelper.registerSound(MPSounds.BLACK_HOLE_AMBIENT, "black_hole_ambient");
        CommonRegisterHelper.registerSound(MPSounds.MACHINE_ACTIVATE_AMBIENT, "machine_activate_ambient");
        CommonRegisterHelper.registerSound(MPSounds.MACHINE_GENERATOR_AMBIENT, "machine_generator_ambient");
        CommonRegisterHelper.registerSound(MPSounds.MACHINE_ALERT, "machine_alert");
        CommonRegisterHelper.registerSound(MPSounds.MACHINE_DANGER, "machine_danger");
        CommonRegisterHelper.registerSound(MPSounds.MACHINE_STOP, "machine_stop");
        CommonRegisterHelper.registerSound(MPSounds.ALIEN_BEAM, "alien_beam");
        CommonRegisterHelper.registerSound(MPSounds.LASER_SHOOTED, "laser_shooted");
        CommonRegisterHelper.registerSound(MPSounds.ALIEN_EGG_DESTROYED, "alien_egg_destroyed");
        CommonRegisterHelper.registerSound(MPSounds.ALIEN_MINER_AMBIENT, "alien_miner_ambient");
        CommonRegisterHelper.registerSound(MPSounds.ALIEN_MINER_CHARGED, "alien_miner_charged");
        CommonRegisterHelper.registerSound(MPSounds.ALIEN_MINER_ATTACK, "alien_miner_attack");
        CommonRegisterHelper.registerSound(MPSounds.ALIEN_MINER_SHOCK, "alien_miner_shock");
        CommonRegisterHelper.registerSound(MPSounds.INFECTED_MOB_ATTACK, "infected_mob_attack");
        CommonRegisterHelper.registerSound(MPSounds.INFECTED_MOB_HURT, "infected_mob_hurt");
        CommonRegisterHelper.registerSound(MPSounds.INFECTED_MOB_EXPLODE, "infected_mob_explode");
        CommonRegisterHelper.registerSound(MPSounds.FRONOS_MOB_STEP, "fronos_mob_step");
        CommonRegisterHelper.registerSound(MPSounds.FRONOS_MOB_AMBIENT, "fronos_mob_ambient");
        CommonRegisterHelper.registerSound(MPSounds.FRONOS_MOB_HURT, "fronos_mob_hurt");
        CommonRegisterHelper.registerSound(MPSounds.FRONOS_MOB_DEATH, "fronos_mob_death");
        CommonRegisterHelper.registerSound(MPSounds.LOUD_THUNDER, "loud_thunder");
    }
}