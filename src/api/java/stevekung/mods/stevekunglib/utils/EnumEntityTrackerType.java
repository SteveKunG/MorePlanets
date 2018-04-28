package stevekung.mods.stevekunglib.utils;

public enum EnumEntityTrackerType
{
    NORMAL(80, 3),
    THROWABLE(64, 10),
    ARROW(60, 10),
    FISHING_HOOK(64, 5);

    private int trackingRange;
    private int updateFrequency;

    private EnumEntityTrackerType(int trackingRange, int updateFrequency)
    {
        this.trackingRange = trackingRange;
        this.updateFrequency = updateFrequency;
    }

    public int getTrackingRange()
    {
        return this.trackingRange;
    }

    public int getUpdateFrequency()
    {
        return this.updateFrequency;
    }
}