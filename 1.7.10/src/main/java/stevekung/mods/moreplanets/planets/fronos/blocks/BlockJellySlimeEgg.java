/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.blocks;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import micdoodle8.mods.galacticraft.planets.mars.items.MarsItems;
import net.minecraft.block.BlockDragonEgg;
import net.minecraft.block.BlockFalling;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.planets.fronos.entities.EntityJellySlime;

public class BlockJellySlimeEgg extends BlockDragonEgg
{
    private IIcon[] textures;

    public BlockJellySlimeEgg(String name)
    {
        super();
        this.setStepSound(MorePlanetsCore.soundTypeSmallSlime);
        this.setResistance(0.0F);
        this.setHardness(-1.0F);
        this.setBlockName(name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.textures = new IIcon[this.getTypes().length];

        for (int i = 0; i < this.getTypes().length; ++i)
        {
            this.textures[i] = par1IconRegister.registerIcon("fronos:" + this.getTypes()[i]);
        }
    }

    @Override
    public void updateTick(World world, int p_149674_2_, int p_149674_3_, int p_149674_4_, Random rand)
    {
        this.func_150018_e(world, p_149674_2_, p_149674_3_, p_149674_4_);
    }

    private void func_150018_e(World world, int p_150018_2_, int p_150018_3_, int p_150018_4_)
    {
        if (BlockFalling.func_149831_e(world, p_150018_2_, p_150018_3_ - 1, p_150018_4_) && p_150018_3_ >= 0)
        {
            byte b0 = 32;

            if (!BlockFalling.fallInstantly && world.checkChunksExist(p_150018_2_ - b0, p_150018_3_ - b0, p_150018_4_ - b0, p_150018_2_ + b0, p_150018_3_ + b0, p_150018_4_ + b0))
            {
                EntityFallingBlock entityfallingblock = new EntityFallingBlock(world, p_150018_2_ + 0.5F, p_150018_3_ + 0.5F, p_150018_4_ + 0.5F, this, world.getBlockMetadata(p_150018_2_, p_150018_3_, p_150018_4_));
                world.spawnEntityInWorld(entityfallingblock);
            }
            else
            {
                world.setBlockToAir(p_150018_2_, p_150018_3_, p_150018_4_);

                while (BlockFalling.func_149831_e(world, p_150018_2_, p_150018_3_ - 1, p_150018_4_) && p_150018_3_ > 0)
                {
                    --p_150018_3_;
                }

                if (p_150018_3_ > 0)
                {
                    world.setBlock(p_150018_2_, p_150018_3_, p_150018_4_, this, world.getBlockMetadata(p_150018_2_, p_150018_3_, p_150018_4_), 2);
                }
            }
        }
    }

    @Override
    public IIcon getIcon(int side, int meta)
    {
        if (meta < 0 || meta >= this.textures.length)
        {
            meta = 0;
        }
        return this.textures[meta];
    }

    @Override
    public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list)
    {
        for (int i = 0; i < this.getTypes().length; ++i)
        {
            list.add(new ItemStack(block, 1, i));
        }
    }

    private String[] getTypes()
    {
        return new String[] { "grape_jelly_block", "raspberry_jelly_block", "strawberry_jelly_block", "berry_jelly_block", "lime_jelly_block", "orange_jelly_block", "green_jelly_block", "lemon_jelly_block" };
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
    public int damageDropped(int meta)
    {
        return meta;
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
            EntityJellySlime slime = new EntityJellySlime(par1World);
            slime.setPosition(par2 + 0.5, par3 + 1, par4 + 0.5);
            slime.setSlimeSize(par1World.rand.nextInt(2));
            slime.setTameSkin(par1World.getBlockMetadata(par2, par3, par4));
            par1World.spawnEntityInWorld(slime);
        }
        par1World.setBlockToAir(par2, par3, par4);
        par1World.playSoundEffect(par2, par3, par4, "mob.slime.big", 1.0F, 1.0F);
        this.onBlockDestroyedByExplosion(par1World, par2, par3, par4, explosion);
    }
}