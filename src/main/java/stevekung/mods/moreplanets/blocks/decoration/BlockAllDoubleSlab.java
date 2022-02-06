package stevekung.mods.moreplanets.blocks.decoration;

import java.util.Random;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.utils.blocks.BlockSlabMP;

public class BlockAllDoubleSlab extends BlockAllHalfSlab
{
    public BlockAllDoubleSlab(String name, BlockType type)
    {
        this(name, type, Material.ROCK);
    }

    public BlockAllDoubleSlab(String name, BlockType type, Material material)
    {
        super(material);
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
        else
        {
            this.setHardness(2.0F);
        }
        this.setTranslationKey(name);
    }

    @Override
    public boolean isDouble()
    {
        return true;
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState();
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return 0;
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(this.getHalf());
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(this.getHalf());
    }

    @Override
    public BlockSlabMP setHalf(BlockSlabMP halfSlab)
    {
        this.halfSlab = halfSlab;
        return this;
    }
}