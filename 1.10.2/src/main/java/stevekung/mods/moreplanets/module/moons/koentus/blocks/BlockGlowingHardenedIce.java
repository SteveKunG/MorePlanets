package stevekung.mods.moreplanets.module.moons.koentus.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.util.CachedEnumUtil;
import stevekung.mods.moreplanets.util.blocks.BlockBreakableMP;
import stevekung.mods.moreplanets.util.helper.BlockStateHelper;

public class BlockGlowingHardenedIce extends BlockBreakableMP
{
    public BlockGlowingHardenedIce(String name)
    {
        super(Material.ICE);
        this.setUnlocalizedName(name);
        this.slipperiness = 0.98F;
        this.setSoundType(SoundType.GLASS);
        this.setDefaultState(this.getDefaultState().withProperty(BlockStateHelper.COLOR, EnumDyeColor.WHITE));
        this.setHardness(0.5F);
        this.setLightLevel(1.0F);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return state.getValue(BlockStateHelper.COLOR).getMetadata();
    }

    @Override
    public MapColor getMapColor(IBlockState state)
    {
        return state.getValue(BlockStateHelper.COLOR).getMapColor();
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(BlockStateHelper.COLOR, EnumDyeColor.byMetadata(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(BlockStateHelper.COLOR).getMetadata();
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, BlockStateHelper.COLOR);
    }

    @Override
    public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player)
    {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs creativeTabs, List<ItemStack> list)
    {
        for (EnumDyeColor color : CachedEnumUtil.valuesDyeCached())
        {
            list.add(new ItemStack(item, 1, color.getMetadata()));
        }
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return null;
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