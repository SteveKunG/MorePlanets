/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.player;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class MPPlayerStats implements IExtendedEntityProperties
{
    public static String MP_PLAYER_PROPERTY = "MPPlayerStats";
    public boolean usingHomePlanetCommand;

    @Override
    public void saveNBTData(NBTTagCompound nbt)
    {
        nbt.setBoolean("usingHomePlanetCM", this.usingHomePlanetCommand);
    }

    @Override
    public void loadNBTData(NBTTagCompound nbt)
    {
        this.usingHomePlanetCommand = nbt.getBoolean("usingHomePlanetCM");
    }

    @Override
    public void init(Entity entity, World world) {}

    public static MPPlayerStats get(EntityPlayerMP player)
    {
        return (MPPlayerStats) player.getExtendedProperties(MPPlayerStats.MP_PLAYER_PROPERTY);
    }
}