package stevekung.mods.stevekunglib.world.gen;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenMinableBase extends WorldGenerator
{
    private final IBlockState oreBlock;
    private final IBlockState fillBlock;
    private int numberOfBlocks;

    public WorldGenMinableBase(IBlockState oreBlock, IBlockState fillBlock, int numberOfBlocks)
    {
        this.oreBlock = oreBlock;
        this.numberOfBlocks = numberOfBlocks;
        this.fillBlock = fillBlock;
    }

    public WorldGenMinableBase(IBlockState oreBlock, IBlockState fillBlock, EnumOreGen oreGen)
    {
        this.oreBlock = oreBlock;
        this.numberOfBlocks = oreGen.getGenCount();
        this.fillBlock = fillBlock;
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
        float f = rand.nextFloat() * (float)Math.PI;
        double d0 = pos.getX() + 8 + MathHelper.sin(f) * this.numberOfBlocks / 8.0F;
        double d1 = pos.getX() + 8 - MathHelper.sin(f) * this.numberOfBlocks / 8.0F;
        double d2 = pos.getZ() + 8 + MathHelper.cos(f) * this.numberOfBlocks / 8.0F;
        double d3 = pos.getZ() + 8 - MathHelper.cos(f) * this.numberOfBlocks / 8.0F;
        double d4 = pos.getY() + rand.nextInt(3) - 2;
        double d5 = pos.getY() + rand.nextInt(3) - 2;

        for (int i = 0; i < this.numberOfBlocks; ++i)
        {
            float f1 = (float)i / (float)this.numberOfBlocks;
            double d6 = d0 + (d1 - d0) * f1;
            double d7 = d4 + (d5 - d4) * f1;
            double d8 = d2 + (d3 - d2) * f1;
            double d9 = rand.nextDouble() * this.numberOfBlocks / 16.0D;
            double d10 = (MathHelper.sin((float)Math.PI * f1) + 1.0F) * d9 + 1.0D;
            double d11 = (MathHelper.sin((float)Math.PI * f1) + 1.0F) * d9 + 1.0D;
            int j = MathHelper.floor(d6 - d10 / 2.0D);
            int k = MathHelper.floor(d7 - d11 / 2.0D);
            int l = MathHelper.floor(d8 - d10 / 2.0D);
            int i1 = MathHelper.floor(d6 + d10 / 2.0D);
            int j1 = MathHelper.floor(d7 + d11 / 2.0D);
            int k1 = MathHelper.floor(d8 + d10 / 2.0D);

            for (int l1 = j; l1 <= i1; ++l1)
            {
                double d12 = (l1 + 0.5D - d6) / (d10 / 2.0D);

                if (d12 * d12 < 1.0D)
                {
                    for (int i2 = k; i2 <= j1; ++i2)
                    {
                        double d13 = (i2 + 0.5D - d7) / (d11 / 2.0D);

                        if (d12 * d12 + d13 * d13 < 1.0D)
                        {
                            for (int j2 = l; j2 <= k1; ++j2)
                            {
                                double d14 = (j2 + 0.5D - d8) / (d10 / 2.0D);

                                if (d12 * d12 + d13 * d13 + d14 * d14 < 1.0D)
                                {
                                    BlockPos blockpos = new BlockPos(l1, i2, j2);

                                    if (world.getBlockState(blockpos) == this.fillBlock)
                                    {
                                        world.setBlockState(blockpos, this.oreBlock, 2);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}