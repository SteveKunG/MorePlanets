package stevekung.mods.moreplanets.module.planets.diona.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.util.items.ItemSchematicVariantsMP;

public class ItemTier5RocketSchematic extends ItemSchematicVariantsMP
{
    public static int SCHEMATIC_INDEX = 0;

    public ItemTier5RocketSchematic(String name)
    {
        super();
        this.setUnlocalizedName(name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean advanced)
    {
        if (itemStack.getItemDamage() == 0)
        {
            list.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("desc.tier_4.name"));
        }
        else
        {
            list.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("desc.tier_5.name"));
        }
    }

    @Override
    public String[] getItemVariantsName()
    {
        return new String[] { "tier_4_rocket_schematic", "tier_5_rocket_schematic" };
    }

    @Override
    protected int getIndex(int damage)
    {
        return damage + ItemTier5RocketSchematic.SCHEMATIC_INDEX;
    }

    public static enum ItemType
    {
        TIER_4_ROCKET_SCHEMATIC,
        TIER_5_ROCKET_SCHEMATIC;
    }
}