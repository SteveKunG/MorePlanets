package stevekung.mods.moreplanets.planets.chalos.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import stevekung.mods.moreplanets.planets.chalos.blocks.ChalosBlocks;
import stevekung.mods.moreplanets.utils.tileentity.TileEntityChestMP;

public class TileEntityCheeseSporeChest extends TileEntityChestMP
{
    public TileEntityCheeseSporeChest()
    {
        super(ChalosBlocks.CHEESE_SPORE_CHEST);
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
    protected TileEntityCheeseSporeChest getAdjacentChest(EnumFacing side)
    {
        BlockPos blockpos = this.pos.offset(side);

        if (this.isChestAt(blockpos))
        {
            TileEntity tileentity = this.world.getTileEntity(blockpos);

            if (tileentity instanceof TileEntityCheeseSporeChest)
            {
                TileEntityCheeseSporeChest tileentitychest = (TileEntityCheeseSporeChest)tileentity;
                tileentitychest.getAdjacentChestFacing(this, side.getOpposite());
                return tileentitychest;
            }
        }
        return null;
    }

    @SuppressWarnings("incomplete-switch")
    private void getAdjacentChestFacing(TileEntityCheeseSporeChest chest, EnumFacing side)
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