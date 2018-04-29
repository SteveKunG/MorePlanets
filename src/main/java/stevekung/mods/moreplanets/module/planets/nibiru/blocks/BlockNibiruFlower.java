package stevekung.mods.moreplanets.module.planets.nibiru.blocks;

import java.util.Random;

import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature.WorldGenTerrashroom;
import stevekung.mods.moreplanets.utils.CompatibilityManagerMP;
import stevekung.mods.moreplanets.utils.VariantsName;
import stevekung.mods.moreplanets.utils.blocks.BlockBushMP;
import stevekung.mods.moreplanets.utils.blocks.IBlockVariants;

public class BlockNibiruFlower extends BlockBushMP implements IBlockVariants, IGrowable
{
    public static PropertyEnum<BlockType> VARIANT = PropertyEnum.create("variant", BlockType.class);

    public BlockNibiruFlower(String name)
    {
        super(Material.PLANTS);
        this.setUnlocalizedName(name);
        this.setDefaultState(this.getDefaultState().withProperty(VARIANT, BlockType.PURE_HURB));
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        int meta = this.getMetaFromState(state);

        if (meta == 0)
        {
            return new AxisAlignedBB(0.3D, 0.0D, 0.3D, 0.7D, 0.6D, 0.7D);
        }
        else if (meta == 4)
        {
            return new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 0.6D, 0.75D);
        }
        else if (meta == 5)
        {
            return new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 0.9D, 0.75D);
        }
        else if (meta == 6)
        {
            return new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 1.0D, 0.75D);
        }
        else if (meta == 7)
        {
            return new AxisAlignedBB(0.3D, 0.0D, 0.3D, 0.7D, 0.3D * 2.0D, 0.7D);
        }
        else
        {
            return new AxisAlignedBB(0.3D, 0.0D, 0.3D, 0.7D, 0.8D, 0.7D);
        }
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        if (this.getMetaFromState(state) == 7 && rand.nextInt(25) == 0)
        {
            int i = 5;

            for (BlockPos blockpos : BlockPos.getAllInBoxMutable(pos.add(-4, -1, -4), pos.add(4, 1, 4)))
            {
                if (world.getBlockState(blockpos).getBlock() == this)
                {
                    --i;

                    if (i <= 0)
                    {
                        return;
                    }
                }
            }

            BlockPos blockpos1 = pos.add(rand.nextInt(3) - 1, rand.nextInt(2) - rand.nextInt(2), rand.nextInt(3) - 1);

            for (int k = 0; k < 4; ++k)
            {
                if (world.isAirBlock(blockpos1) && this.canBlockStay(world, blockpos1, this.getStateFromMeta(7)))
                {
                    pos = blockpos1;
                }
                blockpos1 = pos.add(rand.nextInt(3) - 1, rand.nextInt(2) - rand.nextInt(2), rand.nextInt(3) - 1);
            }
            if (world.isAirBlock(blockpos1) && this.canBlockStay(world, blockpos1, this.getStateFromMeta(7)))
            {
                world.setBlockState(blockpos1, this.getStateFromMeta(7), 2);
            }
        }
    }

    @Override
    public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        int meta = this.getMetaFromState(state);

        if (meta == 0)
        {
            return 2;
        }
        else if (meta == 1 || meta == 6)
        {
            return 4;
        }
        else if (meta == 7)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }

    @Override
    public void getSubBlocks(CreativeTabs creativeTabs, NonNullList<ItemStack> list)
    {
        for (int i = 0; i < BlockType.valuesCached().length; ++i)
        {
            list.add(new ItemStack(this, 1, i));
        }
    }

    @Override
    public boolean canBlockStay(World world, BlockPos pos, IBlockState state)
    {
        IBlockState blockDown = world.getBlockState(pos.down());
        IBlockState blockUp = world.getBlockState(pos.up());
        int meta = this.getMetaFromState(state);

        if (meta == 1)
        {
            return blockDown.getBlock() == NibiruBlocks.GREEN_VEIN_GRASS_BLOCK || blockDown.getBlock() == NibiruBlocks.INFECTED_DIRT;
        }
        else if (meta == 2)
        {
            return blockDown.getBlock() == NibiruBlocks.INFECTED_SAND;
        }
        else if (meta == 6)
        {
            return blockUp.getBlock() == NibiruBlocks.NIBIRU_LEAVES && blockUp.getValue(BlockNibiruLeaves.VARIANT) == BlockNibiruLeaves.BlockType.INFECTED_JUNGLE_LEAVES || blockUp.getBlock() == this && blockUp.getValue(BlockNibiruFlower.VARIANT) == BlockNibiruFlower.BlockType.VEALIUM_VINE_FLOWER;
        }
        else if (meta == 7)
        {
            boolean lowlight = world.getLight(pos) < 13 && (blockDown.getBlock() == NibiruBlocks.NIBIRU_ROCK || blockDown.getBlock() == NibiruBlocks.INFECTED_DIRT || blockDown.getBlock() == NibiruBlocks.NIBIRU_ORE);
            return blockDown.getBlock() == NibiruBlocks.GREEN_VEIN_GRASS_BLOCK || blockDown.getBlock() == NibiruBlocks.TERRASTONE || blockDown.getBlock() == NibiruBlocks.PURIFY_GRAVEL || lowlight;
        }
        else
        {
            return blockDown.getBlock() == NibiruBlocks.INFECTED_GRASS_BLOCK || blockDown.getBlock() == NibiruBlocks.INFECTED_DIRT;
        }
    }

    @Override
    public boolean canGrow(World world, BlockPos pos, IBlockState state, boolean isClient)
    {
        return state.getValue(VARIANT) == BlockType.TERRASHROOM;
    }

    @Override
    public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state)
    {
        return rand.nextFloat() < 0.4D;
    }

    @Override
    public void grow(World world, Random rand, BlockPos pos, IBlockState state)
    {
        this.generateBigMushroom(world, pos, state, rand);
    }

    private boolean generateBigMushroom(World world, BlockPos pos, IBlockState state, Random rand)
    {
        world.setBlockToAir(pos);
        WorldGenerator worldgenerator = new WorldGenTerrashroom();

        if (worldgenerator.generate(world, rand, pos))
        {
            return true;
        }
        else
        {
            world.setBlockState(pos, state, 3);
            return false;
        }
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return this.getMetaFromState(state);
    }

    @Override
    public EnumOffsetType getOffsetType()
    {
        return EnumOffsetType.XZ;
    }

    @Override
    public boolean canRenderInLayer(IBlockState state, BlockRenderLayer layer)
    {
        return state.getValue(VARIANT) == BlockType.TERRAPUFF_HURB && CompatibilityManagerMP.isCTMLoaded ? layer == BlockRenderLayer.CUTOUT : state.getValue(VARIANT) == BlockType.TERRASHROOM ? layer == BlockRenderLayer.TRANSLUCENT : super.canRenderInLayer(state, layer);
    }

    @Override
    public VariantsName getVariantsName()
    {
        return new VariantsName("pure_hurb", "terrapuff_hurb", "batasia_dandelion", "pyolonia_flower", "philipy_flower", "white_tail", "vealium_vine_flower", "terrashroom");
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, VARIANT);
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, BlockType.valuesCached()[meta]);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(VARIANT).ordinal();
    }

    public static enum BlockType implements IStringSerializable
    {
        PURE_HURB,
        TERRAPUFF_HURB,
        BATASIA_DANDELION,
        PYOLONIA_FLOWER,
        PHILIPY_FLOWER,
        WHITE_TAIL,
        VEALIUM_VINE_FLOWER,
        TERRASHROOM;

        private static BlockType[] values = BlockType.values();

        public static BlockType[] valuesCached()
        {
            return BlockType.values;
        }

        @Override
        public String toString()
        {
            return this.name().toLowerCase();
        }

        @Override
        public String getName()
        {
            return this.name().toLowerCase();
        }
    }
}