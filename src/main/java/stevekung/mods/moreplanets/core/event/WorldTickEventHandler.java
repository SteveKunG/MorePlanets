package stevekung.mods.moreplanets.core.event;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import micdoodle8.mods.galacticraft.core.util.WorldUtil;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import stevekung.mods.moreplanets.core.data.WorldDataSurvivalPlanet;
import stevekung.mods.moreplanets.init.MPBiomes;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.planets.diona.dimension.WorldProviderDiona;
import stevekung.mods.moreplanets.planets.diona.entity.EntityAlienBeam;
import stevekung.mods.moreplanets.planets.nibiru.dimension.WorldProviderNibiru;
import stevekung.mods.moreplanets.planets.nibiru.entity.weather.EntityNibiruLightningBolt;
import stevekung.mods.moreplanets.planets.nibiru.world.gen.biome.BiomeInfectedBadlands;
import stevekung.mods.moreplanets.planets.nibiru.world.gen.biome.BiomeInfectedDesert;
import stevekung.mods.moreplanets.planets.nibiru.world.gen.biome.BiomeInfectedMountains;

public class WorldTickEventHandler
{
    private int updateLCG = new Random().nextInt();
    public static WorldDataSurvivalPlanet survivalPlanetData = null;

    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event)
    {
        this.loadPlanetData();
    }

    @SubscribeEvent
    public void onClientConnectServer(FMLNetworkEvent.ServerConnectionFromClientEvent event)
    {
        this.loadPlanetData();
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
                        Biome biome = worldServer.getBiome(pos);

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

                            if (worldServer.isAreaLoaded(pos1, 1))
                            {
                                if (worldServer.canBlockFreezeNoWater(pos1))
                                {
                                    worldServer.setBlockState(pos1, MPBlocks.INFECTED_ICE.getDefaultState());
                                }
                            }
                            if (raining && worldServer.canSnowAt(pos, true))
                            {
                                worldServer.setBlockState(pos, biome == MPBiomes.COLD_GREEN_VEIN_MOUTAINS ? MPBlocks.PURIFIED_SNOW_LAYER.getDefaultState() : MPBlocks.INFECTED_SNOW_LAYER.getDefaultState());
                            }
                            if (raining && worldServer.getBiome(pos1).canRain())
                            {
                                worldServer.getBlockState(pos1).getBlock().fillWithRain(worldServer, pos1);
                            }
                        }
                        if (biome instanceof BiomeInfectedDesert || biome instanceof BiomeInfectedMountains || biome instanceof BiomeInfectedBadlands)
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
            return this.getSunBrightness(world) < 0.1F;
        }
    }

    private float getSunBrightness(WorldServer world)
    {
        float angle = world.getCelestialAngle(1.0F);
        float value = 1.0F - (MathHelper.cos(angle * ((float)Math.PI * 2.0F)) * 2.0F + 0.1F);
        value = MathHelper.clamp(value, 0.55F, 1.0F);
        value = 1.0F - value;
        return value * 0.9F;
    }

    private void loadPlanetData()
    {
        World world = WorldUtil.getWorldForDimensionServer(0);

        if (world == null)
        {
            return;
        }

        WorldTickEventHandler.survivalPlanetData = (WorldDataSurvivalPlanet) world.loadData(WorldDataSurvivalPlanet.class, WorldDataSurvivalPlanet.saveDataID);

        if (WorldTickEventHandler.survivalPlanetData == null)
        {
            WorldTickEventHandler.survivalPlanetData = new WorldDataSurvivalPlanet(WorldDataSurvivalPlanet.saveDataID);
            world.setData(WorldDataSurvivalPlanet.saveDataID, WorldTickEventHandler.survivalPlanetData);
        }
    }
}