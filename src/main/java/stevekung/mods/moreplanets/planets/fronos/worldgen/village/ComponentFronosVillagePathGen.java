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

public class ComponentFronosVillagePathGen extends ComponentFronosVillageRoadPiece
{
    private int averageGroundLevel;

    public ComponentFronosVillagePathGen() {}

    public ComponentFronosVillagePathGen(ComponentFronosVillageStartPiece p_i2105_1_, int p_i2105_2_, Random p_i2105_3_, StructureBoundingBox p_i2105_4_, int p_i2105_5_)
    {
        super(p_i2105_1_, p_i2105_2_);
        this.coordBaseMode = p_i2105_5_;
        this.boundingBox = p_i2105_4_;
        this.averageGroundLevel = Math.max(p_i2105_4_.getXSize(), p_i2105_4_.getZSize());
    }

    @Override
    protected void func_143012_a(NBTTagCompound p_143012_1_)
    {
        super.func_143012_a(p_143012_1_);
        p_143012_1_.setInteger("Length", this.averageGroundLevel);
    }

    @Override
    protected void func_143011_b(NBTTagCompound p_143011_1_)
    {
        super.func_143011_b(p_143011_1_);
        this.averageGroundLevel = p_143011_1_.getInteger("Length");
    }

    @Override
    public void buildComponent(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_)
    {
        boolean flag = false;
        int i;
        StructureComponent structurecomponent1;

        for (i = p_74861_3_.nextInt(5); i < this.averageGroundLevel - 8; i += 2 + p_74861_3_.nextInt(5))
        {
            structurecomponent1 = this.getNextComponentNN((ComponentFronosVillageStartPiece)p_74861_1_, p_74861_2_, p_74861_3_, 0, i);

            if (structurecomponent1 != null)
            {
                i += Math.max(structurecomponent1.getBoundingBox().getXSize(), structurecomponent1.getBoundingBox().getZSize());
                flag = true;
            }
        }

        for (i = p_74861_3_.nextInt(5); i < this.averageGroundLevel - 8; i += 2 + p_74861_3_.nextInt(5))
        {
            structurecomponent1 = this.getNextComponentPP((ComponentFronosVillageStartPiece)p_74861_1_, p_74861_2_, p_74861_3_, 0, i);

            if (structurecomponent1 != null)
            {
                i += Math.max(structurecomponent1.getBoundingBox().getXSize(), structurecomponent1.getBoundingBox().getZSize());
                flag = true;
            }
        }

        if (flag && p_74861_3_.nextInt(3) > 0)
        {
            switch (this.coordBaseMode)
            {
            case 0:
                StructureFronosVillagePieces.getNextStructureComponentVillagePath((ComponentFronosVillageStartPiece)p_74861_1_, p_74861_2_, p_74861_3_, this.getBoundingBox().minX - 1, this.getBoundingBox().minY, this.getBoundingBox().maxZ - 2, 1, this.getComponentType());
                break;
            case 1:
                StructureFronosVillagePieces.getNextStructureComponentVillagePath((ComponentFronosVillageStartPiece)p_74861_1_, p_74861_2_, p_74861_3_, this.getBoundingBox().minX, this.getBoundingBox().minY, this.getBoundingBox().minZ - 1, 2, this.getComponentType());
                break;
            case 2:
                StructureFronosVillagePieces.getNextStructureComponentVillagePath((ComponentFronosVillageStartPiece)p_74861_1_, p_74861_2_, p_74861_3_, this.getBoundingBox().minX - 1, this.getBoundingBox().minY, this.getBoundingBox().minZ, 1, this.getComponentType());
                break;
            case 3:
                StructureFronosVillagePieces.getNextStructureComponentVillagePath((ComponentFronosVillageStartPiece)p_74861_1_, p_74861_2_, p_74861_3_, this.getBoundingBox().maxX - 2, this.getBoundingBox().minY, this.getBoundingBox().minZ - 1, 2, this.getComponentType());
            }
        }

        if (flag && p_74861_3_.nextInt(3) > 0)
        {
            switch (this.coordBaseMode)
            {
            case 0:
                StructureFronosVillagePieces.getNextStructureComponentVillagePath((ComponentFronosVillageStartPiece)p_74861_1_, p_74861_2_, p_74861_3_, this.getBoundingBox().maxX + 1, this.getBoundingBox().minY, this.getBoundingBox().maxZ - 2, 3, this.getComponentType());
                break;
            case 1:
                StructureFronosVillagePieces.getNextStructureComponentVillagePath((ComponentFronosVillageStartPiece)p_74861_1_, p_74861_2_, p_74861_3_, this.getBoundingBox().minX, this.getBoundingBox().minY, this.getBoundingBox().maxZ + 1, 0, this.getComponentType());
                break;
            case 2:
                StructureFronosVillagePieces.getNextStructureComponentVillagePath((ComponentFronosVillageStartPiece)p_74861_1_, p_74861_2_, p_74861_3_, this.getBoundingBox().maxX + 1, this.getBoundingBox().minY, this.getBoundingBox().minZ, 3, this.getComponentType());
                break;
            case 3:
                StructureFronosVillagePieces.getNextStructureComponentVillagePath((ComponentFronosVillageStartPiece)p_74861_1_, p_74861_2_, p_74861_3_, this.getBoundingBox().maxX - 2, this.getBoundingBox().minY, this.getBoundingBox().maxZ + 1, 0, this.getComponentType());
            }
        }
    }

    @Override
    public boolean addComponentParts(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_)
    {
        Block block = this.getBiomeSpecificBlock(Blocks.gravel, 0);

        for (int i = this.boundingBox.minX; i <= this.boundingBox.maxX; ++i)
        {
            for (int j = this.boundingBox.minZ; j <= this.boundingBox.maxZ; ++j)
            {
                if (p_74875_3_.isVecInside(i, 64, j))
                {
                    int k = p_74875_1_.getTopSolidOrLiquidBlock(i, j) - 1;
                    p_74875_1_.setBlock(i, k, j, block, 0, 2);
                }
            }
        }
        return true;
    }

    @SuppressWarnings("rawtypes")
    public static StructureBoundingBox func_74933_a(ComponentFronosVillageStartPiece par0ComponentVillageStartPiece, List par1List, Random par2Random, int par3, int par4, int par5, int par6)
    {
        for (int var7 = 7 * MathHelper.getRandomIntegerInRange(par2Random, 3, 5); var7 >= 7; var7 -= 7)
        {
            StructureBoundingBox var8 = StructureBoundingBox.getComponentToAddBoundingBox(par3, par4, par5, 0, 0, 0, 3, 3, var7, par6);

            if (StructureComponent.findIntersecting(par1List, var8) == null)
            {
                return var8;
            }
        }
        return null;
    }
}