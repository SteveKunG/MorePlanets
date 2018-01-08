package stevekung.mods.moreplanets.module.moons.koentus.blocks;

import java.util.Random;

import javax.annotation.Nullable;

import micdoodle8.mods.galacticraft.api.block.IDetectableResource;
import micdoodle8.mods.galacticraft.api.block.ITerraformableBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.entity.EntityAntiGravFallingBlock;
import stevekung.mods.moreplanets.module.planets.diona.items.DionaItems;
import stevekung.mods.moreplanets.util.VariantsName;
import stevekung.mods.moreplanets.util.blocks.BlockBasicMP;
import stevekung.mods.moreplanets.util.blocks.EnumSortCategoryBlock;
import stevekung.mods.moreplanets.util.blocks.IBlockVariants;

public class BlockKoentus extends BlockBasicMP implements IDetectableResource, ITerraformableBlock, IBlockVariants
{
    public static PropertyEnum<BlockType> VARIANT = PropertyEnum.create("variant", BlockType.class);

    public BlockKoentus(String name)
    {
        super(Material.ROCK);
        this.setDefaultState(this.getDefaultState().withProperty(VARIANT, BlockType.KOENTUS_SURFACE_ROCK));
        this.setUnlocalizedName(name);
    }

    @Override
    public void getSubBlocks(CreativeTabs creativeTabs, NonNullList<ItemStack> list)
    {
        for (int i = 0; i < BlockType.valuesCached().length; ++i)
        {
            list.add(new ItemStack(this, 1, i));
        }
    }

    @Override
    public boolean isBeaconBase(IBlockAccess world, BlockPos pos, BlockPos beacon)
    {
        IBlockState state = world.getBlockState(pos);
        return state.getValue(VARIANT) == BlockType.ANTI_GRAVITY_FRAGMENT_BLOCK || state.getValue(VARIANT) == BlockType.GOLDENITE_CRYSTAL_BLOCK;
    }

    @Override
    public float getBlockHardness(IBlockState state, World world, BlockPos pos)
    {
        int meta = this.getMetaFromState(state);

        if (meta == 0 || meta == 1)
        {
            return 1.25F;
        }
        if (meta == 2)
        {
            return 1.5F;
        }
        if (meta == 3)
        {
            return 2.0F;
        }
        if (meta >= 4 && meta <= 9)
        {
            return 3.0F;
        }
        if (meta == 10 || meta == 11)
        {
            return 5.0F;
        }
        return 4.0F;
    }

    @Override
    public float getExplosionResistance(World world, BlockPos pos, Entity exploder, Explosion explosion)
    {
        if (this.getMetaFromState(world.getBlockState(pos)) == 12)
        {
            return 40.0F;
        }
        return super.getExplosionResistance(world, pos, exploder, explosion);
    }

    @Override
    public SoundType getSoundType(IBlockState state, World world, BlockPos pos, @Nullable Entity entity)
    {
        return state.getValue(VARIANT) == BlockType.ANTI_GRAVITY_FRAGMENT_BLOCK || state.getValue(VARIANT) == BlockType.GOLDENITE_CRYSTAL_BLOCK ? SoundType.METAL : SoundType.STONE;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        if (this.getMetaFromState(state) == 4)
        {
            return DionaItems.DIONA_ITEM;
        }
        return Item.getItemFromBlock(this);
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        if (this.getMetaFromState(state) == 2)
        {
            return 3;
        }
        return this.getMetaFromState(state);
    }

    @Override
    public boolean isValueable(IBlockState state)
    {
        if (this.getMetaFromState(state) >= 4 && this.getMetaFromState(state) <= 9)
        {
            return true;
        }
        return false;
    }

    @Override
    public int quantityDropped(IBlockState state, int fortune, Random rand)
    {
        if (this.getMetaFromState(state) == 4)
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
        return this.getMetaFromState(state) == 4 ? MathHelper.getInt(new Random(), 3, 7) : 0;
    }

    @Override
    public boolean isTerraformable(World world, BlockPos pos)
    {
        int meta = this.getMetaFromState(world.getBlockState(pos));

        if ((meta == 0 || meta == 1) && !world.getBlockState(pos.up()).isOpaqueCube())
        {
            return true;
        }
        return false;
    }

    @Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state)
    {
        if (state.getValue(VARIANT) == BlockType.ANTI_GRAVITY_ORE || state.getValue(VARIANT) == BlockType.ANTI_GRAVITY_FRAGMENT_BLOCK)
        {
            world.scheduleUpdate(pos, this, 2);
        }
    }

    @Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos)
    {
        if (state.getValue(VARIANT) == BlockType.ANTI_GRAVITY_ORE || state.getValue(VARIANT) == BlockType.ANTI_GRAVITY_FRAGMENT_BLOCK)
        {
            world.scheduleUpdate(pos, this, 2);
        }
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        if (!world.isRemote)
        {
            if (state.getValue(VARIANT) == BlockType.ANTI_GRAVITY_ORE || state.getValue(VARIANT) == BlockType.ANTI_GRAVITY_FRAGMENT_BLOCK)
            {
                this.checkFallable(world, pos);
            }
        }
    }

    private void checkFallable(World world, BlockPos pos)
    {
        if ((world.isAirBlock(pos.up()) || BlockFalling.canFallThrough(world.getBlockState(pos.up()))) && pos.getY() < 256)
        {
            if (!BlockFalling.fallInstantly && world.isAreaLoaded(pos.add(-32, -32, -32), pos.add(32, 32, 32)))
            {
                if (!world.isRemote)
                {
                    EntityAntiGravFallingBlock block = new EntityAntiGravFallingBlock(world, pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, world.getBlockState(pos));
                    world.spawnEntity(block);
                }
            }
            else
            {
                IBlockState state = world.getBlockState(pos);
                world.setBlockToAir(pos);
                BlockPos blockpos;

                for (blockpos = pos.up(); (world.isAirBlock(blockpos) || BlockFalling.canFallThrough(world.getBlockState(blockpos))) && blockpos.getY() > 0; blockpos = blockpos.up()) {}

                if (blockpos.getY() < 256)
                {
                    world.setBlockState(blockpos.up(), state);
                }
            }
        }
    }

    @Override
    public EnumSortCategoryBlock getBlockCategory(int meta)
    {
        switch (meta)
        {
        case 0:
        case 1:
        case 2:
        case 3:
        default:
            return EnumSortCategoryBlock.BUILDING_BLOCK;
        case 4:
        case 5:
        case 6:
        case 7:
        case 8:
        case 9:
            return EnumSortCategoryBlock.ORE;
        case 10:
        case 11:
            return EnumSortCategoryBlock.INGOT_BLOCK;
        case 12:
            return EnumSortCategoryBlock.DUNGEON_BRICK;
        }
    }

    @Override
    public VariantsName getVariantsName()
    {
        return new VariantsName("surface_rock", "sub_surface", "rock", "cobblestone", "anti_gravity_ore", "goldenite_crystal_ore", "tin_ore", "copper_ore", "aluminum_ore", "iron_ore", "anti_grav_block", "goldenite_block", "dungeon_brick");
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, VARIANT);
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, BlockType.valuesCached()[meta]);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(VARIANT).ordinal();
    }

    public static enum BlockType implements IStringSerializable
    {
        KOENTUS_SURFACE_ROCK,
        KOENTUS_SUB_SURFACE_ROCK,
        KOENTUS_ROCK,
        KOENTUS_COBBLESTONE,
        ANTI_GRAVITY_ORE,
        GOLDENITE_CRYSTAL_ORE,
        KOENTUS_TIN_ORE,
        KOENTUS_COPPER_ORE,
        KOENTUS_ALUMINUM_ORE,
        KOENTUS_IRON_ORE,
        ANTI_GRAVITY_FRAGMENT_BLOCK,
        GOLDENITE_CRYSTAL_BLOCK,
        KOENTUS_DUNGEON_BRICK;

        private static BlockType[] values = BlockType.values();

        public static BlockType[] valuesCached()
        {
            return BlockType.values;
        }

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