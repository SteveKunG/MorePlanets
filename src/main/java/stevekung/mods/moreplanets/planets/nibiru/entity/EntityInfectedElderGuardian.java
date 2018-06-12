package stevekung.mods.moreplanets.planets.nibiru.entity;

import javax.annotation.Nullable;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.init.MPLootTables;
import stevekung.mods.moreplanets.init.MPPotions;
import stevekung.mods.moreplanets.utils.EnumParticleTypesMP;

public class EntityInfectedElderGuardian extends EntityInfectedGuardian
{
    public EntityInfectedElderGuardian(World world)
    {
        super(world);
        this.setSize(this.width * 2.35F, this.height * 2.35F);
        this.enablePersistence();

        if (this.wander != null)
        {
            this.wander.setExecutionChance(400);
        }
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30000001192092896D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(8.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(80.0D);
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable()
    {
        return MPLootTables.INFECTED_ELDER_GUARDIAN;
    }

    @Override
    public int getAttackDuration()
    {
        return 60;
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return this.isInWater() ? SoundEvents.ENTITY_ELDER_GUARDIAN_AMBIENT : SoundEvents.ENTITY_ELDERGUARDIAN_AMBIENTLAND;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source)
    {
        return this.isInWater() ? SoundEvents.ENTITY_ELDER_GUARDIAN_HURT : SoundEvents.ENTITY_ELDER_GUARDIAN_HURT_LAND;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return this.isInWater() ? SoundEvents.ENTITY_ELDER_GUARDIAN_DEATH : SoundEvents.ENTITY_ELDER_GUARDIAN_DEATH_LAND;
    }

    @Override
    protected SoundEvent getFlopSound()
    {
        return SoundEvents.ENTITY_ELDER_GUARDIAN_FLOP;
    }

    @Override
    protected void updateAITasks()
    {
        if ((this.ticksExisted + this.getEntityId()) % 1200 == 0)
        {
            Potion potion = MobEffects.MINING_FATIGUE;

            for (EntityPlayerMP player : this.world.getPlayers(EntityPlayerMP.class, entity -> this.getDistanceSq(entity) < 2500.0D && entity.interactionManager.survivalOrAdventure()))
            {
                if (!player.isPotionActive(potion) || player.getActivePotionEffect(potion).getAmplifier() < 2 || player.getActivePotionEffect(potion).getDuration() < 1200)
                {
                    MorePlanetsMod.PROXY.spawnParticle(EnumParticleTypesMP.INFECTED_GUARDIAN_APPEARANCE, player.posX, player.posY, player.posZ);
                    player.world.playSound(player, player.getPosition(), SoundEvents.ENTITY_ELDER_GUARDIAN_CURSE, SoundCategory.HOSTILE, 1.0F, 1.0F);
                    player.addPotionEffect(new PotionEffect(potion, 6000, 2));

                    if (!player.capabilities.isCreativeMode && !player.isPotionActive(MPPotions.INFECTED_SPORE_PROTECTION))
                    {
                        player.addPotionEffect(new PotionEffect(MPPotions.INFECTED_SPORE, 80, 2));
                    }
                }
            }
        }
        if (!this.hasHome())
        {
            this.setHomePosAndDistance(new BlockPos(this), 16);
        }
    }

    @SideOnly(Side.CLIENT)
    public void setGhost()
    {
        this.clientSideSpikesAnimation = 1.0F;
        this.clientSideSpikesAnimationO = this.clientSideSpikesAnimation;
    }
}