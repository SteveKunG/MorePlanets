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

public class BlockFluidTea extends BlockFluidBaseMP
{
    public BlockFluidTea(String name)
    {
        super(FronosBlocks.tea_fluid);
        this.setRenderPass(1);
        this.setQuantaPerBlock(4);
        this.setLightOpacity(3);
        this.setBlockName(name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        super.randomDisplayTick(par1World, par2, par3, par4, par5Random);

        if (par5Random.nextInt(1) == 0)
        {
            MorePlanetsCore.proxy.spawnParticle("tea", par2 + par5Random.nextFloat(), par3 + 1, par4 + par5Random.nextFloat());
        }
        if (par5Random.nextInt(10) == 0 && World.doesBlockHaveSolidTopSurface(par1World, par2, par3 - 1, par4) && !par1World.getBlock(par2, par3 - 2, par4).getMaterial().blocksMovement())
        {
            double d5 = par2 + par5Random.nextFloat();
            double d6 = par3 - 1.05D;
            double d7 = par4 + par5Random.nextFloat();
            MorePlanetsCore.proxy.spawnParticle("teaDrip", d5, d6, d7);
        }
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
    {
        if (entity instanceof EntityLivingBase)
        {
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.resistance.id, 60, 1));
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 100, 1));
        }
    }

    @Override
    public String getStillTextures()
    {
        return "fronos:tea_still";
    }

    @Override
    public String getFlowingTextures()
    {
        return "fronos:tea_flowing";
    }
}