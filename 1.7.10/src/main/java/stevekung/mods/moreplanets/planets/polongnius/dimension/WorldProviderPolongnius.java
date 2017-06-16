/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.polongnius.dimension;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import net.minecraft.util.MathHelper;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.dimension.WorldProviderMP;
import stevekung.mods.moreplanets.core.world.IMeteorType;
import stevekung.mods.moreplanets.planets.polongnius.worldgen.ChunkProviderPolongnius;
import stevekung.mods.moreplanets.planets.polongnius.worldgen.WorldChunkManagerPolongnius;

public class WorldProviderPolongnius extends WorldProviderMP implements IMeteorType
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
    public long getDayLength()
    {
        return 44000L;
    }

    @Override
    public Class<? extends IChunkProvider> getChunkProviderClass()
    {
        return ChunkProviderPolongnius.class;
    }

    @Override
    public Class<? extends WorldChunkManager> getWorldChunkManagerClass()
    {
        return WorldChunkManagerPolongnius.class;
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
            f2 = 1.0F;
        }
        return f2 * f2 * 0.4F;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float getSunBrightness(float bright)
    {
        float f1 = this.worldObj.getCelestialAngle(1.0F);
        float f2 = 0.675F - (MathHelper.cos(f1 * (float) Math.PI * 2.0F) * 2.0F + 0.2F);

        if (f2 < 0.0F)
        {
            f2 = 0.0F;
        }
        if (f2 > 1.0F)
        {
            f2 = 1.0F;
        }
        f2 = 0.95F - f2;
        return f2 * 1.0F;
    }

    @Override
    public double getSolarEnergyMultiplier()
    {
        return 1.2D;
    }

    @Override
    public float getGravity()
    {
        return 0.027F;
    }

    @Override
    public double getMeteorFrequency()
    {
        return 10.0D;
    }

    @Override
    public boolean canSpaceshipTierPass(int tier)
    {
        return tier >= 5;
    }

    @Override
    public float getFallDamageModifier()
    {
        return 0.72F;
    }

    @Override
    public float getSoundVolReductionAmount()
    {
        return 10.0F;
    }

    @Override
    public CelestialBody getCelestialBody()
    {
        return MorePlanetsCore.polongnius;
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
            return 1.75F;
        }
        else
        {
            return -1.5F;
        }
    }

    @Override
    public float getWindLevel()
    {
        return 0.9F;
    }

    @Override
    public double getUltraVioletEnergyMultiplie()
    {
        return 8.5D;
    }

    @Override
    public int getMeteorEventType()
    {
        return 0;
    }

    @Override
    public double getMeteorSpawnFrequency()
    {
        return 1.25D;
    }
}