/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.koentus.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.moons.koentus.tileentities.TileEntityEledosEgg;

public class BlockEledosEgg extends Block implements ITileEntityProvider
{
    private IIcon koentusEggCrack;

    public BlockEledosEgg(String name)
    {
        super(Material.rock);
        this.setHardness(0.75F);
        this.setBlockName(name);
        this.setBlockBounds(0.125F, 0.0F, 0.1F, 0.875F, 0.70F, 0.9F);
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.blockIcon = iconRegister.registerIcon("koentus:eledos_egg");
        this.koentusEggCrack = iconRegister.registerIcon("koentus:eledos_egg_crack");
    }

    @Override
    public boolean canBlockStay(World par1World, int par2, int par3, int par4)
    {
        final Block block = par1World.getBlock(par2, par3 - 1, par4);

        if (block != Blocks.air)
        {
            return block.isSideSolid(par1World, par2, par3, par4, ForgeDirection.UP);
        }
        return false;
    }

    @Override
    public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z)
    {
        final ItemStack currentStack = player.getCurrentEquippedItem();

        if (currentStack != null && currentStack.getItem() instanceof ItemPickaxe)
        {
            return world.setBlockToAir(x, y, z);
        }
        else if (player.capabilities.isCreativeMode)
        {
            return world.setBlockToAir(x, y, z);
        }
        /*else if (l < 1)
		{
			world.setBlockMetadataWithNotify(x, y, z, 1, 2);

			TileEntity tile = world.getTileEntity(x, y, z);

			if (tile instanceof TileEntityEledosEgg)
			{
				((TileEntityEledosEgg) tile).timeToHatch = world.rand.nextInt(1000);
			}
			return false;
		}*///TODO
        else
        {
            return false;
        }
    }

    @Override
    public void harvestBlock(World world, EntityPlayer par2EntityPlayer, int x, int y, int z, int par6)
    {
        final ItemStack currentStack = par2EntityPlayer.getCurrentEquippedItem();

        if (currentStack != null && currentStack.getItem() instanceof ItemPickaxe)
        {
            par2EntityPlayer.addStat(StatList.mineBlockStatArray[Block.getIdFromBlock(this)], 1);
            par2EntityPlayer.addExhaustion(0.025F);
            this.dropBlockAsItem(world, x, y, z, 0, 0);
        }
    }

    @Override
    public IIcon getIcon(int side, int metadata)
    {
        if (metadata == 1)
        {
            return this.koentusEggCrack;
        }
        return this.blockIcon;
    }

    @Override
    public int getRenderType()
    {
        return MorePlanetsCore.proxy.getBlockRender(this);
    }

    @Override
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return MorePlanetsCore.mpBlocksTab;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public Item getItemDropped(int meta, Random random, int par3)
    {
        return Item.getItemFromBlock(this);
    }

    @Override
    public int damageDropped(int meta)
    {
        return 0;
    }

    @Override
    public int quantityDropped(int meta, int fortune, Random random)
    {
        return 1;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TileEntityEledosEgg();
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
    {
        final int metadata = world.getBlockMetadata(x, y, z);

        if (metadata == 1)
        {
            return new ItemStack(Item.getItemFromBlock(this), 1, 0);
        }
        return super.getPickBlock(target, world, x, y, z);
    }
}