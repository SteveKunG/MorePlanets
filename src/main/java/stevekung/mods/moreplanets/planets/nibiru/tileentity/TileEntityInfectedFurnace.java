package stevekung.mods.moreplanets.planets.nibiru.tileentity;

import stevekung.mods.moreplanets.utils.blocks.BlockFurnaceMP;
import stevekung.mods.moreplanets.utils.tileentity.TileEntityFurnaceMP;

public class TileEntityInfectedFurnace extends TileEntityFurnaceMP
{
    @Override
    protected void setState()
    {
        BlockFurnaceMP.setState(BlockFurnaceMP.BlockType.INFECTED, this.isBurning(), this.world, this.pos);
    }
}