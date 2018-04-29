package stevekung.mods.moreplanets.blocks;

import micdoodle8.mods.galacticraft.api.block.IPartialSealableBlock;
import micdoodle8.mods.galacticraft.api.world.IGalacticraftWorldProvider;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.tileentity.TileEntitySpaceWarpPad;
import stevekung.mods.moreplanets.utils.ItemDescription;
import stevekung.mods.moreplanets.utils.blocks.BlockAdvancedTileMP;
import stevekung.mods.moreplanets.utils.blocks.EnumSortCategoryBlock;
import stevekung.mods.moreplanets.utils.blocks.IBlockDescription;
import stevekung.mods.moreplanets.utils.helper.ItemDescriptionHelper;
import stevekung.mods.stevekunglib.utils.ClientUtils;
import stevekung.mods.stevekunglib.utils.JsonUtils;
import stevekung.mods.stevekunglib.utils.LangUtils;

public class BlockSpaceWarpPad extends BlockAdvancedTileMP implements IPartialSealableBlock, IBlockDescription
{
    private static final AxisAlignedBB AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.1875D, 1.0D);

    public BlockSpaceWarpPad(String name)
    {
        super(Material.IRON);
        this.setHardness(3.0F);
        this.setResistance(10.0F);
        this.setSoundType(SoundType.METAL);
        this.setUnlocalizedName(name);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return BlockSpaceWarpPad.AABB;
    }

    @Override
    public boolean canPlaceBlockOnSide(World world, BlockPos pos, EnumFacing side)
    {
        if (!(GCCoreUtil.getDimensionID(world) == 0 || world.provider instanceof IGalacticraftWorldProvider))
        {
            ClientUtils.printClientMessage(JsonUtils.create(LangUtils.translate("gui.place_only_space.message")).setStyle(JsonUtils.red()));
            return false;
        }
        if (!this.checkAxis(world, pos, EnumFacing.EAST) || !this.checkAxis(world, pos, EnumFacing.WEST) || !this.checkAxis(world, pos, EnumFacing.NORTH) || !this.checkAxis(world, pos, EnumFacing.SOUTH))
        {
            return false;
        }
        if (world.getBlockState(pos.offset(EnumFacing.DOWN)).getBlock() == MPBlocks.SPACE_WARP_PAD && side == EnumFacing.UP)
        {
            return false;
        }
        else
        {
            return this.canPlaceBlockAt(world, pos);
        }
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing facing)
    {
        return BlockFaceShape.UNDEFINED;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TileEntitySpaceWarpPad();
    }

    @Override
    public boolean isSealed(World world, BlockPos pos, EnumFacing direction)
    {
        return direction == EnumFacing.UP;
    }

    @Override
    public EnumSortCategoryBlock getBlockCategory()
    {
        return EnumSortCategoryBlock.MACHINE_NON_BLOCK;
    }

    @Override
    public ItemDescription getDescription()
    {
        return (itemStack, list) -> list.addAll(ItemDescriptionHelper.getDescription(BlockSpaceWarpPad.this.getUnlocalizedName() + ".description"));
    }

    private boolean checkAxis(World world, BlockPos pos, EnumFacing facing)
    {
        int sameCount = 0;

        for (int i = 1; i <= 3; i++)
        {
            if (world.getBlockState(pos.offset(facing, i)).getBlock() == MPBlocks.SPACE_WARP_PAD)
            {
                sameCount++;
            }
        }
        return sameCount < 3;
    }
}