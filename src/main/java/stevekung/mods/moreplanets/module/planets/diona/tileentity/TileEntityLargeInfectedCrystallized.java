package stevekung.mods.moreplanets.module.planets.diona.tileentity;

import micdoodle8.mods.miccore.Annotations.NetworkedField;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import stevekung.mods.moreplanets.utils.tileentity.TileEntityAdvancedMP;

public class TileEntityLargeInfectedCrystallized extends TileEntityAdvancedMP
{
    @NetworkedField(targetSide = Side.CLIENT)
    public EnumFacing facing = null;
    private int preLoadFacing = -1;
    public int renderTicks;

    @Override
    public void update()
    {
        super.update();
        this.renderTicks++;

        if (this.preLoadFacing != -1)
        {
            this.setFacing(EnumFacing.getFront(this.preLoadFacing));
            this.preLoadFacing = -1;
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        this.preLoadFacing = nbt.getInteger("Facing");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);

        if (this.facing != null)
        {
            nbt.setInteger("Facing", this.facing.ordinal());
        }
        return nbt;
    }

    @Override
    public double getPacketRange()
    {
        return 64.0D;
    }

    @Override
    public boolean isNetworkedTile()
    {
        return true;
    }

    public void setFacing(EnumFacing facing)
    {
        this.facing = facing;
    }
}