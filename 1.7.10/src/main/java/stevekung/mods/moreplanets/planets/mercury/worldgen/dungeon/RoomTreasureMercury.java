/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.mercury.worldgen.dungeon;

import java.util.Random;

import micdoodle8.mods.galacticraft.core.world.gen.dungeon.DungeonBoundingBox;
import micdoodle8.mods.galacticraft.core.world.gen.dungeon.DungeonRoom;
import micdoodle8.mods.galacticraft.core.world.gen.dungeon.MapGenDungeon;
import net.minecraft.block.Block;
import net.minecraftforge.common.util.ForgeDirection;

public class RoomTreasureMercury extends DungeonRoom
{
    int sizeX;
    int sizeZ;

    public RoomTreasureMercury(MapGenDungeon dungeon, int posX, int posY, int posZ, ForgeDirection entranceDir)
    {
        super(dungeon, posX, posY, posZ, entranceDir);

        if (this.worldObj != null)
        {
            Random rand = new Random(this.worldObj.getSeed() * posX * posY * 57 * posZ);
            this.sizeX = rand.nextInt(6) + 7;
            this.sizeZ = rand.nextInt(6) + 7;
        }
    }

    @Override
    public void generate(Block[] chunk, byte[] meta, int cx, int cz) {}

    @Override
    public DungeonBoundingBox getBoundingBox()
    {
        return new DungeonBoundingBox(this.posX, this.posZ, this.posX + this.sizeX, this.posZ + this.sizeZ);
    }

    @Override
    protected void handleTileEntities(Random rand) {}

    @Override
    protected DungeonRoom makeRoom(MapGenDungeon dungeon, int x, int y, int z, ForgeDirection dir)
    {
        return new RoomTreasureMercury(dungeon, x, y, z, dir);
    }
}