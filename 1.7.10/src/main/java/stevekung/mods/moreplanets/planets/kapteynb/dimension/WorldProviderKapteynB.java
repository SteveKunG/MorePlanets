/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.kapteynb.dimension;

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
import stevekung.mods.moreplanets.planets.kapteynb.worldgen.ChunkProviderKapteynB;
import stevekung.mods.moreplanets.planets.kapteynb.worldgen.WorldChunkManagerKapteynB;

public class WorldProviderKapteynB extends WorldProviderMP implements IMeteorType
{
    @Override
    public Vector3 getFogColor()
    {
        float f = 1.1F - this.getStarBrightness(1.0F);
        return new Vector3(135F / 255F * f, 145F / 255F * f, 170F / 255F * f);
    }

    @Override
    public Vector3 getSkyColor()
    {
        float f = 1.1F - this.getStarBrightness(1.0F);
        return new Vector3(172 / 255F * f, 193 / 255F * f, 210 / 255F * f);
    }

    @Override
    public long getDayLength()
    {
        return 246000L;
    }

    @Override
    public Class<? extends IChunkProvider> getChunkProviderClass()
    {
        return ChunkProviderKapteynB.class;
    }

    @Override
    public Class<? extends WorldChunkManager> getWorldChunkManagerClass()
    {
        return WorldChunkManagerKapteynB.class;
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
        return f2 * f2 * 0.7F;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float getSunBrightness(float par1)
    {
        float f1 = this.worldObj.getCelestialAngle(1.0F);
        float f2 = -0.55F - (MathHelper.cos(f1 * (float) Math.PI * 2.0F) * 2.0F + 0.2F);

        if (f2 < 0.0F)
        {
            f2 = 0.0F;
        }
        if (f2 > 1.0F)
        {
            f2 = 1.05F;
        }
        f2 = 1.0F - f2;
        return f2 * 1.0F;
    }

    @Override
    public double getSolarEnergyMultiplier()
    {
        return 4.0D;
    }

    @Override
    public float getGravity()
    {
        return 0.032F;
    }

    @Override
    public double getMeteorFrequency()
    {
        return 5.0D;
    }

    @Override
    public boolean canSpaceshipTierPass(int tier)
    {
        return tier >= 7;
    }

    @Override
    public float getFallDamageModifier()
    {
        return 0.5F;
    }

    @Override
    public float getSoundVolReductionAmount()
    {
        return 2.5F;
    }

    @Override
    public CelestialBody getCelestialBody()
    {
        return MorePlanetsCore.kapteynB;
    }

    @Override
    public boolean hasBreathableAtmosphere()
    {
        return !this.isDaytime();
    }

    @Override
    public float getThermalLevelModifier()
    {
        if (this.isDaytime())
        {
            return 0.25F;
        }
        else
        {
            return -1.25F;
        }
    }

    @Override
    public float getWindLevel()
    {
        return 0.5F;
    }

    @Override
    public double getUltraVioletEnergyMultiplie()
    {
        return 4.0D;
    }

    @Override
    public int getMeteorEventType()
    {
        return 2;
    }

    @Override
    public double getMeteorSpawnFrequency()
    {
        return 3.5D;
    }
}