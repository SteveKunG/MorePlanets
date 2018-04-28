package stevekung.mods.moreplanets.util;

import micdoodle8.mods.galacticraft.api.recipe.SchematicRegistry;
import micdoodle8.mods.galacticraft.api.world.IGalacticraftWorldProvider;
import micdoodle8.mods.galacticraft.core.GCItems;
import micdoodle8.mods.galacticraft.core.entities.player.GCPlayerStats;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import micdoodle8.mods.galacticraft.planets.asteroids.items.AsteroidsItems;
import micdoodle8.mods.galacticraft.planets.mars.items.MarsItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketEntityEffect;
import net.minecraft.network.play.server.SPacketPlayerAbilities;
import net.minecraft.network.play.server.SPacketRespawn;
import net.minecraft.network.play.server.SPacketSetExperience;
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
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.util.helper.WorldDimensionHelper;
import stevekung.mods.moreplanets.world.IStartedDimension;

public class TeleportUtil
{
    public static Entity teleportEntity(Entity entity, int dimension, double xCoord, double yCoord, double zCoord)
    {
        return TeleportUtil.teleportEntity(entity, dimension, xCoord, yCoord, zCoord, 0.0F, 0.0F);
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
            return TeleportUtil.handleEntityTeleport(entity, server, sourceDim, dimension, xCoord, yCoord, zCoord, yaw, pitch);
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
                return TeleportUtil.teleportPlayerInternational((EntityPlayerMP) entity, server, sourceDim, targetDim, xCoord, yCoord, zCoord, yaw, pitch);
            }
            else
            {
                return TeleportUtil.teleportEntityInternational(entity, server, sourceDim, targetDim, xCoord, yCoord, zCoord, yaw, pitch);
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
                    MPLog.error("Cannot Transfer Entity to Dimension: Could not get World for Dimension {}", dimID);
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

    @Deprecated
    public static void startNewDimension(EntityPlayerMP player)
    {
        WorldServer worldOld = (WorldServer) player.world;
        WorldServer worldNew = WorldDimensionHelper.getStartWorld(worldOld);
        BlockPos blockpos = worldNew.getTopSolidOrLiquidBlock(worldNew.getSpawnPoint());
        boolean dimChange = player.world != worldNew;
        player.world.updateEntityWithOptionalForce(player, false);
        int oldDimID = GCCoreUtil.getDimensionID(player.world);
        ChunkPos pair = worldNew.getChunkFromChunkCoords(blockpos.getX(), blockpos.getZ()).getPos();

        if (dimChange)
        {
            try
            {
                worldOld.getPlayerChunkMap().removePlayer(player);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            player.closeScreen();
            player.dimension = GCCoreUtil.getDimensionID(worldNew);
            player.connection.sendPacket(new SPacketRespawn(GCCoreUtil.getDimensionID(worldNew), player.world.getDifficulty(), player.world.getWorldInfo().getTerrainType(), player.interactionManager.getGameType()));
            worldOld.playerEntities.remove(player);
            worldOld.updateAllPlayersSleepingFlag();

            if (player.addedToChunk && worldOld.getChunkProvider().chunkExists(player.chunkCoordX, player.chunkCoordZ))
            {
                Chunk chunkOld = worldOld.getChunkFromChunkCoords(player.chunkCoordX, player.chunkCoordZ);
                chunkOld.removeEntity(player);
                chunkOld.setModified(true);
            }

            worldOld.loadedEntityList.remove(player);
            worldOld.onEntityRemoved(player);
            worldNew.spawnEntity(player);
            player.setWorld(worldNew);
            MorePlanetsMod.PROXY.resetFloatingTick(player);

            if (!(worldNew.provider instanceof IGalacticraftWorldProvider))
            {
                MPLog.error("{} is not space world!", ConfigManagerMP.moreplanets_general.startedPlanet);
                throw new RuntimeException(ConfigManagerMP.moreplanets_general.startedPlanet + " is not space world!");
            }

            if (worldNew.provider instanceof IStartedDimension)
            {
                IStartedDimension dimension = (IStartedDimension) worldNew.provider;
                MPLog.debug("Setting up player gear");
                dimension.setup(player);
            }
            else
            {
                GCPlayerStats stats = GCPlayerStats.get(player);
                MPLog.debug("Setting up player gear for Non-IStartedDimension world");
                SchematicRegistry.unlockNewPage(player, new ItemStack(GCItems.schematic, 1, 1)); //Knows how to build T2 rocket
                SchematicRegistry.unlockNewPage(player, new ItemStack(MarsItems.schematic, 1, 0)); //Knows how to build T3 rocket
                stats.getExtendedInventory().setInventorySlotContents(0, new ItemStack(GCItems.oxMask, 1, 0));
                stats.getExtendedInventory().setInventorySlotContents(1, new ItemStack(GCItems.oxygenGear, 1, 0));
                stats.getExtendedInventory().setInventorySlotContents(2, new ItemStack(GCItems.oxTankHeavy, 1, 0));
                stats.getExtendedInventory().setInventorySlotContents(3, new ItemStack(GCItems.oxTankHeavy, 1, 0));
                stats.getExtendedInventory().setInventorySlotContents(5, new ItemStack(GCItems.basicItem, 1, 19));
                stats.getExtendedInventory().setInventorySlotContents(6, new ItemStack(AsteroidsItems.thermalPadding, 1, 0));
                stats.getExtendedInventory().setInventorySlotContents(7, new ItemStack(AsteroidsItems.thermalPadding, 1, 1));
                stats.getExtendedInventory().setInventorySlotContents(8, new ItemStack(AsteroidsItems.thermalPadding, 1, 2));
                stats.getExtendedInventory().setInventorySlotContents(9, new ItemStack(AsteroidsItems.thermalPadding, 1, 3));
                player.inventory.addItemStackToInventory(new ItemStack(AsteroidsItems.canisterLOX));
                player.inventory.addItemStackToInventory(new ItemStack(AsteroidsItems.canisterLOX));
                player.inventory.addItemStackToInventory(new ItemStack(AsteroidsItems.canisterLOX));
            }

            worldNew.getChunkProvider().loadChunk(pair.x, pair.z);
            worldNew.updateEntityWithOptionalForce(player, false);
            player.setLocationAndAngles(blockpos.getX(), blockpos.getY() + 16.0D, blockpos.getZ(), player.rotationYaw, player.rotationPitch);
            player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 15 * 20, 5));
            player.mcServer.getPlayerList().preparePlayer(player, worldNew);
            player.connection.setPlayerLocation(blockpos.getX(), blockpos.getY() + 16.0D, blockpos.getZ(), player.rotationYaw, player.rotationPitch);
            player.interactionManager.setWorld(worldNew);
            player.mcServer.getPlayerList().updateTimeAndWeatherForPlayer(player, worldNew);
            player.mcServer.getPlayerList().syncPlayerInventory(player);

            player.getActivePotionEffects().forEach(potion ->
            {
                player.connection.sendPacket(new SPacketEntityEffect(player.getEntityId(), potion));
            });
            player.connection.sendPacket(new SPacketSetExperience(player.experience, player.experienceTotal, player.experienceLevel));
        }
        else
        {
            player.closeScreen();
            worldNew.updateEntityWithOptionalForce(player, false);
            player.connection.setPlayerLocation(blockpos.getX(), blockpos.getY() + 16.0D, blockpos.getZ(), player.rotationYaw, player.rotationPitch);
            player.setLocationAndAngles(blockpos.getX(), blockpos.getY() + 16.0D, blockpos.getZ(), player.rotationYaw, player.rotationPitch);
            player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 15 * 20, 5));
            worldNew.updateEntityWithOptionalForce(player, false);
        }

        FMLCommonHandler.instance().firePlayerChangedDimensionEvent(player, oldDimID, GCCoreUtil.getDimensionID(worldNew));

        if (player.onGround && player.getBedLocation(GCCoreUtil.getDimensionID(player.world)) == null)
        {
            int i = 30000000;
            int j = Math.min(i, Math.max(-i, MathHelper.floor(player.posX + 0.5D)));
            int k = Math.min(256, Math.max(0, MathHelper.floor(player.posY + 1.5D)));
            int l = Math.min(i, Math.max(-i, MathHelper.floor(player.posZ + 0.5D)));
            BlockPos coords = new BlockPos(j, k, l);
            player.setSpawnChunk(coords, true, GCCoreUtil.getDimensionID(player.world));
        }
    }
}