package stevekung.mods.moreplanets.module.planets.diona.dimension;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
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
import stevekung.mods.moreplanets.module.planets.diona.client.sky.SkyProviderDiona;
import stevekung.mods.moreplanets.module.planets.diona.world.gen.BiomeProviderDiona;
import stevekung.mods.moreplanets.module.planets.diona.world.gen.ChunkProviderDiona;
import stevekung.mods.moreplanets.util.dimension.WorldProviderMP;

public class WorldProviderDiona extends WorldProviderMP
{
    @Override
    public long getDayLength()
    {
        return 96000L;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float getStarBrightness(float partialTicks)
    {
        float angle = this.worldObj.getCelestialAngle(partialTicks);
        float value = 1.0F - (MathHelper.cos(angle * (float) Math.PI * 2.0F) * 2.0F + 0.25F);

        if (value < 0.0F)
        {
            value = 0.0F;
        }
        if (value > 0.75F)
        {
            value = 0.75F;
        }
        return value * value * 0.5F + 0.3F;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float getSunBrightness(float partialTicks)
    {
        float angle = this.worldObj.getCelestialAngle(partialTicks);
        float value = 1.0F - (MathHelper.cos(angle * (float) Math.PI * 2.0F) * 2.0F + 0.1F);

        if (value < 0.55F)//day
        {
            value = 0.55F;
        }
        if (value > 1.075F)//night
        {
            value = 1.075F;
        }
        value = 1.0F - value;
        return value * 0.8F;
    }

    @Override
    public double getSolarEnergyMultiplier()
    {
        return 2.5D;
    }

    @Override
    public float getGravity()
    {
        return 0.065F;
    }

    @Override
    public boolean canSpaceshipTierPass(int tier)
    {
        return tier >= 4;
    }

    @Override
    public float getFallDamageModifier()
    {
        return 0.4F;
    }

    @Override
    public CelestialBody getCelestialBody()
    {
        return MPPlanets.DIONA;
    }

    @Override
    public float getThermalLevelModifier()
    {
        if (this.isDaytime())
        {
            return -0.5F;
        }
        else
        {
            return -1.75F;
        }
    }

    @Override
    public int getDarkEnergyMultiplier(World world, BlockPos pos)
    {
        if (this.isDaytime())
        {
            return 120 + pos.getY() * 2;
        }
        else
        {
            return 150 + pos.getY() * 4;
        }
    }

    @Override
    protected void renderSky()
    {
        this.setSkyRenderer(new SkyProviderDiona(this));
    }

    @Override
    protected void renderCloud()
    {
        this.setCloudRenderer(new CloudRenderer());
    }

    @Override
    protected void renderWeather() {}

    @Override
    public void createBiomeProvider()
    {
        this.biomeProvider = new BiomeProviderDiona();
    }

    @Override
    public IChunkGenerator createChunkGenerator()
    {
        return new ChunkProviderDiona(this.worldObj, this.worldObj.getSeed());
    }

    @Override
    public DimensionType getDimensionType()
    {
        return MPDimensions.DIONA;
    }
}