package stevekung.mods.moreplanets.util.inventory;

import micdoodle8.mods.galacticraft.core.network.PacketSimple;
import micdoodle8.mods.galacticraft.core.network.PacketSimple.EnumSimplePacket;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.util.math.BlockPos;

public class SlotRocketSchematicMP extends Slot
{
    private BlockPos pos;
    private EntityPlayer player;

    public SlotRocketSchematicMP(IInventory inv, int index, int xDisplay, int yDisplay, BlockPos pos, EntityPlayer player)
    {
        super(inv, index, xDisplay, yDisplay);
        this.pos = pos;
        this.player = player;
    }

    @Override
    public void onSlotChanged()
    {
        if (this.player instanceof EntityPlayerMP)
        {
            int dimID = GCCoreUtil.getDimensionID(this.player.worldObj);
            GCCoreUtil.sendToAllAround(new PacketSimple(EnumSimplePacket.C_SPAWN_SPARK_PARTICLES, dimID, new Object[] { this.pos }), this.player.worldObj, dimID, this.pos, 24);
        }
    }

    @Override
    public int getSlotStackLimit()
    {
        return 1;
    }
}