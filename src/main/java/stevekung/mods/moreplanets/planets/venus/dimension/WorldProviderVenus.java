/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.venus.dimension;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import net.minecraft.util.MathHelper;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.dimension.WorldProviderMP;
import stevekung.mods.moreplanets.core.world.ILightningStorm;
import stevekung.mods.moreplanets.planets.venus.worldgen.ChunkProviderVenus;
import stevekung.mods.moreplanets.planets.venus.worldgen.WorldChunkManagerVenus;

public class WorldProviderVenus extends WorldProviderMP implements ILightningStorm
{
    private double solarMultiplier = -1D;

    @Override
    public Vector3 getFogColor()
    {
        return new Vector3(177 / 855.0F * 0.45F, 83 / 855.0F * 0.45F, 13 / 855.0F * 0.45F);
    }

    @Override
    public Vector3 getSkyColor()
    {
        return new Vector3(177 / 455.0F * 0.45F, 83 / 455.0F * 0.45F, 13 / 455.0F * 0.45F);
    }

    @Override
    public long getDayLength()
    {
        return 5832000L;
    }

    @Override
    public Class<? extends IChunkProvider> getChunkProviderClass()
    {
        return ChunkProviderVenus.class;
    }

    @Override
    public Class<? extends WorldChunkManager> getWorldChunkManagerClass()
    {
        return WorldChunkManagerVenus.class;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float getStarBrightness(float par1)
    {
        float f1 = this.worldObj.getCelestialAngle(par1);
        float f2 = 1.0F - (MathHelper.cos(f1 * (float) Math.PI * 2.0F) * 2.0F + 0.25F);

        if (f2 < 0.0F)
        {
            f2 = 0.0F;
        }
        if (f2 > 1.0F)
        {
            f2 = 0.7F;
        }
        return f2 * f2 * 0.75F;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float getSunBrightness(float par1)
    {
        float f1 = this.worldObj.getCelestialAngle(1.0F);
        float f2 = 1.25F - (MathHelper.cos(f1 * (float) Math.PI * 2.0F) * 2.0F + 0.2F);

        if (f2 < 0.0F)
        {
            f2 = 0.0F;
        }
        if (f2 > 1.0F)
        {
            f2 = 1.0F;
        }
        f2 = 1.2F - f2;
        return f2 * 1.0F;
    }

    @Override
    public double getSolarEnergyMultiplier()
    {
        if (this.solarMultiplier < 0D)
        {
            double s = this.getSolarSize();
            this.solarMultiplier = s * s * s;
        }
        return this.solarMultiplier;
    }

    @Override
    public float getGravity()
    {
        return 0.058F;
    }

    @Override
    public double getMeteorFrequency()
    {
        return 100.0D;
    }

    @Override
    public boolean canSpaceshipTierPass(int tier)
    {
        return tier >= 3;
    }

    @Override
    public float getFallDamageModifier()
    {
        return 0.38F;
    }

    @Override
    public float getSoundVolReductionAmount()
    {
        return 5.0F;
    }

    @Override
    public CelestialBody getCelestialBody()
    {
        return MorePlanetsCore.venus;
    }

    @Override
    public boolean hasBreathableAtmosphere()
    {
        return false;
    }

    @Override
    public float getThermalLevelModifier()
    {
        return this.isDaytime() ? 2.0F : -1.0F;
    }

    @Override
    public float getWindLevel()
    {
        return 0.3F;
    }

    @Override
    public double getUltraVioletEnergyMultiplie()
    {
        return 30.0D;
    }

    @Override
    public double getLightningStormFrequency()
    {
        return 1.5D;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float getCloudHeight()
    {
        return this.terrainType.getCloudHeight();
    }
}