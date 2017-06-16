/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.europa.blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import stevekung.mods.moreplanets.core.blocks.base.BlockBaseMP;

public class BlockPackedEuropaIce extends BlockBaseMP
{
    public BlockPackedEuropaIce(String name)
    {
        super(Material.packedIce);
        this.slipperiness = 0.98F;
        this.setHardness(0.5F);
        this.setResistance(0.1F);
        this.setStepSound(soundTypeGlass);
        this.setBlockName(name);
        this.setBlockTextureName("europa:packed_europa_ice");
    }

    @Override
    public int quantityDropped(Random rand)
    {
        return 0;
    }
}