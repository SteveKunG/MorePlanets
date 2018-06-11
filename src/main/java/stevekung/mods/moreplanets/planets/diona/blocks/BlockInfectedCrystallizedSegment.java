package stevekung.mods.moreplanets.planets.diona.blocks;

import java.util.Random;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.init.MPSounds;
import stevekung.mods.moreplanets.planets.diona.tileentity.TileEntityInfectedCrystallizedEnderCore;
import stevekung.mods.moreplanets.utils.CompatibilityManagerMP;
import stevekung.mods.moreplanets.utils.blocks.BlockBaseMP;
import stevekung.mods.moreplanets.utils.blocks.EnumSortCategoryBlock;
import stevekung.mods.moreplanets.utils.itemblocks.IItemRarity;
import stevekung.mods.stevekunglib.utils.ColorUtils;

public class BlockInfectedCrystallizedSegment extends BlockBaseMP implements ITileEntityProvider
{
    private BlockType type;

    public BlockInfectedCrystallizedSegment(String name, BlockType type)
    {
        super(Material.GROUND);
        this.setHardness(0.5F);
        this.setResistance(50.0F);
        this.setUnlocalizedName(name);
        this.setSoundType(MPSounds.ALIEN_EGG);
        this.setDefaultSlipperiness(0.8F);
        this.type = type;
    }

    @Override
    public void onFallenUpon(World world, BlockPos pos, Entity entity, float fallDistance)
    {
        if (entity.isSneaking())
        {
            super.onFallenUpon(world, pos, entity, fallDistance);
        }
        else
        {
            entity.fall(fallDistance, 0.0F);
        }
    }

    @Override
    public void onLanded(World world, Entity entity)
    {
        if (entity.isSneaking())
        {
            super.onLanded(world, entity);
        }
        else if (entity.motionY < 0.0D)
        {
            entity.motionY = -entity.motionY;

            if (!(entity instanceof EntityLivingBase))
            {
                entity.motionY *= 0.8D;
            }
        }
    }

    @Override
    public void onEntityWalk(World world, BlockPos pos, Entity entity)
    {
        if (Math.abs(entity.motionY) < 0.1D && !entity.isSneaking())
        {
            double d0 = 0.4D + Math.abs(entity.motionY) * 0.2D;
            entity.motionX *= d0;
            entity.motionZ *= d0;
        }
    }

    @Override
    public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player)
    {
        return true;
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
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return new AxisAlignedBB(0.1875D, 0.0D, 0.1875D, 0.8125D, 1.0D, 0.8125D);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return this.type != BlockType.INFECTED_CRYSTALLIZED_SEGMENT ? Item.getItemFromBlock(MPBlocks.INFECTED_CRYSTALLIZED_SEGMENT) : Item.getItemFromBlock(this);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack heldItem = player.getHeldItem(hand);

        if (this.type == BlockType.INFECTED_CRYSTALLIZED_SEGMENT)
        {
            if (!heldItem.isEmpty())
            {
                if (heldItem.getItem() == MPItems.INFECTED_CRYSTALLIZED_SLIMEBALL)
                {
                    if (!player.capabilities.isCreativeMode)
                    {
                        heldItem.shrink(1);
                    }
                    world.setBlockState(pos, MPBlocks.INFECTED_CRYSTALLIZED_EYE_CORE.getDefaultState());
                    return true;
                }
                else if (heldItem.getItem() == Items.ENDER_EYE)
                {
                    if (!player.capabilities.isCreativeMode)
                    {
                        heldItem.shrink(1);
                    }
                    world.setBlockState(pos, MPBlocks.INFECTED_CRYSTALLIZED_ENDER_CORE.getDefaultState());
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean canHarvestBlock(IBlockAccess world, BlockPos pos, EntityPlayer player)
    {
        return true;
    }

    @Override
    public boolean canRenderInLayer(IBlockState state, BlockRenderLayer layer)
    {
        return CompatibilityManagerMP.isCTMLoaded && this.type != BlockType.INFECTED_CRYSTALLIZED_SEGMENT ? layer == BlockRenderLayer.CUTOUT : layer == BlockRenderLayer.SOLID;
    }

    @Override
    public EnumSortCategoryBlock getBlockCategory()
    {
        return EnumSortCategoryBlock.DECORATION_NON_BLOCK;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return this.type == BlockType.INFECTED_CRYSTALLIZED_ENDER_CORE ? new TileEntityInfectedCrystallizedEnderCore() : null;
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing facing)
    {
        return BlockFaceShape.MIDDLE_POLE;
    }

    @Override
    public ColorUtils.RGB getRarity()
    {
        return ColorUtils.stringToFullRGB(IItemRarity.ALIEN);
    }

    public static enum BlockType
    {
        INFECTED_CRYSTALLIZED_SEGMENT,
        INFECTED_CRYSTALLIZED_EYE_CORE,
        INFECTED_CRYSTALLIZED_ENDER_CORE;

        @Override
        public String toString()
        {
            return this.name().toLowerCase();
        }
    }
}