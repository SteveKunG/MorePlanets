/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.io.worldgen;

import micdoodle8.mods.galacticraft.api.prefab.world.gen.BiomeDecoratorSpace;
import micdoodle8.mods.galacticraft.core.world.gen.WorldGenMinableMeta;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.moons.io.blocks.IoBlocks;

public class BiomeDecoratorIo extends BiomeDecoratorSpace
{
    private WorldGenerator sulfurGen;

    private World world;

    public BiomeDecoratorIo()
    {
        this.sulfurGen = new WorldGenMinableMeta(IoBlocks.io_block, 16, 8, true, IoBlocks.io_block, 2);
    }

    @Override
    public void decorate()
    {
        this.generateOre(32, this.sulfurGen, 0, 256);
    }

    @Override
    protected void setCurrentWorld(World world)
    {
        this.world = world;
    }

    @Override
    protected World getCurrentWorld()
    {
        return this.world;
    }
}