/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.deimos.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import stevekung.mods.moreplanets.core.util.RegisterHelper;
import stevekung.mods.moreplanets.moons.deimos.itemblocks.ItemBlockDeimos;

public class DeimosBlocks
{
    public static Block deimos_block;

    public static void init()
    {
        initBlocks();
        setHarvestLevels();
        registerBlocks();
    }

    private static void initBlocks()
    {
        DeimosBlocks.deimos_block = new BlockBasicDeimos("deimos_block");
    }

    private static void setHarvestLevels()
    {
        DeimosBlocks.deimos_block.setHarvestLevel("pickaxe", 1);
    }

    private static void registerBlocks()
    {
        RegisterHelper.registerBlock(DeimosBlocks.deimos_block, ItemBlockDeimos.class);

        OreDictionary.registerOre("oreTin", new ItemStack(DeimosBlocks.deimos_block, 1, 4));
        OreDictionary.registerOre("oreCopper", new ItemStack(DeimosBlocks.deimos_block, 1, 5));
        OreDictionary.registerOre("oreIron", new ItemStack(DeimosBlocks.deimos_block, 1, 6));
        OreDictionary.registerOre("oreDesh", new ItemStack(DeimosBlocks.deimos_block, 1, 7));
    }
}