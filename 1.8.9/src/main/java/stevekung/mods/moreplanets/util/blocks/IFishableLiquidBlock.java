package stevekung.mods.moreplanets.util.blocks;

import java.util.List;

import net.minecraft.util.WeightedRandomFishable;
import stevekung.mods.moreplanets.util.helper.ItemLootHelper;

public interface IFishableLiquidBlock
{
    public default List<WeightedRandomFishable> getJunkLoot()
    {
        return ItemLootHelper.SPACE_JUNK_LOOT;
    }

    public default List<WeightedRandomFishable> getTreasureLoot()
    {
        return ItemLootHelper.SPACE_TREASURE_LOOT;
    }

    public default List<WeightedRandomFishable> getFishLoot()
    {
        return ItemLootHelper.SPACE_FISH_LOOT;
    }
}