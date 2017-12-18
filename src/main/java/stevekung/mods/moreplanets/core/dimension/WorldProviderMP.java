/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.dimension;

import micdoodle8.mods.galacticraft.api.prefab.world.gen.WorldProviderSpace;
import micdoodle8.mods.galacticraft.api.world.ISolarLevel;
import micdoodle8.mods.galacticraft.core.event.EventHandlerGC;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import net.minecraft.entity.player.EntityPlayerMP;
import stevekung.mods.moreplanets.core.world.IUltraVioletLevel;

public abstract class WorldProviderMP extends WorldProviderSpace implements ISolarLevel, IUltraVioletLevel
{
    @Override
    public boolean isSurfaceWorld()
    {
        return this.worldObj == null ? false : this.worldObj.isRemote;
    }

    @Override
    public boolean canRespawnHere()
    {
        if (EventHandlerGC.bedActivated)
        {
            EventHandlerGC.bedActivated = false;
            return true;
        }
        return false;
    }

    @Override
    public int getRespawnDimension(EntityPlayerMP player)
    {
        return this.shouldForceRespawn() ? this.dimensionId : 0;
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
    public boolean shouldForceRespawn()
    {
        return !ConfigManagerCore.forceOverworldRespawn;
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
    public boolean canCoordinateBeSpawn(int chunkX, int chunkZ)
    {
        return true;
    }

    @Override
    public double getFuelUsageMultiplier()
    {
        return 0.9D;
    }

    @Override
    public boolean hasBreathableAtmosphere()
    {
        return false;
    }
}