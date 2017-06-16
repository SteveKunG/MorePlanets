package stevekung.mods.moreplanets.moons.europa.itemblocks;

import net.minecraft.block.Block;
import stevekung.mods.moreplanets.core.itemblocks.ItemBlockBaseMP;

public class ItemBlockEuropaSandstone extends ItemBlockBaseMP
{
    public ItemBlockEuropaSandstone(Block block)
    {
        super(block);
    }

    @Override
    public String[] getBlockVariantsName()
    {
        return new String[] { "europaSandstone", "europaSandstoneChiseled", "europaSandstoneSmooth" };
    }
}