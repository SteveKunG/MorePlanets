package stevekung.mods.moreplanets.utils;

public enum BossType
{
    ZELIUS("Zelius Boss"),
    CHALOS("Chalos Boss"),
    NIBIRU("Nibiru Boss"),
    NIBIRU_MINI("Nibiru Boss");

    private String name;

    private BossType(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }
}