package stevekung.mods.moreplanets.items;

import javax.annotation.Nullable;

import micdoodle8.mods.galacticraft.planets.mars.items.MarsItems;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.module.planets.diona.entity.projectile.EntityAntiGravityArrow;
import stevekung.mods.moreplanets.module.planets.diona.entity.projectile.EntityInfectedCrystallizeArrow;
import stevekung.mods.moreplanets.module.planets.diona.items.DionaItems;
import stevekung.mods.moreplanets.module.planets.nibiru.entity.projectile.EntityInfectedArrow;
import stevekung.mods.moreplanets.module.planets.nibiru.items.NibiruItems;
import stevekung.mods.moreplanets.util.entity.EntityArrowMP;
import stevekung.mods.moreplanets.util.items.EnumSortCategoryItem;
import stevekung.mods.moreplanets.util.items.ItemBaseMP;

public class ItemSpaceBow extends ItemBaseMP
{
    public ItemSpaceBow(String name)
    {
        super();
        this.setMaxStackSize(1);
        this.setMaxDamage(511);
        this.setUnlocalizedName(name);

        this.addPropertyOverride(new ResourceLocation("pull"), new IItemPropertyGetter()
        {
            @Override
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack itemStack, @Nullable World world, @Nullable EntityLivingBase entity)
            {
                if (entity == null)
                {
                    return 0.0F;
                }
                else
                {
                    ItemStack bow = entity.getActiveItemStack();
                    return bow != null && bow.getItem() == MPItems.SPACE_BOW ? (itemStack.getMaxItemUseDuration() - entity.getItemInUseCount()) / 20.0F : 0.0F;
                }
            }
        });
        this.addPropertyOverride(new ResourceLocation("pulling"), new IItemPropertyGetter()
        {
            @Override
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack itemStack, @Nullable World world, @Nullable EntityLivingBase entity)
            {
                return entity != null && entity.isHandActive() && entity.getActiveItemStack() == itemStack ? 1.0F : 0.0F;
            }
        });
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack itemStack, World world, EntityLivingBase living, int timeLeft)
    {
        if (living instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer)living;
            int useDuration = this.getMaxItemUseDuration(itemStack) - timeLeft;
            int power = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, itemStack);
            int punch = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, itemStack);
            ItemStack arrowStack = this.findAmmo(player);
            ArrowLooseEvent event = new ArrowLooseEvent(player, itemStack, world, useDuration, arrowStack != null);
            boolean flag = player.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, itemStack) > 0;
            EntityArrowMP arrow = null;
            MinecraftForge.EVENT_BUS.post(event);

            if (event.isCanceled())
            {
                return;
            }

            useDuration = event.getCharge();
            float duration = useDuration / 20.0F;
            duration = (duration * duration + duration * 2.0F) / 3.0F;

            if (duration < 0.1D)
            {
                return;
            }
            if (duration > 1.0F)
            {
                duration = 1.0F;
            }
            if (flag || arrowStack != null)
            {
                if (arrowStack == null)
                {
                    arrowStack = new ItemStack(Items.ARROW);
                }
                if (arrowStack.getItem() instanceof ItemArrow)
                {
                    ItemArrow itemarrow = (ItemArrow) arrowStack.getItem();
                    EntityArrow entityarrow = itemarrow.createArrow(world, arrowStack, player);
                    entityarrow.setAim(player, player.rotationPitch, player.rotationYaw, 0.0F, duration * 3.0F, 1.0F);

                    if (duration == 1.0F)
                    {
                        entityarrow.setIsCritical(true);
                    }
                    if (power > 0)
                    {
                        entityarrow.setDamage(entityarrow.getDamage() + power * 0.5D + 0.5D);
                    }
                    if (punch > 0)
                    {
                        entityarrow.setKnockbackStrength(punch);
                    }
                    if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, itemStack) > 0)
                    {
                        entityarrow.setFire(100);
                    }

                    itemStack.damageItem(1, player);
                    world.playSound((EntityPlayer)null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.NEUTRAL, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + duration * 0.5F);

                    if (flag)
                    {
                        entityarrow.pickupStatus = EntityArrow.PickupStatus.CREATIVE_ONLY;
                    }
                    else
                    {
                        arrowStack.stackSize--;

                        if (arrowStack.stackSize == 0)
                        {
                            player.inventory.deleteStack(arrowStack);
                        }
                    }
                    if (!world.isRemote)
                    {
                        world.spawnEntityInWorld(entityarrow);
                    }
                }
                else if (arrowStack.getItem() == DionaItems.INFECTED_CRYSTALLIZE_ARROW)
                {
                    arrow = new EntityInfectedCrystallizeArrow(world, player);
                    ItemSpaceBow.spawnArrow(itemStack, arrowStack, world, player, arrow, DionaItems.INFECTED_CRYSTALLIZE_ARROW, power, punch, duration, flag);
                }
                else if (arrowStack.getItem() == NibiruItems.INFECTED_ARROW)
                {
                    arrow = new EntityInfectedArrow(world, player);
                    ItemSpaceBow.spawnArrow(itemStack, arrowStack, world, player, arrow, NibiruItems.INFECTED_ARROW, power, punch, duration, flag);
                }
                else if (arrowStack.getItem() == DionaItems.ANTI_GRAVITY_ARROW)
                {
                    arrow = new EntityAntiGravityArrow(world, player);
                    ItemSpaceBow.spawnArrow(itemStack, arrowStack, world, player, arrow, DionaItems.ANTI_GRAVITY_ARROW, power, punch, duration, flag);
                }
            }
        }
    }

    @Override
    public int getMaxItemUseDuration(ItemStack itemStack)
    {
        return 72000;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack itemStack)
    {
        return EnumAction.BOW;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStack, World world, EntityPlayer player, EnumHand hand)
    {
        boolean flag = this.findAmmo(player) != null;
        ActionResult<ItemStack> ret = ForgeEventFactory.onArrowNock(itemStack, world, player, hand, flag);

        if (ret != null)
        {
            return ret;
        }
        if (!player.capabilities.isCreativeMode && !flag)
        {
            return !flag ? new ActionResult(EnumActionResult.FAIL, itemStack) : new ActionResult(EnumActionResult.PASS, itemStack);
        }
        else
        {
            player.setActiveHand(hand);
            return new ActionResult(EnumActionResult.SUCCESS, itemStack);
        }
    }

    @Override
    public int getItemEnchantability()
    {
        return 4;
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
    {
        return repair.getItem() == MarsItems.marsItemBasic && repair.getItemDamage() == 5;
    }

    @Override
    public EnumSortCategoryItem getItemCategory(int meta)
    {
        return EnumSortCategoryItem.BOW;
    }

    @Override
    public String getName()
    {
        return "space_bow";
    }

    private ItemStack findAmmo(EntityPlayer player)
    {
        if (this.isArrow(player.getHeldItem(EnumHand.OFF_HAND)))
        {
            return player.getHeldItem(EnumHand.OFF_HAND);
        }
        else if (this.isArrow(player.getHeldItem(EnumHand.MAIN_HAND)))
        {
            return player.getHeldItem(EnumHand.MAIN_HAND);
        }
        else
        {
            for (int i = 0; i < player.inventory.getSizeInventory(); ++i)
            {
                ItemStack itemStack = player.inventory.getStackInSlot(i);

                if (this.isArrow(itemStack))
                {
                    return itemStack;
                }
            }
            return null;
        }
    }

    protected boolean isArrow(ItemStack itemStack)
    {
        return itemStack != null && (itemStack.getItem() instanceof ItemArrow || itemStack.getItem() == DionaItems.INFECTED_CRYSTALLIZE_ARROW || itemStack.getItem() == NibiruItems.INFECTED_ARROW || itemStack.getItem() == DionaItems.ANTI_GRAVITY_ARROW);
    }

    private static void spawnArrow(ItemStack itemStack, ItemStack arrowStack, World world, EntityPlayer player, EntityArrowMP arrow, Item arrowItem, int power, int punch, float duration, boolean flag)
    {
        arrow.setAim(player, player.rotationPitch, player.rotationYaw, 0.0F, duration * 3.0F, 1.0F);

        if (duration == 1.0F)
        {
            arrow.setIsCritical(true);
        }
        if (power > 0)
        {
            arrow.setDamage(arrow.getDamage() + power * 0.5D + 0.5D);
        }
        if (punch > 0)
        {
            arrow.setKnockbackStrength(punch);
        }
        if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, itemStack) > 0)
        {
            arrow.setFire(100);
        }

        itemStack.damageItem(1, player);
        world.playSound((EntityPlayer)null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.NEUTRAL, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + duration * 0.5F);

        if (flag)
        {
            arrow.pickupStatus = EntityArrow.PickupStatus.CREATIVE_ONLY;
        }
        else
        {
            if (arrowStack.getItem() == arrowItem)
            {
                arrowStack.stackSize--;

                if (arrowStack.stackSize == 0)
                {
                    player.inventory.deleteStack(arrowStack);
                }
            }
        }
        if (!world.isRemote)
        {
            world.spawnEntityInWorld(arrow);
        }
    }
}