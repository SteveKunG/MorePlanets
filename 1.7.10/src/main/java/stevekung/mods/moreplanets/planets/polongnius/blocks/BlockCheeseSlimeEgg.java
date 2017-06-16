/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.polongnius.blocks;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import micdoodle8.mods.galacticraft.planets.mars.items.MarsItems;
import net.minecraft.block.BlockDragonEgg;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.planets.polongnius.entities.EntityCheeseSlime;

public class BlockCheeseSlimeEgg extends BlockDragonEgg
{
    public BlockCheeseSlimeEgg(String name)
    {
        super();
        this.setStepSound(MorePlanetsCore.soundTypeSlime);
        this.setResistance(0.0F);
        this.setHardness(-1.0F);
        this.setBlockName(name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.blockIcon = iconRegister.registerIcon("polongnius:cheese_slime");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass()
    {
        return 1;
    }

    @Override
    public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        return super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5 - 1);
    }

    @Override
    public Item getItemDropped(int par1, Random par2Random, int par3)
    {
        return Item.getItemFromBlock(this);
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return MorePlanetsCore.mpBlocksTab;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
        return false;
    }

    @Override
    public void onBlockClicked(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer)
    {
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getItem(World par1World, int par2, int par3, int par4)
    {
        return Item.getItemFromBlock(this);
    }

    @Override
    public boolean canHarvestBlock(EntityPlayer player, int metadata)
    {
        ItemStack stack = player.inventory.getCurrentItem();

        if (stack == null)
        {
            return player.canHarvestBlock(this);
        }
        return stack.getItem() == MarsItems.deshPickSlime;
    }

    @Override
    public float getPlayerRelativeBlockHardness(EntityPlayer player, World world, int x, int y, int z)
    {
        ItemStack stack = player.inventory.getCurrentItem();

        if (stack != null && stack.getItem() == MarsItems.deshPickSlime)
        {
            return 0.2F;
        }
        return ForgeHooks.blockStrength(this, player, world, x, y, z);
    }

    @Override
    public void onBlockExploded(World par1World, int par2, int par3, int par4, Explosion explosion)
    {
        if (!par1World.isRemote)
        {
            EntityCheeseSlime slime = new EntityCheeseSlime(par1World);
            slime.setPosition(par2 + 0.5, par3 + 1, par4 + 0.5);
            par1World.spawnEntityInWorld(slime);
            slime.setSlimeSize(par1World.rand.nextInt(4));
        }
        par1World.setBlockToAir(par2, par3, par4);
        par1World.playSoundEffect(par2, par3, par4, "mob.slime.big", 1.0F, 1.0F);
        this.onBlockDestroyedByExplosion(par1World, par2, par3, par4, explosion);
    }
}