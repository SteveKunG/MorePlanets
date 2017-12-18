/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.worldgen.village;

import java.util.List;
import java.util.Random;

import micdoodle8.mods.galacticraft.core.entities.EntityAlienVillager;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;

public abstract class ComponentMartianVillage extends StructureComponent
{
    static
    {
        MapGenStructureIO.registerStructure(StructureMartianVillageStart.class, "MartianVillage");
        MapGenStructureIO.func_143031_a(ComponentMartianVillageField.class, "MartianField1");
        MapGenStructureIO.func_143031_a(ComponentMartianVillageField2.class, "MartianField2");
        MapGenStructureIO.func_143031_a(ComponentMartianVillageHouse.class, "MartianHouse");
        MapGenStructureIO.func_143031_a(ComponentMartianVillageRoadPiece.class, "MartianRoadPiece");
        MapGenStructureIO.func_143031_a(ComponentMartianVillagePathGen.class, "MartianPath");
        MapGenStructureIO.func_143031_a(ComponentMartianVillageTorch.class, "MartianTorch");
        MapGenStructureIO.func_143031_a(ComponentMartianVillageStartPiece.class, "MartianWell");
        MapGenStructureIO.func_143031_a(ComponentMartianVillageHut.class, "MartianHut");
    }

    private int villagersSpawned;
    protected ComponentMartianVillageStartPiece startPiece;

    public ComponentMartianVillage()
    {
    }

    protected ComponentMartianVillage(ComponentMartianVillageStartPiece par1ComponentVillageStartPiece, int par2)
    {
        super(par2);
        this.startPiece = par1ComponentVillageStartPiece;
    }

    @Override
    protected void func_143012_a(NBTTagCompound nbttagcompound)
    {
        nbttagcompound.setInteger("VCount", this.villagersSpawned);
    }

    @Override
    protected void func_143011_b(NBTTagCompound nbttagcompound)
    {
        this.villagersSpawned = nbttagcompound.getInteger("VCount");
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected StructureComponent getNextComponentNN(ComponentMartianVillageStartPiece par1ComponentVillageStartPiece, List par2List, Random par3Random, int par4, int par5)
    {
        switch (this.coordBaseMode)
        {
        case 0:
            return StructureMartianVillagePieces.getNextStructureComponent(par1ComponentVillageStartPiece, par2List, par3Random, this.boundingBox.minX - 1, this.boundingBox.minY + par4, this.boundingBox.minZ + par5, 1, this.getComponentType());
        case 1:
            return StructureMartianVillagePieces.getNextStructureComponent(par1ComponentVillageStartPiece, par2List, par3Random, this.boundingBox.minX + par5, this.boundingBox.minY + par4, this.boundingBox.minZ - 1, 2, this.getComponentType());
        case 2:
            return StructureMartianVillagePieces.getNextStructureComponent(par1ComponentVillageStartPiece, par2List, par3Random, this.boundingBox.minX - 1, this.boundingBox.minY + par4, this.boundingBox.minZ + par5, 1, this.getComponentType());
        case 3:
            return StructureMartianVillagePieces.getNextStructureComponent(par1ComponentVillageStartPiece, par2List, par3Random, this.boundingBox.minX + par5, this.boundingBox.minY + par4, this.boundingBox.minZ - 1, 2, this.getComponentType());
        default:
            return null;
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected StructureComponent getNextComponentPP(ComponentMartianVillageStartPiece par1ComponentVillageStartPiece, List par2List, Random par3Random, int par4, int par5)
    {
        switch (this.coordBaseMode)
        {
        case 0:
            return StructureMartianVillagePieces.getNextStructureComponent(par1ComponentVillageStartPiece, par2List, par3Random, this.boundingBox.maxX + 1, this.boundingBox.minY + par4, this.boundingBox.minZ + par5, 3, this.getComponentType());
        case 1:
            return StructureMartianVillagePieces.getNextStructureComponent(par1ComponentVillageStartPiece, par2List, par3Random, this.boundingBox.minX + par5, this.boundingBox.minY + par4, this.boundingBox.maxZ + 1, 0, this.getComponentType());
        case 2:
            return StructureMartianVillagePieces.getNextStructureComponent(par1ComponentVillageStartPiece, par2List, par3Random, this.boundingBox.maxX + 1, this.boundingBox.minY + par4, this.boundingBox.minZ + par5, 3, this.getComponentType());
        case 3:
            return StructureMartianVillagePieces.getNextStructureComponent(par1ComponentVillageStartPiece, par2List, par3Random, this.boundingBox.minX + par5, this.boundingBox.minY + par4, this.boundingBox.maxZ + 1, 0, this.getComponentType());
        default:
            return null;
        }
    }

    protected int getAverageGroundLevel(World par1World, StructureBoundingBox par2StructureBoundingBox)
    {
        int var3 = 0;
        int var4 = 0;

        for (int var5 = this.boundingBox.minZ; var5 <= this.boundingBox.maxZ; ++var5)
        {
            for (int var6 = this.boundingBox.minX; var6 <= this.boundingBox.maxX; ++var6)
            {
                if (par2StructureBoundingBox.isVecInside(var6, 64, var5))
                {
                    var3 += Math.max(par1World.getTopSolidOrLiquidBlock(var6, var5), par1World.provider.getAverageGroundLevel());
                    ++var4;
                }
            }
        }

        if (var4 == 0)
        {
            return -1;
        }
        else
        {
            return var3 / var4;
        }
    }

    protected static boolean canVillageGoDeeper(StructureBoundingBox par0StructureBoundingBox)
    {
        return par0StructureBoundingBox != null && par0StructureBoundingBox.minY > 10;
    }

    protected void spawnVillagers(World par1World, StructureBoundingBox par2StructureBoundingBox, int par3, int par4, int par5, int par6)
    {
        if (this.villagersSpawned < par6)
        {
            for (int var7 = this.villagersSpawned; var7 < par6; ++var7)
            {
                int var8 = this.getXWithOffset(par3 + var7, par5);
                int var9 = this.getYWithOffset(par4);
                int var10 = this.getZWithOffset(par3 + var7, par5);

                var8 += par1World.rand.nextInt(3) - 1;
                var10 += par1World.rand.nextInt(3) - 1;

                if (!par2StructureBoundingBox.isVecInside(var8, var9, var10))
                {
                    break;
                }

                ++this.villagersSpawned;
                EntityAlienVillager var11 = new EntityAlienVillager(par1World);
                var11.setLocationAndAngles(var8 + 0.5D, var9, var10 + 0.5D, 0.0F, 0.0F);
                par1World.spawnEntityInWorld(var11);
            }
        }
    }

    protected int getVillagerType(int par1)
    {
        return 0;
    }

    protected Block getBiomeSpecificBlock(Block par1, int par2)
    {
        return par1;
    }

    protected int getBiomeSpecificBlockMetadata(Block par1, int par2)
    {
        return par2;
    }

    @Override
    protected void placeBlockAtCurrentPosition(World par1World, Block par2, int par3, int par4, int par5, int par6, StructureBoundingBox par7StructureBoundingBox)
    {
        Block var8 = this.getBiomeSpecificBlock(par2, par3);
        int var9 = this.getBiomeSpecificBlockMetadata(par2, par3);
        super.placeBlockAtCurrentPosition(par1World, var8, var9, par4, par5, par6, par7StructureBoundingBox);
    }

    @Override
    protected void fillWithBlocks(World par1World, StructureBoundingBox par2StructureBoundingBox, int par3, int par4, int par5, int par6, int par7, int par8, Block par9, Block par10, boolean par11)
    {
        Block var12 = this.getBiomeSpecificBlock(par9, 0);
        int var13 = this.getBiomeSpecificBlockMetadata(par9, 0);
        Block var14 = this.getBiomeSpecificBlock(par10, 0);
        int var15 = this.getBiomeSpecificBlockMetadata(par10, 0);
        super.fillWithMetadataBlocks(par1World, par2StructureBoundingBox, par3, par4, par5, par6, par7, par8, var12, var13, var14, var15, par11);
    }

    @Override
    protected void func_151554_b(World par1World, Block par2, int par3, int par4, int par5, int par6, StructureBoundingBox par7StructureBoundingBox)
    {
        Block var8 = this.getBiomeSpecificBlock(par2, par3);
        int var9 = this.getBiomeSpecificBlockMetadata(par2, par3);
        super.func_151554_b(par1World, var8, var9, par4, par5, par6, par7StructureBoundingBox);
    }
}