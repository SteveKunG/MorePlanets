package stevekung.mods.moreplanets.util.blocks;

import net.minecraft.block.BlockFence;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
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