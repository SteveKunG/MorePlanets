package stevekung.mods.moreplanets.core.itemblocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemBlockTintedGlassPane extends ItemBlockMorePlanet
{
    public ItemBlockTintedGlassPane(Block block)
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
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta)
    {
        return this.field_150939_a.func_149735_b(2, BlockColored.func_150032_b(meta));
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return this.field_150939_a.getUnlocalizedName() + "." + ItemDye.field_150921_b[BlockColored.func_150032_b(itemStack.getItemDamage())];
    }
}