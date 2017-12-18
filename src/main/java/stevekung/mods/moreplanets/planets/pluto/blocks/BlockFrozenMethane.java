/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.pluto.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.MorePlanetsCore;

public class BlockFrozenMethane extends BlockBreakable
{
    public BlockFrozenMethane(String name)
    {
        super("pluto:frozen_methane_block", Material.ice, false);
        this.slipperiness = 1.1F;
        this.setBlockName(name);
        this.setHardness(4.0F);
        this.setResistance(8.0F);
        this.setStepSound(soundTypeGlass);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass()
    {
        return 1;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return MorePlanetsCore.mpBlocksTab;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side)
    {
        return super.shouldSideBeRendered(world, x, y, z, 1 - side);
    }

    @Override
    public int getMobilityFlag()
    {
        return 0;
    }

    @Override
    public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int meta)
    {
        ItemStack itemStack = player.getCurrentEquippedItem();
        player.addExhaustion(0.025F);

        if (itemStack == null || !(itemStack.getItem() instanceof ItemPickaxe))
        {
            if (world.rand.nextInt(10) == 0)
            {
                world.setBlock(x, y, z, PlutoBlocks.liquid_methane);
            }
        }
        if (itemStack != null && itemStack.getItem() instanceof ItemPickaxe)
        {
            this.dropBlockAsItem(world, x, y, z, meta, 0);
        }
    }
}