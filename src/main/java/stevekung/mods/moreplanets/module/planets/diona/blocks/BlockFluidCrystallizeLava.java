package stevekung.mods.moreplanets.module.planets.diona.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.module.planets.diona.items.DionaItems;
import stevekung.mods.moreplanets.util.EnumParticleTypesMP;
import stevekung.mods.moreplanets.util.blocks.BlockFluidLavaBaseMP;

public class BlockFluidCrystallizeLava extends BlockFluidLavaBaseMP
{
    public BlockFluidCrystallizeLava(String name)
    {
        super(DionaBlocks.CRYSTALLIZE_LAVA_FLUID);
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
                MorePlanetsMod.PROXY.spawnParticle(EnumParticleTypesMP.CRYSTALLIZE_LAVA, d5, d6, d7);
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
                MorePlanetsMod.PROXY.spawnParticle(EnumParticleTypesMP.CRYSTALLIZE_LAVA_DRIP, d5, d6, d7);
            }
        }
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
                    Block.spawnAsEntity(world, pos.up(), new ItemStack(DionaItems.DIONA_ITEM, 1, 4));
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
        return DionaBlocks.DIONA_BLOCK.getDefaultState().withProperty(BlockDiona.VARIANT, BlockDiona.BlockType.DIONA_ROCK);
    }

    @Override
    protected IBlockState getObsidianBlock()
    {
        return Blocks.OBSIDIAN.getDefaultState();
    }

    @Override
    protected IBlockState getCobblestoneBlock()
    {
        return DionaBlocks.DIONA_BLOCK.getDefaultState().withProperty(BlockDiona.VARIANT, BlockDiona.BlockType.DIONA_COBBLESTONE);
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
}