package stevekung.mods.moreplanets.core.event;

import micdoodle8.mods.galacticraft.core.entities.EntityMeteor;
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
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.ZombieEvent.SummonAidEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
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
import stevekung.mods.moreplanets.util.MPLog;
import stevekung.mods.moreplanets.util.helper.EntityEffectHelper;
import stevekung.mods.moreplanets.world.IMeteorType;

public class EntityEventHandler
{
    @SubscribeEvent
    public void onZombieSummonAid(SummonAidEvent event)
    {
        if (event.entity instanceof EntityInfectedZombie)
        {
            event.customSummonedAid = new EntityInfectedZombie(event.world);

            if (((EntityLivingBase) event.entity).getRNG().nextFloat() < ((EntityInfectedZombie) event.entity).getEntityAttribute(((EntityInfectedZombie) event.entity).getReinforcementsAttribute()).getAttributeValue())
            {
                event.setResult(Result.ALLOW);
            }
            event.setResult(Result.DENY);
        }
        if (event.entity instanceof EntityZeliusZombie)
        {
            event.customSummonedAid = new EntityZeliusZombie(event.world);

            if (((EntityLivingBase) event.entity).getRNG().nextFloat() < ((EntityZeliusZombie) event.entity).getEntityAttribute(((EntityZeliusZombie) event.entity).getReinforcementsAttribute()).getAttributeValue())
            {
                event.setResult(Result.ALLOW);
            }
            event.setResult(Result.DENY);
        }
    }

    @SubscribeEvent
    public void onLivingUpdate(LivingUpdateEvent event)
    {
        EntityLivingBase living = event.entityLiving;
        World world = living.worldObj;

        if (living instanceof EntityPlayerMP)
        {
            EntityPlayerMP player = (EntityPlayerMP)living;

            if (ConfigManagerMP.enableStartedPlanet && !WorldTickEventHandler.startedDimensionData.startedDimension && !(ConfigManagerMP.startedPlanet.equals("planet.") || ConfigManagerMP.startedPlanet.equals("moon.") || ConfigManagerMP.startedPlanet.equals("satellite.")))
            {
                MPLog.debug("Start teleporting player to dimension %s", ConfigManagerMP.startedPlanet);
                TeleportHandler.startNewDimension(player);
                WorldTickEventHandler.startedDimensionData.startedDimension = true;
                WorldTickEventHandler.startedDimensionData.planetToBack = ConfigManagerMP.startedPlanet;
                WorldTickEventHandler.startedDimensionData.setDirty(true);
            }
            if (player.isPotionActive(MPPotions.INFECTED_SPORE_PROTECTION) || this.isInOxygen(world, player))
            {
                player.removePotionEffect(MPPotions.INFECTED_SPORE.id);
            }
            if (player.isPotionActive(MPPotions.DARK_ENERGY_PROTECTION))
            {
                player.removePotionEffect(MPPotions.DARK_ENERGY.id);
            }
            if (world.provider instanceof WorldProviderNibiru)
            {
                if (world.canLightningStrike(player.getPosition()) && !this.isGodPlayer(player) && !player.isPotionActive(MPPotions.INFECTED_SPORE_PROTECTION) && world.getBiomeGenForCoords(player.getPosition()) != MPBiomes.GREEN_VEIN)
                {
                    player.addPotionEffect(new PotionEffect(MPPotions.INFECTED_SPORE.id, 40));
                }
                if (player.ticksExisted % 128 == 0 && !this.isGodPlayer(player) && !this.isInOxygen(world, player) && !player.isPotionActive(MPPotions.INFECTED_SPORE_PROTECTION) && world.getBiomeGenForCoords(player.getPosition()) != MPBiomes.GREEN_VEIN)
                {
                    player.addPotionEffect(new PotionEffect(MPPotions.INFECTED_SPORE.id, 80));
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
                if (living.ticksExisted % 128 == 0 && world.getBiomeGenForCoords(living.getPosition()) != MPBiomes.GREEN_VEIN)
                {
                    living.addPotionEffect(new PotionEffect(MPPotions.INFECTED_SPORE.id, 80));
                }
                if (world.canLightningStrike(living.getPosition()) && world.getBiomeGenForCoords(living.getPosition()) != MPBiomes.GREEN_VEIN)
                {
                    living.addPotionEffect(new PotionEffect(MPPotions.INFECTED_SPORE.id, 40));
                }
            }
        }
    }

    @SubscribeEvent
    public void onInteractEntity(EntityInteractEvent event)
    {
        ItemStack itemStack = event.entityPlayer.getCurrentEquippedItem();
        Entity entity = event.target;

        if (itemStack != null)
        {
            if (itemStack.getItem() == Items.dye)
            {
                EnumDyeColor color = EnumDyeColor.byDyeDamage(itemStack.getItemDamage() & 15);

                if (entity instanceof EntityShlime)
                {
                    EntityShlime grappy = (EntityShlime)entity;

                    if (!grappy.getSheared() && grappy.getFleeceColor() != color)
                    {
                        grappy.setFleeceColor(color);

                        if (!event.entityPlayer.capabilities.isCreativeMode)
                        {
                            --itemStack.stackSize;
                        }
                    }
                    event.setResult(Result.ALLOW);
                }
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
                            world.spawnEntityInWorld(meteorEntity);
                            MPLog.debug("Spawn %s at %s %s %s", meteor.getClass().getSimpleName(), (int)meteorEntity.posX, (int)meteorEntity.posY, (int)meteorEntity.posZ);
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
                            world.spawnEntityInWorld(meteorEntity);
                            MPLog.debug("Spawn %s at %s %s %s", meteor.getClass().getSimpleName(), (int)meteorEntity.posX, (int)meteorEntity.posY, (int)meteorEntity.posZ);
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