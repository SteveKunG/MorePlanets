package stevekung.mods.moreplanets.module.planets.nibiru.blocks;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.init.MPBiomes;
import stevekung.mods.moreplanets.init.MPPotions;
import stevekung.mods.moreplanets.module.planets.nibiru.items.NibiruItems;
import stevekung.mods.moreplanets.util.EnumParticleTypesMP;
import stevekung.mods.moreplanets.util.blocks.BlockFluidBaseMP;
import stevekung.mods.moreplanets.util.blocks.IFishableLiquidBlock;
import stevekung.mods.moreplanets.util.entity.ISpaceMob;
import stevekung.mods.moreplanets.util.entity.ISpaceMob.EnumMobType;
import stevekung.mods.moreplanets.util.helper.EntityEffectHelper;

public class BlockFluidInfectedWater extends BlockFluidBaseMP implements IFishableLiquidBlock
{
    public BlockFluidInfectedWater(String name)
    {
        super(NibiruBlocks.INFECTED_WATER_FLUID);
        this.setRenderLayer(EnumWorldBlockLayer.TRANSLUCENT);
        this.setLightOpacity(3);
        this.setUnlocalizedName(name);
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        super.updateTick(world, pos, state, rand);
        BiomeGenBase biome = world.getBiomeGenForCoords(pos);

        if (biome == MPBiomes.GREEN_VEIN)
        {
            if (this.getMetaFromState(state) == 0 && rand.nextInt(8) == 0)
            {
                world.setBlockState(pos, NibiruBlocks.PURIFY_WATER_FLUID_BLOCK.getDefaultState());
            }
        }
    }

    @Override
    public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity)
    {
        if (entity instanceof EntityLivingBase && !EntityEffectHelper.isGalacticraftMob(entity) && !(entity instanceof ISpaceMob && ((ISpaceMob)entity).getMobType() == EnumMobType.NIBIRU))
        {
            EntityLivingBase living = (EntityLivingBase) entity;

            if (living instanceof EntityPlayer)
            {
                EntityPlayer player = (EntityPlayer) living;

                if (!player.capabilities.isCreativeMode && !player.isPotionActive(MPPotions.INFECTED_SPORE_PROTECTION))
                {
                    player.addPotionEffect(new PotionEffect(MPPotions.INFECTED_SPORE.id, 80));
                }
            }
            if (!(living instanceof EntityPlayer))
            {
                living.addPotionEffect(new PotionEffect(MPPotions.INFECTED_SPORE.id, 80, 0));
            }
        }
        if (entity instanceof EntityItem)
        {
            EntityItem item = (EntityItem) entity;

            if (item.getEntityItem().getItem() == Items.arrow)
            {
                item.setEntityItemStack(new ItemStack(NibiruItems.INFECTED_ARROW, item.getEntityItem().stackSize));
            }
        }
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
            MorePlanetsCore.PROXY.spawnParticle(EnumParticleTypesMP.INFECTED_WATER_DRIP, d5, d6, d7);
        }
    }

    @Override
    protected boolean isInfinite()
    {
        return true;
    }

    @Override
    public String getName()
    {
        return "infected_water_fluid";
    }
}