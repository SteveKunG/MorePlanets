package stevekung.mods.moreplanets.module.planets.chalos.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import stevekung.mods.moreplanets.module.planets.chalos.blocks.ChalosBlocks;
import stevekung.mods.moreplanets.util.tileentity.TileEntityAncientChestMP;

public class TileEntityChalosAncientChest extends TileEntityAncientChestMP
{
    public TileEntityChalosAncientChest()
    {
        super(ChalosBlocks.CHALOS_ANCIENT_CHEST, "chalos");
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

    protected TileEntityChalosAncientChest getAdjacentChest(EnumFacing side)
    {
        BlockPos blockpos = this.pos.offset(side);

        if (this.isChestAt(blockpos))
        {
            TileEntity tileentity = this.worldObj.getTileEntity(blockpos);

            if (tileentity instanceof TileEntityChalosAncientChest)
            {
                TileEntityChalosAncientChest tileentitychest = (TileEntityChalosAncientChest)tileentity;
                tileentitychest.getAdjacentChestFacing(this, side.getOpposite());
                return tileentitychest;
            }
        }
        return null;
    }

    @SuppressWarnings("incomplete-switch")
    private void getAdjacentChestFacing(TileEntityChalosAncientChest chest, EnumFacing side)
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