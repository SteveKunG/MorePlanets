package stevekung.mods.moreplanets.util.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockColoredMP extends ItemBlock
{
    public ItemBlockColoredMP(Block block)
    {
        super(block);
        this.setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int meta)
    {
        return meta;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return super.getUnlocalizedName() + "." + EnumDyeColor.byMetadata(itemStack.getMetadata()).getUnlocalizedName();
    }
}