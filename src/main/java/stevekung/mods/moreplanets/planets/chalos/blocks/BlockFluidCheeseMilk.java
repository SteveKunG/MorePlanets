package stevekung.mods.moreplanets.planets.chalos.blocks;

import javax.annotation.Nullable;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.init.MPLootTables;
import stevekung.mods.moreplanets.utils.EnumParticleTypesMP;
import stevekung.mods.moreplanets.utils.blocks.BlockFluidBaseMP;
import stevekung.mods.moreplanets.utils.blocks.IFishableLiquidBlock;

public class BlockFluidCheeseMilk extends BlockFluidBaseMP implements IFishableLiquidBlock
{
    public BlockFluidCheeseMilk(String name)
    {
        super(MPBlocks.CHEESE_MILK_FLUID);
        this.isWater = true;
        this.setRenderLayer(BlockRenderLayer.TRANSLUCENT);
        this.setLightOpacity(3);
        this.setUnlocalizedName(name);
    }

    @Override
    public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity)
    {
        if (entity instanceof EntityLivingBase)
        {
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(MobEffects.SATURATION, 100));
        }
    }

    @Override
    @Nullable
    public ResourceLocation getLootTable()
    {
        return MPLootTables.CHEESE_MILK_FISHING;
    }

    @Override
    protected EnumParticleTypesMP getDripParticle()
    {
        return EnumParticleTypesMP.CHEESE_MILK_DRIP;
    }
}