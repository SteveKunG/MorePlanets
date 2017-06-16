package stevekung.mods.moreplanets.moons.europa.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.core.itemblocks.ItemBlockMorePlanet;
import stevekung.mods.moreplanets.moons.europa.blocks.BlockEuropaLeaves;

public class ItemBlockEuropaLeaves extends ItemBlockMorePlanet
{
    public ItemBlockEuropaLeaves(Block block)
    {
        super(block);
    }

    @Override
    public int getMetadata(int meta)
    {
        return meta | 4;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        BlockEuropaLeaves block = (BlockEuropaLeaves)this.field_150939_a;
        return super.getUnlocalizedName() + "." + block.getLeafType(itemStack.getItemDamage());
    }
}