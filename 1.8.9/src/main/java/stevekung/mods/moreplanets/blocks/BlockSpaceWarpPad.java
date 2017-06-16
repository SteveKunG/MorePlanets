package stevekung.mods.moreplanets.blocks;

import java.util.List;

import micdoodle8.mods.galacticraft.api.block.IPartialSealableBlock;
import micdoodle8.mods.galacticraft.api.world.IGalacticraftWorldProvider;
import micdoodle8.mods.galacticraft.core.blocks.BlockAdvancedTile;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.tileentity.TileEntitySpaceWarpPad;
import stevekung.mods.moreplanets.util.ItemDescription;
import stevekung.mods.moreplanets.util.blocks.EnumSortCategoryBlock;
import stevekung.mods.moreplanets.util.blocks.IBlockDescription;
import stevekung.mods.moreplanets.util.blocks.ISingleBlockRender;
import stevekung.mods.moreplanets.util.blocks.ISortableBlock;
import stevekung.mods.moreplanets.util.helper.ItemDescriptionHelper;

public class BlockSpaceWarpPad extends BlockAdvancedTile implements IPartialSealableBlock, IBlockDescription, ISortableBlock, ISingleBlockRender
{
    public BlockSpaceWarpPad(String name)
    {
        super(Material.iron);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 3.0F / 16.0F, 1.0F);
        this.setHardness(3.0F);
        this.setResistance(10.0F);
        this.setStepSound(soundTypeMetal);
        this.setUnlocalizedName(name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return MorePlanetsCore.BLOCK_TAB;
    }

    private boolean checkAxis(World world, BlockPos pos, EnumFacing facing)
    {
        int sameCount = 0;

        for (int i = 1; i <= 3; i++)
        {
            if (world.getBlockState(pos.offset(facing, i)).getBlock() == MPBlocks.SPACE_WARP_PAD)
            {
                sameCount++;
            }
        }
        return sameCount < 3;
    }

    @Override
    public boolean canPlaceBlockOnSide(World world, BlockPos pos, EnumFacing side)
    {
        if (!(GCCoreUtil.getDimensionID(world) == 0 || world.provider instanceof IGalacticraftWorldProvider))
        {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("gui.place_only_space.message")));
            return false;
        }
        if (!this.checkAxis(world, pos, EnumFacing.EAST) || !this.checkAxis(world, pos, EnumFacing.WEST) || !this.checkAxis(world, pos, EnumFacing.NORTH) || !this.checkAxis(world, pos, EnumFacing.SOUTH))
        {
            return false;
        }
        if (world.getBlockState(pos.offset(EnumFacing.DOWN)).getBlock() == MPBlocks.SPACE_WARP_PAD && side == EnumFacing.UP)
        {
            return false;
        }
        else
        {
            return this.canPlaceBlockAt(world, pos);
        }
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
        return new TileEntitySpaceWarpPad();
    }

    @Override
    public boolean isSealed(World world, BlockPos pos, EnumFacing direction)
    {
        return direction == EnumFacing.UP;
    }

    @Override
    public EnumSortCategoryBlock getBlockCategory(int meta)
    {
        return EnumSortCategoryBlock.MACHINE_NON_BLOCK;
    }

    @Override
    public ItemDescription getDescription()
    {
        return new ItemDescription()
        {
            @Override
            public void addDescription(ItemStack itemStack, List list)
            {
                list.addAll(ItemDescriptionHelper.getDescription(BlockSpaceWarpPad.this.getUnlocalizedName() + ".description"));
            }
        };
    }

    @Override
    public String getName()
    {
        return "space_warp_pad";
    }

    @Override
    public Block getBlock()
    {
        return this;
    }
}