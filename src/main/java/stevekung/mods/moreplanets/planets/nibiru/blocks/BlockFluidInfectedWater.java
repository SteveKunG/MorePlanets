package stevekung.mods.moreplanets.planets.nibiru.blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.init.MPPotions;
import stevekung.mods.moreplanets.utils.EntityEffectUtils;
import stevekung.mods.moreplanets.utils.EnumParticleTypesMP;
import stevekung.mods.moreplanets.utils.blocks.BlockFluidBaseMP;
import stevekung.mods.moreplanets.utils.blocks.IFishableLiquidBlock;
import stevekung.mods.moreplanets.utils.entity.ISpaceMob;
import stevekung.mods.moreplanets.utils.entity.ISpaceMob.EnumMobType;

public class BlockFluidInfectedWater extends BlockFluidBaseMP implements IFishableLiquidBlock
{
    public BlockFluidInfectedWater(String name)
    {
        super(MPBlocks.INFECTED_WATER_FLUID);
        this.isWater = true;
        this.setRenderLayer(BlockRenderLayer.TRANSLUCENT);
        this.setLightOpacity(3);
        this.setUnlocalizedName(name);
    }

    @Override
    public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity)
    {
        if (entity instanceof EntityLivingBase && !EntityEffectUtils.isGalacticraftMob(entity) && !(entity instanceof ISpaceMob && ((ISpaceMob)entity).getMobType() == EnumMobType.NIBIRU))
        {
            EntityLivingBase living = (EntityLivingBase) entity;

            if (living instanceof EntityPlayer)
            {
                EntityPlayer player = (EntityPlayer) living;

                if (!player.capabilities.isCreativeMode && !player.isPotionActive(MPPotions.INFECTED_SPORE_PROTECTION))
                {
                    player.addPotionEffect(new PotionEffect(MPPotions.INFECTED_SPORE, 80));
                }
            }
            if (!(living instanceof EntityPlayer))
            {
                living.addPotionEffect(new PotionEffect(MPPotions.INFECTED_SPORE, 80, 0));
            }
        }
        if (entity instanceof EntityItem)
        {
            EntityItem item = (EntityItem) entity;

            if (item.getItem().getItem() == Items.ARROW)
            {
                item.setItem(new ItemStack(MPItems.INFECTED_ARROW, item.getItem().getCount()));
            }
        }
    }

    @Override
    protected EnumParticleTypesMP getDripParticle()
    {
        return EnumParticleTypesMP.INFECTED_WATER_DRIP;
    }
}