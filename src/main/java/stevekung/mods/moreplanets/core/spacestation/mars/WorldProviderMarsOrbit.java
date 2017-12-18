/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.spacestation.mars;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.core.dimension.WorldProviderOrbit;
import net.minecraft.world.chunk.IChunkProvider;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.world.IUltraVioletLevel;

public class WorldProviderMarsOrbit extends WorldProviderOrbit implements IUltraVioletLevel
{
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
    public CelestialBody getCelestialBody()
    {
        return MorePlanetsCore.marsSpaceStation;
    }

    @Override
    public Class<? extends IChunkProvider> getChunkProviderClass()
    {
        return ChunkProviderMarsOrbit.class;
    }

    @Override
    public String getDimensionName()
    {
        return "Space Station " + this.spaceStationDimensionID;
    }

    @Override
    public String getPlanetToOrbit()
    {
        return "planet.mars";
    }

    @Override
    public int getYCoordToTeleportToPlanet()
    {
        return 30;
    }

    @Override
    public String getSaveFolder()
    {
        return "DIM_SPACESTATION" + this.spaceStationDimensionID;
    }

    @Override
    public double getYCoordinateToTeleport()
    {
        return 1200;
    }

    @Override
    public boolean canSpaceshipTierPass(int tier)
    {
        return tier > 2;
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
        SkyProviderMarsOrbit skyProvider = (SkyProviderMarsOrbit)this.getSkyRenderer();

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
        return 6.0D;
    }
}