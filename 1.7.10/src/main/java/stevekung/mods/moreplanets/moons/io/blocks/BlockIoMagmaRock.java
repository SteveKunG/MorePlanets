/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.io.blocks;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import stevekung.mods.moreplanets.core.blocks.base.BlockBaseMP;

public class BlockIoMagmaRock extends BlockBaseMP
{
    private IIcon[] rockIcon = new IIcon[2];

    public BlockIoMagmaRock(String name)
    {
        super(Material.rock);
        this.setBlockName(name);
        this.setHardness(3.0F);
    }

    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.rockIcon[0] = par1IconRegister.registerIcon("io:magma_rock");
        this.rockIcon[1] = par1IconRegister.registerIcon("io:sulfur_rock");
    }

    @Override
    public IIcon getIcon(int side, int meta)
    {
        return this.rockIcon[meta];
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int par2, int par3, int par4)
    {
        if (world.getBlockMetadata(par2, par3, par4) == 0)
        {
            float f = 0.1F;
            return AxisAlignedBB.getBoundingBox(par2, par3, par4, par2 + 1, par3 + 1 - f, par4 + 1);
        }
        return super.getCollisionBoundingBoxFromPool(world, par2, par3, par4);
    }

    @Override
    public boolean isFireSource(World world, int x, int y, int z, ForgeDirection side)
    {
        if (world.getBlockMetadata(x, y, z) == 0)
        {
            if (side == ForgeDirection.UP)
            {
                return true;
            }
        }
        return super.isFireSource(world, x, y, z, side);
    }

    @Override
    public Item getItemDropped(int meta, Random par2Random, int par3)
    {
        return Item.getItemFromBlock(this);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list)
    {
        for (int i = 0; i < 2; ++i)
        {
            list.add(new ItemStack(this, 1, i));
        }
    }

    @Override
    public int damageDropped(int meta)
    {
        return meta;
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
    {
        if (entity instanceof EntityPlayer && ((EntityPlayer)entity).capabilities.isCreativeMode)
        {
            return;
        }
        if (world.getBlockMetadata(x, y, z) == 0)
        {
            entity.setFire(10);
            entity.motionX *= 0.5D;
            entity.motionZ *= 0.5D;
        }
    }

    @Override
    public void randomDisplayTick(World world, int x, int y, int z, Random random)
    {
        if (random.nextInt(1) == 0)
        {
            world.spawnParticle("smoke", x + random.nextFloat(), y + 1.1F, z + random.nextFloat(), 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int meta)
    {
        ItemStack itemStack = player.getCurrentEquippedItem();
        player.addExhaustion(0.025F);

        if (world.getBlockMetadata(x, y, z) == 0)
        {
            if (itemStack == null || !(itemStack.getItem() instanceof ItemPickaxe))
            {
                if (world.rand.nextInt(20) == 0)
                {
                    world.setBlock(x, y, z, Blocks.flowing_lava);
                }
            }
            if (itemStack != null && itemStack.getItem() instanceof ItemPickaxe)
            {
                this.dropBlockAsItem(world, x, y, z, meta, 0);
            }
        }
        super.harvestBlock(world, player, x, y, z, meta);
    }
}