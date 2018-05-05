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
import stevekung.mods.moreplanets.utils.EnumParticleTypesMP;
import stevekung.mods.moreplanets.utils.blocks.BlockFluidLavaBaseMP;

public class BlockFluidNuclearWaste extends BlockFluidLavaBaseMP
{
    public BlockFluidNuclearWaste(String name)
    {
        super(NibiruBlocks.NUCLEAR_WASTE_FLUID);
        this.setUnlocalizedName(name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand)
    {
        if (this.blockMaterial == Material.LAVA && world.getBlockState(pos.up()).getMaterial() == Material.AIR && !world.getBlockState(pos.up()).isOpaqueCube())
        {
            if (rand.nextInt(50) == 0)
            {
                double d5 = pos.getX() + rand.nextFloat();
                double d6 = pos.getY() + state.getBoundingBox(world, pos).maxY;
                double d7 = pos.getZ() + rand.nextFloat();
                world.playSound(d5, d6, d7, SoundEvents.BLOCK_LAVA_POP, SoundCategory.BLOCKS, 0.2F + rand.nextFloat() * 0.2F, 0.9F + rand.nextFloat() * 0.15F, false);
            }
            if (rand.nextInt(200) == 0)
            {
                world.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_LAVA_AMBIENT, SoundCategory.BLOCKS, 0.2F + rand.nextFloat() * 0.2F, 0.9F + rand.nextFloat() * 0.15F, false);
            }
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
}