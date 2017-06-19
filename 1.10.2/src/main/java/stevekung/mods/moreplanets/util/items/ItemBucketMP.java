package stevekung.mods.moreplanets.util.items;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsCore;

public class ItemBucketMP extends ItemBucket implements ISortableItem, ISingleItemRender
{
    protected String name;

    public ItemBucketMP(String name, Block fluid)
    {
        super(fluid);
        this.name = name;
        this.setUnlocalizedName(name);
        this.setContainerItem(Items.BUCKET);
    }

    public ItemBucketMP()
    {
        super(Blocks.AIR);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public CreativeTabs getCreativeTab()
    {
        return MorePlanetsCore.ITEM_TAB;
    }

    @Override
    public EnumSortCategoryItem getItemCategory(int meta)
    {
        return EnumSortCategoryItem.BUCKET_FLUID;
    }

    @Override
    public String getName()
    {
        return this.name;
    }

    @Override
    public Item getItem()
    {
        return this;
    }
}