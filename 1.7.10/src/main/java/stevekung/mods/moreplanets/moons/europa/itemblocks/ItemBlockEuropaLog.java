package stevekung.mods.moreplanets.moons.europa.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.core.itemblocks.ItemBlockMorePlanet;
import stevekung.mods.moreplanets.moons.europa.blocks.BlockEuropaLog;

public class ItemBlockEuropaLog extends ItemBlockMorePlanet
{
    public ItemBlockEuropaLog(Block block)
    {
        super(block);
    }

    @Override
    public int getMetadata(int meta)
    {
        return meta;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        BlockEuropaLog block = (BlockEuropaLog)this.field_150939_a;
        return super.getUnlocalizedName() + "." + block.getWoodType(itemStack.getItemDamage()) + "_wood";
    }
}