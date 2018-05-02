package stevekung.mods.moreplanets.module.planets.fronos.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.utils.ClientRendererUtils;
import stevekung.mods.moreplanets.utils.blocks.BlockGrassBlockMP;

public class BlockFronosGrass extends BlockGrassBlockMP
{
    private static final PropertyEnum<BlockType> HAS_LAYER = PropertyEnum.create("layer", BlockType.class);

    public BlockFronosGrass(String name)
    {
        super();
        this.setUnlocalizedName(name);
        this.setDefaultState(this.getDefaultState().withProperty(HAS_LAYER, BlockType.NONE));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        Block block = world.getBlockState(pos.up()).getBlock();

        if (block == Blocks.SNOW || block == Blocks.SNOW_LAYER)
        {
            state = state.withProperty(HAS_LAYER, BlockType.SNOW);
        }
        else
        {
            state = state.withProperty(HAS_LAYER, BlockType.NONE);
        }
        return state;
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        if (!world.isRemote)
        {
            if (world.getLightFromNeighbors(pos.up()) < 4 && world.getBlockLightOpacity(pos.up()) > 2)
            {
                world.setBlockState(pos, FronosBlocks.FRONOS_DIRT.getDefaultState());
            }
            else if (world.getLightFromNeighbors(pos.up()) >= 9)
            {
                for (int i = 0; i < 4; ++i)
                {
                    BlockPos pos1 = pos.add(rand.nextInt(3) - 1, rand.nextInt(5) - 3, rand.nextInt(3) - 1);

                    if (world.getBlockState(pos1) == FronosBlocks.FRONOS_DIRT.getDefaultState())
                    {
                        if (world.getLightFromNeighbors(pos1.up()) >= 4 && world.getBlockState(pos1.up()).getLightOpacity(world, pos1.up()) <= 2)
                        {
                            world.setBlockState(pos1, FronosBlocks.FRONOS_GRASS_BLOCK.getDefaultState());
                        }
                    }
                }
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean addHitEffects(IBlockState state, World world, RayTraceResult target, ParticleManager manager)
    {
        ClientRendererUtils.addBlockHitEffects(world, target.getBlockPos(), target.sideHit, manager);
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean addDestroyEffects(World world, BlockPos pos, ParticleManager manager)
    {
        ClientRendererUtils.addBlockDestroyEffects(world, pos, this.getDefaultState(), manager);
        return true;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(FronosBlocks.FRONOS_DIRT);
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, HAS_LAYER);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return 0;
    }

    public static enum BlockType implements IStringSerializable
    {
        NONE,
        SNOW,
        CREAM;

        @Override
        public String toString()
        {
            return this.getName().toLowerCase();
        }

        @Override
        public String getName()
        {
            return this.name().toLowerCase();
        }
    }
}