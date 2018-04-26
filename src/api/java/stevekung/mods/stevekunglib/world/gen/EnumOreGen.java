package stevekung.mods.stevekunglib.world.gen;

public enum EnumOreGen
{
    COAL(17, 20, 0, 128),
    IRON(9, 20, 0, 64),
    GOLD(9, 2, 0, 32),
    REDSTONE(8, 8, 0, 16),
    LAPIS(7, 1, 16, 16),//lapis genCount, blockCount, centerHeight, spread
    DIAMOND(8, 1, 0, 16),
    DIRT(33, 10, 0, 256),
    GRAVEL(33, 8, 0, 256),
    SILICON(7, 3, 0, 25),
    ALUMINUM(7, 18, 0, 45),
    TIN(7, 22, 0, 60),
    COPPER(7, 24, 0, 75);

    private final int genCount;
    private final int blockCount;
    private final int minHeight;
    private final int maxHeight;

    private EnumOreGen(int genCount, int blockCount, int minHeight, int maxHeight)
    {
        this.genCount = genCount;
        this.blockCount = blockCount;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
    }

    public int getGenCount()
    {
        return this.genCount;
    }

    public int getBlockCount()
    {
        return this.blockCount;
    }

    public int getMinHeight()
    {
        return this.minHeight;
    }

    public int getMaxHeight()
    {
        return this.maxHeight;
    }
}