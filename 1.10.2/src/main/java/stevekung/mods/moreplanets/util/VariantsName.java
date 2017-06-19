package stevekung.mods.moreplanets.util;

import org.apache.commons.lang3.Validate;

public class VariantsName
{
    private String[] name;

    public VariantsName(String... name)
    {
        Validate.notNull(name);
        this.name = name;
    }

    public String[] getNameList()
    {
        return this.name;
    }
}