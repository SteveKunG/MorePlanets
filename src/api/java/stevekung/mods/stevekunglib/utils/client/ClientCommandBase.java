package stevekung.mods.stevekunglib.utils.client;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.IClientCommand;
import net.minecraftforge.common.ForgeHooks;

public abstract class ClientCommandBase extends CommandBase implements IClientCommand
{
    @Override
    public String getUsage(ICommandSender sender)
    {
        return "/" + this.getName();
    }

    @Override
    public int getRequiredPermissionLevel()
    {
        return 0;
    }

    @Override
    public boolean allowUsageWithoutPrefix(ICommandSender sender, String message)
    {
        return false;
    }

    protected static ITextComponent getChatComponentFromNthArg(String[] args, int index)
    {
        ITextComponent component = new TextComponentString("");

        for (int i = index; i < args.length; ++i)
        {
            if (i > index)
            {
                component.appendText(" ");
            }
            ITextComponent itextcomponent1 = ForgeHooks.newChatWithLinks(args[i]);
            component.appendSibling(itextcomponent1);
        }
        return component;
    }
}