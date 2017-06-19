package stevekung.mods.moreplanets.items.capsule;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
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
    public ItemStack onItemUseFinish(ItemStack itemStack, World world, EntityPlayer player)
    {
        if (!player.capabilities.isCreativeMode)
        {
            --itemStack.stackSize;
        }
        if (!world.isRemote)
        {
            NBTTagCompound nbt = itemStack.getTagCompound();
            world.playSoundAtEntity(player, "random.burp", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);

            if (nbt != null)
            {
                if (nbt.getBoolean("InfectedProtection"))
                {
                    player.removePotionEffect(MPPotions.INFECTED_SPORE_PROTECTION.id);
                    player.addPotionEffect(new PotionEffect(MPPotions.INFECTED_SPORE_PROTECTION.id, 36020, 0, true, true));
                }
                if (nbt.getBoolean("DarkEnergyProtection"))
                {
                    player.removePotionEffect(MPPotions.DARK_ENERGY_PROTECTION.id);
                    player.addPotionEffect(new PotionEffect(MPPotions.DARK_ENERGY_PROTECTION.id, 15020, 0, true, true));
                }
            }
            if (!player.capabilities.isCreativeMode && !player.inventory.addItemStackToInventory(new ItemStack(this)))
            {
                player.dropPlayerItemWithRandomChoice(new ItemStack(this), false);
            }
        }
        return itemStack;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack itemStack, int renderPass)
    {
        NBTTagCompound nbt = itemStack.getTagCompound();

        if (nbt != null && renderPass == 1)
        {
            return nbt.getInteger("Color");
        }
        return super.getColorFromItemStack(itemStack, renderPass);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player, List<String> list, boolean advanced)
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
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs creativeTabs, List list)
    {
        list.add(new ItemStack(item, 1, 0));

        if (ItemCapsule.init)
        {
            list.add(CapsuleType.getInfectedProtectionCapsule());
            list.add(CapsuleType.getDarkEnergyProtectionCapsule());
        }
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
        if (itemStack.hasTagCompound() && (player.canEat(true) || player.capabilities.isCreativeMode))
        {
            player.setItemInUse(itemStack, this.getMaxItemUseDuration(itemStack));
        }
        return itemStack;
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