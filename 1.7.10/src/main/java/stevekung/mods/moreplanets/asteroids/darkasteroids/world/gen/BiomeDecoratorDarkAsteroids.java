package stevekung.mods.moreplanets.asteroids.darkasteroids.world.gen;

import micdoodle8.mods.galacticraft.api.prefab.world.gen.BiomeDecoratorSpace;
import micdoodle8.mods.galacticraft.core.world.gen.WorldGenMinableMeta;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.asteroids.darkasteroids.blocks.DarkAsteroidsBlocks;

public class BiomeDecoratorDarkAsteroids extends BiomeDecoratorSpace
{
    private World world;
    private WorldGenerator sandGen;
    private WorldGenerator sandGen1;
    private WorldGenerator sandGen2;
    private boolean isDecorating = false;

    public BiomeDecoratorDarkAsteroids()
    {
        // Block,NumberOfBlock,Meta,IsMeta,FillBlock,FillMeta
        this.sandGen = new WorldGenMinableMeta(DarkAsteroidsBlocks.dark_asteroid_quicksand, 8, 0, true, DarkAsteroidsBlocks.dark_asteroid_block, 0);
        this.sandGen1 = new WorldGenMinableMeta(DarkAsteroidsBlocks.dark_asteroid_quicksand, 8, 0, true, DarkAsteroidsBlocks.dark_asteroid_block, 1);
        this.sandGen2 = new WorldGenMinableMeta(DarkAsteroidsBlocks.dark_asteroid_quicksand, 8, 0, true, DarkAsteroidsBlocks.dark_asteroid_block, 2);
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
        this.generateOre(16, this.sandGen, 0, 255);
        this.generateOre(16, this.sandGen1, 0, 255);
        this.generateOre(16, this.sandGen2, 0, 255);
        this.isDecorating = false;
    }
}