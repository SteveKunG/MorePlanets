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
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.blocks.BlockFlowerMP;

public class BlockDandelion extends BlockFlowerMP
{
    private static String[] plants = new String[] {
            "orange_dandelion_0",
            "pink_dandelion_0",
            "purple_dandelion_0",
            "orange_dandelion_1",
            "pink_dandelion_1",
            "purple_dandelion_1"
    };

    private IIcon[] textures;

    public BlockDandelion(String name)
    {
        super(Material.plants);
        this.setTickRandomly(true);
        this.setStepSound(Block.soundTypeGrass);
        this.setBlockBounds(0.3F, 0.0F, 0.3F, 0.7F, 0.6F, 0.7F);
        this.setBlockName(name);
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.textures = new IIcon[BlockDandelion.plants.length];

        for (int i = 0; i < BlockDandelion.plants.length; ++i)
        {
            this.textures[i] = iconRegister.registerIcon("fronos:" + BlockDandelion.plants[i]);
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
    public void updateTick(World world, int x, int y, int z, Random rand)
    {
        super.updateTick(world, x, y, z, rand);
        int meta = world.getBlockMetadata(x, y, z);

        if (!world.isRemote)
        {
            if (world.isDaytime() && !world.isRaining())
            {
                if (meta == 0)
                {
                    world.setBlock(x, y, z, this, 3, 3);
                }
                if (meta == 1)
                {
                    world.setBlock(x, y, z, this, 4, 3);
                }
                if (meta == 2)
                {
                    world.setBlock(x, y, z, this, 5, 3);
                }
            }
            if (world.isRaining())
            {
                if (rand.nextInt(20) == 0)
                {
                    if (meta == 3)
                    {
                        world.setBlock(x, y, z, this, 0, 3);
                    }
                    if (meta == 4)
                    {
                        world.setBlock(x, y, z, this, 1, 3);
                    }
                    if (meta == 5)
                    {
                        world.setBlock(x, y, z, this, 2, 3);
                    }
                }
            }
        }
    }

    @Override
    public void randomDisplayTick(World world, int x, int y, int z, Random rand)
    {
        super.randomDisplayTick(world, x, y, z, rand);
        int meta = world.getBlockMetadata(x, y, z);

        if (meta == 3)
        {
            if (rand.nextInt(20) == 0)
            {
                MorePlanetsCore.proxy.spawnParticle("orangeDandelion", x + rand.nextFloat(), y + rand.nextFloat(), z + rand.nextFloat());
            }
            else if (rand.nextInt(2) == 0)
            {
                if (world.isRaining())
                {
                    MorePlanetsCore.proxy.spawnParticle("orangeDandelion", x + rand.nextFloat(), y + rand.nextFloat(), z + rand.nextFloat());
                }
            }
        }
        else if (meta == 4)
        {
            if (rand.nextInt(20) == 0)
            {
                MorePlanetsCore.proxy.spawnParticle("pinkDandelion", x + rand.nextFloat(), y + rand.nextFloat(), z + rand.nextFloat());
            }
            else if (rand.nextInt(2) == 0)
            {
                if (world.isRaining())
                {
                    MorePlanetsCore.proxy.spawnParticle("pinkDandelion", x + rand.nextFloat(), y + rand.nextFloat(), z + rand.nextFloat());
                }
            }
        }
        else if (meta == 5)
        {
            if (rand.nextInt(20) == 0)
            {
                MorePlanetsCore.proxy.spawnParticle("purpleDandelion", x + rand.nextFloat(), y + rand.nextFloat(), z + rand.nextFloat());
            }
            else if (rand.nextInt(2) == 0)
            {
                if (world.isRaining())
                {
                    MorePlanetsCore.proxy.spawnParticle("purpleDandelion", x + rand.nextFloat(), y + rand.nextFloat(), z + rand.nextFloat());
                }
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list)
    {
        for (int i = 0; i < BlockDandelion.plants.length; ++i)
        {
            list.add(new ItemStack(block, 1, i));
        }
    }

    @Override
    public Item getItemDropped(int meta, Random rand, int fortune)
    {
        return Item.getItemFromBlock(this);
    }

    @Override
    public int damageDropped(int meta)
    {
        return meta;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        int meta = world.getBlockMetadata(x, y, z);

        if (player.isPotionActive(Potion.regeneration.id))
        {
            return false;
        }
        if (meta == 3)
        {
            world.setBlock(x, y, z, this, 0, 3);
            player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 120, 1));
            return true;
        }
        else if (meta == 4)
        {
            world.setBlock(x, y, z, this, 1, 3);
            player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 120, 1));
            return true;
        }
        else if (meta == 5)
        {
            world.setBlock(x, y, z, this, 2, 3);
            player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 120, 1));
            return true;
        }
        return false;
    }

    @Override
    public boolean isValidPosition(World world, int x, int y, int z, int metadata)
    {
        Block block = world.getBlock(x, y - 1, z);
        return block instanceof IFronosGrass || block == FronosBlocks.fronos_dirt;
    }

    @Override
    public int getDamageValue(World world, int x, int y, int z)
    {
        return world.getBlockMetadata(x, y, z);
    }
}