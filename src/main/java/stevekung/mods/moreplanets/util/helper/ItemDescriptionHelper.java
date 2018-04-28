package stevekung.mods.moreplanets.util.helper;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.stevekunglib.utils.LangUtils;

public class ItemDescriptionHelper
{
    @SideOnly(Side.CLIENT)
    public static List<String> getDescription(String name)
    {
        return Minecraft.getMinecraft().fontRenderer.listFormattedStringToWidth(LangUtils.translate(name), 150);
    }
}