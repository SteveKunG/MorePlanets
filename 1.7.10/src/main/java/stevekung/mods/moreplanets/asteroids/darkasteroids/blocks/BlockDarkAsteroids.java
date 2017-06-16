/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.asteroids.darkasteroids.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import micdoodle8.mods.galacticraft.api.block.IDetectableResource;
import micdoodle8.mods.galacticraft.core.items.GCItems;
import micdoodle8.mods.galacticraft.planets.asteroids.items.AsteroidsItems;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.blocks.BlockBasicMP;

public class BlockDarkAsteroids extends BlockBasicMP implements IDetectableResource
{
    @SideOnly(Side.CLIENT)
    private IIcon[] blockIcons;

    public BlockDarkAsteroids(String name)
    {
        super(Material.rock);
        this.setHardness(3.0F);
        this.setResistance(15.0F);
        this.setBlockName(name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.blockIcons = new IIcon[11];
        this.blockIcons[0] = par1IconRegister.registerIcon("mpcore:darkasteroids/asteroid_rock_1");
        this.blockIcons[1] = par1IconRegister.registerIcon("mpcore:darkasteroids/asteroid_rock_2");
        this.blockIcons[2] = par1IconRegister.registerIcon("mpcore:darkasteroids/asteroid_rock_3");
        this.blockIcons[3] = par1IconRegister.registerIcon("mpcore:darkasteroids/aluminum_ore");
        this.blockIcons[4] = par1IconRegister.registerIcon("mpcore:darkasteroids/ilmenite_ore");
        this.blockIcons[5] = par1IconRegister.registerIcon("mpcore:darkasteroids/iron_ore");
        this.blockIcons[6] = par1IconRegister.registerIcon("mpcore:darkasteroids/meteoric_iron_ore");
        this.blockIcons[7] = par1IconRegister.registerIcon("mpcore:darkasteroids/silicon_ore");
        this.blockIcons[8] = par1IconRegister.registerIcon("mpcore:darkasteroids/diamond_ore");
        this.blockIcons[9] = par1IconRegister.registerIcon("mpcore:darkasteroids/emerald_ore");
        this.blockIcons[10] = par1IconRegister.registerIcon("mpcore:darkasteroids/lapis_ore");
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta)
    {
        return this.blockIcons[meta];
    }

    @Override
    public Item getItemDropped(int meta, Random rand, int fortune)
    {
        if (meta == 6)
        {
            return GCItems.meteoricIronRaw;
        }
        if (meta == 7)
        {
            return GCItems.basicItem;
        }
        if (meta == 8)
        {
            return Items.diamond;
        }
        if (meta == 9)
        {
            return Items.emerald;
        }
        if (meta == 10)
        {
            return Items.dye;
        }
        return Item.getItemFromBlock(this);
    }

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
    {
        switch (metadata)
        {
        case 4:
            ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
            int count = this.quantityDropped(metadata, fortune, world.rand);

            for (int i = 0; i < count; i++)
            {
                ret.add(new ItemStack(AsteroidsItems.basicItem, 1, 3));
            }
            for (int i = 0; i < count; i++)
            {
                ret.add(new ItemStack(AsteroidsItems.basicItem, 1, 4));
            }
            this.dropXpOnBlockBreak(world, x, y, z, MathHelper.getRandomIntegerInRange(world.rand, 3, 5));
            return ret;
        default:
            return super.getDrops(world, x, y, z, metadata, fortune);
        }
    }

    @Override
    public int damageDropped(int meta)
    {
        if (meta == 6 || meta == 8 || meta == 9)
        {
            return 0;
        }
        if (meta == 7)
        {
            return 2;
        }
        if (meta == 10)
        {
            return 4;
        }
        return meta;
    }

    @Override
    public int quantityDropped(int meta, int fortune, Random rand)
    {
        if (meta == 4)
        {
            if (fortune >= 1)
            {
                return rand.nextFloat() < fortune * 0.29F - 0.25F ? 2 : 1;
            }
        }
        if (meta == 10)
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
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list)
    {
        for (int i = 0; i < 11; ++i)
        {
            list.add(new ItemStack(this, 1, i));
        }
    }

    @Override
    public boolean isValueable(int meta)
    {
        return meta >= 3;
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
    {
        return new ItemStack(this, 1, world.getBlockMetadata(x, y, z));
    }

    @Override
    public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int metadata)
    {
        return true;
    }

    @Override
    public int getDungeonSpawnerMetadata()
    {
        return -1;
    }

    @Override
    public TileEntity getDungeonSpawner()
    {
        return null;
    }
}