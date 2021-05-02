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
        this.dropSelf(MPBlocks.GLOWING_IRON_BLOCK);
        this.dropSelf(MPBlocks.RAW_GLOWING_IRON_BLOCK);
        this.dropSelf(MPBlocks.METEORIC_IRON_STABILIZER);
        this.dropSelf(MPBlocks.ION_PLASMA_ROD);
    }
}