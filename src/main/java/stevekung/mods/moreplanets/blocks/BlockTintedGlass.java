package stevekung.mods.moreplanets.blocks;

import java.util.Random;

import javax.annotation.Nullable;

import micdoodle8.mods.galacticraft.api.block.IPartialSealableBlock;
import net.minecraft.block.BlockBeacon;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.util.blocks.BlockBreakableMP;
import stevekung.mods.moreplanets.util.blocks.EnumSortCategoryBlock;

public class BlockTintedGlass extends BlockBreakableMP implements IPartialSealableBlock
{
    private final EnumDyeColor type;

    public BlockTintedGlass(String name, EnumDyeColor type)
    {
        super(Material.GLASS);
        this.setHardness(0.5F);
        this.setResistance(20.0F);
        this.setSoundType(SoundType.GLASS);
        this.setUnlocalizedName(name);
        this.type = type;
    }

    @Override
    @Nullable
    public float[] getBeaconColorMultiplier(IBlockState state, World world, BlockPos pos, BlockPos beaconPos)
    {
        return EntitySheep.getDyeRgb(this.type);
    }

    @Override
    public MapColor getMapColor(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return MapColor.getBlockColor(this.type);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public int quantityDropped(Random rand)
    {
        return 0;
    }

    @Override
    public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player)
    {
        return true;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state)
    {
        if (!world.isRemote)
        {
            BlockBeacon.updateColorAsync(world, pos);
        }
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state)
    {
        if (!world.isRemote)
        {
            BlockBeacon.updateColorAsync(world, pos);
        }
    }

    @Override
    public boolean isSealed(World world, BlockPos pos, EnumFacing facing)
    {
        return true;
    }

    @Override
    public EnumSortCategoryBlock getBlockCategory()
    {
        return EnumSortCategoryBlock.BUILDING_BLOCK;
    }

    @Override
    protected boolean isTranslucent()
    {
        return true;
    }

    @Override
    protected boolean renderSideWithState()
    {
        return true;
    }
}