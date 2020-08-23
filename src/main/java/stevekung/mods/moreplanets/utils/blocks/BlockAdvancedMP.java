package stevekung.mods.moreplanets.utils.blocks;

import micdoodle8.mods.galacticraft.core.blocks.BlockAdvanced;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.utils.client.renderer.IItemModelRender;
import stevekung.mods.moreplanets.utils.itemblocks.IItemRarity;
import stevekung.mods.stevekunglib.utils.ColorUtils;

public abstract class BlockAdvancedMP extends BlockAdvanced implements ITileEntityProvider, ISortableBlock, IItemModelRender, IItemRarity
{
    private EnumSortCategoryBlock category;
    private String name;
    private ColorUtils.RGB rgb;

    public BlockAdvancedMP(Material material)
    {
        super(material);
        this.setHardness(0.6F);
        this.setResistance(2.5F);
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
        return this.rgb != null ? this.rgb : null;
    }

    public BlockAdvancedMP setSortCategory(EnumSortCategoryBlock category)
    {
        this.category = category;
        return this;
    }

    public BlockAdvancedMP setRarityRGB(ColorUtils.RGB rgb)
    {
        this.rgb = rgb;
        return this;
    }
}