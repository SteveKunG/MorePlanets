package stevekung.mods.stevekunglib.utils.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.stevekunglib.utils.ModLogger;

@SideOnly(Side.CLIENT)
public class ColoredFontRenderer extends FontRenderer
{
    private static final int MARKER = 59136;
    private boolean dropShadow;
    private int state = 0;
    private int red;
    private int green;
    private int blue;

    public ColoredFontRenderer(GameSettings gameSettings, ResourceLocation location, TextureManager textureManager, boolean unicode)
    {
        super(gameSettings, location, textureManager, unicode);
        ModLogger.info("Loading {} for fancy colored text!", this.getClass().getName());
    }

    @Override
    protected String wrapFormattedStringToWidth(String str, int wrapWidth)
    {
        int i = this.sizeStringToWidth(str, wrapWidth);

        if (str.length() <= i)
        {
            return str;
        }
        else
        {
            String s = str.substring(0, i);
            char c0 = str.charAt(i);
            boolean flag = c0 == ' ' || c0 == '\n';
            String s1 = this.getCustomFormatFromString(s) + str.substring(i + (flag ? 1 : 0));
            return s + "\n" + this.wrapFormattedStringToWidth(s1, wrapWidth);
        }
    }

    @Override
    public int renderString(String text, float x, float y, int color, boolean dropShadow)
    {
        this.dropShadow = dropShadow;
        this.setUnicodeFlag(Minecraft.getMinecraft().getLanguageManager().isCurrentLocaleUnicode() || Minecraft.getMinecraft().gameSettings.forceUnicodeFont);
        this.setBidiFlag(Minecraft.getMinecraft().getLanguageManager().isCurrentLanguageBidirectional());
        return super.renderString(text, x, y, color, dropShadow);
    }

    @Override
    protected float renderUnicodeChar(char charac, boolean italic)
    {
        return this.renderColoredChar(charac, super.renderUnicodeChar(charac, italic));
    }

    @Override
    protected float renderDefaultChar(int charac, boolean italic)
    {
        return this.renderColoredChar(charac, super.renderDefaultChar(charac, italic));
    }

    public static String color(int r, int g, int b)
    {
        return String.format("%c%c%c", (char) (ColoredFontRenderer.MARKER + (r & 255)), (char) (ColoredFontRenderer.MARKER + (g & 255)), (char) (ColoredFontRenderer.MARKER + (b & 255)));
    }

    private float renderColoredChar(int charac, float defaultValue)
    {
        if (charac >= ColoredFontRenderer.MARKER && charac <= ColoredFontRenderer.MARKER + 255)
        {
            int value = charac & 255;

            switch (this.state)
            {
            case 0:
                this.red = value;
                break;
            case 1:
                this.green = value;
                break;
            case 2:
                this.blue = value;
                break;
            default:
                this.setColor(1.0F, 1.0F, 1.0F, 1.0F);
                return 0.0F;
            }

            this.state = ++this.state % 3;
            int color = this.red << 16 | this.green << 8 | this.blue | 255 << 24;

            if ((color & -67108864) == 0)
            {
                color |= -16777216;
            }
            if (this.dropShadow)
            {
                color = (color & 16579836) >> 2 | color & -16777216;
            }
            this.setColor((color >> 16 & 255) / 255.0F, (color >> 8 & 255) / 255.0F, (color >> 0 & 255) / 255.0F, (color >> 24 & 255) / 255.0F);
            return 0.0F;
        }
        if (this.state != 0)
        {
            this.state = 0;
            this.setColor(1.0F, 1.0F, 1.0F, 1.0F);
        }
        return defaultValue;
    }

    private String getCustomFormatFromString(String text)
    {
        String s = "";
        int i = 0;
        int j = text.length();

        while (i < j - 1)
        {
            char c = text.charAt(i);

            if (c == 167)
            {
                char c0 = text.charAt(i + 1);

                if (c0 >= 48 && c0 <= 57 || c0 >= 97 && c0 <= 102 || c0 >= 65 && c0 <= 70)
                {
                    s = "\u00a7" + c0;
                    i++;
                }
                else if (c0 >= 107 && c0 <= 111 || c0 >= 75 && c0 <= 79 || c0 == 114 || c0 == 82)
                {
                    s = s + "\u00a7" + c0;
                    i++;
                }
            }
            else if (c >= ColoredFontRenderer.MARKER && c <= ColoredFontRenderer.MARKER + 255)
            {
                s = String.format("%s%s%s", c, text.charAt(i + 1), text.charAt(i + 2));
                i += 2;
            }
            i++;
        }
        return s;
    }
}