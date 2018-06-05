package stevekung.mods.moreplanets.core.dimension;

import java.util.Arrays;
import java.util.List;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.core.util.WorldUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.client.renderer.sky.SkyProviderSpaceNether;
import stevekung.mods.moreplanets.core.event.WorldTickEventHandler;
import stevekung.mods.moreplanets.core.world.ChunkGeneratorSpaceNether;
import stevekung.mods.moreplanets.init.MPDimensions;
import stevekung.mods.moreplanets.init.MPPlanets;
import stevekung.mods.moreplanets.utils.dimension.WorldProviderMP;

public class WorldProviderSpaceNether extends WorldProviderMP
{
    @Override
    public Vector3 getFogColor()
    {
        return new Vector3(0.20000000298023224D, 0.029999999329447746D, 0.029999999329447746D);
    }

    @Override
    public Vector3 getSkyColor()
    {
        return new Vector3(0, 0, 0);
    }

    @Override
    public void init()
    {
        super.init();
        this.biomeProvider = new BiomeProviderSingle(Biomes.HELL);
        this.doesWaterVaporize = true;
        this.nether = true;
    }

    @Override
    protected void generateLightBrightnessTable()
    {
        for (int i = 0; i <= 15; ++i)
        {
            float f1 = 1.0F - i / 15.0F;
            this.lightBrightnessTable[i] = (1.0F - f1) / (f1 * 3.0F + 1.0F) * 0.9F + 0.1F;
        }
    }

    @Override
    public IChunkGenerator createChunkGenerator()
    {
        return new ChunkGeneratorSpaceNether(this.world, this.world.getSeed());
    }

    @Override
    public boolean canCoordinateBeSpawn(int x, int z)
    {
        return false;
    }

    @Override
    public boolean canRespawnHere()
    {
        return false;
    }

    @Override
    public int getRespawnDimension(EntityPlayerMP player)
    {
        return WorldUtil.getProviderForNameServer(WorldTickEventHandler.survivalPlanetData.survivalPlanetName).getDimension();
    }

    @Override
    public DimensionType getDimensionType()
    {
        return MPDimensions.SPACE_NETHER;
    }

    @Override
    public double getSolarEnergyMultiplier()
    {
        return 0;
    }

    @Override
    public int getDarkEnergyMultiplier(World world, BlockPos pos)
    {
        return 0;
    }

    @Override
    public float getGravity()
    {
        return 0.069F;
    }

    @Override
    public boolean canSpaceshipTierPass(int tier)
    {
        return false;
    }

    @Override
    public float getFallDamageModifier()
    {
        return 0.4F;
    }

    @Override
    public CelestialBody getCelestialBody()
    {
        return MPPlanets.SPACE_NETHER;
    }

    @Override
    public List<Block> getSurfaceBlocks()
    {
        return Arrays.asList(Blocks.NETHERRACK);
    }

    @Override
    protected void renderSky()
    {
        this.setSkyRenderer(new SkyProviderSpaceNether(this));
    }

    @Override
    public long getDayLength()
    {
        return 24000;
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
        if (value > 0.75F)
        {
            value = 0.75F;
        }
        return value * value * 0.5F + 0.3F;
    }

    @Override
    public float getThermalLevelModifier()
    {
        if (this.isDaytime())
        {
            return 3.5F;
        }
        else
        {
            return -2.0F;
        }
    }
}