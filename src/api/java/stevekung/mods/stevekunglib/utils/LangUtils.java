package stevekung.mods.stevekunglib.utils;

import net.minecraft.util.text.translation.LanguageMap;

public class LangUtils
{
    private static final LanguageMap INSTANCE = LanguageMap.getInstance();

    public static String translate(String key)
    {
        return LangUtils.INSTANCE.translateKey(key);
    }

    public static String translate(String key, Object... obj)
    {
        return LangUtils.INSTANCE.translateKeyFormat(key, obj);
    }
}