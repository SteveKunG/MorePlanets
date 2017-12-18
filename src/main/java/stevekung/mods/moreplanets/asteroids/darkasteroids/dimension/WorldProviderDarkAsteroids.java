/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.asteroids.darkasteroids.dimension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeMap;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.vector.BlockVec3;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import micdoodle8.mods.galacticraft.planets.asteroids.dimension.AsteroidSaveData;
import micdoodle8.mods.galacticraft.planets.asteroids.dimension.WorldProviderAsteroids;
import micdoodle8.mods.galacticraft.planets.asteroids.entities.EntityAstroMiner;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;
import stevekung.mods.moreplanets.asteroids.darkasteroids.world.gen.ChunkProviderDarkAsteroids;
import stevekung.mods.moreplanets.asteroids.darkasteroids.world.gen.WorldChunkManagerDarkAsteroids;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.util.MPLog;
import stevekung.mods.moreplanets.core.world.IUltraVioletLevel;

public class WorldProviderDarkAsteroids extends WorldProviderAsteroids implements IUltraVioletLevel
{
    //Used to list asteroid centres to external code that needs to know them
    private HashSet<AsteroidData> asteroids = new HashSet();
    private boolean dataNotLoaded = true;
    private AsteroidSaveData datafile;
    private double solarMultiplier = -2.0D;

    @Override
    public CelestialBody getCelestialBody()
    {
        return MorePlanetsCore.darkAsteroids;
    }

    @Override
    public Vector3 getFogColor()
    {
        return new Vector3(0, 0, 0);
    }

    @Override
    public Vector3 getSkyColor()
    {
        return new Vector3(0, 0, 0);
    }

    @Override
    public long getDayLength()
    {
        return 0;
    }

    @Override
    public boolean isDaytime()
    {
        return true;
    }

    @Override
    public Class<? extends IChunkProvider> getChunkProviderClass()
    {
        return ChunkProviderDarkAsteroids.class;
    }

    @Override
    public Class<? extends WorldChunkManager> getWorldChunkManagerClass()
    {
        return WorldChunkManagerDarkAsteroids.class;
    }

    @Override
    public float calculateCelestialAngle(long par1, float par3)
    {
        return 0.25F;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float getStarBrightness(float par1)
    {
        return 1.0F;
    }

    @Override
    public float getGravity()
    {
        return 0.072F;
    }

    @Override
    public double getMeteorFrequency()
    {
        return 10.0D;
    }

    @Override
    public boolean canSpaceshipTierPass(int tier)
    {
        return tier >= 5;
    }

    @Override
    public float getFallDamageModifier()
    {
        return 0.1F;
    }

    @Override
    public float getSoundVolReductionAmount()
    {
        return 15.0F;
    }

    @Override
    public float getThermalLevelModifier()
    {
        return -2.5F;
    }

    @Override
    public void addAsteroid(int x, int y, int z, int size, int core)
    {
        AsteroidData coords = new AsteroidData(x, y, z, size, core);

        if (!this.asteroids.contains(coords))
        {
            if (this.dataNotLoaded)
            {
                this.loadAsteroidSavedData();
            }
            if (!this.asteroids.contains(coords))
            {
                this.addToNBT(this.datafile.datacompound, coords);
                this.asteroids.add(coords);
            }
        }
    }

    @Override
    public void removeAsteroid(int x, int y, int z)
    {
        AsteroidData coords = new AsteroidData(x, y, z);

        if (this.asteroids.contains(coords))
        {
            this.asteroids.remove(coords);

            if (this.dataNotLoaded)
            {
                this.loadAsteroidSavedData();
            }
            this.writeToNBT(this.datafile.datacompound);
        }
    }

    private void loadAsteroidSavedData()
    {
        this.datafile = (AsteroidSaveData) this.worldObj.loadItemData(AsteroidSaveData.class, AsteroidSaveData.saveDataID);

        if (this.datafile == null)
        {
            this.datafile = new AsteroidSaveData("");
            this.worldObj.setItemData(AsteroidSaveData.saveDataID, this.datafile);
            this.writeToNBT(this.datafile.datacompound);
        }
        else
        {
            this.readFromNBT(this.datafile.datacompound);
        }
        this.dataNotLoaded = false;
    }

    private void readFromNBT(NBTTagCompound nbt)
    {
        NBTTagList coordList = nbt.getTagList("coords", 10);

        if (coordList.tagCount() > 0)
        {
            for (int j = 0; j < coordList.tagCount(); j++)
            {
                NBTTagCompound tag1 = coordList.getCompoundTagAt(j);

                if (tag1 != null)
                {
                    this.asteroids.add(AsteroidData.readFromNBT(tag1));
                }
            }
        }
    }

    private void writeToNBT(NBTTagCompound nbt)
    {
        NBTTagList coordList = new NBTTagList();

        for (AsteroidData coords : this.asteroids)
        {
            NBTTagCompound tag = new NBTTagCompound();
            coords.writeToNBT(tag);
            coordList.appendTag(tag);
        }
        nbt.setTag("coords", coordList);
        this.datafile.markDirty();
    }

    private void addToNBT(NBTTagCompound nbt, AsteroidData coords)
    {
        NBTTagList coordList = nbt.getTagList("coords", 10);
        NBTTagCompound tag = new NBTTagCompound();
        coords.writeToNBT(tag);
        coordList.appendTag(tag);
        nbt.setTag("coords", coordList);
        this.datafile.markDirty();
    }

    @Override
    public BlockVec3 getClosestAsteroidXZ(int x, int y, int z)
    {
        if (this.dataNotLoaded)
        {
            this.loadAsteroidSavedData();
        }
        if (this.asteroids.size() == 0)
        {
            return null;
        }

        BlockVec3 result = null;
        AsteroidData resultRoid = null;
        int lowestDistance = Integer.MAX_VALUE;

        for (AsteroidData test : this.asteroids)
        {
            if ((test.sizeAndLandedFlag & 128) > 0)
            {
                continue;
            }

            int dx = x - test.centre.x;
            int dz = z - test.centre.z;
            int a = dx * dx + dz * dz;

            if (a < lowestDistance)
            {
                lowestDistance = a;
                result = test.centre;
                resultRoid = test;
            }
        }

        if (result == null)
        {
            return null;
        }
        resultRoid.sizeAndLandedFlag |= 128;
        this.writeToNBT(this.datafile.datacompound);
        return result.clone();
    }

    @Override
    public ArrayList<BlockVec3> getClosestAsteroidsXZ(int x, int y, int z, int facing, int count)
    {
        if (this.dataNotLoaded)
        {
            this.loadAsteroidSavedData();
        }
        if (this.asteroids.size() == 0)
        {
            return null;
        }

        TreeMap<Integer, BlockVec3> targets = new TreeMap();

        for (AsteroidData roid : this.asteroids)
        {
            BlockVec3 test = roid.centre;

            switch (facing)
            {
            case 2:
                if (z - 16 < test.z)
                {
                    continue;
                }
                break;
            case 3:
                if (z + 16 > test.z)
                {
                    continue;
                }
                break;
            case 4:
                if (x - 16 < test.x)
                {
                    continue;
                }
                break;
            case 5:
                if (x + 16 > test.x)
                {
                    continue;
                }
                break;
            }

            int dx = x - test.x;
            int dz = z - test.z;
            int a = dx * dx + dz * dz;

            if (a < 262144)
            {
                targets.put(a, test);
            }
        }

        int max = Math.max(count,  targets.size());

        if (max <= 0)
        {
            return null;
        }

        ArrayList<BlockVec3> returnValues = new ArrayList();
        int i = 0;
        int offset = EntityAstroMiner.MINE_LENGTH_AST / 2;

        for (BlockVec3 target : targets.values())
        {
            BlockVec3 coords = target.clone();
            MPLog.debug("Found nearby asteroid at "+ target.toString());

            switch (facing)
            {
            case 2:
                coords.z += offset;
                break;
            case 3:
                coords.z -= offset;
                break;
            case 4:
                coords.x += offset;
                break;
            case 5:
                coords.x -= offset;
                break;
            }

            returnValues.add(coords);

            if (++i >= count)
            {
                break;
            }
        }
        return returnValues;
    }

    @Override
    public float getWindLevel()
    {
        return 0.05F;
    }

    @Override
    public int getActualHeight()
    {
        return 256;
    }

    @Override
    public void registerWorldChunkManager()
    {
        super.registerWorldChunkManager();
        this.hasNoSky = true;
    }

    @Override
    public double getSolarEnergyMultiplier()
    {
        if (this.solarMultiplier < 0D)
        {
            double s = this.getSolarSize();
            this.solarMultiplier = s * s * s * ConfigManagerCore.spaceStationEnergyScalar;
        }
        return this.solarMultiplier;
    }

    @Override
    public double getUltraVioletEnergyMultiplie()
    {
        return 0.5D;
    }

    public static class AsteroidData
    {
        protected BlockVec3 centre;
        protected int sizeAndLandedFlag = 15;
        protected int coreAndSpawnedFlag = -2;

        public AsteroidData(int x, int y, int z)
        {
            this.centre = new BlockVec3(x, y, z);
        }

        public AsteroidData(int x, int y, int z, int size, int core)
        {
            this.centre = new BlockVec3(x, y, z);
            this.sizeAndLandedFlag = size;
            this.coreAndSpawnedFlag = core;
        }

        public AsteroidData(BlockVec3 bv)
        {
            this.centre = bv;
        }

        @Override
        public int hashCode()
        {
            if (this.centre != null)
            {
                return this.centre.hashCode();
            }
            else
            {
                return 0;
            }
        }

        @Override
        public boolean equals(Object o)
        {
            if (o instanceof AsteroidData)
            {
                BlockVec3 vector = ((AsteroidData) o).centre;
                return this.centre.x == vector.x && this.centre.y == vector.y && this.centre.z == vector.z;
            }
            if (o instanceof BlockVec3)
            {
                BlockVec3 vector = (BlockVec3) o;
                return this.centre.x == vector.x && this.centre.y == vector.y && this.centre.z == vector.z;
            }
            return false;
        }

        public NBTTagCompound writeToNBT(NBTTagCompound tag)
        {
            tag.setInteger("x", this.centre.x);
            tag.setInteger("y", this.centre.y);
            tag.setInteger("z", this.centre.z);
            tag.setInteger("coreAndFlag", this.coreAndSpawnedFlag);
            tag.setInteger("sizeAndFlag", this.sizeAndLandedFlag);
            return tag;
        }

        public static AsteroidData readFromNBT(NBTTagCompound tag)
        {
            BlockVec3 tempVector = new BlockVec3();
            tempVector.x = tag.getInteger("x");
            tempVector.y = tag.getInteger("y");
            tempVector.z = tag.getInteger("z");
            AsteroidData roid = new AsteroidData(tempVector);

            if (tag.hasKey("coreAndFlag"))
            {
                roid.coreAndSpawnedFlag = tag.getInteger("coreAndFlag");
            }
            if (tag.hasKey("sizeAndFlag"))
            {
                roid.sizeAndLandedFlag = tag.getInteger("sizeAndFlag");
            }
            return roid;
        }
    }
}