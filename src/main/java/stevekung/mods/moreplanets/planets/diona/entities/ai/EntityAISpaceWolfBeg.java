/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.diona.entities.ai;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.planets.diona.entities.EntitySpaceWolf;

public class EntityAISpaceWolfBeg extends EntityAIBase
{
    private EntitySpaceWolf theWolf;
    private EntityPlayer thePlayer;
    private World worldObject;
    private float minPlayerDistance;
    private int field_75384_e;

    public EntityAISpaceWolfBeg(EntitySpaceWolf par1EntityWolf, float par2)
    {
        this.theWolf = par1EntityWolf;
        this.worldObject = par1EntityWolf.worldObj;
        this.minPlayerDistance = par2;
        this.setMutexBits(2);
    }

    @Override
    public boolean shouldExecute()
    {
        this.thePlayer = this.worldObject.getClosestPlayerToEntity(this.theWolf, this.minPlayerDistance);
        return this.thePlayer == null ? false : this.hasPlayerGotBoneInHand(this.thePlayer);
    }

    @Override
    public boolean continueExecuting()
    {
        return !this.thePlayer.isEntityAlive() ? false : this.theWolf.getDistanceSqToEntity(this.thePlayer) > this.minPlayerDistance * this.minPlayerDistance ? false : this.field_75384_e > 0 && this.hasPlayerGotBoneInHand(this.thePlayer);
    }

    @Override
    public void startExecuting()
    {
        this.theWolf.func_70918_i(true);
        this.field_75384_e = 40 + this.theWolf.getRNG().nextInt(40);
    }

    @Override
    public void resetTask()
    {
        this.theWolf.func_70918_i(false);
        this.thePlayer = null;
    }

    @Override
    public void updateTask()
    {
        this.theWolf.getLookHelper().setLookPosition(this.thePlayer.posX, this.thePlayer.posY + this.thePlayer.getEyeHeight(), this.thePlayer.posZ, 10.0F, this.theWolf.getVerticalFaceSpeed());
        --this.field_75384_e;
    }

    private boolean hasPlayerGotBoneInHand(EntityPlayer par1EntityPlayer)
    {
        ItemStack itemstack = par1EntityPlayer.inventory.getCurrentItem();
        return itemstack == null ? false : !this.theWolf.isTamed() && itemstack.getItem() == Items.bone ? true : this.theWolf.isBreedingItem(itemstack);
    }
}