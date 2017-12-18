/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.pluto.blocks;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockTorch;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.MorePlanetsCore;

public class BlockXeoniumTorch extends BlockTorch
{
    public BlockXeoniumTorch(String name)
    {
        super();
        this.setTickRandomly(true);
        this.setLightLevel(0.95F);
        this.setStepSound(soundTypeWood);
        this.setBlockTextureName("pluto:xeonium_torch");
        this.setBlockName(name);
    }

    @Override
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return MorePlanetsCore.mpBlocksTab;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int par2, int par3, int par4, Random par5Random)
    {
        int l = world.getBlockMetadata(par2, par3, par4);
        double d0 = par2 + 0.5F;
        double d1 = par3 + 0.7F;
        double d2 = par4 + 0.5F;
        double d3 = 0.2199999988079071D;
        double d4 = 0.27000001072883606D;

        if (l == 1)
        {
            MorePlanetsCore.proxy.spawnParticle("xeoniumSmoke", d0 - d4, d1 + d3, d2);
        }
        else if (l == 2)
        {
            MorePlanetsCore.proxy.spawnParticle("xeoniumSmoke", d0 + d4, d1 + d3, d2);
        }
        else if (l == 3)
        {
            MorePlanetsCore.proxy.spawnParticle("xeoniumSmoke", d0, d1 + d3, d2 - d4);
        }
        else if (l == 4)
        {
            MorePlanetsCore.proxy.spawnParticle("xeoniumSmoke", d0, d1 + d3, d2 + d4);
        }
        else
        {
            MorePlanetsCore.proxy.spawnParticle("xeoniumSmoke", d0, d1, d2);
        }
    }
}