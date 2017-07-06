package stevekung.mods.moreplanets.core.event;

import micdoodle8.mods.galacticraft.core.entities.EntityMeteor;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import micdoodle8.mods.galacticraft.core.util.OxygenUtil;
import micdoodle8.mods.galacticraft.planets.venus.entities.EntityJuicer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.ZombieEvent.SummonAidEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteract;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.core.handler.TeleportHandler;
import stevekung.mods.moreplanets.init.MPBiomes;
import stevekung.mods.moreplanets.init.MPPotions;
import stevekung.mods.moreplanets.module.planets.diona.entity.EntityZeliusZombie;
import stevekung.mods.moreplanets.module.planets.nibiru.dimension.WorldProviderNibiru;
import stevekung.mods.moreplanets.module.planets.nibiru.entity.EntityInfectedZombie;
import stevekung.mods.moreplanets.module.planets.nibiru.entity.EntityShlime;
import stevekung.mods.moreplanets.network.PacketSimpleMP;
import stevekung.mods.moreplanets.network.PacketSimpleMP.EnumSimplePacketMP;
import stevekung.mods.moreplanets.util.MPLog;
import stevekung.mods.moreplanets.util.helper.EntityEffectHelper;
import stevekung.mods.moreplanets.world.IMeteorType;

public class EntityEventHandler
{
    @SubscribeEvent
    public void onZombieSummonAid(SummonAidEvent event)
    {
        if (event.getEntity() instanceof EntityInfectedZombie)
        {
            event.setCustomSummonedAid(new EntityInfectedZombie(event.getWorld()));

            if (((EntityLivingBase) event.getEntity()).getRNG().nextFloat() < ((EntityInfectedZombie) event.getEntity()).getEntityAttribute(((EntityInfectedZombie) event.getEntity()).getReinforcementsAttribute()).getAttributeValue())
            {
                event.setResult(Result.ALLOW);
            }
            event.setResult(Result.DENY);
        }
        if (event.getEntity() instanceof EntityZeliusZombie)
        {
            event.setCustomSummonedAid(new EntityZeliusZombie(event.getWorld()));

            if (((EntityLivingBase) event.getEntity()).getRNG().nextFloat() < ((EntityZeliusZombie) event.getEntity()).getEntityAttribute(((EntityZeliusZombie) event.getEntity()).getReinforcementsAttribute()).getAttributeValue())
            {
                event.setResult(Result.ALLOW);
            }
            event.setResult(Result.DENY);
        }
    }

    @SubscribeEvent
    public void onLivingDeath(LivingDeathEvent event)
    {
        EntityLivingBase living = event.getEntityLiving();
        int id = GCCoreUtil.getDimensionID(living.world);
        PacketSimpleMP.sendToAllAround(new PacketSimpleMP(EnumSimplePacketMP.C_REMOVE_ENTITY_ID, id, String.valueOf(living.getEntityId())), living.world, id, living.getPosition(), 64);
    }

    @SubscribeEvent
    public void onLivingUpdate(LivingUpdateEvent event)
    {
        EntityLivingBase living = event.getEntityLiving();
        World world = living.world;

        if (living.isDead)
        {
            int id = GCCoreUtil.getDimensionID(living.world);
            PacketSimpleMP.sendToAllAround(new PacketSimpleMP(EnumSimplePacketMP.C_REMOVE_ENTITY_ID, id, String.valueOf(living.getEntityId())), living.world, id, living.getPosition(), 64);
        }
        if (living instanceof EntityPlayerMP)
        {
            EntityPlayerMP player = (EntityPlayerMP)living;

            if (ConfigManagerMP.enableStartedPlanet && !WorldTickEventHandler.startedDimensionData.startedDimension && !(ConfigManagerMP.startedPlanet.equals("planet.") || ConfigManagerMP.startedPlanet.equals("moon.") || ConfigManagerMP.startedPlanet.equals("satellite.")))
            {
                MPLog.debug("Start teleporting player to dimension {}", ConfigManagerMP.startedPlanet);
                TeleportHandler.startNewDimension(player);
                WorldTickEventHandler.startedDimensionData.startedDimension = true;
                WorldTickEventHandler.startedDimensionData.planetToBack = ConfigManagerMP.startedPlanet;
                WorldTickEventHandler.startedDimensionData.setDirty(true);
            }
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
                if (world.isRainingAt(player.getPosition()) && !this.isGodPlayer(player) && !player.isPotionActive(MPPotions.INFECTED_SPORE_PROTECTION) && world.getBiome(player.getPosition()) != MPBiomes.GREEN_VEIN)
                {
                    player.addPotionEffect(new PotionEffect(MPPotions.INFECTED_SPORE, 40));
                }
                if (player.ticksExisted % 128 == 0 && !this.isGodPlayer(player) && !this.isInOxygen(world, player) && !player.isPotionActive(MPPotions.INFECTED_SPORE_PROTECTION) && world.getBiome(player.getPosition()) != MPBiomes.GREEN_VEIN)
                {
                    player.addPotionEffect(new PotionEffect(MPPotions.INFECTED_SPORE, 80));
                }
            }
            if (world.provider instanceof IMeteorType)
            {
                //this.spawnMeteor(world, player, (IMeteorType)world.provider);
            }
        }
        if (world.provider instanceof WorldProviderNibiru)
        {
            if (!(living instanceof EntityPlayer) && !EntityEffectHelper.isGalacticraftMob(living) && !(living instanceof EntityJuicer))
            {
                if (living.ticksExisted % 128 == 0 && world.getBiome(living.getPosition()) != MPBiomes.GREEN_VEIN)
                {
                    living.addPotionEffect(new PotionEffect(MPPotions.INFECTED_SPORE, 80));
                }
                if (world.isRainingAt(living.getPosition()) && world.getBiome(living.getPosition()) != MPBiomes.GREEN_VEIN)
                {
                    living.addPotionEffect(new PotionEffect(MPPotions.INFECTED_SPORE, 40));
                }
            }
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

    public void spawnMeteor(World world, EntityPlayerMP player, IMeteorType meteor)
    {
        Entity meteorEntity = null;

        if (FMLCommonHandler.instance().getEffectiveSide() != Side.CLIENT)
        {
            if (((IMeteorType)world.provider).getMeteorSpawnFrequency() > 0.0D && meteor.getMeteorType() != null)
            {
                int f = (int) (((IMeteorType)world.provider).getMeteorSpawnFrequency() * 1000D);

                if (world.rand.nextInt(f) == 0)
                {
                    EntityPlayer closestPlayer = world.getClosestPlayerToEntity(player, 100);

                    if (closestPlayer == null || closestPlayer.getEntityId() <= player.getEntityId())
                    {
                        int x, y, z;
                        double motX, motZ;
                        x = world.rand.nextInt(20) - 10;
                        y = world.rand.nextInt(20) + 200;
                        z = world.rand.nextInt(20) - 10;
                        motX = world.rand.nextDouble() * 5;
                        motZ = world.rand.nextDouble() * 5;

                        switch (meteor.getMeteorType())
                        {
                        case ANTAROS:
                            meteorEntity = new EntityMeteor(world, player.posX + x, player.posY + y, player.posZ + z, motX - 2.5D, 0, motZ - 2.5D, 1);//TODO
                            break;
                        }

                        if (!world.isRemote)
                        {
                            world.spawnEntity(meteorEntity);
                            MPLog.debug("Spawn {} at {} {} {}", meteor.getClass().getSimpleName(), (int)meteorEntity.posX, (int)meteorEntity.posY, (int)meteorEntity.posZ);
                        }
                    }
                }
                if (world.rand.nextInt(f * 3) == 0)
                {
                    EntityPlayer closestPlayer = world.getClosestPlayerToEntity(player, 100);

                    if (closestPlayer == null || closestPlayer.getEntityId() <= player.getEntityId())
                    {
                        int x, y, z;
                        double motX, motZ;
                        x = world.rand.nextInt(20) - 10;
                        y = world.rand.nextInt(20) + 200;
                        z = world.rand.nextInt(20) - 10;
                        motX = world.rand.nextDouble() * 5;
                        motZ = world.rand.nextDouble() * 5;

                        switch (meteor.getMeteorType())
                        {
                        case ANTAROS:
                            meteorEntity = new EntityMeteor(world, player.posX + x, player.posY + y, player.posZ + z, motX - 2.5D, 0, motZ - 2.5D, 1);//TODO
                            break;
                        }

                        if (!world.isRemote)
                        {
                            world.spawnEntity(meteorEntity);
                            MPLog.debug("Spawn {} at {} {} {}", meteor.getClass().getSimpleName(), (int)meteorEntity.posX, (int)meteorEntity.posY, (int)meteorEntity.posZ);
                        }
                    }
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