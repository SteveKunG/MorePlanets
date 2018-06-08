package stevekung.mods.moreplanets.planets.nibiru.entity;

import micdoodle8.mods.galacticraft.api.entity.IEntityBreathable;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityTerrasquid extends EntitySquid implements IEntityBreathable
{
    public EntityTerrasquid(World world)
    {
        super(world);
    }

    @Override
    protected void initEntityAI()
    {
        this.tasks.addTask(0, new AIMoveRandom(this));
    }

    @Override
    public boolean canBreath()
    {
        return true;
    }

    static class AIMoveRandom extends EntityAIBase
    {
        private final EntityTerrasquid entity;

        public AIMoveRandom(EntityTerrasquid entity)
        {
            this.entity = entity;
        }

        @Override
        public boolean shouldExecute()
        {
            return true;
        }

        @Override
        public void updateTask()
        {
            int i = this.entity.getIdleTime();

            if (i > 100)
            {
                this.entity.setMovementVector(0.0F, 0.0F, 0.0F);
            }
            else if (this.entity.getRNG().nextInt(100) == 0 || !this.entity.inWater || !this.entity.hasMovementVector())
            {
                float f = this.entity.getRNG().nextFloat() * ((float)Math.PI * 2F);
                float f1 = MathHelper.cos(f) * 0.2F;
                float f2 = -0.1F + this.entity.getRNG().nextFloat() * 0.2F;
                float f3 = MathHelper.sin(f) * 0.2F;
                this.entity.setMovementVector(f1, f2, f3);
            }
        }
    }
}