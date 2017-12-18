/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.nibiru.items;

import java.util.List;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import micdoodle8.mods.galacticraft.api.entity.IRocketType;
import micdoodle8.mods.galacticraft.api.entity.IRocketType.EnumRocketType;
import micdoodle8.mods.galacticraft.api.item.IHoldableItem;
import micdoodle8.mods.galacticraft.api.prefab.entity.EntityAutoRocket;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.blocks.GCBlocks;
import micdoodle8.mods.galacticraft.core.tile.TileEntityLandingPad;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.core.items.ItemMorePlanet;
import stevekung.mods.moreplanets.planets.nibiru.entities.EntityTier6Rocket;
import stevekung.mods.moreplanets.planets.nibiru.entities.EntityTier6RocketNoFlag;

public class ItemTier6Rocket extends ItemMorePlanet implements IHoldableItem
{
    public ItemTier6Rocket(String name)
    {
        super();
        this.setHasSubtypes(true);
        this.setMaxStackSize(1);
        this.setUnlocalizedName(name);
        this.setTextureName("mpcore:blank");
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
        boolean padFound = false;
        TileEntity tile = null;

        if (world.isRemote)
        {
            return false;
        }
        else
        {
            float centerX = -1;
            float centerY = -1;
            float centerZ = -1;

            for (int i = -1; i < 2; i++)
            {
                for (int j = -1; j < 2; j++)
                {
                    final Block block = world.getBlock(par4 + i, par5, par6 + j);
                    final int meta = world.getBlockMetadata(par4 + i, par5, par6 + j);

                    if (block == GCBlocks.landingPadFull && meta == 0)
                    {
                        padFound = true;
                        tile = world.getTileEntity(par4 + i, par5, par6 + j);

                        centerX = par4 + i + 0.5F;
                        centerY = par5 + 0.4F;
                        centerZ = par6 + j + 0.5F;
                        break;
                    }
                }
                if (padFound)
                {
                    break;
                }
            }

            if (padFound)
            {
                if (tile instanceof TileEntityLandingPad)
                {
                    if (((TileEntityLandingPad)tile).getDockedEntity() != null)
                    {
                        return false;
                    }
                }
                else
                {
                    return false;
                }

                EntityAutoRocket rocket;

                if (itemStack.getItemDamage() < 10)
                {
                    rocket = new EntityTier6Rocket(world, centerX, centerY, centerZ, EnumRocketType.values()[itemStack.getItemDamage()]);
                }
                else
                {
                    rocket = new EntityTier6RocketNoFlag(world, centerX, centerY, centerZ, EnumRocketType.values()[itemStack.getItemDamage() - 10]);
                }

                rocket.setPosition(rocket.posX, rocket.posY + rocket.getOnPadYOffset(), rocket.posZ);
                world.spawnEntityInWorld(rocket);

                if (itemStack.hasTagCompound() && itemStack.getTagCompound().hasKey("RocketFuel"))
                {
                    rocket.fuelTank.fill(new FluidStack(GalacticraftCore.fluidFuel, itemStack.getTagCompound().getInteger("RocketFuel")), true);
                }
                if (!player.capabilities.isCreativeMode)
                {
                    itemStack.stackSize--;

                    if (itemStack.stackSize <= 0)
                    {
                        itemStack = null;
                    }
                }
                if (((IRocketType) rocket).getType().getPreFueled())
                {
                    if (rocket instanceof EntityTier6Rocket)
                    {
                        ((EntityTier6Rocket) rocket).fuelTank.fill(new FluidStack(GalacticraftCore.fluidFuel, rocket.getMaxFuel()), true);
                    }
                    else if (rocket instanceof EntityTier6RocketNoFlag)
                    {
                        ((EntityTier6RocketNoFlag) rocket).fuelTank.fill(new FluidStack(GalacticraftCore.fluidFuel, rocket.getMaxFuel()), true);
                    }
                }
            }
            else
            {
                return false;
            }
        }
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs creativeTabs, List list)
    {
        if (ConfigManagerMP.enableRocketWithThaiFlag)
        {
            for (int i = 0; i < EnumRocketType.values().length; i++)
            {
                list.add(new ItemStack(item, 1, i));
            }
        }
        for (int i = 10; i < 10 + EnumRocketType.values().length; i++)
        {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4)
    {
        EnumRocketType type = null;

        if (itemStack.getItemDamage() < 10)
        {
            type = EnumRocketType.values()[itemStack.getItemDamage()];
        }
        else
        {
            if (ConfigManagerMP.enableRocketWithThaiFlag)
            {
                list.add(EnumChatFormatting.GRAY + "\u00a7o" + StatCollector.translateToLocal("desc.rocket.noflag.name"));
            }
            type = EnumRocketType.values()[itemStack.getItemDamage() - 10];
        }

        if (!type.getTooltip().isEmpty())
        {
            list.add(type.getTooltip());
        }
        if (type.getPreFueled())
        {
            list.add(EnumChatFormatting.RED + "\u00a7o" + GCCoreUtil.translate("gui.creativeOnly.desc"));
        }
        if (itemStack.hasTagCompound() && itemStack.getTagCompound().hasKey("RocketFuel"))
        {
            EntityAutoRocket rocket;

            if (itemStack.getItemDamage() < 10)
            {
                rocket = new EntityTier6Rocket(FMLClientHandler.instance().getWorldClient(), 0, 0, 0, EnumRocketType.values()[itemStack.getItemDamage()]);
            }
            else
            {
                rocket = new EntityTier6RocketNoFlag(FMLClientHandler.instance().getWorldClient(), 0, 0, 0, EnumRocketType.values()[itemStack.getItemDamage() - 10]);
            }
            list.add(GCCoreUtil.translate("gui.message.fuel.name") + ": " + itemStack.getTagCompound().getInteger("RocketFuel") + " / " + rocket.fuelTank.getCapacity());
        }
    }

    @Override
    public boolean shouldHoldLeftHandUp(EntityPlayer player)
    {
        return true;
    }

    @Override
    public boolean shouldHoldRightHandUp(EntityPlayer player)
    {
        return true;
    }

    @Override
    public boolean shouldCrouch(EntityPlayer player)
    {
        return true;
    }
}