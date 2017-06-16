/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.blocks;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.blocks.base.BlockContainerMP;
import stevekung.mods.moreplanets.planets.fronos.tileentities.TileEntityCandyExtractor;

public class BlockCandyExtractor extends BlockContainerMP
{
    private Random extractorRand = new Random();
    private boolean isActive;
    private static boolean keepExtractorInventory;

    private IIcon extractorSide;
    private IIcon extractorFace;
    private IIcon extractorTop;
    private IIcon extractorBottom;

    public BlockCandyExtractor(String name, boolean active)
    {
        super(Material.rock);
        this.isActive = active;
        this.setHardness(4.0F);
        this.setBlockName(name);

        if (this.isActive)
        {
            this.setLightLevel(1.0F);
        }
    }

    @Override
    public Item getItemDropped(int par1, Random par2Random, int par3)
    {
        return Item.getItemFromBlock(FronosBlocks.candy_extractor_idle);
    }

    @Override
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        if (!this.isActive)
        {
            return MorePlanetsCore.mpBlocksTab;
        }
        return null;
    }

    @Override
    public void onBlockAdded(World world, int par2, int par3, int par4)
    {
        super.onBlockAdded(world, par2, par3, par4);
        this.setDefaultDirection(world, par2, par3, par4);
    }

    private void setDefaultDirection(World world, int par2, int par3, int par4)
    {
        if (!world.isRemote)
        {
            Block l = world.getBlock(par2, par3, par4 - 1);
            Block i1 = world.getBlock(par2, par3, par4 + 1);
            Block j1 = world.getBlock(par2 - 1, par3, par4);
            Block k1 = world.getBlock(par2 + 1, par3, par4);
            byte b0 = 3;

            if (l.func_149730_j() && !i1.func_149730_j())
            {
                b0 = 3;
            }
            if (i1.func_149730_j() && !l.func_149730_j())
            {
                b0 = 2;
            }
            if (j1.func_149730_j() && !k1.func_149730_j())
            {
                b0 = 5;
            }
            if (k1.func_149730_j() && !j1.func_149730_j())
            {
                b0 = 4;
            }
            world.setBlockMetadataWithNotify(par2, par3, par4, b0, 2);
        }
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.extractorBottom = iconRegister.registerIcon("fronos:extractor_bottom");
        this.extractorTop = iconRegister.registerIcon("fronos:extractor_top");
        this.extractorSide = iconRegister.registerIcon("fronos:extractor_side");
        this.extractorFace = iconRegister.registerIcon(this.isActive ? "fronos:extractor_front_on" : "fronos:extractor_front_off");
    }

    @Override
    public boolean onBlockActivated(World world, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
        if (world.isRemote)
        {
            return true;
        }
        else
        {
            TileEntityCandyExtractor tileentityfurnace = (TileEntityCandyExtractor)world.getTileEntity(par2, par3, par4);

            if (tileentityfurnace != null)
            {
                par5EntityPlayer.openGui(MorePlanetsCore.instance, -1, world, par2, par3, par4);
            }
            return true;
        }
    }

    public static void updateExtractorBlockState(boolean par0, World world, int par2, int par3, int par4)
    {
        int l = world.getBlockMetadata(par2, par3, par4);
        TileEntity tileentity = world.getTileEntity(par2, par3, par4);
        BlockCandyExtractor.keepExtractorInventory = true;

        if (par0)
        {
            world.setBlock(par2, par3, par4, FronosBlocks.candy_extractor_active);
        }
        else
        {
            world.setBlock(par2, par3, par4, FronosBlocks.candy_extractor_idle);
        }

        BlockCandyExtractor.keepExtractorInventory = false;
        world.setBlockMetadataWithNotify(par2, par3, par4, l, 2);

        if (tileentity != null)
        {
            tileentity.validate();
            world.setTileEntity(par2, par3, par4, tileentity);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int par2, int par3, int par4, Random rand)
    {
        if (this.isActive)
        {
            int l = world.getBlockMetadata(par2, par3, par4);
            float f = par2 + 0.5F;
            float f1 = par3 + 0.0F + rand.nextFloat() * 6.0F / 16.0F;
            float f2 = par4 + 0.5F;
            float f3 = 0.52F;
            float f4 = rand.nextFloat() * 0.6F - 0.3F;

            if (l == 4)
            {
                world.spawnParticle("smoke", f - f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
                MorePlanetsCore.proxy.spawnParticle("blueFlame", f - f3, f1, f2 + f4);
            }
            else if (l == 5)
            {
                world.spawnParticle("smoke", f + f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
                MorePlanetsCore.proxy.spawnParticle("blueFlame", f + f3, f1, f2 + f4);
            }
            else if (l == 2)
            {
                world.spawnParticle("smoke", f + f4, f1, f2 - f3, 0.0D, 0.0D, 0.0D);
                MorePlanetsCore.proxy.spawnParticle("blueFlame", f + f4, f1, f2 - f3);
            }
            else if (l == 3)
            {
                world.spawnParticle("smoke", f + f4, f1, f2 + f3, 0.0D, 0.0D, 0.0D);
                MorePlanetsCore.proxy.spawnParticle("blueFlame", f + f4, f1, f2 + f3);
            }
        }
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TileEntityCandyExtractor();
    }

    @Override
    public void onBlockPlacedBy(World world, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
    {
        int l = MathHelper.floor_double(par5EntityLivingBase.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

        if (l == 0)
        {
            world.setBlockMetadataWithNotify(par2, par3, par4, 2, 2);
        }
        if (l == 1)
        {
            world.setBlockMetadataWithNotify(par2, par3, par4, 5, 2);
        }
        if (l == 2)
        {
            world.setBlockMetadataWithNotify(par2, par3, par4, 3, 2);
        }
        if (l == 3)
        {
            world.setBlockMetadataWithNotify(par2, par3, par4, 4, 2);
        }
        if (par6ItemStack.hasDisplayName())
        {
            ((TileEntityCandyExtractor)world.getTileEntity(par2, par3, par4)).setGuiDisplayName(par6ItemStack.getDisplayName());
        }
    }

    @Override
    public void breakBlock(World world, int par2, int par3, int par4, Block par5, int par6)
    {
        if (!BlockCandyExtractor.keepExtractorInventory)
        {
            TileEntityCandyExtractor tileentityfurnace = (TileEntityCandyExtractor)world.getTileEntity(par2, par3, par4);

            if (tileentityfurnace != null)
            {
                for (int j1 = 0; j1 < tileentityfurnace.getSizeInventory(); ++j1)
                {
                    ItemStack itemstack = tileentityfurnace.getStackInSlot(j1);

                    if (itemstack != null)
                    {
                        float f = this.extractorRand.nextFloat() * 0.8F + 0.1F;
                        float f1 = this.extractorRand.nextFloat() * 0.8F + 0.1F;
                        float f2 = this.extractorRand.nextFloat() * 0.8F + 0.1F;

                        while (itemstack.stackSize > 0)
                        {
                            int k1 = this.extractorRand.nextInt(21) + 10;

                            if (k1 > itemstack.stackSize)
                            {
                                k1 = itemstack.stackSize;
                            }

                            itemstack.stackSize -= k1;
                            EntityItem entityitem = new EntityItem(world, par2 + f, par3 + f1, par4 + f2, new ItemStack(itemstack.getItem(), k1, itemstack.getItemDamage()));

                            if (itemstack.hasTagCompound())
                            {
                                entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
                            }

                            float f3 = 0.05F;
                            entityitem.motionX = (float)this.extractorRand.nextGaussian() * f3;
                            entityitem.motionY = (float)this.extractorRand.nextGaussian() * f3 + 0.2F;
                            entityitem.motionZ = (float)this.extractorRand.nextGaussian() * f3;
                            world.spawnEntityInWorld(entityitem);
                        }
                    }
                }
                world.func_147453_f(par2, par3, par4, par5);
            }
        }
        super.breakBlock(world, par2, par3, par4, par5, par6);
    }

    @Override
    public boolean hasComparatorInputOverride()
    {
        return true;
    }

    @Override
    public int getComparatorInputOverride(World world, int par2, int par3, int par4, int par5)
    {
        return Container.calcRedstoneFromInventory((IInventory)world.getTileEntity(par2, par3, par4));
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ItemStack getPickBlock(MovingObjectPosition mov, World world, int par2, int par3, int par4)
    {
        return new ItemStack(FronosBlocks.candy_extractor_idle, 1, 0);
    }

    @Override
    public IIcon getIcon(int side, int metadata)
    {
        if (side == 0)
        {
            return this.extractorBottom;
        }
        if (side == 1)
        {
            return this.extractorTop;
        }
        if (metadata == 2 && side == 2 || metadata == 3 && side == 3 || metadata == 4 && side == 4 || metadata == 5 && side == 5 || metadata == 0 && side == 4)
        {
            return this.extractorFace;
        }
        return this.extractorSide;
    }
}