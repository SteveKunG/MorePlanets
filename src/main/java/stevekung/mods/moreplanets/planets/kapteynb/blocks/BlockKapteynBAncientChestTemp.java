/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.kapteynb.blocks;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import stevekung.mods.moreplanets.core.blocks.BlockAncientChestMP;
import stevekung.mods.moreplanets.planets.kapteynb.tileentities.TileEntityKapteynBAncientChestTemp;

public class BlockKapteynBAncientChestTemp extends BlockAncientChestMP implements ITileEntityProvider
{
    public BlockKapteynBAncientChestTemp(String name)
    {
        super();
        this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
        this.setBlockName(name);
    }

    @Override
    public String chestTexture()
    {
        return null;
    }

    @Override
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return null;
    }

    @Override
    public TileEntity getChestTile()
    {
        return new TileEntityKapteynBAncientChestTemp();
    }
}