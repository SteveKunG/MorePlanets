package stevekung.mods.moreplanets.planets.nibiru.blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.utils.EnumParticleTypesMP;
import stevekung.mods.moreplanets.utils.blocks.BlockFluidBaseMP;
import stevekung.mods.moreplanets.utils.blocks.IFishableLiquidBlock;

public class BlockFluidPurifyWater extends BlockFluidBaseMP implements IFishableLiquidBlock
{
    public BlockFluidPurifyWater(String name)
    {
        super(MPBlocks.PURIFIED_WATER_FLUID);
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
            EntityLivingBase living = (EntityLivingBase) entity;
            living.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 80, 0));
        }
    }

    @Override
    protected EnumParticleTypesMP getDripParticle()
    {
        return EnumParticleTypesMP.PURIFY_WATER_DRIP;
    }
}