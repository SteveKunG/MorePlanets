package stevekung.mods.moreplanets.module.planets.nibiru.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.util.items.ItemFoodVariantsMP;

public class ItemNibiruFruits extends ItemFoodVariantsMP
{
    public ItemNibiruFruits(String name)
    {
        super();
        this.setUnlocalizedName(name);
    }

    @Override
    public EnumRarity getRarity(ItemStack itemStack)
    {
        int meta = itemStack.getItemDamage();
        return meta == ItemType.INFECTED_GOLDEN_APPLE.ordinal() || meta == ItemType.GOLDEN_ALIEN_BERRY.ordinal() ? EnumRarity.RARE : meta == ItemType.ENCHANTED_INFECTED_GOLDEN_APPLE.ordinal() ? EnumRarity.EPIC : super.getRarity(itemStack);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack itemStack)
    {
        return itemStack.getItemDamage() == ItemType.ENCHANTED_INFECTED_GOLDEN_APPLE.ordinal();
    }

    @Override
    protected void onFoodEaten(ItemStack itemStack, World world, EntityPlayer player)
    {
        int meta = itemStack.getItemDamage();

        if (!world.isRemote)
        {
            if (meta == ItemType.INFECTED_GOLDEN_APPLE.ordinal())
            {
                player.addPotionEffect(new PotionEffect(Potion.absorption.id, 2400, 0));
                player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 120, 1));
            }
            else if (meta == ItemType.GOLDEN_ALIEN_BERRY.ordinal())
            {
                player.addPotionEffect(new PotionEffect(Potion.absorption.id, 3600, 0));
                player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 60, 1));
            }
            else if (meta == ItemType.ENCHANTED_INFECTED_GOLDEN_APPLE.ordinal())
            {
                player.addPotionEffect(new PotionEffect(Potion.absorption.id, 2400, 0));
                player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 600, 4));
                player.addPotionEffect(new PotionEffect(Potion.resistance.id, 6000, 0));
                player.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 6000, 0));
            }
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return itemStack.getItemDamage() == ItemType.ENCHANTED_INFECTED_GOLDEN_APPLE.ordinal() ? StatCollector.translateToLocal("item.nibiru_fruits.infected_golden_apple") : super.getUnlocalizedName(itemStack);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
        int meta = itemStack.getItemDamage();

        if (meta == ItemType.INFECTED_GOLDEN_APPLE.ordinal() || meta == ItemType.ENCHANTED_INFECTED_GOLDEN_APPLE.ordinal() || meta == ItemType.GOLDEN_ALIEN_BERRY.ordinal())
        {
            if (player.canEat(true))
            {
                player.setItemInUse(itemStack, this.getMaxItemUseDuration(itemStack));
            }
        }
        return super.onItemRightClick(itemStack, world, player);
    }

    @Override
    public int getMaxItemUseDuration(ItemStack itemStack)
    {
        return itemStack.getItemDamage() == ItemType.TERRABERRY.ordinal() ? 18 : 32;
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (itemStack.getItemDamage() == ItemType.TERRABERRY.ordinal())
        {
            if (side != EnumFacing.UP)
            {
                return false;
            }
            else if (!player.canPlayerEdit(pos.offset(side), side, itemStack))
            {
                return false;
            }
            else if (world.getBlockState(pos).getBlock() == NibiruBlocks.INFECTED_FARMLAND && world.isAirBlock(pos.up()))
            {
                world.setBlockState(pos.up(), NibiruBlocks.TERRABERRY_BLOCK.getDefaultState());
                --itemStack.stackSize;
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    @Override
    public int getHealAmount(ItemStack itemStack)
    {
        return ItemType.valuesCached()[itemStack.getItemDamage()].hunger;
    }

    @Override
    public float getSaturationModifier(ItemStack itemStack)
    {
        return ItemType.valuesCached()[itemStack.getItemDamage()].saturation;
    }

    @Override
    protected String[] getItemVariantsName()
    {
        return new String[] { "infected_apple", "infected_golden_apple", "enchanted_infected_golden_apple", "infected_melon", "alien_berry", "golden_alien_berry", "terraberry" };
    }

    public static enum ItemType
    {
        INFECTED_APPLE(4, 0.3F),
        INFECTED_GOLDEN_APPLE(4, 1.2F),
        ENCHANTED_INFECTED_GOLDEN_APPLE(4, 1.5F),
        INFECTED_MELON(3, 0.4F),
        ALIEN_BERRY(4, 1.0F),
        GOLDEN_ALIEN_BERRY(5, 1.25F),
        TERRABERRY(6, 1.5F);

        int hunger;
        float saturation;
        private static ItemType[] values = ItemType.values();

        ItemType(int hunger, float saturation)
        {
            this.hunger = hunger;
            this.saturation = saturation;
        }

        public static ItemType[] valuesCached()
        {
            return ItemType.values;
        }
    }
}