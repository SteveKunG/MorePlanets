package stevekung.mods.moreplanets.planets.fronos.dimension;

import java.util.Arrays;
import java.util.List;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.init.MPDimensions;
import stevekung.mods.moreplanets.init.MPPlanets;
import stevekung.mods.moreplanets.planets.fronos.world.gen.BiomeProviderFronos;
import stevekung.mods.moreplanets.planets.fronos.world.gen.ChunkGeneratorFronos;
import stevekung.mods.moreplanets.utils.dimension.WorldProviderMP;

public class WorldProviderFronos extends WorldProviderMP
{
    @Override
    public Vector3 getFogColor()
    {
        float f = 1.0F - this.getStarBrightness(1.0F);
        return new Vector3(149 / 255F * f, 210 / 255F * f, 245 / 255F * f);
    }

    @Override
    public Vector3 getSkyColor()
    {
        float f = 1.0F - this.getStarBrightness(1.0F);
        return new Vector3(83 / 255F * f, 186 / 255F * f, 245 / 255F * f);
    }

    @Override
    public boolean hasSunset()
    {
        return true;
    }

    @Override
    public boolean canDoLightning(Chunk chunk)
    {
        return true;
    }

    @Override
    public boolean canDoRainSnowIce(Chunk chunk)
    {
        return false;
    }

    @Override
    public boolean shouldDisablePrecipitation()
    {
        super.updateWeatherOverride();
        return false;
    }

    @Override
    public long getDayLength()
    {
        return 24000L;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float getStarBrightness(float partialTicks)
    {
        float angle = this.world.getCelestialAngle(partialTicks);
        float value = 1.0F - (MathHelper.cos(angle * ((float)Math.PI * 2.0F)) * 2.0F + 0.25F);
        value = MathHelper.clamp(value, 0.0F, 0.85F);
        return value * value * 0.35F;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float getSunBrightness(float partialTicks)
    {
        float angle = this.world.getCelestialAngle(partialTicks);
        float value = 1.0F - (MathHelper.cos(angle * ((float)Math.PI * 2.0F)) * 2.0F + 0.2F);
        value = MathHelper.clamp(value, 0.0F, 1.0F);
        value = 1.0F - value;
        value = value * (1.0F - this.world.getRainStrength(partialTicks) * 6.0F / 16.0F);
        value = value * (1.0F - this.world.getThunderStrength(partialTicks) * 8.0F / 16.0F);
        return value * 1.0F;
    }

    @Override
    public double getSolarEnergyMultiplier()
    {
        return 1.0D;
    }

    @Override
    public float getGravity()
    {
        return 0.0F;
    }

    @Override
    public double getMeteorFrequency()
    {
        return 0.0D;
    }

    @Override
    public boolean canSpaceshipTierPass(int tier)
    {
        return tier >= ConfigManagerMP.moreplanets_planet_settings.planetFronosTier;
    }

    @Override
    public float getFallDamageModifier()
    {
        return 1.0F;
    }

    @Override
    public CelestialBody getCelestialBody()
    {
        return MPPlanets.FRONOS;
    }

    @Override
    public float getArrowGravity()
    {
        return 1.0F;
    }

    @Override
    public int getDarkEnergyMultiplier(World world, BlockPos pos)
    {
        return 100;
    }

    @Override
    public void setup(EntityPlayerMP player) {}

    //    @Override
    //    protected void renderSky()
    //    {
    //        this.setSkyRenderer(new SkyProviderNibiru(this.getSolarSize()));
    //    }

    //    @Override
    //    protected void renderCloud()
    //    {
    //        this.setCloudRenderer(new CloudRendererNibiru());
    //    }

    //    @Override
    //    protected void renderWeather()
    //    {
    //        this.setWeatherRenderer(new WeatherRendererNibiru());
    //    }

    @Override
    public void init()
    {
        super.init();
        this.biomeProvider = new BiomeProviderFronos(this.world.getSeed());
    }

    @Override
    public IChunkGenerator createChunkGenerator()
    {
        return new ChunkGeneratorFronos(this.world, this.world.getSeed());
    }

    @Override
    public DimensionType getDimensionType()
    {
        return MPDimensions.FRONOS;
    }

    @Override
    public List<Block> getSurfaceBlocks()
    {
        return Arrays.asList(MPBlocks.FRONOS_GRASS_BLOCK, MPBlocks.FRONOS_DIRT, MPBlocks.FRONOS_STONE);
    }
}