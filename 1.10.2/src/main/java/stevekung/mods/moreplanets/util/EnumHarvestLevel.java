package stevekung.mods.moreplanets.util;

public enum EnumHarvestLevel
{
    PICKAXE,
    AXE,
    SHOVEL;

    @Override
    public String toString()
    {
        return this.name().toLowerCase();
    }
}