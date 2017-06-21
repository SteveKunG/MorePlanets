package stevekung.mods.moreplanets.util.world.gen.dungeon;

import java.util.Random;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.storage.loot.LootTableList;
import stevekung.mods.moreplanets.util.helper.BlockStateHelper;
import stevekung.mods.moreplanets.util.tileentity.TileEntityAncientChestMP;

public class RoomChestMP extends RoomEmptyMP
{
    public RoomChestMP() {}

    public RoomChestMP(DungeonConfigurationMP configuration, Random rand, int blockPosX, int blockPosZ, int sizeX, int sizeY, int sizeZ, EnumFacing entranceDir)
    {
        super(configuration, rand, blockPosX, blockPosZ, sizeX, sizeY, sizeZ, entranceDir);
    }

    public RoomChestMP(DungeonConfigurationMP configuration, Random rand, int blockPosX, int blockPosZ, EnumFacing entranceDir)
    {
        super(configuration, rand, blockPosX, blockPosZ, rand.nextInt(4) + 6, configuration.getRoomHeight(), rand.nextInt(4) + 6, entranceDir);
    }

    @Override
    public boolean addComponentParts(World world, Random rand, StructureBoundingBox boundingBox)
    {
        if (super.addComponentParts(world, rand, boundingBox))
        {
            int chestX = this.sizeX / 2;
            int chestY = 1;
            int chestZ = this.sizeZ / 2;
            this.setBlockState(world, this.configuration.getAncientChestBlock().withProperty(BlockStateHelper.FACING, this.getDirection().getOpposite()), chestX, chestY, chestZ, boundingBox);
            BlockPos blockpos = new BlockPos(this.getXWithOffset(chestX, chestZ), this.getYWithOffset(chestY), this.getZWithOffset(chestX, chestZ));
            TileEntityAncientChestMP chest = (TileEntityAncientChestMP)world.getTileEntity(blockpos);

            if (chest != null)
            {
                chest.setLootTable(LootTableList.CHESTS_SIMPLE_DUNGEON, rand.nextLong());

                /*for (int i = 0; i < chest.getSizeInventory(); ++i)TODO
                {
                    chest.setInventorySlotContents(i, null);
                }
                ChestGenHooks info = ChestGenHooks.getInfo(ItemLootHelper.COMMON_SPACE_DUNGEON);
                WeightedRandomChestContent.generateChestContents(rand, info.getItems(rand), chest, info.getCount(rand));*/
            }
            return true;
        }
        return false;
    }
}