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
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.blocks.base.BlockBaseMP;
import stevekung.mods.moreplanets.planets.fronos.entities.EntityCreamGolem;

public class BlockGolemCreamHead extends BlockBaseMP
{
    private IIcon[] creamBlockIcon;

    protected BlockGolemCreamHead(String name)
    {
        super(Material.plants);
        this.setHardness(0.4F);
        this.setTickRandomly(true);
        this.setBlockBounds(0.2F, 0.2F, 0.2F, 0.8F, 0.8F, 0.8F);
        this.setStepSound(Block.soundTypeSnow);
        this.setBlockName(name);
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public Item getItemDropped(int meta, Random par2Random, int par3)
    {
        return Item.getItemFromBlock(this);
    }

    @Override
    public int damageDropped(int meta)
    {
        return meta;
    }

    @Override
    public int getDamageValue(World world, int x, int y, int z)
    {
        return world.getBlockMetadata(x, y, z);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list)
    {
        for (int i = 0; i < 6; i++)
        {
            list.add(new ItemStack(block, 1, i));
        }
    }

    @Override
    public IIcon getIcon(int side, int meta)
    {
        return this.creamBlockIcon[meta];
    }

    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.creamBlockIcon = new IIcon[6];
        this.creamBlockIcon[0] = par1IconRegister.registerIcon("fronos:vanilla_cream");
        this.creamBlockIcon[1] = par1IconRegister.registerIcon("fronos:chocolate_cream");
        this.creamBlockIcon[2] = par1IconRegister.registerIcon("fronos:strawberry_cream");
        this.creamBlockIcon[3] = par1IconRegister.registerIcon("fronos:orange_cream");
        this.creamBlockIcon[4] = par1IconRegister.registerIcon("fronos:tea_cream");
        this.creamBlockIcon[5] = par1IconRegister.registerIcon("fronos:lemon_cream");
    }

    @Override
    public void onBlockAdded(World world, int x, int y, int z)
    {
        super.onBlockAdded(world, x, y, z);
        Block block = world.getBlock(x, y - 1, z);
        Block block2 = world.getBlock(x, y - 2, z);
        int meta = world.getBlockMetadata(x, y, z);
        int meta1 = world.getBlockMetadata(x, y - 1, z);
        int meta2 = world.getBlockMetadata(x, y - 2, z);
        EntityCreamGolem golem = new EntityCreamGolem(world);

        if (this == FronosBlocks.cream_golem_head && meta == 0)
        {
            if (block == FronosBlocks.cream_block && meta1 == 0 && block2 == FronosBlocks.cream_block && meta2 == 0)
            {
                if (!world.isRemote)
                {
                    world.setBlock(x, y, z, Blocks.air, 0, 2);
                    world.setBlock(x, y - 1, z, Blocks.air, 0, 2);
                    world.setBlock(x, y - 2, z, Blocks.air, 0, 2);
                    golem.setLocationAndAngles(x + 0.5D, y - 1.95D, z + 0.5D, 0.0F, 0.0F);
                    golem.setCreamGolemType(0);
                    world.spawnEntityInWorld(golem);
                    world.notifyBlockChange(x, y, z, Blocks.air);
                    world.notifyBlockChange(x, y - 1, z, Blocks.air);
                    world.notifyBlockChange(x, y - 2, z, Blocks.air);
                }
            }
        }
        else if (this == FronosBlocks.cream_golem_head && meta == 1)
        {
            if (block == FronosBlocks.cream_block && meta1 == 1 && block2 == FronosBlocks.cream_block && meta2 == 1)
            {
                if (!world.isRemote)
                {
                    world.setBlock(x, y, z, Blocks.air, 0, 2);
                    world.setBlock(x, y - 1, z, Blocks.air, 0, 2);
                    world.setBlock(x, y - 2, z, Blocks.air, 0, 2);
                    golem.setLocationAndAngles(x + 0.5D, y - 1.95D, z + 0.5D, 0.0F, 0.0F);
                    golem.setCreamGolemType(1);
                    world.spawnEntityInWorld(golem);
                    world.notifyBlockChange(x, y, z, Blocks.air);
                    world.notifyBlockChange(x, y - 1, z, Blocks.air);
                    world.notifyBlockChange(x, y - 2, z, Blocks.air);
                }
            }
        }
        else if (this == FronosBlocks.cream_golem_head && meta == 2)
        {
            if (block == FronosBlocks.cream_block && meta1 == 2 && block2 == FronosBlocks.cream_block && meta2 == 2)
            {
                if (!world.isRemote)
                {
                    world.setBlock(x, y, z, Blocks.air, 0, 2);
                    world.setBlock(x, y - 1, z, Blocks.air, 0, 2);
                    world.setBlock(x, y - 2, z, Blocks.air, 0, 2);
                    golem.setLocationAndAngles(x + 0.5D, y - 1.95D, z + 0.5D, 0.0F, 0.0F);
                    golem.setCreamGolemType(2);
                    world.spawnEntityInWorld(golem);
                    world.notifyBlockChange(x, y, z, Blocks.air);
                    world.notifyBlockChange(x, y - 1, z, Blocks.air);
                    world.notifyBlockChange(x, y - 2, z, Blocks.air);
                }
            }
        }
        else if (this == FronosBlocks.cream_golem_head && meta == 3)
        {
            if (block == FronosBlocks.cream_block && meta1 == 3 && block2 == FronosBlocks.cream_block && meta2 == 3)
            {
                if (!world.isRemote)
                {
                    world.setBlock(x, y, z, Blocks.air, 0, 2);
                    world.setBlock(x, y - 1, z, Blocks.air, 0, 2);
                    world.setBlock(x, y - 2, z, Blocks.air, 0, 2);
                    golem.setLocationAndAngles(x + 0.5D, y - 1.95D, z + 0.5D, 0.0F, 0.0F);
                    golem.setCreamGolemType(3);
                    world.spawnEntityInWorld(golem);
                    world.notifyBlockChange(x, y, z, Blocks.air);
                    world.notifyBlockChange(x, y - 1, z, Blocks.air);
                    world.notifyBlockChange(x, y - 2, z, Blocks.air);
                }
            }
        }
        else if (this == FronosBlocks.cream_golem_head && meta == 4)
        {
            if (block == FronosBlocks.cream_block && meta1 == 4 && block2 == FronosBlocks.cream_block && meta2 == 4)
            {
                if (!world.isRemote)
                {
                    world.setBlock(x, y, z, Blocks.air, 0, 2);
                    world.setBlock(x, y - 1, z, Blocks.air, 0, 2);
                    world.setBlock(x, y - 2, z, Blocks.air, 0, 2);
                    golem.setLocationAndAngles(x + 0.5D, y - 1.95D, z + 0.5D, 0.0F, 0.0F);
                    golem.setCreamGolemType(4);
                    world.spawnEntityInWorld(golem);
                    world.notifyBlockChange(x, y, z, Blocks.air);
                    world.notifyBlockChange(x, y - 1, z, Blocks.air);
                    world.notifyBlockChange(x, y - 2, z, Blocks.air);
                }
            }
        }
        else if (this == FronosBlocks.cream_golem_head && meta == 5)
        {
            if (block == FronosBlocks.cream_block && meta1 == 5 && block2 == FronosBlocks.cream_block && meta2 == 5)
            {
                if (!world.isRemote)
                {
                    world.setBlock(x, y, z, Blocks.air, 0, 2);
                    world.setBlock(x, y - 1, z, Blocks.air, 0, 2);
                    world.setBlock(x, y - 2, z, Blocks.air, 0, 2);
                    golem.setLocationAndAngles(x + 0.5D, y - 1.95D, z + 0.5D, 0.0F, 0.0F);
                    golem.setCreamGolemType(5);
                    world.spawnEntityInWorld(golem);
                    world.notifyBlockChange(x, y, z, Blocks.air);
                    world.notifyBlockChange(x, y - 1, z, Blocks.air);
                    world.notifyBlockChange(x, y - 2, z, Blocks.air);
                }
            }
        }
    }
}