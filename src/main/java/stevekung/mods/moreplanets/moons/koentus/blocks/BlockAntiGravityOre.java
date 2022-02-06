package stevekung.mods.moreplanets.moons.koentus.blocks;

import java.util.Random;

import micdoodle8.mods.galacticraft.api.block.IDetectableResource;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.entity.EntityAntiGravFallingBlock;
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.utils.CompatibilityManagerMP;
import stevekung.mods.moreplanets.utils.blocks.BlockBasicMP;

public class BlockAntiGravityOre extends BlockBasicMP implements IDetectableResource
{
    public BlockAntiGravityOre(String name)
    {
        super(Material.ROCK);
        this.setHardness(3.0F);
        this.setTranslationKey(name);
    }

    @Override
    public boolean canRenderInLayer(IBlockState state, BlockRenderLayer layer)
    {
        return CompatibilityManagerMP.isCTMLoaded ? layer == BlockRenderLayer.CUTOUT : super.canRenderInLayer(state, layer);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return MPItems.ANTI_GRAVITY_FRAGMENTS;
    }

    @Override
    public boolean isValueable(IBlockState state)
    {
        return true;
    }

    @Override
    public int quantityDropped(IBlockState state, int fortune, Random rand)
    {
        int j = rand.nextInt(fortune + 2) - 1;

        if (j < 0)
        {
            j = 0;
        }
        return 1 + rand.nextInt(3) * j;
    }

    @Override
    public int getExpDrop(IBlockState state, IBlockAccess world, BlockPos pos, int fortune)
    {
        return MathHelper.getInt(RANDOM, 3, 7);
    }

    @Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state)
    {
        world.scheduleUpdate(pos, this, 2);
    }

    @Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos)
    {
        world.scheduleUpdate(pos, this, 2);
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        if (!world.isRemote)
        {
            this.checkFallable(world, pos);
        }
    }

    private void checkFallable(World world, BlockPos pos)
    {
        if ((world.isAirBlock(pos.up()) || BlockFalling.canFallThrough(world.getBlockState(pos.up()))) && pos.getY() < 256)
        {
            if (!BlockFalling.fallInstantly && world.isAreaLoaded(pos.add(-32, -32, -32), pos.add(32, 32, 32)))
            {
                if (!world.isRemote)
                {
                    EntityAntiGravFallingBlock block = new EntityAntiGravFallingBlock(world, pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, world.getBlockState(pos));
                    world.spawnEntity(block);
                }
            }
            else
            {
                IBlockState state = world.getBlockState(pos);
                world.setBlockToAir(pos);
                BlockPos blockpos;

                for (blockpos = pos.up(); (world.isAirBlock(blockpos) || BlockFalling.canFallThrough(world.getBlockState(blockpos))) && blockpos.getY() > 0; blockpos = blockpos.up()) {}

                if (blockpos.getY() < 256)
                {
                    world.setBlockState(blockpos.up(), state);
                }
            }
        }
    }
}