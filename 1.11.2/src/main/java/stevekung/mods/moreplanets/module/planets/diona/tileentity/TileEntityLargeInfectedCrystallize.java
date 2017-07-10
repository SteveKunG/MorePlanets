package stevekung.mods.moreplanets.module.planets.diona.tileentity;

import micdoodle8.mods.galacticraft.core.tile.TileEntityAdvanced;
import micdoodle8.mods.miccore.Annotations.NetworkedField;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.fml.relauncher.Side;

public class TileEntityLargeInfectedCrystallize extends TileEntityAdvanced implements ITickable
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
        nbt.setInteger("Facing", this.facing.ordinal());
        return nbt;
    }

    @Override
    public double getPacketRange()
    {
        return 64.0D;
    }

    @Override
    public int getPacketCooldown()
    {
        return 1;
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