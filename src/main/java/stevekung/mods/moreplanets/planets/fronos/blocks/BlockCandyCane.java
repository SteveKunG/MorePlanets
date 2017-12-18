/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.blocks;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.blocks.base.BlockBaseMP;

public class BlockCandyCane extends BlockBaseMP
{
    public static enum CandyCategory
    {
        CAT1, CAT2, CAT3, CAT4;
    }

    private static String[] types = new String[] {
            "pink",
            "orange",
            "green",
            "yellow",
            "light_blue",
            "blue",
            "red",
            "purple"
    };

    private IIcon[] textures;
    private IIcon[] logHearts;
    private CandyCategory category;

    public BlockCandyCane(String name, CandyCategory cat)
    {
        super(Material.cloth);
        this.category = cat;
        this.setHardness(1.0F);
        this.setResistance(3.0F);
        this.setStepSound(Block.soundTypeCloth);
        this.setBlockName(name);
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.textures = new IIcon[BlockCandyCane.types.length];
        this.logHearts = new IIcon[BlockCandyCane.types.length];

        for (int i = 0; i < BlockCandyCane.types.length; ++i)
        {
            this.textures[i] = iconRegister.registerIcon("fronos:candy_cane_" + BlockCandyCane.types[i] + "_side");
            this.logHearts[i] = iconRegister.registerIcon("fronos:candy_cane_" + BlockCandyCane.types[i] + "_top");
        }
    }

    @Override
    public IIcon getIcon(int side, int meta)
    {
        int pos = meta & 12;

        if (pos == 0 && (side == 1 || side == 0) || pos == 4 && (side == 5 || side == 4) || pos == 8 && (side == 2 || side == 3))
        {
            return this.logHearts[(meta & 3) + this.category.ordinal() * 4];
        }
        return this.textures[(meta & 3) + this.category.ordinal() * 4];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list)
    {
        for (int i = 0; i < 4; ++i)
        {
            list.add(new ItemStack(this, 1, i));
        }
    }

    @Override
    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta)
    {
        byte orientation = 0;

        switch (side)
        {
        case 0:
        case 1:
            orientation = 0;
            break;
        case 2:
        case 3:
            orientation = 8;
            break;
        case 4:
        case 5:
            orientation = 4;
        }
        return meta & 3 | orientation;
    }

    @Override
    public int damageDropped(int meta)
    {
        return meta & 3;
    }

    @Override
    protected ItemStack createStackedBlock(int meta)
    {
        return new ItemStack(this, 1, meta & 3);
    }

    @Override
    public int getRenderType()
    {
        return 31;
    }

    public String getCandyCaneType(int meta)
    {
        return BlockCandyCane.types[(meta & 3) + this.category.ordinal() * 4];
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition moving, World world, int x, int y, int z)
    {
        return new ItemStack(this, 1, world.getBlockMetadata(x, y, z) & 3);
    }
}