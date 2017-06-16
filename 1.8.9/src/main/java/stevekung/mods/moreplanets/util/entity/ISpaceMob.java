package stevekung.mods.moreplanets.util.entity;

public interface ISpaceMob
{
    EnumMobType getMobType();

    public static enum EnumMobType
    {
        NIBIRU,
        ROBOT;
    }
}