package stevekung.mods.moreplanets.util.items;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.util.blocks.BlockFluidLavaBaseMP;

public class ItemBucketMP extends ItemBucket implements ISortableItem, ISingleItemRender
{
    protected String name;
    private Block fluid;

    public ItemBucketMP(String name, Block fluid)
    {
        super(fluid);
        this.fluid = fluid;
        this.name = name;
        this.setUnlocalizedName(name);
        this.setContainerItem(Items.BUCKET);
    }

    public ItemBucketMP()
    {
        super(Blocks.AIR);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public CreativeTabs getCreativeTab()
    {
        return MorePlanetsCore.ITEM_TAB;
    }

    @Override
    public EnumSortCategoryItem getItemCategory(int meta)
    {
        return EnumSortCategoryItem.BUCKET_FLUID;
    }

    @Override
    public boolean tryPlaceContainedLiquid(@Nullable EntityPlayer player, World world, BlockPos pos)
    {
        if (this.fluid == Blocks.AIR)
        {
            return false;
        }
        else
        {
            IBlockState state = world.getBlockState(pos);
            Material material = state.getMaterial();
            boolean flag = !material.isSolid();
            boolean flag1 = state.getBlock().isReplaceable(world, pos);

            if (!world.isAirBlock(pos) && !flag && !flag1)
            {
                return false;
            }
            else
            {
                if (world.provider.doesWaterVaporize() && this.fluid == Blocks.FLOWING_WATER)
                {
                    int l = pos.getX();
                    int i = pos.getY();
                    int j = pos.getZ();
                    world.playSound(player, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);

                    for (int k = 0; k < 8; ++k)
                    {
                        world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, l + Math.random(), i + Math.random(), j + Math.random(), 0.0D, 0.0D, 0.0D, new int[0]);
                    }
                }
                else
                {
                    if (!world.isRemote && (flag || flag1) && !material.isLiquid())
                    {
                        world.destroyBlock(pos, true);
                    }
                    SoundEvent soundevent = this.fluid instanceof BlockFluidLavaBaseMP ? SoundEvents.ITEM_BUCKET_EMPTY_LAVA : SoundEvents.ITEM_BUCKET_EMPTY;
                    world.playSound(player, pos, soundevent, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    world.setBlockState(pos, this.fluid.getDefaultState(), 11);
                }
                return true;
            }
        }
    }

    @Override
    public String getName()
    {
        return this.name;
    }

    @Override
    public Item getItem()
    {
        return this;
    }
}