package stevekung.mods.moreplanets.utils.blocks;

import java.util.Optional;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.utils.client.renderer.IItemModelRender;
import stevekung.mods.moreplanets.utils.itemblocks.IItemRarity;
import stevekung.mods.stevekunglib.utils.ColorUtils;

public class BlockBaseMP extends Block implements ISortableBlock, IItemModelRender, IItemRarity
{
    private EnumSortCategoryBlock category;
    private String name;
    private ColorUtils.RGB rgb;

    public BlockBaseMP(Material material)
    {
        super(material);
    }

    public BlockBaseMP(String name, Material material)
    {
        super(material);
        this.setUnlocalizedName(name);
    }

    @Override
    public Block setUnlocalizedName(String name)
    {
        this.name = name;
        return super.setUnlocalizedName(name);
    }

    @Override
    public Block setSoundType(SoundType sound)
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

    @Override
    public String getName()
    {
        return this.name;
    }

    @Override
    public ColorUtils.RGB getRarity()
    {
        return Optional.ofNullable(this.rgb).orElse(null);
    }

    public BlockBaseMP setSortCategory(EnumSortCategoryBlock category)
    {
        this.category = category;
        return this;
    }

    public BlockBaseMP setRarityRGB(ColorUtils.RGB rgb)
    {
        this.rgb = rgb;
        return this;
    }
}