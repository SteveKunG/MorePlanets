package stevekung.mods.moreplanets.module.planets.diona.blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.util.CompatibilityManagerMP;
import stevekung.mods.moreplanets.util.blocks.BlockBaseMP;
import stevekung.mods.moreplanets.util.blocks.EnumSortCategoryBlock;

public class BlockAlienMinerBlood extends BlockBaseMP
{
    public BlockAlienMinerBlood(String name)
    {
        super(Material.ROCK);
        this.setTickRandomly(true);
        this.setHardness(1.25F);
        this.setUnlocalizedName(name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return CompatibilityManagerMP.isCTMLoaded ? BlockRenderLayer.CUTOUT : BlockRenderLayer.SOLID;
    }

    @Override
    public EnumSortCategoryBlock getBlockCategory()
    {
        return EnumSortCategoryBlock.DECORATION_BLOCK;
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        if (rand.nextInt(200) == 0)
        {
            world.setBlockState(pos, DionaBlocks.DIONA_ROCK.getDefaultState());
        }
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(DionaBlocks.DIONA_ROCK);
    }

    @Override
    public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player)
    {
        return false;
    }
}