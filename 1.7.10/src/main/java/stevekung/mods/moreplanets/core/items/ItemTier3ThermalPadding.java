package stevekung.mods.moreplanets.core.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import micdoodle8.mods.galacticraft.api.item.IItemThermal;
import micdoodle8.mods.galacticraft.planets.asteroids.items.ItemThermalPadding;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import stevekung.mods.moreplanets.core.MorePlanetsCore;

public class ItemTier3ThermalPadding extends ItemMorePlanet implements IItemThermal
{
    public static String[] names = { "thermalHelmTier3", "thermalChestplateTier3", "thermalLeggingsTier3", "thermalBootsTier3", "thermalHelm0", "thermalChestplate0", "thermalLeggings0", "thermalBoots0" };
    protected IIcon[] icons = new IIcon[ItemTier3ThermalPadding.names.length];

    public ItemTier3ThermalPadding(String name)
    {
        super();
        this.setHasSubtypes(true);
        this.setMaxStackSize(1);
        this.setUnlocalizedName(name);
    }

    @Override
    public CreativeTabs getCreativeTab()
    {
        return MorePlanetsCore.mpArmorTab;
    }

    @Override
    public IIcon getIconFromDamage(int meta)
    {
        if (this.icons.length > meta)
        {
            return this.icons[meta];
        }
        return super.getIconFromDamage(meta);
    }

    @Override
    public int getMetadata(int meta)
    {
        return meta;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamageForRenderPass(int damage, int pass)
    {
        if (pass == 1)
        {
            if (this.icons.length > damage + 4)
            {
                return this.icons[damage + 4];
            }
        }
        return this.getIconFromDamage(damage);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses()
    {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        int i = 0;

        for (String name : ItemTier3ThermalPadding.names)
        {
            this.icons[i++] = iconRegister.registerIcon("mpcore:" + name);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean advanced)
    {
        if (player.worldObj.isRemote)
        {
            list.add(StatCollector.translateToLocal("item.tier3.desc"));
            list.add("Ability : Immune to Infected Gas and Icy Poison");
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs creativeTabs, List list)
    {
        for (int i = 0; i < 4; i++)
        {
            list.add(new ItemStack(this, 1, i));
        }
    }

    @Override
    public String getItemStackDisplayName(ItemStack itemStack)
    {
        return StatCollector.translateToLocal("item." + ItemThermalPadding.names[itemStack.getItemDamage()] + ".name");
    }

    @Override
    public int getThermalStrength()
    {
        return 6;
    }

    @Override
    public boolean isValidForSlot(ItemStack stack, int armorSlot)
    {
        return stack.getItemDamage() == armorSlot;
    }
}