package stevekung.mods.moreplanets.itemblocks;

import net.minecraft.block.Block;
import stevekung.mods.moreplanets.client.renderer.TileEntityItemStackRendererMP;
import stevekung.mods.moreplanets.utils.itemblocks.ItemBlockMP;

public class ItemBlockTESRMP extends ItemBlockMP
{
    public ItemBlockTESRMP(Block block)
    {
        super(block);
        this.setTileEntityItemStackRenderer(TileEntityItemStackRendererMP.INSTANCE);
    }
}