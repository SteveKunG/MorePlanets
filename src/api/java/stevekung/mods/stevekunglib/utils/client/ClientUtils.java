package stevekung.mods.stevekunglib.utils.client;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import stevekung.mods.stevekunglib.utils.JsonUtils;

public class ClientUtils
{
    public static boolean isClient()
    {
        return FMLCommonHandler.instance().getSide() == Side.CLIENT;
    }

    public static boolean isEffectiveClient()
    {
        return FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT;
    }

    public static boolean isShiftKeyDown()
    {
        return Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT);
    }

    public static boolean isControlKeyDown()
    {
        return Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) || Keyboard.isKeyDown(Keyboard.KEY_RCONTROL);
    }

    public static void setOverlayMessage(String message)
    {
        Minecraft.getMinecraft().ingameGUI.setOverlayMessage(message, false);
    }

    public static void setOverlayMessage(ITextComponent component)
    {
        Minecraft.getMinecraft().ingameGUI.setOverlayMessage(component.getFormattedText(), false);
    }

    public static void printClientMessage(String text)
    {
        ClientUtils.printClientMessage(JsonUtils.create(text));
    }

    public static void printClientMessage(String text, Style color)
    {
        ClientUtils.printClientMessage(JsonUtils.create(text).setStyle(color));
    }

    public static void printClientMessage(ITextComponent component)
    {
        if (Minecraft.getMinecraft().player != null)
        {
            Minecraft.getMinecraft().player.sendMessage(component);
        }
    }

    public static void registerCommand(ClientCommandBase command)
    {
        ClientCommandHandler.instance.registerCommand(command);
    }
}