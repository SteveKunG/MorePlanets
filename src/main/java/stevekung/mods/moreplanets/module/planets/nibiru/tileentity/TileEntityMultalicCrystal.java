package stevekung.mods.moreplanets.module.planets.nibiru.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import stevekung.mods.moreplanets.util.tileentity.TileEntityRenderTickable;

public class TileEntityMultalicCrystal extends TileEntityRenderTickable
{
    public int facing;

    public TileEntityMultalicCrystal()
    {
        this.facing = 1;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        this.facing = nbt.getShort("Facing");
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setInteger("Facing", this.facing);
    }

    @Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("Facing", this.facing);
        return new S35PacketUpdateTileEntity(this.pos, -1, nbt);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
    {
        if (pkt.getTileEntityType() == -1)
        {
            NBTTagCompound nbt = pkt.getNbtCompound();
            this.facing = nbt.getInteger("Facing");
        }
    }
}