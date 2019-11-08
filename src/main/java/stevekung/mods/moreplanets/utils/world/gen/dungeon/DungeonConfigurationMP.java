package stevekung.mods.moreplanets.utils.world.gen.dungeon;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.gen.structure.StructureComponent;
import stevekung.mods.moreplanets.utils.LoggerMP;

public class DungeonConfigurationMP
{
    private IBlockState brickBlock;
    private IBlockState glowstoneBlock;
    private IBlockState webBlock;
    private IBlockState torchBlock;
    private IBlockState ancientChestBlock;
    private int yPosition;
    private int hallwayLengthMin;
    private int hallwayLengthMax;
    private int hallwayHeight;
    private int roomHeight;
    private Class<? extends StructureComponent> bossRoom;
    private Class<? extends StructureComponent> treasureRoom;
    private Class<? extends StructureComponent> spawnerRoom;
    private Class<? extends StructureComponent> chestRoom;

    public DungeonConfigurationMP() {}

    public DungeonConfigurationMP(IBlockState brickBlock, IBlockState glowstoneBlock, IBlockState webBlock, IBlockState torchBlock, IBlockState ancientChestBlock, int yPosition, int hallwayLengthMin, int hallwayLengthMax, int hallwayHeight, int roomHeight, Class<? extends StructureComponent> bossRoom, Class<? extends StructureComponent> treasureRoom, Class<? extends StructureComponent> spawnerRoom, Class<? extends StructureComponent> chestRoom)
    {
        this.brickBlock = brickBlock;
        this.glowstoneBlock = glowstoneBlock;
        this.webBlock = webBlock;
        this.torchBlock = torchBlock;
        this.ancientChestBlock = ancientChestBlock;
        this.yPosition = yPosition;
        this.hallwayLengthMin = hallwayLengthMin;
        this.hallwayLengthMax = hallwayLengthMax;
        this.hallwayHeight = hallwayHeight;
        this.roomHeight = roomHeight;
        this.bossRoom = bossRoom;
        this.treasureRoom = treasureRoom;
        this.spawnerRoom = spawnerRoom;
        this.chestRoom = chestRoom;
    }

    public void writeToNBT(NBTTagCompound tagCompound)
    {
        tagCompound.setString("DungeonBrickBlock", Block.REGISTRY.getNameForObject(this.brickBlock.getBlock()).toString());
        tagCompound.setString("GlowstoneBlock", Block.REGISTRY.getNameForObject(this.glowstoneBlock.getBlock()).toString());
        tagCompound.setString("WebBlock", Block.REGISTRY.getNameForObject(this.webBlock.getBlock()).toString());
        tagCompound.setString("TorchBlock", Block.REGISTRY.getNameForObject(this.torchBlock.getBlock()).toString());
        tagCompound.setString("AncientChestBlock", Block.REGISTRY.getNameForObject(this.ancientChestBlock.getBlock()).toString());
        tagCompound.setInteger("YPosition", this.yPosition);
        tagCompound.setInteger("HallwayLengthMin", this.hallwayLengthMin);
        tagCompound.setInteger("HallwayLengthMax", this.hallwayLengthMax);
        tagCompound.setInteger("HallwayHeight", this.hallwayHeight);
        tagCompound.setInteger("RoomHeight", this.roomHeight);
        tagCompound.setString("BossRoom", this.bossRoom.getName());
        tagCompound.setString("TreasureRoom", this.treasureRoom.getName());
        tagCompound.setString("SpawnerRoom", this.spawnerRoom.getName());
        tagCompound.setString("ChestRoom", this.chestRoom.getName());
    }

    public void readFromNBT(NBTTagCompound tagCompound)
    {
        try
        {
            // backward compatibility
            String webBlock = tagCompound.getString("WebBlock");
            String torchBlock = tagCompound.getString("TorchBlock");

            if (webBlock.equals("moreplanets:infected_crystallized_cobweb"))
            {
                webBlock = "moreplanets:infected_purlonite_cobweb";
            }
            if (torchBlock.equals("moreplanets:infected_crystallized_torch"))
            {
                torchBlock = "moreplanets:infected_purlonite_torch";
            }

            this.brickBlock = Block.getBlockFromName(tagCompound.getString("DungeonBrickBlock")).getDefaultState();
            this.glowstoneBlock = Block.getBlockFromName(tagCompound.getString("GlowstoneBlock")).getDefaultState();
            this.webBlock = Block.getBlockFromName(webBlock).getDefaultState();
            this.torchBlock = Block.getBlockFromName(torchBlock).getDefaultState();
            this.ancientChestBlock = Block.getBlockFromName(tagCompound.getString("AncientChestBlock")).getDefaultState();
            this.yPosition = tagCompound.getInteger("YPosition");
            this.hallwayLengthMin = tagCompound.getInteger("HallwayLengthMin");
            this.hallwayLengthMax = tagCompound.getInteger("HallwayLengthMax");
            this.hallwayHeight = tagCompound.getInteger("HallwayHeight");
            this.roomHeight = tagCompound.getInteger("RoomHeight");
            this.bossRoom = (Class<? extends RoomBossMP>) Class.forName(tagCompound.getString("BossRoom"));
            this.treasureRoom = (Class<? extends RoomTreasureMP>) Class.forName(tagCompound.getString("TreasureRoom"));
            this.spawnerRoom = (Class<? extends RoomEmptyMP>) Class.forName(tagCompound.getString("SpawnerRoom"));
            this.chestRoom = (Class<? extends RoomChestMP>) Class.forName(tagCompound.getString("ChestRoom"));
        }
        catch (Exception e)
        {
            LoggerMP.error("Failed to read dungeon configuration from NBT");
            e.printStackTrace();
        }
    }

    public IBlockState getBrickBlock()
    {
        return this.brickBlock;
    }

    public IBlockState getGlowstoneBlock()
    {
        return this.glowstoneBlock;
    }

    public IBlockState getWebBlock()
    {
        return this.webBlock;
    }

    public IBlockState getTorchBlock()
    {
        return this.torchBlock;
    }

    public IBlockState getAncientChestBlock()
    {
        return this.ancientChestBlock;
    }

    public int getYPosition()
    {
        return this.yPosition;
    }

    public int getHallwayLengthMin()
    {
        return this.hallwayLengthMin;
    }

    public int getHallwayLengthMax()
    {
        return this.hallwayLengthMax;
    }

    public int getHallwayHeight()
    {
        return this.hallwayHeight;
    }

    public int getRoomHeight()
    {
        return this.roomHeight;
    }

    public Class<? extends StructureComponent> getBossRoom()
    {
        return this.bossRoom;
    }

    public Class<? extends StructureComponent> getTreasureRoom()
    {
        return this.treasureRoom;
    }

    public Class<? extends StructureComponent> getSpawnerRoom()
    {
        return this.spawnerRoom;
    }

    public Class<? extends StructureComponent> getChestRoom()
    {
        return this.chestRoom;
    }
}