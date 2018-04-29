package stevekung.mods.moreplanets.utils.blocks;

import java.util.Random;

import micdoodle8.mods.galacticraft.api.block.IDetectableResource;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import stevekung.mods.moreplanets.module.planets.chalos.items.ChalosItems;
import stevekung.mods.moreplanets.module.planets.diona.items.DionaItems;

public class BlockDropableOre extends BlockBasicMP implements IDetectableResource
{
    private BlockType type;

    public BlockDropableOre(String name, BlockType type)
    {
        super(Material.ROCK);
        this.type = type;
        this.setUnlocalizedName(name);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        if (this.type == BlockType.SETRORIUM_ORE)
        {
            return DionaItems.SETRORIUM_SHARD;
        }
        else if (this.type == BlockType.CHEESE_MILK_ORE)
        {
            return ChalosItems.CHEESE_MILK_CURD;
        }
        return Item.getItemFromBlock(this);
    }

    @Override
    public boolean isValueable(IBlockState state)
    {
        return true;
    }

    @Override
    public int quantityDropped(IBlockState state, int fortune, Random rand)
    {
        if (this.type == BlockType.SETRORIUM_ORE)
        {
            int j = rand.nextInt(fortune + 2) - 1;

            if (j < 0)
            {
                j = 0;
            }
            return 1 + rand.nextInt(3) * j;
        }
        return this.quantityDropped(rand);
    }

    @Override
    public int getExpDrop(IBlockState state, IBlockAccess world, BlockPos pos, int fortune)
    {
        return this.type == BlockType.SETRORIUM_ORE || this.type == BlockType.CHEESE_MILK_ORE ? MathHelper.getInt(RANDOM, 3, 7) : 0;
    }

    @Override
    public EnumSortCategoryBlock getBlockCategory()
    {
        return EnumSortCategoryBlock.ORE;
    }

    public static enum BlockType
    {
        SETRORIUM_ORE,
        CHEESE_MILK_ORE;

        @Override
        public String toString()
        {
            return this.name().toLowerCase();
        }
    }
}