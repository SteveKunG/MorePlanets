package stevekung.mods.moreplanets.utils.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.utils.client.renderer.IItemModelRender;

public abstract class BlockSlabMP extends BlockSlab implements ISortableBlock, ISlab, IItemModelRender
{
    private String name;

    public BlockSlabMP(Material material)
    {
        super(material);
    }

    @Override
    public Block setUnlocalizedName(String name)
    {
        this.name = name;
        return super.setUnlocalizedName(name);
    }

    @Override
    public boolean isDouble()
    {
        return false;
    }

    @Override
    public IProperty<?> getVariantProperty()
    {
        return null;
    }

    @Override
    public Comparable<?> getTypeForItem(ItemStack stack)
    {
        return null;
    }

    @Override
    public int quantityDropped(Random rand)
    {
        return this.isDouble() ? 2 : 1;
    }

    @Override
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return !this.isDouble() ? MorePlanetsMod.BLOCK_TAB : null;
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(this.getHalf());
    }

    @Override
    public String getUnlocalizedName(int meta)
    {
        return super.getUnlocalizedName();
    }

    @Override
    public String getName()
    {
        return this.name;
    }
}