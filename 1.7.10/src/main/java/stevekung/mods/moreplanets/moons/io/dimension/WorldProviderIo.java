/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.io.dimension;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import net.minecraft.util.MathHelper;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.dimension.WorldProviderMP;
import stevekung.mods.moreplanets.moons.io.worldgen.ChunkProviderIo;
import stevekung.mods.moreplanets.moons.io.worldgen.WorldChunkManagerIo;

public class WorldProviderIo extends WorldProviderMP
{
    @Override
    public Vector3 getFogColor()
    {
        float f = 1.0F - this.getStarBrightness(1.0F);
        return new Vector3(182F / 255F * f, 108F / 255F * f, 10F / 255F * f);
    }

    @Override
    public Vector3 getSkyColor()
    {
        float f = 1.0F - this.getStarBrightness(1.0F);
        return new Vector3(242 / 255F * f, 145 / 255F * f, 13 / 255F * f);
    }

    @Override
    public long getDayLength()
    {
        return (long) 2122.9653432;
    }

    @Override
    public Class<? extends IChunkProvider> getChunkProviderClass()
    {
        return ChunkProviderIo.class;
    }

    @Override
    public Class<? extends WorldChunkManager> getWorldChunkManagerClass()
    {
        return WorldChunkManagerIo.class;
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
            f2 = 1.0F;
        }
        return f2 * f2 * 0.75F;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float getSunBrightness(float par1)
    {
        float f1 = this.worldObj.getCelestialAngle(1.0F);
        float f2 = 0.45F - (MathHelper.cos(f1 * (float) Math.PI * 2.0F) * 2.0F + 0.2F);

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
    public float getGravity()
    {
        return 0.032F;
    }

    @Override
    public double getMeteorFrequency()
    {
        return 10.0D;
    }

    @Override
    public boolean canSpaceshipTierPass(int tier)
    {
        return tier >= 4;
    }

    @Override
    public float getFallDamageModifier()
    {
        return 0.26F;
    }

    @Override
    public float getSoundVolReductionAmount()
    {
        return 10.0F;
    }

    @Override
    public CelestialBody getCelestialBody()
    {
        return MorePlanetsCore.io;
    }

    @Override
    public float getThermalLevelModifier()
    {
        if (this.isDaytime())
        {
            return 3.25F;
        }
        return -2.0F;
    }

    @Override
    public float getWindLevel()
    {
        return 0.4F;
    }

    @Override
    public double getUltraVioletEnergyMultiplie()
    {
        return 2.5D;
    }

    @Override
    public double getSolarEnergyMultiplier()
    {
        return 1.5D;
    }
}