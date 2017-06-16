/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.koentus.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.core.itemblocks.ItemBlockMorePlanet;
import stevekung.mods.moreplanets.moons.koentus.blocks.BlockCrystalLog;

public class ItemBlockCrystalLog extends ItemBlockMorePlanet
{
    public ItemBlockCrystalLog(Block block)
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
        BlockCrystalLog block = (BlockCrystalLog)this.field_150939_a;
        return super.getUnlocalizedName() + "." + block.getWoodType(itemStack.getItemDamage()) + "Wood";
    }
}