package stevekung.mods.moreplanets.items;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.utils.items.ItemSchematicMP;
import stevekung.mods.stevekunglib.utils.LangUtils;

public class ItemIonCannonSchematic extends ItemSchematicMP
{
    public static int SCHEMATIC_INDEX = 0;

    public ItemIonCannonSchematic(String name)
    {
        super();
        this.setUnlocalizedName(name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, @Nullable World world, List<String> list, ITooltipFlag flag)
    {
        list.add(TextFormatting.GRAY + LangUtils.translate("desc.ion_cannon.name"));
    }

    @Override
    protected int getIndex(int damage)
    {
        return damage + ItemIonCannonSchematic.SCHEMATIC_INDEX;
    }
}