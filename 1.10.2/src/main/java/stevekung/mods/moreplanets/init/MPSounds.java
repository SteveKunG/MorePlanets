package stevekung.mods.moreplanets.init;

import net.minecraft.util.SoundEvent;
import stevekung.mods.moreplanets.util.helper.CommonRegisterHelper;

public class MPSounds
{
    public static SoundEvent BLACK_HOLE_CREATED;
    public static SoundEvent BLACK_HOLE_DESTROYED;
    public static SoundEvent BLACK_HOLE_AMBIENT;
    public static SoundEvent MACHINE_AMBIENT_ACTIVATE;
    public static SoundEvent ALIEN_BEAM;
    public static SoundEvent LASER_SHOOTED;

    public static void init()
    {
        CommonRegisterHelper.registerSound(MPSounds.BLACK_HOLE_CREATED, "black_hole_created");
        CommonRegisterHelper.registerSound(MPSounds.BLACK_HOLE_DESTROYED, "black_hole_destroyed");
        CommonRegisterHelper.registerSound(MPSounds.BLACK_HOLE_AMBIENT, "black_hole_ambient");
        CommonRegisterHelper.registerSound(MPSounds.MACHINE_AMBIENT_ACTIVATE, "machine_ambient_activate");
        CommonRegisterHelper.registerSound(MPSounds.ALIEN_BEAM, "alien_beam");
        CommonRegisterHelper.registerSound(MPSounds.LASER_SHOOTED, "laser_shooted");
    }
}