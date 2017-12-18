/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.blocks;

import java.util.List;
import java.util.Random;

import micdoodle8.mods.galacticraft.api.block.IDetectableResource;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.blocks.BlockBasicMP;
import stevekung.mods.moreplanets.planets.fronos.items.FronosItems;
import stevekung.mods.moreplanets.planets.fronos.tileentities.TileEntityFronosDungeonSpawner;

public class BlockFronosStone extends BlockBasicMP implements IDetectableResource
{
    private IIcon[] fronosBlockIcon;

    public BlockFronosStone(String name)
    {
        super(Material.rock);
        this.setBlockName(name);
    }

    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.fronosBlockIcon = new IIcon[16];
        this.fronosBlockIcon[0] = par1IconRegister.registerIcon("fronos:fronos_rock");
        this.fronosBlockIcon[1] = par1IconRegister.registerIcon("fronos:fronos_cobblestone");
        this.fronosBlockIcon[2] = par1IconRegister.registerIcon("fronos:fronos_iron_ore");
        this.fronosBlockIcon[3] = par1IconRegister.registerIcon("fronos:fronos_coal_ore");
        this.fronosBlockIcon[4] = par1IconRegister.registerIcon("fronos:fronos_aluminum_ore");
        this.fronosBlockIcon[5] = par1IconRegister.registerIcon("fronos:fronos_tin_ore");
        this.fronosBlockIcon[6] = par1IconRegister.registerIcon("fronos:fronos_copper_ore");
        this.fronosBlockIcon[7] = par1IconRegister.registerIcon("fronos:fronos_lapis_ore");
        this.fronosBlockIcon[8] = par1IconRegister.registerIcon("fronos:mineral_crystal_ore");
        this.fronosBlockIcon[9] = par1IconRegister.registerIcon("fronos:black_diamond_ore");
        this.fronosBlockIcon[10] = par1IconRegister.registerIcon("fronos:iridium_ore");
        this.fronosBlockIcon[11] = par1IconRegister.registerIcon("fronos:fronos_stone_brick");
        this.fronosBlockIcon[12] = par1IconRegister.registerIcon("fronos:cracked_fronos_stone_brick");
        this.fronosBlockIcon[13] = par1IconRegister.registerIcon("fronos:chiseled_fronos_stone_brick");
        this.fronosBlockIcon[14] = par1IconRegister.registerIcon("fronos:fronos_dungeon_brick");
        this.fronosBlockIcon[15] = par1IconRegister.registerIcon("galacticraftcore:blank");
    }

    @Override
    public IIcon getIcon(int side, int meta)
    {
        return this.fronosBlockIcon[meta];
    }

    @Override
    public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list)
    {
        for (int i = 0; i < 15; ++i)
        {
            list.add(new ItemStack(this, 1, i));
        }
    }

    @Override
    public float getBlockHardness(World par1World, int par2, int par3, int par4)
    {
        int meta = par1World.getBlockMetadata(par2, par3, par4);

        if (meta == 0)
        {
            return 2.0F;
        }
        if (meta == 1)
        {
            return 1.75F;
        }
        if (meta >= 2 && meta <= 10)
        {
            return 2.5F;
        }
        if (meta >= 11 && meta <= 13)
        {
            return 2.25F;
        }
        if (meta == 14)
        {
            return 4.0F;
        }
        return 1.0F;
    }

    @Override
    public float getExplosionResistance(Entity entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ)
    {
        return world.getBlockMetadata(x, y, z) == 14 ? 40.0F : super.getExplosionResistance(entity, world, x, y, z, explosionX, explosionY, explosionZ);
    }

    @Override
    public Item getItemDropped(int meta, Random par2Random, int par3)
    {
        if (meta == 0)
        {
            return Item.getItemFromBlock(FronosBlocks.fronos_block);
        }
        if (meta == 3)
        {
            return Items.coal;
        }
        if (meta == 7)
        {
            return Items.dye;
        }
        if (meta == 8 || meta == 9)
        {
            return FronosItems.fronos_item;
        }
        return Item.getItemFromBlock(this);
    }

    @Override
    public int damageDropped(int meta)
    {
        if (meta == 0)
        {
            return 1;
        }
        if (meta == 3 || meta == 8)
        {
            return 0;
        }
        if (meta == 7)
        {
            return 4;
        }
        if (meta == 9)
        {
            return 2;
        }
        return meta;
    }

    @Override
    public int quantityDropped(int meta, int fortune, Random rand)
    {
        if (meta == 7)
        {
            if (fortune > 0)
            {
                int j = rand.nextInt(fortune + 2) - 1;

                if (j < 0)
                {
                    j = 0;
                }
                return 4 + rand.nextInt(5) * (j + 1);
            }
            else
            {
                return 4 + rand.nextInt(5);
            }
        }
        return super.quantityDropped(meta, fortune, rand);
    }

    @Override
    public boolean isValueable(int metadata)
    {
        if (metadata >= 2 && metadata <= 10)
        {
            return true;
        }
        return false;
    }

    @Override
    public void dropBlockAsItemWithChance(World world, int par2, int par3, int par4, int par5, float par6, int par7)
    {
        int meta = world.getBlockMetadata(par2, par3, par4);
        int var8 = MathHelper.getRandomIntegerInRange(world.rand, 2, 5);

        if (meta == 7)
        {
            this.dropXpOnBlockBreak(world, par2, par3, par4, var8);
        }
        super.dropBlockAsItemWithChance(world, par2, par3, par4, par5, par6, par7);
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
        return new TileEntityFronosDungeonSpawner();
    }
}