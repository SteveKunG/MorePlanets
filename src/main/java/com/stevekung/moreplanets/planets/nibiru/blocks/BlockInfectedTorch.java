package com.stevekung.moreplanets.planets.nibiru.blocks;

import java.util.Random;

import com.stevekung.moreplanets.core.config.ConfigManagerMP;
import com.stevekung.moreplanets.planets.nibiru.dimension.WorldProviderNibiru;
import com.stevekung.moreplanets.utils.blocks.BlockTorchMP;

import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import micdoodle8.mods.galacticraft.core.GCBlocks;
import micdoodle8.mods.galacticraft.core.util.OxygenUtil;

public class BlockInfectedTorch extends BlockTorchMP
{
    public BlockInfectedTorch(String name)
    {
        this.setTranslationKey(name);
        this.setLightLevel(0.9375F);
        this.setSoundType(SoundType.WOOD);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        if (OxygenUtil.noAtmosphericCombustion(world.provider) && !(world.provider instanceof WorldProviderNibiru))
        {
            world.setBlockState(pos, GCBlocks.unlitTorch.getStateFromMeta(this.getMetaFromState(state)));
        }
        else if (!(world.provider instanceof WorldProviderNibiru))
        {
            world.setBlockState(pos, Blocks.TORCH.getStateFromMeta(this.getMetaFromState(state)));
        }
    }

    @Override
    public String getModelName()
    {
        return ConfigManagerMP.moreplanets_general.use3DTorchItemModel ? "infected_torch" : "infected_torch_vanilla";
    }
}