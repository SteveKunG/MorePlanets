package stevekung.mods.moreplanets.planets.venus.itemblocks;

import net.minecraft.block.Block;
import stevekung.mods.moreplanets.core.itemblocks.ItemBlockBaseMP;

public class ItemBlockVenusSandstone extends ItemBlockBaseMP
{
    public ItemBlockVenusSandstone(Block block)
    {
        super(block);
    }

    @Override
    public String[] getBlockVariantsName()
    {
        return new String[] { "venusSandstone", "venusSandstoneChiseled", "venusSandstoneSmooth" };
    }
}