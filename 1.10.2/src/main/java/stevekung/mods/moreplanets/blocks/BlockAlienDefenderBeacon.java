package stevekung.mods.moreplanets.blocks;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.tileentity.TileEntityAlienDefenderBeacon;
import stevekung.mods.moreplanets.util.blocks.BlockBaseMP;
import stevekung.mods.moreplanets.util.blocks.EnumSortCategoryBlock;

public class BlockAlienDefenderBeacon extends BlockBaseMP implements ITileEntityProvider
{
    public BlockAlienDefenderBeacon(String name)
    {
        super(Material.rock);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 2.0F / 16.0F, 1.0F);
        this.setResistance(1000000.0F);
        this.setHardness(-1.0F);
        this.setUnlocalizedName(name);
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack itemStack)
    {
        TileEntity tile = world.getTileEntity(pos);
        world.setBlockState(pos, this.getDefaultState());

        if (!world.isRemote)
        {
            if (placer instanceof EntityPlayer)
            {
                EntityPlayer player = (EntityPlayer) placer;

                if (tile instanceof TileEntityAlienDefenderBeacon)
                {
                    TileEntityAlienDefenderBeacon beacon = (TileEntityAlienDefenderBeacon) tile;
                    beacon.creativeSpawn = player.capabilities.isCreativeMode;
                }
            }
        }
    }

    @Override
    public int getRenderType()
    {
        return 3;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean isFullCube()
    {
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TileEntityAlienDefenderBeacon();
    }

    @Override
    public EnumSortCategoryBlock getBlockCategory(int meta)
    {
        return EnumSortCategoryBlock.DECORATION_NON_BLOCK;
    }

    @Override
    public String getName()
    {
        return "alien_defender_beacon";
    }
}