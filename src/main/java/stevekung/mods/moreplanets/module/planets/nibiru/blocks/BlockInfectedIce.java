package stevekung.mods.moreplanets.module.planets.nibiru.blocks;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
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
        super(Material.ice);
        this.slipperiness = 0.98F;
        this.setTickRandomly(true);
        this.setHardness(0.5F);
        this.setLightOpacity(3);
        this.setStepSound(soundTypeGlass);
        this.setUnlocalizedName(name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumWorldBlockLayer getBlockLayer()
    {
        return EnumWorldBlockLayer.TRANSLUCENT;
    }

    @Override
    public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity te)
    {
        player.addExhaustion(0.025F);

        if (this.canSilkHarvest(world, pos, world.getBlockState(pos), player) && EnchantmentHelper.getSilkTouchModifier(player))
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

            int i = EnchantmentHelper.getFortuneModifier(player);
            this.harvesters.set(player);
            this.dropBlockAsItem(world, pos, state, i);
            this.harvesters.set(null);
            Material material = world.getBlockState(pos.down()).getBlock().getMaterial();

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
        if (world.getLightFor(EnumSkyBlock.BLOCK, pos) > 11 - this.getLightOpacity())
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
    public int getMobilityFlag()
    {
        return 0;
    }

    @Override
    protected boolean isTranslucentBlock()
    {
        return true;
    }

    @Override
    protected boolean renderSideOnOtherState()
    {
        return false;
    }

    @Override
    public String getName()
    {
        return "infected_ice";
    }
}