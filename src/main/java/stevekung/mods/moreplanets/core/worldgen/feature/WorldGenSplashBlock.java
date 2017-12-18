/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.worldgen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenSplashBlock extends WorldGenerator
{
    private Block block;
    private int meta;
    private Block blockToGen;
    private int metaToGen;

    public WorldGenSplashBlock(Block block, int meta, Block blockToGen, int metaToGen)
    {
        super();
        this.block = block;
        this.meta = meta;
        this.blockToGen = blockToGen;
        this.metaToGen = metaToGen;
    }

    @Override
    public boolean generate(World world, Random rand, int x, int y, int z)
    {
        for (int i = 0; i < 64; i++)
        {
            int x1 = x + rand.nextInt(8) - rand.nextInt(8);
            int y1 = y + rand.nextInt(4) - rand.nextInt(4);
            int z1 = z + rand.nextInt(8) - rand.nextInt(8);

            if (world.getBlock(x1, y1 - 1, z1) == this.blockToGen && world.getBlockMetadata(x1, y1 - 1, z1) == this.metaToGen)
            {
                world.setBlock(x1, y1 - 1, z1, this.block, this.meta, 2);
            }
        }
        return true;
    }
}