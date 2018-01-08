package stevekung.mods.moreplanets.items.capsule;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.init.MPPotions;
import stevekung.mods.moreplanets.util.items.ItemFoodMP;

public class ItemCapsule extends ItemFoodMP
{
    public static boolean init;

    public ItemCapsule(String name)
    {
        super();
        this.setMaxStackSize(16);
        this.setUnlocalizedName(name);
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack itemStack, World world, EntityLivingBase living)
    {
        if (living instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) living;

            if (!player.capabilities.isCreativeMode)
            {
                itemStack.shrink(1);
            }
            if (!world.isRemote)
            {
                NBTTagCompound nbt = itemStack.getTagCompound();
                world.playSound((EntityPlayer)null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);

                if (nbt != null)
                {
                    if (nbt.getBoolean("InfectedProtection"))
                    {
                        player.removePotionEffect(MPPotions.INFECTED_SPORE_PROTECTION);
                        player.addPotionEffect(new PotionEffect(MPPotions.INFECTED_SPORE_PROTECTION, 36020, 0, true, true));
                    }
                    if (nbt.getBoolean("DarkEnergyProtection"))
                    {
                        player.removePotionEffect(MPPotions.DARK_ENERGY_PROTECTION);
                        player.addPotionEffect(new PotionEffect(MPPotions.DARK_ENERGY_PROTECTION, 15020, 0, true, true));
                    }
                }
                if (!player.capabilities.isCreativeMode && !player.inventory.addItemStackToInventory(new ItemStack(this)))
                {
                    player.dropItem(new ItemStack(this), false);
                }
            }
        }
        return itemStack;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, @Nullable World world, List<String> list, ITooltipFlag flag)
    {
        if (itemStack.hasTagCompound())
        {
            NBTTagCompound nbt = itemStack.getTagCompound();

            if (nbt.getBoolean("InfectedProtection"))
            {
                list.add("Infected Protection");
            }
            if (nbt.getBoolean("DarkEnergyProtection"))
            {
                list.add("Dark Energy Protection");
            }
        }
    }

    @Override
    public void getSubItems(CreativeTabs creativeTabs, NonNullList<ItemStack> list)
    {
        list.add(new ItemStack(this, 1, 0));

        if (ItemCapsule.init)
        {
            list.add(CapsuleType.getInfectedProtectionCapsule());
            list.add(CapsuleType.getDarkEnergyProtectionCapsule());
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
    {
        ItemStack itemStack = player.getHeldItem(hand);

        if (itemStack.hasTagCompound() && (player.canEat(true) || player.capabilities.isCreativeMode))
        {
            player.setActiveHand(hand);
            return new ActionResult<>(EnumActionResult.SUCCESS, itemStack);
        }
        else
        {
            return new ActionResult<>(EnumActionResult.FAIL, itemStack);
        }
    }

    @Override
    public int getHealAmount(ItemStack itemStack)
    {
        return 0;
    }

    @Override
    public float getSaturationModifier(ItemStack itemStack)
    {
        return 0.0F;
    }

    @Override
    public String getName()
    {
        return "capsule";
    }
}