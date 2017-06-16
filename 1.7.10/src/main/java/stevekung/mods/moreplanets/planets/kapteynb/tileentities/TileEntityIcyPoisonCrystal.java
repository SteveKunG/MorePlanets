/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.kapteynb.tileentities;

import micdoodle8.mods.galacticraft.core.util.OxygenUtil;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityIcyPoisonCrystal extends TileEntity
{
    public short orientation;

    public TileEntityIcyPoisonCrystal()
    {
        this.orientation = 1;
    }

    @Override
    public void updateEntity()
    {
        if (!this.worldObj.isRemote)
        {
            if (OxygenUtil.inOxygenBubble(this.worldObj, this.xCoord, this.yCoord, this.zCoord) || OxygenUtil.isInOxygenBlock(this.worldObj, AxisAlignedBB.getBoundingBox(this.xCoord, this.yCoord, this.zCoord, this.xCoord, this.yCoord, this.zCoord)))
            {
                this.worldObj.createExplosion(null, this.xCoord, this.yCoord, this.zCoord, 2.0F, true);
            }
            if (this.worldObj.provider.dimensionId == 0 || !OxygenUtil.noAtmosphericCombustion(this.worldObj.provider))
            {
                if (this.worldObj.rand.nextInt(100) == 0)
                {
                    this.worldObj.createExplosion(null, this.xCoord, this.yCoord, this.zCoord, 2.0F, true);
                }
            }
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        this.orientation = nbt.getShort("Orientation");
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setShort("Orientation", this.orientation);
    }

    @Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound nbt = new NBTTagCompound();
        this.writeToNBT(nbt);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, -999, nbt);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
    {
        this.readFromNBT(pkt.func_148857_g());
    }
}