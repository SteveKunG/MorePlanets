package stevekung.mods.moreplanets.tileentity;

import java.util.ArrayList;

import com.google.common.collect.Lists;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import stevekung.mods.moreplanets.init.MPBlocks;

public class TileEntitySpaceWarpPad extends TileEntity implements ITickable
{
    @Override
    public void update()
    {
        if (!this.world.isRemote)
        {
            ArrayList<TileEntity> attachedLaunchPads = Lists.newArrayList();

            for (int x = this.getPos().getX() - 1; x < this.getPos().getX() + 2; x++)
            {
                for (int z = this.getPos().getZ() - 1; z < this.getPos().getZ() + 2; z++)
                {
                    TileEntity tile = this.world.getTileEntity(new BlockPos(x, this.getPos().getY(), z));

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
                    this.world.markTileEntityForRemoval(tile);
                }

                this.world.setBlockState(this.getPos(), MPBlocks.SPACE_WARP_PAD_FULL.getDefaultState(), 2);
                TileEntitySpaceWarpPadFull tilePadFull = (TileEntitySpaceWarpPadFull) this.world.getTileEntity(this.getPos());

                if (tilePadFull != null)
                {
                    tilePadFull.onCreate(this.world, this.getPos());
                }
            }
        }
    }
}