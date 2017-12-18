package stevekung.mods.moreplanets.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.util.blocks.IBlockVariants;
import stevekung.mods.moreplanets.util.itemblocks.ItemBlockDescription;

public class ItemBlockTieredEnergyStorage extends ItemBlockDescription
{
    public ItemBlockTieredEnergyStorage(Block block)
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

        if (meta == 0)
        {
            return super.getUnlocalizedName() + "." + this.getBlockVariantsName()[0];
        }
        else
        {
            return super.getUnlocalizedName() + "." + this.getBlockVariantsName()[1];
        }
    }

    private String[] getBlockVariantsName()
    {
        if (this.block instanceof IBlockVariants)
        {
            return ((IBlockVariants)this.block).getVariantsName().getNameList();
        }
        return new String[] {};
    }
}