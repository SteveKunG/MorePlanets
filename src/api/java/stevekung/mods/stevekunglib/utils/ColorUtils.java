package stevekung.mods.stevekunglib.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.stevekunglib.client.event.ClientEventHandler;
import stevekung.mods.stevekunglib.utils.client.ClientUtils;
import stevekung.mods.stevekunglib.utils.client.ColoredFontRenderer;

public class ColorUtils
{
    public static ColoredFontRenderer coloredFontRenderer;

    @SideOnly(Side.CLIENT)
    public static void init()
    {
        Minecraft mc = Minecraft.getMinecraft();
        ColorUtils.coloredFontRenderer = new ColoredFontRenderer(mc.gameSettings, new ResourceLocation("textures/font/ascii.png"), mc.renderEngine);

        if (mc.gameSettings.language != null)
        {
            ColorUtils.coloredFontRenderer.setUnicodeFlag(mc.getLanguageManager().isCurrentLocaleUnicode() || mc.gameSettings.forceUnicodeFont);
            ColorUtils.coloredFontRenderer.setBidiFlag(mc.getLanguageManager().isCurrentLanguageBidirectional());
        }
        ((IReloadableResourceManager)mc.getResourceManager()).registerReloadListener(ColorUtils.coloredFontRenderer);
    }

    public static int rgbToDecimal(int r, int g, int b)
    {
        return b + 256 * g + 65536 * r;
    }

    public static float[] rgbToFloatArray(int r, int g, int b)
    {
        return new float[] { r / 255.0F, g / 255.0F, b / 255.0F };
    }

    public static int hexToRgb(String color)
    {
        return ColorUtils.rgbToDecimal(Integer.valueOf(color.substring(1, 3), 16), Integer.valueOf(color.substring(3, 5), 16), Integer.valueOf(color.substring(5, 7), 16));
    }

    public static int to32BitColor(int a, int r, int g, int b)
    {
        a = a << 24;
        r = r << 16;
        g = g << 8;
        return a | r | g | b;
    }

    public static RGB stringToRGB(String color)
    {
        return ColorUtils.stringToRGB(color, false, null);
    }

    public static RGB stringToFullRGB(String color)
    {
        return ColorUtils.stringToFullRGB(color, false, null);
    }

    public static RGB intToRGB(int red, int green, int blue, int alpha)
    {
        return new RGB(red, green, blue, alpha);
    }

    public static RGB stringToRGB(String color, boolean printException, String optionName)
    {
        try
        {
            String[] colorArray = color.split(",");
            float red = Float.parseFloat(colorArray[0]);
            float green = Float.parseFloat(colorArray[1]);
            float blue = Float.parseFloat(colorArray[2]);
            return new RGB(red, green, blue, 255.0F);
        }
        catch (Exception e)
        {
            if (printException)
            {
                ModLogger.error("Invalid RGB Color format at option {}!", optionName);
                ClientUtils.printClientMessage("Invalid RGB Color format at option " + optionName + "!", JsonUtils.red());
                e.printStackTrace();
            }
            return new RGB(true);
        }
    }

    public static RGB stringToFullRGB(String color, boolean printException, String optionName)
    {
        try
        {
            String[] colorArray = color.split(",");
            float red = Float.parseFloat(colorArray[0]);
            float green = Float.parseFloat(colorArray[1]);
            float blue = Float.parseFloat(colorArray[2]);
            float alpha = Float.parseFloat(colorArray[3]);
            return new RGB(red, green, blue, alpha);
        }
        catch (Exception e)
        {
            if (printException)
            {
                ModLogger.error("Invalid RGB Color format at option {}!", optionName);
                ClientUtils.printClientMessage("Invalid RGB Color format at option " + optionName + "!", JsonUtils.red());
                e.printStackTrace();
            }
            return new RGB(true);
        }
    }

    public static RGB toRGB(int color)
    {
        float alpha = (color >> 24 & 255) / 255.0F;
        float red = (color >> 16 & 255) / 255.0F;
        float green = (color >> 8 & 255) / 255.0F;
        float blue = (color & 255) / 255.0F;
        return new RGB(red, green, blue, alpha);
    }

    public static class RGB
    {
        float red;
        float green;
        float blue;
        float alpha;
        boolean error;

        public RGB(float red, float green, float blue, float alpha)
        {
            this.red = red;
            this.green = green;
            this.blue = blue;
            this.alpha = alpha;
        }

        RGB(boolean error)
        {
            this.error = error;
        }

        public int packedRed()
        {
            return (int) (this.red * 255.0F);
        }

        public int packedGreen()
        {
            return (int) (this.green * 255.0F);
        }

        public int packedBlue()
        {
            return (int) (this.blue * 255.0F);
        }

        public int packedAlpha()
        {
            return (int) (this.alpha * 255.0F);
        }

        public float floatRed()
        {
            return this.red / 255.0F;
        }

        public float floatGreen()
        {
            return this.green / 255.0F;
        }

        public float floatBlue()
        {
            return this.blue / 255.0F;
        }

        public float floatAlpha()
        {
            return this.alpha / 255.0F;
        }

        public int red()
        {
            return (int) this.red;
        }

        public int green()
        {
            return (int) this.green;
        }

        public int blue()
        {
            return (int) this.blue;
        }

        public int alpha()
        {
            return (int) this.alpha;
        }

        public String toColoredFont()
        {
            if (this.error)
            {
                if (ClientEventHandler.ticks % 16 >= 0 && ClientEventHandler.ticks % 16 <= 8)
                {
                    return this.formatColored(255, 85, 85);
                }
                else
                {
                    return this.formatColored(255, 255, 255);
                }
            }
            return this.formatColored(this.red(), this.green(), this.blue());
        }

        public int to32Bit()
        {
            return ColorUtils.to32BitColor(255, this.red(), this.green(), this.blue());
        }

        private String formatColored(int r, int g, int b)
        {
            int marker = 59136;
            return String.format("%c%c%c", (char) (marker + (r & 255)), (char) (marker + (g & 255)), (char) (marker + (b & 255)));
        }
    }
}