package stevekung.mods.moreplanets.module.planets.nibiru.items;

import stevekung.mods.moreplanets.util.items.ItemBaseVariantsMP;

public class ItemInfectedPrismarine extends ItemBaseVariantsMP
{
    public ItemInfectedPrismarine(String name)
    {
        super();
        this.setUnlocalizedName(name);
    }

    @Override
    public String[] getItemVariantsName()
    {
        return new String[] { "shard", "crystals" };
    }

    public static enum ItemType
    {
        INFECTED_PRISMARINE_SHARD,
        INFECTED_PRISMARINE_CRYSTALS;
    }
}