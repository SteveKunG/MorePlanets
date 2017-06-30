package stevekung.mods.moreplanets.client.command;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
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
            if (icommand.checkPermission(this.getServer(), sender))
            {
                CommandEvent event = new CommandEvent(icommand, sender, args);

                if (MinecraftForge.EVENT_BUS.post(event))
                {
                    if (event.getException() != null)
                    {
                        throw event.getException();
                    }
                    return 0;
                }
                icommand.execute(this.getServer(), sender, args);
                return 1;
            }
            sender.addChatMessage(this.format(TextFormatting.RED, "commands.generic.permission"));
        }
        catch (WrongUsageException wue)
        {
            sender.addChatMessage(this.format(TextFormatting.RED, "commands.generic.usage", new Object[] { this.format(TextFormatting.RED, wue.getMessage(), wue.getErrorObjects()) }));
        }
        catch (CommandException ce)
        {
            sender.addChatMessage(this.format(TextFormatting.RED, ce.getMessage(), ce.getErrorObjects()));
        }
        catch (Throwable t)
        {
            sender.addChatMessage(this.format(TextFormatting.RED, "commands.generic.exception"));
            t.printStackTrace();
        }
        return -1;
    }

    private TextComponentTranslation format(TextFormatting color, String str, Object... args)
    {
        TextComponentTranslation ret = new TextComponentTranslation(str, args);
        ret.getStyle().setColor(color);
        return ret;
    }

    @Override
    public void autoComplete(String leftOfCursor)
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