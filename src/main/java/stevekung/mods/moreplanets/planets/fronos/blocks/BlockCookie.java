/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import stevekung.mods.moreplanets.core.blocks.base.BlockBaseMP;

public class BlockCookie extends BlockBaseMP
{
    public BlockCookie(String name)
    {
        super(Material.clay);
        this.setHardness(1.0F);
        this.setStepSound(Block.soundTypeGravel);
        this.setBlockName(name);
        this.setBlockTextureName("fronos:cookie_block");
    }

    @Override
    public Item getItemDropped(int par1, Random par2Random, int par3)
    {
        return Items.cookie;
    }

    @Override
    public int quantityDropped(Random rand)
    {
        return rand.nextInt(4) + 1;
    }
}