package stevekung.mods.moreplanets.module.planets.nibiru.tileentity;

import stevekung.mods.moreplanets.module.planets.nibiru.blocks.BlockNibiruFurnace;
import stevekung.mods.moreplanets.utils.tileentity.TileEntityFurnaceMP;

public class TileEntityNibiruFurnace extends TileEntityFurnaceMP
{
    @Override
    protected void setState()
    {
        BlockNibiruFurnace.setState(this.isBurning(), this.world, this.pos);
    }
}