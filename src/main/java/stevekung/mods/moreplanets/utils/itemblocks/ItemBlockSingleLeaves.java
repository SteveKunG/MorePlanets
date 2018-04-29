package stevekung.mods.moreplanets.utils.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class ItemBlockSingleLeaves extends ItemBlock
{
    public ItemBlockSingleLeaves(Block block)
    {
        super(block);
    }

    @Override
    public int getMetadata(int meta)
    {
        return meta | 4;
    }
}