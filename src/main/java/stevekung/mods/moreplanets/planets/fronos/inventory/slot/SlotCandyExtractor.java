/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.inventory.slot;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import stevekung.mods.moreplanets.core.recipe.CandyExtractorRecipes;

public class SlotCandyExtractor extends Slot
{
    private final EntityPlayer thePlayer;
    private int experience;

    public SlotCandyExtractor(EntityPlayer player, IInventory inventory, int x, int y, int z)
    {
        super(inventory, x, y, z);
        this.thePlayer = player;
    }

    @Override
    public boolean isItemValid(ItemStack itemStack)
    {
        return false;
    }

    @Override
    public ItemStack decrStackSize(int par1)
    {
        if (this.getHasStack())
        {
            this.experience += Math.min(par1, this.getStack().stackSize);
        }
        return super.decrStackSize(par1);
    }

    @Override
    public void onPickupFromSlot(EntityPlayer player, ItemStack itemStack)
    {
        this.onCrafting(itemStack);
        super.onPickupFromSlot(player, itemStack);
    }

    @Override
    protected void onCrafting(ItemStack itemStack, int par2)
    {
        this.experience += par2;
        this.onCrafting(itemStack);
    }

    @Override
    protected void onCrafting(ItemStack itemStack)
    {
        itemStack.onCrafting(this.thePlayer.worldObj, this.thePlayer, this.experience);

        if (!this.thePlayer.worldObj.isRemote)
        {
            int i = this.experience;
            final float f = CandyExtractorRecipes.extracting().getSlot(itemStack);

            if (f == 0.0F)
            {
                i = 0;
            }
            else if (f < 1.0F)
            {
                int j = MathHelper.floor_float(i * f);
                if (j < MathHelper.ceiling_float_int(i * f) && (float)Math.random() < i * f - j)
                {
                    j++;
                }
                i = j;
            }
            while (i > 0)
            {
                final int j = EntityXPOrb.getXPSplit(i);
                i -= j;
                this.thePlayer.worldObj.spawnEntityInWorld(new EntityXPOrb(this.thePlayer.worldObj, this.thePlayer.posX, this.thePlayer.posY + 0.5D, this.thePlayer.posZ + 0.5D, j));
            }
        }
        this.experience = 0;
        FMLCommonHandler.instance().firePlayerSmeltedEvent(this.thePlayer, itemStack);
    }
}