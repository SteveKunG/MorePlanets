package stevekung.mods.moreplanets.module.planets.diona.blocks;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.WeightedRandomFishable;
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
        this.setRenderLayer(EnumWorldBlockLayer.TRANSLUCENT);
        this.setLightOpacity(3);
        this.setUnlocalizedName(name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        int meta = this.getMetaFromState(state);

        if (rand.nextInt(64) == 0)
        {
            if (meta > 0 && meta < 8)
            {
                world.playSound(pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, "liquid.water", rand.nextFloat() * 0.25F + 0.75F, rand.nextFloat() * 1.0F + 0.5F, false);
            }
            else if (rand.nextInt(10) == 0)
            {
                world.spawnParticle(EnumParticleTypes.SUSPENDED, pos.getX() + (double)rand.nextFloat(), pos.getY() + (double)rand.nextFloat(), pos.getZ() + (double)rand.nextFloat(), 0.0D, 0.0D, 0.0D);
            }
        }
        if (rand.nextInt(10) == 0 && World.doesBlockHaveSolidTopSurface(world, pos.down()) && !world.getBlockState(pos.down(2)).getBlock().getMaterial().blocksMovement())
        {
            double d5 = pos.getX() + rand.nextFloat();
            double d6 = pos.getY() - 1.05D;
            double d7 = pos.getZ() + rand.nextFloat();
            MorePlanetsCore.PROXY.spawnParticle(EnumParticleTypesMP.CRYSTALLIZE_WATER_DRIP, d5, d6, d7);
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