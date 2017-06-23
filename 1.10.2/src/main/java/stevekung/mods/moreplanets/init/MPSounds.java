package stevekung.mods.moreplanets.init;

import javax.annotation.ParametersAreNonnullByDefault;

import net.minecraft.block.SoundType;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@ParametersAreNonnullByDefault
public class MPSounds
{
    public static SoundEvent BLACK_HOLE_CREATED = MPSounds.registerSound("black_hole_created");
    public static SoundEvent BLACK_HOLE_DESTROYED = MPSounds.registerSound("black_hole_destroyed");
    public static SoundEvent BLACK_HOLE_AMBIENT = MPSounds.registerSound("black_hole_ambient");
    public static SoundEvent MACHINE_ACTIVATE_AMBIENT = MPSounds.registerSound("machine_activate_ambient");
    public static SoundEvent MACHINE_GENERATOR_AMBIENT = MPSounds.registerSound("machine_generator_ambient");
    public static SoundEvent MACHINE_ALERT = MPSounds.registerSound("machine_alert");
    public static SoundEvent MACHINE_DANGER = MPSounds.registerSound("machine_danger");
    public static SoundEvent MACHINE_STOP = MPSounds.registerSound("machine_stop");
    public static SoundEvent ALIEN_BEAM = MPSounds.registerSound("alien_beam");
    public static SoundEvent LASER_SHOOTED = MPSounds.registerSound("laser_shooted");
    public static SoundEvent ALIEN_EGG_DESTROYED = MPSounds.registerSound("alien_egg_destroyed");
    public static SoundEvent ALIEN_MINER_AMBIENT = MPSounds.registerSound("alien_miner_ambient");
    public static SoundEvent ALIEN_MINER_CHARGED = MPSounds.registerSound("alien_miner_charged");
    public static SoundEvent ALIEN_MINER_ATTACK = MPSounds.registerSound("alien_miner_attack");
    public static SoundEvent ALIEN_MINER_SHOCK = MPSounds.registerSound("alien_miner_shock");
    public static SoundEvent INFECTED_MOB_ATTACK = MPSounds.registerSound("infected_mob_attack");
    public static SoundEvent INFECTED_MOB_HURT = MPSounds.registerSound("infected_mob_hurt");
    public static SoundEvent INFECTED_MOB_EXPLODE = MPSounds.registerSound("infected_mob_explode");
    public static SoundEvent FRONOS_MOB_STEP = MPSounds.registerSound("fronos_mob_step");
    public static SoundEvent FRONOS_MOB_AMBIENT = MPSounds.registerSound("fronos_mob_ambient");
    public static SoundEvent FRONOS_MOB_HURT = MPSounds.registerSound("fronos_mob_hurt");
    public static SoundEvent FRONOS_MOB_DEATH = MPSounds.registerSound("fronos_mob_death");
    public static SoundEvent LOUD_THUNDER = MPSounds.registerSound("loud_thunder");
    public static SoundEvent LILYPAD_PLACE = MPSounds.registerSound("lilypad_place");
    public static SoundEvent UNLOCK_TREASURE_CHEST = MPSounds.registerSound("unlock_treasure_chest");
    public static SoundEvent SHLIME_HURT = MPSounds.registerSound("shlime_hurt");
    public static SoundEvent SHLIME_DEATH = MPSounds.registerSound("shlime_death");
    public static SoundEvent SLIME_SMALL = MPSounds.registerSound("slime_small");

    public static SoundType ALIEN_EGG = new SoundType(1.0F, 1.0F, MPSounds.ALIEN_EGG_DESTROYED, SoundEvents.BLOCK_SLIME_STEP, SoundEvents.BLOCK_SLIME_PLACE, SoundEvents.BLOCK_SLIME_HIT, SoundEvents.BLOCK_SLIME_FALL);
    public static SoundType SMALL_SLIME = new SoundType(1.0F, 1.0F, MPSounds.SLIME_SMALL, SoundEvents.BLOCK_SLIME_STEP, MPSounds.SLIME_SMALL, SoundEvents.BLOCK_SLIME_HIT, SoundEvents.BLOCK_SLIME_FALL);
    public static SoundType LILYPAD = new SoundType(1.0F, 1.0F, SoundEvents.BLOCK_GRASS_BREAK, SoundEvents.BLOCK_GRASS_STEP, MPSounds.LILYPAD_PLACE, SoundEvents.BLOCK_GRASS_HIT, SoundEvents.BLOCK_GRASS_FALL);

    private static SoundEvent registerSound(String name)
    {
        GameRegistry.register(new SoundEvent(new ResourceLocation("moreplanets:" + name)).setRegistryName(new ResourceLocation("moreplanets:" + name)));
        return new SoundEvent(new ResourceLocation("moreplanets:" + name));
    }
}