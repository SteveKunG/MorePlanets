/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.asteroids.darkasteroids.blocks;

import java.util.Random;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import stevekung.mods.moreplanets.core.blocks.base.BlockBaseMP;

public class BlockAlienGlowstone extends BlockBaseMP
{
    public BlockAlienGlowstone(String name)
    {
        super(Material.glass);
        this.setBlockName(name);
        this.setHardness(0.3F);
        this.setStepSound(soundTypeGlass);
        this.setBlockTextureName("mpcore:darkasteroids/alien_glowstone");
        this.setLightLevel(1.0F);
    }

    @Override
    public int quantityDroppedWithBonus(int fortune, Random rand)
    {
        return MathHelper.clamp_int(this.quantityDropped(rand) + rand.nextInt(fortune + 1), 1, 4);
    }

    @Override
    public int quantityDropped(Random rand)
    {
        return 2 + rand.nextInt(3);
    }

    @Override
    public Item getItemDropped(int meta, Random rand, int fortune)
    {
        return null;
    }

    @Override
    public MapColor getMapColor(int meta)
    {
        return MapColor.cyanColor;
    }
}