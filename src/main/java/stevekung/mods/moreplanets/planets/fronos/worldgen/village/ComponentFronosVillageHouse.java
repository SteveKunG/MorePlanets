/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.worldgen.village;

import java.util.List;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraftforge.common.ChestGenHooks;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;

public class ComponentFronosVillageHouse extends ComponentFronosVillage
{
    private int averageGroundLevel = -1;
    private boolean hasMadeChest;

    public ComponentFronosVillageHouse() {}

    public ComponentFronosVillageHouse(ComponentFronosVillageStartPiece p_i2103_1_, int p_i2103_2_, Random p_i2103_3_, StructureBoundingBox p_i2103_4_, int p_i2103_5_)
    {
        super(p_i2103_1_, p_i2103_2_);
        this.coordBaseMode = p_i2103_5_;
        this.boundingBox = p_i2103_4_;
    }

    public static ComponentFronosVillageHouse func_74915_a(ComponentFronosVillageStartPiece p_74915_0_, List p_74915_1_, Random p_74915_2_, int p_74915_3_, int p_74915_4_, int p_74915_5_, int p_74915_6_, int p_74915_7_)
    {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_74915_3_, p_74915_4_, p_74915_5_, 0, 0, 0, 10, 6, 7, p_74915_6_);
        return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_74915_1_, structureboundingbox) == null ? new ComponentFronosVillageHouse(p_74915_0_, p_74915_7_, p_74915_2_, structureboundingbox, p_74915_6_) : null;
    }

    @Override
    protected void func_143012_a(NBTTagCompound p_143012_1_)
    {
        super.func_143012_a(p_143012_1_);
        p_143012_1_.setBoolean("Chest", this.hasMadeChest);
    }

    @Override
    protected void func_143011_b(NBTTagCompound p_143011_1_)
    {
        super.func_143011_b(p_143011_1_);
        this.hasMadeChest = p_143011_1_.getBoolean("Chest");
    }

    @Override
    public boolean addComponentParts(World world, Random rand, StructureBoundingBox box)
    {
        if (this.averageGroundLevel < 0)
        {
            this.averageGroundLevel = this.getAverageGroundLevel(world, box);

            if (this.averageGroundLevel < 0)
            {
                return true;
            }
            this.boundingBox.offset(0, this.averageGroundLevel - this.boundingBox.maxY + 6 - 1, 0);
        }

        this.fillWithBlocks(world, box, 0, 1, 0, 9, 4, 6, Blocks.air, Blocks.air, false);
        this.fillWithBlocks(world, box, 0, 0, 0, 9, 0, 6, Blocks.cobblestone, Blocks.cobblestone, false);
        this.fillWithBlocks(world, box, 0, 4, 0, 9, 4, 6, Blocks.cobblestone, Blocks.cobblestone, false);
        this.fillWithBlocks(world, box, 0, 5, 0, 9, 5, 6, Blocks.stone_slab, Blocks.stone_slab, false);
        this.fillWithBlocks(world, box, 1, 5, 1, 8, 5, 5, Blocks.air, Blocks.air, false);
        this.fillWithBlocks(world, box, 1, 1, 0, 2, 3, 0, FronosBlocks.fronos_wooden_planks, FronosBlocks.fronos_wooden_planks, false);
        this.fillWithBlocks(world, box, 0, 1, 0, 0, 4, 0, FronosBlocks.fronos_log, FronosBlocks.fronos_log, false);
        this.fillWithBlocks(world, box, 3, 1, 0, 3, 4, 0, FronosBlocks.fronos_log, FronosBlocks.fronos_log, false);
        this.fillWithBlocks(world, box, 0, 1, 6, 0, 4, 6, FronosBlocks.fronos_log, FronosBlocks.fronos_log, false);
        this.placeBlockAtCurrentPosition(world, FronosBlocks.fronos_wooden_planks, 0, 3, 3, 1, box);
        this.fillWithBlocks(world, box, 3, 1, 2, 3, 3, 2, FronosBlocks.fronos_wooden_planks, FronosBlocks.fronos_wooden_planks, false);
        this.fillWithBlocks(world, box, 4, 1, 3, 5, 3, 3, FronosBlocks.fronos_wooden_planks, FronosBlocks.fronos_wooden_planks, false);
        this.fillWithBlocks(world, box, 0, 1, 1, 0, 3, 5, FronosBlocks.fronos_wooden_planks, FronosBlocks.fronos_wooden_planks, false);
        this.fillWithBlocks(world, box, 1, 1, 6, 5, 3, 6, FronosBlocks.fronos_wooden_planks, FronosBlocks.fronos_wooden_planks, false);
        this.fillWithBlocks(world, box, 5, 1, 0, 5, 3, 0, FronosBlocks.fronos_fence, FronosBlocks.fronos_fence, false);
        this.fillWithBlocks(world, box, 9, 1, 0, 9, 3, 0, FronosBlocks.fronos_fence, FronosBlocks.fronos_fence, false);
        this.fillWithBlocks(world, box, 6, 1, 4, 9, 4, 6, Blocks.cobblestone, Blocks.cobblestone, false);
        this.placeBlockAtCurrentPosition(world, Blocks.flowing_lava, 0, 7, 1, 5, box);
        this.placeBlockAtCurrentPosition(world, Blocks.flowing_lava, 0, 8, 1, 5, box);
        this.placeBlockAtCurrentPosition(world, Blocks.iron_bars, 0, 9, 2, 5, box);
        this.placeBlockAtCurrentPosition(world, Blocks.iron_bars, 0, 9, 2, 4, box);
        this.fillWithBlocks(world, box, 7, 2, 4, 8, 2, 5, Blocks.air, Blocks.air, false);
        this.placeBlockAtCurrentPosition(world, Blocks.cobblestone, 0, 6, 1, 3, box);
        this.placeBlockAtCurrentPosition(world, FronosBlocks.candy_extractor_idle, 0, 6, 2, 3, box);
        this.placeBlockAtCurrentPosition(world, FronosBlocks.candy_extractor_idle, 0, 6, 3, 3, box);
        this.placeBlockAtCurrentPosition(world, Blocks.double_stone_slab, 0, 8, 1, 1, box);
        this.placeBlockAtCurrentPosition(world, FronosBlocks.cheese_glass_pane, 0, 0, 2, 2, box);
        this.placeBlockAtCurrentPosition(world, FronosBlocks.cheese_glass_pane, 0, 0, 2, 4, box);
        this.placeBlockAtCurrentPosition(world, FronosBlocks.cheese_glass_pane, 0, 2, 2, 6, box);
        this.placeBlockAtCurrentPosition(world, FronosBlocks.cheese_glass_pane, 0, 4, 2, 6, box);
        this.placeBlockAtCurrentPosition(world, Blocks.fence, 0, 2, 1, 4, box);
        this.placeBlockAtCurrentPosition(world, Blocks.wooden_pressure_plate, 0, 2, 2, 4, box);
        this.placeBlockAtCurrentPosition(world, FronosBlocks.fronos_wooden_planks, 0, 1, 1, 5, box);
        this.placeBlockAtCurrentPosition(world, Blocks.oak_stairs, this.getMetadataWithOffset(Blocks.oak_stairs, 3), 2, 1, 5, box);
        this.placeBlockAtCurrentPosition(world, Blocks.oak_stairs, this.getMetadataWithOffset(Blocks.oak_stairs, 1), 1, 1, 4, box);
        int i;
        int j;

        if (!this.hasMadeChest)
        {
            i = this.getYWithOffset(1);
            j = this.getXWithOffset(5, 5);
            int k = this.getZWithOffset(5, 5);

            if (box.isVecInside(j, i, k))
            {
                this.hasMadeChest = true;
                this.generateStructureChestContents(world, box, rand, 5, 1, 5, ChestGenHooks.getItems(ChestGenHooks.VILLAGE_BLACKSMITH, rand), ChestGenHooks.getCount(ChestGenHooks.VILLAGE_BLACKSMITH, rand));
            }
        }

        for (i = 6; i <= 8; ++i)
        {
            if (this.getBlockAtCurrentPosition(world, i, 0, -1, box).getMaterial() == Material.air && this.getBlockAtCurrentPosition(world, i, -1, -1, box).getMaterial() != Material.air)
            {
                this.placeBlockAtCurrentPosition(world, Blocks.stone_stairs, this.getMetadataWithOffset(Blocks.stone_stairs, 3), i, 0, -1, box);
            }
        }

        for (i = 0; i < 7; ++i)
        {
            for (j = 0; j < 10; ++j)
            {
                this.clearCurrentPositionBlocksUpwards(world, j, 6, i, box);
                this.func_151554_b(world, Blocks.cobblestone, 0, j, -1, i, box);
            }
        }
        this.spawnVillagers(world, box, 7, 1, 1, 1);
        return true;
    }
}