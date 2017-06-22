package stevekung.mods.moreplanets.module.planets.nibiru.blocks;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.util.blocks.BlockBreakableMP;

public class BlockInfectedIce extends BlockBreakableMP
{
    public BlockInfectedIce(String name)
    {
        super(Material.ICE);
        this.slipperiness = 0.98F;
        this.setTickRandomly(true);
        this.setHardness(0.5F);
        this.setLightOpacity(3);
        this.setSoundType(SoundType.GLASS);
        this.setUnlocalizedName(name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity tile, ItemStack itemStack)
    {
        player.addExhaustion(0.025F);

        if (this.canSilkHarvest(world, pos, world.getBlockState(pos), player) && EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, itemStack) > 0)
        {
            List<ItemStack> items = Lists.newArrayList();
            ItemStack itemstack = this.createStackedBlock(state);

            if (itemstack != null)
            {
                items.add(itemstack);
            }

            ForgeEventFactory.fireBlockHarvesting(items, world, pos, world.getBlockState(pos), 0, 1.0f, true, player);

            for (ItemStack is : items)
            {
                Block.spawnAsEntity(world, pos, is);
            }
        }
        else
        {
            if (world.provider.doesWaterVaporize())
            {
                world.setBlockToAir(pos);
                return;
            }

            int i = EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, itemStack);
            this.harvesters.set(player);
            this.dropBlockAsItem(world, pos, state, i);
            this.harvesters.set(null);
            Material material = world.getBlockState(pos.down()).getMaterial();

            if (material.blocksMovement() || material.isLiquid())
            {
                world.setBlockState(pos, NibiruBlocks.INFECTED_WATER_FLUID_BLOCK.getDefaultState());
            }
        }
    }

    @Override
    public int quantityDropped(Random rand)
    {
        return 0;
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        if (world.getLightFor(EnumSkyBlock.BLOCK, pos) > 11 - state.getLightOpacity(world, pos))
        {
            if (world.provider.doesWaterVaporize())
            {
                world.setBlockToAir(pos);
            }
            else
            {
                this.dropBlockAsItem(world, pos, world.getBlockState(pos), 0);
                world.setBlockState(pos, NibiruBlocks.INFECTED_WATER_FLUID_BLOCK.getDefaultState());
            }
        }
    }

    @Override
    public EnumPushReaction getMobilityFlag(IBlockState state)
    {
        return EnumPushReaction.NORMAL;
    }

    @Override
    protected boolean isTranslucentBlock()
    {
        return true;
    }

    @Override
    protected boolean renderSideOnOtherState()
    {
        return true;
    }

    @Override
    public String getName()
    {
        return "infected_ice";
    }
}