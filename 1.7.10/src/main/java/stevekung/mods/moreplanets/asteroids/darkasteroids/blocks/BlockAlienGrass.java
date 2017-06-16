/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.asteroids.darkasteroids.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.blocks.BlockGrassMP;

public class BlockAlienGrass extends BlockGrassMP
{
    private IIcon[] blockIcon;

    public BlockAlienGrass(String name)
    {
        super();
        this.setBlockName(name);
    }

    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.blockIcon = new IIcon[8];
        this.blockIcon[0] = par1IconRegister.registerIcon("mpcore:darkasteroids/alien_dirt");
        this.blockIcon[1] = par1IconRegister.registerIcon("mpcore:darkasteroids/alien_grass_top");
        this.blockIcon[2] = par1IconRegister.registerIcon("mpcore:darkasteroids/alien_grass_side");
        this.blockIcon[3] = par1IconRegister.registerIcon("mpcore:darkasteroids/alien_grass_side");
        this.blockIcon[4] = par1IconRegister.registerIcon("mpcore:darkasteroids/alien_grass_side");
        this.blockIcon[5] = par1IconRegister.registerIcon("mpcore:darkasteroids/alien_grass_side");
    }

    @Override
    public IIcon getIcon(int par1, int par2)
    {
        if (par1 < 0 || par1 >= this.blockIcon.length)
        {
            par1 = 1;
        }
        return this.blockIcon[par1];
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand)
    {
        if (!world.isRemote)
        {
            if (world.getBlockLightValue(x, y + 1, z) < 4 && world.getBlockLightOpacity(x, y + 1, z) > 2)
            {
                world.setBlock(x, y, z, DarkAsteroidsBlocks.alien_dirt);
            }
            else if (world.getBlockLightValue(x, y + 1, z) >= 9)
            {
                for (int var6 = 0; var6 < 4; ++var6)
                {
                    int var7 = x + rand.nextInt(3) - 1;
                    int var8 = y + rand.nextInt(5) - 3;
                    int var9 = z + rand.nextInt(3) - 1;
                    Block block = world.getBlock(var7, var8 + 1, var9);

                    if (world.getBlock(var7, var8, var9) == DarkAsteroidsBlocks.alien_dirt && world.getBlockMetadata(var7, var8, var9) == 0 && world.getBlockLightValue(var7, var8 + 1, var9) >= 4 && block.getLightOpacity() <= 2)
                    {
                        world.setBlock(var7, var8, var9, DarkAsteroidsBlocks.alien_grass);
                    }
                }
            }
        }
    }

    @Override
    public Item getItemDropped(int meta, Random rand, int fortune)
    {
        return Item.getItemFromBlock(DarkAsteroidsBlocks.alien_dirt);
    }

    @Override
    public Block getFarmlandBlock()
    {
        return DarkAsteroidsBlocks.alien_farmland;
    }
}