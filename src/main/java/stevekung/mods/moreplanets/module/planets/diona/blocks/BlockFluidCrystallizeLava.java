package stevekung.mods.moreplanets.module.planets.diona.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
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
    public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        if (this.blockMaterial == Material.lava && world.getBlockState(pos.up()).getBlock().getMaterial() == Material.air && !world.getBlockState(pos.up()).getBlock().isOpaqueCube())
        {
            if (rand.nextInt(50) == 0)
            {
                double d5 = pos.getX() + rand.nextFloat();
                double d6 = pos.getY() + this.maxY;
                double d7 = pos.getZ() + rand.nextFloat();
                MorePlanetsCore.PROXY.spawnParticle(EnumParticleTypesMP.CRYSTALLIZE_LAVA, d5, d6, d7);
                world.playSound(d5, d6, d7, "liquid.lavapop", 0.2F + rand.nextFloat() * 0.2F, 0.9F + rand.nextFloat() * 0.15F, false);
            }
            if (rand.nextInt(200) == 0)
            {
                world.playSound(pos.getX(), pos.getY(), pos.getZ(), "liquid.lava", 0.2F + rand.nextFloat() * 0.2F, 0.9F + rand.nextFloat() * 0.15F, false);
            }
        }
        if (rand.nextInt(10) == 0 && World.doesBlockHaveSolidTopSurface(world, pos.down()) && !world.getBlockState(pos.down(2)).getBlock().getMaterial().blocksMovement())
        {
            double d5 = pos.getX() + rand.nextFloat();
            double d6 = pos.getY() - 1.05D;
            double d7 = pos.getZ() + rand.nextFloat();
            MorePlanetsCore.PROXY.spawnParticle(EnumParticleTypesMP.CRYSTALLIZE_LAVA_DRIP, d5, d6, d7);
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

            if (enumfacing != EnumFacing.DOWN && world.getBlockState(pos.offset(enumfacing)).getBlock().getMaterial() == Material.water)
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
        return Blocks.obsidian.getDefaultState();
    }

    @Override
    protected IBlockState getCobblestoneBlock()
    {
        return DionaBlocks.DIONA_BLOCK.getDefaultState().withProperty(BlockDiona.VARIANT, BlockDiona.BlockType.DIONA_COBBLESTONE);
    }

    @Override
    protected IBlockState getFireBlock()
    {
        return Blocks.fire.getDefaultState();
    }

    @Override
    protected boolean enableFire()
    {
        return true;
    }

    @Override
    public String getName()
    {
        return "crystallize_lava_fluid";
    }
}