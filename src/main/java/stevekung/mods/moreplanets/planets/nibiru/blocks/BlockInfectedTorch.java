package stevekung.mods.moreplanets.planets.nibiru.blocks;

import java.util.Random;

import micdoodle8.mods.galacticraft.core.GCBlocks;
import micdoodle8.mods.galacticraft.core.util.OxygenUtil;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.planets.nibiru.dimension.WorldProviderNibiru;
import stevekung.mods.moreplanets.utils.blocks.BlockTorchMP;

public class BlockInfectedTorch extends BlockTorchMP
{
    public BlockInfectedTorch(String name)
    {
        this.setUnlocalizedName(name);
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
    public String getName()
    {
        return ConfigManagerMP.moreplanets_general.use3DTorchItemModel ? "infected_torch" : "infected_torch_vanilla";
    }
}