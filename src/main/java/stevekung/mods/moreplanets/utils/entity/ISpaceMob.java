package stevekung.mods.moreplanets.utils.entity;

public interface ISpaceMob
{
    EnumMobType getMobType();

    public static enum EnumMobType
    {
        NIBIRU,
        ROBOT;
    }
}