/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.polongnius.worldgen;

import micdoodle8.mods.galacticraft.api.prefab.world.gen.BiomeDecoratorSpace;
import micdoodle8.mods.galacticraft.core.world.gen.WorldGenMinableMeta;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.core.worldgen.feature.WorldGenLiquidLakes;
import stevekung.mods.moreplanets.planets.polongnius.blocks.PolongniusBlocks;

public class BiomeDecoratorPolongnius extends BiomeDecoratorSpace
{
    private World world;

    private WorldGenerator dirtGen;
    private WorldGenerator copperGen;
    private WorldGenerator tinGen;
    private WorldGenerator ironGen;
    private WorldGenerator palladiumGen;
    private WorldGenerator floniumGen;
    private WorldGenerator purpleCrystalGen;
    private boolean isDecorating = false;

    private int lavaLakePerChunk;

    public BiomeDecoratorPolongnius()
    {
        // Block,NumberOfBlock,Meta,IsMeta,FillBlock,FillMeta
        this.dirtGen = new WorldGenMinableMeta(PolongniusBlocks.polongnius_block, 32, 1, true, PolongniusBlocks.polongnius_block, 2);
        this.copperGen = new WorldGenMinableMeta(PolongniusBlocks.polongnius_block, 7, 4, true, PolongniusBlocks.polongnius_block, 2);
        this.tinGen = new WorldGenMinableMeta(PolongniusBlocks.polongnius_block, 7, 5, true, PolongniusBlocks.polongnius_block, 2);
        this.ironGen = new WorldGenMinableMeta(PolongniusBlocks.polongnius_block, 8, 6, true, PolongniusBlocks.polongnius_block, 2);
        this.palladiumGen = new WorldGenMinableMeta(PolongniusBlocks.polongnius_block, 6, 7, true, PolongniusBlocks.polongnius_block, 2);
        this.floniumGen = new WorldGenMinableMeta(PolongniusBlocks.polongnius_block, 16, 8, true, PolongniusBlocks.polongnius_block, 2);
        this.purpleCrystalGen = new WorldGenMinableMeta(PolongniusBlocks.polongnius_block, 4, 9, true, PolongniusBlocks.polongnius_block, 2);

        this.lavaLakePerChunk = 2;
    }

    @Override
    public void decorate()
    {
        if (this.isDecorating)
        {
            return;
        }
        this.isDecorating = true;
        this.generateOre(22, this.tinGen, 0, 60);
        this.generateOre(24, this.copperGen, 0, 75);
        this.generateOre(20, this.ironGen, 0, 64);
        this.generateOre(20, this.dirtGen, 0, 256);
        this.generateOre(2, this.purpleCrystalGen, 24, 32);
        this.generateOre(6, this.palladiumGen, 0, 36);
        this.generateOre(4, this.floniumGen, 16, 24);

        int i;
        int x;
        int y;
        int z;

        for (i = 0; i < this.lavaLakePerChunk; ++i)
        {
            if (this.rand.nextInt(10) == 0)
            {
                x = this.chunkX + this.rand.nextInt(16) + 8;
                y = this.rand.nextInt(24 - 16) + 16;
                z = this.chunkZ + this.rand.nextInt(16) + 8;
                new WorldGenLiquidLakes(PolongniusBlocks.cheese_of_milk).generate(this.world, this.rand, x, y, z);
            }
        }
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