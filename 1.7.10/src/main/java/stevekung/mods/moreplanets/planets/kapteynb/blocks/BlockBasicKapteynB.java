/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.kapteynb.blocks;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import micdoodle8.mods.galacticraft.api.block.IDetectableResource;
import micdoodle8.mods.galacticraft.api.block.IPlantableBlock;
import micdoodle8.mods.galacticraft.api.block.ITerraformableBlock;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import stevekung.mods.moreplanets.core.blocks.BlockBasicMP;
import stevekung.mods.moreplanets.planets.kapteynb.items.KapteynBItems;
import stevekung.mods.moreplanets.planets.kapteynb.tileentities.TileEntityKapteynBDungeonSpawner;

public class BlockBasicKapteynB extends BlockBasicMP implements IDetectableResource, IPlantableBlock, ITerraformableBlock
{
    private IIcon[] kapteynBlockIcons;

    public BlockBasicKapteynB(String name)
    {
        super(Material.rock);
        this.setTickRandomly(true);
        this.setBlockName(name);
    }

    @Override
    public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int metadata)
    {
        int meta = world.getBlockMetadata(x, y, z);

        if (meta == 10 || meta == 15)
        {
            return false;
        }
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.kapteynBlockIcons = new IIcon[16];
        this.kapteynBlockIcons[0] = par1IconRegister.registerIcon("kapteynb:kapteyn_b_surface_ice");
        this.kapteynBlockIcons[1] = par1IconRegister.registerIcon("kapteynb:kapteyn_b_sub_surface_ice");
        this.kapteynBlockIcons[2] = par1IconRegister.registerIcon("kapteynb:kapteyn_b_stone");
        this.kapteynBlockIcons[3] = par1IconRegister.registerIcon("kapteynb:kapteyn_b_cobblestone");
        this.kapteynBlockIcons[4] = par1IconRegister.registerIcon("kapteynb:namerium_ore");
        this.kapteynBlockIcons[5] = par1IconRegister.registerIcon("kapteynb:frozen_iron_ore");
        this.kapteynBlockIcons[6] = par1IconRegister.registerIcon("kapteynb:uranium_ore");
        this.kapteynBlockIcons[7] = par1IconRegister.registerIcon("kapteynb:kapteyn_b_tin_ore");
        this.kapteynBlockIcons[8] = par1IconRegister.registerIcon("kapteynb:kapteyn_b_copper_ore");
        this.kapteynBlockIcons[9] = par1IconRegister.registerIcon("kapteynb:kapteyn_b_redstone_ore");
        this.kapteynBlockIcons[10] = par1IconRegister.registerIcon("kapteynb:kapteyn_b_redstone_ore");
        this.kapteynBlockIcons[11] = par1IconRegister.registerIcon("kapteynb:namerium_block");
        this.kapteynBlockIcons[12] = par1IconRegister.registerIcon("kapteynb:frozen_iron_block");
        this.kapteynBlockIcons[13] = par1IconRegister.registerIcon("kapteynb:uranium_block");
        this.kapteynBlockIcons[14] = par1IconRegister.registerIcon("kapteynb:kapteyn_b_dungeon_brick");
        this.kapteynBlockIcons[15] = par1IconRegister.registerIcon("galacticraftcore:blank");
    }

    @Override
    public boolean isBeaconBase(IBlockAccess world, int x, int y, int z, int beaconX, int beaconY, int beaconZ)
    {
        return world.getBlockMetadata(x, y, z) == 12 || world.getBlockMetadata(x, y, z) == 13;
    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z)
    {
        int meta = world.getBlockMetadata(x, y, z);

        if (meta == 10)
        {
            return 9;
        }
        else
        {
            return 0;
        }
    }

    @Override
    public void onBlockClicked(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer)
    {
        super.onBlockClicked(par1World, par2, par3, par4, par5EntityPlayer);

        int meta = par1World.getBlockMetadata(par2, par3, par4);

        if (meta == 9)
        {
            par1World.setBlock(par2, par3, par4, KapteynBBlocks.kapteyn_b_block, 10, 3);
        }
    }

    @Override
    public void onEntityWalking(World par1World, int par2, int par3, int par4, Entity par5Entity)
    {
        super.onEntityWalking(par1World, par2, par3, par4, par5Entity);

        int meta = par1World.getBlockMetadata(par2, par3, par4);

        if (meta == 9)
        {
            par1World.setBlock(par2, par3, par4, KapteynBBlocks.kapteyn_b_block, 10, 3);
        }
    }

    @Override
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
        int meta = par1World.getBlockMetadata(par2, par3, par4);

        if (meta == 9)
        {
            par1World.setBlock(par2, par3, par4, KapteynBBlocks.kapteyn_b_block, 10, 3);
        }
        return super.onBlockActivated(par1World, par2, par3, par4, par5EntityPlayer, par6, par7, par8, par9);
    }

    @Override
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        int meta = par1World.getBlockMetadata(par2, par3, par4);

        if (meta == 10)
        {
            par1World.setBlock(par2, par3, par4, KapteynBBlocks.kapteyn_b_block, 9, 3);
        }
    }

    @Override
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        int meta = par1World.getBlockMetadata(par2, par3, par4);

        if (meta == 10)
        {
            this.sparkle(par1World, par2, par3, par4);
        }
    }

    private void sparkle(World par1World, int par2, int par3, int par4)
    {
        Random random = par1World.rand;
        double d0 = 0.0625D;

        for (int l = 0; l < 6; ++l)
        {
            double d1 = par2 + random.nextFloat();
            double d2 = par3 + random.nextFloat();
            double d3 = par4 + random.nextFloat();

            if (l == 0 && !par1World.getBlock(par2, par3 + 1, par4).isOpaqueCube())
            {
                d2 = par3 + 1 + d0;
            }
            if (l == 1 && !par1World.getBlock(par2, par3 - 1, par4).isOpaqueCube())
            {
                d2 = par3 + 0 - d0;
            }
            if (l == 2 && !par1World.getBlock(par2, par3, par4 + 1).isOpaqueCube())
            {
                d3 = par4 + 1 + d0;
            }
            if (l == 3 && !par1World.getBlock(par2, par3, par4 - 1).isOpaqueCube())
            {
                d3 = par4 + 0 - d0;
            }
            if (l == 4 && !par1World.getBlock(par2 + 1, par3, par4).isOpaqueCube())
            {
                d1 = par2 + 1 + d0;
            }
            if (l == 5 && !par1World.getBlock(par2 - 1, par3, par4).isOpaqueCube())
            {
                d1 = par2 + 0 - d0;
            }
            if (d1 < par2 || d1 > par2 + 1 || d2 < 0.0D || d2 > par3 + 1 || d3 < par4 || d3 > par4 + 1)
            {
                par1World.spawnParticle("reddust", d1, d2, d3, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    public int tickRate(World par1World)
    {
        return 30;
    }

    @Override
    public float getBlockHardness(World par1World, int par2, int par3, int par4)
    {
        int meta = par1World.getBlockMetadata(par2, par3, par4);

        if (meta == 0 || meta == 1 || meta >= 11 && meta <= 14)
        {
            return 4.0F;
        }
        if (meta == 2)
        {
            return 3.5F;
        }
        if (meta == 3)
        {
            return 3.25F;
        }
        if (meta >= 4 && meta <= 10)
        {
            return 3.75F;
        }
        return 1.0F;
    }

    @Override
    public float getExplosionResistance(Entity entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ)
    {
        return world.getBlockMetadata(x, y, z) == 14 ? 40.0F : super.getExplosionResistance(entity, world, x, y, z, explosionX, explosionY, explosionZ);
    }

    @Override
    public IIcon getIcon(int side, int meta)
    {
        return this.kapteynBlockIcons[meta];
    }

    @Override
    public Item getItemDropped(int meta, Random random, int par3)
    {
        switch (meta)
        {
        case 2:
            return Item.getItemFromBlock(KapteynBBlocks.kapteyn_b_block);
        case 4:
            return KapteynBItems.namerium_crystal;
        case 6:
            return KapteynBItems.kapteyn_b_item;
        case 9:
        case 10:
            return Items.redstone;
        default:
            return Item.getItemFromBlock(this);
        }
    }

    @Override
    public int damageDropped(int meta)
    {
        if (meta == 2)
        {
            return 3;
        }
        if (meta == 6)
        {
            return 1;
        }
        if (meta == 6)
        {
            return 1;
        }
        if (meta == 4 || meta == 9 || meta == 10)
        {
            return 0;
        }
        return meta;
    }

    @Override
    public int quantityDropped(int meta, int fortune, Random random)
    {
        if (meta == 9 || meta == 10)
        {
            return 4 + random.nextInt(2);
        }
        return super.quantityDropped(meta, fortune, random);
    }

    @Override
    public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        for (int var4 = 0; var4 < 15; ++var4)
        {
            if (var4 != 10)
            {
                par3List.add(new ItemStack(par1, 1, var4));
            }
        }
    }

    @Override
    public boolean isValueable(int metadata)
    {
        if (metadata >= 4 && metadata <= 10)
        {
            return true;
        }
        return false;
    }

    @Override
    public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plant)
    {
        int meta = world.getBlockMetadata(x, y, z);

        if (meta == 0 || meta == 1)
        {
            return true;
        }
        if (plant.getPlant(world, x, y + 1, z) instanceof BlockFlower)
        {
            return true;
        }
        return false;
    }

    @Override
    public int requiredLiquidBlocksNearby()
    {
        return 4;
    }

    @Override
    public boolean isPlantable(int meta)
    {
        if (meta == 0 || meta == 1)
        {
            return true;
        }
        return false;
    }

    @Override
    public boolean isTerraformable(World world, int x, int y, int z)
    {
        int meta = world.getBlockMetadata(x, y, z);

        if ((meta == 0 || meta == 1) && !world.getBlock(x, y + 1, z).isOpaqueCube())
        {
            return true;
        }
        return false;
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
    {
        int meta = world.getBlockMetadata(x, y, z);

        if (meta == 10)
        {
            return new ItemStack(this, 1, 9);
        }
        return super.getPickBlock(target, world, x, y, z);
    }

    @Override
    public int getDungeonSpawnerMetadata()
    {
        return 15;
    }

    @Override
    public boolean hasTileEntity(int meta)
    {
        return meta == 15;
    }

    @Override
    public TileEntity getDungeonSpawner()
    {
        return new TileEntityKapteynBDungeonSpawner();
    }
}