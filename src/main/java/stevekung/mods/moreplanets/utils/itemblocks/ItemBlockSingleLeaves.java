package stevekung.mods.moreplanets.utils.itemblocks;

import net.minecraft.block.Block;

public class ItemBlockSingleLeaves extends ItemBlockMP
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