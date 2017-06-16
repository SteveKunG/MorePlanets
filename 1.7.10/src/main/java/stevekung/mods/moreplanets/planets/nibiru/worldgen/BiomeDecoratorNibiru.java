/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.nibiru.worldgen;

import micdoodle8.mods.galacticraft.api.prefab.world.gen.BiomeDecoratorSpace;
import micdoodle8.mods.galacticraft.core.world.gen.WorldGenMinableMeta;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.planets.nibiru.blocks.NibiruBlocks;

public class BiomeDecoratorNibiru extends BiomeDecoratorSpace
{
    private World world;
    private WorldGenerator dirtGen;
    private WorldGenerator redGemGen;
    private WorldGenerator coalGen;
    private WorldGenerator diamondGen;
    private WorldGenerator ichoriusGen;
    private WorldGenerator noriumGen;
    private WorldGenerator heliumGen;
    private WorldGenerator oilOreGen;
    private WorldGenerator wormEggGen;
    private boolean isDecorating = false;

    public BiomeDecoratorNibiru()
    {
        // Block,NumberOfBlock,Meta,IsMeta,FillBlock,FillMeta
        this.dirtGen = new WorldGenMinableMeta(NibiruBlocks.nibiru_block, 32, 1, true, NibiruBlocks.nibiru_block, 2);
        this.ichoriusGen = new WorldGenMinableMeta(NibiruBlocks.nibiru_block, 6, 4, true, NibiruBlocks.nibiru_block, 2);
        this.noriumGen = new WorldGenMinableMeta(NibiruBlocks.nibiru_block, 8, 5, true, NibiruBlocks.nibiru_block, 2);
        this.diamondGen = new WorldGenMinableMeta(NibiruBlocks.nibiru_block, 7, 6, true, NibiruBlocks.nibiru_block, 2);
        this.coalGen = new WorldGenMinableMeta(NibiruBlocks.nibiru_block, 16, 7, true, NibiruBlocks.nibiru_block, 2);
        this.redGemGen = new WorldGenMinableMeta(NibiruBlocks.nibiru_block, 4, 8, true, NibiruBlocks.nibiru_block, 2);
        this.heliumGen = new WorldGenMinableMeta(NibiruBlocks.helium_block, 8, 0, true, NibiruBlocks.nibiru_block, 2);
        this.oilOreGen = new WorldGenMinableMeta(NibiruBlocks.oil_rock, 4, 1, true, NibiruBlocks.nibiru_block, 2);
        this.wormEggGen = new WorldGenMinableMeta(NibiruBlocks.infected_worm_egg_rock, 1, 11, true, NibiruBlocks.nibiru_block, 2);
    }

    @Override
    protected void decorate()
    {
        if (this.isDecorating)
        {
            return;
        }
        this.isDecorating = true;
        this.generateOre(1, this.diamondGen, 0, 16);
        this.generateOre(20, this.coalGen, 0, 128);
        this.generateOre(20, this.dirtGen, 0, 256);
        this.generateOre(12, this.redGemGen, 0, 24);
        this.generateOre(8, this.ichoriusGen, 16, 32);
        this.generateOre(15, this.noriumGen, 0, 64);
        this.generateOre(20, this.heliumGen, 0, 256);
        this.generateOre(10, this.oilOreGen, 0, 128);
        this.generateOre(1, this.wormEggGen, 0, 64);
        this.isDecorating = false;
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