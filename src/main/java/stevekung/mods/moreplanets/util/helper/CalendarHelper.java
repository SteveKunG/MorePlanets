package stevekung.mods.moreplanets.util.helper;

import java.util.Calendar;

public class CalendarHelper
{
    public static boolean isHalloweenDay()
    {
        return CalendarHelper.getMonth(10) && CalendarHelper.getDay(31, 0);
    }

    public static boolean isChristmasDay()
    {
        return CalendarHelper.getMonth(12) && CalendarHelper.getDay(24, 1);
    }

    public static boolean isMorePlanetsBirthDay()
    {
        return CalendarHelper.getMonth(3) && CalendarHelper.getDay(31, 1);
    }

    public static boolean isMyBirthDay()
    {
        return CalendarHelper.getMonth(2) && CalendarHelper.getDay(1, 1);
    }

    private static boolean getMonth(int month)
    {
        return Calendar.getInstance().get(Calendar.MONTH) + 1 == month;
    }

    private static boolean getDay(int day, int flag)
    {
        if (flag == 0)
        {
            return Calendar.getInstance().get(Calendar.DATE) == day;
        }
        else if (flag == 1)
        {
            return Calendar.getInstance().get(Calendar.DATE) >= day && Calendar.getInstance().get(Calendar.DATE) <= day + 2;
        }
        return false;
    }
}