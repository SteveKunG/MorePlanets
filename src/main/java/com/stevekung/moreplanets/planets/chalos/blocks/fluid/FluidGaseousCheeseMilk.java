package com.stevekung.moreplanets.planets.chalos.blocks.fluid;

import com.stevekung.moreplanets.utils.blocks.fluid.FluidMP;

public class FluidGaseousCheeseMilk extends FluidMP
{
    public FluidGaseousCheeseMilk(String name, String still, String flowing)
    {
        super(name, still, flowing);
        this.setDensity(-1000);
        this.setGaseous(true);
    }
}