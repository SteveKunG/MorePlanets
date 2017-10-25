package stevekung.mods.moreplanets.tileentity;

import java.util.ArrayList;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import stevekung.mods.moreplanets.init.MPBlocks;

public class TileEntitySpaceWarpPad extends TileEntity implements ITickable
{
    @Override
    public void update()
    {
        if (!this.worldObj.isRemote)
        {
            ArrayList<TileEntity> attachedLaunchPads = new ArrayList<>();

            for (int x = this.getPos().getX() - 1; x < this.getPos().getX() + 2; x++)
            {
                for (int z = this.getPos().getZ() - 1; z < this.getPos().getZ() + 2; z++)
                {
                    TileEntity tile = this.worldObj.getTileEntity(new BlockPos(x, this.getPos().getY(), z));

                    if (tile instanceof TileEntitySpaceWarpPad)
                    {
                        attachedLaunchPads.add(tile);
                    }
                }
            }

            if (attachedLaunchPads.size() == 9)
            {
                for (TileEntity tile : attachedLaunchPads)
                {
                    this.worldObj.markTileEntityForRemoval(tile);
                }

                this.worldObj.setBlockState(this.getPos(), MPBlocks.SPACE_WARP_PAD_FULL.getDefaultState(), 2);
                TileEntitySpaceWarpPadFull tilePadFull = (TileEntitySpaceWarpPadFull) this.worldObj.getTileEntity(this.getPos());

                if (tilePadFull != null)
                {
                    tilePadFull.onCreate(this.worldObj, this.getPos());
                }
            }
        }
    }
}