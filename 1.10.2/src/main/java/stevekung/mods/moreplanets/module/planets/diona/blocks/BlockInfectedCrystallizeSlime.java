package stevekung.mods.moreplanets.module.planets.diona.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.init.MPSounds;
import stevekung.mods.moreplanets.util.blocks.BlockBreakableMP;
import stevekung.mods.moreplanets.util.blocks.EnumSortCategoryBlock;

public class BlockInfectedCrystallizeSlime extends BlockBreakableMP
{
    public BlockInfectedCrystallizeSlime(String name)
    {
        super(Material.CLAY);
        this.setUnlocalizedName(name);
        this.slipperiness = 0.8F;
        this.setSoundType(MPSounds.ALIEN_EGG);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
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
    public EnumSortCategoryBlock getBlockCategory(int meta)
    {
        return EnumSortCategoryBlock.DECORATION_BLOCK;
    }

    @Override
    protected boolean isTranslucentBlock()
    {
        return false;
    }

    @Override
    protected boolean renderSideOnOtherState()
    {
        return false;
    }

    @Override
    public String getName()
    {
        return "infected_crystallize_slime_block";
    }

    @Override
    public Block getBlock()
    {
        return this;
    }
}