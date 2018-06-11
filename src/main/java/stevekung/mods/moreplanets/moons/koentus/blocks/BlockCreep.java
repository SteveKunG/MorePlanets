package stevekung.mods.moreplanets.moons.koentus.blocks;

import java.util.Random;

import net.minecraft.block.BlockFalling;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.entity.EntityAntiGravFallingBlock;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.moons.koentus.tileentity.TileEntityGravityExtractor;
import stevekung.mods.moreplanets.utils.CompatibilityManagerMP;
import stevekung.mods.moreplanets.utils.EnumParticleTypesMP;
import stevekung.mods.moreplanets.utils.blocks.BlockFallingMP;
import stevekung.mods.stevekunglib.utils.ColorUtils;

public class BlockCreep extends BlockFallingMP implements ITileEntityProvider
{
    private BlockType type;

    public BlockCreep(String name, BlockType type)
    {
        super(name, Material.CLAY);
        this.setSoundType(SoundType.SLIME);
        this.setHardness(0.6F);
        this.type = type;
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        if (!world.isRemote)
        {
            if (this.type == BlockType.CREEP_BLOCK)
            {
                this.checkFallable(world, pos);
            }
            else if (this.type == BlockType.GRAVITY_CREEP_BLOCK || this.type == BlockType.GRAVITY_CREEP_EXTRACTOR)
            {
                this.checkFloatable(world, pos);
            }
        }
    }

    @Override
    public boolean canRenderInLayer(IBlockState state, BlockRenderLayer layer)
    {
        return this.type != BlockType.CREEP_BLOCK && CompatibilityManagerMP.isCTMLoaded ? layer == BlockRenderLayer.CUTOUT : super.canRenderInLayer(state, layer);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getDustColor(IBlockState state)
    {
        Random rand = RANDOM;
        return this.type == BlockType.CREEP_BLOCK ? ColorUtils.rgbToDecimal(136, 70, 93) : this.type == BlockType.GRAVITY_CREEP_BLOCK || this.type == BlockType.GRAVITY_CREEP_EXTRACTOR ? rand.nextInt(3) == 0 ? ColorUtils.rgbToDecimal(88, 88, 88) : rand.nextInt(2) == 0 ? ColorUtils.rgbToDecimal(206, 105, 10) : ColorUtils.rgbToDecimal(16, 136, 207) : super.getDustColor(state);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand)
    {
        super.randomDisplayTick(state, world, pos, rand);

        if (this.type == BlockType.GRAVITY_CREEP_EXTRACTOR)
        {
            if (!world.getBlockState(pos.up()).isSideSolid(world, pos.up(), EnumFacing.UP))
            {
                for (int i = 0; i < 8; i++)
                {
                    double x = pos.getX() + rand.nextDouble();
                    double z = pos.getZ() + rand.nextDouble();
                    MorePlanetsMod.PROXY.spawnParticle(EnumParticleTypesMP.GRAVITY_HARVESTER, x, pos.getY(), z, new Object[] {true});
                }
            }
            if (!world.getBlockState(pos.down()).isSideSolid(world, pos.down(), EnumFacing.DOWN))
            {
                for (int i = 0; i < 8; i++)
                {
                    double x = pos.getX() + rand.nextDouble();
                    double z = pos.getZ() + rand.nextDouble();
                    MorePlanetsMod.PROXY.spawnParticle(EnumParticleTypesMP.GRAVITY_HARVESTER, x, pos.getY() - 1, z, new Object[] {false});
                }
            }
        }
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return this.type == BlockType.GRAVITY_CREEP_EXTRACTOR ? new TileEntityGravityExtractor() : null;
    }

    @Override
    public boolean hasTileEntity(IBlockState state)
    {
        return this.type == BlockType.GRAVITY_CREEP_EXTRACTOR ? true : false;
    }

    private void checkFallable(World world, BlockPos pos)
    {
        boolean north = world.getBlockState(pos.north()).getBlock() != MPBlocks.GRAVITY_CREEP_BLOCK && world.getBlockState(pos.north()).getBlock() != MPBlocks.GRAVITY_CREEP_EXTRACTOR;
        boolean east = world.getBlockState(pos.east()).getBlock() != MPBlocks.GRAVITY_CREEP_BLOCK && world.getBlockState(pos.east()).getBlock() != MPBlocks.GRAVITY_CREEP_EXTRACTOR;
        boolean south = world.getBlockState(pos.south()).getBlock() != MPBlocks.GRAVITY_CREEP_BLOCK && world.getBlockState(pos.south()).getBlock() != MPBlocks.GRAVITY_CREEP_EXTRACTOR;
        boolean west = world.getBlockState(pos.west()).getBlock() != MPBlocks.GRAVITY_CREEP_BLOCK && world.getBlockState(pos.west()).getBlock() != MPBlocks.GRAVITY_CREEP_EXTRACTOR;
        boolean up = world.getBlockState(pos.up()).getBlock() != MPBlocks.GRAVITY_CREEP_BLOCK && world.getBlockState(pos.up()).getBlock() != MPBlocks.GRAVITY_CREEP_EXTRACTOR;

        if ((world.isAirBlock(pos.down()) || world.getBlockState(pos.down()).getBlock() == MPBlocks.GRAVITY_CREEP_VINES || BlockFalling.canFallThrough(world.getBlockState(pos.down()))) && north && east && south && west && up && pos.getY() >= 0)
        {
            if (!fallInstantly && world.isAreaLoaded(pos.add(-32, -32, -32), pos.add(32, 32, 32)))
            {
                if (!world.isRemote)
                {
                    EntityFallingBlock block = new EntityFallingBlock(world, pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, world.getBlockState(pos));
                    this.onStartFalling(block);
                    world.spawnEntity(block);
                }
            }
            else
            {
                IBlockState state = world.getBlockState(pos);
                world.setBlockToAir(pos);
                BlockPos blockpos;

                for (blockpos = pos.down(); (world.isAirBlock(blockpos) || world.getBlockState(blockpos.down()).getBlock() == MPBlocks.GRAVITY_CREEP_VINES || BlockFalling.canFallThrough(world.getBlockState(blockpos))) &&
                        world.getBlockState(blockpos.north()).getBlock() != MPBlocks.GRAVITY_CREEP_BLOCK && world.getBlockState(blockpos.east()).getBlock() != MPBlocks.GRAVITY_CREEP_BLOCK && world.getBlockState(blockpos.south()).getBlock() != MPBlocks.GRAVITY_CREEP_BLOCK && world.getBlockState(blockpos.west()).getBlock() != MPBlocks.GRAVITY_CREEP_BLOCK && world.getBlockState(blockpos.up()).getBlock() != MPBlocks.GRAVITY_CREEP_BLOCK
                        && world.getBlockState(blockpos.north()).getBlock() != MPBlocks.GRAVITY_CREEP_EXTRACTOR && world.getBlockState(blockpos.east()).getBlock() != MPBlocks.GRAVITY_CREEP_EXTRACTOR && world.getBlockState(blockpos.south()).getBlock() != MPBlocks.GRAVITY_CREEP_EXTRACTOR && world.getBlockState(blockpos.west()).getBlock() != MPBlocks.GRAVITY_CREEP_EXTRACTOR && world.getBlockState(blockpos.up()).getBlock() != MPBlocks.GRAVITY_CREEP_EXTRACTOR
                        && blockpos.getY() > 0; blockpos = blockpos.down()) {}

                if (blockpos.getY() > 0)
                {
                    world.setBlockState(blockpos.up(), state);
                }
            }
        }
    }

    private void checkFloatable(World world, BlockPos pos)
    {
        boolean north = world.getBlockState(pos.north()).getBlock() != MPBlocks.CREEP_BLOCK;
        boolean east = world.getBlockState(pos.east()).getBlock() != MPBlocks.CREEP_BLOCK;
        boolean south = world.getBlockState(pos.south()).getBlock() != MPBlocks.CREEP_BLOCK;
        boolean west = world.getBlockState(pos.west()).getBlock() != MPBlocks.CREEP_BLOCK;
        boolean down = world.getBlockState(pos.down()).getBlock() != MPBlocks.CREEP_BLOCK;

        if ((world.isAirBlock(pos.up()) || BlockFalling.canFallThrough(world.getBlockState(pos.up()))) && north && east && south && west && down && pos.getY() < 256)
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

                for (blockpos = pos.up(); (world.isAirBlock(blockpos) || BlockFalling.canFallThrough(world.getBlockState(blockpos))) && world.getBlockState(blockpos.north()).getBlock() != MPBlocks.CREEP_BLOCK && world.getBlockState(blockpos.east()).getBlock() != MPBlocks.CREEP_BLOCK && world.getBlockState(blockpos.south()).getBlock() != MPBlocks.CREEP_BLOCK && world.getBlockState(blockpos.west()).getBlock() != MPBlocks.CREEP_BLOCK && world.getBlockState(blockpos.down()).getBlock() != MPBlocks.CREEP_BLOCK && blockpos.getY() > 0; blockpos = blockpos.up()) {}

                if (blockpos.getY() < 256)
                {
                    world.setBlockState(blockpos.up(), state);
                }
            }
        }
    }

    public static enum BlockType
    {
        CREEP_BLOCK,
        GRAVITY_CREEP_BLOCK,
        GRAVITY_CREEP_EXTRACTOR;
    }
}