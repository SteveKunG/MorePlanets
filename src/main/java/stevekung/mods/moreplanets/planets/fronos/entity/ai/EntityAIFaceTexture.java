package stevekung.mods.moreplanets.planets.fronos.entity.ai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.world.World;

public class EntityAIFaceTexture extends EntityAIBase
{
    private EntityLiving entity;
    private World world;
    private int timer;

    public EntityAIFaceTexture(EntityLiving entity)
    {
        this.entity = entity;
        this.world = entity.world;
        this.setMutexBits(7);
    }

    @Override
    public boolean shouldExecute()
    {
        if (this.entity.getRNG().nextInt(this.entity.isChild() ? 5 : 25) != 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    @Override
    public void startExecuting()
    {
        this.timer = 20;
        this.world.setEntityState(this.entity, (byte)10);
        this.entity.getNavigator().clearPath();
    }

    @Override
    public void resetTask()
    {
        this.timer = 0;
    }

    @Override
    public boolean shouldContinueExecuting()
    {
        return this.timer > 0;
    }

    @Override
    public void updateTask()
    {
        this.timer = Math.max(0, this.timer - 1);
    }

    public int getTimer()
    {
        return this.timer;
    }
}