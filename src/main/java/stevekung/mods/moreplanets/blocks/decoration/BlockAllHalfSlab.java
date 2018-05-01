package stevekung.mods.moreplanets.blocks.decoration;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import stevekung.mods.moreplanets.utils.blocks.BlockSlabMP;
import stevekung.mods.moreplanets.utils.blocks.EnumSortCategoryBlock;

public class BlockAllHalfSlab extends BlockSlabMP
{
    public BlockType type;
    protected BlockSlabMP halfSlab;
    private BlockSlabMP doubleSlab;

    public BlockAllHalfSlab(String name, BlockType type)
    {
        this(name, type, Material.ROCK);
    }

    public BlockAllHalfSlab(String name, BlockType type, Material material)
    {
        super(material);
        this.setUnlocalizedName(name);
        this.useNeighborBrightness = true;
        this.type = type;

        if (type.isDungeonBrick())
        {
            this.setHardness(4.0F);
            this.setResistance(40.0F);
        }
        else if (type.isWood())
        {
            this.setHardness(2.0F);
            this.setResistance(5.0F);
            this.setSoundType(SoundType.WOOD);
        }
        else if (type == BlockType.INFECTED_SANDSTONE_SLAB || type == BlockType.INFECTED_CUT_SANDSTONE_SLAB)
        {
            this.setHardness(0.8F);
        }
        else if (type == BlockType.INFECTED_PRISMARINE_SLAB || type == BlockType.INFECTED_PRISMARINE_BRICKS_SLAB || type == BlockType.INFECTED_DARK_PRISMARINE_SLAB)
        {
            this.setHardness(1.5F);
            this.setResistance(10.0F);
        }
        else if (type == BlockType.INFECTED_STONE_BRICKS_SLAB || type == BlockType.INFECTED_VEIN_STONE_BRICKS_SLAB || type == BlockType.INFECTED_CRACKED_STONE_BRICKS_SLAB || type == BlockType.TERRASTONE_SLAB)
        {
            this.setHardness(1.5F);
        }
        else
        {
            this.setHardness(2.0F);
        }
        this.setDefaultState(this.blockState.getBaseState().withProperty(HALF, BlockSlab.EnumBlockHalf.BOTTOM));
    }

    public BlockAllHalfSlab(Material material)
    {
        super(material);
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(HALF, meta == 0 ? BlockSlab.EnumBlockHalf.BOTTOM : BlockSlab.EnumBlockHalf.TOP);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(HALF) == BlockSlab.EnumBlockHalf.TOP ? 1 : 0;
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, HALF);
    }

    @Override
    public EnumSortCategoryBlock getBlockCategory()
    {
        return this.type.isDungeonBrick() ? EnumSortCategoryBlock.SLAB_DUNGEON_BRICK : EnumSortCategoryBlock.SLAB_STONE;
    }

    @Override
    public BlockSlabMP getHalf()
    {
        return this.halfSlab;
    }

    @Override
    public BlockSlabMP getDouble()
    {
        return this.doubleSlab;
    }

    @Override
    public String getName()
    {
        return this.type.toString();
    }

    public BlockSlabMP setHalf(BlockSlabMP halfSlab)
    {
        this.halfSlab = halfSlab;
        return this;
    }

    public BlockSlabMP setDouble(BlockSlabMP doubleSlab)
    {
        this.doubleSlab = doubleSlab;
        return this;
    }

    public static enum BlockType
    {
        DIONA_COBBLESTONE_SLAB,
        CHALOS_COBBLESTONE_SLAB,
        NIBIRU_COBBLESTONE_SLAB,
        DIONA_DUNGEON_BRICK_SLAB(true, false),
        CHALOS_DUNGEON_BRICK_SLAB(true, false),
        NIBIRU_DUNGEON_BRICK_SLAB(true, false),
        CHEESE_SPORE_WOOD_SLAB(false, true),
        INFECTED_OAK_WOOD_SLAB(false, true),
        ALIEN_BERRY_OAK_WOOD_SLAB(false, true),
        INFECTED_PRISMARINE_SLAB,
        INFECTED_PRISMARINE_BRICKS_SLAB,
        INFECTED_DARK_PRISMARINE_SLAB,
        INFECTED_STONE_BRICKS_SLAB,
        INFECTED_VEIN_STONE_BRICKS_SLAB,
        INFECTED_CRACKED_STONE_BRICKS_SLAB,
        TERRASTONE_SLAB,
        INFECTED_SANDSTONE_SLAB,
        INFECTED_CUT_SANDSTONE_SLAB;

        private boolean isDungeonBrick;
        private boolean isWood;

        BlockType() {}

        BlockType(boolean isDungeonBrick, boolean isWood)
        {
            this.isDungeonBrick = isDungeonBrick;
            this.isWood = isWood;
        }

        @Override
        public String toString()
        {
            return this.name().toLowerCase();
        }

        public boolean isDungeonBrick()
        {
            return this.isDungeonBrick;
        }

        public boolean isWood()
        {
            return this.isWood;
        }
    }
}