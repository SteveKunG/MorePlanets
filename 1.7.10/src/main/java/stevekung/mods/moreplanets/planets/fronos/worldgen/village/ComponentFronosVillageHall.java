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

public class ComponentFronosVillageHall extends ComponentFronosVillage
{
    private int averageGroundLevel = -1;

    public ComponentFronosVillageHall() {}

    public ComponentFronosVillageHall(ComponentFronosVillageStartPiece p_i2099_1_, int p_i2099_2_, Random p_i2099_3_, StructureBoundingBox p_i2099_4_, int p_i2099_5_)
    {
        super(p_i2099_1_, p_i2099_2_);
        this.coordBaseMode = p_i2099_5_;
        this.boundingBox = p_i2099_4_;
    }

    public static ComponentFronosVillageHall func_74906_a(ComponentFronosVillageStartPiece p_74906_0_, List p_74906_1_, Random p_74906_2_, int p_74906_3_, int p_74906_4_, int p_74906_5_, int p_74906_6_, int p_74906_7_)
    {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_74906_3_, p_74906_4_, p_74906_5_, 0, 0, 0, 9, 7, 11, p_74906_6_);
        return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_74906_1_, structureboundingbox) == null ? new ComponentFronosVillageHall(p_74906_0_, p_74906_7_, p_74906_2_, structureboundingbox, p_74906_6_) : null;
    }

    @Override
    protected void func_143012_a(NBTTagCompound nbt)
    {
        super.func_143012_a(nbt);
        nbt.setInteger("AvgGroundLevel", this.averageGroundLevel);
    }

    @Override
    protected void func_143011_b(NBTTagCompound nbt)
    {
        super.func_143011_b(nbt);
        this.averageGroundLevel = nbt.getInteger("AvgGroundLevel");
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
            this.boundingBox.offset(0, this.averageGroundLevel - this.boundingBox.maxY + 7 - 1, 0);
        }

        this.fillWithBlocks(world, box, 1, 1, 1, 7, 4, 4, Blocks.air, Blocks.air, false);
        this.fillWithBlocks(world, box, 2, 1, 6, 8, 4, 10, Blocks.air, Blocks.air, false);
        this.fillWithBlocks(world, box, 2, 0, 6, 8, 0, 10, FronosBlocks.fronos_dirt, FronosBlocks.fronos_dirt, false);
        this.placeBlockAtCurrentPosition(world, Blocks.cobblestone, 0, 6, 0, 6, box);
        this.fillWithBlocks(world, box, 2, 1, 6, 2, 1, 10, FronosBlocks.fronos_fence, FronosBlocks.fronos_fence, false);
        this.fillWithBlocks(world, box, 8, 1, 6, 8, 1, 10, FronosBlocks.fronos_fence, FronosBlocks.fronos_fence, false);
        this.fillWithBlocks(world, box, 3, 1, 10, 7, 1, 10, FronosBlocks.fronos_fence, FronosBlocks.fronos_fence, false);
        this.fillWithBlocks(world, box, 1, 0, 1, 7, 0, 4, FronosBlocks.fronos_wooden_planks, FronosBlocks.fronos_wooden_planks, false);
        this.fillWithBlocks(world, box, 0, 0, 0, 0, 3, 5, Blocks.cobblestone, Blocks.cobblestone, false);
        this.fillWithBlocks(world, box, 8, 0, 0, 8, 3, 5, Blocks.cobblestone, Blocks.cobblestone, false);
        this.fillWithBlocks(world, box, 1, 0, 0, 7, 1, 0, Blocks.cobblestone, Blocks.cobblestone, false);
        this.fillWithBlocks(world, box, 1, 0, 5, 7, 1, 5, Blocks.cobblestone, Blocks.cobblestone, false);
        this.fillWithBlocks(world, box, 1, 2, 0, 7, 3, 0, FronosBlocks.fronos_wooden_planks, FronosBlocks.fronos_wooden_planks, false);
        this.fillWithBlocks(world, box, 1, 2, 5, 7, 3, 5, FronosBlocks.fronos_wooden_planks, FronosBlocks.fronos_wooden_planks, false);
        this.fillWithBlocks(world, box, 0, 4, 1, 8, 4, 1, FronosBlocks.fronos_wooden_planks, FronosBlocks.fronos_wooden_planks, false);
        this.fillWithBlocks(world, box, 0, 4, 4, 8, 4, 4, FronosBlocks.fronos_wooden_planks, FronosBlocks.fronos_wooden_planks, false);
        this.fillWithBlocks(world, box, 0, 5, 2, 8, 5, 3, FronosBlocks.fronos_wooden_planks, FronosBlocks.fronos_wooden_planks, false);
        this.placeBlockAtCurrentPosition(world, FronosBlocks.fronos_wooden_planks, 0, 0, 4, 2, box);
        this.placeBlockAtCurrentPosition(world, FronosBlocks.fronos_wooden_planks, 0, 0, 4, 3, box);
        this.placeBlockAtCurrentPosition(world, FronosBlocks.fronos_wooden_planks, 0, 8, 4, 2, box);
        this.placeBlockAtCurrentPosition(world, FronosBlocks.fronos_wooden_planks, 0, 8, 4, 3, box);
        int i = this.getMetadataWithOffset(Blocks.oak_stairs, 3);
        int j = this.getMetadataWithOffset(Blocks.oak_stairs, 2);
        int k;
        int l;

        for (k = -1; k <= 2; ++k)
        {
            for (l = 0; l <= 8; ++l)
            {
                this.placeBlockAtCurrentPosition(world, Blocks.oak_stairs, i, l, 4 + k, k, box);
                this.placeBlockAtCurrentPosition(world, Blocks.oak_stairs, j, l, 4 + k, 5 - k, box);
            }
        }

        this.placeBlockAtCurrentPosition(world, FronosBlocks.fronos_log, 0, 0, 2, 1, box);
        this.placeBlockAtCurrentPosition(world, FronosBlocks.fronos_log, 0, 0, 2, 4, box);
        this.placeBlockAtCurrentPosition(world, FronosBlocks.fronos_log, 0, 8, 2, 1, box);
        this.placeBlockAtCurrentPosition(world, FronosBlocks.fronos_log, 0, 8, 2, 4, box);
        this.placeBlockAtCurrentPosition(world, FronosBlocks.cheese_glass_pane, 0, 0, 2, 2, box);
        this.placeBlockAtCurrentPosition(world, FronosBlocks.cheese_glass_pane, 0, 0, 2, 3, box);
        this.placeBlockAtCurrentPosition(world, FronosBlocks.cheese_glass_pane, 0, 8, 2, 2, box);
        this.placeBlockAtCurrentPosition(world, FronosBlocks.cheese_glass_pane, 0, 8, 2, 3, box);
        this.placeBlockAtCurrentPosition(world, FronosBlocks.cheese_glass_pane, 0, 2, 2, 5, box);
        this.placeBlockAtCurrentPosition(world, FronosBlocks.cheese_glass_pane, 0, 3, 2, 5, box);
        this.placeBlockAtCurrentPosition(world, FronosBlocks.cheese_glass_pane, 0, 5, 2, 0, box);
        this.placeBlockAtCurrentPosition(world, FronosBlocks.cheese_glass_pane, 0, 6, 2, 5, box);
        this.placeBlockAtCurrentPosition(world, Blocks.fence, 0, 2, 1, 3, box);
        this.placeBlockAtCurrentPosition(world, Blocks.wooden_pressure_plate, 0, 2, 2, 3, box);
        this.placeBlockAtCurrentPosition(world, FronosBlocks.fronos_wooden_planks, 0, 1, 1, 4, box);
        this.placeBlockAtCurrentPosition(world, Blocks.oak_stairs, this.getMetadataWithOffset(Blocks.oak_stairs, 3), 2, 1, 4, box);
        this.placeBlockAtCurrentPosition(world, Blocks.oak_stairs, this.getMetadataWithOffset(Blocks.oak_stairs, 1), 1, 1, 3, box);
        this.fillWithBlocks(world, box, 5, 0, 1, 7, 0, 3, Blocks.double_stone_slab, Blocks.double_stone_slab, false);
        this.placeBlockAtCurrentPosition(world, Blocks.double_stone_slab, 0, 6, 1, 1, box);
        this.placeBlockAtCurrentPosition(world, Blocks.double_stone_slab, 0, 6, 1, 2, box);
        this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 2, 1, 0, box);
        this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 2, 2, 0, box);
        this.placeBlockAtCurrentPosition(world, Blocks.torch, 0, 2, 3, 1, box);
        this.placeDoorAtCurrentPosition(world, box, rand, 2, 1, 0, this.getMetadataWithOffset(Blocks.wooden_door, 1));

        if (this.getBlockAtCurrentPosition(world, 2, 0, -1, box).getMaterial() == Material.air && this.getBlockAtCurrentPosition(world, 2, -1, -1, box).getMaterial() != Material.air)
        {
            this.placeBlockAtCurrentPosition(world, Blocks.stone_stairs, this.getMetadataWithOffset(Blocks.stone_stairs, 3), 2, 0, -1, box);
        }

        this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 6, 1, 5, box);
        this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 6, 2, 5, box);
        this.placeBlockAtCurrentPosition(world, Blocks.torch, 0, 6, 3, 4, box);
        this.placeDoorAtCurrentPosition(world, box, rand, 6, 1, 5, this.getMetadataWithOffset(Blocks.wooden_door, 1));

        for (k = 0; k < 5; ++k)
        {
            for (l = 0; l < 9; ++l)
            {
                this.clearCurrentPositionBlocksUpwards(world, l, 7, k, box);
                this.func_151554_b(world, Blocks.cobblestone, 0, l, -1, k, box);
            }
        }
        this.spawnVillagers(world, box, 4, 1, 2, 2);
        return true;
    }
}