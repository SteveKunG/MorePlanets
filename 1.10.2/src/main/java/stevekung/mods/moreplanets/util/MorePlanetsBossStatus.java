package stevekung.mods.moreplanets.util;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public final class MorePlanetsBossStatus
{
    public static float HEALTH_SCALE;
    public static int STATUS_BAR_TIME;
    public static String BOSS_NAME;
    public static String BOSS_TYPE;
    public static int BOSS_TEXT_COLOR;

    public static void setBossStatus(IMorePlanetsBossDisplayData data, String type, int color)
    {
        MorePlanetsBossStatus.HEALTH_SCALE = data.getBossHealth() / data.getBossMaxHealth();
        MorePlanetsBossStatus.STATUS_BAR_TIME = 100;
        MorePlanetsBossStatus.BOSS_NAME = data.getBossDisplayName().getFormattedText();
        MorePlanetsBossStatus.BOSS_TYPE = type;
        MorePlanetsBossStatus.BOSS_TEXT_COLOR = color;
    }
}