/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.fluids;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.blocks.BlockFluidBaseMP;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;

public class BlockFluidMineralWater extends BlockFluidBaseMP
{
    public BlockFluidMineralWater(String name)
    {
        super(FronosBlocks.mineral_water_fluid);
        this.setRenderPass(1);
        this.setLightOpacity(3);
        this.setBlockName(name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        super.randomDisplayTick(par1World, par2, par3, par4, par5Random);
        int meta = par1World.getBlockMetadata(par2, par3, par4);

        if (par5Random.nextInt(1) == 0)
        {
            MorePlanetsCore.proxy.spawnParticle("mineralWater", par2 + par5Random.nextFloat(), par3 + 1, par4 + par5Random.nextFloat());
        }
        if (par5Random.nextInt(64) == 0)
        {
            if (meta > 0 && meta < 8)
            {
                par1World.playSound(par2 + 0.5F, par3 + 0.5F, par4 + 0.5F, "liquid.water", par5Random.nextFloat() * 0.25F + 0.75F, par5Random.nextFloat() * 1.0F + 0.5F, false);
            }
        }
        if (par5Random.nextInt(10) == 0)
        {
            if (meta <= 0 || meta >= 8)
            {
                par1World.spawnParticle("suspended", par2 + par5Random.nextFloat(), par3 + par5Random.nextFloat(), par4 + par5Random.nextFloat(), 0.0D, 0.0D, 0.0D);
            }
        }
        if (par5Random.nextInt(10) == 0 && World.doesBlockHaveSolidTopSurface(par1World, par2, par3 - 1, par4) && !par1World.getBlock(par2, par3 - 2, par4).getMaterial().blocksMovement())
        {
            double d5 = par2 + par5Random.nextFloat();
            double d6 = par3 - 1.05D;
            double d7 = par4 + par5Random.nextFloat();
            MorePlanetsCore.proxy.spawnParticle("mineralWaterDrip", d5, d6, d7);
        }
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
    {
        if (entity instanceof EntityLivingBase)
        {
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.regeneration.id, 50, 1));
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 100, 1));
        }
    }

    @Override
    public String getStillTextures()
    {
        return "fronos:mineral_water_still";
    }

    @Override
    public String getFlowingTextures()
    {
        return "fronos:mineral_water_flowing";
    }
}