package stevekung.mods.stevekunglib.utils;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraftforge.common.ForgeVersion;

public class VersionChecker implements Runnable
{
    private static boolean latest;
    private static boolean noConnection;
    private static String latestVersion;
    private static String exceptionMessage;
    private static List<String> announceMessage = new ArrayList<>();
    private static VersionChecker instance;
    private static String MOD_ID;
    private static String MOD_VERSION;
    private static int MAJOR_VERSION = 2;
    private static int MINOR_VERSION = 0;
    private static int BUILD_VERSION = 0;

    static
    {
        if (instance == null)
        {
            instance = new VersionChecker();
        }
    }

    private VersionChecker() {}

    public VersionChecker(String modId, String modVersion, int major, int minor, int build)
    {
        MOD_ID = modId;
        MOD_VERSION = modVersion;
        MAJOR_VERSION = major;
        MINOR_VERSION = minor;
        BUILD_VERSION = build;
    }

    public void startCheck()
    {
        Thread thread = new Thread(VersionChecker.instance);
        thread.start();
    }

    @Override
    public void run()
    {
        InputStream version = null;
        InputStream desc = null;
        String latest = null;
        int major = 0, minor = 0, build = 0;

        try
        {
            version = new URL("https://raw.githubusercontent.com/SteveKunG/VersionCheckLibrary/master/" + MOD_ID + "/" + MOD_ID + "_version.txt").openStream();
            desc = new URL("https://raw.githubusercontent.com/SteveKunG/VersionCheckLibrary/master/" + MOD_ID + "/" + MOD_ID + "_desc.txt").openStream();
        }
        catch (MalformedURLException e)
        {
            VersionChecker.exceptionMessage = e.getClass().getName() + " " + e.getMessage();
            e.printStackTrace();
        }
        catch (UnknownHostException e)
        {
            VersionChecker.exceptionMessage = e.getClass().getName() + " " + e.getMessage();
            e.printStackTrace();
        }
        catch (Exception e)
        {
            VersionChecker.exceptionMessage = e.getClass().getName() + " " + e.getMessage();
            e.printStackTrace();
        }

        if (version == null && desc == null)
        {
            VersionChecker.noConnection = true;
            return;
        }

        try
        {
            for (EnumMCVersion mcVersion : CachedEnum.mcValues)
            {
                for (String enumVersion : mcVersion.getVersion().split(" "))
                {
                    if (ForgeVersion.mcVersion.contains(enumVersion))
                    {
                        latest = IOUtils.readLines(version, StandardCharsets.UTF_8).get(mcVersion.ordinal());
                    }
                }
            }
        }
        catch (Exception e) {}
        finally
        {
            IOUtils.closeQuietly(version);
        }

        try
        {
            VersionChecker.announceMessage = IOUtils.readLines(desc, StandardCharsets.UTF_8);
        }
        catch (Exception e) {}
        finally
        {
            IOUtils.closeQuietly(desc);
        }

        for (String latestVersion : latest.split(" "))
        {
            if (latestVersion.contains(ForgeVersion.mcVersion))
            {
                latestVersion = latest.substring(latest.indexOf("=")).replace("=", "").replace(".", "");

                try
                {
                    major = Integer.parseInt(String.valueOf(latestVersion.charAt(0)));
                    minor = Integer.parseInt(String.valueOf(latestVersion.charAt(1)));
                    build = Integer.parseInt(String.valueOf(latestVersion.charAt(2)));
                }
                catch (Exception e) {}
            }
        }
        String latestVersion = major + "." + minor + "." + build;
        VersionChecker.latestVersion = latestVersion;
        VersionChecker.latest = !MOD_VERSION.equals(latestVersion) && (major > MAJOR_VERSION || minor > MINOR_VERSION || build > BUILD_VERSION);
    }

    public boolean isLatestVersion()
    {
        return VersionChecker.latest;
    }

    public boolean noConnection()
    {
        return VersionChecker.noConnection;
    }

    public String getLatestVersion()
    {
        return VersionChecker.latestVersion;
    }

    public String getExceptionMessage()
    {
        return VersionChecker.exceptionMessage;
    }

    public List<String> getAnnounceMessage()
    {
        return VersionChecker.announceMessage;
    }

    public static void createFoundLatestMessage(EntityPlayerSP player, String modName, String url)
    {
        player.sendMessage(JsonUtils.create("New version of ").appendSibling(JsonUtils.create(modName).setStyle(JsonUtils.style().setColor(TextFormatting.AQUA)).appendSibling(JsonUtils.create(" is available ").setStyle(JsonUtils.white()).appendSibling(JsonUtils.create("v" + instance.getLatestVersion().replace("[" + ForgeVersion.mcVersion + "]=", "")).setStyle(JsonUtils.style().setColor(TextFormatting.GREEN)).appendSibling(JsonUtils.create(" for ").setStyle(JsonUtils.white()).appendSibling(JsonUtils.create("MC-" + ForgeVersion.mcVersion).setStyle(JsonUtils.style().setColor(TextFormatting.BLUE))))))));
        player.sendMessage(JsonUtils.create("Download Link ").setStyle(JsonUtils.style().setColor(TextFormatting.YELLOW)).appendSibling(JsonUtils.create("[CLICK HERE]").setStyle(JsonUtils.style().setColor(TextFormatting.RED).setHoverEvent(JsonUtils.hover(HoverEvent.Action.SHOW_TEXT, JsonUtils.create("Click Here!").setStyle(JsonUtils.style().setColor(TextFormatting.AQUA)))).setClickEvent(JsonUtils.click(ClickEvent.Action.OPEN_URL, url)))));
    }

    public static void createFailedToCheckMessage(EntityPlayerSP player, String exception)
    {
        player.sendMessage(JsonUtils.create("Unable to check latest version, Please check your internet connection").setStyle(JsonUtils.red()));
        player.sendMessage(JsonUtils.create(exception).setStyle(JsonUtils.red()));
    }
}