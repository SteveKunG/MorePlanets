package stevekung.mods.moreplanets.planets.nibiru.entity.ai;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.Village;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;
import stevekung.mods.moreplanets.planets.nibiru.entity.EntityNibiruVillager;

public class EntityAINibiruVillagerMate extends EntityAIBase
{
    private final EntityNibiruVillager entity;
    private final World world;
    private EntityNibiruVillager mate;
    private int matingTimeout;
    private Village village;

    public EntityAINibiruVillagerMate(EntityNibiruVillager entity)
    {
        this.entity = entity;
        this.world = entity.world;
        this.setMutexBits(3);
    }

    @Override
    public boolean shouldExecute()
    {
        if (this.entity.getGrowingAge() != 0)
        {
            return false;
        }
        else if (this.entity.getRNG().nextInt(500) != 0)
        {
            return false;
        }
        else
        {
            this.village = this.world.getVillageCollection().getNearestVillage(new BlockPos(this.entity), 0);

            if (this.village == null)
            {
                return false;
            }
            else if (this.checkSufficientDoorsPresentForNewVillager() && this.entity.getIsWillingToMate(true))
            {
                Entity entity = this.world.findNearestEntityWithinAABB(EntityNibiruVillager.class, this.entity.getEntityBoundingBox().grow(8.0D, 3.0D, 8.0D), this.entity);

                if (entity == null)
                {
                    return false;
                }
                else
                {
                    this.mate = (EntityNibiruVillager)entity;
                    return this.mate.getGrowingAge() == 0 && this.mate.getIsWillingToMate(true);
                }
            }
            else
            {
                return false;
            }
        }
    }

    @Override
    public void startExecuting()
    {
        this.matingTimeout = 300;
        this.entity.setMating(true);
    }

    @Override
    public void resetTask()
    {
        this.village = null;
        this.mate = null;
        this.entity.setMating(false);
    }

    @Override
    public boolean shouldContinueExecuting()
    {
        return this.matingTimeout >= 0 && this.checkSufficientDoorsPresentForNewVillager() && this.entity.getGrowingAge() == 0 && this.entity.getIsWillingToMate(false);
    }

    @Override
    public void updateTask()
    {
        --this.matingTimeout;
        this.entity.getLookHelper().setLookPositionWithEntity(this.mate, 10.0F, 30.0F);

        if (this.entity.getDistanceSq(this.mate) > 2.25D)
        {
            this.entity.getNavigator().tryMoveToEntityLiving(this.mate, 0.25D);
        }
        else if (this.matingTimeout == 0 && this.mate.isMating())
        {
            this.giveBirth();
        }

        if (this.entity.getRNG().nextInt(35) == 0)
        {
            this.world.setEntityState(this.entity, (byte)12);
        }
    }

    private boolean checkSufficientDoorsPresentForNewVillager()
    {
        if (!this.village.isMatingSeason())
        {
            return false;
        }
        else
        {
            int i = (int)(this.village.getNumVillageDoors() * 0.35D);
            return this.village.getNumVillagers() < i;
        }
    }

    private void giveBirth()
    {
        EntityAgeable entity = this.entity.createChild(this.mate);
        this.mate.setGrowingAge(6000);
        this.entity.setGrowingAge(6000);
        this.mate.setIsWillingToMate(false);
        this.entity.setIsWillingToMate(false);
        BabyEntitySpawnEvent event = new BabyEntitySpawnEvent(this.entity, this.mate, entity);

        if (MinecraftForge.EVENT_BUS.post(event) || event.getChild() == null)
        {
            return;
        }
        entity = event.getChild();
        entity.setGrowingAge(-24000);
        entity.setLocationAndAngles(this.entity.posX, this.entity.posY, this.entity.posZ, 0.0F, 0.0F);
        this.world.spawnEntity(entity);
        this.world.setEntityState(entity, (byte)12);
    }
}