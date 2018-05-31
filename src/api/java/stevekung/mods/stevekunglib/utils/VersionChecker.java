package stevekung.mods.stevekunglib.utils;

import java.util.Map;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.versioning.ComparableVersion;

public class VersionChecker
{
    private Object mod;
    private String modName;
    private String changeLog;
    private String latestVersion;
    private String url;
    private String command;
    private boolean failed;

    public VersionChecker(Object mod, String modName, String url, String command)
    {
        this.mod = mod;
        this.modName = modName;
        this.url = url;
        this.command = command;
    }

    public void startCheck()
    {
        ForgeVersion.CheckResult result = ForgeVersion.getResult(Loader.instance().getModObjectList().inverse().get(this.mod));

        for (Map.Entry<ComparableVersion, String> entry : result.changes.entrySet())
        {
            ComparableVersion version = entry.getKey();
            this.changeLog = entry.getValue();

            if (result.status == ForgeVersion.Status.OUTDATED)
            {
                this.latestVersion = version.toString();
            }
        }
    }

    public void startCheckIfFailed()
    {
        ForgeVersion.CheckResult result = ForgeVersion.getResult(Loader.instance().getModObjectList().inverse().get(this.mod));
        this.failed = result.status == ForgeVersion.Status.FAILED || result.status == ForgeVersion.Status.PENDING;
    }

    public void printInfo(EntityPlayerSP player)
    {
        if (this.failed)
        {
            player.sendMessage(JsonUtils.create("Unable to check latest version of " + this.formatText(TextFormatting.DARK_RED, this.modName + "!") + "!, Please check your internet connection.").setStyle(JsonUtils.red().setBold(true)));
            return;
        }
        if (this.latestVersion != null)
        {
            String text = String.format("New version of %s is available %s for %s", this.formatText(TextFormatting.AQUA, this.modName), this.formatText(TextFormatting.GREEN, "v" + this.latestVersion), this.formatText(TextFormatting.BLUE, "Minecraft " + ForgeVersion.mcVersion));
            player.sendMessage(JsonUtils.create(text));
            player.sendMessage(JsonUtils.create("Download Link ").setStyle(JsonUtils.style().setColor(TextFormatting.YELLOW)).appendSibling(JsonUtils.create("[CLICK HERE]").setStyle(JsonUtils.style().setColor(TextFormatting.RED).setHoverEvent(JsonUtils.hover(HoverEvent.Action.SHOW_TEXT, JsonUtils.create("Click Here!").setStyle(JsonUtils.style().setColor(TextFormatting.DARK_GREEN)))).setClickEvent(JsonUtils.click(ClickEvent.Action.OPEN_URL, this.url)))));
        }
    }

    public void printChangeLog(EntityPlayerSP player)
    {
        if (this.changeLog != null)
        {
            player.sendMessage(JsonUtils.create(this.changeLog).setStyle(JsonUtils.gray()));
            player.sendMessage(JsonUtils.create("To read " + this.modName + " full change log. Use /" + this.command + " command!").setStyle(JsonUtils.gray().setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/" + this.command))));
        }
    }

    private String formatText(TextFormatting color, String text)
    {
        return color + text + TextFormatting.WHITE;
    }
}