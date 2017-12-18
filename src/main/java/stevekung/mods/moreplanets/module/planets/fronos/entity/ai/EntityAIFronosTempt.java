package stevekung.mods.moreplanets.module.planets.fronos.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.util.entity.ai.PathNavigateGroundMP;

public class EntityAIFronosTempt extends EntityAIBase
{
    private EntityCreature temptedEntity;
    private double speed;
    private double targetX;
    private double targetY;
    private double targetZ;
    private double rotationPitch;
    private double rotationYaw;
    private EntityPlayer temptingPlayer;
    private int delayTemptCounter;
    private ItemStack itemStack;
    private boolean scaredByPlayerMovement;
    int timer;

    public EntityAIFronosTempt(EntityCreature entity, double speed, ItemStack itemStack, boolean scared)
    {
        this.temptedEntity = entity;
        this.speed = speed;
        this.itemStack = itemStack;
        this.scaredByPlayerMovement = scared;
        this.setMutexBits(3);

        if (!(entity.getNavigator() instanceof PathNavigateGroundMP))
        {
            throw new IllegalArgumentException("Unsupported mob type for TemptGoal");
        }
    }

    @Override
    public boolean shouldExecute()
    {
        if (this.delayTemptCounter > 0)
        {
            --this.delayTemptCounter;
            return false;
        }
        else
        {
            this.temptingPlayer = this.temptedEntity.world.getClosestPlayerToEntity(this.temptedEntity, 10.0D);

            if (this.temptingPlayer == null)
            {
                return false;
            }
            else
            {
                ItemStack itemStack = this.temptingPlayer.getHeldItemMainhand();
                return itemStack.isEmpty() ? false : itemStack.getItem() == this.itemStack.getItem() && itemStack.getItemDamage() == this.itemStack.getItemDamage();
            }
        }
    }

    @Override
    public boolean continueExecuting()
    {
        if (this.scaredByPlayerMovement)
        {
            if (this.temptedEntity.getDistanceSqToEntity(this.temptingPlayer) < 36.0D)
            {
                if (this.temptingPlayer.getDistanceSq(this.targetX, this.targetY, this.targetZ) > 0.010000000000000002D)
                {
                    return false;
                }
                if (Math.abs(this.temptingPlayer.rotationPitch - this.rotationPitch) > 5.0D || Math.abs(this.temptingPlayer.rotationYaw - this.rotationYaw) > 5.0D)
                {
                    return false;
                }
            }
            else
            {
                this.targetX = this.temptingPlayer.posX;
                this.targetY = this.temptingPlayer.posY;
                this.targetZ = this.temptingPlayer.posZ;
            }
            this.rotationPitch = this.temptingPlayer.rotationPitch;
            this.rotationYaw = this.temptingPlayer.rotationYaw;
        }
        return this.shouldExecute();
    }

    @Override
    public void startExecuting()
    {
        this.targetX = this.temptingPlayer.posX;
        this.targetY = this.temptingPlayer.posY;
        this.targetZ = this.temptingPlayer.posZ;
        this.timer = 1;
        this.temptedEntity.world.setEntityState(this.temptedEntity, (byte)12);
    }

    @Override
    public void resetTask()
    {
        this.temptingPlayer = null;
        this.temptedEntity.getNavigator().clearPathEntity();
        this.delayTemptCounter = 100;
        this.timer = 0;
    }

    @Override
    public void updateTask()
    {
        this.temptedEntity.getLookHelper().setLookPositionWithEntity(this.temptingPlayer, 30.0F, this.temptedEntity.getVerticalFaceSpeed());

        if (this.temptedEntity.getDistanceSqToEntity(this.temptingPlayer) < 6.25D)
        {
            this.temptedEntity.getNavigator().clearPathEntity();
        }
        else
        {
            this.temptedEntity.getNavigator().tryMoveToEntityLiving(this.temptingPlayer, this.speed);
            this.timer = 1;
        }
    }

    public int getTimer()
    {
        return this.timer;
    }
}