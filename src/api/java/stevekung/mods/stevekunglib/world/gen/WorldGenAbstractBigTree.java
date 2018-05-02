package stevekung.mods.stevekunglib.world.gen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import stevekung.mods.stevekunglib.utils.BlockStateProperty;

public abstract class WorldGenAbstractBigTree extends WorldGenAbstractTree
{
    private Random rand;
    private World world;
    private BlockPos pos = BlockPos.ORIGIN;
    private IBlockState leaves;
    private IBlockState log;
    protected boolean genLeaves = true;
    int heightLimit;
    int height;
    double heightAttenuation = 0.618D;
    double branchSlope = 0.381D;
    double scaleWidth = 1.0D;
    double leafDensity = 1.0D;
    int trunkSize = 1;
    int heightLimitLimit = 12;
    int leafDistanceLimit = 4;
    List<WorldGenAbstractBigTree.FoliageCoordinates> foliageCoords;

    public WorldGenAbstractBigTree(IBlockState log, IBlockState leaves)
    {
        super(log, leaves);
        this.log = log;
        this.leaves = leaves;
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
        this.world = world;
        this.pos = pos;
        this.rand = new Random(rand.nextLong());

        if (this.heightLimit == 0)
        {
            this.heightLimit = 5 + this.rand.nextInt(this.heightLimitLimit);
        }

        if (!this.validTreeLocation())
        {
            this.world = null;
            return false;
        }
        else
        {
            this.generateLeafNodeList();

            if (this.genLeaves)
            {
                this.generateLeaves();
            }

            this.generateTrunk();
            this.generateLeafNodeBases();
            this.world = null;
            return true;
        }
    }

    void generateLeafNodeList()
    {
        this.height = (int)(this.heightLimit * this.heightAttenuation);

        if (this.height >= this.heightLimit)
        {
            this.height = this.heightLimit - 1;
        }

        int i = (int)(1.382D + Math.pow(this.leafDensity * this.heightLimit / 13.0D, 2.0D));

        if (i < 1)
        {
            i = 1;
        }

        int j = this.pos.getY() + this.height;
        int k = this.heightLimit - this.leafDistanceLimit;
        this.foliageCoords = new ArrayList<>();
        this.foliageCoords.add(new WorldGenAbstractBigTree.FoliageCoordinates(this.pos.up(k), j));

        for (; k >= 0; --k)
        {
            float f = this.layerSize(k);

            if (f >= 0.0F)
            {
                for (int l = 0; l < i; ++l)
                {
                    double d0 = this.scaleWidth * f * (this.rand.nextFloat() + 0.328D);
                    double d1 = this.rand.nextFloat() * 2.0F * Math.PI;
                    double d2 = d0 * Math.sin(d1) + 0.5D;
                    double d3 = d0 * Math.cos(d1) + 0.5D;
                    BlockPos blockpos = this.pos.add(d2, k - 1, d3);
                    BlockPos blockpos1 = blockpos.up(this.leafDistanceLimit);

                    if (this.checkBlockLine(blockpos, blockpos1) == -1)
                    {
                        int i1 = this.pos.getX() - blockpos.getX();
                        int j1 = this.pos.getZ() - blockpos.getZ();
                        double d4 = blockpos.getY() - Math.sqrt(i1 * i1 + j1 * j1) * this.branchSlope;
                        int k1 = d4 > j ? j : (int)d4;
                        BlockPos blockpos2 = new BlockPos(this.pos.getX(), k1, this.pos.getZ());

                        if (this.checkBlockLine(blockpos2, blockpos) == -1)
                        {
                            this.foliageCoords.add(new WorldGenAbstractBigTree.FoliageCoordinates(blockpos, blockpos2.getY()));
                        }
                    }
                }
            }
        }
    }

    void crosSection(BlockPos pos, float leafSize, IBlockState placeState)
    {
        int i = (int)(leafSize + 0.618D);

        for (int j = -i; j <= i; ++j)
        {
            for (int k = -i; k <= i; ++k)
            {
                if (Math.pow(Math.abs(j) + 0.5D, 2.0D) + Math.pow(Math.abs(k) + 0.5D, 2.0D) <= leafSize * leafSize)
                {
                    BlockPos blockpos = pos.add(j, 0, k);
                    IBlockState state = this.world.getBlockState(blockpos);

                    if (state.getBlock().isAir(state, this.world, blockpos) || state.getBlock().isLeaves(state, this.world, blockpos))
                    {
                        this.setBlockAndNotifyAdequately(this.world, blockpos, placeState);
                    }
                }
            }
        }
    }

    float layerSize(int y)
    {
        if (y < this.heightLimit * 0.3F)
        {
            return -1.0F;
        }
        else
        {
            float f = this.heightLimit / 2.0F;
            float f1 = f - y;
            float f2 = MathHelper.sqrt(f * f - f1 * f1);

            if (f1 == 0.0F)
            {
                f2 = f;
            }
            else if (Math.abs(f1) >= f)
            {
                return 0.0F;
            }
            return f2 * 0.5F;
        }
    }

    float leafSize(int y)
    {
        if (y >= 0 && y < this.leafDistanceLimit)
        {
            return y != 0 && y != this.leafDistanceLimit - 1 ? 3.0F : 2.0F;
        }
        else
        {
            return -1.0F;
        }
    }

    void generateLeafNode(BlockPos pos)
    {
        for (int i = 0; i < this.leafDistanceLimit; ++i)
        {
            this.crosSection(pos.up(i), this.leafSize(i), this.leaves.withProperty(BlockStateProperty.CHECK_DECAY, false));
        }
    }

    void limb(BlockPos pos1, BlockPos pos2, IBlockState log)
    {
        BlockPos blockpos = pos2.add(-pos1.getX(), -pos1.getY(), -pos1.getZ());
        int i = this.getGreatestDistance(blockpos);
        float f = (float)blockpos.getX() / (float)i;
        float f1 = (float)blockpos.getY() / (float)i;
        float f2 = (float)blockpos.getZ() / (float)i;

        for (int j = 0; j <= i; ++j)
        {
            BlockPos blockpos1 = pos1.add(0.5F + j * f, 0.5F + j * f1, 0.5F + j * f2);
            BlockStateProperty.EnumAxis axis = this.getLogAxis(pos1, blockpos1);
            this.setBlockAndNotifyAdequately(this.world, blockpos1, log.withProperty(BlockStateProperty.AXIS, axis));
        }
    }

    private int getGreatestDistance(BlockPos pos)
    {
        int i = MathHelper.abs(pos.getX());
        int j = MathHelper.abs(pos.getY());
        int k = MathHelper.abs(pos.getZ());

        if (k > i && k > j)
        {
            return k;
        }
        else
        {
            return j > i ? j : i;
        }
    }

    private BlockStateProperty.EnumAxis getLogAxis(BlockPos pos1, BlockPos pos2)
    {
        BlockStateProperty.EnumAxis axis = BlockStateProperty.EnumAxis.Y;
        int i = Math.abs(pos2.getX() - pos1.getX());
        int j = Math.abs(pos2.getZ() - pos1.getZ());
        int k = Math.max(i, j);

        if (k > 0)
        {
            if (i == k)
            {
                axis = BlockStateProperty.EnumAxis.X;
            }
            else if (j == k)
            {
                axis = BlockStateProperty.EnumAxis.Z;
            }
        }
        return axis;
    }

    void generateLeaves()
    {
        for (WorldGenAbstractBigTree.FoliageCoordinates coord : this.foliageCoords)
        {
            this.generateLeafNode(coord);
        }
    }

    boolean leafNodeNeedsBase(int base)
    {
        return base >= this.heightLimit * 0.2D;
    }

    void generateTrunk()
    {
        BlockPos blockpos = this.pos;
        BlockPos blockpos1 = this.pos.up(this.height);
        this.limb(blockpos, blockpos1, this.log);

        if (this.trunkSize == 2)
        {
            this.limb(blockpos.east(), blockpos1.east(), this.log);
            this.limb(blockpos.east().south(), blockpos1.east().south(), this.log);
            this.limb(blockpos.south(), blockpos1.south(), this.log);
        }
    }

    void generateLeafNodeBases()
    {
        for (WorldGenAbstractBigTree.FoliageCoordinates coord : this.foliageCoords)
        {
            int i = coord.getBranchBase();
            BlockPos pos = new BlockPos(this.pos.getX(), i, this.pos.getZ());

            if (!pos.equals(coord) && this.leafNodeNeedsBase(i - this.pos.getY()))
            {
                this.limb(pos, coord, this.log);
            }
        }
    }

    int checkBlockLine(BlockPos posOne, BlockPos posTwo)
    {
        BlockPos blockpos = posTwo.add(-posOne.getX(), -posOne.getY(), -posOne.getZ());
        int i = this.getGreatestDistance(blockpos);
        float f = (float)blockpos.getX() / (float)i;
        float f1 = (float)blockpos.getY() / (float)i;
        float f2 = (float)blockpos.getZ() / (float)i;

        if (i == 0)
        {
            return -1;
        }
        else
        {
            for (int j = 0; j <= i; ++j)
            {
                BlockPos blockpos1 = posOne.add(0.5F + j * f, 0.5F + j * f1, 0.5F + j * f2);

                if (!this.isReplaceable(this.world, blockpos1))
                {
                    return j;
                }
            }
            return -1;
        }
    }

    @Override
    public void setDecorationDefaults()
    {
        this.leafDistanceLimit = 5;
    }

    private boolean validTreeLocation()
    {
        BlockPos down = this.pos.down();
        IBlockState state = this.world.getBlockState(down);
        boolean isSoil = this.isSoil(state.getBlock());

        if (!isSoil)
        {
            return false;
        }
        else
        {
            int i = this.checkBlockLine(this.pos, this.pos.up(this.heightLimit - 1));

            if (i == -1)
            {
                return true;
            }
            else if (i < 6)
            {
                return false;
            }
            else
            {
                this.heightLimit = i;
                return true;
            }
        }
    }

    static class FoliageCoordinates extends BlockPos
    {
        private final int branchBase;

        public FoliageCoordinates(BlockPos pos, int branchBase)
        {
            super(pos.getX(), pos.getY(), pos.getZ());
            this.branchBase = branchBase;
        }

        public int getBranchBase()
        {
            return this.branchBase;
        }
    }

    @Override
    protected abstract boolean isSoil(Block block);
}