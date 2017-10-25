package stevekung.mods.moreplanets.module.planets.nibiru.blocks;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import com.google.common.collect.Lists;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.Tuple;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.util.EnumParticleTypesMP;
import stevekung.mods.moreplanets.util.VariantsName;
import stevekung.mods.moreplanets.util.blocks.BlockBaseMP;
import stevekung.mods.moreplanets.util.blocks.IBlockVariants;

public class BlockInfectedSponge extends BlockBaseMP implements IBlockVariants
{
    public static PropertyBool WET = PropertyBool.create("wet");

    public BlockInfectedSponge(String name)
    {
        super(Material.SPONGE);
        this.setDefaultState(this.blockState.getBaseState().withProperty(WET, Boolean.valueOf(false)));
        this.setHardness(0.6F);
        this.setSoundType(SoundType.PLANT);
        this.setUnlocalizedName(name);
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return state.getValue(WET).booleanValue() ? 1 : 0;
    }

    @Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state)
    {
        this.tryAbsorb(world, pos, state);
    }

    @Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos)
    {
        this.tryAbsorb(world, pos, state);
    }

    private void tryAbsorb(World world, BlockPos pos, IBlockState state)
    {
        if (!state.getValue(WET).booleanValue() && this.absorb(world, pos))
        {
            world.setBlockState(pos, state.withProperty(WET, Boolean.valueOf(true)), 2);
            world.playEvent(2001, pos, Block.getIdFromBlock(NibiruBlocks.INFECTED_WATER_FLUID_BLOCK));
        }
    }

    private boolean absorb(World world, BlockPos pos)
    {
        LinkedList<Tuple> linkedlist = Lists.newLinkedList();
        ArrayList<BlockPos> arraylist = Lists.newArrayList();
        linkedlist.add(new Tuple<>(pos, Integer.valueOf(0)));
        int i = 0;
        BlockPos blockpos1;

        while (!linkedlist.isEmpty())
        {
            Tuple tuple = linkedlist.poll();
            blockpos1 = (BlockPos)tuple.getFirst();
            int j = ((Integer)tuple.getSecond()).intValue();
            EnumFacing[] aenumfacing = EnumFacing.VALUES;
            int k = aenumfacing.length;

            for (int l = 0; l < k; ++l)
            {
                EnumFacing enumfacing = aenumfacing[l];
                BlockPos blockpos2 = blockpos1.offset(enumfacing);

                if (world.getBlockState(blockpos2).getBlock() == NibiruBlocks.INFECTED_WATER_FLUID_BLOCK)
                {
                    world.setBlockState(blockpos2, Blocks.AIR.getDefaultState(), 2);
                    arraylist.add(blockpos2);
                    ++i;

                    if (j < 6)
                    {
                        linkedlist.add(new Tuple<>(blockpos2, Integer.valueOf(j + 1)));
                    }
                }
            }
            if (i > 64)
            {
                break;
            }
        }

        Iterator iterator = arraylist.iterator();

        while (iterator.hasNext())
        {
            blockpos1 = (BlockPos)iterator.next();
            world.notifyNeighborsOfStateChange(blockpos1, Blocks.AIR, false);
        }
        return i > 0;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, NonNullList<ItemStack> list)
    {
        for (int i = 0; i < 2; i++)
        {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(WET, Boolean.valueOf((meta & 1) == 1));
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(WET).booleanValue() ? 1 : 0;
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, WET);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand)
    {
        if (state.getValue(WET).booleanValue())
        {
            EnumFacing enumfacing = EnumFacing.random(rand);

            if (enumfacing != EnumFacing.UP && !world.getBlockState(pos.offset(enumfacing)).isSideSolid(world, pos.offset(enumfacing), EnumFacing.UP))
            {
                double d0 = pos.getX();
                double d1 = pos.getY();
                double d2 = pos.getZ();

                if (enumfacing == EnumFacing.DOWN)
                {
                    d1 -= 0.05D;
                    d0 += rand.nextDouble();
                    d2 += rand.nextDouble();
                }
                else
                {
                    d1 += rand.nextDouble() * 0.8D;

                    if (enumfacing.getAxis() == EnumFacing.Axis.X)
                    {
                        d2 += rand.nextDouble();

                        if (enumfacing == EnumFacing.EAST)
                        {
                            ++d0;
                        }
                        else
                        {
                            d0 += 0.05D;
                        }
                    }
                    else
                    {
                        d0 += rand.nextDouble();

                        if (enumfacing == EnumFacing.SOUTH)
                        {
                            ++d2;
                        }
                        else
                        {
                            d2 += 0.05D;
                        }
                    }
                }
                MorePlanetsCore.PROXY.spawnParticle(EnumParticleTypesMP.INFECTED_WATER_DRIP, d0, d1, d2);
            }
        }
    }

    @Override
    public VariantsName getVariantsName()
    {
        return new VariantsName("infected_sponge", "infected_wet_sponge");
    }
}