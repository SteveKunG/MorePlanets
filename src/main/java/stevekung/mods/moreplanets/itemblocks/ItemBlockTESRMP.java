package stevekung.mods.moreplanets.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import stevekung.mods.moreplanets.client.renderer.TileEntityItemStackRendererMP;

public class ItemBlockTESRMP extends ItemBlock
{
    public ItemBlockTESRMP(Block block)
    {
        super(block);
        this.setTileEntityItemStackRenderer(TileEntityItemStackRendererMP.INSTANCE);
    }
}