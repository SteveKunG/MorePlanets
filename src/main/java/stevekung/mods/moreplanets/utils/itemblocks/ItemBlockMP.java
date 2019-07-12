package stevekung.mods.moreplanets.utils.itemblocks;

import micdoodle8.mods.galacticraft.core.proxy.ClientProxyCore;
import net.minecraft.block.Block;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBlockMP extends ItemBlock
{
    public ItemBlockMP(Block block)
    {
        super(block);
    }

    @Override
    public String getItemStackDisplayName(ItemStack itemStack)
    {
        return this.block instanceof IItemRarity && ((IItemRarity)this.block).getRarity() != null ? ((IItemRarity)this.block).getRarity().toColoredFont() + super.getItemStackDisplayName(itemStack) : super.getItemStackDisplayName(itemStack);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack itemStack)
    {
        return ClientProxyCore.galacticraftItem;
    }
}