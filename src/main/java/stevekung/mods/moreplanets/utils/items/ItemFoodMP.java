package stevekung.mods.moreplanets.utils.items;

import javax.annotation.Nullable;

import micdoodle8.mods.galacticraft.core.proxy.ClientProxyCore;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.utils.client.renderer.IItemModelRender;
import stevekung.mods.moreplanets.utils.itemblocks.IItemRarity;
import stevekung.mods.stevekunglib.utils.ColorUtils;

public abstract class ItemFoodMP extends ItemFood implements ISortableItem, IItemModelRender, IItemRarity
{
    private String name;
    private ColorUtils.RGB rgb;

    public ItemFoodMP()
    {
        super(0, false);
    }

    @Override
    public Item setUnlocalizedName(String name)
    {
        this.name = name;
        return super.setUnlocalizedName(name);
    }

    @Override
    public CreativeTabs getCreativeTab()
    {
        return MorePlanetsMod.ITEM_TAB;
    }

    @Override
    public EnumSortCategoryItem getItemCategory()
    {
        return EnumSortCategoryItem.FOOD;
    }

    @Override
    public String getName()
    {
        return this.name;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack itemStack)
    {
        return ClientProxyCore.galacticraftItem;
    }

    @Override
    public ColorUtils.RGB getRarity()
    {
        return this.rgb != null ? this.rgb : ColorUtils.stringToFullRGB(IItemRarity.DEFAULT);
    }

    @Override
    @SideOnly(Side.CLIENT)
    @Nullable
    public FontRenderer getFontRenderer(ItemStack itemStack)
    {
        return ColorUtils.coloredFontRenderer;
    }

    @Override
    public String getItemStackDisplayName(ItemStack itemStack)
    {
        return this instanceof IItemRarity ? ((IItemRarity)this).getRarity().toColoredFont() + super.getItemStackDisplayName(itemStack) : super.getItemStackDisplayName(itemStack);
    }

    public ItemFoodMP setRarityRGB(ColorUtils.RGB rgb)
    {
        this.rgb = rgb;
        return this;
    }
}