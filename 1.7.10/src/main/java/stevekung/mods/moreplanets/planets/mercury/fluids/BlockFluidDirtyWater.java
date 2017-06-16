/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.mercury.fluids;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.blocks.BlockFluidBaseMP;
import stevekung.mods.moreplanets.planets.mercury.blocks.MercuryBlocks;

public class BlockFluidDirtyWater extends BlockFluidBaseMP
{
    public BlockFluidDirtyWater(String name)
    {
        super(MercuryBlocks.dirty_water_fluid);
        this.setRenderPass(1);
        this.setLightOpacity(5);
        this.setBlockName(name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random rand)
    {
        super.randomDisplayTick(world, x, y, z, rand);
        int meta = world.getBlockMetadata(x, y, z);

        if (rand.nextInt(64) == 0)
        {
            if (meta > 0 && meta < 8)
            {
                world.playSound(x + 0.5F, y + 0.5F, z + 0.5F, "liquid.water", rand.nextFloat() * 0.25F + 0.75F, rand.nextFloat() * 1.0F + 0.5F, false);
            }
        }
        if (rand.nextInt(10) == 0)
        {
            if (meta <= 0 || meta >= 8)
            {
                world.spawnParticle("suspended", x + rand.nextFloat(), y + rand.nextFloat(), z + rand.nextFloat(), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    public String getStillTextures()
    {
        return "mercury:dirty_water_still";
    }

    @Override
    public String getFlowingTextures()
    {
        return "mercury:dirty_water_flowing";
    }
}