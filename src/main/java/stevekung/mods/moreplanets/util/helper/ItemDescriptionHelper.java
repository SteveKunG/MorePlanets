package stevekung.mods.moreplanets.util.helper;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemDescriptionHelper
{
    @SideOnly(Side.CLIENT)
    public static List<String> getDescription(String name)
    {
        return Minecraft.getMinecraft().fontRenderer.listFormattedStringToWidth(I18n.format(name), 150);
    }
}