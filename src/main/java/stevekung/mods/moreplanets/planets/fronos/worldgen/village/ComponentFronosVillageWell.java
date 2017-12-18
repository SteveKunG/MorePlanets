/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.worldgen.village;

import java.util.List;
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;

public class ComponentFronosVillageWell extends ComponentFronosVillage
{
    private int averageGroundLevel = -1;

    public ComponentFronosVillageWell() {}

    public ComponentFronosVillageWell(ComponentFronosVillageStartPiece p_i2109_1_, int p_i2109_2_, Random p_i2109_3_, int p_i2109_4_, int p_i2109_5_)
    {
        super(p_i2109_1_, p_i2109_2_);
        this.coordBaseMode = p_i2109_3_.nextInt(4);

        switch (this.coordBaseMode)
        {
        case 0:
        case 2:
            this.boundingBox = new StructureBoundingBox(p_i2109_4_, 64, p_i2109_5_, p_i2109_4_ + 6 - 1, 78, p_i2109_5_ + 6 - 1);
            break;
        default:
            this.boundingBox = new StructureBoundingBox(p_i2109_4_, 64, p_i2109_5_, p_i2109_4_ + 6 - 1, 78, p_i2109_5_ + 6 - 1);
        }
    }

    @Override
    public void buildComponent(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_)
    {
        StructureFronosVillagePieces.getNextStructureComponentVillagePath((ComponentFronosVillageStartPiece)p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.minX - 1, this.boundingBox.maxY - 4, this.boundingBox.minZ + 1, 1, this.getComponentType());
        StructureFronosVillagePieces.getNextStructureComponentVillagePath((ComponentFronosVillageStartPiece)p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.maxX + 1, this.boundingBox.maxY - 4, this.boundingBox.minZ + 1, 3, this.getComponentType());
        StructureFronosVillagePieces.getNextStructureComponentVillagePath((ComponentFronosVillageStartPiece)p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.minX + 1, this.boundingBox.maxY - 4, this.boundingBox.minZ - 1, 2, this.getComponentType());
        StructureFronosVillagePieces.getNextStructureComponentVillagePath((ComponentFronosVillageStartPiece)p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.minX + 1, this.boundingBox.maxY - 4, this.boundingBox.maxZ + 1, 0, this.getComponentType());
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
            this.boundingBox.offset(0, this.averageGroundLevel - this.boundingBox.maxY + 3, 0);
        }

        this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 0, 1, 4, 12, 4, Blocks.cobblestone, Blocks.flowing_water, false);
        this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.air, 0, 2, 12, 2, p_74875_3_);
        this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.air, 0, 3, 12, 2, p_74875_3_);
        this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.air, 0, 2, 12, 3, p_74875_3_);
        this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.air, 0, 3, 12, 3, p_74875_3_);
        this.placeBlockAtCurrentPosition(p_74875_1_, FronosBlocks.fronos_fence, 0, 1, 13, 1, p_74875_3_);
        this.placeBlockAtCurrentPosition(p_74875_1_, FronosBlocks.fronos_fence, 0, 1, 14, 1, p_74875_3_);
        this.placeBlockAtCurrentPosition(p_74875_1_, FronosBlocks.fronos_fence, 0, 4, 13, 1, p_74875_3_);
        this.placeBlockAtCurrentPosition(p_74875_1_, FronosBlocks.fronos_fence, 0, 4, 14, 1, p_74875_3_);
        this.placeBlockAtCurrentPosition(p_74875_1_, FronosBlocks.fronos_fence, 0, 1, 13, 4, p_74875_3_);
        this.placeBlockAtCurrentPosition(p_74875_1_, FronosBlocks.fronos_fence, 0, 1, 14, 4, p_74875_3_);
        this.placeBlockAtCurrentPosition(p_74875_1_, FronosBlocks.fronos_fence, 0, 4, 13, 4, p_74875_3_);
        this.placeBlockAtCurrentPosition(p_74875_1_, FronosBlocks.fronos_fence, 0, 4, 14, 4, p_74875_3_);
        this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 15, 1, 4, 15, 4, Blocks.cobblestone, Blocks.cobblestone, false);

        for (int i = 0; i <= 5; ++i)
        {
            for (int j = 0; j <= 5; ++j)
            {
                if (j == 0 || j == 5 || i == 0 || i == 5)
                {
                    this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.gravel, 0, j, 11, i, p_74875_3_);
                    this.clearCurrentPositionBlocksUpwards(p_74875_1_, j, 12, i, p_74875_3_);
                }
            }
        }
        return true;
    }
}