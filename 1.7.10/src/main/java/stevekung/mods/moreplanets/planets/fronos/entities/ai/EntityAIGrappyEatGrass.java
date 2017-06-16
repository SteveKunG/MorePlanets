/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.entities.ai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;
import stevekung.mods.moreplanets.planets.fronos.blocks.IFronosGrass;

public class EntityAIGrappyEatGrass extends EntityAIBase
{
    private EntityLiving living;
    private World world;
    private int eatGrassTick;

    public EntityAIGrappyEatGrass(EntityLiving living)
    {
        this.living = living;
        this.world = living.worldObj;
        this.setMutexBits(7);
    }

    @Override
    public boolean shouldExecute()
    {
        int x = MathHelper.floor_double(this.living.posX);
        int y = MathHelper.floor_double(this.living.posY);
        int z = MathHelper.floor_double(this.living.posZ);

        if (this.living.getRNG().nextInt(this.living.isChild() ? 50 : 1000) != 0)
        {
            return false;
        }
        if (this.world.getBlock(x, y, z) == FronosBlocks.fronos_tall_grass)
        {
            return true;
        }
        if (this.world.getBlock(x, y - 1, z) instanceof IFronosGrass)
        {
            return true;
        }
        return false;
    }

    @Override
    public void startExecuting()
    {
        this.eatGrassTick = 40;
        this.world.setEntityState(this.living, (byte)10);
        this.living.getNavigator().clearPathEntity();
    }

    @Override
    public void resetTask()
    {
        this.eatGrassTick = 0;
    }

    @Override
    public boolean continueExecuting()
    {
        return this.eatGrassTick > 0;
    }

    public int getEatGrassTick()
    {
        return this.eatGrassTick;
    }

    @Override
    public void updateTask()
    {
        int x = MathHelper.floor_double(this.living.posX);
        int y = MathHelper.floor_double(this.living.posY);
        int z = MathHelper.floor_double(this.living.posZ);
        this.eatGrassTick = Math.max(0, this.eatGrassTick - 1);

        if (this.eatGrassTick != 4)
        {
            return;
        }
        if (this.world.getBlock(x, y, z) == FronosBlocks.fronos_tall_grass)
        {
            if (this.world.getGameRules().getGameRuleBooleanValue("mobGriefing"))
            {
                this.world.func_147480_a(x, y, z, false); //getBlockDestroyEffect
            }
            this.living.eatGrassBonus();
        }
        else if (this.world.getBlock(x, y - 1, z) instanceof IFronosGrass)
        {
            if (this.world.getGameRules().getGameRuleBooleanValue("mobGriefing"))
            {
                this.world.func_147480_a(x, y - 1, z, false);
                this.world.setBlock(x, y - 1, z, FronosBlocks.fronos_dirt, 0, 2);
            }
            this.living.eatGrassBonus();
        }
    }
}