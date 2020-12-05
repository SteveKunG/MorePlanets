package stevekung.mods.moreplanets.utils.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.utils.client.renderer.IItemModelRender;

public class BlockTrapdoorMP extends BlockTrapDoor implements ISortableBlock, IItemModelRender
{
    private String name;

    public BlockTrapdoorMP(String name)
    {
        super(Material.WOOD);
        this.setUnlocalizedName(name);
        this.setSoundType(SoundType.WOOD);
        this.setHardness(3.0F);
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
    public EnumSortCategoryBlock getBlockCategory()
    {
        return EnumSortCategoryBlock.TRAPDOOR;
    }

    @Override
    public String getName()
    {
        return this.name;
    }
}