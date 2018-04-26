package stevekung.mods.stevekunglib.utils;

public enum EnumMCVersion
{
    MC_1_12_2("1.12.2"),
    MC_1_13("1.13");

    private String version;

    private EnumMCVersion(String version)
    {
        this.version = version;
    }

    public String getVersion()
    {
        return this.version;
    }
}