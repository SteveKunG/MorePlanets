package stevekung.mods.stevekunglib.utils;

import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.BiomeManager;

public class CachedEnum
{
    public static final EnumHand[] handValues = EnumHand.values();
    public static final EnumDyeColor[] dyeColorValues = EnumDyeColor.values();
    public static final BiomeManager.BiomeType[] biomeValues = BiomeManager.BiomeType.values();
    public static final Axis[] axisValues = Axis.values();
    public static final TextFormatting[] textFormatValues = TextFormatting.values();
    public static final EnumAction[] actionValues = EnumAction.values();
    public static final EnumMCVersion[] mcValues = EnumMCVersion.values();
}