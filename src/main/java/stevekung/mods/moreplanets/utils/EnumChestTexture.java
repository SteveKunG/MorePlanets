package stevekung.mods.moreplanets.utils;

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