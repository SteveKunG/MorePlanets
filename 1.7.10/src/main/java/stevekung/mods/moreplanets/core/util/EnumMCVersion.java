package stevekung.mods.moreplanets.core.util;

public enum EnumMCVersion
{
    MC_1_7_10(0, "1.7.10"),
    MC_1_8_9(1, "1.8.9"),
    MC_1_10_2(2, "1.10.2"),
    MC_1_11_2(3, "1.11.2");

    private int versionIndex;
    private String version;

    private EnumMCVersion(int versionIndex, String version)
    {
        this.versionIndex = versionIndex;
        this.version = version;
    }

    public int getVersionIndex()
    {
        return this.versionIndex;
    }

    public String getVersion()
    {
        return this.version;
    }
}