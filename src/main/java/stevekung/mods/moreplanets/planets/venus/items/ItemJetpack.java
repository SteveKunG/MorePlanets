/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.venus.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.proxy.ClientProxyMP;

public class ItemJetpack extends ItemElectricArmorMP
{
    private boolean keyDown;
    private boolean keySneak;

    public ItemJetpack(String name, ArmorMaterial material, int render, int type)
    {
        super(material, render, type);
        this.setUnlocalizedName(name);
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
    {
        if (stack.getItem() == this)
        {
            return "venus:textures/model/jetpack.png";
        }
        return null;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase living, ItemStack itemStack, int slot)
    {
        ModelBiped armorModel = ClientProxyMP.jetpackModel.get(this);

        if (armorModel != null)
        {
            armorModel.bipedHead.showModel = slot == 0;

            if (living.isSneaking())
            {
                armorModel.isSneak = true;
            }
            else
            {
                armorModel.isSneak = false;
            }
        }
        return armorModel;
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
    {
        MorePlanetsCore.proxy.resetPlayerFloatingTick(player);
    }

    @Override
    public String getTextureLocation()
    {
        return "venus";
    }

    @Override
    public Item getRepairItems()
    {
        return null;
    }

    @Override
    public int getRepairItemsMetadata()
    {
        return -1;
    }

    public void setJetpackKeyDown(boolean bool)
    {
        this.keyDown = bool;
    }

    public boolean getJetpackKeyDown()
    {
        return this.keyDown;
    }

    public void setJetpackKeySneak(boolean bool)
    {
        this.keySneak = bool;
    }

    public boolean getJetpackKeySneak()
    {
        return this.keySneak;
    }

    @Override
    public float getMaxElectricityStored(ItemStack itemStack)
    {
        return 100000.0F;
    }
}