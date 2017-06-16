/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.pluto.blocks;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import micdoodle8.mods.galacticraft.api.world.IGalacticraftWorldProvider;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import stevekung.mods.moreplanets.planets.pluto.items.PlutoItems;

public class BlockSpacePotato extends BlockCrops
{
    @SideOnly(Side.CLIENT)
    private IIcon[] field_149869_a;

    public BlockSpacePotato(String name)
    {
        super();
        this.setStepSound(soundTypeGrass);
        this.setBlockName(name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_)
    {
        if (p_149691_2_ < 7)
        {
            if (p_149691_2_ == 6)
            {
                p_149691_2_ = 5;
            }
            return this.field_149869_a[p_149691_2_ >> 1];
        }
        else
        {
            return this.field_149869_a[3];
        }
    }

    @Override
    public boolean func_149851_a(World world, int p_149851_2_, int p_149851_3_, int p_149851_4_, boolean p_149851_5_)
    {
        if (world.provider instanceof IGalacticraftWorldProvider)
        {
            return world.getBlockMetadata(p_149851_2_, p_149851_3_, p_149851_4_) != 7;
        }
        return false;
    }

    @Override
    public boolean func_149852_a(World world, Random p_149852_2_, int p_149852_3_, int p_149852_4_, int p_149852_5_)
    {
        return world.provider instanceof IGalacticraftWorldProvider;
    }

    @Override
    public void func_149853_b(World world, Random p_149853_2_, int p_149853_3_, int p_149853_4_, int p_149853_5_)
    {
        if (world.provider instanceof IGalacticraftWorldProvider)
        {
            this.func_149863_m(world, p_149853_3_, p_149853_4_, p_149853_5_);
        }
    }

    @Override
    public void updateTick(World world, int p_149674_2_, int p_149674_3_, int p_149674_4_, Random p_149674_5_)
    {
        super.updateTick(world, p_149674_2_, p_149674_3_, p_149674_4_, p_149674_5_);

        if (world.provider instanceof IGalacticraftWorldProvider)
        {
            if (world.getBlockLightValue(p_149674_2_, p_149674_3_ + 1, p_149674_4_) >= 9)
            {
                int l = world.getBlockMetadata(p_149674_2_, p_149674_3_, p_149674_4_);

                if (l < 7)
                {
                    float f = this.func_149864_n(world, p_149674_2_, p_149674_3_, p_149674_4_);

                    if (p_149674_5_.nextInt((int)(25.0F / f) + 1) == 0)
                    {
                        ++l;
                        world.setBlockMetadataWithNotify(p_149674_2_, p_149674_3_, p_149674_4_, l, 2);
                    }
                }
            }
        }
    }

    private float func_149864_n(World p_149864_1_, int p_149864_2_, int p_149864_3_, int p_149864_4_)
    {
        float f = 1.0F;
        Block block = p_149864_1_.getBlock(p_149864_2_, p_149864_3_, p_149864_4_ - 1);
        Block block1 = p_149864_1_.getBlock(p_149864_2_, p_149864_3_, p_149864_4_ + 1);
        Block block2 = p_149864_1_.getBlock(p_149864_2_ - 1, p_149864_3_, p_149864_4_);
        Block block3 = p_149864_1_.getBlock(p_149864_2_ + 1, p_149864_3_, p_149864_4_);
        Block block4 = p_149864_1_.getBlock(p_149864_2_ - 1, p_149864_3_, p_149864_4_ - 1);
        Block block5 = p_149864_1_.getBlock(p_149864_2_ + 1, p_149864_3_, p_149864_4_ - 1);
        Block block6 = p_149864_1_.getBlock(p_149864_2_ + 1, p_149864_3_, p_149864_4_ + 1);
        Block block7 = p_149864_1_.getBlock(p_149864_2_ - 1, p_149864_3_, p_149864_4_ + 1);
        boolean flag = block2 == this || block3 == this;
        boolean flag1 = block == this || block1 == this;
        boolean flag2 = block4 == this || block5 == this || block6 == this || block7 == this;

        for (int l = p_149864_2_ - 1; l <= p_149864_2_ + 1; ++l)
        {
            for (int i1 = p_149864_4_ - 1; i1 <= p_149864_4_ + 1; ++i1)
            {
                float f1 = 0.0F;

                if (p_149864_1_.getBlock(l, p_149864_3_ - 1, i1).canSustainPlant(p_149864_1_, l, p_149864_3_ - 1, i1, ForgeDirection.UP, this))
                {
                    f1 = 1.0F;

                    if (p_149864_1_.getBlock(l, p_149864_3_ - 1, i1).isFertile(p_149864_1_, l, p_149864_3_ - 1, i1))
                    {
                        f1 = 3.0F;
                    }
                }

                if (l != p_149864_2_ || i1 != p_149864_4_)
                {
                    f1 /= 4.0F;
                }
                f += f1;
            }
        }

        if (flag2 || flag && flag1)
        {
            f /= 2.0F;
        }
        return f;
    }

    @Override
    public int damageDropped(int meta)
    {
        return 0;
    }

    @Override
    protected Item func_149866_i()
    {
        return PlutoItems.space_potato;
    }

    @Override
    protected Item func_149865_P()
    {
        return PlutoItems.space_potato;
    }

    @Override
    public void dropBlockAsItemWithChance(World world, int p_149690_2_, int p_149690_3_, int p_149690_4_, int p_149690_5_, float p_149690_6_, int p_149690_7_)
    {
        super.dropBlockAsItemWithChance(world, p_149690_2_, p_149690_3_, p_149690_4_, p_149690_5_, p_149690_6_, p_149690_7_);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
        this.field_149869_a = new IIcon[4];
        this.field_149869_a[0] = p_149651_1_.registerIcon("potatoes_stage_0");
        this.field_149869_a[1] = p_149651_1_.registerIcon("potatoes_stage_1");
        this.field_149869_a[2] = p_149651_1_.registerIcon("potatoes_stage_2");
        this.field_149869_a[3] = p_149651_1_.registerIcon("pluto:space_potatoes_stage_3");
    }
}