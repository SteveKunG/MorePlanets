package stevekung.mods.moreplanets.utils.blocks;

import javax.annotation.Nullable;

import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.init.MPLootTables;

public interface IFishableLiquidBlock
{
    @Nullable
    public default ResourceLocation getLootTable()
    {
        return MPLootTables.SPACE_FISHING;
    }
}