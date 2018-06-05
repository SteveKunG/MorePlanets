package stevekung.mods.moreplanets.moons.koentus.dimension;

import java.util.Arrays;
import java.util.List;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.api.world.IExitHeight;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.init.MPDimensions;
import stevekung.mods.moreplanets.init.MPPlanets;
import stevekung.mods.moreplanets.planets.diona.world.gen.ChunkGeneratorDiona;
import stevekung.mods.moreplanets.utils.dimension.WorldProviderMP;

public class WorldProviderKoentus extends WorldProviderMP implements IExitHeight
{
    @Override
    public Vector3 getFogColor()
    {
        float f = 1.2F - this.getStarBrightness(1.0F);
        return new Vector3(23F / 255F * f, 49F / 255F * f, 108F / 255F * f);
    }

    @Override
    public Vector3 getSkyColor()
    {
        float f = 1.3F - this.getStarBrightness(1.0F);
        return new Vector3(35 / 255F * f, 74 / 255F * f, 165 / 255F * f);
    }

    @Override
    public long getDayLength()
    {
        return 12000L;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float getStarBrightness(float partialTicks)
    {
        float angle = this.world.getCelestialAngle(partialTicks);
        float value = 0.8F - (MathHelper.cos(angle * (float) Math.PI * 2.0F) * 2.0F + 0.25F);

        if (value < 0.0F)
        {
            value = 0.0F;
        }
        if (value > 1.0F)
        {
            value = 1.0F;
        }
        return value * value * 0.75F;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float getSunBrightness(float partialTicks)
    {
        float angle = this.world.getCelestialAngle(1.0F);
        float value = 0.45F - (MathHelper.cos(angle * (float) Math.PI * 2.0F) * 2.0F + 0.2F);

        if (value < 0.0F)// day
        {
            value = 0.0F;
        }
        if (value > 1.0F)// night
        {
            value = 1.0F;
        }
        value = 1.0F - value;
        return value * 1.0F;
    }

    @Override
    public double getSolarEnergyMultiplier()
    {
        return 1.6D;
    }

    @Override
    public float getGravity()
    {
        return 0.07F;
    }

    @Override
    public double getYCoordinateToTeleport()
    {
        return 512;
    }

    @Override
    public boolean canSpaceshipTierPass(int tier)
    {
        return tier >= ConfigManagerMP.moreplanets_moon_settings.moonKoentusTier;
    }

    @Override
    public float getFallDamageModifier()
    {
        return 0.24F;
    }

    @Override
    public CelestialBody getCelestialBody()
    {
        return MPPlanets.KOENTUS;
    }

    @Override
    public float getThermalLevelModifier()
    {
        if (this.isDaytime())
        {
            return 0.5F;
        }
        return -1.0F;
    }

    @Override
    public int getDarkEnergyMultiplier(World world, BlockPos pos)
    {
        return 100;
    }

    @Override
    public List<Block> getSurfaceBlocks()
    {
        return Arrays.asList(MPBlocks.KOENTUS_REGOLITH);
    }

    @Override
    protected void renderSky()//TODO
    {

    }

    @Override
    public IChunkGenerator createChunkGenerator()
    {
        return new ChunkGeneratorDiona(this.world, this.world.getSeed());//TODO
    }

    @Override
    public DimensionType getDimensionType()
    {
        return MPDimensions.KOENTUS;
    }
}