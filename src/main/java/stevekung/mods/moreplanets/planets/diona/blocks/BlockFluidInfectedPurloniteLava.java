package stevekung.mods.moreplanets.planets.diona.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.utils.EnumParticleTypesMP;
import stevekung.mods.moreplanets.utils.blocks.BlockFluidLavaBaseMP;

public class BlockFluidInfectedPurloniteLava extends BlockFluidLavaBaseMP
{
    public BlockFluidInfectedPurloniteLava(String name)
    {
        super(MPBlocks.INFECTED_PURLONITE_LAVA_FLUID);
        this.setUnlocalizedName(name);
    }

    @Override
    protected boolean checkForMixing(World world, BlockPos pos, IBlockState state)
    {
        boolean flag = false;
        EnumFacing[] aenumfacing = EnumFacing.VALUES;
        int i = aenumfacing.length;

        for (int j = 0; j < i; ++j)
        {
            EnumFacing enumfacing = aenumfacing[j];

            if (enumfacing != EnumFacing.DOWN && world.getBlockState(pos.offset(enumfacing)).getMaterial() == Material.WATER)
            {
                flag = true;
                break;
            }
        }

        if (flag)
        {
            Integer integer = state.getValue(LEVEL);

            if (integer.intValue() == 0)
            {
                world.setBlockState(pos, this.getObsidianBlock());
                this.triggerMixEffects(world, pos);

                if (world.rand.nextInt(500) == 0)
                {
                    Block.spawnAsEntity(world, pos.up(), new ItemStack(MPItems.INFECTED_PURLONITE_SHARD));
                }
                return true;
            }
            if (integer.intValue() <= 4)
            {
                world.setBlockState(pos, this.getCobblestoneBlock());
                this.triggerMixEffects(world, pos);
                return true;
            }
        }
        return false;
    }

    @Override
    protected IBlockState getBlockFromWaterTo()
    {
        return MPBlocks.DIONA_ROCK.getDefaultState();
    }

    @Override
    protected IBlockState getObsidianBlock()
    {
        return Blocks.OBSIDIAN.getDefaultState();
    }

    @Override
    protected IBlockState getCobblestoneBlock()
    {
        return MPBlocks.DIONA_COBBLESTONE.getDefaultState();
    }

    @Override
    protected IBlockState getFireBlock()
    {
        return Blocks.FIRE.getDefaultState();
    }

    @Override
    protected boolean enableFire()
    {
        return true;
    }

    @Override
    protected EnumParticleTypesMP getDripParticle()
    {
        return EnumParticleTypesMP.INFECTED_PURLONITE_LAVA_DRIP;
    }

    @Override
    protected EnumParticleTypesMP getLavaParticle()
    {
        return EnumParticleTypesMP.INFECTED_PURLONITE_LAVA;
    }
}