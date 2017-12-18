package stevekung.mods.moreplanets.module.planets.nibiru.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.util.tileentity.TileEntityChestMP;

public class TileEntityInfectedChest extends TileEntityChestMP
{
    public TileEntityInfectedChest()
    {
        super(NibiruBlocks.INFECTED_CHEST);
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

    @Override
    protected TileEntityInfectedChest getAdjacentChest(EnumFacing side)
    {
        BlockPos blockpos = this.pos.offset(side);

        if (this.isChestAt(blockpos))
        {
            TileEntity tileentity = this.worldObj.getTileEntity(blockpos);

            if (tileentity instanceof TileEntityInfectedChest)
            {
                TileEntityInfectedChest tileentitychest = (TileEntityInfectedChest)tileentity;
                tileentitychest.getAdjacentChestFacing(this, side.getOpposite());
                return tileentitychest;
            }
        }
        return null;
    }

    @SuppressWarnings("incomplete-switch")
    private void getAdjacentChestFacing(TileEntityInfectedChest chest, EnumFacing side)
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