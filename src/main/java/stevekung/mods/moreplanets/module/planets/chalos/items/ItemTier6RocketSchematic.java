package stevekung.mods.moreplanets.module.planets.chalos.items;

import java.util.List;

import javax.annotation.Nullable;

import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
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
    public void addInformation(ItemStack itemStack, @Nullable World world, List<String> list, ITooltipFlag flag)
    {
        list.add(TextFormatting.GRAY + GCCoreUtil.translate("desc.tier_6.name"));
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