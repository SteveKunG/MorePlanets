package stevekung.mods.stevekunglib.world.gen;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import com.google.common.base.MoreObjects;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.MapGenBase;

public class MapGenCavesBase extends MapGenBase
{
    private IBlockState top = Blocks.GRASS.getDefaultState();
    private IBlockState lava = Blocks.LAVA.getDefaultState();
    private Set<Block> topBlock = new HashSet<>();
    private Set<Block> digBlock = new HashSet<>();
    private Set<Block> fluidBlock = new HashSet<>();

    public MapGenCavesBase(Set<Block> topBlock, IBlockState lava, Set<Block> digBlock, Set<Block> fluidBlock)
    {
        this.topBlock = topBlock;
        this.lava = lava;
        this.digBlock = digBlock;
        this.fluidBlock = fluidBlock;
    }

    public MapGenCavesBase(IBlockState top, IBlockState lava, Set<Block> digBlock)
    {
        this(top, lava, digBlock, new HashSet<>());
    }

    public MapGenCavesBase(IBlockState top, IBlockState lava, Set<Block> digBlock, Set<Block> fluidBlock)
    {
        this.top = top;
        this.lava = lava;
        this.digBlock = digBlock;
        this.fluidBlock = fluidBlock;
    }

    @Override
    protected void recursiveGenerate(World world, int chunkX, int chunkZ, int originalX, int originalZ, ChunkPrimer primer)
    {
        int i = this.rand.nextInt(this.rand.nextInt(this.rand.nextInt(15) + 1) + 1);

        if (this.rand.nextInt(7) != 0)
        {
            i = 0;
        }

        for (int j = 0; j < i; ++j)
        {
            double d0 = chunkX * 16 + this.rand.nextInt(16);
            double d1 = this.rand.nextInt(this.rand.nextInt(120) + 8);
            double d2 = chunkZ * 16 + this.rand.nextInt(16);
            int k = 1;

            if (this.rand.nextInt(4) == 0)
            {
                this.addRoom(this.rand.nextLong(), originalX, originalZ, primer, d0, d1, d2);
                k += this.rand.nextInt(4);
            }
            for (int l = 0; l < k; ++l)
            {
                float f = this.rand.nextFloat() * ((float)Math.PI * 2F);
                float f1 = (this.rand.nextFloat() - 0.5F) * 2.0F / 8.0F;
                float f2 = this.rand.nextFloat() * 2.0F + this.rand.nextFloat();

                if (this.rand.nextInt(10) == 0)
                {
                    f2 *= this.rand.nextFloat() * this.rand.nextFloat() * 3.0F + 1.0F;
                }
                this.addTunnel(this.rand.nextLong(), originalX, originalZ, primer, d0, d1, d2, f2, f, f1, 0, 0, 1.0D);
            }
        }
    }

    private void addRoom(long seed, int originalX, int originalZ, ChunkPrimer primer, double x, double y, double z)
    {
        this.addTunnel(seed, originalX, originalZ, primer, x, y, z, 1.0F + this.rand.nextFloat() * 6.0F, 0.0F, 0.0F, -1, -1, 0.5D);
    }

    private void addTunnel(long seed, int originalX, int originalZ, ChunkPrimer primer, double x, double y, double z, float p_180702_12_, float p_180702_13_, float p_180702_14_, int p_180702_15_, int p_180702_16_, double p_180702_17_)
    {
        double d0 = originalX * 16 + 8;
        double d1 = originalZ * 16 + 8;
        float f = 0.0F;
        float f1 = 0.0F;
        Random rand = new Random(seed);

        if (p_180702_16_ <= 0)
        {
            int i = this.range * 16 - 16;
            p_180702_16_ = i - rand.nextInt(i / 4);
        }

        boolean flag2 = false;

        if (p_180702_15_ == -1)
        {
            p_180702_15_ = p_180702_16_ / 2;
            flag2 = true;
        }

        int j = rand.nextInt(p_180702_16_ / 2) + p_180702_16_ / 4;

        for (boolean flag = rand.nextInt(6) == 0; p_180702_15_ < p_180702_16_; ++p_180702_15_)
        {
            double d2 = 1.5D + MathHelper.sin(p_180702_15_ * (float)Math.PI / p_180702_16_) * p_180702_12_;
            double d3 = d2 * p_180702_17_;
            float f2 = MathHelper.cos(p_180702_14_);
            float f3 = MathHelper.sin(p_180702_14_);
            x += MathHelper.cos(p_180702_13_) * f2;
            y += f3;
            z += MathHelper.sin(p_180702_13_) * f2;

            if (flag)
            {
                p_180702_14_ = p_180702_14_ * 0.92F;
            }
            else
            {
                p_180702_14_ = p_180702_14_ * 0.7F;
            }

            p_180702_14_ = p_180702_14_ + f1 * 0.1F;
            p_180702_13_ += f * 0.1F;
            f1 = f1 * 0.9F;
            f = f * 0.75F;
            f1 = f1 + (rand.nextFloat() - rand.nextFloat()) * rand.nextFloat() * 2.0F;
            f = f + (rand.nextFloat() - rand.nextFloat()) * rand.nextFloat() * 4.0F;

            if (!flag2 && p_180702_15_ == j && p_180702_12_ > 1.0F && p_180702_16_ > 0)
            {
                this.addTunnel(rand.nextLong(), originalX, originalZ, primer, x, y, z, rand.nextFloat() * 0.5F + 0.5F, p_180702_13_ - (float)Math.PI / 2F, p_180702_14_ / 3.0F, p_180702_15_, p_180702_16_, 1.0D);
                this.addTunnel(rand.nextLong(), originalX, originalZ, primer, x, y, z, rand.nextFloat() * 0.5F + 0.5F, p_180702_13_ + (float)Math.PI / 2F, p_180702_14_ / 3.0F, p_180702_15_, p_180702_16_, 1.0D);
                return;
            }

            if (flag2 || rand.nextInt(4) != 0)
            {
                double d4 = x - d0;
                double d5 = z - d1;
                double d6 = p_180702_16_ - p_180702_15_;
                double d7 = p_180702_12_ + 2.0F + 16.0F;

                if (d4 * d4 + d5 * d5 - d6 * d6 > d7 * d7)
                {
                    return;
                }

                if (x >= d0 - 16.0D - d2 * 2.0D && z >= d1 - 16.0D - d2 * 2.0D && x <= d0 + 16.0D + d2 * 2.0D && z <= d1 + 16.0D + d2 * 2.0D)
                {
                    int k2 = MathHelper.floor(x - d2) - originalX * 16 - 1;
                    int k = MathHelper.floor(x + d2) - originalX * 16 + 1;
                    int l2 = MathHelper.floor(y - d3) - 1;
                    int l = MathHelper.floor(y + d3) + 1;
                    int i3 = MathHelper.floor(z - d2) - originalZ * 16 - 1;
                    int i1 = MathHelper.floor(z + d2) - originalZ * 16 + 1;

                    if (k2 < 0)
                    {
                        k2 = 0;
                    }
                    if (k > 16)
                    {
                        k = 16;
                    }
                    if (l2 < 1)
                    {
                        l2 = 1;
                    }
                    if (l > 248)
                    {
                        l = 248;
                    }
                    if (i3 < 0)
                    {
                        i3 = 0;
                    }
                    if (i1 > 16)
                    {
                        i1 = 16;
                    }

                    boolean flag3 = false;

                    for (int j1 = k2; !flag3 && j1 < k; ++j1)
                    {
                        for (int k1 = i3; !flag3 && k1 < i1; ++k1)
                        {
                            for (int l1 = l + 1; !flag3 && l1 >= l2 - 1; --l1)
                            {
                                if (l1 >= 0 && l1 < 256)
                                {
                                    if (this.isOceanBlock(primer, j1, l1, k1, originalX, originalZ))
                                    {
                                        flag3 = true;
                                    }
                                    if (l1 != l2 - 1 && j1 != k2 && j1 != k - 1 && k1 != i3 && k1 != i1 - 1)
                                    {
                                        l1 = l2;
                                    }
                                }
                            }
                        }
                    }

                    if (!flag3)
                    {
                        for (int j3 = k2; j3 < k; ++j3)
                        {
                            double d10 = (j3 + originalX * 16 + 0.5D - x) / d2;

                            for (int i2 = i3; i2 < i1; ++i2)
                            {
                                double d8 = (i2 + originalZ * 16 + 0.5D - z) / d2;
                                boolean flag1 = false;

                                if (d10 * d10 + d8 * d8 < 1.0D)
                                {
                                    for (int j2 = l; j2 > l2; --j2)
                                    {
                                        double d9 = (j2 - 1 + 0.5D - y) / d3;

                                        if (d9 > -0.7D && d10 * d10 + d9 * d9 + d8 * d8 < 1.0D)
                                        {
                                            IBlockState iblockstate1 = primer.getBlockState(j3, j2, i2);
                                            IBlockState iblockstate2 = MoreObjects.firstNonNull(primer.getBlockState(j3, j2 + 1, i2), Blocks.AIR.getDefaultState());

                                            if (this.isTopBlock(primer, j3, j2, i2, originalX, originalZ))
                                            {
                                                flag1 = true;
                                            }
                                            this.digBlock(primer, j3, j2, i2, originalX, originalZ, flag1, iblockstate1, iblockstate2);
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

    private boolean canReplaceBlock(IBlockState state, IBlockState up)
    {
        for (Block block : this.digBlock)
        {
            if (state.getBlock().equals(block))
            {
                return true;
            }
        }
        return state.getBlock() == this.top.getBlock();
    }

    private boolean isOceanBlock(ChunkPrimer primer, int x, int y, int z, int chunkX, int chunkZ)
    {
        Block block = primer.getBlockState(x, y, z).getBlock();

        if (this.fluidBlock.isEmpty())
        {
            return false;
        }
        else
        {
            for (Block fluidBlock : this.fluidBlock)
            {
                if (fluidBlock.equals(block))
                {
                    return true;
                }
            }
        }
        return block == Blocks.FLOWING_WATER || block == Blocks.WATER;
    }

    private boolean isTopBlock(ChunkPrimer primer, int x, int y, int z, int chunkX, int chunkZ)
    {
        IBlockState state = primer.getBlockState(x, y, z);

        if (this.topBlock.isEmpty())
        {
            return false;
        }
        else
        {
            for (Block topBlock : this.topBlock)
            {
                if (topBlock.equals(state.getBlock()))
                {
                    return true;
                }
            }
        }
        return state.getBlock() == this.top.getBlock();
    }

    private void digBlock(ChunkPrimer primer, int x, int y, int z, int chunkX, int chunkZ, boolean foundTop, IBlockState state, IBlockState up)
    {
        Biome biome = this.world.getBiome(new BlockPos(x + chunkX * 16, 0, z + chunkZ * 16));
        IBlockState top = biome.topBlock;
        IBlockState filler = biome.fillerBlock;

        if (this.canReplaceBlock(state, up) || state.getBlock() == top.getBlock() || state.getBlock() == filler.getBlock())
        {
            if (y - 1 < 10)
            {
                primer.setBlockState(x, y, z, this.lava);
            }
            else
            {
                primer.setBlockState(x, y, z, Blocks.AIR.getDefaultState());

                if (foundTop && primer.getBlockState(x, y - 1, z).getBlock() == filler.getBlock())
                {
                    primer.setBlockState(x, y - 1, z, top.getBlock().getDefaultState());
                }
            }
        }
    }
}