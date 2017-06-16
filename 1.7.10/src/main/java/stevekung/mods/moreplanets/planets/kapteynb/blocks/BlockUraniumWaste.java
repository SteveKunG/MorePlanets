/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.kapteynb.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.init.MPPotions;
import stevekung.mods.moreplanets.planets.kapteynb.items.armor.KapteynBArmorItems;
import stevekung.mods.moreplanets.planets.kapteynb.tileentities.TileEntityUraniumWaste;

public class BlockUraniumWaste extends BlockBreakable implements IShearable, ITileEntityProvider
{
    private IIcon[] uraniumWasteIcon = new IIcon[2];

    public BlockUraniumWaste(String name)
    {
        super(name, Material.plants, false);
        this.setBlockName(name);
        this.setStepSound(Block.soundTypeGrass);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.05F, 1.0F);
    }

    @Override
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return MorePlanetsCore.mpBlocksTab;
    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z)
    {
        if (world.getBlockMetadata(x, y, z) == 0)
        {
            return 8;
        }
        else
        {
            return 0;
        }
    }

    @Override
    public boolean canSilkHarvest()
    {
        return true;
    }

    @Override
    public boolean isReplaceable(IBlockAccess world, int x, int y, int z)
    {
        return true;
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
    public int quantityDropped(int meta, int fortune, Random rand)
    {
        return 0;
    }

    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.uraniumWasteIcon[0] = par1IconRegister.registerIcon("kapteynb:uranium_waste");
        this.uraniumWasteIcon[1] = par1IconRegister.registerIcon("kapteynb:uranium_waste_inactive");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list)
    {
        for (int i = 0; i < 2; ++i)
        {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    public IIcon getIcon(int side, int meta)
    {
        return this.uraniumWasteIcon[meta];
    }

    @Override
    public Item getItemDropped(int meta, Random random, int par3)
    {
        return Item.getItemFromBlock(this);
    }

    @Override
    public int damageDropped(int meta)
    {
        return meta;
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
    public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int meta)
    {
        ItemStack equippedItem = player.getCurrentEquippedItem();

        if (equippedItem != null)
        {
            if (equippedItem.getItem() != Items.shears)
            {
                if (meta == 0)
                {
                    player.addPotionEffect(new PotionEffect(MPPotions.chemical.id, 100));
                }
                else if (meta == 1)
                {
                    return;
                }
            }
        }
        else
        {
            if (meta == 0)
            {
                player.addPotionEffect(new PotionEffect(MPPotions.chemical.id, 100));
            }
            else if (meta == 1)
            {
                return;
            }
        }
    }

    @Override
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block par5)
    {
        this.canBlockStay(par1World, par2, par3, par4);
    }

    @Override
    public boolean canBlockStay(World par1World, int par2, int par3, int par4)
    {
        if (!this.canPlaceBlockAt(par1World, par2, par3, par4))
        {
            par1World.setBlockToAir(par2, par3, par4);
            return false;
        }
        else
        {
            return true;
        }
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
    {
        TileEntity tile = world.getTileEntity(x, y, z);

        if (tile instanceof TileEntityUraniumWaste)
        {
            TileEntityUraniumWaste waste = (TileEntityUraniumWaste)tile;

            if (waste.radiationLevel <= 0)
            {
                return;
            }
            if (waste.radiationLevel > 0)
            {
                if (entity instanceof EntityLivingBase)
                {
                    if (entity instanceof EntityPlayer)
                    {
                        InventoryPlayer inventory = ((EntityPlayer)entity).inventory;

                        if (!(inventory.armorInventory[0] != null && inventory.armorInventory[0].getItem() == KapteynBArmorItems.uranium_boots))
                        {
                            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(MPPotions.chemical.id, 60));
                        }
                    }
                    else
                    {
                        ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(MPPotions.chemical.id, 60));
                    }
                }
            }
        }
    }

    @Override
    public Item getItem(World par1World, int par2, int par3, int par4)
    {
        return Item.getItemFromBlock(this);
    }

    @Override
    public int getDamageValue(World world, int x, int y, int z)
    {
        return world.getBlockMetadata(x, y, z);
    }

    @Override
    public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z)
    {
        return true;
    }

    @Override
    public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune)
    {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        ret.add(new ItemStack(this, 1, world.getBlockMetadata(x, y, z)));
        return ret;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        if (meta == 0)
        {
            return new TileEntityUraniumWaste();
        }
        return null;
    }

    public static void updateBlockState(boolean flag, World world, int x, int y, int z)
    {
        if (flag)
        {
            world.setBlock(x, y, z, KapteynBBlocks.uranium_waste, 1, 3);
        }
    }
}