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
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;
import stevekung.mods.moreplanets.core.data.WorldDataStartedDimension;
import stevekung.mods.moreplanets.init.MPBiomes;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.planets.diona.dimension.WorldProviderDiona;
import stevekung.mods.moreplanets.planets.diona.entity.EntityAlienBeam;
import stevekung.mods.moreplanets.planets.nibiru.dimension.WorldProviderNibiru;
import stevekung.mods.moreplanets.planets.nibiru.entity.weather.EntityNibiruLightningBolt;

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
                World world = server.getWorld(0);
                WorldTickEventHandler.startedDimensionData = (WorldDataStartedDimension) world.getMapStorage().getOrLoadData(WorldDataStartedDimension.class, WorldDataStartedDimension.saveDataID);

                if (WorldTickEventHandler.startedDimensionData == null)
                {
                    WorldTickEventHandler.startedDimensionData = new WorldDataStartedDimension(WorldDataStartedDimension.saveDataID);
                    world.getMapStorage().setData(WorldDataStartedDimension.saveDataID, WorldTickEventHandler.startedDimensionData);
                }
            }
        }
    }

    @SubscribeEvent
    public void onWorldTick(WorldTickEvent event)
    {
        World world = event.world;

        if (event.phase == Phase.END)//TODO Check entity spawning outside unloaded chunk
        {
            if (world != null && world instanceof WorldServer)
            {
                WorldServer worldServer = (WorldServer) world;

                for (Iterator<Chunk> iterator = worldServer.getPersistentChunkIterable(worldServer.getPlayerChunkMap().getChunkIterator()); iterator.hasNext();)
                {
                    Chunk chunk = iterator.next();
                    int x = chunk.x * 16;
                    int z = chunk.z * 16;
                    this.updateLCG = this.updateLCG * 3 + 1013904223;
                    int l = this.updateLCG >> 2;
                    BlockPos pos = this.adjustPosToNearbyEntity(worldServer, new BlockPos(x + (l & 15), 0, z + (l >> 8 & 15)));

                    if (worldServer.provider instanceof WorldProviderDiona)
                    {
                        if (worldServer.rand.nextInt(75000) == 0)
                        {
                            if (this.canBeamStrike(worldServer, pos))
                            {
                                EntityAlienBeam beam = new EntityAlienBeam(worldServer);
                                beam.setLocationAndAngles(pos.getX(), pos.getY(), pos.getZ(), 0.0F, 0.0F);

                                if (worldServer.isBlockLoaded(pos))
                                {
                                    worldServer.spawnEntity(beam);
                                }
                            }
                        }
                    }
                    if (worldServer.provider instanceof WorldProviderNibiru)
                    {
                        boolean raining = worldServer.isRaining();
                        boolean thunder = worldServer.isThundering();

                        if (worldServer.provider.canDoLightning(chunk) && raining && thunder && worldServer.rand.nextInt(1000) == 0)
                        {
                            if (worldServer.isRainingAt(pos))
                            {
                                worldServer.spawnEntity(new EntityNibiruLightningBolt(worldServer, pos.getX(), pos.getY(), pos.getZ(), true));
                            }
                        }
                        if (worldServer.rand.nextInt(16) == 0)
                        {
                            BlockPos pos1 = pos.down();

                            if (worldServer.canBlockFreezeNoWater(pos1))
                            {
                                worldServer.setBlockState(pos1, MPBlocks.INFECTED_ICE.getDefaultState());
                            }
                            if (raining && worldServer.canSnowAt(pos, true))
                            {
                                worldServer.setBlockState(pos, MPBlocks.INFECTED_SNOW_LAYER.getDefaultState());
                            }
                            if (raining && worldServer.getBiome(pos1).canRain())
                            {
                                worldServer.getBlockState(pos1).getBlock().fillWithRain(worldServer, pos1);
                            }
                        }
                        if (worldServer.getBiome(pos) == MPBiomes.INFECTED_DESERT || worldServer.getBiome(pos) == MPBiomes.INFECTED_MOUNTAINS)
                        {
                            if (worldServer.rand.nextInt(250000) == 0)
                            {
                                worldServer.spawnEntity(new EntityNibiruLightningBolt(worldServer, pos.getX(), pos.getY(), pos.getZ(), false));
                            }
                        }
                    }
                }
            }
        }
    }

    private BlockPos adjustPosToNearbyEntity(WorldServer world, BlockPos pos)
    {
        BlockPos pos1 = world.getPrecipitationHeight(pos);
        AxisAlignedBB aabb = new AxisAlignedBB(pos1, new BlockPos(pos1.getX(), world.getHeight(), pos1.getZ())).grow(3.0D);
        List<EntityLivingBase> list = world.getEntitiesWithinAABB(EntityLivingBase.class, aabb, entity -> entity != null && entity.isEntityAlive() && world.canSeeSky(entity.getPosition()));

        if (!list.isEmpty())
        {
            return list.get(world.rand.nextInt(list.size())).getPosition();
        }
        else
        {
            if (pos1.getY() == -1)
            {
                pos1 = pos1.up(2);
            }
            return pos1;
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
            return this.getSunBrightness(world) < 0.25F;
        }
    }

    private float getSunBrightness(WorldServer world)
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