package stevekung.mods.moreplanets.util.blocks;

import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.BlockFluidFinite;
import net.minecraftforge.fluids.Fluid;

public abstract class BlockFluidFiniteMP extends BlockFluidFinite implements ISingleBlockRender
{
    public BlockFluidFiniteMP(Fluid fluid, Material material)
    {
        super(fluid, material);
    }
}