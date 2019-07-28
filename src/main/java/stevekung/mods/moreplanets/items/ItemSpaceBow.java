package stevekung.mods.moreplanets.items;

import micdoodle8.mods.galacticraft.core.proxy.ClientProxyCore;
import micdoodle8.mods.galacticraft.planets.mars.items.MarsItems;
import net.minecraft.creativetab.CreativeTabs;
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
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.utils.client.renderer.IItemModelRender;
import stevekung.mods.moreplanets.utils.itemblocks.IItemRarity;
import stevekung.mods.moreplanets.utils.items.EnumSortCategoryItem;
import stevekung.mods.moreplanets.utils.items.ISortableItem;
import stevekung.mods.stevekunglib.utils.ColorUtils;

public class ItemSpaceBow extends ItemBow implements ISortableItem, IItemModelRender, IItemRarity
{
    private final String name;

    public ItemSpaceBow(String name)
    {
        this.name = name;
        this.setMaxStackSize(1);
        this.setMaxDamage(511);
        this.setUnlocalizedName(name);
        this.addPropertyOverride(new ResourceLocation("pull"), (itemStack, world, living) ->
        {
            if (living == null)
            {
                return 0.0F;
            }
            else
            {
                return living.getActiveItemStack().getItem() == MPItems.SPACE_BOW ? (itemStack.getMaxItemUseDuration() - living.getItemInUseCount()) / 20.0F : 0.0F;
            }
        });
        this.addPropertyOverride(new ResourceLocation("pulling"), (itemStack, world, living) -> living != null && living.isHandActive() && living.getActiveItemStack() == itemStack ? 1.0F : 0.0F);
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
            if (flag || !arrowStack.isEmpty())
            {
                if (arrowStack.isEmpty())
                {
                    arrowStack = new ItemStack(Items.ARROW);
                }
                if (arrowStack.getItem() instanceof ItemArrow)
                {
                    ItemArrow itemArrow = (ItemArrow) arrowStack.getItem();
                    EntityArrow vanillaArrow = itemArrow.createArrow(world, arrowStack, player);
                    ItemSpaceBow.spawnArrow(itemStack, arrowStack, world, player, vanillaArrow, itemArrow, power, punch, duration, flag);
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
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
    {
        boolean flag = !this.findAmmo(player).isEmpty();
        ItemStack itemStack = player.getHeldItem(hand);
        ActionResult<ItemStack> ret = ForgeEventFactory.onArrowNock(itemStack, world, player, hand, flag);

        if (ret != null)
        {
            return ret;
        }
        if (!player.capabilities.isCreativeMode && !flag)
        {
            return !flag ? new ActionResult<>(EnumActionResult.FAIL, itemStack) : new ActionResult<>(EnumActionResult.PASS, itemStack);
        }
        else
        {
            player.setActiveHand(hand);
            return new ActionResult<>(EnumActionResult.SUCCESS, itemStack);
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
    public EnumSortCategoryItem getItemCategory()
    {
        return EnumSortCategoryItem.BOW;
    }

    @Override
    public ColorUtils.RGB getRarity()
    {
        return ColorUtils.stringToRGB(IItemRarity.SPECIAL);
    }

    @Override
    public String getName()
    {
        return this.name;
    }

    @Override
    protected boolean isArrow(ItemStack itemStack)
    {
        return super.isArrow(itemStack) || itemStack.getItem() == MPItems.INFECTED_CRYSTALLIZED_ARROW || itemStack.getItem() == MPItems.INFECTED_ARROW || itemStack.getItem() == MPItems.ANTI_GRAVITY_ARROW;
    }

    @Override
    public CreativeTabs getCreativeTab()
    {
        return MorePlanetsMod.ITEM_TAB;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack itemStack)
    {
        return ClientProxyCore.galacticraftItem;
    }

    @Override
    public String getItemStackDisplayName(ItemStack itemStack)
    {
        return this instanceof IItemRarity && ((IItemRarity)this).getRarity() != null ? ((IItemRarity)this).getRarity().toColoredFont() + super.getItemStackDisplayName(itemStack) : super.getItemStackDisplayName(itemStack);
    }

    private static void spawnArrow(ItemStack itemStack, ItemStack arrowStack, World world, EntityPlayer player, EntityArrow arrow, Item arrowItem, int power, int punch, float duration, boolean flag)
    {
        arrow.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, duration * 3.0F, 1.0F);

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
        world.playSound(null, player.getPosition(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.NEUTRAL, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + duration * 0.5F);

        if (flag)
        {
            arrow.pickupStatus = EntityArrow.PickupStatus.CREATIVE_ONLY;
        }
        else
        {
            if (arrowStack.getItem() == arrowItem)
            {
                arrowStack.shrink(1);

                if (arrowStack.isEmpty())
                {
                    player.inventory.deleteStack(arrowStack);
                }
            }
        }
        if (!world.isRemote)
        {
            world.spawnEntity(arrow);
        }
    }
}