package stevekung.mods.moreplanets.util;

public enum EnumToolSpeed
{
    WOOD(-3.2F),
    COMMON(-3.0F);

    float speed;

    private EnumToolSpeed(float speed)
    {
        this.speed = speed;
    }

    public float getSpeed()
    {
        return this.speed;
    }
}