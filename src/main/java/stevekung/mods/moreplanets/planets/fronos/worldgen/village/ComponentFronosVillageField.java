/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.worldgen.village;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;

public class ComponentFronosVillageField extends ComponentFronosVillage
{
    private int averageGroundLevel = -1;
    private Block cropTypeA;
    private Block cropTypeB;
    private Block cropTypeC;
    private Block cropTypeD;

    public ComponentFronosVillageField() {}

    public ComponentFronosVillageField(ComponentFronosVillageStartPiece p_i2095_1_, int p_i2095_2_, Random p_i2095_3_, StructureBoundingBox p_i2095_4_, int p_i2095_5_)
    {
        super(p_i2095_1_, p_i2095_2_);
        this.coordBaseMode = p_i2095_5_;
        this.boundingBox = p_i2095_4_;
        this.cropTypeA = this.func_151559_a(p_i2095_3_);
        this.cropTypeB = this.func_151559_a(p_i2095_3_);
        this.cropTypeC = this.func_151559_a(p_i2095_3_);
        this.cropTypeD = this.func_151559_a(p_i2095_3_);
    }

    @Override
    protected void func_143012_a(NBTTagCompound p_143012_1_)
    {
        super.func_143012_a(p_143012_1_);
        p_143012_1_.setInteger("CA", Block.blockRegistry.getIDForObject(this.cropTypeA));
        p_143012_1_.setInteger("CB", Block.blockRegistry.getIDForObject(this.cropTypeB));
        p_143012_1_.setInteger("CC", Block.blockRegistry.getIDForObject(this.cropTypeC));
        p_143012_1_.setInteger("CD", Block.blockRegistry.getIDForObject(this.cropTypeD));
    }

    @Override
    protected void func_143011_b(NBTTagCompound p_143011_1_)
    {
        super.func_143011_b(p_143011_1_);
        this.cropTypeA = Block.getBlockById(p_143011_1_.getInteger("CA"));
        this.cropTypeB = Block.getBlockById(p_143011_1_.getInteger("CB"));
        this.cropTypeC = Block.getBlockById(p_143011_1_.getInteger("CC"));
        this.cropTypeD = Block.getBlockById(p_143011_1_.getInteger("CD"));
    }

    private Block func_151559_a(Random p_151559_1_)
    {
        switch (p_151559_1_.nextInt(5))
        {
        case 0:
            return Blocks.carrots;
        case 1:
            return Blocks.potatoes;
        default:
            return Blocks.wheat;
        }
    }

    public static ComponentFronosVillageField func_74900_a(ComponentFronosVillageStartPiece par0ComponentVillageStartPiece, List p_74900_1_, Random p_74900_2_, int p_74900_3_, int p_74900_4_, int p_74900_5_, int p_74900_6_, int p_74900_7_)
    {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_74900_3_, p_74900_4_, p_74900_5_, 0, 0, 0, 13, 4, 9, p_74900_6_);
        return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_74900_1_, structureboundingbox) == null ? new ComponentFronosVillageField(par0ComponentVillageStartPiece, p_74900_7_, p_74900_2_, structureboundingbox, p_74900_6_) : null;
    }

    @Override
    public boolean addComponentParts(World world, Random p_74875_2_, StructureBoundingBox p_74875_3_)
    {
        if (this.averageGroundLevel < 0)
        {
            this.averageGroundLevel = this.getAverageGroundLevel(world, p_74875_3_);

            if (this.averageGroundLevel < 0)
            {
                return true;
            }
            this.boundingBox.offset(0, this.averageGroundLevel - this.boundingBox.maxY + 7 - 1, 0);
        }

        this.fillWithBlocks(world, p_74875_3_, 0, 1, 0, 12, 4, 8, Blocks.air, Blocks.air, false);
        this.fillWithBlocks(world, p_74875_3_, 1, 0, 1, 2, 0, 7, FronosBlocks.fronos_farmland, FronosBlocks.fronos_farmland, false);
        this.fillWithBlocks(world, p_74875_3_, 4, 0, 1, 5, 0, 7, FronosBlocks.fronos_farmland, FronosBlocks.fronos_farmland, false);
        this.fillWithBlocks(world, p_74875_3_, 7, 0, 1, 8, 0, 7, FronosBlocks.fronos_farmland, FronosBlocks.fronos_farmland, false);
        this.fillWithBlocks(world, p_74875_3_, 10, 0, 1, 11, 0, 7, FronosBlocks.fronos_farmland, FronosBlocks.fronos_farmland, false);
        this.fillWithBlocks(world, p_74875_3_, 0, 0, 0, 0, 0, 8, FronosBlocks.fronos_log, FronosBlocks.fronos_log, false);
        this.fillWithBlocks(world, p_74875_3_, 6, 0, 0, 6, 0, 8, FronosBlocks.fronos_log, FronosBlocks.fronos_log, false);
        this.fillWithBlocks(world, p_74875_3_, 12, 0, 0, 12, 0, 8, FronosBlocks.fronos_log, FronosBlocks.fronos_log, false);
        this.fillWithBlocks(world, p_74875_3_, 1, 0, 0, 11, 0, 0, FronosBlocks.fronos_log, FronosBlocks.fronos_log, false);
        this.fillWithBlocks(world, p_74875_3_, 1, 0, 8, 11, 0, 8, FronosBlocks.fronos_log, FronosBlocks.fronos_log, false);
        this.fillWithBlocks(world, p_74875_3_, 3, 0, 1, 3, 0, 7, Blocks.water, Blocks.water, false);
        this.fillWithBlocks(world, p_74875_3_, 9, 0, 1, 9, 0, 7, Blocks.water, Blocks.water, false);
        int i;

        for (i = 1; i <= 7; ++i)
        {
            this.placeBlockAtCurrentPosition(world, this.cropTypeA, MathHelper.getRandomIntegerInRange(p_74875_2_, 2, 7), 1, 1, i, p_74875_3_);
            this.placeBlockAtCurrentPosition(world, this.cropTypeA, MathHelper.getRandomIntegerInRange(p_74875_2_, 2, 7), 2, 1, i, p_74875_3_);
            this.placeBlockAtCurrentPosition(world, this.cropTypeB, MathHelper.getRandomIntegerInRange(p_74875_2_, 2, 7), 4, 1, i, p_74875_3_);
            this.placeBlockAtCurrentPosition(world, this.cropTypeB, MathHelper.getRandomIntegerInRange(p_74875_2_, 2, 7), 5, 1, i, p_74875_3_);
            this.placeBlockAtCurrentPosition(world, this.cropTypeC, MathHelper.getRandomIntegerInRange(p_74875_2_, 2, 7), 7, 1, i, p_74875_3_);
            this.placeBlockAtCurrentPosition(world, this.cropTypeC, MathHelper.getRandomIntegerInRange(p_74875_2_, 2, 7), 8, 1, i, p_74875_3_);
            this.placeBlockAtCurrentPosition(world, this.cropTypeD, MathHelper.getRandomIntegerInRange(p_74875_2_, 2, 7), 10, 1, i, p_74875_3_);
            this.placeBlockAtCurrentPosition(world, this.cropTypeD, MathHelper.getRandomIntegerInRange(p_74875_2_, 2, 7), 11, 1, i, p_74875_3_);
        }

        for (i = 0; i < 9; ++i)
        {
            for (int j = 0; j < 13; ++j)
            {
                this.clearCurrentPositionBlocksUpwards(world, j, 4, i, p_74875_3_);
                this.func_151554_b(world, FronosBlocks.fronos_dirt, 0, j, -1, i, p_74875_3_);
            }
        }
        return true;
    }
}