package stevekung.mods.moreplanets.core.event;

import java.util.List;
import java.util.Random;

import com.google.common.base.Predicate;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.ServerTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;
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
    public void onWorldTick(WorldTickEvent event)
    {
        World world = event.world;

        if (event.phase == Phase.START)
        {
            if (world.provider instanceof WorldProviderDiona)
            {
                for (ChunkCoordIntPair chunkcoordintpair : world.activeChunkSet)
                {
                    int k = chunkcoordintpair.chunkXPos * 16;
                    int l = chunkcoordintpair.chunkZPos * 16;

                    if (world.rand.nextInt(50000) == 0)
                    {
                        this.updateLCG = this.updateLCG * 3 + 1013904223;
                        int i1 = this.updateLCG >> 2;
                        BlockPos blockpos = this.adjustPosToNearbyEntity(world, new BlockPos(k + (i1 & 15), 0, l + (i1 >> 8 & 15)));

                        if (this.canLightningStrike(world, blockpos))
                        {
                            world.spawnEntityInWorld(new EntityAlienBeam(world, blockpos.getX(), blockpos.getY(), blockpos.getZ()));
                        }
                    }
                }
            }
            if (world.provider instanceof WorldProviderNibiru)
            {
                for (ChunkCoordIntPair chunkcoordintpair : world.activeChunkSet)
                {
                    int k = chunkcoordintpair.chunkXPos * 16;
                    int l = chunkcoordintpair.chunkZPos * 16;
                    Chunk chunk = world.getChunkFromChunkCoords(chunkcoordintpair.chunkXPos, chunkcoordintpair.chunkZPos);

                    if (world.rand.nextInt(16) == 0)
                    {
                        this.updateLCG = this.updateLCG * 3 + 1013904223;
                        int k2 = this.updateLCG >> 2;
                        BlockPos blockpos2 = world.getPrecipitationHeight(new BlockPos(k + (k2 & 15), 0, l + (k2 >> 8 & 15)));
                        BlockPos blockpos1 = blockpos2.down();

                        if (world.canBlockFreezeNoWater(blockpos1))
                        {
                            world.setBlockState(blockpos1, NibiruBlocks.INFECTED_ICE.getDefaultState());
                        }
                        if (world.isRaining() && world.canSnowAt(blockpos2, true))
                        {
                            world.setBlockState(blockpos2, NibiruBlocks.INFECTED_SNOW_LAYER.getDefaultState());
                        }
                        if (world.isRaining() && world.getBiomeGenForCoords(blockpos1).canSpawnLightningBolt())
                        {
                            world.getBlockState(blockpos1).getBlock().fillWithRain(world, blockpos1);
                        }
                    }
                    if (world.provider.canDoLightning(chunk) && world.rand.nextInt(1000) == 0 && world.isRaining() && world.isThundering())
                    {
                        this.updateLCG = this.updateLCG * 3 + 1013904223;
                        int i1 = this.updateLCG >> 2;
                        BlockPos blockpos = this.adjustPosToNearbyEntity(world, new BlockPos(k + (i1 & 15), 0, l + (i1 >> 8 & 15)));

                        if (world.canLightningStrike(blockpos))
                        {
                            world.spawnEntityInWorld(new EntityNibiruLightningBolt(world, blockpos.getX(), blockpos.getY(), blockpos.getZ(), true));
                        }
                    }
                    if (world.getBiomeGenForCoords(new BlockPos(k, 0, l)) == MPBiomes.INFECTED_DESERT || world.getBiomeGenForCoords(new BlockPos(k, 0, l)) == MPBiomes.INFECTED_EXTREME_HILLS)
                    {
                        if (world.rand.nextInt(250000) == 0)
                        {
                            this.updateLCG = this.updateLCG * 3 + 1013904223;
                            int i1 = this.updateLCG >> 2;
                            BlockPos blockpos = this.adjustPosToNearbyEntity(world, new BlockPos(k + (i1 & 15), 0, l + (i1 >> 8 & 15)));
                            world.spawnEntityInWorld(new EntityNibiruLightningBolt(world, blockpos.getX(), blockpos.getY(), blockpos.getZ(), false));
                        }
                    }
                }
            }
        }
    }

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
                WorldTickEventHandler.startedDimensionData = (WorldDataStartedDimension) world.getMapStorage().loadData(WorldDataStartedDimension.class, WorldDataStartedDimension.saveDataID);

                if (WorldTickEventHandler.startedDimensionData == null)
                {
                    WorldTickEventHandler.startedDimensionData = new WorldDataStartedDimension(WorldDataStartedDimension.saveDataID);
                    world.getMapStorage().setData(WorldDataStartedDimension.saveDataID, WorldTickEventHandler.startedDimensionData);
                }
            }
        }
    }

    private BlockPos adjustPosToNearbyEntity(World world, BlockPos pos)
    {
        BlockPos blockpos = world.getPrecipitationHeight(pos);
        AxisAlignedBB axisalignedbb = new AxisAlignedBB(blockpos, new BlockPos(blockpos.getX(), world.getHeight(), blockpos.getZ())).expand(1.0D, 1.0D, 1.0D);
        List<EntityLivingBase> list = world.getEntitiesWithinAABB(EntityLivingBase.class, axisalignedbb, new Predicate<EntityLivingBase>()
        {
            @Override
            public boolean apply(EntityLivingBase entity)
            {
                return entity != null && entity.isEntityAlive() && world.canSeeSky(entity.getPosition());
            }
        });
        return !list.isEmpty() ? list.get(world.rand.nextInt(list.size())).getPosition() : blockpos;
    }

    private boolean canLightningStrike(World world, BlockPos strikePosition)
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