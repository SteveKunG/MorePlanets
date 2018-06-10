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
import stevekung.mods.moreplanets.moons.koentus.client.sky.SkyProviderKoentus;
import stevekung.mods.moreplanets.moons.koentus.world.gen.BiomeProviderKoentus;
import stevekung.mods.moreplanets.moons.koentus.world.gen.ChunkGeneratorKoentus;
import stevekung.mods.moreplanets.utils.dimension.WorldProviderMP;

public class WorldProviderKoentus extends WorldProviderMP implements IExitHeight
{
    @Override
    public Vector3 getFogColor()
    {
        float f = 1.2F - this.getStarBrightness(1.0F);
        return new Vector3(23 / 255F * f, 49 / 255F * f, 91 / 255F * f);
    }

    @Override
    public Vector3 getSkyColor()
    {
        float f = 1.3F - this.getStarBrightness(1.0F);
        return new Vector3(35 / 255F * f, 74 / 255F * f, 120 / 255F * f);
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
        float value = 1.0F - (MathHelper.cos(angle * ((float)Math.PI * 2.0F)) * 2.0F + 0.25F);
        value = MathHelper.clamp(value, 0.0F, 1.0F);
        return value * value * 0.75F;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float getSunBrightness(float partialTicks)
    {
        float angle = this.world.getCelestialAngle(partialTicks);
        float value = 1.0F - (MathHelper.cos(angle * ((float)Math.PI * 2.0F)) * 2.0F + 0.1F);
        value = MathHelper.clamp(value, 0.55F, 1.0F);
        value = 1.0F - value;
        return value * 0.8F;
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
            return 1.0F;
        }
        return -2.5F;
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
    protected void renderSky()
    {
        this.setSkyRenderer(new SkyProviderKoentus(this.getSolarSize()));
    }

    @Override
    protected void init()
    {
        super.init();
        this.biomeProvider = new BiomeProviderKoentus();
    }

    @Override
    public IChunkGenerator createChunkGenerator()
    {
        return new ChunkGeneratorKoentus(this.world, this.world.getSeed());
    }

    @Override
    public DimensionType getDimensionType()
    {
        return MPDimensions.KOENTUS;
    }
}