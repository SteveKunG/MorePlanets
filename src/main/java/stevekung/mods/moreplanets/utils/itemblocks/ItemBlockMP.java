package stevekung.mods.moreplanets.utils.itemblocks;

import javax.annotation.Nullable;

import micdoodle8.mods.galacticraft.core.proxy.ClientProxyCore;
import net.minecraft.block.Block;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.stevekunglib.utils.ColorUtils;

public class ItemBlockMP extends ItemBlock
{
    public ItemBlockMP(Block block)
    {
        super(block);
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
        return this.block instanceof IItemRarity && ((IItemRarity)this.block).getRarity() != null ? ((IItemRarity)this.block).getRarity().toColoredFont() + super.getItemStackDisplayName(itemStack) : super.getItemStackDisplayName(itemStack);
    }

    @Override
    public String getHighlightTip(ItemStack itemStack, String displayName)
    {
        return super.getItemStackDisplayName(itemStack);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack itemStack)
    {
        return ClientProxyCore.galacticraftItem;
    }
}