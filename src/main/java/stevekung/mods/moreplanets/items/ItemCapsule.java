package stevekung.mods.moreplanets.items;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.init.MPPotions;
import stevekung.mods.moreplanets.utils.items.ItemFoodMP;
import stevekung.mods.stevekunglib.utils.ColorUtils;

public class ItemCapsule extends ItemFoodMP
{
    private CapsuleType type;

    public ItemCapsule(String name, CapsuleType type)
    {
        super();
        this.setMaxStackSize(16);
        this.setUnlocalizedName(name);
        this.type = type;
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
                world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);

                if (this.type == CapsuleType.INFECTED_SPORE)
                {
                    player.removePotionEffect(MPPotions.INFECTED_SPORE_PROTECTION);
                    player.addPotionEffect(new PotionEffect(MPPotions.INFECTED_SPORE_PROTECTION, 36020, 0, true, true));
                }
                if (this.type == CapsuleType.DARK_ENERGY)
                {
                    player.removePotionEffect(MPPotions.DARK_ENERGY_PROTECTION);
                    player.addPotionEffect(new PotionEffect(MPPotions.DARK_ENERGY_PROTECTION, 15020, 0, true, true));
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
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
    {
        ItemStack itemStack = player.getHeldItem(hand);

        if (this.type != CapsuleType.EMPTY && (player.canEat(true) || player.capabilities.isCreativeMode))
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

    public static enum CapsuleType
    {
        EMPTY(-1),
        INFECTED_SPORE(ColorUtils.rgbToDecimal(232, 62, 19)),
        DARK_ENERGY(ColorUtils.rgbToDecimal(75, 75, 75));

        private int color;

        CapsuleType(int color)
        {
            this.color = color;
        }

        @Override
        public String toString()
        {
            return this.name().toLowerCase();
        }

        public int getColor()
        {
            return this.color;
        }
    }
}