package stevekung.mods.moreplanets.utils.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.utils.EnumParticleTypesMP;
import stevekung.mods.moreplanets.utils.client.renderer.IItemModelRender;

public abstract class BlockFluidBaseMP extends BlockFluidClassic implements IItemModelRender
{
    private String name;
    protected boolean isWater;

    public BlockFluidBaseMP(Fluid fluid)
    {
        super(fluid, Material.WATER);
        this.setHardness(100.0F);
    }

    public BlockFluidBaseMP(Fluid fluid, Material material)
    {
        super(fluid, material);
        this.setHardness(100.0F);
    }

    @Override
    public Block setTranslationKey(String name)
    {
        this.name = name;
        return super.setTranslationKey(name);
    }

    @Override
    public boolean isPassable(IBlockAccess world, BlockPos pos)
    {
        return this.material != Material.LAVA;
    }

    @Override
    public boolean canDisplace(IBlockAccess world, BlockPos pos)
    {
        if (world.getBlockState(pos).getMaterial().isLiquid())
        {
            return false;
        }
        if (world.getBlockState(pos).getMaterial() == Material.LAVA)
        {
            return false;
        }
        return super.canDisplace(world, pos);
    }

    @Override
    public boolean displaceIfPossible(World world, BlockPos pos)
    {
        if (world.getBlockState(pos).getMaterial().isLiquid())
        {
            return false;
        }
        if (world.getBlockState(pos).getMaterial() == Material.LAVA)
        {
            return false;
        }
        return super.displaceIfPossible(world, pos);
    }

    @SuppressWarnings("deprecation")
    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing facing)
    {
        IBlockState iblockstate = world.getBlockState(pos.offset(facing));
        Block block = iblockstate.getBlock();

        if (block == this)
        {
            return false;
        }
        if (block != this && block instanceof BlockFluidBaseMP)
        {
            return true;
        }
        return super.shouldSideBeRendered(state, world, pos, facing);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand)
    {
        int meta = this.getMetaFromState(state);

        if (this.isWater)
        {
            if (rand.nextInt(64) == 0)
            {
                if (meta > 0 && meta < 8)
                {
                    world.playSound(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, SoundEvents.BLOCK_WATER_AMBIENT, SoundCategory.BLOCKS, rand.nextFloat() * 0.25F + 0.75F, rand.nextFloat() + 0.5F, false);
                }
                else if (rand.nextInt(10) == 0)
                {
                    world.spawnParticle(EnumParticleTypes.SUSPENDED, pos.getX() + (double)rand.nextFloat(), pos.getY() + (double)rand.nextFloat(), pos.getZ() + (double)rand.nextFloat(), 0.0D, 0.0D, 0.0D);
                }
            }
        }
        else
        {
            if (world.getBlockState(pos.up()).getMaterial() == Material.AIR && !world.getBlockState(pos.up()).isOpaqueCube())
            {
                if (rand.nextInt(50) == 0)
                {
                    double d5 = pos.getX() + rand.nextFloat();
                    double d6 = pos.getY() + state.getBoundingBox(world, pos).maxY;
                    double d7 = pos.getZ() + rand.nextFloat();

                    if (this.getLavaParticle() != null)
                    {
                        MorePlanetsMod.PROXY.spawnParticle(this.getLavaParticle(), d5, d6, d7);
                    }
                    world.playSound(d5, d6, d7, SoundEvents.BLOCK_LAVA_POP, SoundCategory.BLOCKS, 0.2F + rand.nextFloat() * 0.2F, 0.9F + rand.nextFloat() * 0.15F, false);
                }
                if (rand.nextInt(200) == 0)
                {
                    world.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_LAVA_AMBIENT, SoundCategory.BLOCKS, 0.2F + rand.nextFloat() * 0.2F, 0.9F + rand.nextFloat() * 0.15F, false);
                }
            }
        }
        if (rand.nextInt(10) == 0 && this.getDripParticle() != null && world.getBlockState(pos.down()).isSideSolid(world, pos.down(), EnumFacing.UP))
        {
            Material material = world.getBlockState(pos.down(2)).getMaterial();

            if (!material.blocksMovement() && !material.isLiquid())
            {
                double d5 = pos.getX() + rand.nextFloat();
                double d6 = pos.getY() - 1.05D;
                double d7 = pos.getZ() + rand.nextFloat();
                MorePlanetsMod.PROXY.spawnParticle(this.getDripParticle(), d5, d6, d7);
            }
        }
    }

    @Override
    public String getName()
    {
        return this.name;
    }

    protected EnumParticleTypesMP getLavaParticle()
    {
        return null;
    }

    protected abstract EnumParticleTypesMP getDripParticle();

    protected void triggerMixEffects(World world, BlockPos pos)
    {
        world.playSound(null, pos, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);

        if (!world.isRemote && world instanceof WorldServer)
        {
            ((WorldServer)world).spawnParticle(EnumParticleTypes.SMOKE_LARGE, pos.getX() + Math.random(), pos.getY() + 1.2D, pos.getZ() + Math.random(), 8, 0.0D, 0.0D, 0.0D, 0.0D);
        }
    }
}