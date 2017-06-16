package stevekung.mods.moreplanets.module.planets.chalos.blocks.fluid;

import stevekung.mods.moreplanets.util.blocks.fluid.FluidMP;

public class FluidCheeseOfMilkGas extends FluidMP
{
    public FluidCheeseOfMilkGas(String name, String still, String flowing)
    {
        super(name, still, flowing);
        this.setDensity(-1000);
        this.setGaseous(true);
    }
}