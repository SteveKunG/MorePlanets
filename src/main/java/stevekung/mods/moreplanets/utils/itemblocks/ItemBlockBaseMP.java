package stevekung.mods.moreplanets.utils.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public abstract class ItemBlockBaseMP extends ItemBlock
{
    public ItemBlockBaseMP(Block block)
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
        int meta = itemStack.getItemDamage();
        return meta >= this.getBlockVariantsName().length ? super.getUnlocalizedName(itemStack) + "." + this.getBlockVariantsName()[0] : super.getUnlocalizedName(itemStack) + "." + this.getBlockVariantsName()[meta];
    }

    protected abstract String[] getBlockVariantsName();
}