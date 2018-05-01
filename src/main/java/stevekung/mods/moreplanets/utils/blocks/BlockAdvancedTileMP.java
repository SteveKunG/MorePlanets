package stevekung.mods.moreplanets.utils.blocks;

import micdoodle8.mods.galacticraft.core.blocks.BlockAdvancedTile;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.utils.client.renderer.IItemModelRender;

public abstract class BlockAdvancedTileMP extends BlockAdvancedTile implements ISortableBlock, IItemModelRender
{
    private String name;

    public BlockAdvancedTileMP(Material material)
    {
        super(material);
    }

    @Override
    public Block setUnlocalizedName(String name)
    {
        this.name = name;
        return super.setUnlocalizedName(name);
    }

    @Override
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return MorePlanetsMod.BLOCK_TAB;
    }

    @Override
    public String getName()
    {
        return this.name;
    }
}