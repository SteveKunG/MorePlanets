/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.blocks;

import java.util.ArrayList;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.blocks.base.BlockBaseMP;
import stevekung.mods.moreplanets.planets.fronos.items.FronosItems;
import stevekung.mods.moreplanets.planets.fronos.tileentities.TileEntityCavernOysterOpen;

public class BlockCavernOysterOpen extends BlockBaseMP implements ITileEntityProvider
{
    public BlockCavernOysterOpen(String name)
    {
        super(Material.plants);
        this.setHardness(0.6F);
        this.setBlockBounds(0.225F, 0.0F, 0.225F, 0.775F, 0.275F, 0.775F);
        this.setTickRandomly(true);
        this.setBlockName(name);
        this.setBlockTextureName("fronos:cavern_oyster");
    }

    @Override
    public int getRenderType()
    {
        return MorePlanetsCore.proxy.getBlockRender(this);
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
    public int getLightValue(IBlockAccess world, int x, int y, int z)
    {
        return 4;
    }

    @Override
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block par5)
    {
        this.canBlockStay(par1World, par2, par3, par4);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random rand)
    {
        for (int i = 0; i < 4; i++)
        {
            double d1 = x + rand.nextFloat();
            double d2 = y + rand.nextFloat();
            double d3 = z + rand.nextFloat();
            double d4 = 0.0D;
            double d5 = 0.0D;
            double d6 = 0.0D;
            d4 = (rand.nextFloat() - 0.5D) * 0.5D;
            d5 = (rand.nextFloat() - 0.5D) * 0.5D;
            d6 = (rand.nextFloat() - 0.5D) * 0.5D;
            MorePlanetsCore.proxy.spawnMotionParticle("cavernOyster", d1, d2, d3, d4, d5, d6);
        }
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        world.setBlock(x, y, z, FronosBlocks.cavern_oyster_close, world.getBlockMetadata(x, y, z), 3);
        EntityItem entityitem = new EntityItem(world, x, y, z, new ItemStack(FronosItems.pearl, 1, 1));

        if (!world.isRemote && world.rand.nextInt(20) == 0)
        {
            world.spawnEntityInWorld(entityitem);
        }
        return true;
    }

    @Override
    public boolean canBlockStay(World par1World, int par2, int par3, int par4)
    {
        if (!this.canPlaceBlockAt(par1World, par2, par3, par4))
        {
            this.dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
            par1World.setBlockToAir(par2, par3, par4);
            return false;
        }
        else
        {
            return true;
        }
    }

    @Override
    public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
    {
        Block block = par1World.getBlock(par2, par3 - 1, par4);

        if (block == null)
        {
            return false;
        }
        if (!block.isLeaves(par1World, par2, par3 - 1, par4) && !block.isOpaqueCube())
        {
            return false;
        }
        return par1World.getBlock(par2, par3 - 1, par4).getMaterial().blocksMovement();
    }

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
    {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        int count = this.quantityDropped(metadata, fortune, world.rand);

        for (int i = 0; i < count; i++)
        {
            if (world.rand.nextInt(20) == 0)
            {
                ret.add(new ItemStack(FronosItems.pearl, 1, 1));
            }
            else
            {
                ret.add(new ItemStack(this, 1, 0));
            }
        }
        return ret;
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack)
    {
        int angle = MathHelper.floor_double(entityLiving.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
        byte change = 0;

        switch (angle)
        {
        case 0:
            change = 0;
            break;
        case 1:
            change = 3;
            break;
        case 2:
            change = 1;
            break;
        case 3:
            change = 2;
            break;
        }
        world.setBlockMetadataWithNotify(x, y, z, change, 3);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TileEntityCavernOysterOpen();
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
    {
        return new ItemStack(this, 1, 0);
    }
}