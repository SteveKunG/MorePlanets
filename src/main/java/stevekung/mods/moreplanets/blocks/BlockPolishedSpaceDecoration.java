package stevekung.mods.moreplanets.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.util.IStringSerializable;
import stevekung.mods.moreplanets.util.blocks.BlockBaseMP;

public class BlockPolishedSpaceDecoration extends BlockBaseMP
{
    private final BlockType type;

    public BlockPolishedSpaceDecoration(String name, BlockType type)
    {
        super(Material.ROCK);
        this.setHardness(1.5F);
        this.setUnlocalizedName(name);
        this.type = type;
    }

    @Override
    public String getName()
    {
        return this.type.toString();
    }

    public static enum BlockType implements IStringSerializable
    {
        POLISHED_TIN_DECORATION_BLOCK,
        POLISHED_ALUMINUM_DECORATION_BLOCK;

        @Override
        public String toString()
        {
            return this.name().toLowerCase();
        }

        @Override
        public String getName()
        {
            return this.name().toLowerCase();
        }
    }
}