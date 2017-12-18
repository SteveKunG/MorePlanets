package stevekung.mods.moreplanets.util.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsCore;

public class BlockFenceMP extends BlockFence implements ISortableBlock, ISingleBlockRender
{
    private String name;

    public BlockFenceMP(String name)
    {
        super(Material.wood);
        this.setHardness(2.0F);
        this.setResistance(5.0F);
        this.setStepSound(soundTypeWood);
        this.setUnlocalizedName(name);
        this.name = name;
    }

    public BlockFenceMP(Material material)
    {
        super(material);
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
        return block == Blocks.barrier ? false : (!(block instanceof BlockFence) || block.getMaterial() != this.blockMaterial) && !(block instanceof BlockFenceGate) ? block.getMaterial().isOpaque() && block.isFullCube() ? block.getMaterial() != Material.gourd : false : true;
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

    @Override
    public Block getBlock()
    {
        return this;
    }
}