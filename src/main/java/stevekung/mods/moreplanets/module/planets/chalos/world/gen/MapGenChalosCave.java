package stevekung.mods.moreplanets.module.planets.chalos.world.gen;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.MapGenBase;
import stevekung.mods.moreplanets.module.planets.chalos.blocks.ChalosBlocks;

public class MapGenChalosCave extends MapGenBase
{
    protected void generateLargeCaveNode(long seed, int chunkX, int chunkZ, ChunkPrimer chunk, double par5, double par6, double par7)
    {
        this.generateCaveNode(seed, chunkX, chunkZ, chunk, par5, par6, par7, 1.0F + this.rand.nextFloat() * 6.0F, 0.0F, 0.0F, -1, -1, 0.5D);
    }

    protected void generateCaveNode(long seed, int chunkX, int chunkZ, ChunkPrimer chunk, double par5, double par6, double par7, float par8, float par9, float par10, int par11, int par12, double par13)
    {
        double d4 = chunkX * 16 + 8;
        double d5 = chunkZ * 16 + 8;
        float f3 = 0.0F;
        float f4 = 0.0F;
        Random random = new Random(seed);

        if (par12 <= 0)
        {
            int j1 = this.range * 16 - 16;
            par12 = j1 - random.nextInt(j1 / 4);
        }

        boolean flag2 = false;

        if (par11 == -1)
        {
            par11 = par12 / 2;
            flag2 = true;
        }

        int k1 = random.nextInt(par12 / 2) + par12 / 4;

        for (boolean flag = random.nextInt(6) == 0; par11 < par12; ++par11)
        {
            double d6 = 1.5D + MathHelper.sin(par11 * (float)Math.PI / par12) * par8 * 1.0F;
            double d7 = d6 * par13;
            float f5 = MathHelper.cos(par10);
            float f6 = MathHelper.sin(par10);
            par5 += MathHelper.cos(par9) * f5;
            par6 += f6;
            par7 += MathHelper.sin(par9) * f5;

            if (flag)
            {
                par10 *= 0.92F;
            }
            else
            {
                par10 *= 0.7F;
            }

            par10 += f4 * 0.1F;
            par9 += f3 * 0.1F;
            f4 *= 0.9F;
            f3 *= 0.75F;
            f4 += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 2.0F;
            f3 += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 4.0F;

            if (!flag2 && par11 == k1 && par8 > 1.0F && par12 > 0)
            {
                this.generateCaveNode(random.nextLong(), chunkX, chunkZ, chunk, par5, par6, par7, random.nextFloat() * 0.5F + 0.5F, par9 - (float)Math.PI / 2F, par10 / 3.0F, par11, par12, 1.0D);
                this.generateCaveNode(random.nextLong(), chunkX, chunkZ, chunk, par5, par6, par7, random.nextFloat() * 0.5F + 0.5F, par9 + (float)Math.PI / 2F, par10 / 3.0F, par11, par12, 1.0D);
                return;
            }

            if (flag2 || random.nextInt(4) != 0)
            {
                double d8 = par5 - d4;
                double d9 = par7 - d5;
                double d10 = par12 - par11;
                double d11 = par8 + 2.0F + 16.0F;

                if (d8 * d8 + d9 * d9 - d10 * d10 > d11 * d11)
                {
                    return;
                }

                if (par5 >= d4 - 16.0D - d6 * 2.0D && par7 >= d5 - 16.0D - d6 * 2.0D && par5 <= d4 + 16.0D + d6 * 2.0D && par7 <= d5 + 16.0D + d6 * 2.0D)
                {
                    int k3 = MathHelper.floor(par5 - d6) - chunkX * 16 - 1;
                    int l1 = MathHelper.floor(par5 + d6) - chunkX * 16 + 1;
                    int l3 = MathHelper.floor(par6 - d7) - 1;
                    int i2 = MathHelper.floor(par6 + d7) + 1;
                    int i4 = MathHelper.floor(par7 - d6) - chunkZ * 16 - 1;
                    int j2 = MathHelper.floor(par7 + d6) - chunkZ * 16 + 1;

                    if (k3 < 0)
                    {
                        k3 = 0;
                    }
                    if (l1 > 16)
                    {
                        l1 = 16;
                    }
                    if (l3 < 1)
                    {
                        l3 = 1;
                    }
                    if (i2 > 248)
                    {
                        i2 = 248;
                    }
                    if (i4 < 0)
                    {
                        i4 = 0;
                    }
                    if (j2 > 16)
                    {
                        j2 = 16;
                    }

                    int k2;

                    for (k2 = k3; k2 < l1; ++k2)
                    {
                        for (int l2 = i4; l2 < j2; ++l2)
                        {
                            for (int i3 = i2 + 1; i3 >= l3 - 1; --i3)
                            {
                                if (i3 >= 0 && i3 < 256)
                                {
                                    if (i3 != l3 - 1 && k2 != k3 && k2 != l1 - 1 && l2 != i4 && l2 != j2 - 1)
                                    {
                                        i3 = l3;
                                    }
                                }
                            }
                        }
                    }

                    if (true)
                    {
                        for (k2 = k3; k2 < l1; ++k2)
                        {
                            double d14 = (k2 + chunkX * 16 + 0.5D - par5) / d6;

                            for (int j4 = i4; j4 < j2; ++j4)
                            {
                                double d12 = (j4 + chunkZ * 16 + 0.5D - par7) / d6;

                                if (d14 * d14 + d12 * d12 < 1.0D)
                                {
                                    for (int j3 = i2; j3 > l3; --j3)
                                    {
                                        double d13 = (j3 - 1 + 0.5D - par6) / d7;

                                        if (d13 > -0.7D && d14 * d14 + d13 * d13 + d12 * d12 < 1.0D)
                                        {
                                            IBlockState state = chunk.getBlockState(k2, j3, j4);

                                            if (state.getBlock() == ChalosBlocks.CHEESE_DIRT || state.getBlock() == ChalosBlocks.CHEESE_COARSE_DIRT || state.getBlock() == ChalosBlocks.CHEESE_SLIME_BLOCK || state.getBlock() == ChalosBlocks.CHALOS_ROCK)
                                            {
                                                if (j3 < 10)
                                                {
                                                    chunk.setBlockState(k2, j3, j4, Blocks.LAVA.getDefaultState());
                                                }
                                                else
                                                {
                                                    chunk.setBlockState(k2, j3, j4, Blocks.AIR.getDefaultState());
                                                }
                                            }
                                            else if (state.getBlock() == ChalosBlocks.CHEESE_GRASS_BLOCK || state.getBlock() == ChalosBlocks.CHEESE_SLIME_BLOCK)
                                            {
                                                chunk.setBlockState(k2, j3, j4, Blocks.AIR.getDefaultState());
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (flag2)
                        {
                            break;
                        }
                    }
                }
            }
        }
    }

    @Override
    protected void recursiveGenerate(World world, int chunkX, int chunkZ, int oriX, int oriZ, ChunkPrimer chunk)
    {
        int i1 = this.rand.nextInt(this.rand.nextInt(this.rand.nextInt(15) + 1) + 1);

        if (this.rand.nextInt(7) != 0)
        {
            i1 = 0;
        }

        for (int j1 = 0; j1 < i1; ++j1)
        {
            double d0 = chunkX * 16 + this.rand.nextInt(16);
            double d1 = this.rand.nextInt(this.rand.nextInt(120) + 8);
            double d2 = chunkZ * 16 + this.rand.nextInt(16);
            int k1 = 1;

            if (this.rand.nextInt(4) == 0)
            {
                this.generateLargeCaveNode(this.rand.nextLong(), oriX, oriZ, chunk, d0, d1, d2);
                k1 += this.rand.nextInt(4);
            }

            for (int l1 = 0; l1 < k1; ++l1)
            {
                float f = this.rand.nextFloat() * (float)Math.PI * 2.0F;
                float f1 = (this.rand.nextFloat() - 0.5F) * 2.0F / 8.0F;
                float f2 = this.rand.nextFloat() * 2.0F + this.rand.nextFloat();

                if (this.rand.nextInt(10) == 0)
                {
                    f2 *= this.rand.nextFloat() * this.rand.nextFloat() * 3.0F + 1.0F;
                }
                this.generateCaveNode(this.rand.nextLong(), oriX, oriZ, chunk, d0, d1, d2, f2, f, f1, 0, 0, 1.0D);
            }
        }
    }
}