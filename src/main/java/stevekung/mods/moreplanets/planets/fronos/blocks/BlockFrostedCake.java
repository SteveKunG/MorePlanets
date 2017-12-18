/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.MorePlanetsCore;

public class BlockFrostedCake extends Block
{
    private static final String[] types = new String[] {
            "cakeBread",//0
            "whiteCakeBread",//1
            "chocolateCakeBread",//2
            "whiteCake",//3
            "pinkCake",//4
            "whiteCake2",//5
            "chocolateCake"//6
    };

    private IIcon[] cakeIcon;
    private IIcon[] pinkCakeIcon;
    private IIcon[] chocolateCakeIcon;
    private IIcon[] whiteCakeIcon;

    public BlockFrostedCake(String name)
    {
        super(Material.cake);
        this.setStepSound(Block.soundTypeCloth);
        this.setHardness(0.55F);
        this.setResistance(0.55F);
        this.setBlockName(name);
    }

    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.cakeIcon = new IIcon[3];
        this.cakeIcon[0] = par1IconRegister.registerIcon("fronos:cake_bread");
        this.cakeIcon[1] = par1IconRegister.registerIcon("fronos:white_cake_side");
        this.cakeIcon[2] = par1IconRegister.registerIcon("fronos:frosted_white_cake_top");

        this.chocolateCakeIcon = new IIcon[2];
        this.chocolateCakeIcon[0] = par1IconRegister.registerIcon("fronos:chocolate_cake_bread");
        this.chocolateCakeIcon[1] = par1IconRegister.registerIcon("fronos:frosted_chocolate_cake_side");

        this.pinkCakeIcon = new IIcon[2];
        this.pinkCakeIcon[0] = par1IconRegister.registerIcon("fronos:pink_cake_top");
        this.pinkCakeIcon[1] = par1IconRegister.registerIcon("fronos:pink_cake_side");

        this.whiteCakeIcon = new IIcon[3];
        this.whiteCakeIcon[0] = par1IconRegister.registerIcon("fronos:white_cake_bread");
        this.whiteCakeIcon[1] = par1IconRegister.registerIcon("fronos:frosted_white_cake_side");
        this.whiteCakeIcon[2] = par1IconRegister.registerIcon("fronos:white_cake_top2");
    }

    @Override
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return MorePlanetsCore.mpBlocksTab;
    }

    @Override
    public IIcon getIcon(int side, int meta)
    {
        switch (meta)
        {
        case 0:
            return this.cakeIcon[0];
        case 1:
            return this.whiteCakeIcon[0];
        case 2:
            return this.chocolateCakeIcon[0];
        case 3:
            switch (side)
            {
            case 0:
                return this.cakeIcon[0]; //BOTTOM
            case 1:
                return this.cakeIcon[2]; //TOP
            case 2:
                return this.cakeIcon[1]; //Z-
            case 3:
                return this.cakeIcon[1]; //Z+
            case 4:
                return this.cakeIcon[1]; //X-
            case 5:
                return this.cakeIcon[1]; //X+
            default:
                return this.cakeIcon[0];
            }
        case 4:
            switch (side)
            {
            case 0:
                return this.cakeIcon[0]; //BOTTOM
            case 1:
                return this.pinkCakeIcon[0]; //TOP
            case 2:
                return this.pinkCakeIcon[1]; //Z-
            case 3:
                return this.pinkCakeIcon[1]; //Z+
            case 4:
                return this.pinkCakeIcon[1]; //X-
            case 5:
                return this.pinkCakeIcon[1]; //X+
            default:
                return this.pinkCakeIcon[0];
            }
        case 5:
            switch (side)
            {
            case 0:
                return this.whiteCakeIcon[0]; //BOTTOM
            case 1:
                return this.whiteCakeIcon[2]; //TOP
            case 2:
                return this.whiteCakeIcon[1]; //Z-
            case 3:
                return this.whiteCakeIcon[1]; //Z+
            case 4:
                return this.whiteCakeIcon[1]; //X-
            case 5:
                return this.whiteCakeIcon[1]; //X+
            default:
                return this.whiteCakeIcon[0];
            }
        case 6:
            switch (side)
            {
            case 0:
                return this.chocolateCakeIcon[0]; //BOTTOM
            case 1:
                return this.cakeIcon[2]; //TOP
            case 2:
                return this.chocolateCakeIcon[1]; //Z-
            case 3:
                return this.chocolateCakeIcon[1]; //Z+
            case 4:
                return this.chocolateCakeIcon[1]; //X-
            case 5:
                return this.chocolateCakeIcon[1]; //X+
            default:
                return this.chocolateCakeIcon[0];
            }
        }
        return this.blockIcon;
    }

    @Override
    public int getDamageValue(World world, int x, int y, int z)
    {
        return world.getBlockMetadata(x, y, z);
    }

    @Override
    public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list)
    {
        for (int i = 0; i < BlockFrostedCake.types.length; ++i)
        {
            list.add(new ItemStack(block, 1, i));
        }
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitVecX, float hitVecY, float hitVecZ)
    {
        int metafarm = 0;

        if (world.getBlockMetadata(x, y, z) == 0 || world.getBlockMetadata(x, y, z) == 3 || world.getBlockMetadata(x, y, z) == 4)
        {
            metafarm = 0;
        }
        else if (world.getBlockMetadata(x, y, z) == 1 || world.getBlockMetadata(x, y, z) == 5)
        {
            metafarm = 1;
        }
        else if (world.getBlockMetadata(x, y, z) == 2 || world.getBlockMetadata(x, y, z) == 6)
        {
            metafarm = 2;
        }

        if (player.getCurrentEquippedItem() != null)
        {
            if (player.getCurrentEquippedItem().getDisplayName().toLowerCase().contains("hoe"))
            {
                Block farmland = FronosBlocks.cake_farmland;

                world.playSoundEffect(x + 0.5F, y + 0.5F, z + 0.5F, farmland.stepSound.getStepResourcePath(), (farmland.stepSound.getVolume() + 1.0F) / 2.0F, farmland.stepSound.getPitch() * 0.8F);

                if (!world.isRemote)
                {
                    world.setBlock(x, y, z, farmland, metafarm, 2);
                }
                player.getCurrentEquippedItem().damageItem(1, player);
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
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
}