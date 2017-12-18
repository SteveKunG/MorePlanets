package stevekung.mods.moreplanets.util.helper;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemDescriptionHelper
{
    @SideOnly(Side.CLIENT)
    public static List<String> getDescription(String name)
    {
        return Minecraft.getMinecraft().fontRendererObj.listFormattedStringToWidth(StatCollector.translateToLocal(name), 150);
    }
}