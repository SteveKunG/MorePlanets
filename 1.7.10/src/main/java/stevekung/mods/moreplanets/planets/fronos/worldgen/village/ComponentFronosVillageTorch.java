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
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;

public class ComponentFronosVillageTorch extends ComponentFronosVillage
{
    private int averageGroundLevel = -1;

    public ComponentFronosVillageTorch() {}

    public ComponentFronosVillageTorch(ComponentFronosVillageStartPiece p_i2097_1_, int p_i2097_2_, Random p_i2097_3_, StructureBoundingBox p_i2097_4_, int p_i2097_5_)
    {
        super(p_i2097_1_, p_i2097_2_);
        this.coordBaseMode = p_i2097_5_;
        this.boundingBox = p_i2097_4_;
    }

    public static StructureBoundingBox func_74904_a(ComponentFronosVillageStartPiece p_74904_0_, List p_74904_1_, Random p_74904_2_, int p_74904_3_, int p_74904_4_, int p_74904_5_, int p_74904_6_)
    {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_74904_3_, p_74904_4_, p_74904_5_, 0, 0, 0, 3, 4, 2, p_74904_6_);
        return StructureComponent.findIntersecting(p_74904_1_, structureboundingbox) != null ? null : structureboundingbox;
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
    public boolean addComponentParts(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_)
    {
        if (this.averageGroundLevel < 0)
        {
            this.averageGroundLevel = this.getAverageGroundLevel(p_74875_1_, p_74875_3_);

            if (this.averageGroundLevel < 0)
            {
                return true;
            }
            this.boundingBox.offset(0, this.averageGroundLevel - this.boundingBox.maxY + 4 - 1, 0);
        }

        this.fillWithBlocks(p_74875_1_, p_74875_3_, 0, 0, 0, 2, 3, 1, Blocks.air, Blocks.air, false);
        this.placeBlockAtCurrentPosition(p_74875_1_, FronosBlocks.fronos_fence, 0, 1, 0, 0, p_74875_3_);
        this.placeBlockAtCurrentPosition(p_74875_1_, FronosBlocks.fronos_fence, 0, 1, 1, 0, p_74875_3_);
        this.placeBlockAtCurrentPosition(p_74875_1_, FronosBlocks.fronos_fence, 0, 1, 2, 0, p_74875_3_);
        this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.wool, 15, 1, 3, 0, p_74875_3_);
        this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.torch, 0, 0, 3, 0, p_74875_3_);
        this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.torch, 0, 1, 3, 1, p_74875_3_);
        this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.torch, 0, 2, 3, 0, p_74875_3_);
        this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.torch, 0, 1, 3, -1, p_74875_3_);
        return true;
    }
}