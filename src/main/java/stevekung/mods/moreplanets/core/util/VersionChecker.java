package stevekung.mods.moreplanets.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.google.common.collect.Lists;

import cpw.mods.fml.relauncher.FMLInjectionData;
import stevekung.mods.moreplanets.core.MorePlanetsCore;

// Credit to Jarbelar
public class VersionChecker implements Runnable
{
    private static boolean isLatest = false;
    private static boolean noConnection = false;
    private static String latestVersion = "";
    private static String exceptionMessage = "";
    private static List<String> changeLog = Lists.newArrayList();
    public static VersionChecker INSTANCE = new VersionChecker();

    public static void startCheck()
    {
        Thread thread = new Thread(VersionChecker.INSTANCE);
        thread.start();
    }

    @Override
    public void run()
    {
        InputStream version = null;
        InputStream desc = null;
        String versionMC = (String) FMLInjectionData.data()[4];

        try
        {
            version = new URL("https://raw.githubusercontent.com/SteveKunG/VersionCheckLibrary/master/more_planets/more_planets_version.txt").openStream();
            desc = new URL("https://raw.githubusercontent.com/SteveKunG/VersionCheckLibrary/master/more_planets/more_planets_desc.txt").openStream();
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
            for (EnumMCVersion mcVersion : EnumMCVersion.values())
            {
                if (versionMC.equals(mcVersion.getVersion()))
                {
                    VersionChecker.latestVersion = IOUtils.readLines(version).get(mcVersion.getVersionIndex());
                }
            }
            VersionChecker.changeLog = IOUtils.readLines(desc);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        finally
        {
            IOUtils.closeQuietly(version);
            IOUtils.closeQuietly(desc);
        }
        int major = 0;
        int minor = 0;
        int build = 0;
        String latest = VersionChecker.latestVersion;

        if (latest.contains("[" + versionMC + "]="))
        {
            latest = latest.replace("[" + versionMC + "]=", "").replace(".", "");

            try
            {
                major = Integer.parseInt(String.valueOf(latest.charAt(0)));
                minor = Integer.parseInt(String.valueOf(latest.charAt(1)));
                build = Integer.parseInt(String.valueOf(latest.charAt(2)));
            }
            catch (NumberFormatException e) {}
            catch (IndexOutOfBoundsException e) {}
        }
        String latestVersion = major + "." + minor + "." + build;
        VersionChecker.isLatest = !MorePlanetsCore.VERSION.equals(latestVersion) && (major > MorePlanetsCore.major_version || minor > MorePlanetsCore.minor_version || build > MorePlanetsCore.build_version);
    }

    public boolean isLatestVersion()
    {
        return VersionChecker.isLatest;
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

    public List<String> getChangeLog()
    {
        return VersionChecker.changeLog;
    }
}