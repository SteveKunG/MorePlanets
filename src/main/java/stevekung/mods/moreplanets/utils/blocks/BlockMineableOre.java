package stevekung.mods.moreplanets.utils.blocks;

import micdoodle8.mods.galacticraft.api.block.IDetectableResource;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;

public class BlockMineableOre extends BlockBaseMP implements IDetectableResource
{
    public BlockMineableOre(String name)
    {
        super(Material.ROCK);
        this.setUnlocalizedName(name);
    }

    @Override
    public EnumSortCategoryBlock getBlockCategory()
    {
        return EnumSortCategoryBlock.ORE;
    }

    @Override
    public boolean isValueable(IBlockState state)
    {
        return true;
    }
}