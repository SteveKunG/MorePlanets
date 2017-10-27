package stevekung.mods.moreplanets.util.helper;

public class ColorHelper
{
    public static int rgbToDecimal(int r, int g, int b)
    {
        return b + 256 * g + 65536 * r;
    }

    public static float[] rgbToFloatArray(int r, int g, int b)
    {
        return new float[] { r / 255.0F, g / 255.0F, b / 255.0F };
    }
}