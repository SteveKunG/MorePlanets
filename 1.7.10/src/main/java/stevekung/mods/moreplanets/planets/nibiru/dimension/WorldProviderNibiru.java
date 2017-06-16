/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.nibiru.dimension;

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
import stevekung.mods.moreplanets.planets.nibiru.worldgen.ChunkProviderNibiru;
import stevekung.mods.moreplanets.planets.nibiru.worldgen.WorldChunkManagerNibiru;

public class WorldProviderNibiru extends WorldProviderMP implements ILightningStorm
{
    @Override
    public Vector3 getFogColor()
    {
        float f = 1.0F - this.getStarBrightness(1.0F);
        return new Vector3(163F / 255F * f, 91F / 255F * f, 44F / 255F * f);
    }

    @Override
    public Vector3 getSkyColor()
    {
        float f = 1.15F - this.getStarBrightness(1.0F);
        return new Vector3(195 / 255F * f, 110 / 255F * f, 50 / 255F * f);
    }

    @Override
    public long getDayLength()
    {
        return 120000L;
    }

    @Override
    public Class<? extends IChunkProvider> getChunkProviderClass()
    {
        return ChunkProviderNibiru.class;
    }

    @Override
    public Class<? extends WorldChunkManager> getWorldChunkManagerClass()
    {
        return WorldChunkManagerNibiru.class;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float getStarBrightness(float bright)
    {
        float f1 = this.worldObj.getCelestialAngle(bright);
        float f2 = 1.0F - (MathHelper.cos(f1 * (float) Math.PI * 2.0F) * 2.0F + 0.25F);

        if (f2 < 0.0F)
        {
            f2 = 0.0F;
        }
        if (f2 > 1.0F)
        {
            f2 = 0.95F;
        }
        return f2 * f2 * 0.6F;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float getSunBrightness(float bright)
    {
        float f1 = this.worldObj.getCelestialAngle(1.0F);
        float f2 = -0.7F - (MathHelper.cos(f1 * (float) Math.PI * 2.0F) * 2.0F + 0.2F);

        if (f2 < 0.0F)
        {
            f2 = 0.0F;
        }
        if (f2 > 1.0F)
        {
            f2 = 1.0F;
        }
        f2 = 1.0F - f2;
        return f2 * 1.0F;
    }

    @Override
    public double getSolarEnergyMultiplier()
    {
        return 0.8D;
    }

    @Override
    public float getGravity()
    {
        return 0.042F;
    }

    @Override
    public double getMeteorFrequency()
    {
        return 4.0D;
    }

    @Override
    public boolean canSpaceshipTierPass(int tier)
    {
        return tier >= 6;
    }

    @Override
    public float getFallDamageModifier()
    {
        return 0.58F;
    }

    @Override
    public float getSoundVolReductionAmount()
    {
        return 5.0F;
    }

    @Override
    public CelestialBody getCelestialBody()
    {
        return MorePlanetsCore.nibiru;
    }

    @Override
    public boolean hasBreathableAtmosphere()
    {
        return false;
    }

    @Override
    public float getThermalLevelModifier()
    {
        if (this.isDaytime())
        {
            return 2.5F;
        }
        else
        {
            return 1.0F;
        }
    }

    @Override
    public float getWindLevel()
    {
        return 0.4F;
    }

    @Override
    public double getUltraVioletEnergyMultiplie()
    {
        return 12.0D;
    }

    @Override
    public double getLightningStormFrequency()
    {
        return 4.0D;
    }
}