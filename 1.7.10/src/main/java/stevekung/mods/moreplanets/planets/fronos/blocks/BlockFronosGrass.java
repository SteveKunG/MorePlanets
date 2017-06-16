/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.blocks.BlockGrassMP;

public class BlockFronosGrass extends BlockGrassMP implements IFronosGrass
{
    private IIcon[] fronosGrassIcon;

    public BlockFronosGrass(String name)
    {
        super();
        this.setBlockName(name);
    }

    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.fronosGrassIcon = new IIcon[7];
        this.fronosGrassIcon[0] = par1IconRegister.registerIcon("fronos:fronos_dirt");
        this.fronosGrassIcon[1] = par1IconRegister.registerIcon("fronos:fronos_grass_top");
        this.fronosGrassIcon[2] = par1IconRegister.registerIcon("fronos:fronos_grass_side");
        this.fronosGrassIcon[3] = par1IconRegister.registerIcon("fronos:fronos_grass_side");
        this.fronosGrassIcon[4] = par1IconRegister.registerIcon("fronos:fronos_grass_side");
        this.fronosGrassIcon[5] = par1IconRegister.registerIcon("fronos:fronos_grass_side");
        this.fronosGrassIcon[6] = par1IconRegister.registerIcon("fronos:fronos_grass_side_cream");
    }

    @Override
    public IIcon getIcon(int par1, int par2)
    {
        if (par1 < 0 || par1 >= this.fronosGrassIcon.length)
        {
            par1 = 1;
        }
        return this.fronosGrassIcon[par1];
    }

    @Override
    public IIcon getIcon(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        Block block = par1IBlockAccess.getBlock(par2, par3 + 1, par4);

        if (par5 == 1)
        {
            return this.fronosGrassIcon[1];
        }
        else if (par5 == 0)
        {
            return FronosBlocks.fronos_dirt.getBlockTextureFromSide(par5);
        }
        else if (par5 == 2 || par5 == 3 || par5 == 4 || par5 == 5)
        {
            return block != FronosBlocks.vanilla_cream_layer ? this.fronosGrassIcon[2] : this.fronosGrassIcon[6];
        }
        return this.blockIcon;
    }

    @Override
    public void updateTick(World world, int par2, int par3, int par4, Random par5Random)
    {
        if (!world.isRemote)
        {
            if (world.getBlockLightValue(par2, par3 + 1, par4) < 4 && world.getBlockLightOpacity(par2, par3 + 1, par4) > 2)
            {
                world.setBlock(par2, par3, par4, FronosBlocks.fronos_dirt);
            }
            else if (world.getBlockLightValue(par2, par3 + 1, par4) >= 9)
            {
                for (int var6 = 0; var6 < 4; ++var6)
                {
                    int var7 = par2 + par5Random.nextInt(3) - 1;
                    int var8 = par3 + par5Random.nextInt(5) - 3;
                    int var9 = par4 + par5Random.nextInt(3) - 1;
                    Block var10 = world.getBlock(var7, var8 + 1, var9);

                    if (world.getBlock(var7, var8, var9) == FronosBlocks.fronos_dirt && world.getBlockMetadata(var7, var8, var9) == 0)
                    {
                        if (world.getBlockLightValue(var7, var8 + 1, var9) >= 4 && var10.getLightOpacity() <= 2)
                        {
                            world.setBlock(var7, var8, var9, FronosBlocks.fronos_grass);
                        }
                    }
                }
            }
        }
    }

    @Override
    public Item getItemDropped(int par1, Random par2Random, int par3)
    {
        return Item.getItemFromBlock(FronosBlocks.fronos_dirt);
    }

    @Override
    public Block getFarmlandBlock()
    {
        return FronosBlocks.fronos_farmland;
    }
}