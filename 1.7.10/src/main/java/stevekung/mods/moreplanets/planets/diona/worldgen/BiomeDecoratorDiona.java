/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.diona.worldgen;

import micdoodle8.mods.galacticraft.api.prefab.world.gen.BiomeDecoratorSpace;
import micdoodle8.mods.galacticraft.core.world.gen.WorldGenMinableMeta;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.planets.diona.blocks.DionaBlocks;

public class BiomeDecoratorDiona extends BiomeDecoratorSpace
{
    private World world;

    private WorldGenerator dirtGen;
    private WorldGenerator quontoniumGen;
    private WorldGenerator fronisiumGen;
    private WorldGenerator aluminumGen;
    private WorldGenerator siliconGen;
    private WorldGenerator tinGen;
    private WorldGenerator copperGen;
    private boolean isDecorating = false;

    public BiomeDecoratorDiona()
    {
        // Block,NumberOfBlock,Meta,IsMeta,FillBlock,FillMeta
        this.dirtGen = new WorldGenMinableMeta(DionaBlocks.diona_block, 32, 1, true, DionaBlocks.diona_block, 2);
        this.quontoniumGen = new WorldGenMinableMeta(DionaBlocks.diona_block, 8, 4, true, DionaBlocks.diona_block, 2);
        this.fronisiumGen = new WorldGenMinableMeta(DionaBlocks.diona_block, 4, 5, true, DionaBlocks.diona_block, 2);
        this.tinGen = new WorldGenMinableMeta(DionaBlocks.diona_block, 7, 6, true, DionaBlocks.diona_block, 2);
        this.copperGen = new WorldGenMinableMeta(DionaBlocks.diona_block, 7, 7, true, DionaBlocks.diona_block, 2);
        this.siliconGen = new WorldGenMinableMeta(DionaBlocks.diona_block, 7, 8, true, DionaBlocks.diona_block, 2);
        this.aluminumGen = new WorldGenMinableMeta(DionaBlocks.diona_block, 7, 9, true, DionaBlocks.diona_block, 2);
    }

    @Override
    protected void decorate()
    {
        if (this.isDecorating)
        {
            return;
        }
        this.isDecorating = true;
        this.generateOre(12, this.fronisiumGen, 0, 36);
        this.generateOre(16, this.quontoniumGen, 0, 64);
        this.generateOre(18, this.aluminumGen, 0, 45);
        this.generateOre(22, this.tinGen, 0, 60);
        this.generateOre(24, this.copperGen, 0, 75);
        this.generateOre(3, this.siliconGen, 0, 25);
        this.generateOre(20, this.dirtGen, 0, 256);
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