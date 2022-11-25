package stevekung.mods.moreplanets.core.event;

import java.util.UUID;

import micdoodle8.mods.galacticraft.api.event.oxygen.GCCoreOxygenSuffocationEvent;
import micdoodle8.mods.galacticraft.api.vector.BlockVec3Dim;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import micdoodle8.mods.galacticraft.core.util.OxygenUtil;
import micdoodle8.mods.galacticraft.planets.venus.entities.EntityJuicer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.entity.living.ZombieEvent.SummonAidEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteract;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.init.MPPotions;
import stevekung.mods.moreplanets.moons.koentus.entity.EntityKoentusMeteor;
import stevekung.mods.moreplanets.planets.diona.entity.EntityZeliusZombie;
import stevekung.mods.moreplanets.planets.nibiru.dimension.WorldProviderNibiru;
import stevekung.mods.moreplanets.planets.nibiru.entity.EntityInfectedZombie;
import stevekung.mods.moreplanets.planets.nibiru.entity.EntityShlime;
import stevekung.mods.moreplanets.planets.nibiru.world.gen.biome.BiomeGreenVeinFields;
import stevekung.mods.moreplanets.tileentity.TileEntityShieldGenerator;
import stevekung.mods.moreplanets.utils.CompatibilityManagerMP;
import stevekung.mods.moreplanets.utils.EntityEffectUtils;
import stevekung.mods.moreplanets.utils.LoggerMP;
import stevekung.mods.moreplanets.world.IMeteorType;

public class EntityEventHandler
{
    @SubscribeEvent
    public void onZombieSummonAid(SummonAidEvent event)
    {
        if (event.getEntity() instanceof EntityInfectedZombie)
        {
            EntityInfectedZombie zombie = new EntityInfectedZombie(event.getWorld());
            event.setCustomSummonedAid(zombie);

            if (zombie.getRNG().nextFloat() < zombie.getEntityAttribute(zombie.getReinforcementsAttribute()).getAttributeValue())
            {
                event.setResult(Result.ALLOW);
            }
            event.setResult(Result.DENY);
        }
        if (event.getEntity() instanceof EntityZeliusZombie)
        {
            EntityZeliusZombie zombie = new EntityZeliusZombie(event.getWorld());
            event.setCustomSummonedAid(zombie);

            if (zombie.getRNG().nextFloat() < zombie.getEntityAttribute(zombie.getReinforcementsAttribute()).getAttributeValue())
            {
                event.setResult(Result.ALLOW);
            }
            event.setResult(Result.DENY);
        }
    }

    @SubscribeEvent
    public void onLivingFall(LivingFallEvent event)
    {
        if (!CompatibilityManagerMP.isBaubleLoaded)
        {
            EntityLivingBase living = event.getEntityLiving();

            if (living instanceof EntityPlayerMP)
            {
                EntityPlayerMP player = (EntityPlayerMP) living;

                if (player.inventory.hasItemStack(new ItemStack(MPItems.GRAVITY_AMULET)))
                {
                    event.setCanceled(true);
                }
                else
                {
                    event.setCanceled(false);
                }
            }
        }
    }

    @SubscribeEvent
    public void onLivingUpdate(LivingEvent.LivingUpdateEvent event)
    {
        EntityLivingBase living = event.getEntityLiving();
        World world = living.world;

        if (living instanceof EntityPlayerMP)
        {
            EntityPlayerMP player = (EntityPlayerMP)living;

            if (player.isPotionActive(MPPotions.INFECTED_SPORE_PROTECTION) || this.isInOxygen(world, player))
            {
                player.removePotionEffect(MPPotions.INFECTED_SPORE);
            }
            if (player.isPotionActive(MPPotions.DARK_ENERGY_PROTECTION))
            {
                player.removePotionEffect(MPPotions.DARK_ENERGY);
            }
            if (world.provider instanceof WorldProviderNibiru)
            {
                if (!this.isGodPlayer(player) && !player.isPotionActive(MPPotions.INFECTED_SPORE_PROTECTION) && !(world.getBiome(player.getPosition()) instanceof BiomeGreenVeinFields))
                {
                    if (world.isRainingAt(player.getPosition()))
                    {
                        player.addPotionEffect(new PotionEffect(MPPotions.INFECTED_SPORE, 40));
                    }
                    if (player.ticksExisted % 128 == 0 && !this.isInOxygen(world, player))
                    {
                        player.addPotionEffect(new PotionEffect(MPPotions.INFECTED_SPORE, 80));
                    }
                }
            }
            if (!world.isRemote && world.provider instanceof IMeteorType)
            {
                this.spawnMeteors(world, player, (IMeteorType)world.provider);
            }
        }
        else
        {
            if (ConfigManagerMP.moreplanets_planet_settings.enableInfectedSporeForMobs && world.provider instanceof WorldProviderNibiru)
            {
                if (!EntityEffectUtils.isGalacticraftMob(living) && !(living instanceof EntityJuicer) && !(world.getBiome(living.getPosition()) instanceof BiomeGreenVeinFields))
                {
                    if (living.ticksExisted % 128 == 0)
                    {
                        living.addPotionEffect(new PotionEffect(MPPotions.INFECTED_SPORE, 80));
                    }
                    else if (world.isRainingAt(living.getPosition()))
                    {
                        living.addPotionEffect(new PotionEffect(MPPotions.INFECTED_SPORE, 40));
                    }
                }
            }
        }

        if (living instanceof IMob)
        {
            for (BlockVec3Dim vec : TileEntityShieldGenerator.LOADED_GENERATORS)
            {
                if (vec != null && vec.dim == GCCoreUtil.getDimensionID(world))
                {
                    TileEntity tile = vec.getTileEntity();

                    if (tile instanceof TileEntityShieldGenerator)
                    {
                        TileEntityShieldGenerator shield = (TileEntityShieldGenerator)tile;

                        if (!living.world.isRemote && !living.isDead && shield.isInsideShield(living.getPosition()) && !shield.disabled && shield.enableShield && shield.shieldCapacity > 0)
                        {
                            if (!shield.enableDamage)
                            {
                                double d4 = living.getDistance(tile.getPos().getX(), tile.getPos().getY(), tile.getPos().getZ());
                                double d6 = living.posX - tile.getPos().getX();
                                double d8 = living.posY - tile.getPos().getY();
                                double d10 = living.posZ - tile.getPos().getZ();
                                double d11 = MathHelper.sqrt(d6 * d6 + d8 * d8 + d10 * d10);
                                d6 /= d11;
                                d8 /= d11;
                                d10 /= d11;
                                double d13 = (0.0D - d4) * 2.0D / 10.0D;
                                double d14 = d13;
                                double knockback = 10.0D;
                                living.motionX -= d6 * d14 / knockback;
                                living.motionY -= d8 * d14 / knockback;
                                living.motionZ -= d10 * d14 / knockback;
                            }

                            UUID uuid;

                            try
                            {
                                uuid = UUID.fromString(shield.ownerUUID);
                            }
                            catch (Exception e)
                            {
                                uuid = UUID.fromString("eef3a603-1c1b-4c98-8264-d2f04b231ef4"); //default uuid :)
                            }

                            if (living.world.getPlayerEntityByUUID(uuid) != null)
                            {
                                if (living.ticksExisted % 8 == 0)
                                {
                                    ((WorldServer)living.world).spawnParticle(EnumParticleTypes.CRIT_MAGIC, living.posX, living.posY, living.posZ, 20, 0.0D, 0.5D, 0.0D, 1.0D);
                                }
                                if (shield.enableDamage)
                                {
                                    living.attackEntityFrom(DamageSource.causePlayerDamage(living.world.getPlayerEntityByUUID(uuid)), shield.shieldDamage);
                                }
                            }
                            else
                            {
                                if (living.ticksExisted % 8 == 0)
                                {
                                    ((WorldServer)living.world).spawnParticle(EnumParticleTypes.CRIT_MAGIC, living.posX, living.posY, living.posZ, 20, 0.0D, 0.5D, 0.0D, 1.0D);
                                }
                                if (shield.enableDamage)
                                {
                                    living.attackEntityFrom(DamageSource.GENERIC, shield.shieldDamage);
                                }
                            }
                            float motion = MathHelper.sqrt(living.motionX * living.motionX + living.motionZ * living.motionZ);
                            shield.shieldCapacity -= motion * 2;
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onOxygenSuffocation(GCCoreOxygenSuffocationEvent.Pre event)
    {
        EntityLivingBase living = event.getEntityLiving();

        if (living.world.getBiome(living.getPosition()) instanceof BiomeGreenVeinFields)
        {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onInteractEntity(EntityInteract event)
    {
        ItemStack itemStack = event.getItemStack();
        Entity entity = event.getTarget();

        if (!itemStack.isEmpty() && itemStack.getItem() == Items.DYE)
        {
            EnumDyeColor color = EnumDyeColor.byDyeDamage(itemStack.getItemDamage() & 15);

            if (entity instanceof EntityShlime)
            {
                EntityShlime shlime = (EntityShlime)entity;

                if (!shlime.getSheared() && shlime.getFleeceColor() != color)
                {
                    shlime.setFleeceColor(color);

                    if (!event.getEntityPlayer().capabilities.isCreativeMode)
                    {
                        itemStack.shrink(1);
                    }
                }
                event.setResult(Result.ALLOW);
            }
        }
    }

    @SubscribeEvent
    public void onEntityUpdate(EntityEvent.CanUpdate event)
    {
        if (event.getEntity() instanceof EntityKoentusMeteor)
        {
            event.setCanUpdate(true);
            return;
        }
    }

    @SubscribeEvent
    public void onLivingSpawn(LivingSpawnEvent.CheckSpawn event)
    {
        if (event.getResult() == Result.ALLOW || event.isSpawner())
        {
            return;
        }

        for (BlockVec3Dim vec : TileEntityShieldGenerator.LOADED_GENERATORS)
        {
            if (vec != null && vec.dim == GCCoreUtil.getDimensionID(event.getWorld()))
            {
                TileEntity tile = vec.getTileEntity();

                if (tile instanceof TileEntityShieldGenerator)
                {
                    TileEntityShieldGenerator shield = (TileEntityShieldGenerator)tile;

                    if (!shield.disabled && shield.isInsideShield(event.getEntityLiving().getPosition()))
                    {
                        event.setResult(Result.DENY);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onEnderTeleport(EnderTeleportEvent event)
    {
        for (BlockVec3Dim vec : TileEntityShieldGenerator.LOADED_GENERATORS)
        {
            if (vec != null && vec.dim == GCCoreUtil.getDimensionID(event.getEntityLiving().getEntityWorld()))
            {
                TileEntity tile = vec.getTileEntity();

                if (tile instanceof TileEntityShieldGenerator)
                {
                    TileEntityShieldGenerator shield = (TileEntityShieldGenerator)tile;

                    if (!shield.disabled && shield.isInsideShield(event.getEntityLiving().getPosition()))
                    {
                        event.setCanceled(true);
                    }
                }
            }
        }
    }

    private void spawnMeteors(World world, EntityPlayerMP player, IMeteorType meteor)
    {
        if (meteor.getMeteorSpawnFrequency() > 0.0D && ConfigManagerCore.meteorSpawnMod > 0.0D && meteor.getMeteorType() != null)
        {
            Entity meteorEntity = null;
            int frequency = (int)(meteor.getMeteorSpawnFrequency() * 750.0D * (1.0D / ConfigManagerCore.meteorSpawnMod));
            int serverDistance = world.getMinecraftServer().getPlayerList().getViewDistance();
            int x, z;
            double motX, motZ;
            x = world.rand.nextInt(20) + 160;
            z = world.rand.nextInt(20) - 10;
            motX = world.rand.nextDouble() * 2 - 2.5D;
            motZ = world.rand.nextDouble() * 5 - 2.5D;
            int px = MathHelper.floor(player.posX);
            EntityPlayer closestPlayer = world.getClosestPlayerToEntity(player, 100.0D);

            if (closestPlayer == null || closestPlayer.getEntityId() <= player.getEntityId())
            {
                if ((x + px >> 4) - (px >> 4) >= serverDistance)
                {
                    x = ((px >> 4) + serverDistance << 4) - 1 - px;
                }

                if (world.rand.nextInt(frequency) == 0)
                {
                    int size = 1 + world.rand.nextInt(2);

                    switch (meteor.getMeteorType())
                    {
                    case KOENTUS:
                        meteorEntity = new EntityKoentusMeteor(world, player.posX + x, 355.0D, player.posZ + z, motX - 2.5D, 0, motZ - 2.5D, size);
                        break;
                    }
                    world.spawnEntity(meteorEntity);
                    LoggerMP.debug("Spawn meteor {} at {} {} {}", EntityList.getKey(meteorEntity), (int)meteorEntity.posX, (int)meteorEntity.posY, (int)meteorEntity.posZ);
                }
                if (world.rand.nextInt(frequency * 3) == 0)
                {
                    int size = 3 + world.rand.nextInt(6);

                    switch (meteor.getMeteorType())
                    {
                    case KOENTUS:
                        meteorEntity = new EntityKoentusMeteor(world, player.posX + x, 355.0D, player.posZ + z, motX - 2.5D, 0, motZ - 2.5D, size);
                        break;
                    }
                    world.spawnEntity(meteorEntity);
                    LoggerMP.debug("Spawn meteor {} at {} {} {}", EntityList.getKey(meteorEntity), (int)meteorEntity.posX, (int)meteorEntity.posY, (int)meteorEntity.posZ);
                }
            }
        }
    }

    private boolean isInOxygen(World world, EntityPlayer player)
    {
        return OxygenUtil.inOxygenBubble(world, player.posX, player.posY, player.posZ) || OxygenUtil.isAABBInBreathableAirBlock(player);
    }

    private boolean isGodPlayer(EntityPlayer player)
    {
        return player.capabilities.isCreativeMode || player.isSpectator();
    }
}