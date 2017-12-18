/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.worldgen.village;

import java.util.List;
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import stevekung.mods.moreplanets.core.init.MPBlocks;
import stevekung.mods.moreplanets.moons.deimos.blocks.DeimosBlocks;
import stevekung.mods.moreplanets.moons.deimos.dimension.WorldProviderDeimos;
import stevekung.mods.moreplanets.moons.phobos.blocks.PhobosBlocks;
import stevekung.mods.moreplanets.moons.phobos.dimension.WorldProviderPhobos;

public class ComponentMartianVillagePathGen extends ComponentMartianVillageRoadPiece
{
    private int averageGroundLevel;

    public ComponentMartianVillagePathGen()
    {
    }

    public ComponentMartianVillagePathGen(ComponentMartianVillageStartPiece par1ComponentVillageStartPiece, int par2, Random par3Random, StructureBoundingBox par4StructureBoundingBox, int par5)
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
            var6 = this.getNextComponentNN((ComponentMartianVillageStartPiece) par1StructureComponent, par2List, par3Random, 0, var5);

            if (var6 != null)
            {
                var5 += Math.max(var6.getBoundingBox().getXSize(), var6.getBoundingBox().getZSize());
                var4 = true;
            }
        }

        for (var5 = par3Random.nextInt(5); var5 < this.averageGroundLevel - 8; var5 += 2 + par3Random.nextInt(5))
        {
            var6 = this.getNextComponentPP((ComponentMartianVillageStartPiece) par1StructureComponent, par2List, par3Random, 0, var5);

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
                StructureMartianVillagePieces.getNextStructureComponentVillagePath((ComponentMartianVillageStartPiece) par1StructureComponent, par2List, par3Random, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.maxZ - 2, 1, this.getComponentType());
                break;
            case 1:
                StructureMartianVillagePieces.getNextStructureComponentVillagePath((ComponentMartianVillageStartPiece) par1StructureComponent, par2List, par3Random, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.minZ - 1, 2, this.getComponentType());
                break;
            case 2:
                StructureMartianVillagePieces.getNextStructureComponentVillagePath((ComponentMartianVillageStartPiece) par1StructureComponent, par2List, par3Random, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.minZ, 1, this.getComponentType());
                break;
            case 3:
                StructureMartianVillagePieces.getNextStructureComponentVillagePath((ComponentMartianVillageStartPiece) par1StructureComponent, par2List, par3Random, this.boundingBox.maxX - 2, this.boundingBox.minY, this.boundingBox.minZ - 1, 2, this.getComponentType());
            }
        }

        if (var4 && par3Random.nextInt(3) > 0)
        {
            switch (this.coordBaseMode)
            {
            case 0:
                StructureMartianVillagePieces.getNextStructureComponentVillagePath((ComponentMartianVillageStartPiece) par1StructureComponent, par2List, par3Random, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.maxZ - 2, 3, this.getComponentType());
                break;
            case 1:
                StructureMartianVillagePieces.getNextStructureComponentVillagePath((ComponentMartianVillageStartPiece) par1StructureComponent, par2List, par3Random, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.maxZ + 1, 0, this.getComponentType());
                break;
            case 2:
                StructureMartianVillagePieces.getNextStructureComponentVillagePath((ComponentMartianVillageStartPiece) par1StructureComponent, par2List, par3Random, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.minZ, 3, this.getComponentType());
                break;
            case 3:
                StructureMartianVillagePieces.getNextStructureComponentVillagePath((ComponentMartianVillageStartPiece) par1StructureComponent, par2List, par3Random, this.boundingBox.maxX - 2, this.boundingBox.minY, this.boundingBox.maxZ + 1, 0, this.getComponentType());
            }
        }
    }

    @SuppressWarnings("rawtypes")
    public static StructureBoundingBox func_74933_a(ComponentMartianVillageStartPiece par0ComponentVillageStartPiece, List par1List, Random par2Random, int par3, int par4, int par5, int par6)
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
    public boolean addComponentParts(World world, Random rand, StructureBoundingBox box)
    {
        for (int x = this.boundingBox.minX; x <= this.boundingBox.maxX; ++x)
        {
            for (int z = this.boundingBox.minZ; z <= this.boundingBox.maxZ; ++z)
            {
                int y = world.getTopSolidOrLiquidBlock(x, z) - 1;

                if (world.provider instanceof WorldProviderDeimos)
                {
                    if (box.isVecInside(x, 64, z) && (world.getBlock(x, world.getTopSolidOrLiquidBlock(x, z) - 1, z) == DeimosBlocks.deimos_block && world.getBlockMetadata(x, world.getTopSolidOrLiquidBlock(x, z) - 1, z) == 0 || Blocks.air == world.getBlock(x, world.getTopSolidOrLiquidBlock(x, z) - 1, z)))
                    {
                        world.setBlock(x, y, z, MPBlocks.chondrite_block, 0, 3);
                    }
                }
                else if (world.provider instanceof WorldProviderPhobos)
                {
                    if (box.isVecInside(x, 64, z) && (world.getBlock(x, world.getTopSolidOrLiquidBlock(x, z) - 1, z) == PhobosBlocks.phobos_block && world.getBlockMetadata(x, world.getTopSolidOrLiquidBlock(x, z) - 1, z) == 0 || Blocks.air == world.getBlock(x, world.getTopSolidOrLiquidBlock(x, z) - 1, z)))
                    {
                        world.setBlock(x, y, z, MPBlocks.chondrite_block, 0, 3);
                    }
                }
            }
        }
        return true;
    }
}