package stevekung.mods.stevekunglib.utils;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class NumberUtils
{
    private static final NavigableMap<Long, String> SUFFIXES = new TreeMap<>();

    static
    {
        SUFFIXES.put(1_000L, "k");
        SUFFIXES.put(1_000_000L, "M");
        SUFFIXES.put(1_000_000_000L, "G");
        SUFFIXES.put(1_000_000_000_000L, "T");
        SUFFIXES.put(1_000_000_000_000_000L, "P");
        SUFFIXES.put(1_000_000_000_000_000_000L, "E");
    }

    public static String format(long value)
    {
        if (value == Long.MIN_VALUE)
        {
            return NumberUtils.format(Long.MIN_VALUE + 1);
        }
        if (value < 0)
        {
            return "-" + NumberUtils.format(-value);
        }
        if (value < 1000)
        {
            return Long.toString(value);
        }
        Map.Entry<Long, String> entry = NumberUtils.SUFFIXES.floorEntry(value);
        Long divideBy = entry.getKey();
        String suffix = entry.getValue();
        long truncated = value / (divideBy / 10);
        boolean hasDecimal = truncated < 100 && truncated / 10D != truncated / 10;
        return hasDecimal ? truncated / 10D + suffix : truncated / 10 + suffix;
    }

    public static boolean isNumber(char character)
    {
        return character >= 48 && character <= 57 || character == 45;
    }
}