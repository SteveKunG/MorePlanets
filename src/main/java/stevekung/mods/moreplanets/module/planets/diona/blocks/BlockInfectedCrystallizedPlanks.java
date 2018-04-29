package stevekung.mods.moreplanets.module.planets.diona.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.utils.CompatibilityManagerMP;
import stevekung.mods.moreplanets.utils.blocks.BlockBaseMP;

public class BlockInfectedCrystallizedPlanks extends BlockBaseMP
{
    public BlockInfectedCrystallizedPlanks(String name)
    {
        super(Material.WOOD);
        this.setHardness(2.0F);
        this.setResistance(5.0F);
        this.setSoundType(SoundType.WOOD);
        this.setUnlocalizedName(name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return CompatibilityManagerMP.isCTMLoaded ? BlockRenderLayer.CUTOUT : BlockRenderLayer.SOLID;
    }
}