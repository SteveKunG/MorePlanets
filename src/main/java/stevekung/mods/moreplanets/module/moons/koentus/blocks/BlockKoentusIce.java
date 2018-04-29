package stevekung.mods.moreplanets.module.moons.koentus.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import stevekung.mods.moreplanets.utils.blocks.BlockIceMP;
import stevekung.mods.stevekunglib.utils.ColorUtils;

public class BlockKoentusIce extends BlockIceMP
{
    public BlockKoentusIce(String name)
    {
        super();
        this.setUnlocalizedName(name);
        this.setDefaultSlipperiness(1.05F);
    }

    @Override
    @Nullable
    public float[] getBeaconColorMultiplier(IBlockState state, World world, BlockPos pos, BlockPos beaconPos)
    {
        return ColorUtils.rgbToFloatArray(78, 101, 145);
    }

    @Override
    public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity tile, @Nullable ItemStack stack)
    {
        player.addStat(StatList.getBlockStats(this));
        player.addExhaustion(0.025F);

        if (this.canSilkHarvest(world, pos, state, player) && EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, stack) > 0)
        {
            List<ItemStack> items = new ArrayList<>();
            ItemStack itemstack = this.getSilkTouchDrop(state);

            if (itemstack != null)
            {
                items.add(itemstack);
            }

            ForgeEventFactory.fireBlockHarvesting(items, world, pos, state, 0, 1.0f, true, player);

            for (ItemStack item : items)
            {
                Block.spawnAsEntity(world, pos, item);
            }
        }
        else
        {
            if (world.provider.doesWaterVaporize())
            {
                world.setBlockToAir(pos);
                return;
            }

            int i = EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, stack);
            this.harvesters.set(player);
            this.dropBlockAsItem(world, pos, state, i);
            this.harvesters.set(null);
            Material material = world.getBlockState(pos.down()).getMaterial();

            if (material.blocksMovement() || material.isLiquid())
            {
                world.setBlockState(pos, Blocks.FLOWING_WATER.getDefaultState());//TODO Custom fluid
            }
        }
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        if (world.getLightFor(EnumSkyBlock.BLOCK, pos) > 11 - this.getDefaultState().getLightOpacity(world, pos))
        {
            this.turnIntoWater(world, pos);
        }
    }

    private void turnIntoWater(World worldIn, BlockPos pos)
    {
        if (worldIn.provider.doesWaterVaporize())
        {
            worldIn.setBlockToAir(pos);
        }
        else
        {
            this.dropBlockAsItem(worldIn, pos, worldIn.getBlockState(pos), 0);
            worldIn.setBlockState(pos, Blocks.WATER.getDefaultState());//TODO Custom fluid
            worldIn.neighborChanged(pos, Blocks.WATER, pos);
        }
    }
}