package stevekung.mods.moreplanets.utils.blocks;

import java.util.Random;

import micdoodle8.mods.galacticraft.api.block.IDetectableResource;
import micdoodle8.mods.galacticraft.core.GCItems;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import stevekung.mods.moreplanets.init.MPItems;

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
            return MPItems.SETRORIUM_SHARD;
        }
        else if (this.type == BlockType.CHEESE_MILK_ORE)
        {
            return MPItems.CHEESE_MILK_CURD;
        }
        else if (this.type == BlockType.COAL_ORE)
        {
            return Items.COAL;
        }
        else if (this.type == BlockType.DIAMOND_ORE)
        {
            return Items.DIAMOND;
        }
        else if (this.type == BlockType.LAPIS_ORE)
        {
            return Items.DYE;
        }
        else if (this.type == BlockType.EMERALD_ORE)
        {
            return Items.EMERALD;
        }
        else if (this.type == BlockType.SILICON_ORE)
        {
            return GCItems.basicItem;
        }
        else if (this.type == BlockType.QUARTZ_ORE)
        {
            return Items.QUARTZ;
        }
        else if (this.type == BlockType.INFECTED_COAL_ORE)
        {
            return MPItems.INFECTED_COAL;
        }
        else if (this.type == BlockType.INFERUMITE_CRYSTAL_ORE)
        {
            return MPItems.INFERUMITE_CRYSTAL;
        }
        return Item.getItemFromBlock(this);
    }

    @Override
    @Deprecated //TODO Remove 1.13
    public int damageDropped(IBlockState state)
    {
        if (this.type == BlockType.LAPIS_ORE)
        {
            return EnumDyeColor.BLUE.getDyeDamage();
        }
        else if (this.type == BlockType.SILICON_ORE)
        {
            return 2;
        }
        return 0;
    }

    @Override
    public boolean isValueable(IBlockState state)
    {
        return true;
    }

    @Override
    public int quantityDropped(IBlockState state, int fortune, Random rand)
    {
        if (this.type == BlockType.LAPIS_ORE)
        {
            return 4 + rand.nextInt(5);
        }

        int j = rand.nextInt(fortune + 2) - 1;

        if (j < 0)
        {
            j = 0;
        }
        return 1 + rand.nextInt(3) * j;
    }

    @Override
    public int getExpDrop(IBlockState state, IBlockAccess world, BlockPos pos, int fortune)
    {
        if (this.type == BlockType.COAL_ORE)
        {
            return MathHelper.getInt(RANDOM, 0, 2);
        }
        else if (this.type == BlockType.LAPIS_ORE)
        {
            return MathHelper.getInt(RANDOM, 2, 5);
        }
        else if (this.type == BlockType.QUARTZ_ORE)
        {
            return MathHelper.getInt(RANDOM, 2, 5);
        }
        else if (this.type == BlockType.INFERUMITE_CRYSTAL_ORE)
        {
            return 1 + RANDOM.nextInt(5);
        }
        return MathHelper.getInt(RANDOM, 3, 7);
    }

    @Override
    public EnumSortCategoryBlock getBlockCategory()
    {
        return EnumSortCategoryBlock.ORE;
    }

    public static enum BlockType
    {
        COAL_ORE,
        LAPIS_ORE,
        DIAMOND_ORE,
        EMERALD_ORE,
        SILICON_ORE,
        QUARTZ_ORE,
        SETRORIUM_ORE,
        CHEESE_MILK_ORE,
        GOLDENITE_CRYSTALS_ORE,
        INFECTED_COAL_ORE,
        INFERUMITE_CRYSTAL_ORE;

        @Override
        public String toString()
        {
            return this.name().toLowerCase();
        }
    }
}