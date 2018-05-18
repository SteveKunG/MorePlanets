package stevekung.mods.moreplanets.planets.nibiru.blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.utils.EnumParticleTypesMP;
import stevekung.mods.moreplanets.utils.blocks.BlockFluidLavaBaseMP;

public class BlockFluidNuclearWaste extends BlockFluidLavaBaseMP
{
    public BlockFluidNuclearWaste(String name)
    {
        super(MPBlocks.NUCLEAR_WASTE_FLUID);
        this.setUnlocalizedName(name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand)
    {
        if (rand.nextInt(1000) == 0)
        {
            world.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_LAVA_AMBIENT, SoundCategory.BLOCKS, rand.nextFloat() * 0.25F + 0.75F, 0.00001F + rand.nextFloat() * 0.5F, false);
        }
        if (rand.nextInt(10) == 0 && world.getBlockState(pos.down()).isSideSolid(world, pos.down(), EnumFacing.UP))
        {
            Material material = world.getBlockState(pos.down(2)).getMaterial();

            if (!material.blocksMovement() && !material.isLiquid())
            {
                double d5 = pos.getX() + rand.nextFloat();
                double d6 = pos.getY() - 1.05D;
                double d7 = pos.getZ() + rand.nextFloat();
                MorePlanetsMod.PROXY.spawnParticle(EnumParticleTypesMP.NUCLEAR_WASTE_DRIP, d5, d6, d7);
            }
        }
    }

    @Override
    protected IBlockState getBlockFromWaterTo()
    {
        return Blocks.OBSIDIAN.getDefaultState();
    }

    @Override
    protected IBlockState getObsidianBlock()
    {
        return Blocks.OBSIDIAN.getDefaultState();
    }

    @Override
    protected IBlockState getCobblestoneBlock()
    {
        return Blocks.OBSIDIAN.getDefaultState();
    }

    @Override
    protected IBlockState getFireBlock()
    {
        return Blocks.FIRE.getDefaultState();
    }

    @Override
    protected boolean enableFire()
    {
        return false;
    }

    @Override
    protected EnumParticleTypesMP getDripParticle()
    {
        return null;
    }
}