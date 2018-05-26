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
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.ZombieEvent.SummonAidEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteract;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.init.MPBiomes;
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.init.MPPotions;
import stevekung.mods.moreplanets.network.PacketSimpleMP;
import stevekung.mods.moreplanets.network.PacketSimpleMP.EnumSimplePacketMP;
import stevekung.mods.moreplanets.planets.diona.entity.EntityZeliusZombie;
import stevekung.mods.moreplanets.planets.nibiru.dimension.WorldProviderNibiru;
import stevekung.mods.moreplanets.planets.nibiru.entity.EntityInfectedZombie;
import stevekung.mods.moreplanets.planets.nibiru.entity.EntityShlime;
import stevekung.mods.moreplanets.utils.CompatibilityManagerMP;
import stevekung.mods.moreplanets.utils.EntityEffectUtils;
import stevekung.mods.moreplanets.utils.LoggerMP;
import stevekung.mods.moreplanets.utils.TeleportUtils;
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
    public void onLivingDeath(LivingDeathEvent event)
    {
        EntityLivingBase living = event.getEntityLiving();
        int id = GCCoreUtil.getDimensionID(living.world);
        PacketSimpleMP.sendToAllAround(new PacketSimpleMP(EnumSimplePacketMP.C_REMOVE_ENTITY_ID, id, String.valueOf(living.getEntityId())), living.world, id, living.getPosition(), 64);
    }

    @SubscribeEvent //TODO Fix fall damage
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
            String startedPlanet = ConfigManagerMP.moreplanets_general.startedPlanet;

            if (ConfigManagerMP.moreplanets_general.enableStartedPlanet && !WorldTickEventHandler.startedDimensionData.startedDimension && !(startedPlanet.equals("planet.") || startedPlanet.equals("moon.") || startedPlanet.equals("satellite.")))
            {
                LoggerMP.debug("Start teleporting player to dimension {}", startedPlanet);
                TeleportUtils.startNewDimension(player);
                WorldTickEventHandler.startedDimensionData.startedDimension = true;
                WorldTickEventHandler.startedDimensionData.planetToBack = startedPlanet;
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
                if (world.isRainingAt(player.getPosition()) && !this.isGodPlayer(player) && !player.isPotionActive(MPPotions.INFECTED_SPORE_PROTECTION) && world.getBiome(player.getPosition()) != MPBiomes.GREEN_VEIN_FIELDS)
                {
                    player.addPotionEffect(new PotionEffect(MPPotions.INFECTED_SPORE, 40));
                }
                if (player.ticksExisted % 128 == 0 && !this.isGodPlayer(player) && !this.isInOxygen(world, player) && !player.isPotionActive(MPPotions.INFECTED_SPORE_PROTECTION) && world.getBiome(player.getPosition()) != MPBiomes.GREEN_VEIN_FIELDS)
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
            if (!(living instanceof EntityPlayer) && !EntityEffectUtils.isGalacticraftMob(living) && !(living instanceof EntityJuicer))
            {
                if (living.ticksExisted % 128 == 0 && world.getBiome(living.getPosition()) != MPBiomes.GREEN_VEIN_FIELDS)
                {
                    living.addPotionEffect(new PotionEffect(MPPotions.INFECTED_SPORE, 80));
                }
                if (world.isRainingAt(living.getPosition()) && world.getBiome(living.getPosition()) != MPBiomes.GREEN_VEIN_FIELDS)
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
                int f = (int) (((IMeteorType)world.provider).getMeteorSpawnFrequency() * 750D);
                int r = ((WorldServer)world).getMinecraftServer().getPlayerList().getViewDistance();
                int x, z;
                double motX, motZ;
                x = world.rand.nextInt(20) + 160;
                z = world.rand.nextInt(20) - 10;
                motX = world.rand.nextDouble() * 2 - 2.5D;
                motZ = world.rand.nextDouble() * 5 - 2.5D;
                int px = MathHelper.floor(player.posX);

                if (world.rand.nextInt(f) == 0)
                {
                    EntityPlayer closestPlayer = world.getClosestPlayerToEntity(player, 100);

                    if (closestPlayer == null || closestPlayer.getEntityId() <= player.getEntityId())
                    {
                        if ((x + px >> 4) - (px >> 4) >= r)
                        {
                            x = ((px >> 4) + r << 4) - 1 - px;
                        }

                        switch (meteor.getMeteorType())
                        {
                        case ANTAROS:
                            meteorEntity = new EntityMeteor(world, player.posX + x, 355.0D, player.posZ + z, motX - 2.5D, 0, motZ - 2.5D, 1);//TODO
                            break;
                        }

                        if (!world.isRemote)
                        {
                            world.spawnEntity(meteorEntity);
                            LoggerMP.debug("Spawn {} at {} {} {}", meteor.getClass().getSimpleName(), (int)meteorEntity.posX, (int)meteorEntity.posY, (int)meteorEntity.posZ);
                        }
                    }
                }
                if (world.rand.nextInt(f * 3) == 0)
                {
                    EntityPlayer closestPlayer = world.getClosestPlayerToEntity(player, 100);

                    if (closestPlayer == null || closestPlayer.getEntityId() <= player.getEntityId())
                    {
                        switch (meteor.getMeteorType())
                        {
                        case ANTAROS:
                            meteorEntity = new EntityMeteor(world, player.posX + x, 355.0D, player.posZ + z, motX - 2.5D, 0, motZ - 2.5D, 1);//TODO
                            break;
                        }

                        if (!world.isRemote)
                        {
                            world.spawnEntity(meteorEntity);
                            LoggerMP.debug("Spawn {} at {} {} {}", meteor.getClass().getSimpleName(), (int)meteorEntity.posX, (int)meteorEntity.posY, (int)meteorEntity.posZ);
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