package stevekung.mods.moreplanets.module.planets.diona.entity;

import java.util.List;

import micdoodle8.mods.galacticraft.api.entity.IEntityBreathable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.init.MPPotions;
import stevekung.mods.moreplanets.module.planets.diona.items.DionaItems;

public class EntityZeliusCreeper extends EntityCreeper implements IEntityBreathable
{
    public EntityZeliusCreeper(World world)
    {
        super(world);
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(25.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
    }

    @Override
    public void fall(float distance, float damageMultiplier)
    {
        super.fall(distance, damageMultiplier);

        if (distance >= 5 && this.getHealth() <= 0.0F)
        {
            this.explode();
        }
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();

        if (this.isEntityAlive())
        {
            if (this.timeSinceIgnited >= this.fuseTime)
            {
                this.explode();
            }
        }
    }

    @Override
    public void explode()
    {
        if (!this.worldObj.isRemote)
        {
            boolean flag = this.worldObj.getGameRules().getBoolean("mobGriefing");

            if (this.getPowered())
            {
                this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, this.explosionRadius * 2, flag);
                List<EntityLivingBase> list = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(this.posX - this.explosionRadius * 2, this.posY - this.explosionRadius * 2, this.posZ - this.explosionRadius * 2, this.posX + this.explosionRadius * 2, this.posY + this.explosionRadius * 2, this.posZ + this.explosionRadius * 2));

                for (EntityLivingBase living : list)
                {
                    living.addPotionEffect(new PotionEffect(MPPotions.INFECTED_CRYSTALLIZE, 240, 1));
                }
            }
            else
            {
                List<EntityLivingBase> list = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(this.posX - this.explosionRadius, this.posY - this.explosionRadius, this.posZ - this.explosionRadius, this.posX + this.explosionRadius, this.posY + this.explosionRadius, this.posZ + this.explosionRadius));

                for (EntityLivingBase living : list)
                {
                    living.addPotionEffect(new PotionEffect(MPPotions.INFECTED_CRYSTALLIZE, 120, 1));
                }
                this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, this.explosionRadius, flag);
            }
            this.setDead();
        }
    }

    @Override
    protected void dropFewItems(boolean drop, int fortune)
    {
        int j = 1 + this.rand.nextInt(4);
        int j2 = this.rand.nextInt(3);

        if (fortune > 0)
        {
            j += this.rand.nextInt(fortune + 1);
            j2 += this.rand.nextInt(fortune + 1);
        }
        for (int k = 0; k < j; ++k)
        {
            this.entityDropItem(new ItemStack(DionaItems.DIONA_ITEM, 1, 4), 0.0F);
        }
        for (int k = 0; k < j2; ++k)
        {
            this.entityDropItem(new ItemStack(Items.GUNPOWDER, 1), 0.0F);
        }
    }

    @Override
    public boolean isPotionApplicable(PotionEffect potion)
    {
        return potion.getPotion() == MPPotions.INFECTED_CRYSTALLIZE ? false : super.isPotionApplicable(potion);
    }

    @Override
    public boolean canBreath()
    {
        return true;
    }
}