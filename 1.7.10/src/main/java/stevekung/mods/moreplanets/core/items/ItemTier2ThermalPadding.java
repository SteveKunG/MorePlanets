package stevekung.mods.moreplanets.core.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import micdoodle8.mods.galacticraft.api.item.IItemThermal;
import micdoodle8.mods.galacticraft.planets.asteroids.items.ItemThermalPadding;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.init.MPItems;
import stevekung.mods.moreplanets.planets.kapteynb.blocks.KapteynBBlocks;
import stevekung.mods.moreplanets.planets.kapteynb.items.KapteynBItems;

public class ItemTier2ThermalPadding extends ItemMorePlanet implements IItemThermal
{
    public static String[] names = { "thermalHelmTier2", "thermalChestplateTier2", "thermalLeggingsTier2", "thermalBootsTier2", "thermalHelm0", "thermalChestplate0", "thermalLeggings0", "thermalBoots0" };
    protected IIcon[] icons = new IIcon[ItemTier2ThermalPadding.names.length];

    public ItemTier2ThermalPadding(String name)
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

        for (String name : ItemTier2ThermalPadding.names)
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
            list.add(StatCollector.translateToLocal("item.tier2.desc"));
            int count = 0;

            switch (itemStack.getItemDamage())
            {
            case 0:
                count = 16;
                break;
            case 1:
                count = 20;
                break;
            case 2:
                count = 18;
                break;
            case 3:
                count = 12;
                break;
            }
            list.add("Thermal Armor Upgrade Required : " + count);
            list.add("Ability : Immune to Infected Gas");
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
        return 3;
    }

    @Override
    public boolean isValidForSlot(ItemStack stack, int armorSlot)
    {
        return stack.getItemDamage() == armorSlot;
    }

    @Override
    public boolean onEntityItemUpdate(EntityItem item)
    {
        World world = item.worldObj;
        List<EntityItem> item1 = world.getEntitiesWithinAABB(EntityItem.class, AxisAlignedBB.getBoundingBox(item.posX, item.posY, item.posZ, item.posX + 1, item.posY + 1, item.posZ + 1));
        int meta = item.getEntityItem().getItemDamage();

        if (item.worldObj.getBlock((int)Math.floor(item.posX), (int)Math.floor(item.posY), (int)Math.floor(item.posZ)) == KapteynBBlocks.frozen_water)
        {
            if (meta == 0)
            {
                if (item1.get(0).getEntityItem().getItem() == KapteynBItems.kapteyn_b_item && item1.get(0).getEntityItem().getItemDamage() == 5 && item1.get(0).getEntityItem().stackSize >= 16)
                {
                    item.setEntityItemStack(new ItemStack(MPItems.tier_3_thermal_padding, 1, 0));
                    item1.get(0).setEntityItemStack(new ItemStack(KapteynBItems.kapteyn_b_item, item1.get(0).getEntityItem().stackSize - 16, 5));
                }
            }
            if (meta == 1)
            {
                if (item1.get(0).getEntityItem().getItem() == KapteynBItems.kapteyn_b_item && item1.get(0).getEntityItem().getItemDamage() == 5 && item1.get(0).getEntityItem().stackSize >= 20)
                {
                    item.setEntityItemStack(new ItemStack(MPItems.tier_3_thermal_padding, 1, 1));
                    item1.get(0).setEntityItemStack(new ItemStack(KapteynBItems.kapteyn_b_item, item1.get(0).getEntityItem().stackSize - 20, 5));
                }
            }
            if (meta == 2)
            {
                if (item1.get(0).getEntityItem().getItem() == KapteynBItems.kapteyn_b_item && item1.get(0).getEntityItem().getItemDamage() == 5 && item1.get(0).getEntityItem().stackSize >= 18)
                {
                    item.setEntityItemStack(new ItemStack(MPItems.tier_3_thermal_padding, 1, 2));
                    item1.get(0).setEntityItemStack(new ItemStack(KapteynBItems.kapteyn_b_item, item1.get(0).getEntityItem().stackSize - 18, 5));
                }
            }
            if (meta == 3)
            {
                if (item1.get(0).getEntityItem().getItem() == KapteynBItems.kapteyn_b_item && item1.get(0).getEntityItem().getItemDamage() == 5 && item1.get(0).getEntityItem().stackSize >= 12)
                {
                    item.setEntityItemStack(new ItemStack(MPItems.tier_3_thermal_padding, 1, 3));
                    item1.get(0).setEntityItemStack(new ItemStack(KapteynBItems.kapteyn_b_item, item1.get(0).getEntityItem().stackSize - 12, 5));
                }
            }
        }
        return false;
    }
}