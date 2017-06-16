/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.spacestation.jupiter;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.core.dimension.WorldProviderOrbit;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import net.minecraft.util.MathHelper;
import net.minecraft.world.chunk.IChunkProvider;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.world.IUltraVioletLevel;

public class WorldProviderJupiterOrbit extends WorldProviderOrbit implements IUltraVioletLevel
{
    @Override
    public CelestialBody getCelestialBody()
    {
        return MorePlanetsCore.jupiterSpaceStation;
    }

    @Override
    public Vector3 getFogColor()
    {
        return new Vector3(0, 0, 0);
    }

    @Override
    public Vector3 getSkyColor()
    {
        return new Vector3(0, 0, 0);
    }

    @Override
    public boolean canRainOrSnow()
    {
        return false;
    }

    @Override
    public boolean hasSunset()
    {
        return false;
    }

    @Override
    public long getDayLength()
    {
        return 0;
    }

    @Override
    public float calculateCelestialAngle(long par1, float par3)
    {
        return 0.1975F;
    }

    @Override
    public boolean shouldForceRespawn()
    {
        return !ConfigManagerCore.forceOverworldRespawn;
    }

    @Override
    public Class<? extends IChunkProvider> getChunkProviderClass()
    {
        return ChunkProviderJupiterOrbit.class;
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
        return f2 * 0.7F;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float getStarBrightness(float par1)
    {
        return 1.0F;
    }

    @Override
    public boolean isSkyColored()
    {
        return false;
    }

    @Override
    public double getHorizon()
    {
        return 44.0D;
    }

    @Override
    public int getAverageGroundLevel()
    {
        return 44;
    }

    @Override
    public boolean isSurfaceWorld()
    {
        return true;
    }

    @Override
    public boolean canCoordinateBeSpawn(int x, int z)
    {
        return true;
    }

    @Override
    public String getDimensionName()
    {
        return "Space Station " + this.spaceStationDimensionID;
    }

    @Override
    public boolean canSnowAt(int x, int y, int z, boolean checkLight)
    {
        return false;
    }

    @Override
    public float getGravity()
    {
        return 0.075F;
    }

    @Override
    public boolean hasBreathableAtmosphere()
    {
        return false;
    }

    @Override
    public double getMeteorFrequency()
    {
        return 0;
    }

    @Override
    public double getFuelUsageMultiplier()
    {
        return 0.75D;
    }

    @Override
    public String getPlanetToOrbit()
    {
        return "planet.jupiter";
    }

    @Override
    public int getYCoordToTeleportToPlanet()
    {
        return -1000;
    }

    @Override
    public String getSaveFolder()
    {
        return "DIM_SPACESTATION" + this.spaceStationDimensionID;
    }

    @Override
    public double getSolarEnergyMultiplier()
    {
        return ConfigManagerCore.spaceStationEnergyScalar - 1.0D;
    }

    @Override
    public double getYCoordinateToTeleport()
    {
        return 1200;
    }

    @Override
    public boolean canSpaceshipTierPass(int tier)
    {
        return tier > 3;
    }

    @Override
    public float getFallDamageModifier()
    {
        return 0.4F;
    }

    @Override
    public float getSoundVolReductionAmount()
    {
        return 50.0F;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void setSpinDeltaPerTick(float angle)
    {
        SkyProviderJupiterOrbit skyProvider = (SkyProviderJupiterOrbit)this.getSkyRenderer();

        if (skyProvider != null)
        {
            skyProvider.spinDeltaPerTick = angle;
        }
    }

    @Override
    public float getThermalLevelModifier()
    {
        return 0;
    }

    @Override
    public float getWindLevel()
    {
        return 0.1F;
    }

    @Override
    public double getUltraVioletEnergyMultiplie()
    {
        return 2.0D;
    }
}