package stevekung.mods.moreplanets.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.IFuelHandler;
import stevekung.mods.moreplanets.module.planets.chalos.blocks.ChalosBlocks;
import stevekung.mods.moreplanets.module.planets.diona.items.DionaItems;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.module.planets.nibiru.items.NibiruItems;

public class FuelHandlerMP implements IFuelHandler
{
    @Override
    public int getBurnTime(ItemStack itemStack)
    {
        Item item = itemStack.getItem();
        Block block = Block.getBlockFromItem(item);
        int meta = itemStack.getItemDamage();

        if (item == DionaItems.DIONA_ITEM && meta == 4)
        {
            return 400;
        }
        if (item == NibiruItems.NIBIRU_ITEM && meta == 2)
        {
            return 1600;
        }
        if (block == ChalosBlocks.CHEESE_SPORE_FLOWER || block == NibiruBlocks.NIBIRU_SAPLING)
        {
            return 100;
        }
        return 0;
    }
}