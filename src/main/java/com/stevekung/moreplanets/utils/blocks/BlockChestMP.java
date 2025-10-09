package com.stevekung.moreplanets.utils.blocks;

import com.stevekung.lib.utils.BlockStateProperty;
import com.stevekung.lib.utils.ColorUtils;
import com.stevekung.moreplanets.utils.itemblocks.ItemRarity;
import com.stevekung.moreplanets.utils.tileentity.TileEntityChestMP;

import net.minecraft.block.BlockChest;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public abstract class BlockChestMP extends BlockChest implements MorePlanetsBlock
{
    private final String name;
    private final Material newMaterial;

    protected BlockChestMP(String name, Material material)
    {
        super(Type.BASIC);
        this.newMaterial = material;
        this.name = name;
        this.setTranslationKey(name);
        this.setDefaultState(this.blockState.getBaseState().withProperty(BlockStateProperty.FACING_HORIZON, EnumFacing.NORTH));
    }

    protected BlockChestMP(String name)
    {
        this(name, Material.WOOD);
        this.setResistance(5.0F);
        this.setHardness(2.5F);
        this.setSoundType(SoundType.WOOD);
    }

    @Override
    public Material getMaterial(IBlockState state)
    {
        return this.newMaterial;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return this.getChestTile();
    }

    @Override
    public MPBlockCategory getBlockCategory()
    {
        return this.getTranslationKey().contains("ancient_chest") ? MPBlockCategory.ANCIENT_CHEST : MPBlockCategory.CHEST;
    }

    @Override
    public ColorUtils.RGB getRarityColor()
    {
        return this.getTranslationKey().contains("ancient_chest") ? ColorUtils.stringToRGB(ItemRarity.COMMON) : null;
    }

    @Override
    public String getModelName()
    {
        return this.name;
    }

    protected abstract TileEntityChestMP getChestTile();
}