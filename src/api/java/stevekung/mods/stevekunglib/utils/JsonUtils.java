package stevekung.mods.stevekunglib.utils;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;

import com.google.gson.*;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonWriter;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;

public class JsonUtils
{
    public static ITextComponent rawTextToJson(String raw)
    {
        ITextComponent json = create("Cannot parse json format! ").setStyle(red());

        try
        {
            json = ITextComponent.Serializer.jsonToComponent("[{" + raw + "}]");
        }
        catch (JsonParseException e)
        {
            if (Minecraft.getMinecraft().player.ticksExisted % 300 == 0)
            {
                Minecraft.getMinecraft().player.sendMessage(create(e.getMessage()).setStyle(red()));
            }
        }
        return json;
    }

    public static TextComponentString create(String text)
    {
        return new TextComponentString(text);
    }

    public static Style style()
    {
        return new Style();
    }

    public static ClickEvent click(ClickEvent.Action action, String url)
    {
        return new ClickEvent(action, url);
    }

    public static HoverEvent hover(HoverEvent.Action action, ITextComponent text)
    {
        return new HoverEvent(action, text);
    }

    public static Style black()
    {
        return style().setColor(TextFormatting.BLACK);
    }

    public static Style darkBlue()
    {
        return style().setColor(TextFormatting.DARK_BLUE);
    }

    public static Style darkGreen()
    {
        return style().setColor(TextFormatting.DARK_GREEN);
    }

    public static Style darkAqua()
    {
        return style().setColor(TextFormatting.DARK_AQUA);
    }

    public static Style darkRed()
    {
        return style().setColor(TextFormatting.DARK_RED);
    }

    public static Style darkPurple()
    {
        return style().setColor(TextFormatting.DARK_PURPLE);
    }

    public static Style gold()
    {
        return style().setColor(TextFormatting.GOLD);
    }

    public static Style gray()
    {
        return style().setColor(TextFormatting.GRAY);
    }

    public static Style darkGray()
    {
        return style().setColor(TextFormatting.DARK_GRAY);
    }

    public static Style blue()
    {
        return style().setColor(TextFormatting.BLUE);
    }

    public static Style green()
    {
        return style().setColor(TextFormatting.GREEN);
    }

    public static Style aqua()
    {
        return style().setColor(TextFormatting.AQUA);
    }

    public static Style red()
    {
        return style().setColor(TextFormatting.RED);
    }

    public static Style lightPurple()
    {
        return style().setColor(TextFormatting.LIGHT_PURPLE);
    }

    public static Style yellow()
    {
        return style().setColor(TextFormatting.YELLOW);
    }

    public static Style white()
    {
        return style().setColor(TextFormatting.WHITE);
    }

    public static void toJson(Object src, Appendable writer) throws JsonIOException
    {
        if (src != null)
        {
            toJson(src, src.getClass(), writer);
        }
        else
        {
            toJson(JsonNull.INSTANCE, writer);
        }
    }

    private static void toJson(Object src, Type typeOfSrc, Appendable writer) throws JsonIOException
    {
        try
        {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonWriter jsonWriter = newJsonWriter(Streams.writerForAppendable(writer));
            gson.toJson(src, typeOfSrc, jsonWriter);
        }
        catch (IOException e)
        {
            throw new JsonIOException(e);
        }
    }

    private static JsonWriter newJsonWriter(Writer writer) throws IOException
    {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonWriter jsonWriter = new JsonWriter(writer);
        jsonWriter.setIndent("    ");
        jsonWriter.setSerializeNulls(gson.serializeNulls());
        return jsonWriter;
    }
}