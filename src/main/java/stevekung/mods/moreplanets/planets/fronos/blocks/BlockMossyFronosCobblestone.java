/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.blocks;

import net.minecraft.block.material.Material;
import stevekung.mods.moreplanets.core.blocks.base.BlockBaseMP;

public class BlockMossyFronosCobblestone extends BlockBaseMP
{
    public BlockMossyFronosCobblestone(String name)
    {
        super(Material.rock);
        this.setHardness(2.0F);
        this.setBlockName(name);
        this.setBlockTextureName("fronos:mossy_fronos_cobblestone");
    }
}