package stevekung.mods.moreplanets.module.planets.fronos.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBlockFronosGrass extends ItemBlock
{
    public ItemBlockFronosGrass(Block block)
    {
        super(block);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack itemStack, int renderPass)
    {
        return this.block.getRenderColor(this.block.getStateFromMeta(itemStack.getMetadata()));
    }
}