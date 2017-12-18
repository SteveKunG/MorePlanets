/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.entities.ai;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.planets.fronos.entities.IFronosPet;
import stevekung.mods.moreplanets.planets.fronos.items.FronosItems;

public class EntityAIFronosBeg extends EntityAIBase
{
    private IFronosPet pet;
    private EntityPlayer thePlayer;
    private World world;
    private float minPlayerDistance;
    private int field_75384_e;

    public EntityAIFronosBeg(IFronosPet pet, float distance)
    {
        this.pet = pet;
        this.world = pet.worldObj;
        this.minPlayerDistance = distance;
        this.setMutexBits(2);
    }

    @Override
    public boolean shouldExecute()
    {
        this.thePlayer = this.world.getClosestPlayerToEntity(this.pet, this.minPlayerDistance);
        return this.thePlayer == null ? false : this.hasPlayerGotPearlInHand(this.thePlayer);
    }

    @Override
    public boolean continueExecuting()
    {
        return !this.thePlayer.isEntityAlive() ? false : this.pet.getDistanceSqToEntity(this.thePlayer) > this.minPlayerDistance * this.minPlayerDistance ? false : this.field_75384_e > 0 && this.hasPlayerGotPearlInHand(this.thePlayer);
    }

    @Override
    public void startExecuting()
    {
        this.field_75384_e = 40 + this.pet.getRNG().nextInt(40);
    }

    @Override
    public void resetTask()
    {
        this.thePlayer = null;
    }

    @Override
    public void updateTask()
    {
        this.pet.getLookHelper().setLookPosition(this.thePlayer.posX, this.thePlayer.posY + this.thePlayer.getEyeHeight(), this.thePlayer.posZ, 10.0F, this.pet.getVerticalFaceSpeed());
        --this.field_75384_e;
    }

    private boolean hasPlayerGotPearlInHand(EntityPlayer player)
    {
        ItemStack itemStack = player.inventory.getCurrentItem();
        return itemStack == null ? false : !this.pet.isTamed() && itemStack.getItem() == FronosItems.pearl && itemStack.getItemDamage() == 0 ? true : this.pet.isBreedingItem(itemStack);
    }
}