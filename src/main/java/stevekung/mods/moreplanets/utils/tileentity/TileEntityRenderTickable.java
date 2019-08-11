package stevekung.mods.moreplanets.utils.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TileEntityRenderTickable extends TileEntity implements ITickable
{
    public int renderTicks;
    protected boolean isContainer;
    protected boolean initialize;

    @Override
    public void update()
    {
        if (!this.initialize)
        {
            this.renderTicks = this.renderTicks + this.world.rand.nextInt(100);
            this.initialize = true;
        }
        this.renderTicks++;
    }

    @Override
    public NBTTagCompound getUpdateTag()
    {
        if (!this.isContainer)
        {
            return this.writeToNBT(new NBTTagCompound());
        }
        return super.getUpdateTag();
    }
}