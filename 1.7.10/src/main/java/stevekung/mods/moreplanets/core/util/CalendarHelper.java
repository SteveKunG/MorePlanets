/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.util;

import java.util.Calendar;

public class CalendarHelper
{
    private static Calendar calendar = Calendar.getInstance();

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
        return CalendarHelper.calendar.get(2) + 1 == month;
    }

    private static boolean getDay(int day, int flag)
    {
        if (flag == 0)
        {
            return CalendarHelper.calendar.get(5) == day;
        }
        else if (flag == 1)
        {
            return CalendarHelper.calendar.get(5) >= day && CalendarHelper.calendar.get(5) <= day + 2;
        }
        return false;
    }
}