/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.koentus.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.core.itemblocks.ItemBlockMorePlanet;

public class ItemBlockGlowingIce extends ItemBlockMorePlanet
{
    public ItemBlockGlowingIce(Block block)
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
        return this.field_150939_a.getUnlocalizedName() + "." + ItemDye.field_150921_b[BlockColored.func_150032_b(itemStack.getItemDamage())];
    }
}