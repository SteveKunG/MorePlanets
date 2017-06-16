/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.mercury.blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.blocks.base.BlockBaseMP;
import stevekung.mods.moreplanets.planets.mercury.items.MercuryItems;

public class BlockMetallicRock extends BlockBaseMP
{
    public BlockMetallicRock(String name)
    {
        super(Material.rock);
        this.setBlockName(name);
        this.setHardness(3.0F);
        this.setBlockTextureName("mercury:metallic_rock");
    }

    @Override
    public Item getItemDropped(int state, Random rand, int fortune)
    {
        if (fortune > 3)
        {
            fortune = 3;
        }
        if (rand.nextInt(10 - fortune * 3) == 0)
        {
            return MercuryItems.mercury_item;
        }
        return Item.getItemFromBlock(this);
    }

    @Override
    public void dropBlockAsItemWithChance(World world, int par2, int par3, int par4, int par5, float par6, int par7)
    {
        super.dropBlockAsItemWithChance(world, par2, par3, par4, par5, par6, par7);

        if (this.getItemDropped(par5, world.rand, par7) != Item.getItemFromBlock(this))
        {
            int var8 = MathHelper.getRandomIntegerInRange(world.rand, 3, 5);
            this.dropXpOnBlockBreak(world, par2, par3, par4, var8);
        }
    }
}