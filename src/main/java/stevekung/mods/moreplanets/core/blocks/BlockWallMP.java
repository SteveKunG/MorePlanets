/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.blocks;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockWall;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.init.MPBlocks;

public class BlockWallMP extends BlockWall
{
    @SideOnly(Side.CLIENT)
    private IIcon[] wallBlockIcon;

    public BlockWallMP(String name)
    {
        super(Blocks.stone);
        this.setBlockName(name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.wallBlockIcon = new IIcon[16];
        this.wallBlockIcon[0] = par1IconRegister.registerIcon("diona:diona_cobblestone");
        this.wallBlockIcon[1] = par1IconRegister.registerIcon("diona:quontonium_brick");
        this.wallBlockIcon[2] = par1IconRegister.registerIcon("diona:chiseled_quontonium");
        this.wallBlockIcon[3] = par1IconRegister.registerIcon("polongnius:polongnius_cobblestone");
        this.wallBlockIcon[4] = par1IconRegister.registerIcon("nibiru:nibiru_cobblestone");
        this.wallBlockIcon[5] = par1IconRegister.registerIcon("koentus:koentus_cobblestone");
        this.wallBlockIcon[6] = par1IconRegister.registerIcon("koentus:koentus_ancient_stone");
        this.wallBlockIcon[7] = par1IconRegister.registerIcon("koentus:ancient_koentus_brick");
        this.wallBlockIcon[8] = par1IconRegister.registerIcon("fronos:fronos_cobblestone");
        this.wallBlockIcon[9] = par1IconRegister.registerIcon("fronos:fronos_stone_brick");
        this.wallBlockIcon[10] = par1IconRegister.registerIcon("fronos:cracked_fronos_stone_brick");
        this.wallBlockIcon[11] = par1IconRegister.registerIcon("kapteynb:kapteyn_b_cobblestone");
        this.wallBlockIcon[12] = par1IconRegister.registerIcon("siriusb:sirius_b_carbon_cobblestone");
        this.wallBlockIcon[13] = par1IconRegister.registerIcon("mercury:mercury_cobblestone");
        this.wallBlockIcon[14] = par1IconRegister.registerIcon("venus:venus_cobblestone");
        this.wallBlockIcon[15] = par1IconRegister.registerIcon("pluto:pluto_cobblestone");
    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z)
    {
        if (world.getBlockMetadata(x, y, z) == 12)
        {
            return 15;
        }
        return 0;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        return this.wallBlockIcon[meta];
    }

    @Override
    public float getBlockHardness(World world, int x, int y, int z)
    {
        int meta = world.getBlockMetadata(x, y, z);

        switch (meta)
        {
        case 0:
        case 6:
        case 7:
            this.blockHardness = 2.5F;
            break;
        case 1:
        case 2:
        case 5:
        case 11:
            this.blockHardness = 3.25F;
            break;
        case 3:
            this.blockHardness = 3.0F;
            break;
        case 4:
            this.blockHardness = 4.25F;
            break;
        case 8:
            this.blockHardness = 1.75F;
            break;
        case 9:
        case 10:
            this.blockHardness = 2.25F;
            break;
        case 12:
            this.blockHardness = 4.5F;
            break;
        default:
            this.blockHardness = 2.0F;
            break;
        }
        return this.blockHardness;
    }

    @Override
    public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ)
    {
        return super.getBlockHardness(world, x, y, z) + 2.0F;
    }

    @Override
    public boolean canPlaceTorchOnTop(World world, int x, int y, int z)
    {
        return true;
    }

    @Override
    public boolean canConnectWallTo(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        Block block = par1IBlockAccess.getBlock(par2, par3, par4);

        if (block != this && !(block instanceof BlockFenceGate) && block != MPBlocks.dungeon_brick_wall)
        {
            return block != null && block.getMaterial().isOpaque() && block.renderAsNormalBlock() ? block.getMaterial() != Material.gourd : false;
        }
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list)
    {
        for (int i = 0; i < this.wallBlocks().length; ++i)
        {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return MorePlanetsCore.mpBlocksTab;
    }

    @Override
    public int damageDropped(int meta)
    {
        return meta;
    }

    private String[] wallBlocks()
    {
        return new String[] { "diona", "quonBrick", "quonChiseled", "polongnius", "nibiru", "koentus", "koentusStone", "koentusBrick", "fronos", "fronosBrick", "fronosCrackBrick", "kapteyn", "sirius", "mercury", "venus", "pluto" };
    }
}