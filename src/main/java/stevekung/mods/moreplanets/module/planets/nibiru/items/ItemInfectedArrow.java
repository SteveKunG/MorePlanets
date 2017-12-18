package stevekung.mods.moreplanets.module.planets.nibiru.items;

import java.util.List;

import org.lwjgl.input.Keyboard;

import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.util.helper.ItemDescriptionHelper;
import stevekung.mods.moreplanets.util.items.EnumSortCategoryItem;
import stevekung.mods.moreplanets.util.items.ItemBaseMP;

public class ItemInfectedArrow extends ItemBaseMP
{
    public ItemInfectedArrow(String name)
    {
        this.setUnlocalizedName(name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean advanced)
    {
        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
        {
            list.addAll(ItemDescriptionHelper.getDescription(this.getUnlocalizedName() + ".description"));
        }
        else
        {
            list.add(GCCoreUtil.translateWithFormat("item_desc.shift.name", GameSettings.getKeyDisplayString(Minecraft.getMinecraft().gameSettings.keyBindSneak.getKeyCode())));
        }
    }

    @Override
    public EnumSortCategoryItem getItemCategory(int meta)
    {
        return EnumSortCategoryItem.ARROW;
    }

    @Override
    public String getName()
    {
        return "infected_arrow";
    }
}