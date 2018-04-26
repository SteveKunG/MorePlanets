package stevekung.mods.moreplanets.blocks;

import java.util.Random;

import micdoodle8.mods.galacticraft.api.block.IPartialSealableBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.BlockNuclearWasteTank;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.module.planets.nibiru.items.NibiruItems;
import stevekung.mods.moreplanets.tileentity.TileEntityDarkEnergyReceiver;
import stevekung.mods.moreplanets.tileentity.TileEntityDummy;
import stevekung.mods.moreplanets.util.blocks.BlockContainerMP;

public class BlockDummy extends BlockContainerMP implements IPartialSealableBlock
{
    private static final AxisAlignedBB AABB_WARP_PAD = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.1875D, 1.0D);
    private static final AxisAlignedBB AABB_DER_SOLAR1 = new AxisAlignedBB(-0.25D, 0.3D, 0.3D, 0.5D, 0.6D, 0.7D);
    private static final AxisAlignedBB AABB_DER_SOLAR2 = new AxisAlignedBB(0.5D, 0.3D, 0.3D, 1.25D, 0.6D, 0.7D);
    private static final AxisAlignedBB AABB_DER_SOLAR3 = new AxisAlignedBB(0.3D, 0.3D, -0.25D, 0.7D, 0.6D, 0.5D);
    private static final AxisAlignedBB AABB_DER_SOLAR4 = new AxisAlignedBB(0.3D, 0.3D, 0.5D, 0.7D, 0.6D, 1.25D);
    private final BlockType type;

    public BlockDummy(String name, BlockType type)
    {
        super(Material.IRON);
        this.setHardness(1.0F);
        this.setSoundType(SoundType.METAL);
        this.setUnlocalizedName(name);
        this.setResistance(1000000000000000.0F);
        this.type = type;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        if (this.type == BlockType.WARP_PAD)
        {
            return BlockDummy.AABB_WARP_PAD;
        }
        else if (this.type == BlockType.DARK_ENERGY_SOLAR1)
        {
            return BlockDummy.AABB_DER_SOLAR1;
        }
        else if (this.type == BlockType.DARK_ENERGY_SOLAR2)
        {
            return BlockDummy.AABB_DER_SOLAR2;
        }
        else if (this.type == BlockType.DARK_ENERGY_SOLAR3)
        {
            return BlockDummy.AABB_DER_SOLAR3;
        }
        else if (this.type == BlockType.DARK_ENERGY_SOLAR4)
        {
            return BlockDummy.AABB_DER_SOLAR4;
        }
        else
        {
            return Block.FULL_BLOCK_AABB;
        }
    }

    @Override
    public boolean canDropFromExplosion(Explosion explosion)
    {
        return false;
    }

    @Override
    public float getBlockHardness(IBlockState state, World world, BlockPos pos)
    {
        TileEntity tileEntity = world.getTileEntity(pos);

        if (tileEntity instanceof TileEntityDummy)
        {
            BlockPos mainBlockPosition = ((TileEntityDummy) tileEntity).mainBlockPosition;

            if (mainBlockPosition != null && !mainBlockPosition.equals(pos))
            {
                TileEntity tileOther = world.getTileEntity(mainBlockPosition);

                if (tileOther instanceof TileEntityDarkEnergyReceiver)
                {
                    TileEntityDarkEnergyReceiver dark = (TileEntityDarkEnergyReceiver) tileOther;

                    if ((dark.activated || dark.failed) && !dark.successful)
                    {
                        return -1.0F;
                    }
                }
                return world.getBlockState(mainBlockPosition).getBlock().getBlockHardness(state, world, mainBlockPosition);
            }
        }
        return super.getBlockHardness(state, world, pos);
    }

    @Override
    public boolean isSealed(World world, BlockPos pos, EnumFacing facing)
    {
        if (this.type == BlockType.WARP_PAD)
        {
            return facing == EnumFacing.DOWN;
        }
        return false;
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state)
    {
        TileEntity tileEntity = world.getTileEntity(pos);

        if (tileEntity instanceof TileEntityDummy)
        {
            ((TileEntityDummy) tileEntity).onBlockRemoval();
        }
        super.breakBlock(world, pos, state);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        TileEntityDummy tileEntity = (TileEntityDummy) world.getTileEntity(pos);
        ItemStack heldStack = player.getHeldItem(hand);

        if (tileEntity == null)
        {
            return false;
        }
        if (this.type == BlockType.NUCLEAR_WASTE_TANK_MIDDLE && world.getBlockState(pos.down()) == NibiruBlocks.NUCLEAR_WASTE_TANK.getDefaultState().withProperty(BlockNuclearWasteTank.STATE, BlockNuclearWasteTank.BlockType.NONE))
        {
            if (!heldStack.isEmpty() && heldStack.getItem() == NibiruItems.WASTE_ROD_PICKER)
            {
                if (!player.capabilities.isCreativeMode)
                {
                    heldStack.damageItem(1, player);
                }
                Block.spawnAsEntity(world, pos, new ItemStack(NibiruItems.NUCLEAR_WASTE_ROD));
                world.setBlockState(pos.down(), NibiruBlocks.NUCLEAR_WASTE_TANK.getDefaultState().withProperty(BlockNuclearWasteTank.STATE, BlockNuclearWasteTank.BlockType.NO_ROD));
                return true;
            }
        }
        if (this.type == BlockType.NUCLEAR_WASTE_TANK_TOP && world.getBlockState(pos.down(2)) == NibiruBlocks.NUCLEAR_WASTE_TANK.getDefaultState().withProperty(BlockNuclearWasteTank.STATE, BlockNuclearWasteTank.BlockType.NONE))
        {
            if (!heldStack.isEmpty() && heldStack.getItem() == NibiruItems.WASTE_ROD_PICKER)
            {
                if (!player.capabilities.isCreativeMode)
                {
                    heldStack.damageItem(1, player);
                }
                Block.spawnAsEntity(world, pos, new ItemStack(NibiruItems.NUCLEAR_WASTE_ROD));
                world.setBlockState(pos.down(2), NibiruBlocks.NUCLEAR_WASTE_TANK.getDefaultState().withProperty(BlockNuclearWasteTank.STATE, BlockNuclearWasteTank.BlockType.NO_ROD));
                return true;
            }
        }
        return tileEntity.onBlockActivated(world, pos, player);
    }

    @Override
    public int quantityDropped(Random rand)
    {
        return 0;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state)
    {
        return new TileEntityDummy();
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
        TileEntity tileEntity = world.getTileEntity(pos);

        if (tileEntity instanceof TileEntityDummy)
        {
            BlockPos mainBlockPosition = ((TileEntityDummy) tileEntity).mainBlockPosition;

            if (mainBlockPosition != null && !mainBlockPosition.equals(pos))
            {
                Block mainBlock = world.getBlockState(mainBlockPosition).getBlock();

                if (Blocks.AIR != mainBlock)
                {
                    return mainBlock.getPickBlock(state, target, world, mainBlockPosition, player);
                }
            }
        }
        return ItemStack.EMPTY;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean addHitEffects(IBlockState state, World world, RayTraceResult target, ParticleManager manager)
    {
        TileEntity tileEntity = world.getTileEntity(target.getBlockPos());

        if (tileEntity instanceof TileEntityDummy)
        {
            BlockPos mainBlockPosition = ((TileEntityDummy) tileEntity).mainBlockPosition;

            if (mainBlockPosition != null && !mainBlockPosition.equals(target.getBlockPos()))
            {
                manager.addBlockHitEffects(mainBlockPosition, target);
            }
        }
        return super.addHitEffects(state, world, target, manager);
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing facing)
    {
        return this.type == BlockType.NUCLEAR_WASTE_TANK_TOP ? BlockFaceShape.SOLID : BlockFaceShape.UNDEFINED;
    }

    public void makeFakeBlock(World world, BlockPos pos, BlockPos mainBlock)
    {
        world.setBlockState(pos, this.getDefaultState(), 3);
        world.getTileEntity(pos).setWorld(world);
        ((TileEntityDummy) world.getTileEntity(pos)).setMainBlock(mainBlock);
    }

    public static enum BlockType implements IStringSerializable
    {
        WARP_PAD,
        DARK_ENERGY_SOLAR1,
        DARK_ENERGY_SOLAR2,
        DARK_ENERGY_SOLAR3,
        DARK_ENERGY_SOLAR4,
        NUCLEAR_WASTE_TANK_MIDDLE,
        NUCLEAR_WASTE_TANK_TOP,
        SHIELD_GENERATOR_TOP;

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