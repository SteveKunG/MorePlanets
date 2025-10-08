package com.stevekung.moreplanets.planets.nibiru.tileentity;

import com.stevekung.moreplanets.init.MPBlocks;
import com.stevekung.moreplanets.utils.tileentity.TileEntityChestMP;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class TileEntityNibiruAncientChest extends TileEntityChestMP
{
    public TileEntityNibiruAncientChest()
    {
        super(MPBlocks.NIBIRU_ANCIENT_CHEST, "nibiru");
    }

    @Override
    public void checkForAdjacentChests()
    {
        if (!this.adjacentChestChecked)
        {
            if (this.world == null || !this.world.isAreaLoaded(this.pos, 1))
            {
                return;
            }
            this.adjacentChestChecked = true;
            this.adjacentChestXNeg = this.getAdjacentChest(EnumFacing.WEST);
            this.adjacentChestXPos = this.getAdjacentChest(EnumFacing.EAST);
            this.adjacentChestZNeg = this.getAdjacentChest(EnumFacing.NORTH);
            this.adjacentChestZPos = this.getAdjacentChest(EnumFacing.SOUTH);
        }
    }

    private TileEntityNibiruAncientChest getAdjacentChest(EnumFacing side)
    {
        BlockPos blockpos = this.pos.offset(side);

        if (this.isChestAt(blockpos))
        {
            TileEntity tileentity = this.world.getTileEntity(blockpos);

            if (tileentity instanceof TileEntityNibiruAncientChest)
            {
                TileEntityNibiruAncientChest tileentitychest = (TileEntityNibiruAncientChest)tileentity;
                tileentitychest.setNeighbor(this, side.getOpposite());
                return tileentitychest;
            }
        }
        return null;
    }
}