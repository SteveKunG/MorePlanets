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
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
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
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.blocks.BlockFlowerMP;
import stevekung.mods.moreplanets.core.util.DamageSourceMP;
import stevekung.mods.moreplanets.planets.fronos.worldgen.feature.WorldGenBigSkyMushroom;

public class BlockFronosFlower extends BlockFlowerMP implements IGrowable
{
    private static String[] plants = new String[] {
            "pink_butter_cup",
            "orange_butterfly_flower",
            "yellow_milk_cap",
            "little_sun_flower",
            "sky_mushroom",
            "purple_spike_flower",
            "jungle_iris",
            "blue_poison_mushroom",
            "white_moss"
    };

    private IIcon[] textures;

    public BlockFronosFlower(String name)
    {
        super(Material.plants);
        this.setTickRandomly(true);
        float var4 = 0.2F;
        this.setStepSound(Block.soundTypeGrass);
        this.setBlockName(name);
        this.setBlockBounds(0.5F - var4, 0.0F, 0.5F - var4, 0.5F + var4, var4 * 3.0F, 0.5F + var4);
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand)
    {
        if (world.getBlockMetadata(x, y, z) == 4)
        {
            if (rand.nextInt(25) == 0)
            {
                byte b0 = 4;
                int l = 5;
                int i1;
                int j1;
                int k1;

                for (i1 = x - b0; i1 <= x + b0; ++i1)
                {
                    for (j1 = z - b0; j1 <= z + b0; ++j1)
                    {
                        for (k1 = y - 1; k1 <= y + 1; ++k1)
                        {
                            if (world.getBlock(i1, k1, j1) == this && world.getBlockMetadata(x, y, z) == 4)
                            {
                                --l;

                                if (l <= 0)
                                {
                                    return;
                                }
                            }
                        }
                    }
                }

                i1 = x + rand.nextInt(3) - 1;
                j1 = y + rand.nextInt(2) - rand.nextInt(2);
                k1 = z + rand.nextInt(3) - 1;

                for (int l1 = 0; l1 < 4; ++l1)
                {
                    if (world.isAirBlock(i1, j1, k1) && this.isValidPosition(world, i1, j1, k1, 4))
                    {
                        x = i1;
                        y = j1;
                        z = k1;
                    }
                    i1 = x + rand.nextInt(3) - 1;
                    j1 = y + rand.nextInt(2) - rand.nextInt(2);
                    k1 = z + rand.nextInt(3) - 1;
                }

                if (world.isAirBlock(i1, j1, k1) && this.isValidPosition(world, i1, j1, k1, 4))
                {
                    world.setBlock(i1, j1, k1, this, 4, 2);
                }
            }
        }
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.textures = new IIcon[BlockFronosFlower.plants.length];

        for (int i = 0; i < BlockFronosFlower.plants.length; ++i)
        {
            this.textures[i] = iconRegister.registerIcon("fronos:" + BlockFronosFlower.plants[i]);
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
    public int getRenderType()
    {
        return 1;
    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z)
    {
        int meta = world.getBlockMetadata(x, y, z);

        if (meta == 2 || meta == 5)
        {
            return 4;
        }
        else if (meta == 7)
        {
            return 2;
        }
        return 0;
    }

    @Override
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return MorePlanetsCore.mpBlocksTab;
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int par2, int par3, int par4)
    {
        int meta = world.getBlockMetadata(par2, par3, par4);

        switch (meta)
        {
        case 2:
            this.setBlockBounds(0.3F, 0.0F, 0.3F, 0.7F, 0.5F, 0.7F);
            break;
        case 3:
            this.setBlockBounds(0.3F, 0.0F, 0.3F, 0.7F, 0.8F, 0.7F);
            break;
        case 5:
            this.setBlockBounds(0.2F, 0.0F, 0.2F, 0.8F, 0.75F, 0.8F);
            break;
        case 6:
            this.setBlockBounds(0.15F, 0.0F, 0.15F, 0.85F, 0.95F, 0.85F);
            break;
        case 4:
        case 7:
            this.setBlockBounds(0.3F, 0.0F, 0.3F, 0.7F, 0.45F, 0.7F);
            break;
        case 8:
            this.setBlockBounds(0.3F, 0.0F, 0.3F, 0.7F, 0.95F, 0.7F);
            break;
        default:
            this.setBlockBounds(0.1F, 0.0F, 0.1F, 0.9F, 0.8F, 0.9F);
            break;
        }
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
    {
        int meta = world.getBlockMetadata(x, y, z);

        if (meta == 5)
        {
            if (entity instanceof EntityLivingBase)
            {
                if (entity instanceof EntityPlayer)
                {
                    InventoryPlayer inventory = ((EntityPlayer)entity).inventory;

                    if (!(inventory.armorInventory[0] != null && inventory.armorInventory[0].getItem() == Items.leather_boots && inventory.armorInventory[1] != null && inventory.armorInventory[1].getItem() == Items.leather_leggings))
                    {
                        entity.attackEntityFrom(DamageSourceMP.purpleSpike, (int) (4.0D * 0.15 + 1.0D));
                    }
                }
                else
                {
                    entity.attackEntityFrom(DamageSourceMP.purpleSpike, (int) (4.0D * 0.15 + 1.0D));
                }
            }
        }
        else if (meta == 7)
        {
            if (entity instanceof EntityLivingBase)
            {
                if (entity instanceof EntityPlayer)
                {
                    InventoryPlayer inventory = ((EntityPlayer)entity).inventory;

                    if (!(inventory.armorInventory[0] != null && inventory.armorInventory[0].getItem() == Items.leather_boots && inventory.armorInventory[1] != null && inventory.armorInventory[1].getItem() == Items.leather_leggings))
                    {
                        ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.poison.id, 120));
                    }
                }
                else
                {
                    ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.poison.id, 120));
                }
            }
        }
    }

    @Override
    public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int meta)
    {
        super.harvestBlock(world, player, x, y, z, meta);

        ItemStack equippedItem = player.getCurrentEquippedItem();

        if (equippedItem != null)
        {
            if (equippedItem.getItem() != Items.shears)
            {
                if (meta == 5)
                {
                    player.attackEntityFrom(DamageSourceMP.purpleSpike, (int) (4.0D * 0.15 + 1.0D));
                }
                else if (meta == 7)
                {
                    player.addPotionEffect(new PotionEffect(Potion.poison.id, 100));
                }
            }
        }
        else
        {
            if (meta == 5)
            {
                player.attackEntityFrom(DamageSourceMP.purpleSpike, (int) (4.0D * 0.15 + 1.0D));
            }
            else if (meta == 7)
            {
                player.addPotionEffect(new PotionEffect(Potion.poison.id, 100));
            }
        }
    }

    @Override
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random rand)
    {
        int meta = par1World.getBlockMetadata(par2, par3, par4);

        if (meta == 5)
        {
            if (rand.nextInt(1) == 0)
            {
                MorePlanetsCore.proxy.spawnParticle("purpleSpike", par2 + rand.nextFloat(), par3 + rand.nextFloat(), par4 + rand.nextFloat());
            }
        }
        else if (meta == 6)
        {
            if (rand.nextInt(5) == 0)
            {
                for (int i = 0; i < 1; i++)
                {
                    double d0 = rand.nextGaussian() * 0.02D;
                    double d1 = rand.nextGaussian() * 0.02D;
                    double d2 = rand.nextGaussian() * 0.02D;
                    MorePlanetsCore.proxy.spawnMotionParticle("jungleIris", par2 + rand.nextFloat(), par3 + rand.nextFloat() * 1.0D, par4 + rand.nextFloat(), d0, d1, d2);
                }
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list)
    {
        for (int i = 0; i < BlockFronosFlower.plants.length; ++i)
        {
            list.add(new ItemStack(block, 1, i));
        }
    }

    @Override
    public boolean isValidPosition(World world, int x, int y, int z, int metadata)
    {
        Block block = world.getBlock(x, y - 1, z);
        int meta = world.getBlockMetadata(x, y - 1, z);

        switch (metadata)
        {
        case 4:
            return block == FronosBlocks.fronos_block;
        case 7:
            return block == FronosBlocks.fronos_block || block instanceof IFronosGrass || block == FronosBlocks.fronos_dirt;
        case 8:
            return block == FronosBlocks.fronos_sand && meta == 1 || block == FronosBlocks.plains_grass || block == FronosBlocks.fronos_dirt;
        default:
            return block instanceof IFronosGrass || block == FronosBlocks.fronos_dirt;
        }
    }

    @Override
    public int getDamageValue(World world, int x, int y, int z)
    {
        return world.getBlockMetadata(x, y, z);
    }

    @Override
    public int damageDropped(int meta)
    {
        return meta;
    }

    @Override
    public boolean func_149851_a(World world, int x, int y, int z, boolean p_149851_5_)
    {
        return world.getBlockMetadata(x, y, z) == 4 ? true : false;
    }

    @Override
    public boolean func_149852_a(World world, Random rand, int x, int y, int z)
    {
        return world.getBlockMetadata(x, y, z) == 4 ? rand.nextFloat() < 0.4D : false;
    }

    @Override
    public void func_149853_b(World world, Random rand, int x, int y, int z)
    {
        if (world.getBlockMetadata(x, y, z) == 4)
        {
            this.func_149884_c(world, x, y, z, rand);
        }
    }

    public boolean func_149884_c(World world, int x, int y, int z, Random rand)
    {
        world.setBlockToAir(x, y, z);
        WorldGenBigSkyMushroom worldgenbigmushroom = new WorldGenBigSkyMushroom();

        if (worldgenbigmushroom != null && worldgenbigmushroom.generate(world, rand, x, y, z))
        {
            return true;
        }
        else
        {
            world.setBlock(x, y, z, this, 4, 3);
            return false;
        }
    }
}