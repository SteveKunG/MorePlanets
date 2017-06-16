/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTorch;
import net.minecraft.creativetab.CreativeTabs;
import stevekung.mods.moreplanets.core.MorePlanetsCore;

public class BlockCandyTorch extends BlockTorch
{
    private String torchTexture;

    public BlockCandyTorch(String name, String texture)
    {
        super();
        this.torchTexture = texture;
        this.setTickRandomly(true);
        this.setLightLevel(1.0F);
        this.setStepSound(Block.soundTypeCloth);
        this.setBlockTextureName(this.torchTexture);
        this.setBlockName(name);
    }

    @Override
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return MorePlanetsCore.mpBlocksTab;
    }
}