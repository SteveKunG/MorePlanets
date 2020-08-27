package stevekung.mods.moreplanets.core.event;

import micdoodle8.mods.galacticraft.core.util.WorldUtil;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
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
import stevekung.mods.stevekunglib.utils.event.WeatherTickEvent;

public class WorldTickEventHandler
{
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
    public void onWeatherTick(WeatherTickEvent event)
    {
        World world = event.getWorld();
        BlockPos pos = event.getStrikePos();

        if (DimensionManager.getWorld(ConfigManagerMP.moreplanets_dimension.idDimensionDiona) != null)
        {
            if (world.provider instanceof WorldProviderDiona)
            {
                if (this.canBeamStrike(world, pos) && world.rand.nextInt(75000) == 0)
                {
                    if (world.isBlockLoaded(pos))
                    {
                        EntityAlienBeam beam = new EntityAlienBeam(world, pos.getX(), pos.getY(), pos.getZ());
                        beam.spawnWeather();
                    }
                }
            }
        }
        if (DimensionManager.getWorld(ConfigManagerMP.moreplanets_dimension.idDimensionNibiru) != null)
        {
            if (world.provider instanceof WorldProviderNibiru)
            {
                boolean raining = world.isRaining();
                boolean thunder = world.isThundering();
                Biome biome = world.getBiome(pos);

                if (world.provider.canDoLightning(event.getChunk()) && raining && thunder && world.rand.nextInt(1000) == 0)
                {
                    if (world.isRainingAt(pos))
                    {
                        EntityNibiruLightningBolt boltFire = new EntityNibiruLightningBolt(world, pos.getX(), pos.getY(), pos.getZ(), true);
                        boltFire.spawnWeather();
                    }
                }
                if (world.rand.nextInt(16) == 0)
                {
                    BlockPos pos1 = pos.down();

                    if (world.isAreaLoaded(pos1, 1))
                    {
                        if (world.canBlockFreezeNoWater(pos1))
                        {
                            world.setBlockState(pos1, MPBlocks.INFECTED_ICE.getDefaultState());
                        }
                    }
                    if (raining)
                    {
                        if (world.canSnowAt(pos, true))
                        {
                            world.setBlockState(pos, biome == MPBiomes.COLD_GREEN_VEIN_MOUTAINS ? MPBlocks.PURIFIED_SNOW_LAYER.getDefaultState() : MPBlocks.INFECTED_SNOW_LAYER.getDefaultState());
                        }
                        if (world.getBiome(pos1).canRain())
                        {
                            world.getBlockState(pos1).getBlock().fillWithRain(world, pos1);
                        }
                    }
                }
                if (biome instanceof BiomeInfectedDesert || biome instanceof BiomeInfectedMountains || biome instanceof BiomeInfectedBadlands)
                {
                    if (world.rand.nextInt(250000) == 0)
                    {
                        EntityNibiruLightningBolt bolt = new EntityNibiruLightningBolt(world, pos.getX(), pos.getY(), pos.getZ(), false);
                        bolt.spawnWeather();
                    }
                }
            }
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
            return this.getSunBrightness(world) < 0.1F;
        }
    }

    private float getSunBrightness(World world)
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