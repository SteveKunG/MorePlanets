/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.pluto.dimension;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import net.minecraft.util.MathHelper;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.dimension.WorldProviderMP;
import stevekung.mods.moreplanets.planets.pluto.worldgen.ChunkProviderPluto;
import stevekung.mods.moreplanets.planets.pluto.worldgen.WorldChunkManagerPluto;

public class WorldProviderPluto extends WorldProviderMP
{
    @Override
    public Vector3 getFogColor()
    {
        float f = 1.0F - this.getStarBrightness(1.0F);
        return new Vector3(20 / 255F * f, 27 / 255F * f, 33 / 255F * f);
    }

    @Override
    public Vector3 getSkyColor()
    {
        return new Vector3(0, 0, 0);
    }

    @Override
    public long getDayLength()
    {
        return 144000L;
    }

    @Override
    public Class<? extends IChunkProvider> getChunkProviderClass()
    {
        return ChunkProviderPluto.class;
    }

    @Override
    public Class<? extends WorldChunkManager> getWorldChunkManagerClass()
    {
        return WorldChunkManagerPluto.class;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float getStarBrightness(float bright)
    {
        float var2 = this.worldObj.getCelestialAngle(bright);
        float var3 = 1.0F - (MathHelper.cos(var2 * (float) Math.PI * 2.0F) * 2.0F + 0.25F);

        if (var3 < 0.0F)
        {
            var3 = 0.25F;
        }
        if (var3 > 1.0F)
        {
            var3 = 0.75F;
        }
        return var3 * var3 * 0.5F + 0.2F;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float getSunBrightness(float bright)
    {
        float f1 = this.worldObj.getCelestialAngle(1.0F);
        float f2 = 1.0F - (MathHelper.cos(f1 * (float) Math.PI * 2.0F) * 2.0F + 0.2F);

        if (f2 < 0.0F)
        {
            f2 = 0.6F;
        }
        if (f2 < 0.4F)
        {
            f2 = 0.8F;
        }
        if (f2 > 1.0F)
        {
            f2 = 0.95F;
        }
        f2 = 0.95F - f2;
        return f2 * 1.0F;
    }

    @Override
    public double getSolarEnergyMultiplier()
    {
        return 1.0D;
    }

    @Override
    public float getGravity()
    {
        return 0.0715F;
    }

    @Override
    public double getMeteorFrequency()
    {
        return 2.5D;
    }

    @Override
    public boolean canSpaceshipTierPass(int tier)
    {
        return tier >= 5;
    }

    @Override
    public float getFallDamageModifier()
    {
        return 0.16F;
    }

    @Override
    public float getSoundVolReductionAmount()
    {
        return 20.0F;
    }

    @Override
    public CelestialBody getCelestialBody()
    {
        return MorePlanetsCore.pluto;
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
            return 0.0F;
        }
        else
        {
            return -1.5F;
        }
    }

    @Override
    public float getWindLevel()
    {
        return 0.0F;
    }

    @Override
    public double getUltraVioletEnergyMultiplie()
    {
        return 1.25D;
    }
}