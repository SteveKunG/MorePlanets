package stevekung.mods.stevekunglib.utils.enums;

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