/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockWall;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.init.MPBlocks;

public class BlockDungeonBrickWall extends BlockWall
{
    private IIcon[] wallBlockIcon;

    public BlockDungeonBrickWall(String name)
    {
        super(Blocks.stone);
        this.setBlockName(name);
        this.setHardness(4.0F);
        this.setResistance(40.0F);
    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z)
    {
        if (world.getBlockMetadata(x, y, z) == 6)
        {
            return 15;
        }
        return 0;
    }

    @Override
    public IIcon getIcon(int side, int meta)
    {
        return this.wallBlockIcon[meta];
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public boolean getBlocksMovement(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        return false;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean canPlaceTorchOnTop(World world, int x, int y, int z)
    {
        return true;
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        boolean flag = this.canConnectWallTo(par1IBlockAccess, par2, par3, par4 - 1);
        boolean flag1 = this.canConnectWallTo(par1IBlockAccess, par2, par3, par4 + 1);
        boolean flag2 = this.canConnectWallTo(par1IBlockAccess, par2 - 1, par3, par4);
        boolean flag3 = this.canConnectWallTo(par1IBlockAccess, par2 + 1, par3, par4);
        float f = 0.25F;
        float f1 = 0.75F;
        float f2 = 0.25F;
        float f3 = 0.75F;
        float f4 = 1.0F;

        if (flag)
        {
            f2 = 0.0F;
        }
        if (flag1)
        {
            f3 = 1.0F;
        }
        if (flag2)
        {
            f = 0.0F;
        }
        if (flag3)
        {
            f1 = 1.0F;
        }
        if (flag && flag1 && !flag2 && !flag3)
        {
            f4 = 0.8125F;
            f = 0.3125F;
            f1 = 0.6875F;
        }
        else if (!flag && !flag1 && flag2 && flag3)
        {
            f4 = 0.8125F;
            f2 = 0.3125F;
            f3 = 0.6875F;
        }
        this.setBlockBounds(f, 0.0F, f2, f1, f4, f3);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
        this.maxY = 1.5D;
        return super.getCollisionBoundingBoxFromPool(par1World, par2, par3, par4);
    }

    @Override
    public boolean canConnectWallTo(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        Block block = par1IBlockAccess.getBlock(par2, par3, par4);

        if (block != this && !(block instanceof BlockFenceGate) && block != MPBlocks.stone_wall)
        {
            return block != null && block.getMaterial().isOpaque() && block.renderAsNormalBlock() ? block.getMaterial() != Material.gourd : false;
        }
        return true;
    }

    @Override
    public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        for (int var4 = 0; var4 < this.getTypes().length; ++var4)
        {
            par3List.add(new ItemStack(par1, 1, var4));
        }
    }

    @Override
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return MorePlanetsCore.mpBlocksTab;
    }

    @Override
    public int damageDropped(int par1)
    {
        return par1;
    }

    @Override
    public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        return par5 == 0 ? super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5) : true;
    }

    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.wallBlockIcon = new IIcon[16];
        this.wallBlockIcon[0] = par1IconRegister.registerIcon("diona:diona_dungeon_brick");
        this.wallBlockIcon[1] = par1IconRegister.registerIcon("polongnius:polongnius_dungeon_brick");
        this.wallBlockIcon[2] = par1IconRegister.registerIcon("nibiru:nibiru_dungeon_brick");
        this.wallBlockIcon[3] = par1IconRegister.registerIcon("koentus:koentus_dungeon_brick");
        this.wallBlockIcon[4] = par1IconRegister.registerIcon("fronos:fronos_dungeon_brick");
        this.wallBlockIcon[5] = par1IconRegister.registerIcon("kapteynb:kapteyn_b_dungeon_brick");
        this.wallBlockIcon[6] = par1IconRegister.registerIcon("siriusb:sirius_b_dungeon_brick");
        this.wallBlockIcon[7] = par1IconRegister.registerIcon("mercury:mercury_dungeon_brick");
        this.wallBlockIcon[8] = par1IconRegister.registerIcon("venus:venus_dungeon_brick");
        this.wallBlockIcon[9] = par1IconRegister.registerIcon("pluto:pluto_dungeon_brick");
    }

    private String[] getTypes()
    {
        return new String[] { "dionaBrick", "polongniusBrick", "nibiruBrick", "koentusBrick", "fronosBrick", "kapteynBrick", "siriusBrick", "mercury", "venus", "pluto" };
    }
}