package stevekung.mods.moreplanets.module.planets.diona.blocks;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.util.EnumParticleTypesMP;
import stevekung.mods.moreplanets.util.blocks.BlockFluidBaseMP;
import stevekung.mods.moreplanets.util.blocks.IFishableLiquidBlock;
import stevekung.mods.moreplanets.util.blocks.ISingleBlockRender;

public class BlockFluidCrystallizeWater extends BlockFluidBaseMP implements IFishableLiquidBlock, ISingleBlockRender
{
    public BlockFluidCrystallizeWater(String name)
    {
        super(DionaBlocks.CRYSTALLIZE_WATER_FLUID);
        this.setRenderLayer(BlockRenderLayer.TRANSLUCENT);
        this.setLightOpacity(3);
        this.setUnlocalizedName(name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand)
    {
        int meta = this.getMetaFromState(state);

        if (rand.nextInt(64) == 0)
        {
            if (meta > 0 && meta < 8)
            {
                world.playSound(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, SoundEvents.BLOCK_WATER_AMBIENT, SoundCategory.BLOCKS, rand.nextFloat() * 0.25F + 0.75F, rand.nextFloat() + 0.5F, false);
            }
            else if (rand.nextInt(10) == 0)
            {
                world.spawnParticle(EnumParticleTypes.SUSPENDED, pos.getX() + (double)rand.nextFloat(), pos.getY() + (double)rand.nextFloat(), pos.getZ() + (double)rand.nextFloat(), 0.0D, 0.0D, 0.0D);
            }
        }
        if (rand.nextInt(10) == 0 && world.getBlockState(pos.down()).isSideSolid(world, pos, EnumFacing.DOWN))
        {
            Material material = world.getBlockState(pos.down(2)).getMaterial();

            if (!material.blocksMovement() && !material.isLiquid())
            {
                double d5 = pos.getX() + rand.nextFloat();
                double d6 = pos.getY() - 1.05D;
                double d7 = pos.getZ() + rand.nextFloat();
                MorePlanetsCore.PROXY.spawnParticle(EnumParticleTypesMP.CRYSTALLIZE_WATER_DRIP, d5, d6, d7);
            }
        }
    }

    @Override
    protected boolean isInfinite()
    {
        return false;
    }

    @Override
    public String getName()
    {
        return "crystallize_water_fluid";
    }

    @Override
    public List<WeightedRandomFishable> getFishLoot()
    {
        List<WeightedRandomFishable> FISH = Arrays.asList(new WeightedRandomFishable[] {
                new WeightedRandomFishable(new ItemStack(MPItems.SPACE_FISH, 1, 0), 20),
                new WeightedRandomFishable(new ItemStack(MPItems.SPACE_FISH, 1, 1), 1),
        });
        return FISH;
    }
}