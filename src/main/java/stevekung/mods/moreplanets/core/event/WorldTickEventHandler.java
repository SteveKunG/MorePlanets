package stevekung.mods.moreplanets.core.event;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
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
                World world = FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(0);
                WorldTickEventHandler.startedDimensionData = (WorldDataStartedDimension) world.getMapStorage().getOrLoadData(WorldDataStartedDimension.class, WorldDataStartedDimension.saveDataID);

                if (WorldTickEventHandler.startedDimensionData == null)
                {
                    WorldTickEventHandler.startedDimensionData = new WorldDataStartedDimension(WorldDataStartedDimension.saveDataID);
                    world.getMapStorage().setData(WorldDataStartedDimension.saveDataID, WorldTickEventHandler.startedDimensionData);
                }
            }
        }
        if (false&&event.phase == Phase.END)//TODO Fix this
        {
            World world = FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(ConfigManagerMP.moreplanets_dimension.idDimensionDiona);

            if (world.provider instanceof WorldProviderDiona)
            {
                if (world instanceof WorldServer)
                {
                    WorldServer worldServer = (WorldServer) world;

                    for (Iterator<Chunk> iterator = world.getPersistentChunkIterable(worldServer.getPlayerChunkMap().getChunkIterator()); iterator.hasNext();)
                    {
                        Chunk chunk = iterator.next();
                        int j = chunk.x * 16;
                        int k = chunk.z * 16;

                        if (world.rand.nextInt(75000) == 0)
                        {
                            this.updateLCG = this.updateLCG * 3 + 1013904223;
                            int l = this.updateLCG >> 2;
                            BlockPos blockpos = this.adjustPosToNearbyEntity(world, new BlockPos(j + (l & 15), 0, k + (l >> 8 & 15)));

                            if (this.canBeamStrike(world, blockpos))
                            {
                                EntityAlienBeam beam = new EntityAlienBeam(world);
                                beam.setLocationAndAngles(blockpos.getX(), blockpos.getY(), blockpos.getZ(), 0.0F, 0.0F);
                                world.spawnEntity(beam);
                            }
                        }
                    }
                }
            }

            world = FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(ConfigManagerMP.moreplanets_dimension.idDimensionNibiru);

            if (world.provider instanceof WorldProviderNibiru)
            {
                if (world instanceof WorldServer)
                {
                    WorldServer worldServer = (WorldServer) world;

                    for (Iterator<Chunk> iterator = world.getPersistentChunkIterable(worldServer.getPlayerChunkMap().getChunkIterator()); iterator.hasNext();)
                    {
                        Chunk chunk = iterator.next();
                        int j = chunk.x * 16;
                        int k = chunk.z * 16;
                        boolean flag = world.isRaining();
                        boolean flag1 = world.isThundering();

                        if (world.provider.canDoLightning(chunk) && flag && flag1 && world.rand.nextInt(1000) == 0)
                        {
                            this.updateLCG = this.updateLCG * 3 + 1013904223;
                            int l = this.updateLCG >> 2;
                            BlockPos blockpos = this.adjustPosToNearbyEntity(world, new BlockPos(j + (l & 15), 0, k + (l >> 8 & 15)));

                            if (world.isRainingAt(blockpos))
                            {
                                world.spawnEntity(new EntityNibiruLightningBolt(world, blockpos.getX(), blockpos.getY(), blockpos.getZ(), true));
                            }
                        }
                        if (world.rand.nextInt(16) == 0)
                        {
                            this.updateLCG = this.updateLCG * 3 + 1013904223;
                            int j2 = this.updateLCG >> 2;
                            BlockPos blockpos1 = world.getPrecipitationHeight(new BlockPos(j + (j2 & 15), 0, k + (j2 >> 8 & 15)));
                            BlockPos blockpos2 = blockpos1.down();

                            if (world.canBlockFreezeNoWater(blockpos2))
                            {
                                world.setBlockState(blockpos2, NibiruBlocks.INFECTED_ICE.getDefaultState());
                            }
                            if (flag && world.canSnowAt(blockpos1, true))
                            {
                                world.setBlockState(blockpos1, NibiruBlocks.INFECTED_SNOW_LAYER.getDefaultState());
                            }
                            if (flag && world.getBiome(blockpos2).canRain())
                            {
                                world.getBlockState(blockpos2).getBlock().fillWithRain(world, blockpos2);
                            }
                        }
                        if (world.getBiome(new BlockPos(j, 0, k)) == MPBiomes.INFECTED_DESERT || world.getBiome(new BlockPos(j, 0, k)) == MPBiomes.INFECTED_EXTREME_HILLS)
                        {
                            if (world.rand.nextInt(250000) == 0)
                            {
                                this.updateLCG = this.updateLCG * 3 + 1013904223;
                                int l = this.updateLCG >> 2;
                                BlockPos blockpos = this.adjustPosToNearbyEntity(world, new BlockPos(j + (l & 15), 0, k + (l >> 8 & 15)));
                                world.spawnEntity(new EntityNibiruLightningBolt(world, blockpos.getX(), blockpos.getY(), blockpos.getZ(), false));
                            }
                        }
                    }
                }
            }
        }
    }

    private BlockPos adjustPosToNearbyEntity(World world, BlockPos pos)
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

    private boolean canBeamStrike(World world, BlockPos strikePosition)
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
            return this.getSunBrightness(world) < 0.25F;
        }
    }

    private float getSunBrightness(World world)
    {
        float partialTicks = 1.0F;
        float angle = world.getCelestialAngle(partialTicks);
        float celestialAngle = 1.0F - (MathHelper.cos(angle * ((float)Math.PI * 2F)) * 2.0F + 0.2F);
        celestialAngle = MathHelper.clamp(celestialAngle, 0.0F, 1.0F);
        celestialAngle = 1.0F - celestialAngle;
        celestialAngle = (float)(celestialAngle * (1.0D - world.getRainStrength(partialTicks) * 5.0F / 16.0D));
        celestialAngle = (float)(celestialAngle * (1.0D - world.getThunderStrength(partialTicks) * 5.0F / 16.0D));
        return celestialAngle * 0.8F + 0.2F;
    }
}