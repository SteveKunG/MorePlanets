/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.europa.blocks;

import net.minecraft.block.BlockFalling;
import net.minecraft.creativetab.CreativeTabs;
import stevekung.mods.moreplanets.core.MorePlanetsCore;

public class BlockEuropaSand extends BlockFalling
{
    public BlockEuropaSand(String name)
    {
        super();
        this.setStepSound(soundTypeSand);
        this.setHardness(0.55F);
        this.setBlockName(name);
        this.setBlockTextureName("europa:europa_sand");
    }

    @Override
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return MorePlanetsCore.mpBlocksTab;
    }
}