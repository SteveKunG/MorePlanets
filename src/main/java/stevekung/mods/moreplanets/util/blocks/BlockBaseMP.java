package stevekung.mods.moreplanets.util.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import stevekung.mods.moreplanets.core.MorePlanetsMod;

public class BlockBaseMP extends Block implements ISortableBlock, ISingleBlockRender
{
    private EnumSortCategoryBlock category;
    protected String name;

    public BlockBaseMP(Material material)
    {
        super(material);
    }

    public BlockBaseMP(String name, Material material)
    {
        super(material);
        this.name = name;
        this.setUnlocalizedName(name);
    }

    @Override
    public BlockBaseMP setSoundType(SoundType sound)
    {
        this.blockSoundType = sound;
        return this;
    }

    @Override
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return MorePlanetsMod.BLOCK_TAB;
    }

    @Override
    public EnumSortCategoryBlock getBlockCategory()
    {
        return this.category == null ? EnumSortCategoryBlock.BUILDING_BLOCK : this.category;
    }

    public BlockBaseMP setSortCategory(EnumSortCategoryBlock category)
    {
        this.category = category;
        return this;
    }

    @Override
    public String getName()
    {
        return this.name;
    }
}