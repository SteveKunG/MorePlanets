/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.venus.worldgen.village;

import java.util.List;
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import stevekung.mods.moreplanets.planets.venus.blocks.VenusBlocks;

public class ComponentVenusVillagePathGen extends ComponentVenusVillageRoadPiece
{
    private int averageGroundLevel;

    public ComponentVenusVillagePathGen()
    {
    }

    public ComponentVenusVillagePathGen(ComponentVenusVillageStartPiece par1ComponentVillageStartPiece, int par2, Random par3Random, StructureBoundingBox par4StructureBoundingBox, int par5)
    {
        super(par1ComponentVillageStartPiece, par2);
        this.coordBaseMode = par5;
        this.boundingBox = par4StructureBoundingBox;
        this.averageGroundLevel = Math.max(par4StructureBoundingBox.getXSize(), par4StructureBoundingBox.getZSize());
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

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void buildComponent(StructureComponent par1StructureComponent, List par2List, Random par3Random)
    {
        boolean var4 = false;
        int var5;
        StructureComponent var6;

        for (var5 = par3Random.nextInt(5); var5 < this.averageGroundLevel - 8; var5 += 2 + par3Random.nextInt(5))
        {
            var6 = this.getNextComponentNN((ComponentVenusVillageStartPiece) par1StructureComponent, par2List, par3Random, 0, var5);

            if (var6 != null)
            {
                var5 += Math.max(var6.getBoundingBox().getXSize(), var6.getBoundingBox().getZSize());
                var4 = true;
            }
        }

        for (var5 = par3Random.nextInt(5); var5 < this.averageGroundLevel - 8; var5 += 2 + par3Random.nextInt(5))
        {
            var6 = this.getNextComponentPP((ComponentVenusVillageStartPiece) par1StructureComponent, par2List, par3Random, 0, var5);

            if (var6 != null)
            {
                var5 += Math.max(var6.getBoundingBox().getXSize(), var6.getBoundingBox().getZSize());
                var4 = true;
            }
        }

        if (var4 && par3Random.nextInt(3) > 0)
        {
            switch (this.coordBaseMode)
            {
            case 0:
                StructureVenusVillagePieces.getNextStructureComponentVillagePath((ComponentVenusVillageStartPiece) par1StructureComponent, par2List, par3Random, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.maxZ - 2, 1, this.getComponentType());
                break;
            case 1:
                StructureVenusVillagePieces.getNextStructureComponentVillagePath((ComponentVenusVillageStartPiece) par1StructureComponent, par2List, par3Random, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.minZ - 1, 2, this.getComponentType());
                break;
            case 2:
                StructureVenusVillagePieces.getNextStructureComponentVillagePath((ComponentVenusVillageStartPiece) par1StructureComponent, par2List, par3Random, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.minZ, 1, this.getComponentType());
                break;
            case 3:
                StructureVenusVillagePieces.getNextStructureComponentVillagePath((ComponentVenusVillageStartPiece) par1StructureComponent, par2List, par3Random, this.boundingBox.maxX - 2, this.boundingBox.minY, this.boundingBox.minZ - 1, 2, this.getComponentType());
            }
        }

        if (var4 && par3Random.nextInt(3) > 0)
        {
            switch (this.coordBaseMode)
            {
            case 0:
                StructureVenusVillagePieces.getNextStructureComponentVillagePath((ComponentVenusVillageStartPiece) par1StructureComponent, par2List, par3Random, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.maxZ - 2, 3, this.getComponentType());
                break;
            case 1:
                StructureVenusVillagePieces.getNextStructureComponentVillagePath((ComponentVenusVillageStartPiece) par1StructureComponent, par2List, par3Random, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.maxZ + 1, 0, this.getComponentType());
                break;
            case 2:
                StructureVenusVillagePieces.getNextStructureComponentVillagePath((ComponentVenusVillageStartPiece) par1StructureComponent, par2List, par3Random, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.minZ, 3, this.getComponentType());
                break;
            case 3:
                StructureVenusVillagePieces.getNextStructureComponentVillagePath((ComponentVenusVillageStartPiece) par1StructureComponent, par2List, par3Random, this.boundingBox.maxX - 2, this.boundingBox.minY, this.boundingBox.maxZ + 1, 0, this.getComponentType());
            }
        }
    }

    @SuppressWarnings("rawtypes")
    public static StructureBoundingBox func_74933_a(ComponentVenusVillageStartPiece par0ComponentVillageStartPiece, List par1List, Random par2Random, int par3, int par4, int par5, int par6)
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

    @Override
    public boolean addComponentParts(World par1World, Random par2Random, StructureBoundingBox par3StructureBoundingBox)
    {
        this.getBiomeSpecificBlock(VenusBlocks.venus_block, 13);

        for (int var5 = this.boundingBox.minX; var5 <= this.boundingBox.maxX; ++var5)
        {
            for (int var6 = this.boundingBox.minZ; var6 <= this.boundingBox.maxZ; ++var6)
            {
                if (par3StructureBoundingBox.isVecInside(var5, 64, var6) && (par1World.getBlock(var5, par1World.getTopSolidOrLiquidBlock(var5, var6) - 1, var6) == VenusBlocks.venus_block && par1World.getBlockMetadata(var5, par1World.getTopSolidOrLiquidBlock(var5, var6) - 1, var6) == 0 || Blocks.air == par1World.getBlock(var5, par1World.getTopSolidOrLiquidBlock(var5, var6) - 1, var6)))
                {
                    int var7 = par1World.getTopSolidOrLiquidBlock(var5, var6) - 1;
                    par1World.setBlock(var5, var7, var6, VenusBlocks.venus_block, 12, 3);
                }
            }
        }
        return true;
    }
}