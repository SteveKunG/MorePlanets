package stevekung.mods.moreplanets.utils;

import java.util.Collection;

import micdoodle8.mods.galacticraft.api.GalacticraftRegistry;
import micdoodle8.mods.galacticraft.api.recipe.SchematicRegistry;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.api.world.ITeleportType;
import micdoodle8.mods.galacticraft.core.GCBlocks;
import micdoodle8.mods.galacticraft.core.GCItems;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.dimension.WorldProviderMoon;
import micdoodle8.mods.galacticraft.core.entities.player.GCPlayerStats;
import micdoodle8.mods.galacticraft.core.inventory.InventoryExtended;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import micdoodle8.mods.galacticraft.planets.asteroids.dimension.WorldProviderAsteroids;
import micdoodle8.mods.galacticraft.planets.asteroids.items.AsteroidsItems;
import micdoodle8.mods.galacticraft.planets.mars.dimension.WorldProviderMars;
import micdoodle8.mods.galacticraft.planets.mars.items.MarsItems;
import micdoodle8.mods.galacticraft.planets.venus.VenusItems;
import micdoodle8.mods.galacticraft.planets.venus.dimension.WorldProviderVenus;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.ai.attributes.AttributeMap;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.*;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fml.common.FMLCommonHandler;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.network.PacketSimpleMP;
import stevekung.mods.moreplanets.network.PacketSimpleMP.EnumSimplePacketMP;
import stevekung.mods.moreplanets.world.IStartedDimension;

public class TeleportUtils
{
    public static Entity teleportEntity(Entity entity, int dimension, double xCoord, double yCoord, double zCoord)
    {
        return TeleportUtils.teleportEntity(entity, dimension, xCoord, yCoord, zCoord, 0.0F, 0.0F);
    }

    public static Entity teleportEntity(Entity entity, int dimension, double xCoord, double yCoord, double zCoord, float yaw, float pitch)
    {
        if (entity == null || entity.world.isRemote)
        {
            return entity;
        }

        MinecraftServer server = entity.getServer();
        int sourceDim = entity.world.provider.getDimension();

        if (!entity.isBeingRidden() && !entity.isRiding())
        {
            return TeleportUtils.handleEntityTeleport(entity, server, sourceDim, dimension, xCoord, yCoord, zCoord, yaw, pitch);
        }
        return entity;
    }

    private static Entity handleEntityTeleport(Entity entity, MinecraftServer server, int sourceDim, int targetDim, double xCoord, double yCoord, double zCoord, float yaw, float pitch)
    {
        if (entity == null || entity.world.isRemote)
        {
            return entity;
        }

        boolean interDimensional = sourceDim != targetDim;

        if (interDimensional && !ForgeHooks.onTravelToDimension(entity, targetDim))
        {
            return entity;
        }

        if (interDimensional)
        {
            if (entity instanceof EntityPlayerMP)
            {
                return TeleportUtils.teleportPlayerInternational((EntityPlayerMP) entity, server, sourceDim, targetDim, xCoord, yCoord, zCoord, yaw, pitch);
            }
            else
            {
                return TeleportUtils.teleportEntityInternational(entity, server, sourceDim, targetDim, xCoord, yCoord, zCoord, yaw, pitch);
            }
        }
        else
        {
            if (entity instanceof EntityPlayerMP)
            {
                EntityPlayerMP player = (EntityPlayerMP) entity;
                player.connection.setPlayerLocation(xCoord, yCoord, zCoord, yaw, pitch);
                player.setRotationYawHead(yaw);
            }
            else
            {
                entity.setLocationAndAngles(xCoord, yCoord, zCoord, yaw, pitch);
                entity.setRotationYawHead(yaw);
            }
        }
        return entity;
    }

    private static Entity teleportEntityInternational(Entity entity, MinecraftServer server, int sourceDim, int targetDim, double xCoord, double yCoord, double zCoord, float yaw, float pitch)
    {
        if (entity.isDead)
        {
            return null;
        }

        WorldServer sourceWorld = server.getWorld(sourceDim);
        WorldServer targetWorld = server.getWorld(targetDim);

        //Set the entity dead before calling changeDimension. Still need to call changeDimension for things like minecarts which will drop their contents otherwise.
        if (!entity.isDead && entity instanceof EntityMinecart)
        {
            entity.isDead = true;
            entity.changeDimension(targetDim);
            entity.isDead = false;
        }

        entity.dimension = targetDim;
        sourceWorld.removeEntity(entity);
        entity.isDead = false;
        entity.setLocationAndAngles(xCoord, yCoord, zCoord, yaw, pitch);
        sourceWorld.updateEntityWithOptionalForce(entity, false);
        Entity newEntity = EntityList.newEntity(entity.getClass(), targetWorld);

        if (newEntity != null)
        {
            newEntity.copyDataFromOld(entity);
            newEntity.setLocationAndAngles(xCoord, yCoord, zCoord, yaw, pitch);
            boolean flag = newEntity.forceSpawn;
            newEntity.forceSpawn = true;
            targetWorld.spawnEntity(newEntity);
            newEntity.forceSpawn = flag;
            targetWorld.updateEntityWithOptionalForce(newEntity, false);
        }
        entity.isDead = true;
        sourceWorld.resetUpdateEntityTick();
        targetWorld.resetUpdateEntityTick();
        return newEntity;
    }

    private static EntityPlayer teleportPlayerInternational(EntityPlayerMP player, MinecraftServer server, int sourceDim, int targetDim, double xCoord, double yCoord, double zCoord, float yaw, float pitch)
    {
        WorldServer sourceWorld = server.getWorld(sourceDim);
        WorldServer targetWorld = server.getWorld(targetDim);
        PlayerList playerList = server.getPlayerList();
        player.dimension = targetDim;
        player.connection.sendPacket(new SPacketRespawn(player.dimension, targetWorld.getDifficulty(), targetWorld.getWorldInfo().getTerrainType(), player.interactionManager.getGameType()));
        playerList.updatePermissionLevel(player);
        sourceWorld.removeEntityDangerously(player);
        player.isDead = false;

        // world transfer
        player.setLocationAndAngles(xCoord, yCoord, zCoord, yaw, pitch);
        player.connection.setPlayerLocation(xCoord, yCoord, zCoord, yaw, pitch);
        targetWorld.spawnEntity(player);
        targetWorld.updateEntityWithOptionalForce(player, false);
        player.setWorld(targetWorld);

        playerList.preparePlayer(player, sourceWorld);
        player.connection.setPlayerLocation(xCoord, yCoord, zCoord, yaw, pitch);
        player.interactionManager.setWorld(targetWorld);
        player.connection.sendPacket(new SPacketPlayerAbilities(player.capabilities));
        playerList.updateTimeAndWeatherForPlayer(player, targetWorld);
        playerList.syncPlayerInventory(player);

        player.getActivePotionEffects().forEach(potion ->
        {
            player.connection.sendPacket(new SPacketEntityEffect(player.getEntityId(), potion));
        });
        player.connection.sendPacket(new SPacketSetExperience(player.experience, player.experienceTotal, player.experienceLevel));
        FMLCommonHandler.instance().firePlayerChangedDimensionEvent(player, sourceDim, targetDim);
        player.setLocationAndAngles(xCoord, yCoord, zCoord, yaw, pitch);
        return player;
    }

    // OLD STUFF
    @Deprecated
    public static EntityPlayerMP setWarpDimension(EntityPlayerMP player, WorldServer world, int x, int y, int z, int dimID, boolean nether)
    {
        if (!world.isRemote)
        {
            MinecraftServer mcServer = FMLCommonHandler.instance().getMinecraftServerInstance();

            if (mcServer != null)
            {
                WorldServer worldServer = mcServer.getWorld(dimID);

                if (worldServer == null)
                {
                    LoggerMP.error("Cannot Transfer Entity to Dimension: Could not get World for Dimension {}", dimID);
                    return null;
                }
                return teleportEntity(worldServer, player, x, y, z, dimID, nether);
            }
        }
        return null;
    }

    @Deprecated
    private static EntityPlayerMP teleportEntity(World worldNew, EntityPlayerMP player, int x, int y, int z, int dimID, boolean nether)
    {
        BlockPos blockpos = player.getBedLocation(dimID);
        boolean flag = player.isSpawnForced(dimID);
        boolean dimChange = player.world != worldNew;
        player.world.updateEntityWithOptionalForce(player, false);
        int oldDimID = GCCoreUtil.getDimensionID(player.world);
        ChunkPos pair = worldNew.getChunkFromChunkCoords(x, z).getPos();
        y = (int) (y + 1.5F);

        if (dimChange)
        {
            World worldOld = player.world;

            try
            {
                ((WorldServer) worldOld).getPlayerChunkMap().removePlayer(player);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            player.closeScreen();
            player.dimension = dimID;
            player.connection.sendPacket(new SPacketRespawn(dimID, player.world.getDifficulty(), player.world.getWorldInfo().getTerrainType(), player.interactionManager.getGameType()));
            worldOld.playerEntities.remove(player);
            worldOld.updateAllPlayersSleepingFlag();
            int i = player.chunkCoordX;
            int j = player.chunkCoordZ;

            if (player.addedToChunk && worldOld.isBlockLoaded(new BlockPos(i << 4, 63, j << 4), true))
            {
                Chunk chunkOld = worldOld.getChunkFromChunkCoords(player.chunkCoordX, player.chunkCoordZ);
                chunkOld.removeEntity(player);
                chunkOld.setModified(true);
            }

            worldOld.loadedEntityList.remove(player);
            worldOld.onEntityRemoved(player);
            worldNew.spawnEntity(player);
            player.setWorld(worldNew);
            ((WorldServer) worldNew).getChunkProvider().loadChunk(pair.x, pair.z);
            worldNew.updateEntityWithOptionalForce(player, false);

            if (!nether)
            {
                player.setLocationAndAngles(x + 0.5F, y + 0.5F, z + 0.5F, player.rotationYaw, player.rotationPitch);
                player.mcServer.getPlayerList().preparePlayer(player, (WorldServer) worldNew);
                player.connection.setPlayerLocation(x + 0.5F, y + 0.5F, z + 0.5F, player.rotationYaw, player.rotationPitch);
            }
            else
            {
                if (blockpos != null)
                {
                    BlockPos blockpos1 = EntityPlayer.getBedSpawnLocation(player.mcServer.getWorld(player.dimension), blockpos, flag);

                    if (blockpos1 != null)
                    {
                        player.setLocationAndAngles(blockpos1.getX() + 0.5F, blockpos1.getY() + 0.1F, blockpos1.getZ() + 0.5F, 0.0F, 0.0F);
                        player.setSpawnPoint(blockpos, flag);
                    }
                }
            }

            player.interactionManager.setWorld((WorldServer) worldNew);
            player.mcServer.getPlayerList().updateTimeAndWeatherForPlayer(player, (WorldServer) worldNew);
            player.mcServer.getPlayerList().syncPlayerInventory(player);
            player.setSneaking(false);

            player.getActivePotionEffects().forEach(potion ->
            {
                player.connection.sendPacket(new SPacketEntityEffect(player.getEntityId(), potion));
            });
            player.connection.sendPacket(new SPacketSetExperience(player.experience, player.experienceTotal, player.experienceLevel));
        }
        else
        {
            player.closeScreen();
            player.setSneaking(false);
            worldNew.updateEntityWithOptionalForce(player, false);
            player.connection.setPlayerLocation(x + 0.5F, y + 0.5F, z + 0.5F, player.rotationYaw, player.rotationPitch);
            player.setLocationAndAngles(x + 0.5F, y + 0.5F, z + 0.5F, player.rotationYaw, player.rotationPitch);
            worldNew.updateEntityWithOptionalForce(player, false);
        }
        FMLCommonHandler.instance().firePlayerChangedDimensionEvent(player, oldDimID, dimID);
        return player;
    }

    public static EntityPlayer teleportPlayerToPlanet(EntityPlayerMP player, MinecraftServer server, int sourceDim, int targetDim)
    {
        WorldServer sourceWorld = server.getWorld(sourceDim);
        WorldServer targetWorld = server.getWorld(targetDim);
        BlockPos spawnPos = targetWorld.getTopSolidOrLiquidBlock(targetWorld.getSpawnPoint());
        PlayerList playerList = server.getPlayerList();
        GCPlayerStats stats = GCPlayerStats.get(player);
        InventoryExtended inv = stats.getExtendedInventory();

        player.dimension = targetDim;
        player.connection.sendPacket(new SPacketRespawn(player.dimension, targetWorld.getDifficulty(), targetWorld.getWorldInfo().getTerrainType(), player.interactionManager.getGameType()));
        playerList.updatePermissionLevel(player);
        sourceWorld.removeEntityDangerously(player);
        player.isDead = false;

        // world transfer
        player.setLocationAndAngles(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ(), player.rotationYaw, player.rotationPitch);
        player.connection.setPlayerLocation(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ(), player.rotationYaw, player.rotationPitch);
        targetWorld.spawnEntity(player);
        targetWorld.updateEntityWithOptionalForce(player, false);
        player.setWorld(targetWorld);
        MorePlanetsMod.PROXY.resetFloatingTick(player);//prevent kicked from lan or server world

        playerList.preparePlayer(player, sourceWorld);
        player.connection.setPlayerLocation(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ(), player.rotationYaw, player.rotationPitch);
        player.interactionManager.setWorld(targetWorld);
        player.connection.sendPacket(new SPacketPlayerAbilities(player.capabilities));
        playerList.updateTimeAndWeatherForPlayer(player, targetWorld);
        playerList.syncPlayerInventory(player);

        if (targetWorld.provider instanceof IStartedDimension)
        {
            IStartedDimension dimension = (IStartedDimension)targetWorld.provider;
            LoggerMP.info("Setting up survival player gear");
            dimension.setup(player);
        }
        else if (targetWorld.provider instanceof WorldProviderMoon)
        {
            LoggerMP.info("Setting up default survival player gear for Moon");
            inv.setInventorySlotContents(0, new ItemStack(GCItems.oxMask));
            inv.setInventorySlotContents(1, new ItemStack(GCItems.oxygenGear));
            inv.setInventorySlotContents(2, new ItemStack(GCItems.oxTankMedium));
            inv.setInventorySlotContents(3, new ItemStack(GCItems.oxTankMedium));
            inv.setInventorySlotContents(4, new ItemStack(GCItems.parachute));
            inv.setInventorySlotContents(5, new ItemStack(GCItems.basicItem, 1, 19));
            player.inventory.addItemStackToInventory(new ItemStack(Blocks.LOG, 32 + player.world.rand.nextInt(32)));
            player.inventory.addItemStackToInventory(new ItemStack(Blocks.COBBLESTONE, 32 + player.world.rand.nextInt(32)));
            player.inventory.addItemStackToInventory(new ItemStack(Blocks.DIRT, 16));
            player.inventory.addItemStackToInventory(new ItemStack(Blocks.SAPLING, 8 + player.world.rand.nextInt(8)));
            player.inventory.addItemStackToInventory(new ItemStack(Items.IRON_INGOT, 32 + player.world.rand.nextInt(16)));
            player.inventory.addItemStackToInventory(new ItemStack(Items.WHEAT_SEEDS, 16 + player.world.rand.nextInt(16)));
            player.inventory.addItemStackToInventory(new ItemStack(Items.POTATO, 16 + player.world.rand.nextInt(16)));
            player.inventory.addItemStackToInventory(new ItemStack(Items.CARROT, 16 + player.world.rand.nextInt(16)));
            player.inventory.addItemStackToInventory(new ItemStack(Items.WATER_BUCKET));
            player.inventory.addItemStackToInventory(new ItemStack(Items.WATER_BUCKET));
            player.inventory.addItemStackToInventory(new ItemStack(GCBlocks.landingPad, 9));
            ItemStack rocket = new ItemStack(GCItems.rocketTier1);
            rocket.setTagCompound(new NBTTagCompound());
            rocket.getTagCompound().setInteger("RocketFuel", 1000);
            player.inventory.addItemStackToInventory(rocket);
            player.inventory.addItemStackToInventory(new ItemStack(AsteroidsItems.canisterLOX));
            player.inventory.addItemStackToInventory(new ItemStack(AsteroidsItems.canisterLOX));
            player.inventory.addItemStackToInventory(new ItemStack(AsteroidsItems.canisterLOX));
        }
        else if (targetWorld.provider instanceof WorldProviderMars)
        {
            LoggerMP.info("Setting up default survival player gear for Mars");
            SchematicRegistry.unlockNewPage(player, new ItemStack(GCItems.schematic, 1, 1)); //Knows how to build T2 rocket
            inv.setInventorySlotContents(0, new ItemStack(GCItems.oxMask));
            inv.setInventorySlotContents(1, new ItemStack(GCItems.oxygenGear));
            inv.setInventorySlotContents(2, new ItemStack(GCItems.oxTankMedium));
            inv.setInventorySlotContents(3, new ItemStack(GCItems.oxTankMedium));
            inv.setInventorySlotContents(4, new ItemStack(GCItems.parachute));
            inv.setInventorySlotContents(5, new ItemStack(GCItems.basicItem, 1, 19));
            inv.setInventorySlotContents(6, new ItemStack(AsteroidsItems.thermalPadding));
            inv.setInventorySlotContents(7, new ItemStack(AsteroidsItems.thermalPadding, 1, 1));
            inv.setInventorySlotContents(8, new ItemStack(AsteroidsItems.thermalPadding, 1, 2));
            inv.setInventorySlotContents(9, new ItemStack(AsteroidsItems.thermalPadding, 1, 3));
            player.inventory.addItemStackToInventory(new ItemStack(Blocks.LOG, 32 + player.world.rand.nextInt(32)));
            player.inventory.addItemStackToInventory(new ItemStack(Blocks.COBBLESTONE, 32 + player.world.rand.nextInt(32)));
            player.inventory.addItemStackToInventory(new ItemStack(Blocks.DIRT, 16));
            player.inventory.addItemStackToInventory(new ItemStack(Blocks.SAPLING, 8 + player.world.rand.nextInt(8)));
            player.inventory.addItemStackToInventory(new ItemStack(Items.IRON_INGOT, 32 + player.world.rand.nextInt(16)));
            player.inventory.addItemStackToInventory(new ItemStack(Items.WHEAT_SEEDS, 16 + player.world.rand.nextInt(16)));
            player.inventory.addItemStackToInventory(new ItemStack(Items.POTATO, 16 + player.world.rand.nextInt(16)));
            player.inventory.addItemStackToInventory(new ItemStack(Items.CARROT, 16 + player.world.rand.nextInt(16)));
            player.inventory.addItemStackToInventory(new ItemStack(Items.WATER_BUCKET));
            player.inventory.addItemStackToInventory(new ItemStack(Items.WATER_BUCKET));
            player.inventory.addItemStackToInventory(new ItemStack(GCBlocks.landingPad, 9));
            ItemStack rocket = new ItemStack(MarsItems.rocketMars);
            rocket.setTagCompound(new NBTTagCompound());
            rocket.getTagCompound().setInteger("RocketFuel", 1000);
            player.inventory.addItemStackToInventory(rocket);
            player.inventory.addItemStackToInventory(new ItemStack(AsteroidsItems.canisterLOX));
            player.inventory.addItemStackToInventory(new ItemStack(AsteroidsItems.canisterLOX));
            player.inventory.addItemStackToInventory(new ItemStack(AsteroidsItems.canisterLOX));
            player.inventory.addItemStackToInventory(new ItemStack(AsteroidsItems.canisterLOX));
        }
        else if (targetWorld.provider instanceof WorldProviderVenus)
        {
            LoggerMP.info("Setting up default survival player gear for Venus");
            SchematicRegistry.unlockNewPage(player, new ItemStack(GCItems.schematic, 1, 1)); //Knows how to build T2 rocket
            SchematicRegistry.unlockNewPage(player, new ItemStack(MarsItems.schematic, 1, 0)); //Knows how to build T3 rocket
            inv.setInventorySlotContents(0, new ItemStack(GCItems.oxMask));
            inv.setInventorySlotContents(1, new ItemStack(GCItems.oxygenGear));
            inv.setInventorySlotContents(2, new ItemStack(GCItems.oxTankHeavy));
            inv.setInventorySlotContents(3, new ItemStack(GCItems.oxTankHeavy));
            inv.setInventorySlotContents(4, new ItemStack(GCItems.parachute));
            inv.setInventorySlotContents(5, new ItemStack(GCItems.basicItem, 1, 19));
            inv.setInventorySlotContents(6, new ItemStack(VenusItems.thermalPaddingTier2));
            inv.setInventorySlotContents(7, new ItemStack(VenusItems.thermalPaddingTier2, 1, 1));
            inv.setInventorySlotContents(8, new ItemStack(VenusItems.thermalPaddingTier2, 1, 2));
            inv.setInventorySlotContents(9, new ItemStack(VenusItems.thermalPaddingTier2, 1, 3));
            inv.setInventorySlotContents(10, new ItemStack(VenusItems.basicItem));
            player.inventory.addItemStackToInventory(new ItemStack(Blocks.LOG, 32 + player.world.rand.nextInt(32)));
            player.inventory.addItemStackToInventory(new ItemStack(Blocks.COBBLESTONE, 32 + player.world.rand.nextInt(32)));
            player.inventory.addItemStackToInventory(new ItemStack(Blocks.DIRT, 16));
            player.inventory.addItemStackToInventory(new ItemStack(Blocks.SAPLING, 8 + player.world.rand.nextInt(8)));
            player.inventory.addItemStackToInventory(new ItemStack(Items.IRON_INGOT, 32 + player.world.rand.nextInt(16)));
            player.inventory.addItemStackToInventory(new ItemStack(Items.WHEAT_SEEDS, 16 + player.world.rand.nextInt(16)));
            player.inventory.addItemStackToInventory(new ItemStack(Items.POTATO, 16 + player.world.rand.nextInt(16)));
            player.inventory.addItemStackToInventory(new ItemStack(Items.CARROT, 16 + player.world.rand.nextInt(16)));
            player.inventory.addItemStackToInventory(new ItemStack(Items.WATER_BUCKET));
            player.inventory.addItemStackToInventory(new ItemStack(Items.WATER_BUCKET));
            player.inventory.addItemStackToInventory(new ItemStack(GCBlocks.landingPad, 9));
            ItemStack rocket = new ItemStack(AsteroidsItems.tier3Rocket);
            rocket.setTagCompound(new NBTTagCompound());
            rocket.getTagCompound().setInteger("RocketFuel", 1000);
            player.inventory.addItemStackToInventory(rocket);
            player.inventory.addItemStackToInventory(new ItemStack(AsteroidsItems.canisterLOX));
            player.inventory.addItemStackToInventory(new ItemStack(AsteroidsItems.canisterLOX));
            player.inventory.addItemStackToInventory(new ItemStack(AsteroidsItems.canisterLOX));
            player.inventory.addItemStackToInventory(new ItemStack(AsteroidsItems.canisterLOX));
        }
        else if (targetWorld.provider instanceof WorldProviderAsteroids)//TODO Fix can't /kill??
        {
            LoggerMP.info("Setting up default survival player gear for Asteroids");
            ITeleportType type = GalacticraftRegistry.getTeleportTypeForDimension(targetWorld.provider.getClass());
            Vector3 spawnPosVec = type.getPlayerSpawnLocation(targetWorld, player);
            ChunkPos pair = targetWorld.getChunkFromChunkCoords(spawnPosVec.intX() >> 4, spawnPosVec.intZ() >> 4).getPos();
            player.setLocationAndAngles(spawnPosVec.x, spawnPosVec.y, spawnPosVec.z, player.rotationYaw, player.rotationPitch);
            player.setSpawnChunk(new BlockPos(spawnPosVec.intX(), spawnPosVec.intY(), spawnPosVec.intZ()), true, GCCoreUtil.getDimensionID(targetWorld));
            targetWorld.getChunkProvider().loadChunk(pair.x, pair.z);
            type.setupAdventureSpawn(player);
            type.onSpaceDimensionChanged(targetWorld, player, false);
        }
        else
        {
            LoggerMP.info("Setting up default survival player gear for Non-IStartedDimension world");
            SchematicRegistry.unlockNewPage(player, new ItemStack(GCItems.schematic, 1, 1)); //Knows how to build T2 rocket
            SchematicRegistry.unlockNewPage(player, new ItemStack(MarsItems.schematic, 1, 0)); //Knows how to build T3 rocket
            inv.setInventorySlotContents(0, new ItemStack(GCItems.oxMask));
            inv.setInventorySlotContents(1, new ItemStack(GCItems.oxygenGear));
            inv.setInventorySlotContents(2, new ItemStack(GCItems.oxTankHeavy));
            inv.setInventorySlotContents(3, new ItemStack(GCItems.oxTankHeavy));
            inv.setInventorySlotContents(5, new ItemStack(GCItems.basicItem, 1, 19));
            inv.setInventorySlotContents(6, new ItemStack(AsteroidsItems.thermalPadding));
            inv.setInventorySlotContents(7, new ItemStack(AsteroidsItems.thermalPadding, 1, 1));
            inv.setInventorySlotContents(8, new ItemStack(AsteroidsItems.thermalPadding, 1, 2));
            inv.setInventorySlotContents(9, new ItemStack(AsteroidsItems.thermalPadding, 1, 3));
            player.inventory.addItemStackToInventory(new ItemStack(AsteroidsItems.canisterLOX));
            player.inventory.addItemStackToInventory(new ItemStack(AsteroidsItems.canisterLOX));
            player.inventory.addItemStackToInventory(new ItemStack(AsteroidsItems.canisterLOX));
        }

        player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 15 * 20, 5));

        player.getActivePotionEffects().forEach(potion ->
        {
            player.connection.sendPacket(new SPacketEntityEffect(player.getEntityId(), potion));
        });
        player.connection.sendPacket(new SPacketSetExperience(player.experience, player.experienceTotal, player.experienceLevel));
        player.connection.sendPacket(new SPacketSpawnPosition(spawnPos));
        AttributeMap attributemap = (AttributeMap) player.getAttributeMap();
        Collection<IAttributeInstance> watchedAttribs = attributemap.getWatchedAttributes();

        if (!watchedAttribs.isEmpty())
        {
            player.connection.sendPacket(new SPacketEntityProperties(player.getEntityId(), watchedAttribs));
        }

        FMLCommonHandler.instance().firePlayerChangedDimensionEvent(player, sourceDim, targetDim);

        if (!(targetWorld.provider instanceof WorldProviderAsteroids))
        {
            player.setLocationAndAngles(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ(), player.rotationYaw, player.rotationPitch);
            player.setSpawnChunk(spawnPos, true, GCCoreUtil.getDimensionID(player.world));
        }

        if (!(targetWorld.provider instanceof WorldProviderAsteroids) && player.onGround && player.getBedLocation(GCCoreUtil.getDimensionID(player.world)) == null)
        {
            int i = 30000000;
            int x = Math.min(i, Math.max(-i, MathHelper.floor(player.posX + 0.5D)));
            int y = Math.min(256, Math.max(0, MathHelper.floor(player.posY + 1.5D)));
            int z = Math.min(i, Math.max(-i, MathHelper.floor(player.posZ + 0.5D)));
            BlockPos spawnChunkPos = targetWorld.getTopSolidOrLiquidBlock(new BlockPos(x, y, z));
            player.setSpawnChunk(spawnChunkPos, true, GCCoreUtil.getDimensionID(player.world));
        }
        GalacticraftCore.packetPipeline.sendTo(new PacketSimpleMP(EnumSimplePacketMP.C_RELOAD_RENDERER, player.dimension), player);
        return player;
    }
}