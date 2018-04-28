package stevekung.mods.moreplanets.module.planets.diona.blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.module.planets.diona.entity.EntityAlbetiusWorm;
import stevekung.mods.moreplanets.util.blocks.BlockBaseMP;
import stevekung.mods.moreplanets.util.blocks.EnumSortCategoryBlock;

public class BlockAlbetiusWormEgg extends BlockBaseMP
{
    public BlockAlbetiusWormEgg(String name)
    {
        super(Material.ROCK);
        this.setResistance(5.0F);
        this.setHardness(4.0F);
        this.setUnlocalizedName(name);
    }

    @Override
    public int quantityDropped(Random rand)
    {
        return 0;
    }

    @Override
    public void onBlockDestroyedByPlayer(World world, BlockPos pos, IBlockState state)
    {
        if (!world.isRemote)
        {
            EntityAlbetiusWorm worm = new EntityAlbetiusWorm(world);
            worm.setLocationAndAngles(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, 0.0F, 0.0F);
            world.spawnEntity(worm);
            worm.spawnExplosionParticle();
        }
    }

    @Override
    public void dropBlockAsItemWithChance(World world, BlockPos pos, IBlockState state, float chance, int fortune)
    {
        if (!world.isRemote)
        {
            EntityAlbetiusWorm worm = new EntityAlbetiusWorm(world);
            worm.setLocationAndAngles(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, 0.0F, 0.0F);
            world.spawnEntity(worm);
            worm.spawnExplosionParticle();
        }
    }

    @Override
    public EnumSortCategoryBlock getBlockCategory()
    {
        return EnumSortCategoryBlock.DECORATION_BLOCK;
    }
}