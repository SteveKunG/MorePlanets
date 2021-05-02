package com.stevekung.moreplanets.data.loot;

import com.stevekung.moreplanets.world.level.block.MPBlocks;
import com.stevekung.stevekungslib.data.loot.BlockLootBase;

public class BlockLootTable extends BlockLootBase
{
    public BlockLootTable(String modId)
    {
        super(modId);
    }

    @Override
    protected void addTables()
    {
        this.dropSelf(MPBlocks.DIONA_REGOLITH);
        this.dropSelf(MPBlocks.DIONA_FINE_REGOLITH);
        this.dropSelf(MPBlocks.DIONA_COBBLESTONE);
        this.add(MPBlocks.DIONA_STONE, (block) -> createSingleItemTableWithSilkTouch(block, MPBlocks.DIONA_COBBLESTONE));
    }
}