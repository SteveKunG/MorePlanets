package stevekung.mods.moreplanets.planets.diona.blocks;

import micdoodle8.mods.galacticraft.api.block.IDetectableResource;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.utils.CompatibilityManagerMP;
import stevekung.mods.moreplanets.utils.blocks.BlockBaseMP;
import stevekung.mods.moreplanets.utils.blocks.EnumSortCategoryBlock;
import stevekung.mods.moreplanets.utils.itemblocks.IItemRarity;
import stevekung.mods.stevekunglib.utils.ColorUtils;

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
    public ColorUtils.RGB getRarity()
    {
        return ColorUtils.stringToFullRGB(IItemRarity.ALIEN);
    }

    @Override
    public boolean isValueable(IBlockState state)
    {
        return true;
    }
}