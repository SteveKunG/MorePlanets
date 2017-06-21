package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.structure;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import micdoodle8.mods.galacticraft.core.GCBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemDoor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import stevekung.mods.moreplanets.init.MPBiomes;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.BlockHalfInfectedStoneBricksSlab;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.BlockNibiru;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.module.planets.nibiru.entity.EntityNibiruVillager;
import stevekung.mods.moreplanets.util.blocks.BlockChestMP;
import stevekung.mods.moreplanets.util.tileentity.TileEntityChestMP;
import stevekung.mods.moreplanets.util.world.gen.structure.StructureComponentMP;

@SuppressWarnings("incomplete-switch")
public class StructureNibiruVillagePieces
{
    private static Stones villageStones = new Stones();
    private static Planks villagePlanks = new Planks();
    private static Logs villageLogs = new Logs();
    private static Dirts villageDirts = new Dirts();

    public static void registerVillagePieces()
    {
        MapGenStructureIO.registerStructureComponent(StructureNibiruVillagePieces.House1.class, "NViBH");
        MapGenStructureIO.registerStructureComponent(StructureNibiruVillagePieces.Field1.class, "NViDF");
        MapGenStructureIO.registerStructureComponent(StructureNibiruVillagePieces.Field2.class, "NViF");
        MapGenStructureIO.registerStructureComponent(StructureNibiruVillagePieces.Torch.class, "NViL");
        MapGenStructureIO.registerStructureComponent(StructureNibiruVillagePieces.Hall.class, "NViPH");
        MapGenStructureIO.registerStructureComponent(StructureNibiruVillagePieces.House4Garden.class, "NViSH");
        MapGenStructureIO.registerStructureComponent(StructureNibiruVillagePieces.WoodHut.class, "NViSmH");
        MapGenStructureIO.registerStructureComponent(StructureNibiruVillagePieces.Church.class, "NViST");
        MapGenStructureIO.registerStructureComponent(StructureNibiruVillagePieces.House2.class, "NViS");
        MapGenStructureIO.registerStructureComponent(StructureNibiruVillagePieces.Start.class, "NViStart");
        MapGenStructureIO.registerStructureComponent(StructureNibiruVillagePieces.Path.class, "NViSR");
        MapGenStructureIO.registerStructureComponent(StructureNibiruVillagePieces.House3.class, "NViTRH");
        MapGenStructureIO.registerStructureComponent(StructureNibiruVillagePieces.Well.class, "NViW");
    }

    public static List<StructureNibiruVillagePieces.PieceWeight> getStructureVillageWeightedPieceList(Random random, int p_75084_1_)
    {
        List<StructureNibiruVillagePieces.PieceWeight> list = Lists.<StructureNibiruVillagePieces.PieceWeight>newArrayList();
        list.add(new StructureNibiruVillagePieces.PieceWeight(StructureNibiruVillagePieces.House4Garden.class, 4, MathHelper.getRandomIntegerInRange(random, 2 + p_75084_1_, 4 + p_75084_1_ * 2)));
        list.add(new StructureNibiruVillagePieces.PieceWeight(StructureNibiruVillagePieces.Church.class, 20, MathHelper.getRandomIntegerInRange(random, 0 + p_75084_1_, 1 + p_75084_1_)));
        list.add(new StructureNibiruVillagePieces.PieceWeight(StructureNibiruVillagePieces.House1.class, 20, MathHelper.getRandomIntegerInRange(random, 0 + p_75084_1_, 2 + p_75084_1_)));
        list.add(new StructureNibiruVillagePieces.PieceWeight(StructureNibiruVillagePieces.WoodHut.class, 3, MathHelper.getRandomIntegerInRange(random, 2 + p_75084_1_, 5 + p_75084_1_ * 3)));
        list.add(new StructureNibiruVillagePieces.PieceWeight(StructureNibiruVillagePieces.Hall.class, 15, MathHelper.getRandomIntegerInRange(random, 0 + p_75084_1_, 2 + p_75084_1_)));
        list.add(new StructureNibiruVillagePieces.PieceWeight(StructureNibiruVillagePieces.Field1.class, 3, MathHelper.getRandomIntegerInRange(random, 1 + p_75084_1_, 4 + p_75084_1_)));
        list.add(new StructureNibiruVillagePieces.PieceWeight(StructureNibiruVillagePieces.Field2.class, 3, MathHelper.getRandomIntegerInRange(random, 2 + p_75084_1_, 4 + p_75084_1_ * 2)));
        list.add(new StructureNibiruVillagePieces.PieceWeight(StructureNibiruVillagePieces.House2.class, 15, MathHelper.getRandomIntegerInRange(random, 0, 1 + p_75084_1_)));
        list.add(new StructureNibiruVillagePieces.PieceWeight(StructureNibiruVillagePieces.House3.class, 8, MathHelper.getRandomIntegerInRange(random, 0 + p_75084_1_, 3 + p_75084_1_ * 2)));
        Iterator<StructureNibiruVillagePieces.PieceWeight> iterator = list.iterator();

        while (iterator.hasNext())
        {
            if (iterator.next().villagePiecesLimit == 0)
            {
                iterator.remove();
            }
        }
        return list;
    }

    private static int func_75079_a(List<StructureNibiruVillagePieces.PieceWeight> p_75079_0_)
    {
        boolean flag = false;
        int i = 0;

        for (StructureNibiruVillagePieces.PieceWeight structurevillagepieces$pieceweight : p_75079_0_)
        {
            if (structurevillagepieces$pieceweight.villagePiecesLimit > 0 && structurevillagepieces$pieceweight.villagePiecesSpawned < structurevillagepieces$pieceweight.villagePiecesLimit)
            {
                flag = true;
            }
            i += structurevillagepieces$pieceweight.villagePieceWeight;
        }
        return flag ? i : -1;
    }

    private static StructureNibiruVillagePieces.Village func_176065_a(StructureNibiruVillagePieces.Start start, StructureNibiruVillagePieces.PieceWeight weight, List<StructureComponent> p_176065_2_, Random rand, int p_176065_4_, int p_176065_5_, int p_176065_6_, EnumFacing facing, int p_176065_8_)
    {
        Class <? extends StructureNibiruVillagePieces.Village > oclass = weight.villagePieceClass;
        StructureNibiruVillagePieces.Village structurevillagepieces$village = null;

        if (oclass == StructureNibiruVillagePieces.House4Garden.class)
        {
            structurevillagepieces$village = StructureNibiruVillagePieces.House4Garden.func_175858_a(start, p_176065_2_, rand, p_176065_4_, p_176065_5_, p_176065_6_, facing, p_176065_8_);
        }
        else if (oclass == StructureNibiruVillagePieces.Church.class)
        {
            structurevillagepieces$village = StructureNibiruVillagePieces.Church.func_175854_a(start, p_176065_2_, rand, p_176065_4_, p_176065_5_, p_176065_6_, facing, p_176065_8_);
        }
        else if (oclass == StructureNibiruVillagePieces.House1.class)
        {
            structurevillagepieces$village = StructureNibiruVillagePieces.House1.func_175850_a(start, p_176065_2_, rand, p_176065_4_, p_176065_5_, p_176065_6_, facing, p_176065_8_);
        }
        else if (oclass == StructureNibiruVillagePieces.WoodHut.class)
        {
            structurevillagepieces$village = StructureNibiruVillagePieces.WoodHut.func_175853_a(start, p_176065_2_, rand, p_176065_4_, p_176065_5_, p_176065_6_, facing, p_176065_8_);
        }
        else if (oclass == StructureNibiruVillagePieces.Hall.class)
        {
            structurevillagepieces$village = StructureNibiruVillagePieces.Hall.func_175857_a(start, p_176065_2_, rand, p_176065_4_, p_176065_5_, p_176065_6_, facing, p_176065_8_);
        }
        else if (oclass == StructureNibiruVillagePieces.Field1.class)
        {
            structurevillagepieces$village = StructureNibiruVillagePieces.Field1.func_175851_a(start, p_176065_2_, rand, p_176065_4_, p_176065_5_, p_176065_6_, facing, p_176065_8_);
        }
        else if (oclass == StructureNibiruVillagePieces.Field2.class)
        {
            structurevillagepieces$village = StructureNibiruVillagePieces.Field2.func_175852_a(start, p_176065_2_, rand, p_176065_4_, p_176065_5_, p_176065_6_, facing, p_176065_8_);
        }
        else if (oclass == StructureNibiruVillagePieces.House2.class)
        {
            structurevillagepieces$village = StructureNibiruVillagePieces.House2.func_175855_a(start, p_176065_2_, rand, p_176065_4_, p_176065_5_, p_176065_6_, facing, p_176065_8_);
        }
        else if (oclass == StructureNibiruVillagePieces.House3.class)
        {
            structurevillagepieces$village = StructureNibiruVillagePieces.House3.func_175849_a(start, p_176065_2_, rand, p_176065_4_, p_176065_5_, p_176065_6_, facing, p_176065_8_);
        }
        return structurevillagepieces$village;
    }

    private static StructureNibiruVillagePieces.Village func_176067_c(StructureNibiruVillagePieces.Start start, List<StructureComponent> p_176067_1_, Random rand, int p_176067_3_, int p_176067_4_, int p_176067_5_, EnumFacing facing, int p_176067_7_)
    {
        int i = func_75079_a(start.structureVillageWeightedPieceList);

        if (i <= 0)
        {
            return null;
        }
        else
        {
            int j = 0;

            while (j < 5)
            {
                ++j;
                int k = rand.nextInt(i);

                for (StructureNibiruVillagePieces.PieceWeight structurevillagepieces$pieceweight : start.structureVillageWeightedPieceList)
                {
                    k -= structurevillagepieces$pieceweight.villagePieceWeight;

                    if (k < 0)
                    {
                        if (!structurevillagepieces$pieceweight.canSpawnMoreVillagePiecesOfType(p_176067_7_) || structurevillagepieces$pieceweight == start.structVillagePieceWeight && start.structureVillageWeightedPieceList.size() > 1)
                        {
                            break;
                        }

                        StructureNibiruVillagePieces.Village structurevillagepieces$village = func_176065_a(start, structurevillagepieces$pieceweight, p_176067_1_, rand, p_176067_3_, p_176067_4_, p_176067_5_, facing, p_176067_7_);

                        if (structurevillagepieces$village != null)
                        {
                            ++structurevillagepieces$pieceweight.villagePiecesSpawned;
                            start.structVillagePieceWeight = structurevillagepieces$pieceweight;

                            if (!structurevillagepieces$pieceweight.canSpawnMoreVillagePieces())
                            {
                                start.structureVillageWeightedPieceList.remove(structurevillagepieces$pieceweight);
                            }
                            return structurevillagepieces$village;
                        }
                    }
                }
            }

            StructureBoundingBox structureboundingbox = StructureNibiruVillagePieces.Torch.func_175856_a(start, p_176067_1_, rand, p_176067_3_, p_176067_4_, p_176067_5_, facing);

            if (structureboundingbox != null)
            {
                return new StructureNibiruVillagePieces.Torch(start, p_176067_7_, rand, structureboundingbox, facing);
            }
            else
            {
                return null;
            }
        }
    }

    private static StructureComponent func_176066_d(StructureNibiruVillagePieces.Start start, List<StructureComponent> p_176066_1_, Random rand, int p_176066_3_, int p_176066_4_, int p_176066_5_, EnumFacing facing, int p_176066_7_)
    {
        if (p_176066_7_ > 50)
        {
            return null;
        }
        else if (Math.abs(p_176066_3_ - start.getBoundingBox().minX) <= 112 && Math.abs(p_176066_5_ - start.getBoundingBox().minZ) <= 112)
        {
            StructureComponent structurecomponent = func_176067_c(start, p_176066_1_, rand, p_176066_3_, p_176066_4_, p_176066_5_, facing, p_176066_7_ + 1);

            if (structurecomponent != null)
            {
                int i = (structurecomponent.getBoundingBox().minX + structurecomponent.getBoundingBox().maxX) / 2;
                int j = (structurecomponent.getBoundingBox().minZ + structurecomponent.getBoundingBox().maxZ) / 2;
                int k = structurecomponent.getBoundingBox().maxX - structurecomponent.getBoundingBox().minX;
                int l = structurecomponent.getBoundingBox().maxZ - structurecomponent.getBoundingBox().minZ;
                int i1 = k > l ? k : l;

                if (start.getWorldChunkManager().areBiomesViable(i, j, i1 / 2 + 4, MapGenNibiruVillage.villageSpawnBiomes))
                {
                    p_176066_1_.add(structurecomponent);
                    start.field_74932_i.add(structurecomponent);
                    return structurecomponent;
                }
            }
            return null;
        }
        else
        {
            return null;
        }
    }

    private static StructureComponent func_176069_e(StructureNibiruVillagePieces.Start start, List<StructureComponent> p_176069_1_, Random rand, int p_176069_3_, int p_176069_4_, int p_176069_5_, EnumFacing facing, int p_176069_7_)
    {
        if (p_176069_7_ > 3 + start.terrainType)
        {
            return null;
        }
        else if (Math.abs(p_176069_3_ - start.getBoundingBox().minX) <= 112 && Math.abs(p_176069_5_ - start.getBoundingBox().minZ) <= 112)
        {
            StructureBoundingBox structureboundingbox = StructureNibiruVillagePieces.Path.func_175848_a(start, p_176069_1_, rand, p_176069_3_, p_176069_4_, p_176069_5_, facing);

            if (structureboundingbox != null && structureboundingbox.minY > 10)
            {
                StructureComponent structurecomponent = new StructureNibiruVillagePieces.Path(start, p_176069_7_, rand, structureboundingbox, facing);
                int i = (structurecomponent.getBoundingBox().minX + structurecomponent.getBoundingBox().maxX) / 2;
                int j = (structurecomponent.getBoundingBox().minZ + structurecomponent.getBoundingBox().maxZ) / 2;
                int k = structurecomponent.getBoundingBox().maxX - structurecomponent.getBoundingBox().minX;
                int l = structurecomponent.getBoundingBox().maxZ - structurecomponent.getBoundingBox().minZ;
                int i1 = k > l ? k : l;

                if (start.getWorldChunkManager().areBiomesViable(i, j, i1 / 2 + 4, MapGenNibiruVillage.villageSpawnBiomes))
                {
                    p_176069_1_.add(structurecomponent);
                    start.field_74930_j.add(structurecomponent);
                    return structurecomponent;
                }
            }
            return null;
        }
        else
        {
            return null;
        }
    }

    public static class Church extends StructureNibiruVillagePieces.Village
    {
        public Church() {}

        public Church(StructureNibiruVillagePieces.Start start, int p_i45564_2_, Random rand, StructureBoundingBox p_i45564_4_, EnumFacing facing)
        {
            super(start, p_i45564_2_);
            this.coordBaseMode = facing;
            this.boundingBox = p_i45564_4_;
        }

        public static StructureNibiruVillagePieces.Church func_175854_a(StructureNibiruVillagePieces.Start start, List<StructureComponent> p_175854_1_, Random rand, int p_175854_3_, int p_175854_4_, int p_175854_5_, EnumFacing facing, int p_175854_7_)
        {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175854_3_, p_175854_4_, p_175854_5_, 0, 0, 0, 5, 12, 9, facing);
            return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_175854_1_, structureboundingbox) == null ? new StructureNibiruVillagePieces.Church(start, p_175854_7_, rand, structureboundingbox, facing) : null;
        }

        @Override
        public boolean addComponentParts(World world, Random rand, StructureBoundingBox box)
        {
            if (this.field_143015_k < 0)
            {
                this.field_143015_k = this.getAverageGroundLevel(world, box);

                if (this.field_143015_k < 0)
                {
                    return true;
                }
                this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + 12 - 1, 0);
            }

            this.fillWithBlocks(world, box, 1, 1, 1, 3, 3, 7, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.fillWithBlocks(world, box, 1, 5, 1, 3, 9, 3, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.fillWithRandomizedBlocks(world, box, 1, 0, 0, 3, 0, 8, false, rand, StructureNibiruVillagePieces.villageStones);
            this.fillWithRandomizedBlocks(world, box, 1, 1, 0, 3, 10, 0, false, rand, StructureNibiruVillagePieces.villageStones);
            this.fillWithRandomizedBlocks(world, box, 0, 1, 1, 0, 10, 3, false, rand, StructureNibiruVillagePieces.villageStones);
            this.fillWithRandomizedBlocks(world, box, 4, 1, 1, 4, 10, 3, false, rand, StructureNibiruVillagePieces.villageStones);
            this.fillWithRandomizedBlocks(world, box, 0, 0, 4, 0, 4, 7, false, rand, StructureNibiruVillagePieces.villageStones);
            this.fillWithRandomizedBlocks(world, box, 4, 0, 4, 4, 4, 7, false, rand, StructureNibiruVillagePieces.villageStones);
            this.fillWithRandomizedBlocks(world, box, 1, 1, 8, 3, 4, 8, false, rand, StructureNibiruVillagePieces.villageStones);
            this.fillWithRandomizedBlocks(world, box, 1, 5, 4, 3, 10, 4, false, rand, StructureNibiruVillagePieces.villageStones);
            this.fillWithRandomizedBlocks(world, box, 1, 5, 5, 3, 5, 7, false, rand, StructureNibiruVillagePieces.villageStones);
            this.fillWithRandomizedBlocks(world, box, 0, 9, 0, 4, 9, 4, false, rand, StructureNibiruVillagePieces.villageStones);
            this.fillWithRandomizedBlocks(world, box, 0, 4, 0, 4, 4, 4, false, rand, StructureNibiruVillagePieces.villageStones);
            this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getStateFromMeta(1), 0, 11, 2, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getStateFromMeta(1), 4, 11, 2, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getStateFromMeta(1), 2, 11, 0, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getStateFromMeta(1), 2, 11, 4, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getStateFromMeta(1), 1, 1, 6, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getStateFromMeta(1), 1, 1, 7, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getStateFromMeta(1), 2, 1, 7, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getStateFromMeta(1), 3, 1, 6, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getStateFromMeta(1), 3, 1, 7, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_COBBLESTONE_STAIRS.getStateFromMeta(this.getMetadataWithOffset(NibiruBlocks.NIBIRU_COBBLESTONE_STAIRS, 3)), 1, 1, 5, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_COBBLESTONE_STAIRS.getStateFromMeta(this.getMetadataWithOffset(NibiruBlocks.NIBIRU_COBBLESTONE_STAIRS, 3)), 2, 1, 6, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_COBBLESTONE_STAIRS.getStateFromMeta(this.getMetadataWithOffset(NibiruBlocks.NIBIRU_COBBLESTONE_STAIRS, 3)), 3, 1, 5, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_COBBLESTONE_STAIRS.getStateFromMeta(this.getMetadataWithOffset(NibiruBlocks.NIBIRU_COBBLESTONE_STAIRS, 1)), 1, 2, 7, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_COBBLESTONE_STAIRS.getStateFromMeta(this.getMetadataWithOffset(NibiruBlocks.NIBIRU_COBBLESTONE_STAIRS, 0)), 3, 2, 7, box);
            this.setBlockState(world, rand.nextInt(5) == 0 ? Blocks.AIR.getDefaultState() : Blocks.GLASS_PANE.getDefaultState(), 0, 2, 2, box);
            this.setBlockState(world, rand.nextInt(5) == 0 ? Blocks.AIR.getDefaultState() : Blocks.GLASS_PANE.getDefaultState(), 0, 3, 2, box);
            this.setBlockState(world, rand.nextInt(5) == 0 ? Blocks.AIR.getDefaultState() : Blocks.GLASS_PANE.getDefaultState(), 4, 2, 2, box);
            this.setBlockState(world, rand.nextInt(5) == 0 ? Blocks.AIR.getDefaultState() : Blocks.GLASS_PANE.getDefaultState(), 4, 3, 2, box);
            this.setBlockState(world, rand.nextInt(5) == 0 ? Blocks.AIR.getDefaultState() : Blocks.GLASS_PANE.getDefaultState(), 0, 6, 2, box);
            this.setBlockState(world, rand.nextInt(5) == 0 ? Blocks.AIR.getDefaultState() : Blocks.GLASS_PANE.getDefaultState(), 0, 7, 2, box);
            this.setBlockState(world, rand.nextInt(5) == 0 ? Blocks.AIR.getDefaultState() : Blocks.GLASS_PANE.getDefaultState(), 4, 6, 2, box);
            this.setBlockState(world, rand.nextInt(5) == 0 ? Blocks.AIR.getDefaultState() : Blocks.GLASS_PANE.getDefaultState(), 4, 7, 2, box);
            this.setBlockState(world, rand.nextInt(5) == 0 ? Blocks.AIR.getDefaultState() : Blocks.GLASS_PANE.getDefaultState(), 2, 6, 0, box);
            this.setBlockState(world, rand.nextInt(5) == 0 ? Blocks.AIR.getDefaultState() : Blocks.GLASS_PANE.getDefaultState(), 2, 7, 0, box);
            this.setBlockState(world, rand.nextInt(5) == 0 ? Blocks.AIR.getDefaultState() : Blocks.GLASS_PANE.getDefaultState(), 2, 6, 4, box);
            this.setBlockState(world, rand.nextInt(5) == 0 ? Blocks.AIR.getDefaultState() : Blocks.GLASS_PANE.getDefaultState(), 2, 7, 4, box);
            this.setBlockState(world, rand.nextInt(5) == 0 ? Blocks.AIR.getDefaultState() : Blocks.GLASS_PANE.getDefaultState(), 0, 3, 6, box);
            this.setBlockState(world, rand.nextInt(5) == 0 ? Blocks.AIR.getDefaultState() : Blocks.GLASS_PANE.getDefaultState(), 4, 3, 6, box);
            this.setBlockState(world, rand.nextInt(5) == 0 ? Blocks.AIR.getDefaultState() : Blocks.GLASS_PANE.getDefaultState(), 2, 3, 8, box);
            Block torch = this.isGreenVeinVillage ? GCBlocks.glowstoneTorch : NibiruBlocks.INFECTED_TORCH;
            this.setBlockState(world, torch.getDefaultState().withProperty(BlockTorch.FACING, this.coordBaseMode.getOpposite()), 2, 4, 7, box);
            this.setBlockState(world, torch.getDefaultState().withProperty(BlockTorch.FACING, this.coordBaseMode.rotateY()), 1, 4, 6, box);
            this.setBlockState(world, torch.getDefaultState().withProperty(BlockTorch.FACING, this.coordBaseMode.rotateYCCW()), 3, 4, 6, box);
            this.setBlockState(world, torch.getDefaultState().withProperty(BlockTorch.FACING, this.coordBaseMode), 2, 4, 5, box);
            int i = this.getMetadataWithOffset(Blocks.ladder, 4);

            for (int j = 1; j <= 9; ++j)
            {
                this.setBlockState(world, Blocks.ladder.getStateFromMeta(i), 3, j, 3, box);
            }

            this.setBlockState(world, Blocks.AIR.getDefaultState(), 2, 1, 0, box);
            this.setBlockState(world, Blocks.AIR.getDefaultState(), 2, 2, 0, box);
            this.placeDoorCurrentPosition(world, box, rand, 2, 1, 0, EnumFacing.getHorizontal(this.getMetadataWithOffset(NibiruBlocks.INFECTED_DOOR_BLOCK, 1)));

            if (this.getBlockStateFromPos(world, 2, 0, -1, box).getBlock().getMaterial() == Material.AIR && this.getBlockStateFromPos(world, 2, -1, -1, box).getBlock().getMaterial() != Material.AIR)
            {
                this.setBlockState(world, NibiruBlocks.NIBIRU_COBBLESTONE_STAIRS.getStateFromMeta(this.getMetadataWithOffset(NibiruBlocks.NIBIRU_COBBLESTONE_STAIRS, 3)), 2, 0, -1, box);
            }

            for (int l = 0; l < 9; ++l)
            {
                for (int k = 0; k < 5; ++k)
                {
                    this.clearCurrentPositionBlocksUpwards(world, k, 12, l, box);
                    this.replaceAirAndLiquidDownwards(world, NibiruBlocks.NIBIRU_BLOCK.getStateFromMeta(1), k, -1, l, box);
                }
            }
            if (rand.nextInt(3) == 0)
            {
                this.spawnVillagers(world, box, 2, 1, 2, 1);
            }
            return true;
        }

        @Override
        protected int chooseProfession(int spawnCount, int profession)
        {
            return this.isGreenVeinVillage ? 0 : 3;
        }
    }

    public static class Field1 extends StructureNibiruVillagePieces.Village
    {
        private Block cropTypeA;
        private Block cropTypeB;
        private Block cropTypeC;
        private Block cropTypeD;

        public Field1() {}

        public Field1(StructureNibiruVillagePieces.Start start, int p_i45570_2_, Random rand, StructureBoundingBox p_i45570_4_, EnumFacing facing)
        {
            super(start, p_i45570_2_);
            this.coordBaseMode = facing;
            this.boundingBox = p_i45570_4_;
            this.cropTypeA = this.func_151559_a(rand);
            this.cropTypeB = this.func_151559_a(rand);
            this.cropTypeC = this.func_151559_a(rand);
            this.cropTypeD = this.func_151559_a(rand);
        }

        @Override
        protected void writeStructureToNBT(NBTTagCompound tagCompound)
        {
            super.writeStructureToNBT(tagCompound);
            tagCompound.setInteger("CA", Block.blockRegistry.getIDForObject(this.cropTypeA));
            tagCompound.setInteger("CB", Block.blockRegistry.getIDForObject(this.cropTypeB));
            tagCompound.setInteger("CC", Block.blockRegistry.getIDForObject(this.cropTypeC));
            tagCompound.setInteger("CD", Block.blockRegistry.getIDForObject(this.cropTypeD));
        }

        @Override
        protected void readStructureFromNBT(NBTTagCompound tagCompound)
        {
            super.readStructureFromNBT(tagCompound);
            this.cropTypeA = Block.getBlockById(tagCompound.getInteger("CA"));
            this.cropTypeB = Block.getBlockById(tagCompound.getInteger("CB"));
            this.cropTypeC = Block.getBlockById(tagCompound.getInteger("CC"));
            this.cropTypeD = Block.getBlockById(tagCompound.getInteger("CD"));
        }

        private Block func_151559_a(Random rand)
        {
            switch (rand.nextInt(5))
            {
            default:
                return this.isGreenVeinVillage ? NibiruBlocks.TERRABERRY_BLOCK : NibiruBlocks.INFECTED_WHEAT_BLOCK;
            }
        }

        public static StructureNibiruVillagePieces.Field1 func_175851_a(StructureNibiruVillagePieces.Start start, List<StructureComponent> p_175851_1_, Random rand, int p_175851_3_, int p_175851_4_, int p_175851_5_, EnumFacing facing, int p_175851_7_)
        {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175851_3_, p_175851_4_, p_175851_5_, 0, 0, 0, 13, 4, 9, facing);
            return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_175851_1_, structureboundingbox) == null ? new StructureNibiruVillagePieces.Field1(start, p_175851_7_, rand, structureboundingbox, facing) : null;
        }

        @Override
        public boolean addComponentParts(World world, Random rand, StructureBoundingBox box)
        {
            if (this.field_143015_k < 0)
            {
                this.field_143015_k = this.getAverageGroundLevel(world, box);

                if (this.field_143015_k < 0)
                {
                    return true;
                }
                this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + 4 - 1, 0);
            }

            this.fillWithBlocks(world, box, 0, 1, 0, 12, 4, 8, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.fillWithBlocks(world, box, 1, 0, 1, 2, 0, 7, NibiruBlocks.INFECTED_FARMLAND.getDefaultState(), NibiruBlocks.INFECTED_FARMLAND.getDefaultState(), false);
            this.fillWithBlocks(world, box, 4, 0, 1, 5, 0, 7, NibiruBlocks.INFECTED_FARMLAND.getDefaultState(), NibiruBlocks.INFECTED_FARMLAND.getDefaultState(), false);
            this.fillWithBlocks(world, box, 7, 0, 1, 8, 0, 7, NibiruBlocks.INFECTED_FARMLAND.getDefaultState(), NibiruBlocks.INFECTED_FARMLAND.getDefaultState(), false);
            this.fillWithBlocks(world, box, 10, 0, 1, 11, 0, 7, NibiruBlocks.INFECTED_FARMLAND.getDefaultState(), NibiruBlocks.INFECTED_FARMLAND.getDefaultState(), false);
            this.fillWithRandomizedBlocks(world, box, 0, 0, 0, 0, 0, 8, false, rand, villageLogs);
            this.fillWithRandomizedBlocks(world, box, 6, 0, 0, 6, 0, 8, false, rand, villageLogs);
            this.fillWithRandomizedBlocks(world, box, 12, 0, 0, 12, 0, 8, false, rand, villageLogs);
            this.fillWithRandomizedBlocks(world, box, 1, 0, 0, 11, 0, 0, false, rand, villageLogs);
            this.fillWithRandomizedBlocks(world, box, 1, 0, 8, 11, 0, 8, false, rand, villageLogs);
            this.fillWithBlocks(world, box, 3, 0, 1, 3, 0, 7, NibiruBlocks.INFECTED_WATER_FLUID_BLOCK.getDefaultState(), NibiruBlocks.INFECTED_WATER_FLUID_BLOCK.getDefaultState(), false);
            this.fillWithBlocks(world, box, 9, 0, 1, 9, 0, 7, NibiruBlocks.INFECTED_WATER_FLUID_BLOCK.getDefaultState(), NibiruBlocks.INFECTED_WATER_FLUID_BLOCK.getDefaultState(), false);

            for (int i = 1; i <= 7; ++i)
            {
                this.setBlockState(world, this.cropTypeA.getStateFromMeta(MathHelper.getRandomIntegerInRange(rand, 2, 7)), 1, 1, i, box);
                this.setBlockState(world, this.cropTypeA.getStateFromMeta(MathHelper.getRandomIntegerInRange(rand, 2, 7)), 2, 1, i, box);
                this.setBlockState(world, this.cropTypeB.getStateFromMeta(MathHelper.getRandomIntegerInRange(rand, 2, 7)), 4, 1, i, box);
                this.setBlockState(world, this.cropTypeB.getStateFromMeta(MathHelper.getRandomIntegerInRange(rand, 2, 7)), 5, 1, i, box);
                this.setBlockState(world, this.cropTypeC.getStateFromMeta(MathHelper.getRandomIntegerInRange(rand, 2, 7)), 7, 1, i, box);
                this.setBlockState(world, this.cropTypeC.getStateFromMeta(MathHelper.getRandomIntegerInRange(rand, 2, 7)), 8, 1, i, box);
                this.setBlockState(world, this.cropTypeD.getStateFromMeta(MathHelper.getRandomIntegerInRange(rand, 2, 7)), 10, 1, i, box);
                this.setBlockState(world, this.cropTypeD.getStateFromMeta(MathHelper.getRandomIntegerInRange(rand, 2, 7)), 11, 1, i, box);
            }

            for (int k = 0; k < 9; ++k)
            {
                for (int j = 0; j < 13; ++j)
                {
                    this.clearCurrentPositionBlocksUpwards(world, j, 4, k, box);
                    this.replaceAirAndLiquidDownwards(world, NibiruBlocks.INFECTED_DIRT.getDefaultState(), j, -1, k, box);
                }
            }
            return true;
        }
    }

    public static class Field2 extends StructureNibiruVillagePieces.Village
    {
        private Block cropTypeA;
        private Block cropTypeB;

        public Field2() {}

        public Field2(StructureNibiruVillagePieces.Start start, int p_i45569_2_, Random rand, StructureBoundingBox p_i45569_4_, EnumFacing facing)
        {
            super(start, p_i45569_2_);
            this.coordBaseMode = facing;
            this.boundingBox = p_i45569_4_;
            this.cropTypeA = this.func_151560_a(rand);
            this.cropTypeB = this.func_151560_a(rand);
        }

        @Override
        protected void writeStructureToNBT(NBTTagCompound tagCompound)
        {
            super.writeStructureToNBT(tagCompound);
            tagCompound.setInteger("CA", Block.blockRegistry.getIDForObject(this.cropTypeA));
            tagCompound.setInteger("CB", Block.blockRegistry.getIDForObject(this.cropTypeB));
        }

        @Override
        protected void readStructureFromNBT(NBTTagCompound tagCompound)
        {
            super.readStructureFromNBT(tagCompound);
            this.cropTypeA = Block.getBlockById(tagCompound.getInteger("CA"));
            this.cropTypeB = Block.getBlockById(tagCompound.getInteger("CB"));
        }

        private Block func_151560_a(Random rand)
        {
            switch (rand.nextInt(5))
            {
            default:
                return this.isGreenVeinVillage ? NibiruBlocks.TERRABERRY_BLOCK : NibiruBlocks.INFECTED_WHEAT_BLOCK;
            }
        }

        public static StructureNibiruVillagePieces.Field2 func_175852_a(StructureNibiruVillagePieces.Start start, List<StructureComponent> p_175852_1_, Random rand, int p_175852_3_, int p_175852_4_, int p_175852_5_, EnumFacing facing, int p_175852_7_)
        {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175852_3_, p_175852_4_, p_175852_5_, 0, 0, 0, 7, 4, 9, facing);
            return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_175852_1_, structureboundingbox) == null ? new StructureNibiruVillagePieces.Field2(start, p_175852_7_, rand, structureboundingbox, facing) : null;
        }

        @Override
        public boolean addComponentParts(World world, Random rand, StructureBoundingBox box)
        {
            if (this.field_143015_k < 0)
            {
                this.field_143015_k = this.getAverageGroundLevel(world, box);

                if (this.field_143015_k < 0)
                {
                    return true;
                }
                this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + 4 - 1, 0);
            }

            this.fillWithBlocks(world, box, 0, 1, 0, 6, 4, 8, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.fillWithBlocks(world, box, 1, 0, 1, 2, 0, 7, NibiruBlocks.INFECTED_FARMLAND.getDefaultState(), NibiruBlocks.INFECTED_FARMLAND.getDefaultState(), false);
            this.fillWithBlocks(world, box, 4, 0, 1, 5, 0, 7, NibiruBlocks.INFECTED_FARMLAND.getDefaultState(), NibiruBlocks.INFECTED_FARMLAND.getDefaultState(), false);
            this.fillWithRandomizedBlocks(world, box, 0, 0, 0, 0, 0, 8, false, rand, villageLogs);
            this.fillWithRandomizedBlocks(world, box, 6, 0, 0, 6, 0, 8, false, rand, villageLogs);
            this.fillWithRandomizedBlocks(world, box, 1, 0, 0, 5, 0, 0, false, rand, villageLogs);
            this.fillWithRandomizedBlocks(world, box, 1, 0, 8, 5, 0, 8, false, rand, villageLogs);
            this.fillWithBlocks(world, box, 3, 0, 1, 3, 0, 7, NibiruBlocks.INFECTED_WATER_FLUID_BLOCK.getDefaultState(), NibiruBlocks.INFECTED_WATER_FLUID_BLOCK.getDefaultState(), false);

            for (int i = 1; i <= 7; ++i)
            {
                this.setBlockState(world, this.cropTypeA.getStateFromMeta(MathHelper.getRandomIntegerInRange(rand, 2, 7)), 1, 1, i, box);
                this.setBlockState(world, this.cropTypeA.getStateFromMeta(MathHelper.getRandomIntegerInRange(rand, 2, 7)), 2, 1, i, box);
                this.setBlockState(world, this.cropTypeB.getStateFromMeta(MathHelper.getRandomIntegerInRange(rand, 2, 7)), 4, 1, i, box);
                this.setBlockState(world, this.cropTypeB.getStateFromMeta(MathHelper.getRandomIntegerInRange(rand, 2, 7)), 5, 1, i, box);
            }

            for (int k = 0; k < 9; ++k)
            {
                for (int j = 0; j < 7; ++j)
                {
                    this.clearCurrentPositionBlocksUpwards(world, j, 4, k, box);
                    this.replaceAirAndLiquidDownwards(world, NibiruBlocks.INFECTED_DIRT.getDefaultState(), j, -1, k, box);
                }
            }
            return true;
        }
    }

    public static class Hall extends StructureNibiruVillagePieces.Village
    {
        public Hall() {}

        public Hall(StructureNibiruVillagePieces.Start start, int p_i45567_2_, Random rand, StructureBoundingBox p_i45567_4_, EnumFacing facing)
        {
            super(start, p_i45567_2_);
            this.coordBaseMode = facing;
            this.boundingBox = p_i45567_4_;
        }

        public static StructureNibiruVillagePieces.Hall func_175857_a(StructureNibiruVillagePieces.Start start, List<StructureComponent> p_175857_1_, Random rand, int p_175857_3_, int p_175857_4_, int p_175857_5_, EnumFacing facing, int p_175857_7_)
        {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175857_3_, p_175857_4_, p_175857_5_, 0, 0, 0, 9, 7, 11, facing);
            return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_175857_1_, structureboundingbox) == null ? new StructureNibiruVillagePieces.Hall(start, p_175857_7_, rand, structureboundingbox, facing) : null;
        }

        @Override
        public boolean addComponentParts(World world, Random rand, StructureBoundingBox box)
        {
            if (this.field_143015_k < 0)
            {
                this.field_143015_k = this.getAverageGroundLevel(world, box);

                if (this.field_143015_k < 0)
                {
                    return true;
                }
                this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + 7 - 1, 0);
            }

            this.fillWithBlocks(world, box, 1, 1, 1, 7, 4, 4, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.fillWithBlocks(world, box, 2, 1, 6, 8, 4, 10, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.fillWithRandomizedBlocks(world, box, 2, 0, 6, 8, 0, 10, false, rand, villageDirts);
            this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getStateFromMeta(1), 6, 0, 6, box);
            this.fillWithBlocks(world, box, 2, 1, 6, 2, 1, 10, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), NibiruBlocks.NIBIRU_FENCE.getDefaultState(), false);
            this.fillWithBlocks(world, box, 8, 1, 6, 8, 1, 10, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), NibiruBlocks.NIBIRU_FENCE.getDefaultState(), false);
            this.fillWithBlocks(world, box, 3, 1, 10, 7, 1, 10, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), NibiruBlocks.NIBIRU_FENCE.getDefaultState(), false);
            this.fillWithRandomizedBlocks(world, box, 1, 0, 1, 7, 0, 4, false, rand, villagePlanks);
            this.fillWithRandomizedBlocks(world, box, 0, 0, 0, 0, 3, 5, false, rand, villageStones);
            this.fillWithRandomizedBlocks(world, box, 8, 0, 0, 8, 3, 5, false, rand, villageStones);
            this.fillWithRandomizedBlocks(world, box, 1, 0, 0, 7, 1, 0, false, rand, villageStones);
            this.fillWithRandomizedBlocks(world, box, 1, 0, 5, 7, 1, 5, false, rand, villageStones);
            this.fillWithRandomizedBlocks(world, box, 1, 2, 0, 7, 3, 0, false, rand, villagePlanks);
            this.fillWithRandomizedBlocks(world, box, 1, 2, 5, 7, 3, 5, false, rand, villagePlanks);
            this.fillWithRandomizedBlocks(world, box, 0, 4, 1, 8, 4, 1, false, rand, villagePlanks);
            this.fillWithRandomizedBlocks(world, box, 0, 4, 4, 8, 4, 4, false, rand, villagePlanks);
            this.fillWithRandomizedBlocks(world, box, 0, 5, 2, 8, 5, 3, false, rand, villagePlanks);
            this.setBlockState(world, NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), 0, 4, 2, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), 0, 4, 3, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), 8, 4, 2, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), 8, 4, 3, box);
            int i = this.getMetadataWithOffset(NibiruBlocks.INFECTED_OAK_STAIRS, 3);
            int j = this.getMetadataWithOffset(NibiruBlocks.INFECTED_OAK_STAIRS, 2);

            for (int k = -1; k <= 2; ++k)
            {
                for (int l = 0; l <= 8; ++l)
                {
                    this.setBlockState(world, NibiruBlocks.INFECTED_OAK_STAIRS.getStateFromMeta(i), l, 4 + k, k, box);
                    this.setBlockState(world, NibiruBlocks.INFECTED_OAK_STAIRS.getStateFromMeta(j), l, 4 + k, 5 - k, box);
                }
            }

            this.setBlockState(world, NibiruBlocks.NIBIRU_LOG.getDefaultState(), 0, 2, 1, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_LOG.getDefaultState(), 0, 2, 4, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_LOG.getDefaultState(), 8, 2, 1, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_LOG.getDefaultState(), 8, 2, 4, box);
            this.setBlockState(world, rand.nextInt(5) == 0 ? Blocks.AIR.getDefaultState() : Blocks.GLASS_PANE.getDefaultState(), 0, 2, 2, box);
            this.setBlockState(world, rand.nextInt(5) == 0 ? Blocks.AIR.getDefaultState() : Blocks.GLASS_PANE.getDefaultState(), 0, 2, 3, box);
            this.setBlockState(world, rand.nextInt(5) == 0 ? Blocks.AIR.getDefaultState() : Blocks.GLASS_PANE.getDefaultState(), 8, 2, 2, box);
            this.setBlockState(world, rand.nextInt(5) == 0 ? Blocks.AIR.getDefaultState() : Blocks.GLASS_PANE.getDefaultState(), 8, 2, 3, box);
            this.setBlockState(world, rand.nextInt(5) == 0 ? Blocks.AIR.getDefaultState() : Blocks.GLASS_PANE.getDefaultState(), 2, 2, 5, box);
            this.setBlockState(world, rand.nextInt(5) == 0 ? Blocks.AIR.getDefaultState() : Blocks.GLASS_PANE.getDefaultState(), 3, 2, 5, box);
            this.setBlockState(world, rand.nextInt(5) == 0 ? Blocks.AIR.getDefaultState() : Blocks.GLASS_PANE.getDefaultState(), 5, 2, 0, box);
            this.setBlockState(world, rand.nextInt(5) == 0 ? Blocks.AIR.getDefaultState() : Blocks.GLASS_PANE.getDefaultState(), 6, 2, 5, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), 2, 1, 3, box);
            this.setBlockState(world, Blocks.WOODEN_PRESSURE_PLATE.getDefaultState(), 2, 2, 3, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), 1, 1, 4, box);
            this.setBlockState(world, NibiruBlocks.INFECTED_OAK_STAIRS.getStateFromMeta(this.getMetadataWithOffset(NibiruBlocks.INFECTED_OAK_STAIRS, 3)), 2, 1, 4, box);
            this.setBlockState(world, NibiruBlocks.INFECTED_OAK_STAIRS.getStateFromMeta(this.getMetadataWithOffset(NibiruBlocks.INFECTED_OAK_STAIRS, 1)), 1, 1, 3, box);
            this.fillWithBlocks(world, box, 5, 0, 1, 7, 0, 3, NibiruBlocks.DOUBLE_INFECTED_STONE_BRICKS_SLAB.getDefaultState(), NibiruBlocks.DOUBLE_INFECTED_STONE_BRICKS_SLAB.getDefaultState(), false);
            this.setBlockState(world, NibiruBlocks.DOUBLE_INFECTED_STONE_BRICKS_SLAB.getDefaultState(), 6, 1, 1, box);
            this.setBlockState(world, NibiruBlocks.DOUBLE_INFECTED_STONE_BRICKS_SLAB.getDefaultState(), 6, 1, 2, box);
            this.setBlockState(world, Blocks.AIR.getDefaultState(), 2, 1, 0, box);
            this.setBlockState(world, Blocks.AIR.getDefaultState(), 2, 2, 0, box);
            Block torch = this.isGreenVeinVillage ? GCBlocks.glowstoneTorch : NibiruBlocks.INFECTED_TORCH;
            this.setBlockState(world, torch.getDefaultState().withProperty(BlockTorch.FACING, this.coordBaseMode), 2, 3, 1, box);
            this.placeDoorCurrentPosition(world, box, rand, 2, 1, 0, EnumFacing.getHorizontal(this.getMetadataWithOffset(NibiruBlocks.INFECTED_DOOR_BLOCK, 1)));

            if (this.getBlockStateFromPos(world, 2, 0, -1, box).getBlock().getMaterial() == Material.AIR && this.getBlockStateFromPos(world, 2, -1, -1, box).getBlock().getMaterial() != Material.AIR)
            {
                this.setBlockState(world, NibiruBlocks.NIBIRU_COBBLESTONE_STAIRS.getStateFromMeta(this.getMetadataWithOffset(NibiruBlocks.NIBIRU_COBBLESTONE_STAIRS, 3)), 2, 0, -1, box);
            }

            this.setBlockState(world, Blocks.AIR.getDefaultState(), 6, 1, 5, box);
            this.setBlockState(world, Blocks.AIR.getDefaultState(), 6, 2, 5, box);
            this.setBlockState(world, torch.getDefaultState().withProperty(BlockTorch.FACING, this.coordBaseMode.getOpposite()), 6, 3, 4, box);
            this.placeDoorCurrentPosition(world, box, rand, 6, 1, 5, EnumFacing.getHorizontal(this.getMetadataWithOffset(NibiruBlocks.INFECTED_DOOR_BLOCK, 1)));

            for (int i1 = 0; i1 < 5; ++i1)
            {
                for (int j1 = 0; j1 < 9; ++j1)
                {
                    this.clearCurrentPositionBlocksUpwards(world, j1, 7, i1, box);
                    this.replaceAirAndLiquidDownwards(world, NibiruBlocks.NIBIRU_BLOCK.getStateFromMeta(1), j1, -1, i1, box);
                }
            }
            if (rand.nextInt(3) == 0)
            {
                this.spawnVillagers(world, box, 4, 1, 2, 2);
            }
            return true;
        }

        @Override
        protected int chooseProfession(int spawnCount, int profession)
        {
            return spawnCount == 0 ? this.isGreenVeinVillage ? 0 : 3 : super.chooseProfession(spawnCount, profession);
        }
    }

    public static class House1 extends StructureNibiruVillagePieces.Village
    {
        public House1() {}

        static
        {
            ItemLootHelper.register(ItemLootHelper.NIBIRU_VILLAGE_LIBRARY, ItemLootHelper.NIBIRU_VILLAGE_LIBRARY_LOOT, 4, 8);
            ItemLootHelper.add(ItemLootHelper.NIBIRU_VILLAGE_LIBRARY, ItemLootHelper.ENCHANTED_BOOK);
        }

        public House1(StructureNibiruVillagePieces.Start start, int p_i45571_2_, Random rand, StructureBoundingBox p_i45571_4_, EnumFacing facing)
        {
            super(start, p_i45571_2_);
            this.coordBaseMode = facing;
            this.boundingBox = p_i45571_4_;
        }

        public static StructureNibiruVillagePieces.House1 func_175850_a(StructureNibiruVillagePieces.Start start, List<StructureComponent> p_175850_1_, Random rand, int p_175850_3_, int p_175850_4_, int p_175850_5_, EnumFacing facing, int p_175850_7_)
        {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175850_3_, p_175850_4_, p_175850_5_, 0, 0, 0, 9, 9, 6, facing);
            return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_175850_1_, structureboundingbox) == null ? new StructureNibiruVillagePieces.House1(start, p_175850_7_, rand, structureboundingbox, facing) : null;
        }

        @Override
        public boolean addComponentParts(World world, Random rand, StructureBoundingBox box)
        {
            if (this.field_143015_k < 0)
            {
                this.field_143015_k = this.getAverageGroundLevel(world, box);

                if (this.field_143015_k < 0)
                {
                    return true;
                }
                this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + 9 - 1, 0);
            }

            this.fillWithBlocks(world, box, 1, 1, 1, 7, 5, 4, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.fillWithRandomizedBlocks(world, box, 0, 0, 0, 8, 0, 5, false, rand, villageStones);
            this.fillWithRandomizedBlocks(world, box, 0, 5, 0, 8, 5, 5, false, rand, villageStones);
            this.fillWithRandomizedBlocks(world, box, 0, 6, 1, 8, 6, 4, false, rand, villageStones);
            this.fillWithRandomizedBlocks(world, box, 0, 7, 2, 8, 7, 3, false, rand, villageStones);
            int i = this.getMetadataWithOffset(NibiruBlocks.INFECTED_OAK_STAIRS, 3);
            int j = this.getMetadataWithOffset(NibiruBlocks.INFECTED_OAK_STAIRS, 2);

            for (int k = -1; k <= 2; ++k)
            {
                for (int l = 0; l <= 8; ++l)
                {
                    this.setBlockState(world, NibiruBlocks.INFECTED_OAK_STAIRS.getStateFromMeta(i), l, 6 + k, k, box);
                    this.setBlockState(world, NibiruBlocks.INFECTED_OAK_STAIRS.getStateFromMeta(j), l, 6 + k, 5 - k, box);
                }
            }

            this.fillWithRandomizedBlocks(world, box, 0, 1, 0, 0, 1, 5, false, rand, villageStones);
            this.fillWithRandomizedBlocks(world, box, 1, 1, 5, 8, 1, 5, false, rand, villageStones);
            this.fillWithRandomizedBlocks(world, box, 8, 1, 0, 8, 1, 4, false, rand, villageStones);
            this.fillWithRandomizedBlocks(world, box, 2, 1, 0, 7, 1, 0, false, rand, villageStones);
            this.fillWithRandomizedBlocks(world, box, 0, 2, 0, 0, 4, 0, false, rand, villageStones);
            this.fillWithRandomizedBlocks(world, box, 0, 2, 5, 0, 4, 5, false, rand, villageStones);
            this.fillWithRandomizedBlocks(world, box, 8, 2, 5, 8, 4, 5, false, rand, villageStones);
            this.fillWithRandomizedBlocks(world, box, 8, 2, 0, 8, 4, 0, false, rand, villageStones);
            this.fillWithRandomizedBlocks(world, box, 0, 2, 1, 0, 4, 4, false, rand, villagePlanks);
            this.fillWithRandomizedBlocks(world, box, 1, 2, 5, 7, 4, 5, false, rand, villagePlanks);
            this.fillWithRandomizedBlocks(world, box, 8, 2, 1, 8, 4, 4, false, rand, villagePlanks);
            this.fillWithRandomizedBlocks(world, box, 1, 2, 0, 7, 4, 0, false, rand, villagePlanks);
            this.setBlockState(world, rand.nextInt(5) == 0 ? Blocks.AIR.getDefaultState() : Blocks.GLASS_PANE.getDefaultState(), 4, 2, 0, box);
            this.setBlockState(world, rand.nextInt(5) == 0 ? Blocks.AIR.getDefaultState() : Blocks.GLASS_PANE.getDefaultState(), 5, 2, 0, box);
            this.setBlockState(world, rand.nextInt(5) == 0 ? Blocks.AIR.getDefaultState() : Blocks.GLASS_PANE.getDefaultState(), 6, 2, 0, box);
            this.setBlockState(world, rand.nextInt(5) == 0 ? Blocks.AIR.getDefaultState() : Blocks.GLASS_PANE.getDefaultState(), 4, 3, 0, box);
            this.setBlockState(world, rand.nextInt(5) == 0 ? Blocks.AIR.getDefaultState() : Blocks.GLASS_PANE.getDefaultState(), 5, 3, 0, box);
            this.setBlockState(world, rand.nextInt(5) == 0 ? Blocks.AIR.getDefaultState() : Blocks.GLASS_PANE.getDefaultState(), 6, 3, 0, box);
            this.setBlockState(world, rand.nextInt(5) == 0 ? Blocks.AIR.getDefaultState() : Blocks.GLASS_PANE.getDefaultState(), 0, 2, 2, box);
            this.setBlockState(world, rand.nextInt(5) == 0 ? Blocks.AIR.getDefaultState() : Blocks.GLASS_PANE.getDefaultState(), 0, 2, 3, box);
            this.setBlockState(world, rand.nextInt(5) == 0 ? Blocks.AIR.getDefaultState() : Blocks.GLASS_PANE.getDefaultState(), 0, 3, 2, box);
            this.setBlockState(world, rand.nextInt(5) == 0 ? Blocks.AIR.getDefaultState() : Blocks.GLASS_PANE.getDefaultState(), 0, 3, 3, box);
            this.setBlockState(world, rand.nextInt(5) == 0 ? Blocks.AIR.getDefaultState() : Blocks.GLASS_PANE.getDefaultState(), 8, 2, 2, box);
            this.setBlockState(world, rand.nextInt(5) == 0 ? Blocks.AIR.getDefaultState() : Blocks.GLASS_PANE.getDefaultState(), 8, 2, 3, box);
            this.setBlockState(world, rand.nextInt(5) == 0 ? Blocks.AIR.getDefaultState() : Blocks.GLASS_PANE.getDefaultState(), 8, 3, 2, box);
            this.setBlockState(world, rand.nextInt(5) == 0 ? Blocks.AIR.getDefaultState() : Blocks.GLASS_PANE.getDefaultState(), 8, 3, 3, box);
            this.setBlockState(world, rand.nextInt(5) == 0 ? Blocks.AIR.getDefaultState() : Blocks.GLASS_PANE.getDefaultState(), 2, 2, 5, box);
            this.setBlockState(world, rand.nextInt(5) == 0 ? Blocks.AIR.getDefaultState() : Blocks.GLASS_PANE.getDefaultState(), 3, 2, 5, box);
            this.setBlockState(world, rand.nextInt(5) == 0 ? Blocks.AIR.getDefaultState() : Blocks.GLASS_PANE.getDefaultState(), 5, 2, 5, box);
            this.setBlockState(world, rand.nextInt(5) == 0 ? Blocks.AIR.getDefaultState() : Blocks.GLASS_PANE.getDefaultState(), 6, 2, 5, box);
            this.fillWithRandomizedBlocks(world, box, 1, 4, 1, 7, 4, 1, false, rand, villagePlanks);
            this.fillWithRandomizedBlocks(world, box, 1, 4, 4, 7, 4, 4, false, rand, villagePlanks);
            this.fillWithBlocks(world, box, 1, 3, 4, 7, 3, 4, NibiruBlocks.NIBIRU_BOOKSHELF.getDefaultState(), NibiruBlocks.NIBIRU_BOOKSHELF.getDefaultState(), false);
            this.setBlockState(world, NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), 7, 1, 4, box);
            this.setBlockState(world, NibiruBlocks.INFECTED_OAK_STAIRS.getStateFromMeta(this.getMetadataWithOffset(NibiruBlocks.INFECTED_OAK_STAIRS, 0)), 7, 1, 3, box);
            int j1 = this.getMetadataWithOffset(NibiruBlocks.INFECTED_OAK_STAIRS, 3);
            this.setBlockState(world, NibiruBlocks.INFECTED_OAK_STAIRS.getStateFromMeta(j1), 6, 1, 4, box);
            this.setBlockState(world, NibiruBlocks.INFECTED_OAK_STAIRS.getStateFromMeta(j1), 5, 1, 4, box);
            this.setBlockState(world, NibiruBlocks.INFECTED_OAK_STAIRS.getStateFromMeta(j1), 4, 1, 4, box);
            this.setBlockState(world, NibiruBlocks.INFECTED_OAK_STAIRS.getStateFromMeta(j1), 3, 1, 4, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), 6, 1, 3, box);
            this.setBlockState(world, Blocks.WOODEN_PRESSURE_PLATE.getDefaultState(), 6, 2, 3, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), 4, 1, 3, box);
            this.setBlockState(world, Blocks.WOODEN_PRESSURE_PLATE.getDefaultState(), 4, 2, 3, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_CRAFTING_TABLE.getDefaultState(), 7, 1, 1, box);
            this.generateChestContents(world, box, rand, 7, 2, 1, ChestGenHooks.getItems(ItemLootHelper.NIBIRU_VILLAGE_LIBRARY, rand), ChestGenHooks.getCount(ItemLootHelper.NIBIRU_VILLAGE_LIBRARY, rand));
            this.setBlockState(world, Blocks.AIR.getDefaultState(), 1, 1, 0, box);
            this.setBlockState(world, Blocks.AIR.getDefaultState(), 1, 2, 0, box);
            this.placeDoorCurrentPosition(world, box, rand, 1, 1, 0, EnumFacing.getHorizontal(this.getMetadataWithOffset(NibiruBlocks.INFECTED_DOOR_BLOCK, 1)));

            if (this.getBlockStateFromPos(world, 1, 0, -1, box).getBlock().getMaterial() == Material.AIR && this.getBlockStateFromPos(world, 1, -1, -1, box).getBlock().getMaterial() != Material.AIR)
            {
                this.setBlockState(world, NibiruBlocks.NIBIRU_COBBLESTONE_STAIRS.getStateFromMeta(this.getMetadataWithOffset(NibiruBlocks.NIBIRU_COBBLESTONE_STAIRS, 3)), 1, 0, -1, box);
            }

            for (int k1 = 0; k1 < 6; ++k1)
            {
                for (int i1 = 0; i1 < 9; ++i1)
                {
                    this.clearCurrentPositionBlocksUpwards(world, i1, 9, k1, box);
                    this.replaceAirAndLiquidDownwards(world, NibiruBlocks.NIBIRU_BLOCK.getStateFromMeta(1), i1, -1, k1, box);
                }
            }
            if (rand.nextInt(3) == 0)
            {
                this.spawnVillagers(world, box, 2, 1, 2, 1);
            }
            return true;
        }

        @Override
        protected int chooseProfession(int spawnCount, int profession)
        {
            return this.isGreenVeinVillage ? 4 : 1;
        }
    }

    public static class House2 extends StructureNibiruVillagePieces.Village
    {
        private boolean hasMadeChest;

        public House2() {}

        static
        {
            ItemLootHelper.register(ItemLootHelper.NIBIRU_VILLAGE_BLACKSMITH, ItemLootHelper.NIBIRU_VILLAGE_BLACKSMITH_LOOT, 3, 8);
            ItemLootHelper.add(ItemLootHelper.NIBIRU_VILLAGE_BLACKSMITH, ItemLootHelper.ENCHANTED_BOOK);
        }

        public House2(StructureNibiruVillagePieces.Start start, int p_i45563_2_, Random rand, StructureBoundingBox p_i45563_4_, EnumFacing facing)
        {
            super(start, p_i45563_2_);
            this.coordBaseMode = facing;
            this.boundingBox = p_i45563_4_;
        }

        public static StructureNibiruVillagePieces.House2 func_175855_a(StructureNibiruVillagePieces.Start start, List<StructureComponent> p_175855_1_, Random rand, int p_175855_3_, int p_175855_4_, int p_175855_5_, EnumFacing facing, int p_175855_7_)
        {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175855_3_, p_175855_4_, p_175855_5_, 0, 0, 0, 10, 6, 7, facing);
            return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_175855_1_, structureboundingbox) == null ? new StructureNibiruVillagePieces.House2(start, p_175855_7_, rand, structureboundingbox, facing) : null;
        }

        @Override
        protected void writeStructureToNBT(NBTTagCompound tagCompound)
        {
            super.writeStructureToNBT(tagCompound);
            tagCompound.setBoolean("Chest", this.hasMadeChest);
        }

        @Override
        protected void readStructureFromNBT(NBTTagCompound tagCompound)
        {
            super.readStructureFromNBT(tagCompound);
            this.hasMadeChest = tagCompound.getBoolean("Chest");
        }

        @Override
        public boolean addComponentParts(World world, Random rand, StructureBoundingBox box)
        {
            if (this.field_143015_k < 0)
            {
                this.field_143015_k = this.getAverageGroundLevel(world, box);

                if (this.field_143015_k < 0)
                {
                    return true;
                }
                this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + 6 - 1, 0);
            }

            this.fillWithBlocks(world, box, 0, 1, 0, 9, 4, 6, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.fillWithRandomizedBlocks(world, box, 0, 0, 0, 9, 0, 6, false, rand, StructureNibiruVillagePieces.villageStones);
            this.fillWithRandomizedBlocks(world, box, 0, 4, 0, 9, 4, 6, false, rand, StructureNibiruVillagePieces.villageStones);
            this.fillWithBlocks(world, box, 0, 5, 0, 9, 5, 6, NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB.getDefaultState(), NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB.getDefaultState(), false);
            this.fillWithBlocks(world, box, 1, 5, 1, 8, 5, 5, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.fillWithRandomizedBlocks(world, box, 1, 1, 0, 2, 3, 0, false, rand, villagePlanks);
            this.fillWithRandomizedBlocks(world, box, 0, 1, 0, 0, 4, 0, false, rand, StructureNibiruVillagePieces.villageLogs);
            this.fillWithRandomizedBlocks(world, box, 3, 1, 0, 3, 4, 0, false, rand, StructureNibiruVillagePieces.villageLogs);
            this.fillWithRandomizedBlocks(world, box, 0, 1, 6, 0, 4, 6, false, rand, StructureNibiruVillagePieces.villageLogs);
            this.setBlockState(world, NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), 3, 3, 1, box);
            this.fillWithRandomizedBlocks(world, box, 3, 1, 2, 3, 3, 2, false, rand, StructureNibiruVillagePieces.villagePlanks);
            this.fillWithRandomizedBlocks(world, box, 4, 1, 3, 5, 3, 3, false, rand, StructureNibiruVillagePieces.villagePlanks);
            this.fillWithRandomizedBlocks(world, box, 0, 1, 1, 0, 3, 5, false, rand, StructureNibiruVillagePieces.villagePlanks);
            this.fillWithRandomizedBlocks(world, box, 1, 1, 6, 5, 3, 6, false, rand, StructureNibiruVillagePieces.villagePlanks);
            this.fillWithBlocks(world, box, 5, 1, 0, 5, 3, 0, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), NibiruBlocks.NIBIRU_FENCE.getDefaultState(), false);
            this.fillWithBlocks(world, box, 9, 1, 0, 9, 3, 0, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), NibiruBlocks.NIBIRU_FENCE.getDefaultState(), false);
            this.fillWithRandomizedBlocks(world, box, 6, 1, 4, 9, 4, 6, false, rand, StructureNibiruVillagePieces.villageStones);
            IBlockState lava = rand.nextInt(100) == 0 ? NibiruBlocks.NUCLEAR_WASTE_FLUID_BLOCK.getDefaultState() : Blocks.flowing_lava.getDefaultState();
            this.setBlockState(world, lava, 7, 1, 5, box);
            this.setBlockState(world, lava, 8, 1, 5, box);
            this.setBlockState(world, rand.nextInt(5) == 0 ? Blocks.AIR.getDefaultState() : Blocks.iron_bars.getDefaultState(), 9, 2, 5, box);
            this.setBlockState(world, rand.nextInt(5) == 0 ? Blocks.AIR.getDefaultState() : Blocks.iron_bars.getDefaultState(), 9, 2, 4, box);
            this.fillWithBlocks(world, box, 7, 2, 4, 8, 2, 5, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getStateFromMeta(1), 6, 1, 3, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_FURNACE.getDefaultState(), 6, 2, 3, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_FURNACE.getDefaultState(), 6, 3, 3, box);
            this.setBlockState(world, NibiruBlocks.DOUBLE_INFECTED_STONE_BRICKS_SLAB.getDefaultState(), 8, 1, 1, box);
            this.setBlockState(world, rand.nextInt(5) == 0 ? Blocks.AIR.getDefaultState() : Blocks.GLASS_PANE.getDefaultState(), 0, 2, 2, box);
            this.setBlockState(world, rand.nextInt(5) == 0 ? Blocks.AIR.getDefaultState() : Blocks.GLASS_PANE.getDefaultState(), 0, 2, 4, box);
            this.setBlockState(world, rand.nextInt(5) == 0 ? Blocks.AIR.getDefaultState() : Blocks.GLASS_PANE.getDefaultState(), 2, 2, 6, box);
            this.setBlockState(world, rand.nextInt(5) == 0 ? Blocks.AIR.getDefaultState() : Blocks.GLASS_PANE.getDefaultState(), 4, 2, 6, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), 2, 1, 4, box);
            this.setBlockState(world, Blocks.WOODEN_PRESSURE_PLATE.getDefaultState(), 2, 2, 4, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), 1, 1, 5, box);
            this.setBlockState(world, NibiruBlocks.INFECTED_OAK_STAIRS.getStateFromMeta(this.getMetadataWithOffset(NibiruBlocks.INFECTED_OAK_STAIRS, 3)), 2, 1, 5, box);
            this.setBlockState(world, NibiruBlocks.INFECTED_OAK_STAIRS.getStateFromMeta(this.getMetadataWithOffset(NibiruBlocks.INFECTED_OAK_STAIRS, 1)), 1, 1, 4, box);

            if (!this.hasMadeChest && box.isVecInside(new BlockPos(this.getXWithOffset(5, 5), this.getYWithOffset(1), this.getZWithOffset(5, 5))))
            {
                this.hasMadeChest = true;
                this.generateChestContents(world, box, rand, 5, 1, 5, ChestGenHooks.getItems(ItemLootHelper.NIBIRU_VILLAGE_BLACKSMITH, rand), ChestGenHooks.getCount(ItemLootHelper.NIBIRU_VILLAGE_BLACKSMITH, rand));
            }

            for (int i = 6; i <= 8; ++i)
            {
                if (this.getBlockStateFromPos(world, i, 0, -1, box).getBlock().getMaterial() == Material.AIR && this.getBlockStateFromPos(world, i, -1, -1, box).getBlock().getMaterial() != Material.AIR)
                {
                    this.setBlockState(world, NibiruBlocks.NIBIRU_COBBLESTONE_STAIRS.getStateFromMeta(this.getMetadataWithOffset(NibiruBlocks.NIBIRU_COBBLESTONE_STAIRS, 3)), i, 0, -1, box);
                }
            }

            for (int k = 0; k < 7; ++k)
            {
                for (int j = 0; j < 10; ++j)
                {
                    this.clearCurrentPositionBlocksUpwards(world, j, 6, k, box);
                    this.replaceAirAndLiquidDownwards(world, NibiruBlocks.NIBIRU_BLOCK.getStateFromMeta(1), j, -1, k, box);
                }
            }
            if (rand.nextInt(3) == 0)
            {
                this.spawnVillagers(world, box, 7, 1, 1, 1);
            }
            return true;
        }

        @Override
        protected int chooseProfession(int spawnCount, int profession)
        {
            return this.isGreenVeinVillage ? 5 : 2;
        }
    }

    public static class House3 extends StructureNibiruVillagePieces.Village
    {
        public House3() {}

        public House3(StructureNibiruVillagePieces.Start start, int p_i45561_2_, Random rand, StructureBoundingBox p_i45561_4_, EnumFacing facing)
        {
            super(start, p_i45561_2_);
            this.coordBaseMode = facing;
            this.boundingBox = p_i45561_4_;
        }

        public static StructureNibiruVillagePieces.House3 func_175849_a(StructureNibiruVillagePieces.Start start, List<StructureComponent> p_175849_1_, Random rand, int p_175849_3_, int p_175849_4_, int p_175849_5_, EnumFacing facing, int p_175849_7_)
        {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175849_3_, p_175849_4_, p_175849_5_, 0, 0, 0, 9, 7, 12, facing);
            return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_175849_1_, structureboundingbox) == null ? new StructureNibiruVillagePieces.House3(start, p_175849_7_, rand, structureboundingbox, facing) : null;
        }

        @Override
        public boolean addComponentParts(World world, Random rand, StructureBoundingBox box)
        {
            if (this.field_143015_k < 0)
            {
                this.field_143015_k = this.getAverageGroundLevel(world, box);

                if (this.field_143015_k < 0)
                {
                    return true;
                }
                this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + 7 - 1, 0);
            }

            this.fillWithBlocks(world, box, 1, 1, 1, 7, 4, 4, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.fillWithBlocks(world, box, 2, 1, 6, 8, 4, 10, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.fillWithRandomizedBlocks(world, box, 2, 0, 5, 8, 0, 10, false, rand, villagePlanks);
            this.fillWithRandomizedBlocks(world, box, 1, 0, 1, 7, 0, 4, false, rand, villagePlanks);
            this.fillWithRandomizedBlocks(world, box, 0, 0, 0, 0, 3, 5, false, rand, villageStones);
            this.fillWithRandomizedBlocks(world, box, 8, 0, 0, 8, 3, 10, false, rand, villageStones);
            this.fillWithRandomizedBlocks(world, box, 1, 0, 0, 7, 2, 0, false, rand, villageStones);
            this.fillWithRandomizedBlocks(world, box, 1, 0, 5, 2, 1, 5, false, rand, villageStones);
            this.fillWithRandomizedBlocks(world, box, 2, 0, 6, 2, 3, 10, false, rand, villageStones);
            this.fillWithRandomizedBlocks(world, box, 3, 0, 10, 7, 3, 10, false, rand, villageStones);
            this.fillWithRandomizedBlocks(world, box, 1, 2, 0, 7, 3, 0, false, rand, villagePlanks);
            this.fillWithRandomizedBlocks(world, box, 1, 2, 5, 2, 3, 5, false, rand, villagePlanks);
            this.fillWithRandomizedBlocks(world, box, 0, 4, 1, 8, 4, 1, false, rand, villagePlanks);
            this.fillWithRandomizedBlocks(world, box, 0, 4, 4, 3, 4, 4, false, rand, villagePlanks);
            this.fillWithRandomizedBlocks(world, box, 0, 5, 2, 8, 5, 3, false, rand, villagePlanks);
            this.setBlockState(world, NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), 0, 4, 2, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), 0, 4, 3, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), 8, 4, 2, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), 8, 4, 3, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), 8, 4, 4, box);
            int i = this.getMetadataWithOffset(NibiruBlocks.INFECTED_OAK_STAIRS, 3);
            int j = this.getMetadataWithOffset(NibiruBlocks.INFECTED_OAK_STAIRS, 2);

            for (int k = -1; k <= 2; ++k)
            {
                for (int l = 0; l <= 8; ++l)
                {
                    this.setBlockState(world, NibiruBlocks.INFECTED_OAK_STAIRS.getStateFromMeta(i), l, 4 + k, k, box);

                    if ((k > -1 || l <= 1) && (k > 0 || l <= 3) && (k > 1 || l <= 4 || l >= 6))
                    {
                        this.setBlockState(world, NibiruBlocks.INFECTED_OAK_STAIRS.getStateFromMeta(j), l, 4 + k, 5 - k, box);
                    }
                }
            }

            this.fillWithRandomizedBlocks(world, box, 3, 4, 5, 3, 4, 10, false, rand, villagePlanks);
            this.fillWithRandomizedBlocks(world, box, 7, 4, 2, 7, 4, 10, false, rand, villagePlanks);
            this.fillWithRandomizedBlocks(world, box, 4, 5, 4, 4, 5, 10, false, rand, villagePlanks);
            this.fillWithRandomizedBlocks(world, box, 6, 5, 4, 6, 5, 10, false, rand, villagePlanks);
            this.fillWithRandomizedBlocks(world, box, 5, 6, 3, 5, 6, 10, false, rand, villagePlanks);
            int k1 = this.getMetadataWithOffset(NibiruBlocks.INFECTED_OAK_STAIRS, 0);

            for (int l1 = 4; l1 >= 1; --l1)
            {
                this.setBlockState(world, NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), l1, 2 + l1, 7 - l1, box);

                for (int i1 = 8 - l1; i1 <= 10; ++i1)
                {
                    this.setBlockState(world, NibiruBlocks.INFECTED_OAK_STAIRS.getStateFromMeta(k1), l1, 2 + l1, i1, box);
                }
            }

            int i2 = this.getMetadataWithOffset(NibiruBlocks.INFECTED_OAK_STAIRS, 1);
            this.setBlockState(world, NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), 6, 6, 3, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), 7, 5, 4, box);
            this.setBlockState(world, NibiruBlocks.INFECTED_OAK_STAIRS.getStateFromMeta(i2), 6, 6, 4, box);

            for (int j2 = 6; j2 <= 8; ++j2)
            {
                for (int j1 = 5; j1 <= 10; ++j1)
                {
                    this.setBlockState(world, NibiruBlocks.INFECTED_OAK_STAIRS.getStateFromMeta(i2), j2, 12 - j2, j1, box);
                }
            }

            this.setBlockState(world, NibiruBlocks.NIBIRU_LOG.getDefaultState(), 0, 2, 1, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_LOG.getDefaultState(), 0, 2, 4, box);
            this.setBlockState(world, rand.nextInt(5) == 0 ? Blocks.AIR.getDefaultState() : Blocks.GLASS_PANE.getDefaultState(), 0, 2, 2, box);
            this.setBlockState(world, rand.nextInt(5) == 0 ? Blocks.AIR.getDefaultState() : Blocks.GLASS_PANE.getDefaultState(), 0, 2, 3, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_LOG.getDefaultState(), 4, 2, 0, box);
            this.setBlockState(world, rand.nextInt(5) == 0 ? Blocks.AIR.getDefaultState() : Blocks.GLASS_PANE.getDefaultState(), 5, 2, 0, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_LOG.getDefaultState(), 6, 2, 0, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_LOG.getDefaultState(), 8, 2, 1, box);
            this.setBlockState(world, rand.nextInt(5) == 0 ? Blocks.AIR.getDefaultState() : Blocks.GLASS_PANE.getDefaultState(), 8, 2, 2, box);
            this.setBlockState(world, rand.nextInt(5) == 0 ? Blocks.AIR.getDefaultState() : Blocks.GLASS_PANE.getDefaultState(), 8, 2, 3, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_LOG.getDefaultState(), 8, 2, 4, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), 8, 2, 5, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_LOG.getDefaultState(), 8, 2, 6, box);
            this.setBlockState(world, rand.nextInt(5) == 0 ? Blocks.AIR.getDefaultState() : Blocks.GLASS_PANE.getDefaultState(), 8, 2, 7, box);
            this.setBlockState(world, rand.nextInt(5) == 0 ? Blocks.AIR.getDefaultState() : Blocks.GLASS_PANE.getDefaultState(), 8, 2, 8, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_LOG.getDefaultState(), 8, 2, 9, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_LOG.getDefaultState(), 2, 2, 6, box);
            this.setBlockState(world, rand.nextInt(5) == 0 ? Blocks.AIR.getDefaultState() : Blocks.GLASS_PANE.getDefaultState(), 2, 2, 7, box);
            this.setBlockState(world, rand.nextInt(5) == 0 ? Blocks.AIR.getDefaultState() : Blocks.GLASS_PANE.getDefaultState(), 2, 2, 8, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_LOG.getDefaultState(), 2, 2, 9, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_LOG.getDefaultState(), 4, 4, 10, box);
            this.setBlockState(world, rand.nextInt(5) == 0 ? Blocks.AIR.getDefaultState() : Blocks.GLASS_PANE.getDefaultState(), 5, 4, 10, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_LOG.getDefaultState(), 6, 4, 10, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), 5, 5, 10, box);
            this.setBlockState(world, Blocks.AIR.getDefaultState(), 2, 1, 0, box);
            this.setBlockState(world, Blocks.AIR.getDefaultState(), 2, 2, 0, box);
            Block torch = this.isGreenVeinVillage ? GCBlocks.glowstoneTorch : NibiruBlocks.INFECTED_TORCH;
            this.setBlockState(world, torch.getDefaultState().withProperty(BlockTorch.FACING, this.coordBaseMode), 2, 3, 1, box);
            this.placeDoorCurrentPosition(world, box, rand, 2, 1, 0, EnumFacing.getHorizontal(this.getMetadataWithOffset(NibiruBlocks.INFECTED_DOOR_BLOCK, 1)));
            this.fillWithBlocks(world, box, 1, 0, -1, 3, 2, -1, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);

            if (this.getBlockStateFromPos(world, 2, 0, -1, box).getBlock().getMaterial() == Material.AIR && this.getBlockStateFromPos(world, 2, -1, -1, box).getBlock().getMaterial() != Material.AIR)
            {
                this.setBlockState(world, NibiruBlocks.NIBIRU_COBBLESTONE_STAIRS.getStateFromMeta(this.getMetadataWithOffset(NibiruBlocks.NIBIRU_COBBLESTONE_STAIRS, 3)), 2, 0, -1, box);
            }

            for (int k2 = 0; k2 < 5; ++k2)
            {
                for (int i3 = 0; i3 < 9; ++i3)
                {
                    this.clearCurrentPositionBlocksUpwards(world, i3, 7, k2, box);
                    this.replaceAirAndLiquidDownwards(world, NibiruBlocks.NIBIRU_BLOCK.getStateFromMeta(1), i3, -1, k2, box);
                }
            }

            for (int l2 = 5; l2 < 11; ++l2)
            {
                for (int j3 = 2; j3 < 9; ++j3)
                {
                    this.clearCurrentPositionBlocksUpwards(world, j3, 7, l2, box);
                    this.replaceAirAndLiquidDownwards(world, NibiruBlocks.NIBIRU_BLOCK.getStateFromMeta(1), j3, -1, l2, box);
                }
            }
            if (rand.nextInt(3) == 0)
            {
                this.spawnVillagers(world, box, 4, 1, 2, 2);
            }
            return true;
        }
    }

    public static class House4Garden extends StructureNibiruVillagePieces.Village
    {
        private boolean isRoofAccessible;

        public House4Garden() {}

        public House4Garden(StructureNibiruVillagePieces.Start start, int p_i45566_2_, Random rand, StructureBoundingBox p_i45566_4_, EnumFacing facing)
        {
            super(start, p_i45566_2_);
            this.coordBaseMode = facing;
            this.boundingBox = p_i45566_4_;
            this.isRoofAccessible = rand.nextBoolean();
        }

        @Override
        protected void writeStructureToNBT(NBTTagCompound tagCompound)
        {
            super.writeStructureToNBT(tagCompound);
            tagCompound.setBoolean("Terrace", this.isRoofAccessible);
        }

        @Override
        protected void readStructureFromNBT(NBTTagCompound tagCompound)
        {
            super.readStructureFromNBT(tagCompound);
            this.isRoofAccessible = tagCompound.getBoolean("Terrace");
        }

        public static StructureNibiruVillagePieces.House4Garden func_175858_a(StructureNibiruVillagePieces.Start start, List<StructureComponent> p_175858_1_, Random rand, int p_175858_3_, int p_175858_4_, int p_175858_5_, EnumFacing facing, int p_175858_7_)
        {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175858_3_, p_175858_4_, p_175858_5_, 0, 0, 0, 5, 6, 5, facing);
            return StructureComponent.findIntersecting(p_175858_1_, structureboundingbox) != null ? null : new StructureNibiruVillagePieces.House4Garden(start, p_175858_7_, rand, structureboundingbox, facing);
        }

        @Override
        public boolean addComponentParts(World world, Random rand, StructureBoundingBox box)
        {
            if (this.field_143015_k < 0)
            {
                this.field_143015_k = this.getAverageGroundLevel(world, box);

                if (this.field_143015_k < 0)
                {
                    return true;
                }
                this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + 6 - 1, 0);
            }

            this.fillWithRandomizedBlocks(world, box, 0, 0, 0, 4, 0, 4, false, rand, villageStones);
            this.fillWithRandomizedBlocks(world, box, 0, 4, 0, 4, 4, 4, false, rand, villageLogs);
            this.fillWithRandomizedBlocks(world, box, 1, 4, 1, 3, 4, 3, false, rand, villagePlanks);
            this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getStateFromMeta(1), 0, 1, 0, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getStateFromMeta(1), 0, 2, 0, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getStateFromMeta(1), 0, 3, 0, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getStateFromMeta(1), 4, 1, 0, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getStateFromMeta(1), 4, 2, 0, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getStateFromMeta(1), 4, 3, 0, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getStateFromMeta(1), 0, 1, 4, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getStateFromMeta(1), 0, 2, 4, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getStateFromMeta(1), 0, 3, 4, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getStateFromMeta(1), 4, 1, 4, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getStateFromMeta(1), 4, 2, 4, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_BLOCK.getStateFromMeta(1), 4, 3, 4, box);
            this.fillWithRandomizedBlocks(world, box, 0, 1, 1, 0, 3, 3, false, rand, villagePlanks);
            this.fillWithRandomizedBlocks(world, box, 4, 1, 1, 4, 3, 3, false, rand, villagePlanks);
            this.fillWithRandomizedBlocks(world, box, 1, 1, 4, 3, 3, 4, false, rand, villagePlanks);
            this.setBlockState(world, rand.nextInt(5) == 0 ? Blocks.AIR.getDefaultState() : Blocks.GLASS_PANE.getDefaultState(), 0, 2, 2, box);
            this.setBlockState(world, rand.nextInt(5) == 0 ? Blocks.AIR.getDefaultState() : Blocks.GLASS_PANE.getDefaultState(), 2, 2, 4, box);
            this.setBlockState(world, rand.nextInt(5) == 0 ? Blocks.AIR.getDefaultState() : Blocks.GLASS_PANE.getDefaultState(), 4, 2, 2, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), 1, 1, 0, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), 1, 2, 0, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), 1, 3, 0, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), 2, 3, 0, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), 3, 3, 0, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), 3, 2, 0, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_PLANKS.getDefaultState(), 3, 1, 0, box);

            if (this.getBlockStateFromPos(world, 2, 0, -1, box).getBlock().getMaterial() == Material.AIR && this.getBlockStateFromPos(world, 2, -1, -1, box).getBlock().getMaterial() != Material.AIR)
            {
                this.setBlockState(world, NibiruBlocks.NIBIRU_COBBLESTONE_STAIRS.getStateFromMeta(this.getMetadataWithOffset(NibiruBlocks.NIBIRU_COBBLESTONE_STAIRS, 3)), 2, 0, -1, box);
            }

            this.fillWithBlocks(world, box, 1, 1, 1, 3, 3, 3, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);

            if (this.isRoofAccessible)
            {
                this.setBlockState(world, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), 0, 5, 0, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), 1, 5, 0, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), 2, 5, 0, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), 3, 5, 0, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), 4, 5, 0, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), 0, 5, 4, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), 1, 5, 4, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), 2, 5, 4, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), 3, 5, 4, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), 4, 5, 4, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), 4, 5, 1, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), 4, 5, 2, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), 4, 5, 3, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), 0, 5, 1, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), 0, 5, 2, box);
                this.setBlockState(world, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), 0, 5, 3, box);
            }

            if (this.isRoofAccessible)
            {
                int i = this.getMetadataWithOffset(Blocks.ladder, 3);
                this.setBlockState(world, Blocks.ladder.getStateFromMeta(i), 3, 1, 3, box);
                this.setBlockState(world, Blocks.ladder.getStateFromMeta(i), 3, 2, 3, box);
                this.setBlockState(world, Blocks.ladder.getStateFromMeta(i), 3, 3, 3, box);
                this.setBlockState(world, Blocks.ladder.getStateFromMeta(i), 3, 4, 3, box);
            }

            Block torch = this.isGreenVeinVillage ? GCBlocks.glowstoneTorch : NibiruBlocks.INFECTED_TORCH;
            this.setBlockState(world, torch.getDefaultState().withProperty(BlockTorch.FACING, this.coordBaseMode), 2, 3, 1, box);

            for (int k = 0; k < 5; ++k)
            {
                for (int j = 0; j < 5; ++j)
                {
                    this.clearCurrentPositionBlocksUpwards(world, j, 6, k, box);
                    this.replaceAirAndLiquidDownwards(world, NibiruBlocks.NIBIRU_BLOCK.getStateFromMeta(1), j, -1, k, box);
                }
            }
            if (rand.nextInt(3) == 0)
            {
                this.spawnVillagers(world, box, 1, 1, 2, 1);
            }
            return true;
        }
    }

    public static class Path extends StructureNibiruVillagePieces.Road
    {
        private int length;

        public Path() {}

        public Path(StructureNibiruVillagePieces.Start start, int p_i45562_2_, Random rand, StructureBoundingBox p_i45562_4_, EnumFacing facing)
        {
            super(start, p_i45562_2_);
            this.coordBaseMode = facing;
            this.boundingBox = p_i45562_4_;
            this.length = Math.max(p_i45562_4_.getXSize(), p_i45562_4_.getZSize());
        }

        @Override
        protected void writeStructureToNBT(NBTTagCompound tagCompound)
        {
            super.writeStructureToNBT(tagCompound);
            tagCompound.setInteger("Length", this.length);
        }

        @Override
        protected void readStructureFromNBT(NBTTagCompound tagCompound)
        {
            super.readStructureFromNBT(tagCompound);
            this.length = tagCompound.getInteger("Length");
        }

        @Override
        public void buildComponent(StructureComponent componentIn, List<StructureComponent> listIn, Random rand)
        {
            boolean flag = false;

            for (int i = rand.nextInt(5); i < this.length - 8; i += 2 + rand.nextInt(5))
            {
                StructureComponent structurecomponent = this.getNextComponentNN((StructureNibiruVillagePieces.Start)componentIn, listIn, rand, 0, i);

                if (structurecomponent != null)
                {
                    i += Math.max(structurecomponent.getBoundingBox().getXSize(), structurecomponent.getBoundingBox().getZSize());
                    flag = true;
                }
            }

            for (int j = rand.nextInt(5); j < this.length - 8; j += 2 + rand.nextInt(5))
            {
                StructureComponent structurecomponent1 = this.getNextComponentPP((StructureNibiruVillagePieces.Start)componentIn, listIn, rand, 0, j);

                if (structurecomponent1 != null)
                {
                    j += Math.max(structurecomponent1.getBoundingBox().getXSize(), structurecomponent1.getBoundingBox().getZSize());
                    flag = true;
                }
            }

            if (flag && rand.nextInt(3) > 0 && this.coordBaseMode != null)
            {
                switch (this.coordBaseMode)
                {
                case NORTH:
                    StructureNibiruVillagePieces.func_176069_e((StructureNibiruVillagePieces.Start)componentIn, listIn, rand, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.minZ, EnumFacing.WEST, this.getComponentType());
                    break;
                case SOUTH:
                    StructureNibiruVillagePieces.func_176069_e((StructureNibiruVillagePieces.Start)componentIn, listIn, rand, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.maxZ - 2, EnumFacing.WEST, this.getComponentType());
                    break;
                case WEST:
                    StructureNibiruVillagePieces.func_176069_e((StructureNibiruVillagePieces.Start)componentIn, listIn, rand, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType());
                    break;
                case EAST:
                    StructureNibiruVillagePieces.func_176069_e((StructureNibiruVillagePieces.Start)componentIn, listIn, rand, this.boundingBox.maxX - 2, this.boundingBox.minY, this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType());
                }
            }

            if (flag && rand.nextInt(3) > 0 && this.coordBaseMode != null)
            {
                switch (this.coordBaseMode)
                {
                case NORTH:
                    StructureNibiruVillagePieces.func_176069_e((StructureNibiruVillagePieces.Start)componentIn, listIn, rand, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.minZ, EnumFacing.EAST, this.getComponentType());
                    break;
                case SOUTH:
                    StructureNibiruVillagePieces.func_176069_e((StructureNibiruVillagePieces.Start)componentIn, listIn, rand, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.maxZ - 2, EnumFacing.EAST, this.getComponentType());
                    break;
                case WEST:
                    StructureNibiruVillagePieces.func_176069_e((StructureNibiruVillagePieces.Start)componentIn, listIn, rand, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
                    break;
                case EAST:
                    StructureNibiruVillagePieces.func_176069_e((StructureNibiruVillagePieces.Start)componentIn, listIn, rand, this.boundingBox.maxX - 2, this.boundingBox.minY, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
                }
            }
        }

        public static StructureBoundingBox func_175848_a(StructureNibiruVillagePieces.Start start, List<StructureComponent> p_175848_1_, Random rand, int p_175848_3_, int p_175848_4_, int p_175848_5_, EnumFacing facing)
        {
            for (int i = 7 * MathHelper.getRandomIntegerInRange(rand, 3, 5); i >= 7; i -= 7)
            {
                StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175848_3_, p_175848_4_, p_175848_5_, 0, 0, 0, 3, 3, i, facing);

                if (StructureComponent.findIntersecting(p_175848_1_, structureboundingbox) == null)
                {
                    return structureboundingbox;
                }
            }
            return null;
        }

        @Override
        public boolean addComponentParts(World world, Random rand, StructureBoundingBox box)
        {
            IBlockState iblockstate = this.getBiomeSpecificBlockState(NibiruBlocks.INFECTED_GRAVEL.getDefaultState());
            IBlockState iblockstate1 = this.getBiomeSpecificBlockState(NibiruBlocks.NIBIRU_BLOCK.getStateFromMeta(1));

            for (int i = this.boundingBox.minX; i <= this.boundingBox.maxX; ++i)
            {
                for (int j = this.boundingBox.minZ; j <= this.boundingBox.maxZ; ++j)
                {
                    BlockPos blockpos = new BlockPos(i, 64, j);

                    if (box.isVecInside(blockpos))
                    {
                        blockpos = world.getTopSolidOrLiquidBlock(blockpos).down();
                        world.setBlockState(blockpos, iblockstate, 2);
                        world.setBlockState(blockpos.down(), iblockstate1, 2);
                    }
                }
            }
            return true;
        }
    }

    public static class PieceWeight
    {
        public Class <? extends StructureNibiruVillagePieces.Village > villagePieceClass;
        public int villagePieceWeight;
        public int villagePiecesSpawned;
        public int villagePiecesLimit;

        public PieceWeight(Class <? extends StructureNibiruVillagePieces.Village > p_i2098_1_, int p_i2098_2_, int p_i2098_3_)
        {
            this.villagePieceClass = p_i2098_1_;
            this.villagePieceWeight = p_i2098_2_;
            this.villagePiecesLimit = p_i2098_3_;
        }

        public boolean canSpawnMoreVillagePiecesOfType(int p_75085_1_)
        {
            return this.villagePiecesLimit == 0 || this.villagePiecesSpawned < this.villagePiecesLimit;
        }

        public boolean canSpawnMoreVillagePieces()
        {
            return this.villagePiecesLimit == 0 || this.villagePiecesSpawned < this.villagePiecesLimit;
        }
    }

    public abstract static class Road extends StructureNibiruVillagePieces.Village
    {
        public Road() {}

        protected Road(StructureNibiruVillagePieces.Start start, int type)
        {
            super(start, type);
        }
    }

    public static class Start extends StructureNibiruVillagePieces.Well
    {
        public WorldChunkManager worldChunkMngr;
        public boolean inDesert;
        public boolean inGreenVein;
        public int terrainType;
        public StructureNibiruVillagePieces.PieceWeight structVillagePieceWeight;
        public List<StructureNibiruVillagePieces.PieceWeight> structureVillageWeightedPieceList;
        public List<StructureComponent> field_74932_i = Lists.<StructureComponent>newArrayList();
        public List<StructureComponent> field_74930_j = Lists.<StructureComponent>newArrayList();
        public BiomeGenBase biome;

        public Start() {}

        public Start(WorldChunkManager chunkManagerIn, int p_i2104_2_, Random rand, int p_i2104_4_, int p_i2104_5_, List<StructureNibiruVillagePieces.PieceWeight> p_i2104_6_, int p_i2104_7_)
        {
            super((StructureNibiruVillagePieces.Start)null, 0, rand, p_i2104_4_, p_i2104_5_);
            this.worldChunkMngr = chunkManagerIn;
            this.structureVillageWeightedPieceList = p_i2104_6_;
            this.terrainType = p_i2104_7_;
            BiomeGenBase biomegenbase = chunkManagerIn.getBiomeGenerator(new BlockPos(p_i2104_4_, 0, p_i2104_5_), BiomeGenBase.field_180279_ad);
            this.inDesert = biomegenbase == MPBiomes.INFECTED_DESERT;
            this.inGreenVein = biomegenbase == MPBiomes.GREEN_VEIN;
            this.biome = biomegenbase;
            this.setIsDesert(this.inDesert);
            this.setIsGreenVein(this.inGreenVein);
        }

        public WorldChunkManager getWorldChunkManager()
        {
            return this.worldChunkMngr;
        }
    }

    public static class Torch extends StructureNibiruVillagePieces.Village
    {
        public Torch() {}

        public Torch(StructureNibiruVillagePieces.Start start, int p_i45568_2_, Random rand, StructureBoundingBox p_i45568_4_, EnumFacing facing)
        {
            super(start, p_i45568_2_);
            this.coordBaseMode = facing;
            this.boundingBox = p_i45568_4_;
        }

        public static StructureBoundingBox func_175856_a(StructureNibiruVillagePieces.Start start, List<StructureComponent> p_175856_1_, Random rand, int p_175856_3_, int p_175856_4_, int p_175856_5_, EnumFacing facing)
        {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175856_3_, p_175856_4_, p_175856_5_, 0, 0, 0, 3, 4, 2, facing);
            return StructureComponent.findIntersecting(p_175856_1_, structureboundingbox) != null ? null : structureboundingbox;
        }

        @Override
        public boolean addComponentParts(World world, Random rand, StructureBoundingBox box)
        {
            if (this.field_143015_k < 0)
            {
                this.field_143015_k = this.getAverageGroundLevel(world, box);

                if (this.field_143015_k < 0)
                {
                    return true;
                }
                this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + 4 - 1, 0);
            }
            this.fillWithBlocks(world, box, 0, 0, 0, 2, 3, 1, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.setBlockState(world, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), 1, 0, 0, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), 1, 1, 0, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), 1, 2, 0, box);
            this.setBlockState(world, NibiruBlocks.INFECTED_PRISMARINE.getStateFromMeta(2), 1, 3, 0, box);
            boolean flag = this.coordBaseMode == EnumFacing.EAST || this.coordBaseMode == EnumFacing.NORTH;
            Block torch = this.isGreenVeinVillage ? GCBlocks.glowstoneTorch : NibiruBlocks.INFECTED_TORCH;
            this.setBlockState(world, torch.getDefaultState().withProperty(BlockTorch.FACING, this.coordBaseMode.rotateY()), flag ? 2 : 0, 3, 0, box);
            this.setBlockState(world, torch.getDefaultState().withProperty(BlockTorch.FACING, this.coordBaseMode), 1, 3, 1, box);
            this.setBlockState(world, torch.getDefaultState().withProperty(BlockTorch.FACING, this.coordBaseMode.rotateYCCW()), flag ? 0 : 2, 3, 0, box);
            this.setBlockState(world, torch.getDefaultState().withProperty(BlockTorch.FACING, this.coordBaseMode.getOpposite()), 1, 3, -1, box);
            return true;
        }
    }

    public abstract static class Village extends StructureComponentMP
    {
        protected int field_143015_k = -1;
        private int villagersSpawned;
        protected boolean isDesertVillage;
        protected boolean isGreenVeinVillage;

        public Village() {}

        protected Village(StructureNibiruVillagePieces.Start start, int type)
        {
            super(type);

            if (start != null)
            {
                this.isDesertVillage = start.inDesert;
                this.isGreenVeinVillage = start.inGreenVein;
            }
        }

        @Override
        protected void placeDoorCurrentPosition(World world, StructureBoundingBox box, Random rand, int x, int y, int z, EnumFacing facing)
        {
            BlockPos blockpos = new BlockPos(this.getXWithOffset(x, z), this.getYWithOffset(y), this.getZWithOffset(x, z));

            if (box.isVecInside(blockpos))
            {
                if (this.isGreenVeinVillage)
                {
                    ItemDoor.placeDoor(world, blockpos, facing.rotateYCCW(), NibiruBlocks.ALIEN_BERRY_OAK_DOOR_BLOCK);
                }
                else
                {
                    ItemDoor.placeDoor(world, blockpos, facing.rotateYCCW(), NibiruBlocks.INFECTED_DOOR_BLOCK);
                }
            }
        }

        @Override
        protected boolean generateChestContents(World world, StructureBoundingBox box, Random rand, int x, int y, int z, List<WeightedRandomChestContent> list, int max)
        {
            BlockPos blockpos = new BlockPos(this.getXWithOffset(x, z), this.getYWithOffset(y), this.getZWithOffset(x, z));
            Block chest = NibiruBlocks.INFECTED_CHEST;

            if (this.isGreenVeinVillage)
            {
                chest = NibiruBlocks.ALIEN_BERRY_CHEST;
            }
            if (box.isVecInside(blockpos) && world.getBlockState(blockpos).getBlock() != chest)
            {
                IBlockState iblockstate = chest.getDefaultState();
                world.setBlockState(blockpos, ((BlockChestMP)chest).correctFacing(world, blockpos, iblockstate), 2);
                TileEntity tileentity = world.getTileEntity(blockpos);

                if (tileentity instanceof TileEntityChestMP)
                {
                    WeightedRandomChestContent.generateChestContents(rand, list, (TileEntityChestMP)tileentity, max);
                }
                return true;
            }
            else
            {
                return false;
            }
        }

        @Override
        protected void writeStructureToNBT(NBTTagCompound tagCompound)
        {
            tagCompound.setInteger("HPos", this.field_143015_k);
            tagCompound.setInteger("VCount", this.villagersSpawned);
            tagCompound.setBoolean("Desert", this.isDesertVillage);
            tagCompound.setBoolean("GreenVein", this.isGreenVeinVillage);
        }

        @Override
        protected void readStructureFromNBT(NBTTagCompound tagCompound)
        {
            this.field_143015_k = tagCompound.getInteger("HPos");
            this.villagersSpawned = tagCompound.getInteger("VCount");
            this.isDesertVillage = tagCompound.getBoolean("Desert");
            this.isGreenVeinVillage = tagCompound.getBoolean("GreenVein");
        }

        protected StructureComponent getNextComponentNN(StructureNibiruVillagePieces.Start start, List<StructureComponent> p_74891_2_, Random rand, int p_74891_4_, int p_74891_5_)
        {
            if (this.coordBaseMode != null)
            {
                switch (this.coordBaseMode)
                {
                case NORTH:
                    return StructureNibiruVillagePieces.func_176066_d(start, p_74891_2_, rand, this.boundingBox.minX - 1, this.boundingBox.minY + p_74891_4_, this.boundingBox.minZ + p_74891_5_, EnumFacing.WEST, this.getComponentType());
                case SOUTH:
                    return StructureNibiruVillagePieces.func_176066_d(start, p_74891_2_, rand, this.boundingBox.minX - 1, this.boundingBox.minY + p_74891_4_, this.boundingBox.minZ + p_74891_5_, EnumFacing.WEST, this.getComponentType());
                case WEST:
                    return StructureNibiruVillagePieces.func_176066_d(start, p_74891_2_, rand, this.boundingBox.minX + p_74891_5_, this.boundingBox.minY + p_74891_4_, this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType());
                case EAST:
                    return StructureNibiruVillagePieces.func_176066_d(start, p_74891_2_, rand, this.boundingBox.minX + p_74891_5_, this.boundingBox.minY + p_74891_4_, this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType());
                }
            }
            return null;
        }

        protected StructureComponent getNextComponentPP(StructureNibiruVillagePieces.Start start, List<StructureComponent> p_74894_2_, Random rand, int p_74894_4_, int p_74894_5_)
        {
            if (this.coordBaseMode != null)
            {
                switch (this.coordBaseMode)
                {
                case NORTH:
                    return StructureNibiruVillagePieces.func_176066_d(start, p_74894_2_, rand, this.boundingBox.maxX + 1, this.boundingBox.minY + p_74894_4_, this.boundingBox.minZ + p_74894_5_, EnumFacing.EAST, this.getComponentType());
                case SOUTH:
                    return StructureNibiruVillagePieces.func_176066_d(start, p_74894_2_, rand, this.boundingBox.maxX + 1, this.boundingBox.minY + p_74894_4_, this.boundingBox.minZ + p_74894_5_, EnumFacing.EAST, this.getComponentType());
                case WEST:
                    return StructureNibiruVillagePieces.func_176066_d(start, p_74894_2_, rand, this.boundingBox.minX + p_74894_5_, this.boundingBox.minY + p_74894_4_, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
                case EAST:
                    return StructureNibiruVillagePieces.func_176066_d(start, p_74894_2_, rand, this.boundingBox.minX + p_74894_5_, this.boundingBox.minY + p_74894_4_, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
                }
            }
            return null;
        }

        protected int getAverageGroundLevel(World world, StructureBoundingBox p_74889_2_)
        {
            int i = 0;
            int j = 0;
            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

            for (int k = this.boundingBox.minZ; k <= this.boundingBox.maxZ; ++k)
            {
                for (int l = this.boundingBox.minX; l <= this.boundingBox.maxX; ++l)
                {
                    blockpos$mutableblockpos.set(l, 64, k);

                    if (p_74889_2_.isVecInside(blockpos$mutableblockpos))
                    {
                        i += Math.max(world.getTopSolidOrLiquidBlock(blockpos$mutableblockpos).getY(), world.provider.getAverageGroundLevel());
                        ++j;
                    }
                }
            }

            if (j == 0)
            {
                return -1;
            }
            else
            {
                return i / j;
            }
        }

        protected static boolean canVillageGoDeeper(StructureBoundingBox box)
        {
            return box != null && box.minY > 10;
        }

        protected void spawnVillagers(World world, StructureBoundingBox box, int x, int y, int z, int count)
        {
            if (this.villagersSpawned < count)
            {
                for (int i = this.villagersSpawned; i < count; ++i)
                {
                    int j = this.getXWithOffset(x + i, z);
                    int k = this.getYWithOffset(y);
                    int l = this.getZWithOffset(x + i, z);

                    if (!box.isVecInside(new BlockPos(j, k, l)))
                    {
                        break;
                    }
                    ++this.villagersSpawned;
                    EntityNibiruVillager entityvillager = new EntityNibiruVillager(world);
                    entityvillager.setLocationAndAngles(j + 0.5D, k, l + 0.5D, 0.0F, 0.0F);
                    entityvillager.onInitialSpawn(world.getDifficultyForLocation(new BlockPos(entityvillager)), (IEntityLivingData)null);
                    entityvillager.setProfession(this.chooseProfession(i, entityvillager.getProfession()));
                    world.spawnEntityInWorld(entityvillager);
                }
            }
        }

        protected int chooseProfession(int spawnCount, int profession)
        {
            return profession;
        }

        protected IBlockState getBiomeSpecificBlockState(IBlockState state)
        {
            if (this.isDesertVillage)
            {
                if (state.getBlock() == NibiruBlocks.NIBIRU_LOG)
                {
                    return NibiruBlocks.NIBIRU_SANDSTONE.getDefaultState();
                }
                if (state == NibiruBlocks.NIBIRU_BLOCK.getStateFromMeta(1))
                {
                    return NibiruBlocks.NIBIRU_SANDSTONE.getDefaultState();
                }
                if (state == NibiruBlocks.NIBIRU_BLOCK.getStateFromMeta(2))
                {
                    return NibiruBlocks.NIBIRU_SANDSTONE.getStateFromMeta(1);
                }
                if (state.getBlock() == NibiruBlocks.NIBIRU_PLANKS)
                {
                    return NibiruBlocks.NIBIRU_SANDSTONE.getStateFromMeta(2);
                }
                if (state.getBlock() == NibiruBlocks.INFECTED_OAK_STAIRS)
                {
                    return NibiruBlocks.NIBIRU_SANDSTONE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, state.getValue(BlockStairs.FACING));
                }
                if (state.getBlock() == NibiruBlocks.NIBIRU_COBBLESTONE_STAIRS)
                {
                    return NibiruBlocks.NIBIRU_SANDSTONE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, state.getValue(BlockStairs.FACING));
                }
                if (state.getBlock() == NibiruBlocks.INFECTED_GRAVEL)
                {
                    return NibiruBlocks.NIBIRU_SANDSTONE.getDefaultState();
                }
            }
            if (this.isGreenVeinVillage)
            {
                if (state.getBlock() == NibiruBlocks.NIBIRU_BLOCK)
                {
                    return NibiruBlocks.TERRASTONE.getDefaultState();
                }
                if (state.getBlock() == NibiruBlocks.INFECTED_VINES)
                {
                    return Blocks.AIR.getDefaultState();
                }
                if (state.getBlock() == NibiruBlocks.NIBIRU_LOG)
                {
                    return NibiruBlocks.NIBIRU_LOG.getStateFromMeta(3);
                }
                if (state.getBlock() == NibiruBlocks.NIBIRU_FENCE)
                {
                    return NibiruBlocks.NIBIRU_FENCE.getStateFromMeta(2);
                }
                if (state.getBlock() == NibiruBlocks.NIBIRU_PLANKS)
                {
                    return NibiruBlocks.NIBIRU_PLANKS.getStateFromMeta(2);
                }
                if (state.getBlock() == NibiruBlocks.NIBIRU_BOOKSHELF)
                {
                    return NibiruBlocks.NIBIRU_BOOKSHELF.getStateFromMeta(1);
                }
                if (state.getBlock() == NibiruBlocks.NIBIRU_CRAFTING_TABLE)
                {
                    return NibiruBlocks.NIBIRU_CRAFTING_TABLE.getStateFromMeta(1);
                }
                if (state.getBlock() == NibiruBlocks.INFECTED_OAK_STAIRS)
                {
                    return NibiruBlocks.ALIEN_BERRY_OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, state.getValue(BlockStairs.FACING));
                }
                if (state.getBlock() == NibiruBlocks.NIBIRU_COBBLESTONE_STAIRS)
                {
                    return NibiruBlocks.TERRASTONE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, state.getValue(BlockStairs.FACING));
                }
                if (state.getBlock() == NibiruBlocks.INFECTED_WATER_FLUID_BLOCK)
                {
                    return NibiruBlocks.PURIFY_WATER_FLUID_BLOCK.getDefaultState();
                }
                if (state.getBlock() == NibiruBlocks.INFECTED_GRAVEL)
                {
                    return NibiruBlocks.PURIFY_GRAVEL.getDefaultState();
                }
                if (state.getBlock() == NibiruBlocks.INFECTED_PRISMARINE)
                {
                    return NibiruBlocks.TERRASTONE.getDefaultState();
                }
                if (state.getBlock() == NibiruBlocks.INFECTED_VINES_DIRT)
                {
                    return NibiruBlocks.TERRASTONE.getDefaultState();
                }
                if (state.getBlock() == NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB)
                {
                    return NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB.getDefaultState().withProperty(BlockHalfInfectedStoneBricksSlab.VARIANT, BlockHalfInfectedStoneBricksSlab.BlockType.TERRASTONE_SLAB);
                }
                if (state.getBlock() == NibiruBlocks.DOUBLE_INFECTED_STONE_BRICKS_SLAB)
                {
                    return NibiruBlocks.DOUBLE_INFECTED_STONE_BRICKS_SLAB.getDefaultState().withProperty(BlockHalfInfectedStoneBricksSlab.VARIANT, BlockHalfInfectedStoneBricksSlab.BlockType.TERRASTONE_SLAB);
                }
                if (state.getBlock() == NibiruBlocks.NIBIRU_FURNACE)
                {
                    return NibiruBlocks.TERRASTONE_FURNACE.getDefaultState();
                }
            }
            return state;
        }

        @Override
        protected void setBlockState(World world, IBlockState blockstateIn, int x, int y, int z, StructureBoundingBox boundingboxIn)
        {
            IBlockState iblockstate = this.getBiomeSpecificBlockState(blockstateIn);
            super.setBlockState(world, iblockstate, x, y, z, boundingboxIn);
        }

        @Override
        protected void fillWithBlocks(World world, StructureBoundingBox boundingboxIn, int xMin, int yMin, int zMin, int xMax, int yMax, int zMax, IBlockState boundaryBlockState, IBlockState insideBlockState, boolean existingOnly)
        {
            IBlockState iblockstate = this.getBiomeSpecificBlockState(boundaryBlockState);
            IBlockState iblockstate1 = this.getBiomeSpecificBlockState(insideBlockState);
            super.fillWithBlocks(world, boundingboxIn, xMin, yMin, zMin, xMax, yMax, zMax, iblockstate, iblockstate1, existingOnly);
        }

        @Override
        protected void replaceAirAndLiquidDownwards(World world, IBlockState blockstateIn, int x, int y, int z, StructureBoundingBox boundingboxIn)
        {
            IBlockState iblockstate = this.getBiomeSpecificBlockState(blockstateIn);
            super.replaceAirAndLiquidDownwards(world, iblockstate, x, y, z, boundingboxIn);
        }

        protected void setIsDesert(boolean desert)
        {
            this.isDesertVillage = desert;
        }

        protected void setIsGreenVein(boolean greenVein)
        {
            this.isGreenVeinVillage = greenVein;
        }
    }

    public static class Well extends StructureNibiruVillagePieces.Village
    {
        public Well() {}

        public Well(StructureNibiruVillagePieces.Start start, int p_i2109_2_, Random rand, int p_i2109_4_, int p_i2109_5_)
        {
            super(start, p_i2109_2_);
            this.coordBaseMode = EnumFacing.Plane.HORIZONTAL.random(rand);

            switch (this.coordBaseMode)
            {
            case NORTH:
            case SOUTH:
                this.boundingBox = new StructureBoundingBox(p_i2109_4_, 64, p_i2109_5_, p_i2109_4_ + 6 - 1, 78, p_i2109_5_ + 6 - 1);
                break;
            default:
                this.boundingBox = new StructureBoundingBox(p_i2109_4_, 64, p_i2109_5_, p_i2109_4_ + 6 - 1, 78, p_i2109_5_ + 6 - 1);
            }
        }

        @Override
        public void buildComponent(StructureComponent componentIn, List<StructureComponent> listIn, Random rand)
        {
            StructureNibiruVillagePieces.func_176069_e((StructureNibiruVillagePieces.Start)componentIn, listIn, rand, this.boundingBox.minX - 1, this.boundingBox.maxY - 4, this.boundingBox.minZ + 1, EnumFacing.WEST, this.getComponentType());
            StructureNibiruVillagePieces.func_176069_e((StructureNibiruVillagePieces.Start)componentIn, listIn, rand, this.boundingBox.maxX + 1, this.boundingBox.maxY - 4, this.boundingBox.minZ + 1, EnumFacing.EAST, this.getComponentType());
            StructureNibiruVillagePieces.func_176069_e((StructureNibiruVillagePieces.Start)componentIn, listIn, rand, this.boundingBox.minX + 1, this.boundingBox.maxY - 4, this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType());
            StructureNibiruVillagePieces.func_176069_e((StructureNibiruVillagePieces.Start)componentIn, listIn, rand, this.boundingBox.minX + 1, this.boundingBox.maxY - 4, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
        }

        @Override
        public boolean addComponentParts(World world, Random rand, StructureBoundingBox box)
        {
            if (this.field_143015_k < 0)
            {
                this.field_143015_k = this.getAverageGroundLevel(world, box);

                if (this.field_143015_k < 0)
                {
                    return true;
                }
                this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + 3, 0);
            }

            this.fillWithBlocks(world, box, 1, 0, 1, 4, 12, 4, NibiruBlocks.NIBIRU_BLOCK.getStateFromMeta(1), NibiruBlocks.INFECTED_WATER_FLUID_BLOCK.getDefaultState(), false);
            this.setBlockState(world, Blocks.AIR.getDefaultState(), 2, 12, 2, box);
            this.setBlockState(world, Blocks.AIR.getDefaultState(), 3, 12, 2, box);
            this.setBlockState(world, Blocks.AIR.getDefaultState(), 2, 12, 3, box);
            this.setBlockState(world, Blocks.AIR.getDefaultState(), 3, 12, 3, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), 1, 13, 1, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), 1, 14, 1, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), 4, 13, 1, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), 4, 14, 1, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), 1, 13, 4, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), 1, 14, 4, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), 4, 13, 4, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), 4, 14, 4, box);
            this.fillWithRandomizedBlocks(world, box, 1, 15, 1, 4, 15, 4, false, rand, villageStones);

            for (int i = 0; i <= 5; ++i)
            {
                for (int j = 0; j <= 5; ++j)
                {
                    if (j == 0 || j == 5 || i == 0 || i == 5)
                    {
                        this.setBlockState(world, NibiruBlocks.INFECTED_GRAVEL.getDefaultState(), j, 11, i, box);
                        this.clearCurrentPositionBlocksUpwards(world, j, 12, i, box);
                    }
                }
            }
            return true;
        }
    }

    public static class WoodHut extends StructureNibiruVillagePieces.Village
    {
        private boolean isTallHouse;
        private int tablePosition;

        public WoodHut() {}

        public WoodHut(StructureNibiruVillagePieces.Start start, int p_i45565_2_, Random rand, StructureBoundingBox p_i45565_4_, EnumFacing facing)
        {
            super(start, p_i45565_2_);
            this.coordBaseMode = facing;
            this.boundingBox = p_i45565_4_;
            this.isTallHouse = rand.nextBoolean();
            this.tablePosition = rand.nextInt(3);
        }

        @Override
        protected void writeStructureToNBT(NBTTagCompound tagCompound)
        {
            super.writeStructureToNBT(tagCompound);
            tagCompound.setInteger("T", this.tablePosition);
            tagCompound.setBoolean("C", this.isTallHouse);
        }

        @Override
        protected void readStructureFromNBT(NBTTagCompound tagCompound)
        {
            super.readStructureFromNBT(tagCompound);
            this.tablePosition = tagCompound.getInteger("T");
            this.isTallHouse = tagCompound.getBoolean("C");
        }

        public static StructureNibiruVillagePieces.WoodHut func_175853_a(StructureNibiruVillagePieces.Start start, List<StructureComponent> p_175853_1_, Random rand, int p_175853_3_, int p_175853_4_, int p_175853_5_, EnumFacing facing, int p_175853_7_)
        {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175853_3_, p_175853_4_, p_175853_5_, 0, 0, 0, 4, 6, 5, facing);
            return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_175853_1_, structureboundingbox) == null ? new StructureNibiruVillagePieces.WoodHut(start, p_175853_7_, rand, structureboundingbox, facing) : null;
        }

        @Override
        public boolean addComponentParts(World world, Random rand, StructureBoundingBox box)
        {
            if (this.field_143015_k < 0)
            {
                this.field_143015_k = this.getAverageGroundLevel(world, box);

                if (this.field_143015_k < 0)
                {
                    return true;
                }
                this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + 6 - 1, 0);
            }

            this.fillWithBlocks(world, box, 1, 1, 1, 3, 5, 4, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.fillWithRandomizedBlocks(world, box, 0, 0, 0, 3, 0, 4, false, rand, villageStones);
            this.fillWithRandomizedBlocks(world, box, 1, 0, 1, 2, 0, 3, false, rand, villageDirts);

            if (this.isTallHouse)
            {
                this.fillWithRandomizedBlocks(world, box, 1, 4, 1, 2, 4, 3, false, rand, villageLogs);
            }
            else
            {
                this.fillWithRandomizedBlocks(world, box, 1, 5, 1, 2, 5, 3, false, rand, villageLogs);
            }

            this.setBlockState(world, NibiruBlocks.NIBIRU_LOG.getDefaultState(), 1, 4, 0, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_LOG.getDefaultState(), 2, 4, 0, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_LOG.getDefaultState(), 1, 4, 4, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_LOG.getDefaultState(), 2, 4, 4, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_LOG.getDefaultState(), 0, 4, 1, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_LOG.getDefaultState(), 0, 4, 2, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_LOG.getDefaultState(), 0, 4, 3, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_LOG.getDefaultState(), 3, 4, 1, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_LOG.getDefaultState(), 3, 4, 2, box);
            this.setBlockState(world, NibiruBlocks.NIBIRU_LOG.getDefaultState(), 3, 4, 3, box);
            this.fillWithRandomizedBlocks(world, box, 0, 1, 0, 0, 3, 0, false, rand, villageLogs);
            this.fillWithRandomizedBlocks(world, box, 3, 1, 0, 3, 3, 0, false, rand, villageLogs);
            this.fillWithRandomizedBlocks(world, box, 0, 1, 4, 0, 3, 4, false, rand, villageLogs);
            this.fillWithRandomizedBlocks(world, box, 3, 1, 4, 3, 3, 4, false, rand, villageLogs);
            this.fillWithRandomizedBlocks(world, box, 0, 1, 1, 0, 3, 3, false, rand, villagePlanks);
            this.fillWithRandomizedBlocks(world, box, 3, 1, 1, 3, 3, 3, false, rand, villagePlanks);
            this.fillWithRandomizedBlocks(world, box, 1, 1, 0, 2, 3, 0, false, rand, villagePlanks);
            this.fillWithRandomizedBlocks(world, box, 1, 1, 4, 2, 3, 4, false, rand, villagePlanks);
            this.setBlockState(world, rand.nextInt(5) == 0 ? Blocks.AIR.getDefaultState() : Blocks.GLASS_PANE.getDefaultState(), 0, 2, 2, box);
            this.setBlockState(world, rand.nextInt(5) == 0 ? Blocks.AIR.getDefaultState() : Blocks.GLASS_PANE.getDefaultState(), 3, 2, 2, box);

            if (this.tablePosition > 0)
            {
                this.setBlockState(world, NibiruBlocks.NIBIRU_FENCE.getDefaultState(), this.tablePosition, 1, 3, box);
                this.setBlockState(world, Blocks.WOODEN_PRESSURE_PLATE.getDefaultState(), this.tablePosition, 2, 3, box);
            }

            this.setBlockState(world, Blocks.AIR.getDefaultState(), 1, 1, 0, box);
            this.setBlockState(world, Blocks.AIR.getDefaultState(), 1, 2, 0, box);
            this.placeDoorCurrentPosition(world, box, rand, 1, 1, 0, EnumFacing.getHorizontal(this.getMetadataWithOffset(NibiruBlocks.INFECTED_DOOR_BLOCK, 1)));

            if (this.getBlockStateFromPos(world, 1, 0, -1, box).getBlock().getMaterial() == Material.air && this.getBlockStateFromPos(world, 1, -1, -1, box).getBlock().getMaterial() != Material.air)
            {
                this.setBlockState(world, NibiruBlocks.NIBIRU_COBBLESTONE_STAIRS.getStateFromMeta(this.getMetadataWithOffset(NibiruBlocks.NIBIRU_COBBLESTONE_STAIRS, 3)), 1, 0, -1, box);
            }

            for (int i = 0; i < 5; ++i)
            {
                for (int j = 0; j < 4; ++j)
                {
                    this.clearCurrentPositionBlocksUpwards(world, j, 6, i, box);
                    this.replaceAirAndLiquidDownwards(world, NibiruBlocks.NIBIRU_BLOCK.getStateFromMeta(1), j, -1, i, box);
                }
            }
            if (rand.nextInt(3) == 0)
            {
                this.spawnVillagers(world, box, 1, 1, 2, 1);
            }
            return true;
        }
    }

    static class Stones extends StructureComponent.BlockSelector
    {
        private Stones() {}

        @Override
        public void selectBlocks(Random rand, int x, int y, int z, boolean p_75062_5_)
        {
            if (p_75062_5_)
            {
                float f = rand.nextFloat();

                if (f < 0.05F)
                {
                    this.blockstate = Blocks.web.getDefaultState();
                }
                else if (f < 0.1F)
                {
                    this.blockstate = NibiruBlocks.INFECTED_VINES.getStateFromMeta(15);
                }
                else if (f < 0.125F)
                {
                    this.blockstate = Blocks.air.getDefaultState();
                }
                else if (f < 0.5F)
                {
                    this.blockstate = NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.NIBIRU_VEIN_COBBLESTONE);
                }
                else
                {
                    this.blockstate = NibiruBlocks.NIBIRU_BLOCK.getDefaultState().withProperty(BlockNibiru.VARIANT, BlockNibiru.BlockType.NIBIRU_COBBLESTONE);
                }
            }
            else
            {
                this.blockstate = Blocks.air.getDefaultState();
            }
        }
    }

    static class Planks extends StructureComponent.BlockSelector
    {
        private Planks() {}

        @Override
        public void selectBlocks(Random rand, int x, int y, int z, boolean p_75062_5_)
        {
            if (p_75062_5_)
            {
                float f = rand.nextFloat();

                if (f < 0.05F)
                {
                    this.blockstate = Blocks.web.getDefaultState();
                }
                else if (f < 0.1F)
                {
                    this.blockstate = NibiruBlocks.INFECTED_VINES.getStateFromMeta(15);
                }
                else if (f < 0.125F)
                {
                    this.blockstate = Blocks.air.getDefaultState();
                }
                else if (f < 0.5F)
                {
                    this.blockstate = NibiruBlocks.NIBIRU_PLANKS.getDefaultState();
                }
                else
                {
                    this.blockstate = NibiruBlocks.NIBIRU_PLANKS.getDefaultState();
                }
            }
            else
            {
                this.blockstate = Blocks.air.getDefaultState();
            }
        }
    }

    static class Logs extends StructureComponent.BlockSelector
    {
        private Logs() {}

        @Override
        public void selectBlocks(Random rand, int x, int y, int z, boolean p_75062_5_)
        {
            if (p_75062_5_)
            {
                float f = rand.nextFloat();

                if (f < 0.05F)
                {
                    this.blockstate = Blocks.web.getDefaultState();
                }
                else if (f < 0.1F)
                {
                    this.blockstate = NibiruBlocks.INFECTED_VINES.getStateFromMeta(15);
                }
                else if (f < 0.125F)
                {
                    this.blockstate = Blocks.air.getDefaultState();
                }
                else if (f < 0.5F)
                {
                    this.blockstate = NibiruBlocks.NIBIRU_LOG.getDefaultState();
                }
                else
                {
                    this.blockstate = NibiruBlocks.NIBIRU_LOG.getDefaultState();
                }
            }
            else
            {
                this.blockstate = Blocks.air.getDefaultState();
            }
        }
    }

    static class Dirts extends StructureComponent.BlockSelector
    {
        private Dirts() {}

        @Override
        public void selectBlocks(Random rand, int x, int y, int z, boolean p_75062_5_)
        {
            if (p_75062_5_)
            {
                float f = rand.nextFloat();

                if (f < 0.2F)
                {
                    this.blockstate = NibiruBlocks.INFECTED_DIRT.getStateFromMeta(1);
                }
                else if (f < 0.4F)
                {
                    this.blockstate = NibiruBlocks.INFECTED_VINES_DIRT.getDefaultState();
                }
                else
                {
                    this.blockstate = NibiruBlocks.INFECTED_DIRT.getDefaultState();
                }
            }
            else
            {
                this.blockstate = Blocks.AIR.getDefaultState();
            }
        }
    }
}