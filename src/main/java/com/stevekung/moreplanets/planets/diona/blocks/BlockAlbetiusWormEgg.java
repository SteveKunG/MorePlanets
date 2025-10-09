package com.stevekung.moreplanets.planets.diona.blocks;

import java.util.Random;

import com.stevekung.moreplanets.planets.diona.entity.EntityAlbetiusWorm;
import com.stevekung.moreplanets.utils.CompatibilityManagerMP;
import com.stevekung.moreplanets.utils.blocks.BlockBaseMP;
import com.stevekung.moreplanets.utils.blocks.MPBlockCategory;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockAlbetiusWormEgg extends BlockBaseMP
{
    public BlockAlbetiusWormEgg(String name)
    {
        super(name, Material.ROCK);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getRenderLayer()
    {
        return CompatibilityManagerMP.isCTMLoaded ? BlockRenderLayer.CUTOUT : BlockRenderLayer.SOLID;
    }

    @Override
    public int quantityDropped(Random rand)
    {
        return 0;
    }

    @Override
    public void dropBlockAsItemWithChance(World world, BlockPos pos, IBlockState state, float chance, int fortune)
    {
        if (!world.isRemote && world.getGameRules().getBoolean("doTileDrops"))
        {
            EntityAlbetiusWorm worm = new EntityAlbetiusWorm(world);
            worm.setLocationAndAngles(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, 0.0F, 0.0F);
            world.spawnEntity(worm);
            worm.spawnExplosionParticle();
        }
    }

    @Override
    public MPBlockCategory getBlockCategory()
    {
        return MPBlockCategory.DECORATION_BLOCK;
    }
}