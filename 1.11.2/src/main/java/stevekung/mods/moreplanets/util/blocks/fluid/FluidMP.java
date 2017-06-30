package stevekung.mods.moreplanets.util.blocks.fluid;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class FluidMP extends Fluid
{
    public FluidMP(String name)
    {
        super(name, new ResourceLocation("moreplanets:blocks/" + name.replace("_fluid", "") + "_still"), new ResourceLocation("moreplanets:blocks/" + name.replace("_fluid", "") + "_flowing"));
    }

    public FluidMP(String name, String still, String flowing)
    {
        super(name, new ResourceLocation(still), new ResourceLocation(flowing));
    }
}