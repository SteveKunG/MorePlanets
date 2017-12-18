package stevekung.mods.moreplanets.module.planets.nibiru.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature.WorldGenTerrashroom;
import stevekung.mods.moreplanets.util.VariantsName;
import stevekung.mods.moreplanets.util.blocks.BlockBushMP;
import stevekung.mods.moreplanets.util.blocks.IBlockVariants;

public class BlockNibiruFlower extends BlockBushMP implements IBlockVariants, IGrowable
{
    public static PropertyEnum VARIANT = PropertyEnum.create("variant", BlockType.class);

    public BlockNibiruFlower(String name)
    {
        super(Material.plants);
        this.setUnlocalizedName(name);
        this.setDefaultState(this.getDefaultState().withProperty(VARIANT, BlockType.PURE_HURB));
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, BlockPos pos)
    {
        int meta = this.getMetaFromState(world.getBlockState(pos));

        if (meta == 0)
        {
            this.setBlockBounds(0.3F, 0.0F, 0.3F, 0.7F, 0.6F, 0.7F);
        }
        else if (meta == 4)
        {
            this.setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 0.6F, 0.75F);
        }
        else if (meta == 5)
        {
            this.setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 0.9F, 0.75F);
        }
        else if (meta == 6)
        {
            this.setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 1.0F, 0.75F);
        }
        else if (meta == 7)
        {
            float f = 0.2F;
            this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
        }
        else
        {
            this.setBlockBounds(0.3F, 0.0F, 0.3F, 0.7F, 0.8F, 0.7F);
        }
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        if (this.getMetaFromState(state) == 7 && rand.nextInt(25) == 0)
        {
            int i = 5;

            for (BlockPos blockpos : BlockPos.getAllInBoxMutable(pos.add(-4, -1, -4), pos.add(4, 1, 4)))
            {
                if (world.getBlockState(blockpos).getBlock() == this)
                {
                    --i;

                    if (i <= 0)
                    {
                        return;
                    }
                }
            }

            BlockPos blockpos1 = pos.add(rand.nextInt(3) - 1, rand.nextInt(2) - rand.nextInt(2), rand.nextInt(3) - 1);

            for (int k = 0; k < 4; ++k)
            {
                if (world.isAirBlock(blockpos1) && this.canBlockStay(world, blockpos1, this.getStateFromMeta(7)))
                {
                    pos = blockpos1;
                }
                blockpos1 = pos.add(rand.nextInt(3) - 1, rand.nextInt(2) - rand.nextInt(2), rand.nextInt(3) - 1);
            }
            if (world.isAirBlock(blockpos1) && this.canBlockStay(world, blockpos1, this.getStateFromMeta(7)))
            {
                world.setBlockState(blockpos1, this.getStateFromMeta(7), 2);
            }
        }
    }

    @Override
    public int getLightValue(IBlockAccess world, BlockPos pos)
    {
        int meta = this.getMetaFromState(world.getBlockState(pos));

        if (meta == 0)
        {
            return 2;
        }
        else if (meta == 1 || meta == 6)
        {
            return 4;
        }
        else if (meta == 7)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list)
    {
        for (int i = 0; i < BlockType.valuesCached().length; ++i)
        {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    public boolean canBlockStay(World world, BlockPos pos, IBlockState state)
    {
        IBlockState blockDown = world.getBlockState(pos.down());
        IBlockState blockUp = world.getBlockState(pos.up());
        int meta = this.getMetaFromState(state);

        if (meta == 1)
        {
            return blockDown.getBlock() == NibiruBlocks.GREEN_VEIN_GRASS || blockDown.getBlock() == NibiruBlocks.INFECTED_DIRT;
        }
        else if (meta == 2)
        {
            return blockDown.getBlock() == NibiruBlocks.INFECTED_SAND;
        }
        else if (meta == 6)
        {
            return blockUp.getBlock() == NibiruBlocks.NIBIRU_LEAVES && blockUp.getValue(BlockNibiruLeaves.VARIANT) == BlockNibiruLeaves.BlockType.INFECTED_JUNGLE_LEAVES || blockUp.getBlock() == this && blockUp.getValue(BlockNibiruFlower.VARIANT) == BlockNibiruFlower.BlockType.VEALIUM_VINE_FLOWER;
        }
        else if (meta == 7)
        {
            boolean lowlight = world.getLight(pos) < 13 && (blockDown.getBlock() == NibiruBlocks.NIBIRU_BLOCK || blockDown.getBlock() == NibiruBlocks.INFECTED_DIRT || blockDown.getBlock() == NibiruBlocks.NIBIRU_ORE);
            return blockDown.getBlock() == NibiruBlocks.GREEN_VEIN_GRASS || blockDown.getBlock() == NibiruBlocks.TERRASTONE || blockDown.getBlock() == NibiruBlocks.PURIFY_GRAVEL || lowlight;
        }
        else
        {
            return blockDown.getBlock() == NibiruBlocks.INFECTED_GRASS || blockDown.getBlock() == NibiruBlocks.INFECTED_DIRT;
        }
    }

    @Override
    public boolean canGrow(World world, BlockPos pos, IBlockState state, boolean isClient)
    {
        return state.getValue(VARIANT) == BlockType.TERRASHROOM;
    }

    @Override
    public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state)
    {
        return rand.nextFloat() < 0.4D;
    }

    @Override
    public void grow(World world, Random rand, BlockPos pos, IBlockState state)
    {
        this.generateBigMushroom(world, pos, state, rand);
    }

    private boolean generateBigMushroom(World world, BlockPos pos, IBlockState state, Random rand)
    {
        world.setBlockToAir(pos);
        WorldGenerator worldgenerator = new WorldGenTerrashroom();

        if (worldgenerator.generate(world, rand, pos))
        {
            return true;
        }
        else
        {
            world.setBlockState(pos, state, 3);
            return false;
        }
    }

    @Override
    public int getDamageValue(World world, BlockPos pos)
    {
        return this.getMetaFromState(world.getBlockState(pos));
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return this.getMetaFromState(state);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumOffsetType getOffsetType()
    {
        return EnumOffsetType.XZ;
    }

    @Override
    public VariantsName getVariantsName()
    {
        return new VariantsName("pure_hurb", "terrapuff_hurb", "batasia_dandelion", "pyolonia_flower", "philipy_flower", "white_tail", "vealium_vine_flower", "terrashroom");
    }

    @Override
    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] { VARIANT });
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, BlockType.valuesCached()[meta]);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return ((BlockType)state.getValue(VARIANT)).ordinal();
    }

    public static enum BlockType implements IStringSerializable
    {
        PURE_HURB,
        TERRAPUFF_HURB,
        BATASIA_DANDELION,
        PYOLONIA_FLOWER,
        PHILIPY_FLOWER,
        WHITE_TAIL,
        VEALIUM_VINE_FLOWER,
        TERRASHROOM;

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