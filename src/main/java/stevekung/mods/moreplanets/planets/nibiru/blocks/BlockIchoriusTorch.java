/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.nibiru.blocks;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTorch;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.MorePlanetsCore;

public class BlockIchoriusTorch extends BlockTorch
{
    public BlockIchoriusTorch(String name)
    {
        super();
        this.setTickRandomly(true);
        this.setLightLevel(0.7F);
        this.setStepSound(Block.soundTypeWood);
        this.setBlockName(name);
    }

    @Override
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return MorePlanetsCore.mpBlocksTab;
    }

    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon("nibiru:ichorius_torch");
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        final int l = par1World.getBlockMetadata(par2, par3, par4);
        final double d0 = par2 + 0.5F;
        final double d1 = par3 + 0.7F;
        final double d2 = par4 + 0.5F;
        final double d3 = 0.2199999988079071D;
        final double d4 = 0.27000001072883606D;

        if (l == 1)
        {
            MorePlanetsCore.proxy.spawnParticle("ichoriusSmoke", d0 - d4, d1 + d3, d2);
        }
        else if (l == 2)
        {
            MorePlanetsCore.proxy.spawnParticle("ichoriusSmoke", d0 + d4, d1 + d3, d2);
        }
        else if (l == 3)
        {
            MorePlanetsCore.proxy.spawnParticle("ichoriusSmoke", d0, d1 + d3, d2 - d4);
        }
        else if (l == 4)
        {
            MorePlanetsCore.proxy.spawnParticle("ichoriusSmoke", d0, d1 + d3, d2 + d4);
        }
        else
        {
            MorePlanetsCore.proxy.spawnParticle("ichoriusSmoke", d0, d1, d2);
        }
    }
}