package stevekung.mods.moreplanets.items;

import java.util.List;

import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.util.items.ItemSchematicVariantsMP;

public class ItemSpecialSchematic extends ItemSchematicVariantsMP
{
    public static int SCHEMATIC_INDEX = 0;

    public ItemSpecialSchematic(String name)
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
            list.add(TextFormatting.GRAY + GCCoreUtil.translate("desc.ion_cannon.name"));
        }
        else
        {
            list.add(TextFormatting.GRAY + GCCoreUtil.translate("desc.black_hole_storage.name"));
        }
    }

    @Override
    public String[] getItemVariantsName()
    {
        return new String[] { "ion_cannon", "black_hole_storage" };
    }

    @Override
    protected int getIndex(int damage)
    {
        return damage + ItemSpecialSchematic.SCHEMATIC_INDEX;
    }

    public static enum ItemType
    {
        ION_CANNON_SCHEMATIC,
        BLACK_HOLE_STORAGE_SCHEMATIC;
    }
}