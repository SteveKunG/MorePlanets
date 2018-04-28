package stevekung.mods.moreplanets.module.planets.diona.blocks;

import micdoodle8.mods.galacticraft.api.block.IDetectableResource;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.util.CompatibilityManagerMP;
import stevekung.mods.moreplanets.util.blocks.BlockBaseMP;
import stevekung.mods.moreplanets.util.blocks.EnumSortCategoryBlock;

public class BlockGlowingIronBlock extends BlockBaseMP implements IDetectableResource
{
    public BlockGlowingIronBlock(String name)
    {
        super(Material.IRON);
        this.setHardness(5.0F);
        this.setResistance(10.0F);
        this.setSoundType(SoundType.METAL);
        this.setUnlocalizedName(name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return CompatibilityManagerMP.isCTMLoaded ? BlockRenderLayer.CUTOUT : BlockRenderLayer.SOLID;
    }

    @Override
    public EnumSortCategoryBlock getBlockCategory()
    {
        return EnumSortCategoryBlock.INGOT_BLOCK;
    }

    @Override
    public boolean isValueable(IBlockState state)
    {
        return true;
    }
}