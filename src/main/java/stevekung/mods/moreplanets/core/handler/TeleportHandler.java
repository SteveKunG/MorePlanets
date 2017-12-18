package stevekung.mods.moreplanets.core.handler;

import micdoodle8.mods.galacticraft.api.recipe.SchematicRegistry;
import micdoodle8.mods.galacticraft.api.world.IGalacticraftWorldProvider;
import micdoodle8.mods.galacticraft.core.GCItems;
import micdoodle8.mods.galacticraft.core.entities.player.GCPlayerStats;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import micdoodle8.mods.galacticraft.planets.asteroids.items.AsteroidsItems;
import micdoodle8.mods.galacticraft.planets.mars.items.MarsItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketEntityEffect;
import net.minecraft.network.play.server.SPacketRespawn;
import net.minecraft.network.play.server.SPacketSetExperience;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fml.common.FMLCommonHandler;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.util.MPLog;
import stevekung.mods.moreplanets.util.helper.WorldDimensionHelper;
import stevekung.mods.moreplanets.world.IStartedDimension;

public class TeleportHandler
{
    public static EntityPlayerMP setWarpDimension(EntityPlayerMP player, WorldServer world, int x, int y, int z, int dimID, boolean nether)
    {
        if (!world.isRemote)
        {
            MinecraftServer mcServer = FMLCommonHandler.instance().getMinecraftServerInstance();

            if (mcServer != null)
            {
                WorldServer worldServer = mcServer.worldServerForDimension(dimID);

                if (worldServer == null)
                {
                    MPLog.error("Cannot Transfer Entity to Dimension: Could not get World for Dimension " + dimID);
                    return null;
                }
                return TeleportHandler.teleportEntity(worldServer, player, x, y, z, dimID, nether);
            }
        }
        return null;
    }

    private static EntityPlayerMP teleportEntity(World worldNew, EntityPlayerMP player, int x, int y, int z, int dimID, boolean nether)
    {
        BlockPos blockpos = player.getBedLocation(dimID);
        boolean flag = player.isSpawnForced(dimID);
        boolean dimChange = player.worldObj != worldNew;
        player.worldObj.updateEntityWithOptionalForce(player, false);
        int oldDimID = GCCoreUtil.getDimensionID(player.worldObj);
        ChunkPos pair = worldNew.getChunkFromChunkCoords(x, z).getChunkCoordIntPair();
        y = (int) (y + 1.5F);

        if (dimChange)
        {
            World worldOld = player.worldObj;

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
            player.connection.sendPacket(new SPacketRespawn(dimID, player.worldObj.getDifficulty(), player.worldObj.getWorldInfo().getTerrainType(), player.interactionManager.getGameType()));
            worldOld.playerEntities.remove(player);
            worldOld.updateAllPlayersSleepingFlag();
            int i = player.chunkCoordX;
            int j = player.chunkCoordZ;

            if (player.addedToChunk && worldOld.isBlockLoaded(new BlockPos(i << 4, 63, j << 4), true))
            {
                Chunk chunkOld = worldOld.getChunkFromChunkCoords(player.chunkCoordX, player.chunkCoordZ);
                chunkOld.removeEntity(player);
                chunkOld.setChunkModified();
            }

            worldOld.loadedEntityList.remove(player);
            worldOld.onEntityRemoved(player);
            worldNew.spawnEntityInWorld(player);
            player.setWorld(worldNew);
            ((WorldServer) worldNew).getChunkProvider().loadChunk(pair.chunkXPos, pair.chunkZPos);
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
                    BlockPos blockpos1 = EntityPlayer.getBedSpawnLocation(player.mcServer.worldServerForDimension(player.dimension), blockpos, flag);

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

            for (Object o : player.getActivePotionEffects())
            {
                PotionEffect potion = (PotionEffect) o;
                player.connection.sendPacket(new SPacketEntityEffect(player.getEntityId(), potion));
            }
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

    public static void startNewDimension(EntityPlayerMP player)
    {
        WorldServer worldOld = (WorldServer) player.worldObj;
        WorldServer worldNew = WorldDimensionHelper.getStartWorld(worldOld);
        BlockPos blockpos = worldNew.getTopSolidOrLiquidBlock(worldNew.getSpawnPoint());
        boolean dimChange = player.worldObj != worldNew;
        player.worldObj.updateEntityWithOptionalForce(player, false);
        int oldDimID = GCCoreUtil.getDimensionID(player.worldObj);
        ChunkPos pair = worldNew.getChunkFromChunkCoords(blockpos.getX(), blockpos.getZ()).getChunkCoordIntPair();

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
            player.connection.sendPacket(new SPacketRespawn(GCCoreUtil.getDimensionID(worldNew), player.worldObj.getDifficulty(), player.worldObj.getWorldInfo().getTerrainType(), player.interactionManager.getGameType()));
            worldOld.playerEntities.remove(player);
            worldOld.updateAllPlayersSleepingFlag();

            if (player.addedToChunk && worldOld.getChunkProvider().chunkExists(player.chunkCoordX, player.chunkCoordZ))
            {
                Chunk chunkOld = worldOld.getChunkFromChunkCoords(player.chunkCoordX, player.chunkCoordZ);
                chunkOld.removeEntity(player);
                chunkOld.setChunkModified();
            }

            worldOld.loadedEntityList.remove(player);
            worldOld.onEntityRemoved(player);
            worldNew.spawnEntityInWorld(player);
            player.setWorld(worldNew);
            MorePlanetsCore.PROXY.resetFloatingTick(player);

            if (!(worldNew.provider instanceof IGalacticraftWorldProvider))
            {
                MPLog.error("%s is not space world!", ConfigManagerMP.startedPlanet);
                throw new RuntimeException(ConfigManagerMP.startedPlanet + " is not space world!");
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

            worldNew.getChunkProvider().loadChunk(pair.chunkXPos, pair.chunkZPos);
            worldNew.updateEntityWithOptionalForce(player, false);
            player.setLocationAndAngles(blockpos.getX(), blockpos.getY() + 16.0D, blockpos.getZ(), player.rotationYaw, player.rotationPitch);
            player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 15 * 20, 5));
            player.mcServer.getPlayerList().preparePlayer(player, worldNew);
            player.connection.setPlayerLocation(blockpos.getX(), blockpos.getY() + 16.0D, blockpos.getZ(), player.rotationYaw, player.rotationPitch);
            player.interactionManager.setWorld(worldNew);
            player.mcServer.getPlayerList().updateTimeAndWeatherForPlayer(player, worldNew);
            player.mcServer.getPlayerList().syncPlayerInventory(player);

            for (Object o : player.getActivePotionEffects())
            {
                PotionEffect potion = (PotionEffect) o;
                player.connection.sendPacket(new SPacketEntityEffect(player.getEntityId(), potion));
            }
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

        if (player.onGround && player.getBedLocation(GCCoreUtil.getDimensionID(player.worldObj)) == null)
        {
            int i = 30000000;
            int j = Math.min(i, Math.max(-i, MathHelper.floor_double(player.posX + 0.5D)));
            int k = Math.min(256, Math.max(0, MathHelper.floor_double(player.posY + 1.5D)));
            int l = Math.min(i, Math.max(-i, MathHelper.floor_double(player.posZ + 0.5D)));
            BlockPos coords = new BlockPos(j, k, l);
            player.setSpawnChunk(coords, true, GCCoreUtil.getDimensionID(player.worldObj));
        }
    }
}