package stevekung.mods.moreplanets.planets.nibiru.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.village.Village;
import net.minecraft.village.VillageDoorInfo;

public class EntityAINibiruVillagerMoveIndoors extends EntityAIBase
{
    private final EntityCreature entity;
    private VillageDoorInfo doorInfo;
    private int insidePosX = -1;
    private int insidePosZ = -1;

    public EntityAINibiruVillagerMoveIndoors(EntityCreature entity)
    {
        this.entity = entity;
        this.setMutexBits(1);
    }

    @Override
    public boolean shouldExecute()
    {
        BlockPos pos = new BlockPos(this.entity);

        if ((!this.entity.world.isDaytime() || this.entity.world.isRaining() || this.entity.world.isThundering() && !this.entity.world.getBiome(pos).canRain()) && this.entity.world.provider.hasSkyLight())
        {
            if (this.entity.getRNG().nextInt(50) != 0)
            {
                return false;
            }
            else if (this.insidePosX != -1 && this.entity.getDistanceSq(this.insidePosX, this.entity.posY, this.insidePosZ) < 4.0D)
            {
                return false;
            }
            else
            {
                Village village = this.entity.world.getVillageCollection().getNearestVillage(pos, 14);

                if (village == null)
                {
                    return false;
                }
                else
                {
                    this.doorInfo = village.getDoorInfo(pos);
                    return this.doorInfo != null;
                }
            }
        }
        else
        {
            return false;
        }
    }

    @Override
    public boolean shouldContinueExecuting()
    {
        return !this.entity.getNavigator().noPath();
    }

    @Override
    public void startExecuting()
    {
        this.insidePosX = -1;
        BlockPos pos = this.doorInfo.getInsideBlockPos();
        int i = pos.getX();
        int j = pos.getY();
        int k = pos.getZ();

        if (this.entity.getDistanceSq(pos) > 256.0D)
        {
            Vec3d vec3d = RandomPositionGenerator.findRandomTargetBlockTowards(this.entity, 14, 3, new Vec3d(i + 0.5D, j, k + 0.5D));

            if (vec3d != null)
            {
                this.entity.getNavigator().tryMoveToXYZ(vec3d.x, vec3d.y, vec3d.z, 1.0D);
            }
        }
        else
        {
            this.entity.getNavigator().tryMoveToXYZ(i + 0.5D, j, k + 0.5D, 1.0D);
        }
    }

    @Override
    public void resetTask()
    {
        this.insidePosX = this.doorInfo.getInsideBlockPos().getX();
        this.insidePosZ = this.doorInfo.getInsideBlockPos().getZ();
        this.doorInfo = null;
    }
}