package stevekung.mods.moreplanets.planets.diona.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.utils.tileentity.TileEntityAncientChestMP;

public class TileEntityDionaAncientChest extends TileEntityAncientChestMP
{
    public TileEntityDionaAncientChest()
    {
        super(MPBlocks.DIONA_ANCIENT_CHEST, "diona");
    }

    @Override
    public void checkForAdjacentChests()
    {
        if (!this.adjacentChestChecked)
        {
            this.adjacentChestChecked = true;
            this.adjacentChestXNeg = this.getAdjacentChest(EnumFacing.WEST);
            this.adjacentChestXPos = this.getAdjacentChest(EnumFacing.EAST);
            this.adjacentChestZNeg = this.getAdjacentChest(EnumFacing.NORTH);
            this.adjacentChestZPos = this.getAdjacentChest(EnumFacing.SOUTH);
        }
    }

    protected TileEntityDionaAncientChest getAdjacentChest(EnumFacing side)
    {
        BlockPos blockpos = this.pos.offset(side);

        if (this.isChestAt(blockpos))
        {
            TileEntity tileentity = this.world.getTileEntity(blockpos);

            if (tileentity instanceof TileEntityDionaAncientChest)
            {
                TileEntityDionaAncientChest tileentitychest = (TileEntityDionaAncientChest)tileentity;
                tileentitychest.getAdjacentChestFacing(this, side.getOpposite());
                return tileentitychest;
            }
        }
        return null;
    }

    @SuppressWarnings("incomplete-switch")
    private void getAdjacentChestFacing(TileEntityDionaAncientChest chest, EnumFacing side)
    {
        if (chest.isInvalid())
        {
            this.adjacentChestChecked = false;
        }
        else if (this.adjacentChestChecked)
        {
            switch (side)
            {
            case NORTH:
                if (this.adjacentChestZNeg != chest)
                {
                    this.adjacentChestChecked = false;
                }
                break;
            case SOUTH:
                if (this.adjacentChestZPos != chest)
                {
                    this.adjacentChestChecked = false;
                }
                break;
            case EAST:
                if (this.adjacentChestXPos != chest)
                {
                    this.adjacentChestChecked = false;
                }
                break;
            case WEST:
                if (this.adjacentChestXNeg != chest)
                {
                    this.adjacentChestChecked = false;
                }
            }
        }
    }
}