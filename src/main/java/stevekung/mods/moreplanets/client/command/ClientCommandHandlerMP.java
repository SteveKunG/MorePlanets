package stevekung.mods.moreplanets.client.command;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.fml.client.FMLClientHandler;

public class ClientCommandHandlerMP extends ClientCommandHandler
{
    @Override
    public int executeCommand(ICommandSender sender, String message)
    {
        message = message.trim();

        if (!message.startsWith("/"))
        {
            return 0;
        }

        message = message.substring(1);
        String[] temp = message.split(" ");
        String[] args = new String[temp.length - 1];
        String commandName = temp[0];
        System.arraycopy(temp, 1, args, 0, args.length);
        ICommand icommand = this.getCommands().get(commandName);

        try
        {
            if (icommand == null)
            {
                return 0;
            }
            if (icommand.canCommandSenderUseCommand(sender))
            {
                CommandEvent event = new CommandEvent(icommand, sender, args);

                if (MinecraftForge.EVENT_BUS.post(event))
                {
                    if (event.exception != null)
                    {
                        throw event.exception;
                    }
                    return 0;
                }
                icommand.processCommand(sender, args);
                return 1;
            }
            else
            {
                sender.addChatMessage(this.format(EnumChatFormatting.RED, "commands.generic.permission"));
            }
        }
        catch (WrongUsageException wue)
        {
            sender.addChatMessage(this.format(EnumChatFormatting.RED, "commands.generic.usage", this.format(EnumChatFormatting.RED, wue.getMessage(), wue.getErrorObjects())));
        }
        catch (CommandException ce)
        {
            sender.addChatMessage(this.format(EnumChatFormatting.RED, ce.getMessage(), ce.getErrorObjects()));
        }
        catch (Throwable t)
        {
            sender.addChatMessage(this.format(EnumChatFormatting.RED, "commands.generic.exception"));
            t.printStackTrace();
        }
        return -1;
    }

    private ChatComponentTranslation format(EnumChatFormatting color, String str, Object... args)
    {
        ChatComponentTranslation ret = new ChatComponentTranslation(str, args);
        ret.getChatStyle().setColor(color);
        return ret;
    }

    @Override
    public void autoComplete(String leftOfCursor, String full)
    {
        this.latestAutoComplete = null;

        if (leftOfCursor.charAt(0) == '/')
        {
            leftOfCursor = leftOfCursor.substring(1);
            Minecraft mc = FMLClientHandler.instance().getClient();

            if (mc.currentScreen instanceof GuiChat)
            {
                List<String> commands = this.getTabCompletionOptions(mc.thePlayer, leftOfCursor, mc.thePlayer.getPosition());

                if (commands != null && !commands.isEmpty())
                {
                    if (leftOfCursor.indexOf(' ') == -1)
                    {
                        for (int i = 0; i < commands.size(); i++)
                        {
                            commands.set(i, "/" + commands.get(i));
                        }
                    }
                    else
                    {
                        for (int i = 0; i < commands.size(); i++)
                        {
                            commands.set(i, commands.get(i));
                        }
                    }
                    this.latestAutoComplete = commands.toArray(new String[commands.size()]);
                }
            }
        }
    }
}