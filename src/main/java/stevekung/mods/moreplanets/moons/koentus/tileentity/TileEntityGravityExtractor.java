package stevekung.mods.moreplanets.moons.koentus.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TileEntityGravityExtractor extends TileEntity implements ITickable
{
    private int lifeTime;

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        this.lifeTime = nbt.getInteger("LifeTime");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setInteger("LifeTime", this.lifeTime);
        return nbt;
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket()
    {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("LifeTime", this.lifeTime);
        return new SPacketUpdateTileEntity(this.pos, -1, nbt);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt)
    {
        if (pkt.getTileEntityType() == -1)
        {
            NBTTagCompound nbt = pkt.getNbtCompound();
            this.lifeTime = nbt.getInteger("LifeTime");
        }
    }

    @Override
    public void update()
    {
        if (this.lifeTime > 0)
        {
            if (this.world.rand.nextFloat() > 0.85F)
            {
                this.lifeTime--;
            }
            if (this.lifeTime == 0)
            {
                //TODO
            }
        }
    }

    public void setLifeTime(int lifeTime)
    {
        this.lifeTime = lifeTime;
    }

    public int getLifeTime()
    {
        return this.lifeTime;
    }
}