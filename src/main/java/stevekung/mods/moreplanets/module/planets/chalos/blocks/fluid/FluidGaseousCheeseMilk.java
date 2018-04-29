package stevekung.mods.moreplanets.module.planets.chalos.blocks.fluid;

import stevekung.mods.moreplanets.utils.blocks.fluid.FluidMP;

public class FluidGaseousCheeseMilk extends FluidMP
{
    public FluidGaseousCheeseMilk(String name, String still, String flowing)
    {
        super(name, still, flowing);
        this.setDensity(-1000);
        this.setGaseous(true);
    }
}