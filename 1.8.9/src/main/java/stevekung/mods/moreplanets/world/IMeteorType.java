package stevekung.mods.moreplanets.world;

public interface IMeteorType
{
    EnumMeteorType getMeteorType();
    double getMeteorSpawnFrequency();

    public static enum EnumMeteorType
    {
        ANTAROS;
    }
}