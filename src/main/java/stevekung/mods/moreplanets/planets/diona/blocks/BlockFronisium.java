/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.diona.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import stevekung.mods.moreplanets.core.blocks.base.BlockBaseMP;

public class BlockFronisium extends BlockBaseMP
{
    public BlockFronisium(String name)
    {
        super(Material.iron);
        this.setResistance(6.0F);
        this.setHardness(3.5F);
        this.setBlockName(name);
        this.setStepSound(soundTypeMetal);
        this.setBlockTextureName("diona:fronisium_block");
    }

    @Override
    public boolean isBeaconBase(IBlockAccess world, int x, int y, int z, int beaconX, int beaconY, int beaconZ)
    {
        return true;
    }
}