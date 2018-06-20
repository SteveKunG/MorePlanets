package stevekung.mods.moreplanets.utils.entity.ai;

import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIAttackRangedBow;
import net.minecraft.entity.monster.EntityMob;
import stevekung.mods.moreplanets.init.MPItems;

public class EntityAIAttackRangedBowMP<T extends EntityMob & IRangedAttackMob> extends EntityAIAttackRangedBow<T>
{
    private final T entity;

    public EntityAIAttackRangedBowMP(T entity, double speedAmplifier, int delay, float maxDistance)
    {
        super(entity, speedAmplifier, delay, maxDistance);
        this.entity = entity;
    }

    @Override
    protected boolean isBowInMainhand()
    {
        return !this.entity.getHeldItemMainhand().isEmpty() && this.entity.getHeldItemMainhand().getItem() == MPItems.SPACE_BOW;
    }
}