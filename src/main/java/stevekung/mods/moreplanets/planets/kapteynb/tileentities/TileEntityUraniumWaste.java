/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.kapteynb.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import stevekung.mods.moreplanets.planets.kapteynb.blocks.BlockUraniumWaste;

public class TileEntityUraniumWaste extends TileEntity
{
    public int radiationLevel = 10000;

    @Override
    public void updateEntity()
    {
        super.updateEntity();

        boolean flag = this.radiationLevel > 0;

        if (!this.worldObj.isRemote && this.radiationLevel > 0)
        {
            this.radiationLevel--;
        }
        if (flag != this.radiationLevel > 0)
        {
            BlockUraniumWaste.updateBlockState(this.radiationLevel <= 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
        }
    }

    public int getRadiationLevel()
    {
        return this.radiationLevel;
    }

    public void setRadiationLevel(int radLevel)
    {
        this.radiationLevel = radLevel;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        this.radiationLevel = nbt.getInteger("RadiationLevel");
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setInteger("RadiationLevel", this.radiationLevel);
    }
}