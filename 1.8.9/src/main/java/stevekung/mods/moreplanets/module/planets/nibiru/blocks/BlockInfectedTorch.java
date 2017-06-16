package stevekung.mods.moreplanets.module.planets.nibiru.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.module.planets.nibiru.dimension.WorldProviderNibiru;
import stevekung.mods.moreplanets.util.blocks.EnumSortCategoryBlock;
import stevekung.mods.moreplanets.util.blocks.ISingleBlockRender;
import stevekung.mods.moreplanets.util.blocks.ISortableBlock;

public class BlockInfectedTorch extends BlockTorch implements ISortableBlock, ISingleBlockRender
{
    public BlockInfectedTorch(String name)
    {
        this.setUnlocalizedName(name);
        this.setHardness(0.0F);
        this.setLightLevel(0.9375F);
        this.setStepSound(soundTypeWood);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return MorePlanetsCore.BLOCK_TAB;
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        if (!(world.provider instanceof WorldProviderNibiru))
        {
            if (rand.nextInt(5) == 0)
            {
                world.setBlockState(pos, Blocks.torch.getStateFromMeta(this.getMetaFromState(state)));
            }
        }
    }

    @Override
    public EnumSortCategoryBlock getBlockCategory(int meta)
    {
        return EnumSortCategoryBlock.TORCH;
    }

    @Override
    public String getName()
    {
        return ConfigManagerMP.use3DTorchItemModel ? "infected_torch" : "infected_torch_vanilla";
    }

    @Override
    public Block getBlock()
    {
        return this;
    }
}