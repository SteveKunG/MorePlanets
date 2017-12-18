package stevekung.mods.moreplanets.util;

public enum EnumChestTexture
{
    DIONA,
    CHALOS,
    NIBIRU
    ;

    @Override
    public String toString()
    {
        return this.name().toLowerCase();
    }
}