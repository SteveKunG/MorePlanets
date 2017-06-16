/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.asteroids.darkasteroids.blocks;

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

public class BlockAlienFarmland extends BlockBaseMP
{
    private IIcon wet;
    private IIcon dry;

    public BlockAlienFarmland(String name)
    {
        super(Material.ground);
        this.setTickRandomly(true);
        this.setStepSound(Block.soundTypeGravel);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.9375F, 1.0F);
        this.setHardness(0.55F);
        this.setBlockName(name);
    }

    @Override
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return null;
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
    public IIcon getIcon(int par1, int par2)
    {
        return par1 == 1 ? par2 > 0 ? this.wet : this.dry : DarkAsteroidsBlocks.alien_dirt.getBlockTextureFromSide(par1);
    }

    @Override
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (!this.isWaterNearby(par1World, par2, par3, par4) && !par1World.canLightningStrikeAt(par2, par3 + 1, par4))
        {
            int meta = par1World.getBlockMetadata(par2, par3, par4);

            if (meta > 0)
            {
                par1World.setBlockMetadataWithNotify(par2, par3, par4, meta - 1, 2);
            }
            else if (!this.isCropsNearby(par1World, par2, par3, par4))
            {
                par1World.setBlock(par2, par3, par4, DarkAsteroidsBlocks.alien_dirt);
            }
        }
        else
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 7, 2);
        }
    }

    @Override
    public void onFallenUpon(World par1World, int par2, int par3, int par4, Entity par5Entity, float par6)
    {
        if (!par1World.isRemote && par1World.rand.nextFloat() < par6 - 0.5F)
        {
            if (!(par5Entity instanceof EntityPlayer) && !par1World.getGameRules().getGameRuleBooleanValue("mobGriefing"))
            {
                return;
            }
            par1World.setBlock(par2, par3, par4, DarkAsteroidsBlocks.alien_dirt);
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
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block par5)
    {
        super.onNeighborBlockChange(par1World, par2, par3, par4, par5);
        Block block = par1World.getBlock(par2, par3 + 1, par4);

        if (block.getMaterial().isSolid())
        {
            par1World.setBlock(par2, par3, par4, DarkAsteroidsBlocks.alien_dirt);
        }
    }

    @Override
    public Item getItemDropped(int par1, Random par2Random, int par3)
    {
        return Item.getItemFromBlock(DarkAsteroidsBlocks.alien_dirt);
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition moving, World world, int x, int y, int z)
    {
        return new ItemStack(DarkAsteroidsBlocks.alien_dirt, 1, 0);
    }

    @Override
    public boolean isFertile(World world, int x, int y, int z)
    {
        return world.getBlockMetadata(x, y, z) > 0;
    }

    @Override
    public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side)
    {
        return side != DOWN && side != UP;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.wet = par1IconRegister.registerIcon("mpcore:darkasteroids/alien_farmland_wet");
        this.dry = par1IconRegister.registerIcon("mpcore:darkasteroids/alien_farmland_dry");
    }
}