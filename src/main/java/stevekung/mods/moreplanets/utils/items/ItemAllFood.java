package stevekung.mods.moreplanets.utils.items;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.stevekunglib.utils.LangUtils;

public class ItemAllFood extends ItemFoodMP
{
    private final ItemType type;

    public ItemAllFood(String name, ItemType type)
    {
        super();
        this.setUnlocalizedName(name);
        this.type = type;
    }

    @Override
    protected void onFoodEaten(ItemStack itemStack, World world, EntityPlayer player)
    {
        if (!world.isRemote)
        {
            if (this.type == ItemType.INFECTED_GOLDEN_APPLE)
            {
                player.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 2400, 0));
                player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 120, 1));
            }
            else if (this.type == ItemType.GOLDEN_ALIEN_BERRY)
            {
                player.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 3600, 0));
                player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 60, 1));
            }
            else if (this.type == ItemType.ENCHANTED_INFECTED_GOLDEN_APPLE)
            {
                player.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 2400, 0));
                player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 600, 4));
                player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 6000, 0));
                player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 6000, 0));
            }
        }
    }

    @Override
    public EnumRarity getRarity(ItemStack itemStack)
    {
        return this.type == ItemType.INFECTED_GOLDEN_APPLE || this.type == ItemType.GOLDEN_ALIEN_BERRY ? EnumRarity.RARE : this.type == ItemType.ENCHANTED_INFECTED_GOLDEN_APPLE ? EnumRarity.EPIC : super.getRarity(itemStack);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack itemStack)
    {
        return this.type == ItemType.ENCHANTED_INFECTED_GOLDEN_APPLE;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack itemStack)
    {
        if (this.type == ItemType.CHEESE_MILK_CURD || this.type == ItemType.JELLY_BEANS)
        {
            return 8;
        }
        else if (this.type == ItemType.TERRABERRY)
        {
            return 18;
        }
        return 32;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return this.type == ItemType.ENCHANTED_INFECTED_GOLDEN_APPLE ? LangUtils.translate("item.infected_golden_apple") : super.getUnlocalizedName(itemStack);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
    {
        ItemStack itemStack = player.getHeldItem(hand);

        if (this.type == ItemType.INFECTED_GOLDEN_APPLE || this.type == ItemType.ENCHANTED_INFECTED_GOLDEN_APPLE || this.type == ItemType.GOLDEN_ALIEN_BERRY)
        {
            if (player.canEat(true))
            {
                player.setActiveHand(hand);
                return new ActionResult<>(EnumActionResult.SUCCESS, itemStack);
            }
            else
            {
                return new ActionResult<>(EnumActionResult.FAIL, itemStack);
            }
        }
        return super.onItemRightClick(world, player, hand);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack itemStack = player.getHeldItem(hand);
        IBlockState state = world.getBlockState(pos);

        if (this.type == ItemType.TERRABERRY)
        {
            if (facing == EnumFacing.UP && player.canPlayerEdit(pos.offset(facing), facing, itemStack) && state.getBlock() == MPBlocks.INFECTED_FARMLAND && world.isAirBlock(pos.up()))
            {
                world.setBlockState(pos.up(), MPBlocks.TERRABERRY.getDefaultState(), 11);
                itemStack.shrink(1);
                return EnumActionResult.SUCCESS;
            }
            else
            {
                return EnumActionResult.FAIL;
            }
        }
        return EnumActionResult.PASS;
    }

    @Override
    public int getHealAmount(ItemStack itemStack)
    {
        return this.type.getHunger();
    }

    @Override
    public float getSaturationModifier(ItemStack itemStack)
    {
        return this.type.getSaturation();
    }

    public static enum ItemType
    {
        CHEESE_MILK_CURD(3, 0.35F),
        RAW_CHEESE_BEEF(3, 0.2F),
        COOKED_CHEESE_BEEF(8, 0.8F),
        CHEESE_SPORE_BERRY(4, 0.4F),
        INFECTED_APPLE(4, 0.3F),
        INFECTED_GOLDEN_APPLE(4, 1.2F),
        ENCHANTED_INFECTED_GOLDEN_APPLE(4, 1.5F),
        INFECTED_MELON(3, 0.4F),
        ALIEN_BERRY(4, 1.0F),
        GOLDEN_ALIEN_BERRY(5, 1.25F),
        TERRABERRY(6, 1.5F),
        RAW_SHLIME_MEAT(3, 0.3F),
        COOKED_SHLIME_MEAT(8, 0.8F),
        STRAWBERRY(4, 0.25F),
        GIANT_BLUEBERRY(6, 0.45F),
        CHOCOLATE_BAR(5, 0.6F),
        JELLY_BEANS(1, 0.05F),
        MARSHMALLOW(3, 0.15F),
        COOKED_MARSHMALLOW(6, 0.35F),
        RED_CANDY_CANE_STICK(4, 0.5F),
        GREEN_CANDY_CANE_STICK(4, 0.5F),
        BLUE_CANDY_CANE_STICK(4, 0.5F),
        ORANGE_CANDY_CANE_STICK(4, 0.5F),
        PINK_CANDY_CANE_STICK(4, 0.5F),
        YELLOW_CANDY_CANE_STICK(4, 0.5F),
        PURPLE_CANDY_CANE_STICK(4, 0.5F),
        RAINBOW_CANDY_CANE_STICK(4, 0.5F),
        GRAPE_JELLY(4, 0.35F),
        RASPBERRY_JELLY(4, 0.35F),
        STRAWBERRY_JELLY(4, 0.35F),
        BERRY_JELLY(4, 0.35F),
        LIME_JELLY(4, 0.35F),
        ORANGE_JELLY(4, 0.35F),
        GREEN_JELLY(4, 0.35F),
        LEMON_JELLY(4, 0.35F);

        private int hunger;
        private float saturation;

        ItemType(int hunger, float saturation)
        {
            this.hunger = hunger;
            this.saturation = saturation;
        }

        public int getHunger()
        {
            return this.hunger;
        }

        public float getSaturation()
        {
            return this.saturation;
        }
    }
}