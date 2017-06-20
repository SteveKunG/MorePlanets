package stevekung.mods.moreplanets.module.planets.diona.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.module.planets.diona.tileentity.TileEntityDarkEnergyCore;
import stevekung.mods.moreplanets.util.ItemDescription;
import stevekung.mods.moreplanets.util.blocks.BlockBaseMP;
import stevekung.mods.moreplanets.util.blocks.EnumSortCategoryBlock;
import stevekung.mods.moreplanets.util.blocks.IBlockDescription;
import stevekung.mods.moreplanets.util.helper.BlockSoundHelper;
import stevekung.mods.moreplanets.util.helper.ItemDescriptionHelper;

public class BlockDarkEnergyCore extends BlockBaseMP implements ITileEntityProvider, IBlockDescription
{
    public BlockDarkEnergyCore(String name)
    {
        super(Material.GROUND);
        this.setResistance(0.0F);
        this.setHardness(0.5F);
        this.setUnlocalizedName(name);
        this.setSoundType(BlockSoundHelper.ALIEN_EGG);
        this.slipperiness = 0.8F;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.62225D, 1.0D);
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
    public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player)
    {
        return true;
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
        }
    }

    @Override
    public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity)
    {
        if (Math.abs(entity.motionY) < 0.1D && !entity.isSneaking())
        {
            double d0 = 0.4D + Math.abs(entity.motionY) * 0.2D;
            entity.motionX *= d0;
            entity.motionZ *= d0;
        }
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TileEntityDarkEnergyCore();
    }

    @Override
    public EnumSortCategoryBlock getBlockCategory(int meta)
    {
        return EnumSortCategoryBlock.MACHINE_NON_BLOCK;
    }

    @Override
    public ItemDescription getDescription()
    {
        return new ItemDescription()
        {
            @Override
            public void addDescription(ItemStack itemStack, List list)
            {
                list.addAll(ItemDescriptionHelper.getDescription(BlockDarkEnergyCore.this.getUnlocalizedName() + ".description"));
            }
        };
    }

    @Override
    public String getName()
    {
        return "dark_energy_core";
    }

    @Override
    public Block getBlock()
    {
        return this;
    }
}