package stevekung.mods.moreplanets.util.helper;

public class ColorHelper
{
    public static int rgbToDecimal(int r, int g, int b)
    {
        return b + 256 * g + 65536 * r;
    }
}