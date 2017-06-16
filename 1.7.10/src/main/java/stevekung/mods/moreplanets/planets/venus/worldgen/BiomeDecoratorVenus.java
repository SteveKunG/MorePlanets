/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.venus.worldgen;

import micdoodle8.mods.galacticraft.api.prefab.world.gen.BiomeDecoratorSpace;
import micdoodle8.mods.galacticraft.core.world.gen.WorldGenMinableMeta;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.core.worldgen.feature.WorldGenLiquidLakes;
import stevekung.mods.moreplanets.planets.venus.blocks.VenusBlocks;
import stevekung.mods.moreplanets.planets.venus.worldgen.feature.WorldGenSurfaceLava;

public class BiomeDecoratorVenus extends BiomeDecoratorSpace
{
    private World world;
    private WorldGenerator dirtGen;
    private WorldGenerator ironGen;
    private WorldGenerator leadGen;
    private WorldGenerator sulfurGen;
    private WorldGenerator tinGen;
    private WorldGenerator copperGen;
    private WorldGenerator coalGen;
    private WorldGenerator goldGen;
    private WorldGenerator redstoneGen;
    private WorldGenerator sandGen;
    private WorldGenerator sandGen1;
    private WorldGenerator magmaGen;
    private boolean isDecorating = false;

    private int lavaLakePerChunk;

    public BiomeDecoratorVenus()
    {
        // Block,NumberOfBlock,Meta,IsMeta,FillBlock,FillMeta
        this.dirtGen = new WorldGenMinableMeta(VenusBlocks.venus_block, 32, 1, true, VenusBlocks.venus_block, 2);
        this.ironGen = new WorldGenMinableMeta(VenusBlocks.venus_block, 7, 9, true, VenusBlocks.venus_block, 2);
        this.sulfurGen = new WorldGenMinableMeta(VenusBlocks.venus_block, 7, 4, true, VenusBlocks.venus_block, 2);
        this.leadGen = new WorldGenMinableMeta(VenusBlocks.venus_block, 7, 5, true, VenusBlocks.venus_block, 2);
        this.tinGen = new WorldGenMinableMeta(VenusBlocks.venus_block, 7, 6, true, VenusBlocks.venus_block, 2);
        this.copperGen = new WorldGenMinableMeta(VenusBlocks.venus_block, 7, 7, true, VenusBlocks.venus_block, 2);
        this.coalGen = new WorldGenMinableMeta(VenusBlocks.venus_block, 16, 8, true, VenusBlocks.venus_block, 2);
        this.goldGen = new WorldGenMinableMeta(VenusBlocks.venus_block, 8, 10, true, VenusBlocks.venus_block, 2);
        this.redstoneGen = new WorldGenMinableMeta(VenusBlocks.venus_redstone_ore, 8, 0, true, VenusBlocks.venus_block, 2);
        this.sandGen = new WorldGenMinableMeta(VenusBlocks.venus_sand, 32, 0, true, VenusBlocks.venus_block, 0);
        this.sandGen1 = new WorldGenMinableMeta(VenusBlocks.venus_sand, 32, 0, true, VenusBlocks.venus_block, 1);
        this.magmaGen = new WorldGenMinableMeta(VenusBlocks.venus_magma_rock, 4, 0, true, VenusBlocks.venus_block, 2);

        this.lavaLakePerChunk = 8;
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

    @Override
    protected void decorate()
    {
        if (this.isDecorating)
        {
            return;
        }
        this.isDecorating = true;
        this.generateOre(20, this.dirtGen, 0, 256);
        this.generateOre(20, this.ironGen, 0, 64);
        this.generateOre(20, this.sulfurGen, 0, 64);
        this.generateOre(12, this.leadGen, 0, 48);
        this.generateOre(22, this.tinGen, 0, 60);
        this.generateOre(24, this.copperGen, 0, 75);
        this.generateOre(20, this.coalGen, 0, 256);
        this.generateOre(4, this.goldGen, 0, 32);
        this.generateOre(8, this.redstoneGen, 0, 16);
        this.generateOre(20, this.sandGen, 0, 256);
        this.generateOre(20, this.sandGen1, 0, 256);
        this.generateOre(20, this.magmaGen, 0, 128);

        int i;
        int x;
        int y;
        int z;

        for (i = 0; i < this.lavaLakePerChunk; ++i)
        {
            if (this.rand.nextInt(20) == 0)
            {
                x = this.chunkX + this.rand.nextInt(16) + 8;
                y = this.rand.nextInt(32 - 16) + 16;
                z = this.chunkZ + this.rand.nextInt(16) + 8;
                new WorldGenLiquidLakes(Blocks.lava).generate(this.world, this.rand, x, y, z);
            }
        }
        for (i = 0; i < 16; ++i)
        {
            x = this.chunkX + this.rand.nextInt(16) + 8;
            y = this.rand.nextInt(255);
            z = this.chunkZ + this.rand.nextInt(16) + 8;
            new WorldGenSurfaceLava().generate(this.world, this.rand, x, y, z);
        }
        this.isDecorating = false;
    }
}