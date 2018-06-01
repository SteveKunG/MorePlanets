package stevekung.mods.stevekunglib.utils.enums;

public enum EnumEntityTrackerType
{
    NORMAL(80, 3, true),
    THROWABLE_SMALL(64, 10, false),
    THROWABLE_LARGE(64, 10, true),
    ARROW(64, 20, true),
    FISHING_HOOK(64, 5, true);

    private int trackingRange;
    private int updateFrequency;
    private boolean sendsVelocityUpdates;

    private EnumEntityTrackerType(int trackingRange, int updateFrequency, boolean sendsVelocityUpdates)
    {
        this.trackingRange = trackingRange;
        this.updateFrequency = updateFrequency;
        this.sendsVelocityUpdates = sendsVelocityUpdates;
    }

    public int getTrackingRange()
    {
        return this.trackingRange;
    }

    public int getUpdateFrequency()
    {
        return this.updateFrequency;
    }

    public boolean sendsVelocityUpdates()
    {
        return this.sendsVelocityUpdates;
    }
}