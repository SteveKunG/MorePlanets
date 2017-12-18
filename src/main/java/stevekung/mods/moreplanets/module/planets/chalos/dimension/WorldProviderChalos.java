package stevekung.mods.moreplanets.module.planets.chalos.dimension;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.core.client.CloudRenderer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.init.MPDimensions;
import stevekung.mods.moreplanets.init.MPPlanets;
import stevekung.mods.moreplanets.module.planets.chalos.client.sky.SkyProviderChalos;
import stevekung.mods.moreplanets.module.planets.chalos.world.gen.BiomeProviderChalos;
import stevekung.mods.moreplanets.module.planets.chalos.world.gen.ChunkGeneratorChalos;
import stevekung.mods.moreplanets.util.dimension.WorldProviderMP;

public class WorldProviderChalos extends WorldProviderMP
{
    @Override
    public Vector3 getFogColor()
    {
        float f = 0.65F - this.getStarBrightness(1.0F);
        return new Vector3(255F / 255F * f, 193F / 255F * f, 6F / 255F * f);
    }

    @Override
    public Vector3 getSkyColor()
    {
        float f = 0.6F - this.getStarBrightness(1.0F);
        return new Vector3(255 / 255F * f, 223 / 255F * f, 128 / 255F * f);
    }

    @Override
    public boolean hasSunset()
    {
        return true;
    }

    @Override
    public long getDayLength()
    {
        return 48000L;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float getStarBrightness(float partialTicks)
    {
        float angle = this.world.getCelestialAngle(partialTicks);
        float value = 1.0F - (MathHelper.cos(angle * (float) Math.PI * 2.0F) * 2.0F + 0.25F);

        if (value < 0.0F)
        {
            value = 0.0F;
        }
        if (value > 1.0F)
        {
            value = 1.0F;
        }
        return value * value * 0.4F;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float getSunBrightness(float partialTicks)
    {
        float angle = this.world.getCelestialAngle(partialTicks);
        float value = 1.0F - (MathHelper.cos(angle * (float) Math.PI * 2.0F) * 2.0F + 0.2F);

        if (value < 0.0F)//day
        {
            value = 0.0F;
        }
        if (value > 1.025F)//night
        {
            value = 1.025F;
        }
        value = 1.0F - value;
        return value * 1.0F;
    }

    @Override
    public double getSolarEnergyMultiplier()
    {
        return 1.5D;
    }

    @Override
    public float getGravity()
    {
        return 0.027F;
    }

    @Override
    public boolean canSpaceshipTierPass(int tier)
    {
        return tier >= 5;
    }

    @Override
    public float getFallDamageModifier()
    {
        return 0.75F;
    }

    @Override
    public CelestialBody getCelestialBody()
    {
        return MPPlanets.CHALOS;
    }

    @Override
    public float getThermalLevelModifier()
    {
        float angle = this.world.getCelestialAngle(this.getDayLength());
        float value = 1.0F - (MathHelper.cos(angle * (float) Math.PI * 2.0F) * 2.0F + 0.2F);
        value = 1.0F - value;
        return value * 1.0F;
    }

    @Override
    public float getArrowGravity()
    {
        return 0.035F;
    }

    @Override
    public int getDarkEnergyMultiplier(World world, BlockPos pos)
    {
        return pos.getY();
    }

    @Override
    protected void renderSky()
    {
        this.setSkyRenderer(new SkyProviderChalos(this));
    }

    @Override
    protected void renderCloud()
    {
        this.setCloudRenderer(new CloudRenderer());
    }

    @Override
    protected void renderWeather() {}

    @Override
    public void init()
    {
        this.biomeProvider = new BiomeProviderChalos(this.world.getSeed());
    }

    @Override
    public IChunkGenerator createChunkGenerator()
    {
        return new ChunkGeneratorChalos(this.world, this.world.getSeed());
    }

    @Override
    public DimensionType getDimensionType()
    {
        return MPDimensions.CHALOS;
    }
}