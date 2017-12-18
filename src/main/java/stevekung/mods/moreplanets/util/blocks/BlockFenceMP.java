package stevekung.mods.moreplanets.util.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsCore;

public class BlockFenceMP extends BlockFence implements ISortableBlock, ISingleBlockRender
{
    private String name;

    public BlockFenceMP(String name)
    {
        super(Material.WOOD, null);
        this.setHardness(2.0F);
        this.setResistance(5.0F);
        this.setSoundType(SoundType.WOOD);
        this.setUnlocalizedName(name);
        this.name = name;
    }

    public BlockFenceMP(Material material)
    {
        super(material, null);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return MorePlanetsCore.BLOCK_TAB;
    }

    @Override
    public boolean canConnectTo(IBlockAccess world, BlockPos pos)
    {
        Block block = world.getBlockState(pos).getBlock();
        return block == Blocks.BARRIER ? false : (!(block instanceof BlockFence) || world.getBlockState(pos).getMaterial() != this.blockMaterial) && !(block instanceof BlockFenceGate) ? world.getBlockState(pos).getMaterial().isOpaque() && world.getBlockState(pos).isFullCube() ? world.getBlockState(pos).getMaterial() != Material.GOURD : false : true;
    }

    @Override
    public EnumSortCategoryBlock getBlockCategory(int meta)
    {
        return EnumSortCategoryBlock.FENCE;
    }

    @Override
    public String getName()
    {
        return this.name;
    }
}