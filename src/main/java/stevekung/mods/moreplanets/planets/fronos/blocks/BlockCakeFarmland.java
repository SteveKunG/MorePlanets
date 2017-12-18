/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.blocks;

import static net.minecraftforge.common.util.ForgeDirection.DOWN;
import static net.minecraftforge.common.util.ForgeDirection.UP;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import stevekung.mods.moreplanets.core.blocks.base.BlockBaseMP;

public class BlockCakeFarmland extends BlockBaseMP
{
    private IIcon[] wet;
    private IIcon[] dry;
    private IIcon[] bottom;

    //meta 0 = cake, 1 = white cake, 2 = chocolate cake
    //meta 3 = cakefarm, 4 = whitecakefarm, 5 = chocolatecakefarm

    public BlockCakeFarmland(String name)
    {
        super(Material.ground);
        this.setTickRandomly(true);
        this.setStepSound(soundTypeCloth);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.9375F, 1.0F);
        this.setHardness(0.55F);
        this.setBlockName(name);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int par2, int par3, int par4)
    {
        float f = 0.0F;
        return AxisAlignedBB.getBoundingBox(par2, par3, par4, par2 + 1, par3 + 1 - f, par4 + 1);
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
    public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plant)
    {
        return true;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta)
    {
        if (meta == 0)
        {
            return side == 1 ? this.dry[0] : this.bottom[0];
        }
        else if (meta == 1)
        {
            return side == 1 ? this.dry[1] : this.bottom[1];
        }
        else if (meta == 2)
        {
            return side == 1 ? this.dry[2] : this.bottom[2];
        }
        else if (meta == 3)
        {
            return side == 1 ? this.wet[0] : this.bottom[0];
        }
        else if (meta == 4)
        {
            return side == 1 ? this.wet[1] : this.bottom[1];
        }
        else if (meta == 5)
        {
            return side == 1 ? this.wet[2] : this.bottom[2];
        }
        return this.blockIcon;
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random par5Random)
    {
        if (!this.isWaterNearby(world, x, y, z) && !world.canLightningStrikeAt(x, y + 1, z))
        {
            int meta = world.getBlockMetadata(x, y, z);

            if (meta == 3)
            {
                world.setBlockMetadataWithNotify(x, y, z, 0, 2);
            }
            else if (meta == 4)
            {
                world.setBlockMetadataWithNotify(x, y, z, 1, 2);
            }
            else if (meta == 5)
            {
                world.setBlockMetadataWithNotify(x, y, z, 2, 2);
            }
            else if (!this.isCropsNearby(world, x, y, z))
            {
                if (world.getBlockMetadata(x, y, z) == 0 || world.getBlockMetadata(x, y, z) == 3)
                {
                    meta = 0;
                }
                else if (world.getBlockMetadata(x, y, z) == 1 || world.getBlockMetadata(x, y, z) == 4)
                {
                    meta = 1;
                }
                else if (world.getBlockMetadata(x, y, z) == 2 || world.getBlockMetadata(x, y, z) == 5)
                {
                    meta = 2;
                }
                world.setBlock(x, y, z, FronosBlocks.frosted_cake, meta, 3);
            }
        }
        else
        {
            int meta = world.getBlockMetadata(x, y, z);

            if (meta == 0)
            {
                world.setBlockMetadataWithNotify(x, y, z, 3, 2);
            }
            else if (meta == 1)
            {
                world.setBlockMetadataWithNotify(x, y, z, 4, 2);
            }
            else if (meta == 2)
            {
                world.setBlockMetadataWithNotify(x, y, z, 5, 2);
            }
        }
    }

    @Override
    public void onFallenUpon(World world, int x, int y, int z, Entity par5Entity, float par6)
    {
        int meta = 0;

        if (world.getBlockMetadata(x, y, z) == 0 || world.getBlockMetadata(x, y, z) == 3)
        {
            meta = 0;
        }
        else if (world.getBlockMetadata(x, y, z) == 1 || world.getBlockMetadata(x, y, z) == 4)
        {
            meta = 1;
        }
        else if (world.getBlockMetadata(x, y, z) == 2 || world.getBlockMetadata(x, y, z) == 5)
        {
            meta = 2;
        }

        if (!world.isRemote && world.rand.nextFloat() < par6 - 0.5F)
        {
            if (!(par5Entity instanceof EntityPlayer) && !world.getGameRules().getGameRuleBooleanValue("mobGriefing"))
            {
                return;
            }
            world.setBlock(x, y, z, FronosBlocks.frosted_cake, meta, 3);
        }
    }

    private boolean isCropsNearby(World par1World, int par2, int par3, int par4)
    {
        byte b0 = 0;

        for (int l = par2 - b0; l <= par2 + b0; ++l)
        {
            for (int i1 = par4 - b0; i1 <= par4 + b0; ++i1)
            {
                Block plant = par1World.getBlock(l, par3 + 1, i1);

                if (plant instanceof IPlantable && this.canSustainPlant(par1World, par2, par3, par4, ForgeDirection.UP, (IPlantable)plant))
                {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isWaterNearby(World par1World, int par2, int par3, int par4)
    {
        for (int l = par2 - 4; l <= par2 + 4; ++l)
        {
            for (int i1 = par3; i1 <= par3 + 1; ++i1)
            {
                for (int j1 = par4 - 4; j1 <= par4 + 4; ++j1)
                {
                    if (par1World.getBlock(l, i1, j1).getMaterial() == Material.water)
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
    {
        int meta = 0;

        if (world.getBlockMetadata(x, y, z) == 0 || world.getBlockMetadata(x, y, z) == 3)
        {
            meta = 0;
        }
        else if (world.getBlockMetadata(x, y, z) == 1 || world.getBlockMetadata(x, y, z) == 4)
        {
            meta = 1;
        }
        else if (world.getBlockMetadata(x, y, z) == 2 || world.getBlockMetadata(x, y, z) == 5)
        {
            meta = 2;
        }

        if (world.getBlock(x, y + 1, z).getMaterial().isSolid())
        {
            world.setBlock(x, y, z, FronosBlocks.frosted_cake, meta, 3);
        }
    }

    @Override
    public Item getItemDropped(int par1, Random par2Random, int par3)
    {
        return Item.getItemFromBlock(FronosBlocks.frosted_cake);
    }

    @Override
    public int damageDropped(int meta)
    {
        if (meta == 0 || meta == 3)
        {
            meta = 0;
        }
        else if (meta == 1 || meta == 4)
        {
            meta = 1;
        }
        else if (meta == 2 || meta == 5)
        {
            meta = 2;
        }
        return meta;
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition moving, World world, int x, int y, int z)
    {
        int meta = 0;

        if (world.getBlockMetadata(x, y, z) == 0 || world.getBlockMetadata(x, y, z) == 3)
        {
            meta = 0;
        }
        else if (world.getBlockMetadata(x, y, z) == 1 || world.getBlockMetadata(x, y, z) == 4)
        {
            meta = 1;
        }
        else if (world.getBlockMetadata(x, y, z) == 2 || world.getBlockMetadata(x, y, z) == 5)
        {
            meta = 2;
        }
        return new ItemStack(FronosBlocks.frosted_cake, 1, meta);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.bottom = new IIcon[3];
        this.bottom[0] = par1IconRegister.registerIcon("fronos:cake_bread");
        this.bottom[1] = par1IconRegister.registerIcon("fronos:white_cake_bread");
        this.bottom[2] = par1IconRegister.registerIcon("fronos:chocolate_cake_bread");

        this.wet = new IIcon[3];
        this.wet[0] = par1IconRegister.registerIcon("fronos:cake_farmland_wet");
        this.wet[1] = par1IconRegister.registerIcon("fronos:white_cake_farmland_wet");
        this.wet[2] = par1IconRegister.registerIcon("fronos:chocolate_cake_farmland_wet");

        this.dry = new IIcon[3];
        this.dry[0] = par1IconRegister.registerIcon("fronos:cake_farmland_dry");
        this.dry[1] = par1IconRegister.registerIcon("fronos:white_cake_farmland_dry");
        this.dry[2] = par1IconRegister.registerIcon("fronos:chocolate_cake_farmland_dry");
    }

    @Override
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return null;
    }

    @Override
    public boolean isFertile(World world, int x, int y, int z)
    {
        return world.getBlockMetadata(x, y, z) >= 3;
    }

    @Override
    public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side)
    {
        return side != DOWN && side != UP;
    }
}