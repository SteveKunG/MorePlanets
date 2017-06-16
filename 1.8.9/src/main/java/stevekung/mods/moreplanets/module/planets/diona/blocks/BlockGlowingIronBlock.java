package stevekung.mods.moreplanets.module.planets.diona.blocks;

import micdoodle8.mods.galacticraft.api.block.IDetectableResource;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import stevekung.mods.moreplanets.util.blocks.BlockBaseMP;
import stevekung.mods.moreplanets.util.blocks.EnumSortCategoryBlock;

public class BlockGlowingIronBlock extends BlockBaseMP implements IDetectableResource
{
    public BlockGlowingIronBlock(String name)
    {
        super(Material.iron);
        this.setHardness(5.0F);
        this.setResistance(10.0F);
        this.setStepSound(soundTypeMetal);
        this.setUnlocalizedName(name);
    }

    @Override
    public EnumSortCategoryBlock getBlockCategory(int meta)
    {
        return EnumSortCategoryBlock.INGOT_BLOCK;
    }

    @Override
    public boolean isValueable(IBlockState state)
    {
        return true;
    }

    @Override
    public String getName()
    {
        return "glowing_iron_block";
    }

    @Override
    public Block getBlock()
    {
        return this;
    }
}