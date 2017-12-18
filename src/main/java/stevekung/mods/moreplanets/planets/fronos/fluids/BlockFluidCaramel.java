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
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidFinite;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;

public class BlockFluidCaramel extends BlockFluidFinite
{
    public IIcon caramelStillIcon;
    public IIcon caramelFlowingIcon;

    public BlockFluidCaramel(String name)
    {
        super(FronosBlocks.caramel_fluid, Material.water);
        this.setQuantaPerBlock(4);
        this.setRenderPass(1);
        this.setLightOpacity(1);
        this.setBlockName(name);
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
    {
        if (entity instanceof EntityLivingBase)
        {
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 240, 2));
        }
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.caramelStillIcon = iconRegister.registerIcon("fronos:caramel_still");
        this.caramelFlowingIcon = iconRegister.registerIcon("fronos:caramel_flowing");
        FronosBlocks.caramel_fluid.setFlowingIcon(this.caramelStillIcon);
        FronosBlocks.caramel_fluid.setStillIcon(this.caramelFlowingIcon);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        super.randomDisplayTick(par1World, par2, par3, par4, par5Random);

        if (par5Random.nextInt(10) == 0 && World.doesBlockHaveSolidTopSurface(par1World, par2, par3 - 1, par4) && !par1World.getBlock(par2, par3 - 2, par4).getMaterial().blocksMovement())
        {
            double d5 = par2 + par5Random.nextFloat();
            double d6 = par3 - 1.05D;
            double d7 = par4 + par5Random.nextFloat();
            MorePlanetsCore.proxy.spawnParticle("caramelDrip", d5, d6, d7);
        }
    }

    @Override
    public boolean canDisplace(IBlockAccess world, int x, int y, int z)
    {
        if (world.getBlock(x, y, z).getMaterial().isLiquid())
        {
            return false;
        }
        return super.canDisplace(world, x, y, z);
    }

    @Override
    public boolean displaceIfPossible(World world, int x, int y, int z)
    {
        if (world.getBlock(x, y, z).getMaterial().isLiquid())
        {
            return false;
        }
        return super.displaceIfPossible(world, x, y, z);
    }

    @Override
    public IIcon getIcon(int side, int meta)
    {
        return side != 0 && side != 1 ? this.caramelFlowingIcon : this.caramelStillIcon;
    }
}