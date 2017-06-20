package stevekung.mods.moreplanets.items;

import micdoodle8.mods.galacticraft.planets.mars.items.MarsItems;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.module.planets.diona.entity.projectile.EntityInfectedCrystallizeArrow;
import stevekung.mods.moreplanets.module.planets.diona.items.DionaItems;
import stevekung.mods.moreplanets.module.planets.nibiru.entity.projectile.EntityInfectedArrow;
import stevekung.mods.moreplanets.module.planets.nibiru.items.NibiruItems;
import stevekung.mods.moreplanets.util.entity.EntityArrowMP;
import stevekung.mods.moreplanets.util.helper.ClientRegisterHelper;
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
    }

//    @Override
//    @SideOnly(Side.CLIENT)
//    public ModelResourceLocation getModel(ItemStack itemStack, EntityPlayer player, int useRemaining)
//    {
//        if (itemStack != null && itemStack.getItem() == this && player.getItemInUse() != null)
//        {
//            int i = itemStack.getMaxItemUseDuration() - player.getItemInUseCount();
//
//            if (i >= 18)
//            {
//                return ClientRegisterHelper.getModelResourceLocation("moreplanets:space_bow_pulling_2");
//            }
//            if (i > 13)
//            {
//                return ClientRegisterHelper.getModelResourceLocation("moreplanets:space_bow_pulling_1");
//            }
//            if (i > 0)
//            {
//                return ClientRegisterHelper.getModelResourceLocation("moreplanets:space_bow_pulling_0");
//            }
//        }
//        return null;
//    }

    @Override
    public void onPlayerStoppedUsing(ItemStack itemStack, World world, EntityPlayer player, int timeLeft)
    {
        int useDuration = this.getMaxItemUseDuration(itemStack) - timeLeft;
        ArrowLooseEvent event = new ArrowLooseEvent(player, itemStack, useDuration);
        int power = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, itemStack);
        int punch = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, itemStack);
        ItemStack arrowStack = this.findAmmo(player);
        boolean flag = player.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, itemStack) > 0;
        EntityArrowMP arrow = null;
        MinecraftForge.EVENT_BUS.post(event);

        if (event.isCanceled())
        {
            return;
        }

        useDuration = event.charge;
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
                arrowStack = new ItemStack(DionaItems.INFECTED_CRYSTALLIZE_ARROW);
            }
            if (arrowStack.getItem() == DionaItems.INFECTED_CRYSTALLIZE_ARROW)
            {
                arrow = new EntityInfectedCrystallizeArrow(world, player, duration * 2.0F);

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
                if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, itemStack) > 0)
                {
                    arrow.setFire(100);
                }

                itemStack.damageItem(1, player);
                world.playSoundAtEntity(player, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + duration * 0.5F);

                if (flag)
                {
                    arrow.canBePickedUp = 2;
                }
                else
                {
                    player.inventory.consumeInventoryItem(DionaItems.INFECTED_CRYSTALLIZE_ARROW);
                }
                if (!world.isRemote)
                {
                    world.spawnEntityInWorld(arrow);
                }
            }
            else if (arrowStack.getItem() == NibiruItems.INFECTED_ARROW)
            {
                arrow = new EntityInfectedArrow(world, player, duration * 2.0F);

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
                if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, itemStack) > 0)
                {
                    arrow.setFire(100);
                }

                itemStack.damageItem(1, player);
                world.playSoundAtEntity(player, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + duration * 0.5F);

                if (flag)
                {
                    arrow.canBePickedUp = 2;
                }
                else
                {
                    player.inventory.consumeInventoryItem(NibiruItems.INFECTED_ARROW);
                }
                if (!world.isRemote)
                {
                    world.spawnEntityInWorld(arrow);
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
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
        ArrowNockEvent event = new ArrowNockEvent(player, itemStack);
        MinecraftForge.EVENT_BUS.post(event);

        if (event.isCanceled())
        {
            return event.result;
        }
        if (player.capabilities.isCreativeMode || player.inventory.hasItem(DionaItems.INFECTED_CRYSTALLIZE_ARROW) || player.inventory.hasItem(NibiruItems.INFECTED_ARROW))
        {
            player.setItemInUse(itemStack, this.getMaxItemUseDuration(itemStack));
        }
        return itemStack;
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

    private ItemStack findAmmo(EntityPlayer player)
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

    protected boolean isArrow(ItemStack itemStack)
    {
        return itemStack != null && (itemStack.getItem() == DionaItems.INFECTED_CRYSTALLIZE_ARROW || itemStack.getItem() == NibiruItems.INFECTED_ARROW);
    }
}