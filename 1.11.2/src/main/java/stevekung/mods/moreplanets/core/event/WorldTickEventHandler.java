package stevekung.mods.moreplanets.core.event;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.ServerTickEvent;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.core.data.WorldDataStartedDimension;
import stevekung.mods.moreplanets.init.MPBiomes;
import stevekung.mods.moreplanets.module.planets.diona.dimension.WorldProviderDiona;
import stevekung.mods.moreplanets.module.planets.diona.entity.EntityAlienBeam;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.module.planets.nibiru.dimension.WorldProviderNibiru;
import stevekung.mods.moreplanets.module.planets.nibiru.entity.weather.EntityNibiruLightningBolt;

public class WorldTickEventHandler
{
    private int updateLCG = new Random().nextInt();
    public static WorldDataStartedDimension startedDimensionData = null;

    @SubscribeEvent
    public void onServerTick(ServerTickEvent event)
    {
        MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();

        if (server == null)
        {
            return;
        }

        if (event.phase == Phase.START)
        {
            if (WorldTickEventHandler.startedDimensionData == null)
            {
                World world = FMLCommonHandler.instance().getMinecraftServerInstance().worldServerForDimension(0);
                WorldTickEventHandler.startedDimensionData = (WorldDataStartedDimension) world.getMapStorage().getOrLoadData(WorldDataStartedDimension.class, WorldDataStartedDimension.saveDataID);

                if (WorldTickEventHandler.startedDimensionData == null)
                {
                    WorldTickEventHandler.startedDimensionData = new WorldDataStartedDimension(WorldDataStartedDimension.saveDataID);
                    world.getMapStorage().setData(WorldDataStartedDimension.saveDataID, WorldTickEventHandler.startedDimensionData);
                }
            }
        }
        if (event.phase == Phase.END)
        {
            World world = FMLCommonHandler.instance().getMinecraftServerInstance().worldServerForDimension(ConfigManagerMP.idDimensionDiona);

            if (world.provider instanceof WorldProviderDiona)
            {
                if (world instanceof WorldServer)
                {
                    WorldServer worldServer = (WorldServer) world;

                    for (Iterator<Chunk> iterator = worldServer.getPersistentChunkIterable(worldServer.getPlayerChunkMap().getChunkIterator()); iterator.hasNext(); worldServer.theProfiler.endSection())
                    {
                        Chunk chunk = iterator.next();
                        int j = chunk.xPosition * 16;
                        int k = chunk.zPosition * 16;

                        if (worldServer.rand.nextInt(75000) == 0)
                        {
                            this.updateLCG = this.updateLCG * 3 + 1013904223;
                            int l = this.updateLCG >> 2;
                            BlockPos blockpos = this.adjustPosToNearbyEntity(worldServer, new BlockPos(j + (l & 15), 0, k + (l >> 8 & 15)));

                            if (this.canBeamStrike(worldServer, blockpos))
                            {
                                EntityAlienBeam beam = new EntityAlienBeam(worldServer);
                                beam.setLocationAndAngles(blockpos.getX(), blockpos.getY(), blockpos.getZ(), 0.0F, 0.0F);
                                worldServer.spawnEntity(beam);
                            }
                        }
                    }
                }
            }

            world = FMLCommonHandler.instance().getMinecraftServerInstance().worldServerForDimension(ConfigManagerMP.idDimensionNibiru);

            if (world.provider instanceof WorldProviderNibiru)
            {
                if (world instanceof WorldServer)
                {
                    WorldServer worldServer = (WorldServer) world;

                    for (Iterator<Chunk> iterator = worldServer.getPersistentChunkIterable(worldServer.getPlayerChunkMap().getChunkIterator()); iterator.hasNext(); worldServer.theProfiler.endSection())
                    {
                        Chunk chunk = iterator.next();
                        int j = chunk.xPosition * 16;
                        int k = chunk.zPosition * 16;
                        boolean flag = worldServer.isRaining();
                        boolean flag1 = worldServer.isThundering();

                        if (worldServer.provider.canDoLightning(chunk) && flag && flag1 && worldServer.rand.nextInt(1000) == 0)
                        {
                            this.updateLCG = this.updateLCG * 3 + 1013904223;
                            int l = this.updateLCG >> 2;
                            BlockPos blockpos = this.adjustPosToNearbyEntity(worldServer, new BlockPos(j + (l & 15), 0, k + (l >> 8 & 15)));

                            if (worldServer.isRainingAt(blockpos))
                            {
                                worldServer.spawnEntity(new EntityNibiruLightningBolt(worldServer, blockpos.getX(), blockpos.getY(), blockpos.getZ(), true));
                            }
                        }
                        if (worldServer.rand.nextInt(16) == 0)
                        {
                            this.updateLCG = this.updateLCG * 3 + 1013904223;
                            int j2 = this.updateLCG >> 2;
                            BlockPos blockpos1 = worldServer.getPrecipitationHeight(new BlockPos(j + (j2 & 15), 0, k + (j2 >> 8 & 15)));
                            BlockPos blockpos2 = blockpos1.down();

                            if (worldServer.canBlockFreezeNoWater(blockpos2))
                            {
                                worldServer.setBlockState(blockpos2, NibiruBlocks.INFECTED_ICE.getDefaultState());
                            }
                            if (flag && worldServer.canSnowAt(blockpos1, true))
                            {
                                worldServer.setBlockState(blockpos1, NibiruBlocks.INFECTED_SNOW_LAYER.getDefaultState());
                            }
                            if (flag && worldServer.getBiome(blockpos2).canRain())
                            {
                                worldServer.getBlockState(blockpos2).getBlock().fillWithRain(worldServer, blockpos2);
                            }
                        }
                        if (worldServer.getBiome(new BlockPos(j, 0, k)) == MPBiomes.INFECTED_DESERT || worldServer.getBiome(new BlockPos(j, 0, k)) == MPBiomes.INFECTED_EXTREME_HILLS)
                        {
                            if (worldServer.rand.nextInt(250000) == 0)
                            {
                                this.updateLCG = this.updateLCG * 3 + 1013904223;
                                int l = this.updateLCG >> 2;
                                BlockPos blockpos = this.adjustPosToNearbyEntity(worldServer, new BlockPos(j + (l & 15), 0, k + (l >> 8 & 15)));
                                worldServer.spawnEntity(new EntityNibiruLightningBolt(worldServer, blockpos.getX(), blockpos.getY(), blockpos.getZ(), false));
                            }
                        }
                    }
                }
            }
        }
    }

    private BlockPos adjustPosToNearbyEntity(WorldServer world, BlockPos pos)
    {
        BlockPos blockpos = world.getPrecipitationHeight(pos);
        AxisAlignedBB axisalignedbb = new AxisAlignedBB(blockpos, new BlockPos(blockpos.getX(), world.getHeight(), blockpos.getZ()));
        List<EntityLivingBase> list = world.getEntitiesWithinAABB(EntityLivingBase.class, axisalignedbb, living -> living != null && living.isEntityAlive() && world.canSeeSky(living.getPosition()));

        if (!list.isEmpty())
        {
            return list.get(world.rand.nextInt(list.size())).getPosition();
        }
        else
        {
            if (blockpos.getY() == -1)
            {
                blockpos = blockpos.up(2);
            }
            return blockpos;
        }
    }

    private boolean canBeamStrike(WorldServer world, BlockPos strikePosition)
    {
        if (!world.canSeeSky(strikePosition))
        {
            return false;
        }
        else if (world.getPrecipitationHeight(strikePosition).getY() > strikePosition.getY())
        {
            return false;
        }
        else
        {
            return world.getSunBrightness(1.0F) < 0.25F;
        }
    }
}