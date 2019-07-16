package stevekung.mods.moreplanets.moons.koentus.tileentity;

import net.minecraft.nbt.NBTTagCompound;
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