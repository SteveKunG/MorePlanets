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
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;

public class ComponentFronosVillageHouse4 extends ComponentFronosVillage
{
    private int averageGroundLevel = -1;
    private boolean isRoofAccessible;

    public ComponentFronosVillageHouse4() {}

    public ComponentFronosVillageHouse4(ComponentFronosVillageStartPiece p_i2100_1_, int p_i2100_2_, Random p_i2100_3_, StructureBoundingBox p_i2100_4_, int p_i2100_5_)
    {
        super(p_i2100_1_, p_i2100_2_);
        this.coordBaseMode = p_i2100_5_;
        this.boundingBox = p_i2100_4_;
        this.isRoofAccessible = p_i2100_3_.nextBoolean();
    }

    @Override
    protected void func_143012_a(NBTTagCompound p_143012_1_)
    {
        super.func_143012_a(p_143012_1_);
        p_143012_1_.setBoolean("Terrace", this.isRoofAccessible);
    }

    @Override
    protected void func_143011_b(NBTTagCompound p_143011_1_)
    {
        super.func_143011_b(p_143011_1_);
        this.isRoofAccessible = p_143011_1_.getBoolean("Terrace");
    }

    public static ComponentFronosVillageHouse4 func_74912_a(ComponentFronosVillageStartPiece p_74912_0_, List p_74912_1_, Random p_74912_2_, int p_74912_3_, int p_74912_4_, int p_74912_5_, int p_74912_6_, int p_74912_7_)
    {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_74912_3_, p_74912_4_, p_74912_5_, 0, 0, 0, 5, 6, 5, p_74912_6_);
        return StructureComponent.findIntersecting(p_74912_1_, structureboundingbox) != null ? null : new ComponentFronosVillageHouse4(p_74912_0_, p_74912_7_, p_74912_2_, structureboundingbox, p_74912_6_);
    }

    @Override
    public boolean addComponentParts(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_)
    {
        if (this.averageGroundLevel < 0)
        {
            this.averageGroundLevel = this.getAverageGroundLevel(p_74875_1_, p_74875_3_);

            if (this.averageGroundLevel < 0)
            {
                return true;
            }
            this.boundingBox.offset(0, this.averageGroundLevel - this.boundingBox.maxY + 6 - 1, 0);
        }

        this.fillWithBlocks(p_74875_1_, p_74875_3_, 0, 0, 0, 4, 0, 4, Blocks.cobblestone, Blocks.cobblestone, false);
        this.fillWithBlocks(p_74875_1_, p_74875_3_, 0, 4, 0, 4, 4, 4, FronosBlocks.fronos_log, FronosBlocks.fronos_log, false);
        this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 4, 1, 3, 4, 3, FronosBlocks.fronos_wooden_planks, FronosBlocks.fronos_wooden_planks, false);
        this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.cobblestone, 0, 0, 1, 0, p_74875_3_);
        this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.cobblestone, 0, 0, 2, 0, p_74875_3_);
        this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.cobblestone, 0, 0, 3, 0, p_74875_3_);
        this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.cobblestone, 0, 4, 1, 0, p_74875_3_);
        this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.cobblestone, 0, 4, 2, 0, p_74875_3_);
        this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.cobblestone, 0, 4, 3, 0, p_74875_3_);
        this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.cobblestone, 0, 0, 1, 4, p_74875_3_);
        this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.cobblestone, 0, 0, 2, 4, p_74875_3_);
        this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.cobblestone, 0, 0, 3, 4, p_74875_3_);
        this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.cobblestone, 0, 4, 1, 4, p_74875_3_);
        this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.cobblestone, 0, 4, 2, 4, p_74875_3_);
        this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.cobblestone, 0, 4, 3, 4, p_74875_3_);
        this.fillWithBlocks(p_74875_1_, p_74875_3_, 0, 1, 1, 0, 3, 3, FronosBlocks.fronos_wooden_planks, FronosBlocks.fronos_wooden_planks, false);
        this.fillWithBlocks(p_74875_1_, p_74875_3_, 4, 1, 1, 4, 3, 3, FronosBlocks.fronos_wooden_planks, FronosBlocks.fronos_wooden_planks, false);
        this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 1, 4, 3, 3, 4, FronosBlocks.fronos_wooden_planks, FronosBlocks.fronos_wooden_planks, false);
        this.placeBlockAtCurrentPosition(p_74875_1_, FronosBlocks.cheese_glass_pane, 0, 0, 2, 2, p_74875_3_);
        this.placeBlockAtCurrentPosition(p_74875_1_, FronosBlocks.cheese_glass_pane, 0, 2, 2, 4, p_74875_3_);
        this.placeBlockAtCurrentPosition(p_74875_1_, FronosBlocks.cheese_glass_pane, 0, 4, 2, 2, p_74875_3_);
        this.placeBlockAtCurrentPosition(p_74875_1_, FronosBlocks.fronos_wooden_planks, 0, 1, 1, 0, p_74875_3_);
        this.placeBlockAtCurrentPosition(p_74875_1_, FronosBlocks.fronos_wooden_planks, 0, 1, 2, 0, p_74875_3_);
        this.placeBlockAtCurrentPosition(p_74875_1_, FronosBlocks.fronos_wooden_planks, 0, 1, 3, 0, p_74875_3_);
        this.placeBlockAtCurrentPosition(p_74875_1_, FronosBlocks.fronos_wooden_planks, 0, 2, 3, 0, p_74875_3_);
        this.placeBlockAtCurrentPosition(p_74875_1_, FronosBlocks.fronos_wooden_planks, 0, 3, 3, 0, p_74875_3_);
        this.placeBlockAtCurrentPosition(p_74875_1_, FronosBlocks.fronos_wooden_planks, 0, 3, 2, 0, p_74875_3_);
        this.placeBlockAtCurrentPosition(p_74875_1_, FronosBlocks.fronos_wooden_planks, 0, 3, 1, 0, p_74875_3_);

        if (this.getBlockAtCurrentPosition(p_74875_1_, 2, 0, -1, p_74875_3_).getMaterial() == Material.air && this.getBlockAtCurrentPosition(p_74875_1_, 2, -1, -1, p_74875_3_).getMaterial() != Material.air)
        {
            this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stone_stairs, this.getMetadataWithOffset(Blocks.stone_stairs, 3), 2, 0, -1, p_74875_3_);
        }

        this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 1, 1, 3, 3, 3, Blocks.air, Blocks.air, false);

        if (this.isRoofAccessible)
        {
            this.placeBlockAtCurrentPosition(p_74875_1_, FronosBlocks.fronos_fence, 0, 0, 5, 0, p_74875_3_);
            this.placeBlockAtCurrentPosition(p_74875_1_, FronosBlocks.fronos_fence, 0, 1, 5, 0, p_74875_3_);
            this.placeBlockAtCurrentPosition(p_74875_1_, FronosBlocks.fronos_fence, 0, 2, 5, 0, p_74875_3_);
            this.placeBlockAtCurrentPosition(p_74875_1_, FronosBlocks.fronos_fence, 0, 3, 5, 0, p_74875_3_);
            this.placeBlockAtCurrentPosition(p_74875_1_, FronosBlocks.fronos_fence, 0, 4, 5, 0, p_74875_3_);
            this.placeBlockAtCurrentPosition(p_74875_1_, FronosBlocks.fronos_fence, 0, 0, 5, 4, p_74875_3_);
            this.placeBlockAtCurrentPosition(p_74875_1_, FronosBlocks.fronos_fence, 0, 1, 5, 4, p_74875_3_);
            this.placeBlockAtCurrentPosition(p_74875_1_, FronosBlocks.fronos_fence, 0, 2, 5, 4, p_74875_3_);
            this.placeBlockAtCurrentPosition(p_74875_1_, FronosBlocks.fronos_fence, 0, 3, 5, 4, p_74875_3_);
            this.placeBlockAtCurrentPosition(p_74875_1_, FronosBlocks.fronos_fence, 0, 4, 5, 4, p_74875_3_);
            this.placeBlockAtCurrentPosition(p_74875_1_, FronosBlocks.fronos_fence, 0, 4, 5, 1, p_74875_3_);
            this.placeBlockAtCurrentPosition(p_74875_1_, FronosBlocks.fronos_fence, 0, 4, 5, 2, p_74875_3_);
            this.placeBlockAtCurrentPosition(p_74875_1_, FronosBlocks.fronos_fence, 0, 4, 5, 3, p_74875_3_);
            this.placeBlockAtCurrentPosition(p_74875_1_, FronosBlocks.fronos_fence, 0, 0, 5, 1, p_74875_3_);
            this.placeBlockAtCurrentPosition(p_74875_1_, FronosBlocks.fronos_fence, 0, 0, 5, 2, p_74875_3_);
            this.placeBlockAtCurrentPosition(p_74875_1_, FronosBlocks.fronos_fence, 0, 0, 5, 3, p_74875_3_);
        }

        int i;

        if (this.isRoofAccessible)
        {
            i = this.getMetadataWithOffset(Blocks.ladder, 3);
            this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.ladder, i, 3, 1, 3, p_74875_3_);
            this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.ladder, i, 3, 2, 3, p_74875_3_);
            this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.ladder, i, 3, 3, 3, p_74875_3_);
            this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.ladder, i, 3, 4, 3, p_74875_3_);
        }

        this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.torch, 0, 2, 3, 1, p_74875_3_);

        for (i = 0; i < 5; ++i)
        {
            for (int j = 0; j < 5; ++j)
            {
                this.clearCurrentPositionBlocksUpwards(p_74875_1_, j, 6, i, p_74875_3_);
                this.func_151554_b(p_74875_1_, Blocks.cobblestone, 0, j, -1, i, p_74875_3_);
            }
        }
        this.spawnVillagers(p_74875_1_, p_74875_3_, 1, 1, 2, 1);
        return true;
    }
}