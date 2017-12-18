package stevekung.mods.moreplanets.core.entities;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import micdoodle8.mods.galacticraft.api.entity.IEntityBreathable;
import micdoodle8.mods.galacticraft.core.items.GCItems;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.asteroids.darkasteroids.blocks.DarkAsteroidsBlocks;
import stevekung.mods.moreplanets.asteroids.darkasteroids.dimension.WorldProviderDarkAsteroids;
import stevekung.mods.moreplanets.core.init.MPItems;

public class EntityEvolvedWitch extends EntityMob implements IRangedAttackMob, IEntityBreathable
{
    private static UUID field_110184_bp = UUID.fromString("8CD17E52-A79A-43D3-A529-90FDE04B181E");
    private static AttributeModifier field_110185_bq = new AttributeModifier(field_110184_bp, "Drinking speed penalty", -0.25D, 0).setSaved(false);
    private static Item[] witchDrops = new Item[] {Items.glowstone_dust, Items.sugar, Items.redstone, Items.spider_eye, Items.glass_bottle, Items.gunpowder, Items.stick, Items.stick};
    private int witchAttackTimer;

    public EntityEvolvedWitch(World world)
    {
        super(world);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIArrowAttack(this, 1.0D, 60, 10.0F));
        this.tasks.addTask(2, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(3, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
    }

    @Override
    public ItemStack getPickedResult(MovingObjectPosition target)
    {
        return new ItemStack(MPItems.spawn_egg_mp, 1, 1040);
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();
        this.getDataWatcher().addObject(21, Byte.valueOf((byte)0));
    }

    @Override
    protected String getLivingSound()
    {
        return "mpcore:mob.witch.idle";
    }

    @Override
    protected String getHurtSound()
    {
        return "mpcore:mob.witch.hurt";
    }

    @Override
    protected String getDeathSound()
    {
        return "mpcore:mob.witch.death";
    }

    public void setAggressive(boolean p_82197_1_)
    {
        this.getDataWatcher().updateObject(21, Byte.valueOf((byte)(p_82197_1_ ? 1 : 0)));
    }

    public boolean getAggressive()
    {
        return this.getDataWatcher().getWatchableObjectByte(21) == 1;
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(26.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
    }

    @Override
    public boolean isAIEnabled()
    {
        return true;
    }

    @Override
    public void onLivingUpdate()
    {
        if (!this.worldObj.isRemote)
        {
            if (this.getAggressive())
            {
                if (this.witchAttackTimer-- <= 0)
                {
                    this.setAggressive(false);
                    ItemStack itemstack = this.getHeldItem();
                    this.setCurrentItemOrArmor(0, (ItemStack)null);

                    if (itemstack != null && itemstack.getItem() == Items.potionitem)
                    {
                        List list = Items.potionitem.getEffects(itemstack);

                        if (list != null)
                        {
                            Iterator iterator = list.iterator();

                            while (iterator.hasNext())
                            {
                                PotionEffect potioneffect = (PotionEffect)iterator.next();
                                this.addPotionEffect(new PotionEffect(potioneffect));
                            }
                        }
                    }
                    this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).removeModifier(field_110185_bq);
                }
            }
            else
            {
                short short1 = -1;

                if (this.rand.nextFloat() < 0.15F && this.isInsideOfMaterial(Material.water) && !this.isPotionActive(Potion.waterBreathing))
                {
                    short1 = 8237;
                }
                else if (this.rand.nextFloat() < 0.15F && this.isBurning() && !this.isPotionActive(Potion.fireResistance))
                {
                    short1 = 16307;
                }
                else if (this.rand.nextFloat() < 0.05F && this.getHealth() < this.getMaxHealth())
                {
                    short1 = 16341;
                }
                else if (this.rand.nextFloat() < 0.25F && this.getAttackTarget() != null && !this.isPotionActive(Potion.moveSpeed) && this.getAttackTarget().getDistanceSqToEntity(this) > 121.0D)
                {
                    short1 = 16274;
                }
                else if (this.rand.nextFloat() < 0.25F && this.getAttackTarget() != null && !this.isPotionActive(Potion.moveSpeed) && this.getAttackTarget().getDistanceSqToEntity(this) > 121.0D)
                {
                    short1 = 16274;
                }

                if (short1 > -1)
                {
                    this.playSound("mpcore:mob.witch.drink", this.getSoundVolume(), this.getSoundPitch());
                    this.setCurrentItemOrArmor(0, new ItemStack(Items.potionitem, 1, short1));
                    this.witchAttackTimer = this.getHeldItem().getMaxItemUseDuration();
                    this.setAggressive(true);
                    IAttributeInstance iattributeinstance = this.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
                    iattributeinstance.removeModifier(field_110185_bq);
                    iattributeinstance.applyModifier(field_110185_bq);
                }
            }

            if (this.rand.nextFloat() < 7.5E-4F)
            {
                this.worldObj.setEntityState(this, (byte)15);
            }
        }
        super.onLivingUpdate();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void handleHealthUpdate(byte p_70103_1_)
    {
        if (p_70103_1_ == 15)
        {
            for (int i = 0; i < this.rand.nextInt(35) + 10; ++i)
            {
                this.worldObj.spawnParticle("witchMagic", this.posX + this.rand.nextGaussian() * 0.12999999523162842D, this.boundingBox.maxY + 0.5D + this.rand.nextGaussian() * 0.12999999523162842D, this.posZ + this.rand.nextGaussian() * 0.12999999523162842D, 0.0D, 0.0D, 0.0D);
            }
        }
        else
        {
            super.handleHealthUpdate(p_70103_1_);
        }
    }

    @Override
    protected float applyPotionDamageCalculations(DamageSource p_70672_1_, float p_70672_2_)
    {
        p_70672_2_ = super.applyPotionDamageCalculations(p_70672_1_, p_70672_2_);

        if (p_70672_1_.getEntity() == this)
        {
            p_70672_2_ = 0.0F;
        }
        if (p_70672_1_.isMagicDamage())
        {
            p_70672_2_ = (float)(p_70672_2_ * 0.15D);
        }
        return p_70672_2_;
    }

    @Override
    protected void dropFewItems(boolean p_70628_1_, int p_70628_2_)
    {
        int j = this.rand.nextInt(3) + 1;

        for (int k = 0; k < j; ++k)
        {
            int l = this.rand.nextInt(3);
            Item item = witchDrops[this.rand.nextInt(witchDrops.length)];

            if (p_70628_2_ > 0)
            {
                l += this.rand.nextInt(p_70628_2_ + 1);
            }

            for (int i1 = 0; i1 < l; ++i1)
            {
                this.dropItem(item, 1);
            }
        }
    }

    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase p_82196_1_, float p_82196_2_)
    {
        if (!this.getAggressive())
        {
            EntityPotion entitypotion = new EntityPotion(this.worldObj, this, 32732);
            entitypotion.rotationPitch -= -20.0F;
            double d0 = p_82196_1_.posX + p_82196_1_.motionX - this.posX;
            double d1 = p_82196_1_.posY + p_82196_1_.getEyeHeight() - 1.100000023841858D - this.posY;
            double d2 = p_82196_1_.posZ + p_82196_1_.motionZ - this.posZ;
            float f1 = MathHelper.sqrt_double(d0 * d0 + d2 * d2);

            if (f1 >= 8.0F && !p_82196_1_.isPotionActive(Potion.moveSlowdown))
            {
                entitypotion.setPotionDamage(32698);
            }
            else if (p_82196_1_.getHealth() >= 8.0F && !p_82196_1_.isPotionActive(Potion.poison))
            {
                entitypotion.setPotionDamage(32660);
            }
            else if (f1 <= 3.0F && !p_82196_1_.isPotionActive(Potion.weakness) && this.rand.nextFloat() < 0.25F)
            {
                entitypotion.setPotionDamage(32696);
            }
            this.playSound("mpcore:mob.witch.throw", this.getSoundVolume(), this.getSoundPitch());
            entitypotion.setThrowableHeading(d0, d1 + f1 * 0.2F, d2, 0.75F, 8.0F);
            this.worldObj.spawnEntityInWorld(entitypotion);
        }
    }

    @Override
    protected float getSoundVolume()
    {
        return 0.6F;
    }

    @Override
    public boolean canBreath()
    {
        return true;
    }

    @Override
    protected void dropRareDrop(int p_70600_1_)
    {
        switch (this.rand.nextInt(10))
        {
        case 0:
        case 1:
        case 9:
            //Dehydrated carrot
            this.entityDropItem(new ItemStack(GCItems.basicItem, 1, 16), 0.0F);
            break;
        case 2:
        case 3:
            this.entityDropItem(new ItemStack(this.worldObj.provider instanceof WorldProviderDarkAsteroids ? DarkAsteroidsBlocks.dark_asteroid_block : Blocks.glowstone, 1 + this.worldObj.rand.nextInt(4), 8), 0.0F);
            break;
        case 4:
        case 5:
            this.entityDropItem(new ItemStack(GCItems.basicItem, 1, 20), 0.0F);
            break;
        case 6:
            //Oxygen tank half empty or less
            this.entityDropItem(new ItemStack(GCItems.oxTankHeavy, 1, 901 + this.rand.nextInt(900)), 0.0F);
            break;
        case 7:
            this.dropItem(GCItems.oxMask, 1);
            break;
        case 8:
            this.dropItem(GCItems.oxygenVent, 1);
            break;
        }
    }
}