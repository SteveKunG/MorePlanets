/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.pluto.fluids;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.blocks.BlockFluidBaseMP;
import stevekung.mods.moreplanets.core.util.WorldUtilMP;
import stevekung.mods.moreplanets.planets.pluto.blocks.PlutoBlocks;
import stevekung.mods.moreplanets.planets.pluto.dimension.WorldProviderPluto;

public class BlockFluidNitrogen extends BlockFluidBaseMP
{
    public BlockFluidNitrogen(String name)
    {
        super(PlutoBlocks.liquid_nitrogen_fluid);
        this.setQuantaPerBlock(8);
        this.setRenderPass(1);
        this.setTickRandomly(true);
        this.setBlockName(name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int par2, int par3, int par4, Random rand)
    {
        super.randomDisplayTick(world, par2, par3, par4, rand);
        int meta = world.getBlockMetadata(par2, par3, par4);

        if (rand.nextInt(64) == 0)
        {
            if (meta > 0 && meta < 8)
            {
                world.playSound(par2 + 0.5F, par3 + 0.5F, par4 + 0.5F, "liquid.water", rand.nextFloat() * 0.25F + 0.75F, rand.nextFloat() * 1.0F + 0.5F, false);
            }
        }
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
    {
        if (entity instanceof EntityLivingBase)
        {
            if (WorldUtilMP.isSpaceWorld(world, new WorldProviderPluto()))
            {
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 60, 2));
            }
        }
    }

    @Override
    public String getStillTextures()
    {
        return "mpcore:liquid_gas_still";
    }

    @Override
    public String getFlowingTextures()
    {
        return "mpcore:liquid_gas_flowing";
    }
}