package stevekung.mods.moreplanets.planets.nibiru.blocks;

import java.util.Iterator;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.planets.nibiru.entity.EntityInfectedChicken;
import stevekung.mods.moreplanets.planets.nibiru.entity.EntityShlime;
import stevekung.mods.moreplanets.utils.blocks.BlockFarmlandMP;
import stevekung.mods.stevekunglib.utils.BlockStateProperty;

public class BlockInfectedFarmland extends BlockFarmlandMP
{
    public BlockInfectedFarmland(String name)
    {
        super();
        this.setDefaultState(this.getDefaultState().withProperty(BlockStateProperty.MOISTURE, 0));
        this.setUnlocalizedName(name);
    }

    @Override
    public void onFallenUpon(World world, BlockPos pos, Entity entity, float fallDistance)
    {
        if (entity instanceof EntityLivingBase && !(entity instanceof EntityInfectedChicken || entity instanceof EntityShlime))
        {
            if (!world.isRemote && world.rand.nextFloat() < fallDistance - 0.5F)
            {
                if (!(entity instanceof EntityPlayer) && !world.getGameRules().getBoolean("mobGriefing"))
                {
                    return;
                }
                world.setBlockState(pos, this.getDirtBlock().getDefaultState());
            }
            entity.fall(fallDistance, 1.0F);
        }
    }

    @Override
    protected Block getDirtBlock()
    {
        return MPBlocks.INFECTED_DIRT;
    }

    @Override
    protected boolean hasWater(World world, BlockPos pos)
    {
        Iterator iterator = BlockPos.getAllInBoxMutable(pos.add(-4, 0, -4), pos.add(4, 1, 4)).iterator();
        MutableBlockPos mutableblockpos;

        do
        {
            if (!iterator.hasNext())
            {
                return false;
            }
            mutableblockpos = (MutableBlockPos)iterator.next();
        }
        while (world.getBlockState(mutableblockpos).getBlock() != MPBlocks.INFECTED_WATER_FLUID_BLOCK && world.getBlockState(mutableblockpos).getBlock() != MPBlocks.PURIFIED_WATER_FLUID_BLOCK);
        return true;
    }

    @Override
    protected Block getSourceBlock()
    {
        return null;
    }
}