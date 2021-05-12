package com.stevekung.moreplanets.data.loot;

import com.stevekung.moreplanets.world.item.MPItems;
import com.stevekung.moreplanets.world.level.block.DarkEnergyCoreBlock;
import com.stevekung.moreplanets.world.level.block.MPBlocks;
import com.stevekung.stevekungslib.data.loot.BlockLootBase;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.storage.loot.ConstantIntValue;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;

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
        this.dropSelf(MPBlocks.PURLONITE_BLOCK);
        this.add(MPBlocks.PURLONITE_CLUSTER, block -> createSilkTouchDispatchTable(block, LootItem.lootTableItem(MPItems.PURLONITE_SHARD).apply(SetItemCountFunction.setCount(ConstantIntValue.exactly(4))).apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE)).when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(MPItems.GLOWING_IRON_PICKAXE))).otherwise(applyExplosionDecay(block, LootItem.lootTableItem(MPItems.PURLONITE_SHARD).apply(SetItemCountFunction.setCount(ConstantIntValue.exactly(2)))))));
        this.dropWhenSilkTouch(MPBlocks.LARGE_PURLONITE_BUD);
        this.dropWhenSilkTouch(MPBlocks.MEDIUM_PURLONITE_BUD);
        this.dropWhenSilkTouch(MPBlocks.SMALL_PURLONITE_BUD);
        this.add(MPBlocks.PURLONITE_CRYSTAL_LANTERN, BlockLoot::createSingleItemTable);
        this.add(MPBlocks.DARK_CRYSTAL_LANTERN, BlockLoot::createSingleItemTable);
        this.dropSelf(MPBlocks.DARK_ENERGY_CORE);
        this.add(MPBlocks.DARK_ENERGY_CORE, block -> createSinglePropConditionTable(block, DarkEnergyCoreBlock.STATE, DarkEnergyCoreBlock.State.FULL));
        this.dropWhenSilkTouch(MPBlocks.ZELIUS_EGG);
        this.dropSelf(MPBlocks.DARK_ENERGY_GENERATOR);
        this.dropSelf(MPBlocks.COMPACTED_PURLONITE_BLOCK);
        this.dropSelf(MPBlocks.SPACE_JAR);
        this.dropSelf(MPBlocks.PURLONITE_WORM_JAR);
        this.dropSelf(MPBlocks.CHALOS_SPORE_JAR);
    }
}