package stevekung.mods.moreplanets.module.planets.nibiru.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.util.helper.ColorHelper;

public class ItemBlockMultalicCrystalBlock extends ItemBlock
{
    public ItemBlockMultalicCrystalBlock(Block block)
    {
        super(block);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack itemStack, int renderPass)
    {
        return ColorHelper.rgbToDecimal(50, 101, 236);
    }
}