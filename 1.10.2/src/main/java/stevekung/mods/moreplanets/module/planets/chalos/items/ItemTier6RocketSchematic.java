package stevekung.mods.moreplanets.module.planets.chalos.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.util.items.ItemSchematicMP;

public class ItemTier6RocketSchematic extends ItemSchematicMP
{
    public static int SCHEMATIC_INDEX = 0;

    public ItemTier6RocketSchematic(String name)
    {
        super();
        this.setUnlocalizedName(name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean advanced)
    {
        list.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("desc.tier_6.name"));
    }

    @Override
    public String getName()
    {
        return "tier_6_rocket_schematic";
    }

    @Override
    protected int getIndex(int damage)
    {
        return damage + ItemTier6RocketSchematic.SCHEMATIC_INDEX;
    }
}