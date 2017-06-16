/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.europa.blocks.fluids;

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
import stevekung.mods.moreplanets.moons.europa.blocks.EuropaBlocks;

public class BlockFluidEuropaWater extends BlockFluidBaseMP
{
    public BlockFluidEuropaWater(String name)
    {
        super(EuropaBlocks.europa_water_fluid);
        this.setRenderPass(1);
        this.setLightOpacity(3);
        this.setBlockName(name);
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
    {
        if (entity instanceof EntityLivingBase)
        {
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 50, 1));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random rand)
    {
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
        if (rand.nextInt(10) == 0 && World.doesBlockHaveSolidTopSurface(world, x, y - 1, z) && !world.getBlock(x, y - 2, z).getMaterial().blocksMovement())
        {
            double d5 = x + rand.nextFloat();
            double d6 = y - 1.05D;
            double d7 = z + rand.nextFloat();
            MorePlanetsCore.proxy.spawnParticle("frozenWaterDrip", d5, d6, d7);
        }
    }

    @Override
    public String getStillTextures()
    {
        return "europa:europa_water_still";
    }

    @Override
    public String getFlowingTextures()
    {
        return "europa:europa_water_flowing";
    }
}